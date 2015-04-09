package service;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import dao.UserDAO;

import util.RefreshToken;

public class UserService {
	
	public User getUserByOpenid(String openid){
		List<User> users = new UserDAO().findByWeixinOpenid(openid);
		if(users == null || users.size() == 0)
			return null;
		else
			return users.get(0);
	}
	
	public User getUserByUnionid(String unionid){
		List<User> users = new UserDAO().findByWeixinUnionid(unionid);
		if(users == null || users.size() == 0)
			return null;
		else
			return users.get(0);
	}
	
	public User getUserById(int id){
		return new UserDAO().findById(id);
	}
	
	
	public User registerWeixinUser(String openid){
		User weixinuser = getUserByOpenid(openid);
		if(weixinuser == null){
			weixinuser = getWeixinUser(openid);
			if(weixinuser == null)
				return null;
			
			Timestamp current_time = new Timestamp(System.currentTimeMillis()); 
			weixinuser.setRegTime(current_time);
			weixinuser.setLastLoginTime(current_time);
			weixinuser.setVerified(User.NO_VERIFIED);
			weixinuser.setPermission(User.PERMISSION_NORMAL);
			
			new UserDAO().save(weixinuser);
			
			return weixinuser;
		}else{
			return weixinuser;
		}
	}
	
	
	public User getWeixinUser(String openid){

		String turl = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", RefreshToken.current_token, openid);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(turl);
		JSONParser jsonparer = new JSONParser();// 初始化解析json格式的对象
		String result = null;
		
		User user = new User();
		
		try{
			HttpResponse res = client.execute(get);
			String responseContent = null; // 响应内容
			
			HttpEntity entity = res.getEntity();
			
			responseContent = EntityUtils.toString(entity, "UTF-8");
			
			JSONObject json = (JSONObject) jsonparer.parse(responseContent);
						
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				if (json.get("errcode") != null){
					return null;
				}
				else{// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
					result = json.get("subscribe").toString();
					if(result.equals("0"))
						return null;
					
					user.setWeixinOpenid(openid);
					user.setNickname(json.get("nickname").toString());
					user.setSex(new Integer(json.get("sex").toString()));
					user.setCity(json.get("city").toString());
					user.setCountry(json.get("country").toString());
					user.setProvince(json.get("province").toString());
					user.setWeixinHeadimg(json.get("headimgurl").toString());
					
					if(json.get("unionid") != null)
						user.setWeixinUnionid(json.get("unionid").toString());
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			// 关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
		}
		
		return user;
	}
	
	public static String getCookie(HttpServletRequest request, String cookie_name){
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				try {
					if (cookie.getName().trim()
							.equals(cookie_name)){
						return URLDecoder.decode(cookie.getValue(),"utf8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static User check_login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		String unionid = (String) session.getAttribute("unionid");
		if(openid == null)
			openid = getCookie(request ,"myficc_openid");
		if(unionid == null)
			unionid = getCookie(request ,"myficc_unionid");
		
		UserService us = new UserService();
		User userinfo = null;
		if(openid != null){
			userinfo = us.getUserByOpenid(openid);
		}
		if(unionid != null){
			userinfo = us.getUserByUnionid(unionid);
		}
		
		if(userinfo != null){
			session.setAttribute("unionid", userinfo.getWeixinUnionid());
		}
		
		
		if(request.getServerName().equals("localhost")){
			return us.getUserByOpenid("odOlcs7NIErgKoJXkwdKHbLGr6lY");
		}
		

		return userinfo;
	}
	
	public void modifyUser(User u){
		UserDAO ud = new UserDAO();
		ud.modify(u);
	}
	
	public List findAllOrderBy(String orderby, int offset, int count){
		return new UserDAO().findAllOrderBy(orderby, offset, count);
	}
	
	public static void main(String[] argvs){
		UserService us = new UserService();
		String openid = "odOlcs7NIErgKoJXkwdKHbLGr6lY";
		RefreshToken.current_token = "1wRQDbph-in8JrwfJeFKE4DME4JHRCCJhs2Vyq9r70BHh2hnqLu054WJdSZJVOVw1F5iBEAYW1nDYwqt13adfjDaPGJbRrnKdJMTrQx6ZFQ";
		
		us.getUserByOpenid(openid);
		us.registerWeixinUser(openid);
	}
}

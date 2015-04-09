package service;

import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
  
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
  

public class WeixinLoginService {

	public Map<String , String> getAccessTokenAndOpenid(String strCode,String strAppId,String strAppSecret){  
        Map<String , String> token = null;  
        
        String apiurl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String turl = String.format(
				"%s?appid=%s&secret=%s&code=%s&grant_type=%s", 
				apiurl, strAppId, strAppSecret, strCode, "authorization_code");
        
        //System.out.println(turl);
          
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
		
		JSONParser jsonparer = new JSONParser();// 初始化解析json格式的对象
		
        try {  
        	HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			
			//System.out.println(responseContent);
			
			
			JSONObject json = (JSONObject) jsonparer.parse(responseContent);
			
	        token = new HashMap<String, String>();  
	        if (json.get("errcode") != null) {
	        	token.put("errcode", json.get("errcode").toString());  
	            token.put("errmsg", json.get("errmsg").toString());  
	            return null;  
	        }else{
	        	token.put("access_token", json.get("access_token").toString());
	        	token.put("openid", json.get("openid").toString());
	        }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return  token;
    }  
	
	public Map<String , String> getUserInfo(String access_token,String openid){  
        Map<String , String> userinfo = null;  
        
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        
        String apiurl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String turl = String.format(
				"https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", 
        		access_token, openid);
        
          
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
		
		JSONParser jsonparer = new JSONParser();// 初始化解析json格式的对象
		
        try {  
        	HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			
			JSONObject json = (JSONObject) jsonparer.parse(responseContent);
			
			userinfo = new HashMap<String, String>();  
	        if (json.get("errcode") != null) {
	        	userinfo.put("errcode", json.get("errcode").toString());  
	        	userinfo.put("errmsg", json.get("errmsg").toString());  
	            return null;  
	        }else{
	        	userinfo.put("unionid", json.get("unionid").toString());
	        	userinfo.put("nickname", json.get("nickname").toString());
	        	userinfo.put("sex", json.get("sex").toString());
	        	userinfo.put("province", json.get("province").toString());
	        	userinfo.put("city", json.get("city").toString());
	        	userinfo.put("country", json.get("country").toString());
	        	userinfo.put("headimgurl", json.get("headimgurl").toString());
	        	userinfo.put("openid", json.get("openid").toString());
	        }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return  userinfo;
    }  
}

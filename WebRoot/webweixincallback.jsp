<%@ page language="java" import="java.util.*,util.*,service.*,config.*, model.*" pageEncoding="utf-8"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String state = request.getParameter("state");
	String code = request.getParameter("code");
	
	if(code == null){
		out.println("code = "+ code +" 登录失败!");
	}else{
		WeixinLoginService wxls = new WeixinLoginService();
		Map<String ,String> token = wxls.getAccessTokenAndOpenid(code, Config.WEB_APPID, Config.WEB_AppSecret);
		
		if(token == null){
			out.println("token = " + token +" 登录失败!");
		}else{
			Map<String , String> userinfo = wxls.getUserInfo(token.get("access_token"), token.get("openid"));
			
			if(userinfo == null){
				out.println("userinfo = " + userinfo +" 登录失败!");
			}else{
			
				UserService us = new UserService();
				
				User realuser = us.getUserByUnionid(userinfo.get("unionid"));
				if(realuser != null){
					realuser.setNickname(userinfo.get("nickname"));
					realuser.setWeixinHeadimg(userinfo.get("headimgurl"));
					us.modifyUser(realuser);
				}
				
				session.setAttribute("unionid", userinfo.get("unionid"));
				
				String cookieName="myficc_unionid"; 
				Cookie cookie=new Cookie(cookieName, userinfo.get("unionid")); 
				cookie.setMaxAge(365*24*60*60);
				response.addCookie(cookie); 
				
				response.sendRedirect(basePath + state + ".jsp");
			}
		}
	}
%>
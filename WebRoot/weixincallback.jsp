<%@ page language="java" import="java.util.*,util.*,service.*"
	pageEncoding="utf-8"%>
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
		Map<String ,String> token = wxls.getAccessTokenAndOpenid(code, RefreshToken.APP_ID, RefreshToken.SECRET);
		
		if(token == null){
			out.println("token = " + token +" 登录失败!");
		}else{
			session.setAttribute("openid", token.get("openid"));
			response.sendRedirect(basePath + state + ".jsp");
		}
	}
	
%>
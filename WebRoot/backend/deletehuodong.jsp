<%@ page language="java"
	import="java.util.*, service.*, model.*, config.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%

request.setCharacterEncoding ("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();

String aid = request.getParameter("aid");
Activity a = null;

ActivityService as = new ActivityService();

try{
	
	Integer aintid = Integer.parseInt(aid);
	a = as.findActivityWithExtraData(aintid);
		
}catch(Exception e){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
}

User userinfo = us.check_login(request);

if(userinfo == null || a == null){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
}

as.deleteActivity(a.getId());

response.sendRedirect(basePath + "userinfo.jsp?type=faqi&message=delete_success");
%>


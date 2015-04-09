<%@ page language="java" import="java.util.*,util.*,service.*,config.*" pageEncoding="utf-8"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	Cookie deleteNewCookie=new Cookie("myficc_unionid",null);
	deleteNewCookie.setMaxAge(0);
	deleteNewCookie.setPath("/"); 
	response.addCookie(deleteNewCookie);

	Cookie deleteOpenidCookie=new Cookie("myficc_openid",null);
	deleteOpenidCookie.setMaxAge(0);
	deleteOpenidCookie.setPath("/");
	response.addCookie(deleteOpenidCookie);

	session.removeAttribute("unionid");
	session.removeAttribute("openid");
	
	response.sendRedirect(basePath + "huodongs.jsp");
%>
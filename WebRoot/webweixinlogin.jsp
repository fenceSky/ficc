<%@ page language="java" import="java.util.*, config.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String message = request.getParameter("message");

if(message == null)
	message = "您还未登录，请通过微信登录。";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>领金社区微信登录</title>
<link rel='stylesheet' id='main-css' href='style/style.css'
	type='text/css' media='all' />


<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>

</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
		
			<jsp:param name="thispage" value="huodongs" /> 
		</jsp:include>
		
		<div class="content-wrap">
			<div id="content_grid" class="content">
				<%=message %>
				<a target="_blank" onclick="weixincode(); return false;" href="#"><img src="./images/icon48_wx_button.png"/></a><br>
			</div>
		</div>
		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
</body>
</html>
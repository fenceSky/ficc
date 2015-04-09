<%@ page language="java" import="java.util.*, service.*, model.*, dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
ArrayList<User> users = (ArrayList<User>)us.findAllOrderBy("regTime", 0, 1000);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>领金社区-用户认证</title>
<link rel='stylesheet' id='main-css' href='style/admin_style.css'
	type='text/css' media='all' />

</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<header class="header">
			<div class="logo">
				<a href="<%=basePath %>admin.jsp" title="领金活动">领金后台管理</a>
			</div>
			<ul class="nav">
				<li id="menu-item-8"
					class="menu-item menu-item-type-taxonomy current-menu-item menu-item-object-category menu-item-8"><a
					href="<%=basePath %>admin_user.jsp">用户认证</a>
				</li>
				
				<li id="menu-item-7"
					class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7"><a
					href="<%=basePath %>admin_activity.jsp">活动管理</a>
				</li>
			</ul>
			<!--  
			<div class="slinks"></div>
			<div class="user-welcome">
				<strong>root</strong><span class="text-muted">æ¬¢è¿ç»å½ï¼</span>
			</div>
			<p class="user-logout">
				<a
					href="http://123.56.46.250/wp-login.php?action=logout&amp;_wpnonce=d2573c910c">éåº</a>
			</p>
			-->
		</header>
		<div class="content-wrap">
			<div id="content_grid" class="content">
				<h4>用户管理：</h4>
				<table border="1">
				<tr>
				  <th>微信昵称</th>
				  <th>微信头像</th>
				  <th>email</th>
				</tr>
				<%for(int i = 0; i < users.size(); i++){ 
					User u = users.get(i);
				%>
				<tr>
				  <td><%=u.getNickname() %></td>
				  <td><img src="<%=u.getWeixinHeadimg() %>" width="35" height="35" /></td>
				  <td><%=u.getEmail() %></td>
				</tr>
				<%} %>
				
				</table>
			</div>
		</div>
</body>
</html>
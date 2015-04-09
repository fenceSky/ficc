<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<style>
a:hover,a:focus,.post-like.actived,.excerpt h2 a:hover,.user-welcome strong,.article-title a:hover,#comments b,.text-muted a:hover,.relates a:hover,.archives .item:hover h3,.linkcat h2,.sticky a:hover,.article-content a:hover,.nav li.current-menu-item>a,.nav li.current-menu-parent>a,.nav li.current_page_item>a,.nav li.current-posa,.article-meta a:hover
	{
	color: #1e73be;
}

.logo a,.article-tags a,.search-form .btn,.widget_tags_inner a:hover:hover,.focusmo a:hover h4,.tagslist .tagname:hover,.pagination ul>li.next-page>a
	{
	background-color: #1e73be;
}

.label-important,.badge-important {
	background-color: #1e73be;
}

.label-important .label-arrow,.badge-important .label-arrow {
	border-left-color: #1e73be;
}

.title strong {
	border-bottom-color: #1e73be;
}

#submit {
	background: #1e73be;
	border-right: 2px solid #1e73be;
	border-bottom: 2px solid #1e73be;
}

</style>


<link rel="stylesheet" type="text/css" href="js/extjs/ext-theme-neptune/build/resources/ext-theme-neptune-all.css">
       <script type="text/javascript" src="js/extjs/ext-all.js"></script> 
       <script type="text/javascript" src="js/extjs/ext-theme-neptune/build/ext-theme-neptune.js"></script>

        <script type ="text/javascript" src="js/app.js"></script>

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
					href="<%=basePath %>admin.jsp">用户认证</a>
				</li>
				
				<li id="menu-item-7"
					class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7"><a
					href="<%=basePath %>admin_huodongguanli.jsp">活动管理</a>
				</li>
				<li id="menu-item-10"
					class="menu-item menu-item-type-custom menu-item-object-custom menu-item-10"><a
					href="<%=basePath %>admin_baoming.jsp">报名审核</a>
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
				
			</div>
		</div>
</body>
</html>
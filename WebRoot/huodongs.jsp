<%@ page language="java" import="java.util.*, config.*,service.*, model.*"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
User userinfo = us.check_login(request);

ActivityService as = new ActivityService();
ArrayList<Activity> index_activities = as.findRecentOpenActivitiesWithExtraData(20);
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>领金活动-领金社区</title>
<link rel='stylesheet' id='main-css' href='style/style.css'
	type='text/css' media='all' />
<style>
/*
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
}*/
</style>



</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
		
			<jsp:param name="thispage" value="huodongs" /> 
		</jsp:include>
		<div class="content-wrap">
			<div class="content">
				<h1 class="title">
					<strong><a href="http://123.56.46.250/?cat=4">领金活动</a> </strong>
				</h1>
				<%for(int i = 0; i < index_activities.size(); i++){ 
					Activity a = index_activities.get(i);
				%>
				<article class="excerpt excerpt-one">
					<header>
						<h2>
							<a href="<%=basePath %>activity.jsp?activity_id=<%=a.getId() %>"
								title="<%=a.getTitle() %>"><%=a.getTitle() %></a>
								
						</h2>
					</header>
					<p class="focus">
						<a href="<%=basePath %>activity.jsp?activity_id=<%=a.getId() %>" class="thumbnail"><img
							data-original="" class="thumb"
							src="<%=a.getImageUrl()%>">
						</a>
					</p>
					<p class="note"><%=a.getIntro() %></p>
					<p class="text-muted views">
						<span class="post-views">报名人数：<%=a.getSignup_users().size() %>/<%=a.getTotalPeople() %></span>
						<span class="post-comments">费用：<%=a.getFee() %>元</span>
					</p>
				</article>
				
				<%} %>
			</div>
		</div>
		
		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
	</body>
</html>
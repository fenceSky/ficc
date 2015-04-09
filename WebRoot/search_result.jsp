<%@ page language="java" import="java.util.*, service.*, model.*, config.*, java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

request.setCharacterEncoding ("utf-8");

String search_word = request.getParameter("s");
ArrayList<Activity> search_result = new ArrayList<Activity>();
if(search_word == null || search_word.trim().equals("")){
	search_word = "";
}else{
	ActivityService as = new ActivityService();
	search_result = as.search(search_word);
}

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

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
			<jsp:param name="thispage" value="huodongs" /> 
		</jsp:include>
		<div class="content-wrap">
			<div class="content">
				<h1 class="title" style="margin-top:10px">
					"<%=search_word %>" 搜索结果
				</h1>
				
				
				<%for(int i = 0; i < search_result.size(); i++){ 
					Activity a = search_result.get(i);
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


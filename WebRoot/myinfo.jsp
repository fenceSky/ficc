<%@ page language="java" import="java.util.*, service.*, model.*, dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
User userinfo = us.check_login(request);

if(userinfo == null){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	
	return;
}else{


LogDAO ld = new LogDAO();
ArrayList<Log> logs = (ArrayList<Log>)ld.findByUserId(userinfo.getId());

%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>个人信息-领金社区</title>
<link rel='stylesheet' id='main-css' href='style/style.css'
	type='text/css' media='all' />
	<script src="js/jquery.min.1.4.2.js" type="text/javascript"></script>
	<script>
function clicklog(log_id){
	$('#log_content_'+log_id).slideToggle();
}
</script>
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

<script
	src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>

</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
		
			<jsp:param name="thispage" value="myinfo" /> 
		</jsp:include>
		<div class="content-wrap">
			<div class="content">
				<h1 class="title">
					<strong><a href="http://123.56.46.250/?cat=4">个人信息</a> </strong>
				</h1>
				
				欢迎<%=userinfo.getNickname() %>登录领金社区！
				<img width="100px" src="<%=userinfo.getWeixinHeadimg() %>" />
				
				<h1 class="title" style="margin-top:10px">
					个人动态
				</h1>
				
				<%for(int i = 0 ; i < logs.size(); i++){
					Log l = logs.get(i);
				 %>
				 <article class="excerpt" style="padding:15px 0">
					<header>
						<h4 style="font-size:14px">
						<%=l.getContent() %>
						<%
						String span = "";
						switch(l.getType()){
							case 1: span = "<span id='noreadspan_' style='background-color:red' class='post-views no_read'>发起</span>";break;
							case 2: span = "<span id='noreadspan_' style='background-color:blue' class='post-views no_read'>删除</span>";break;
							case 3: span = "<span id='noreadspan_' style='background-color:fuchsia' class='post-views no_read'>报名</span>";break;
							case 4: span = "<span id='noreadspan_' style='background-color:limegreen' class='post-views no_read'>取消</span>";break;
							case 5: span = "<span id='noreadspan_' style='background-color:sandybrown' class='post-views no_read'>点赞</span>";break;
							case 6: span = "<span id='noreadspan_' style='background-color:dodgerblue' class='post-views no_read'>取消</span>";break;
							case 7: span = "<span id='noreadspan_' style='background-color:violet' class='post-views no_read'>评论</span>";break;
							case 8: span = "<span id='noreadspan_' style='background-color:silver' class='post-views no_read'>撤销</span>";break;
						}
						
						 %>
							<%=span %>
							
						</h4>
					</header>
					
				</article>
				<%} %>				
			</div>
		</div>
		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
</body>
</html>
<%} %>


<%@ page language="java" import="java.util.*, service.*, model.*, config.*, java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String signup_type = request.getParameter("signup_type");
String activity_id = request.getParameter("activity_id");
String user_id = request.getParameter("user_id");

if(signup_type == null || activity_id == null || user_id == null){
	System.out.println("1");
	response.sendRedirect(basePath + "webweixinlogin.jsp?message=报名过程出错，请重新报名!");
	return;
}

Integer aid = -1;
Integer uid = -1;
try{
	aid = Integer.parseInt(activity_id);
	uid = Integer.parseInt(user_id);
}catch(Exception e){
	System.out.println("2");
	response.sendRedirect(basePath + "webweixinlogin.jsp?message=报名过程出错，请重新报名!");
	return;
}

UserService us = new UserService();
User userinfo = us.check_login(request);

ActivityService as = new ActivityService();


ArrayList<Activity> recent_activities = as.findRecentOpenActivities(5);
Activity thisa = as.findActivity(aid);

if(userinfo == null){
	System.out.println("3");
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
}

System.out.println("3 out");

if(!userinfo.getId().equals(uid) || thisa == null || !thisa.getState().equals(Activity.PASS_VERIFIED)){
	System.out.println("4");
	
	System.out.println(!signup_type.equals("baoming"));
	System.out.println(!userinfo.getId().equals(uid));
	
	System.out.println(thisa == null);
	
	System.out.println(!thisa.getState().equals(Activity.PASS_VERIFIED));
	
	response.sendRedirect(basePath + "webweixinlogin.jsp?message=报名过程出错，请重新报名!");
	return;
}

int signup_result = ActivityService.SIGNUP_ALREADY;

if(signup_type.equals("baoming"))
	signup_result = as.signup(aid, uid);
else if(signup_type.equals("cancelbaoming")){
	signup_result = as.cancelsignup(aid, uid);
}

String type = "baoming";
ArrayList<Activity> activities = as.findActivityByUserid(uid, "baoming");
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

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
			<jsp:param name="thispage" value="userinfo" /> 
		</jsp:include>
		<div class="content-wrap">
			<div class="content">
				<h1 class="title" style="margin-top:10px">
					<strong style="<%if(!type.equals("baoming")){%>border-bottom: 0px;<%}%>">                <a href="<%=basePath%>userinfo.jsp?type=baoming">我报名的</a> </strong>
					<strong style="<%if(!type.equals("canjia")){%>border-bottom: 2px;<%}%> margin-left:5px"><a href="<%=basePath%>userinfo.jsp?type=canjia">我参加的</a> </strong>
					<strong style="<%if(!type.equals("faqi")){%>border-bottom: 2px;<%}%> margin-left:5px"><a href="<%=basePath%>userinfo.jsp?type=faqi">我发起的</a> </strong>
					<strong style="<%if(!type.equals("faqihuodong")){%>border-bottom: 2px;<%}%> margin-left:5px"><a href="<%=basePath%>userinfo.jsp?type=faqihuodong">发起活动</a> </strong>
				</h1>
				
				
				<h2 style="color:green">
				<%
				if(signup_result == ActivityService.SIGNUP_FAILURE){
					out.println("报名过程出错，报名失败！");
				}else if(signup_result == ActivityService.SIGNUP_ALREADY){
					out.println("您已报名过该活动！");
				}else if(signup_result == ActivityService.SIGNUP_NO_REALNAME_VERIFIED){
					out.println("您还未进行实名认证，无法报名活动，请关注 myFicc 微信服务号，联系客服进行实名认证！");
				}else if(signup_result == ActivityService.SIGNUP_OVER_PEOPLE){
					out.println("很抱歉，该活动报名人数已满！");
				}else if(signup_result == ActivityService.SIGNUP_SUCCESS){
					out.println("恭喜您，报名活动成功啦！");
				}else if(signup_result == ActivityService.CANCEL_SIGNUP_NOT_EXIST){
					out.println("您还没有报名该活动！");
				}else if(signup_result == ActivityService.CANCEL_SIGNUP_SUCCESS){
					out.println("取消报名该活动成功！");
				}

				 %>
				</h2>
				
				<%for(int i = 0; i < activities.size(); i++){ 
					Activity a = activities.get(i);
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


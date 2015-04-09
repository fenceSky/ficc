<%@ page language="java" import="java.util.*, service.*, model.*, java.text.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
User userinfo = us.check_login(request);


ActivityService as = new ActivityService();
ArrayList<Activity> recent_activities = as.findRecentOpenActivities(5);

if(userinfo == null){
	out.println("登陆错误！");
}else{

MessageService ms = new MessageService();


ArrayList<Message> messages = ms.findMessagesByToUser(userinfo.getId());

int unread_count = 0;
for(int i = 0; i < messages.size(); i++){
	if(messages.get(i).getState() == Message.STATE_NO_READ)
		unread_count++;
}


SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>我的消息-领金社区</title>
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

<style>
				
				
</style>
				
<script src="js/jquery.min.1.4.2.js" type="text/javascript"></script>
<script
	src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script>
function clickmessage(message_id, read_state){
	if(read_state == 1){
		$.ajax({     
    	url:'backend/clickmessage.jsp',     
    	type:'post',     
    	data:'message_id='+message_id+'&read_state='+2,     
    	dataType:'text',
    	error:function(){     
       		
    	},
    	
    	success:function(data){     
    		
    		$('#noreadspan_'+message_id).hide();
    		//hide no read
    	}  
	});  
	}
	$('#message_content_'+message_id).slideToggle();
}
</script>

</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
		<jsp:include page="header.jsp">
			<jsp:param name="thispage" value="mymessage" /> 
		</jsp:include>
		
		<div class="content-wrap">
			<div class="content">
				<h1 class="title">
					<strong><a href="http://123.56.46.250/?cat=4">我的消息</a> </strong>
				</h1>
					
				
				<%for(int i = 0 ; i < messages.size(); i++){ 
					Message m = messages.get(i);
				%>
				<article class="excerpt" style="padding:15px 0">
					<header>
						<h4>
							<a href="#"
								title="<%=m.getTitle() %>" onclick="clickmessage(<%=m.getId() %>, 1);return false;"><%=m.getTitle() %></a>
							<%if(m.getState() == Message.STATE_NO_READ){ %>
							<span id="noreadspan_<%=m.getId() %>" class="post-views no_read">未读</span>
							<%} %>
							<span class="post-views time_span"><%=m.getFromUsername() %> 发于<%=df.format(m.getCtime())%></span>
						</h4>
					</header>
					<div id="message_content_<%=m.getId() %>" style="display: none;">
					<p><%=m.getContent() %></p>
					</div>
				</article>
				<%} %>
				
			</div>
		</div>

		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
</body>
</html>
<%} %>


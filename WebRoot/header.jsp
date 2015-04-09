<%@ page language="java" import="java.util.*, service.*, model.*,config.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	UserService us = new UserService();
	User userinfo = us.check_login(request);
	
	String thispage = request.getParameter("thispage");
%>

<script
	src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>

<header class="header">
	<div class="logo">
		<a href="<%=basePath %>huodongs.jsp" title="领金活动">领金活动</a>
	</div>
	<ul class="nav">
		<li id="menu-item-8"
			class="menu-item menu-item-type-taxonomy <%if(thispage.equals("huodongs")) {%>current-menu-item<%} %> menu-item-object-category menu-item-8"><a
			href="<%=basePath %>huodongs.jsp">领金活动</a></li>
			
		<%if(userinfo != null){ 
		MessageService ms = new MessageService();
		int unread_count = ms.getUnreadCount(userinfo.getId());
		
		%>
		<li id="menu-item-8"
			class="menu-item menu-item-type-taxonomy menu-item-object-category <%if(thispage.equals("userinfo")) {%>current-menu-item<%} %> menu-item-8"><a
			href="<%=basePath %>userinfo.jsp">我的活动</a></li>
		<li id="menu-item-7"
			class="menu-item menu-item-type-taxonomy menu-item-object-category <%if(thispage.equals("mymessage")) {%>current-menu-item<%} %> menu-item-7"><a
			href="<%=basePath %>mymessage.jsp">我的消息 <%
			if (unread_count > 0) {
			%><span
				class="unread_count"> <%=unread_count%> </span>
				<%
					}
				%>
			</a></li>
		<li id="menu-item-10"
			class="menu-item menu-item-type-custom menu-item-object-custom <%if(thispage.equals("myinfo")) {%>current-menu-item<%} %> menu-item-10"><a
			href="<%=basePath %>myinfo.jsp">个人主页</a></li>
		<%} %>
	</ul>

	<div class="user-signin">
			<%if(userinfo == null){ %>

				<script type="text/javascript">
			function weixincode(){
				var callback = encodeURIComponent("http://www.ihaomen.com/webweixincallback.jsp");
				var weixinurl = "https://open.weixin.qq.com/connect/qrconnect?appid=<%=Config.WEB_APPID %>&redirect_uri="+
					callback
					+"&response_type=code&scope=snsapi_login&state=userinfo#wechat_redirect";
				
				window.location.href = weixinurl;
				//return false;
			}
			
			</script>

				<a target="_blank" onclick="weixincode(); return false;" href="#"><img
					width="150px" src="./images/icon48_wx_button.png" /> </a><br>
			<%}else{ %>
				欢迎 <a href="<%=basePath%>userinfo.jsp"><%=userinfo.getNickname() %></a> 登录领金社区！
				<a href="<%=basePath%>userinfo.jsp"><img width="100px" src="<%=userinfo.getWeixinHeadimg() %>" /></a>
				<a href="<%=basePath%>/quit.jsp">退出</a>
				<br>
			<%} %>
			</div>

	<!--  
			<div class="slinks"></div>
			<div class="user-welcome">
				<strong>root</strong><span class="text-muted">欢迎登录！</span>
			</div>
			<p class="user-logout">
				<a
					href="http://123.56.46.250/wp-login.php?action=logout&amp;_wpnonce=d2573c910c">退出</a>
			</p>
			-->
</header>


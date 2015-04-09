<%@ page language="java" import="java.util.*, service.*, model.*, config.*, java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String type = request.getParameter("type");
String message = request.getParameter("message");

if(type == null)
	type = "baoming";


UserService us = new UserService();
User userinfo = us.check_login(request);

if(userinfo == null){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
}

ActivityService as = new ActivityService();

ArrayList<Activity> activities = as.findActivityByUserid(userinfo.getId(), type);
ArrayList<Activity> recent_activities = as.findRecentOpenActivities(5);


String ua = ((HttpServletRequest) request).getHeader("user-agent")
          .toLowerCase();

boolean weixinbrowser = false;    
if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
   weixinbrowser = true;
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
<script src="js/ajaxupload.js" type="text/javascript"></script>
<script src="js/jquery.min.1.4.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		
		/* 上传头像 */
		var button = $('#upload_file'), interval;
		new AjaxUpload(button,{
			action: 'backend/upload.jsp?type=file', 
			name: 'myfile',
			onSubmit : function(file, ext){
				button.text('上传活动材料');
				this.disable();
				
				interval = window.setInterval(function(){
					var text = button.text();
					if (text.length < 11){
						button.text(text + '.');					
					} else {
						button.text('上传活动材料');				
					}
				}, 200);
			},
			onComplete: function(file, response){
				button.text('上传活动材料成功！');
				window.clearInterval(interval);
				this.enable();
				if(response == "error"){
					alert("上传失败！文件过大或格式不正确！");					
				}else{
					 $('#attach_file').val("<%=basePath%>"+"upload/file/"+response);
				}
			}
		});
		
		var button2 = $('#upload_image'), interval;
		new AjaxUpload(button2,{
			action: 'backend/upload.jsp?type=image', 
			name: 'myfile',
			onSubmit : function(file, ext){
				button2.text('上传活动配图');
				this.disable();
				
				interval = window.setInterval(function(){
					var text = button2.text();
					if (text.length < 11){
						button2.text(text + '.');					
					} else {
						button2.text('上传活动配图');				
					}
				}, 200);
			},
			onComplete: function(file, response){
				button2.text('上传活动配图成功！');
				window.clearInterval(interval);
				this.enable();
				if(response == "error"){
					alert("上传失败！文件过大或格式不正确！");					
				}else{
				
				
					$('#attach_image').val("<%=basePath%>"+"upload/image/"+response);
					var myimg = document.getElementById("attach_image_display");
					myimg.src="<%=basePath%>"+"upload/image/"+response;
				}
			}
		});
	});
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
					<strong style="<%if(!type.equals("faqi")){%>border-bottom: 2px ;<%}%> margin-left:5px"><a href="<%=basePath%>userinfo.jsp?type=faqi">我发起的</a> </strong>
					<strong style="<%if(!type.equals("faqihuodong")){%>border-bottom: 2px;<%}%> margin-left:5px"><a href="<%=basePath%>userinfo.jsp?type=faqihuodong">发起活动</a> </strong>
				</h1>
				
				<%if(message != null) {%>
					<%if(message.equals("faqi_success")){ %>
					<h2 style="color:green">
						发起活动成功，我们的客服将在1个工作日内以通过微信服务号告知您审核结果！
					</h2>
					<%}else if(message.equals("edit_success")){ %>
					<h2 style="color:green">
						修改活动成功，我们的客服将在1个工作日内以通过微信服务号告知您审核结果！
					</h2>
					<%} %>
				<%} %>
				
				<%if(!type.equals("faqihuodong")){ %>
				
				<%for(int i = 0; i < activities.size(); i++){ 
					Activity a = activities.get(i);
				%>
				<article class="excerpt excerpt-one">
					<header>
						<h2>
							<a href="<%=basePath %>activity.jsp?activity_id=<%=a.getId() %>"
								title="<%=a.getTitle() %>"><%=a.getTitle() %></a>
								<%if(type.equals("faqi")){ %>
								<a href="<%=basePath %>editactivity.jsp?activity_id=<%=a.getId() %>"><span class="post-views no_read" style="background-color:green">修改</span></a>
								
								<%} %>
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
				<!-- 
				<div class="pagination">
					<ul>
						<li class="prev-page"></li>
						<li class="next-page"><a
							href="http://123.56.46.250/?cat=4&amp;paged=2">下一页</a></li>
					</ul>
				</div>
				 -->
				<%}else{ %>
				
				<style>
				.huodong_input{
				margin-right: 10px;
				position: relative;
				border: 2px solid #CCD4D9;
				border-radius: 2px;
				height: 34px;
				padding: 3px 5px;
				outline: none;
				width: 100%;
				box-shadow: none;
				}
				
				.huodong_input_div{
					width:100%; 
					margin-bottom:15px;
					
				}
				
				.huodong_input_select{
					width:80px;
					
				}
				
				.huodong_input_span{
					width:90px;
					display:block; 
					float:left;
					padding-top:5px;
				}
				
				</style>
				
				<form id="newactivity" method="post" style="padding-top:10px;padding-right:5px;padding-bottom:15px" action="<%=basePath %>backend/faqihuodong.jsp" accept-charset="utf-8">
					<div class="huodong_input_div">
					<span class="huodong_input_span">活动名称：</span>
					<input class="huodong_input" name="title" id="title" type="text"/> <br />
					</div>
					<div class="huodong_input_div">
					活动开始时间：<br />
					<%
						Timestamp ctime = new Timestamp(System.currentTimeMillis()); 
					%>
					
							<select class="huodong_input_select" id="start_year" name="start_year">
							<option value="2015" selected="selected">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							</select>
							年
							<select class="huodong_input_select" id="start_month" name="start_month" >
							<%for(int i = 1; i <= 12; i++){ %><option <%if(ctime.getMonth()+1 == i){ %>selected="selected"<%} %> value="<%=i %>"><%=i %></option><%} %>
							</select>
							月
							<select class="huodong_input_select" id="start_day" name="start_day" >
							<%for(int i = 1; i <= 31; i++){ %><option <%if(ctime.getDate() == i){ %>selected="selected"<%} %> value="<%=i %>"><%=i %></option><%} %>
							</select>
							日
							<br />
							<select class="huodong_input_select" id="start_hour" name="start_hour" >
							<%for(int i = 1; i <= 24; i++){ %><option <%if(ctime.getHours() == i){ %>selected="selected"<%} %> value="<%=i %>"><%=i %></option><%} %>
							</select>
							时
							<select class="huodong_input_select" id="start_minite" name="start_minite" >
							<%for(int i = 0; i <= 59; i++){ %><option <%if(i == 0){ %>selected="selected"<%} %> value="<%=i %>"><%=i %></option><%} %>
							</select>
							分
 							
 					</div>
					<div class="huodong_input_div">
					活动时长：<br />
					<select class="huodong_input_select" id="last_time" name="last_time" >
							<%for(int i = 1; i <= 12; i++){ %><option <%if(i == 0){ %>selected="selected"<%} %> value="<%=0.5 * i %>"><%=0.5 * i %></option><%} %>
							</select>个小时 <br />
					</div>
					<div class="huodong_input_div">
					活动地点：<br />
							<select class="huodong_input_select" id="province" name="province" >
							<%for(String province : Config.PROVINCES){ %>
								<option value="<%=province%>"><%=province%></option>
							<%} %>
							</select>省 <br />
							
							<input class="huodong_input" style="margin-top:5px" id="detail_addr" name="detail_addr" type="text"/>
					</div>
					<div class="huodong_input_div">
					参加人数：<br />
					<input style="width:100px" class="huodong_input" id="total_people"  name="total_people" type="number" min="1" max="10000"  value="20"/>人
							<br />
					</div>
					<div class="huodong_input_div">
					人均费用：<br />
							<input style="width:100px" class="huodong_input" id="fee"  name="fee" type="number" min="0" max="10000"  value="50"/>元
							<br /><font style="color:grey; font-size:10px">说明：活动费用由发起人自行向参会者收取</font>
					</div>
					<div class="huodong_input_div">
					主讲人&nbsp;&nbsp;：<input class="huodong_input" id="speaker_name"  name="speaker_name" type="text"/><br />
					</div>
					<div class="huodong_input_div">
					主讲人简介：
					<textarea form="newactivity" id="speaker_intr" name="speaker_intr"  class="huodong_input" style="height:80px"></textarea><br />
					</div>
					<div class="huodong_input_div" <%if(weixinbrowser){ %>style="display:none"<%} %>>
					活动材料：
					<div id="upload_file" class="button" >上传活动材料(小于50M，可选)</div>
					<font style="color:grey; font-size:10px">说明：材料上传有问题请联系客服</font>
					<input type="hidden" id="attach_file" name="attach_file" value=""/>
					</div>
					<div class="huodong_input_div">
					活动配图：
					<div id="upload_image" class="button" >上传活动配图(小于2M，可选)</div>
					<font style="color:grey; font-size:10px">说明：配图上传有问题请联系客服</font>
					<input type="hidden" id="attach_image" name="attach_image" value=""/>
					<img id="attach_image_display" src="images/nothing.gif"/>
					</div>
					<div class="huodong_input_div">
					活动内容：
					<textarea form="newactivity" id="content" name="content" class="huodong_input" style="height:200px"></textarea>
					<br />
					
					</div>
					<input type="submit" value="发布" />
					
					<br />
				</form>
				<%} %>
			</div>
		</div>

		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
		</section>
</body>
</html>


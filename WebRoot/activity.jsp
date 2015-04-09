<%@ page language="java"
	import="java.util.*, config.*,service.*,java.text.*, model.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	UserService us = new UserService();
	User userinfo = us.check_login(request);
	
	String activity_id = request.getParameter("activity_id");
	
	ActivityService as = new ActivityService();
	ArrayList<Activity> recent_activities = as.findRecentOpenActivities(5);
	
	Activity a = as.findActivityWithExtraData(Integer.parseInt(activity_id));
	
	
	boolean checksignup = false;
	if(userinfo != null)
		checksignup = as.checksignup(Integer.parseInt(activity_id), userinfo.getId());
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title><%=a.getTitle() %>-领金活动</title>
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

<script
	src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
	
<script src="js/jquery.min.1.4.2.js" type="text/javascript"></script>

<script>

$(document).ready(function(){
  $("#likezana").toggle(
    function(){
    	likezan('<%=a.getId() %>', '<%=userinfo==null?-1:userinfo.getId() %>', 'likezan');
    },
    function(){
    	likezan('<%=a.getId() %>', '<%=userinfo==null?-1:userinfo.getId() %>', 'cancellikezan');
    }
  );
});

function likezan(activity_id, user_id, type){

	if(user_id == -1){
		alert("您还未登录,请登录后点赞！");
		return false;
	}
	
	$.ajax({     
    	url:'backend/likezan.jsp',     
    	type:'post',     
    	data:'activity_id='+activity_id+'&user_id='+user_id+'&type='+type,     
    	dataType:'text',
    	error:function(){     
       		alert('赞未成功');     
    	},
    	
    	success:function(data){     
    		
    		if(type == 'likezan')
        		$('#likezanspan').html(parseInt($('#likezanspan').html())+1);   
        	if(type == 'cancellikezan')
        		$('#likezanspan').html(parseInt($('#likezanspan').html())-1);   
    	}  
	});  
}

<%if(userinfo != null){%>
function addcomment(activity_id, user_id, comment){
	
	$.ajax({     
    	url:'backend/comment.jsp',     
    	type:'post',     
    	data:'activity_id='+activity_id+'&user_id='+user_id+'&comment='+comment,     
    	dataType:'text',
    	error:function(){     
       		alert('评论未成功');     
    	},
    	
    	success:function(data){     
    		commentcontent = '<li class="comment even thread-even depth-1">'+
    						'<div class="c-avatar"></div>'+
							'<div class="c-main">'+
							'<span class="c-author"><img src="<%=userinfo.getWeixinHeadimg() %>" width="30"/></span><span class="c-author"><%=userinfo.getNickname() %></span> : '+
							comment + 
							'<time class="c-time">刚刚</time>' +
							'<a class="comment-reply-link" href="#comment_form">回复</a>'+
							'</div>'+
							'</li>';
    		
    		$(commentcontent).appendTo('#commentlist');
    		
    		$("html,body").animate({scrollTop:$("#comment_tail").offset().top},300);
    	}  
	});  
}
<%}%>
</script>

</head>

<body
	class="archive category category-research category-4 logged-in ui-c3">
	<section class="container">
	
		<jsp:include page="header.jsp">
			<jsp:param name="thispage" value="huodongs" /> 
		</jsp:include>

		<div class="content-wrap">
			<div class="content">
				<header class="article-header">
					<h1 class="article-title">
						<a href="<%=basePath%>activity.jsp?activity_id=<%=a.getId()%>"><%=a.getTitle()%></a>
					</h1>
					<ul class="article-meta">
						<li><%=a.getPublisher().getNickname()%> 发布于 <%=df.format(a.getPublishTime())%></li>
						<li><span class="post-views">已报名 (<%=a.getSignup_users().size()%>人)</span>
						</li>
						<li>评论(<%=a.getComments().size()%>)</li>
						<li>赞 (<%=a.getLikezan_count()%>)</li>
					</ul>
				</header>
				<article class="article-content">
					<p>
						<img class="alignnone size-medium wp-image-53"
							src="<%=a.getImageUrl()%>"
							alt="6bd27901f0f55f203f314e3be6746f27" width="655" height="491"
							data-tag="bdshare">
					</p>
					<p>
						<span style="color: #0000ff;">活动名称：</span><span
							style="color: #ff0000;"><%=a.getTitle()%></span>
					</p>
					<p>
						<span style="color: #0000ff;">活动时间：</span><span
							style="color: #ff0000;"><%=df.format(a.getStartTime())%></span>
					</p>
					<p>
						<span style="color: #0000ff;">活动时长：</span><%=a.getLastTime()%>分钟
					</p>
					<p>
						<span style="color: #0000ff;">活动地点：</span><span
							style="color: #ff0000;"><%=a.getProvince()%><%=a.getDetailAddr()%></span>
					</p>
					<p>
						<span style="color: #0000ff;">参加人数：</span><%=a.getTotalPeople()%>（已报名
						<%=a.getSignup_users().size()%>
						人）
					</p>
					<p>
						<span style="color: #0000ff;">活动费用：</span>
						<%if(a.getFee() == 0){ %>
						免费
						<%}else{ %>
						<%=a.getFee()%>
						元
						<%} %>
					</p>
					<%
						for(int i = 0 ; i < a.getSpeakers().size(); i++){
					%>
					<p>
						<span style="color: #0000ff;">主讲人：</span><%=a.getSpeakers().get(i).getName()%></p>
					<p>
						<span style="color: #0000ff;">主讲人简介：<span
							style="color: #000000;"><%=a.getSpeakers().get(i).getIntroduction()%></span>
						</span>
					</p>

					<%
						}
					%>
					<%
						if(a.getFile() != null){
					%>
					<p>
						<span style="color: #0000ff;">活动材料：<span
							style="color: #000000;"><a href="<%=a.getFile()%>">材料下载</a>
						</span>
						</span>
					</p>
					<%
						}
					%>

					<p>
						<span style="color: #0000ff;">活动内容：</span>
					</p>
					<p><%=a.getContent()%></p>
				</article>

				<div class="article-social">
				
					<%if(checksignup){ %>
						<a style="background-color:grey; width:200px"
					href="<%=basePath %>process_signup.jsp?activity_id=<%=a.getId() %>&user_id=<%=userinfo.getId() %>&signup_type=cancelbaoming" 
					class="action action-like">
						取消报名（<%=a.getSignup_users().size() %>/<%=a.getTotalPeople()%>）
						</a>
					<%}else{ %>
						<a style="background-color:green; width:200px"
					href="<%=basePath %>process_signup.jsp?activity_id=<%=a.getId() %>&user_id=<%=userinfo==null?-1:userinfo.getId() %>&signup_type=baoming" 
					class="action action-like">
						报名 (还有<%=a.getTotalPeople()-a.getSignup_users().size() %>个名额)
						</a>
					<%} %>
				
					
						
					<a id="likezana" href="javascript:;" class="action action-like" data-pid="6126"
						data-event="like"><i class="glyphicon glyphicon-thumbs-up"></i>
						赞
						(<span id="likezanspan"><%=a.getLikezan_count() %></span>)
						</a>
				</div>

				<a name="comment_form"></a>
				<h3 class="title" id="comments">

					<strong>评论 <b> <%=a.getComments().size()%> </b>
					</strong>
				</h3>
				
				<%if(userinfo != null){ %>
				<div id="respond" class="no_webshot">

					<form action="#"
						method="post" id="commentform">

						<div class="comt-title">
							<div class="comt-avatar">
								<img src="<%=userinfo.getWeixinHeadimg()%>" width="50" />
							</div>
							<div class="comt-author">
								<%=userinfo.getNickname()%>
							</div>
						</div>

						<div class="comt">
							<div class="comt-box">
								
								<textarea placeholder="欢迎参与金融阁的讨论"
									class="input-block-level comt-area" name="comment" id="comment"
									cols="100%" rows="3" tabindex="1"></textarea>
								<div class="comt-ctrl">
									<button type="submit" name="submit" id="submit" tabindex="5" onclick="addcomment(<%=a.getId() %>, <%=userinfo.getId() %>, $('#comment').val());return false;">
										<i class="icon-ok-circle icon-white icon12"></i> 提交评论
									</button>
									<!-- <span data-type="comment-insert-smilie" class="muted comt-smilie"><i class="icon-thumbs-up icon12"></i> 表情</span> -->
								</div>
							</div>
						</div>
					</form>
				</div>
				<%} %>
				<div id="postcomments">
					<ol id="commentlist" class="commentlist">
						<%for(int j = 0; j < a.getComments().size(); j++){ 
						
						Comment c = a.getComments().get(j);
						%>
						<li class="comment even thread-even depth-1" id="comment-16"><div
								class="c-avatar"></div>
							<div class="c-main" id="div-comment-16">
								<span class="c-author"><img src="<%=c.getUserheadimg() %>" width="30"/></span><span class="c-author"><%=c.getUsername() %></span>： 
								<%=c.getContent() %>
								<time class="c-time"><%=df.format(c.getCtime()) %></time>
								<a class="comment-reply-link"
									href="#comment_form" onclick="$('#comment').val('回复 <%=c.getUsername()%>: ')">回复</a>
							</div>
						</li>
						<%} %>
					</ol>
					<a id="comment_tail" name="comment_tail"></a>
					<div class="pagenav"></div>
				</div>
			</div>
		</div>

		<jsp:include page="sidebar.jsp" />
		<jsp:include page="footer.jsp" />
	</body>
</html>
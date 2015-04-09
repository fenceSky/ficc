<%@ page language="java" import="java.util.*, service.*, model.*,config.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	ActivityService as = new ActivityService();
	ArrayList<Activity> recent_activities = as.findRecentOpenActivities(5);
	
%>

<aside class="sidebar">
			<div class="widget widget_searchbox affix-top" style="top: 0px;">
				<h3 class="title">
					<strong>活动搜索</strong>
				</h3>
				<form method="post" class="search-form"
					action="<%=basePath%>search_result.jsp" accept-charset="utf-8">
					<input class="form-control" id="s" name="s" type="text" placeholder=""><input
						class="btn" type="submit" value="搜索">
				</form>
			</div>
			
			<div class="widget widget_recent_entries" style="top: 0px;">
				<h3 class="title">
					<strong>近期活动</strong>
				</h3>
				<ul>
					<%for(int i = 0; i < recent_activities.size(); i++){ %>
					<li><a href="<%=basePath%>activity.jsp?activity_id=<%=recent_activities.get(i).getId()%>"><%=recent_activities.get(i).getTitle() %></a>
					</li>
					<%} %>
				</ul>
			</div>
</aside>

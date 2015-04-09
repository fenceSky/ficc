<%@ page language="java"
	import="java.util.*,java.sql.*, service.*, model.*,dao.*, config.*,java.io.PrintWriter"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding ("utf-8");

	String comment = request.getParameter("comment");
	String activity_id = request.getParameter("activity_id");
	String user_id = request.getParameter("user_id");
	
	try {
	
		ActivityService as = new ActivityService();
		as.addComment(Integer.parseInt(activity_id), Integer.parseInt(user_id), "comment", comment, new Timestamp(System.currentTimeMillis()), Comment.NORMAL_STATE);
		
	
		PrintWriter resultout;
		resultout = response.getWriter();
		
		resultout.write(1);
		
		resultout.flush();
		resultout.close();
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
%>


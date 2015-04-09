<%@ page language="java"
	import="java.util.*, service.*, model.*,dao.*, config.*,java.io.PrintWriter"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String type = request.getParameter("type");
	String activity_id = request.getParameter("activity_id");
	String user_id = request.getParameter("user_id");
	
	try {
	
		ActivityService as = new ActivityService();
		
		//System.out.println(type);
	
		if(type.equals("likezan"))
			as.likezan(Integer.parseInt(activity_id), Integer.parseInt(user_id));
		else if(type.equals("cancellikezan")){
			as.cancellikezan(Integer.parseInt(activity_id), Integer.parseInt(user_id));
		}
	
		PrintWriter resultout;
		resultout = response.getWriter();
		
		int likezancount = new LikezanDAO().findByActivityId(Integer.parseInt(activity_id)).size();
		//out.print(likezancount);
		resultout.write(likezancount);
		
		resultout.flush();
		resultout.close();
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
%>


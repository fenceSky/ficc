<%@ page language="java"
	import="java.util.*, service.*, model.*,dao.*, config.*,java.io.PrintWriter"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String message_id = request.getParameter("message_id");
	String read_state = request.getParameter("read_state");
	
	try {
	
		ActivityService as = new ActivityService();
		MessageService ms = new MessageService();
		ms.changeMessageState(Integer.parseInt(message_id), Integer.parseInt(read_state));
		
		PrintWriter resultout;
		resultout = response.getWriter();
		
		resultout.write("success");
		
		resultout.flush();
		resultout.close();
		
	} catch (Exception e1) {
		e1.printStackTrace();
	}
%>


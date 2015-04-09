<%@ page language="java"
	import="java.util.*, service.*, model.*, config.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%

request.setCharacterEncoding ("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


UserService us = new UserService();
User userinfo = us.check_login(request);

if(userinfo == null){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
}

String title = request.getParameter("title");
String start_year = request.getParameter("start_year");
String start_month = request.getParameter("start_month");
String start_day = request.getParameter("start_day");
String start_hour = request.getParameter("start_hour");
String start_minite = request.getParameter("start_minite");
String last_time = request.getParameter("last_time");
String province = request.getParameter("province");
String detail_addr = request.getParameter("detail_addr");
String total_people = request.getParameter("total_people");
String fee = request.getParameter("fee");
String speaker_name = request.getParameter("speaker_name");

String speaker_intr = request.getParameter("speaker_intr");
String attach_file = request.getParameter("attach_file");
String attach_image = request.getParameter("attach_image");

String content = request.getParameter("content");

ActivityService as = new ActivityService();

Timestamp startTime = new Timestamp(Integer.parseInt(start_year)-1900,Integer.parseInt(start_month)-1, 
	Integer.parseInt(start_day), Integer.parseInt(start_hour), Integer.parseInt(start_minite), 0, 0);

int activity_id = as.newActivity(title, startTime, (int)(Double.parseDouble(last_time) * 60), province, "", "", detail_addr, userinfo.getId(), Integer.parseInt(total_people), Integer.parseInt(fee), content);

if(speaker_name != null && !speaker_name.trim().equals(""))
	as.addSpeaker(activity_id, speaker_name, speaker_intr);
	
if(attach_file != null && !attach_file.trim().equals("")){
	String[] files = attach_file.split("/");
	String[] filetypes = attach_file.split("\\.");
	

	as.addAttachFile(activity_id, userinfo.getId(), filetypes[filetypes.length-1], files[files.length-1], attach_file, 0);
}

if(attach_image != null && !attach_image.trim().equals("")){
	String[] files = attach_image.split("/");
	String[] filetypes = attach_image.split("\\.");
	

	as.addAttachImage(activity_id, userinfo.getId(), filetypes[filetypes.length-1], files[files.length-1], attach_image, 0);
}

response.sendRedirect(basePath + "userinfo.jsp?type=faqi&message=faqi_success");

%>


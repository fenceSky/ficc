<%@ page language="java"
	import="java.util.*, service.*, model.*, config.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%

request.setCharacterEncoding ("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();

String aid = request.getParameter("aid");
Activity a = null;

ActivityService as = new ActivityService();

try{
	
	Integer aintid = Integer.parseInt(aid);
	a = as.findActivityWithExtraData(aintid);
		
}catch(Exception e){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
}

User userinfo = us.check_login(request);

if(userinfo == null || a == null){
	response.sendRedirect(basePath + "webweixinlogin.jsp");
	return;
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

Timestamp startTime = new Timestamp(Integer.parseInt(start_year)-1900,Integer.parseInt(start_month)-1, 
	Integer.parseInt(start_day), Integer.parseInt(start_hour), Integer.parseInt(start_minite), 0, 0);

as.modifyActivity(a.getId(), title, startTime, (int)(Double.parseDouble(last_time) * 60), province, "", "", detail_addr, userinfo.getId(), Integer.parseInt(total_people), Integer.parseInt(fee), content, a.getState(), a.getPublishTime());

if(speaker_name != null && !speaker_name.trim().equals("")){
	if(a.getSpeakers().size() == 0)
		as.addSpeaker(a.getId(), speaker_name, speaker_intr);
	else
		as.modifySpeaker(a.getSpeakers().get(0).getId(), speaker_name, speaker_intr);
}


if(attach_file != null && !attach_file.trim().equals("")){
	String[] files = attach_file.split("/");
	String[] filetypes = attach_file.split("\\.");
	
	boolean flag = true;
	for(int i = 0 ; i < a.getAttachments().size(); i++){
		if(a.getAttachments().get(i).getState().equals(Attachment.ATTACH_FILE)){
			as.modifyAttachment(a.getAttachments().get(i).getId(), a.getId(), userinfo.getId(), 
			 filetypes[filetypes.length-1], files[files.length-1], attach_file, 0, new Timestamp(System.currentTimeMillis()), a.getAttachments().get(i).getState());
			flag = false;
		}
	}
	if(flag)
		as.addAttachFile(a.getId(), userinfo.getId(), filetypes[filetypes.length-1], files[files.length-1], attach_file, 0);
}

if(attach_image != null && !attach_image.trim().equals("")){
	String[] files = attach_image.split("/");
	String[] filetypes = attach_image.split("\\.");
	
	boolean flag = true;
	for(int i = 0 ; i < a.getAttachments().size(); i++){
		if(a.getAttachments().get(i).getState().equals(Attachment.ATTACH_IMAGE)){
			as.modifyAttachment(a.getAttachments().get(i).getId(), a.getId(), userinfo.getId(), 
			 filetypes[filetypes.length-1], files[files.length-1], attach_image, 0, new Timestamp(System.currentTimeMillis()), a.getAttachments().get(i).getState());
			flag = false;
		}
	}
	if(flag)
		as.addAttachImage(a.getId(), userinfo.getId(), filetypes[filetypes.length-1], files[files.length-1], attach_image, 0);
}

response.sendRedirect(basePath + "userinfo.jsp?type=faqi&message=edit_success");
%>


<%@ page language="java" import="java.util.*, service.*, model.*,java.sql.Timestamp" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
ArrayList<User> users = (ArrayList<User>)us.findAllOrderBy("id", 0, 50);
%>

[
<%for(int i = 0; i < users.size(); i++){ %>
{           
	"verified":"0",
	"user_id":"1232",
	"nickname":"王喆",
	"activity_id":"21132",
	"activity_name":"金融街期货讲座",
	"ctime":"<%=new Timestamp(System.currentTimeMillis()).toString() %>"
}
	<%if(i < users.size() - 1)
		out.print(",");
	 %>
  <%} %>
 ]
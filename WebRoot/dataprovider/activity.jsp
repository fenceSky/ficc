<%@ page language="java" import="java.util.*, service.*, model.*,java.sql.Timestamp" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
ArrayList<User> users = (ArrayList<User>)us.findAllOrderBy("id", 0, 50);

Activity a = new Activity();
a.setTitle("金融街期货讲座");
a.setStartTime(new Timestamp(System.currentTimeMillis()));
a.setLastTime(60);
a.setProvince("北京");
a.setCity("东城区");
a.setShortAddr("金融街");
a.setDetailAddr("金融街工商银行大楼502");
a.setTotalPeople(100);
a.setFee(50);
a.setContent("主讲期货知识！");
a.setPublishTime(new Timestamp(System.currentTimeMillis()));

%>

[
<%for(int i = 0; i < users.size(); i++){ %>
{
"title":"<%=a.getTitle() %>",
"start_time":"<%=a.getStartTime().toString() %>",
"lasttime":"<%=a.getLastTime() %>",
"province":"<%=a.getProvince() %>",
"city":"<%=a.getCity() %>",
"short_addr":"<%=a.getShortAddr() %>",
"detail_addr":"<%=a.getDetailAddr() %>",
"total_people":"<%=a.getTotalPeople() %>",
"fee":"<%=a.getFee() %>",
"content":"<%=a.getContent() %>",
"publish_time":"<%=a.getPublishTime().toString() %>"
}
	<%if(i < users.size() - 1)
		out.print(",");
	 %>
  <%} %>
 ]
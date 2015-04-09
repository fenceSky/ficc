<%@ page language="java" import="java.util.*, service.*, model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserService us = new UserService();
ArrayList<User> users = (ArrayList<User>)us.findAllOrderBy("id", 0, 50);
%>

[
<%for(int i = 0; i < users.size(); i++){ %>
{
	"nickname":"<%=users.get(i).getNickname() %>",
	"verified":"<%=users.get(i).getVerified() %>",
	"start_activity":"<%=users.get(i).getPermission() %>",
	"realname":"<%=users.get(i).getRealname() %>",
	"cellphone":"<%=users.get(i).getCellphone() %>",
	"corp":"<%=users.get(i).getCorp() %>",
	"corpdetail":"<%=users.get(i).getCorpdetail() %>",
	"department":"<%=users.get(i).getDepartment() %>",
	"position":"<%=users.get(i).getPosition() %>",
	"qq":"<%=users.get(i).getQq() %>",
	"email":"<%=users.get(i).getEmail() %>",
	"pemail":"<%=users.get(i).getPemail() %>",
	"address":"<%=users.get(i).getAddress() %>",
	"remark1":"<%=users.get(i).getRemark1() %>",
	"remark2":"<%=users.get(i).getRemark2() %>",
	"remark3":"<%=users.get(i).getRemark3() %>",
	"reg_time":"<%=users.get(i).getRegTime().toString() %>"
}
	<%if(i < users.size() - 1)
		out.print(",");
	 %>
  <%} %>
 ]
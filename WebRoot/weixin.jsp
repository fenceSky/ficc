<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="service.WeixinService"%>  
<%  
    WeixinService wxs = new WeixinService(request, response);
    wxs.valid();
%>

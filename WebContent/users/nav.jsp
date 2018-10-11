<%@ page language="java" import="edu.bean.*,edu.conf.*,java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<nav class="container">
	<a href="<%=basePath %>contact/Lst">通讯录</a>
	<a href="<%=basePath %>log/Lst">备忘录</a>
	<div class="rt">
		<span><%=((Users)request.getSession().getAttribute("user")).getLoginName() %></span>
		<a href="<%=basePath %>users/Logout">退出登录</a>
	</div>
</nav>
<%@ page language="java" import="edu.bean.*,edu.conf.*,java.util.ArrayList" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=WebConfig.PRJ_NAME %></title>
<link href="<%=basePath %>css/prj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/bower_components/jquery/jquery.js"></script>
</head>
<body>

	<div class="container">
		<form action=""  method="post">
		
			<div class="form-group">
				<label class="label-text">登陆账户</label>
				<input type="text" class="input-text" value="" name="login_name" />
			</div>
			<div class="form-group">
				<label class="label-text">登陆密码</label>
				<input type="text" class="input-text" value="" name="login_pwd" />
			</div>
			<p><%=request.getAttribute("login_name") %></p>
			<p><%=request.getAttribute("login_pwd") %></p>
			<div class="form-group">
			    <label class="label-text"></label>
				<a class="btn"  href="<%=basePath %>student/Reg" >继续注册</a>
			</div>
		</form>
	</div>
</body>
</html>
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
		<h4 style="font-size:22px;line-height:48px;padding-bottom:20px;padding-left:30%;"><%=WebConfig.PRJ_NAME %></h4>
		<form action="<%=basePath%>users/Login"  method="post">
		
			<div class="form-group">
				<label class="label-text">登陆账户</label>
				<input type="text" class="input-text" value="zhangsan" name="login_name" />
			</div>
			<div class="form-group">
				<label class="label-text">登陆密码</label>
				<input type="text" class="input-text" value="123123" name="login_pwd" />
			</div>
			<div class="form-group">
			    <label class="label-text"></label>
				<button class="btn" type="submit" >登陆</button>
			</div>
		</form>
	</div>
</body>
</html>
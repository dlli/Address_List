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
<link href="<%=basePath %>js/bower_components/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/bower_components/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/bower_components/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/bower_components/lang/zh-cn.js"></script>

</head>
<body>
	<jsp:include page="/users/nav.jsp"></jsp:include>
	<div class="container">
		<form action="<%=basePath%>log/Add"  method="post">
			<input type="hidden" name="item_id" value="<%=request.getParameter("id")%>"/>
			<div class="form-group">
				<label class="label-text">备忘录标题</label>
				<input type="text"  class="input-text"    name="log_title" />
			</div>
			<div class="form-group">
				<label class="label-text">备忘录描述</label>
				<input class="input-text"  name="log_desc" ></input>
			</div>
			<div class="form-group">
				<label class="label-text">备忘录日期</label>
				<input class="input-text"  name="date" onClick="WdatePicker()"></input>
			</div>
			<div class="form-group">
				<label class="label-text">类型</label>
				<select name="log_type">
					<option value="2">备忘</option>
					<option value="1">提醒</option>
					<option value="3">生日提醒</option>
				</select>
			</div>
			<div class="form-group">
			    <label class="label-text"></label>
				<button class="btn" type="submit" >保存</button>
			</div>
		</form>
	</div>
</body>
</html>
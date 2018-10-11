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
	<%
		ContactsItem item = (ContactsItem)request.getAttribute("item");
	%>
	<div class="container">
		<form action="<%=basePath%>contact/Edit"  method="post">
			<input type="hidden" name="item_id" value="<%=item.getItemId() %>" />
			<div class="form-group">
				<label class="label-text">姓名</label>
				<input type="text"  class="input-text" value="<%=item.getItemName() %>"   name="item_name" />
			</div>
			<div class="form-group">
				<label class="label-text">别名</label>
				<input class="input-text" value="<%=item.getItemAlias() %>"  name="item_alias" ></input>
			</div>
			<div class="form-group">
				<label class="label-text">联系电话</label>
				<input class="input-text"  name="item_tel" value="<%=item.getItemTel() %>"></input>
			</div>
			<div class="form-group">
				<label class="label-text">住址</label>
				<input class="input-text"  name="item_addr" value="<%=item.getItemAddr() %>" ></input>
			</div>
			<div class="form-group">
				<label class="label-text">生日</label>
				<input class="input-text"  name="item_birthday" value="<%=item.getItemBirthday() %>" onClick="WdatePicker()"></input>
			</div>
			<div class="form-group">
			    <label class="label-text"></label>
				<button class="btn" type="submit" >保存</button>
			</div>
		</form>
	</div>
</body>
</html>
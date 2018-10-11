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
		<%
			Log item = (Log)request.getAttribute("item");
		%>
		<form action="<%=basePath%>log/Edit"  method="post">
			<input type="hidden" name="f" value="<%=request.getParameter("f") %>"/>
			<input type="hidden" name="detail_id" value="<%=request.getParameter("detail_id") %>"/>
			<input type="hidden" name="item_id" value="<%=item.getItemId()%>"/>
			<input type="hidden" name="log_id" value="<%=item.getLogId()%>"/>
			<div class="form-group">
				<label class="label-text">备忘录标题</label>
				<input type="text"  class="input-text" value="<%=item.getLogTitle() %>"   name="log_title" />
			</div>
			<div class="form-group">
				<label class="label-text">备忘录描述</label>
				<input class="input-text"  name="log_desc" value="<%=item.getLogDesc()%>"></input>
			</div>
			<div class="form-group">
				<label class="label-text">备忘录日期</label>
				<input class="input-text"  name="date" value="<%=item.getDate() %>" onClick="WdatePicker()"></input>
			</div>
			<div class="form-group">
				<label class="label-text">类型</label>
				<select name="log_type">
					<option value="2" <%=(item.getLogType()==2)?"selected":"" %>>备忘</option>
					<option value="1"<%=(item.getLogType()==1)?"selected":"" %>>提醒</option>
					<option value="3"<%=(item.getLogType()==3)?"selected":"" %>>生日提醒</option>
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
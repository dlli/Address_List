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
	<jsp:include page="/users/nav.jsp"></jsp:include>
	<div class="container">
		<form id="search" action="<%=basePath%>contact/Lst" method="post">
			<input type="text" placeholder="请输入名字模糊查询" name="k" value="<%=(request.getAttribute("k")==null)?"":request.getAttribute("k") %>" /><button type="submit">搜索</button>
		</form>
	    <div class="table-bef">
	    	<a class="btn" href="<%=basePath%>contact/Add">新建联系人</a>
	    </div>
		<table class="table">
			<tr>
				<th>姓名</th>
				<th>别名</th>
				<th>联系电话</th>
				<th>生日</th>
				<th>住址</th>
				<th>操作</th>
			</tr>
			<%
				ArrayList<ContactsItem> lst = (ArrayList<ContactsItem>)request.getAttribute("lst");
				for(int x = 0 ; x < lst.size();x++){
					System.out.println("course:" + lst.get(x).toString());
					%>
					<tr>
						<td><%=lst.get(x).getItemName() %></td>
						<td><%=lst.get(x).getItemAlias() %></td>
						<td><%=lst.get(x).getItemTel() %></td>
						<td><%=lst.get(x).getItemBirthday() %></td>
						<td><%=lst.get(x).getItemAddr() %></td>
						<td>
							<a href="<%=basePath%>contact/Detail?id=<%=lst.get(x).getItemId()%>">详情</a>
							<a href="<%=basePath%>contact/Edit?id=<%=lst.get(x).getItemId()%>">编辑</a>
							<a href="<%=basePath%>contact/Delete?id=<%=lst.get(x).getItemId()%>">删除</a>
							
						</td>
					</tr>
					<%
				}
			%>
		</table>
	</div>
</body>
</html>
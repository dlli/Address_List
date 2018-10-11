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
		<%
		ContactsItem item = (ContactsItem)request.getAttribute("item");
		%>
		<h4><strong>名称:</strong><%=item.getItemName() %></h4>
	    <p><strong>别名:</strong><%=item.getItemAlias() %></p>
	    <p><strong>联系电话:</strong><%=item.getItemTel() %></p>
	    <p><strong>联系地址:</strong><%=item.getItemAddr() %></p>
	    <div class="table-bef">
	    	<a class="btn" href="<%=basePath%>log/Add?id=<%=request.getParameter("id")%>">添加备注</a>
	    </div>
		<table class="table">
			<tr>
				<th>日期</th>
				<th>备注标题</th>
				<th>备注详情</th>
				<th>操作</th>
			</tr>
			<%
				ArrayList<Log> lst = (ArrayList<Log>)request.getAttribute("lst");
				for(int x = 0 ; x < lst.size();x++){
					System.out.println("course:" + lst.get(x).toString());
					%>
					<tr>
						<td><%=lst.get(x).getDate()%></td>
						<td><%=lst.get(x).getLogTitle()  %></td>
						<td><%=lst.get(x).getLogDesc() %></td> 
						<td>
							<a href="<%=basePath%>log/Edit?id=<%=lst.get(x).getLogId()%>&f=detail&detail_id=<%=request.getParameter("id")%>">编辑</a>
							<a href="<%=basePath%>log/Delete?id=<%=lst.get(x).getLogId()%>&f=detail&detail_id=<%=request.getParameter("id")%>">删除</a>
						</td>
					</tr>
					<%
				}
			%>
		</table>
	</div>
</body>
</html>
<%@ page language="java" import="edu.bean.*,edu.conf.*,java.util.ArrayList,java.util.HashMap" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=basePath %>js/bower_components/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/bower_components/lang/zh-cn.js"></script>
</head>
<body>
	<jsp:include page="/users/nav.jsp"></jsp:include>
	<div class="container">
		<form id="search" action="<%=basePath%>log/Lst" method="post">
			<input type="text" name="d" value="<%=request.getAttribute("d") %>" onClick="WdatePicker({dateFmt:'yyyy-MM'})" />
			<button type="submit">搜索</button>
		</form>
	    <div class="table-bef">
	    	<a class="btn" href="<%=basePath%>log/Add?id=0">新建备忘录</a>
	    </div>
		
		<div id="calendar">
			<%
				int day = (Integer)request.getAttribute("day");
				HashMap<Integer,ArrayList<LogContactBean>> map = (HashMap<Integer,ArrayList<LogContactBean>>)request.getAttribute("map");
				for(int x =0 ; x < day; x++){
				%>
						<div class="day-item">
							<a class="day"><%=x+1 %></a>
							<%
								ArrayList<LogContactBean> lst = map.get(x+1);
								for(int y =0; lst!=null&&y<lst.size();y++){
									if(lst.get(y).getItemId() == 0){
										%>
										<a class="log" href="<%=basePath%>log/Edit?id=<%=lst.get(y).getLogId()%>"><%=lst.get(y).getLogTitle() %></a>
										<%
									}else{
										%>
										<a class="log" href="<%=basePath%>contact/Detail?id=<%=lst.get(y).getItemId()%>">
											<%=lst.get(y).getItemId() %>
											<%=lst.get(y).getItemName()%>
											&nbsp;
											<%=lst.get(y).getLogTitle() %>
										</a>
										<%
									}
								}
							%>
						</div>
					<%
				}
			%>
		</div>
		
	</div>
</body>
</html>
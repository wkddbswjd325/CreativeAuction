<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="model.*" %>
<%
	Matching matching = (Matching)request.getAttribute("matching"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
<body>
<jsp:include page="upperBar.jsp" />
<center>
<table style="padding-top:40px">
	<tr>
		<td><img style="width:300px" src="<%=matching.getA_item_img()%>" /></td>
		<td width="200px" style="text-align:center" rowspan=4><img width="130px" src="img/exchange.jpg"></td>
		<td><img style="width:300px" src="<%=matching.getR_item_img()%>" /></td>
	</tr>
	<tr>
		<td><%=matching.getA_mem_id()%></td>
		<td><%=matching.getR_mem_id()%></td>
	</tr>
	<tr>
		<td><%=matching.getA_mem_address()%></td>
		<td><%=matching.getR_mem_address()%></td>
	</tr>
	<tr>
		<td><%=matching.getA_mem_phone()%></td>
		<td><%=matching.getR_mem_phone()%></td>
	</tr>
	
</table>
</center>
</body>
</html>
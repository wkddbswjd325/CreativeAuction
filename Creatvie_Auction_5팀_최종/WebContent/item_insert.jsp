<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title> ������ �߰� </title>
</head>
<body>
<script>

function insertPopup() {
	if ( item_insert_form.i_name.value == "" ) {
		alert("������ �̸��� �Է��ϼ���.");
		item_insert_form.i_name.focus();
		return false;
	}
	item_insert_form.action = "item_insert.m2";
	item_insert_form.submit();
}
function winClose() {
	alert('�������� ���������� ��ϵǾ����ϴ�.');
	opener.location.reload();
	self.close();
}

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<%
	if(request.getParameter("status")!=null) {
		String s = (String)request.getAttribute("status");

		if(s == "upload") {
			out.println("�������߰�");
	
			out.println("<script language='javaScript'>winClose();</script>");
		}
	}
%>
<center>
<br>
������ �߰��ϱ�
<br><br>
<form name="item_insert_form" method="post" enctype="multipart/form-data">
	<input type="hidden" name="command" value="item_image_insert"/>
	<input type="hidden" name="m_id" value="${member.m_id}"/>
	<table border="0" >
		<tr>
			<th width="100">������ �̸� : </th>
			<td align="center"><input type="text" name="i_name" size="25"/></td>
		</tr>
		<tr>
			<th width="100">������ ���� : </th>		
			<td align="center"><input type="file" name="i_img" size="11"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="������ �߰�" onClick="insertPopup()"/></td>
		</tr>
	</table>
	</form>
</center>
</body>
</html>
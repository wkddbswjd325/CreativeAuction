<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α���(Login)</title>
<script>
function memCreate() {
	login_form.command.value = "mem_join";
	login_form.method = "GET";
	login_form.action = "mem_join.m2";
	login_form.submit();
}

function login() {
	if ( login_form.m_id.value == "" ) {
		alert("����� ���̵� �Է��Ͻʽÿ�.");
		login_form.m_id.focus();
		return false;
	} 
	if ( login_form.m_password.value == "" ) {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		login_form.m_password.focus();
		return false;
	}	
	
	login_form.command.value = "login";
	login_form.action = "login.m2";
	login_form.submit();
}
</script>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<jsp:include page="upperBar.jsp" />

<br>
<!-- write Form  -->
<form name="login_form" method="POST">
  <input type="hidden" name="command"/>

	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  	<c:if test="${not empty exception}">
		<script>alert('${exception.getMessage()}');</script>
	</c:if>
	  <br>
	  
	  <center>
	  <table style="background-color: gray;">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">����� ���̵�</td>
			<td width="250px" bgcolor="ffffff" style="padding-left:10px;">
				<input type="text" style="width:200px" name="m_id">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ</td>
			<td width="250px" bgcolor="ffffff" style="padding-left:10px">
				<input type="password" style="width:200px" name="m_password">
			</td>
		  </tr>
	  </table>
	  <br>
	  </center>
	  <table style="width:100%">
		  <tr>
			<td align=center>
			<input type="submit" value="�α���" onClick="login()"> &nbsp;
			<input type="button" value="ȸ������" onClick="memCreate()">
			</td>
		  </tr>
	  </table>
	  </td>
	</tr>
  </table>  
  </center>
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login Page</title>
<script>
function memCreate() {
	f.command.value = "mem_join";
	f.method = "GET";
	f.action = "mem_join.m2";
	f.submit();
}

function login() {
	if ( f.m_id.value == "" ) {
		alert("����� ���̵� �Է��Ͻʽÿ�.");
		f.m_id.focus();
		return false;
	} 
	if ( f.m_password.value == "" ) {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		f.m_password.focus();
		return false;
	}	
	
	f.command.value = "login";
	f.action = "login.m2";
	f.submit();
}
</script>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<center>
<br>
<!-- write Form  -->
<form name="f" method="POST">
  <input type="hidden" name="command"/>
  <table style="width:100%">
	<tr>
	  <td width="20"></td>
	  <td>
      <!--contents-->
	  <table style="width:100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>Login</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">����� ���̵�</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="m_id">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:240" name="m_password">
			</td>
		  </tr>
	  </table>
	  <br>
	  
	  <table style="width:100%">
		  <tr>
			<td align=center>
			<input type="button" value="�α���" onClick="login()"> &nbsp;
			<input type="button" value="ȸ������" onClick="memCreate()">
			</td>
		  </tr>
	  </table>
	  </td>
	</tr>
  </table>  
</form>
</center>
</body>
</html>
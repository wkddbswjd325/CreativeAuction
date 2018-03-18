<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>

<script>
function memCreate() {
	if ( join_form.m_id.value == "" ) {
		alert("사용자 아이디를 입력하십시요.");
		join_form.m_id.focus();
		return false;
	} 
	if ( join_form.m_password.value == "" ) {
		alert("비밀번호를 입력하십시요.");
		join_form.m_password.focus();
		return false;
	}
	if ( join_form.m_password.value != join_form.m_password2.value ) {
		alert("비밀번호가 일치하지 않습니다.");
		join_form.m_password.focus();
		return false;
	}
	if ( join_form.m_name.value == "" ) {
		alert("이름을 입력하십시요.");
		join_form.m_name.focus();
		return false;
	}
	if ( join_form.m_phone.value == "" ) {
		alert("번호를 입력하십시요.");
		join_form.m_phone.focus();
		return false;
	}
	if ( join_form.m_email.value == "" ) {
		alert("이메일을 입력하십시요.");
		join_form.m_email.focus();
		return false;
	}
	if ( join_form.m_address.value == "" ) {
		alert("주소를 입력하십시요.");
		join_form.m_address.focus();
		return false;
	}
	
	join_form.command.value = "mem_join";
	join_form.action = "mem_join.m2";
	join_form.submit();
}
</script>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<jsp:include page="upperBar.jsp" />
<center>
<br>
<c:if test="${not empty exception}">
	<script>alert('${exception.getMessage()}');</script>
</c:if>
<br>
<form name="join_form" method="POST">
	<input type="hidden" name="command" />

	<table style="background-color: #E6ECDE;" width=65% border=4>
		<tr>
			<td colspan='2' height="50" align="left" style="padding-left: 40px; font-size: 13pt;">&nbsp;&nbsp;<b>회원가입</b></td>
		</tr>
		<tr>
			<td width="25%" height="35" bgcolor="ffffff" align="center"	height="22">사용자ID</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 25%;" name="m_id">
			</td>
		</tr>
		<tr>
			<td width="25%" height="35" bgcolor="ffffff" align="center"	height="22">비밀번호</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="password" style="width: 25%;" name="m_password">
			</td>
		</tr>
		<tr>
			<td width="25%" height="35" bgcolor="ffffff" align="center"	height="22">비밀번호 확인</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="password" style="width: 25%;" name="m_password2">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">이름</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 25%;" name="m_name">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">전화번호</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 25%;" name="m_phone">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">이메일</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 40%;" name="m_email">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">주소</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 80%;" name="m_address">
			</td>
		</tr>
		<tr>
			<td height="50" align="center" colspan='2'><input type="submit" value="회원 가입" onClick="memCreate()" style="font-size: 11pt;"></td>
		</tr>
	</table>
</form>
</center>
</body>
</html>
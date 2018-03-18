<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ include file="loginCheck.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>마이페이지 수정(내 정보 수정하기)</title>
</head>
<body>
<script>
function memberModify() {
	mem_modify_form.command.value = "mem_update";
	mem_modify_form.action = "mem_modify.m2";
	mem_modify_form.submit();
}

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<jsp:include page="upperBar.jsp" />
<br>
<center>
<form name="mem_modify_form" method="POST">
	<input type="hidden" name="command"/>
	<input type="hidden" name="m_id" value="${member.m_id}"/>
	<input type="hidden" name="m_score" value="${member.m_score}"/>
	<table style="background-color: #E6ECDE;" width=65% border=4>
		<tr>
			<td colspan='2' height="50" align="left" style="padding-left: 40px; font-size: 13pt;">&nbsp;&nbsp;<b>마이페이지 수정 (내 정보 수정)</b></td>
		</tr>
		<tr>
			<td width="25%" height="26" bgcolor="ffffff" align="center" height="22">사용자ID</td>
			<td  bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_id}</td>
		</tr>
		<tr>
			<td width="25%" height="35" bgcolor="ffffff" align="center"	height="22">비밀번호</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="password" style="width: 25%;" name="m_password" value="${member.m_password}">
			</td>
		</tr>
		<tr>
			<td width="25%" height="35" bgcolor="ffffff" align="center"	height="22">비밀번호 확인</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="password" style="width: 25%;" name="m_password2" value="${member.m_password}">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">이름</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 25%;" name="m_name" value="${member.m_name}">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">전화번호</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 25%;" name="m_phone" value="${member.m_phone}">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">이메일</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 40%;" name="m_email" value="${member.m_email}">
			</td>
		</tr>
		<tr>
			<td height="35" bgcolor="ffffff" align="center" height="22">주소</td>
			<td bgcolor="ffffff" align="left" style="padding-left: 40px;">
				<input type="text" style="width: 80%;" name="m_address" value="${member.m_address}">
			</td>
		</tr>
		<tr>
			<td height="26" bgcolor="ffffff" align="center" height="22">평점</td>
			<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_score}</td>
		</tr>
		<tr>
			<td height="50" align="center" colspan='2'><input type="submit" value="수정" onClick="memberModify()" style="font-size: 11pt;"></td>
		</tr>
	</table>
</form>
</center>
</body>
</html>
<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@include file="loginCheck.jsp" %>
<html>
<head>
<title>댓글 작성</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function replyCreate() {
	if ( f_replyWrite.r_comment.value == "" ) {
		alert("내용을 입력하세요");
		f_replyWrite.name.focus();
		return false;
	}
	f_replyWrite.command.value = "rep_insert";
	f_replyWrite.action = "reply_write.m2";
	f_replyWrite.submit();
}
function winClose() {
	alert('아이템이 정상적으로 등록되었습니다.');
	opener.location.reload();
	self.close();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<%
	if(request.getParameter("exception") != null)
		out.print("<script>alert('이미 사용된 아이템입니다. 다른 아이템을 올려주세요')</script>");

	if(request.getParameter("status")!=null) {
		String s = (String)request.getAttribute("status");

		if(s == "ok") {
			out.println("<script language='javaScript'>winClose();</script>");
		}
	}
%>
<br>
<%
	//session에 저장된 id 불러오기
	String userId = (String) session.getAttribute("m_id");
	@SuppressWarnings("unchecked")
	List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>
<!-- write Form  -->
<form name="f_replyWrite" method="POST">
<input type="hidden" name="command"/>
<input type="hidden" name="a_id" value="<%=request.getParameter("a_id")%>" /> 
<table style="width: 100%">
  <tr>
    <td width="20"></td>
	<td>
      <!--contents-->
	  <table style="width: 100%">
		  <tr>
			<td height="22">&nbsp;<b>[댓글 입력]</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  <table style="background-color: gray">
		 <tr height="80">
			<td width="150" align="center" bgcolor="E6ECDE">comment</td>
			<td width="600" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="r_comment">
			</td>
		 </tr>  
		 <tr height="80">
			<td width="150" align="center" bgcolor="E6ECDE">아이템 선택</td>
			<td bgcolor="ffffff" style="padding-left: 10">
				<table>
		<%
			int num = 1;
			if (itemList != null) {
				Iterator<Item> itemIter = itemList.iterator();

				while (itemIter.hasNext()) {
					Item item = (Item) itemIter.next();
		%>
	
	
	<tr>
		<td>&nbsp;
		<input type="radio" name="itemId" value="<%=item.getI_id()%>"> &nbsp;
		</td>
		<td><%=item.getI_name()%>&nbsp;</td>
		<td><img src=<%=item.getI_img()%> width='60px'
			height='60px'>
		</td>
	</tr>	
	
	<%
		num++;
			}

		} else {
			out.print("<td>아이템을 추가해주세요!</td>");
		}
	%>
	</table>
			</td>
		 </tr>  
	  </table>
	  <br>
	 
	  <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="올리기" onClick="replyCreate()"> &nbsp;
			</td>
		  </tr>
	  </table>
	</td>
  </tr>
</table>  
</form>
</body>
</html>
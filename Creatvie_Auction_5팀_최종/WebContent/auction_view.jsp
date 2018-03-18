<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	@SuppressWarnings("unchecked") 
	List<Reply> replyList = (List<Reply>)request.getAttribute("replyList");
	Auction auction = (Auction)request.getAttribute("auction"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script>
function viewMatching() {
	f_matching.command.value = "matching_view";
	f_matching.action = "matching_view.m2";
	f_matching.submit();
}
function deleteAuction() {
	if ( confirm("정말 삭제 하시겠습니까?") ) {
		f_view.command.value = "auc_delete";
		f_view.action = "auction_delete.m2";
		f_view.submit();
	}
}
function favoriteAuction() {
	if ( confirm("보관함에 넣으시겠습니까?") ) {
		f_view.command.value = "favor_insert";
		f_view.action = "favorite_insert.m2";
		f_view.submit();
	}
}
function matchingReply(r_id)
{
	if ( confirm("매칭하시겠습니까?") ) {
		f_reply.method = "post";
		f_reply.command.value = "matching_insert";
		f_reply.r_id.value = r_id;
		f_reply.action = "matching_insert.m2";
		f_reply.submit();
	}
}
function OnloadImg(url){
	 var img=new Image();
	 img.src=url;
	 var img_width=img.width;
	 var win_width=img.width+25;
	 var height=img.height+30;
	 var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+height+', menubars=no, scrollbars=auto');
	 OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
}
function replyInsert(a_id) {
	window.open("./reply_write.m2?command=rep_insert&a_id="+a_id, "댓글 추가", "location=no, scrollbars=no, toolbar=no, width=900, height=600");
}
</script>
</head>
<body>
	<jsp:include page="upperBar.jsp" />

	<!--  exception 객체가 전달된 경우 오류메시지를 출력 -->
	<c:if test="${not empty exception}">
		<script>alert('${exception.getMessage()}');</script>
	</c:if>
	<br>
	
	<center>
	<div style="font-size: 18px;padding-top: 20px;margin-left: auto; margin-right: auto;">
		<font style="font-size: 18px;">[<%=auction.getSmall_cate_name()%>]
			<%=auction.getA_title()%></font><br>
		<hr width="65%"
			style="margin-top: 4px; margin-bottom: 1px; border: solid 1px gray;">
		<font style="font-size: 13px;"><%=auction.getM_id()%> | <%=auction.getA_writedate()%></font><br>
		<br>
		<form name="f_matching" method="get" >
		
		<input type="hidden" name="command">
		<input type="hidden" name="a_id" value="<%=auction.getA_id()%>" /> 
		<table width="50%">
			<tr>
				<td rowspan=4><img width="300px" src="<%=auction.getI_img()%>" /></td>
				<%
					if (auction.getA_state().equals("true")) {
						out.println("<td style='padding-left:10%;font-size:30px;color:blue'>경매 완료&nbsp;");
						out.println("<input type='button' value='매칭 결과보기' onClick='viewMatching();'></td>");
					}
					else
						out.println("<td style='padding-left:10%;font-size:30px;color:red'>경매중</td>");
				%>
			</tr>
			<tr>
				<td style="font-size: 20px">&nbsp;</td>
			</tr>
			<tr>
				<td style="padding-left: 10%; font-size: 28px;">[DEADLINE]</td>
			</tr>
			<tr>
				<td style="padding-left: 10%; font-size: 28px;"><%=auction.getA_deadline()%></td>
			</tr>
		</table>
		</form>
		<br><br>
		<div style="width: 65%">
		<font style="font-size: 18px;"><%=auction.getA_content()%></font>
		<br><br>
		
		
		<form name="f_view" method="POST">
		<input type="hidden" name="command" /> 
		<input type="hidden" name="a_id" value="<%=auction.getA_id()%>" /> 
		<input type="hidden" name="cate" value="<%=auction.getBig_cate_id()%>" />
		<input type="button" style="font-size: 15px; float: right; width: 40px; height: 30px" value="목록" 
		onClick="history.back()">
		<%
			String current_id = (String) session.getAttribute("m_id");
			if (auction.getM_id().equals(current_id))
				out.println("<input type='button' style='font-size:15px;margin-right:10px;float:right;width:40px;height:30px' value='삭제' onClick='deleteAuction()''>");
			else if (current_id != null)
				out.println("<input type='button' style='font-size:15px;margin-right:10px;float:right;width:40px;height:30px' value='담기' onClick='favoriteAuction()''>");
		%>
		</form>
		<br><br><br>
		<hr style="border: solid 1px gray;">
		<br><br>
		
		<form name="f_reply" method="GET" action="reply_write.m2">
		<input type="hidden" name="command">
		<input type="hidden" name="a_id" value="<%=auction.getA_id()%>" /> 
		<input type="hidden" name="r_id" />
		
<%
	int num=1;
	if (replyList != null) {	
	  Iterator<Reply> replyIter = replyList.iterator();
	
	  while ( replyIter.hasNext() ) {
		Reply reply = (Reply)replyIter.next();
%>		  	
	  <table style="width:600px;table_layout:fixed;">
	  	<tr><td><font size=3>&nbsp;</font></td></tr>
		<tr>
			<td rowspan=3 width="40px"><%=num%>&nbsp;&nbsp;</td>
			<td rowspan=3>
				<img width="70px" src = "<%=reply.getI_img()%>" onClick="OnloadImg(this.src)" />
			</td>
			<td width="200px" style="word-break:break-all" align="right" bgcolor="ffffff" height="20">
				<font style="font-size: 18px;"><%= reply.getR_comment() %></font>
			</td>
			<tr>
			<td width="200px" style="word-break:break-all" align="right" bgcolor="ffffff" height="20">
				<font style="font-size: 12px;"> <%= reply.getM_id() %> | <%= reply.getR_date() %></font>
			</td>
		</tr>
		<tr><td>
		<%
			if(auction.getA_state().equals("false") && auction.getM_id().equals((String)session.getAttribute("m_id"))) {
		%>
				<input type='button' style="float:right"  value='교환하기' onClick="matchingReply(<%= reply.getR_id() %>)">
		<%
			} else {
				out.print("<font size=3>&nbsp;</font>");
			}
		%>
		</td></tr>
		
		</table>
<%
		num++;
	  }
	}

		out.print("<br><br>");
		if(session.getAttribute("m_id") != null && !session.getAttribute("m_id").equals(auction.getM_id())) {
%>	
		<input type="button" style="float:right" value="댓글 쓰기" onClick="replyInsert(<%=auction.getA_id()%>)" /><br>
<%		
		}
		
%>
		
		</form>
		
	</div>
</div>
</center>
<br><br><br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	@SuppressWarnings("unchecked")
	List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>
<%
	@SuppressWarnings("unchecked") 
	List<Auction> auctionList = (List<Auction>)request.getAttribute("myAuctionList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>마이페이지(내 정보보기)</title>
</head>
<script>
function memberModify() {
	mypage_form.command.value = "mem_update";
	mypage_form.action = "mem_modify.m2";
	mypage_form.submit();
}

function memberRemove() {
	if ( confirm("정말 탈퇴하시겠습니까?") ) {
		mypage_form.command.value = "mem_delete";
		mypage_form.action = "mem_remove.m2";
		mypage_form.submit();
	}
}

function itemInsert() {
	window.open("./item_insert.jsp", "아이템 추가", "location=no, scrollbars=no, toolbar=no, width=450, height=200");
	window.location.reload(true);
}
function itemDelete() {
	if ( confirm("정말 아이템을 삭제하시겠습니까?") ) {
		myItem_form.command.value = "item_delete";
		myItem_form.action = "item_delete.m2";
		myItem_form.submit();
	}	
}
</script>
</head>
<body>
<jsp:include page="upperBar.jsp" />
<center>
<br>
	<form name="mypage_form" method="GET">
		<input type="hidden" name="command" /> <input type="hidden"
			name="m_id" value="${member.m_id}" />

		<table style="background-color: #E6ECDE; width: 70%" border=4>
			<tr>
				<td colspan='2' height="40" align="center" style="font-size:13pt;">&nbsp;&nbsp;<b>마이페이지(내정보보기)</b></td>
			</tr>
			<tr>
				<td width="25%" height="26" bgcolor="ffffff" align="center" height="22">사용자ID</td>
				<td  bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_id}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">이름</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_name}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">전화번호</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_phone}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">이메일</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_email}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">주소</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_address}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">평점</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_score}</td>
			</tr>
			<tr >
				<td height="40" align="center" colspan='2'><input type="button" style="font-size:11pt;" value="정보 수정"
					onClick="memberModify()"> &nbsp; <input type="button" style="font-size:11pt;"
					value="회원 탈퇴" onClick="memberRemove()"></td>
			</tr>
		</table>
	</form>
<br>	
<a style='color:#616161' href='javascript:void(0)' onclick="this.innerHTML=(this.nextSibling.style.display=='none')?'아이템 목록 닫기':'내아이템들';this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'";>내아이템들</a><DIV style='display:none'>
	<!-- 해당 사용자에 맞는 아이템 보여주기 -->
<br>
<form name="myItem_form" method="POST">
	<input type="hidden" name="command"/>
	&nbsp;내 아이템들&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="아이템 추가" onClick="itemInsert()"> &nbsp;
	<input type="button" value="아이템 삭제" onClick="itemDelete()">
	<table style="width: 30%" >
<%
	int num=1;
	if (itemList != null) {	
	  Iterator<Item> itemIter = itemList.iterator();
	
	  while ( itemIter.hasNext() ) {
		Item item = (Item)itemIter.next();


		%>
		<tr>
    	<td rowspan="3" width=25 align="center"><input type="checkbox" name="chkbox_del" value="<%=item.getI_id()%>"></td>
      	<tr>
          <td rowspan='2' width=25 align="center"><%=num %></td>
          <td><img src=<%=item.getI_img()%> width='100px' height='100px'></td>
     	</tr>
      	<tr>
        	<td ><%=item.getI_name() %></td>
      	</tr>
		<%
		
			num++;
		}
	  

	} else {
		out.print("<tr><td>아이템을 추가해주세요!</td></tr>");
	}
%>
	</table>
</form>
</DIV>
<br>
<span style="font-size:1"><br></span>

<!-- 매칭 목록 -->
<a style='color:#616161' href='javascript:void(0)' onclick="this.innerHTML=(this.nextSibling.style.display=='none')?'매칭목록 닫기':'매칭목록 열기';this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'";>내 매칭 목록 보기</a><DIV style='display:none'>
<br> 
<div align=center>
<table style="margin-top: 60px;">
<%
	int numMat=1;
	
	if (auctionList != null) {	
	  Iterator<Auction> auctionIter = auctionList.iterator();
	
	  while ( auctionIter.hasNext() ) {
		Auction auction = (Auction)auctionIter.next();
%>
		<tr>
			<td><%=numMat %>&nbsp;&nbsp;</td>
			<td width="700px">
				<a  style="color:black" href="matching_view.m2?command=matching_view&a_id=<%=auction.getA_id()%> ">
					<%= auction.getA_title() %>
				</a>
			</td>
		</tr>
		<tr><td><font size=2>&nbsp;</font></td></tr>
<%
		numMat++;
	  }
	} else {
		out.print("<tr><td>매칭된 글이 존재하지 않습니다</td></tr>");
	}
%>
</table>
</div>
<br>
</DIV>
<br><br>
</center>
</body>
</html>
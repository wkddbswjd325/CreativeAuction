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
<title>����������(�� ��������)</title>
</head>
<script>
function memberModify() {
	mypage_form.command.value = "mem_update";
	mypage_form.action = "mem_modify.m2";
	mypage_form.submit();
}

function memberRemove() {
	if ( confirm("���� Ż���Ͻðڽ��ϱ�?") ) {
		mypage_form.command.value = "mem_delete";
		mypage_form.action = "mem_remove.m2";
		mypage_form.submit();
	}
}

function itemInsert() {
	window.open("./item_insert.jsp", "������ �߰�", "location=no, scrollbars=no, toolbar=no, width=450, height=200");
	window.location.reload(true);
}
function itemDelete() {
	if ( confirm("���� �������� �����Ͻðڽ��ϱ�?") ) {
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
				<td colspan='2' height="40" align="center" style="font-size:13pt;">&nbsp;&nbsp;<b>����������(����������)</b></td>
			</tr>
			<tr>
				<td width="25%" height="26" bgcolor="ffffff" align="center" height="22">�����ID</td>
				<td  bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_id}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">�̸�</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_name}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">��ȭ��ȣ</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_phone}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">�̸���</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_email}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">�ּ�</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_address}</td>
			</tr>
			<tr>
				<td height="26" bgcolor="ffffff" align="center" height="22">����</td>
				<td bgcolor="ffffff" align="left" style="padding-left:40px;">${member.m_score}</td>
			</tr>
			<tr >
				<td height="40" align="center" colspan='2'><input type="button" style="font-size:11pt;" value="���� ����"
					onClick="memberModify()"> &nbsp; <input type="button" style="font-size:11pt;"
					value="ȸ�� Ż��" onClick="memberRemove()"></td>
			</tr>
		</table>
	</form>
<br>	
<a style='color:#616161' href='javascript:void(0)' onclick="this.innerHTML=(this.nextSibling.style.display=='none')?'������ ��� �ݱ�':'�������۵�';this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'";>�������۵�</a><DIV style='display:none'>
	<!-- �ش� ����ڿ� �´� ������ �����ֱ� -->
<br>
<form name="myItem_form" method="POST">
	<input type="hidden" name="command"/>
	&nbsp;�� �����۵�&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="������ �߰�" onClick="itemInsert()"> &nbsp;
	<input type="button" value="������ ����" onClick="itemDelete()">
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
		out.print("<tr><td>�������� �߰����ּ���!</td></tr>");
	}
%>
	</table>
</form>
</DIV>
<br>
<span style="font-size:1"><br></span>

<!-- ��Ī ��� -->
<a style='color:#616161' href='javascript:void(0)' onclick="this.innerHTML=(this.nextSibling.style.display=='none')?'��Ī��� �ݱ�':'��Ī��� ����';this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'";>�� ��Ī ��� ����</a><DIV style='display:none'>
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
		out.print("<tr><td>��Ī�� ���� �������� �ʽ��ϴ�</td></tr>");
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
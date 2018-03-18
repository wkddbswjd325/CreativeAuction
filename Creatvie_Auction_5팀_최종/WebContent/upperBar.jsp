<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/menuStyle.css" type="text/css" />
<link rel="stylesheet" href="css/drop_down_menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>menu</title>
<script>
function auctionList(cate) {
	f_upper.command.value = "list";
	f_upper.cate.value = cate;
	f_upper.action = "auction_list.m2";
	f_upper.submit();
}
</script>
</head>
<body>

<div id="header" align=right style="padding-top:20px; padding-right:20px;font-weight:bold;">
<%
	if(session.getAttribute("m_id") == null) {
		out.println("<a style='color:#616161' href='login.jsp'>�α���</a>&nbsp;");
		out.println("<a style='color:#616161'; href='member_join.m2?command=mem_join'>ȸ������</a>&nbsp;");
	}
	else { 
		String current_id = (String)session.getAttribute("m_id");
		out.println("<a style='color:#616161' href='my_page.m2?command=mem_view'>" +current_id +"�� </a>");
		out.println("<a style='color:#616161' href='logout.m2?command=logout'> &nbsp �α׾ƿ�&nbsp</a>");
	}
%>
	<a style="color:#616161" href="favorite.m2?command=favor_list">������</a>&nbsp;
	<%--���⿡ if else������ �α� ������ �������� üũ --%>
	<a style="color:#616161" href="auction_write.m2?command=insert&big_cate=1&small_cate=0">����ǰ������</a>
</div>

<div id="header" align=center>
	<span id="logo" style="padding-bottom:20px;"><img src="img/logo.png" width="200px" onClick="document.location='main.m2?command=main'"></span>
</div>

<form name="f_upper" method="post">
<input type="hidden" name="command"/>
<input type="hidden" name="cate"/>

<ul style="padding-left:10%;padding-right:10%;">
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(1);" class="dropbtn">�м��Ƿ�</a>
	    <div class="dropdown-content">
	   		<a onClick="auctionList(13)">�����Ƿ�</a>
	    	<a onClick="auctionList(14)">�����Ƿ�</a>
	    	<a onClick="auctionList(15)">�������/���</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(2)" class="dropbtn">��Ƽ��ȭ</a>
	    <div class="dropdown-content">
	   		<a onClick="auctionList(16)">����/����ȭ/����ȭ</a>
	   		<a onClick="auctionList(17)">����/�м���ȭ</a>
	    	<a onClick="auctionList(18)">���/�ð�/���۶�</a>
	    	<a onClick="auctionList(19)">���Ը�ǰ</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(3)" class="dropbtn">���ƿ�ǰ</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(20)">������/����/������</a>
	    	<a onClick="auctionList(21)">���/���ƿ�ǰ/�Ӻκ�</a>
	    	<a onClick="auctionList(22)">�峭��/�����ϱ�/����</a>
	    	<a onClick="auctionList(23)">���Ƶ��Ƿ�</a>
	    	<a onClick="auctionList(24)">���Ƶ��Ź�/����/��ȭ</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(4)" class="dropbtn">������Ȱ</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(25)">����/���׸���</a>
	    	<a onClick="auctionList(26)">ħ��/Ŀư/����</a>
	    	<a onClick="auctionList(27)">��Ȱ/���/������ǰ</a>
	    	<a onClick="auctionList(28)">�ֹ��ǰ/�����ֹ�</a>
	    	<a onClick="auctionList(29)">�ǰ���ǰ/���̾�Ʈ</a>
	    	<a onClick="auctionList(30)">��ǰ</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(5)" class="dropbtn">����÷���</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(31)">�ǰ�/�Ƿ��ǰ</a>
	    	<a onClick="auctionList(32)">�ݷ�������ǰ</a>
	    	<a onClick="auctionList(33)">�Ǳ�/���/Ű��Ʈ</a>
	    	<a onClick="auctionList(34)">������/����/�繫��ǰ</a>
	    	<a onClick="auctionList(35)">��/����/�ҽ�/����</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(6)" class="dropbtn">������</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(36)">ī�޶�/�Ǽ��縮</a>
	    	<a onClick="auctionList(37)">������</a>
	    	<a onClick="auctionList(38)">���ӱ�/Ÿ��Ʋ</a>
	    	<a onClick="auctionList(39)">�޴���/����Ʈ��</a>
	    	<a onClick="auctionList(40)">�º�/�׼�����</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(7)" class="dropbtn">��ǻ��</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(41)">��Ʈ��/����ũž</a>
	    	<a onClick="auctionList(42)">�����/����Ʈ��ũ</a>
	    	<a onClick="auctionList(43)">��ǻ�ͺ�ǰ/�ֺ����</a>
	    	<a onClick="auctionList(44)">������ġ/�޸�</a>
	    </div>
	</li>      
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(8)" class="dropbtn">����������</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(45)">������/�ｺ/����</a>
	    	<a onClick="auctionList(46)">�������Ƿ�/�ȭ</a>
	    	<a onClick="auctionList(47)">����Ŭ��/�Ƿ�/��ǰ</a>
	    	<a onClick="auctionList(48)">���/�ƿ�����</a>
	    	<a onClick="auctionList(49)">ķ��/����/����</a>
	    	<a onClick="auctionList(50)">����/����/������</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(9)" class="dropbtn">��Ƽ</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(51)">ȭ��ǰ/���</a>
	    	<a onClick="auctionList(52)">�ٵ�/���</a>   	
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(10)" class="dropbtn">��Ȱ����</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(53)">Ƽ��/��������</a>
	    	<a onClick="auctionList(54)">�����/��Ź��/û�ұ�</a>
	    	<a onClick="auctionList(55)">�ó���/û��/��������</a>
	    	<a onClick="auctionList(56)">�ֹ氡��</a>
	    	<a onClick="auctionList(57)">��Ȱ/�̹̿밡��</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(11)" class="dropbtn">�ڵ�������</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(58)">�ڵ�����ǰ/���ڽ�</a>
	    	<a onClick="auctionList(59)">Ÿ�̾�/����/��ǰ</a>
	    	<a onClick="auctionList(60)">����/�����ǰ/������</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(12)" class="dropbtn">����/��Ÿ</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(61)">����/����/����</a>
	    	<a onClick="auctionList(62)">��ȭ��/��ȭ��ǰ��</a>
	    	<a onClick="auctionList(63)">����/�װ���</a>
	    	<a onClick="auctionList(64)">e����/����ϻ�ǰ��</a>
	    </div>
	</li>  
</ul>

</form>
</body>
</html>
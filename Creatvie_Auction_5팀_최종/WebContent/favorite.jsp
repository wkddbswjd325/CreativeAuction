<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@include file="loginCheck.jsp" %>
<%
	@SuppressWarnings("unchecked") 
	List<Auction> FavoriteList = (List<Auction>)request.getAttribute("FavoriteList");
%>
<html>
<head>
<title>보관함</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script>
function del_favorite() {
	f_favorite.command.value = "favor_delete";
	f_favorite.action = "favorite_delete.m2";
	f_favorite.submit();
}
</script>
</head>
<body>
<jsp:include page="upperBar.jsp" />

<div align=center>
<form name="f_favorite" method="post">
<input type="hidden" name="command"/>

<table style="margin-top: 60px;">
<%
	int num=1;
	if (FavoriteList != null) {	
	  Iterator<Auction> FavoriteIter = FavoriteList.iterator();
	
	  while ( FavoriteIter.hasNext() ) {
		Auction auction = (Auction)FavoriteIter.next();
	
%>
		<tr>
			<td rowspan=3><input type="checkbox" name="chkbox_del" value="<%=auction.getA_id()%>">
			&nbsp;<%=num %>&nbsp;&nbsp;</td>
			<td rowspan=3 width="130px"><img src="<%=auction.getI_img()%>" width="100px"></td>
			<td width="700px">
				<a  style="color:black" href="auction_view.m2?command=auc_view&a_id=<%=auction.getA_id()%> ">
					[<%= auction.getSmall_cate_name() %>] <%= auction.getA_title() %>
				</a>
			</td>
			<td rowspan=3>
				<%
					if(auction.getA_state().equals("false")) 
						out.print("<font color='red'>경매중</font>");
					else
						out.print("<font color='blue'>경매완료</font>");
				%>	
			</td>
		</tr>
		<tr>
			<td>작성자 : <%= auction.getM_id() %></td>
		</tr>
		<tr><td><font size=5>&nbsp;</font></td></tr>
		<tr><td><font size=2>&nbsp;</font></td></tr>
<%
		num++;
	  }
	} else {
		out.print("<tr><td>보관함에 담긴 글이 없습니다</td></tr>");
	}
%>
</table>
<div align=right style="padding-right:20%">
	<input type="submit" style="color: black; width: 50px; height: 30px" onClick='del_favorite()' value="삭제" />
</div>
</form> 
</div>

</body>
</html>
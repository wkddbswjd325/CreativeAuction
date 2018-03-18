<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%
	@SuppressWarnings("unchecked") 
	List<Auction> auctionList = (List<Auction>)request.getAttribute("auctionList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MainPage</title>
</head>
<body>

<jsp:include page="upperBar.jsp" />
<jsp:include page="searchBar.jsp" />

<center>
<table style="margin-top: 60px;">
<%
	int num=1;
	if (auctionList != null) {	
	  Iterator<Auction> auctionIter = auctionList.iterator();
	
	  while ( auctionIter.hasNext() ) {
		Auction auction = (Auction)auctionIter.next();
		
	//	auction.getSmall_cate_id()  
%>
		<tr>
			<td rowspan=3><%=num %>&nbsp;</td>
			<td rowspan=3 width="130px"><img src="<%=auction.getI_img()%>" width="100px"></td>
			<td width="700px">
				<a style="color:black" href="auction_view.m2?command=auc_view&a_id=<%=auction.getA_id()%> ">
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
	}
%>
</table> 
<br><br><br>
</center>
</body>
</html>
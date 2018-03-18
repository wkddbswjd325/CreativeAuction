<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%
	@SuppressWarnings("unchecked") 
	List<Auction> auctionList = (List<Auction>)request.getAttribute("auctionList");
%>
<html>
<head>
<title>경매 리스트</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script>
function goWrite() {
   formWrite.command.value = "insert";
   formWrite.method = "GET";
   formWrite.action = "auction_write.m2";
   formWrite.submit();
}
</script>
</head>
<body>

<jsp:include page="upperBar.jsp" />
<jsp:include page="searchBar.jsp" />

<br> 
<div align=center>
<table style="margin-top: 60px;">
<%
	int num=1;
	int big_cate = 0, small_cate=0;
	
	if(request.getParameter("cate") == null) ;
	else if(Integer.parseInt(request.getParameter("cate"))>=1&&Integer.parseInt(request.getParameter("cate"))<13)
		big_cate = Integer.parseInt(request.getParameter("cate"));
   	else
		small_cate= Integer.parseInt(request.getParameter("cate"));
	
	if (auctionList != null) {	
	  Iterator<Auction> auctionIter = auctionList.iterator();
	
	  while ( auctionIter.hasNext() ) {
		Auction auction = (Auction)auctionIter.next();
%>
		<tr>
			<td rowspan=3><%=num %>&nbsp;&nbsp;</td>
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
		if(big_cate==0)
    		big_cate = auction.getBig_cate_id();
		num++;
	  }
	} else {
		out.print("<tr><td>경매글이 존재하지 않습니다</td></tr>");
	}
%>
</table>
</div>
<br>
<form name="formWrite" method="get">
<input type="hidden" name="command"/>
<input type="hidden" name="big_cate" value="<%=big_cate%>">
<input type="hidden" name="small_cate" value="<%=small_cate%>">
   <table style="width:80%;">
      <tr>
         <td style="float:right;">
            <input type="button" value="글 작성" onClick="goWrite()"/>
         </td>
      </tr>
     </table>
</form>
	
<br><br><br>
  	
</body>
</html>


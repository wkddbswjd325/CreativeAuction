<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@include file="loginCheck.jsp" %>
<jsp:useBean id="b_sel" scope="page" class="model.CategoryDAO" />

<%
	String cp = request.getContextPath();
	List<Category> bigCateList = new ArrayList<Category>();
	bigCateList = b_sel.selectBigCateList();
	Category bigCate = new Category();
%>
<%--ContextPath ���� --%>
<html>
<head>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<jsp:include page="upperBar.jsp" />
<script type="text/javascript">
//ajax
 $(document).ready(function(){
  
  $('#auctionBigCate').change(function(){
   
   var param = 'auctionBigCate='+$(this).val();
   
   $.ajax({
    url : 'jqueryAjaxCate.jsp',
    data : param,
    contentType : "application/x-www-form-urlencoded; charset=euc-kr",
    type : 'post',
    dataType : 'text',
    success : function(data){
     $("#auctionSmallCate").html(data);
    }
   
   });
  });
  
 });
</script>
<title>��ű� �ۼ�</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script>
	function auctionCreate() {
		if (form_write.auctionTitle.value == "") {
			alert("�������Է��ϼ���");
			form_write.auctionTitle.focus();
			return false;
		}
		if (form_write.auctionBigCate.value == "") {
			alert("��з��� �����ϼ���");
			form_write.auctionBigCate.focus();
			return false;
		}
		if (form_write.auctionSmallCate.value == "") {
			alert("�Һз��� �����ϼ���");
			form_write.auctionSmallCate.focus();
			return false;
		}
		if (form_write.itemId.value == "") {
			alert("�������� �����ϼ���");
			form_write.itemId.focus();
			return false;
		}
		if (form_write.auctionContent.value == "") {
			alert("���� �ۼ��ϼ���");
			form_write.auctionContent.focus();
			return false;
		}
		form_write.command.value = "insert";
		form_write.action = "auction_write.m2";
		form_write.submit();
	}

	function cancel() {
		form_write.command.value = "list";
		form_write.action = "auction_list.m2";
		from_write.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<%
		if(request.getParameter("exception") != null)
			out.print("<script>alert('�̹� ���� �������Դϴ�. �ٸ� �������� �÷��ּ���')</script>");
	%>
	<br>
	<!-- write Form  -->
	<form name="form_write" method="post">
		<input type="hidden" name="command" />
		<table style="width: 100%">
			<%
				//session�� ����� id �ҷ�����
				String userId = (String) session.getAttribute("m_id");
				@SuppressWarnings("unchecked")
				List<Item> itemList = (List<Item>) request.getAttribute("itemList");
			%>
			<tr>
				<td width="20"></td>
				<td>
					<!--contents-->
					<table style="width: 100%">
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>��ŰԽñ�</b></td>
						</tr>
					</table> <br> <!--  exception ��ü�� ���޵� ��� �����޽����� ��� --> <c:if
						test="${not empty exception}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if> <br>

					<table
						style="border-left: 0px none; border-right: 0px none; border-top: 0px none; border-bottom: 0px none; margin-left: auto; margin-right: auto;">
						<tr height="40">
							<td width="150" align="center" bgcolor="gray" style="color: white">ID</td>
							<td width="400" bgcolor="ffffff" style="padding-left: 10"><%=userId%>
							</td>
						</tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="gray" style="color: white">����</td>
							<td width="400" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="auctionTitle"></td>
						</tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="gray" style="color: white">ī�װ�</td>
							<td width="400" bgcolor="ffffff" style="padding-left: 10">
							<select name="auctionBigCate" id="auctionBigCate" style="font-size: 14px;" >
									<%
										if (bigCateList!= null && bigCateList.size() > 0) {
											for (int i = 0; i < bigCateList.size(); i++) {
												bigCate = bigCateList.get(i);
									%>
									<option value="<%=bigCate.getBig_cate_id()%>"
										<%if (request.getParameter("big_cate").equals(String.valueOf(bigCate.getBig_cate_id()))) 
										{%> selected <%}%>><%=bigCate.getBig_cate_name()%></option>
									<%
										}
										}
									%>
									</select>
							<select name="auctionSmallCate" id="auctionSmallCate" style="font-size: 14px;">
									<%
									List<Category> smallCateList = new ArrayList<Category>();
									smallCateList = b_sel.selectSmallCateList(Integer.valueOf(request.getParameter("big_cate")));
									Category smallCate = new Category();
										if (smallCateList!= null && smallCateList.size() > 0) {
											for (int i = 0; i < smallCateList.size(); i++) {
												 smallCate = smallCateList.get(i);
									%>
									<option value="<%=smallCate.getSmall_cate_id()%>"
										<%if (request.getParameter("small_cate").equals(String.valueOf(smallCate.getSmall_cate_id()))) 
										{%> selected <%}%>><%=smallCate.getSmall_cate_name()%></option>
									<%
										}
										}
									%>
							</select>
							</td>
						</tr>
						
						<tr height="40">
							<td  width="150" align="center" bgcolor="gray" style="color: white">������ ����</td>
							<!-- �ش� ����ڿ� �´� ������ �����ֱ� -->
							
							<td>
							
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
								out.print("<td>�������� �߰����ּ���!</td>");
							}
						%>
						</table>
						</td></tr>
						<tr height="40">
							<td width="150" align="center" bgcolor="gray" style="color: white">���ۼ�</td>
							<td width="400" bgcolor="ffffff" style="padding-left: 10">
							<TEXTAREA name="auctionContent" id="auctionContent" COLS="70" ROWS="4"></TEXTAREA>
							</td>
						</tr>
					</table> <br>

					<table style="border-left: 0px none; border-right: 0px none; border-top: 0px none; border-bottom: 0px none; margin-left: auto; margin-right: auto;">
						<tr>
							<td align="center">
							<input type="button" value="���ۼ�" onClick="auctionCreate()"> &nbsp; 
							<input type="button" value="���" onClick="history.go(-1);">
							</td>
						</tr>
					</table>
					</td>
					</tr>
					</table>
	</form>
</body>
</html>
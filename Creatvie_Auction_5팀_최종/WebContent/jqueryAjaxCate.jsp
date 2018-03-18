<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@page import="model.*"%>
<jsp:useBean id="b_sel" scope="page" class="model.CategoryDAO" />
<%
 int big_cate_id = Integer.valueOf(request.getParameter("auctionBigCate"));
 
 List<Category> smallCateList = new ArrayList<Category>();
 smallCateList = b_sel.selectSmallCateList(big_cate_id);
 Category smallCate = new Category();
 
 if(smallCateList != null && smallCateList.size() > 0){
  for(int i=0; i < smallCateList.size(); i++){
	  smallCate = smallCateList.get(i);
   if(i == 0){
    out.println("<option value=''>카테고리 선택</option>");
   }
   out.println("<option value='"+smallCate.getSmall_cate_id()+"'>"+smallCate.getSmall_cate_name()+"</option>");
  }
 }
%>
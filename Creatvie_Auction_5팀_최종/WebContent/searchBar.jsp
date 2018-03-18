<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/menuStyle.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
function search() {
	f_search.command.value = "search";
	f_search.action = "auction_list.m2";
	f_search.submit();
}
</script>
</head>
<body>
<form name="f_search" method="post">
<input type="hidden" name="command"/>

<div style="float:right;padding:3px;">
	<select style="width:80px; height:33px" name="select_search" size=1>
        <option value="title" selected>글 제목</option>
        <option value="writer">글 작성자</option>
    </select>
	<span class='search_window'>
		<input type='text' name="search_text" class='input_text' />
	</span>
	<button type='submit' id='smt' onClick='search()' class='sch_smit'>검색</button> 
</div>


</form>
</body>
</html>
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
		out.println("<a style='color:#616161' href='login.jsp'>로그인</a>&nbsp;");
		out.println("<a style='color:#616161'; href='member_join.m2?command=mem_join'>회원가입</a>&nbsp;");
	}
	else { 
		String current_id = (String)session.getAttribute("m_id");
		out.println("<a style='color:#616161' href='my_page.m2?command=mem_view'>" +current_id +"님 </a>");
		out.println("<a style='color:#616161' href='logout.m2?command=logout'> &nbsp 로그아웃&nbsp</a>");
	}
%>
	<a style="color:#616161" href="favorite.m2?command=favor_list">보관함</a>&nbsp;
	<%--여기에 if else문으로 로긴 했을때 안했을때 체크 --%>
	<a style="color:#616161" href="auction_write.m2?command=insert&big_cate=1&small_cate=0">내상품내놓기</a>
</div>

<div id="header" align=center>
	<span id="logo" style="padding-bottom:20px;"><img src="img/logo.png" width="200px" onClick="document.location='main.m2?command=main'"></span>
</div>

<form name="f_upper" method="post">
<input type="hidden" name="command"/>
<input type="hidden" name="cate"/>

<ul style="padding-left:10%;padding-right:10%;">
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(1);" class="dropbtn">패션의류</a>
	    <div class="dropdown-content">
	   		<a onClick="auctionList(13)">여성의류</a>
	    	<a onClick="auctionList(14)">남성의류</a>
	    	<a onClick="auctionList(15)">언더웨어/잠옷</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(2)" class="dropbtn">뷰티잡화</a>
	    <div class="dropdown-content">
	   		<a onClick="auctionList(16)">구두/여성화/남성화</a>
	   		<a onClick="auctionList(17)">가방/패션잡화</a>
	    	<a onClick="auctionList(18)">쥬얼리/시계/선글라스</a>
	    	<a onClick="auctionList(19)">수입명품</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(3)" class="dropbtn">유아용품</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(20)">기저귀/분유/이유식</a>
	    	<a onClick="auctionList(21)">출산/유아용품/임부복</a>
	    	<a onClick="auctionList(22)">장난감/교육완구/인형</a>
	    	<a onClick="auctionList(23)">유아동의류</a>
	    	<a onClick="auctionList(24)">유아동신발/가방/잡화</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(4)" class="dropbtn">가구생활</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(25)">가구/인테리어</a>
	    	<a onClick="auctionList(26)">침구/커튼/조명</a>
	    	<a onClick="auctionList(27)">생활/욕실/수납용품</a>
	    	<a onClick="auctionList(28)">주방용품/수입주방</a>
	    	<a onClick="auctionList(29)">건강식품/다이어트</a>
	    	<a onClick="auctionList(30)">식품</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(5)" class="dropbtn">취미컬렉션</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(31)">건강/의료용품</a>
	    	<a onClick="auctionList(32)">반려동물용품</a>
	    	<a onClick="auctionList(33)">악기/취미/키덜트</a>
	    	<a onClick="auctionList(34)">디자인/문구/사무용품</a>
	    	<a onClick="auctionList(35)">꽃/원예/팬시/선물</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(6)" class="dropbtn">디지털</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(36)">카메라/악세사리</a>
	    	<a onClick="auctionList(37)">음향기기</a>
	    	<a onClick="auctionList(38)">게임기/타이틀</a>
	    	<a onClick="auctionList(39)">휴대폰/스마트폰</a>
	    	<a onClick="auctionList(40)">태블릿/액세서리</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(7)" class="dropbtn">컴퓨터</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(41)">노트북/데스크탑</a>
	    	<a onClick="auctionList(42)">모니터/프린트잉크</a>
	    	<a onClick="auctionList(43)">컴퓨터부품/주변기기</a>
	    	<a onClick="auctionList(44)">저장장치/메모리</a>
	    </div>
	</li>      
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(8)" class="dropbtn">스포츠레저</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(45)">자전거/헬스/수영</a>
	    	<a onClick="auctionList(46)">스포츠의류/운동화</a>
	    	<a onClick="auctionList(47)">골프클럽/의류/용품</a>
	    	<a onClick="auctionList(48)">등산/아웃도어</a>
	    	<a onClick="auctionList(49)">캠프/낚시/보드</a>
	    	<a onClick="auctionList(50)">구기/라켓/스포츠</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(9)" class="dropbtn">뷰티</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(51)">화장품/향수</a>
	    	<a onClick="auctionList(52)">바디/헤어</a>   	
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(10)" class="dropbtn">생활가전</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(53)">티비/프로젝터</a>
	    	<a onClick="auctionList(54)">냉장고/세탁기/청소기</a>
	    	<a onClick="auctionList(55)">냉난방/청정/제습가전</a>
	    	<a onClick="auctionList(56)">주방가전</a>
	    	<a onClick="auctionList(57)">생활/이미용가전</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(11)" class="dropbtn">자동차공구</a>
	    <div class="dropdown-content">
	    	<a onClick="auctionList(58)">자동차용품/블랙박스</a>
	    	<a onClick="auctionList(59)">타이어/오일/부품</a>
	    	<a onClick="auctionList(60)">공구/산업용품/포장재</a>
	    </div>
	</li>
	<li class="dropdown">
	    <a href="javascript:void(0)" onClick="auctionList(12)" class="dropbtn">도서/기타</a>
	    <div class="dropdown-content">
			<a onClick="auctionList(61)">도서/교육/음반</a>
	    	<a onClick="auctionList(62)">백화점/제화상품권</a>
	    	<a onClick="auctionList(63)">여행/항공권</a>
	    	<a onClick="auctionList(64)">e쿠폰/모바일상품권</a>
	    </div>
	</li>  
</ul>

</form>
</body>
</html>
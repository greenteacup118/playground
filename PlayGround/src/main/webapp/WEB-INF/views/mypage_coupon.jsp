<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginId" value="${sessionScope.uId_Session}" />
<c:set var="urlInfo" value="/resources/" />
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>mypage</title>

<!-- font awesome -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- css -->
<link rel="stylesheet" href="${urlInfo}css/default.css">
<link rel="stylesheet" href="${urlInfo}css/mypage.css">
<style type="text/css">
.page {
	color: black;
	padding: 6px;
	margin-right: 10px;
}

.paging-active {
	background-color: rgb(216, 216, 216);
	border-radius: 5px;
	color: rgb(24, 24, 24);
}
</style>
<!-- js -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${urlInfo}script/script.js"></script>

</head>

<body>
	<div id="wrap">
		<header>
			<div class="center">
				<!-- <div class="header_fix"> -->
				<!-- <div class="logo"> -->
				<a href="/#container" class="logo"> <span>PLAY</span> <span>GROUND</span>
				</a>
				<!-- <a href="#"><img src="image/logo.png" alt="logo"></a> -->
				<!-- </div> -->
				<nav class="gnb">
					<ul class="main_menu">
						<li class="main_list"><a href="#">PLAY GROUND</a>
							<ul class="sub_menu">
								<li class="sub_list"><a href="/exhibit/list?exState=N">현재 전시</a></li>
								<li class="sub_list"><a href="/exhibit/list?exState=B">지난 전시</a></li>
								<li class="sub_list"><a href="/exhibit/list?exState=A">예정 전시</a></li>
							</ul></li>
						<li class="main_list"><a href="#">STORE</a> <!-- <ul class="sub_menu">
                <li class="sub_list"><a href="#"></a></li>
              </ul> --></li>
						<li class="main_list"><a href="#">BOARD</a>
							<ul class="sub_menu">
								<li class="sub_list"><a href="#">공지사항</a></li>
                                <li class="sub_list"><a href="/faq/bbs">FAQ</a></li>
                                <li class="sub_list"><a href="/inquiry/board">1:1문의</a></li>
							</ul></li>
					</ul>
				</nav>
				<div class="quick_menu">
					<ul>
						<c:choose>
							<c:when test="${loginId eq null}">
								<a href="/Join/page" style="margin-right: 20px;"><li>회원가입</li></a>
								<a href="/Login/Login"><li>LOGIN</li></a>
							</c:when>
							<c:otherwise>
								<a href="/Login/Logout" style="margin-right: 20px;"><li>LOGOUT</li></a>
								<a href="/mypage/myPageHome"><li>MY PAGE</li></a>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<!-- </div> -->
		</header>
		<!-- 탑버튼 시작 -->
		<div class="center">
			<div id="top_button">
				<a href="#"> <i class="material-icons" style="font-size: 30px">vertical_align_top</i>
				</a>
			</div>
		</div>
		<!-- 탑버튼 끝 -->
		<div class="containts">
			<div class="center myp">
				<div class="menu_bar">
					<ul class="main_meun">
						<a href="/mypage/myPageHome">MY PAGE</a>
					</ul>
					<ul class="main_list myt">
						<a class="main_title" href="/mypage/reList">MY TICKETS</a>
						<li class="sub_list"><a href="/mypage/reList">티켓예매목록</a></li>
					</ul>
					<ul class="main_list">
						<a class="main_title" href="">MY INFO</a>
						<li class="sub_list"><a href="/mypage/mycoupon?page=1&state=U">나의 쿠폰</a></li>
						<li class="sub_list"><a href="/mypage/mypoint?page=1">적립금</a></li>
						<li class="sub_list"><a href="/mypage/myreview?page=1">나의 리뷰</a></li>
						<li class="sub_list"><a href="/mypage/userpwchk">개인정보변경/탈퇴</a></li>
					</ul>
				</div>


				<c:set var="Coupon" value="${CouponList}" />


				<div class="contents4_1">
					<h2 class="con_title">나의 쿠폰</h2>
					<div class="container">
						<ul class="tabs">
							<li class="tab-link current" data-tab="tab-1">사용 가능한 쿠폰</li>
							<li class="tab-link" data-tab="tab-2">사용완료 쿠폰</li>
						</ul>
						<p class="con4_info">1인 1회 혜택입니다. 사용하기 버튼을 누를 시 혜택 적용이 불가합니다.
							(현장 직원만 클릭 가능)</p>
						<div id="tab-1" class="tab-content current">
							<c:choose>
								<c:when test="${poCouponList.size() eq 0}">
									<div class="con5">
										<p>사용가능한 쿠폰이 없습니다.</p>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="cpDto" items="${poCouponList}">
										<div class="con5">
											<div class="coupon_box">
												<div class="coupon_boxs">
													<div class="coupon_text">
														<p>${cpDto.couInfo}</p>
														<h3>${cpDto.coupon }</h3>
														<fmt:formatDate var="start" value="${cpDto.couDate1 }"/>
														<fmt:formatDate var="end" value="${cpDto.couDate2 }"/>
														<p>${start }~${end }</p>
													</div>
													<div class="coupon_btn">
														<a class="coup_btn" href="#">확인</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>



						<div id="tab-2" class="tab-content sub">
							<c:choose>
								<c:when test="${ImpoCouponList.size() eq 0}">
									<div class="con5">
										<p>사용가능한 쿠폰이 없습니다.</p>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="cpDto" items="${ImpoCouponList}">
										<div class="con5">
											<div class="coupon_box">
												<div class="coupon_boxs">
													<div class="coupon_text">
														<p>${cpDto.couInfo}</p>
														<h3>${cpDto.coupon }</h3>
														<fmt:formatDate var="start" value="${cpDto.couDate1 }"/>
														<fmt:formatDate var="end" value="${cpDto.couDate2 }"/>
														<p>${start }~${end }</p>
													</div>
													<div class="coupon_btn">
														<a class="coup_btn" href="#">확인</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>


						</div>
					</div>
					<!-- partial -->
					<script
						src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js'></script>
					<script src="${urlInfo}script/script.js"></script>

					<div class="next_page">
						<c:if test="${ph.showPrev}">
							<a class="next_page_icon" href="#"><img
								src="${urlInfo}image/pre.png" alt="pre"></a>
						</c:if>
						<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
							<a class="page ${i==ph.sc.page? 'paging-active' : ''}" href="#">${i}</a>
						</c:forEach>
						<c:if test="${ph.showNext}">
							<a class="next_page_icon" href="#"><img
								src="${urlInfo}image/next.png" alt="next"></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer">
			<div class="center">
				<span>본 페이지는 상업적 목적이 아닌 개인 포트폴리오로 제작되었습니다.</span> <span
					class="copyright">Copyright ⓒ 2022.PLAY GROUND ALL rights
					reserved.</span>
			</div>
		</footer>
	</div>
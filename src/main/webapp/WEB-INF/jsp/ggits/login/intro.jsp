<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<section class="login_container">
	<div class="login_wrap">
		<div class="">
			<h1 class="login_logo_box intro_logo">
				<img src="${pageContext.request.contextPath}/statics/images/logo.png" alt="경기도 교통 빅데이터 시스템">
				<span class="login_logo_title intro_logo_title">경기도 교통 빅데이터 시스템</span>
			</h1>
			
			<div class="intro-list-wrap">
			    <c:forEach var="menuItem" items="${menuList}">
   				    <c:if test="${menuItem.cntnSystemCd eq 'GG001'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon01.png" alt="모니터링 아이콘">
							</div>
							<div class="intro-text">
								GIMS<br>
								(돌발정보 시스템)
							</div>
							<div class="intro-button">
								<a href="https://gims.gg.go.kr/">바로가기 <i></i></a>
							</div>
						</div>
					</c:if>
					<c:if test="${menuItem.cntnSystemCd eq 'GG003'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon02.png" alt="빅데이터 분석 TOOL">
							</div>
							<div class="intro-text">
								빅데이터<br>
								분석 플랫폼
							</div>
							<div class="intro-button">
								<a href="${pageContext.request.contextPath}/monitoring/dashboard.do">바로가기 <i></i></a>
							</div>
						</div>
					</c:if>
					<c:if test="${menuItem.cntnSystemCd eq 'GG002'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon03.png" alt="긴급차량 신호 및 이동 현황">
							</div>
							<div class="intro-text">
								광역 긴급차량 신호<br>
								및 이동 현황
							</div>
							<div class="intro-button">
								<a href="http://ggdata.gg.go.kr:3010">바로가기 <i></i></a>
							</div>
						</div>
					</c:if>
					<c:if test="${menuItem.cntnSystemCd eq 'GG004'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon04.png" alt="시각화 리포트">
							</div>
							<div class="intro-text">
								시각화<br>
								리포트
							</div>
							<div class="intro-button">
								<div class="intro-select">
									<button>
										<span class="label">선택하기</span> <i class="intro-select-icon"></i>
									</button>
								  <ul class="optionList">
								    <li class="optionItem">
								    	<a href="https://vtc.gg.go.kr/">Daisy 교통 센터용</a>
								    </li>
								    <li class="optionItem">
								    	<a href="https://vti.gg.go.kr/">Daisy 시군구</a>
								    </li>
								  </ul>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${menuItem.cntnSystemCd eq 'GG005'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon05.png" alt="시각화 분석">
							</div>
							<div class="intro-text">
								시각화<br>
								분석
							</div>
							 <div class="intro-button">
								<div class="intro-select">
									<button>
										<span class="label">선택하기</span> <i class="intro-select-icon"></i>
									</button>
								  <ul class="optionList">
								    <li class="optionItem">
								    	<a href="https://ggphy.gg.go.kr/">Python</a>
								    </li>
								    <li class="optionItem">
								    	<a href="https://znote.gg.go.kr/">Zupiter</a>
								    </li>
								   <li class="optionItem">
								    	<a href="https://zep.gg.go.kr/">Zeplin</a>
								    </li>
								    <li class="optionItem">
								    	<a href="https://rst.gg.go.kr/">R studio</a>
								    </li>
								  </ul>
								</div>
							</div> 
						</div>
					</c:if>
					<c:if test="${menuItem.cntnSystemCd eq 'GG006'}">
						<div class="intro-list">
							<div class="intro-icon">
								<img src="${pageContext.request.contextPath}/statics/images/intro_icon06.png" alt="공간정보(GIS) 제어 시스템">
							</div>
							<div class="intro-text">
								공간정보<br>
								제어 시스템
							</div>
							<div class="intro-button">
								<a href="http://gims.gg.go.kr/">바로가기 <i></i></a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</section>
<button type="button" id="bkdoorBtn" style="position:fixed;left:0;top:0;width:50px;height:50px;z-index:10"></button>
<form action="${pageContext.request.contextPath}/login.do" id="loginForm" name="loginForm" method="POST">
	<input type="hidden" class="data-validate" id="oprtrEmail" name="oprtrEmail" data-valid-required data-valid-email data-valid-name="이메일 아이디"/>
	<input type="hidden" class="data-validate" id="oprtrPswd" name="oprtrPswd" data-valid-required data-valid-password data-valid-name="비밀번호"/>
	<input type="hidden" id="oprtrNm" name="oprtrNm" />
	<input type="hidden" id="oprtrTel" name="oprtrTel" />
	<input type="hidden" id="loginType" name="loginType" />
</form>
<script type="text/javascript">
introcheck()
</script>
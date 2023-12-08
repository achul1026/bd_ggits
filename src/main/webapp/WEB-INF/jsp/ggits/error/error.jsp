<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="error_container">
	<div class="error_wrap">
		<div class="logo">
			<h1 class="login_logo_box">
				<img src="${pageContext.request.contextPath}/statics/images/logo.png" alt="경기도 교통 빅데이터 시스템"></a> 
				<span class="login_logo_title mj0">경기도 교통 빅데이터 시스템</span>
			</h1>
		</div>
		<div class="error_title">페이지를 찾을 수 없습니다.</div>
		<div class="error_txt">존재하지 않거나 만료된 페이지입니다.<br>페이지 확인 후 다시 시도해 주세요.</div>
		<div class="error_btn"><a href="${pageContext.request.contextPath}/monitoring.do" class="is-darkgreen-btn">홈으로 이동</a></div>
	</div>
</div>
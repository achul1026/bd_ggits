<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="error_container">
	<div class="error_wrap">
		<div class="error_logo">
			<img src="${pageContext.request.contextPath}/statics/images/error_img.png" alt="오류 이미지">
		</div>
		<div class="error_title">메뉴권한이 존재하지 않습니다.</div>
		<div class="error_txt">관리자에게 문의해주세요.</div>
		<div class="error_btn"><a href="${pageContext.request.contextPath}/monitoring.do" class="">메인페이지로 이동</a></div>
	</div>
</div>
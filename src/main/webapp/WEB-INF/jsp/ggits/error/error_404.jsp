<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경기도 교통 빅데이터 시스템</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css">
</head>
<body>
<div class="error_container">
	<div class="error_wrap">
		<div class="error_logo">
			<img src="${pageContext.request.contextPath}/statics/images/error_img.png" alt="오류 이미지">
		</div>
		<div class="error_title">요청하신 페이지를 찾을 수 없습니다.</div>
		<div class="error_txt">존재하지 않거나 만료된 페이지입니다.<br>페이지 확인 후 다시 시도해 주세요.</div>
		<div class="error_btn"><a href="${pageContext.request.contextPath}/monitoring.do" class="">메인페이지로 이동</a></div>
	</div>
</div>
</body>

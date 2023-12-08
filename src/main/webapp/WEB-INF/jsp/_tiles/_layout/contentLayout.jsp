<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경기도 교통 빅데이터 시스템</title>
    <tiles:insertAttribute name="head" />
</head>
<body class="body-bg">
<div id="skip" class="center">
    <a href="/">메인페이지 바로가기</a>
</div>
<tiles:insertAttribute name="gnb" />
<main>
<tiles:insertAttribute name="content" />
</main>
</body>
</html>
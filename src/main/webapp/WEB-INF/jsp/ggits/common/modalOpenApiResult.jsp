<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<div style="max-width:27rem">
	<div>
		<div class="modal_input_label">제목</div>
		<div class="pt8">
			<c:out value="${openApiLogDetail.apiNm}"/>
		</div>
	</div>
	<div class="mt16">
		<div class="modal_input_label">호출 URL</div>
		<div class="pt8">
			<c:out value="${openApiLogDetail.apiCallUrl}"/>
		</div>
	</div>
	<div class="mt16">
		<div class="modal_input_label">결과 데이터 수</div>
		<div class="pt8">
			<c:out value="${openApiLogDetail.responseCnt}"/>
		</div>
	</div>
	<div class="mt16">
		<div class="modal_input_label">호출 파라미터</div>
		<div class="pt8">
			<c:out value="${openApiLogDetail.parameterValue}"/>
		</div>
	</div>
</div>

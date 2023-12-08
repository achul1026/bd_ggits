<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pt16 pb16" style="width:30rem;">
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">로그 ID</label>
			<div class="modal_input">${logInfo.logId}</div>
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">발생 일자</label>
			<div class="modal_input">${logInfo.occurDtStr}</div> 
		</div>
	</div>
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">로그 유형</label>
			<div class="modal_input">${logInfo.cdNm}</div> 
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">요청자 ID</label>
			<c:choose>
				<c:when test="${logType eq 'user'}">
					<div class="modal_input">${logInfo.prgrmSesnId}</div> 
				</c:when>
				<c:otherwise>
					<div class="modal_input">${logInfo.sesnId}</div> 
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">요청자 이름</label>
			<c:choose>
				<c:when test="${logType eq 'server'}">
					<input type="text" class="modal_input" readonly="readonly"> 
				</c:when>
				<c:otherwise>
					<div class="modal_input">${logInfo.rqstrNm}</div> 
				</c:otherwise>
			</c:choose>
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">요청자 IP</label>
			<div class="modal_input">${logInfo.lgnIp}</div> 
		</div>
	</div>
</div>
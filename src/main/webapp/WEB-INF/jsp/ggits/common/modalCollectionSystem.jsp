<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pt16 pb16" style="width:30rem;">
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">수집 시스템명</label>
			<div class="modal_input">${lTcDataLog.jobNm}</div>
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">수집 시스템 ID</label>
			<div class="modal_input">${lTcDataLog.jobId}</div> 
		</div>
	</div>
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">실패사유</label>
			<div class="modal_input">${lTcDataLog.failRsn}</div> 
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<div>
	<div>
		<div class="modal_input_txt mj0">
			리포트를 업로드 해주세요.
			<br>
			일괄 업로드를 위해서 제공하고 있는 양식을 활용해 주시기 바랍니다.
		</div>
	</div>
	<form id="dataReportFrm">
	    <div class="csv_file_wrap flex-center">
	    	<div class="csv_file_name">리포트 제목</div> 
		    <input type="text" class="modal_input_upload modal_upload_name" placeholder="분석 리포트 제목을 입력해주세요." id="rptTitle" name="rptTitle" maxlength="50">
		</div>
		
	    <div class="csv_file_wrap flex-center">
	    	<div class="csv_file_name">iframe 코드 입력</div> 
	    	<textarea class="modal_input_upload" placeholder="iframe 코드만 입력해주세요." id="dtlUrl" name="dtlUrl"></textarea>
		</div>
	</form>
</div>
<script>
</script>


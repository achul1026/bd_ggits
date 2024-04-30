<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
	<div>
		<div class="modal_input_txt mj0">
			교통량 및 교통 영향 평가 조사 결과를 업로드 해주세요.
			<br>
			일괄 업로드를 위해서 제공하고 있는 양식을 활용해 주시기 바랍니다.
			<br>
			주의사항<br>
			1. 시트 수정, 삭제 및 추가 금지<br>
			2. 셀 임의 수정 금지<br>
			3. 정해진 용도명 사용<br>
		</div>
	</div>
	<form id="frmAttachedFiles" class="form-horizontal" enctype="multipart/form-data">
	    <div class="csv_file_wrap">
	    	<div class="csv_file_name">파일명 :</div> 
		    <input class="modal_input_upload" value="첨부파일" readonly="readonly" placeholder="첨부파일">
		    <label for="file">파일찾기</label> 
		    <input type="file" name="excelFile" id="file">
		</div>
	</form>	


<script>
	$("#file").on('change', function(){
		var filename = $(this)[0].files[0].name;
		$(".modal_input_upload").val(filename);
	});
</script>
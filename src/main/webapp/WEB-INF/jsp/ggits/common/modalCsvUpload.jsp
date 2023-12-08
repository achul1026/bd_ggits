<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="width:20rem;">
	<div>
		<div class="modal_input_txt">
			교통량 및 교통 영향 평가 조사 결과를 업로드 해주세요.
			<br>
			일괄 업로드를 위해서 제공하고 있는 양식을 활용해 주시기 바랍니다.
		</div>
	</div>
	<form id="frmAttachedFiles" class="form-horizontal" enctype="multipart/form-data">
		<div class="flex-center mt16">
			<div style="width:40%;">파일찾기</div>			
	        <input type="text" class="modal_input_srbox" id="fileNm" readonly="readonly">
	        <input type="file" name="excelFile" id="file">
	    </div>	
	</form>	
</div>

<script>
	$("#file").on('change', function(){
		var filename = $(this)[0].files[0].name;
		$("#fileNm").val(filename);
	});
</script>
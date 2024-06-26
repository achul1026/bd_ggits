<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       

	
	<form id="fileMngSaveFrm">
	    <div class="csv_file_wrap">
	    	<input type="hidden" id="fileDivCd" name="fileDivCd" value="<c:out value='${fileDivCd}'/>"/>
	    	<div class="csv_file_name">자료명</div> 
		    <input type="text" class="modal_input_upload modal_upload_name" placeholder="자료명을 입력해주세요." id="fileNm" name="fileNm" maxlength="50">
		</div>
		
	    <div class="csv_file_wrap">
	    	<div class="csv_file_name">파일명</div> 
		    <input class="modal_input_upload" id="uploadFileNm" value="파일찾기" readonly="readonly" placeholder="파일찾기">
		    <label for="uploadFile">파일찾기</label> 
		    <input type="file" id="uploadFile" name="uploadFile">
		    <div class="upload_type">업로드 가능한 파일 유형 : hwp,pdf,xlsx</div>
		</div>
	</form>
<script>
	$("#uploadFile").on('change',function(){
		var uploadFile = $(this)[0];
		var maxBytes = 20971520;
        var fileNmMaxLength = 50;
        
		var fileOrgNm = uploadFile.files[0].name;
		var ext = fileOrgNm.slice(fileOrgNm.lastIndexOf(".") + 1).toLowerCase();
		var fileBytes = uploadFile.files[0].size;
		
		if(fileOrgNm.length > fileNmMaxLength) {
			$(this).val("");
			$("#uploadFileNm").val("파일찾기");
			
			new ModalBuilder().init().alertBoby('파일명은 ' + fileNmMaxLength + '자 이상 넘을 수 없습니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
		} else if(ext != 'hwp' && ext != 'hwpx' && ext != 'xlsx' && ext != 'xls' && ext != 'pdf'){
			$(this).val("");
			$("#uploadFileNm").val("파일찾기");
			
			new ModalBuilder().init().alertBoby(ext+' 유형의 파일은 등록이 불가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();                    
        } else if(fileBytes > maxBytes){
			$(this).val("");
			$("#uploadFileNm").val("파일찾기");
			
			new ModalBuilder().init().alertBoby('20MB 이하의 파일만 등록이 가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();                     
        } else {
			$("#uploadFileNm").val(uploadFile.files[0].name);
		}
		
	});
</script>


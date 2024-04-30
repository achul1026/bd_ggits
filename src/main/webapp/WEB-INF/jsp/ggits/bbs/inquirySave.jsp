<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">문의하기</h3>
                <div class="side_txt">관리자에게 문의 하고자 하는<br>내용을 문의 하세요.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">문의하기</h2>
            	<form id="noticeSaveFrm">
					<div class="contents_wrap mt24">
						<div class="group">
							<div class="group_text">제목<span class="required-alert">*</span></div>
							<input type="text" id="inqryTitle" name="inqryTitle" class="input_same group_box" placeholder="제목을 입력해 주세요." maxlength="32">
						</div>
						<div class="group">
							<div class="group_text">내용<span class="required-alert">*</span></div>
							<textarea id="inqryCnts" name="inqryCnts" class="textarea_same" placeholder="내용을 입력해주세요." style="display: block"></textarea>			
						</div>
		            </div>
       				<div class="group flex-start">
						<div class="group_text">파일 업로드</div>
						<div class="flex-column">
							<div id="dragArea">
								<div class="drag-area">
								    <div class="mb16 center ftsize14">
								        파일 이름은 50자를 넘을 수 없습니다.<br>
								        파일 용량은 5MB 이하만 업로드가 가능합니다.<br>
								        파일은 ZIP, HWP, PDF, DOC, DOCX, XLS, XLSX, PPT, PPTX 유형의 파일만 업로드가 가능합니다.
								    </div>
								    <div>
								        <input type="file" id="uploadFiles" style="display: none;">
								        <button type="button" id="uploadBtn" class="is-darkgreen-btn mj0">파일 업로드</button>
								    </div>
								</div>
							</div>
							<div class="upload_wrap none">
								<div id="upload_list_box"></div>	
							</div>
						</div>
					</div>
            	</form>
           	<div class="group btn_search_wrap_left btn_search_wrap">
				<ul>
					<li>
						<button id="inquirySaveBtn" class="is-darkgreen-btn">문의 제출 하기</button>
						<button id="cancelBtn" class="is-dark-btn">취소</button>
					</li>
				</ul>
			</div>	
        </section>
    </div>
</main>
<script>
	$("#cancelBtn").on('click',function(){
		new ModalBuilder().init().alertBoby("취소하시겠습니까? 작성중인 내용은 삭제 됩니다.").footer(5,'취소하기',function(button, modal){
			location.href="${pageContext.request.contextPath}/bbs/notice/list.do";
			modal.close();
		 },'취소하기',function(button, modal){
			 modal.close();
		 }).open();
		
	});
	
	$(document).ready(function() {
		let isClick = true;
			
		$("#inquirySaveBtn").on('click',function(){
			if(isClick){
				//중복클릭 방지
				isClick = false;
				
				var inqryTitle = $("#inqryTitle").val();
				var inqryCnts  = $("#inqryCnts").val();
				var uploadFiles = $("#uploadFiles")[0];
				
				if(inqryTitle == null || inqryTitle == ''){
					isClick = true;
					new ModalBuilder().init().alertBoby("제목을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}

				if(inqryCnts == null || inqryCnts == ''){
					isClick = true;
					new ModalBuilder().init().alertBoby("내용을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
				
				var obj = new Object();
				obj.inqryTitle = inqryTitle;
				obj.inqryCnts = inqryCnts;
				
				const formData = new FormData();
				formData.acceptChartset="UTF-8";
				
				if(uploadFiles.files.length > 0){
					for(var i = 0; i < uploadFiles.files.length; i++){
						formData.append("uploadFiles",uploadFiles.files[i]);
					}
				}
				formData.append("mOpInqryBbs",new Blob([JSON.stringify(obj)], {type: "application/json"}));

				$.ajax({
					type : "post",
					data : formData,
					contentType : false,
					processData : false,
					enctype : "multipart/form-data;charset=UTF-8",
					dataType : "json",
					url : "${pageContext.request.contextPath}/bbs/inquiry/save.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("문의 등록에 성공 하였습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.href = ${pageContext.request.contextPath}"/bbs/inquiry/list.do";
								}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("문의 등록에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
							isClick = true;
						}
					}
				});
			}
		});
	})
		
	//file upload
    $(document).ready(function() {
        let $dragArea = $("#dragArea");
        let $fileInput = $("#uploadFiles");
        let $fileList = $("#upload_list_box");

        $dragArea.on("dragover", function(e) {
            e.preventDefault();
            $(this).css("background-color", "#737373");
        });

        $dragArea.on("dragleave", function(e) {
            e.preventDefault();
            $(this).css("background-color", "transparent");
        });

        $dragArea.on("drop", function(e) {
            const inputFile = $fileInput[0].files;
            let files = e.originalEvent.dataTransfer.files;
            e.preventDefault();
            $(this).css("background-color", "transparent");
            
            if(inputFile.length > 0){
                if(!fileUploadChange(inputFile)|| !fileUploadChange(files)){
                    $fileInput.prop('files',inputFile);
                    return;
                }
                fileChangeAddEvent(inputFile,files);
            } else{
                if(!fileUploadChange(files)){
                    $fileInput.prop('files',inputFile);
                    return;
                }
                fileChangeDefaultEvent(files);
            }
            $('.upload_wrap').removeClass('none')
        });

        $("#uploadBtn").click(function() {
            const inputFile = $fileInput[0].files;
            $fileInput.click();
            $fileInput.off().on("change", function() {
                if(!fileUploadChange(inputFile) || !fileUploadChange(this.files)){
                    $fileInput.prop('files',inputFile);
                    return;
                }
                if(inputFile.length > 0){
                    fileChangeAddEvent(inputFile,this.files);
                } else{
                    fileChangeDefaultEvent(this.files);
                }
                $('.upload_wrap').removeClass('none')
            });
        });

        function fileChangeAddEvent(inputFileArr , addFileArr){
            const dataTransfer = new DataTransfer();
            Array.from(inputFileArr).forEach(file => {
                addUniqueFile(file, dataTransfer);
            });
            Array.from(addFileArr).forEach(file => {
                addUniqueFile(file, dataTransfer);
            });
            handleFiles(dataTransfer.files);
            $fileInput.prop('files',dataTransfer.files);
        }

        function fileChangeDefaultEvent(files){
            handleFiles(files);
            $fileInput.prop('files',files);
        }

        function addUniqueFile(file, dataTransfer) {
            const uniqueFileSet = new Set();
            const uniqueFiles = [];
            const uniqueKey = file.name + "_" + file.size;

            for (let i = dataTransfer.items.length - 1; i >= 0; i--) {
                const file = dataTransfer.items[i].getAsFile();
                if (uniqueFileSet.has(file.name + "_" + file.size)) {
                    dataTransfer.items.remove(i);
                } else {
                    uniqueFileSet.add(file.name + "_" + file.size);
                    uniqueFiles.push(file);
                }
            }
            if (!uniqueFileSet.has(uniqueKey)) {
                uniqueFileSet.add(uniqueKey);
                dataTransfer.items.add(file);
            }
        }

        function handleFiles(files) {
            if (files.length === 0) return;
            $("#upload_list_box").empty();
//             for (let i = 0; i < files.length; i++) {
                let fileNm = files[0].name;
                var uploadList = $(`
	                <div class="upload_list">
                		<div class="list_item input_same group_box flex-center">
                			<div class="file_list">
		                		`+fileNm+`
                			</div>
	                		<button type="button" data-value='`+fileNm+`' class="fileDelBtn">
	                			<img src="${pageContext.request.contextPath}/statics/images/upload_close.png" alt="업로드파일 삭제">
	                		</button>
                		</div>
	                </div>`);
                $fileList.append(uploadList);
//             }
            $(".fileDelBtn").on('click',function(){
                var $this = $(this);
                var fileNm = $this.data('value');
                const dataTransfer = new DataTransfer();
                let trans = $('#uploadFiles')[0].files;
                let fileArray = Array.from(trans);
	
                $this.closest('.upload_list').remove();
                fileArray.filter(file => file.name != fileNm).forEach(file => {
                    dataTransfer.items.add(file);
                });
                $fileInput.prop('files',dataTransfer.files);
                
                let uploadLength = $('#upload_list_box').children().length;
        		if(uploadLength <= 0){
        			$('.upload_wrap').addClass('none')
        		}
            });
        }
    });

    function fileUploadChange(files){
        var maxBytes = 5242880;
        var fileNmMaxLength = 50;

        for(var i = 0; i < files.length; i++){
            var fileNm = files[i].name;
            var fileBytes = 0;

            if(fileNm != ''){
                fileBytes = files[i].size;
            }
            if(fileNm != ''){
                var ext = fileNm.slice(fileNm.lastIndexOf(".")+1).toLowerCase();
                if(ext != 'zip' && ext != 'hwp' && ext != 'pdf' && ext != 'docx' && ext != 'doc' && ext != 'xlsx' && ext != 'xls' && ext != 'ppt' && ext != 'pptx'){
					new ModalBuilder().init().alertBoby(ext+' 유형의 파일은 등록이 불가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();                    
                    return false;
                }else if(fileBytes > maxBytes){
					new ModalBuilder().init().alertBoby('5MB 이하의 파일만 등록이 가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();                     
                    return false;
                }else if(fileNm.length > fileNmMaxLength){
					new ModalBuilder().init().alertBoby('파일 제목은 50자 이상 넘을 수 없습니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();                   
                    return false;
                }
            }
        }
        return true;
    }
    
    //scrollbar 유/무
    function hasHorizontalScrollbar(element) {
		return element.get(0).scrollWidth > element.innerWidth();
	}
    
    function scrollbarCheck(){
		var element = $('#keywordWrap');
		if (hasHorizontalScrollbar(element)) {
			element.css('margin-bottom', '0.7rem')
		} else {
			element.css('margin-bottom', '0rem')
		}
    }

</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<main>
    <div class="main_container">
		<aside class="snb_container">
			<div class="snb_wrap">
				<h3 class="side_title">공지사항 상세</h3>
				<div class="side_txt">공지사항을 상세 내용을<br>확인 할 수 있습니다.</div>
			</div>
		</aside>
        <section class="main_section">
            <h2 class="blind">공지사항 상세</h2>
            	<form id="noticeUpdateFrm">
					<div class="contents_wrap mt24">
						<div class="group">
							<div class="group_text">제목</div>
							<input type="text" id="ntcTitle" name="ntcTitle" class="input_same group_box" style="width: 40.5rem" placeholder="제목을 입력해 주세요." maxlength="32" value="<c:out value='${mOpNtcBbsDetail.ntcTitle}'/>" ${mOpOperatorInfo.oprtrGrd eq 'SUPER' ? '':'readonly'}>
						</div>
						<div class="group">
							<div class="group_text">내용</div>
							<textarea id="ntcCnts" name="ntcCnts" class="textarea_same" placeholder="내용을 입력해주세요." style="display: block" ${mOpOperatorInfo.oprtrGrd eq 'SUPER' ? '':'readonly'}><c:out value='${mOpNtcBbsDetail.ntcCnts}'/></textarea>			
						</div>
		            </div>
		            <c:choose>
			            <c:when test="${mOpNtcBbsDetail.atchFileYn eq 'Y'}">
		       				<div class="group flex-start">
								<div class="group_text">첨부파일</div>
								<div class="flex-column">
									<div id="download_list_box">
				                		<div class="list_item input_same group_box flex-center">
												<div class="file_list">
							                		<c:out value='${mOpNtcBbsDetail.atchFileOrgNm}'/>
					                			</div>
						                		<div class="flex-center gap8">
						                			<a href="${pageContext.request.contextPath}/bbs/notice/file/download.do?ntcId=<c:out value='${mOpNtcBbsDetail.ntcId}'/>" class="downloadBtn">
							                			<img src="${pageContext.request.contextPath}/statics/images/download.png" alt="업로드파일 다운로드">
						                			</a>
												<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
						                			<span class="pointer" onclick="deleteFile('<c:out value='${mOpNtcBbsDetail.ntcId}'/>')">
							                			<img src="${pageContext.request.contextPath}/statics/images/upload_close.png" alt="업로드파일 삭제">
						                			</span>
					                			</c:if>
					                		</div>
				                		</div>
									</div>	
								</div>
							</div>
			            </c:when>
			            <c:otherwise>
  						<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
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
						</c:if>
			            </c:otherwise>
		            </c:choose>
            	</form>
           	<div class="group btn_search_wrap_left btn_search_wrap">
				<ul>
					<li>
						<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER' or mOpNtcBbsDetail.oprtrId eq mOpOperatorInfo.oprtrId}">
							<button id="noticeUpdateBtn" class="is-darkgreen-btn">수정</button>
							<button id="noticeDelteBtn" class="is-darkgreen-btn">삭제</button>
						</c:if>
						<button id="moveListBtn" class="is-dark-btn">목록</button>
					</li>
				</ul>
			</div>	
        </section>
    </div>
</main>
<script>
    
	$("#moveListBtn").on('click',function(){
		location.href="${pageContext.request.contextPath}/bbs/notice/list.do";
	});
	
	<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER' or mOpNtcBbsDetail.oprtrId eq mOpOperatorInfo.oprtrId}">
	$(document).ready(function() {
		//<![CDATA[
		var ntcId = '<c:out value="${mOpNtcBbsDetail.ntcId}"/>';
		//]]>
		 
		let isClick = true;
		
		$("#noticeUpdateBtn").on('click',function(){
			if(isClick){
				//중복클릭 방지
				isClick = false;
				
				var ntcTitle = $("#ntcTitle").val();
				var ntcCnts  = $("#ntcCnts").val();
				var uploadFiles = $("#uploadFiles")[0];
				
				if(ntcTitle == null || ntcTitle == ''){
					isClick = true;
					new ModalBuilder().init().alertBoby("제목을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}

				if(ntcCnts == null || ntcCnts == ''){
					isClick = true;
					new ModalBuilder().init().alertBoby("내용을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
				
				var obj = new Object();
				obj.ntcId = ntcId;
				obj.ntcTitle = ntcTitle;
				obj.ntcCnts = ntcCnts;
				
				const formData = new FormData();
				formData.acceptChartset="UTF-8";
				
				<c:if test="${mOpNtcBbsDetail.atchFileYn ne 'Y'}">
				if(uploadFiles.files != null && uploadFiles.files.length > 0){
					for(var i = 0; i < uploadFiles.files.length; i++){
						formData.append("uploadFiles",uploadFiles.files[i]);
					}
				}
				</c:if>
				formData.append("mOpNtcBbs",new Blob([JSON.stringify(obj)], {type: "application/json"}));

				$.ajax({
					type : "post",
					data : formData,
					contentType : false,
					processData : false,
					enctype : "multipart/form-data;charset=UTF-8",
					dataType : "json",
					url : "${pageContext.request.contextPath}/bbs/notice/update.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("공지사항을 수정 했습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.reload();
								}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("공지사항 수정에 실패했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
							isClick = true;
						}
					}
				});
			}
		});
		
		$("#noticeDelteBtn").on('click',function(){
			new ModalBuilder().init().alertBoby("공지사항을 삭제 하시겠습니까? 삭제시 복구 할 수 없습니다.").footer(5,'삭제하기',function(button, modal){
				$.ajax({
					type : "post",
					data : {
						"ntcId" : ntcId
					},
					dataType : "json",
					url : "${pageContext.request.contextPath}/bbs/notice/delete.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("공지사항을 삭제 했습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.href="${pageContext.request.contextPath}/bbs/notice/list.do";
							}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("공지사항 삭제를 실패 했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				})
			 },'취소하기',function(button, modal) {
				 modal.close();
			 }).open();
		})

		let $fileInput = $("#uploadFiles");
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
	});
	
	
	function deleteFile(ntcId){
		if(ntcId == null || ntcId == ''){
			return false;
		}
		
		new ModalBuilder().init().alertBoby("파일을 삭제 하시겠습니까? 삭제시 복구 할 수 없습니다.").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "post",
				data : {
					"ntcId" : ntcId
				},
				dataType : "json",
				url : "${pageContext.request.contextPath}/bbs/notice/file/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("파일이 삭제 되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
							$(".modal_container").remove();
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("파일 삭제에 실패 했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
		 },'취소하기',function(button, modal){
			 modal.close();
		 }).open();
	};
	</c:if>
</script>

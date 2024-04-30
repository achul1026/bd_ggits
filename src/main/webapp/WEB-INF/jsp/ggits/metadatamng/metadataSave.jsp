<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
	<aside class="snb_container">
		<div class="snb_wrap">
			<h3 class="side_title">메타데이터 등록</h3>
			<div class="side_txt">메타데이터 등록 및 관리를<br>할 수 있습니다.</div>
		</div>
	</aside>
	<section class="main_section">
		<h2 class="blind">메타데이터 상세</h2>
		<form id="metadataRegForm">
			<div class="contents_wrap mt24">
				<div class="group">
					<div class="group_text">서비스 이름(KOR)<span class="required-alert">*</span></div>
					<input type="text" id="tblKoreanNm" class="input_same group_box input-width-long data-validate" data-valid-name="서비스 이름(KOR)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupKorEvent(this)">
				</div>
				<div class="group">
					<div class="group_text">서비스 이름(ENG)<span class="required-alert">*</span></div>
					<input type="text" id="tblEngNm" class="input_same group_box input-width-long data-validate" data-valid-name="서비스 이름(ENG)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupColEvent(this)">
				</div>
				<div class="group">
					<div class="group_text">분류체계</div>
                    <div class="metadata_sort_container">
						<input type="text" class="input_same group_box input-width-long" id="clschmId"  placeholder="분류체계를 입력해 주세요.">
						<div class="metadata_sort_box none">
							<div class="metadata_sort_wrap">
								<div class="metadata_sort_title">등록된 분류체계</div>
								<ul class="metadata_sort_list_box">
							     <c:if test="${empty clschmIdList}">
									<li class="metadata_sort_list">등록된 분류체계가 없습니다.</li>
								</c:if>
									<c:forEach var="clschmNm" items="${clschmIdList}">
										<li class="metadata_sort_list"><c:out value='${clschmNm}'/></li>
		                            </c:forEach>
								</ul>
							</div>
						</div>
                    </div>
				</div>
<!-- 				<div class="group"> -->
<!-- 					<div class="group_text">유관기관<span class="required-alert">*</span></div> -->
<!-- 					<select class="selectBox data-validate" id="rltinstId" data-valid-name="유관기관" data-valid-required> -->
<!-- 						<option value="">선택하기</option> -->
<%-- 						<c:forEach var="metaInfSysInfoList" items="${metaInfSysInfoList}"> --%>
<%-- 							<option value="<c:out value='${metaInfSysInfoList.rltinstId}'/>"><c:out value='${metaInfSysInfoList.rltinstNm}'/></option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="group"> -->
<!-- 					<div class="group_text">원본 데이터 이름<span class="required-alert">*</span></div> -->
<!-- 					<input type="text" id="orgDataNm" class="input_same group_box data-validate" data-valid-name="원본 데이터 이름" data-valid-required placeholder="원본 데이터 이름을 입력해 주세요."> -->
<!-- 				</div> -->
				<div class="group_wrap">
					<div class="flex-center relative">
						<div class="group_text">데이터 유형<span class="required-alert">*</span></div>
						<div class="group_contents btn_search_wrap btn_search_wrap_left">
							<ul>
								<li>
									<select id="dataType" class="selectBox">
										<option value="">유형선택</option>
										<c:forEach var="dataType" items="${dataTypeList}">
											<option value="${dataType}"><c:out value="${dataType}"/></option>
										</c:forEach>
									</select>
									<input type="text" id="colEngNm" class="input_same group_box input-width-long" placeholder="데이터 컬럼 명(ENG)을 입력해 주세요." onkeyup="keyupColEvent(this)">
									<input type="text" id="colKoreanNm" class="input_same group_box input-width-long" placeholder="데이터 컬럼 명(KOR)을 입력해 주세요." onkeyup="keyupKorEvent(this)">
								</li>
								<li>
									<button type="button" id="addDataTypeBtn" class="is-darkgreen-btn group_search_btn">추가</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="group_ex mb24">
<!-- 						데이터 유형은 최대 3개 까지 입력 가능 합니다. -->
					</div>
					<div id="dataTypeWrap" class="flex-center data_type_append mb24">
						<div class="group_text">데이터 유형 상세</div>
						<div class="group_contents">
							<table class="content-table-layout" style="width: 60rem">
								<colgroup>
									<col style="width:20%">
									<col style="width:34%">
									<col style="width:34%">
									<col style="width:12%">
								</colgroup>
								<thead>
									<tr>
										<th>데이터 유형</th>
										<th>컬럼명 (ENG)</th>
										<th>컬럼명 (KOR)</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody id="metaColInfoTr">
									<tr id="isNullTr">
										<td colspan="4">데이터 유형을 추가해 주세요</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="group_text"></div>
					</div>
				</div>
<!-- 				<div class="group_wrap"> -->
<!-- 					<div class="flex-center relative"> -->
<!-- 						<div class="group_text">데이터 키워드<span class="required-alert">*</span></div> -->
<!-- 						<div class="group_contents btn_search_wrap btn_search_wrap_left"> -->
<!-- 							<ul> -->
<!-- 								<li> -->
<!-- 									<input type="text" class="input_same group_box" id="dataKeyword" placeholder="데이터 키워드를 입력해 주세요." maxlength="10"> -->
<!-- 								</li> -->
<!-- 								<li> -->
<!-- 									<button type="button" id="addKeywordBtn" class="is-darkgreen-btn group_search_btn">추가</button> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="group_ex mb8"> -->
<!-- 						키워드는 최대 10자 까지 입력 가능 합니다.<br>예시) 정류소 명칭, 버스 노선 명칭 -->
<!-- 					</div>		 -->
<!-- 					<div id="keywordWrap" class="flex-center"></div> -->
<!-- 				</div> -->
<!-- 				<div class="group flex-center-remove"> -->
<!-- 					<div class="group_text">데이터 수집 유형<span class="required-alert">*</span></div> -->
<!-- 					<div class="flex-center gap8"> -->
<%-- 						<c:forEach var="collTyCd" items="${collTyCdList}"> --%>
<%-- 							<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="<c:out value='${collTyCd.cdId}'/>"><c:out value='${collTyCd.cdNm}'/></label> --%>
<%-- 						</c:forEach> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="group">
					<div class="group_text">데이터 설명</div>
					<textarea id="tblDescr" name="tblDescr" class="textarea_same" placeholder="설명을 입력해 주세요." style="display: block"></textarea>			
				</div>
				<div class="group flex-start">
					<div class="group_text">파일 업로드</div>
					<div class="flex-column">
						<div id="dragArea">
							<div class="drag-area">
							    <div class="mb16 center ftsize14">
							        파일 이름은 50자를 넘을 수 없습니다.<br>
									파일은 JSON, CSV, HWP, HWPX, PDF, XML, TXT, ZIP, DOCX, DOC 유형의 파일만 업로드가 가능합니다.
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
				<div class="group group_search">
					<input type="button" id="metadataSaveBtn" class="is-darkgreen-btn" value="저장"> 
					<a href="${pageContext.request.contextPath}/metadata/manage/list.do" class="is-dark-btn">취소</a>
				</div>
			</div>
		</form>
	</section>
</div>

<script>
	function deleteKeyword(idx){
		$("#keywordContent"+idx).remove();
		scrollbarCheck()
	}
	
	function deleteDataType(idx){
		$("#dataTypeContent"+idx).remove();
		var dataTypeValLength = $(".dataTypeVal").length;
		if(dataTypeValLength == 0){
			$("#isNullTr").removeClass('none');
		}
	}
	
	// 분류 체계 키업
	$("#clschmId").on('mouseenter', function(){
		$(this).siblings('.metadata_s	ort_box').removeClass('none');
	})
	$("#clschmId").on('mouseleave', function(){
		$(this).siblings('.metadata_sort_box').addClass('none');
	})
	//
	
	$("#addDataTypeBtn").on('click',function(){
		var html = "";
		var dataType = $("#dataType").val().trim();
		var colEngNm = $("#colEngNm").val().trim();
		var colKoreanNm = $("#colKoreanNm").val().trim();
		
		if(dataType == null || dataType == ''){
			new ModalBuilder().init().alertBoby("데이터 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		if(colEngNm == null || colEngNm == ''){
			new ModalBuilder().init().alertBoby("컬럼명(ENG)을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		if(colKoreanNm == null || colKoreanNm == ''){
			new ModalBuilder().init().alertBoby("컬럼명(KOR)을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		var dataTypeIdx = $(".dataTypeVal").length;
		
		html+='<tr id="dataTypeContent'+dataTypeIdx+'">';
		html+='		<td>'+dataType+'<input type="hidden" class="dataTypeVal" value="'+dataType+'" readonly/> </td>';
		html+='		<td>'+colEngNm+'<input type="hidden" class="colEngNmVal" value="'+colEngNm+'" readonly/> </td>';
		html+='		<td>'+colKoreanNm+' <input type="hidden" class="colKoreanNmVal" value="'+colKoreanNm+'" readonly/> </td>';
		html+='		<td><span onclick="deleteDataType(\''+dataTypeIdx+'\')"><img src="/statics/images/delete.png" alt="삭제" class="datamng_img"></span></td>';
		html+='</tr>';
		
// 		html += '<div id="dataTypeContent'+dataTypeIdx+'" class="dataTypeContent">';
// 		html += '<label class="is-darkgreen-btn mr8"> 컬럼명(ENG) :'+colEngNm+' <br> 컬럼명(KOR) '+colKoreanNm+'<br> 데이터유형 : '+dataType+'</label>';
// 		html += 	'<input type="hidden" class="dataTypeVal" value="'+dataType+'" readonly/>';
// 		html += 	'<input type="hidden" class="colEngNmVal" value="'+colEngNm+'" readonly/>';
// 		html += 	'<input type="hidden" class="colKoreanNmVal" value="'+colKoreanNm+'" readonly/>';
// 		html += 	'<button type="button" onclick="deleteDataType(\''+dataTypeIdx+'\')" class="mr8">삭제</button>';
// 		html += '</div>';
// 		if(3 <= dataTypeIdx){
// 			new ModalBuilder().init().alertBoby("데이터 유형은 최대 3개까지 입력 가능합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
// 			modalAlertWrap();			
// 			return false;
// 		}
		
		$("#metaColInfoTr").append(html);
		$("#dataType").val("");
		$("#colEngNm").val("");
		$("#colKoreanNm").val("");
		$("#isNullTr").addClass('none');
	});
	
// 	$("#addKeywordBtn").on('click',function(){
// 		var html = "";
// 		var dataKeyword = $("#dataKeyword").val().trim();
		
// 		if(dataKeyword == null || dataKeyword == ''){
// 			new ModalBuilder().init().alertBoby("데이터 키워드를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
// 			modalAlertWrap();			
// 			return false;
// 		}
// 		var keywordIdx = $(".keyword").length;
		
// 		html += '<div id="keywordContent'+keywordIdx+'" class="keywordContent">';
// 		html += '<label class="is-darkgreen-btn mr8">'+dataKeyword+'</label>';
// 		html += 	'<input type="hidden" class="keyword" value="'+dataKeyword+'" readonly/>';
// 		html += 	'<button type="button" onclick="deleteKeyword(\''+keywordIdx+'\')" class="mr8">삭제</button>';
// 		html += '</div>';
		
// 		$("#keywordWrap").append(html);
// 		$("#dataKeyword").val("");
// 		scrollbarCheck();
// 	});
	
	$(document).ready(function() {
		let isClick = true;
		$("#metadataSaveBtn").on('click',function(){
			if(isClick){
				//중복클릭 방지
				isClick = false;

				var tblKoreanNm = $("#tblKoreanNm").val();
				var tblEngNm  = $("#tblEngNm").val();
				var clschmId = $("#clschmId").val();
// 				var rltinstId = $("#rltinstId").val();
// 				var orgDataNm = $("#orgDataNm").val();
				var uploadFiles = $("#uploadFiles")[0];
				// 객체 배열 초기화
				var metaColInfoList = new Array();
				
				var dataType = "";
				var colEngNm = "";
				var colKoreanNm = "";
// 				var dataKeyword = "";
// 				var collDataType = "";
				
				var tblDescr = $("#tblDescr").val();
				
				var dataTypeVal = $(".dataTypeVal");
				var colEngNmVal = $(".colEngNmVal");
				var colKoreanNmVal = $(".colKoreanNmVal");
// 				var collectionType = $(".collectionType");
				var keyword = $(".keyword");
				
				if(!$("#metadataRegForm").soValid()){
					isClick = true;
					return false;
				}
                                         
				if(dataTypeVal.length == 0){
					isClick = true;
					new ModalBuilder().init().alertBoby("데이터 유형을 추가 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				} else {
					// 배열 길이 확인
// 					var maxLength = Math.max(dataTypeVal.length, colEngNmVal.length,colKoreanNmVal.length);

// 					for (var i = 0; i < maxLength; i++) {
// 					    var metaColInfo = {
// 					        dataType: dataTypeVal.eq(i).val(),
// 					        colEngNm: colEngNmVal.eq(i).val(),
// 					        colKoreanNm : colKoreanNmVal.eq(i).val()
// 					    };
// 					    metaColInfoList.push(metaColInfo);
// 					}
					
					for(var i = 0; i < dataTypeVal.length; i++){
						if(dataType == ''){
							dataType += dataTypeVal.eq(i).val();
						} else {
							dataType += ","+dataTypeVal.eq(i).val();
						}
					}			
					for(var i = 0; i < colEngNmVal.length; i++){
						if(colEngNm == ''){
							colEngNm += colEngNmVal.eq(i).val();
						} else {
							colEngNm += ","+colEngNmVal.eq(i).val();
						}
					}
					for(var i = 0; i < colKoreanNmVal.length; i++){
						if(colKoreanNm == ''){
							colKoreanNm += colKoreanNmVal.eq(i).val();
						} else {
							colKoreanNm += ","+colKoreanNmVal.eq(i).val();
						}
					}
				}
				
// 				if(keyword.length == 0){
// 					isClick = true;
// 					new ModalBuilder().init().alertBoby("데이터 키워드를 추가 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();

// 					modalAlertWrap();			
// 					return false;
// 				} else {
// 					for(var i = 0; i < keyword.length; i++){
// 						if(dataKeyword == ''){
// 							dataKeyword += keyword.eq(i).val();
// 						} else {
// 							dataKeyword += ","+keyword.eq(i).val();
// 						}
// 					}			
// 				}
				
// 				if(!collectionType.is(":checked")){
// 					isClick = true;
// 					new ModalBuilder().init().alertBoby("수집 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
// 					modalAlertWrap();			
// 					return false;
// 				} else {
// 					for(var i = 0; i < collectionType.length; i++){
// 						if(collectionType.eq(i).is(':checked')){
// 							if(collDataType == ''){
// 								collDataType += collectionType.eq(i).val();
// 							} else {
// 								collDataType += ","+collectionType.eq(i).val();
// 							}
// 						}
// 					}
// 				}
				
				var obj = new Object();
				obj.tblKoreanNm = tblKoreanNm;
				obj.tblEngNm = tblEngNm;
				obj.clschmId = clschmId;
// 				obj.metaColInfoList = metaColInfoList;
// 				obj.rltinstId = rltinstId;
				obj.strDataTypeArr = dataType;
				obj.strColEngNmArr = colEngNm;
				obj.strColKoreanNmArr = colKoreanNm;
// 				obj.orgDataNm = orgDataNm;
// 				obj.dataKeyword = dataKeyword;
// 				obj.collDataType = collDataType;
				obj.tblDescr = tblDescr;
				
				const formData = new FormData();
				formData.acceptChartset="UTF-8";
				
				if(uploadFiles.files.length > 0){
					for(var i = 0; i < uploadFiles.files.length; i++){
						formData.append("uploadFiles",uploadFiles.files[i]);
					}
				}
				
				formData.append("metaTabInfo",new Blob([JSON.stringify(obj)], {type: "application/json"}));
				
				console.log(obj);
				
				$.ajax({
					type : "post",
					data : formData,
					contentType : false,
					processData : false,
					enctype : "multipart/form-data;charset=UTF-8",
					dataType : "json",
					url : "${pageContext.request.contextPath}/metadata/manage/save.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("메타데이터 등록을 성공 하였습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.href = ${pageContext.request.contextPath}"/metadata/manage/list.do";
								}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("메타데이터 등록에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
							isClick = true;
						}
					}
				});
			}
		});
	});
	
	
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
            for (let i = 0; i < files.length; i++) {
                let fileNm = files[i].name;
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
            }
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
                if(ext != 'json' && ext != 'hwp' && ext != 'csv' && ext != 'pdf' && ext != 'txt' && ext != 'xml' && ext != 'zip'){
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
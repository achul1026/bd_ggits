<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
	<aside class="snb_container">
		<div class="snb_wrap">
			<h3 class="side_title">메타데이터 상세</h3>
			<div class="side_txt">메타데이터 등록 및 관리를<br>할 수 있습니다.</div>
		</div>
	</aside>
	<section class="main_section">
		<h2 class="blind">메타데이터 상세</h2>
		<form id="">
			<div class="contents_wrap mt24">
				<div class="group">
					<div class="group_text">데이터셋 아이디</div>
					<input type="text" class="input_same group_box input-width-long" value="<c:out value='${metadataInfo.dsetId}'/>">
				</div>
				<c:if test="${metaDataList.tblType eq 'GPDB' or metaDataList.tblType eq 'NDAP'}">
					<div class="group">
						<div class="group_text">유형</div>
						<c:choose>
	                    	<c:when test="${not empty metaFileInfoList}">
	                      		<input type="text" class="input_same group_box" value="DATABASE, FILE" readonly>                                   	                                    	
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input type="text" class="input_same group_box" value="DATABASE" readonly>      
	                    	</c:otherwise>
	                    </c:choose>
					</div>					
				</c:if>
				<div class="group">
					<div class="group_text">서비스 이름(KOR)</div>
					<input type="text" id="tblKoreanNm" class="input_same input-width-long group_box data-validate" data-valid-name="서비스 이름(KOR)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupKorEvent(this)" value="<c:out value='${metadataInfo.tblKoreanNm}'/>">
				</div>
				<div class="group">
					<div class="group_text">서비스 이름(ENG)</div>
					<input type="text" id="tblEngNm" class="input_same input-width-long group_box data-validate" data-valid-name="서비스 이름(ENG)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupColEvent(this)" value="<c:out value='${metadataInfo.tblEngNm}'/>">
				</div>
				<div class="group">
					<div class="group_text">분류체계</div>
                    <div class="metadata_sort_container">
						<input type="text" class="input_same group_box input-width-long" id="clschmNm" placeholder="분류체계를 입력해 주세요." value="<c:out value='${metadataInfo.clschmNm}'/>">
						<input type="hidden" class="input_same group_box" id="clschmId" placeholder="분류체계를 입력해 주세요." value="<c:out value='${metadataInfo.clschmId}'/>">
						<div class="metadata_sort_box none">
							<div class="metadata_sort_wrap">
								<div class="metadata_sort_title">등록된 분류체계</div>
								<ul class="metadata_sort_list_box">
									<c:forEach var="clschmNm" items="${clschmIdList}">
										<li class="metadata_sort_list"><c:out value='${clschmNm}'/></li>
		                            </c:forEach>
								</ul>
							</div>
						</div>
                    </div>
				</div>
			</div>
<!-- 				<div class="group"> -->
<!-- 					<div class="group_text">유관기관</div> -->
<!-- 					<select class="selectBox" id="rltinstId" data-valid-name="유관기관" data-valid-required> -->
<!-- 						<option value="">선택하기</option> -->
<%-- 						<c:forEach var="metaInfSysInfoList" items="${metaInfSysInfoList}"> --%>
<%-- 							<option value="<c:out value='${metaInfSysInfoList.rltinstId}'/>" ${metaInfSysInfoList.rltinstId eq metadataInfo.rltinstId ? 'selected':''}><c:out value='${metaInfSysInfoList.rltinstNm}'/></option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="group"> -->
<!-- 					<div class="group_text">원본 데이터 이름</div> -->
<%-- 					<input type="text" class="input_same group_box" id="orgDataNm" value="<c:out value='${metadataInfo.orgDataNm}'/>"> --%>
<!-- 				</div> -->
				<div>
				<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER' or isUserChk}">
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
										<input type="text" id="colEngNm" class="input_same input-width-long group_box" placeholder="데이터 컬럼 명(ENG)을 입력해 주세요." onkeyup="keyupColEvent(this)">
										<input type="text" id="colKoreanNm" class="input_same input-width-long group_box" placeholder="데이터 컬럼 명(KOR)을 입력해 주세요." onkeyup="keyupKorEvent(this)">
									</li>
									<li>
										<button type="button" id="addDataTypeBtn" class="is-darkgreen-btn group_search_btn">추가</button>
									</li>
								</ul>
							</div>
						</div>
	<!-- 						<div class="group_ex"> -->
	<!-- 								데이터 유형은 최대 3개 까지 입력 가능 합니다. -->
	<!-- 						</div>	 -->
					</div>
				</c:if>
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
								<c:forEach var="metaColInfoList" items="${metaColInfoList}" varStatus="status">
									<tr id="dataTypeContent<c:out value='${status.index}'/>">
										<td><c:out value='${metaColInfoList.dataType}'/><input type="hidden" class="dataTypeVal" value="<c:out value='${metaColInfoList.dataType}'/>" readonly/> </td>
										<td><c:out value='${metaColInfoList.colEngNm}'/><input type="hidden" class="colEngNmVal" value="<c:out value='${metaColInfoList.colEngNm}'/>" readonly/> </td>
										<td><c:out value='${metaColInfoList.colKoreanNm}'/> <input type="hidden" class="colKoreanNmVal" value="<c:out value='${metaColInfoList.colKoreanNm}'/>" readonly/> </td>
										<td><span onclick="deleteDataType('<c:out value='${status.index}'/>','<c:out value='${metaColInfoList.colSqno}'/>')"><img src="/statics/images/delete.png" alt="삭제" class="datamng_img"></span></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="group_text"></div>
					</div>
<!-- 					<div id="dataTypeWrap" class="flex-center gap8 meta_append"> -->
<%-- 						<c:forEach var="metaColInfoList" items="${metaColInfoList}" varStatus="status"> --%>
<%-- 							<div id="dataTypeContent<c:out value='${status.index}'/>"> --%>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${metadataInfo.tblType eq 'GPDB' or metadataInfo.tblType eq 'NDAP'}"> --%>
<%-- 									<label class="is-darkgreen-btn">컬럼 명 : <c:out value='${metaColInfoList.colEngNm}'/> <br> 데이터 타입: <c:out value='${metaColInfoList.dataType}'/></label> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									<label class="is-darkgreen-btn"><c:out value='${metaColInfoList.dataType}'/></label> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<%-- 								<input type="hidden" class="dataTypeVal" value="<c:out value='${metaColInfoList.dataType}'/>" readonly/> --%>
<%-- 								<c:if test="${isUserChk}"> --%>
<%-- 									<button type="button" onclick="deleteDataType('<c:out value='${status.index}'/>','<c:out value='${metaColInfoList.colSqno}'/>')" class="ftsize14">삭제</button> --%>
<%-- 								</c:if> --%>
<!-- 							</div>		 -->
<%-- 						</c:forEach> --%>
<!-- 					</div> -->
				</div>
				<div class="mt24">
<!-- 					<div class="group_wrap"> -->
<!-- 						<div class="flex-center relative"> -->
<!-- 							<div class="group_text">데이터 키워드</div> -->
<!-- 							<div class="group_contents btn_search_wrap btn_search_wrap_left"> -->
<!-- 								<ul> -->
<!-- 									<li> -->
<!-- 										<input type="text" class="input_same group_box" id="dataKeyword" placeholder="데이터 키워드를 입력해 주세요." maxlength="10"> -->
<!-- 									</li> -->
<!-- 									<li> -->
<%-- 										<c:if test="${isUserChk}"> --%>
<!-- 											<button type="button" id="addKeywordBtn" class="is-darkgreen-btn group_search_btn">추가</button> -->
<%-- 										</c:if> --%>
<!-- 									</li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="group_ex"> -->
<!-- 								키워드는 최대 10자 까지 입력  가능 합니다.<br>예시) 정류소 명칭, 버스 노선 명칭 -->
<!-- 						</div>		 -->
<!-- 					</div> -->
<!-- 					<div id="keywordWrap" class="flex-center gap8 meta_append"></div> -->
<!-- 					</div> -->
<!-- 				<div class="group mt24"> -->
<!-- 					<div class="group_text">데이터 수집 유형<span class="required-alert">*</span></div> -->
<!-- 					<div class="flex-column gap8 meta-input"> -->
<!-- 						<div class="flex-center"> -->
<%-- 							<c:forEach var="collTyCd" items="${collTyCdList}"> --%>
<%-- 								<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="<c:out value='${collTyCd.cdId}'/>"><c:out value='${collTyCd.cdNm}'/></label> --%>
<%-- 							</c:forEach> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="group">
					<div class="group_text">데이터 설명</div>
										<textarea id="tblDescr" name="tblDescr" class="textarea_same" placeholder="설명을 입력해 주세요." style="display: block"><c:out value='${metadataInfo.tblDescr}'/></textarea>			
				</div>
				<c:if test="${isUserChk}">
				<!-- 파일업로드 Start -->				
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
								        <input type="file" id="uploadFiles" style="display: none;" multiple>
								        <button type="button" id="uploadBtn" class="is-darkgreen-btn mj0">파일 업로드</button>
								    </div>
								</div>
							</div>
							<div class="upload_wrap none">
								<div id="upload_list_box"></div>	
							</div>
						</div>
					</div>	
				<!-- 파일업로드 END -->	
				</c:if>
						
				<!-- 파일 다운드로드 Start -->
				<c:if test="${metadataInfo.tblType ne 'GPDB' and metadataInfo.tblType ne 'NDAP'}">
					<div class="group flex-start">
						<div class="group_text">파일 다운로드</div>
						<c:choose>
							<c:when test="${not empty metaFileInfoList}">
								<div class="flex-column">
									<div class="download_wrap mj0">
										<div id="download_list_box">
								            <c:forEach var="metaFileInfo" items="${metaFileInfoList}">
						                		<div class="list_item input_same group_box flex-center">
						                			<div class="file_list">
								                		<c:out value='${metaFileInfo.orgFileNm}'/>
						                			</div>
								                		<div class="flex-center gap8">
								                			<a href="${pageContext.request.contextPath}/metadata/manage/file/download.do?fileId=<c:out value='${metaFileInfo.fileId}'/>" class="downloadBtn">
									                			<img src="${pageContext.request.contextPath}/statics/images/download.png" alt="업로드파일 다운로드">
								                			</a>
														<c:if test="${isUserChk}">
								                			<span class="pointer" onclick="deleteFile('<c:out value='${metaFileInfo.fileId}'/>')">
									                			<img src="${pageContext.request.contextPath}/statics/images/upload_close.png" alt="업로드파일 삭제">
								                			</span>
							                			</c:if>
							                		</div>
						                		</div>
											</c:forEach>
										</div>	
									</div>
								</div>
							<!-- 파일 다운드로드 END -->		
							</c:when>
							<c:otherwise>
								<div> 등록된 파일이 없습니다. </div>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				<div class="group btn_search_wrap_left btn_search_wrap">
					<ul>
						<c:if test="${isUserChk and authCd eq 'AUC000' or mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
							<li>
								<button type="button" class="is-darkgreen-btn" id="updateMetadataBtn">저장</button> 
							</li>
							<li>
								<button type="button" class="is-darkgreen-btn" id="deleteMetadataBtn">삭제</button> 
							</li>
						</c:if>
						<c:if test="${downloadYn eq 'Y'}">
							<li>
	                			<a class="is-darkgreen-btn downloadBtn" href="${pageContext.request.contextPath}/metadata/manage/export.do?dsetId=<c:out value='${metadataInfo.dsetId}'/>">
	                				데이터 다운로드
	                			</a>
							</li>
						</c:if>
						<c:if test="${mOpOperatorInfo.oprtrGrd ne 'SUPER'}">
							<li>
	                			<button type="button" class="is-darkgreen-btn" onclick="catlogAply('<c:out value='${metadataInfo.dsetId}'/>')">
	                				데이터 이관 신청
	                			</button>
							</li>
						</c:if>
						<li>
							<a href="${pageContext.request.contextPath}/metadata/manage/list.do" class="is-dark-btn">목록</a>
						</li>
					</ul>
				</div>						
			</div>
		</form>
	</section>
</div>

<script>
// 	function deleteKeyword(idx){
// 		$("#keywordContent"+idx).remove();
// 	}
	
	function deleteDataType(idx,colSqno = null){
		var tblId = '<c:out value="${metadataInfo.tblId}"/>';
		var dsetId = '<c:out value="${metadataInfo.dsetId}"/>';

		var dataTypeIdx = $(".dataTypeVal").length;

		var obj = new Object();
			obj.tblId = tblId;
			obj.dsetId = dsetId;
			obj.colSqno = colSqno;
		
		if(dataTypeIdx <= 1){
			new ModalBuilder().init().alertBoby("데이터 유형은 1개이상 존재 해야합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
			
		if(colSqno == null || colSqno == ''){
			$("#dataTypeContent"+idx).remove();
		} else {
			new ModalBuilder().init().alertBoby("데이터 유형을 삭제 하시겠습니까? 삭제시 복구 할 수 없습니다.").footer(5,'삭제하기',function(button, modal){
				$.ajax({
					type : "post",
					contentType : "application/json; charset=UTF-8",
					data : JSON.stringify(obj),
					dataType : "json",
					url : "${pageContext.request.contextPath}/metadata/manage/dataType/delete.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("데이터 유형이 삭제 되었습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.reload();
								$(".modal_container").remove();
							}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("데이터 유형 삭제를 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				})
			 },'취소하기',function(button, modal){modal.close();}).open();
			}
		}
	
	$("#addDataTypeBtn").on('click',function(){
		var tblId = '<c:out value="${metadataInfo.tblId}"/>';
		var dsetId = '<c:out value="${metadataInfo.dsetId}"/>';
		
		var html = "";
		var dataType = $("#dataType").val().trim();
		var colEngNm = $("#colEngNm").val().trim();
		var colKoreanNm = $("#colKoreanNm").val().trim();
		
		var obj = new Object();
		obj.tblId = tblId;
		obj.dsetId = dsetId;
// 		obj.rltinstId = rltinstId;
		obj.dataType = dataType;
		obj.colEngNm = colEngNm;
		obj.colKoreanNm = colKoreanNm;
		
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
		
		new ModalBuilder().init().alertBoby("데이터 유형을 추가 하시겠습니까?").footer(5,'추가하기',function(button, modal){
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/metadata/manage/saveMetaColInfo.ajax",
				success : function(result) {
					if(result.code == 200){
						html+='<tr id="dataTypeContent'+dataTypeIdx+'">';
						html+='		<td>'+dataType+'<input type="hidden" class="dataTypeVal" value="'+dataType+'" readonly/> </td>';
						html+='		<td>'+colEngNm+'<input type="hidden" class="colEngNmVal" value="'+colEngNm+'" readonly/> </td>';
						html+='		<td>'+colKoreanNm+' <input type="hidden" class="colKoreanNmVal" value="'+colKoreanNm+'" readonly/> </td>';
						html+='		<td><span onclick="deleteDataType(\''+dataTypeIdx+'\',\''+result.data+'\')"><img src="/statics/images/delete.png" alt="삭제" class="datamng_img"></span></td>';
						html+='</tr>';
						new ModalBuilder().init().successBody("데이터 유형 추가에 성공헀습니다.").footer(4,'확인',function(button, modal){modal.close(); $(".modal_container").remove();}).open();
						modalAlertWrap();
						$("#metaColInfoTr").append(html);
						$("#dataType").val("");
						$("#colEngNm").val("");
						$("#colKoreanNm").val("");
					} else {
						new ModalBuilder().init().alertBoby("데이터 유형 추가를 실패했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
		 },'취소하기',function(button, modal){}).open();
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
		
// 		html += '<div id="keywordContent'+keywordIdx+'" class="keyword_item">';
// 		html += '<label class="is-darkgreen-btn">'+dataKeyword+'</label>';
// 		html += 	'<input type="hidden" class="keyword" vadddDlue="'+dataKeyword+'" readonly/>';
// 		html += 	'<button type="button" onclick="deleteKeyword(\''+keywordIdx+'\')" class="ftsize14">삭제</button>';
// 		html += '</div>';
		
// 		$("#keywordWrap").append(html);
// 		$("#dataKeyword").val("");
// 	});
	
	// 분류 체계 키업
	$("#clschmNm").on('mouseenter', function(){
		$(this).siblings('.metadata_sort_box').removeClass('none');
	})
	$("#clschmNm").on('mouseleave', function(){
		$(this).siblings('.metadata_sort_box').addClass('none');
	})
	//
	
	//<![CDATA[
		$(document).ready(function(){
// 			var dataKeyword = '<c:out value="${metadataInfo.dataKeyword}"/>';
			var collectionTypeCd = '<c:out value="${metadataInfo.opngDataListNm}"/>';
			var isUserChk = '<c:out value="${isUserChk}"/>';
			
// 			var dataKeywordArr = dataKeyword.split(',');
			var collectionTypeCdArr = collectionTypeCd.split(',');
			var collectionType = $(".collectionType");
			
			//작성자 체크후 disabled처리
			if (isUserChk === 'false') {
				$("input[type=text]").addClass("is-disabled");
				$("select").addClass("is-disabled");
				collectionType.parent('label').addClass("is-disabled");
			}
			
			//데이터 키워드 그리기
// 			for(var i = 0; i < dataKeywordArr.length; i++){
// 				var html = "";
// 				html += '<div id="keywordContent'+i+'" class="keyword_item">'
// 				html += 	'<label class="is-darkgreen-btn">'+dataKeywordArr[i]+'</label>';
// 				html += 	'<input type="hidden" class="keyword" value="'+dataKeywordArr[i]+'"/>';
// 				html += 	'<button type="button" onclick="deleteKeyword(\''+i+'\')" class="ftsize14">삭제</button>';
// 				html += '</div>';
// 				$("#keywordWrap").append(html);
// 			}
			
			//수집 유형 선택
			for(var i = 0; i < collectionType.length; i++){
				for(var j = 0; j < collectionTypeCdArr.length; j++){
					var code = collectionType.eq(i).val();
					if(code == collectionTypeCdArr[j]){
						collectionType.eq(i).prop('checked',true);
						collectionType.eq(i).parent('label').addClass('is-darkgreen-btn');
					}
				}
			}
			
		});
	//]]>
	
	$("#updateMetadataBtn").on('click',function(){
		var tblId = '<c:out value="${metadataInfo.tblId}"/>';
		var dsetId = '<c:out value="${metadataInfo.dsetId}"/>';
		var tblKoreanNm = $("#tblKoreanNm").val();
		var tblEngNm  = $("#tblEngNm").val();
		var clschmId = $("#clschmId").val();
// 		var clschmNm = $("#clschmNm").val();
// 		var rltinstId = $("#rltinstId").val();
// 		var orgDataNm = $("#orgDataNm").val();
		var uploadFiles = $("#uploadFiles")[0];
// 		var dataKeyword = "";
// 		var collDataType = "";
		
		var tblDescr = $("#tblDescr").val();
		
// 		var collectionType = $(".collectionType");
// 		var keyword = $(".keyword");
		
		if(!$("#metadataRegForm").soValid()){
			return false;
		}

// 		if(keyword.length == 0){
// 			new ModalBuilder().init().alertBoby("데이터 키워드를 추가 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
// 			modalAlertWrap();			
// 			return false;
// 		} else {
// 			for(var i = 0; i < keyword.length; i++){
// 				if(dataKeyword == ''){
// 					dataKeyword += keyword.eq(i).val();
// 				} else {
// 					dataKeyword += ","+keyword.eq(i).val();
// 				}
// 			}			
// 		}
		
// 		if(!collectionType.is(":checked")){
// 			new ModalBuilder().init().alertBoby("수집 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
// 			modalAlertWrap();

// 			return false;
// 		} else {
// 			for(var i = 0; i < collectionType.length; i++){
// 				if(collectionType.eq(i).is(':checked')){
// 					if(collDataType == ''){
// 						collDataType += collectionType.eq(i).val();
// 					} else {
// 						collDataType += ","+collectionType.eq(i).val();
// 					}
// 				}
// 			}
// 		}
		
		var obj = new Object();
		obj.tblId = tblId;
		obj.dsetId = dsetId;
		obj.tblKoreanNm = tblKoreanNm;
		obj.tblEngNm = tblEngNm;
		obj.clschmId = clschmId;
		obj.clschmNm = clschmNm;
// 		obj.rltinstId = rltinstId;
// 		obj.orgDataNm = orgDataNm;
// 		obj.dataKeyword = dataKeyword;
// 		obj.collDataType = collDataType;
		obj.tblDescr = tblDescr;
		
		const formData = new FormData();
		formData.acceptChartset="UTF-8";
		if(uploadFiles.files.length > 0){
			for(var i = 0; i < uploadFiles.files.length; i++){
				formData.append("uploadFiles",uploadFiles.files[i]);
			}
			formData.append("metaTabInfo",new Blob([JSON.stringify(obj)], {type: "application/json"}));
		}
		formData.append("metaTabInfo",new Blob([JSON.stringify(obj)], {type: "application/json"}));
		
		$.ajax({
			type : "post",
			data : formData,
			contentType : false,
			processData : false,
			enctype : "multipart/form-data;charset=UTF-8",
			dataType : "json",
			url : "${pageContext.request.contextPath}/metadata/manage/update.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("메타데이터 수정에 성공 하였습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href="${pageContext.request.contextPath}/metadata/manage/list.do";
					}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby("메타데이터 수정을 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	});
	
	$("#deleteMetadataBtn").on('click',function(){
		//<![CDATA[
		var tblId = '<c:out value="${metadataInfo.tblId}"/>';
		var dsetId = '<c:out value="${metadataInfo.dsetId}"/>';
		//]]>
		
		var obj = new Object();
		obj.tblId = tblId;
		obj.dsetId = dsetId;
		
		new ModalBuilder().init().alertBoby("메타데이터를 삭제 하시겠습니까? 삭제시 복구 할 수 없습니다.").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify(obj),
				dataType : "json",
				url : "${pageContext.request.contextPath}/metadata/manage/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("메타데이터가 삭제 되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href="${pageContext.request.contextPath}/metadata/manage/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("메타데이터가 삭제 되지 않았습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
		 },'취소하기',function(button, modal){}).open();				
	})
	
	function deleteFile(fileId){
		if(fileId == null || fileId == ''){
			return false;
		}
		
		new ModalBuilder().init().alertBoby("파일을 삭제 하시겠습니까? 삭제시 복구 할 수 없습니다.").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "post",
				data : {
					"fileId" : fileId
				},
				dataType : "json",
				url : "${pageContext.request.contextPath}/metadata/manage/uploadfile/delete.ajax",
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
		 },'취소하기',function(button, modal){}).open();
	};
	
	function fileUploadChange(obj){
		var fileNm = obj.value;
		var maxBytes = 209715200; //200MB
		var fileBytes = 0;
		var fileNmMaxLength = 50;
		var fileValue = $("#uploadFiles").val().split("\\");
		var fileName = fileValue[fileValue.length-1];
		
		if(fileNm != ''){
			fileBytes = document.getElementById("uploadFiles").files[0].size;
		}
		if(fileNm != ''){
			var ext = fileNm.slice(fileNm.lastIndexOf(".")+1).toLowerCase();
			//pptx , pdf 는 테스트용
			if(ext != 'csv' && ext != 'pptx' && ext != 'pdf'){
				new ModalBuilder().init().alertBoby("csv 파일만 등록이 가능합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				fileRemove();
				modalAlertWrap();								
			}else if(fileBytes > maxBytes){
				new ModalBuilder().init().alertBoby("200MB 이하의 파일만 등록이 가능합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				fileRemove();
				return;
			}else if(fileName.length > fileNmMaxLength){
				new ModalBuilder().init().alertBoby("저장 가능 길이를 초과하였습니다.<br>변경 후 다시 업로드 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				fileRemove();
				$("#uploadFiles").focus();
				return false;				
			}
		}
	}	
	
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
        			$('.upload_wrap').addClass('none');
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
    
    function exportMetaData(dsetId){
		
    }
    
    //카탈로그 이관 신청
    function catlogAply(dsetId){
		var obj = new Object();
		obj.aplyDsetId = dsetId;
		
		new ModalBuilder().init().alertBoby("데이터 이관을 신청하시겠습니까?").footer(5,'신청',function(button, modal){
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify(obj),
				dataType : "json",
				url : "${pageContext.request.contextPath}/metadata/history/save.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("데이터 이관신청이 완료 되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("데이터 이관신청중 오류가 발생했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
		},'취소하기',function(button, modal){modal.close()}).open();
    }
</script>
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
					<div class="group_text">서비스 이름(KOR)</div>
					<input type="text" id="tblKoreanNm" class="input_same group_box data-validate" data-valid-name="서비스 이름(KOR)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupKorEvent(this)">
				</div>
				<div class="group">
					<div class="group_text">서비스 이름(ENG)</div>
					<input type="text" id="tblEngNm" class="input_same group_box data-validate" data-valid-name="서비스 이름(ENG)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupEngEvent(this)">
				</div>
				<div class="group">
					<div class="group_text">분류체계</div>
                    <div class="metadata_sort_container">
						<input type="text" class="input_same group_box " id="clschmId"  placeholder="분류체계를 입력해 주세요.">
						<div class="metadata_sort_box none">
							<div class="metadata_sort_wrap">
								<div class="metadata_sort_title">등록된 분류체계</div>
								<ul class="metadata_sort_list_box">
									<c:forEach var="clschmId" items="${clschmIdList}">
										<li class="metadata_sort_list">${clschmId}</li>
		                            </c:forEach>
								</ul>
							</div>
						</div>
                    </div>
				</div>
				<div class="group">
					<div class="group_text">유관기관</div>
					<select class="selectBox data-validate" id="rltinstId" data-valid-name="유관기관" data-valid-required>
						<option value="">선택하기</option>
						<c:forEach var="metaInfSysInfoList" items="${metaInfSysInfoList}">
							<option value="${metaInfSysInfoList.rltinstId}">${metaInfSysInfoList.rltinstNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="group">
					<div class="group_text">원본 데이터 이름</div>
					<input type="text" id="orgDataNm" class="input_same group_box data-validate" data-valid-name="원본 데이터 이름" data-valid-required placeholder="원본 데이터 이름을 입력해 주세요.">
				</div>
				<div class="group mt56">
					<div class="group_text">데이터 유형</div>
					<input type="text" id="dataType" class="input_same group_box" placeholder="데이터 유형을 입력해 주세요.">
					<div class="group_ex_box"><span class="group_ex">데이터 유형은 최대 3개 까지 입력 가능 합니다.</span></div>		
					<button type="button" id="addDataTypeBtn" class="is-darkgreen-btn group_search_btn ml8">추가</button>
					<div id="dataTypeWrap" class="flex-center gap8">

					</div>
				</div>
				<div class="group mt56">
					<div class="group_text">데이터 키워드</div>
					<input type="text" class="input_same group_box" id="dataKeyword" placeholder="데이터 키워드를 입력해 주세요." maxlength="10">
					<div class="group_ex_box"><span class="group_ex">키워드는 최대 10자 까지 입력 가능 합니다.<br>예시) 정류소 명칭, 버스 노선 명칭</span></div>		
					<button type="button" id="addKeywordBtn" class="is-darkgreen-btn group_search_btn ml8">추가</button>
					<div id="keywordWrap" class="flex-center gap8">

					</div>
				</div>
				<div class="group mt56 flex-center-remove">
					<div class="group_text">데이터 수집 유형</div>
					<div class="flex-center gap8">
						<c:forEach var="collTyCd" items="${collTyCdList}">
							<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="${collTyCd.cdId}">${collTyCd.cdNm}</label>
						</c:forEach>
					</div>
				</div>
				<div class="group">
					<div class="group_text">데이터 설명</div>
					<input type="text" id="tblDescr" class="input_same group_box" placeholder="설명을 입력해 주세요.">
				</div>
				<div class="group group_search">
					<input type="button" id="metadataSaveBtn" class="is-darkgreen-btn" value="저장"> 
					<a href="${pageContext.request.contextPath}/metadatamng/list.do" class="is-dark-btn">취소</a>
				</div>
			</div>
		</form>
	</section>
</div>

<script>

	function deleteKeyword(idx){
		$("#keywordContent"+idx).remove();
	}
	
	function deleteDataType(idx){
		$("#dataTypeContent"+idx).remove();
	}
	
	// 분류 체계 키업
	$("#clschmId").on('mouseenter', function(){
		$(this).siblings('.metadata_sort_box').removeClass('none');
	})
	$("#clschmId").on('mouseleave', function(){
		$(this).siblings('.metadata_sort_box').addClass('none');
	})
	//
	$("#addDataTypeBtn").on('click',function(){
		var html = "";
		var dataType = $("#dataType").val().trim();
		
		if(dataType == null || dataType == ''){
			new ModalBuilder().init().alertBoby("데이터 유형을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		var dataTypeIdx = $(".dataTypeVal").length;
		
		if(3 <= dataTypeIdx){
			new ModalBuilder().init().alertBoby("데이터 유형은 최대 3개까지 입력 가능합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		html += '<div id="dataTypeContent'+dataTypeIdx+'">';
		html += '<label class="is-darkgreen-btn">'+dataType+'</label>';
		html += 	'<input type="hidden" class="dataTypeVal" value="'+dataType+'" readonly/>';
		html += 	'<button type="button" onclick="deleteDataType(\''+dataTypeIdx+'\')">삭제</button>';
		html += '</div>';
		
		$("#dataTypeWrap").append(html);
		$("#dataType").val("");
	});
	
	$("#addKeywordBtn").on('click',function(){
		var html = "";
		var dataKeyword = $("#dataKeyword").val().trim();
		
		if(dataKeyword == null || dataKeyword == ''){
			new ModalBuilder().init().alertBoby("데이터 키워드를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		var keywordIdx = $(".keyword").length;
		
		html += '<div id="keywordContent'+keywordIdx+'">';
		html += '<label class="is-darkgreen-btn">'+dataKeyword+'</label>';
		html += 	'<input type="hidden" class="keyword" value="'+dataKeyword+'" readonly/>';
		html += 	'<button type="button" onclick="deleteKeyword(\''+keywordIdx+'\')">삭제</button>';
		html += '</div>';
		
		$("#keywordWrap").append(html);
		$("#dataKeyword").val("");
	});
	
	$("#metadataSaveBtn").on('click',function(){
		var tblKoreanNm = $("#tblKoreanNm").val();
		var tblEngNm  = $("#tblEngNm").val();
		var clschmId = $("#clschmId").val();
		var rltinstId = $("#rltinstId").val();
		var orgDataNm = $("#orgDataNm").val();
		var dataType = "";
		var dataKeyword = "";
		var collDataType = "";
		
		var tblDescr = $("#tblDescr").val();
		
		var dataTypeVal = $(".dataTypeVal");
		var collectionType = $(".collectionType");
		var keyword = $(".keyword");
		
		if(!$("#metadataRegForm").soValid()){
			return false;
		}

		if(dataTypeVal.length == 0){
			new ModalBuilder().init().alertBoby("데이터 유형을 추가 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		} else {
			for(var i = 0; i < dataTypeVal.length; i++){
				if(dataType == ''){
					dataType += dataTypeVal.eq(i).val();
				} else {
					dataType += ","+dataTypeVal.eq(i).val();
				}
			}			
		}
		
		if(keyword.length == 0){
			new ModalBuilder().init().alertBoby("데이터 키워드를 추가 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		} else {
			for(var i = 0; i < keyword.length; i++){
				if(dataKeyword == ''){
					dataKeyword += keyword.eq(i).val();
				} else {
					dataKeyword += ","+keyword.eq(i).val();
				}
			}			
		}
		
		if(!collectionType.is(":checked")){
			new ModalBuilder().init().alertBoby("수집 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		} else {
			for(var i = 0; i < collectionType.length; i++){
				if(collectionType.eq(i).is(':checked')){
					if(collDataType == ''){
						collDataType += collectionType.eq(i).val();
					} else {
						collDataType += ","+collectionType.eq(i).val();
					}
				}
			}
		}
		
		var obj = new Object();
		obj.tblKoreanNm = tblKoreanNm;
		obj.tblEngNm = tblEngNm;
		obj.clschmId = clschmId;
		obj.rltinstId = rltinstId;
		obj.dataType = dataType;
		obj.orgDataNm = orgDataNm;
		obj.dataKeyword = dataKeyword;
		obj.collDataType = collDataType;
		obj.tblDescr = tblDescr;
		
		$.ajax({
			type : "post",
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/metadatamng/save.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("메타데이터 등록을 성공 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					location.href = ${pageContext.request.contextPath}"/metadatamng/list.do";
				} else {
					new ModalBuilder().init().alertBoby("메타데이터 등록에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	
	});
</script>
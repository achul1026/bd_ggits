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
					<input type="text" class="input_same group_box" value="${metadataInfo.tblId}" readonly>
				</div>
				<div class="group">
					<div class="group_text">서비스 이름(KOR)</div>
					<input type="text" id="tblKoreanNm" class="input_same group_box data-validate" data-valid-name="서비스 이름(KOR)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupKorEvent(this)" value="${metadataInfo.tblKoreanNm}">
				</div>
				<div class="group">
					<div class="group_text">서비스 이름(ENG)</div>
					<input type="text" id="tblEngNm" class="input_same group_box data-validate" data-valid-name="서비스 이름(ENG)" data-valid-required placeholder="서비스 이름을 입력해 주세요." onkeyup="keyupEngEvent(this)" value="${metadataInfo.tblEngNm}">
				</div>
				<div class="group">
					<div class="group_text">분류체계</div>
                    <div class="metadata_sort_container">
						<input type="text" class="input_same group_box" id="clschmId" placeholder="분류체계를 입력해 주세요." value="${metadataInfo.clschmId}">
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
					<select class="selectBox" id="rltinstId" data-valid-name="유관기관" data-valid-required>
						<option value="">선택하기</option>
						<c:forEach var="metaInfSysInfoList" items="${metaInfSysInfoList}">
							<option value="${metaInfSysInfoList.rltinstId}" ${metaInfSysInfoList.rltinstId eq metadataInfo.rltinstId ? 'selected':''}>${metaInfSysInfoList.rltinstNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="group">
					<div class="group_text">원본 데이터 이름</div>
					<input type="text" class="input_same group_box" id="orgDataNm" value="${metadataInfo.orgDataNm}">
				</div>
				<div>
					<div class="flex-center relative">
						<div class="group_text">데이터 유형</div>
						<input type="text" id="dataType" class="input_same group_box" placeholder="데이터 유형을 입력해 주세요.">
						<c:if test="${isUserChk}">
							<button type="button" id="addDataTypeBtn" class="is-darkgreen-btn group_search_btn ml8">추가</button>
						</c:if>
						<div class="group_ex_box"><span class="group_ex">데이터 유형은 최대 3개 까지 입력 가능 합니다.</span></div>		
					</div>
					<div id="dataTypeWrap" class="flex-center gap8 meta_append">
						<c:forEach var="metaColInfoList" items="${metaColInfoList}" varStatus="status">
							<div id="dataTypeContent${status.index}">
							<label class="is-darkgreen-btn">${metaColInfoList.dataType}</label>
								<input type="hidden" class="dataTypeVal" value="${metaColInfoList.dataType}" readonly/>
								<c:if test="${isUserChk}">
									<button type="button" onclick="deleteDataType('${status.index}','${metaColInfoList.colSqno}')" class="ftsize14">삭제</button>
								</c:if>
							</div>		
						</c:forEach>
					</div>
				</div>
				<div class="mt24">
					<div class="flex-center relative">
						<div class="group_text">데이터 키워드</div>
						<input type="text" class="input_same group_box" id="dataKeyword" placeholder="데이터 키워드를 입력해 주세요." maxlength="10">
						<div class="group_ex_box"><span class="group_ex">키워드는 최대 10자 까지 입력 가능 합니다.<br>예시) 정류소 명칭, 버스 노선 명칭</span></div>		
						<c:if test="${isUserChk}">
							<button type="button" id="addKeywordBtn" class="is-darkgreen-btn group_search_btn ml8">추가</button>
						</c:if>
					</div>
					<div id="keywordWrap" class="flex-center gap8 meta_append2"></div>
				</div>
				<div class="group mt24 flex-center-remove">
					<div class="group_text">데이터 수집 유형</div>
					<div class="flex-column gap8">
						<div class="flex-center">
							<c:forEach var="collTyCd" items="${collTyCdList}">
								<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="${collTyCd.cdId}">${collTyCd.cdNm}</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="group">
					<div class="group_text">데이터 설명</div>
					<input type="text" class="input_same group_box" id="tblDescr" value="${metadataInfo.tblDescr}">
				</div>
				<div class="group group_search">
					<c:if test="${isUserChk}">
						<button type="button" class="is-darkgreen-btn" id="updateMetadataBtn">저장</button> 
						<button type="button" class="is-darkgreen-btn" id="deleteMetadataBtn">삭제</button> 
					</c:if>
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
	
	function deleteDataType(idx,colSqno = null){
		//<![CDATA[
		var tblId = '${metadataInfo.tblId}';
		var dsetId = '${metadataInfo.dsetId}';
		//]]>
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
					url : "${pageContext.request.contextPath}/metadatamng/dataType/delete.ajax",
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
			 },'취소하기',function(button, modal){}).open();
			}
		}
	
	$("#addDataTypeBtn").on('click',function(){
		//<![CDATA[
		var tblId = '${metadataInfo.tblId}';
		var dsetId = '${metadataInfo.dsetId}';
		var rltinstId = '${metadataInfo.rltinstId}';
		//]]>
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
		
		var obj = new Object();
		obj.tblId = tblId;
		obj.dsetId = dsetId;
		obj.rltinstId = rltinstId;
		obj.dataType = dataType;
		
		new ModalBuilder().init().alertBoby("데이터 유형을 추가 하시겠습니까?").footer(5,'추가하기',function(button, modal){
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/metadatamng/saveMetaColInfo.ajax",
				success : function(result) {
					if(result.code == 200){
						html += '<div id="dataTypeContent'+dataTypeIdx+'">';
						html += '<label class="is-darkgreen-btn">'+dataType+'</label>';
						html += 	'<input type="hidden" class="dataTypeVal" value="'+dataType+'" readonly/>';
						html += 	'<button type="button" onclick="deleteDataType(\''+dataTypeIdx+'\',\''+result.data+'\')" class="ftsize14">삭제</button>';
						html += '</div>';
						new ModalBuilder().init().successBody("데이터 유형 추가에 성공헀습니다.").footer(4,'확인',function(button, modal){modal.close(); $(".modal_container").remove();}).open();
						modalAlertWrap();
						
						$("#dataTypeWrap").append(html);
						$("#dataType").val("");
					} else {
						new ModalBuilder().init().alertBoby("데이터 유형 추가를 실패했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
		 },'취소하기',function(button, modal){}).open();
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
		
		html += '<div id="keywordContent'+keywordIdx+'" class="keyword_item">';
		html += '<label class="is-darkgreen-btn">'+dataKeyword+'</label>';
		html += 	'<input type="hidden" class="keyword" value="'+dataKeyword+'" readonly/>';
		html += 	'<button type="button" onclick="deleteKeyword(\''+keywordIdx+'\')" class="ftsize14">삭제</button>';
		html += '</div>';
		
		$("#keywordWrap").append(html);
		$("#dataKeyword").val("");
	});
	
	// 	분류 체계 키업
	$("#clschmId").on('mouseenter', function(){
		$(this).siblings('.metadata_sort_box').removeClass('none');
	})
	$("#clschmId").on('mouseleave', function(){
		$(this).siblings('.metadata_sort_box').addClass('none');
	})
	// 
	
	
	//<![CDATA[
		$(document).ready(function(){
			var dataKeyword = '${metadataInfo.dataKeyword}';
			var collectionTypeCd = '${metadataInfo.opngDataListNm}';
			var isUserChk = ${isUserChk};
			
			var dataKeywordArr = dataKeyword.split(',');
			var collectionTypeCdArr = collectionTypeCd.split(',');
			var collectionType = $(".collectionType");
			
			//작성자 체크후 disabled처리
			if(!isUserChk){
				$("input").addClass("is-disabled");
				$("select").addClass("is-disabled");
				collectionType.parent('label').addClass("is-disabled");
			}
			
			//데이터 키워드 그리기
			for(var i = 0; i < dataKeywordArr.length; i++){
				var html = "";
				html += '<div id="keywordContent'+i+'" class="keyword_item">'
				html += 	'<label class="is-darkgreen-btn">'+dataKeywordArr[i]+'</label>';
				html += 	'<input type="hidden" class="keyword" value="'+dataKeywordArr[i]+'"/>';
				<c:if test="${isUserChk}">
				html += 	'<button type="button" onclick="deleteKeyword(\''+i+'\')" class="ftsize14">삭제</button>';
				</c:if>				
				html += '</div>';
				$("#keywordWrap").append(html);
			}
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
		//<![CDATA[
		var tblId = '${metadataInfo.tblId}';
		var dsetId = '${metadataInfo.dsetId}';
		//]]>
		var tblKoreanNm = $("#tblKoreanNm").val();
		var tblEngNm  = $("#tblEngNm").val();
		var clschmId = $("#clschmId").val();
		var rltinstId = $("#rltinstId").val();
		var orgDataNm = $("#orgDataNm").val();
		var dataKeyword = "";
		var collDataType = "";
		
		var tblDescr = $("#tblDescr").val();
		
		var collectionType = $(".collectionType");
		var keyword = $(".keyword");
		
		if(!$("#metadataRegForm").soValid()){
			return false;
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
		obj.tblId = tblId;
		obj.dsetId = dsetId;
		obj.tblKoreanNm = tblKoreanNm;
		obj.tblEngNm = tblEngNm;
		obj.clschmId = clschmId;
		obj.rltinstId = rltinstId;
		obj.orgDataNm = orgDataNm;
		obj.dataKeyword = dataKeyword;
		obj.collDataType = collDataType;
		obj.tblDescr = tblDescr;
		
		$.ajax({
			type : "post",
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/metadatamng/update.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("메타데이터 수정에 성공 하였습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href="${pageContext.request.contextPath}/metadatamng/list.do";
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
		var tblId = '${metadataInfo.tblId}';
		var dsetId = '${metadataInfo.dsetId}';
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
				url : "${pageContext.request.contextPath}/metadatamng/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("메타데이터가 삭제 되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href="${pageContext.request.contextPath}/metadatamng/list.do";
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
</script>
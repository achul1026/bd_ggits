<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">Open API 관리</h3>
                <div class="side_txt">Open API를 관리합니다.</div>
            </div>
        </aside>
        <section class="main_section">
	        <div class="contents_wrap">
	        	<form id="openApiFrm">
	                <div class="group">
	                    <div class="group_text">API 이름</div>
	                    <input type="text" placeholder="API 이름을 입력해주세요." class="input_same group_box data-validate" data-valid-name="API 이름" id="apiNm" name="apiNm" value="<c:out value='${openApiDetailInfo.apiNm}'/>" style="width:400px;" maxlength="100" data-valid-required>
	                </div>
	                <div class="group">
	                    <div class="group_text">API 설명</div>
	                    <input type="text" placeholder="API 설명을 입력해주세요" class="input_same group_box data-validate" data-valid-name="API 설명" id="descr" name="descr" value="<c:out value='${openApiDetailInfo.descr}'/>" style="width:400px;" maxlength="400" data-valid-required>
	                </div>
	                <div class="group">
	                    <div class="group_text">API URL</div>
	                	<a class="input_same div_input_style" href="<c:out value='${openApiDetailInfo.apiCallUrl}'/>" style="width:400px;"><c:out value="${openApiDetailInfo.apiCallUrl}"/></a>
	                </div>
	                <div class="group flex-start">
	                    <div class="group_text">요청 파라미터</div>
	                    <input type="hidden" placeholder="샘플 URL을 입력해 주세요." class="input_same group_box" id="apiCallUrl" name="apiCallUrl" value="<c:out value='${openApiDetailInfo.apiCallUrl}'/>">
	                    <textarea class="input_same group_box input_textarea" id="contents" name="contents" rows="10"><c:out value="${openApiDetailInfo.contents}"/></textarea>
	                </div>
	                <div class="group group_search">
	                	 <c:if test="${isUserChk and authCd eq 'AUC000' or mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
							<button type="button" class="is-darkgreen-btn" id="openApiUpdateBtn">수정</button>
							<button type="button" class="is-darkgreen-btn" id="openApiDeleteBtn">삭제</button>
		                </c:if>
						<button type="button" class="is-dark-btn" id="openApiListBtn">이전 페이지</button>
	                </div>
	            </form>
            </div>
        </section>
    </div>
</main>

<script>
$("#openApiListBtn").on("click",function(){
	location.href='${pageContext.request.contextPath}/system/openapi/list.do'
});

$("#openApiUpdateBtn").on("click",function(){
	if($("#openApiFrm").soValid()) {
		if($('#contents').val().trim() != '') {
			var dsetId = '${openApiDetailInfo.dsetId}';
			var apiNm = $("#apiNm").val();
			var contents = $("#contents").val();
			var apiCallUrl = $("#apiCallUrl").val();
			var descr = $("#descr").val();
			
			var obj = new Object();
			obj.dsetId = dsetId;
			obj.apiNm = apiNm;
			obj.contents = contents;
			obj.apiCallUrl = apiCallUrl;
			obj.descr = descr;
			
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/system/openapi/update.ajax",
				success : function(result) {
					if(result.code == 200){
						new ModalBuilder().init().successBody("Open API 정보가 수정되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
							}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("Open API 정보 수정에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
		} else {
			new ModalBuilder().init().alertBoby('사용 예제을(를) 입력해주세요').footer(4,'확인',function(button, modal){
				$('#contents').focus();
				modal.close();
			}).open();
			modalAlertWrap();
		}
	}

});

$("#openApiDeleteBtn").on("click",function(){
	//<![CDATA[
	var dsetId = '<c:out value="${openApiDetailInfo.dsetId}"/>';
	//]]>
	
	var obj = new Object();
	obj.dsetId = dsetId;
	
	new ModalBuilder().init().alertBoby("OPEN API를 삭제 하시겠습니까?").footer(5,'삭제하기',function(button, modal){
		$.ajax({
			type : "post",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(obj),
			dataType : "json",
			url : "${pageContext.request.contextPath}/system/openapi/delete.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("OPEN API가 삭제 되었습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href="${pageContext.request.contextPath}/system/openapi/list.do";
					}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby("OPEN API가 삭제 되지 않았습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		})
	 },'취소하기',function(button, modal){}).open();	
});
</script>

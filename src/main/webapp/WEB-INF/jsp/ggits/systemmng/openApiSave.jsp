<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">Open API 등록하기</h3>
                <div class="side_txt">Open API를 등록 합니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <div class="contents_wrap">
            	<form id="openApiFrm">
	                <div class="group">
	                    <div class="group_text">API 이름</div>
	                    <input type="text" placeholder="API 이름을 입력해주세요." class="input_same group_box data-validate" data-valid-name="API 이름" id="apiNm" name="apiNm" style="width:400px;" maxlength="100" data-valid-required>
	                </div>
	                <div class="group">
	                    <div class="group_text">API 설명</div>
	                    <input type="text" placeholder="API 설명을 입력해주세요" class="input_same group_box data-validate" data-valid-name="API 설명" id="descr" name="descr" style="width:400px;" maxlength="400" data-valid-required>
	                </div>
	                <div class="group">
	                    <div class="group_text">API URL</div>
	                    <input type="text" placeholder="API URL을 입력해 주세요." class="input_same group_box data-validate" data-valid-name="API URL" id="apiCallUrl" name="apiCallUrl" style="width:400px;" maxlength="100" data-valid-required>
	                </div>
	                <div class="group flex-start">
	                    <div class="group_text">요청 파라미터</div>
	                    <textarea class="input_same group_box input_textarea" id="contents" name="contents" rows="10" placeholder="사용 예제를 입력해 주세요." maxlength="4000"></textarea>
	                </div>
					<button type="button" class="is-darkgreen-btn" id="openApiSaveBtn">등록</button>
					<button type="button" class="is-dark-btn" id="openApiCancelBtn">취소</button>
				</form>
            </div>
        </section>
    </div>
</main>
<script>

	$("#openApiCancelBtn").on("click",function(){
		location.href='${pageContext.request.contextPath}/system/openapi/list.do'
	});

	$(document).ready(function() {
		let isClick = true;
		
		$("#openApiSaveBtn").on("click",function(e){
			e.preventDefault();
			if(isClick){
				if($("#openApiFrm").soValid()) {
					if($('#contents').val().trim() != '') {
						isClick = false;
						var apiNm = $("#apiNm").val();
						var apiCallUrl = $("#apiCallUrl").val();
						var contents = $("#contents").val();
						var descr = $("#descr").val();
						
						var obj = new Object();
						obj.apiNm = apiNm;
						obj.apiCallUrl = apiCallUrl;
						obj.contents = contents;
						obj.descr = descr;
						
						$.ajax({
							type : "post",
							contentType : "application/json; charset=UTF-8",
							dataType : "json",
							data : JSON.stringify(obj),
							url : "${pageContext.request.contextPath}/system/openapi/save.ajax",
							success : function(result) {
								if(result.code == 200){
									new ModalBuilder().init().successBody("Open API 등록을 성공 하였습니다.").footer(4,'확인',function(button, modal){
										modal.close();
										location.href = "${pageContext.request.contextPath}/system/openapi/list.do";
										}).open();
									modalAlertWrap();
								} else {
									new ModalBuilder().init().alertBoby("Open API 등록에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
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
						isClick = true;
					}
				} else {
					isClick = true;
					return false;
				}
			}
			
		});
	})

</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">그룹코드 등록</h3>
                <div class="side_txt">그룹코드를 등록해보세요.<br>그룹코드 정보는 언제든지<br>추가로 지정하실 수 있습니다.
                    <br><br>
                    그룹코드 등록 후 그룹코드별<br>코드를 등록하실 수 있습니다.
                </div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">그룹코드 등록</h2>
            <form id="groupCodeUpdateForm">
             <div class="contents_wrap mt24">
                 <div class="group">
                     <div class="group_text">그룹코드 (필수)</div>
                     <input type="text" name="grpCdId" id="grpCdId" placeholder="그룹코드를 입력해 주세요." 
                     	class="input_same group_box data-validate" data-valid-name="그룹코드" data-valid-required maxlength="20">
                 </div>
                 <div class="group">
                     <div class="group_text">그룹코드명 (필수)</div>
                     <input type="text" name="grpCdNm" id="grpCdNm" placeholder="그룹코드명을 입력해 주세요." 
                     	class="input_same group_box data-validate" data-valid-name="그룹코드명" data-valid-required maxlength="50">
                 </div>
                 <div class="group">
                     <div class="group_text">그룹코드 설명</div>
                     <input type="text" name="descr" id="descr" placeholder="그룹코드 설명을 입력해 주세요." class="input_same group_box" maxlength="400">
                 </div>
                 <div class="group">
                     <div class="group_text">사용여부 (필수)</div>
                     <select class="selectBox data-validate" name="useYn" id="useYn" data-valid-name="사용여부" data-valid-required>
                         <option value="Y">사용</option>
                         <option value="N">미사용</option>
                     </select>
                 </div>
                 <div class="group group_search">
                     <button type="button" id="saveGrpCode" class="is-darkgreen-btn">등록</button>
                     <a href="${pageContext.request.contextPath}/system/codegrp/list.do" class="is-dark-btn">이전 페이지</a>
                 </div>
             </div>
            </form>
        </section>
    </div>
</main>
<script>
$("#saveGrpCode").on('click',function(){
	// validation
	if($("#groupCodeUpdateForm").soValid()){
		var groupCodeUpdateForm = $("#groupCodeUpdateForm").serializeObject();
	
		$.ajax({
			type : "post",
			data : JSON.stringify(groupCodeUpdateForm),
			contentType: "application/json; charset=UTF-8",
			url : "${pageContext.request.contextPath}/system/codegrp/save.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("그룹코드가 등록되었습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href = "${pageContext.request.contextPath}/system/codegrp/list.do";
					}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby("그룹코드 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});		
	}
});
</script>
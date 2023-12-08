<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 정보</h3>
                    <div class="side_txt">사용자 정보를 확인하거나<br>수정할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 정보</h2>
	                <div class="contents_wrap mt24">
		                <form id="userDetailForm">
		                	<input type="hidden" id="oprtrId" name="oprtrId" value="${userDetail.oprtrId}">
		                	<input type="hidden" id="oprtrEmail" name="oprtrEmail" value="${userDetail.oprtrEmail}">
		                	<input type="hidden" name="grpId" value="${userDetail.grpId}">
		                    <div class="group">
		                        <div class="group_text">사용자 ID</div>
		                        ${userDetail.oprtrEmail}
		                    </div>
		                    <div class="group">
		                        <div class="group_text">사용자 이름</div>
		                        <input type="text" class="input_same group_box data-validate" name="oprtrNm" value="${userDetail.oprtrNm}"
		                        	data-valid-name="사용자 이름" data-valid-required maxlength="4"/>
		                    </div>
		                    <div class="group">
		                        <div class="group_text">비밀번호</div>
		                        	<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER' or (mOpOperatorInfo.oprtrGrd eq 'GENERAL' and mOpOperatorInfo.oprtrId eq userDetail.oprtrId)}">
		                        		<input type="button" class="is-darkgreen-btn mt16 pwchange" value="변경하기" data-chk-value="Y">
                        			</c:if>
		                    </div>
		                    <div class="group group_update_pswd none">
<!-- 		                    s -->
			                    <div class="flex-column gap24">
				                    <div class="input_wrap flex-center">
				                        <div class="group_text">기존 비밀번호</div>
				                        <input type="password" class="input_same group_box" id="oprtrPswd" placeholder="비밀번호를 입력해 주세요." onkeyup="isPwValidated(this)" maxlength="32">
				                        <p class="error_message system_message"></p>
				                        <button type="button" class="eye_img system_eye_img"><img src="/statics/images/eye_off.png" alt="눈"></button>
				                        <div class="pswd_true_btn">
					                        <input type="button" class="is-darkgreen-btn" id="oprtrPswdChk" value="확인">
				                        </div>
				                    </div>
				                    <div class="input_wrap flex-center">
				                        <div class="group_text">새 비밀번호</div>
				                        <input type="password" class="input_same group_box chkPw" 
				                        id="newOprtrPswd" placeholder="새 비밀번호를 입력해 주세요." onkeyup="isPwValidated(this)" >
				                        <p class="error_message system_message"></p>
				                        <button type="button" class="eye_img system_eye_img"><img src="/statics/images/eye_off.png" alt="눈"></button>
				                    </div>
				                    <div class="input_wrap flex-center">
				                        <div class="group_text">새 비밀번호 확인</div>
				                        <input type="password"class="input_same group_box chkNewPw" id="newOprtrPswdChk" placeholder="새 비밀번호를 확인 입력해 주세요." onkeyup="isPwValidated(this)">
				                        <p class="error_message system_message"></p>
				                        <button type="button" class="eye_img system_eye_img"><img src="/statics/images/eye_off.png" alt="눈"></button>
				                    </div>
				                    <div class="right mr72">
				                        <input type="button" class="is-darkgreen-btn" id="updatePswd" value="변경완료">
				                    </div>
			                    </div>
		                    </div>
		                    
		                    <div class="group">
		                        <div class="group_text">연락처</div>
		                        <input type="text" class="input_same group_box data-validate" name="oprtrTel" value="${userDetail.oprtrTel}"
		                        	data-valid-name="연락처" data-valid-required oninput="keyupPhoneEvent(this)" maxlength="13"/>
		                    </div>
		                    <div class="group">
		                        <div class="group_text">소속행정기관</div>
		                        <input type="hidden" class="data-validate" id="addngCd" name="addngCd" value="${userDetail.addngCd}"
		                        	data-valid-name="소속행정기관" data-valid-required/>
		                        <div class="input_same group_box div_input_style" id="addngCdNm">${userDetail.cdNm}</div>
		                        <input type="button" class="is-darkgreen-btn administrative_btn ml8" value="찾기">
		                    </div>
		                    <div class="group">
		                        <div class="group_text">그룹</div>
		                        <input type="hidden" id="grpId" class="data-validate" name="grpId" value="${userDetail.grpId}"
		                        	data-valid-name="그룹" data-valid-required/>
		                        <div class="input_same group_box div_input_style" id="grpNm">${userDetail.grpNm}</div>
		                        <input type="button" class="is-darkgreen-btn group_search_btn ml8" value="찾기">
		                    </div>
		                    <div class="group">
		                        <div class="group_text">상태</div>
		                        <select class="selectBox" name="oprtrSttsCd" >
		                            <option value="OSC001" <c:if test="${userDetail.oprtrSttsCd eq 'OSC001'}">selected="selected"</c:if>>미승인</option>
		                            <option value="OSC002" <c:if test="${userDetail.oprtrSttsCd eq 'OSC002'}">selected="selected"</c:if>>승인</option>
		                            <option value="OSC003" <c:if test="${userDetail.oprtrSttsCd eq 'OSC003'}">selected="selected"</c:if>>중지</option>
		                        </select>
		                    </div>
		                </form>
	                    <div class="group group_search">
	                        <a href="${pageContext.request.contextPath}/system/user/list.do" class="is-dark-btn">이전 페이지</a>
                        		<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER' or (mOpOperatorInfo.oprtrGrd eq 'GENERAL' and mOpOperatorInfo.oprtrId eq userDetail.oprtrId)}">
              				    	<a href="javascript:void(0);" class="is-darkgreen-btn" id="userInfoUpdateBtn">수정</a>
		                        	<c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
			                        	<a href="javascript:void(0);" class="is-dark-btn" id="userInfoDeleteBtn">삭제</a>
		                        	</c:if>
                        		</c:if>
	                    </div>
	                </div>
            	</section>
        	</div>
    <script type="text/javascript">
    	
    $("#userInfoUpdateBtn").on("click",function(){
    	if($("#userDetailForm").soValid()){
    		var userDetailForm = $("#userDetailForm").serializeObject();
    		$.ajax({
    			type : "post",
    			data : JSON.stringify(userDetailForm),
    			contentType: "application/json; charset=UTF-8",
    			url : "${pageContext.request.contextPath}/system/user/update.ajax",
    			success : function(result) {
    				var resultCode = result.code;
    				var resultMessage = result.message;
    				if(resultCode == '200'){
    					new ModalBuilder().init().successBody("유저 정보가 수정되었습니다.").footer(4,'확인',function(button, modal){
    						modal.close();
	    					window.location.reload();
    					}).open();
    					modalAlertWrap();    					
    				}else{
    					new ModalBuilder().init().alertBoby("유저 정보 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();    					
    				}
	  			}
   			});    		
    	}
    })
    	
    	$("#userInfoDeleteBtn").on("click",function(){
    		var oprtrId = $("#oprtrId").val();
    		
    		$.ajax({
    			type : "get",
    			url : "${pageContext.request.contextPath}/system/user/"+oprtrId+"/delete.ajax",
    			success : function(result) {
    				var resultCode = result.code;
    				var resultMessage = result.message;
    				if(resultCode == '200'){
    					new ModalBuilder().init().successBody("유저 정보가 삭제되었습니다.").footer(4,'확인',function(button, modal){
    						modal.close();
	    					window.location.href="${pageContext.request.contextPath}/system/user/list.do";
    					}).open();
    					modalAlertWrap();    						
    				}else{
    					new ModalBuilder().init().alertBoby("유저 정보 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();    					
    				}
	  			}
   			});
    	})
    	
    	/* 비밀번호변경 */
    	var pswdChKVal = false;
        $('.pwchange').on("click", function(){
        	var dataChkVal = $(this).attr("data-chk-value");
        	if(dataChkVal == 'Y'){
	        	$(".group_update_pswd").removeClass("none");
	        	$(this).val("변경취소");
	        	$(this).attr("data-chk-value","N");
	        	pswdChKVal = false;
        	}else{
        		$("input[type='password']").val("");
	        	$(".group_update_pswd").addClass("none");
	        	$(this).val("변경하기");
	        	$(this).attr("data-chk-value","Y");
        	}
        	
        });
    
		/* modal start */
    	/* 행정기관찾기 */
        $('.administrative_btn').on("click", function(){
            new ModalBuilder().init('행정기관 찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/administrative/list.do").footer(1,'확인',function(button, modal){
            	var modalCdId = $("input[name=modalCdId]:checked").val();
            	var modalCdNm = $("input[name=modalCdId]:checked").attr("data-cdnm");
				$("#addngCd").val(modalCdId);
				$("#addngCdNm").text(modalCdNm);
				
            	modal.close();
            }).open();
            $('.modal_ex_txt').removeClass('none');
        	$(".modal_input_srbtn").on('click', function(){
        		$('.modal_ex_txt').addClass('none');
        		$('.modal_table_container').removeClass('none');
        		$('.modal_footer').removeClass('none');
        	})
        });
        /* 그룹목록 */
		$('.group_search_btn').on('click', function(){
			new ModalBuilder().init('그룹 목록').ajaxBody("${pageContext.request.contextPath}/common/modal/group/list.do").footer(1,'확인',function(button, modal){
				var modalGrpId = $("input[name='modalGrpId']:checked").val();
				var modalGrpNm = $("input[name='modalGrpId']:checked").attr("data-grpnm");
				$("#grpNm").text(modalGrpNm);
				$("#grpId").val(modalGrpId);
				
				modal.close();
			}).open();
		})
    	
        $("#oprtrPswd").on("change",function(){
        	pswdChKVal = false;
        })
        
		$("#oprtrPswdChk").on("click",function(){
			var oprtrPswd = $("#oprtrPswd").val();
			var oprtrEmail = $("#oprtrEmail").val();
			
			if(isNull(oprtrPswd) || oprtrPswd == ''){
				new ModalBuilder().init().alertBoby("기존 비밀번호를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();				
				return false;
			}
			$.ajax({
    			type : "post",
    			data : {"oprtrPswd" : oprtrPswd, "oprtrEmail" : oprtrEmail},
    			url : "${pageContext.request.contextPath}/system/user/pswd/check.ajax",
    			success : function(result) {
    				var resultCode = result.code;
    				var resultMessage = result.message;
    				if(resultCode == '200'){
    					new ModalBuilder().init().successBody("기존 비밀번호가 확인되었습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    					pswdChKVal = true;
    				}else{
    					new ModalBuilder().init().alertBoby("기존 비밀번호가 일치하지않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();    					
    					pswdChKVal = false;
    					
    				}
	  			}
   			});
		})
		
		$("#updatePswd").on("click",function(){
			if(!pswdChKVal){
				new ModalBuilder().init().alertBoby("기존 비밀번호 확인 후 변경해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap(); 
				return false;
			}
			
			var newOprtrPswd = $("#newOprtrPswd").val();
			var newOprtrPswdChk = $("#newOprtrPswdChk").val();
			
			//비밀번호 일치여부 체크
			if(newOprtrPswd == newOprtrPswdChk){
				// 새 비밀번호 validation 체크
				let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,32}$/;
				if(!passwordRegExp.test(newOprtrPswd)) {
					new ModalBuilder().init().alertBoby("비밀번호 값이 올바르지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();					
					$("#newOprtrPswd").focus();
					return false;
				}
				var oprtrId = $("#oprtrId").val();
				
				$.ajax({
	    			type : "post",
	    			data : {"oprtrId" : oprtrId, "oprtrPswd" : newOprtrPswd},
	    			url : "${pageContext.request.contextPath}/system/user/pswd/update.ajax",
	    			success : function(result) {
	    				var resultCode = result.code;
	    				var resultMessage = result.message;
	    				if(resultCode == '200'){
    						new ModalBuilder().init().successBody("비밀번호가 변경되었습니다.").footer(4,'확인',function(button, modal){
    							modal.close();
	    						window.location.reload();
    						}).open();
    						modalAlertWrap();    						
	    				}else{
	    					new ModalBuilder().init().alertBoby("비밀번호 변경을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
	    				}
		  			}
	   			});
			} else {
				new ModalBuilder().init().alertBoby("비밀번호가 불일치합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();				
			}
		})
    </script>

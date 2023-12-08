<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="skip" class="center">
	<a href="${pageContext.request.contextPath}/index.html">메인페이지 바로가기</a>
</div>
    <section class="login_container">
        <div class="login_wrap w446">
            <div class="login_box">
                <h1 class="login_logo_box login_title">비밀번호 재설정</h1>
                <div class="login_tip">
                    비밀번호를 변경해주세요.<br>
                    <strong class="login_tip_txt">비밀번호는 N자리 이상 1개 이상의 특수문자</strong>를 포함해야 합니다. <br>
                	${oprtrEmail} 계정의 비밀번호를 재설정 해주세요.
                </div>
                <form action="/" method="GET" class="login_form" id="userChngPwForm">
                	<input type="hidden" id="oprtrTel" name="oprtrTel" value="${oprtrTel}" />
                	<input type="hidden" id="oprtrNm" name="oprtrNm" value="${oprtrNm}"/>
                	<input type="hidden" id="oprtrId" name="oprtrId" value="${oprtrId}"/>
                    <div class="input_wrap">
                        <input type="password" class="input_item chkPw data-validate" id="oprtrPswd" name="oprtrPswd" onkeyup="isPwValidated(this)" required
                        	data-valid-name="새 비밀번호" data-valid-required maxlength="32"/>
                        <label class="input_label">새 비밀번호</label>
                        <span class="input_line"></span>
                        <p class="error_message"></p>
                        <button type="button" class="eye_img"><img src="../statics/images/eye_off.png" alt="눈"></button>
                    </div>
                    <div class="input_wrap input_margin">
                        <input type="password" class="input_item chkNewPw data-validate" id="oprtrPswdChk" name="oprtrPswdChk" onkeyup="isPwValidated(this)" required
                        	data-valid-name="새 비밀번호 확인" data-valid-required maxlength="32"/>
                        <label class="input_label">새 비밀번호 확인</label>
                        <span class="input_line"></span>
                        <p class="error_message"></p>
                        <button type="button" class="eye_img"><img src="../statics/images/eye_off.png" alt="눈"></button>
                    </div>
                    <button type="button" id="resetPwBtn" class="is-button password_btn">변경 완료</button>
                    <div class="login_bottom_item">
                        <div class="pw_finde">
                            <a href="${pageContext.request.contextPath}/login.do" class="is-link-btn">로그인</a>
                        </div>
                        <div class="admin">
                            <a href="${pageContext.request.contextPath}/joinUs.do" class="is-link-btn">관리자 등록 신청</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    
    <script>
    	$("#resetPwBtn").on('click',function(){
    		if($("#userChngPwForm").soValid()){			
	    		var oprtrPswd = $("#oprtrPswd").val();
	    		var oprtrPswdChk = $("#oprtrPswdChk").val();
	    		var oprtrTel = $("#oprtrTel").val();
	    		var oprtrNm = $("#oprtrNm").val();
	    		var oprtrId = $("#oprtrId").val();
	    		
	    		var obj = new Object();
	    		obj.oprtrPswd = oprtrPswd;
	    		obj.oprtrPswdChk = oprtrPswdChk;
	    		obj.oprtrTel = oprtrTel;
	    		obj.oprtrNm = oprtrNm;
	    		obj.oprtrId = oprtrId;
	    		
	    		$.ajax({
	    			type : "post",
	    			data : JSON.stringify(obj),
	    			dataType : "json",
	    			contentType: "application/json; charset=UTF-8",
	    			url : "${pageContext.request.contextPath}/operator/update/pswd.ajax",
	    			success : function(data) {
	    				if(data.code == '200'){
	    					new ModalBuilder().init().successBody("비밀번호가 재설정 되었습니다.<br>로그인 페이지로 이동됩니다.").footer(4,'확인',function(button, modal){
	    						modal.close();
		    					location.href = "${pageContext.request.contextPath}"+data.successUrl;
	    					}).open();
	    					modalAlertWrap();	    					
	    				} else {
	    					new ModalBuilder().init().alertBoby("비밀번호 재설정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
	    				}
	    			}
	    		});
    		}
    	});
    </script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="skip" class="center">
	<a href="${pageContext.request.contextPath}/index.html">메인페이지 바로가기</a>
</div>
    <section class="login_container">
        <div class="login_wrap w446">
            <div class="login_box">
                <h1 class="login_logo_box login_title">비밀번호 찾기</h1>
                <div class="login_tip">
                    아이디와 이름을 입력해주세요.<br>
                    <strong class="login_tip_txt">등록된 사용자 확인 후 본인인증</strong>을 통해 비밀번호를 재설정할 수 있습니다.
                </div>
                <form id="findPwForm" name="findPwForm" method="POST" class="login_form">
<!--                     <div class="input_wrap"> -->
<!--                         <input type="text" class="input_item" id="oprtrEmail" name="oprtrEmail" onkeyup="isEmailValidated(this)" required> -->
<!--                         <label class="input_label">이메일</label> -->
<!--                         <span class="input_line"></span> -->
<!--                         <p class="error_message"></p> -->
<!--                     </div> -->
<!--                     <div class="input_wrap input_margin"> -->
<!--                         <input type="text" class="input_item" id="oprtrNm" name="oprtrNm" onkeyup="isNameValidated(this)" required>  -->
<!--                         <label class="input_label">이름</label> -->
<!--                         <span class="input_line"></span> -->
<!--                         <p class="error_message"></p> -->
<!--                     </div> -->
                    <button type="button" id="findPwBtn" class="is-button password_btn">본인인증 후 비밀번호 재설정 </button>
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
    	$("#findPwBtn").on('click',function(){
    		
    		//TODO::본인인증로직 Success function
    		
    		
    		var oprtrTel = "01034621961";
    		var oprtrNm = "김남규";
    		
    		$.ajax({
    			type : "get",
    			dataType : "json",
    			data : {
    				"oprtrTel" : oprtrTel,
    				"oprtrNm"	 : oprtrNm
    			},
    			url : "${pageContext.request.contextPath}/operator/deatil/pswd.ajax",
    			success : function(result) {
    				if(result.code == '200'){
    					new ModalBuilder().init().successBody("본인 인증이 완료 되었습니다.<br>등록하신 이메일 목록 화면으로 이동 됩니다.").footer(4,'확인',function(button, modal){
    						modal.close();
	    					window.location.href = "${pageContext.request.contextPath}"+result.successUrl+"?oprtrNm="+result.data.oprtrNm+"&oprtrTel="+result.data.oprtrTel;
    					}).open();
    					modalAlertWrap();
    				} else {
    					new ModalBuilder().init().alertBoby("본인 인증에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				}
    			}
    		});
    	});
    </script>
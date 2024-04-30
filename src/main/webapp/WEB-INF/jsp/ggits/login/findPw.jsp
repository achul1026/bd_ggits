<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="skip" class="center">
	<a href="${pageContext.request.contextPath}/index.html">메인페이지 바로가기</a>
</div>
    <section class="login_container">
        <div class="login_wrap w446">
            <div class="login_box">
                <h1 class="login_logo_box login_title">비밀번호 찾기</h1>
                <div class="login_tip">
                    
                    <strong class="login_tip_txt">본인인증 후 등록된 사용자 확인</strong>을 통해 비밀번호를 재설정할 수 있습니다.
                </div>
                <form id="findPwForm" name="findPwForm" method="POST" class="login_form">
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
    		
    		$.ajax({
				url : "${pageContext.request.contextPath}/kg/base/info.ajax",
				success : function(result) {
					identityManager.identityProcess({
						cashGb : result.data.CASH_GB,
						siteUrl : result.data.SITE_URL,
						tradeId : result.data.TRADE_ID,
						ciSvcid : result.data.CI_SVCID,
						ciMode : result.data.CI_MODE,
						okUrl : result.data.OK_URL,
						cryptYn : result.data.CRYPT_YN,
						keygb : result.data.KEYGB,
						callbackFunc : function(data) {
							// 결제정보 조회, 성공여부 체크
							var resultCode = data.resultCode; // 결제처리 결과코드
							var resultMsg = data.resultMsg; // 결제처리 결과코드
							// 결제성공
							if (resultCode == "0000") {
								
								var oprtrTel = data.tel;
					    		var oprtrNm = data.userName;
					    		
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
							} else {
								new ModalBuilder().init().alertBoby(resultMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
								modalAlertWrap();								
								return false;
							}
						}
					});
				}
			});
    	});
    </script>
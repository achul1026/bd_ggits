<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@commonProperties['ggits.mode']" var="ggitsMode"/>
<section class="login_container">
	<div class="login_wrap w446">
		<div class="login_box">
			<h1 class="login_logo_box">
				<a href="/">
				<img src="${pageContext.request.contextPath}/statics/images/logo.png" alt="경기도 교통 빅데이터 시스템"></a> 
				<span class="login_logo_title">경기도 교통 빅데이터 시스템</span>
			</h1>
			<div class="input_wrap">
				<input type="text" class="input_item" id="loginEmail" onkeyup="isEmailValidated(this)" required maxlength="40"> 
				<label class="input_label">이메일 아이디</label> <span class="input_line"></span>
				<p class="error_message"></p>
			</div>
			<div class="input_wrap input_margin">
				<input type="password" class="input_item" id="loginPwd" onkeyup="isPwValidated(this)" required maxlength="32"> 
				<label class="input_label">비밀번호</label> <span class="input_line"></span>
				<p class="error_message"></p>
				<button type="button" class="eye_img">
					<img src="${pageContext.request.contextPath}/statics/images/eye_off.png" alt="눈">
				</button>
			</div>
			<div class="check_box">
				<input type="checkbox" id="info"> 
				<label for="info">아이디 저장</label>
			</div>
			<button type="button" class="is-button login_btn" id="loginBtn">로그인</button>
			<div class="login_bottom_item">
				<div class="pw_finde">
					<a href="${pageContext.request.contextPath}/findPw.do" class="is-link-btn">비밀번호 찾기</a>
				</div>
				<div class="admin">
					<a href="${pageContext.request.contextPath}/joinUs.do"
						class="is-link-btn">관리자 등록 신청</a>
				</div>
			</div>
		</div>
	</div>
</section>
<button type="button" id="bkdoorBtn" style="position:fixed;left:0;top:0;width:50px;height:50px;z-index:10"></button>
<form action="${pageContext.request.contextPath}/login.do" id="loginForm" name="loginForm" method="POST">
	<input type="hidden" class="data-validate" id="oprtrEmail" name="oprtrEmail" data-valid-required data-valid-email data-valid-name="이메일 아이디"/>
	<input type="hidden" class="data-validate" id="oprtrPswd" name="oprtrPswd" data-valid-required data-valid-password data-valid-name="비밀번호"/>
	<input type="hidden" id="oprtrNm" name="oprtrNm" />
	<input type="hidden" id="oprtrTel" name="oprtrTel" />
	<input type="hidden" id="loginType" name="loginType" />
</form>
<script type="text/javascript">
	$(document).ready(function(){
		
		if(sessionStorage.getItem('email') != null){
			$("#loginEmail").val(sessionStorage.getItem('email'));
			$("#info").prop("checked", true);
		}
		
		var loginFailMessage = "${loginFailMessage}";
		if(loginFailMessage != null && loginFailMessage != '' && loginFailMessage != undefined){
			new ModalBuilder().init().alertBoby(loginFailMessage).footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
		}

		$("#bkdoorBtn").on("click",function(){
			var loginEmail = $("#loginEmail").val();
			var loginPwd = $("#loginPwd").val();
			var ggitsMode = '${ggitsMode}';

			$("#oprtrEmail").val(loginEmail);
			$("#oprtrPswd").val(loginPwd);

			// 데이터 유효성 검사
			if($("#loginForm").soValid()){
				$.ajax({
					type : "post",
					data : $("#loginForm").serialize(),
					url : "${pageContext.request.contextPath}/login/user/detail.ajax",
					beforeSend : function(){
						startLoading();
					},
					error : function(){
						endLoading();
					},
					success : function(loginResult) {
						endLoading();
						var resultCode = loginResult.code;
						if(resultCode == '200'){

							var oprtrNm = loginResult.data.mOpOperatorInfo.oprtrNm;
							var oprtrTel = loginResult.data.mOpOperatorInfo.oprtrTel;

							$("#oprtrNm").val(oprtrNm);
							$("#oprtrTel").val(oprtrTel);
							$("#loginType").val("ULC001");
							loginPrc();
						}else{
							new ModalBuilder().init().alertBoby("로그인 정보가 존재하지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
							return false;
						}
					}
				});
			}
		});


		$("#loginBtn").on("click",function(){
			var loginEmail = $("#loginEmail").val();
			var loginPwd = $("#loginPwd").val();
			var ggitsMode = '${ggitsMode}';
			
			$("#oprtrEmail").val(loginEmail);
			$("#oprtrPswd").val(loginPwd);
			
			// 데이터 유효성 검사
			if($("#loginForm").soValid()){
				$.ajax({
					type : "post",
					data : $("#loginForm").serialize(),
					url : "${pageContext.request.contextPath}/login/user/detail.ajax",
					beforeSend : function(){
						startLoading();
					},
					error : function(){
						endLoading();
					},
					success : function(loginResult) {
						endLoading();
						var resultCode = loginResult.code;
						if(resultCode == '200'){
							
							var oprtrNm = loginResult.data.mOpOperatorInfo.oprtrNm;
							var oprtrTel = loginResult.data.mOpOperatorInfo.oprtrTel;
							
							$("#oprtrNm").val(oprtrNm);
							$("#oprtrTel").val(oprtrTel);
							
							if(ggitsMode === 'prd'){
								$("#loginType").val("ULC005");
								identityPrc(oprtrNm,oprtrTel);
							}else{
								$("#loginType").val("ULC001");
								loginPrc();
							}			
						}else{
							new ModalBuilder().init().alertBoby("로그인 정보가 존재하지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();			    										
							return false;
						}
					}
				});
				
				
				
			}
		})
	})
	
	$("#info").on("change", function(){
		if($(this).is(":checked")){
			sessionStorage.setItem("email", $("#loginEmail").val());
		}else{
			sessionStorage.removeItem("email");
		}
	})
	
	
	function loginPrc(){
		$.ajax({
			type : "post",
			data : $("#loginForm").serialize(),
			url : "${pageContext.request.contextPath}/login.ajax",
			success : function(loginResult) {
				var resultCode = loginResult.code;
				var resultMessage = loginResult.message;
				var resultSuccessUrl = loginResult.successUrl;
				if(resultCode == '200'){
					window.location.href="${pageContext.request.contextPath}"+resultSuccessUrl;
// 					window.location.href="${pageContext.request.contextPath}/intro.do";
				}else{
					new ModalBuilder().init().alertBoby(resultMessage).footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	}
	
	function identityPrc(loginUserName,loginUserTel){
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
						// 본인인증정보 조회, 성공여부 체크
						var resultCode = data.resultCode; // 본인인증처리 결과코드
						var resultMsg = data.resultMsg; // 본인인증처리 결과코드
						
						var identityUserName = data.userName;
						var identityTel = data.tel;
						// 본인인증성공
						if (resultCode == "0000") {
							//로그인 AJAX
							//로그인 이름 /전화번호 - > 본인인증 이름/ 전화번호 비교
							if((loginUserName == identityUserName) && (loginUserTel == identityTel)){
								new ModalBuilder().init().alertBoby("본인인증이 완료 되었습니다.").footer(4,'확인',function(button, modal){
									modal.close();
									loginPrc();
								}).open();
								modalAlertWrap();
							}else{
								new ModalBuilder().init().alertBoby("로그인 정보와 본인인증 정보가 일치하지 않습니다.").footer(4,'확인',function(button, modal){
									modal.close();
								}).open();
								modalAlertWrap();
								return false;
							}
						} else {
							new ModalBuilder().init().alertBoby(resultMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();			    										
							return false;
						}
					}
				});
			}
		});
	}
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<form action="${pageContext.request.contextPath}/login.do" id="loginForm" name="loginForm" method="POST">
	<input type="hidden" class="data-validate" id="oprtrEmail" name="oprtrEmail" data-valid-required data-valid-email data-valid-name="이메일 아이디"/>
	<input type="hidden" class="data-validate" id="oprtrPswd" name="oprtrPswd" data-valid-required data-valid-password data-valid-name="비밀번호"/>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		
		if(sessionStorage.getItem('email') != null){
			$("#loginEmail").val(sessionStorage.getItem('email'));
			$("#info").prop("checked", true);
		}
		
		var loginFailMessage = "${loginFailMessage}";
		if(loginFailMessage != null && loginFailMessage != '' && loginFailMessage != undefined){
			alert(loginFailMessage)
		}
		
		$("#loginBtn").on("click",function(){
			var loginEmail = $("#loginEmail").val();
			var loginPwd = $("#loginPwd").val();
			
			$("#oprtrEmail").val(loginEmail);
			$("#oprtrPswd").val(loginPwd);
			
			// 데이터 유효성 검사
			if($("#loginForm").soValid()){
				$.ajax({
	    			type : "post",
	    			data : $("#loginForm").serialize(),
	    			url : "${pageContext.request.contextPath}/login.ajax",
	    			success : function(data) {
	    				var resultCode = data.code;
	    				var resultMessage = data.message;
	    				
	    				if(resultCode == '0000'){
	   						window.location.href="${pageContext.request.contextPath}/monitoring.do";
	    				}else{
	    					new ModalBuilder().init().alertBoby("이메일 또는 비밀번호를 확인해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
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
</script>
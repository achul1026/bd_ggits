<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="login_container">
	<div class="login_wrap w876 admin_wrap">
		<div class="admin_box">
			<h1 class="login_logo_box login_title">최상위 관리자 등록</h1>
			<form class="login_form" id="adminJoinUsForm" name="adminJoinUsForm" method="POST">
			<div class="admin_flexbox">
				<div class="admin_item_box">
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrEmail" onkeyup="isEmailValidated(this)" data-valid-name="이메일" data-valid-required data-valid-email required> 
						<label class="input_label">이메일 (필수)</label> <span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrNm" onkeyup="isNameValidated(this)" data-valid-name="이름" data-valid-required required> 
						<label class="input_label">이름 (필수)</label> 
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="password" class="input_item data-validate" name="oprtrPswd" onkeyup="isPwValidated(this)" data-valid-name="비밀번호" data-valid-required data-valid-password required> 
						<label class="input_label">비밀번호 (필수)</label> <span class="input_line"></span>
						<p class="error_message"></p>
						<button type="button" class="eye_img">
							<img src="${pageContext.request.contextPath}/statics/images/eye_off.png" alt="눈">
						</button>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrTel" onkeyup="isPhoneValidated(this)" data-valid-name="연락처" data-valid-required data-valid-phone required> 
						<label class="input_label">연락처 (필수)</label>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
				</div>
				<div class="admin_item_box">
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item" name="addngCd" required>
						<label class="input_label">소속 행정기관</label>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item" name="oprtrDept" required>
						<label class="input_label">부서명</label> 
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item" name="oprtrJbttl" required>
						<label class="input_label">직급</label> 
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
				</div>
			</div>
			<div class="admin_check_wrap">
			</div>
			</form>
			<input type="button" class="is-button admin_btn" id="joinUsBtn" value="등록 하기">
			<div class="login_now_box">
				<a href="${pageContext.request.contextPath}/login.do" class="login_now">로그인</a>
			</div>
		</div>
	</div>
</section>


<script type="text/javascript">
	$(document).ready(function(){
		
        function pageMove(num){
            const pageNumber = $('.modal_page_wrap .pagination li a')
            $(pageNumber).off().on('click', function(){
                $(this).closest('.pagination').find('li').find('a').removeClass("active");
                $(this).addClass("active");
                $('.modal_table').remove();
                $('.modal_page_wrap').prepend(tableView(dataNum,title, arr,num));
            })
        }
        
        $(".joinChkBox").on("click",function(){
        	var isChecked = $(this).is(":checked");
        	if(isChecked){
				$(this).val("Y");        		
        	}else{
        		$(this).val("N");
        	}
        })
        
        $(".terms_btn").on("click", function(){
            new ModalBuilder().init('이용약관').body('이용약관 내용').open();
        })
        $(".information_btn").on("click", function(){
            new ModalBuilder().init('개인정보 처리방침').body('개인정보 처리방침 내용').open();
        })
        
		$("#joinUsBtn").on("click",function(){
			// 데이터 유효성 검사
			if(!$("#joinUsForm").soValid()){
				return false;
			}
			
			$.ajax({
    			type : "post",
    			data : $("#adminJoinUsForm").serialize(),
    			url : "${pageContext.request.contextPath}/admin/operator/save.ajax",
    			success : function(data) {
    				var resultCode = data.code;
    				var resultMessage = data.message;
    				
    				if(resultCode == '200'){
    					new ModalBuilder().init().successBody("등록 신청이 완료되었습니다.<br>관리자의 등록 승인이 완료되면 입력하신 이메일 주소로 알림 메시지가 전달됩니다.").footer(5,'확인',function(button, modal){
    						window.location.href="${pageContext.request.contextPath}/login.do";
    					 },'취소',function(button, modal){}).open();	    	
    				}else{
    					new ModalBuilder().init().alertBoby("등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				}
    			}
    		});
		})
		
	})
	
</script>
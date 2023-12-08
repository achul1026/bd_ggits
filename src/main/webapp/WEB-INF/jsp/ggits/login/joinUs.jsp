<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="skip" class="center">
	<a href="${pageContext.request.contextPath}/index.html">메인페이지 바로가기</a>
</div>
<section class="login_container">
	<div class="login_wrap w876 admin_wrap">
		<div class="admin_box">
			<h1 class="login_logo_box login_title">관리자 등록 신청</h1>
			<form class="login_form" id="joinUsForm" name="joinUsForm" method="POST">
			<div class="admin_flexbox">
				<div class="admin_item_box">
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrEmail" onkeyup="isEmailValidated(this)" data-valid-name="이메일" data-valid-maximum="40" data-valid-required data-valid-email required> 
						<label class="input_label">이메일 (필수)</label> <span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrNm" onkeyup="isNameValidated(this)" data-valid-name="이름" data-valid-maximum="4" data-valid-required required> 
						<label class="input_label">이름 (필수)</label> 
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="password" class="input_item data-validate" name="oprtrPswd" onkeyup="isPwValidated(this)" data-valid-name="비밀번호" data-valid-maximum="32" data-valid-required data-valid-password required> 
						<label class="input_label">비밀번호 (필수)</label> <span class="input_line"></span>
						<p class="error_message"></p>
						<button type="button" class="eye_img">
							<img src="${pageContext.request.contextPath}/statics/images/eye_off.png" alt="눈">
						</button>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrTel" onkeyup="isPhoneValidated(this)" data-valid-name="연락처" data-valid-required data-valid-phone required maxlength="13" > 
						<label class="input_label">연락처 (필수)</label>
						<button type="button" class="admin_search" onclick="identityVerification()">인증하기</button>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
				</div>
				<div class="admin_item_box">
					<div class="input_wrap admin_input_wrap">
						<input type="hidden" class="data-validate" id="addngCd" name="addngCd" data-valid-name="소속행정기관" data-valid-required>
						<div class="input_label">소속 행정기관</div>
	                    <div class="input_item" id="addngCdNm"></div>
						<button type="button" class="admin_search administrative_btn">찾기</button>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="check_box mt8" >
						<div>
							<input type="checkbox" class="joinChkBox" id="upperOprtrAuthGrpYn" name="upperOprtrAuthGrpYn"> 
							<label for="upperOprtrAuthGrpYn">해당 기관의 상위 관리자입니다.</label>
						</div>
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
				<div class="check_box admin_check_box">
					<div class="admin_check_item">
						<input type="checkbox" class="joinChkBox data-validate" id="info_check1" name="useStplatAgre"
							data-valid-name="이용약관 동의" data-valid-required/> 
						<label for="info_check1">이용약관 동의</label>
					</div>
					<button type="button" class="admin_read_more terms_btn">자세히보기</button>
				</div>
				<div class="check_box admin_check_box">
					<div class="admin_check_item">
						<input type="checkbox" class="joinChkBox data-validate" id="info_check2" name="prvcMngPolcyAgre"
							data-valid-name="개인정보 처리방침 동의" data-valid-required/> 
						<label for="info_check2">개인정보 처리방침 동의</label>
					</div>
					<button type="button" class="admin_read_more information_btn">자세히보기</button>
				</div>
			</div>
			</form>
			<input type="button" class="is-button admin_btn" id="joinUsBtn" value="등록 신청 하기">
			<div class="login_now_box">
				<a href="${pageContext.request.contextPath}/login.do" class="login_now">로그인</a>
			</div>
		</div>
	</div>
	<form id="identityForm" name="identityForm">
		<input type="hidden" id="CASH_GB" name="CASH_GB" />
		<input type="hidden" id="PAY_MODE" name="PAY_MODE" value="10"/>
		<input type="hidden" id="Siteurl" name="Siteurl" />
		<input type="hidden" id="Tradeid" name="Tradeid" />
		<input type="hidden" id="CI_SVCID" name="CI_SVCID" />
		<input type="hidden" id="CI_Mode" name="CI_Mode" />
		<input type="hidden" id="Okurl" name="Okurl" value=""/>
		<input type="hidden" id="Notiurl" name="Notiurl" />
		<input type="hidden" id="Cryptyn" name="Cryptyn"/>
		<input type="hidden" id="Keygb" name="Keygb" />
		<input type="hidden" id="CALL_TYPE" name="CALL_TYPE" value="P" />
		
	</form>
</section>


<script type="text/javascript">
	$(document).ready(function(){
		
		/* modal start */
		/* 행정기관 */
        $('.administrative_btn').on("click", function(){
            new ModalBuilder().init('행정기관 찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/join/administrative/list.do").footer(1,'확인',function(button, modal){
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
			if($("#joinUsForm").soValid()){
				$.ajax({
	    			type : "post",
	    			data : $("#joinUsForm").serialize(),
	    			url : "${pageContext.request.contextPath}/operator/save.ajax",
	    			success : function(data) {
	    				var resultCode = data.code;
	    				var resultMessage = data.message;
	    				
	    				if(resultCode == '200'){
	    					new ModalBuilder().init().successBody("등록 신청이 완료되었습니다.<br>관리자의 등록 승인이 완료되면 입력하신 이메일 주소로 알림 메시지가 전달됩니다.").footer(5,'확인',function(button, modal){
	    						window.location.href="${pageContext.request.contextPath}/login.do";
	    					 },'취소',function(button, modal){}).open();	    					
	    				}else{
	    					new ModalBuilder().init().alertBoby("등록 신청을 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
	    				}
	    			}
	    		});
			
			}
		})
		
	})
	
	//본인인증
	function identityVerification(){
		$.ajax({
			url : "${pageContext.request.contextPath}/kg/base/info.ajax",
			success : function(result) {
				
				$("#CASH_GB").val(result.data.CASH_GB);
				$("#PAY_MODE").val(result.data.PAY_MODE);
				$("#Tradeid").val(result.data.TRADE_ID);
				$("#CI_SVCID").val(result.data.CI_SVCID);
				$("#CI_Mode").val(result.data.CI_MODE);
				$("#Siteurl").val(result.data.SITE_URL);
				$("#Okurl").val(result.data.OK_URL);
				$("#Cryptyn").val(result.data.CRYPT_YN);
				$("#Keygb").val(result.data.KEYGB);
				
				//아래와 같이 ext_inc_comm.js에 선언되어 있는 함수를 호출
				IDENTITY_VERIFICATION(document.identityForm);			
			}
		});
	}
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:eval expression="@commonProperties['ggits.mode']" var="ggitsMode"/>
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
						<label class="input_label">이메일 (필수)<span class="required-alert">*</span></label> <span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="password" class="input_item data-validate input-admin-eye" name="oprtrPswd" onkeyup="isPwValidated(this)" data-valid-name="비밀번호" data-valid-maximum="32" data-valid-required data-valid-password required> 
						<label class="input_label">비밀번호 (필수)<span class="required-alert">*</span></label> <span class="input_line"></span>
						<p class="error_message"></p>
						<button type="button" class="eye_img">
							<img src="${pageContext.request.contextPath}/statics/images/eye_off.png" alt="눈">
						</button>
					</div>
					<div class="input_wrap admin_input_wrap certification">
						<div class="certification_text">
							본인인증을 진행해주세요
						</div>
						<div class="certification_btn">
							<button type="button" class="" onclick="identityVerification()">휴대폰 인증</button>
						</div>
					</div>
					
					  <div class="input_wrap admin_input_wrap certification_after">
						<input type="text" class="input_item data-validate" name="oprtrNm" ${ggitsMode eq 'prd' ? 'readonly="readonly"' : 'onkeyup="isNameValidated(this)"' } data-valid-name="이름" data-valid-maximum="4" data-valid-required required /> 
						<label class="input_label identityLabel">이름 (필수)</label> 
						<c:if test="${ggitsMode eq 'prd'}">
							<button type="button" class="admin_search" onclick="identityVerification()">인증하기</button>
						</c:if>
					</div>
					<div class="input_wrap admin_input_wrap certification_after">
						<input type="text" class="input_item data-validate" name="oprtrTel" ${ggitsMode eq 'prd' ? 'readonly="readonly"' : 'onkeyup="isPhoneValidated(this)"' } data-valid-name="연락처" data-valid-required data-valid-phone required maxlength="13"> 
						<label class="input_label identityLabel">연락처 (필수)</label>
					</div>
					
					<%-- <div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrNm" ${ggitsMode eq 'prd' ? 'readonly="readonly"' : 'onkeyup="isNameValidated(this)"' } data-valid-name="이름" data-valid-maximum="4" data-valid-required required /> 
						<label class="input_label identityLabel">이름 (필수)</label> 
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="text" class="input_item data-validate" name="oprtrTel" ${ggitsMode eq 'prd' ? 'readonly="readonly"' : 'onkeyup="isPhoneValidated(this)"' } data-valid-name="연락처" data-valid-required data-valid-phone required maxlength="13"> 
						<label class="input_label identityLabel">연락처 (필수)</label>
						<c:if test="${ggitsMode eq 'prd'}">
							<button type="button" class="admin_search" onclick="identityVerification()">인증하기</button>
						</c:if>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div> --%>
				</div>
				<div class="admin_item_box">
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
					<div class="input_wrap admin_input_wrap">
						<input type="hidden" class="data-validate" id="addngCd" name="addngCd" data-valid-name="행정동" data-valid-required>
						<div class="input_label addng_label">행정구역</div>
	                    <div class="input_item" id="addngCdNm"></div>
						<button type="button" class="admin_search addng_btn">찾기</button>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="input_wrap admin_input_wrap">
						<input type="hidden" class="data-validate" id="mngInstCd" name="mngInstCd" data-valid-name="소속행정기관" data-valid-required>
						<div class="input_label inst_label">소속 행정기관</div>
	                    <div class="input_item" id="addMngInstCd"></div>
						<button type="button" class="admin_search administrative_btn">찾기</button>
						<span class="input_line"></span>
						<p class="error_message"></p>
					</div>
					<div class="check_box" >
						<div>
							<input type="checkbox" class="joinChkBox" id="upperOprtrAuthGrpYn" name="upperOprtrAuthGrpYn"> 
							<label for="upperOprtrAuthGrpYn">해당 기관의 상위 관리자입니다.</label>
						</div>
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
			<div class="btn-wrap">
				<input type="button" class="is-button is-sub-button admin_btn" id="loginBtn" value="로그인">
				<input type="button" class="is-button admin_btn" id="joinUsBtn" value="등록 신청 하기">
			</div>
			
		</div>
	</div>
</section>


<script type="text/javascript">
	var isIdentityChk = false;

	$(document).ready(function(){
		
		/* modal start */
		/* 행정기관 */
        $('.administrative_btn').on("click", function(){
            new ModalBuilder().init('행정기관 찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/join/administrative/inst/list.do").footer(1,'확인',function(button, modal){
            	var modalCdId = $("input[name=modalCdId]:checked").val();
            	var modalCdNm = $("input[name=modalCdId]:checked").attr("data-cdnm");
				$("#mngInstCd").val(modalCdId);
				$("#addMngInstCd").text(modalCdNm);
				
            	modal.close();
            }).open();
            $('.modal_ex_txt').removeClass('none');
        	$(".modal_input_srbtn").on('click', function(){
        		$('.modal_ex_txt').addClass('none');
        		$('.modal_table_container').removeClass('none');
        		$('.modal_footer').removeClass('none');
        	})
        });
        $('.addng_btn').on("click", function(){
            new ModalBuilder().init('행정동 찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/join/administrative/addng/list.do").footer(1,'확인',function(button, modal){
            	var modalCdId = $("input[name=modalCdId]:checked").val();
            	var modalCdNm = $("input[name=modalCdId]:checked").attr("data-cdnm");
				$("#addngCd").val(modalCdId);
				$("#addngCdNm").text(modalCdNm);
				$(".addng_label").addClass("input-read-only-label");				
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
			if(!isIdentityChk){
				new ModalBuilder().init().alertBoby("본인 인증을 먼저 진행해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false;
			}
			
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
	    					new ModalBuilder().init().successBody("등록 신청이 완료되었습니다.<br>관리자의 등록 승인이 완료되면 로그인이 가능합니다.").footer(5,'확인',function(button, modal){
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
	function identityVerification() {
		isIdentityChk = false;
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
							new ModalBuilder().init().alertBoby("본인인증이 완료 되었습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();							
							isIdentityChk = true;
							$("input[name='oprtrNm']").val(data.userName);
							$("input[name='oprtrTel']").val(data.tel);
							$(".identityLabel").addClass("input-read-only-label");
							$('.certification_after').show();
							$('.certification').hide();
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
	
	$("#loginBtn").on("click",function(){
		window.location.href="${pageContext.request.contextPath}/login.do";
	})
</script>
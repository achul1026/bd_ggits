<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 등록</h3>
                    <div class="side_txt">사용자를 등록할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 등록</h2>
                <form id="userSaveForm">
                	<input type="hidden" name="useStplatAgre" value="Y">
                	<input type="hidden" name="prvcMngPolcyAgre" value="Y">
	                <div class="contents_wrap">
	                    <div class="group">
	                        <div class="group_big_text">
	                            <strong>사용하시는 이메일로 아이디를 생성해 주세요.</strong>
	                            <br>
	                            관리자 등록을 신청한 후 승인이 완료되면 로그인 할 수 있습니다.
	                        </div>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">사용자 ID<span class="required-alert">*</span></div>
	                        <input type="text" placeholder="사용자 ID를 입력해 주세요." class="input_same group_box data-validate" name="oprtrEmail"
	                        	data-valid-name="사용자 ID" onkeyup="isEmailValidated(this)" data-valid-maximum="40" data-valid-required data-valid-email>
	                        <p class="error_message user_error_message"></p>	
	                    </div>
	                    <div class="group">
	                        <div class="group_text">사용자 이름<span class="required-alert">*</span></div>
	                        <input type="text" placeholder="사용자 이름을 입력해 주세요." class="input_same group_box data-validate" name="oprtrNm"
	                        	data-valid-name="사용자 이름" data-valid-maximum="4" data-valid-required/>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">비밀번호<span class="required-alert">*</span></div>
	                        <input type="password" placeholder="비밀번호를 입력해 주세요." class="input_same group_box data-validate input-eye" name="oprtrPswd" onkeyup="isPwValidated(this)"
	                        	data-valid-name="비밀번호" data-valid-maximum="32" data-valid-required data-valid-password/>
	                        <p class="error_message user_error_message"></p>
	                        <button type="button" class="eye_img system_eye_img"><img src="/statics/images/eye_off.png" alt="눈"></button>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">연락처<span class="required-alert">*</span></div>
	                        <input type="text" placeholder="연락처를 입력해 주세요." class="input_same group_box data-validate" name="oprtrTel"
	                        	data-valid-name="연락처" data-valid-required data-valid-phone maxlength="13" oninput="keyupPhoneEvent(this)"/>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">소속 행정동<span class="required-alert">*</span></div>
                        	<c:choose>
                        		<c:when test="${isUserChk and authCd eq 'AUC000' or mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
                        			<input type="hidden" class="data-validate" id="addngCd" name="addngCd" data-valid-name="행정동" data-valid-required>
                        			<div class="input_same group_box div_input_style" id="addngCdNm">소속 행정동을 선택해주세요.</div>
				                    <input type="button" class="is-darkgreen-btn addng_btn ml8" value="찾기">
                        		</c:when>
                        		<c:otherwise>
                        			<input type="hidden" class="data-validate" id="addngCd" name="addngCd" data-valid-name="행정동" data-valid-required value="<c:out value='${mOpOperatorInfo.addngCd}'/>">
                        			<div class="input_same group_box div_input_style" id="addngCdNm"><c:out value="${mOpOperatorInfo.cdNm}"></c:out></div>
                        		</c:otherwise>
                        	</c:choose>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">소속행정기관<span class="required-alert">*</span></div>
	                        <c:choose>
	                        	<c:when test="${isUserChk and authCd eq 'AUC000' or mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
			                        <input type="hidden" class="data-validate" id="mngInstCd" name="mngInstCd" data-valid-name="소속행정기관" data-valid-required>
		                        	<div class="input_same group_box div_input_style" id="addMngInstCd">소속 행정기관을 선택해주세요.</div>
		                        	<input type="button" class="is-darkgreen-btn administrative_btn ml8" value="찾기">
	                        	</c:when>
	                        	<c:otherwise>
	                        		<input type="hidden" class="data-validate" id="mngInstCd" name="mngInstCd" data-valid-name="소속행정기관" data-valid-required value="<c:out value='${mOpOperatorInfo.mngInstCd}'/>">
		                        	<div class="input_same group_box div_input_style" id="addMngInstCd"><c:out value="${mOpOperatorInfo.mngInstNm}"></c:out></div>
	                        	</c:otherwise>
	                        </c:choose>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">그룹<span class="required-alert">*</span></div>
	                        <input type="hidden" id="grpId" name="grpId" class="data-validate" data-valid-name="그룹" data-valid-required>
	                        <div class="input_same group_box div_input_style" id="grpNm">그룹을 선택해 주세요</div>
		                    <input type="button" class="is-darkgreen-btn group_search_btn ml8" value="찾기">
	                    </div>
	                    <div class="group group_search">
	                        <input type="button" class="is-darkgreen-btn" id="userSaveFormBtn" value="등록" />
	                        <a href="${pageContext.request.contextPath}/system/user/list.do" class="is-dark-btn">취소</a>
	                    </div>
	                </div>
                </form>
            </section>
        </div>
    <script type="text/javascript">
		$(document).ready(function(){
			
			$("#userSaveFormBtn").on("click",function(){
				if($("#userSaveForm").soValid()){
					$.ajax({
		    			type : "post",
		    			data : $("#userSaveForm").serialize(),
		    			url : "${pageContext.request.contextPath}/system/user/save.ajax",
		    			success : function(data) {
		    				var resultCode = data.code;
		    				if(resultCode == '200'){
		    					new ModalBuilder().init().successBody("사용자 등록이 되었습니다.").footer(5,'확인',function(button, modal){
		    						window.location.href="${pageContext.request.contextPath}/system/user/list.do";
		    					 },'취소',function(button, modal){}).open();		    					
		    				}else{
		    					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
		    					modalAlertWrap();
		    				}
		    			}
		    		});					
				}				
			})
		});

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
				$("#grpId").val(modalGrpId);
				$("#grpNm").text(modalGrpNm);
				
				modal.close();
			}).open();
			modalAlertWrap();
		})

 
    </script>

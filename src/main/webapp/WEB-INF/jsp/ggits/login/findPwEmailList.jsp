<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <section class="login_container">
        <div class="login_wrap w446">
            <div class="login_box">
                <h1 class="login_logo_box login_title">비밀번호 찾기</h1>
                <div class="login_tip">
                    이메일을 선택 후 비밀번호를 재설정할 수 있습니다.
                    <input type="hidden" id="oprtrNm" name="oprtrNm" value="<c:out value='${oprtrNm}'/>"/>
                    <input type="hidden" id="oprtrTel" name="oprtrTel" value="<c:out value='${oprtrTel}'/>"/>
                </div>
                	<c:forEach var="mOpOperatorList" items="${mOpOperatorList}">
	                    <div class="input_wrap">
	                    	<label class="login_radio_label">
		                        <input type="radio" class="login_radio" name="oprtrId" value="<c:out value='${mOpOperatorList.oprtrId}'/>">
								<c:out value='${mOpOperatorList.oprtrEmail}'/>
	                    	</label>
	                    </div>
                	</c:forEach>
                    <button type="button" class="is-button password_btn" id="findPwBtn">확인</button>
                    <div class="login_bottom_item">
                        <div class="pw_finde">
                            <a href="${pageContext.request.contextPath}/login.do" class="is-link-btn">로그인</a>
                        </div>
                        <div class="admin">
                            <a href="${pageContext.request.contextPath}/joinUs.do" class="is-link-btn">관리자 등록 신청</a>
                        </div>
                    </div>
            </div>
        </div>
    </section>
<script>

   /**
	* 해당 이메일 비밀번호 재설정
	*/
	$("#findPwBtn").on('click',function(){
		var oprtrNm = $("#oprtrNm").val();
		var oprtrTel = $("#oprtrTel").val();
		var oprtrId = $("input[name='oprtrId']:checked").val();
		if(oprtrId == null ||  oprtrId == ''){
			new ModalBuilder().init().alertBoby("이메일을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return false;
		}
		window.location.href = "${pageContext.request.contextPath}/resetPw.do?oprtrId="+oprtrId+"&oprtrNm="+oprtrNm+"&oprtrTel="+oprtrTel;
	});
</script>
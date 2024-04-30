<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">권한 상세</h3>
                <div class="side_txt">권한을 확인하거나 수정하실 수<br>있습니다.<br><br>대상자 및 권한은 언제든지<br>추가로 지정하실 수 있습니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">권한 상세</h2>
            <div class="contents_wrap mt24">
            	<form id="authUpdFrm">
	                <div class="group">
	                    <div class="group_text">권한 이름 (필수)</div>
	                    <input type="hidden" id="authId" name="authId" value="<c:out value='${mOpAuthority.authId}'/>"/>
	                    <input type="text" placeholder="권한 이름을 입력해 주세요." id="authNm" class="input_same group_box data-validate"
	                    	data-valid-name="권한 이름" data-valid-required value="<c:out value='${mOpAuthority.authNm}'/>" maxlength="50">
	                </div>
	                <div class="group">
	                    <div class="group_text">권한 설명</div>
	                    <input type="text" placeholder="권한 설명을 입력해 주세요." id="descr" class="input_same group_box" value="<c:out value='${mOpAuthority.descr}'/>"
	                    	maxlength="400">
	                </div>
	                
           	        <div class="group">
	                    <div class="group_text">권한 상세<span class="required-alert">*</span></div>
   	                    <div class="flex-center gap8">
				            <select id="authCd" class="authority_selectbox ${mOpAuthority.authCd eq 'AUC002' ? 'is-disabled':''}">
				            	<option value="AUC000" ${mOpAuthority.authCd eq 'AUC000' ? 'selected':''}>상위 권한</option>
				            	<option value="AUC001" ${mOpAuthority.authCd eq 'AUC001' ? 'selected':''}>일반 권한</option>
				            	<option value="AUC002" ${mOpAuthority.authCd eq 'AUC002' ? 'selected':''}>VIP 권한</option>
				            </select>
	                    </div>
						 <div class="flex-center gap8 ml16">
			            	<span>권한 사용 여부</span>
				            <select id="authUseYn" class="authority_selectbox">
				            	<option value="Y" ${mOpAuthority.authUseYn eq 'Y' ? 'selected':''}>사용</option>
				            	<option value="N" ${mOpAuthority.authUseYn eq 'N' ? 'selected':''}>미사용</option>
				            </select>
	                    </div>
	                </div>
            	</form>
                
                <c:if test="${mOpAuthority.authCd ne 'AUC002'}">
	                <div class="">
	                    <div class="group_text">권한 설정</div>
	                    <div class="group_authority">
		                    <c:forEach var="menuList" items="${menuList}" varStatus="upprStatus">
								<div class="authority_box"> 
									<div id="upperMenuNm" class="authority_title_box">
										<c:out value="${menuList.upperMenu.menuNm}"/>
										<input type="checkbox" id="menuId<c:out value='${menuList.upperMenu.menuId}'/>" role="switch" class="facility_input mainMenu" value="<c:out value='${menuList.upperMenu.menuId}'/>"/>
									</div>
			                        <c:choose>
			                        	<c:when test="${not empty menuList.subMenuList}">
											<div class="authority_click_bg not_click">
												<c:forEach var="subMenuList" items="${menuList.subMenuList}" varStatus="subStatus">
													<div class="authority_item">
							                            <label class="is-dark-btn authority_btn mj0">
							                            	<c:out value="${subMenuList.menuNm}"/>
															<input type="checkbox" id="menuId<c:out value='${subMenuList.menuId}'/>" class="none subMenu<c:out value="${upprStatus.index}"/>" value="<c:out value='${subMenuList.menuId}'/>"/>
							                            </label>
						                        	</div>
						                        </c:forEach>
											</div>
			                        	</c:when>
										<c:otherwise>
											<div>
												<p>하위 메뉴가 없습니다.</p>
											</div>
										</c:otherwise>
			                        </c:choose>
	
								</div>
		                    </c:forEach>
	                    </div> 
	                </div>
	             </c:if>
                <div class="group group_search">
                	<c:if test="${mOpAuthority.authCd ne 'AUC002'}">
                    	<button type="button" id="updateBtn" class="is-darkgreen-btn">수정</button>
                    </c:if>
                    <button type="button" id="deleteBtn" class="is-darkgreen-btn">삭제</button>
                    <a href="${pageContext.request.contextPath}/system/auth/list.do" class="is-dark-btn">취소</a>
                </div>
            </div>
        </section>
    </div>
</main>
<script>
	$(document).ready(function(){
		//<![CDATA[
			var authMenuArr = new Array();
			var authMenuLength = '<c:out value="${fn:length(authMenuList)}"/>';
			<c:forEach var="authMenuList" items="${authMenuList}">
				authMenuArr.push("<c:out value='${authMenuList["menuId"]}'/>");
			</c:forEach>
			
			for(var i = 0; i < authMenuLength; i++){
			let menuItem = $("#menuId"+authMenuArr[i]);
				$(menuItem).prop('checked',true)
				if($(menuItem).is(':checked') == true){
					$(menuItem).closest('.authority_box').addClass('opactiy');
					$(menuItem).parent('label').addClass('is-darkgreen-btn');
					$(menuItem).data("authYn","Y");
					if($(menuItem).hasClass('mainMenu')){
						$(menuItem).closest('.authority_box').find('.authority_click_bg').removeClass('not_click');
					} else {
						$(menuItem).closest('.authority_click_bg').removeClass('not_click');
					}
				}
			}
		//]]>

		/* label active */
	    $('.authority_btn').on("change", function(){
	    	$(this).toggleClass('is-darkgreen-btn');
	    })
		/* check 제어 start */
		$('.facility_input').on('change', function(){
			$(this).closest('.authority_box').find('.authority_btn').find('input:checkbox').prop('checked', false);
	    	$(this).closest('.authority_box').find('.authority_btn').removeClass('is-darkgreen-btn');
	    })
  	    $('.facility_input').on('click', function(){
	    	$(this).closest('.authority_box').toggleClass('opactiy');
			$(this).closest('.authority_box').find('.authority_click_bg').toggleClass('not_click');
	    })
		/* check 제어 end */
	});
	<c:if test="${mOpAuthority.authCd ne 'AUC002'}">
	$("#updateBtn").on('click',function(){
		//TO DO:: UI변경 수정 인덱스 파악 후 구현
		var authId = $("#authId").val();
		var authNm = $("#authNm").val();
		var descr = $("#descr").val();
		var authUseYn = $("#authUseYn").val();
		var authCd = $("#authCd option:selected").val();

		var obj = new Object();

		if(authCd == "" || authCd == null){
			new ModalBuilder().init().alertBoby("권한 상세를 선택해주세요.").footer(4,'확인',function(button, modal){
				modal.close();
				$("#authCd").focus();
			}).open();
			return false;
		}
		
		// 데이터 유효성 검사
		if(!$("#authUpdFrm").soValid()){
			return false;
		}
		
		//삭제 메뉴
		var deleteArr = new Array();
		
		//생성 메뉴
		var insertArr = new Array();
		
		var mainMenu = $(".mainMenu");
		for(var i = 0; i < mainMenu.length; i++){
			if($(mainMenu).eq(i).data('authYn') == 'Y'){
				//기존 에있던 메뉴는 풀려있으면 delete 안풀려있으면 그대로 유지
				if(!$(mainMenu).eq(i).is(':checked')){
					var menuId = $(mainMenu).eq(i).val();
					var subMenu = $(".subMenu"+i);
					for(var j = 0; j < subMenu.length; j++){
						if($(subMenu).eq(j).data('authYn') == 'Y'){
							var subMenuId = $(subMenu).eq(j).val();
							deleteArr.push(subMenuId);
						}
					}
					deleteArr.push(menuId);
				} else {
					//기존 메뉴의 새로운 소메뉴
					var menuId = $(mainMenu).eq(i).val();
					var subMenu = $(".subMenu"+i);
					for(var j = 0; j < subMenu.length; j++){
						var subMenuId = $(subMenu).eq(j).val();
						if($(subMenu).eq(j).is(':checked') && $(subMenu).eq(j).data('authYn') != 'Y'){
							insertArr.push(subMenuId);
						} else if(!$(subMenu).eq(j).is(':checked') && $(subMenu).eq(j).data('authYn') == 'Y'){
							deleteArr.push(subMenuId);
						}
					}
				}
			} else {
				//기존에 없던메뉴는 풀려있으면 유지 체크되어있으면 insert
				if($(mainMenu).eq(i).is(':checked')){
					var menuId = $(mainMenu).eq(i).val();
					var subMenu = $(".subMenu"+i);
					for(var j = 0; j < subMenu.length; j++){
						if($(subMenu).eq(j).is(':checked')){
							var subMenuId = $(subMenu).eq(j).val();
							insertArr.push(subMenuId);
						}
					}
					insertArr.push(menuId);
				}
			}
		}
		
// 		console.log('deleteArr::'+deleteArr);
// 		console.log('insertArr::'+insertArr);
		
		obj.authId = authId;
		obj.authNm = authNm;
		obj.descr = descr;
		obj.authUseYn = authUseYn;
		obj.authCd = authCd;
		obj.menuIdArr = insertArr;
		obj.deleteMenuIdArr = deleteArr;
		
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/system/auth/update.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("권한 수정에 성공했습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.reload();
					}).open();
					modalAlertWrap();					
				} else {
					new ModalBuilder().init().alertBoby("권한 수정을 실패했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();					
				}
			}
		});
	});
	</c:if>
	
	$("#deleteBtn").on('click',function(){
		var authId = $("#authId").val();
		
		if(authId == null || authId == ''){
			new ModalBuilder().init().alertBoby("삭제 할 권한의 정보를 찾지 못했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		new ModalBuilder().init().alertBoby("해당 권한을 삭제 하시겠습니까?").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "get",
				dataType : "json",
				url : "${pageContext.request.contextPath}/system/auth/"+authId+"/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("권한 삭제를 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href="${pageContext.request.contextPath}/system/auth/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
		 },'취소하기',function(button, modal){modal.close()}).open();				
	});
</script>

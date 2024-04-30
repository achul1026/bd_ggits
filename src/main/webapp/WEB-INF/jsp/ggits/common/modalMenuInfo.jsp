<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div>
	<div id="mainMenuWrap">
		<div class="modal_input_wrap flex-center gap16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인 메뉴명</label>
				<input type="hidden" id="mainSortNo" value="<c:out value='${mOpMenu.sortNo}'/>"/>
			    <input type="hidden" id="menuId" value="<c:out value='${mOpMenu.menuId}'/>"/>
				<input class="modal_input data-validate" type="text" id="mainMenuNm" value="<c:out value='${mOpMenu.menuNm}'/>" data-valid-name="메뉴 메뉴명" data-valid-required maxlength="50"/> 
			</div>
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인 메뉴 URL 패턴</label>
				<input class="modal_input" type="text" id="mainUrlPttrn" value="<c:out value='${mOpMenu.urlPttrn}'/>" maxlength="100" onkeyup="urlCheck(this.id)"/> 
			</div>
			
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인메뉴 URL</label>
				<input type="text" class="modal_input data-validate" id="mainUrlAddr" value="<c:out value='${mOpMenu.urlAddr}'/>" data-valid-name="메뉴 URL 주소" data-valid-required maxlength="100" onkeyup="urlCheck(this.id)"/> 
			</div>
			<div class="flex-center modal_input_box ${mOpMenu.urlAddr eq '/monitoring.do'?'none':''}">
				<div class="flex-column wd100">
					<label class="modal_input_label">노출여부</label>
					<select class="modal_input_selectbox" id="mainUseYn" name="mainUseYn">
			            <option value="Y" ${mOpMenu.useYn eq 'Y' ?'selected':''}>노출</option>
			           	<option value="N" ${mOpMenu.useYn eq 'N' ?'selected':''}>비노출</option>
		        	</select>
				</div>
			</div>
<!-- 			<div class="flex-center modal_input_box"> -->
<!-- 				<div class="flex-column wd100"> -->
<%-- 					<input type="hidden" id="cdDivCd" value="<c:out value='${mOpMenu.cdDivCd}'/>"/> --%>
<!-- 					<label class="modal_input_label">GIS 카테고리</label> -->
<!-- 			        <select id="categCd" name="categCd" class="modal_input_selectbox"> -->
<!-- 			        	<option value="">없음</option> -->
<%-- 			        	<c:forEach var="ctgryCdList"  items="${ctgryCdList}"> --%>
<%-- 			        		<option value="<c:out value='${ctgryCdList.cdId}'/>" ${mOpMenu.categCd eq ctgryCdList.cdId ? 'selected':''}><c:out value='${ctgryCdList.cdNm}'/></option> --%>
<%-- 			        	</c:forEach> --%>
<!-- 			        </select> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>
	<div class="modal_table_container mt24">
		<div>
			<div class="modal_input_sub_title">- 서브 메뉴 리스트</div>
		</div>
		<div class="modal_table_scroll">
			<table class="modal_table" style="width:56rem">
				<colgroup>
				<c:choose>
					<c:when test="${mOpMenu.urlAddr eq '/monitoring.do'}">
						<col style="width:7%">
						<col style="width:15%">
						<col style="width:15%">
						<col style="width:15%">
						<col style="width:12%">
						<col style="width:23%">
						<col style="width:23%">
					</c:when>
					<c:otherwise>
						<col style="width:7%">
						<col style="width:18%">
						<col style="width:18%">
						<col style="width:18%">
						<col style="width:16%">
						<col style="width:23%">
					</c:otherwise>
				</c:choose>
				</colgroup>
				<thead>
					<tr>
						<th class="">번호</th>
						<th class="">서브 메뉴명</th>
						<th class="">URL</th>
						<th class="">URL 패턴</th>
						<th class="">노출여부</th>
						<c:if test="${mOpMenu.urlAddr eq '/monitoring.do'}">
							<th class="">GIS 메뉴</th>
						</c:if>
						<th class="">수정</th>
					</tr>
				</thead>
				<tbody id="subMenuTbody">
					<c:choose>
						<c:when test="${not empty subMenuList}">
				            <c:forEach var="subMenuList" items="${subMenuList}" varStatus="subStatus">
								<tr id="subMenu<c:out value='${subMenuList.menuId}'/>" class="isNotActive">
									<td class=" subMenuIdx"><c:out value="${subStatus.count}"/></td>
									<td class=""><input type="text" class="modal_table_input ${mOpMenu.urlAddr eq '/monitoring.do'?'modal_system_menu_input':''}" id="subMenuNm<c:out value='${subMenuList.menuId}'/>" value="<c:out value='${subMenuList.menuNm}'/>"  data-valid-name="서브 메뉴명" data-valid-required readonly/></td>
									<td class=""><input type="text" class="modal_table_input ${mOpMenu.urlAddr eq '/monitoring.do'?'modal_system_menu_input':''}" id="subUrlAddr<c:out value='${subMenuList.menuId}'/>" value="<c:out value='${subMenuList.urlAddr}'/>" data-valid-name="서브 메뉴 URL 주소" data-valid-required readonly onkeyup="urlCheck(this.id)"/></td>
									<td class=""><input type="text" class="modal_table_input ${mOpMenu.urlAddr eq '/monitoring.do'?'modal_system_menu_input':''}" id="subUrlPttrn<c:out value='${subMenuList.menuId}'/>" value="<c:out value='${subMenuList.urlPttrn}'/>" data-valid-name="서브 메뉴 URL 패턴" data-valid-required readonly onkeyup="urlCheck(this.id)"/></td>
									<td class="">					
										<select class="modal_input_selectbox is_disabled wd90" id="subUseYn<c:out value='${subMenuList.menuId}'/>">
								            <option value="Y" <c:out value="${subMenuList.useYn eq 'Y' ?'selected':''}"/>>노출</option>
								           	<option value="N" <c:out value="${subMenuList.useYn eq 'N' ?'selected':''}"/>>비노출</option>
							        	</select>
			        				</td>
			        				<c:if test="${mOpMenu.urlAddr eq '/monitoring.do'}">
				        				<td class="">
											<select class="modal_input_selectbox is_disabled wd90" id="subCategCd<c:out value='${subMenuList.menuId}'/>">
									        	<c:forEach var="ctgryCdList"  items="${ctgryCdList}">
									        		<option value="<c:out value='${ctgryCdList.cdId}'/>" ${subMenuList.categCd eq ctgryCdList.cdId?'selected':''}><c:out value='${ctgryCdList.cdNm}'/></option>
									        	</c:forEach>
								        	</select>
				        				</td>
			        				</c:if>
									<td class="">
										<div class="">
											<div class="">
												<div class="menu_updata_box">
													<button type="button" class="ftsize12 menu_updata_btn" onclick="changeSubMenu('<c:out value="${subMenuList.menuId}"/>')">수정하기</button>
													<c:if test="${mOpMenu.urlAddr ne '/monitoring.do'}">
													/
													<button type="button" class="ftsize12" id="delSubMenuBtn" onclick="delSubMenu('<c:out value="${subMenuList.menuId}"/>')">삭제하기</button>
													</c:if>
												</div>
												<div class="menu_updata_sub_btn_box none">
													<button type="button" class="ftsize12 menu_updata_btn_item" onclick="changeSubMenu('<c:out value="${subMenuList.menuId}"/>')">취소</button>
													/
													<button type="button" class="ftsize12 menu_updata_btn_item" onclick="updateSubMenuBtn('<c:out value="${subMenuList.menuId}"/>')">저장</button>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr id="noneSubMenuTr">
								<td colspan="6" class="center">
									<p> 하위 메뉴가 존재하지 않습니다. 하위 메뉴를 생성 해주세요.</p>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<c:if test="${mOpMenu.urlAddr ne '/monitoring.do'}">
			<div class="modal_btn_wrap">
				<button type="button" class="modal_table_addbtn">추가하기</button>
			</div>
		</c:if>
	</div>
	<div id="subMenuWrap" class="modal_menubox_append mt24 none">
		<div class="modal_menubox_append-title">
			<div class="modal_input_sub_title">
				- 서브 메뉴 등록
				<span class="modal_message"> (서브 메뉴는 1개씩 추가가 가능합니다.)</span>
			</div>
		</div>
		<div class="modal_menubox_append_wrap">
			<div class="flex-center gap16">
				<div class="flex-column modal_input_box">
					<label class="modal_input_label">서브 메뉴명</label>
					<input class="modal_input data-validate" placeholder="서브메뉴 명을 입력해주세요." type="text" id="subMenuNm" name="subMenuNm" data-valid-name="서브 메뉴명" data-valid-required maxlength="50"/> 
				</div>
				<div class="flex-column modal_input_box">
					<label class="modal_input_label">서브 메뉴 URL</label>
					<input class="modal_input data-validate" placeholder="메뉴 URL패턴에 맞춰 사용하실 URL을 입력해주세요." type="text" id="subUrlAddr" name="subUrlAddr" data-valid-name="서브 메뉴 URL 주소" data-valid-required maxlength="100"/> 
				</div>
			</div>
			<div class="flex-center gap16 mt16">
				<div class="flex-column modal_input_box">
					<label class="modal_input_label">서브 메뉴 URL 패턴</label>
					<input class="modal_input data-validate" placeholder="사용하실 URL패턴을 상위 메뉴에 맞춰 입력해주세요." type="text" id="subUrlPttrn" name="subUrlPttrn" data-valid-name="서브 메뉴 URL 주소" data-valid-required maxlength="100"/> 
				</div>
				<div class="flex-column modal_input_box">
					<label class="modal_input_label">서브 메뉴 노출여부</label>
					<select class="modal_input_selectbox" id="subUseYn" name="subUseYn">
			            <option value="Y">노출</option>
			           	<option value="N">비노출</option>
		        	</select>
				</div>
			</div>
			<c:if test="${mOpMenu.urlAddr eq '/monitoring.do'}">
				<div class="flex-center gap16 mt8">
					<div class="flex-center modal_input_box">
						<div class="flex-column wd100">
							<label class="modal_input_label">GIS 카테고리</label>
					        <select id="subCategCd" name="subCategCd" class="modal_input_selectbox">
					        	<option value="">없음</option>
					        	<c:forEach var="ctgryCdList"  items="${ctgryCdList}">
					        		<option value="<c:out value='${ctgryCdList.cdId}'/>"><c:out value='${ctgryCdList.cdNm}'/></option>
					        	</c:forEach>
					        </select>
						</div>
					</div>
				</div>
			</c:if>
			<div class="right modal-foot-btn-wrap">
				<button type="button" id="subMenuRemoveBtn" class="modal_append_btn">취소하기</button>
				<button type="button" id="subMenuInsertBtn" class="modal_append_btn">추가하기</button>
			</div>
		</div>
		
	</div>
</div>
<script>
	//<![CDATA[
		var urlAddr = '<c:out value="${mOpMenu.urlAddr}"/>'
	//]]>
	
	if(urlAddr == '/monitoring.do'){
		$(".modal_footer_remove_btn").addClass('none');
	}

	function changeSubMenu(menuId){
		//클릭 안된 상태
		var subMenu = $("#subMenu"+menuId);
		var subMenuNm = $("#subMenuNm"+menuId);
		var subUrlAddr = $("#subUrlAddr"+menuId);
		var subUrlPttrn = $("#subUrlPttrn"+menuId);
		var subUseYn = $("#subUseYn"+menuId);
		var subCategCd = $("#subCategCd"+menuId);
		
		if(subMenu.hasClass("isNotActive")){
			subMenu.removeClass("isNotActive");
			subUseYn.removeClass("is_disabled");
			subCategCd.removeClass("is_disabled");
			
			subMenuNm.attr("readonly",false);
			subUrlAddr.attr("readonly",false);
			subUrlPttrn.attr("readonly",false);
			
			subMenuNm.addClass("data-validate");
			subUrlAddr.addClass("data-validate");
			subUrlPttrn.addClass("data-validate");
			subMenu.addClass("isActive");
			
		} else {
			subMenu.removeClass("isActive");
			subMenuNm.removeClass("data-validate");
			subUrlAddr.removeClass("data-validate");
			subUrlPttrn.removeClass("data-validate");

			subMenuNm.attr("readonly",true);
			subUrlAddr.attr("readonly",true);
			subUrlPttrn.attr("readonly",true);
			
			subCategCd.addClass("is_disabled");
			subUseYn.addClass("is_disabled");
			subMenu.addClass("isNotActive");
		}
	}

	function updateSubMenuBtn(menuId){
		var subMenu = $("#subMenu"+menuId);
		var menuNm = $("#subMenuNm"+menuId);
		var urlAddr = $("#subUrlAddr"+menuId);
		var urlPttrn = $("#subUrlPttrn"+menuId);
		var useYn = $("#subUseYn"+menuId);
		var categCd = $("#subCategCd"+menuId);
		
		// 데이터 유효성 검사
		if(!$(subMenu).soValid()){
			return false;
		}
		
		var obj = new Object();
		obj.menuId = menuId;
		obj.menuNm = menuNm.val();
		obj.urlAddr = urlAddr.val();
		obj.urlPttrn = urlPttrn.val();
		obj.useYn = useYn.val();
		obj.categCd = categCd.val();
		
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/system/menu/sub/update.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("메인 메뉴 수정에 성공 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby("메인 메뉴 수정을 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	}

	/**
	 * 서브메뉴 삭제
	 */
	function delSubMenu(subMenuId){
		if(subMenuId == null || subMenuId == ''){
			new ModalBuilder().init().alertBoby("삭제 할 메뉴의 정보를 찾지 못했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		new ModalBuilder().init().alertBoby("해당 하위 메뉴를 삭제 하시겠습니까?").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "get",
				dataType : "json",
				url : "${pageContext.request.contextPath}/system/menu/sub/"+subMenuId+"/delete.ajax",
				success : function(data) {
					new ModalBuilder().init().successBody("하위 메뉴 삭제가 완료되었습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						modalAlertClose();
					}).open();
					modalAlertWrap();					
					if(data.code == 200){
						$("#subMenu"+subMenuId).remove();
						var subMenuIdx = $(".subMenuIdx").length
						if(subMenuIdx > 0){
							for(var i = 0; i < subMenuIdx; i++){
								$(".subMenuIdx").eq(i).text(i+1);
							}
						} else {
						var html = "";
							html += '<tr id="noneSubMenuTr">';
							html += 	'<td colspan="6" class="center">';
							html += 		'<p> 하위 메뉴가 존재하지 않습니다. 하위 메뉴를 생성 해주세요.</p>';
							html += 	'</td>';
							html += '</tr>';
						$("#subMenuTbody").append(html);
						}
					}
				}
			});
		 },'취소하기',function(button, modal){
			 modal.close();
		 }).open();		
	};
	
	/**
	* 서브메뉴 추가하기
	*/
	$("#subMenuInsertBtn").on('click',function() {
		var menuNm = $("#subMenuNm").val();
		var urlAddr = $("#subUrlAddr").val();
		var urlPttrn = $("#subUrlPttrn").val();
		var	useYn = $("#subUseYn").val();
		var menuId = $("#menuId").val();
		var sortNo = $("#mainSortNo").val();
		var categCd = $("#subCategCd").val();
		var cdDivCd = $("#cdDivCd").val();
		// 데이터 유효성 검사
		if(!$("#subMenuWrap").soValid()){
			return false;
		}
		
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				"menuId"	 : menuId,
				"menuNm" 	 : menuNm,
				"urlPttrn"	 : urlPttrn,
				"urlAddr"	 : urlAddr,
				"useYn"		 : useYn,
				"sortNo"	 : sortNo,
				"categCd"	 : categCd,
				"cdDivCd"	 : cdDivCd
			},
			url : "${pageContext.request.contextPath}/system/menu/sub/save.ajax",
			success : function(result) {
				new ModalBuilder().init().successBody("서브메뉴 추가를 성공하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();				
				if(result.code == 200){
					$("#noneSubMenuTr").remove();
					var html = "";
					var subMenuIdx = $(".subMenuIdx").length+1;
					var selectUseYnY = result.data.useYn == 'Y' ? 'selected' : '';
					var selectUseYnN = result.data.useYn == 'N' ? 'selected' : '';
					 	html += '<tr id="subMenu'+result.data.menuId+'" class="isNotActive">';
					 	html += 	'<td class="center subMenuIdx">'+subMenuIdx+'</td>';
						html += 	'<td class="pl24"> <input type="text" class="modal_table_input" id="subMenuNm'+result.data.menuId+'"value="'+result.data.menuNm+'"data-valid-name="서브 메뉴명" data-valid-required readonly</td>';
						html += 	'<td class="pl24"> <input type="text" class="modal_table_input" id="subUrlAddr'+result.data.menuId+'"value="'+result.data.urlAddr+'"data-valid-name="서브 메뉴 URL 주소" data-valid-required readonly</td>';
						html += 	'<td class="pl24"> <input type="text" class="modal_table_input" id="subUrlPttrn'+result.data.menuId+'"value="'+result.data.urlPttrn+'"data-valid-name="서브 메뉴 URL 패턴" data-valid-required readonly</td>';
						html += 	'<td class="pl24"> <select class="modal_input_selectbox is_disabled wd90" id="subUseYn'+result.data.menuId+'">';
						html +=     '<option value="Y"'+selectUseYnY+'>노출</option>'
						html +=     '<option value="N"'+selectUseYnN+'>비노출</option>'
						html += 	'</select></td>';
						html += 	'<td class="pl24">';
						html += 		'<div class="left">';
						html += 			'<div class="flex-center">';
						html +=		'<div class="menu_updata_box">';
						html += 		'<button type="button" class="ftsize12 menu_updata_btn" onclick="changeSubMenu(\''+result.data.menuId+'\')">수정하기</button>';
						if(urlAddr == '/monitoring.do'){
						html +=			' / ';
						html +=			'<button type="button" class="ftsize12" id="delSubMenuBtn" onclick="delSubMenu(\''+result.data.menuId+'\')">삭제하기</button>';
						}
						html +=		'</div>';
						html +=	 	'<div class="menu_updata_sub_btn_box none">';
						html +=		'<button type="button" class="ftsize12 menu_updata_btn_item" onclick="changeSubMenu(\''+result.data.menuId+'\')">취소</button>';
						html +=						' / ';
						html +=		'<button type="button" class="ftsize12 menu_updata_btn_item" onclick="updateSubMenuBtn(\''+result.data.menuId+'\')">저장</button>';
						html +=					'</div>';
						html += 			'</div>';
						html += 		'</div>'
						html += 	'</td>';
						html += '</tr>';
					$("#subMenuTbody").append(html);
				}
			}
		});
	});
	
	/* 서브메뉴 생성 */
	$('.modal_table_addbtn').on('click', function(){
		$('.modal_menubox_append').removeClass('none');
		$(this).addClass('none');
	})
	
	/* 서브메뉴 제거 */
	$('#subMenuRemoveBtn').on('click', function(){
		$('.modal_menubox_append').addClass('none');
		$('.modal_table_addbtn').removeClass('none');
	})
	
	/* 수정하기, 저장하기 버튼 */
	$(document).on('click', '.menu_updata_btn', function(){
		$(this).parent('.menu_updata_box').addClass('none');
		$(this).parent('.menu_updata_box').siblings('.menu_updata_sub_btn_box').removeClass('none');
	})
	$(document).on('click', '.menu_updata_btn_item', function(){
		$(this).parent('.menu_updata_sub_btn_box').addClass('none');
		$(this).parent('.menu_updata_sub_btn_box').siblings('.menu_updata_box').removeClass('none');
	})
	
	// url 한글입력 방지
	const korExp = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
	
	function urlCheck(inputId) {
		let urlInput = document.getElementById(inputId);
		let urlInputValue = urlInput.value;
		
		let checkedValue = urlInputValue.trim().replace(korExp, "");
		urlInput.value = checkedValue;
	}
</script>
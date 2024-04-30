<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">권한 등록</h3>
                <div class="side_txt">권한을 등록해 보세요.<br><br>대상자 및 권한은 언제든지<br>추가로 지정하실 수 있습니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">권한 등록</h2>
            <form id="authSaveFrm">
	            <div class="contents_wrap mt24">
	                <div class="group">
	                    <div class="group_text">권한 이름 (필수)<span class="required-alert">*</span></div>
	                    <input type="text" placeholder="권한 이름을 입력해 주세요." id="authNm" class="input_same group_box data-validate" 
	                    	data-valid-name="권한 이름" data-valid-required maxlength="50">
	                </div>
	                <div class="group">
	                    <div class="group_text">권한 설명</div>
	                    <input type="text" placeholder="권한 설명을 입력해 주세요." id="descr" class="input_same group_box"
	                    	maxlength="400">
	                </div>
					
	                <div class="group">
	                    <div class="group_text">권한 상세<span class="required-alert">*</span></div>
   	                    <div class="flex-center gap8">
				            <select id="authCd" class="authority_selectbox">
				            	<option value="" selected>선택</option>
				            	<option value="AUC000">상위 권한</option>
				            	<option value="AUC001">일반 권한</option>
				            	<option value="AUC002">VIP 권한</option>
				            </select>
	                    </div>
						 <div class="flex-center gap8 ml16">
			            	<span>권한 사용 여부</span>
				            <select id="authUseYn" class="authority_selectbox">
				            	<option value="Y">사용</option>
				            	<option value="N">미사용</option>
				            </select>
	                    </div>
	                </div>
	                <div id="nomalwrap">
		                <div class="group">
		                    <div class="group_text">권한 설정<span class="required-alert">*</span></div>
		                    <div class="group_btn">
		                    	<div class="flex-center flex-wrap">
		                        	<label class="is-dark-btn mt8 inpd" for="all_selector_main">
			                        	<input type="checkbox" id="all_selector_main" class="none">전체선택/해제
			                        </label>
				                    <c:forEach var="menuList" items="${menuList}" varStatus="upprStatus">
				                        <label class="is-dark-btn mt8 inpd">
				                        	<input type="checkbox" class="none mainMenu" value="<c:out value='${menuList.menuId}'/>" data-value="<c:out value='${menuList.menuNm}'/>"><c:out value="${menuList.menuNm}"/>
				                        </label>
				                    </c:forEach>
		                    	</div>
		                    </div> 
		                </div>
		                <div class="group">
		                    <div class="group_btn auth_setting">
		                    	상세 설정
		                    	<div id="subMenuWrap"></div>
		                    </div>
		                </div>
	                </div>
	                <div class="group group_search">
	                    <button type="button" id="saveBtn" class="is-darkgreen-btn">등록</button>
	                    <a href="${pageContext.request.contextPath}/system/auth/list.do" class="is-dark-btn">취소</a>
	                </div>
	            </div>
            </form>
        </section>
    </div>
</main>
<script>


	$("#authCd").on("change",function(){
		var authCd = $(this).val();
		if(authCd == 'AUC002'){
			$("#nomalwrap").hide();
			$("#authUseYn").val("Y");
			$("#authUseYn").addClass("is-disabled");
		} else {
			$("#nomalwrap").show();
		}
	});

	$("#all_selector_main").on("click",function(){
		var isChk = $(this).is(":checked");
		if(!isChk){
			$(this).prop("checked",false);
            $(this).parents("label").removeClass("is-darkgreen-btn");
            $(".mainMenu").parents("label").removeClass("is-darkgreen-btn");
			$(".mainMenu").prop("checked",false);
			$("#subMenuWrap").empty();
		} else {
			$(this).prop("checked",true);
            $(this).parents("label").addClass("is-darkgreen-btn");
            $(".mainMenu").parents("label").addClass("is-darkgreen-btn");
			$(".mainMenu").prop("checked",true);
			var mainMenuLength = $(".mainMenu").length;
			$("#subMenuWrap").empty();
			for(var i = 0; i < mainMenuLength; i++){
				var menuId = $(".mainMenu").eq(i).val();
				var menuNm = $(".mainMenu").eq(i).data('value');
				menuAppendAjax(menuId,menuNm);
			}
		}
	});
	
	$(".mainMenu").on("click",function(){
		var menuId = $(this).val();
		var menuNm = $(this).data('value');
		var isChk = $(this).is(":checked");
		if(!isChk){
			$(this).prop("checked",false);
            $(this).parents("label").removeClass("is-darkgreen-btn");
            $("#sub_menu_div_"+menuId).remove();
		} else {
			menuAppendAjax(menuId,menuNm);
			$(this).prop("checked",true);
            $(this).parents("label").addClass("is-darkgreen-btn");
		}
	});
	
	function menuAppendAjax(menuId,menuNm,isChk){
		var html = "";
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				"upperMenuId" 	 : menuId
			},
			url : "${pageContext.request.contextPath}/system/auth/sub/menu/list.ajax",
			success : function(data) {
				if(0 < data.length){
	              html += "<div class='group_btn mt16' id='sub_menu_div_"+menuId+"'>";
	              html += 	   "<div class='mb8'>"+menuNm+"</div>";
                  html +=	   "<div class='flex-center flex-wrap'>"
	              html +=      	   "<button type='button' id='subMenuAllChk_"+menuId+"' class='is-dark-btn all_selector mt8 mr8' onclick='fnSubMenuAllChk("+menuId+")'>전체선택/해제</button>"
	              
	              for(var i = 0; i < data.length; i++){
					  html +=       "<label class='group_btn_item is-dark-btn mt8 inpd'>";
		              html +=		'<input type="checkbox" id="subMenu_'+data[i].menuId+'" class="all_check none menu_'+menuId+'" onclick="fnSubMenuChk('+data[i].menuId+','+menuId+')";" value="'+data[i].menuId+'">'+data[i].menuNm;
		              html += 		"</label>";
	              }
	              html +=	   "</div>"
	              html += "</div>";
				  $("#subMenuWrap").append(html);
				} else {
				  html +=  "<div class='group_btn' id='sub_menu_div_"+menuId+"'>";
				  html +=      "<div class='group_btn mt16'>"+menuNm+"</div>";
				  html +=      "<span class='group_ex pl0'>선택된 메뉴의 하위 메뉴가 없습니다.</span>";
				  html +=  "</div>";
				  $("#subMenuWrap").append(html);
				}
			}
		});
	}
	
	$("#saveBtn").on('click',function(){
		var authNm = $("#authNm").val();
		var descr = $("#descr").val();
		var authUseYn = $("#authUseYn option:selected").val();
		var authCd = $("#authCd option:selected").val();

		if(authCd == "" || authCd == null){
			new ModalBuilder().init().alertBoby("권한 상세를 선택해주세요.").footer(4,'확인',function(button, modal){
				modal.close();
				$("#authCd").focus();
			}).open();
			return false;
		}
		
		if(!$("#authSaveFrm").soValid()){
			return false;
		}
		
		
		var mainMenu = $(".mainMenu");
		var menuIdArr = new Array();		
		for(var i = 0; i < mainMenu.length; i++){
			var isMenuChk = mainMenu.eq(i).is(":checked");
			if(isMenuChk){
				var menuId = mainMenu.eq(i).val();
				var subMenu = $(".menu_"+menuId);
				for(var j = 0; j < subMenu.length; j++){
				var isSubMenuChk = subMenu.eq(j).is(":checked");
					if(isSubMenuChk){
					var subMenuId = subMenu.eq(j).val();
					menuIdArr.push(subMenuId);
					}
				}
				menuIdArr.push(menuId);
			}
		}
		if(authCd != 'AUC002' && menuIdArr.length == 0){
			new ModalBuilder().init().alertBoby("메뉴 권한은 한개 이상 메뉴를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		var obj = new Object();
		obj.authNm = authNm;
		obj.descr = descr;
		obj.authUseYn = authUseYn;
		obj.menuIdArr = menuIdArr;
		obj.authCd = authCd;

		
		$.ajax({
			type : "post",
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/system/auth/save.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("권한 등록을 성공하였습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href = ${pageContext.request.contextPath}"/system/auth/list.do";
					}).open();
					modalAlertWrap();					
				} else {
					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	});

	function fnSubMenuAllChk(idx,isChk){
		var subMenuAllChk = $("#subMenuAllChk_"+idx);
		var isCheckedVal = subMenuAllChk.hasClass("is-darkgreen-btn");
		var subMenu = $(".menu_"+idx);
		var group_btn_item = subMenu.closest('.group_btn_item');
		if(!isCheckedVal){
			subMenuAllChk.addClass("is-darkgreen-btn");
			subMenu.prop("checked",true);
			group_btn_item.addClass("is-darkgreen-btn");
		}else{
			subMenuAllChk.removeClass("is-darkgreen-btn");
			subMenu.prop("checked",false);
			group_btn_item.removeClass("is-darkgreen-btn");
		}
	}
	
	function fnSubMenuChk(subMenuId,menuId){
		var subMenu = $("#subMenu_"+subMenuId);
		var isCheckedVal = subMenu.is(":checked");
		var group_btn_item = subMenu.closest('.group_btn_item');
		if(isCheckedVal){
			group_btn_item.addClass("is-darkgreen-btn");
		}else{
			var subMenuAllChk = $("#subMenuAllChk_"+menuId);
			var subMenuAllCheckedVal = $("#subMenuAllChk_"+menuId).hasClass("is-darkgreen-btn");
			if(subMenuAllCheckedVal){
				subMenuAllChk.removeClass("is-darkgreen-btn");		
			}
			group_btn_item.removeClass("is-darkgreen-btn");
		}
	}
	
</script>

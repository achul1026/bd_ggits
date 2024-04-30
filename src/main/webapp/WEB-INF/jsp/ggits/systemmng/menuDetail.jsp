<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <main>
        <div class="main_container">
        	<div style="width:500px">
        	
	         메인 메뉴정보<br>
	         <input type="hidden" id="menuId" value="<c:out value='${mOpMenu.menuId}"/>'/>메인메뉴 명 

	         <input type="text" id="mainMenuNm" value="<c:out value='${mOpMenu.menuNm}'/>"/><br>
	         메인메뉴 URL 패턴 <input type="text" id="mainUrlPttrn" value="<c:out value='${mOpMenu.urlPttrn}'/>"/> <br>
	         메인메뉴 URL <input type="text" id="mainUrlAddr" value="<c:out value='${mOpMenu.urlAddr}"/>'/>"/> <br>
	         메인메뉴 노출여부 <input type="text" id="mainUseYn" value="<c:out value='${mOpMenu.useYn}"/>'/>"/> <br>
	         메인메뉴 노출 순위 <input type="text" id="mainSortNo" value="<c:out value='${mOpMenu.sortNo}'/>"/> <br>

	         
	         
	         
			서브메뉴
			<table style="width:500px">
				<tr>
					<th>노출순위</th>
					<th>서브 메뉴 명</th>
					<th>URL</th>
					<th>노출여부</th>
					<th>삭제</th>
				</tr>
			<c:forEach var="subMenuList" items="${subMenuList}" varStatus="subStatus">
				<tr>
					<td><c:out value="${subStatus.count}"/></td>
					<td><c:out value="${subMenuList.menuNm}"/></td>
					<td><c:out value="${subMenuList.urlAddr}"/></td>
					<td><c:out value="${subMenuList.useYn eq 'Y' ? '노출' : '비노출'}"/></td>
					<td>
						<button type="button" id="delSubMenuBtn" onclick="delSubMenu('<c:out value="${subMenuList.menuId}"/>')">삭제하기</button>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="4">
						서브메뉴 명 <input type="text" id="subMenuNm" name="menuNm" value="메뉴생성테스트"/> <br>
				        서브메뉴 URL <input type="text" id="subUrlAddr" name="subUrlAddr" value="/bigdata/**"/> <br>
				        서브메뉴 노출여부 <input type="text" id="subUseYn" name="useYn" value="Y"/> <br>
				        GIS 카테고리 
				        <select id="categCd" name="categCd">
				        	<option value="">없음</option>
				        	<c:forEach var="ctgryCdList"  items="${ctgryCdList}">
				        		<option value="<c:out value='${ctgryCdList.cdId}'/>"><c:out value="${ctgryCdList.cdNm}"/></option>
				        	</c:forEach>
				        </select>
				        <br>
						<button type="button" id="subMenuInsertBtn">추가하기</button>
					</td>
				</tr>
			</table>
<%--         	<button type="button" id="menuDelBtn" onclick="delMainMenu('${mOpMenu.menuId}')">삭제</button> --%>
        	<button type="button" id="updateMenuBtn" onclick="updateMainMenu('<c:out value="${mOpMenu.menuId}"/>')">저장</button>
        	</div>
        </div>
    </main>
<script>
	/**
	 * 서브메뉴 삭제
	 */
	function delSubMenu(subMenuId){
		
		var menuId = $("#menuId").val();
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
					if(data.code == 200){
						new ModalBuilder().init().successBody("하위 메뉴 삭제가 완료되었습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
						location.reload();
					} else {
						new ModalBuilder().init().alertBoby("하위 메뉴 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
		 },'취소하기',function(button, modal){}).open();		
	};
	
	/**
	* 서브메뉴 추가하기
	*/
	$("#subMenuInsertBtn").on('click',function() {
		var menuNm = $("#subMenuNm").val();
		var urlAddr = $("#subUrlAddr").val();
		var urlPttrn = $("#mainUrlPttrn").val();
		var	useYn = $("#subUseYn").val();
		var menuId = $("#menuId").val();
		var sortNo = $("#mainSortNo").val();
		var categCd = $("#categCd").val();
		
		if(menuNm == null  || menuNm == ''){
			new ModalBuilder().init().alertBoby("메뉴명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		if(urlAddr == null  || urlAddr == ''){
			new ModalBuilder().init().alertBoby("사용 URL를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		if(useYn == null  || useYn == ''){
			new ModalBuilder().init().alertBoby("사용 여부를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
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
				"categCd"	 : categCd
			},
			url : "${pageContext.request.contextPath}/system/menu/sub/save.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("서브메뉴를 추가하였습니다.").footer(4,'확인',function(button, modal){
						modal.close();
						location.href = ${pageContext.request.contextPath}"/system/menu/"+menuId+"/detail.do";
					}).open();
					modalAlertWrap();					
				} else {
					new ModalBuilder().init().alertBoby("서브메뉴 추가를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			}
		});
	});
	
	function updateMainMenu(menuId){
		var menuId = $("#menuId").val();
		var menuNm = $("#mainMenuNm").val();
		var urlAddr = $("#mainUrlAddr").val();
		var urlPttrn = $("#mainUrlPttrn").val();
		var	useYn = $("#mainUseYn").val();
		var sortNo = $("#mainSortNo").val();
		
		var obj = new Object();
		obj.menuId = menuId;
		obj.menuNm = menuNm;
		obj.urlAddr = urlAddr;
		obj.urlPttrn = urlPttrn;
		obj.useYn = useYn;
		obj.sortNo = sortNo;
		
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			data : JSON.stringify(obj),
			url : "${pageContext.request.contextPath}/system/menu/main/update.ajax",
			success : function(data) {
				if(data.code == 200){
					new ModalBuilder().init().successBody("메뉴 수정을 성공하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby("메뉴 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();    					
				}				
			}
		});
	};
	
</script>

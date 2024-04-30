<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">메뉴 관리</h3>
                    <div class="side_txt">전체 메뉴를 관리할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">메뉴 관리</h2>
                    <div class="btn_search_wrap btn_search_wrap_between float-right">
                        <button type="button" class="is-darkgreen-btn mj0" id="menuRegBtn">등록하기</button>
                    </div>
                <table>
                    <colgroup>
                    </colgroup>
                    <tr>
                      <th scope="col">노출 순서</th>
                      <th scope="col">메인 메뉴</th>
                      <th scope="col">서브 메뉴</th>
                      <th scope="col">URL 패턴</th>
                      <th scope="col">URL</th>
                      <th scope="col">노출 여부</th>
                    </tr>
                    <c:forEach var="menuList" items="${menuList}" varStatus="upprStatus">
	                    <tbody>
	                        <tr class="table_1depth" onclick="detailView('<c:out value="${menuList.upperMenu.menuId}"/>')">
	                            <td><c:out value="${upprStatus.count}"/></td>
	                            <td><c:out value="${menuList.upperMenu.menuNm}"/></td>
	                            <td><c:out value="${menuList.subMenuCnt}"/> 개</td>
	                            <td><c:out value="${menuList.upperMenu.urlPttrn}"/></td>
	                            <td><c:out value="${not empty menuList.subMenuList? menuList.subMenuList[0].urlAddr:menuList.upperMenu.urlAddr}"/></td>
	                            <td><c:out value="${menuList.upperMenu.useYn eq 'Y' ? '노출' : '비노출'}"/></td>
	                        </tr>
	                    </tbody>
                    </c:forEach>
                  </table>
            </section>
        </div>
    </main>
<script>

/* 	$("#menuRegBtn").on('click',function(){
	    location.href = ${pageContext.request.contextPath}"/system/menu/save.do";
	});
 */
	$("#schMenuId").on('change', function(){
		var menuId = $(this).val();
	    location.href = ${pageContext.request.contextPath}"/system/menu/list.do?menuId="+menuId;
	});
	
/* 	function detailView(menuId){
	    location.href = ${pageContext.request.contextPath}"/system/menu/"+menuId+"/detail.do";
	} */
	
	/* modal start */
	/* 메인메뉴 등록하기 */
    $('#menuRegBtn').on("click", function(){
        new ModalBuilder().init('메인 메뉴 등록하기').ajaxBody("${pageContext.request.contextPath}/common/modal/memuregistration/list.do").footer(1,'등록', function(button, modal){
    		var menuNm = $("#menuNm").val();
    		var urlPttrn = $("#urlPttrn").val();
    		var urlAddr = $("#urlAddr").val();
    		var useYn = $("#useYn").val();
    		var categCd = $("#categCd").val();
    		
    		// 데이터 유효성 검사
    		if(!$("#menuSaveFrm").soValid()){
    			return false;
    		}
    		
    		var obj = new Object();
    		obj.menuNm = menuNm;
    		obj.urlPttrn = urlPttrn;
    		obj.urlAddr = urlAddr;
    		obj.useYn = useYn;
    		obj.categCd = categCd;
    		
    		$.ajax({
    			type : "post",
    			dataType : "json",
    			contentType : "application/json; charset=UTF-8",
    			data : JSON.stringify(obj),
    			url : "${pageContext.request.contextPath}/system/menu/main/save.ajax",
    			success : function(data) {
    				if(data.code == 200){
    					new ModalBuilder().init().successBody("메뉴 등록을 성공하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();    					
    					location.href = ${pageContext.request.contextPath}"/system/menu/list.do";
    				} else {
    					new ModalBuilder().init().alertBoby("메뉴 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				}
    			}
    		});
        }).open();
        $('.modal_footer').removeClass('none');
    });
	
 	function detailView(menuId){
        new ModalBuilder().init('메인 메뉴 정보').ajaxBody("${pageContext.request.contextPath}/common/modal/menuinfo/"+menuId+"/list.do").footer(3,'삭제하기',function(button, modal){
    		var menuId = $("#menuId").val();
    		if(menuId == null || menuId == ''){
    			new ModalBuilder().init().alertBoby("삭제 할 메뉴의 정보를 찾지 못했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    			modalAlertWrap();		
    			return false;
    		}
    		
			new ModalBuilder().init().alertBoby("해당 메뉴를 삭제 하시겠습니까?").footer(5,'삭제하기',function(button, modal){
    			$.ajax({
    				type : "get",
    				dataType : "json",
    				url : "${pageContext.request.contextPath}/system/menu/main/"+menuId+"/delete.ajax",
    				success : function(data) {
    					if(data.code == 200){
    						new ModalBuilder().init().successBody("메뉴가 삭제되었습니다.").footer(4,'확인',function(button, modal){
    							modal.close();
	    						location.reload();
    						}).open();
    						modalAlertWrap();    						
    					} else {
    						new ModalBuilder().init().alertBoby("메뉴 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    						modalAlertWrap();    						
    					}
    				}
    			});
			 },'취소하기',function(button, modal){}).open();
			
        }, '저장하기', function(button, modal){
    		var menuId = $("#menuId").val();
    		var menuNm = $("#mainMenuNm").val();
    		var urlAddr = $("#mainUrlAddr").val();
    		var urlPttrn = $("#mainUrlPttrn").val();
    		var	useYn = $("#mainUseYn").val();
    		var sortNo = $("#mainSortNo").val();
//     		var categCd = $("#categCd").val();
    		
    		// 데이터 유효성 검사
    		if(!$("#mainMenuWrap").soValid()){
    			return false;
    		}
    		
    		var obj = new Object();
    		obj.menuId = menuId;
    		obj.menuNm = menuNm;
    		obj.urlAddr = urlAddr;
    		obj.urlPttrn = urlPttrn;
    		obj.useYn = useYn;
    		obj.sortNo = sortNo;
//     		obj.categCd = categCd;
    		
    		$.ajax({
    			type : "post",
    			dataType : "json",
    			contentType : "application/json; charset=UTF-8",
    			data : JSON.stringify(obj),
    			url : "${pageContext.request.contextPath}/system/menu/main/update.ajax",
    			success : function(data) {
    				if(data.code == 200){
    					new ModalBuilder().init().successBody("메인 메뉴 수정을 성공하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				} else {
    					new ModalBuilder().init().alertBoby("메인 메뉴 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();    					
    				}
    			}
    		});
        }).open();
        $('.modal_footer').removeClass('none');
	}
	
</script>

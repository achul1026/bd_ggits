<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">그룹코드 정보</h3>
                <div class="side_txt">그룹코드 정보를 확인하고 코드<br>를 추가하여 관리할 수 있습니다.
                    <br><br>
                    그룹코드 정보는 언제든지<br>추가로 지정하실 수 있습니다.
                </div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">그룹코드 정보</h2>
            <div class="contents_wrap">
	            <form id="codeGrpUpdFrm">
	            	<input type="hidden" id="grpCdId" name="grpCdId" value="<c:out value='${grpCodeInfo.grpCdId}'/>">
	                <div class="group">
	                    <div class="group_text">그룹코드 (필수)</div>
	                    <c:out value="${grpCodeInfo.grpCdId}"/>
	                </div>
	                <div class="group">
	                    <div class="group_text">그룹코드명 (필수)<span class="required-alert">*</span></div>
	                    <input type="text" placeholder="코드명을 입력해 주세요." name="grpCdNm" id="grpCdNm" 
	                    	class="input_same group_box data-validate" data-valid-name="그룹코드명" data-valid-required value="<c:out value='${grpCodeInfo.grpCdNm}'/>" maxlength="50">
	                </div>
	                <div class="group">
	                    <div class="group_text">그룹코드 설명</div>
	                    <input type="text" placeholder="코드 설명을 입력해 주세요." name="descr" id="descr" class="input_same group_box" value="<c:out value='${grpCodeInfo.descr}'/>" maxlength="400">
	                </div>
	                <div class="group">
	                    <div class="group_text">사용여부 (필수)</div>
	                    <select class="selectBox" name="useYn" id="useYn">
	                        <option value="Y" <c:if test="${grpCodeInfo.useYn eq 'Y'}">selected</c:if>>사용</option>
	                        <option value="N" <c:if test="${grpCodeInfo.useYn eq 'N'}">selected</c:if>>미사용</option>
	                    </select>
		                <button type="button" id="uptGrpCodeBtn" class="is-darkgreen-btn">저장</button>
	                </div>
	            </form>
	            
	            <div class="group wd100">
	            	<div class="flex-column wd100">
		            	<div>
			           		<div class="group_text">관리코드 설명</div>
			                <div>
				               	<form id="searchForm" method="get">
			                 		<div class="btn_search_wrap btn_search_wrap_left float-right">
			                 			<ul>
			                 				<li>
			                 					<input type="hidden" id="page" name="page"  value="1"/>
						                 		<select class="selectBox float-none pr40 mj0" name="useYn" id="searchUseYn">
						                    		<option value="" <c:if test="${useYn eq ''}">selected</c:if>>사용여부 전체</option>
						                      		<option value="Y" <c:if test="${useYn eq 'Y'}">selected</c:if>>사용</option>
						                      		<option value="N" <c:if test="${useYn eq 'N'}">selected</c:if>>미사용</option>
						                		</select>
			                 				</li>
			                 				<li>
			                 					<select class="selectBox pr40 mj0" name="searchType" id="searchType">
						                        	<option value="all" <c:if test="${searchType eq 'all'}">selected</c:if>>전체</option>
						                        	<option value="cdId" <c:if test="${searchType eq 'cdId'}">selected</c:if>>코드</option>
						                        	<option value="cdNm" <c:if test="${searchType eq 'cdnm'}">selected</c:if>>코드명</option>
						                     	</select>
			                 				</li>
			                 				<li>
			                 					<input type="text" placeholder="검색어를 입력하세요." name="searchContent" id="searchContent" class="input_same group_box" value="<c:out value='${searchContent}'/>">
			                 				</li>
			                 				<li>
			                 					<input type="button" value="검색" class="input_same search_box2" onclick="fnSearchList();">
			                 				</li>
			                 				<li>
			                 					<button type="button" id="saveCodeInfoBtn" class="is-darkgreen-btn mj0">추가하기</button>
			                 				</li>
			                 			</ul>
					        		</div>
				               	</form>
				            </div>
						</div>
						<div class="group_code_table">
						    <table class="mt8">
						        <colgroup>
						            <col style="width:7%">
						            <col style="width:9%">
						            <col style="width:9%">
						            <col style="width:8%">
						            <col style="width:22%">
						            <col style="width:12%">
						            <col style="width:22%">
						            <col style="width:12%">
						        </colgroup>
						        <tr>
						            <th scope="col">번호</th>
						            <th scope="col">코드</th>
						            <th scope="col">코드명</th>
						            <th scope="col">사용여부</th>
						            <th scope="col">등록자</th>
						            <th scope="col">등록일</th>
						            <th scope="col">수정자</th>
						            <th scope="col">수정일</th>
						        </tr>
						        <c:choose>
						        	<c:when test="${fn:length(codeList) > 0 }">
						          		<c:forEach var="codeInfo" items="${codeList}" varStatus="status">
						           			<tr class="pointer modalcodeinfo" data-cdid="<c:out value='${codeInfo.cdId}'/>">
									     		<td><c:out value="${codeInfo.rownum}"/></td>
									     		<td><c:out value="${codeInfo.cdId}"/></td>
									     		<td><c:out value="${codeInfo.cdNm}"/></td>
									     		<td>
									     			<c:if test="${codeInfo.useYn == 'Y'}">사용</c:if>
									   				<c:if test="${codeInfo.useYn == 'N'}">미사용</c:if>
									     		</td>
									     		<td><c:out value="${codeInfo.crtusrId}"/></td>
									     		<td>
							                  		<fmt:formatDate pattern="yyyy-MM-dd" value="${codeInfo.crtDt}"/>
						                  		</td>
									     		<td><c:out value="${codeInfo.uptusrId}"/></td>
									     		<td>
							                  		<fmt:formatDate pattern="yyyy-MM-dd" value="${codeInfo.updtDt}"/>
						                  		</td>
						     	 			</tr>
						          		</c:forEach>
						        	</c:when>
						        	<c:otherwise>
						        		<td colspan="8">관리 코드가 존재하지 않습니다.</td>
						        	</c:otherwise>
						        </c:choose>
						    </table>
						 	<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
						</div>
					</div>
	            </div>
	            <div class="group group_search ">
	                <button type="button" id="delGrpCodeBtn" class="is-darkgreen-btn">삭제</button>
	                <a href="${pageContext.request.contextPath}/system/codegrp/list.do" class="is-dark-btn">이전 페이지</a>
	            </div>
            </div>
        </section>
    </div>
</main>
<script>
	$(document).ready(function() {
		$('#searchContent').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});

	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}detail.do";
		document.getElementById('searchForm').submit();
	}	
	$("#searchUseYn").on("change", function(){
		fnSearchList();
	})
	
	$('.modalcodeinfo').on("click", function(){
    	var cdId = $(this).data("cdid");
       	var grpCdId = $("#grpCdId").val();
        new ModalBuilder().init('코드 정보').ajaxBody("${pageContext.request.contextPath}/common/modal/codeinfo/list.do?cdId="+cdId+"&grpCdId="+grpCdId).footer(3,'삭제하기',function(button, modal){
        	var cdId = $("#cdId").val();
        	$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/system/codegrp/code/"+cdId+"/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("관리 코드를 삭제하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
						}).open();
						modalAlertWrap();						
					} else {
						new ModalBuilder().init().alertBoby("관리 코드 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
				}
			});
        }, '저장하기', function(button, modal){
        	if($("#codeUpdFrm").soValid()){
        		$.ajax({
					type : "post",
					data : $("#codeUpdFrm").serialize(),
					url : "${pageContext.request.contextPath}/system/codegrp/code/update.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("관리 코드를 수정하였습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.reload();
							}).open();
						} else {
							new ModalBuilder().init().alertBoby("관리 코드 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();			
						}
					}
				});
        	}
        }).open();
        $('.modal_footer').removeClass('none');
    });
	
	$("#saveCodeInfoBtn").on('click', function(){
		var data = {"grpCdId" : $("#grpCdId").val()}
		new ModalBuilder().init('관리 코드 등록').ajaxBody("${pageContext.request.contextPath}/common/modal/codeadd/list.do", data).footer(1,'추가하기',function(button, modal){
			if($("#codeSaveFrm").soValid()){
				var grpCdId = $("#grpCdId").val();
				var cdId = $("#cdId").val();
				var cdNm = $("#cdNm").val();
				var descr = $("#cdDescr").val();
				var sortNo = $("#sortNo").val();
				var useYn = $("#cdUseYn").val();
				
	    		var obj = new Object();
	    		obj.cdId = cdId;
	    		obj.cdNm = cdNm;
	    		obj.descr = descr;
	    		obj.sortNo = sortNo;
	    		obj.grpCdId = grpCdId;
	    		obj.useYn = useYn;
	    		
	    		$.ajax({
					type : "post",
					dataType : "json",
	    			contentType : "application/json; charset=UTF-8",
	    			data : JSON.stringify(obj),
					url : "${pageContext.request.contextPath}/system/codegrp/code/save.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("관리 코드를 등록하였습니다.").footer(4,'확인',function(button, modal)
								{modal.close();
								location.reload();
							}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("관리 코드 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				});
			}
		}).open();
        $('.modal_footer').removeClass('none');
	})
	
	$("#uptGrpCodeBtn").on("click", function(){
		// 데이터 유효성 검사
		if($("#codeGrpUpdFrm").soValid()){
			var grpCdId = $("#grpCdId").val();
			var grpCdNm = $("#grpCdNm").val();
			var descr = $("#descr").val();
			var useYn = $("#useYn").val();
			new ModalBuilder().init().alertBoby("그룹코드를 수정하시겠습니까?").footer(5,'확인',function(button, modal){
				$.ajax({
					type : "post",
					data : {
						"grpCdId" 	 : grpCdId,
						"grpCdNm"	 : grpCdNm,
						"descr"	 	 : descr,
						"useYn"	 	 : useYn
					},
					url : "${pageContext.request.contextPath}/system/codegrp/update.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody("그룹코드 수정이 완료되었습니다.").footer(4,'확인',function(button, modal){
								modal.close();
								location.href = "${pageContext.request.contextPath}/system/codegrp/"+grpCdId+"/detail.do";
							}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby("그룹코드 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				});		
			 },'취소',function(button, modal){}).open();			
		}
	})
	
	$("#delGrpCodeBtn").on("click", function(){
		var grpCdId = $("#grpCdId").val();
		new ModalBuilder().init().alertBoby("그룹코드를 삭제하시겠습니까?").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/system/codegrp/"+grpCdId+"/delete.do",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("그룹코드를 삭제하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href = "${pageContext.request.contextPath}/system/codegrp/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("그룹코드 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
				}
			});		
		 },'취소하기',function(button, modal){}).open();
	})
</script>
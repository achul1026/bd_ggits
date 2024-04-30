<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">표준 노드/링크<br> 자료실</h3>
            <div class="side_txt">자료를 업로드 하거나<br>자료를 확인 할 수 있습니다.</div>
            <a href="${pageContext.request.contextPath}/basicinfo/dashboard.do" class="is-darkgreen-btn" style="font-size:0.8rem">GIS로 노드/링크 조회하기</a>
        </div>
    </aside>
    <section class="main_section">
        <h2 class="blind">표준노드링크 자료실</h2>
        <div class="contents_wrap table_btn_wrap flex-between mb8">
        	<form id="searchForm" name="searchForm" action="/basicinfo/nodelink/reference/list.do">
	            <div class="btn_search_wrap float-none">
	            	<ul>
	            		<li>
	            			업데이트 연도 : 
		           			<select class="selectBox radius" name="aplcnYmd">
				                <option value="">전체</option>
					           	<c:forEach var="yearsList" items="${yearsList}">
					                <option value="<c:out value='${yearsList.year}'/>" ${yearsList.year eq aplcnYmd ? 'selected="selected"' : ''}><c:out value='${yearsList.year}'/>년</option>
					           	</c:forEach>
				            </select>
	            		</li>
	            		<li>
	            			<input type="button" id="searchBtn" value="검색" class="input_same search_box2 pointer">
	            		</li>
	            	</ul>
           			
               		
	            </div>
        	</form>
            <div class="mj0">
               	<button type="button" id="uploadBtn" class="input_same search_box2">변경사항 업데이트</button>
            </div>
        </div>
        <table>
            <tr>
                <th scope="col" class="left">
	                <select class="table-filter" onchange="fnChangOrderType(this);">
	                	<option value="">유형</option>
	                	<option ${searchType eq 'NODE' ? 'selected="selected"' : '' } value="NODE">노드</option>
	                	<option ${searchType eq 'LINK' ? 'selected="selected"' : '' } value="LINK">링크</option>
	                </select>
                </th>
                <th scope="col">업데이트 일자</th>
                <th scope="col">제목</th>
                <th scope="col">업데이트 내용</th>
                <th scope="col">등록자</th>
            </tr>
            <c:choose>
            	<c:when test="${not empty referenceList}">
		            <c:forEach var="referenceList" items="${referenceList}">
			            <tr onclick="getDetail('<c:out value="${referenceList.stdInfoId}"/>')" class="pointer">
			                <td class="left"><c:out value="${referenceList.stdAplcnType}"/></td>
			                <td>
					    		<fmt:parseDate value="${referenceList.aplcnYmd}" var="aplcnYmd" pattern="yyyyMMdd"/>
								<fmt:formatDate value="${aplcnYmd}" pattern="yyyy-MM-dd"/>
			                </td>
			                <td><c:out value='${referenceList.stdInfoNm}'/></td>
			                <td><c:out value='${referenceList.rmrk}'/></td>
			                <td><c:out value='${referenceList.saveInfo}'/></td>
			            </tr>
		            </c:forEach>
            	</c:when>
            	<c:otherwise>
					<tr>
						<td colspan="5">조회된 결과가 없습니다.</td>
					</tr>
            	</c:otherwise>
            </c:choose>
          </table>
          <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
    </section>
</div>
<script>
	/* modal start */
	/* 업로드 버튼 모달 */
	$('#uploadBtn').on("click", function(){
	    new ModalBuilder().init('노드/링크 변경사항 등록').ajaxBody("${pageContext.request.contextPath}/common/modal/nodelink/reference/save.do").footer(1,'저장',function(button, modal){
			var stdInfoNm = $("#stdInfoNm").val();
			var rmrk = $("#rmrk").val();
			var refTyCd = $("input[name='refTyCd']:checked").val();
			var aplcnYmd = $("#aplcnYmd").val() != null ? $("#aplcnYmd").val().replaceAll("-","") : '';
			
			if(refTyCd == null || refTyCd == ''){
				new ModalBuilder().init().alertBoby("자료 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(stdInfoNm == null || stdInfoNm == ''){
				new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(aplcnYmd == null || aplcnYmd == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			} else {
				var regExp = /^[0-9]+$/;
				if(!regExp.test(aplcnYmd)){
					new ModalBuilder().init().alertBoby("날짜 형식을 확인해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
			}
			
			
			var obj = new Object();
			obj.stdInfoNm = stdInfoNm;
			obj.stdAplcnType = refTyCd;
			obj.rmrk = rmrk;
			obj.aplcnYmd = aplcnYmd;
			
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/save.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("자료 등록에 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href = ${pageContext.request.contextPath}"/basicinfo/nodelink/reference/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("자료 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
	    	modal.close();
	    }).open();
	    $('.modal_footer').removeClass('none');
	});
	
	$("#searchBtn").on('click',function(){
		fnSearchList();
	});
	
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/basicinfo/nodelink/reference/list.do";
		document.getElementById('searchForm').submit();
	}
	
	function getDetail(stdInfoId){
		new ModalBuilder().init('노드/링크 변경사항 수정').ajaxBody("${pageContext.request.contextPath}/common/modal/nodelink/reference/"+stdInfoId+"/detail.do").footer(3,'삭제',function(button, modal){
    		$.ajax({
    			type : "get",
    			url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/"+stdInfoId+"/delete.ajax",
    				success : function(result) {
    				var resultCode = result.code;
    				if(resultCode == '200'){
						new ModalBuilder().init().successBody("노드/링크 자료 삭제를 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
	    					location.reload();
						}).open();
						modalAlertWrap();
    				}else{
    					new ModalBuilder().init().alertBoby("노드/링크 자료 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				}
	  			}
   			});
	    	modal.close();
	    }, '수정', function(button, modal){
	    	var stdInfoId = $("#stdInfoId").val();
			var stdInfoNm = $("#stdInfoNm").val();
			var rmrk = $("#rmrk").val();
			var refTyCd = $("input[name='refTyCd']:checked").val();
			var aplcnYmd = $("#aplcnYmd").val() != null ? $("#aplcnYmd").val().replaceAll("-","") : '';
			
			if(refTyCd == null || refTyCd == ''){
				new ModalBuilder().init().alertBoby("자료 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(stdInfoNm == null || stdInfoNm == ''){
				new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(aplcnYmd == null || aplcnYmd == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			} else {
				var regExp = /^[0-9]+$/;
				if(!regExp.test(aplcnYmd)){
					new ModalBuilder().init().alertBoby("날짜 형식을 확인해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
			}
			
			
			var obj = new Object();
			obj.stdInfoId = stdInfoId;
			obj.stdInfoNm = stdInfoNm;
			obj.stdAplcnType = refTyCd;
			obj.rmrk = rmrk;
			obj.aplcnYmd = aplcnYmd;
			
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/update.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("노드/링크 자료 수정을 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href = ${pageContext.request.contextPath}"/basicinfo/nodelink/reference/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("노드/링크 자료 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
	    	modal.close();
        }).open();
		$('.modal_footer').removeClass('none');
	}
	
	function fnChangOrderType(_this){
		var searchType = $(_this).val();
		window.location.href="${pageContext.request.contextPath}/basicinfo/nodelink/reference/list.do?searchType="+searchType;
	}
</script>
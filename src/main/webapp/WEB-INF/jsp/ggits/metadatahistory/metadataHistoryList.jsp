<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">신청이력</h3>
                <div class="side_txt">데이터 카탈로그의 신청이력<br>정보를 조회합니다.</div>
            </div>
        </aside>
        <section class="main_section tab_set">
            <h2 class="blind">데이터 카탈로그 신청이력</h2>
            <div class="contents_wrap tab_area">
             <form id="searchForm">
	                <div class="group2">
                        <div class="group_text2">검색</div>
                        <div>
	                    	<select class="selectBox" id="mngCdId" name="mngCdId">
	                    			<option value="">전체</option>
								<c:forEach var="mngInstCdList" items="${mngInstCdList}">
				                	<option value="<c:out value='${mngInstCdList.cdId}'/>" ${searchOption.mngCdId eq mngInstCdList.cdId ? 'selected':''}><c:out value='${mngInstCdList.cdNm}'/></option>
								</c:forEach>
	                    	</select>
							<input type="text" id="searchContent" name="searchContent" placeholder="검색어를 입력하세요." class="input_same ml8" value="<c:out value='${searchOption.searchContent}'/>">							
			                <input type="hidden" name="page" id="page" value="1">
                        </div>
		                </div>
	                        <div class="group2">
	                            <div class="group_text2">시간 설정</div>
	                            <div class="btn_search_wrap">
	                  	            <ul>
	                  	            	<li>
	                  	            		<input type="text" class="date_picker input_same input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요." value="<c:out value='${searchOption.strDt}'/>">
	                  	            	</li>
	                  	            	<li>
	                  	            		~
	                  	            	</li>
	                  	            	<li>
	                  	            		<input type="text" class="end_date_picker input_same input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요." value="<c:out value='${searchOption.endDt}'/>">
	                  	            	</li>
	                  	            </ul>
	                            </div>
                       </div>
	                    <div class="btn_search_wrap btn_search_wrap_center">
	                    	<ul>
	                    		<li>
	                    			<button type="button" class="is-darkgreen-btn" id="searchBtn">찾기</button>
	                    		</li>
	                    	</ul>
                        </div>
	                </form>
                    <div class="search_container flex-between">
                        <div class="search_head">
                            <div class="search_number">
                                <span id="totalCnt">0</span>개의 검색결과를 찾았습니다.
                            </div>
                        </div>
                    </div>
                    <table class="mt16">
                        <thead>
	                        <tr>
	                            <th>번호</th>
	                            <th>요청 테이블 명</th>
	                            <th>신청자 명</th>
	                            <th>신청일시</th>
	                            <th>관리기관</th>
	                            <th>완료 여부</th>
	                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        	<c:when test="${not empty lTcDataCatlogAplyList}">
		                        <c:forEach var="lTcDataCatlogAply" items="${lTcDataCatlogAplyList}">
		                        	<tr>
		                        		<td><c:out value="${lTcDataCatlogAply.rownum}"/></td>
		                        		<td><c:out value="${lTcDataCatlogAply.tblEngNm}"/></td>
		                        		<td><c:out value="${lTcDataCatlogAply.oprtrNm}"/></td>
		                        		<td>
		                        			<fmt:formatDate var="aplyDt" pattern="yyyy년 MM월 dd일" value="${lTcDataCatlogAply.aplyDt}"/>
			                        		<c:out value="${aplyDt}"/>
		                        		</td>
		                        		<td><c:out value="${lTcDataCatlogAply.cdNm}"/></td>
		                        		<td>
		                        		<c:choose>
		                        			<c:when test="${lTcDataCatlogAply.aplyCmptnYn eq 'Y'}">
												승인 완료
		                        			</c:when>
		                        			<c:when test="${lTcDataCatlogAply.aplyCmptnYn ne 'Y' and mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
		                        				<button type="button" class="is-darkgreen-btn" onclick="approveCatlog('<c:out value="${lTcDataCatlogAply.aplyDsetId}"/>','<c:out value="${lTcDataCatlogAply.oprtrId}"/>')">승인 처리</button>
		                        			</c:when>
		                        			<c:otherwise>
												미승인
		                        			</c:otherwise>
		                        		</c:choose>
		                        		
		                        		</td>
		                        	</tr>
		                        </c:forEach>
                        	</c:when>
                        	<c:otherwise>
							<tr>
								<td colspan="6">
									조회된 카탈로그 신청 이력이 없습니다.
								</td>
							</tr>
                        	</c:otherwise>
                        </c:choose>
                        </tbody>
                	</table>
                </div>
            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
        </section>
    </div>
</main>

<script>

	$(document).ready(function(){
		var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
		$("#totalCnt").text(numberComma(dataTotalCnt));
	})
	
	$("#searchBtn").on('click',function(){
		$("#searchForm").submit();
	});
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/metadata/history/list.do";
		document.getElementById('searchForm').submit();
	}

	 <c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
	function approveCatlog(aplyDsetId,oprtrId){
		if(aplyDsetId == null || aplyDsetId == ''){
			return false;
		}
		
		if(oprtrId == null || oprtrId == ''){
			return false;
		}
		
		new ModalBuilder().init().alertBoby("해당 요청을 승인처리 하시겠습니까?").footer(5,'승인',function(button, modal){
			$.ajax({
				type : "post",
				data : {
					"aplyDsetId" : aplyDsetId,
					"oprtrId"	 : oprtrId
				},
				dataType : "json",
				url : "${pageContext.request.contextPath}/metadata/history/approve.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("승인처리 되었습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
							$(".modal_container").remove();
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("승인처리 실패했습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
		 },'취소',function(button, modal){
			modal.close();
		 }).open();
	}
	</c:if>
	
</script>
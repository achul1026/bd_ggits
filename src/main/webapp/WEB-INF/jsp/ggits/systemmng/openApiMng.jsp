<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">Open API 관리</h3>
                <div class="side_txt">Open API를 관리합니다.</div>
            </div>
        </aside>
        <section class="main_section">
	        <form id="searchForm" method="get">
		                <div class="table_btn_wrap clearfix flex-end">
	                        <div class="btn_search_wrap">
	                        	<ul>
	                        		<li>
	                        			<input type="hidden" id="page"  name="page" value="1"/>
	                        			 <select class="selectBox" name="searchType">
			                                <option value="all">전체</option>
			                                <option value="apiNm" ${searchOption.searchType eq 'apiNm'? 'selected':''}>제목</option>
			                                <option value="oprtrNm" ${searchOption.searchType eq 'oprtrNm'? 'selected':''}>등록자</option>
			                                <option value="mngInstNm" ${searchOption.searchType eq 'mngInstNm' ? 'selected':''}>관리기관</option>
			                            </select>
	                        		</li>
	                        		<li>
	                        			  <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" name="searchContent" value="<c:out value='${searchOption.searchContent}'/>">
	                        		</li>
	                        		<li>
	                        			  <input type="button" value="검색" class="input_same search_box2 pointer" onclick="fnSearchList();">
	                        		</li>
	                        		<c:if test="${authCd eq 'AUC000'}">
		                        		<li>
		                        			  <input type="button" value="등록하기" class="input_same search_box2 pointer" onclick="location.href='${pageContext.request.contextPath}/system/openapi/save.do'">
		                        		</li>
	                        		</c:if>
	                        	</ul>
	                        </div>
		                </div>
	                </form>
	                
	                <table>
	                    <colgroup>
	                        <col style="width:10%">
	                        <col style="width:30%">
	                        <col style="width:46%">
	                        <col style="width:14%">
	                    </colgroup>
	                    <tr>
	                        <th scope="col">번호</th>
	                        <th scope="col">제목</th>
	                        <th scope="col">설명</th>
	                        <th scope="col">최신수정일자</th>
	                    </tr>
	                    <c:choose>
	                    	<c:when test="${not empty openApiList}">
			                    <c:forEach var="openApiList" items="${openApiList}">
			                 		<tr onclick="location.href='${pageContext.request.contextPath}/system/openapi/<c:out value='${openApiList.dsetId}'/>/detail.do'" class="is-hover">
				                 		<td><c:out value="${openApiList.rownum}"/></td>
				                 		<td><c:out value="${openApiList.apiNm}"/></td>
				                 		<td><c:out value="${openApiList.descr}"/></td>
				                 		<td><c:out value="${openApiList.dataUpdtYmd}"/></td>
			                    	</tr>
			                    </c:forEach>
		                    </c:when>
		                	<c:otherwise>
		                		<td colspan="4">조회된 OPEN API가 존재하지 않습니다.</td>
		                	</c:otherwise>
	                	</c:choose>
					</table>
               		<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
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
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/system/openapi/list.do";
		document.getElementById('searchForm').submit();
	}
</script>

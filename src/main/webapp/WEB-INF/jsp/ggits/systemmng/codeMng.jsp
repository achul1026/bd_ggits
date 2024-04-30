<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">코드 관리</h3>
                <div class="side_txt">코드를 설정하여 각 코드를<br>클릭하여 수정하실 수 있습니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">코드 관리</h2>
            <form id="searchForm" method="get">
	            <div class="table_btn_wrap table_btn_wrap_between">
	            	<input type="hidden" id="page" name="page"  value="1"/>	
		            <div class="btn_search_wrap float-left">
		            	<ul>
		            		<li>
				                <select class="selectBox float-left" name="useYn" id="searchUseYn">
				                    <option value="" <c:if test="${useYn eq ''}">selected</c:if>>사용여부 전체</option>
				                    <option value="Y" <c:if test="${useYn eq 'Y'}">selected</c:if>>사용</option>
				                    <option value="N" <c:if test="${useYn eq 'N'}">selected</c:if>>미사용</option>
				                </select>
		            		</li>
		            	</ul>
		            </div>
	            	
	            	<div class="btn_search_wrap ">
	            		<ul>
	            			<li>
	            				<select class="selectBox" name="searchType" id="searchType">
			                        <option value="all" <c:if test="${searchType eq 'all'}">selected</c:if>>전체</option>
			                        <option value="grpCdId" <c:if test="${searchType eq 'grpCdId'}">selected</c:if>>그룹코드</option>
			                        <option value="grpCdNm" <c:if test="${searchType eq 'grpCdnm'}">selected</c:if>>그룹코드명</option>
			                    </select>
	            			</li>
	            			<li>
	            				<input type="text" placeholder="검색어를 입력하세요." name="searchContent" id="searchContent" class="input_same search_box" value="<c:out value='${searchContent}'/>">
	            			</li>
	            			<li>
	            				<input type="button" value="검색" class="input_same search_box2 pointer"  onclick="fnSearchList();">
	            			</li>
	            			<li>
	            				<a href="${pageContext.request.contextPath}/system/codegrp/save.do" class="is-darkgreen-btn">등록하기</a>
	            			</li>
	            		</ul>
	            	</div>
	            </div>
            </form>
            
            <table>
                <colgroup>
                </colgroup>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">그룹코드</th>
                    <th scope="col">그룹코드명</th>
                    <th scope="col">사용여부</th>
                    <th scope="col">등록자</th>
                    <th scope="col">등록일</th>
                    <th scope="col">수정자</th>
                    <th scope="col">수정일</th>
                </tr>
                <c:choose>
                	<c:when test="${fn:length(grpCdList) > 0}">
                  <c:forEach var="grpCodeInfo" items="${grpCdList}" varStatus="status">
                  	<tr onclick="location.href='${pageContext.request.contextPath}/system/codegrp/${grpCodeInfo.grpCdId}/detail.do'" class="pointer">
                  		<td><c:out value="${grpCodeInfo.rownum}"/></td>
                  		<td><c:out value="${grpCodeInfo.grpCdId}"/></td>
                  		<td><c:out value="${grpCodeInfo.grpCdNm}"/></td>
                  		<td>
                  			<c:out value="${grpCodeInfo.useYn eq 'Y' ? '사용' : '미사용'}"/>
                  		</td>
                  		<td><c:out value="${grpCodeInfo.crtusrId}"/></td>
                  		<td>
	                  		<fmt:formatDate pattern="yyyy-MM-dd" value="${grpCodeInfo.crtDt}"/>
                  		</td>
                  		<td><c:out value="${grpCodeInfo.uptusrId}"/></td>
                  		<td>
	                  		<fmt:formatDate pattern="yyyy-MM-dd" value="${grpCodeInfo.updtDt}"/>
                  		</td>
                  	</tr>
                  </c:forEach>
                	</c:when>
                	<c:otherwise>
                		<td colspan="8">그룹 코드가 존재하지 않습니다.</td>
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

	$("#searchUseYn").on("change", function(){
		fnSearchList();
	})

	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}list.do";
		document.getElementById('searchForm').submit();
	}	
</script>
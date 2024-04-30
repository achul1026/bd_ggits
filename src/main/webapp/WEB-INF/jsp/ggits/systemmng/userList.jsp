<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 관리</h3>
                    <div class="side_txt">사용자를 관리할 수 있습니다.</div>
                    <c:if test="${oprtrGrd eq 'SUPER'}">
                    	<a href="${pageContext.request.contextPath}/system/user/group/list.do" class="is-darkgreen-btn">그룹 관리하기</a>
                	</c:if>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 관리</h2>
                <form id="searchForm" method="get">
                	<input type="hidden" id="page" name="page"  value="1"/>
	                <div class="table_btn_wrap clearfix">
	                    <div class="table-btn-between-wrap">
	                    	<div class="table_btn_left float-left">
		                        <select class="selectBox" name="oprtrSttsCd" id="searchOprtrSttsCd">
		                            <option value="all"		<c:if test="${oprtrSttsCd eq 'all' }">	    selected="selected"</c:if>>상태 전체</option>
		                            <option value="OSC002"	<c:if test="${oprtrSttsCd eq 'OSC002' }">	selected="selected"</c:if>>승인</option>
		                            <option value="OSC001"	<c:if test="${oprtrSttsCd eq 'OSC001' }">	selected="selected"</c:if>>미승인</option>
		                            <option value="OSC003"	<c:if test="${oprtrSttsCd eq 'OSC003' }">	selected="selected"</c:if>>중지</option>
		                        </select>
		                    </div>
	                        <div class="btn_search_wrap float-none">
	                        	<ul>
	                        		<li>
	                        			<input type="text" name="strDt" id="strDt" class="date_picker input_same input_picker" placeholder="날짜를 선택해주세요." value="<c:out value='${strDt}'/>" autocomplete="off">
	                        		</li>
	                        		<li>~</li>
	                        		<li>
	                        		    <input type="text" name="endDt" id="endDt" class="end_date_picker input_same  input_picker" placeholder="날짜를 선택해주세요." value="<c:out value='${endDt}'/>" autocomplete="off">
	                        		</li>
	                        		<li>
	                        			<select class="selectBox" name="searchType">
			                                <option value="all" 			<c:if test="${searchType eq 'all' }">			selected="selected"</c:if>>전체</option>
			                                <option value="schOprtrEmail"	<c:if test="${searchType eq 'schOprtrEmail' }">	selected="selected"</c:if>>사용자 ID</option>
			                                <option value="schOprtrNm"		<c:if test="${searchType eq 'schOprtrNm' }">	selected="selected"</c:if>>이름</option>
			                                <option value="schOprtrGrpNm"	<c:if test="${searchType eq 'schOprtrGrpNm' }">	selected="selected"</c:if>>그룹명</option>
			                            </select>
	                        		</li>
	                        		<li>
	                        			<input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" name="searchContent" value="${searchContent}">
	                        		</li>
	                        		<li>
	                        			<input type="button" value="검색" class="input_same search_box2 pointer" onclick="fnSearchList();">
	                        		</li>
	                        		<c:if test="${authCd eq 'AUC000'}">
		                        		<li>
		                        			<a href="${pageContext.request.contextPath}/system/user/save.do" class="is-darkgreen-btn">등록하기</a>
		                        		</li>
	                        		</c:if>
	                        	</ul>
	                        </div>
	                    </div>
	                </div>
                </form>
                
                <table>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">사용자 ID</th>
                        <th scope="col">이름</th>
                        <th scope="col">그룹</th>
                        <th scope="col">상태</th>
                        <th scope="col">등록일</th>
                    </tr>
                    <c:choose>
                    	<c:when test="${fn:length(userList) > 0}">
		                    <c:forEach var="userList" items="${userList}">
			                    <tr onclick="location.href='${pageContext.request.contextPath}/system/user/<c:out value='${userList.oprtrId}'/>/detail.do'" class="pointer">
			                        <td><c:out value="${userList.rownum}"/></td>
			                        <td><c:out value="${userList.oprtrEmail}"/></td>
			                        <td><c:out value="${userList.oprtrNm}"/></td>
			                        <td><c:out value="${userList.grpNm}"/></td>
			                        <td>
			                        	<c:choose>
			                        		<c:when test="${userList.oprtrSttsCd eq 'OSC001'}">
			                        			미승인
			                        		</c:when>
			                        		<c:when test="${userList.oprtrSttsCd eq 'OSC002'}">
			                        			승인
			                        		</c:when>
			                        		<c:when test="${userList.oprtrSttsCd eq 'OSC003'}">
			                        			중지
			                        		</c:when>
			                        		<c:otherwise>
			                        			유저 상태 확인 필요
			                        		</c:otherwise>
			                        	</c:choose>
			                        </td>
			                        <td><c:out value="${userList.oprtrRegistDd}"/></td>
			                    </tr>
		                    </c:forEach>
	                    </c:when>
	                	<c:otherwise>
	                		<td colspan="6">해당 사용자가 존재하지 않습니다.</td>
	                	</c:otherwise>
                	</c:choose>
				</table>
				<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
            </section>
        </div>
    </main>
	<script type="text/javascript">
		$("#searchOprtrSttsCd").on("change", function(){
			fnSearchList();
		})
		
		function fnSearchList(){
			document.getElementById('searchForm').action= "${pageContext.request.contextPath}/system/user/list.do";
			document.getElementById('searchForm').submit();
		}				
	</script>
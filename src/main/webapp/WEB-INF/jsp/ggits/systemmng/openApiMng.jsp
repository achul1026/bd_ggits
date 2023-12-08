<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		                <div class="table_btn_wrap clearfix">
		                    <div class="flex-end">
		                        <div class="btn_search_wrap float-none">
		                            <select class="selectBox" name="searchType">
		                                <option value="all">전체</option>
		                                <option value="">제목</option>
		                                <option value="">등록자</option>
		                                <option value="">제공기관</option>
		                            </select>
		                            <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" name="searchContent">
		                            <input type="button" value="검색" class="input_same search_box2 pointer" onclick="fnSearchList();">
		                        </div>
	                            <input type="button" value="등록하기" class="input_same search_box2 pointer" onclick="location.href='${pageContext.request.contextPath}/system/openapi/save.do'">
		                    </div>
		                </div>
	                </form>
	                
	                <table>
	                    <colgroup>
	                        <col style="width:8%">
	                        <col style="width:40%">
	                        <col style="width:12%">
	                        <col style="width:14%">
	                        <col style="width:10%">
	                        <col style="width:16%">
	                    </colgroup>
	                    <tr>
	                        <th scope="col">번호</th>
	                        <th scope="col">제목</th>
	                        <th scope="col">설명</th>
	                        <th scope="col">등록자</th>
	                        <th scope="col">최신수정일자</th>
	                        <th scope="col">제공기관</th>
	                    </tr>
	                    <c:choose>
	                    	<c:when test="${not empty openApiList}">
			                    <c:forEach var="openApiList" items="${openApiList}">
			                 		<tr onclick="location.href='${pageContext.request.contextPath}/system/openapi/${openApiList.apiId}/detail.do'">
				                 		<td>${openApiList.rownum}</td>
				                 		<td>${openApiList.apiNm}</td>
				                 		<td>${openApiList.contents}</td>
				                 		<td>컬럼확인필요</td>
				                 		<td>${openApiList.dataUpdtYmd}</td>
				                 		<td>${openApiList.mngInstCd}</td>
			                    	</tr>
			                    </c:forEach>
		                    </c:when>
		                	<c:otherwise>
		                		<td colspan="8">OPEN API를 찾지 못했습니다.</td>
		                	</c:otherwise>
	                	</c:choose>
					</table>
<!--             <h2 class="blind">open api 관리</h2> -->
<!--             <div class="api_container mt24"> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">교통권역 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">도로분류 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">도로별 링크 정보 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">교통량 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">실시간 도로 소통 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="api_box"> -->
<!--                     <h4 class="api_item_title">실시간 돌발 정보 API</h4> -->
<!--                     <div class="api_toggle_box"> -->
<!--                         <ul class="api_item_box"> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key 이름</span><input type="text" placeholder="교통권역 API Key" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; key</span><input type="text" placeholder="U0ASJKDJAKSDJKALSASJKDLJASKLDJKALS" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 설명</span><input type="text" placeholder="교통권역 API를 사용할 수 있습니다." class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 생성일</span><input type="text" placeholder="2023년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 만료일</span><input type="text" placeholder="2024년 12월 01일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                             <li class="api_item"> -->
<!--                                 <span>-&nbsp; 수정일</span><input type="text" placeholder="2023년 12월 04일" class="input_same search_box"> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                         <div class="group mt24 clearfix"> -->
<!--                             <div class="float-left"> -->
<!--                                 <button type="button" class="is-dark-btn">수정하기</button> -->
<!--                                 <button type="button" class="is-dark-btn">삭제하기</button> -->
<!--                             </div> -->
<!--                             <div class="float-right"> -->
<!--                                 <a href="open_api_register.html" class="is-darkgreen-btn">등록하기</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
        </section>
    </div>
</main>

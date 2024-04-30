<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">공지사항</h3>
                <div class="side_txt">공지사항<br>정보를 조회합니다.</div>
            </div>
        </aside>
        <section class="main_section tab_set">
            <h2 class="blind">공지사항</h2>
            <div class="contents_wrap tab_area">
             <form id="searchForm">
	                <div class="group2">
                        <div class="group_text2">검색</div>
                        <div>
	                    	<select class="selectBox" id="searchType" name="searchType">
	                    		<option value="all">제목 + 내용</option>
	                    		<option value="title">제목</option>
	                    		<option value="writer">작성자</option>
	                    	</select>
							<input type="text" id="searchContent" name="searchContent" placeholder="검색어를 입력해주세요." class="input_same ml8" value="<c:out value='${searchOption.searchContent}'/>">							
			                <input type="hidden" name="page" id="page" value="1">
                        </div>
		                </div>
	                        <div class="group2">
	                            <div class="group_text2">등록일</div>
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
                        <c:if test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
	                        <div class="btn_search_wrap">
								<a href="${pageContext.request.contextPath}/bbs/notice/save.do" class="is-darkgreen-btn mj0">등록하기</a>
	                       </div>
                       </c:if>
                    </div>
                    
                    <table class="mt16">
                        <thead>
	                        <tr>
	                            <th>번호</th>
	                            <th>제목</th>
	                            <th>작성자 명</th>
	                            <th>작성일</th>
	                            <th>첨부</th>
	                            <th>조회수</th>
	                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        	<c:when test="${not empty mOpNtcBbsList}">
		                        <c:forEach var="mOpNtcBbs" items="${mOpNtcBbsList}">
		                        	<tr onclick="viewDetail('<c:out value="${mOpNtcBbs.ntcId}"/>')">
		                        		<td><c:out value="${mOpNtcBbs.rownum}"/></td>
		                        		<td><c:out value="${mOpNtcBbs.ntcTitle}"/></td>
		                        		<td><c:out value="${mOpNtcBbs.oprtrNm}"/></td>
		                        		<td>
		                        			<fmt:formatDate var="ntcCrtDt" pattern="yyyy년 MM월 dd일" value="${mOpNtcBbs.ntcCrtDt}"/>
			                        		<c:out value="${ntcCrtDt}"/>
		                        		</td>
		                        		<td>
		                        		<c:if test="${mOpNtcBbs.atchFileYn eq 'Y'}">
		                        			<img src="/statics/images/atch_file_icon.png">
		                        		</c:if>
		                        		</td>
		                        		<td><c:out value="${mOpNtcBbs.ntcSrchCnt}"/></td>
		                        	</tr>
		                        </c:forEach>
                        	</c:when>
                        	<c:otherwise>
							<tr>
								<td colspan="6">
									조회된 공지사항이 없습니다.
								</td>
							</tr>
                        	</c:otherwise>
                        </c:choose>
<!--                         	<tr onclick="viewDetail('1')"> -->
<!--                         		<td>1</td> -->
<!--                         		<td>공지사항이에요</td> -->
<!--                         		<td>이경용</td> -->
<!--                         		<td>2024-01-24</td> -->
<!--                         		<td><img src="/statics/images/atch_file_icon.png"></td> -->
<!--                         		<td>0</td> -->
<!--                         	</tr> -->
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

 	function viewDetail(noticeId){
		location.href = ${pageContext.request.contextPath}"/bbs/notice/"+noticeId+"/detail.do";
	}
</script>
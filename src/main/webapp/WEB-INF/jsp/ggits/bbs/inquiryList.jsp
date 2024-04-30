<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">문의</h3>
                <div class="side_txt">기존 문의 내역을 확인하고<br>관리자에게 신규<br>문의 할 수 있습니다.</div>
            </div>
        </aside>
        <section class="main_section tab_set">
            <h2 class="blind">문의</h2>
            <div class="contents_wrap tab_area">
             <form id="searchForm">
	                <div class="group2">
                        <div class="group_text2">검색</div>
                        <div>
	                    	<select class="selectBox" id="searchType" name="searchType">
	                    		<option value="title">제목</option>
	                    		<option value="content">내용</option>
	                    		<option value="writer">문의자</option>
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
               		<div class="group2">
                        <div class="group_text2">답변 여부</div>
                        <div>
	                    	<select class="selectBox" id="inqryAnsYn" name="inqryAnsYn">
	                    		<option value="" >전체</option>
	                    		<option value="Y" ${searchOption.inqryAnsYn eq 'Y'?'selected':''}>답변 완료</option>
	                    		<option value="N" ${searchOption.inqryAnsYn eq 'N'?'selected':''}>답변 대기중</option>
	                    	</select>
                        </div>
	                </div>
                       
	                    <div class="btn_search_wrap btn_search_wrap_center">
	                    	<ul>
	                    		<li>
	                    			<button type="button" class="is-darkgreen-btn" id="searchBtn">검색</button>
	                    		</li>
	                    	</ul>
                        </div>
	                </form>
                    <div class="search_container flex-between">
                        <div class="search_head">
                            <div class="search_number">
                                <span id="totalCnt">0</span>개의 문의를 찾았습니다.
                            </div>
                        </div>
            			<c:if test="${mOpOperatorInfo.oprtrGrd ne 'SUPER'}">
	                        <div class="btn_search_wrap">
								<a href="${pageContext.request.contextPath}/bbs/inquiry/save.do" class="is-darkgreen-btn mj0">신규 문의</a>
	                       </div>
                       </c:if>
                    </div>
                    
                    <table class="mt16">
                        <thead>
                        <c:choose>
	            			<c:when test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
		                        <tr>
		                            <th>번호</th>
		                            <th>문의 제목</th>
		                            <th>문의 일시</th>
		                            <th>답변 상태</th>
		                            <th>첨부</th>
		                            <th>문의자</th>
		                        </tr>
	            			</c:when>
	            			<c:otherwise>
		                        <tr>
		                            <th>번호</th>
		                            <th>문의 제목</th>
		                            <th>문의 일시</th>
		                            <th>답변 여부</th>
		                        </tr>
	            			</c:otherwise>
            			</c:choose>
                        </thead>
                        <tbody>
                        <c:choose>
                        	<c:when test="${not empty mOpInqryBbsList}">
                        		<c:choose>
                        			<c:when test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
				                        <c:forEach var="mOpInqryBbs" items="${mOpInqryBbsList}">
				                        	<tr onclick="viewDetail('<c:out value="${mOpInqryBbs.inqryId}"/>')">
				                        		<td><c:out value="${mOpInqryBbs.rownum}"/></td>
				                        		<td><c:out value="${mOpInqryBbs.inqryTitle}"/></td>
				                        		<td>
				                        			<fmt:formatDate var="inqryCrtDt" pattern="yyyy년 MM월 dd일" value="${mOpInqryBbs.inqryCrtDt}"/>
					                        		<c:out value="${inqryCrtDt}"/>
				                        		</td>
				                        		<td><c:out value="${mOpInqryBbs.inqryAnsYn eq 'Y'?'답변 완료': '답변 미완료'}"/></td>
				                        		<td>
			       				                    <c:if test="${mOpInqryBbs.atchFileYn eq 'Y'}">
					                        			<img src="/statics/images/atch_file_icon.png">
					                        		</c:if>
				                        		</td>
				                        		<td><c:out value="${mOpInqryBbs.oprtrNm}"/></td>
				                        	</tr>
			                        	</c:forEach>
                        			</c:when>
                        			<c:otherwise>
				                        <c:forEach var="mOpInqryBbs" items="${mOpInqryBbsList}">
				                        	<tr onclick="viewDetail('<c:out value="${mOpInqryBbs.inqryId}"/>')">
				                        		<td><c:out value="${mOpInqryBbs.rownum}"/></td>
				                        		<td><c:out value="${mOpInqryBbs.inqryTitle}"/></td>
				                        		<td>
				                        			<fmt:formatDate var="inqryCrtDt" pattern="yyyy년 MM월 dd일" value="${mOpInqryBbs.inqryCrtDt}"/>
					                        		<c:out value="${inqryCrtDt}"/>
				                        		</td>
				                        		<td><c:out value="${mOpInqryBbs.inqryAnsYn eq 'Y'?'답변 완료': '답변 대기중'}"/></td>
				                        	</tr>
				                        </c:forEach>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:when>
                        	<c:otherwise>
	                        	<c:choose>
	                        			<c:when test="${mOpOperatorInfo.oprtrGrd eq 'SUPER'}">
											<tr>
												<td colspan="6">
													문의 내역이 없습니다.
												</td>
											</tr>
	                        			</c:when>
	                        			<c:otherwise>
											<tr>
												<td colspan="4">
													문의 내역이 없습니다.
												</td>
											</tr>
										</c:otherwise>
								</c:choose>
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

 	function viewDetail(inqryId){
		location.href = ${pageContext.request.contextPath}"/bbs/inquiry/"+inqryId+"/detail.do";
	}
</script>
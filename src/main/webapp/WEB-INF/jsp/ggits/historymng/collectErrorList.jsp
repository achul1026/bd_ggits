<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">데이터 수집 장애<br>이력 관리</h3>
                <div class="side_txt">데이터 수집 이력 관리<br>정보를 조회합니다.</div>
            </div>
        </aside>
        <section class="main_section tab_set">
            <h2 class="blind">서버 제어 이력 관리</h2>
            <div class="table_btn_wrap clearfix tab_fc">
                <div class="btn_search_wrap float-left">
                  	<input type="hidden" id="linkedType" value="${linkedType}"/>
                	<ul>
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collect/error/list.do?linkedType=SMT000" class="is-dark-btn ${linkedType eq 'SMT000' ? 'is-darkgreen-btn':''}">지자체 연계</a>
                		</li>
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collect/error/list.do?linkedType=SMT001" class="is-dark-btn ${linkedType eq 'SMT001' ? 'is-darkgreen-btn':''}">외부기관 연계</a>
                		</li>
<!--                 		<li> -->
<%--                 			<a href="${pageContext.request.contextPath}/historymng/collect/error/list.do?linkedType=SMT002" class="is-dark-btn ${linkedType eq 'SMT002' ? 'is-darkgreen-btn':''}">신호 연계</a> --%>
<!--                 		</li> -->
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collect/error/list.do?linkedType=SMT003" class="is-dark-btn ${linkedType eq 'SMT003' ? 'is-darkgreen-btn':''}">빅데이터 연계 시스템</a>
                		</li>
                	</ul>
                </div>
            </div>
            <div class="contents_wrap tab_area">
                <div class="tab1">
                    <div class="group2">
                        <div class="group_text2 group2_width">검색</div>
						<input type="text" id="searchContent" name="searchContent" placeholder="요청 프로세스 ID 또는 수집원을 입력해 주세요." class="input_same group_box history-input-witdh" value="${searchOption.searchContent}">							
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <form id="searchForm">
                    	<div class="search_detail_wrap">
	                    	<input type="hidden" name="page" id="page" value="1">	                        
	                    	<div class="group2">
	                            <div class="group_text2 group2_width">시간 설정</div>
	                            <div class="btn_search_wrap">
	                            	<ul>
	                  	            	<li> 
	                  	            		<input type="text" class="date_picker input_same input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
	                  	            	</li>
	                  	            	<li>
	                  	            		~
	                  	            	</li>
	                  	            	<li>
	                  	            		<input type="text" class="end_date_picker input_same input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
	                  	            	</li>
	                            	</ul>	
	                            </div>
	                        </div>
           	 			 </div>
	                     <div class="btn_search_wrap btn_search_wrap_center">
	                     	<ul>
	                    		<li>
	                    			<button type="button" class="is-darkgreen-btn" id="searchBtn">찾기</button>
	                    		</li>
	                    		<li>
	                    			<input type="button" id="resetSchOption" class="is-dark-btn selected_reset" value="검색값 초기화">
	                    		</li>
	                     	</ul>
                    	</div>
	                </form>
                    <div class="search_container">
                        <div class="search_head">
                            <div class="search_number">
                                <span id="totalCnt"><c:out value="${paging.totalCount}"/></span>개의 검색결과를 찾았습니다.
                            </div>
                        </div>
                    </div>
					<table class="mt16">
					       <thead>
					        <tr>
					            <th>번호</th>
					            <th>프로세스 ID</th>
					            <th>요청명</th>
								<th>장애 내용</th>
								<th>수집 실패 데이터</th>
					            <th>장애발생일시</th>
					        </tr>
					       </thead>
					       <tbody>
					        <c:choose>
	                        	<c:when test="${not empty collectErrorList}">
							        <c:forEach var="collectErrorList" items="${collectErrorList}">
				                        <tr>
				                            <td><c:out value="${collectErrorList.rownum}"/></td>
				                            <td><c:out value="${collectErrorList.dsetId}"/></td>
				                            <td><c:out value="${collectErrorList.colName ne null and  collectErrorList.colName ne '' ? collectErrorList.colName : '-'}"/></td>
				                            <td><c:out value="${collectErrorList.failRsn}"/></td>
				                            <td><c:out value="${collectErrorList.jobNm}"/></td>
				                            <td><c:out value="${collectErrorList.clctStartDt}"/></td>
				                        </tr>
	                        		</c:forEach>
	                        	</c:when>
	                        	<c:otherwise>
		                            <tr>
										<td colspan="6">조회된 결과가 없습니다.</td>
									</tr>
	                        	</c:otherwise>
	                       	</c:choose>
						   </tbody>
					</table>
                </div>
            </div>
            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
        </section>
    </div>
</main>

<script>
	var dataTotalCnt = '${paging.totalCount}';
	$("#totalCnt").text(numberComma(dataTotalCnt))

$(document).ready(function(){
	//<![CDATA[
		var strDt = '${searchOption.strDt}';
		var endDt = '${searchOption.endDt}';
	//]]>
	
	//searchOption dataInit
	if(strDt != null && strDt != ''){
		$("#strDt").val(strDt.substring(0,10));
	}
	if(endDt != null && endDt != ''){
		$("#endDt").val(endDt.substring(0,10));
	}
})
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
		fnSearchList();
	})
	
	function fnSearchList(){
		var searchContent = $("#searchContent").val();
		var linkedType = $("#linkedType").val();
		
		var formData = $("#searchForm").serialize();
		formData += '&searchContent='+searchContent;
		formData += '&linkedType='+linkedType;
		
		location.href="${pageContext.request.contextPath}/historymng/collect/error/list.do?"+formData;
	}
	
	$("#resetSchOption").on('click',function(){
		$("#searchContent").val("");
	});
</script>
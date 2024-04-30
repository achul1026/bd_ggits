<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
	<div class="main_container">
		<aside class="snb_container">
			<div class="snb_wrap">
				<h3 class="side_title">Open API 사용 이력</h3>
				<div class="side_txt">
					Open API 이력 관리 정보를<br>조회합니다.
				</div>
			</div>
		</aside>
		<section class="main_section">
			<h2 class="blind">Open API 사용 이력</h2>
			<div class="contents_wrap">
				<div class="tab1">
					<div class="group2 pt0">
						<div class="group_text2">검색</div>
						<input type="text" id="searchContent" name="searchContent" placeholder="검색어를 입력해주세요" class="input_same group_box" value="<c:out value='${searchOption.searchContent}'/>">
						<div class="search_detail_btn">
							상세 검색 <i></i>
						</div>
					</div>
					<form id="searchForm" name="searchForm">
						<div class="search_detail_wrap">
							<input type="hidden" name="page" value="<c:out value='${paging ne null ? paging.pageNo : "1"}'/>"/>
							<div class="group2">
								<div class="group_text2">시간 설정</div>
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
								<span id="totalCnt"><c:out value='${paging.totalCount}'/></span>개의 검색결과를 찾았습니다.
							</div>
						</div>
					</div>
					<table class="mt16">
						<thead>
							<tr>
								<th>번호</th>
								<th>API명</th>
								<th>호출 URL</th>
								<th>호출 결과</th>
								<th>사용일시</th>
							</tr>
						</thead>
						<tbody>
						 <c:choose>
                        	<c:when test="${not empty openApiList}">
							<c:forEach var="openApiList" items="${openApiList}">
		                        <tr class="pointer" onclick="openApiLogDetail('<c:out value='${openApiList.dsetId}'/>')">
		                            <td><c:out value='${openApiList.rownum}'/></td>
		                            <td><c:out value='${openApiList.apiNm}'/></td>
		                            <td><c:out value='${openApiList.apiCallUrl}'/></td>
		                            <td><c:out value="${openApiList.resultStatus eq 'RSC000'?'성공':'실패'}"/></td>
		                            <td>
                            		  <fmt:formatDate value="${openApiList.clctDt}" pattern="yyyy-MM-dd HH:mm"/>
                            		</td>
		                        </tr>
                        	</c:forEach>
                        	</c:when>
                        	<c:otherwise>
			                    <tr>
									<td colspan="5">조회된 결과가 없습니다.</td>
								</tr>
                        	</c:otherwise>
                        </c:choose>
						</tbody>
					</table>
				</div>
			</div>
			<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		</section>
	</div>
</main>

<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function(){
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		
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
		$("input[name='page']").val("1");
		var formData = $("#searchForm").serialize();
		formData += '&searchContent='+searchContent;
		location.href="${pageContext.request.contextPath}/historymng/open/api/list.do?"+formData;
	}
	
	$("#resetSchOption").on('click',function(){
		$("#searchContent").val("");
	});
	
	function openApiLogDetail(dsetId){
	    new ModalBuilder().init('API 호출 결과').ajaxBody("${pageContext.request.contextPath}/common/modal/historymng/openapi/result.do?dsetId="+dsetId).footer(1,'닫기',function(button, modal){modal.close();}).open();
	}
</script>

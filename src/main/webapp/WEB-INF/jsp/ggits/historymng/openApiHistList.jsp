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
			<!--             <div class="table_btn_wrap clearfix tab_fc"> -->
			<!--                 <div class="btn_search_wrap float-left"> -->
			<!--                     <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" data-index="1">지자체 연계</button> -->
			<!--                     <button type="button" class="tab_btn_item is-dark-btn" data-index="2">외부기관 연계</button> -->
			<!--                     <button type="button" class="tab_btn_item is-dark-btn" data-index="3">신호 연계</button> -->
			<!--                     <button type="button" class="tab_btn_item is-dark-btn" data-index="4">빅데이터 저장 플랫폼</button> -->
			<!--                 </div> -->
			<!--             </div> -->
			<div class="contents_wrap">
				<div class="tab1">
					<div class="group2">
						<div class="group_text2">검색</div>
						<input type="text" id="searchContent" name="searchContent" placeholder="검색어를 입력해주세요" class="input_same group_box" value="${searchOption.searchContent}">
						<div class="search_detail_btn">
							상세 검색 <i></i>
						</div>
					</div>
					<div class="search_detail_wrap">
						<form id="searchForm" name="searchForm">
							<input type="hidden" name="page" value="${paging ne null ? paging.pageNo : '1'}"/>
							<div class="group2">
								<div class="group_text2">시간 설정</div>
								<div class="flex-center">
									<div class="calendar">
										<input type="text" class="date_picker input_same mr8 input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="startTime" name="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="endTime" name="endTime"></select>
									</div>
<!-- 									<div class="flex-center"> -->
<!-- 										<div class="select_tltie">집계 시간</div> -->
<!-- 										<select class="selectBox"> -->
<!-- 											<option selected="selected">10분</option> -->
<!-- 											<option>test1</option> -->
<!-- 											<option>test2</option> -->
<!-- 											<option>test3</option> -->
<!-- 											<option>test4</option> -->
<!-- 											<option>test5</option> -->
<!-- 										</select> -->
<!-- 									</div> -->
								</div>
							</div>
							<div class="group2_btn">
								<!-- button id  name 바꿔서 사용하세요  -->
								<button type="button" class="is-darkgreen-btn" id="searchBtn">찾기</button>
								<input type="reset" class="is-dark-btn selected_reset"
									value="검색값 초기화">
							</div>
						</form>
					</div>
					<div class="search_container">
						<div class="search_head">
							<div class="search_number">
								<span>"${paging.totalCount}개"</span>의 검색결과를 찾았습니다.
							</div>
						</div>
					</div>
					<table class="mt16">
						<colgroup>
							<col style="width: 8%">
							<col style="width: 30%">
							<col style="width: 10%">
							<col style="width: 27%">
							<col style="width: 20%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>사용자 이름</th>
								<th>사용자 ID</th>
								<th>사용일시</th>
							</tr>
						</thead>
						<tbody>
						 <c:choose>
                        	<c:when test="${not empty openApiList}">
							<c:forEach var="openApiList" items="${openApiList}">
		                        <tr>
		                            <td>${openApiList.rownum}</td>
		                            <td>${openApiList.apiRqstLog}</td>
		                            <td>${openApiList.oprtrNm}</td>
		                            <td>${openApiList.oprtrEmail}</td>
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

	$(document).ready(function(){
		//<![CDATA[
			var strDt = '${searchOption.strDt}';
			var endDt = '${searchOption.endDt}';
			var strTime = '${searchOption.startTime}';
			var endTime = '${searchOption.endTime}';
		//]]>
		
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(strTime != null && strTime != ''){
			$("#startTime").val(strTime).prop("selected",true);
		}
		if(endTime != null && endTime != ''){
			$("#endTime").val(endTime).prop("selected",true);
		}
	})
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
		fnSearchList();
	})
	
	function fnSearchList(){
		var searchContent = $("#searchContent").val();
		var formData = $("#searchForm").serialize();
		formData += '&searchContent='+searchContent;
		location.href="${pageContext.request.contextPath}/historymng/open/api/list.do?"+formData;
	}
</script>

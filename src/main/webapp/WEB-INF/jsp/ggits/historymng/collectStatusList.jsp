<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<main>
	<div class="main_container">
		<aside class="snb_container">
			<div class="snb_wrap">
				<h3 class="side_title">데이터 수집 상태<br>이력 관리</h3>
				<div class="side_txt">
					데이터 수집 상태 이력 관리<br>정보를 조회합니다.
				</div>
			</div>
		</aside>
		<section class="main_section tab_set">
			<h2 class="blind">서버 제어 이력 관리</h2>
			<div class="table_btn_wrap clearfix tab_fc">
				<div class="btn_search_wrap float-left">
                    <a href="${pageContext.request.contextPath}/historymng/collect/status/list.do?linkedType=SMT000" class="is-dark-btn ${linkedType eq 'SMT000' ? 'is-darkgreen-btn':''}">지자체 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collect/status/list.do?linkedType=SMT001" class="is-dark-btn ${linkedType eq 'SMT001' ? 'is-darkgreen-btn':''}">외부기관 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collect/status/list.do?linkedType=SMT002" class="is-dark-btn ${linkedType eq 'SMT002' ? 'is-darkgreen-btn':''}">신호 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collect/status/list.do?linkedType=SMT003" class="is-dark-btn ${linkedType eq 'SMT003' ? 'is-darkgreen-btn':''}">빅데이터 저장 플랫폼</a>
                </div>
			</div>
			<div class="contents_wrap tab_area">
				<div class="tab1">
					<div class="group2">
						<div class="group_text2">주소 검색</div>
						<input type="text" placeholder="시/군/구/도로명을 입력해 주세요."
							class="input_same group_box">
						<div class="search_detail_btn">
							상세 검색 <i></i>
						</div>
					</div>
					<div class="search_detail_wrap">
						<form id="searchForm">
	                    	<input type="hidden" name="page" />
							<div class="group2">
								<div class="group_text2">시간 설정</div>
								<div class="flex-center">
									<div class="calendar">
										<input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="endTime"></select>
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
								<button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
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
							<col style="width: 28%">
							<col style="width: 12%">
							<col style="width: 10%">
							<col style="width: 12%">
							<col style="width: 15%">
							<col style="width: 15%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>요청 프로세스 ID</th>
								<th><select class="table-filter">
										<option selected="selected">대상 서버</option>
										<option>전체</option>
										<option>WEB서버#1</option>
										<option>WEB서버#2</option>
								</select></th>
								<th><select class="table-filter">
										<option selected="selected">성공 여부</option>
										<option>전체</option>
										<option>성공</option>
										<option>실패</option>
								</select></th>
								<th>요청자</th>
								<th>요청일시</th>
								<th>응답완료일시</th>
							</tr>
						</thead>
						<tbody>
						   <c:choose>
                        		<c:when test="${not empty collectStatusList}">
									<c:forEach var="collectStatusList" items="${collectStatusList}">
										<tr>
											<td>${collectStatusList.rownum}</td>
											<td>${collectStatusList.dsetId}</td>
											<td>WEB 서버#1(컬럼 확인 필요)</td>
											<td><c:choose>
													<c:when test="${collectList.prgrsStts eq 'ERROR'}">
													실패
												</c:when>
													<c:otherwise>
													성공
												</c:otherwise>
												</c:choose></td>
											<td>admin1234[김철수](컬럼 확인 필요)</td>
											<td>${collectStatusList.clctStartDt}</td>
											<td>${collectStatusList.clctEndDt}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
						             <tr>
										<td colspan="7">조회된 결과가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
				</div>
			</div>
		</section>
	</div>
</main>

<script>
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/historymng/collect/status/list";
		document.getElementById('searchForm').submit();
	}	
</script>

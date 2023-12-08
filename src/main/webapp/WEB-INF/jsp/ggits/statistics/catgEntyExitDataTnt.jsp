<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">데이터 활용 통계</h3>
	        <div class="side_txt">
	            데이터 활용 통계 및 분석<br>자료입니다.
	        </div>
	        <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do">서비스 이력</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do" class="on">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do">이력 집계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">데이터 활용 통계</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	           <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do'" >지역별 진출입 데이터 집계</button>
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/impact/catg_enty_exit_data_tnt/list.do'" >유형별 진출입 데이터 집계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
	        	<form>
		            <div class="group2">
						<div class="flex-center gap16">
			            	<div class="flex-center gap80">
				                <div class="group_text2">지역</div>
					            <select class="selectBox">
		                            <option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
	                					<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
									</c:forEach>
		                        </select>
			            	</div>
			            	<div class="flex-center gap16">
				                <div class="group_text3">유형별</div>
					            <select class="selectBox">
		                            <option selected="selected">전체</option>
		                            <option>통근</option>
		                            <option>통학</option>
		                            <option>학원</option>
		                            <option>업무/출장</option>
		                            <option>귀사</option>
		                            <option>쇼핑</option>
		                            <option>여가 운동 관광 레저</option>
		                            <option>외식</option>
		                            <option>친지방문</option>
		                            <option>기타(병원방문,종교,개인용무)</option>
		                        </select>
			            	</div>
		            	</div>
		            	<div class="search_detail_btn">
		                    상세 검색 <i></i>
		                </div>
		            </div>
		            <div class="search_detail_wrap">
		                <div class="group2">
		                    <div class="group_text2">기간 설정</div>
		                    <div class="flex-center">
		                        <div class="calendar">
									<input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
									<select class="selectBox selectTime" id="startTime"></select>
                                    ~
                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" placeholder="날짜를 선택해주세요.">
									<select class="selectBox selectTime" id="endTime"></select>
		                        </div>
		                    </div>
		                </div>
	                    <div class="group2">
	                        <div class="group_text2">요일 설정</div>
	                        <div class="flex-center gap24">
	                            <div>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                            </div>
	                        </div>
	                    </div>		            
		            </div>
	                <div class="group2_btn">
						<!-- button id  name 바꿔서 사용하세요  -->
						<button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
						<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span>"121534개"</span>의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn">엑셀 다운로드</button>
	                    </div>
	                </div>
	            </div>
	            <table class="mt16 table_border">
	                <colgroup>
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" rowspan="2">지역</th>
		                    <th scope="col" rowspan="2">기간</th>
		                    <th scope="col" colspan="2">주거시설</th>
		                    <th scope="col" colspan="2">근린시설</th>
		                    <th scope="col" colspan="2">공연집회</th>
		                    <th scope="col" colspan="2">문화시설</th>
		                </tr>
		                <tr>
		                    <th scope="col">상주/상근</th>
		                    <th scope="col">방문/이동</th>
		                    <th scope="col">상주/상근</th>
		                    <th scope="col">방문/이동</th>
		                    <th scope="col">상주/상근</th>
		                    <th scope="col">방문/이동</th>
		                    <th scope="col">상주/상근</th>
		                    <th scope="col">방문/이동</th>
		                </tr>
	                </thead>
	                <tbody>
		                <tr>
		                    <td class="left">수원</td>
		                    <td>2020.12</td>
		                    <td>88</td>
		                    <td>8</td>
		                    <td>??</td>
		                    <td>??</td>
		                    <td>22</td>
		                    <td>22</td>
		                    <td>24</td>
		                    <td>64</td>
		                </tr>
		                <tr>
		                    <td class="left">파주</td>
		                    <td>2020.12</td>
		                    <td>88</td>
		                    <td>8</td>
		                    <td>??</td>
		                    <td>??</td>
		                    <td>22</td>
		                    <td>22</td>
		                    <td>24</td>
		                    <td>64</td>
		                </tr>
	                </tbody>
	            </table>
			</div>
        </div>
	</section>
</div>
<script>

	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})
	

	
</script>
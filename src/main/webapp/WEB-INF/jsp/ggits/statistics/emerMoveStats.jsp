<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통 정보 통계 분석</h3>
	        <div class="side_txt">
	            교통 정보 통계 분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" class="on">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do">도로안전</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do">교통시설물 통계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do'" >교통현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">기상현황 / 별 교통량 / 돌발대기오염현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">긴급차량 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page"  value="1"/>
		            <div class="group2">
						<div class="flex-center gap16">
							<div class="group_text2">주소</div>
							<div class="flex-center gap16 pl64">
								<div class="group_text3">시군</div>
								<select class="selectBox">
									<option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
										<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
									</c:forEach>
								</select>
							</div>
							<div class="flex-center gap16">
								<div class="group_text3">검색</div>
								<input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="도로명을 입력해주세요.">
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
		                            <input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
		                            ~
		                            <input type="text" class="end_date_picker input_same ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                </div>
                            </div>
                        </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="flex-center gap24">
                                <div>
                                    <input type="hidden" name="dayOfTheWeekStr" id="dayOfTheWeekStr" value="${searchOption.dayOfTheWeekStr}">
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="1">일</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="2">월</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="3">화</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="4">수</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="5">목</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="6">금</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="7">토</label>
                                </div>
                            </div>
                        </div>
		            </div>
	                <div class="group2_btn">
						<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
						<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <colgroup>
	                    <col style="width:11%">
	                    <col style="width:15%">
	                    <col style="width:16%">
	                    <col style="width:16%">
	                    <col style="width:10%">
	                    <col style="width:16%">
	                    <col style="width:16%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">차량분류</th>
		                    <th scope="col">차량번호</th>
		                    <th scope="col">시작 도로명</th>
		                    <th scope="col">종착 도로명</th>
		                    <th scope="col">예상 운행시간</th>
		                    <th scope="col">실제 운행시간</th>
		                    <th scope="col">평균속도</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0}">
	                			<c:forEach var="statsInfo" items="${statsList}">
		                			<tr>
		                				<td class="left">-</td>
		                				<td>${statsInfo.evno}</td>
		                				<td>-</td>
		                				<td>-</td>
		                				<td>
		                					<fmt:formatNumber type="number"  pattern="0" value="${statsInfo.arrivaltime / 60}"/>분
		                				</td>
		                				<td>-</td>
		                				<td>${statsInfo.speed}km/h</td>
		                			</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="7">긴급차량 이동 정보가 없습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
<!-- 		                <tr> -->
<!-- 		                    <td class="left">구급차</td> -->
<!-- 		                    <td>389루 7458</td> -->
<!-- 		                    <td>안양1로</td> -->
<!-- 		                    <td>안양10로</td> -->
<!-- 		                    <td>16분</td> -->
<!-- 		                    <td>12분</td> -->
<!-- 		                    <td>80km/h</td> -->
<!-- 		                    <td>6</td> -->
<!-- 		                </tr> -->
<!-- 		                <tr> -->
<!-- 		                    <td class="left">소방차</td> -->
<!-- 		                    <td>389루 7456</td> -->
<!-- 		                    <td>안양10로</td> -->
<!-- 		                    <td>안양15로</td> -->
<!-- 		                    <td>30분</td> -->
<!-- 		                    <td>20분</td> -->
<!-- 		                    <td>90km/h</td> -->
<!-- 		                    <td>12</td> -->
<!-- 		                </tr> -->
	                </tbody>
	            </table>
		        <div>
	                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            </div>
		        <div class="table_chart_wrap">
		            <div class="table_chart_list">
                       	<div class="chart">
                       		<div class="tab_box_title left mb16">긴급차량 이동 건수 <span>총 400건</span></div>
                       		<div style="height:380px">
	                       		<canvas id="tab4_1_chart"></canvas>
                       		</div>
                       	</div>
                       	<div class="chart">
                       		<div class="tab_box_title left mb16">긴급차량 운행시간 감소율<span>(예상 운행 시간 대비 34% 감소)</span></div>
                       		<div style="height:380px">
	                       		<canvas id="tab4_2_chart"></canvas>
                       		</div>
                       	</div>
		                <div class="chart">
		                    <div class="chart_title">평균 신호 대기 시간</div>
		                    <div class="chart_title_sub">4분 30초</div>
		                </div>
		            </div>
		        </div>
	        </div>
        </div>
	</section>
</div>
<script>
	$(document).ready(function(){
		//<![CDATA[
			var strDt = '${searchOption.strDt}';
			var endDt = '${searchOption.endDt}';
			var searchContent = '${searchOption.searchContent}';
			var dayOfTheWeekStr = '${searchOption.dayOfTheWeekStr}';
		//]]>
	
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(!isNull(searchContent)){
			$("#searchContent").val(searchContent);
		}
		if(dayOfTheWeekStr != null && dayOfTheWeekStr != ''){
			var dayOfWeek = $(".dayOfWeek")
			if(dayOfTheWeekStr.indexOf(",")){
				var dayOfTheWeekStrArr = dayOfTheWeekStr.split(",");
				for(var i = 0; i < dayOfTheWeekStrArr.length; i++){
					for(var j = 0; j < dayOfWeek.length; j++){
						if(dayOfTheWeekStrArr[i] == dayOfWeek.eq(j).val()){
							dayOfWeek.eq(j).prop("checked",true);
							dayOfWeek.eq(j).parent('label').addClass("is-darkgreen-btn");
						}
					}
				}
			} else {
				for(var i = 0; i < dayOfWeek.length; i++){
					if(dayOfTheWeekStr == dayOfWeek.eq(i).val()){
						dayOfWeek.eq(i).prop("checked",true);
						dayOfWeek.eq(i).parent('label').addClass("is-darkgreen-btn");
					}
				}
			}
		}
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})

   	// tab4-1 chart
	new GITSChart(GITSChartType.DOUGHNUT).init("tab4_1_chart")
    .setDataSetLabel('구급차', '소방차', '경찰차', '경호차')
    .setDataSet({
            label: ['구급차', '소방차', '경찰차', '경호차'],
            data:[10, 10, 50, 60],
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();

    // tab4-2 chart
    new GITSChart(GITSChartType.BAR).init("tab4_2_chart")
    .setDataSetLabel('구급차','소방차','경찰차','경호차')
    .setDataSet({
            label:"예상 운행 시간",
            data:[300, 150, 200, 500],
            backgroundColor: '#3A7DFF',
            borderRadius:1,
            borderWidth:1,
            fill: false,
        },{
            label:"실제 운행 시간",
            data:[250, 120, 160, 400],
            backgroundColor: '#F90',
            borderRadius:1,
            borderWidth:1,
            fill: false,
    })
    .setTicksStep(100)
    .setLabelDisplay(false)
    .setAxis('x')
    .setBarGridY(true)
    .setBarGridX(false)
    .setAxisStackedX(false)
    .setAxisStackedY(false)
    .draw();
</script>
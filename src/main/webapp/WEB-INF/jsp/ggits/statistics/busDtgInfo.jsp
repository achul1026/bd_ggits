<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통 정보 통계 분석</h3>
	        <div class="side_txt">
	            교통 정보 통계 분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" class="on">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do">도로안전</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do">교통시설물 통계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do'">버스 DTG</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_rout/list.do'">정류장별 버스노선, 버스유형</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_facility_info/list.do'">정류장별 시설물 및 기타정보</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_pasnr/list.do'">정류장별 승하차 승객</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_rout_pasnr/list.do'">노선구간별 승하차/재차 승객수</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page" value="1"/>
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
								<input type="text" class="input_same" placeholder="도로명을 입력해주세요.">
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
									<select class="selectBox selectTime" id="startTime" name="startTime"></select>
                                    ~
                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
									<select class="selectBox selectTime" id="endTime" name="endTime"></select>
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
	                    <col style="width:7%">
	                    <col style="width:9%">
	                    <col style="width:9%">
	                    <col style="width:12%">
	                    <col style="width:12%">
	                    <col style="width:9%">
	                    <col style="width:9%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:9%">
	                    <col style="width:9%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">버스 번호</th>
		                    <th scope="col">
	                            <select class="table-filter">
		                            <option selected="selected">버스유형</option>
		                            <option>전체</option>
		                            <option>광역</option>
		                            <option>일반</option>
		                            <option>시내</option>
		                        </select>		    		                    
		                    </th>
		                    <th scope="col">차량번호</th>
		                    <th scope="col">출발지</th>
		                    <th scope="col">종착지</th>
		                    <th scope="col">평균 배차 간격</th>
		                    <th scope="col">운행시간</th>
		                    <th scope="col">운행거리</th>
		                    <th scope="col">평균속도</th>
		                    <th scope="col">위험판정</th>
		                    <th scope="col">노선도</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0}">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr>
	                					<td>${statsInfo.busRouteNo}</td>
	                					<td>-</td>
	                					<td>${statsInfo.vhclNo}</td>
	                					<td>${statsInfo.stStaNm}</td>
	                					<td>${statsInfo.edStaNm}</td>
	                					<td>${statsInfo.routeInterval}</td>
	                					<td>-</td>
	                					<td>${statsInfo.routeLength}</td>
	                					<td>-</td>
	                					<td>${statsInfo.riskJugCnt}</td>
	                					<td><button type="button">[보기]</button></td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="11">정보가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            <div class="table_chart_wrap">
					<div class="table_chart_sub_text">
						<div class="chart_content_text">
					        평균 운행 대수
					        <p>230대</p>
					    </div>
					    <div class="chart_content_text">
					        실제 운행 비율
					        <p>24%</p>
					    </div>
					    <div class="chart_content_text">
					        1대당 평균 운행 시간
					        <p>116분</p>
					    </div>
					    <div class="chart_content_text">
					        1대당 평균 운행 속도
					        <p>62km/h</p>
					    </div>
					    <div class="chart_content_text">
					        1대당 평균 운행 거리
					        <p>50km</p>
					    </div>
					</div>
                    <div class="table_chart_list block">
                        <div class="chart wd100">
                            <div class="tab_box_title left mb16">시간대별 위험 측정 대수</div>
	                 		<div style="height:380px">
	                  			<canvas id="tab1_1_chart"></canvas>
	                 		</div>                                    
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
			var strTime = '${searchOption.startTime}';
			var endTime = '${searchOption.endTime}';
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
		if(strTime != null && strTime != ''){
			$("#startTime").val(strTime).prop("selected",true);
		}
		if(endTime != null && endTime != ''){
			$("#endTime").val(endTime).prop("selected",true);
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
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
   	// tab1-1 chart
    new GITSChart(GITSChartType.LINE).init("tab1_1_chart")
    .setDataSetLabel('20~21시','21~22시','22~23시','23~24시')
    .setDataSet({
        label:'80번',
        data: [0, 5, 3, 1],
        backgroundColor:'#FF6B6B',
        borderWidth:3,
        borderColor:'#FF6B6B',
        fill: false
    }, {
        label:'70번',
        data: [5, 1, 6, 2],
        backgroundColor:'#FF9900',
        borderWidth:3,
        borderColor:'#FF9900',
        fill: false
    }, {
        label:'320번',
        data: [3, 4, 6, 0],
        backgroundColor:'#2E8EFF',
        borderWidth:3,
        borderColor:'#2E8EFF',
        fill: false
    })

    .setTicksStep(2)
    .draw();
</script>
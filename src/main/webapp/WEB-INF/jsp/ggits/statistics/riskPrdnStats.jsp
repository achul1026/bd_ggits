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
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">긴급차량 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
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
	                        <span>"${totalCnt}개"</span>의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <colgroup>
	                    <col style="width:22.5%">
	                    <col style="width:22.5%">
<%-- 	                    <col style="width:11%"> --%>
<%-- 	                    <col style="width:11%"> --%>
<%-- 	                    <col style="width:11%"> --%>
	                    <col style="width:11%">
<%-- 	                    <col style="width:11%"> --%>
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">시작 도로명</th>
		                    <th scope="col">종착 도로명</th>
<!-- 		                    <th scope="col">주의 등급 횟수</th> -->
<!-- 		                    <th scope="col">위험 등급 횟수</th> -->
<!-- 		                    <th scope="col">심각 등급 횟수</th> -->
		                    <th scope="col">사고 횟수</th>
<!-- 		                    <th scope="col">통제 횟수</th> -->
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0 }">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr>
<!-- 	                					TODO 컬럼 추가 후 작업 -->
	                					<td class="left">${statsInfo.strRoadName}</td>
	                					<td>${statsInfo.endRoadName}</td>
<!-- 	                					<td>-</td> -->
<!-- 	                					<td>-</td> -->
<!-- 	                					<td>-</td> -->
	                					<td>${statsInfo.acdntOccurCnt}</td>
<!-- 	                					<td>-</td> -->
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="3">위험구간정보가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
                <div>
	                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            </div>
                <div class="table_chart_wrap">
                    <div class="table_chart_list">
<!--                        	<div class="chart"> -->
<!--                        		<div class="tab_box_title left mb16">위험 구간 발생 빈도 <span>총 400건</span></div> -->
<!--                        		<div style="height:380px"> -->
<%-- 	                       		<canvas id="tab6_1_chart"></canvas> --%>
<!--                        		</div> -->
<!--                        	</div> -->
                       	<div class="chart">
                       		<div class="tab_box_title left mb16">사고 유형</div>
                       		<div style="height:380px">
	                       		<canvas id="tab6_2_chart"></canvas>
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
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
   	// tab6-1 chart
	new GITSChart(GITSChartType.DOUGHNUT).init("tab6_1_chart")
    .setDataSetLabel('주의', '위험', '심각')
    .setDataSet({
            label: ['주의', '위험', '심각'],
            data:[10, 10, 50],
            backgroundColor: ['#00BCB1', 'red',  'yellow'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();    
    
    // tab6-2 chart
    new GITSChart(GITSChartType.BAR).init("tab6_2_chart")
    .setDataSetLabel('사고유형1', '사고유형2', '사고유형3', '사고유형4')
    .setDataSet({
        label: '사고건수',
        data:[40, 20, 30, 60],
        backgroundColor: '#00BCB1',
        borderWidth:2
    })
    .setTickStepX(10)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .SetMaxWidth(80)
    .draw();
</script>
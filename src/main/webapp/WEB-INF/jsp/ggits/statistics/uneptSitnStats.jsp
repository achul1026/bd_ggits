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
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">기상현황 / 별 교통량 / 돌발대기오염현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">긴급차량 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab2">
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
		                            <select class="selectBox selectTime" name="startTime" id="startTime"></select>
		                            ~
		                            <input type="text" class="end_date_picker input_same ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	<select class="selectBox selectTime" name="endTime" id="endTime"></select>
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
						<!-- button id  name 바꿔서 사용하세요  -->
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
	                    <col style="width:11%">
	                    <col style="width:22%">
	                    <col style="width:22%">
	                    <col style="width:16%">
	                    <col style="width:12%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">돌발유형</th>
		                    <th scope="col">돌발상황</th>
		                    <th scope="col">발생시간</th>
		                    <th scope="col">종료시간</th>
		                    <th scope="col">도로명</th>
		                    <th scope="col">
	                            <select class="table-filter">
		                            <option selected="selected">차로</option>
		                            <option>전체</option>
		                            <option>일반</option>
		                            <option>버스전용</option>
		                        </select>    
		                    </th>
		                    <th scope="col">기관</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0}">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr>
	                					<td class="left">${statsInfo.inciCate}</td>
	                					<td>${statsInfo.description}</td>
	                					<td>
	                						<fmt:parseDate value="${statsInfo.beginDate}" var="beginDt" pattern="yyyyMMddHHmmss"/>
	                						<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${beginDt}"/>
	                					</td>
	                					<td>
	                						<fmt:parseDate value="${statsInfo.endDate}" var="endDt" pattern="yyyyMMddHHmmss"/>
	                						<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${endDt}"/>
	                					</td>
	                					<td>${fn:split(statsInfo.roadwayNm,'|')[0]}</td>
	                					<td>${statsInfo.occurredLane}</td>
	                					<td>${statsInfo.infoSrcOrg}</td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="7">돌발현황 정보가 존재하지 않습니다.</td>
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
                       	<div class="chart">
                       		<div class="tab_box_title left mb16">돌발 상황 비율 <span>총 ${totalCnt}건</span></div>
                       		<div style="height:380px">
	                       		<canvas id="tab2_1_chart"></canvas>
                       		</div>
                       	</div>
                       	<div class="chart">
                       		<div class="tab_box_title left mb16">시간대별 돌발 상황 발생 건수</div>
                       		<div style="height:380px">
	                       		<canvas id="tab2_2_chart"></canvas>
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
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do";
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
	
   	// tab2-1 chart
   	var uneptSitnRateLabelArray = '${uneptSitnRateLabelArray}';
   	var uneptSitnRateDataArray = '${uneptSitnRateDataArray}';
	new GITSChart(GITSChartType.DOUGHNUT).init("tab2_1_chart")
    .setDataSetLabel(uneptSitnRateLabelArray.split(',')[0],uneptSitnRateLabelArray.split(',')[1],uneptSitnRateLabelArray.split(',')[2],uneptSitnRateLabelArray.split(',')[3])
    .setDataSet({
            label: uneptSitnRateLabelArray.split(','),
            data: uneptSitnRateDataArray.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();

	// tab2-2 chart
	// TODO 변경 예정
	var timeDataArray = '${timeDataArray}';
	var contsCntArray = '${array66}';
	var evntCntArray = '${array77}';
	var cntlCntArray = '${array88}';
	var acdntCntArray = '${array99}';
    new GITSChart(GITSChartType.BAR).init("tab2_2_chart")
    .setDataSetLabel('0~1시','1~2시','2~3시','3~4시','4~5시','5~6시','6~7시','7~8시','8~9시','9~10시'
    				,'10~11시','11~12시','12~13시','13~14시','14~15시','15~16시','16~17시','17~18시','18~19시','19~20시'
    				,'20~21시','21~22시','22~23시','23~24시')
    .setDataSet({
            label: '사고',
            data: acdntCntArray.split(','),
            backgroundColor: '#00BCB1',
        },{
            label: '행사',
            data: evntCntArray.split(','),
            backgroundColor: '#FF5454',
        },{
            label: '통제',
            data: cntlCntArray.split(','),
            backgroundColor: '#F90',
        },{
            label: '공사',
            data: contsCntArray.split(','),
            backgroundColor: '#ACDC87',
    })
    .setTickStepX(1)
    .setAxis('x')
    .setBarGridX(false)
    .setBarGridY(true)
    .setLabelPadding(30)
    .draw();
</script>
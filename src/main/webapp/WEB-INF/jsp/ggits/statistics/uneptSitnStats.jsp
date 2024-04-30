<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통정보 통계분석</h3>
	        <div class="side_txt">
	            교통정보 통계분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" class="on" onclick="startLoading()">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?initAppchYn=Y" onclick="startLoading()">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" onclick="startLoading()">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" onclick="startLoading()">도로안전</a>
<%-- 	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do" onclick="startLoading()">교통시설물 통계</a> --%>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap_left btn_search_wrap">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do'" >교통현황 통계</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">지역별 대기질 통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">광역 긴급차량 이동 현황통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab2">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page"  value="1"/>
	        		<input type="hidden" id="selInciCate" name="selInciCate" />
		            <div class="group2">
							<div class="group_text2">검색</div>
							<div class="btn_search_wrap">
								<ul>
									<li>
										검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder ="돌발상황을 입력해주세요.">
									</li>
								</ul>
								
							</div>
						<div class="search_detail_btn">
							상세 검색 <i></i>
						</div>
					</div>
		            <div class="search_detail_wrap">
                        <div class="group2">
                            <div class="group_text2">기간 설정</div>
                            <div class="btn_search_wrap">
                                <ul class="">
                                	<li>
                                		<input type="text" class="date_picker input_same  input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	</li>
<!-- 		                            <li> -->
<!-- 		                            	<select class="selectBox selectTime" name="startTime" id="startTime"></select> -->
<!-- 		                            </li> -->
		                            <li>
		                            	 ~
		                            </li>
		                            <li>
		                            	<input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
		                            </li>
<!-- 		                            <li> -->
<!-- 		                            	<select class="selectBox selectTime" name="endTime" id="endTime"></select> -->
<!-- 		                            </li> -->
                                </ul>
                            </div>
                        </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="btn_search_wrap">
                                <div>
                                    <input type="hidden" name="dayOfTheWeekStr" id="dayOfTheWeekStr" value="<c:out value='${searchOption.dayOfTheWeekStr}'/>">
                                   <ul>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="7">일</label>
                                   	  </li>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="1">월</label>
                                   	  </li>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="2">화</label>
                                   	  </li>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="3">수</label>
                                   	  </li>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="4">목</label>
                                   	  </li>
                                   	  <li>
                                   	  	 <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="5">금</label>
                                   	  </li>
                                   	  <li>
                                   	  	<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="6">토</label>
                                   	  </li>
                                   </ul>
                                </div>
                            </div>
                        </div>
		            </div>
	                <div class="btn_search_wrap btn_search_wrap_center">
	                	<ul>
	                		<li>
								<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
	                		</li>
	                		<li>
	                			<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
	                		</li>
	                	</ul>
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span id="totalCnt"><c:out value="${totalCnt}"/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <thead>
		                <tr>
		                    <th scope="col">도로명</th>
		                    <th scope="col">
	                            <select class="table-filter seltFilter selInciCate" name="selInciCate">
		                            <option selected="selected" value="all">돌발유형</option>
		                            <option value="1">추가청보필요</option>
		                            <option value="2">차량사고</option>
		                            <option value="3">차량고장</option>
		                            <option value="4">차량화재</option>
		                            <option value="5">장애물</option>
		                            <option value="6">장애물</option>
		                            <option value="7">위험물질방출</option>
		                            <option value="8">지진</option>
		                            <option value="9">산사태</option>
		                            <option value="10">홍수</option>
		                            <option value="11">태풍</option>
		                            <option value="12">예고되지않은 시위집회</option>
		                            <option value="13">차량의 급격한 증가</option>
		                        </select>		     		                    
		                    </th>
		                    <th scope="col">돌발상황</th>
		                    <th scope="col">발생시간</th>
		                    <th scope="col">종료시간</th>
		                    <th scope="col">차로</th>
		                    <th scope="col">기관</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0}">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr>
	                					<td><c:out value="${fn:split(statsInfo.roadwayNm,'|')[0]}"/></td>
	                					<td class=""><c:out value="${statsInfo.inciCate}"/></td>
	                					<td><c:out value="${statsInfo.description}"/></td>
	                					<td><c:out value="${statsInfo.beginDate}"/></td>
	                					<td><c:out value="${statsInfo.endDate ne null ? statsInfo.endDate : '-'}"/></td>
	                					<td><c:out value="${statsInfo.occurredLane}"/></td>
	                					<td><c:out value="${statsInfo.infoSrcOrg ne null and statsInfo.infoSrcOrg ne '' ? statsInfo.infoSrcOrg : '-'}"/></td>
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
                       		<div class="tab_box_title left mb16">돌발 상황 비율 <span id="unstCnt"></span></div>
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
	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))

	$(document).ready(function(){
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
// 		var strTime = '<c:out value="${searchOption.startTime}"/>';
// 		var endTime = '<c:out value="${searchOption.endTime}"/>';
		var searchContent = '<c:out value="${searchOption.searchContent}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
		var selInciCate = '<c:out value="${searchOption.selInciCate}"/>'

		//searchOption dataInit
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
		}
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
// 		if(strTime != null && strTime != ''){
// 			$("#startTime").val(strTime).prop("selected",true);
// 		}
// 		if(endTime != null && endTime != ''){
// 			$("#endTime").val(endTime).prop("selected",true);
// 		}
		if(selInciCate != null && selInciCate != ''){
			$(".selInciCate").val(selInciCate).prop("selected",true);
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
	
	$(".selInciCate").on("change", function(){
		$("#selInciCate").val($(this).val());
		fnSearchList();
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
   	// tab2-1 chart
	var uneptSitnRateLabelArray = '<c:out value="${uneptSitnRateLabelArray}"/>';
	var unstCnt = uneptSitnRateLabelArray.split(',').length > 1 ? uneptSitnRateLabelArray.split(',').length : 0;
	$("#unstCnt").text("총 "+unstCnt+"건");
	var uneptSitnRateDataArray = '<c:out value="${uneptSitnRateDataArray}"/>';
	new GITSChart(GITSChartType.DOUGHNUT).init("tab2_1_chart")
    .setData({
             labels: uneptSitnRateLabelArray.split(','),
             datasets: [{
            	 label:'발생건수',
            	 data: uneptSitnRateDataArray.split(','),
            	 backgroundColor:['#8F5AFF', '#FF8A00',  '#FF4646', '#446DFF', '#8CBD00', '#00BCB1', '#EFC900'],
            	 borderColor:'transparent'
             }]
         })	
	.setLabelPadding(30)
	.draw();

	// tab2-2 chart
	var timeDataArray = '<c:out value="${timeDataArray}"/>';
	var array1 = '<c:out value="${array1}"/>';
	var array2 = '<c:out value="${array2}"/>';
	var array3 = '<c:out value="${array3}"/>';
	var array4 = '<c:out value="${array4}"/>';
	var array5 = '<c:out value="${array5}"/>';
	var array6 = '<c:out value="${array6}"/>';
	var array7 = '<c:out value="${array7}"/>';
	var array8 = '<c:out value="${array8}"/>';
	var array9 = '<c:out value="${array9}"/>';
	var array10 = '<c:out value="${array10}"/>';
	var array11 = '<c:out value="${array11}"/>';
	var array12 = '<c:out value="${array12}"/>';
	var array13 = '<c:out value="${array13}"/>';
    new GITSChart(GITSChartType.BAR).init("tab2_2_chart")
     .setData({
             labels: timeDataArray.split(','),
             datasets: [{
            	 label:'추가정보필요',
            	 data: array1.split(','),
            	 backgroundColor:'#EA1C07'
             },{
            	 label:'차량사고',
            	 data: array2.split(','),
            	 backgroundColor:'#EA640C'
             },{
            	 label:'기상관련사고',
            	 data: array3.split(','),
            	 backgroundColor:'#F88C15'
             },{
            	 label:'차량고장',
            	 data: array4.split(','),
            	 backgroundColor:'#FBD01F'
             },{
            	 label:'차량화재',
            	 data: array5.split(','),
            	 backgroundColor:'#FEEC25'
             },{
            	 label:'장애물',
            	 data: array6.split(','),
            	 backgroundColor:'#E2E01C'
             },{
            	 label:'위험물질방출',
            	 data: array7.split(','),
            	 backgroundColor:'#9CBD24'
             },{
            	 label:'지진',
            	 data: array8.split(','),
            	 backgroundColor:'#66A530'
             },{
            	 label:'산사태',
            	 data: array9.split(','),
            	 backgroundColor:'#118D44'
             },{
            	 label:'홍수',
            	 data: array10.split(','),
            	 backgroundColor:'#0B8B74'
             },{
            	 label:'태풍',
            	 data: array11.split(','),
            	 backgroundColor:'#058BA6'
             },{
            	 label:'예고되지않은 시위집회',
            	 data: array12.split(','),
            	 backgroundColor:'#068FDD'
             },{
            	 label:'차량의 급격한 증가',
            	 data: array13.split(','),
            	 backgroundColor:'#0696F7'
             }]
         })	
    .setTickStepX(1)
    .setAxis('x')
    .setBarGridX(false)
    .setBarGridY(true)
    .setLabelPadding(30)
    .draw();
</script>
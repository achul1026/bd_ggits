<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통정보 통계분석</h3>
	        <div class="side_txt">
	            교통정보 통계분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" onclick="startLoading()">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?initAppchYn=Y" onclick="startLoading()">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" onclick="startLoading()">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" class="on" onclick="startLoading()">대중교통 지표 총괄 통계</a>
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
	        			 <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do'">버스 DTG</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_rout/list.do'">정류장별 버스노선, 버스유형</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_facility_info/list.do'">정류장별 시설물 및 기타정보</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_pasnr/list.do'">정류장별 승하차 승객</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_rout_pasnr/list.do'">노선구간별 승하차/재차 승객수</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area"></div>
	        <div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page" value="1"/>
		            <div class="group2">
							<div class="group_text2">주소</div>
							<div class="btn_search_wrap">
								<ul>
									<li>
										시군 : 
										<select class="selectBox" name="sigunCdId" id="sigunCdId">
											<option value="searchAllLocation">전체</option>
											<c:forEach var="sggCdList" items="${sggCdList}">
												<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
											</c:forEach>
										</select>
									</li>
									<li>
										검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="노선명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
                                <ul>
                                	<li>
                                		<input type="text" class="date_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	</li>
                                	<li>
                                		~
                                	</li>
                                	<li>
                                		 <input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	</li>
                                </ul>
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
	                        <span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <thead>
		                <tr>
		                    <th scope="col" class="left">버스 번호</th>
		                    <th scope="col">차량번호</th>
		                    <th scope="col">출발지</th>
		                    <th scope="col">종착지</th>
		                    <th scope="col">평균 배차 간격</th>
		                    <th scope="col">운행거리</th>
		                    <th scope="col">위험판정</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0}">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr onclick="findStatsInfo('<c:out value='${statsInfo.routeId}'/>','<c:out value='${statsInfo.routeNm}'/>');" class="pointer">
	                					<td class="left"><c:out value='${statsInfo.routeNm}'/></td>
	                					<td><c:out value='${statsInfo.vhclNo}'/></td>
	                					<td><c:out value='${statsInfo.stStaNm}'/></td>
	                					<td><c:out value='${statsInfo.edStaNm}'/></td>
	                					<td><c:out value='${statsInfo.routeInterval ne null and statsInfo.routeInterval ne "" ? statsInfo.routeInterval : "-"}'/></td>
	                					<td><c:out value='${statsInfo.routeLength}'/></td>
	                					<td><fmt:formatNumber value="${statsInfo.riskJugCnt }" pattern="#,###" /></td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="7">정보가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            <div class="tab_chart_container mt32">
	            	<div class="table_chart_text">
					    <span id="chartTrgtRouteNm" class="chart-rute"><c:out value='${routeNm}'/></span> 노선의 시간대별 위험 발생 수
					</div>
	            	<div class="chart_set">
					    <div class="group_btn_wrap chart_fc">
					        <div class="btn_search_wrap btn_search_wrap_left">
					        	<ul>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" data-chart="1">전체</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="2">과속 운행</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="3">급가속 운행</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="4">급감속 운행</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="5">급정지 운행</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="6">급출발 운행</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-chart="7">장기과속 운행</button>
					        		</li>
					        	</ul>
					        </div>
					    </div>
					    <div class="chart_area">
					    	<div class="chart1 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">전체</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart1"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart2 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">과속 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart2"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart3 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">급가속 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart3"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart4 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">급감속 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart4"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart5 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">급정지 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart5"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart6 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">급출발 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart6"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart7 chartPart">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">장기과속 운행</div>
			                        	<div style="height:380px" class="chartDiv">
			                        		<canvas id="chart7"></canvas>
			                        	</div>
			                        </div>
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
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		var searchContent = '<c:out value="${searchOption.searchContent}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
	
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
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
	
	// tab1 chart tab
    $(".chart_fc div button").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-chart"); i++){
                if($(this).attr("data-chart") == i){
                    $(this).closest(".chart_set").find(".chart_area").children(".chart"+i).show();
                    $(this).closest(".chart_set").find(".chart_area").children(".chart"+i).siblings().hide();
                }
            }
        });
    });
	
	function findStatsInfo(routeId, routeNm){
		$(".chartDiv").each(function(index){
			$(this).empty();
			
			var chartDiv = '<canvas id="chart'+(index+1)+'"></canvas>';
        	$(this).append(chartDiv);
		})
		
		$(".chart_fc > .tab_btn_item").each(function(){
			var chartNum = $(this).data("chart");
			if(chartNum == 1){
				$(this).addClass("is-darkgreen-btn");			
			}else{
				$(this).removeClass("is-darkgreen-btn");							
			}
		})
		$(".chartPart").each(function(){
			if($(this).hasClass("chart1")){
				$(this).removeClass("tab-none");
				$(this).css("display","block"); 
			}else{
				$(this).addClass("tab-none");				
				$(this).css("display","none"); 
			}
		})
		$.ajax({
   			type : "get",
   			url : "${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/data.ajax",
   			data : {
   				"routeId" : routeId
   			},
   			success: function(result) {
   				$("#chartTrgtRouteNm").text(routeNm);
   				var graphTimeArr = result.data.graphTimeArr;
   				var riskJugCntArr = result.data.riskJugCntArr;
   				var spdngRungCntArr = result.data.spdngRungCntArr;
   				var sdacelRungCntArr = result.data.sdacelRungCntArr;
   				var rpddclRungCntArr = result.data.rpddclRungCntArr;
   				var sdstopRungCntArr = result.data.sdstopRungCntArr;
   				var sdstrtRungCntArr = result.data.sdstrtRungCntArr;
   				var lngtrmaclRungCntArr = result.data.lngtrmaclRungCntArr;
   				// chart1
   			    new GITSChart(GITSChartType.BAR).init("chart1")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'과속 운행',
   			            	 data: spdngRungCntArr.split(','),
   			            	 backgroundColor:'#FF3131'
   			             },{
   			            	label:'급가속 운행',
  			            	 data: sdacelRungCntArr.split(','),
  			            	 backgroundColor:'#FF9900'
   			             },{
   			            	label:'급감속 운행',
  			            	 data: rpddclRungCntArr.split(','),
  			            	 backgroundColor:'#00BC29'
   			             },{
   			            	label:'급정지 운행',
  			            	 data: sdstopRungCntArr.split(','),
  			            	 backgroundColor:'#8CBD00'
   			             },{
   			            	label:'급출발 운행',
  			            	 data: sdstrtRungCntArr.split(','),
  			            	 backgroundColor:'#00A3FF'
   			             },{
   			            	label:'정지가속 운행',
  			            	 data: lngtrmaclRungCntArr.split(','),
  			            	 backgroundColor:'#EFE500'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   				
   				// chart2
   			    new GITSChart(GITSChartType.BAR).init("chart2")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'과속 운행',
   			            	 data: spdngRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   				
   				// chart3
   			    new GITSChart(GITSChartType.BAR).init("chart3")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'급가속 운행',
   			            	 data: sdacelRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   				
   				// chart4
   			    new GITSChart(GITSChartType.BAR).init("chart4")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'금감속 운행',
   			            	 data: rpddclRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   				
   				// chart5
   			    new GITSChart(GITSChartType.BAR).init("chart5")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'급정지 운행',
   			            	 data: sdstopRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   			
   				// chart6
   			    new GITSChart(GITSChartType.BAR).init("chart6")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'급출발 운행',
   			            	 data: sdstrtRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   			
   			// chart7
   			    new GITSChart(GITSChartType.BAR).init("chart7")
   			    .setData({
   			             labels: graphTimeArr.split(','),
   			             datasets: [{
   			            	 label:'장기과속 운행',
   			            	 data: lngtrmaclRungCntArr.split(','),
   			            	 backgroundColor:'#00bcb1'
   			             }]
   			         })	
   			    .setTickStepX(1)
			    .setAxis('x')
			    .setBarGridX(false)
			    .setBarGridY(true)
			    .setLabelPadding(30)
   			    .draw();
   			}
   		});
	}
	
	var sigunColorEx = ['#FF6666', '#FF8B66', '#FF9D66', '#FFB966', '#FFD466', '#FFF066', '#F3FF66', '#C5FF66', '#8EFF66', '#66FF75', '#66FF9A', '#66FFBF', '#66FFF6', '#66E3FF', '#66B6FF', '#669AFF', '#6688FF', '#6675FF', '#666CFF', '#7B66FF', '#9766FF', '#A966FF', '#BC66FF', '#D766FF', '#F366FF', '#FF66D4', '#FF66B9', '#FF668B', '#FF778F', '#C9C9C9', '#FFFFFF'];

	var graphTimeArr = '<c:out value="${graphTimeArr}"/>';
	var riskJugCntArr = '<c:out value="${riskJugCntArr}"/>';
	var spdngRungCntArr = '<c:out value="${spdngRungCntArr}"/>';
	var sdacelRungCntArr = '<c:out value="${sdacelRungCntArr}"/>';
	var rpddclRungCntArr = '<c:out value="${rpddclRungCntArr}"/>';
	var sdstopRungCntArr = '<c:out value="${sdstopRungCntArr}"/>';
	var sdstrtRungCntArr = '<c:out value="${sdstrtRungCntArr}"/>';
	var lngtrmaclRungCntArr = '<c:out value="${lngtrmaclRungCntArr}"/>';
	// chart1
	    new GITSChart(GITSChartType.BAR).init("chart1")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'과속 운행',
	            	 data: spdngRungCntArr.split(','),
	            	 backgroundColor:'#FF3131'
	             },{
	            	label:'급가속 운행',
	            	 data: sdacelRungCntArr.split(','),
	            	 backgroundColor:'#FF9900'
	             },{
	            	label:'급감속 운행',
	            	 data: rpddclRungCntArr.split(','),
	            	 backgroundColor:'#00BC29'
	             },{
	            	label:'급정지 운행',
	            	 data: sdstopRungCntArr.split(','),
	            	 backgroundColor:'#8CBD00'
	             },{
	            	label:'급출발 운행',
	            	 data: sdstrtRungCntArr.split(','),
	            	 backgroundColor:'#00A3FF'
	             },{
	            	label:'정지가속 운행',
	            	 data: lngtrmaclRungCntArr.split(','),
	            	 backgroundColor:'#EFE500'
	             }]
	         })	
		    .setTickStepX(1)
		    .setAxis('x')
		    .setBarGridX(false)
		    .setBarGridY(true)
		    .setLabelPadding(30)
	   		.draw();
		
		// chart2
	    new GITSChart(GITSChartType.BAR).init("chart2")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'과속 운행',
	            	 data: spdngRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
		
		// chart3
	    new GITSChart(GITSChartType.BAR).init("chart3")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'급가속 운행',
	            	 data: sdacelRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
		
		// chart4
	    new GITSChart(GITSChartType.BAR).init("chart4")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'금감속 운행',
	            	 data: rpddclRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
		
		// chart5
	    new GITSChart(GITSChartType.BAR).init("chart5")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'급정지 운행',
	            	 data: sdstopRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
	
		// chart6
	    new GITSChart(GITSChartType.BAR).init("chart6")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'급출발 운행',
	            	 data: sdstrtRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
	
	// chart7
	    new GITSChart(GITSChartType.BAR).init("chart7")
	    .setData({
	             labels: graphTimeArr.split(','),
	             datasets: [{
	            	 label:'장기과속 운행',
	            	 data: lngtrmaclRungCntArr.split(','),
	            	 backgroundColor:sigunColorEx
	             }]
	         })	
	    .setTickStepX(1)
	    .setAxis('x')
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .draw();
</script>
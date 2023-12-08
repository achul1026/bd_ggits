<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" class="on">도로안전</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do">교통시설물 통계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do'" >교통사고 발생이력</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acdnt_catg_log_colt/list.do'" >교통사고 유형별 이력집계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab1">
	        	<form id="searchForm" method="get">
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
                                    <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요." name="strDt" id="strDt" autocomplete="off">
									<select class="selectBox selectTime" name="startTime" id="startTime"></select>
                                    ~
                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" placeholder="날짜를 선택해주세요." name="endDt" id="endDt" autocomplete="off">
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
						<button type="button" class="is-darkgreen-btn" id="searchBtn"  onclick="fnSearchList();">찾기</button>
						<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span>"${totalCnt}개"</span>의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn" onclick="fnDownloadExcel();">엑셀 다운로드</button>
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
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" rowspan="2">도로명</th>
		                    <th scope="col" rowspan="2">발생건수</th>
		                    <th scope="col" rowspan="2">사상자수</th>
		                    <th scope="col" colspan="3">사고건수</th>
		                </tr>
		                <tr>
		                    <th scope="col">인구 10만명당</th>
		                    <th scope="col">자동차 1만대당</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(logInfoList) > 0 }">
	                			<c:forEach var="logInfo" items="${logInfoList}">
	                				<tr>
	                					<td class="left">${logInfo.roadName}</td>
	                					<td>
	                						<fmt:formatNumber value="${logInfo.acdntCnt}" pattern="#,###"/>
	                					</td>
	                					<td>
	                						<fmt:formatNumber value="${logInfo.dcsdCnt}" pattern="#,###"/>
	                					</td>
	                					<td>
	                						<fmt:formatNumber value="${logInfo.popltn100kAcdntCnt}" pattern="#,###"/>
	                					</td>
	                					<td>
	                						<fmt:formatNumber value="${logInfo.vhcl10kAcdntCnt}" pattern="#,###"/>
	                					</td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="5">교통사고 발생이력이 없습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <div class="tab_chart_container mt32">
	            	<div class="table_chart_text">
					    차트
					</div>
	            	<div class="chart_set">
					    <div class="group_btn_wrap chart_fc">
					        <div class="table_btn_left">
					            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" data-chart="1">발생건수</button>
					            <button type="button" class="tab_btn_item is-dark-btn" data-chart="2">사망자 수</button>
					            <button type="button" class="tab_btn_item is-dark-btn" data-chart="3">인구10만 사고건수</button>
					            <button type="button" class="tab_btn_item is-dark-btn" data-chart="4">차 1만 사고건수</button>
					        </div>
					    </div>
					    <div class="chart_area">
					    	<div class="chart1">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">발생건수</div>
			                        	<div style="height:380px">
			                        		<canvas id="chart1"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart2">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">사망자 수</div>
			                        	<div style="height:380px">
			                        		<canvas id="chart2"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart3">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">인구10만 사고건수</div>
			                        	<div style="height:380px">
			                        		<canvas id="chart3"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none chart4">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">차1만 사고건수</div>
			                        	<div style="height:380px">
			                        		<canvas id="chart4"></canvas>
			                        	</div>
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
		$('.search_head').removeClass('none');
	});
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do";
		document.getElementById('searchForm').submit();
	}	
	
	function fnDownloadExcel(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/common/excel/traffic_acndt_gen_log/download.do";
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
	
	// tab2 chart tab
    $(".tab_chart_fc div button").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-tab-chart"); i++){
                if($(this).attr("data-tab-chart") == i){
                    $(this).closest(".tab_chart_set").find(".tab_chart_area").children(".tab_chart"+i).show();
                    $(this).closest(".tab_chart_set").find(".tab_chart_area").children(".tab_chart"+i).siblings().hide();
                }
            }
        });
    });
	
	
	// chart1
    new GITSChart(GITSChartType.BAR).init("chart1")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 4, 1, 2, 3, 4, 5, 5, 4, 3, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart2
    new GITSChart(GITSChartType.BAR).init("chart2")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [6, 1, 4, 1, 2, 1, 3, 4, 6, 5, 3, 4, 3, 5, 1, 2, 3, 4, 5, 6, 4, 1, 2, 4, 5, 6, 1, 4, 1, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart3
    new GITSChart(GITSChartType.BAR).init("chart3")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 4, 1, 2, 3, 4, 5, 5, 4, 3, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart4
    new GITSChart(GITSChartType.BAR).init("chart4")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [6, 1, 4, 1, 2, 1, 3, 4, 6, 5, 3, 4, 3, 5, 1, 2, 3, 4, 5, 6, 4, 1, 2, 4, 5, 6, 1, 4, 1, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart5
    new GITSChart(GITSChartType.BAR).init("chart5")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 4, 1, 2, 3, 4, 5, 5, 4, 3, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart6
    new GITSChart(GITSChartType.BAR).init("chart6")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [6, 1, 4, 1, 2, 1, 3, 4, 6, 5, 3, 4, 3, 5, 1, 2, 3, 4, 5, 6, 4, 1, 2, 4, 5, 6, 1, 4, 1, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart7
    new GITSChart(GITSChartType.BAR).init("chart7")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 4, 1, 2, 3, 4, 5, 5, 4, 3, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	// chart8
    new GITSChart(GITSChartType.BAR).init("chart8")
    .setDataSetLabel('고양시','과천시','광명시','광주시','구리시','군포시','김포시','남양주시','동두천시','부천시','성남시','수원시'
    				,'시흥시','안산시','안성시','안양시','양주시','여주시','오산시','용인시','의왕시','의정부시','이천시','파주시','평택시'
    				,'포천시','하남시','화성시')
    .setDataSet({
        label:'발생건수',
        data: [6, 1, 4, 1, 2, 1, 3, 4, 6, 5, 3, 4, 3, 5, 1, 2, 3, 4, 5, 6, 4, 1, 2, 4, 5, 6, 1, 4, 1, 2],
        backgroundColor:'#00bcb1'
    })
    .setLabelDisplay(false)
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
</script>
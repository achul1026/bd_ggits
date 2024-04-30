<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" class="on" onclick="startLoading()">도로안전</a>
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
	        			 <button type="button" class="tab_btn_item is-dark-btn " onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do'" >교통사고 발생이력</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acdnt_catg_log_colt/list.do'" >교통사고 유형별 이력집계</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab2">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page"  value="1"/>
		            <div class="group2">
		                <div class="group_text2">유형</div>
		                <div class="btn_search_wrap">
		                	<ul>
		                		<li>
		                			<select class="selectBox" name="selAcdntTp" id="selectAcdntType">
			                            <option selected="selected" value="acdnt">사고유형별</option>
			                            <option value="link">도로형태별</option>
			                        </select>
		                		</li>
		                	</ul>
		                </div>
		            </div>	        	
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
			            				검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="도로명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
                                <ul class="calendar">
                                	<li>
                                		<input type="text" class="date_picker input_same input_picker" placeholder="날짜를 선택해주세요." name="strDt" id="strDt" autocomplete="off">
                                	</li>
<!--                                 	<li> -->
<!--               							 <select class="selectBox selectTime" id="startTime" name="startTime"></select> -->
<!--                                 	</li> -->
                                    <li>
              							 ~
                                	</li>
									<li>
             							<input type="text" class="end_date_picker input_same input_picker" placeholder="날짜를 선택해주세요." name="endDt" id="endDt" autocomplete="off">
                                	</li>
<!--                                     <li> -->
<!--               							<select class="selectBox selectTime" id="endTime" name="endTime"></select> -->
<!--                                 	</li> -->
                                </ul>
                            </div>
                        </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="btn_search_wrap">
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
	                        <span id="totalCnt1"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn mj0" onclick="fnDownloadExcel();">엑셀 다운로드</button>
	                    </div>
	                </div>
	            </div>
	            <table class="mt16 table_border">
	                <thead>
		                <tr class="tr_border">
		                    <th scope="col" class="">도로명</th>
		                    <c:choose>
		                    	<c:when test="${searchOption.selAcdntTp eq 'link'}">
		                    		<th scope="col">도로유형</th>	
		                    	</c:when>
		                    	<c:otherwise>
		                    		<th scope="col" rowspan="2">사고유형</th>	
		                    	</c:otherwise>
		                    </c:choose>
		                    <th scope="col">사고건수</th>
		                    <th scope="col">사상자수</th>
		                    <th scope="col">사망자수</th>
		                    <th scope="col">치사율(%)</th>
		                </tr>
		                <tr class="tr_total">
		                	<c:set var="acdntTotalCnt" value="0"/>
		                	<c:set var="totalDcsdCnt" value="0"/>
		                	<c:set var="totCasltCnt" value="0"/>
		                	<c:set var="totalFtltyRate" value="0"/>
		                	<c:forEach var="logInfo" items="${logInfoList}">
		                		<c:set var="acdntTotalCnt" value="${acdntTotalCnt + logInfo.acdntOccurCnt}"/>
		                		<c:set var="totalDcsdCnt" value="${totalDcsdCnt + logInfo.dcsdCnt}"/>
		                		<c:set var="totCasltCnt" value="${totCasltCnt + logInfo.totCasltCnt}"/>
		                	</c:forEach>
		                	<th scope="col" class="">합계</th>
		                	<c:if test="${searchOption.selAcdntTp eq 'link'}">
				                	<th scope="col"></th>
		                    </c:if>
		                	<th scope="col"><c:out value="${acdntTotalCnt}"/></th>
		                	<th scope="col"><c:out value="${totCasltCnt}"/></th>
		                	<th scope="col"><c:out value="${totalDcsdCnt}"/></th>
		                	<th scope="col">
		                		<c:set var="totalFtltyRate" value="${totalDcsdCnt / totCasltCnt * 100}"/>
		                		<c:choose>
		                			<c:when test="${not empty totalFtltyRate and totalDcsdCnt != 0}">
			                			<fmt:formatNumber type="number"  pattern="0.00" value="${((totalFtltyRate*100) - ((totalFtltyRate*100)%1)) * (1/100)}"/>
		                			</c:when>
		                			<c:otherwise>
		                				-
		                			</c:otherwise>
		                		</c:choose>
		                	</th>
		                </tr>
	                </thead>
	                <tbody>
		                <c:choose>
		                	<c:when test="${fn:length(logInfoList) > 0 }">
		                		<c:forEach var="logInfo" items="${logInfoList}">
		                			<tr class="">
		                				<td class=""><c:out value='${logInfo.roadName}'/></td>
					                    <c:choose>
					                    	<c:when test="${searchOption.selAcdntTp eq 'link'}">
					                    		<td><c:out value='${logInfo.roadRank}'/></td>
					                    	</c:when>
					                    	<c:otherwise>
					                    		<td><c:out value='${logInfo.acdntTypeCd}'/></td>		
					                    	</c:otherwise>
					                    </c:choose>
		                				<td><c:out value='${logInfo.acdntOccurCnt}'/></td>
		                				<td><c:out value='${logInfo.totCasltCnt}'/></td>
		                				<td><c:out value='${logInfo.dcsdCnt}'/></td>
		                				<td><c:out value='${logInfo.ftltyRate}'/></td>
		                			</tr>
		                		</c:forEach>
		                	</c:when>
		                	<c:otherwise>
		                		<tr>
		                			<td colspan="8">집계 이력이 존재하지 않습니다.</td>
		                		</tr>
		                	</c:otherwise>
		                </c:choose>
	                </tbody>
	            </table>
	            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            <div class="tab_chart_container mt32">
	            	<div class="table_chart_text">
					    차트
					</div>
	            	<div class="tab_chart_set">
					    <div class="group_btn_wrap tab_chart_fc">
					        <div class="btn_search_wrap btn_search_wrap_left">
					        	<ul>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" data-tab-chart="1">사고건수</button>
					        		</li>
					        		<li>
					        			 <button type="button" class="tab_btn_item is-dark-btn" data-tab-chart="2">사상자수</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-tab-chart="3">사망자수</button>
					        		</li>
					        		<li>
					        			<button type="button" class="tab_btn_item is-dark-btn" data-tab-chart="4">치사율</button>
					        		</li>
					        	</ul>
					        </div>
					    </div>
					    <div class="tab_chart_area">
					    	<div class="tab_chart1">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">사고건수</div>
			                        	<div style="height:380px">
			                        		<canvas id="tab_chart1"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none tab_chart2">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">사상자수</div>
			                        	<div style="height:380px">
			                        		<canvas id="tab_chart2"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none tab_chart3">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">사망자수</div>
			                        	<div style="height:380px">
			                        		<canvas id="tab_chart3"></canvas>
			                        	</div>
			                        </div>
		                     	</div>					    	
					    	</div>
					    	<div class="tab-none tab_chart4">
								<div class="table_chart_list">
			                        <div class="chart wd100">
			                        	<div class="tab_box_title left mb16">치사율</div>
			                        	<div style="height:380px">
			                        		<canvas id="tab_chart4"></canvas>
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
	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt1").text(numberComma(dataTotalCnt))
	
	$(document).ready(function(){
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
// 		var strTime = '<c:out value="${searchOption.startTime}"/>';
// 		var endTime = '<c:out value="${searchOption.endTime}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var selAcdntTp = '<c:out value="${searchOption.selAcdntTp}"/>';
	
		//searchOption dataInit
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
		if(selAcdntTp != null && selAcdntTp != ''){
			$("#selectAcdntType").val(selAcdntTp).prop("selected",true);
		}
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId).prop("selected",true);
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
	
	$("#selectAcdntType").on("change", function(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acdnt_catg_log_colt/list.do";
		document.getElementById('searchForm').submit();		
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acdnt_catg_log_colt/list.do";
		document.getElementById('searchForm').submit();
	}
	
	function fnDownloadExcel(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/common/excel/traffic_acdnt_catg_log_colt/download.do";
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

    var sigunColorEx = ['#FF6666', '#FF8B66', '#FF9D66', '#FFB966', '#FFD466', '#FFF066', '#F3FF66', '#C5FF66', '#8EFF66', '#66FF75', '#66FF9A', '#66FFBF', '#66FFF6', '#66E3FF', '#66B6FF', '#669AFF', '#6688FF', '#6675FF', '#666CFF', '#7B66FF', '#9766FF', '#A966FF', '#BC66FF', '#D766FF', '#F366FF', '#FF66D4', '#FF66B9', '#FF668B', '#FF778F', '#C9C9C9', '#FFFFFF'];

	// tab_chart1
	var acdntTypeCdArr = '<c:out value="${acdntTypeCdArr}"/>';
	var acdntOccurCntArr = '<c:out value="${acdntOccurCntArr}"/>';
	var totCasltCntArr = '<c:out value="${totCasltCntArr}"/>';
	var dcsdCntArr = '<c:out value="${dcsdCntArr}"/>';
	var ftltyRateArr = '<c:out value="${ftltyRateArr}"/>';
    new GITSChart(GITSChartType.BAR).init("tab_chart1")
    .setData({
             labels: acdntTypeCdArr.split(','),
             datasets: [{
            	 label:'발생건수',
            	 data: acdntOccurCntArr.split(','),
            	 backgroundColor:sigunColorEx
             }]
         })	
	.setTicksStep(100)         
    .setBarGridY(true)
    .setLabelDisplay(false)
    .draw();
	
	// tab_chart2
    new GITSChart(GITSChartType.BAR).init("tab_chart2")
     .setData({
             labels: acdntTypeCdArr.split(','),
             datasets: [{
            	 label:'발생건수',
            	 data: totCasltCntArr.split(','),
            	 backgroundColor:sigunColorEx
             }]
         })	
	.setTicksStep(20)         
    .setBarGridY(true)
    .setLabelDisplay(false)
    .draw();
 
	// tab_chart3
    new GITSChart(GITSChartType.BAR).init("tab_chart3")
     .setData({
             labels: acdntTypeCdArr.split(','),
             datasets: [{
            	 label:'발생건수',
            	 data: dcsdCntArr.split(','),
            	 backgroundColor:sigunColorEx
             }]
         })	
	.setTicksStep(20)         
    .setBarGridY(true)
    .setLabelDisplay(false)
    .draw();
 
	// tab_chart4
    new GITSChart(GITSChartType.BAR).init("tab_chart4")
     .setData({
             labels: acdntTypeCdArr.split(','),
             datasets: [{
            	 label:'발생건수',
            	 data: ftltyRateArr.split(','),
            	 backgroundColor:sigunColorEx
             }]
         })	
	.setTicksStep(20)         
    .setBarGridY(true)
   	.setLabelDisplay(false)
    .draw();
</script>
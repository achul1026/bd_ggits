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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" class="on">혼잡도 통계</a>
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
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do'">정체발생량</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_congest_stats/list.do'">혼잡도</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
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
	                    <col style="width:16%">
	                    <col style="width:12%">
<%-- 	                    <col style="width:12%"> --%>
<%-- 	                    <col style="width:12%"> --%>
<%-- 	                    <col style="width:12%"> --%>
	                    <col style="width:12%">
	                    <col style="width:12%">
<%-- 	                    <col style="width:12%"> --%>
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">도로명</th>
<!-- 		                    <th scope="col">혼잡 등급 횟수</th> -->
<!-- 		                    <th scope="col">지체 등급 횟수</th> -->
<!-- 		                    <th scope="col">서행 등급 횟수</th> -->
		                    <th scope="col">교통량</th>
		                    <th scope="col">평균속도</th>
		                    <th scope="col">
	                            <select class="table-filter">
		                            <option selected="selected">차로</option>
		                            <option>전체</option>
		                            <option>일반</option>
		                            <option>버스전용</option>
		                        </select>		            		                    
		                    </th>
<!-- 		                    <th scope="col"> -->
<!-- 	                            <select class="table-filter"> -->
<!-- 		                            <option selected="selected">기상</option> -->
<!-- 		                            <option>전체</option> -->
<!-- 		                            <option>맑음</option> -->
<!-- 		                            <option>비</option> -->
<!-- 		                            <option>눈</option> -->
<!-- 		                        </select>		            		                     -->
<!-- 		                    </th> -->
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(crowdedList) > 0 }">
	                			<c:forEach var="crowdedInfo" items="${crowdedList}">
	                				<tr>
	                					<td class="left">${crowdedInfo.acsRoadNm}</td>
<!-- 	                					<td>-</td> -->
<!-- 	                					<td>-</td> -->
<!-- 	                					<td>-</td> -->
	                					<td>${crowdedInfo.vhclTrfvlm}</td>
	                					<td>${crowdedInfo.avgVhclSpeed}</td>
	                					<td>${crowdedInfo.laneNo}</td>
<!-- 	                					<td>-</td> -->
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="8">정보가 없습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            <div class="table_chart_wrap">
	                <div class="table_chart_list">
<!--                        	<div class="chart"> -->
<%--                        		<div class="tab_box_title left mb16">정체 발생 빈도 <span>총 ${totalCnt}대</span></div> --%>
<!--                        		<div style="height:380px"> -->
<%-- 	                       		<canvas id="tab1_1_chart"></canvas> --%>
<!--                        		</div> -->
<!--                        	</div> -->
                       	<div class="chart">
		                    <div class="chart_title">평균 속도</div>
		                    <div class="chart_title_sub">${avgSpd}km/h</div>
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
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
   	// tab1-1 chart
	new GITSChart(GITSChartType.DOUGHNUT).init("tab1_1_chart")
    .setDataSetLabel('서행', '정체', '혼잡')
    .setDataSet({
            label: ['서행', '정체', '혼잡'],
            data:[10, 10, 50],
            backgroundColor: ['#00BCB1', 'red',  'yellow'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
</script>
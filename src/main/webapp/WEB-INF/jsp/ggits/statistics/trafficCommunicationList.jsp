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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do" class="on">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do">도로안전</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do">교통시설물 통계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <form id="searchForm" method="get">
		    <div class="group_btn_wrap tab_fc">
		        <div class="table_btn_left">
		        	<input type="hidden" id="tabNum" name="tabNum" value="${tabNum}">
		            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 1}">is-darkgreen-btn</c:if>" data-index="1">소통정보</button>
		            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 5}">is-darkgreen-btn</c:if>" data-index="5">수집원별 소통정보</button>
		        </div>
		    </div>
		    <div class="contents_wrap tab_area">
		        <div class="tab1">
		        		<c:if test="${tabNum ne 5}">
			        		<div class="group2">
				                <div class="group_text2">시계열</div>
				                <select class="selectBox selectTabMenu">
				                	<option <c:if test="${tabNum eq 1}">selected</c:if> data-index="1">시간</option>
				                	<option <c:if test="${tabNum eq 2}">selected</c:if> data-index="2">일별</option>
				                	<option <c:if test="${tabNum eq 3}">selected</c:if> data-index="3">월별</option>
				                	<option <c:if test="${tabNum eq 4}">selected</c:if> data-index="4">연도별</option>
				                </select>
				            </div>
		        		</c:if>
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
			                    <div class="group_text2">시간 설정</div>
			                    <div class="flex-center">
			                        <div class="calendar">
			                        	<c:choose>
			                        		<c:when test="${tabNum eq 1 }">
			                        			<input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
			                        			<select class="selectBox selectTime" name="startTime" id="startTime"></select>
					                            &nbsp; ~ &nbsp;
					                            <input type="text" class="end_date_picker input_same mr8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            <select class="selectBox selectTime" name="endTime" id="endTime"></select>
					                            &nbsp;
			                        		</c:when>
			                        		<c:when test="${tabNum eq 2 }">
			                        			<input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp; ~ &nbsp;
					                            <input type="text" class="end_date_picker input_same mr8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp;
			                        		</c:when>
			                        		<c:when test="${tabNum eq 3 }">
			                        			<input type="text" class="month_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp; ~ &nbsp;
			                        			<input type="text" class="month_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp;
			                        		</c:when>
			                        		<c:when test="${tabNum eq 4 }">
			                        			<input type="text" class="year_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp; ~ &nbsp;
			                        			<input type="text" class="year_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            &nbsp;
			                        		</c:when>
			                        		<c:otherwise>
			                        			<input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
			                        			<select class="selectBox selectTime" name="startTime" id="startTime"></select>
					                            &nbsp; ~ &nbsp;
					                            <input type="text" class="end_date_picker input_same mr8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
					                            <select class="selectBox selectTime" name="endTime" id="endTime"></select>
					                            &nbsp;
			                        		</c:otherwise>
			                        	</c:choose>			                           
					                            <label class="group_btn_item is-dark-btn"> <input type="checkbox" class="none">첨두</label>
					                            <label class="group_btn_item is-dark-btn"> <input type="checkbox" class="none">비첨두</label>
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
							<button type="button" class="is-darkgreen-btn" id="searchBtn"  onclick="fnSearchList();">찾기</button>
							<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
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
		                    <col style="width:40%">
		                    <col style="width:30%">
		                    <col style="width:30%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">도로명</th>
			                    <th scope="col">교통량</th>
			                    <th scope="col">평균속도</th>
			                </tr>
		                </thead>
		                <tbody>
		                	<c:choose>
		                		<c:when test="${fn:length(comunicationList) > 0}">
		                			<c:forEach var="comunicationInfo" items="${comunicationList}">
			                			<tr>
			                				<td class="left">${comunicationInfo.acsRoadNm}</td>
						                    <td>${comunicationInfo.vhclTrfvlm}</td>
						                    <td>${comunicationInfo.avgVhclSpeed}km/h</td>
			                			</tr>
		                			</c:forEach>
		                		</c:when>
		                		<c:otherwise>
		                			<td colspan="7">소통정보가 존재하지 않습니다.</td>
		                		</c:otherwise>
		                	</c:choose>
		                </tbody>
		            </table>
		            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            <div class="table_chart_wrap">
		                <div class="table_chart_list">
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_b">소통 원활</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">평균속도</th>
				                            </tr>
			                            </thead>
										<tbody>
											<c:choose>
												<c:when test="${fn:length(smoothList) > 0}">
													<c:forEach var="smoothInfo" items="${smoothList}" varStatus="status">
														<tr>
															<td>${status.index +1}</td>
						                                	<td>${smoothInfo.acsRoadNm}</td>
						                                	<td>${smoothInfo.avgVhclSpeed}km/h</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
						                			<td colspan="4">소통정보가 존재하지 않습니다.</td>
						                		</c:otherwise>
											</c:choose>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_g">소통 정체</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">평균속도</th>
				                            </tr>
			                            </thead>
										<tbody>
				                            <c:choose>
												<c:when test="${fn:length(jamList) > 0}">
													<c:forEach var="jamInfo" items="${jamList}" varStatus="status">
														<tr>
															<td>${status.index +1}</td>
						                                	<td>${jamInfo.acsRoadNm}</td>
						                                	<td>${jamInfo.avgVhclSpeed}km/h</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
						                			<td colspan="4">소통정보가 존재하지 않습니다.</td>
						                		</c:otherwise>
											</c:choose>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">차종 <span>총 ${totCntVhcl}대</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab1_1_chart"></canvas>
	                       		</div>
	                       	</div>
		                </div>
		            </div>
		        </div>
	        </div>
	    </form>
	</section>
</div>
<script type="text/javascript">
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
	
	/* 세부 검색 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	$(".tabMenu").on('click', function(){
		var tabNum = $(this).data("index");
		window.location.href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?tabNum="+tabNum;
	})
	
	$(".selectTabMenu").on('change', function(){
		var tabNum = $(".selectTabMenu option:selected").data("index");
		window.location.href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?tabNum="+tabNum;
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do";
		document.getElementById('searchForm').submit();
	}	
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
	$(function(){
// 		연도만
		$('.year_picker').datepicker( {
		    yearRange: "c-20:c",
		    changeMonth: false,
		    changeYear: true,
		    showButtonPanel: true,
		    closeText:'선택하기',
		    currentText: 'This year',
		    onClose: function(dateText, inst) {
		      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		      $(this).val($.datepicker.formatDate('yy년', new Date(year, 1, 1)));
		    }
		  }).focus(function () {
		    $(".ui-datepicker-month").hide();
		    $(".ui-datepicker-calendar").hide();
		    $(".ui-datepicker-current").hide();
		    $(".ui-datepicker-prev").hide();
		    $(".ui-datepicker-next").hide();
		    $("#ui-datepicker-div").position({
		      my: "left top",
		      at: "left bottom",
		      of: $(this)
		    });
		    $('table.ui-datepicker-calendar').hide();
		    $('.ui-datepicker').css({"width":"210px", "top":"380px"});
		    $('.ui-datepicker-year').css({"width":"100%","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker .ui-datepicker-title').css({"line-height":"0","text-align":"left",});
		    $('.ui-widget-content').css({"border":"none"});
		    $('.ui-datepicker-close').css({"font-size":"0.875rem","font-weight":"700",})
		  }).attr("readonly", false);
	})
	
	$(".month_picker").datepicker({ 
// 			dateFormat: 'yy.mm',
// 			changeMonth: true,
// 		    changeYear: true,
// 		    showButtonPanel: true,
			closeText: '선택하기',
            nextText : '다음 달',
            prevText : '이전 달',
            currentText : "이번 달",
            changeMonth : true,
            changeYear : true,
            monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
//             dayNames : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
//             dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
//             dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
            weekHeader : "주",
            firstDay : 0,
            isRTL : false,
            showMonthAfterYear : true,
            yearSuffix : "년",
            showButtonPanel: true,
            dateFormat: 'yy-mm',          
            yearRange: "-10:+0",
		    onClose: function(dateText, inst) {  
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
	            $(this).datepicker('setDate', new Date(year, month, 1));
	            $(".ui-datepicker-calendar").css("display","none");
	        }
		});
		$(".month_picker").focus(function () {
			$(".ui-datepicker-calendar").css("display","none");
			$("#ui-datepicker-div").position({
				  my: "center top",
				  at: "center bottom",
				  of: $(this)
			});
		    $('.ui-datepicker-year').css({"width":"44%","margin-right":"0.5rem","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker-month').css({"width":"44%","margin-left":"0.5rem","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker .ui-datepicker-title').css({"line-height":"0","text-align":"left",});
		    $('.ui-widget-content').css({"border":"none"});
		    $('.ui-datepicker-close').css({"font-size":"0.875rem","font-weight":"700",})
		    $('.ui-datepicker-current').css({"font-size":"0.875rem","font-weight":"500",})
		    $('.ui-icon-circle-triangle-w').hide();
		    $('.ui-icon-circle-triangle-e').hide();
		});
	
   	// tab1-1 chart
   	var vhclInfoLabelArray = '${vhclInfoLabelArray}'; 
   	var vhclInfoDataArray = '${vhclInfoDataArray}';
   	var labelArray = vhclInfoLabelArray.replace('[','').replace(']','').trim();
   	var dataArray = vhclInfoDataArray.replace('[','').replace(']','').trim();
	new GITSChart(GITSChartType.DOUGHNUT).init("tab1_1_chart")
    .setDataSetLabel(labelArray.split(',')[0], labelArray.split(',')[1], labelArray.split(',')[2], labelArray.split(',')[3])
    .setDataSet({
            label: labelArray.split(','),
            data: dataArray.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <input type="hidden" id="startYear" value="${yearsList[fn:length(yearsList) -1].year}">
            	<input type="hidden" id="endYear" value="${yearsList[0].year}">
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	                <option value="searchAllYear">전체</option>
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="${yearsList.year}">${yearsList.year}년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekdays">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" id="dateChk" class="none">직접입력</label>
	            </div>
	            <div class="calendar direct_time none">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            ~
		            <div class="end_calendar_box">
						<div class="date_picker_block"></div>								            
			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" id="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
			        </div>	  	            
				</div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" id="toWork" name="peekAndOffPeek" class="none" value="peek">출근 <span class="group_btn_span">(06시~10시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" id="offWork" name="peekAndOffPeek" class="none" value="offPeek">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none">직접 입력</label>
	            </div>
	            <div class="calendar direct_time none">
					<select class="selectBox selectTime" id="startTime" name="startTime"></select>
	                ~
					<select class="selectBox selectTime" id="endTime" name="endTime"></select>
	            </div>
	        </div>
			<div class="tab_item_box flex-center border-none">
		        <h5 class="tab_item_title">노선찾기</h5>
		        <input type="text" id="inputChange1" name="startNodeNm" placeholder="출발지 입력" class="input_same search_box tab_box_input radius">
		        <button type="button" class="tab_box_change_img" onclick="transferValue()"><img src="${pageContext.request.contextPath}/statics/images/change.png" alt="체인지"></button>
		        <input type="text" id="inputChange2" name="endNodeNm" placeholder="도착지 입력" class="input_same search_box tab_box_input radius">
	       		<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:440px;">
				<div class="table_search_number tableTitle">
                	<span>"${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}개"</span>의 검색결과를 찾았습니다.
                </div>			
				<table class="all_center">
				    <colgroup>
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col" rowspan="3">노선번호</th>
				            <th scope="col" colspan="4">시-종점</th>
				        </tr>
				        <tr>
				            <th scope="col" colspan="4">수원여대입구 - 조원뉴타운</th>
				        </tr>
				        <tr>
				            <th>노선거리</th>
				            <th>총 이용 고객수</th>
				            <th>정류장 수</th>
				            <th>굴곡도</th>
				        </tr>
				    </thead>
				    <tbody>
				        
				    </tbody>
				</table>
		        <div id="modalPaging">
			    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
				</div>
			</div>
        </div>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="sectionResult()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();
	
	
	$(document).ready(function() {
		if(!isNull($("#startTime").val())){
			$('.date_picker_block').remove();
		}
	})
	
	$("#toWork").on('click', function(){
    	if($(this).is(":checked")){
    		$("#startTime").val("06").prop("selected", true);
    		$("#endTime").val("10").prop("selected", true);
    	}
    })
    
    $("#offWork").on('click', function(){
    	if($(this).is(":checked")){
    		$("#startTime").val("17").prop("selected", true);
    		$("#endTime").val("20").prop("selected", true);
    	}
    })
	
	function fnSearchList(){
		if(!$("#dateChk").is(":checked")){
			if($("#searchYear :selected").val() == 'searchAllYear'){
				$("#startDate").val($("#startYear").val() + "-01-01");
				$("#endDate").val($("#endYear").val() + "-12-31");
			}else{
				$("#startDate").val($("#searchYear :selected").val() + "-01-01");
				$("#endDate").val($("#searchYear :selected").val() + "-12-31");
			}
		}
		
		$("table>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/road/BD_BUS_ROAD_003/data.ajax",
    		success : function(result){
    			var html = '';
    			var title = '';
    			var startDate = '';
    			var endDate = '';
    			if(result.data.resultList.length > 0){
    				$("#tableHeader").removeClass("none");
    			}
    			$(result.data.resultList).each(function(index, item){
    				html += '<tr>' +
								'<td>' + item.routeNm + '</td>' +
								'<td>' + item.routeLen + '</td>' +
								'<td>' + item.totUserCnt + '</td>' + 									
								'<td>' + item.totBstpCnt + '</td>' +
								'<td>' + item.curvt + '</td>' +
							'</tr>';
    			});
    			$("table>tbody").append(html);
    			var paging = result.data.paging;
    			if(paging != null && paging != ''){
    				title += '<span>'+paging.totalCount+'개</span>의 검색결과를 찾았습니다.';
    				$("#modalPaging").append(getGisPagingHtml(paging, page));
    			};
    			$(".tableTitle").html(title);
    			startDate = result.data.searchOption.startDate;
    			endDate = result.data.searchOption.endDate;
    			$("#startDate").val(startDate.substring(0,10));
    			$("#endDate").val(endDate.substring(0,10));
    		}
    	})
	}
	
	function sectionResult(){	
        var remarksItem = $(`
        <div class="remarks_container">
	        <div class="remarks_title_box">
	            <h6 class="remarks_title">범례 - 노선 수요성</h6>
	        </div>
        	<div class="remarks_wrap tab-none">
            	<div>
	            	<div class="check_line_container">
	                    <button type="button" class="check_line_box remarks-red">포화</button>
		                <button type="button" class="check_line_box remarks-orange">혼잡</button>
		                <button type="button" class="check_line_box remarks-light-orange">만석</button>
		                <button type="button" class="check_line_box remarks-green">여유</button>
		            </div>
	        	</div>
	            <div class="unit">단위 : 이용량 치수 단계</div>
        	</div>
    	</div>`)        
	    $('#map-container').append(remarksItem);
	    legendToggle();
	}		
</script>

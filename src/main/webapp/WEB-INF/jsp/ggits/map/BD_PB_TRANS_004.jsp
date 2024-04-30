<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <input type="hidden" id="startYear" value="<c:out value='${yearsList[0].year}'/>">
            	<input type="hidden" id="endYear" value="<c:out value='${yearsList[fn:length(yearsList) -1].year}'/>">
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value="${yearsList.year}"/>"><c:out value="${yearsList.year}"/>년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekdays">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" id="dateChk" class="none" name="searchPeriod" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none" id="directDate">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            ~
		            <div class="end_calendar_box">
						<div class="date_picker_block"></div>								            
			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" id="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            </div>	            
				</div>
	        </div>
			<div class="tab_item_box flex-column gap8">
				<div class="tab_item_box flex-center">
	            	<h5 class="tab_item_title">지역별</h5>
	            	<select class="selectBox radius" name="sigunCdId">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}00000'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	       		</div>
				<div class="flex-center">
			        <h5 class="tab_item_title">노선</h5>
			        <input type="text" placeholder="노선을 입력해주세요." name="searchContent" class="input_same search_box radius">
		        	<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
				</div>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:500px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>				
				<table id="modalTable" class="result_change">
				    <colgroup>
				        <col style="width:10%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:15%">
				        <col style="width:15%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
				            <th scope="col">버스번호</th>
				            <th scope="col">출발지</th>
				            <th scope="col">종착지</th>
				            <th scope="col">버스유형</th>
				            <th scope="col">평균 환승 횟수</th>
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
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="publicTransportResult()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_TRANS_004");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	function publicTransportResult() {
	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 승하차/환승 분류</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <ul class="check_line_container">
			                    <li class="check_line_box remarks-red">승차수</li>
			                    <li class="check_line_box remarks-orange">하차수</li>
			                    <li class="check_line_box remarks-green">환승수</li>
			                </ul>
		            	</div>
			            <div class="unit">단위 : 승하차/환승 횟수</div>
		        	</div>
		    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	        $('#map-container').append(remarksItem);
	        legendToggle();

			window.map.bigdata.getPublicTransferUsageGroupBySGG($("#searchForm").serialize());
			bigdataSearchForm = $("#searchForm").serializeObject();
	}	
	
	
	$(document).ready(function() {
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
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
		
		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_004/data.ajax",
    		success : function(result){
    			var html = '';
    			var title = '';
    			var startDate = '';
    			var endDate = '';
    			if(result.data.resultList.length == 0){
    				html += '<tr>' +
								'<td colspan="5">노선 정보를 찾을 수 없습니다.</td>' +
							'</tr>';
    			}else{
	    			$(result.data.resultList).each(function(index, item){
	    				html += '<tr>' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.routeNm + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.stStaNm + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.edStaNm + '</label>' + '</td>' +						
									'<td>' + '<label for="listItem'+index+'">' + '-' + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + '-' + '</label>' + '</td>' +
								'</tr>';
	    			});
    			}
    			$("#tableHeader").removeClass("none");
    			$("#modalTable>tbody").append(html);
    			var paging = result.data.paging;
    			if(paging != null && paging != ''){
    				title += '<span id="totalCnt2">'+paging.totalCount+'</span>개의 검색결과를 찾았습니다.';
    				$("#modalPaging").append(getGisPagingHtml(paging, page));
    			}
    			$(".tableTitle").html(title);
    			startDate = result.data.searchOption.startDate;
    			endDate = result.data.searchOption.endDate;
    			$("#startDate").val(startDate.substring(0,10));
    			$("#endDate").val(endDate.substring(0,10));
    			var dataTotalCnt2 = paging.totalCount;
    			$("#totalCnt2").text(numberComma(dataTotalCnt2))
    		}
    	})
	}
</script>

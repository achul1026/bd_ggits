<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" onsubmit="return false;" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" name="stationId" id="stationId">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
        	<div class="tab_item_box flex-center">
            <h5 class="tab_item_title">연도별</h5>
            	<select class="selectBox radius" name="searchYear" id="searchYear">
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           		</c:forEach>
	            </select>
        	</div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">기간<span class="required-alert">*</span></h5>
					<div class="calendar" id="directDate">
						<input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
						~
						<div class="end_calendar_box">
							<div class="date_picker_block"></div>
							<input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
						</div>
					</div>
				</div>
			</div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">기간구분<span class="required-alert">*</span></h5>
					<label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="" checked>전체</label>
					<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekday" checked>평일</label>
					<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
				</div>
			</div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">시간대<span class="required-alert">*</span></h5>
					<label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" class="none" name=searchTime value="workingTime" checked>출근 <span class="group_btn_span">(06시~10시)</span></label>
					<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
					<label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">직접 입력</label>
				</div>
				<div class="calendar direct_time none" id="directTime">
					<select class="selectBox selectTime" id="startTime" name="startTime"></select>
					~
					<select class="selectBox selectTime" id="endTime" name="endTime"></select>
				</div>
			</div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">시군구</h5>
				<select class="selectBox radius change-detect"  name="searchLocation">
					<option value="">시군구 전체</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
						<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
				</select>
			</div>
			<div class="bottom_btn">
				<button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="sectionResult()">결과보기</button>
			</div>
			<%--<div class="tab_item_box flex-center">
		        <h5 class="tab_item_title">정류장<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="정류장 이름 입력" name="searchContent" id="searchContent" class="input_same search_box radius">
		        <button type="button" class="is-darkgreen-btn ml8" id="srchBtn">검색</button>
	        </div>--%>
        </form>
		<%--<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:500px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>
				<table id="modalTable"  class="result_change">
				    <colgroup>
				        <col style="width:10%">
						<col style="width:30%">
				        <col style="width:40%">
				        <col style="width:20%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
							<th scope="col">정류장ID</th>
				            <th scope="col">정류장명</th>
				            <th scope="col">노선개수</th>
				        </tr>
				    </thead>
				    <tbody>
				    	
				    </tbody>
				</table>
		        <div id="modalPaging">
			    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
				</div>
			</div>
        </div>--%>
<!--         <div class="bottom_btn"> -->
<!--             <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="publicTransportResult()">결과보기</button> -->
<!--         </div> -->
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	// settingBigdataSearchParam("BD_PB_TRANS_006");

	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function() {
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		}
	})
	/*$("#busStationLocationFilterSelector").on("change", function(){
		if($(this).val() == "") return;
		if(__Map.getLayer(GITS_ENV.LAYER.BD_BUS_STATION))
			__Map.setFilter(GITS_ENV.LAYER.BD_BUS_STATION, ['in', $(this).val().substring(0,4), ['string', ['get', 'sidoCd']]]);
	});*/
	
	$('#srchBtn').on('click', function(){
		$("#mapPage").val("1");
		fnSearchList();
	})
	
	function fnSearchList(){
		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_006/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
    			if(result.code == 200){
	    			var html = '';
	    			var title = '';
	    			var startDate = '';
	    			var endDate = '';
	    			
	    			$("#modalTable>tbody>tr").remove();
	    			$("#modalPaging > .dashboard-pg-wrap").remove();
	    			if(result.data.resultList != null && result.data.resultList.length >= 0){
	    				$("#tableHeader").removeClass("none");
		    			$(result.data.resultList).each(function(index, item){
		    				var routeInterval = !isNull(item.routeInterval) ? item.routeInterval : "0";
		    				html += '<tr>' +
										'<td onclick="fnSttnLocation(this,'+item.mapX+','+item.mapY+')">' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
										'<td>' + '<label for="listItem'+index+'">' + item.stationId + '</label>' + '</td>' +
										'<td>' + '<label for="listItem'+index+'">' + item.stationNm + '</label>' + '</td>' +
										'<td>' + '<label for="listItem'+index+'">' + item.routeCnt + '</label>' + '</td>' +
									'</tr>';
		    			});
	    			} else {
	    				$("#tableHeader").removeClass("none");
	    				html += '<tr>';
	    				html += '	<td colspan="3">조회 결과가 없습니다.</td>';
	    				html += '</tr>';
	    			}
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
    			}else{
    				endLoading();
    				new ModalBuilder().init().alertBoby("정류장정보 조회에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
    			}
    		},
    		complete : function(){
    			endLoading();
    		}
    	});
	}

	function publicTransportResult(){
    	var radioChecked = $('input:radio[name=listItem]').is(':checked');
    	if(radioChecked == false){
    		new ModalBuilder().init().alertBoby("정류장을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
    	}
		window.map.bigdata.getPublicTransferUsageByStation($("#searchForm").serialize());
		bigdataSearchForm = $("#searchForm").serializeObject();
	}
	
	function fnSttnLocation(_this, mapX, mapY){
		map.control.highlightingTarget(_this, mapX, mapY);
	}	

	function sectionResult(){
		if($("input[name='startDate']").val() == '') {
			alert("시작날짜를 입력해주세요.");
			return;
		}
		if($("input[name='endDate']").val() == '') {
			alert("종료날짜를 입력해주세요.");
			return;
		}
		window.map.bigdata.getPublicTransferPassengerAnalysisAll($("#searchForm").serialize());
		bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}
</script>

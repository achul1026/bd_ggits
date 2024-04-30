<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
		<form id="searchForm" method="get" class="result_change">
		<input type="hidden" name="routeId" id="routeId" value="">
		<input type="hidden" name="startStationId" id="startStationId" value="">
		<input type="hidden" name="endStationId" id="endStationId" value="">
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
				<select class="selectBox radius change-detect" <%--onchange="getDongListBySggCd(this.value, '#sggDongSelect')"--%> name="districtGnm">
					<option value="">시군구 전체</option>
					<c:forEach var="sggCd" items="${sggCdList}" varStatus="status">
						<option value="<c:out value='${sggCd.cdNm}'/>"><c:out value='${sggCd.cdNm}'/></option>
					</c:forEach>
				</select>
			</div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">버스종류</h5>
				<select class="selectBox radius change-detect" id="routeTp" name="routeTp">
					<option value="">전체</option>
					<option value="RTC000">시외버스</option>
					<option value="RTC001">일반버스</option>
					<option value="RTC002">마을버스</option>
					<option value="RTC003">광역버스</option>
					<option value="RTC004">공항버스</option>
				</select>
			</div>
		<div class="tab_item_box flex-center">
			<h5 class="tab_item_title">버스번호<span class="required-alert">*</span></h5>
			<input type="text" placeholder="버스번호를 입력해주세요." name="routeNm" id="routeNm" class="input_same search_box radius">
			<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
        </div>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:600px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
                </div>				
	            <table class="all_center" id="modalTable">
	                <colgroup>
	                    <col style="width:10%">
	                    <col>
	                    <col style="width:12.8%">
						<col>
						<col>
						<col>
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th scope="col">선택</th>
							<th scope="col">버스유형</th>
	                        <th scope="col">버스번호</th>
							<th scope="col">지역</th>
							<th scope="col">승차자수</th>
	                        <th scope="col">하차자수</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(resultList) > 0}">
	                			<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
	                				<tr onclick="setRouteId('<c:out value='${resultInfo.busRouteId}'/>')">
	                					<td><input type="radio" id="listItem<c:out value='${status.index}'/>" name="listItem" class="bigdata_input_radio"></td>
										<td><label for="listItem<c:out value='${status.index}'/>"><c:out value='${resultInfo.routeTp}'/></label></td>
	                					<td><label for="listItem<c:out value='${status.index}'/>"><c:out value='${resultInfo.routeNm}'/></label></td>
										<td><label for="listItem<c:out value='${status.index}'/>"><c:out value='${resultInfo.stStaNm}'/></label></td>
										<td><label for="listItem<c:out value='${status.index}'/>"><c:out value='${resultInfo.edStaNm}'/></label></td>
	                					<td><label for="listItem<c:out value='${status.index}'/>"><c:out value='${resultInfo.passengers ? resultInfo.passengers : 0}'/></label></td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="6">노선정보가 존재 하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <div id="modalPaging">
			    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
				</div>
			</div>
        </div>
		</form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="sectionResult()">결과보기</button>
        </div>

    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	//settingBigdataSearchParam("BD_BUS_ROAD_002");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	function sectionResult(){
		var radioChecked = $('input:radio[name=listItem]').is(':checked');
		if(radioChecked == false) {
			new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
        var remarksItem = $(`
         <div class="remarks_container">
	    		        <div class="remarks_title_box">
	    		            <h6 class="remarks_title">범례 - 이용객수</h6>
	    		        </div>
	    	        	<div class="remarks_wrap" style="padding:0.5rem 1.5rem 0.5rem 0.5rem;">
	    	            	<div>
	    		                <div class="check_line_container">
	    		                	<div class="remarks_gradient_wrap">
										<div class="remarks_gradient_heatmap"></div>
		    		                	<div class="remarks_gradinent_txt_wrap">
		    		                		<div class="remarks_gradinent_txt">20,000</div>
		    		                		<div class="remarks_gradinent_txt">0</div>
		    		                	</div>
	    		                	</div>
	    		                </div>
	    	            	</div>
	    		            <div class="unit">단위 : 명</div>
	    	        	</div>
	    	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	    $('#map-container').append(remarksItem);
	    legendToggle();
		let param = $("#searchForm").serialize(); // routeId(필수)
		window.map.bigdata.getPublicTransferRoutePassengerAnalysis(param);
		bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}
	
	function setRouteId(routeId, startStationId, endStationId){
		$("#routeId").val(routeId);
		$("#startStationId").val(startStationId);
		$("#endStationId").val(endStationId);
	}
	
	 function fnSearchList(){
		if($("input[name='startDate']").val() == '') {
			alert("시작날짜를 입력해주세요.");
			return;
		}
		 if($("input[name='endDate']").val() == '') {
			 alert("종료날짜를 입력해주세요.");
			 return;
		 }
	    	$("#modalTable>tbody > tr").remove();
	    	$("#modalPaging > .dashboard-pg-wrap").remove();
	    	var page = $("#mapPage").val();
	    	var routeId = $("#routeId").val()
			var endStationId = $("#endStationId").val()
			var startStationId = $("#startStationId").val()
			
	    	$.ajax({
	    		type : "get",
	    		data : $("#searchForm").serialize(),
				timeout : 60*1000*10,
	    		url : "${pageContext.request.contextPath}/map/bigdata/bus/road/BD_BUS_ROAD_002/data.ajax",
	    		beforeSend : function(){
	    			startLoading();
	    		},
	    		success : function(result){
	    			if(result.code == 200){
		    			var html = '';
		    			var title = '';
		    			var startDate = '';
		    			var endDate = '';
		    			if(result.data.resultList.length == 0){
		    				html += '<tr>' +
										'<td colspan="6">노선 정보를 찾을 수 없습니다.</td>' +
									'</tr>';
		    			}else{
			    			$(result.data.resultList).each(function(index, item){
			    				var checedAttr = '';
								if(	item.routeId == routeId && 
									item.startStationId == startStationId && 
									item.endStationId == endStationId){
									checedAttr = 'checked="checked"';
								}
								
			    				html += '<tr onclick="setRouteId(' + item.busRouteId + '\,'+ item.startStationId + '\,' + item.endStationId + ')">' +
											'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio" value="' + item.routeId + '" data-start-station-id="' + item.startStationId  + '" data-end-station-id="' + item.endStationId + '" '+checedAttr+'>' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + item.routeTp + '</label>' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + item.routeNm + '</label>' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + (item.districtGnm ? item.districtGnm : item.districtSnm) + '</label>' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + (item.ridePassengers !== null ? numberComma(item.ridePassengers) : 0) + '</label>' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + (item.lndiPassengers !== null ? numberComma(item.lndiPassengers) : 0) + '</label>' + '</td>' +
			    						'</tr>';
			    						
			    			})
		    			}
		    			$("#tableHeader").removeClass("none");
		    			$("#modalTable>tbody").append(html);
		    			var paging = result.data.paging;
		    			if(paging != null && paging != ''){
		    				title += '<span id="totalCnt2">'+paging.totalCount+'</span>개의 검색결과를 찾았습니다.';
		    				$("#modalPaging").append(getGisPagingHtml(paging, page));
		    				var dataTotalCnt2 = '+paging.totalCount+';
		    				$("#totalCnt2").text(numberComma(dataTotalCnt2))
		    				
		    			};
		    			$(".tableTitle").html(title);
		    			startDate = result.data.searchOption.startDate;
		    			endDate = result.data.searchOption.endDate;
		    			$("#startDate").val(startDate.substring(0,10));
		    			$("#endDate").val(endDate.substring(0,10));
	    			}else{
	    				endLoading();
	    				new ModalBuilder().init().alertBoby("노선정보 조회에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
	    			}
	    		},
	    		complete : function(){
	    			endLoading();
	    		}
	    	});
	    }

</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" name="routeId" id="routeId">
    		<input type="hidden" name="startStationId" id="startStationId">
    		<input type="hidden" name="endStationId" id="endStationId">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <input type="hidden" id="startYear" value="<c:out value='${yearsList[fn:length(yearsList) -1].year}'/>">
            	<input type="hidden" id="endYear" value="<c:out value='${yearsList[0].year}'/>">
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekday" checked>평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" name="searchPeriod" id="dateChk" class="none" value="directDate">직접입력</label>
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
			<div class="tab_item_box flex-center">
		        <%--<h5 class="tab_item_title">노선찾기<span class="required-alert">*</span></h5>
		        <input type="text" id="inputChange1" placeholder="출발지 입력" name="startStationNm" class="input_same search_box tab_box_input radius">
		        <button type="button" class="tab_box_change_img" onclick="transferValue()"><img src="${pageContext.request.contextPath}/statics/images/change.png" alt="체인지"></button>
		        <input type="text" id="inputChange2" placeholder="도착지 입력" name="endStationNm" class="input_same search_box tab_box_input radius">
		        <button type="button" class="is-darkgreen-btn ml8" id="srchBtn">검색</button>--%>
				<h5 class="tab_item_title">버스노선<span class="required-alert">*</span></h5>
				<input type="text" placeholder="노선명을 입력해주세요." name="routeNm" id="searchContent" class="input_same search_box radius">
				<button type="button" class="is-darkgreen-btn ml8" id="srchBtn">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>			
				<table id="modalTable" class="result_change">
				    <colgroup>
				        <col style="width:10%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:15%">
				        <col style="width:15%">
				        <col style="width:20%">
				    </colgroup>
				    <thead>
				        <tr>
							<th scope="col">선택</th>
							<th scope="col">버스유형</th>
							<th scope="col">버스번호</th>
							<th scope="col">기점정류장</th>
							<th scope="col">종점정류장</th>
							<th scope="col">이용자수</th>
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
	settingBigdataSearchParam("BD_PB_TRANS_002");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function(){
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		}
	})
	
	$('#srchBtn').on('click', function(){
		$("#mapPage").val("1");
		fnSearchList();
	})
	
	function fnSearchList(){
		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	var routeId = $("#routeId").val()
		var endStationId = $("#endStationId").val()
		var startStationId = $("#startStationId").val()
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_002/data.ajax",
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
    	    				html += '<tr onclick="setRouteId(' + item.busRouteId + '\,'+ item.stStaId + '\,' + item.edStaId + ')">' +
    									'<td>' +
    											'<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio" value="' + item.routeId + '" data-start-station-id="' + item.startStationId  + '" data-end-station-id="' + item.endStationId + '">' +
    									'</td>' +
    									'<td>' + '<label for="listItem'+index+'">' + item.routeTp + '</label>' + '</td>' +
    									'<td>' + '<label for="listItem'+index+'">' + item.routeNm + '</label>' + '</td>' +
    									'<td>' + '<label for="listItem'+index+'">' + item.stStaNm + '</label>' + '</td>' +
    									'<td>' + '<label for="listItem'+index+'">' + item.edStaNm + '</label>' + '</td>' +
    									'<td>' + '<label for="listItem'+index+'">' + numberComma(item.busUserCnt) + '</label>' + '</td>' +
    								'</tr>';	    			
    	    			});
    	    		
    	    		
        			}
        			$("#tableHeader").removeClass("none");
        			$("#modalTable>tbody").append(html);
        			var paging = result.data.paging;
        			if(paging != null && paging != ''){
        				title += '<span>'+paging.totalCount+'개</span>의 검색결과를 찾았습니다.';
        				$("#modalPaging").append(getGisPagingHtml(paging, page));
        			}
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
    	})
	}
	
	function publicTransportResult(){
		if($("#routeId").val() === '' || isNull($("#routeId").val())){
			new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();		
			return;
		}
        var remarksItem =$(`
    	        <div class="remarks_container">
    		        <div class="remarks_title_box">
    		            <h6 class="remarks_title">범례 - 대중교통 이용량</h6>
    		        </div>
    	        	<div class="remarks_wrap">
    	            	<div>
    		                <ul class="check_line_container">
    		                    <li class="check_line_box remarks-red">800명 이상</li>
    		                    <li class="check_line_box remarks-orange">600~800명</li>
    		                    <li class="check_line_box remarks-light-orange">400~600명</li>
								<li class="check_line_box remarks-light-green">200~400명</li>
    		                    <li class="check_line_box remarks-green">200명 이하</li>
    		                </ul>
    	            	</div>
    		            <div class="unit">단위 : 이용량 지수 단계</div>
    	        	</div>
    	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
		$('#map-container').append(remarksItem);
		legendToggle();
		window.map.bigdata.getPublicTransferUsageByStEnd($("#searchForm").serialize());
		bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}	
	
	$(".gis_table_scroll").on("click", ".routeInfo", function(){
		console.log($(this).data("value"))
		$("#routeId").val($(this).data("value"));
	})
	
	function setRouteId(routeId, startStationId, endStationId){
		$("#routeId").val(routeId);
		$("#startStationId").val(startStationId);
		$("#endStationId").val(endStationId);
	}
	
	$('#inputChange1').autocomplete({
		source : function(request, response) {
		     $.ajax({
		           url : "${pageContext.request.contextPath}/map/bigdata/ajax/autocomplete.ajax"   
		         , type : "POST"
		         , dataType: "JSON"
		         , data : {value: request.term}	// 검색 키워드
		         , success : function(data){ 	// 성공
		             response(
		                 $.map(data.resultList, function(item) {
		                     return {
		                    	     label : item.search_word
		                           , value : item.search_word
		                           , idx : item.SEQ 
		                     };
		                 })
		             );
		         }
		     });
		}
		,focus : function(event, ui) {return false;}
		,minLength: 2
		,autoFocus : true
		,delay: 2000
		,select : function(evt, ui) {}
	});
	
	$('#inputChange2').autocomplete({
		source : function(request, response) {
		     $.ajax({
		           url : "${pageContext.request.contextPath}/map/bigdata/ajax/autocomplete.ajax"   
		         , type : "POST"
		         , dataType: "JSON"
		         , data : {value: request.term}	// 검색 키워드
		         , success : function(data){ 	// 성공
		             response(
		                 $.map(data.resultList, function(item) {
		                     return {
		                    	     label : item.search_word
		                           , value : item.search_word
		                           , idx : item.SEQ 
		                     };
		                 })
		             );
		         }
		     });
		}
		,focus : function(event, ui) {return false;}
		,minLength: 2
		,autoFocus : true
		,delay: 2000
		,select : function(evt, ui) {}
	});
</script>

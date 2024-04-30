<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
			<input type="hidden" name="routeId" id="routeId" value="">
			<input type="hidden" name="startStationId" id="startStationId" value="">
			<input type="hidden" name="endStationId" id="endStationId" value="">
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">노선<span class="required-alert">*</span></h5>
				<input type="text" placeholder="노선을 입력해주세요." name="routeNm" id="routeNm" class="input_same search_box radius">
				<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:500px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>			
				<table class="all_center result_change" id="modalTable">
				    <colgroup>
				        <col style="width:10%">
						<col style="width:15%">
						<col style="width:10%">
				        <col style="width:20%">
				        <col style="width:10%">
				        <col style="width:10%">
				        <col style="width:10%">
				    </colgroup>
				    <thead>
				        <tr>
							<th>선택</th>
							<th>버스유형</th>
							<th>버스번호</th>
							<th>지역</th>
				            <th>노선길이(km)</th>
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
	settingBigdataSearchParam("BD_BUS_ROAD_003");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function() {
		if(!isNull($("#startTime").val())){
			$('.date_picker_block').remove();
		}
		
		$('#routeNm').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
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
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/road/BD_BUS_ROAD_003/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
    			if(result.code == 200){
	    			var html = '';
	    			var title = '';
	    			var stStaNm = '';
	    			var edStaNm	= '';
	    			var startDate = '';
	    			var endDate = '';
	    			if(result.data.resultList.length >= 0){
	    				$("#tableHeader").removeClass("none");
	    			}
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
									'<td>' + '<label for="listItem'+index+'">' + item.routeLen + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.totBstpCnt + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.curvt + '</label>' + '</td>' +								
								'</tr>';
	    			});
	    			
	    			$("#sttnInfo").text(result.data.searchOption.startStationId + '(출발) - ' + result.data.searchOption.endStationId + '(도착)');
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
    	})
	}
	
	function sectionResult(){
		var routeId = $("#routeId").val();
		if(isNull(routeId) || routeId == ''){
			new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
        /*var remarksItem = $(`
        <div class="remarks_container">
	        <div class="remarks_title_box">
	            <h6 class="remarks_title">범례 - 노선 수요성</h6>
	        </div>
        	<div class="remarks_wrap tab-none">
            	<div>
	            	<ul class="check_line_container">
	                    <li class="check_line_box remarks-red">포화</li>
		                <li class="check_line_box remarks-orange">혼잡</li>
		                <li class="check_line_box remarks-light-orange">만석</li>
		                <li class="check_line_box remarks-green">여유</li>
		            </ul>
	        	</div>
	            <div class="unit">단위 : 이용량 치수 단계</div>
        	</div>
    	</div>`)        
	    $('#map-container').append(remarksItem);
	    legendToggle();*/
		let param = $("#searchForm").serialize(); // routeId(필수)
		window.map.bigdata.getPublicTransferRouteCurveAnalysis(param);
		bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}		
	
	function setRouteId(routeId){
		$("#routeId").val(routeId);
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" name="stationId" id="stationId">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="tab_item_box flex-center">
		        <h5 class="tab_item_title">정류장<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="정류장ID 또는 이름 입력" name="searchContent" id="searchContent" class="input_same search_box radius">
		        <button type="button" class="is-darkgreen-btn ml8" id="srchBtn">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:500px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>
				<table id="modalTable"  class="result_change">
				    <colgroup>
				        <col style="width:10%">
				        <col style="width:60%">
				        <col style="width:30%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
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
        </div>
<!--         <div class="bottom_btn"> -->
<!--             <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="publicTransportResult()">결과보기</button> -->
<!--         </div> -->
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_TRANS_006");

	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function() {
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
		    				html += '<tr onclick="fnSttnLocation(this,'+item.mapX+','+item.mapY+')">' +
										'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
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
	$('#searchContent').autocomplete({
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

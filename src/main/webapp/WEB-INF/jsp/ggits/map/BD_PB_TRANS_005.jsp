<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">지역별</h5>
				<select id="busStationLocationFilterSelector" class="selectBox radius" name="searchLocation">
					<option value="">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
						<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
				</select>
			</div>
	        <div class="tab_item_box flex-center border-none">
		        <h5 class="tab_item_title">정류장<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="정류장 이름 입력" name="searchContent" id="searchContent" class="input_same search_box radius">
		        <input type="button" class="is-darkgreen-btn ml8 pointer" id="srchBtn" value="검색">
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:400px;">
				<input type="hidden" id="mapX">
				<input type="hidden" id="mapY">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>	
				<table id="fcltTb" class="none result_change">
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
				            <th scope="col">정류장명</th>
<!-- 				            <th scope="col">BIT 수</th> -->
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
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_TRANS_005");
	
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function() {
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		}
		
		$('#searchContent').keydown(function() {
			  if (event.keyCode === 13) {
			    event.preventDefault();
			  }
		});
	});

	$("#busStationLocationFilterSelector").on("change", function(){
		if($(this).val() == "") return;
		if(__Map.getLayer(GITS_ENV.LAYER.BD_BUS_STATION))
			__Map.setFilter(GITS_ENV.LAYER.BD_BUS_STATION, ['in', $(this).val().substring(0,4), ['string', ['get', 'sidoCd']]]);
	});
	
	$('#srchBtn').on('click', function(){
		$("#mapPage").val("1");
		fnSearchList();
	})
	
	function fnSearchList(){
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	bigdataSearchForm = $("#searchForm").serializeObject();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_005/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
    			if(result.code == 200){
	    			var html = '';
	    			var title = '';
	    			var startDate = '';
	    			var endDate = '';
	    			if(result.data.resultList.length > 0){
	    				$("#tableHeader").removeClass("none");
	    			}
	    			
	   				$("#fcltTb>tbody>tr").remove();
	   				$("#modalPaging > .dashboard-pg-wrap").remove();
	   				$(result.data.resultList).each(function(index, item){
	   					var bitTp = !isNull(item.bitTp) ? item.bitTp : '0';
	   					var bitCount = !isNull(item.bitCount) ? item.bitCount : '0';
	   					html += '<tr>' +
	   								'<td onclick="fnSttnLocation(this,'+item.mapX+','+item.mapY+')">' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
	   								'<td>' + '<label for="listItem'+index+'">' + item.stationNm + '</label>' + '</td>' +
// 	   								'<td>' + '<label for="listItem'+index+'">' + bitCount + '</label>' + '</td>' +					
	   							'</tr>';    					
	       					
	       			});
	       			$("#fcltTb>tbody").append(html);
	//        			$("#routeTb").addClass("none");
	       			$("#fcltTb").removeClass("none");
	    		
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
    				new ModalBuilder().init().alertBoby("노선정보 조회에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
    			}
    		},
    		complete : function(){
    			endLoading();
    		}
    	})
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

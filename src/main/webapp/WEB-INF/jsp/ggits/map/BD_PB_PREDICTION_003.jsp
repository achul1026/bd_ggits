<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
		<div class="tab_item_box flex-column">
			<form id="searchForm" method="get" class="result_change" onsubmit="return false;">
			<input type="hidden" id="mapPage" name="page" value="1"/>
			<input type="hidden" id="routeId" name="routeId">
			<input type="hidden" id="candRouteId" name="candRouteId">
			<input type="hidden" id="baseym" name="baseym">
			<input type="hidden" id="btcId" name="btcId">
			<input type="hidden" id="direction" name="direction">
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="flex-center">
		        <h5 class="tab_item_title">노선<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="버스 번호를 입력해 주세요." name="routeNm" id="routeNm" class="input_same search_box">
		        <button type="button" class="is-darkgreen-btn tab_item_list_button ml8" onclick="fnSearchList();">검색</button>
				<div style="font-size: 12px;font-weight: 300;">ex) 16-2, 55</div>
			</div>

	        <div class="flex-center none pt8" id="crossroadsListDiv">
				<h5 class="tab_item_title"></h5>
				<div class="gis_table_scroll">
					<div class="table_search_number tableTitle">
	                	<span id="totalCnt"></span>개의 검색결과를 찾았습니다.
	                </div>	
					<div class="tab_item_box_list_box">
						<table id="modalTable" class="result_change">
						    <colgroup>
						        <col style="width:75px">
								<col style="width:100px">
						        <col style="min-width:100px;">
								<col style="width:100px">
						        <col style="width:100px">
						        <col style="width:100px">
						        <col style="width:75px">
						    </colgroup>
						    <thead>
						        <tr>
						            <th scope="col">선택</th>
									<th scope="col">분석날짜</th>
									<th scope="col">방향</th>
						            <th scope="col">후보 경로 순위</th>
									<th scope="col">승객수</th>
						            <th scope="col">길이</th>
						            <th scope="col">굴곡도</th>
						        </tr>
						    <tbody id="crossroadsListBody">
						    </tbody>
						</table>
						<div id="pagingDiv">
						</div>
					</div>
				</div>
	        </div>
	        </form>        
        </div>
        <div class="bottom_btn" >
	        <span style="font-size:12px; margin-right: 24px;">*후보경로 순위는 예상 승객 수와 길이 등을 종합적으로 판단하여 산정</span>
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="viewResult()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_PREDICTION_003");
	
	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))

	function fnSearchList(){
		var routeNm = $("#routeNm").val();
		if(isNull(routeNm) || routeNm == ''){
			new ModalBuilder().init().alertBoby("버스 번호를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return false;
		}
		
		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/pb/prediction/BD_PB_PREDICTION_003/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
    			var html = '';
    			var title = '';
    			var startDate = '';
    			var endDate = '';
    			if(result.data.resultList.length == 0){
    				html += '<tr>' +
								'<td colspan="7">노선 정보를 찾을 수 없습니다.</td>' +
							'</tr>';
    			}else{
	    			$(result.data.resultList).each(function(index, item){
						let dt = item.baseym.substring(0, 4) + "-" + item.baseym.substring(4, 6);
						let drct = item.btcId.split("_")[1];
	    				html += '<tr onclick="setRouteId(\'' + item.candRouteId+ '\', \''+item.routeId+'\', \''+item.btcId+'\', \''+item.baseym+'\', \''+(drct === "01" ? "forward" : "reverse")+'\')">' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + dt + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + (drct === "01" ? "정방향" : "역방향") + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.candRouteId.split("_")[1] + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + numberComma(item.numPsngr) + ' 명</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + numberComma(item.length) + ' m</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.lengthRatio + '</label>' + '</td>' +  						
	    						'</tr>';
	    			})
    			}
    			$("#crossroadsListDiv").removeClass("none");
    			$("#modalTable>tbody").append(html);
    			var paging = result.data.paging;
    			if(paging != null && paging != ''){
    				$("#modalPaging").append(getGisPagingHtml(paging, page));
    			};
				$("#totalCnt").text(numberComma(paging.totalCount));
    		},
    		complete : function(){
    			endLoading();
    		}
    	});
	}
	
	function setRouteId(candRouteId, routeId, btcId, baseym, direction){
		$("#candRouteId").val(candRouteId);
		$("#btcId").val(btcId);
		$("#baseym").val(baseym);
		$("#routeId").val(routeId);
		$("#direction").val(direction);
	}
	
	
	function viewResult(){
    	var radioChecked = $('input:radio[name=listItem]').is(':checked');
    	if(radioChecked == false){
    		new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
    	}
		if(window.dualMap){
			dualMap.bigdata.getPublicTransferCndcyRouteInfo($("#searchForm").serialize());
			map.bigdata.getBusRouteAndStationInfo($("#searchForm").serialize());
		}else{
			gitsApp.drawDualMap(null, null, function(){
				dualMap.bigdata.getPublicTransferCndcyRouteInfo($("#searchForm").serialize());
				map.bigdata.getBusRouteAndStationInfo($("#searchForm").serialize());
			});
		}
        bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}

</script>

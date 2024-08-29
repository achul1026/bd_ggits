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
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="flex-center">
		        <h5 class="tab_item_title">노선<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="버스 번호를 입력해 주세요." name="routeNm" id="routeNm" class="input_same search_box">
		        <button type="button" class="is-darkgreen-btn tab_item_list_button ml8" onclick="fnSearchList();">검색</button>
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
						        <col style="">
								<col style="width:100px">
						        <col style="width:200px">
						        <col style="width:100px">
						        <col style="width:75px">
						    </colgroup>
						    <thead>
						        <tr>
						            <th scope="col">선택</th>
									<th scope="col">분석날짜</th>
						            <th scope="col">후보 경로 ID</th>
									<th scope="col">방향</th>
						            <th scope="col">평가점수</th>
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
        <div class="bottom_btn">
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
	    				html += '<tr onclick="setRouteId(\'' + item.candRouteId+ '\', \''+item.routeId+'\')">' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + dt + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.candRouteId + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + (drct === "01" ? "정방향" : "역방향") + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + numberComma(item.score) + ' 점</label>' + '</td>' +
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
    		},
    		complete : function(){
    			endLoading();
    		}
    	});
	}
	
	function setRouteId(candRouteId, routeId){
		$("#candRouteId").val(candRouteId);
		$("#routeId").val(routeId);
	}
	
	
	function viewResult(){
    	var radioChecked = $('input:radio[name=listItem]').is(':checked');
    	if(radioChecked == false){
    		new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
    	}
    	
        map.bigdata.getPublicTransferCndcyRouteInfo($("#searchForm").serialize());
        bigdataSearchForm = $("#searchForm").serializeObject();
        busOptimization();
        resultChange();
	}
	
	//버스 최적화 노선
	function busOptimization(){
		var busOptimizationItem = $(`
			<div class="optimization_container">
				<div class="optimization_wrap">
					<div class="optimization_title_box">
						<div class="optimization_title_set mb8">
							<div class="optimization_title">- 버스 노선 정보</div>
							<button type="button" class="optimization_close"><img src="${pageContext.request.contextPath}/statics/images/close.png" alt="닫기"></button>
						</div>
						<ul class="optimization_txt">
							<li class="mb8">노선명 : <span>16-2</span></li>
							<li>노선ID : <span>12312312</span></li>
						</ul>
					</div>
					<div class="optimization_content_box">
						<div class="optimization_title mb8">- 최적화 노선도</div>
						<div class="optimization_table_box">
							<table>
								<tr>
									<th>평가점수</th>
									<td>123.123.123</td>
								</tr>
								<tr>
									<th>길이</th>
									<td>12.12km</td>
								</tr>
								<tr>
									<th>굴곡도</th>
									<td>2</td>
								</tr>
							</table>
						</div>
					</div>
					<div><button type="button" class="is-darkgreen-btn" id="hideButton">기존노선 숨기기</button></div>
				</div>
			</div>			
		`)
		
		$('#map-container').append(busOptimizationItem);
		$('.optimization_close').on('click', function(){
			$(this).closest('.optimization_container').remove();
		})
	}	
	
</script>

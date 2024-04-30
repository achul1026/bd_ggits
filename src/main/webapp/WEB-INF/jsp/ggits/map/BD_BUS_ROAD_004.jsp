<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
			<input type="hidden" name="routeId" id="routeId" value="">
			<input type="hidden" name="stStationId" id="stStationId" value="">
			<input type="hidden" name="edStationId" id="edStationId" value="">
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
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
				<h5 class="tab_item_title">버스번호</h5>
				<input type="text" class="dashboard_input search_box radius mr4" name="routeNm" id="schRouteNm" placeholder="버스번호를 입력해주세요.">
				<button type="button" class="is-darkgreen-btn ml8" id="searchBtn" onclick="fnSearchList()">찾기</button>
			</div>

		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:600px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>				
	            <table class="all_center" id="modalTable">
					<colgroup>
						<col style="width:20%">
						<col style="width:20%">
						<col style="width:20%">
						<col style="width:20%">
						<col style="width:20%">
					</colgroup>
					<thead>
					<tr>
						<th>선택</th>
						<th>버스유형</th>
						<th>노선번호</th>
						<th>운수회사명</th>
						<th>지역</th>
					</tr>
	                <tbody>
	                </tbody>
	            </table>
	            <div id="modalPaging">
			    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
				</div>
			</div>
        </div>
		</form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="analysisResultEvent()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	settingBigdataSearchParam("BD_BUS_ROAD_004");
	
	$(document).ready(function() {
		$('#schRouteNm').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});
	
	//let loadingAjax = null;
	function fnSearchList(){
		//if(loadingAjax) loadingAjax.abort();
		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
    	var page = $("#mapPage").val();

		$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/road/BD_BUS_ROAD_004/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
    			if(result.code == 200){
	    			var html = '';
	    			var title = '';
	    			if(result.data.resultList.length >= 0){
	    				$("#tableHeader").removeClass("none");
	    			}
	    			$(result.data.resultList).each(function(index, item){

	    				html += '<tr onclick="setRouteId(' + item.busRouteId + ')">' +
								'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio" value="' + item.busRouteId + '" >' + '</td>' +
								'<td>'+'<label for="listItem'+index+'">'+item.routeTp+'</label></td>'+
								'<td>'+'<label for="listItem'+index+'">'+item.routeNm+'</label></td>'+
								'<td>'+'<label for="listItem'+index+'">'+item.companyNm+'</label></td>'+
								'<td>'+'<label for="listItem'+index+'">'+(item.districtGnm ? item.districtGnm : item.districtSnm)+'</label></td>'+
								'</tr>';
	    			});
	    			$("#modalTable>tbody").append(html);
	    			var paging = result.data.paging;
	    			if(paging != null && paging != ''){
	    				title += '<span id="totalCnt">'+paging.totalCount+'</span>개의 검색결과를 찾았습니다.';
	    				$("#modalPaging").append(getGisPagingHtml(paging, page));
	    			};
	    			$(".tableTitle").html(title);
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
	function analysisResultEvent(){
		var routeId = $('input[name=routeId]').val();
		if(routeId == false) {
			new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		
		//범례 - 데이터 필요함으로 인한 주석
         var remarksItem = $(`
        <div class="remarks_container">
	        <div class="remarks_title_box">
	            <h6 class="remarks_title">범례</h6>
	        </div>
        	<div class="remarks_wrap">
            	<div>
	            	<ul class="check_line_container">
						<li class="check_line_box remarks-red">21건 이상</li>
	                    <li class="check_line_box remarks-orange">11~20</li>
		                <li class="check_line_box remarks-light-orange">6~10</li>
		                <li class="check_line_box remarks-light-green">1~5</li>
		                <li class="check_line_box remarks-green">0</li>
		            </ul>
	        	</div>
	            <div class="unit">단위 : 중복수</div>
        	</div>
    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	    $('#map-container').append(remarksItem);
	    legendToggle();
		
		map.bigdata.getPublicTransferDuplicateRoute($("#searchForm").serialize());
		bigdataSearchForm = $("#searchForm").serializeObject();
		
		resultChange();
	}
	
	function setRouteId(routeId){
		$("#routeId").val(routeId);
	}
</script>

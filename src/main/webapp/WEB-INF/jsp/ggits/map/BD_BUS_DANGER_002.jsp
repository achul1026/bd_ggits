<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
			<input type="hidden" name="routeId" id="routeId" value=""/>
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">날짜<span class="required-alert">*</span></h5>
					<div class="calendar" id="directDate">
						<input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
					</div>
				</div>
			</div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">유형</h5>
				<select class="selectBox radius" name="collectType">
					<option value="quickAccel">급가속</option>
					<option value="quickDecel">급감속</option>
					<option value="quickDecel">급정지</option>
					<option value="quickStart">급출발</option>
					<option value="quickRouteChange">급진로변경</option>
					<option value="quickOvertake">급앞지르기</option>
					<option value="quickLfrtTurn">급좌우회전</option>
					<option value="quickUturn">급유턴</option>
				</select>
			</div>
<%--			<div class="tab_item_box flex-center">--%>
<%--				<h5 class="tab_item_title">운수회사</h5>--%>
<%--				<select class="selectBox radius" name="companyNm">--%>
<%--					<option value="">전체 회사</option>--%>
<%--					<c:forEach var="company" items="${companyList}">--%>
<%--						<c:if test="${company.company_id != null}">--%>
<%--						<option value="<c:out value='${company.company_id}'/>"><c:out value='${company.company_nm}'/></option>--%>
<%--						</c:if>--%>
<%--					</c:forEach>--%>
<%--				</select>--%>
<%--			</div>--%>
			<div class="tab_item_box flex-center">
		        <h5 class="tab_item_title">차량번호</h5>
		        <input type="text" placeholder="차량번호를 입력해주세요." name="routeNm" id="routeNm" class="input_same search_box radius">
		        <button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList(true);">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:430px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : paging.totalCount}'/></span>개의 검색결과를 찾았습니다.
                </div>			
				<table id="modalTable" class="result_change">
				    <colgroup>
				        <col style="width:10%">
				        <col style="width:18%">
				        <%--<col style="width:18%">--%>
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
							<th scope="col">차량번호</th>
				            <%--<th scope="col">운수회사</th>--%>
				        </tr>
				    </thead>
				    <tbody>
						<c:forEach var="busItem" items="${resultList}" varStatus="status">
							<tr onclick="setRouteId('${busItem.plateNo}')">
								<td><input type="radio" id="listItem${status.index}" name="listItem" class="bigdata_input_radio"></td>
								<td><label for="listItem${status.index}">${busItem.plateNo}</label></td>
								<%--<td><label for="listItem${status.index}">${busItem.companyNm}</label></td>--%>
								</tr>
						</c:forEach>
				    </tbody>
				</table>
		        <div id="modalPaging">
			    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
				</div>     
			</div>
        </div>   
        <div class="bottom_btn">
            <button type="button" onclick="publicTransferSafetyAnalysis()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_BUS_DANGER_002");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
    function publicTransferSafetyAnalysis(){
		var routeId = $("#routeId").val();
		var startDate = $("input[name='startDate']").val();
		if(isNull(routeId) || routeId == ''){
			new ModalBuilder().init().alertBoby("차량번호를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		if(isNull(startDate) || startDate == ''){
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		
        map.bigdata.getPublicTransferDangerSectionInfo($("#searchForm").serialize());
        bigdataSearchForm = $("#searchForm").serializeObject();
        
        /*var remarksItem = $(`
        <div class="remarks_container">
	        <div class="remarks_title_box">
	            <h6 class="remarks_title">범례 - 위험 등급 발생 빈도</h6>
	        </div>
        	<div class="remarks_wrap">
            	<div>
	                <ul class="check_line_container">
	                    <li class="check_line_box remarks-set2-1">41이하</li>
		                <li class="check_line_box remarks-set2-2">21이상 - 30이하</li>
		                <li class="check_line_box remarks-set2-3">11이상 - 20이하</li>
		                <li class="check_line_box remarks-set2-4">5이상 - 10이하</li>
		                <li class="check_line_box remarks-set2-5">4이하</li>
	                </ul>
            	</div>
	            <div class="unit">단위 : (회)</div>
        	</div>
    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	    $('#map-container').append(remarksItem);
	    legendToggle();*/
	    
	    resultChange();
    }

    
    function fnSearchList(reset = false){

		$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
		var startDate = $("input[name='startDate']").val();
		if(isNull(startDate) || startDate == ''){
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		if(reset){
			$("#mapPage").val("1");
		}
    	var page = $("#mapPage").val();
    	
    	$.ajax({
    		type : "get",
    		data : $("#searchForm").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/danger/BD_BUS_DANGER_002/data.ajax",
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
								'<td colspan="3">차량정보를 찾을 수 없습니다.</td>' +
							'</tr>';
    			}else{
	    			$(result.data.resultList).each(function(index, item){
	    				html += '<tr onclick="setRouteId(\'' + item.plateNo+ '\')">' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.plateNo + '</label>' + '</td>' +
									/*'<td>' + '<label for="listItem'+index+'">' + item.companyNm + '</label>' + '</td>' +*/
	    						'</tr>';
	    						
	    			})
    			}
    			$("#tableHeader").removeClass("none");
    			$("#modalTable>tbody").append(html);
    			var paging = result.data.paging;
    			if(paging != null && paging != ''){
    				title += '<span id="totalCnt2">'+numberComma(paging.totalCount)+'</span>개의 검색결과를 찾았습니다.';
    				$("#modalPaging").append(getGisPagingHtml(paging, page));
    			};
    			$(".tableTitle").html(title);
//    				$("#totalCnt2").text(numberComma(dataTotalCnt2))
    		},
    		complete : function(){
    			endLoading();
    		}
    		
    	});
    }
	
	function setRouteId(routeId){
		$("#routeId").val(routeId);
	}
</script>

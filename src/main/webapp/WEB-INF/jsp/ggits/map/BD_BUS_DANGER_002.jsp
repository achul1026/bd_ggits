<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
			<input type="hidden" name="routeId" id="routeId" value=""/>
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <input type="hidden" id="startYear" value="<c:out value='${yearsList[fn:length(yearsList) -1].year}'/>">
            	<input type="hidden" id="endYear" value="<c:out value='${yearsList[0].year}'/>">
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	                <option value="searchAllYear">전체</option>
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
		            <input type="hidden" name="searchPeriod" value="startDate">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="searchLocation">
	                <option value="">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
			<div class="tab_item_box flex-center">
		        <h5 class="tab_item_title">노선<span class="required-alert">*</span></h5>
		        <input type="text" placeholder="노선을 입력해주세요." name="routeNm" id="routeNm" class="input_same search_box radius">
		        <button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
	        </div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:430px;">
				<div class="table_search_number tableTitle">
                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
                </div>			
				<table id="modalTable" class="result_change">
				    <colgroup>
				        <col style="width:10%">
				        <col style="width:18%">
				        <col style="width:18%">
				        <col style="width:18%">
				        <col style="width:18%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">선택</th>
				            <th scope="col">버스번호</th>
				            <th scope="col">출발지</th>
				            <th scope="col">종착지</th>
				            <th scope="col">버스유형</th>
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
		if(isNull(routeId) || routeId == ''){
			new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		
        map.bigdata.getPublicTransferDangerSectionInfo($("#searchForm").serialize());
        bigdataSearchForm = $("#searchForm").serializeObject();
        
        var remarksItem = $(`
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
	    $('#map-container').append(remarksItem);
	    legendToggle();
	    
	    resultChange();
    }
	
    
 	$(document).ready(function() {
		/* if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		} */
	}) 
    
    function fnSearchList(){
    	$("#modalTable>tbody > tr").remove();
    	$("#modalPaging > .dashboard-pg-wrap").remove();
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
								'<td colspan="5">노선 정보를 찾을 수 없습니다.</td>' +
							'</tr>';
    			}else{
	    			$(result.data.resultList).each(function(index, item){
	    				html += '<tr onclick="setRouteId(' + item.routeId+ ')">' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.routeNm + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.stStaNm + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.edStaNm + '</label>' + '</td>' +						
									'<td>' + '<label for="listItem'+index+'">' + item.routeTp + '</label>' + '</td>' +
	    						'</tr>';
	    						
	    			})
    			}
    			$("#tableHeader").removeClass("none");
    			$("#modalTable>tbody").append(html);
    			var paging = result.data.paging;
    			if(paging != null && paging != ''){
    				title += '<span id="totalCnt2">'+paging.totalCount+'</span>개의 검색결과를 찾았습니다.';
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

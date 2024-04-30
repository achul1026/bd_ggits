<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    	<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
    	<input type="hidden" id="mapPage" name="page" value="1">
    	<input type="hidden" id="routeId" name="routeId" value="">
	       <%-- <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	                <option value="searchAllYear">전체</option>
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           		</c:forEach>
	            </select>
	        </div>--%>
	        <div class="tab_item_box flex-center">
                <h5 class="tab_item_title">날짜<span class="required-alert">*</span></h5>
                <div class="calendar">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
<!-- 		            ~ -->
<!-- 		            <div class="end_calendar_box"> -->
<!-- 						<div class="date_picker_block"></div>								             -->
<!-- 			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" id="endDate" placeholder="날짜를 선택해주세요." autocomplete="off"> -->
<!-- 		            </div>	 	             -->
				</div>
	        </div>
			<div class="tab_item_box flex-center">
		        <h5 class="tab_item_title">버스노선<span class="required-alert">*</span></h5>
<!-- 		        <select class="selectBox radius" name="searchResultType"> -->
<!-- 	                <option value="route">버스노선</option> -->
<!-- 	                <option value="fclt">정류장</option> -->
<!-- 	            </select> -->
		        <input type="text" placeholder="노선명을 입력해주세요." name="searchContent" id="searchContent" class="input_same search_box radius">
				<button type="button" class="is-darkgreen-btn ml8" id="srchBtn">검색</button>	 
				<div style="font-size: 12px;font-weight: 300;">ex) 8202 , H123</div>       
	        </div>
			<div class="tab_item_box flex-center pt8 none" id="tableHeader">
				<h5 class="tab_item_title"></h5>
				<div class="gis_table_scroll" style="width:500px;">
					<input type="hidden" id="mapX">
					<input type="hidden" id="mapY">
					<div class="table_search_number tableTitle">
	                	<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
	                </div>				
					<table id="routeTb" class="none result_change">
					    <colgroup>
					        <col style="width:10%">
					        <col style="width:20%">
					        <col style="width:20%">
					        <col style="width:20%">
					        <col style="width:20%">
					    </colgroup>
					    <thead>
					        <tr>
					            <th scope="col">선택</th>
								<th scope="col">버스유형</th>
					            <th scope="col">버스번호</th>
					            <th scope="col">기점정류장</th>
					            <th scope="col">종점정류장</th>
					        </tr>
					    </thead>
					    <tbody>
					    </tbody>
					</table>
					
					<table id="fcltTb" class="none result_change">
					    <colgroup>
					        <col style="width:10%">
					        <col style="width:30%">
					        <col style="width:30%">
					        <col style="width:30%">
					    </colgroup>
					    <thead>
					        <tr>
					            <th scope="col">선택</th>
					            <th scope="col">정류장명</th>
					            <th scope="col">BIT유형</th>
					            <th scope="col">BIT 수</th>
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
	            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="publicTransportResult(this)">결과보기</button>
	        </div>
        </form>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_TRANS_007");
	
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
			var startDate = $("#startDate").val();
			var searchContent =  $("#searchContent").val();
			
			if(startDate == null || startDate == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택 해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false;
			}
			if(searchContent == null || searchContent == ''){
				new ModalBuilder().init().alertBoby("검색어를 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false;
			}
		
	    	$("#modalPaging > .dashboard-pg-wrap").remove();
	    	var page = $("#mapPage").val();
	    	
	    	$.ajax({
	    		type : "get",
	    		data : $("#searchForm").serialize(),
	    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_007/data.ajax",
	    		beforeSend : function(){
	    			startLoading();
	    		},
	    		success : function(result){
	    			if(result.code == 200){
		    			var html = '';
		    			var title = '';
		    			var startDate = '';
		    			var endDate = '';
		    			
		    			$("#modalPaging > .dashboard-pg-wrap").remove();
	    				$("#fcltTb>tbody>tr").remove();
	    				$("#routeTb>tbody>tr").remove();
	    				$("#tableHeader").removeClass("none");
	    				
		    			if(result.data.resultList.length > 0){
		    				$(result.data.resultList).each(function(index, item){
		        				var routeInterval = !isNull(item.routeInterval) ? item.routeInterval : '-';
		        				html += '<tr>' +
		    								'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio" onclick=fnSetRouteId('+item.routeId+') >' + '</td>' +
											'<td>' + '<label for="listItem'+index+'">' + item.routeTp + '</label>' + '</td>' +
		    								'<td>' + '<label for="listItem'+index+'">' + item.routeNm + '</label>' + '</td>' +
		    								'<td>' + '<label for="listItem'+index+'">' + item.stStaNm + '</label>' + '</td>' +
		    								'<td>' + '<label for="listItem'+index+'">' + item.edStaNm + '</label>' + '</td>' +
		    							'</tr>';    					
		        					
		        			});
		        			$("#routeTb>tbody").append(html);
		        			$("#fcltTb").addClass("none");
		        			$("#routeTb").removeClass("none");
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
		    			} else {
		    				title += '<span id="totalCnt2">0</span>개의 검색결과를 찾았습니다.';
		    				html += '<tr>' +
		    						'<td colspan="5">조회된 결과가 없습니다.</td>'+
		    						'</tr>';
		    						
			    			$(".tableTitle").html(title);
		        			$("#routeTb>tbody").append(html);
		        			$("#fcltTb").addClass("none");
		        			$("#routeTb").removeClass("none");
		    			}
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
	
	function fnSetRouteId(routeId){
		$("#routeId").val(routeId);
	}
	
	function publicTransportResult(_this){
    	var radioChecked = $('input:radio[name=listItem]').is(':checked');
		if($("#startDate").val() === ""){
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			return;
		} else if(radioChecked == false) {
    		new ModalBuilder().init().alertBoby("버스노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		window.map.bigdata.getPublicTransferBIT($("#searchForm").serialize());
		resultChange();
	}
</script>

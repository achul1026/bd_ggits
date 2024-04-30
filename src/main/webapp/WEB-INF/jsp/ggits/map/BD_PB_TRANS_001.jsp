<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">권역별 대중교통<br>이용현황 순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">권역별 대중교통 이용현황 순위</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<form id="searchForm1" method="get" class="wd100">
	                		<input type="hidden" name="searchResultType" value="1">
		                	<div>
		                		<button type="button" class="prev_text rollbackBtn prevIndexBtn" data-value="1"><span class="prev_arrow">←</span> 이전</button>
		                	</div>
	                        <div class="tab_item_box flex-center">
	                            <h5 class="tab_item_title">일자</h5>
								<input type="text" class="date_picker input_same mr8 input_picker end_date_check today searchTrgt" name="searchTime" placeholder="날짜를 선택해주세요." autocomplete="off" data-value="1" >
	                        	<input type="hidden" class="todayTxt">
	                        </div>
							<div class="tab_item_box flex-center">
		                        <h5 class="tab_item_title">시군</h5>
		                        <select class="selectBox radius selectbox_width searchTrgt" id="sigunCdId1" name="sigunCdId" data-value="1">
									<option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
										<option value="<c:out value='${sggCdList.cdId}00000'/>"><c:out value='${sggCdList.cdNm}'/></option>
									</c:forEach>
								</select>
		                    </div>                     
							<div class="tab_item_box flex-center">
		                        <h5 class="tab_item_title">유형</h5>
		                        <select class="selectBox radius selectbox_width searchTrgt" id="routeTp1" name="routeTp" data-value="1">
										<option value="13" selected="selected">일반형시내버스</option>
										<option value="11">직행좌석형시내버스</option>
										<option value="12">좌석형시내버스</option>
										<option value="21">직행좌석형농어촌버스</option>
										<option value="22">좌석형농어촌버스</option>
										<option value="23">일반형농어촌버스</option>
										<option value="30">마을버스</option>
										<option value="41">고속형시외버스</option>
										<option value="42">좌석형시외버스</option>
										<option value="43">일반형시외버스</option>
										<option value="51">리무진형공항버스</option>
										<option value="52">좌석형공항버스</option>
										<option value="53">일반형공항버스</option>
								</select>
		                    </div>
	                    </form>                     
                        <div class="tab_item_box chartBox1" style="width:400px; height:300px;">
                            <canvas id="tab1" class="chartCan1"></canvas>
                        </div>	                	
	                </div>
	            </div>
        	</div>
        </div>
    </div>
</div>

<script>
	bigdataTabMenu();
	bigdataPopupPrev();
	gisCheckInit();
	bigdataPopupClose();
	datePickerInit();
	
	let endDate = $('.end_date_check').val();
	$(".todayTxt").val(endDate);
	
	$(".searchTrgt").on("change", function(){
		var chartNum = $(this).data("value");
		$(".chartCan"+chartNum).remove();
		$(".chartBox"+chartNum).append(
				 '<canvas id="tab'+chartNum+'" class="chartCan'+chartNum+'"></canvas>'
		)
		fnSearchList(chartNum);
	})
	
	$(".prevIndexBtn").on("click", function(){
		var chartNum = $(this).data("value");
		$(".chartCan"+chartNum).remove();
		$(".chartBox"+chartNum).append(
				 '<canvas id="tab'+chartNum+'" class="chartCan'+chartNum+'"></canvas>'
		)
		$("#routeTp"+chartNum).val("13").prop("selected", true);
		$("#sigunCdId"+chartNum).val("searchAllLocation").prop("selected", true);
		$(".today").val($(".todayTxt").val());
		if(chartNum == 1){
			//tab1
			var busUseRouteIdArr = '<c:out value="${busUseRouteIdArr}"/>'; 
			var busUserCntArr = '<c:out value="${busUserCntArr}"/>'; 
			settingChart(busUseRouteIdArr, busUserCntArr, chartNum);
		}
	})
	
	function fnSearchList(chartNum){
		$.ajax({
    		type : "get",
    		data : $("#searchForm"+chartNum).serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_001/data.ajax",
    		success : function(result){
    			if(chartNum == 1){
	    			var busUseRouteIdArr = result.data.busUseRouteIdArr; 
	    			var busUserCntArr = result.data.busUserCntArr; 
	    			settingChart(busUseRouteIdArr, busUserCntArr, chartNum);	
    			}
    		}
    	})
	}
	
	//tab1
	var busUseRouteIdArr = '<c:out value="${busUseRouteIdArr}"/>'; 
	var busUserCntArr = '<c:out value="${busUserCntArr}"/>'; 

	new GITSChart(GITSChartType.BAR).init("tab1")
	.setData({
             labels: busUseRouteIdArr.split(','),
             datasets: [{
            	 label:'이용현황',
            	 data:busUserCntArr.split(','),
            	 backgroundColor:'#00BCB1'
             }]
         })
    .setTickStepX(100)
    .setAxis('y')
    .setBarGridX(true)
    .draw();
	
    function settingChart(routeIdArr, cntArr, chartNum){
   		//tab1
   		if(isNull(routeIdArr)) routeIdArr="";
   		if(isNull(cntArr)) cntArr="";
       	var busUseRouteIdArr = routeIdArr; 
       	var busUserCntArr = cntArr; 
       	new GITSChart(GITSChartType.BAR).init("tab1")
       	.setData({
             labels: busUseRouteIdArr.split(','),
             datasets: [{
            	 label:'이용현황',
            	 data:busUserCntArr.split(','),
            	 backgroundColor:'#00BCB1'
	             }]
         })
           .setTickStepX(100)
           .setAxis('y')
           .setBarGridX(true)
           .draw();
    }
</script>
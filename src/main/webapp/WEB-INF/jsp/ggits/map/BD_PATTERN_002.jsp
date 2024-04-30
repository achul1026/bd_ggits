<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" class="result_change">
			<input type="hidden" name="type" value="quantity">
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">수집원별</h5>
				<select class="selectBox radius result_change" name="collectType">
					<option value="smc">스마트교차로</option>
					<option value="vds">VDS</option>
<!-- 					<option value="dsrc">DSRC</option> -->
				</select>
			</div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius result_change" name="searchYear">
	           	<c:forEach var="yearsList" items="${yearsList}">
	                <option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           	</c:forEach>
	            </select>
	        </div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">기간<span class="required-alert">*</span></h5>
					<div class="calendar" id="directDate">
						<input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
						~
						<div class="end_calendar_box">
							<div class="date_picker_block"></div>
							<input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
						</div>
					</div>
				</div>
			</div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">기간구분<span class="required-alert">*</span></h5>
					<label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="" checked>전체</label>
					<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekday">평일</label>
					<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
				</div>
			</div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">도로<span class="required-alert">*</span></h5>
	            <div class="road_rank_list_box">
		            <button type="button" class="is-dark-btn road_all_selector radius is-darkgreen-btn" id="searchAllRoadRankBtn">전체선택/해제</button>
		            <c:import url="/WEB-INF/jsp/ggits/common/modalRoadRankList.jsp" />
	            </div>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="trafficResultEvent();">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	
	$("#searchAllRoadRankBtn").on("click",function(){
		var allChkVal = $(this).hasClass("is-darkgreen-btn");
		var roadRank = $("input[name='roadRank']");
		var roadRankVal = "";
		if(allChkVal){
			roadRank.each(function(idx,item){
				if(idx == 0){
					roadRankVal = $(item).val();
				}else{
					roadRankVal += ","+$(item).val();
				}
			})
		}
		$("#searchRoadRank").val(roadRankVal);
	});
	
	function trafficResultEvent(){
		var startDate = $("input[name='startDate']");
		var endtDate = $("input[name='endDate']");
		var roadRank = $("input[name='roadRank']");
		var roadRankCheckedVal = roadRank.is(":checked");
		if(!roadRankCheckedVal){
			new ModalBuilder().init().alertBoby("도로를 1개 이상 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		if(!startDate.val() || !endtDate.val()) {
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}

		var roadRankVal = "";
		roadRank.each(function(idx,item){
			if($(item).parent().hasClass("is-darkgreen-btn")){
				if(roadRankVal == ""){
					roadRankVal = $(item).val();
				}else{
					roadRankVal += ","+$(item).val();
				}
			}
		})
		$("#searchRoadRank").val(roadRankVal);
		
		/*var crossroadsType = $("input[name='searchCrossroadsType']:checked").val();
		if(crossroadsType == 'map'){
			var mapNodeIdList = __Map.querySourceFeatures("GITS_NODE", {sourceLayer : "ggits_node", filter: ["==", "NODE_TYPE", "101"]}).map(item => { return item.id});
			$("#searchCrossroadsNodeId").val(mapNodeIdList);
		}*/
		var searchForm = $("#searchForm").serialize();
        bigdataSearchForm = $("#searchForm").serializeObject();
        
        window.map.bigdata.getPatternTrafficQuantity(searchForm);
        
        var remarksItem =$(`
	        <div class="remarks_container">
		        <div class="remarks_title_box">
		            <h6 class="remarks_title">범례 - 교통량</h6>
		        </div>
	        	<div class="remarks_wrap">
	            	<div>
		                <ul class="check_line_container">
		                    <li class="check_line_box remarks-red">1131 이상</li>
		                    <li class="check_line_box remarks-orange">1101 - 1130 이하</li>
		                    <li class="check_line_box remarks-light-orange">171 - 1100 이하</li>
		                    <li class="check_line_box remarks-light-green">141 - 170 이하</li>
		                    <li class="check_line_box remarks-green">0 - 140 이하</li>
		                </ul>
	            	</div>
		            <div class="unit">단위 : (대)</div>
	        	</div>
	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
        $('#map-container').append(remarksItem);
        legendToggle();
        resultChange();        
    }
	
	
	$("#searchCrossBtn").on('click', function(){
		$("#mapPage").val("1");
		fnSearchCrossListForBigdata();
	});
	
	$("#selectList").on("click",function(){
		var checkedVal = $(this).is(":checked");
		$("#crossroadsListDiv").removeClass("none");
		fnSearchCrossListForBigdata();
	})
	
	$("#selectMap").on("click",function(){
		$("#crossroadsListDiv").addClass("none");
	})
	
</script>

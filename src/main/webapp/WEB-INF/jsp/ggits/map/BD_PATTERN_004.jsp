<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
	                <option value="searchAllYear">전체</option>
	           	<c:forEach var="yearsList" items="${yearsList}">
	                <option value="${yearsList.year}">${yearsList.year}년</option>
	           	</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchPeriod" value="weekday" checked="checked">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchPeriod" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요.">
		            ~
		            <div class="end_calendar_box">
						<div class="date_picker_block"></div>								            
			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요.">
		            </div>		            
				</div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchTime" value="workingTime" checked="checked">출근 <span class="group_btn_span">(06시~10시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">시간 설정</label>
	            </div>
	            <div class="calendar direct_time none">
					<select class="selectBox selectTime" name="startTime" id="startTime"></select>
	                ~
					<select class="selectBox selectTime ml8" name="endTime" id="endTime"></select>
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
					</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">도로</h5>
	            <div class="road_rank_list_box">
		            <button type="button" class="is-dark-btn road_all_selector radius is-darkgreen-btn" id="searchAllRoadRankBtn">전체선택/해제</button>
		            <c:import url="/WEB-INF/jsp/ggits/common/modalRoadRankList.jsp" />
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">교차로별</h5>
	            <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn">
	            	<input type="checkbox" class="none" id="selectMap" name="searchCrossroadsType" value="all" checked="checked">전체
	            	<input type="hidden" id="searchCrossroadsNodeId" name="searchCrossroadsNodeId">
	            </label>
	            <label class="group_btn_item is-dark-btn radius inpd">
	            	<input type="checkbox" class="none" id="selectList" name="searchCrossroadsType" value="list">리스트에서 선택
	            </label>
	        </div>
	        <div class="tab_item_box flex-center pt8 none" id="crossroadsListDiv">
				<h5 class="tab_item_title"></h5>
				<div class="gis_table_scroll">
					<div class="mt16">
					    <div class="dashboard_sub_title">교차로명으로 찾기</div>
					    <input type="text" class="dashboard_input" id="searchCrsrdNm" placeholder="교차로명을 입력해주세요.">
					    <input type="hidden" id="mapPage" name="page" value="1"/>
					</div>
					<div class="dashboard_btn_box">
					    <button type="button" class="is-darkgreen-btn modal_input_srbtn" id="searchCrossBtn">찾기</button>
					</div>
					<div class="mt16">
						<table>
						    <colgroup>
						        <col style="width:10%">
						        <col style="width:10%">
						        <col style="width:80%">
						    </colgroup>
						    <thead>
						        <tr>
						            <th scope="col"></th>
						            <th scope="col">번호</th>
						            <th scope="col">교차로명</th>
						        </tr>
						    </thead>
						    <tbody id="crossroadsListBody">
						    </tbody>
						</table>
					</div>
					<div id="pagingDiv">
					</div>
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
	tabTitleCss();
	resultRemove();
	
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
		var roadRank = $("input[name='roadRank']");
		var roadRankCheckedVal = roadRank.is(":checked");
		if(!roadRankCheckedVal){
			new ModalBuilder().init().alertBoby("도로를 1개 이상 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
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
		
        window.map.bigdata.getPatternTrafficQuantity(searchForm);
        //범례
        var remarksItem =$(`
    	        <div class="remarks_container">
    		        <div class="remarks_title_box">
    		            <h6 class="remarks_title">범례 - 혼잡강도</h6>
    		        </div>
    	        	<div class="remarks_wrap tab-none">
    	            	<div>
    		                <div class="check_line_container">
	                            <button type="button" class="check_line_box remarks-purple">97-100 이하</button>
		                        <button type="button" class="check_line_box remarks-light-purple">63 - 97 이하</button>
		                        <button type="button" class="check_line_box remarks-bright-purple">34 - 63 이하</button>
		                        <button type="button" class="check_line_box remarks-dark-pink">13 - 34 이하</button>
		                        <button type="button" class="check_line_box remarks-light-pink">0 - 13 이하</button>
    		                </div>
    	            	</div>
    		            <div class="unit">단위 : (%)</div>
    	        	</div>
    	    	</div>`)        
            $('#map-container').append(remarksItem);
            legendToggle();        
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

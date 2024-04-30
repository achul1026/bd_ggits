<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <form id="searchForm" class="result_change">
        	<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
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
           	<span style="font-size:12px; margin-right:200px">*현재시간 기준 30분 후를 예측한 데이터입니다.</span>
            <button type="button" onclick="accidentPredictionResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PREDICTION_003");
	
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
	function accidentPredictionResult(){
		
		var roadRank = $("input[name='roadRank']");
		var roadRankCheckedVal = roadRank.is(":checked");
		var selectDate = $(".date_picker").val();
		
		if(!roadRankCheckedVal) {
			new ModalBuilder().init().alertBoby("도로를 1개 이상 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return
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
		
		var searchForm = $("#searchForm").serialize();
		map.bigdata.getTrafficAccidentPrediction(searchForm);
		bigdataSearchForm = $("#searchForm").serializeObject();
		
        var remarksItem =$(`
    	        <div class="remarks_container">
    		        <div class="remarks_title_box">
    		            <h6 class="remarks_title">범례 - 사고예측 도로 위험 지수</h6>
    		        </div>
    	        	<div class="remarks_wrap">
    	            	<div>
    		                <ul class="check_line_container">
			                    <li class="check_line_box remarks-red">심각</li>
			                    <li class="check_line_box remarks-orange">위험</li>
			                    <li class="check_line_box remarks-light-orange">주의</li>
								<li class="check_line_box remarks-green">안전</li>
    		                </ul>
    	            	</div>
    		            <div class="unit">단위 : 위험 지수 단계</div>
    	        	</div>
    	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
            $('#map-container').append(remarksItem);
            legendToggle();
        resultChange();
	}
</script>

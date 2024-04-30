<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" class="result_change">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">날짜<span class="required-alert">*</span></h5>
					<select class="selectBox radius" name="startDate" id="startDate">
						<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
							<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.yearnm}'/></option>
						</c:forEach>
					</select>
				</div>
			</div>
	        <%--<div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>--%>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">도로<span class="required-alert">*</span></h5>
	            <div class="road_rank_list_box">
		            <button type="button" class="is-dark-btn road_all_selector radius is-darkgreen-btn" id="searchAllRoadRankBtn">전체선택/해제</button>
					<label class="road_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="roadRank" value="고속국도" checked="checked">고속국도</label>
					<label class="road_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="roadRank" value="도시고속국도" checked="checked">도시고속국도</label>
					<label class="road_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="roadRank" value="일반국도" checked="checked">일반국도</label>
					<label class="road_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="roadRank" value="지방도" checked="checked">지방도</label>
					<label class="road_item is-dark-btn radius inpd is-darkgreen-btn mt8"><input type="checkbox" class="none" name="roadRank" value="시군도" checked="checked">시군도</label>
					<input type="hidden" id="searchRoadRank" name="searchRoadRank"/>
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
	settingBigdataSearchParam("BD_PATTERN_004");
	
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
		let startDate = $("select[name='startDate']").val();
		if(isNull(startDate) || startDate == ''){
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return false;
		}

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

		var searchForm = $("#searchForm").serialize();
		bigdataSearchForm = $("#searchForm").serializeObject();
        window.map.bigdata.getPatternTrafficCongDestiny(searchForm);
        
        //범례
        var remarksItem =$(`
    	        <div class="remarks_container">
    		        <div class="remarks_title_box">
    		            <h6 class="remarks_title">범례 - 정체구간</h6>
    		        </div>
    	        	<div class="remarks_wrap">
    	            	<div>
    		                <ul class="check_line_container">
	                            <li class="check_line_box remarks-red">정체</li>
								<li class="check_line_box remarks-orange">지체(서행)</li>
								<li class="check_line_box remarks-green">원활</li>
    		                </ul>
    	            	</div>
    		            <div class="unit">단위 : 소통등급 / 수집원 : GITS<br/>
						<a href='javascript:void(0)' style="text-decoration: underline;color:white;" onclick="openSvcCongestionCalcInfo()">집계 데이터 정보</a></div>
    	        	</div>
    	    	</div>`);
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

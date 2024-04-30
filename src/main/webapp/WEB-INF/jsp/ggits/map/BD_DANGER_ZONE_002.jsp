<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
					<option value="">전체</option>
		           	<c:forEach var="yearsList" items="${yearsList}">
						<c:if test="${fn:startsWith(yearsList.year, '20')}">
		                <option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
						</c:if>
		           	</c:forEach>
	            </select>
	        </div>
	        <%--<div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchPeriod" value="weekday" checked="checked">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="weekend">주말</label>
	<!-- 	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="holiday">공휴일</label> -->
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchPeriod" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none" id="directDate">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            ~
		            <div class="end_calendar_box">
						<div class="date_picker_block"></div>								            
			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
			        </div>	  		            
				</div>
	        </div>--%>
	        <%--<div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchTime" value="workingTime" checked="checked">출근 <span class="group_btn_span">(06시~10시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">시간 설정</label>
	            </div>
	            <div class="calendar direct_time none" id="directTime">
					<select class="selectBox selectTime" name="startTime" id="startTime"></select>
	                ~
					<select class="selectBox selectTime" name="endTime" id="endTime"></select>
	            </div>
	        </div>--%>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius"  name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdNm}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
	       	<div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">유형<span class="required-alert">*</span></h5>
	            <div class="road_rank_list_box">
					<button type="button" class="is-dark-btn danger_zone_all_selector radius is-darkgreen-btn mb8" id="searchAllDangerZoneBtn">전체선택/해제</button>
		            <c:import url="/WEB-INF/jsp/ggits/common/modalDangerZoneList.jsp" />
	            </div>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="dangerZoneResultEvent()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	
	settingBigdataSearchParam("BD_DANGER_ZONE_002")
	
	
	function dangerZoneResultEvent(){
		
		var searchPeriod = $("input[name='searchPeriod']:checked").val();
		
		if($("input[name='dangerType']:checked").length == 0){
			new ModalBuilder().init().alertBoby("도로유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
		}
		
		if(searchPeriod === 'directDate'){
			let startDate = $("input[name='startDate']").val();
			let endDate = $("input[name='endDate']").val();
			if(startDate == '' || endDate == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();			
				return false;
			}
		}
		
	    var remarksItem =$(`
	    	<div class="remarks_container">
		        <div class="remarks_title_box">
		            <h6 class="remarks_title">범례 - 위험 도로 유형</h6>
		        </div>
	        	<div class="remarks_wrap">
	            	<div>
		                <div class="check_line_container">
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-1">안전거리미확보</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-2">미끄러운도로</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-3">급커브(굽은도로)</span>
		                    </div>
		                    <div class="check_line_box check_border">
			                	<span class="remarks_circle set5-4">급경사(내리막)</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-5">기상-결빙</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-6">기상-비</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-7">기상-눈</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-8">침수</span>
		                    </div>
		                    <div class="check_line_box check_border">
		                        <span class="remarks_circle set5-9">과속</span>
		                    </div>
		                </div>
	            	</div>
		            <div class="unit">단위 : 위험 도로 유형</div>
	        	</div>
	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	        $('#map-container').append(remarksItem);
	        legendToggle();
			map.bigdata.getRoadDangerInfo($("#searchForm").serialize());
			bigdataSearchForm = $("#searchForm").serializeObject();
			
			resultChange();
			
	} 

	/*$("#searchAllDangerZoneBtn").on("click",function(){
		var allChkVal = $(this).hasClass("is-darkgreen-btn");
		var dangerType = $("input[name='dangerType']");
		var dangerTypeVal = "";
		if(allChkVal){
			dangerType.each(function(idx,item){
				if(idx == 0){
					dangerTypeVal = $(item).val();
				}else{
					dangerTypeVal += ","+$(item).val();
				}
				$(this).prop("checked",true);
			})
		}else{
			dangerType.each(function(idx,item){
				$(this).prop("checked",false);
			});
		}
		$("#searchDangerType").val(dangerTypeVal);
	});*/
	
</script>

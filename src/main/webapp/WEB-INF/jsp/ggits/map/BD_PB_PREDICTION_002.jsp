<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    	<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
        <div class="tab_item_box">
            <div class="flex-center">
                <h5 class="tab_item_title">날짜<span class="required-alert">*</span></h5>
                <input type="hidden" name="searchPeriod" value="startDate">
                <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
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
        <div class="tab_item_box">
            <div class="flex-center">
                <h5 class="tab_item_title">View 모드<span class="required-alert">*</span></h5>
                <select class="selectBox radius" name="viewMode">
                    <option value="3d">3D</option>
                    <option value="2d">2D</option>
                </select>
            </div>
        </div>
        <div class="bottom_btn">
            <button type="button" onclick="viewResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
        </form>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_PREDICTION_002");
	
    function viewResult(){
    	var startDate = $("#startDate").val();
    	if(isNull(startDate) || startDate == ''){
    		new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return false;
    	}
    	
		window.map.bigdata.getPopulationCongestionInfo($("#searchForm").serialize())
		bigdataSearchForm = $("#searchForm").serializeObject();
		
	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 유동인구 밀집도</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <ul class="check_line_container">
		                        <li class="check_line_box remarks-set2-1">5,000명 이상</li>
                                <li class="check_line_box remarks-set2-2">4,000명 ~ 5,000명</li>
		                        <li class="check_line_box remarks-set2-3">3,000명 ~ 4,000명</li>
		                        <li class="check_line_box remarks-set2-4">2,000명 ~ 3,000명</li>
		                        <li class="check_line_box remarks-set2-6">2,000명 이하</li>
			                </ul>
		            	</div>
			            <div class="unit">단위 : (명)</div>
		        	</div>
		    	</div>`);
        $('#map-container').find(".remarks_container").remove();
        $('#map-container').append(remarksItem);
        legendToggle();
        resultChange();
    }

    $(function(){
        $("select[name='viewMode']").on("change", function(){
            if($(this).val() === "3d"){
                __Map.setPitch(65);
                __Map.setPaintProperty(GITS_ENV.LAYER.GRID, 'fill-extrusion-height', ['number',['feature-state','fltPop_00'], 0]);
            }else{
                window.map.control.setDefaultPanTilt()
                __Map.setPaintProperty(GITS_ENV.LAYER.GRID, 'fill-extrusion-height', 0);
            }
        })
    })
    
</script>

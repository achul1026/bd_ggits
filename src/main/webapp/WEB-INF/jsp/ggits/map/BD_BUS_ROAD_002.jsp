<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <div class="tab_item_box flex-center">
            <h5 class="tab_item_title">연도별</h5>
            <select class="selectBox radius">
                <option>전체 메인 메뉴</option>
                <option>테스트</option>
                <option>테스트</option>
                <option>테스트</option>
            </select>
        </div>
        <div class="tab_item_box">
            <div class="flex-center">
                <h5 class="tab_item_title">기간</h5>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">평일</label>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">주말</label>
                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none">직접입력</label>
            </div>
            <div class="calendar direct_time none">
	            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
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
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">출근 <span class="group_btn_span">(06시~10시)</span></label>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none">직접 입력</label>
            </div>
            <div class="calendar direct_time none">
				<select class="selectBox selectTime" id="startTime"></select>
                ~
				<select class="selectBox selectTime" id="endTime"></select>
            </div>
        </div>
		<div class="tab_item_box flex-center border-none">
	        <h5 class="tab_item_title">노선찾기</h5>
	        <input type="text" id="inputChange1" placeholder="출발지 입력" class="input_same search_box tab_box_input radius">
	        <button type="button" class="tab_box_change_img" onclick="transferValue()"><img src="${pageContext.request.contextPath}/statics/images/change.png" alt="체인지"></button>
	        <input type="text" id="inputChange2" placeholder="도착지 입력" class="input_same search_box tab_box_input radius">
        </div>
        <div class="tab_item_box flex-center border-none pt8">
            <h5 class="tab_item_title"></h5>
            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">전체통행</label>
            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">단일통행</label>
            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">환승통행</label>
        </div>
		<div class="tab_item_box flex-center pt8">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:370px;">
				<div class="table_search_number">
                	<span>"2개"</span>의 검색결과를 찾았습니다.
                </div>				
	            <table class="all_center">
	                <colgroup>
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th scope="col" rowspan="2">버스번호</th>
	                        <th scope="col" colspan="3">단일통행</th>
	                        <th scope="col" colspan="3">환승통행</th>
	                    </tr>
	                    <tr>
	                        <th>승차</th>
	                        <th>하차</th>
	                        <th>전체</th>
	                        <th>승차</th>
	                        <th>하차</th>
	                        <th>전체</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td>1112</td>
	                        <td>44</td>
	                        <td>5</td>
	                        <td>49</td>
	                        <td>2</td>
	                        <td>0</td>
	                        <td>2</td>
	                    </tr>
	                    <tr>
	                        <td>77</td>
	                        <td>125</td>
	                        <td>128</td>
	                        <td>253</td>
	                        <td>11</td>
	                        <td>10</td>
	                        <td>21</td>
	                    </tr>
	                </tbody>
	            </table>
			</div>
        </div>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="sectionResult()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();
	
	function sectionResult(){	
        var remarksItem = $(`
        <div class="remarks_container">
	        <div class="remarks_title_box">
	            <h6 class="remarks_title">범례 - 이용 승객 수</h6>
	        </div>
        	<div class="remarks_wrap tab-none">
            	<div>
	                <div class="check_line_container">
	                    <button type="button" class="check_line_box remarks-red">포화</button>
		                <button type="button" class="check_line_box remarks-orange">혼잡</button>
		                <button type="button" class="check_line_box remarks-light-orange">만석</button>
		                <button type="button" class="check_line_box remarks-green">여유</button>
	                </div>
            	</div>
	            <div class="unit">단위 : 이용량 치수 단계</div>
        	</div>
    	</div>`)        
	    $('#map-container').append(remarksItem);
	    legendToggle();
	}
</script>

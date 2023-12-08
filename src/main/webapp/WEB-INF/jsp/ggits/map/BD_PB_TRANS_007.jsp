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
		<div class="tab_item_box flex-center border-none">
	        <h5 class="tab_item_title">정류장</h5>
	        <input type="text" placeholder="정류장ID 또는 이름 입력" class="input_same search_box radius">
			<button type="button" class="is-darkgreen-btn ml8">검색</button>	        
        </div>
		<div class="tab_item_box flex-center pt8">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll" style="width:320px;">
				<div class="table_search_number">
                	<span>"2개"</span>의 검색결과를 찾았습니다.
                </div>				
				<table>
				    <colgroup>
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				        <col style="width:20%">
				    </colgroup>
				    <thead>
				        <tr>
				            <th scope="col">버스번호</th>
				            <th scope="col">출발지</th>
				            <th scope="col">도착지</th>
				            <th scope="col">버스유형</th>
				            <th scope="col">배차간격</th>
				        </tr>
				    </thead>
				    <tbody>
				        <tr>
				            <td>1112</td>
				            <td>수원역</td>
				            <td>매정동</td>
				            <td>광역버스</td>
				            <td>15분</td>
				        </tr>
				        <tr>
				            <td>1112</td>
				            <td>수원역</td>
				            <td>매정동</td>
				            <td>광역버스</td>
				            <td>15분</td>
				        </tr>
				        <tr>
				            <td>1112</td>
				            <td>수원역</td>
				            <td>매정동</td>
				            <td>광역버스</td>
				            <td>15분</td>
				        </tr>
				    </tbody>
				</table>
			</div>
        </div>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">분석리포트</h3>
            <div class="side_txt">
                교통정보 통게 및 데이터 활용<br>통계 분석 리포트 입니다.
            </div>
            <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/analysis/report/list.do" class="on">교통정보 통계 리포트</a>
			     <a href="${pageContext.request.contextPath}/statistics/analysis/data/report/list.do">데이터 활용 통계 리포트</a>
            </div>
        </div>
    </aside>
    <section class="main_section">
    	<form>
        <h2 class="blind">분석리포트</h2>
        <div class="table_btn_wrap clearfix">
            <div class="flex-center">
                <div class="mr8">
	                <select class="selectBox">
	                	<option>전체</option>
	                	<option>test1</option>
	                	<option>test1</option>
	                </select>
                </div>
                <div class="btn_search_wrap float-none">
                    <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box">
                    <input type="button" value="검색" class="input_same search_box2">
                    <button type="button" class="is-darkgreen-btn mj0">등록하기</button>
                </div>
            </div>
        </div>
        <div class="search_container">
	        <div class="search_head">
	            <div class="search_number">
	                <span>"121534개"</span>의 검색결과를 찾았습니다.
	            </div>
	            <div class="table_right_btn">
	                <button type="button" class="is-darkgreen-btn">보고서 만들기</button>
	            </div>
	        </div>
        </div>
        <div class="contents_wrap tab_area mt8">
			<table class="mt16">
				<colgroup>
					<col style="width:8%">
					<col style="width:30%">
					<col style="width:30%">
					<col style="width:12%">
					<col style="width:10%">
					<col style="width:10%">
				</colgroup>
				<thead>
					<tr>
					    <th scope="col">번호</th>
					    <th scope="col">제목</th>
					    <th scope="col">등록자</th>
					    <th scope="col">등록일</th>
					    <th scope="col">조회수</th>
					    <th scope="col">다운로드</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table_1depth">
						<td>1</td>
						<td>2023년 남양주시 교통정보 통계 보고서 3월</td>
						<td>김길동(glidong123@naver.com)</td>
						<td>2023.12.10</td>
						<td>1234</td>
						<td><button type="button">다운로드</button></td>
					</tr>
					<tr class="table_1depth">
						<td>2</td>
						<td>2023년 남양주시 교통정보 통계 보고서 4월</td>
						<td>김길동(glidong123@naver.com)</td>
						<td>2023.12.10</td>
						<td>1234</td>
						<td><button type="button">다운로드</button></td>
					</tr>
				</tbody>
			</table>
        </div>
        </form>
    </section>
</div>

<script>

	/* modal start */
	/* 통계보고서 */
	$('.table_1depth').on("click", function(){
	    new ModalBuilder().init('2023년 남양주시 데이터활용 통계보고서 5월').ajaxBody("${pageContext.request.contextPath}/common/modal/traffic/report/list.do").footer(1,'푸터없음',function(button, modal){}).open();
		//tab
		$(".tab_fc div button").each(function() {
		    $(this).click(function(){
		        for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
		            if($(this).attr("data-index") == i){
		                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).show();
		                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).siblings().hide();
		            }
		        }
		    });
		});
		$('.tab_btn_item').on('click', function(){
			$(this).parent(".table_btn_left").find('.tab_btn_item').removeClass('modal_input_bg');
			$(this).addClass('modal_input_bg');
		})
		//기간설정
		$('.date_picker').datepicker({
			dateFormat:'yy-mm-dd',
			changMonth:true,
			changYear:true,
			nextText:'다음 달',
			prevText:'이전 달',
			dayNames:['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
			dayNamesMin:['월','화','수','목','금','토','일'],
			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
			monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			showMonthAfterYear:true,
			yearSuffix:'년',
		    format: 'YYYY',
			minViewMode: 'years',
		    viewMode: "years", 
		})
		// chart1
	    new GITSChart(GITSChartType.BAR).init("modal_chart1")
	    .setDataSetLabel('항목1','항목2','항목3','항목4')
	    .setDataSet({
	            label:"sample1",
	            data:[5, 10, 3, 7],
	            backgroundColor: '#3A7DFF',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	        },{
	            label:"sample2",
	            data:[1, 4, 8, 6],
	            backgroundColor: '#F90',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	    })
	    .setTicksStep(2)
	    .setLabelDisplay(false)
	    .setAxis('x')
	    .setBarGridY(true)
	    .setBarGridX(false)
	    .setAxisStackedX(false)
	    .setAxisStackedY(false)
		.setTickX('#000')
		.setTickY('#000')
	    .draw();
		// chart2
	    new GITSChart(GITSChartType.BAR).init("modal_chart2")
	    .setDataSetLabel('항목1','항목2','항목3','항목4')
	    .setDataSet({
	            label:"sample1",
	            data:[1, 4, 8, 6],
	            backgroundColor: '#3A7DFF',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	        },{
	            label:"sample2",
	            data:[5, 10, 3, 7],
	            backgroundColor: '#F90',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	    })
	    .setTicksStep(2)
	    .setLabelDisplay(false)
	    .setAxis('x')
	    .setBarGridY(true)
	    .setBarGridX(false)
	    .setAxisStackedX(false)
	    .setAxisStackedY(false)
		.setTickX('#000')
		.setTickY('#000')
	    .draw();
		// chart3
	    new GITSChart(GITSChartType.BAR).init("modal_chart3")
	    .setDataSetLabel('항목1','항목2','항목3','항목4')
	    .setDataSet({
	            label:"sample1",
	            data:[5, 10, 3, 7],
	            backgroundColor: '#3A7DFF',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	        },{
	            label:"sample2",
	            data:[1, 4, 8, 6],
	            backgroundColor: '#F90',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	    })
	    .setTicksStep(2)
	    .setLabelDisplay(false)
	    .setAxis('x')
	    .setBarGridY(true)
	    .setBarGridX(false)
	    .setAxisStackedX(false)
	    .setAxisStackedY(false)
		.setTickX('#000')
		.setTickY('#000')
	    .draw();		
	});
</script>
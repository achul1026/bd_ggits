<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="blind">빅데이터 플렛폼</h2>
<div class="dashboard_container2 flex-center big_dash_wrap tab_set">
    <div class="dashboard2_contents mb10">
        <div class="dashboard2_sub_contents wdat bigdata_height">
            <div class="dashboard2_sub_title">
                <div class="sub_data_title">2023년 10월 20일</div>
            </div>
            <div class="dashboard2_big_list_wrap big_data_scroll tab_fc">
               <div class="big_list mt16" data-index="1">
					<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>2.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>76%</span></li>
						</ul>
					</div>
				</div>
				<div class="big_list" data-index="2">
					<div class="big_list_title pb8">[중지] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>12.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>14%</span></li>
						</ul>
					</div>
				</div>
                <div class="big_list" data-index="3">
					<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>34.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>16.4%</span></li>
						</ul>
					</div>
			   </div>
               <div class="big_list" data-index="4">
                	<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>1.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>4%</span></li>
						</ul>
					</div>
				</div>
               <div class="big_list" data-index="5">
					<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>64.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>99%</span></li>
						</ul>
					</div>
				</div>
               <div class="big_list" data-index="6">
					<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>5.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>70%</span></li>
						</ul>
					</div>
				</div>
               <div class="big_list" data-index="7">
					<div class="big_list_title pb8">[정상] 빅데이터 WEB 시스템</div>
					<div class="big_list_contents mt8">
						<ul>
							<li>CPU : <span>22.4%</span></li>
							<li>메모리 : <span>24.3%</span></li>
							<li>디스크 : <span>80%</span></li>
						</ul>
					</div>
				</div>
            </div>
        </div>
    </div>
    <div class="dashboard2_contents flex-center tab_area">
        <div class="dashboard2_sub_contents wdat tab1">
            <div class="sub_data_title">총 서버 대수 : 11대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab1" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab2">
            <div class="sub_data_title">총 서버 대수 : 7대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab2" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab3">
            <div class="sub_data_title">총 서버 대수 : 5대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab3" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab4">
            <div class="sub_data_title">총 서버 대수 : 12대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab4" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab5">
            <div class="sub_data_title">총 서버 대수 : 13대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab5" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab6">
            <div class="sub_data_title">총 서버 대수 : 1대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab6" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
        <div class="dashboard2_sub_contents wdat tab-none tab7">
            <div class="sub_data_title">총 서버 대수 : 8대</div>
            <div class="bigdata_chart mt16 mb16">
                <canvas id="big_data_tab7" class="pd16"></canvas>
            </div>
            <div class="big_detail">
                <div class="big_detail_title">서버 상세정보</div>
                <div class="mt10">재시작일자 : 2023년 60월 20일 14시 40분</div>
                <div>버전 : 1.4</div>
                <div>QS : Windows</div>
                <div>점검일자 : 2023년 60월 20일 14시 40분</div>
            </div>
        </div>
    </div>
</div>

<script>
	//tab
	$(".tab_fc div").each(function() {
	    $(this).click(function(){
	        for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
	            if($(this).attr("data-index") == i){
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).show();
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).siblings().hide();
	            }
	        }
	    });
	});
	//chart
	new GITSChart(GITSChartType.LINE).init("big_data_tab1")
	.setDataSetLabel('20~21시','21~22시','22~23시','23~24시')
	.setDataSet({
	    label:'CPU',
	    data: [0, 5, 3, 1],
	    backgroundColor:'#FF6B6B',
	    borderWidth:3,
	    borderColor:'#FF6B6B',
	    fill: false
	}, {
	    label:'메모리',
	    data: [5, 1, 6, 2],
	    backgroundColor:'#FF9900',
	    borderWidth:3,
	    borderColor:'#FF9900',
	    fill: false
	}, {
	    label:'디스크',
	    data: [3, 4, 6, 0],
	    backgroundColor:'#2E8EFF',
	    borderWidth:3,
	    borderColor:'#2E8EFF',
	    fill: false
	})
	
	.setTicksStep(2)
	.draw();
</script>
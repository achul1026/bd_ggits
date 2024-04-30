<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="blind">서비스 운영현황</h2>

<div class="tab_box_body_wrap">
	<div class="dashboard_container2">
	    <div class="dashboard2_contents">
	        <div class="dashboard2_sub_contents service_height">
	            	<div class="">
	            		- 날짜 : <span><c:out value='${mapTotalServiceInfoDTO.today}'/></span>
	            	</div>
	                <div class="">
	                   - 총 접속 횟수 : <span><c:out value='${mapTotalServiceInfoDTO.totalLoginCnt}'/>건</span>
	                </div>
	                <div class="">
	                    - 최근 갱신 : <span><c:out value='${mapTotalServiceInfoDTO.renuwalTime}'/></span>
	                </div>
	                <div class="">
	                    - 전체 관리자 : <span><c:out value='${mapTotalServiceInfoDTO.totalAdminCnt}'/>명</span>
	                </div>
	                <div class="service_chart mt16" style="height:240px">
	                    <canvas id="section9_sidebox_chart"></canvas>
	                </div>
	        </div>
	
	    </div>
	</div>
</div>

<script>
	// tab 유스케이스
	$(".tab_fc ul li button").each(function() {
	    $(this).click(function(){
	        for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
	            if($(this).attr("data-index") == i){
	            	$(this).closest(".tab_fc").hide();
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).show();
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).siblings().hide();
	            }
	        }
	    });
	});
	
	//유스케이스 항목 접속 현황 > 이전
	$('.prev_text').on('click', function(){
		$(this).closest('.tab_area').siblings('.tab_fc').show();
		$(this).closest('.tab').hide();
	})
	
	  	
	//서비스 운영현황 -> 전체 서비스 운영현황
  	var monthArrStr = '<c:out value="${mapTotalServiceInfoDTO.monthArr}"/>';
   	var serviceDataArr = new Array();
  	<c:forEach var="chartDataList" items="${mapTotalServiceInfoDTO.chartDataList}">
		var serviceDataObj = new Object();
		var chartData = '<c:out value="${chartDataList.chartData}"/>';
		var labelNm = '<c:out value="${chartDataList.cdNm}"/>';
		var color = '<c:out value="${chartDataList.color}"/>';
		
		serviceDataObj.label = labelNm;
		serviceDataObj.data = chartData.split(',');
		serviceDataObj.backgroundColor = color;
		serviceDataArr.push(serviceDataObj);
	</c:forEach>
	new GITSChart(GITSChartType.BAR).init("section9_sidebox_chart")
	.setDataSetArrayLabel(monthArrStr.split(','))
	.setDataArraySet(serviceDataArr)
	.setTicksStep(20)
	.setLabelDisplay(true)
	.setBarGridY(true)
	.setLabelPadding(10)
	.draw();
	
</script>
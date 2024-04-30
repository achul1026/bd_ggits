<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="blind">서비스 운영현황</h2>
<div class="tab_box_body_wrap">
	<div class="dashboard_container2">
	    <div class="dashboard2_contents">
	        <div class="dashboard2_sub_contents service_height">
	            <div>
	                <div class="">- 날짜 : <span><c:out value='${today}'/></span></div>
	            </div>
	            <div class="service_chart mt16" style="height:240px">
	                <canvas id="section9_sidebox2_chart"></canvas>
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
	
	

	//서비스 운영현황 ->   시군 이용 대상별 접속 현황
   	var dataArr = new Array();

  	<c:forEach var="userLoginStats" items="${userLoginStats}" varStatus="status">
		var dataObj = new Object();
		var chartData = '<c:out value="${userLoginStats.chartData}"/>';
		var labelNm = '<c:out value="${userLoginStats.cdNm}"/>';
		var color = '<c:out value="${userLoginStats.color}"/>';

		dataObj.label = labelNm;
		dataObj.data = chartData.split(',');
		dataObj.backgroundColor = color;
		dataObj.borderWidth = 3;
		dataObj.borderColor = color;
		dataObj.fill = false;
 		dataArr.push(dataObj);
  	</c:forEach>
//   	var objSet = Array.from(dataArr);
	new GITSChart(GITSChartType.LINE).init("section9_sidebox2_chart")
	.setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
	.setDataArraySet(dataArr)
	.setLabelPadding(10)
	.draw();
</script>
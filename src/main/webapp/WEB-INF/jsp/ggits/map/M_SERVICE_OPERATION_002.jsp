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
                <div class="">
                	- 사용 :
                	<span class="inline-flex">
                		유스케이스를 클릭하여 상세 현황을 보실 수 있습니다.<br>
                	</span>
                  
                </div>
            </div>
            <div class="sub_data_list tab_set">
            	<div class="tab_fc w417">
	                <ul>
	                	<c:forEach var="bigDataMenuList" items="${mapUseCaseInfoDTO}" varStatus="status">
		                    <li><button type="button" class="sub_data_btn" data-index="<c:out value='${status.count}'/>"><c:out value='${bigDataMenuList.menuNm}'/></button></li>
	                	</c:forEach>
	                </ul>
            	</div>
                <div class="tab_area">
	                	<c:forEach var="useCaseInfo" items="${mapUseCaseInfoDTO}" varStatus="status">
		                	<div class="tab tab<c:out value='${status.count}'/> tab-none">
		                		<div>
		                            <div class="mt16 prev_wrap">
		                                <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button>
		                                <div class="prev_title"><c:out value='${useCaseInfo.menuNm}'/></div>
		                            </div>
		                            <div class="service_chart mt8" id="tab<c:out value='${status.count}'/>_div"style="height:205px;position:relative;">
		                                <canvas id="tab<c:out value='${status.count}'/>_chart"></canvas>
		                            </div>
		                        </div>
		                	</div>
						</c:forEach>
                </div>
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
	
    //유스케이스 항목접속현황
  	<c:forEach var="useCaseInfo" items="${mapUseCaseInfoDTO}" varStatus="status">
  		var dataArr = '<c:out value="${useCaseInfo.chartData}"/>';
		var dataChk = '<c:out value="${useCaseInfo.dataChk}"/>'
		if(dataChk === 'true'){
			new GITSChart(GITSChartType.LINE).init("tab${status.count}_chart")
		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
		    .setDataSet({
		        label:'접속자수',
		        data: dataArr.split(','),
		        borderWidth:3,
		        borderColor:'#58edd2',
		        fill: false
		    })
		    /* .setTicksStep(10) */
		    .setLabelPadding(10)
		    .draw();		
		}else{
			$("#tab${status.count}_div").empty().append('<div class="none_use_chart_data">사용 내역이 존재하지 않습니다.</div>')
		}
    </c:forEach>
</script>
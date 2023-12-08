<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="blind">서비스 운영현황</h2>
<div class="dashboard_container2 w1280">
    <div class="dashboard2_contents mb10">
        <div class="dashboard2_sub_contents wd100">
<!--             <div> -->
<!--                 <ul class="flex-center"> -->
<!--                     <li><label class="is-darkgreen-btn"><input type="radio" name="test">일일</label></li> -->
<!--                     <li><label class="is-dark-btn"><input type="radio" name="test">주간</label></li> -->
<!--                     <li><label class="is-dark-btn"><input type="radio" name="test">월간</label></li> -->
<!--                     <li><label class="is-dark-btn"><input type="radio" name="test">연간</label></li> -->
<!--                 </ul> -->
<!--             </div> -->
            <div class="mt16 dashboard2_sub_title">
                <div class="sub_data_title">
                	${mapTotalServiceInfoDTO.today}
                </div>
            </div>
        </div>
    </div>
    <div class="dashboard2_contents flex-center gap8">
        <div class="dashboard2_sub_contents service_height">
            <div class="sub_data_title">전체 서비스 운영현황</div>
                <div class="mt16 flex-between">
                    <div>총 접속 횟수</div>
                    <div>${mapTotalServiceInfoDTO.totalLoginCnt}건</div>
                </div>
                <div class="mt4 flex-between">
                    <div>최근 갱신</div>
                    <div>${mapTotalServiceInfoDTO.renuwalTime}</div>
                </div>
                <div class="mt4 flex-between">
                    <div>전체 관리자</div>
                    <div>${mapTotalServiceInfoDTO.totalAdminCnt}명</div>
                </div>
                <div class="service_chart mt8" style="height:240px">
                    <canvas id="section9_sidebox_chart"></canvas>
                </div>
        </div>
        <div class="dashboard2_sub_contents service_height">
            <div>
                <div class="sub_data_title">유스케이스 항목 접속 현황</div>
                <div class="sub_data_sub_title mt10">
                    유스케이스를 클릭하여 상세 현황을 보실 수 있습니다.<br>
                    각 유스케이스는 접속 순위별로 자동 정렬됩니다.
                </div>
            </div>
            <div class="sub_data_list tab_set">
            	<div class="tab_fc">
	                <ul>
	                	<c:forEach var="bigDataMenuList" items="${mapUseCaseInfoDTO}" varStatus="status">
		                    <li><button type="button" class="sub_data_btn" data-index="${status.count}">${bigDataMenuList.menuNm}</button></li>
	                	</c:forEach>
	                </ul>
            	</div>
                <div class="tab_area">
	                	<c:forEach var="useCaseInfo" items="${mapUseCaseInfoDTO}" varStatus="status">
		                	<div class="tab tab${status.count} tab-none">
		                		<div>
		                            <div class="mt16 prev_wrap pb8">
		                                <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button>
		                                <div class="prev_title">${useCaseInfo.menuNm}</div>
		                            </div>
		                            <div class="service_chart mt8" style="height:205px">
		                                <canvas id="tab${status.count}_chart"></canvas>
		                            </div>
		                        </div>
		                	</div>
						</c:forEach>
<!--                 	<div class="tab tab2 tab-none"> -->
<!--                 		<div> -->
<!--                             <div class="mt16 prev_wrap pb8"> -->
<!--                                 <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button> -->
<!--                                 <div class="prev_title">유스케이스02</div> -->
<!--                             </div> -->
<!--                             <div class="mt8 flex-between"> -->
<!--                                 <div>총 갱신 횟수</div> -->
<!--                                 <div>60건</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>최근 갱신 일시</div> -->
<!--                                 <div>16시 43분 25초</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>갱신 주기</div> -->
<!--                                 <div>60분</div> -->
<!--                             </div> -->
<!--                             <div class="service_chart mt8" style="height:205px"> -->
<%--                                 <canvas id="tab2_chart"></canvas> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                 	</div> -->
<!--                 	<div class="tab tab3 tab-none"> -->
<!--                 		<div> -->
<!--                             <div class="mt16 prev_wrap pb8"> -->
<!--                                 <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button> -->
<!--                                 <div class="prev_title">유스케이스03</div> -->
<!--                             </div> -->
<!--                             <div class="mt8 flex-between"> -->
<!--                                 <div>총 갱신 횟수</div> -->
<!--                                 <div>60건</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>최근 갱신 일시</div> -->
<!--                                 <div>16시 43분 25초</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>갱신 주기</div> -->
<!--                                 <div>60분</div> -->
<!--                             </div> -->
<!--                             <div class="service_chart mt8" style="height:205px"> -->
<%--                                 <canvas id="tab3_chart"></canvas> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                 	</div> -->
<!--                 	<div class="tab tab4 tab-none"> -->
<!--                 		<div> -->
<!--                             <div class="mt16 prev_wrap pb8"> -->
<!--                                 <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button> -->
<!--                                 <div class="prev_title">유스케이스04</div> -->
<!--                             </div> -->
<!--                             <div class="mt8 flex-between"> -->
<!--                                 <div>총 갱신 횟수</div> -->
<!--                                 <div>60건</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>최근 갱신 일시</div> -->
<!--                                 <div>16시 43분 25초</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>갱신 주기</div> -->
<!--                                 <div>60분</div> -->
<!--                             </div> -->
<!--                             <div class="service_chart mt8" style="height:205px"> -->
<%--                                 <canvas id="tab4_chart"></canvas> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                 	</div> -->
<!--                 	<div class="tab tab5 tab-none"> -->
<!--                 		<div> -->
<!--                             <div class="mt16 prev_wrap pb8"> -->
<!--                                 <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button> -->
<!--                                 <div class="prev_title">유스케이스05</div> -->
<!--                             </div> -->
<!--                             <div class="mt8 flex-between"> -->
<!--                                 <div>총 갱신 횟수</div> -->
<!--                                 <div>60건</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>최근 갱신 일시</div> -->
<!--                                 <div>16시 43분 25초</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>갱신 주기</div> -->
<!--                                 <div>60분</div> -->
<!--                             </div> -->
<!--                             <div class="service_chart mt8" style="height:205px"> -->
<%--                                 <canvas id="tab5_chart"></canvas> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                 	</div> -->
<!--                 	<div class="tab tab6 tab-none"> -->
<!--                 		<div> -->
<!--                             <div class="mt16 prev_wrap pb8"> -->
<!--                                 <button type="button" class="prev_text mb8"><span class="prev_arrow">←</span> 이전</button> -->
<!--                                 <div class="prev_title">유스케이스06</div> -->
<!--                             </div> -->
<!--                             <div class="mt8 flex-between"> -->
<!--                                 <div>총 갱신 횟수</div> -->
<!--                                 <div>60건</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>최근 갱신 일시</div> -->
<!--                                 <div>16시 43분 25초</div> -->
<!--                             </div> -->
<!--                             <div class="mt4 flex-between"> -->
<!--                                 <div>갱신 주기</div> -->
<!--                                 <div>60분</div> -->
<!--                             </div> -->
<!--                             <div class="service_chart mt8" style="height:205px"> -->
<%--                                 <canvas id="tab6_chart"></canvas> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                 	</div> -->
                </div>
            </div>
        </div>
        <div class="dashboard2_sub_contents service_height">
            <div>
                <div class="sub_data_title">시군 이용 대상별 접속 현황</div>
            </div>
            <div class="service_chart mt8" style="height:240px">
                <canvas id="section9_sidebox2_chart"></canvas>
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
	//<![CDATA[
  	var monthArrStr = '${mapTotalServiceInfoDTO.monthArr}';
	//]]>
   	var serviceDataArr = new Array();
  	<c:forEach var="chartDataList" items="${mapTotalServiceInfoDTO.chartDataList}">
		var serviceDataObj = new Object();
		//<![CDATA[
			var chartData = '${chartDataList.chartData}';
		//]]>
		var labelNm = '${chartDataList.cdNm}';
		var color = '${chartDataList.color}';
		serviceDataObj.label = labelNm;
		serviceDataObj.data = chartData.split(',');
		serviceDataObj.backgroundColor = color;
		serviceDataArr.push(serviceDataObj);
	</c:forEach>
	new GITSChart(GITSChartType.BAR).init("section9_sidebox_chart")
	.setDataSetArrayLabel(monthArrStr.split(','))
	.setDataArraySet(serviceDataArr)
	.setTicksStep(2)
	.setLabelDisplay(true)
	.setBarGridY(true)
	.setLabelPadding(10)
	.draw();
	
    //유스케이스 항목접속현황
  	<c:forEach var="useCaseInfo" items="${mapUseCaseInfoDTO}" varStatus="status">
	//<![CDATA[
  	var dataArr = '${useCaseInfo.chartData}';
	//]]>

  	new GITSChart(GITSChartType.LINE).init("tab${status.count}_chart")
	    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
	    .setDataSet({
	        label:'접속자수',
	        data: dataArr.split(','),
	        backgroundColor:'#58edd2',
	        borderWidth:3,
	        borderColor:'#58edd2',
	        fill: false
	    })
	    .setTicksStep(10)
	    .setLabelPadding(10)
	    .draw();
    </c:forEach>

	//서비스 운영현황 ->   시군 이용 대상별 접속 현황
   	var dataArr = new Array();

  	<c:forEach var="userLoginStats" items="${userLoginStats}" varStatus="status">
		var dataObj = new Object();
		//<![CDATA[
  		var chartData = '${userLoginStats.chartData}';
  		//]]>
		var labelNm = '${userLoginStats.cdNm}';
		var color = '${userLoginStats.color}';
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
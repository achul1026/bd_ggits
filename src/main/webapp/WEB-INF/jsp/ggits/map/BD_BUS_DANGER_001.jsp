<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">대중교통 안전 운행<br>위험 빈도</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">대중교통 안전 운행 위험 빈도</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<div>
	                		<button type="button" class="prev_text mb8 mt8 rollbackBtn" ><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd defaultTrgt"><input type="checkbox" class="none searchTrgt" checked="checked" name="searchResultType" value="city">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none searchTrgt" name="searchResultType" value="road">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd defaultTrgt" data-tab="1"><input type="checkbox" class="none" checked="checked">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="2"><input type="checkbox" class="none">표</label>
	                    </div>
	                    <div class="list_tab_area">
		                    <div class="tab_item_box list_tab1 chartBox1" style="width:400px; height:300px;">
		                        <canvas id="tab1" class="chartCan1"></canvas>
		                    </div>
		                    <div class="tab_item_box list_tab2 none">
		                    	<div class="bigdata_table">
			                        <table id="modalTable">
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:50%">
			                                <col style="width:40%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">지역명</th>
			                                    <th scope="col">빈도 수</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                            	<c:choose>
			                            		<c:when test="${fn:length(pubTrfDagrfrecRankList) >0}">
			                            			<c:forEach var ="dagrfrec" items="${pubTrfDagrfrecRankList}" varStatus="status">
			                            				<tr>
			                            					<td><c:out value='${(index + 1)}'/></td>
			                            					<td><c:out value='${dagrfrec.adsinm}'/></td>
			                            					<td><fmt:formatNumber value="${dagrfrec.dngrcnt}" pattern="#,###" /></td>
			                            				</tr>
			                            			</c:forEach>
			                            		</c:when>
			                            		<c:otherwise>
			                            			<tr>
			                            				<td colspan="3">조회된 정보가 없습니다.</td>
			                            			</tr>
			                            		</c:otherwise>
			                            	</c:choose>
			                            </tbody>
			                         </table>
			                	</div>
			            	</div> 		                    
	                    </div>
	                </div>
	            </div>
        	</div>
        </div>
    </div>
</div>

<script>
	bigdataTabMenu();
	bigdataPopupPrev();
	gisCheckInit();
	bigdataPopupClose();
	
	var cityInfoArr = '<c:out value="${cityInfoArr}"/>';
	var danrFrecArr = '<c:out value="${danrFrecArr}"/>';
	//tab1
    new GITSChart(GITSChartType.BAR).init("tab1")
    .setData({
             labels: cityInfoArr.split(','),
             datasets: [{
            	 label:'위험빈도',
            	 data: danrFrecArr.split(','),
            	 backgroundColor:'#00BCB1'
             }]
         })	
    .setTickStepX(10000)
    .setAxis('y')
    .setBarGridX(true)
    .draw();	
	
	//대중교통 위험빈도 표/그래프 
    $(".list_tab_button label").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-tab"); i++){
                if($(this).attr("data-tab") == i){
                    $(this).closest(".result_item").find(".list_tab_area").children(".list_tab"+i).removeClass('none');
                    $(this).closest(".result_item").find(".list_tab_area").children(".list_tab"+i).siblings().addClass('none');
                }
            }
        });
    });	
	
	
    $(".searchTrgt").on("change", function(){
    	var searchType = $(this).val();
		$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab1" class="chartCan1"></canvas>'
		)
		fnSearchList(searchType);
	})
	
	function fnSearchList(searchType){
		$.ajax({
    		type : "get",
    		data : {
    			"searchResultType" : searchType
    		},
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/danger/BD_BUS_DANGER_001/data.ajax",
    		success : function(result){
    		    //tab1
    		    var cityInfoArr = result.data.cityInfoArr; 
    			var danrFrecArr = result.data.danrFrecArr; 
    			settingChart(cityInfoArr, danrFrecArr);
    			
    			$("#modalTable>tbody>tr").remove();
    			var html = '';
    			$(result.data.pubTrfDagrfrecRankList).each(function(index, item){
    				var dngrCnt = numberComma(item.dngrcnt);
    				html += '<tr>' +
								'<td>' + (index + 1) + '</td>' +
								'<td>' + item.adsinm + '</td>' +
								'<td>' + dngrCnt + '</td>' + 
							'</tr>';
    			});
    			$("#modalTable>tbody").append(html);
    		}
    	})
	}
    
    function settingChart(cityInfoArr, danrFrecArr){
		//tab1
	    var cityInfoArr = cityInfoArr;
	    var danrFrecArr = danrFrecArr;
	    new GITSChart(GITSChartType.BAR).init("tab1")
	    .setData({
	             labels: cityInfoArr.split(','),
	             datasets: [{
	            	 label:'위험빈도',
	            	 data:danrFrecArr.split(','),
	            	 backgroundColor:'#00BCB1'
	             }]
	         })	
	    .setTickStepX(10000)
	    .setAxis('y')
	    .setBarGridX(true)
	    .draw();
	}
    
    $(".rollbackBtn").on("click", function(){
    	$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab1" class="chartCan1"></canvas>'
		)
		$(".defaultTrgt").addClass("is-darkgreen-btn");
		$(".list_tab1").removeClass("none");
		$(".list_tab2").addClass("none");
		
		fnSearchList("city");
    })
</script>
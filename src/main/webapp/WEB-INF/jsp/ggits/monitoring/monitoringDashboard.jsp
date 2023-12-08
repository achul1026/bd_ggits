<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
    <div class="monitorig_side_button">
	    <ul>
	    	<c:forEach var="mOpLayoutMstInfoList" items="${monitoringDashboardDTO.mOpLayoutMstInfoList}" varStatus="status">
	    		<c:choose>
	    			<c:when test="${layoutNo eq '1' && mOpLayoutMstInfoList.layout1UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn${status.index}" onclick="drawLayer('${mOpLayoutMstInfoList.fnctType}','${status.index}')" class="is-side-btn">${mOpLayoutMstInfoList.layoutMenuNm}</button></li>
	    			</c:when>
	    			<c:when test="${layoutNo eq '2' && mOpLayoutMstInfoList.layout2UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn${status.index}" onclick="drawLayer('${mOpLayoutMstInfoList.fnctType}','${status.index}')" class="is-side-btn">${mOpLayoutMstInfoList.layoutMenuNm}</button></li>
	    			</c:when>
	    			<c:when test="${layoutNo eq '3' && mOpLayoutMstInfoList.layout3UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn${status.index}" onclick="drawLayer('${mOpLayoutMstInfoList.fnctType}','${status.index}')" class="is-side-btn">${mOpLayoutMstInfoList.layoutMenuNm}</button></li>
	    			</c:when>
	    		</c:choose>
	    	</c:forEach>
	    </ul> 
    </div>
    <!-- 대시보드 -->
    <div class="monitoring_dashboard_map_conatiner">
        <div class="monitoring_dashboard_map_wrap">
			<c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
	        <div class="my_layout_wrap">
	        	<div class="my_layout_title">
	                나의 레이아웃 설정하기 <i class="close"></i>
	            </div>
	            <div class="flex-center mb32">
	            	<select class="selectBox radius">
	            		<option>5초</option>
	            		<option>test</option>
	            		<option>test</option>
	            		<option>test</option>
	            	</select>
	            	<div>마다 지역 변경하기</div>
	            </div>
                <div class="my_layout_contents">
	                	<c:forEach var="layoutList" items="${monitoringDashboardDTO.layoutList}" varStatus="layoutStatus">
							<div class="my_layout_tab my_layout_tab${layoutStatus.count} ${layoutStatus.count ne layoutNo ? 'none':''}">
		                        <c:forEach var="menuPttrnType" items="${monitoringDashboardDTO.menuPttrnTypeList}">
		                        <div class="my_layout_list_wrap">
		                        		<c:choose>
		                        			<c:when test="${menuPttrnType eq 'MCT000'}">
					                            <div class="my_layout_list_title">경기도 통합 교통 현황</div>
			 										<div class="my_layout_list">
						                                <ul class="my_layout_flexbox">
															<c:forEach var="layoutMenu" items="${layoutList.layoutMenu}" varStatus="menuStatus">
																	<c:if test="${layoutMenu.menuPttrnType ne 'EVC007'}">
																		<c:choose>
																			<c:when test="${layoutStatus.count eq 1}">
											                                    <li class="check_box wh_check">
											                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout1UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout1UseYn eq 'Y' ? 'checked':''}>
											                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout1UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
											                                    </li>
																			</c:when>
																			<c:when test="${layoutStatus.count eq 2}">
											                                    <li class="check_box wh_check">
											                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout2UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout2UseYn eq 'Y' ? 'checked':''}>
											                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout2UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
											                                    </li>
																			</c:when>
																			<c:when test="${layoutStatus.count eq 3}">
											                                    <li class="check_box wh_check">
											                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout3UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout3UseYn eq 'Y' ? 'checked':''}>
											                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout3UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
											                                    </li>
																			</c:when>
																		</c:choose>
																	</c:if>
															</c:forEach>
						                                </ul>
													</div>
		                        			</c:when>
		                        			<c:when test="${menuPttrnType eq 'MCT001'}">
					                            <div class="my_layout_list_title">수집시스템 운영 현황</div>
					                            	<div class="my_layout_list">
						                                <ul>
															<c:forEach var="layoutMenu" items="${layoutList.layoutMenu}" varStatus="menuStatus">
																<c:if test="${layoutMenu.menuPttrnType eq 'EVC007'}">
																	<c:choose>
																		<c:when test="${layoutStatus.count eq 1}">
										                                    <li class="check_box wh_check">
										                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout1UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout1UseYn eq 'Y' ? 'checked':''}>
										                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout1UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
										                                    </li>
																		</c:when>
																		<c:when test="${layoutStatus.count eq 2}">
										                                    <li class="check_box wh_check">
										                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout2UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout2UseYn eq 'Y' ? 'checked':''}>
										                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout2UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
										                                    </li>
																		</c:when>
																		<c:when test="${layoutStatus.count eq 3}">
										                                    <li class="check_box wh_check">
										                                        <input type="checkbox" class="menu${layoutStatus.count} menuCheckbox" data-id="${layoutMenu.layoutId}" data-value="${layoutMenu.layout3UseYn}" id="menu${layoutStatus.count}_${menuStatus.count}" ${layoutMenu.layout3UseYn eq 'Y' ? 'checked':''}>
										                                        <label for="menu${layoutStatus.count}_${menuStatus.count}" class="${layoutMenu.layout3UseYn eq 'Y' ? 'on':''}">${layoutMenu.layoutMenuNm}(${layoutMenu.dataTypeCd eq 'DTC000'? '차트':'레이어'})</label>
										                                    </li>
																		</c:when>
																	</c:choose>
																</c:if>
															</c:forEach>
						                                </ul>
													</div>
		                        			</c:when>
		                        		</c:choose>
									</div>
		                        </c:forEach>
		                    </div>	                    	
	                   	</c:forEach>
                    </div>
	                <div class="my_layout_btn_wrap">
	                    <div class="my_layout_detail_btn">
	                        <button type="button" id="saveBtn" class="" onclick="saveLayoutUseYn('${layoutNo}')">확인</button>
	                    </div>
	            	</div>
                    <div class="my_layout_bookmark_wrap">
                        <button type="button" class="layoutSelectBtn ${layoutNo eq '1' ? 'my_layout_bookmark':''}" data-layout="1">나의 레이아웃1</button>
                        <button type="button" class="layoutSelectBtn ${layoutNo eq '2' ? 'my_layout_bookmark':''}" data-layout="2">나의 레이아웃2</button>
                        <button type="button" class="layoutSelectBtn ${layoutNo eq '3' ? 'my_layout_bookmark':''}" data-layout="3">나의 레이아웃3</button>
                    </div>
            	</div>

			<div class="moitoring_dashboard_contentbox">
                <div class="mini_dash_box mini_height" id="FTC005">

                </div>
               <div class="mini_dash_box mini_height" id="FTC006">

               </div>
           </div>
        </div>            
    </div>
    <div class="monitoring_dashboard_sortable_conatiner sortable">
       <div class="mini_dash_box" id="FTC004">
       
       </div>
       <div class="mini_dash_box" id="FTC007">
       
       </div>
       <div class="mini_dash_box" id="FTC008">
       
       </div>
       <div class="mini_dash_box" id="FTC009">
       
       </div>
       <div class="mini_dash_box" id="FTC010">
       
       </div>
       <div class="mini_dash_box" id="FTC011">
       
       </div>
    </div>
</div> 

<script>

	window.map = new GITSMapCore("map").init("MONITORING_DASHBOARD", null, null, function(){
		$(".monitorig_side_button .side_item .is-side-btn").click();
	});
	
    $(document).ready(function(){
    	$('main').css('margin-top', '-1rem');
    	$('.main_container').addClass('monitoring_main_container')
        $('#map-container').addClass('monitoring_dashboard_mapbox');
        $('.control_container').addClass('monitoring_dashboard_control');
        $('.remarks_container').css('z-index', '9997');
        $('.my_layout_btn').removeClass('none');
        gitsApp.setMap(map);
     });
    
	function drawLayer(fnctType,idx){
    	//버튼 active 기능
    	var layerBtn = $("#sideBtn"+idx);
    	var isActive = layerBtn.hasClass("active");
    	
    	switch(fnctType){
    	case "FTC000":
    	//교통량
    		if(isActive){
	       		//실시간 교통 현황 조회 끄기
    			map.monitoring.removeTrafficInfo();
    		} else {
	        	//실시간 교통 현황 조회 켜기
	       	 map.monitoring.getTrafficInfo(true,10000);
    		}
    		break;
    	case "FTC001":
    	//TODO::교통 신호현황 구현필요
    		if(isActive){
			console.log(fnctType);
    		}
    		break;
    	case "FTC002":
    	//돌발현황
    		if(isActive){
    			//돌발현황 끄기
    			map.monitoring.removeWarningInfo();
    		}else {
    	        //돌발현황 켜기
   	        	map.monitoring.getWarningInfo(false);
    		}
    		break;
    	case "FTC003":
   	     //긴급차량 운영 현황
    		if(isActive){
		   	    //긴급차량 운영 현황 끄기
    			map.monitoring.removeEmergencyMoveInfo();
    		} else {
		   	    //긴급차량 운영 현황 켜기
    			map.monitoring.getEmergencyMoveInfo(false);
    		}
    		break;
   		default :
    		return false;
    	}

    	if(isActive){
    		layerBtn.removeClass("active");
    	} else {
    		layerBtn.addClass("active");
    	}
    }
	
	$(function(){
	    //layout setting tab
	    $(".my_layout_bookmark_wrap button").each(function() {
	        $(this).click(function(){
	            for( var i = 1;  i <= $(this).parent().children().last().attr("data-layout"); i++){
	                if($(this).attr("data-layout") == i){
	                    $(this).closest(".my_layout_wrap").find('.my_layout_contents').children(".my_layout_tab"+i).removeClass('none');
	                    $(this).closest(".my_layout_wrap").find('.my_layout_contents').children(".my_layout_tab"+i).siblings(".my_layout_tab").addClass('none');
	                    $("#saveBtn").attr("onclick","saveLayoutUseYn('"+i+"')");
	                }
	            }
	        });
	    });
	    $('.layoutSelectBtn').on('click', function(){
	    	$(this).parent('.my_layout_bookmark_wrap').find('.layoutSelectBtn').removeClass('my_layout_bookmark');
	    	$(this).addClass('my_layout_bookmark')
	    })
	    
	    monitoringDashBoardInit();
	    
	    timer = setInterval( function () {
	    	monitoringDashBoardInit();
	    }, 300000);	
	});
	
	
	function monitoringDashBoardInit(){
	    var layoutNo = '${layoutNo}';
	    var collTabOption = $(".collBtn.on").data('value');
	    var commTabOption = $(".commBtn.on").data('value');
	    var delayTabOption = $(".delayBtn.on").data('value');
	    if(typeof(commTabOption) === 'undefined'){
			commTabOption = 'cross';
		}
	    if(typeof(collTabOption) === 'undefined'){
	    	collTabOption = 'all';
		}
	    if(typeof(delayTabOption) === 'undefined'){
	    	delayTabOption = 'city';
		}
	    
        $.ajax ({
            type : "post",
            data : {
            	"layoutNo" : layoutNo,
            	"commTabOption" : commTabOption,
            	"collTabOption" : collTabOption,
            	"delayTabOption" : delayTabOption
            },
            url : "${pageContext.request.contextPath}/monitoring/loadChartDataAjax.ajax",
            cache : false,
            dataType : "json",
            success : function(result) {
	            	var mapChartDataDTOList = result.data;
	            	for(var i = 0; i < mapChartDataDTOList.length; i++){
						var html = "";
	            		switch(mapChartDataDTOList[i].fnctType){
	            		case "FTC004":
            			var crossOn = "";
            			var linkOn = "";
            			var tableOption = mapChartDataDTOList[i].tableOption;
            			var tableData = mapChartDataDTOList[i].tableData;
            			if(tableOption == 'cross'){
            				crossOn = "on";
            			} else if(tableOption == 'link'){
            				linkOn = "on";
            			} 
	            			
	            		//교차로 및 구간 소통정보
	            		html += '<div class="intersection_tab">';
			            html += '    <div class="flex-between">';
			            html += '        <div class="mini_dash_box_btn intersection_button">';
			            html += '            <button type="button" class="dash_dark_black_btn commBtn mj0 '+crossOn+'" onclick="changeCommTabData(this)" data-value="cross">교차로</button>';
			            html += '            <button type="button" class="dash_dark_black_btn commBtn mj0 '+linkOn+'" onclick="changeCommTabData(this)" data-value="link">구간</button>';
			            html += '        </div>';
			            html += '    </div>';
			            html += '    <div class="mt16">';
			            html += '        <div class="flex-column">';
			            html += '            <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
			            html += '            <div class="mini_time">('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</div>';
			            html += '        </div>';
			            html += '    </div>';
			            html += '    <div>';
				        html += '        <div>';
					    html += '            <div id="commTableDiv" class="dashboard_scroll">';
			    		//테이블 리턴함수
						html += communicationTableInit(tableOption,tableData);
					    html += '            </div>';
				   		html += '   	</div>';
					    html += '</div>';
			            
				        $("#FTC004").empty().append(html);
            			break;
            		case "FTC005":
            		    //시간대 별 누적 교통량
				        html += '<div class="mini_height">';
				        html += '     <div class="flex-between baseline">';
				        html += '           <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
				        html += '           <div class="mini_dash_box_title">'+mapChartDataDTOList[i].totalCnt+'건</div>';
				        html += '     </div>';
				        html += '     <div class="flex-between">';
				        html += '         	<div class="mini_time">(00:00 ~ 24:00)</div>';
			            if(mapChartDataDTOList[i].compareStts == 'CSC003'){
						    html += '      <div class="mini_result_blue">';
							html += ' 			전일 동시간 동일 -';
							html += ' 		</div>';
				        } else if(mapChartDataDTOList[i].compareStts == 'CSC000'){
				        	html += '      	<div class="mini_result_blue">';
					       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'% ▲';
					        html += ' 		</div>';
				        } else {
				        	html += '      	<div class="mini_result_red">';
					       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'% ▼';
					        html += ' 		</div>';
				        }
				        html += '     </div>';
                        html += '     <div class="mini_chart_wrap">'
				        html += '     		<div class="mini_chart">';
				        html += '            	<canvas id="trf_accumulate_chart"></canvas>';
				        html += '      		</div>';
				        html += '   	    <div class="flex-between time_rank">';
				        html += '    	        <div>[시간대 별 누적 교통량 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
                        html += '             <button type="button" class="mini_table_top" onclick="onTable(this)">TOP5 보기</button>';
				        html += '      		</div>';
				        html += '     </div>';
                        html += '       <div class="monitoring_dashboard_rank none">'
                   	    html += '         <div class="mt16 monitoring_dashboard_rank_wrap">'
                        html += '            <table class="monitor_table">';
                        html += '               <colgroup>';
                        html += '                  <col style="width:16%;">';
                        html += '                  <col style="width:64%;">';
                        html += '                  <col style="width:20%;">';
                        html += '               </colgroup>';
                        html += '               <thead>';
                        html += '                  <tr>';
                        html += '                     <th>순위</th>';                                 
                        html += '                     <th>도로명</th>';                                 
                        html += '                     <th>평균속도</th>';                                 
                        html += '                  </tr>'; 
                        html += '               </thead>';                               
                        html += '               <tbody>';
                        var vlmtableData = mapChartDataDTOList[i].tableData;
                        for(var j = 0; j < vlmtableData.length; j++){
	                        html += '                  <tr>';
	                        html += '                     <td>'+vlmtableData[j].rownum+'</td>';              
	                        html += '                     <td>'+vlmtableData[j].acsRoadNm+'</td>';                              
	                        html += '                     <td>'+vlmtableData[j].vhclTrfVlm+'</td>';                              
	                        html += '                  </tr>'; 
                        }
                        html += '               </tbody>';                               
                        html += '            </table>';
                        html += '         </div>'
                        html += '        <div class="flex-between time_rank">';
                        html += '             <div>[시간대 별 누적 평균속도 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
                        html += '              <button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</div>';
                        html += '        </div>';
                        html += '      </div>'
				        html += '</div>';
		        
				        $("#FTC005").empty().append(html);
				        var vlmChartData = mapChartDataDTOList[i].chartData;
            		    new GITSChart(GITSChartType.BAR).init("trf_accumulate_chart")
            		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
            		    .setDataSet({
            		        	label : '교통량',
            		            data : vlmChartData.split(','),
            		            backgroundColor: '#58EDD2',
            		            borderColor : '#58EDD2',
            		            borderRadius:2,
            		            borderWidth:8,
            		            fill: false
            		    })
            		    .setTicksStep(200)
            		    .setLabelDisplay(false)
            		    .setBarGridY(true)
            		    .draw();
            			break;
            		case "FTC006":
            			//시간대별 평균 통행 속도
		              html += ' <div class="mini_height">';
		              html += '      <div class="flex-between baseline">';
		              html += '          <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
		              html += '          <div class="mini_dash_box_title">'+mapChartDataDTOList[i].totalCnt+'km/h</div>';
		              html += '      </div>';
		              html += '      <div class="flex-between">';
		              html += '      	<div class="mini_time">(00:00 ~ 24:00)</div>';
		              if(mapChartDataDTOList[i].compareStts == 'CSC003'){
				      	 html += '      <div class="mini_result_blue">';
					     html += ' 			전일 동시간 동일 -';
					     html += ' 		</div>';
		              } else if(mapChartDataDTOList[i].compareStts == 'CSC000'){
				      	 html += '      <div class="mini_result_blue">';
					     html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'km/h ▲';
					     html += ' 		</div>';
				      } else {
				      	 html += '      <div class="mini_result_red">';
					     html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'km/h ▼';
					     html += ' 		</div>';
				      }
		              html += '      </div>';
                      html += '    <div class="mini_chart_wrap">';
		              html += '      	<div class="mini_chart">';
		              html += '          	<canvas id="trf_speed_rank_chart"></canvas>';
		              html += '      	</div>';
		              html += '      	<div class="flex-between time_rank">';
		              html += '          	<div>[시간대 별 누적 평균 속도 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
                      html += '          <button type="button" class="mini_table_top" onclick="onTable(this)">TOP5 보기</button>';
		              html += '     	 </div>';
		              html += '   	</div>';
                      html += '   <div class="monitoring_dashboard_rank none">'
                      html += '      <div class="mt16 monitoring_dashboard_rank_wrap">'
                      html += '         <table class="monitor_table">';
                      html += '            <colgroup>';
                      html += '               <col style="width:16%;">';
                      html += '               <col style="width:64%;">';
                      html += '               <col style="width:20%;">';
                      html += '            </colgroup>';
                      html += '            <thead>';
                      html += '               <tr>';
                      html += '                  <th>순위</th>';                                 
                      html += '                  <th>도로명</th>';                                 
                      html += '                  <th>속도</th>';                                 
                      html += '               </tr>'; 
                      html += '            </thead>';                               
                      html += '            <tbody>';                               
                      var speedtableData = mapChartDataDTOList[i].tableData;
                      for(var j = 0; j < speedtableData.length; j++){
	                        html += '                  <tr>';
	                        html += '                     <td>'+speedtableData[j].rownum+'</td>';              
	                        html += '                     <td>'+speedtableData[j].acsRoadNm+'</td>';                              
	                        html += '                     <td>'+speedtableData[j].avgVhclSpeed+'km/h</td>';                              
	                        html += '                  </tr>'; 
                      } 
                      html += '            </tbody>';                               
                      html += '         </table>';
                      html += '      </div>'
                      html += '       <div class="flex-between time_rank">';
                      html += '          <div>[시간대 별 누적 교통량 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
                      html += '           <button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</div>';
                      html += '       </div>';
                      html += '   </div>'				              
		              html += ' </div>';
				      $("#FTC006").empty().append(html);
				        var speedChartData = mapChartDataDTOList[i].chartData;
            		    new GITSChart(GITSChartType.LINE).init("trf_speed_rank_chart")
            		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
            		    .setDataSet({
            		        	label : '평균 속도',
            		            data : speedChartData.split(','),
            		            backgroundColor: '#58EDD2',
            		            borderColor : '#58EDD2',
            		            borderRadius:1,
            		            borderWidth:3,
            		    })
            		    .setTicksStep(200)
            		    .setLabelDisplay(false)
            		    .setBarGridY(true)
            		    .draw();	
            			break;
            		case "FTC007":
            			//시내버스 운행 현황
				        html += '<div>';
				        html += '     <div class="flex-between baseline">';
				        html += '           <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
				        html += '           <div class="mini_dash_box_title">'+mapChartDataDTOList[i].totalCnt+'건</div>';
				        html += '     </div>';
				        html += '     <div class="flex-between">';
				        html += '         	<div class="mini_time">(00:00 ~ 24:00)</div>';
			            if(mapChartDataDTOList[i].compareStts == 'CSC003'){
						    html += '      <div class="mini_result_blue">';
							html += ' 			전일 동시간 동일 -';
							html += ' 		</div>';
				        } else if(mapChartDataDTOList[i].compareStts == 'CSC000'){
				        	html += '      	<div class="mini_result_blue">';
					       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'건 ▲';
					        html += ' 		</div>';
				        } else {
				        	html += '      	<div class="mini_result_red">';
					       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'건 ▼';
					        html += ' 		</div>';
				        }
				        html += '     </div>';
				        html += '     <div class="mini_chart">';
				        html += '            <canvas id="bus_chart"></canvas>';
				        html += '      </div>';
				        html += '</div>';
				        
					    $("#FTC007").empty().append(html);
				        
				        var busChartData = mapChartDataDTOList[i].chartData != null? mapChartDataDTOList[i].chartData:"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
					    new GITSChart(GITSChartType.BAR).init("bus_chart")
            		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
            		    .setDataSet({
            		        	label : '평균 속도',
            		            data : busChartData.split(','),
            		            backgroundColor: '#8F5AFF',
            		            borderColor : '#8F5AFF',
            		            borderRadius:2,
								fill: false
            		    })
					    .setTicksStep(200)
					    .setLabelDisplay(false)
					    .setBarGridY(true)
					    .draw();
            			break;
            		case "FTC008":
            			// 주요 정체 구간
            			var cityOn = "";		//시,군구
            			var crossOn = "";		//교차로
            			var linkOn = "";		//구간
            			var tableOption = mapChartDataDTOList[i].tableOption;
            			var tableData = mapChartDataDTOList[i].tableData;
            			if(tableOption == 'city'){
            				cityOn = "on";
            			} else if(crossOn == 'cross'){
            				crossOn = "on";
            			} else if(tableOption == 'link'){
            				linkOn = "on";
            			} 
            			
	            		html += '<div class="intersection_tab">';
			            html += '    <div class="flex-between">';
			            html += '        <div class="mini_dash_box_btn intersection_button">';
			            html += '            <button type="button" class="dash_dark_black_btn delayBtn mj0 '+cityOn+'" onclick="changeDelayTabData(this)" data-value="city">시군별</button>';
			            html += '            <button type="button" class="dash_dark_black_btn delayBtn mj0 '+crossOn+'" onclick="changeDelayTabData(this)" data-value="cross">교차로</button>';
			            html += '            <button type="button" class="dash_dark_black_btn delayBtn mj0 '+linkOn+'" onclick="changeDelayTabData(this)" data-value="link">구간</button>';
			            html += '        </div>';
			            html += '    </div>';
			            html += '    <div class="mt16">';
			            html += '        <div class="flex-column">';
			            html += '            <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
			            html += '            <div class="mini_time">('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</div>';
			            html += '        </div>';
			            html += '    </div>';
			            html += '    <div>';
				        html += '        <div>';
					    html += '            <div id="delayTabDiv" class="dashboard_scroll">';
						html += delayTabDataInit(tableOption,tableData);
					    html += '            </div>';
				   		html += '   	</div>';
					    html += '</div>';
			            
				        $("#FTC008").empty().append(html);
            			break;
            		case "FTC009":
            			//돌발 현황
            			html += '<div>';
            			html += '	<div class="flex-between baseline">';
                        html += '       <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
                        html += '    	<div class="flex-column">';
           	            html += '     		<div class="mini_dash_box_title right">'+mapChartDataDTOList[i].totalCnt+'</div>';
               	        html += '     		<div class="mini_result">발생 / 종료 ['+mapChartDataDTOList[i].startCnt+'건 / '+mapChartDataDTOList[i].endCnt+'건]</div>';
                        html += '    	</div>';
                        html += '	</div>';
                        html += '	<div class="mini_chart">';
                        html += '   	<div class="flex-between mini_chart_title">';
                        html += '       	<div>[ 돌발 유형 별 발생 현황 ]</div>';
                        html += '    	</div>';
                        html += '    	<div class="mt8" style="height:74px">';
                        html += '        	<canvas id="outbreak_chart"></canvas>';
                        html += '    	</div>';
                        html += '	</div>';
                        html += '   <div class="monitoring_dashboard_table_wrap">';
                        html += '      <table class="monitoring_dashboard_table">';
                        html +=   '         <colgroup>';
                        html +=   '            <col style="width:20%;">';
                        html +=   '            <col style="width:50%;">';
                        html +=   '            <col style="width:15%;">';
                        html +=   '            <col style="width:15%;">';
                        html +=   '         </colgroup>';
                        html +=   '         <thead>';
                        html +=   '            <tr>';
                        html += '               	<th>돌발유형</th>';
                        html += '               	<th>발생장소명</th>';
                        html += '               	<th>시작시간</th>';
                        html += '               	<th>종료시간</th>';
                        html +=   '            </tr>';
                        html +=   '         </thead>';
                        html +=   '         <tbody>';
                        var tableDataArr = mapChartDataDTOList[i].tableData;
                        if(tableDataArr.length > 0){
                        	for(var j = 0; j < tableDataArr.length; j++){
	                              var beginDate = tableDataArr[j].beginDate!=null?tableDataArr[j].beginDate:'-'
	                              var endDate = tableDataArr[j].endDate!=null?tableDataArr[j].endDate:'-'
	                              var inciCate = tableDataArr[j].inciCate;
		                            html +=   '            <tr>';
		                            if(inciCate == '66'){
			                            html += '<td><span class="unex_type mini_construction">공사</span></td>';                              
		                            } else if(inciCate == '77'){
			                            html += '<td><span class="unex_type mini_event ">행사</span></td>';
			                        } else if(inciCate == '88'){
			                            html += '<td><span class="unex_type mini_construction ">통제</span></td>';
			                        } else if(inciCate == '99'){
			                            html += '<td><span class="unex_type mini_accident">사고</span></td>';
			                        }
		                            html += '                 <td>'+tableDataArr[j].roadwayNm+'</td>';                              
		                            html += '                 <td>'+beginDate+'</td>';                              
		                            html += '                 <td>'+endDate+'</td>';                              
		                            html +=   '            </tr>'; 
	                            }
                        } else {
	                        html += '	<tr>';
	                        html += '   	<td colspan="4" class="center">';
							html += '			금일 돌발 현황이 없습니다.';		                        	
							html += '		</td>';		                        	
							html += '	</tr>';
                        }
                            html +=   '         </tbody>';                               
                            html += '      </table>';
                            html += '   </div>';
                   			html += '</div>';
                   		$("#FTC009").empty().append(html);
                   		
                   		var dataArr = new Array();
                   		var dataSetLabel = new Array();
                   		var chartLabel = mapChartDataDTOList[i].chartLabel;
                   		var chartData = mapChartDataDTOList[i].chartData;
                   		if(chartLabel != null && chartLabel.indexOf(',')){
                   			var chartLabelArr = chartLabel.split(',');
                   			var chartDataArr = chartData.split(',');
                   			var color = "#ff4646";
                   		   	for(var j = 0; j < chartLabelArr.length; j++){
                   		   		var dataObj = new Object();
                   		   		dataObj.label = chartLabelArr[j];
	                              if(chartLabelArr[j] == '공사'){
	                            	  color = "#8f5aff";
	                              } else if(chartLabelArr[j] == '행사'){
	                            	  color = "#ff54bb";
		                          } else if(chartLabelArr[j] == '통제'){
	                            	  color = "#ff8a00";
		                          } else if(chartLabelArr[j] == '사고'){
	                            	  color = "#ff4646";
		                          }
                   		   		dataObj.data = new Array(chartDataArr[j]);
                   		   		dataObj.backgroundColor = color;
                   		   		dataObj.borderRadius = 3;
                   		   	    dataSetLabel.push('');
                   		   		dataArr.push(dataObj);
                   		   	}
                   		}
			            	//차트 그리기
			           	new GITSChart(GITSChartType.BAR).init("outbreak_chart")
			           	    .setDataSetArrayLabel(dataSetLabel)
			           	    .setDataArraySet(dataArr)
			           	    .setTicksStep(1)
			           	    .setAxis('y')
			           	    .setBarGridY(false)
			           	    .setBarGridX(false)
			           	    .setDisplayX(false)
			           	    .setDisplayY(false)
			           	    .draw();
            			break;
            		case "FTC010":
            			//데이터 수집 장애 알림
            			var allOn = "";				//전체
            			var locGovmntOn = "";		//지자체
            			var externalOn = "";		//외부기관
            			var signalOn = "";			//신호
            			var bigDataOn = "";			//빅데이터
            			var tableOption = mapChartDataDTOList[i].tableOption;
            			var tableData = mapChartDataDTOList[i].tableData;
            			if(tableOption == 'all'){
            				allOn = "on";
            			} else if(tableOption == 'SMT000'){
            				locGovmntOn = "on";
            			} else if(tableOption == 'SMT001'){
            				externalOn = "on";
            			} else if(tableOption == 'SMT002'){
            				signalOn = "on";
            			} else if(tableOption == 'SMT003'){
            				bigDataOn = "on";
            			}
	            		html += '<div class="collection_tab">';
			            html += '    <div class="flex-between">';
				        html += '    	<div class="slick_left_arrow mr8">';
				        html += '    		<button type="button" class="slick-arrow"><img src="${pageContext.request.contextPath}/statics/images/angle_left.png" alt="왼쪽버튼"></button>';
				        html += '    	</div>';
				        html += '        <div class="mini_dash_box_btn collection_button">';
				        html += '            <button type="button" class="dash_dark_black_btn collBtn '+allOn+'" onclick="changeCollTabData(this)" data-value="all">전체</button>';
				        html += '            <button type="button" class="dash_dark_black_btn collBtn '+locGovmntOn+'" onclick="changeCollTabData(this)" data-value="SMT000">지자체 연계</button>';
				        html += '            <button type="button" class="dash_dark_black_btn collBtn '+externalOn+'" onclick="changeCollTabData(this)" data-value="SMT001">외부기관 연계</button>';
				        html += '            <button type="button" class="dash_dark_black_btn collBtn '+signalOn+'" onclick="changeCollTabData(this)" data-value="SMT002">신호 연계</button>';
				        html += '            <button type="button" class="dash_dark_black_btn collBtn '+bigDataOn+'" onclick="changeCollTabData(this)" data-value="SMT003">빅데이터 플랫폼</button>';
				        html += '        </div>';
				        html += '        <div class="slick_right_arrow ml8">';
				        html += '    		<button type="button" class="slick-arrow"><img src="${pageContext.request.contextPath}/statics/images/angle_right.png" alt="오른쪽버튼"></button>';
				        html += '    	</div>';
			            html += '    </div>';
			            html += '    <div class="mt16">';
			            html += '        <div class="flex-column">';
			            html += '            <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
			            html += '            <div class="mini_time">('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</div>';
			            html += '        </div>';
			            html += '    </div>';
			            html += '    <div>';
				        html += '        <div>';
					    html += '            <div class="dashboard_scroll">';
						html += '				<table class="monitor_table">';
						html += '					<colgroup>';
						html += ' 						<col width="25%">';
						html += ' 						<col width="25%">';
						html += ' 						<col width="25%">';
						html += ' 						<col width="25%">';
						html += '					</colgroup>';
						html += '					<thead>';
						html += '						<tr>';
						html += '							<th class="left pl8">수집원</th>';
						html += '							<th>장애유형</th>';
						html += '							<th>수집데이터</th>';
						html += '							<th>시간</th>';
						html += '						</tr>';
						html += '					</thead>';
						html += '					<tbody id="collTBody">';
			            var collTableData = mapChartDataDTOList[i].tableData;
			            var colltableOption = mapChartDataDTOList[i].tableOption;
			    		//테이블 리턴함수
						html += collectionTableInit(colltableOption,collTableData);
						html += '					</tbody>';
						html += '				</table>';        
					    html += '            </div>';
				   		html += '   	</div>';
					    html += '</div>';
					    
                   		$("#FTC010").empty().append(html);
                   		
        	            //slick
        	            $(".collection_button").slick({
        	                slidesToShow:3, // 슬라이드개수
        	                speed:800, // 슬라이드 움직이는 속도
        	                prevArrow: $('.slick_left_arrow'),
        	                nextArrow: $('.slick_right_arrow')
        	            });
        	            
            			break;
            		case "FTC011":
            			//긴급 차량 이동 현황
            			html += '<div>';
            			html += '	<div class="flex-between baseline">';
                        html += '       <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
                        html += '    	<div class="flex-column">';
           	            html += '     		<div class="mini_dash_box_title right">'+mapChartDataDTOList[i].totalCnt+'대</div>';
               	        html += '     		<div class="mini_result">이동 중 / 이동완료 ['+mapChartDataDTOList[i].startCnt+'대 / '+mapChartDataDTOList[i].endCnt+'대]</div>';
                        html += '    	</div>';
                        html += '	</div>';
                        html += '	<div class="mini_chart">';
                        html += '   	<div class="flex-between mini_chart_title">';
                        html += '       	<div>[ 긴급차량 골든타임 달성 현황 ]</div>';
                        html += '    	</div>';
                        html += '    	<div class="mt8" style="height:74px">';
                        html += '        	<canvas id="emergency_chart"></canvas>';
                        html += '    	</div>';
                        html += '	</div>';
                        html += '   <div class="monitoring_dashboard_table_wrap">';
                        html += '      <table class="monitoring_dashboard_table">';
                        html += '         <colgroup>';
                        html += '            <col style="width:20%;">';
                        html += '            <col style="width:50%;">';
                        html += '            <col style="width:15%;">';
                        html += '            <col style="width:15%;">';
                        html += '         </colgroup>';
                        html += '         <thead>';
                        html += '            <tr>';
                        html += '               <th>돌발유형</th>';
                        html += '               <th>발생장소명</th>';
                        html += '               <th>시작시간</th>';
                        html += '               <th>종료시간</th>';
                        html += '            </tr>';
                        html += '         </thead>';
                        html += '         <tbody id="collTabBody">';
                        var emgTableDataArr = mapChartDataDTOList[i].tableData;
                        if(emgTableDataArr != null){
//	                              for(var j = 0; j < emgTableDataArr.length; j++){
//	                            	  var beginDate = emgTableDataArr[j].beginDate!=null?emgTableDataArr[j].beginDate:'-'
//	                            	  var endDate = emgTableDataArr[j].endDate!=null?emgTableDataArr[j].endDate:'-'
//	                            	  var inciCate = emgTableDataArr[j].inciCate;
//		                              html +=   '            <tr>';
//		                              if(inciCate == '66'){
//			                              html += '<td><span class="unex_type mini_construction">공사</span></td>';                              
//		                              } else if(inciCate == '77'){
//			                              html += '<td><span class="unex_type mini_event ">행사</span></td>';
//			                          } else if(inciCate == '88'){
//			                              html += '<td><span class="unex_type mini_construction ">통제</span></td>';
//			                          } else if(inciCate == '99'){
//			                              html += '<td><span class="unex_type mini_accident">사고</span></td>';
//			                          }
//		                              html += '                 <td>'+emgTableDataArr[j].roadwayNm+'</td>';                              
//		                              html += '                 <td>'+beginDate+'</td>';                              
//		                              html += '                 <td>'+endDate+'</td>';                              
//		                              html +=   '            </tr>'; 
//	                              }
                        } else {
                        html += '	<tr>';
                        html += '   	<td colspan="4" class="center">';
						html += '			금일 긴급 차량 이동현황이 없습니다.';		                        	
						html += '		</td>';		                        	
						html += '	</tr>';
                        }
                        html +=   '         </tbody>';                               
                        html += '      </table>';
                        html += '   </div>';
                   		html += '</div>';
                   		
                   		$("#FTC011").empty().append(html);
                   	
//	                   		var dataArr = new Array();
//	                   		var dataSetLabel = new Array();
//	                   		var chartLabel = mapChartDataDTOList[i].chartLabel;
//	                   		var chartData = mapChartDataDTOList[i].chartData;
//	                   		if(chartLabel != null && chartLabel.indexOf(',')){
//	                   			var chartLabelArr = chartLabel.split(',');
//	                   			var chartDataArr = chartData.split(',');
//	                   			var color = "#ff4646";
//	                   		   	for(var j = 0; j < chartLabelArr.length; j++){
//	                   		   		var dataObj = new Object();
//	                   		   		dataObj.label = chartLabelArr[j];
//		                              if(chartLabelArr[j] == '이동중'){
//		                            	  color = "#F90";
//		                              } else if(chartLabelArr[j] == '이동완료(달성)'){
//		                            	  color = "#FED501";
//			                          } else if(chartLabelArr[j] == '이동완료(미달성)'){
//		                            	  color = "#8F5AFF";
//			                          }
//	                   		   		dataObj.data = new Array(chartDataArr[j]);
//	                   		   		dataObj.backgroundColor = color;
//	                   		   		dataObj.borderRadius = 3;
//	                   		   	    dataSetLabel.push('');
//	                   		   		dataArr.push(dataObj);
//	                   		   	}
//	                   		}
                   		
			            	//차트 그리기
			           	new GITSChart(GITSChartType.BAR).init("emergency_chart")
					    .setDataSetLabel('', '', '')
					    .setDataSet({
					        label:'이동중',
					        data:[7],
					        backgroundColor:'#F90',
					        borderRadius:3,
					    },{
					        label:'이동완료(달성)',
					        data:[3],
					        backgroundColor:'#FED501',
					        borderRadius:3,
					    },{
					        label:'이동완료(미달성)',
					        data:[4],
					        backgroundColor:'#8F5AFF',
					        borderRadius:3,
					    })
//				           	    .setDataSetArrayLabel('','','','')
//				           	    .setDataArraySet(['1','1','1','1'])
			           	    .setTicksStep(1)
			           	    .setAxis('y')
			           	    .setBarGridY(false)
			           	    .setBarGridX(false)
			           	    .setDisplayX(false)
			           	    .setDisplayY(false)
			           	    .draw();
            			break;
            		default :
            			break;
            		}
            	}
            }
        });
	}
	
   	  
	//레이아웃 useYn저장
	function saveLayoutUseYn(layoutNo){
		if(layoutNo == null){
			layoutNo = 1;
		}
		var menu = $(".menu"+layoutNo);
		var layoutDto = new Object();
		var layoutArr = new Array();
		for(var i = 0; i < menu.length; i++){
			var obj = new Object();
			var layoutId = menu.eq(i).data('id');
			var useYn = menu.eq(i).is(":checked")?'Y':'N';
			
			obj.layoutId = layoutId;
			obj.useYn = useYn;
			layoutArr.push(obj);
		}
		layoutDto.layoutNo = layoutNo;
		layoutDto.layoutList = layoutArr;
		
        $.ajax({
            type : "post",
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			url : "${pageContext.request.contextPath}/layout/update.ajax",
            data :JSON.stringify(layoutDto),       
            success : function(data){
				if(data.code == 200){
					new ModalBuilder().init().successBody(data.message).footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
					}).open();
					modalAlertWrap();
				} else {
					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
            }
        });
	}
	
    // 나의 레이아웃 설정하기 오픈
    $('.my_layout_btn').click(function(){
    	//<![CDATA[
      	var layoutNo = '${layoutNo}';
    	//]]>
        var layoutTab = $(".my_layout_tab");
        var layoutSelectBtn = $(".layoutSelectBtn");
        	layoutSelectBtn.removeClass("my_layout_bookmark");
    		layoutTab.addClass("none");
        for(var i = 1; i <= layoutTab.length; i++){
        	if(layoutNo == i){
        		$(".my_layout_tab"+i).removeClass("none");
        		layoutSelectBtn.eq(i-1).addClass("my_layout_bookmark");
        		$("#saveBtn").attr("onclick","saveLayoutUseYn('"+i+"')");
        	}
        }
        $('.my_layout_wrap').show();
    })
    
    // 나의 레이아웃 설정하기 체크
    $('.wh_check label').click(function(){
        $(this).toggleClass('on');
    })
    
    // 닫기
    $('.close').click(function(){
        var checkbox = $(".menuCheckbox");
        for(var i = 0; i < checkbox.length; i++){
        	var checkedVal = checkbox.eq(i).data('value');
        	if(checkedVal == 'Y'){
        		checkbox.eq(i).prop("checked", true);
        		checkbox.eq(i).parent
        		('.check_box').find('label').addClass("on");
        	} else {
        		checkbox.eq(i).prop("checked",false);
        		checkbox.eq(i).parent('.check_box').find('label').removeClass("on");
        	}
        }
        $('.my_layout_wrap').hide();
    })	
    
    //button active
    $('.dash_dark_black_btn').on('click', function(){
    	$(this).closest('.mini_dash_box_btn').find('.dash_dark_black_btn').removeClass('on');
    	$(this).addClass('on');
    })
    
	//교차로 및 구간 소통 정보 TAB
    $(".intersection_button button").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-intersection"); i++){
                if($(this).attr("data-intersection") == i){
                    $(this).closest(".intersection_tab").find('.intersection_tab_area').children(".intersectionTab"+i).removeClass('none');
                    $(this).closest(".intersection_tab").find('.intersection_tab_area').children(".intersectionTab"+i).siblings().addClass('none');
                }
            }
        });
    });     
	//주요 정체 구간 TAB
    $(".congested_section_button button").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-congested"); i++){
                if($(this).attr("data-congested") == i){
                    $(this).closest(".congested_tab").find('.congested_tab_area').children(".congestedTab"+i).removeClass('none');
                    $(this).closest(".congested_tab").find('.congested_tab_area').children(".congestedTab"+i).siblings().addClass('none');
                }
            }
        });
    });
	
    //chart/table on off
    function onTable($this){
       $($this).closest('.mini_chart_wrap').addClass('none');
       $($this).closest('.mini_chart_wrap').siblings('.monitoring_dashboard_rank').removeClass('none');
    }
    
    function onChart($this){
       $($this).closest('.monitoring_dashboard_rank').addClass('none');
       $($this).closest('.monitoring_dashboard_rank').siblings('.mini_chart_wrap').removeClass('none');
    }
    
    function changeCommTabData(_this){
    	var $this = $(_this);
		var tabType = $this.data('value');

		$(".commBtn").removeClass("on");
		$this.addClass("on");
		
		var commTableData = getTableDataAjax(tabType , "communication");
		
		var tableHtml = communicationTableInit(tabType,commTableData);
		
		$("#commTableDiv").empty().append(tableHtml);
    };
    
    function communicationTableInit(tabType , commTableData){
    	var html = "";
    	if(tabType == 'cross'){
	    	html += '				<table class="monitor_table">';
			html += '					<colgroup>';
			html += '						<col width="16%">';
			html += '						<col width="60%">';
			html += '						<col width="24%">';
			html += '					</colgroup>';
			html += '					<thead>';
			html += '						<tr>';
			html += '							<th>순위</th>';
			html += '							<th>교차로</th>';
			html += '							<th>교통량</th>';
			html += '						</tr>';
			html += '					</thead>';
			html += '					<tbody>';
			if(commTableData.length > 0){
				for(var i = 0; i < commTableData.length; i ++){
		            html += '					    <tr>';
					html += '					        <td>'+(i+1)+'</td>';
					html += '					        <td>'+commTableData[i].crsRdNm+'</td>';
					html += '					        <td>'+commTableData[i].vhclTrfVlm.toLocaleString("ko-KR")+'</td>';
					html += '					    </tr>';
				}
			} else {
                html += '	<tr>';
                html += '   	<td colspan="4" class="center">';
				html += '			교차로 소통정보가 없습니다.';		                        	
				html += '		</td>';		                        	
				html += '	</tr>';
			}
			html += '					</tbody>';
			html += '				</table>';
    	} else if(tabType == 'link'){
	    	html += '				<table class="monitor_table">';
			html += '					<colgroup>';
			html += '						<col width="16%">';
			html += '						<col width="60%">';
			html += '						<col width="24%">';
			html += '					</colgroup>';
			html += '					<thead>';
			html += '						<tr>';
			html += '							<th>순위</th>';
			html += '							<th>도로명</th>';
			html += '							<th>교통량</th>';
			html += '						</tr>';
			html += '					</thead>';
			html += '					<tbody>';
			if(commTableData.length > 0){
				for(var i = 0; i < commTableData.length; i ++){
		            html += '					    <tr>';
					html += '					        <td>'+(i+1)+'</td>';
					html += '					        <td>'+commTableData[i].roadName+'</td>';
					html += '					        <td>'+commTableData[i].trfvlm.toLocaleString("ko-KR")+'</td>';
					html += '					    </tr>';
				}
			} else {
                html += '	<tr>';
                html += '   	<td colspan="4" class="center">';
				html += '			도로 소통정보가 없습니다.';		                        	
				html += '		</td>';		                        	
				html += '	</tr>';
			}
			html += '					</tbody>';
			html += '				</table>';
    	}
		return html;
    }
    
    function changeDelayTabData(_this){
    	var $this = $(_this);
		var tabType = $this.data('value');

		$(".delayBtn").removeClass("on");
		$this.addClass("on");

		var delayTabData = getTableDataAjax(tabType , "delay");
		
		var tableHtml = delayTabDataInit(tabType,delayTabData);
		
		$("#delayTabDiv").empty().append(tableHtml);
    };
    
    function delayTabDataInit(tabType , delayTabData){
    	var html = "";
    	if(tabType == 'city'){
			html += '				<table class="monitor_table">';
			html += '					<colgroup>';
			html += ' 						<col width="16%">';
			html += ' 						<col width="24%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += '					</colgroup>';
			html += '					<thead>';
			html += '						<tr>';
			html += '							<th>순위</th>';
			html += '							<th>시군구</th>';
			html += '							<th>혼잡도</th>';
			html += '							<th>교통량</th>';
			html += '							<th>통행속도</th>';
			html += '						</tr>';
			html += '					</thead>';
			html += '					<tbody>';
			if(delayTabData.length > 0){
				for(var j = 0; j < delayTabData.length; j ++){
					var delayStatus = delayTabData[j].delayStts;
					var delay = "";
					if(delayStatus == 1){
						delay = "원할";
					} else if(delayStatus == 2){
						delay = "지체(서행)";
					} else {
						delay = "정체";
					}
		            html += '					    <tr>';
					html += '					        <td>'+(j+1)+'</td>';
					html += '					        <td>'+delayTabData[j].crsrdNm+'</td>';
					html += '					        <td>'+delay+'</td>';
					html += '					        <td>'+delayTabData[j].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
					html += '					        <td>'+delayTabData[j].avgVhclSpeed.toLocaleString("ko-KR")+'km/h</td>';
					html += '					    </tr>';
				}
			} else {
                html += '	<tr>';
                html += '   	<td colspan="5" class="center">';
				html += '			정체 구간 정보가 없습니다.';		                        	
				html += '		</td>';		                        	
				html += '	</tr>';
			}
			html += '					</tbody>';
			html += '				</table>';  
    	} else if(tabType == 'cross'){
			html += '				<table class="monitor_table">';
			html += '					<colgroup>';
			html += ' 						<col width="16%">';
			html += ' 						<col width="24%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += '					</colgroup>';
			html += '					<thead>';
			html += '						<tr>';
			html += '							<th>순위</th>';
			html += '							<th>교차로</th>';
			html += '							<th>혼잡도</th>';
			html += '							<th>교통량</th>';
			html += '							<th>통행속도</th>';
			html += '						</tr>';
			html += '					</thead>';
			html += '					<tbody>';
			if(delayTabData.length > 0){
				for(var j = 0; j < delayTabData.length; j ++){
					var delayStatus = delayTabData[j].delayStts;
					var delay = "";
					if(delayStatus == 1){
						delay = "원할";
					} else if(delayStatus == 2){
						delay = "지체(서행)";
					} else {
						delay = "정체";
					}
		            html += '					    <tr>';
					html += '					        <td>'+(j+1)+'</td>';
					html += '					        <td>'+delayTabData[j].crsrdNm+'</td>';
					html += '					        <td>'+delay+'</td>';
					html += '					        <td>'+delayTabData[j].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
					html += '					        <td>'+delayTabData[j].avgVhclSpeed.toLocaleString("ko-KR")+'</td>';
					html += '					    </tr>';
				}
			} else {
                html += '	<tr>';
                html += '   	<td colspan="5" class="center">';
				html += '			정체 구간 정보가 없습니다.';		                        	
				html += '		</td>';		                        	
				html += '	</tr>';
			}
			html += '					</tbody>';
			html += '				</table>';  
    	} else if(tabType == 'link'){
			html += '				<table class="monitor_table">';
			html += '					<colgroup>';
			html += ' 						<col width="16%">';
			html += ' 						<col width="24%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += ' 						<col width="20%">';
			html += '					</colgroup>';
			html += '					<thead>';
			html += '						<tr>';
			html += '							<th>순위</th>';
			html += '							<th>구간</th>';
			html += '							<th>혼잡도</th>';
			html += '							<th>교통량</th>';
			html += '							<th>통행속도</th>';
			html += '						</tr>';
			html += '					</thead>';
			html += '					<tbody>';
			if(delayTabData.length > 0){
				for(var j = 0; j < delayTabData.length; j ++){
					var delayStatus = delayTabData[j].delayStts;
					var delay = "";
					if(delayStatus == 1){
						delay = "원할";
					} else if(delayStatus == 2){
						delay = "지체(서행)";
					} else {
						delay = "정체";
					}
		            html += '					    <tr>';
					html += '					        <td>'+(j+1)+'</td>';
					html += '					        <td>'+delayTabData[j].roadName+'</td>';
					html += '					        <td>'+delay+'</td>';
					html += '					        <td>'+delayTabData[j].trfvlm.toLocaleString("ko-KR")+'</td>';
					html += '					        <td>'+delayTabData[j].speed.toLocaleString("ko-KR")+'km/h</td>';
					html += '					    </tr>';
				}
			} else {
                html += '	<tr>';
                html += '   	<td colspan="5" class="center">';
				html += '			정체 구간 정보가 없습니다.';		                        	
				html += '		</td>';		                        	
				html += '	</tr>';
			}
			html += '					</tbody>';
			html += '				</table>';        
    	} 
		return html;
    }
    
    function changeCollTabData(_this){
    	var $this = $(_this);
		var tabType = $this.data('value');

		$(".collBtn").removeClass("on");
		$this.addClass("on");
		var collTableData = getTableDataAjax(tabType , "collection");
		
		var tableHtml = collectionTableInit(tabType,collTableData);
		
		$("#collTBody").empty().append(tableHtml);
    };
    
    function collectionTableInit(colltableOption,collTableData){
    	var html = "";
    	if(collTableData.length > 0){
			for(var j = 0; j < collTableData.length; j ++){
		            html += '					    <tr>';
					html += '					        <td>'+collTableData[j].cdNm+'</td>';
					html += '					        <td>'+collTableData[j].prgrsStts+'</td>';
					html += '					        <td>'+collTableData[j].etlClsf+'</td>';
					html += '					        <td>'+collTableData[j].clctStartDt+'</td>';
					html += '					    </tr>';
			}
        } else {
            html += '	<tr>';
            html += '   	<td colspan="4" class="center">';
			html += '			데이터 수집 장애 이력이 없습니다.';		                        	
			html += '		</td>';		                        	
			html += '	</tr>';
        }
    	return html;
    }
    
    function getTableDataAjax(tabType, tableNm){
    	var tableData;
		 $.ajax ({
	            type : "post",
	            data : {
	            	"tableNm" : tableNm,
	            	"tabType" : tabType
	            },
	            url : "${pageContext.request.contextPath}/monitoring/getTableData.ajax",
	            async : false,
	            dataType : "json",
	            success : function(result) {
	            	tableData = result.data;
	            }
		 });
		 return tableData;
    }
</script>
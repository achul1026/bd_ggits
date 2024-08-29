<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
    <div class="monitorig_side_button">
    	<div>
    		<select id="sggCd"class="selectBox dashboard_select">
                <option value="41110">경기도 전체</option>
				<c:forEach var="sggCdList" items="${sggCdList}">
                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
				</c:forEach>
    		</select>
    	</div>
	    <ul>
	    	<c:forEach var="mOpLayoutMstInfoList" items="${monitoringDashboardDTO.mOpLayoutMstInfoList}" varStatus="status">
	    		<c:choose>
	    			<c:when test="${layoutNo eq '1' && mOpLayoutMstInfoList.layout1UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn<c:out value='${status.index}'/>" onclick="drawLayer('<c:out value='${mOpLayoutMstInfoList.fnctType}'/>','<c:out value='${status.index}'/>')" class="is-side-btn"><c:out value='${mOpLayoutMstInfoList.layoutMenuNm}'/></button></li>
	    			</c:when>
	    			<c:when test="${layoutNo eq '2' && mOpLayoutMstInfoList.layout2UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn<c:out value='${status.index}'/>" onclick="drawLayer('<c:out value='${mOpLayoutMstInfoList.fnctType}'/>','<c:out value='${status.index}'/>')" class="is-side-btn"><c:out value='${mOpLayoutMstInfoList.layoutMenuNm}'/></button></li>
	    			</c:when>
	    			<c:when test="${layoutNo eq '3' && mOpLayoutMstInfoList.layout3UseYn eq 'Y' && mOpLayoutMstInfoList.dataTypeCd eq 'DTC001'}">
			        	<li class="side_item"><button type="button" id="sideBtn<c:out value='${status.index}'/>" onclick="drawLayer('<c:out value='${mOpLayoutMstInfoList.fnctType}'/>','<c:out value='${status.index}'/>')" class="is-side-btn"><c:out value='${mOpLayoutMstInfoList.layoutMenuNm}'/></button></li>
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
	            
                <div class="my_layout_contents">
	                	<c:forEach var="layoutList" items="${monitoringDashboardDTO.layoutList}" varStatus="layoutStatus">
							<div class="my_layout_tab my_layout_tab<c:out value='${layoutStatus.count}'/> <c:out value='${layoutStatus.count ne layoutNo ? "none":""}'/>">
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
										                                        <input type="checkbox" class="menu<c:out value='${layoutStatus.count}'/> menuCheckbox" data-id="<c:out value='${layoutMenu.layoutId}'/>" data-value="<c:out value='${layoutMenu.layout1UseYn}'/>" id="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" <c:out value='${layoutMenu.layout1UseYn eq "Y" ? "checked":""}'/>>
										                                        <label for="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" class="<c:out value='${layoutMenu.layout1UseYn eq "Y" ? "on":""}'/>"><c:out value='${layoutMenu.layoutMenuNm}'/>(<c:out value='${layoutMenu.dataTypeCd eq "DTC000"? "차트":"레이어"}'/>)</label>
										                                    </li>
																		</c:when>
																		<c:when test="${layoutStatus.count eq 2}">
										                                    <li class="check_box wh_check">
										                                        <input type="checkbox" class="menu<c:out value='${layoutStatus.count}'/> menuCheckbox" data-id="<c:out value='${layoutMenu.layoutId}'/>" data-value="<c:out value='${layoutMenu.layout2UseYn}'/>" id="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" <c:out value='${layoutMenu.layout2UseYn eq "Y" ? "checked":""}'/>>
										                                        <label for="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" class="<c:out value='${layoutMenu.layout2UseYn eq "Y" ? "on":""}'/>"><c:out value='${layoutMenu.layoutMenuNm}'/>(<c:out value='${layoutMenu.dataTypeCd eq "DTC000"? "차트":"레이어"}'/>)</label>
										                                    </li>
																		</c:when>
																		<c:when test="${layoutStatus.count eq 3}">
										                                    <li class="check_box wh_check">
										                                        <input type="checkbox" class="menu<c:out value='${layoutStatus.count}'/> menuCheckbox" data-id="<c:out value='${layoutMenu.layoutId}'/>" data-value="<c:out value='${layoutMenu.layout3UseYn}'/>" id="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" <c:out value='${layoutMenu.layout3UseYn eq "Y" ? "checked":""}'/>>
										                                        <label for="menu<c:out value='${layoutStatus.count}'/>_<c:out value='${menuStatus.count}'/>" class="<c:out value='${layoutMenu.layout3UseYn eq "Y" ? "on":""}'/>"><c:out value='${layoutMenu.layoutMenuNm}'/>(<c:out value='${layoutMenu.dataTypeCd eq "DTC000"? "차트":"레이어"}'/>)</label>
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
	                        <button type="button" id="saveBtn" class="" onclick="saveLayoutUseYn('<c:out value='${layoutNo}'/>')">확인</button>
	                    </div>
	            	</div>
                    <div class="my_layout_bookmark_wrap">
                        <button type="button" class="layoutSelectBtn <c:out value='${layoutNo eq "1" ? "my_layout_bookmark":""}'/>" data-layout="1">나의 레이아웃1</button>
                        <button type="button" class="layoutSelectBtn <c:out value='${layoutNo eq "2" ? "my_layout_bookmark":""}'/>" data-layout="2">나의 레이아웃2</button>
                        <button type="button" class="layoutSelectBtn <c:out value='${layoutNo eq "3" ? "my_layout_bookmark":""}'/>" data-layout="3">나의 레이아웃3</button>
                    </div>
            	</div>

			<div class="moitoring_dashboard_contentbox">
                <div class="mini_dash_box mini_height" id="FTC005">
					<div class="nullBox">레이아웃 비활성화</div>
                </div>
               <div class="mini_dash_box mini_height" id="FTC006">
					<div class="nullBox">레이아웃 비활성화</div>
               </div>
           </div>
        </div>            
    </div>
    <div class="monitoring_dashboard_sortable_conatiner sortable">
       <div class="mini_dash_box" id="FTC004">
      	 	<div class="nullBox">레이아웃 비활성화</div>
       </div>
       <div class="mini_dash_box" id="FTC007">
       		<div class="nullBox">레이아웃 비활성화</div>
       </div>
     	<div class="mini_dash_box" id="FTC008">
     		 <div class="nullBox">레이아웃 비활성화</div>
       </div>
       <div class="mini_dash_box" id="FTC009">
       		<div class="nullBox">레이아웃 비활성화</div>
       </div>
       <div class="mini_dash_box" id="FTC010">
       		<div class="nullBox">레이아웃 비활성화</div>
       </div>
       <div class="mini_dash_box" id="FTC011">
       		<div class="nullBox">레이아웃 비활성화</div>
       </div>
    </div>
</div> 

<script>

	$(document).ready(function(){
    	$('main').css('margin-top', '-1rem');
    	$('.main_container').addClass('monitoring_main_container')
        $('#map-container').addClass('monitoring_dashboard_mapbox');
        $('.control_container').addClass('monitoring_dashboard_control');
        $('.remarks_container').css('z-index', '9997');
        $('.my_layout_btn').removeClass('none');
        
        window.map = new GITSMapCore("map").init("MONITORING_DASHBOARD", null, null, function(){
    		$(".monitorig_side_button .side_item .is-side-btn").click();
    	});
        
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
  			map.monitoring.getTrafficInfo(true, 5000);
    		}
    		break;
    	case "FTC001":
    		if(isActive){
//     			map.facility.removeSignal();
    		} else {
//     			map.facility.getSignal();
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
	    
	    monitoringDashBoardInit("default");
	    
	    timer = setInterval( function () {
	    	monitoringDashBoardInit($("#sggCd").val());
	    }, 300000);	
	});
	
	
	function monitoringDashBoardInit(sggCd){
	   	var layoutNo = '<c:out value="${layoutNo}"/>';
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
	            	if(mapChartDataDTOList != null){
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
				            html += '         <div class="mini_dash_box_btn intersection_button btn_search_wrap">';
				            html += '				 <ul>';
					        html += '				 	<li>';
					        html += '						<button type="button" class="dash_dark_black_btn commBtn mj0 '+crossOn+'" onclick="changeCommTabData(this)" data-value="cross">교차로</button>';
					        html += '					 </li>';
					        html += '					<li>';
					        html += '						<button type="button" class="dash_dark_black_btn commBtn mj0 '+linkOn+'" onclick="changeCommTabData(this)" data-value="link">구간</button>';
					        html += '					 </li>';
							html += '				</ul> ';
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
						    html += '            <div id="commTableDiv" class="dashboard_scroll" style="max-height:10rem;">';
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
					        html += '           <div class="mini_dash_box_title"><span id="totalCnt">'+mapChartDataDTOList[i].totalCnt.toLocaleString("ko-KR")+'</span>건</div>';
					        html += '     </div>';
					        html += '     <div class="flex-between">';
					        html += '         	<div class="mini_time">(00:00 ~ 24:00)</div>';
					        
				            if(mapChartDataDTOList[i].compareStts == 'CSC003'){
							    html += '      <div>';
								html += ' 			전일 동시간 동일 -';
								html += ' 		</div>';
					        } else if(mapChartDataDTOList[i].compareStts == 'CSC000'){
					        	html += '      	<div class="mini_result_red">';
						       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt.toLocaleString("ko-KR")+'% ▲';
						        html += ' 		</div>';
					        } else {
					        	html += '      	<div class="mini_result_blue">';
						       	html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt.toLocaleString("ko-KR")+'% ▼';
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
	                        html += '                     <th>교통량</th>';                                 
	                        html += '                  </tr>'; 
	                        html += '               </thead>';                               
	                        html += '               <tbody>';
	                        var vlmtableData = mapChartDataDTOList[i].tableData;
	                        for(var j = 0; j < vlmtableData.length; j++){
		                        html += '                  <tr>';
		                        html += '                     <td>'+(j+1)+'</td>';              
		                        html += '                     <td>'+vlmtableData[j].roadName+'</td>';                              
		                        html += '                     <td>'+vlmtableData[j].trfvlm.toLocaleString("ko-KR")+'</td>';                              
		                        html += '                  </tr>'; 
	                        }
	                        html += '               </tbody>';                               
	                        html += '            </table>';
	                        html += '         </div>'
	                        html += '        <div class="flex-between time_rank">';
	                        html += '             <div>[시간대 별 누적 교통량 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
	                        html += '              <button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</div>';
	                        html += '        </div>';
	                        html += '      </div>'
					        html += '</div>';
	
			        
					        $("#FTC005").empty().append(html);
					        var vlmChartData = mapChartDataDTOList[i].chartData;
					        if(vlmChartData != null){
		            		    new GITSChart(GITSChartType.BAR).init("trf_accumulate_chart")
		            		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
		            		    .setDataSet({
		            		        	label : '교통량',
		            		            data : vlmChartData.split(','),
		            		            backgroundColor: '#58EDD2',
		            		            borderColor : '#58EDD2',
		            		            borderRadius:2,
		            		            fill: false
		            		    })
		            		    .setTicksStep(10000)
		            		    .setLabelDisplay(false)
		            		    .setBarGridY(true)
		            		    .draw();
					        }
	            			break;
	            		case "FTC006":
	            			//시간대별 평균 통행 속도
			              html += ' <div class="mini_height">';
			              html += '      <div class="flex-between baseline">';
			              html += '          <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
			              html += '          <div class="mini_dash_box_title"><span>'+mapChartDataDTOList[i].totalCnt.toLocaleString("ko-KR")+'</span>km/h</div>';
			              html += '      </div>';
			              html += '      <div class="flex-between">';
			              html += '      	<div class="mini_time">(00:00 ~ 24:00)</div>';
			              if(mapChartDataDTOList[i].compareStts == 'CSC003'){
					      	 html += '      <div>';
						     html += ' 			전일 동시간 동일 -';
						     html += ' 		</div>';
			              } else if(mapChartDataDTOList[i].compareStts == 'CSC000'){
					      	 html += '      <div class="mini_result_red">';
						     html += ' 			전일 동시간 대비 '+mapChartDataDTOList[i].compareCnt+'km/h ▲';
						     html += ' 		</div>';
					      } else {
					      	 html += '      <div class="mini_result_blue">';
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
	                      html += '                  <th>평균속도</th>';                                 
	                      html += '               </tr>'; 
	                      html += '            </thead>';                               
	                      html += '            <tbody>';                               
	                      var speedtableData = mapChartDataDTOList[i].tableData;
	                      for(var j = 0; j < speedtableData.length; j++){
		                        html += '                  <tr>';
		                        html += '                     <td>'+(j+1)+'</td>';              
		                        html += '                     <td>'+speedtableData[j].roadName+'</td>';                              
		                        html += '                     <td>'+speedtableData[j].speed+'km/h</td>';                              
		                        html += '                  </tr>'; 
	                      } 
	                      html += '            </tbody>';                               
	                      html += '         </table>';
	                      html += '      </div>'
	                      html += '       <div class="flex-between time_rank">';
	                      html += '          <div>[시간대 별 누적 평균속도 순위] <span>('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</span></div>';
	                      html += '           <button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</div>';
	                      html += '       </div>';
	                      html += '   </div>'				              
			              html += ' </div>';
					      $("#FTC006").empty().append(html);
					        var speedChartData = mapChartDataDTOList[i].chartData;
					        if(speedChartData != null){
		            		    new GITSChart(GITSChartType.LINE).init("trf_speed_rank_chart")
		            		    .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
		            		    .setDataSet({
		            		        	label : '평균속도',
		            		            data : speedChartData.split(','),
		            		            backgroundColor: '#58EDD2',
		            		            borderColor : '#58EDD2',
		            		            borderRadius:1,
		            		    })
		            		    .setTicksStep(50)
		            		    .setLabelDisplay(false)
		            		    .setBarGridY(true)
		            		    .draw();	
					        }
	            			break;
	            		case "FTC007":
	            			//시내버스 운행 현황
					        html += '<div>';
					        html += '     <div class="flex-between baseline">';
					        html += '           <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
					        html += '           <div class="mini_dash_box_title"><span id=busTotalCnt">'+mapChartDataDTOList[i].startCnt.toLocaleString("ko-KR")+'</span>건</div>';
					        html += '     </div>';
					        html += '     <div class="flex-end">';
				        	html += '      		<div class="mini_result"> 현재 운행중 </div>';
					        html += '     </div>';
					        html += '     <div class="mini_chart mini_bus_chart">';
					        html += '			<div class="mini_bus_chart_item">';
					        html += '           	<canvas id="bus_chart"></canvas>';
					        html += '			</div>';
					        html += '      </div>';
					        html += '</div>';
					        
						    $("#FTC007").empty().append(html);
							
						    if(mapChartDataDTOList[i].chartData2 != null){
								var busChartLabelData = mapChartDataDTOList[i].chartData2.split(',');
								var colorArr = new Array();
								for(var j = 0; j < busChartLabelData.length; j++){
							    	const u = new GitsMapUtil();
									const sggCd = busChartLabelData[j].trim();
							    	const sggInfo = u.getSGGInfoByCode(sggCd,GITS_ENV);
							    	
							    	colorArr.push(sggInfo.COLOR);
								}
						        new GITSChart(GITSChartType.BAR).init("bus_chart")
							        .setData({
							            labels: mapChartDataDTOList[i].chartLabel.split(','),
							                datasets: [{
							                    label:'시내버스 운행 현황',
							                    data: mapChartDataDTOList[i].chartData.split(','),
							                    backgroundColor:colorArr
							                }]
							        })    		    
								    .setTicksStep(200)
								    .setLabelDisplay(false)
								    .setBarGridY(true)
								    .draw();
						    }
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
				            html += '        <div class="mini_dash_box_btn intersection_button btn_search_wrap">';
				            html += '			<ul>';
				            html += '			    <li>';
				            html += '                  <button type="button" class="dash_dark_black_btn delayBtn mj0 '+cityOn+'" onclick="changeDelayTabData(this)" data-value="city">시군별</button>';
				            html += '				</li>';
				            html += '			    <li>';
							html += '                  <button type="button" class="dash_dark_black_btn delayBtn mj0 '+crossOn+'" onclick="changeDelayTabData(this)" data-value="cross">교차로</button>';
							html += '				</li>';
							html += '				<li>';
				            html += '                  <button type="button" class="dash_dark_black_btn delayBtn mj0 '+linkOn+'" onclick="changeDelayTabData(this)" data-value="link">구간</button>';						
				            html += '				</li>';
				            html += '			</ul>';
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
						    html += '            <div id="delayTabDiv" class="dashboard_scroll" style="max-height:10rem;">';
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
	           	            html += '     		<div class="mini_dash_box_title right"><span>'+mapChartDataDTOList[i].totalCnt.toLocaleString("ko-KR")+'</span>건</div>';
	               	        html += '     		<div class="mini_result">진행중 / 종료 ['+mapChartDataDTOList[i].startCnt.toLocaleString("ko-KR")+'건 / '+mapChartDataDTOList[i].endCnt.toLocaleString("ko-KR")+'건]</div>';
	                        html += '    	</div>';
	                        html += '	</div>';
	                        html += '	<div class="mini_chart">';
	                        html += '   	<div class="flex-between mini_chart_title">';
	                        html += '       	<div>[ 돌발 유형 별 발생 현황 ]</div>';
	                        html += '    	</div>';
	                        html += '    	<div class="mt8" style="height:30px;">';
	                        html += '        	<canvas id="outbreak_chart"></canvas>';
	                        html += '    	</div>';
	                        html += '	</div>';
	                        html += '	<div class="mini_chart_legend">'
	                        html += '		<ul class="chart_legend_box">'
	                        	if(mapChartDataDTOList[i].chartLabel != null && mapChartDataDTOList[i].chartLabel.indexOf(',')){
	                         		let chartLabelArr = mapChartDataDTOList[i].chartLabel.split(',');
	    	                        for(var j = 0; j < chartLabelArr.length; j++){
		    	                        if(chartLabelArr[j] == '지진'){
		    	                        	html += '			<li class="chart_legend legend_quake">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '홍수') {
		    	                        	html += '			<li class="chart_legend legend_flood">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '태풍') {
		    	                        	html += '			<li class="chart_legend legend_hurricane">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '장애물') {
		    	                        	html += '			<li class="chart_legend legend_hurdle">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '산사태') {
		    	                        	html += '			<li class="chart_legend legend_landslide">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '차량사고') {
		    	                        	html += '			<li class="chart_legend legend_car_accident">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '차량고장') {
		    	                        	html += '			<li class="chart_legend legend_car_breakdown">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '차량화재') {
		    	                        	html += '			<li class="chart_legend legend_car_fire">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '기상관련사고') {
		    	                        	html += '			<li class="chart_legend legend_weather">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '위험물질방출') {
		    	                        	html += '			<li class="chart_legend legend_danger">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '추가정보필요') {
		    	                        	html += '			<li class="chart_legend legend_info">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '차량의 급격한 증가') {
		    	                        	html += '			<li class="chart_legend legend_car_increase">'+chartLabelArr[j]+'</li>';
		    	                        } else if(chartLabelArr[j] == '예고되지않은 시위집회') {
		    	                        	html += '			<li class="chart_legend legend_demonstration">'+chartLabelArr[j]+'</li>';
		    	                        } 
	    	                        }
	                         	}
	                        html += '		</ul>'
	                        html += '	</div>'	
	                        html += '   <div class="dashboard_scroll" style="max-height:6rem; margin-top:1rem">';
	                        html += '      <table class="monitor_table">';
	                        html +=   '         <colgroup>';
	                        html +=   '            <col style="width:30%;">';
	                        html +=   '            <col style="width:40%;">';
	                        html +=   '            <col style="width:30%;">';
	                        html +=   '         </colgroup>';
	                        html +=   '         <thead>';
	                        html +=   '            <tr>';
	                        html += '               	<th>돌발유형</th>';
	                        html += '               	<th>발생장소명</th>';
	                        html += '               	<th>발생시각</th>';
	                        html +=   '            </tr>';
	                        html +=   '         </thead>';
	                        html +=   '         <tbody>';
	                        
	                        var tableDataArr = mapChartDataDTOList[i].tableData;
	                        if(tableDataArr != null && tableDataArr.length > 0){
	                        	for(var j = 0; j < tableDataArr.length; j++){
		                              var beginDate = tableDataArr[j].beginDate!=null?tableDataArr[j].beginDate:'-'
		                              var endDate = tableDataArr[j].endDate!=null?tableDataArr[j].endDate:'-'
		                              var inciCate = tableDataArr[j].inciCate;
			                            html +=   '            <tr>';
			                            if(inciCate == '1'){
				                            html += '<td class="">추가정보필요</td>';                              
				                        } else if(inciCate == '2'){
				                            html += '<td class="">차량사고</td>';
			                            } else if(inciCate == '3'){
				                            html += '<td class="">기상관련사고</td>';
				                        } else if(inciCate == '4'){
				                            html += '<td class="">차량고장</td>';
				                        } else if(inciCate == '5'){
				                            html += '<td class="">차량화재</td>';
				                        } else if(inciCate == '6'){
				                            html += '<td class="">장애물</td>';
				                        } else if(inciCate == '7'){
				                            html += '<td class="">위험물질방출</td>';
				                        } else if(inciCate == '8'){
				                            html += '<td class="">지진</td>';
				                        } else if(inciCate == '9'){
				                            html += '<td class="">산사태</td>';
				                        } else if(inciCate == '10'){
				                            html += '<td class="">홍수</td>';
				                        } else if(inciCate == '11'){
				                            html += '<td class="">태풍</td>';
				                        } else if(inciCate == '12'){
				                            html += '<td class="">예고되지않은 시위집회</td>';
				                        } else if(inciCate == '13'){
				                            html += '<td class="">차량의 급격한 증가</td>';
				                        }
				                         
			                            html += '                 <td>'+tableDataArr[j].roadwayNm+'</td>';                              
			                            html += '                 <td>'+beginDate+'</td>';                              
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
	                   		   		
	                   		   		switch(chartLabelArr[j]){
	                   		   		case "추가정보필요":
	                           	    	color = "#57C275";
	                   		   			break;
	                   		   		case "차량사고":
	                   		   			color = "#FF1C1C";
	                   		   			break;
	                   		   		case "기상관련사고":
	                   		   			color = "#FF5252";
	                   		   			break;
	                   		   		case "차량고장":
	                   		   			color = "#D2BD01";
	                   		   			break;
	                   		   		case "차량화재":
	                   		   			color = "#FC693B";
	                   		   			break;
	                   		   		case "장애물":
	                   		   			color = "#FF7A00";
	                   		   			break;
	                   		   		case "위험물질방출":
	                   		   			color = "#A2BC03";
	                   		   			break;
	                   		   		case "지진":
	                   		   			color = "#00BA8D";
	                   		   			break;
	                   		   		case "산사태":
	                   		   			color = "#DE5D00";
	                   		   			break;
	                   		   		case "홍수":
	                   		   			color = "#458CF6";
	                   		   			break;
	                   		   		case "태풍":
	                   		   			color = "#606DE5";
	                   		   			break;
	                   		   		case "예고되지않은 시위집회":
	                   		   			color = "#D45FC9";
	                   		   			break;
	                   		   		case "차량의 급격한 증가":
	                   		   			color = "#FF5387";
	                   		   			break;
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
				           	    .setAxis('y')
	 			           	    .setBarGridY(false)
				           	    .setBarGridX(false)
				           	    .setDisplayX(false)
				           	    .setDisplayY(false)
				           	    .setLabelDisplay(false)
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
					        html += '        <div class="mini_dash_box_btn btn_search_wrap">';
			    		    html += '			<ul class="collection_button">';
			    		    html += '				<li>';
			    		    html += ' 					<button type="button" class="dash_dark_black_btn dash_dark_black_btn collBtn '+allOn+'" onclick="changeCollTabData(this)" data-value="all">전체</button>';
			    		    html += '				</li>';
			    		    html += '				<li>';
			    		    html += '					<button type="button" class="dash_dark_black_btn collBtn'+locGovmntOn+'" onclick="changeCollTabData(this)" data-value="SMT000">지자체 연계</button>';
			    		    html += '				</li>';
			    		    html += '				<li>';
			    		    html += '					<button type="button" class="dash_dark_black_btn collBtn'+externalOn+'" onclick="changeCollTabData(this)" data-value="SMT001">외부기관 연계</button>';
			    		    html += '				</li>';
			    		    html += '				<li>';
			    		    html += '					<button type="button" class="dash_dark_black_btn collBtn'+signalOn+'" onclick="changeCollTabData(this)" data-value="SMT002">신호 연계</button>';
			    		    html += '				</li>';
			    		    html += '				<li>';
			    		    html += '					<button type="button" class="dash_dark_black_btn collBtn'+bigDataOn+'" onclick="changeCollTabData(this)" data-value="SMT003">빅데이터 플랫폼</button>';
			    		    html += '				</li>';
			                html += '		    </ul>';
					        html += '        </div>';
				            html += '    <div class="mt16">';
				            html += '        <div class="flex-column">';
				            html += '            <div class="mini_dash_box_title">'+mapChartDataDTOList[i].title+'</div>';
				            html += '            <div class="mini_time">('+mapChartDataDTOList[i].startDt+' ~ '+mapChartDataDTOList[i].endDt+')</div>';
				            html += '        </div>';
				            html += '    </div>';
				            html += '    <div>';
					        html += '        <div>';
						    html += '            <div class="dashboard_scroll" style="max-height:10rem;">';
							html += '				<table class="monitor_table">';
							html += '					<colgroup>';
							html += ' 						<col width="20%">';
							html += ' 						<col width="20%">';
							html += ' 						<col width="20%">';
							html += ' 						<col width="15%">';
							html += '					</colgroup>';
							html += '					<thead>';
							html += '						<tr>';
							html += '							<th>수집원</th>';
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
	        	                speed:800,
	        	                slidesToScroll : 1,
	        	                infinite:false,
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
	           	            html += '     		<div class="mini_dash_box_title right"><span>'+mapChartDataDTOList[i].totalCnt.toLocaleString("ko-KR")+'</span>대</div>';
	               	        html += '     		<div class="mini_result">이동 중 / 이동완료 ['+mapChartDataDTOList[i].startCnt.toLocaleString("ko-KR")+'대 / '+mapChartDataDTOList[i].endCnt.toLocaleString("ko-KR")+'대]</div>';
	                        html += '    	</div>';
	                        html += '	</div>';
	                        html += '	<div class="mini_chart">';
	//                         html += '   	<div class="flex-between mini_chart_title">';
	//                         html += '       	<div>[ 긴급차량 골든타임 달성 현황 ]</div>';
	//                         html += '    	</div>';
	                        html += '    	<div class="mt8" style="height:30px">';
	                        html += '        	<canvas id="emergency_chart"></canvas>';
	                        html += '    	</div>';
	                        html += '	</div>';
	                        html += '	<div class="mini_chart_legend">'
							html += '		<ul class="chart_legend_box2">'
							html += '			<li class="chart_legend2">이동중</li>'
							html += '			<li class="chart_legend2">이동완료</li>'
							html += '		</ul>'
							html += '	</div>'	                        
	                        html += '   <div class="dashboard_scroll" style="max-height:8.5rem; margin-top:1rem;">';
	                        html += '      <table class="monitor_table">';
	                        html += '         <colgroup>';
	                        html += '            <col style="width:40%;">';
	                        html += '            <col style="width:20%;">';
	                        html += '            <col style="width:40%;">';
	                        html += '         </colgroup>';
	                        html += '         <thead>';
	                        html += '            <tr>';
	                        html += '               <th class="">차량번호</th>';
	                        html += '               <th>재난명</th>';
	                        html += '               <th>도착예정시간</th>';
	                        html += '            </tr>';
	                        html += '         </thead>';
	                        html += '         <tbody id="collTabBody">';
	                        var emgTableDataArr = mapChartDataDTOList[i].tableData;
	                        	if(emgTableDataArr != null){
		                              for(var j = 0; j < emgTableDataArr.length; j++){
		                            	  var evno = emgTableDataArr[j].evno;
		                            	  var ocrtype = emgTableDataArr[j].ocrtype;
		                            	  var arrivaltimeStr = ''; 
		                            	  var arrivaltime = emgTableDataArr[j].arrivaltime;
		                            	  
		                            	  if(arrivaltime != null && arrivaltime != ''){
											  var arrivaltimeNum = Number(arrivaltime);
											  
											  var minute = arrivaltimeNum/60;
			                            	  var second = arrivaltimeNum%60;
			                            	  
			                            	  arrivaltimeStr = Math.floor(minute)+"분 "+second+"초";
		                            	  }
			                              html +=   '            <tr>';
			                              html += '					<td class="">'+evno+'</td>';
			                              html += '                 <td>'+ocrtype+'</td>';                              
			                              html += '                 <td>'+arrivaltimeStr+'</td>';                              
			                              html +=   '            </tr>'; 
		                              }
	                        } else {
	                        html += '	<tr>';
	                        html += '   	<td colspan="3" class="">';
							html += '			현재 이동중인 긴급 차량이 없습니다.';		                        	
							html += '		</td>';		                        	
							html += '	</tr>';
	                        }
	                        html +=   '         </tbody>';                               
	                        html += '      </table>';
	                        html += '   </div>';
	                   		html += '</div>';
	                   		
	                   		$("#FTC011").empty().append(html);
	                   	
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
			                              if(chartLabelArr[j] == '이동중'){
			                            	  color = "#F90";
				                          } else if(chartLabelArr[j] == '이동완료'){
			                            	  color = "#8F5AFF";
				                          }
		                   		   		dataObj.data = new Array(chartDataArr[j]);
		                   		   		dataObj.backgroundColor = color;
		                   		   		dataObj.borderRadius = 3;
		                   		   	    dataSetLabel.push('');
		                   		   		dataArr.push(dataObj);
		                   		   	}
		                   		}
		                   		
				            	//차트 그리기
				           	new GITSChart(GITSChartType.BAR).init("emergency_chart")
				           	    .setDataSetArrayLabel(dataSetLabel)
				           	    .setDataArraySet(dataArr)
				           	    .setTicksStep(1)
				           	    .setAxis('y')
				           	    .setBarGridY(false)
				           	    .setBarGridX(false)
				           	    .setDisplayX(false)
				           	    .setDisplayY(false)
				           	    .setLabelDisplay(false)
				           	    .draw();
	            			break;
	            		default :
	            			break;
	            		}
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
      	var layoutNo = '<c:out value="${layoutNo}"/>';
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

	//레이아웃 팝업 (2023-11-20)
    /* $("#myLayout").on('click', function(){
        new ModalBuilder().init('나의 레이아웃 설정하기').ajaxBody("${pageContext.request.contextPath}/common/modal/monitroing/layout/list.do").footer(1,'확인',function(button, modal){}).open();
        $(".modal_footer").removeClass('none');
    }) */
    
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
						delay = "원활";
					} else if(delayStatus == 2){
						delay = "지체(서행)";
					} else {
						delay = "정체";
					}
		            html += '					    <tr>';
					html += '					        <td>'+(j+1)+'</td>';
					html += '					        <td>'+delayTabData[j].adsiNm+'</td>';
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
						delay = "원활";
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
						delay = "원활";
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
					html += '					        <td class="">'+collTableData[j].cdNm+'</td>';
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
    
    $("#sggCd").on('change',function(){
    	$this = $(this);
    	
    	const u = new GitsMapUtil();
    	var sggInfo = u.getSGGInfoByCode($(this).val(),GITS_ENV);
    	
     	map.control.moveMap(sggInfo.COORDINATES);
    });
    
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header ver1 mt16 mb16">
    <div class="header_container">
        <div class="logo_box">
            <h1 class="logo">
                <span class="logo_title">
                    <img src="/statics/images/hd_logo.png" alt="경기도 교통 빅데이터 시스템">
                </span>
            </h1>
        </div>
        <div class="utility_box flex-center">
            <div class="day_weather_box flex-center">
                <div class="day_data">2023년 12월 20일</div>
            </div>
            <div class="email_box flex-center">
                <div class="email">bluedustest@bluedus.co.kr</div>
            </div>
            <div class="setting">
                <button type="button" class="userinfo_btn"><img src="/statics/images/setting.png" alt="설정"></button>
                <div class="userinfo_box none">
                	<div class="userinfo">
                		<a href="javascript:void(0)" onclick="logout(this);">
                			<img src="/statics/images/logout_icon.png">로그아웃 
                		</a>
                	</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main_container">
    <div class="monitorig_side_button">
	    <ul>
        	<li class="side_item"><button type="button" id="emergencyBtn" onclick="drawLayer(this)" data-value="FTC003" class="is-side-btn active">광역 긴급차량<br>운행 현황</button></li>
        	<li class="side_item"><button type="button" id="outbreakBtn" onclick="drawLayer(this)" data-value="FTC002" class="is-side-btn active">돌발 현황</button></li>
	    </ul> 
    </div>
    <!-- 대시보드 -->
    <div class="monitoring_dashboard_map_conatiner">
        <div class="monitoring_dashboard_map_wrap">
			<c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
			<div class="moitoring_dashboard_contentbox">
                <div class="mini_dash_box mini_height">
	              	<div class="flex-between baseline"> 
	                    <div class="mini_dash_box_title">경기도 서비스 링크 교통량 순위</div> 
	                    <div class="mini_dash_box_title"><span id="totalCnt"></span>건</div> 
	              	</div> 
	              	<div class="flex-between"> 
	                  	<div class="mini_time">(00:00 ~ 24:00)</div> 
	        	       	<div class="mini_result_blue"> 
		       	  			전일 동시간 대비 %▼ 
		          		</div> 
	              	</div> 
                    <div class="mini_chart_wrap">
	              		<div class="mini_chart"> 
	                     	<canvas id="trafficInfoChart"></canvas> 
	               		</div> 
	            	    <div class="flex-between time_rank"> 
	             	        <div>[시간대 별 누적 교통량 순위] <span></span></div> 
                                   <button type="button" class="mini_table_top" onclick="onTable(this)">TOP5 보기</button> 
	               		</div> 
	              	</div> 
                    <div class="monitoring_dashboard_rank none">
   	              		<div class="mt16 monitoring_dashboard_rank_wrap">
	                        <table class="monitor_table"> 
	                           <colgroup> 
	                              <col style="width:16%;"> 
	                              <col style="width:64%;"> 
	                              <col style="width:20%;"> 
	                           </colgroup> 
	                           <thead> 
	                              <tr> 
	                                 <th>순위</th>                                  
	                                 <th>도로명</th>                                  
	                                 <th>교통량</th>                                  
	                              </tr>  
	                           </thead>                                
	                           <tbody> 
	                               <tr> 
	                                  <td></td>               
	                                  <td></td>                               
	                                  <td></td>                               
	                               </tr>  
	                           </tbody>                                
	                        </table> 
                        </div>
                        <div class="flex-between time_rank"> 
                            <div>[시간대 별 누적 교통량 순위] <span></span></div> 
                            <div><button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</button></div> 
                        </div> 
                	</div>
                </div>
                <div class="mini_dash_box mini_height">
	              	<div class="flex-between baseline"> 
	                    <div class="mini_dash_box_title">경기도 서비스 링크 정체 순위</div> 
	                    <div class="mini_dash_box_title"><span id="totalCnt"></span>건</div> 
	              	</div> 
	              	<div class="flex-between"> 
	                  	<div class="mini_time">(00:00 ~ 24:00)</div> 
	        	       	<div class="mini_result_blue"> 
		       	  			전일 동시간 대비 %▼ 
		          		</div> 
	              	</div> 
                    <div class="mini_chart_wrap">
	              		<div class="mini_chart"> 
	                     	<canvas id="linkDelayChart"></canvas> 
	               		</div> 
	            	    <div class="flex-between time_rank"> 
	             	        <div>[시간대 별 누적 평균속도 순위] <span></span></div> 
                                   <button type="button" class="mini_table_top" onclick="onTable(this)">TOP5 보기</button> 
	               		</div> 
	              	</div> 
                    <div class="monitoring_dashboard_rank none">
   	              		<div class="mt16 monitoring_dashboard_rank_wrap">
	                        <table class="monitor_table"> 
	                           <colgroup> 
	                              <col style="width:16%;"> 
	                              <col style="width:64%;"> 
	                              <col style="width:20%;"> 
	                           </colgroup> 
	                           <thead> 
	                              <tr> 
	                                 <th>순위</th>                                  
	                                 <th>도로명</th>                                  
	                                 <th>교통량</th>                                  
	                              </tr>  
	                           </thead>                                
	                           <tbody> 
	                               <tr> 
	                                  <td></td>               
	                                  <td></td>                               
	                                  <td></td>                               
	                               </tr>  
	                           </tbody>                                
	                        </table> 
                        </div>
                        <div class="flex-between time_rank"> 
                            <div>[시간대 별 누적 평균 속도 순위] <span></span></div> 
                            <div><button type="button" class="mini_chart_top" onclick="onChart(this)">차트 보기</button></div> 
                        </div> 
                	</div>
                </div>
           </div>
        </div>            
    </div>
    <div class="monitoring_dashboard_sortable_conatiner">
       <div class="mini_dash_box vip_dash_box">
	   		<div class="mini_dash_box_title">시군별 광역 긴급차량 발생 건수</div>
	   		<div class="mt16">
		   		<table class="monitor_table">         
			   		<colgroup>            
				   		<col style="width:30%;">            
				   		<col style="width:40%;">            
				   		<col style="width:30%;">         
			   		</colgroup>         
			   		<thead>            
				   		<tr>               	
					   		<th></th>               	
					   		<th></th>               	
					   		<th></th>            
				   		</tr>         
			   		</thead>         
			   		<tbody>            
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
			   		</tbody>
			   	</table>            
	   		</div>
       </div>
       <div class="mini_dash_box vip_dash_box">
       		<div class="mini_dash_box_title">긴급차량 발생 유형</div>
       		<div class="mt16" style="height:30px;">
            	<canvas id="emergency_chart"></canvas>
            </div>
            <div class="mini_chart_legend">		
	            <ul class="chart_legend_box">			
		            <li class="chart_legend legend_car_breakdown">구급</li>			
		            <li class="chart_legend legend_car_accident">재난</li>		
	            </ul>	
            </div>
       </div>
	   <div class="mini_dash_box vip_dash_box">
	   		<div class="mini_dash_box_title">시군별 돌발 발생 건수 순위</div>
	   		<div class="mt16">
		   		<table class="monitor_table">         
			   		<colgroup>            
				   		<col style="width:30%;">            
				   		<col style="width:40%;">            
				   		<col style="width:30%;">         
			   		</colgroup>         
			   		<thead>            
				   		<tr>               	
					   		<th></th>               	
					   		<th></th>               	
					   		<th></th>            
				   		</tr>         
			   		</thead>         
			   		<tbody>            
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
				   		<tr>
					   		<td></td>                 
					   		<td></td>                
					   		<td></td>            
			   			</tr>
			   		</tbody>
			   	</table>            
	   		</div>
       </div>
       <div class="mini_dash_box vip_dash_box">
       		<div class="mini_dash_box_title">시군별 돌발 발생 건수</div>
       </div>
    </div>
</div>
<script>
	/**
		GIS맵 모니터링 레이어 기능
	*/
	function drawLayer($this){
		var fnctType = $($this).data("value");
		var isActive = $($this).hasClass("active");
		//버튼 active 기능
    	switch(fnctType){
    	case "FTC002":
    	//돌발현황
    		if(isActive){
    			//돌발현황 끄기
    			map.monitoring.removeWarningInfo();
		   		$($this).removeClass("active");
    		}else {
    	        //돌발현황 켜기
   	        	map.monitoring.getWarningInfo(false);
		   		$($this).addClass("active");
    		}
    		break;
    	case "FTC003":
   	     //긴급차량 운영 현황
    		if(isActive){
		   	    //긴급차량 운영 현황 끄기
    			map.monitoring.removeEmergencyMoveInfo();
		   		$($this).removeClass("active");
    		} else {
		   	    //긴급차량 운영 현황 켜기
    			map.monitoring.getEmergencyMoveInfo(false);
		   		$($this).addClass("active");
    		}
    		break;
   		default :
    		return false;
    	}
	}
	
	/**
		로그아웃	
	*/
	function logout(_this){
		new ModalBuilder().init().alertBoby("로그아웃 하시겠습니까?").footer(5,'로그아웃',function(button, modal){
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/logout.ajax",
				success : function(data) {
					if(data.code == '200'){
						location.href = "${pageContext.request.contextPath}/login.do";
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();					
					}
				}
			});	
		},'취소하기',function(button, modal){
			modal.close();
		}).open();	
	}

	$(document).ready(function(){
		$('main').css('margin-top', '-1rem');
		$('.main_container').addClass('monitoring_main_container')
	    $('#map-container').addClass('monitoring_dashboard_mapbox');
	    $('.control_container').addClass('monitoring_dashboard_control');
	    $('.remarks_container').css('z-index', '9997');
	    
	    window.map = new GITSMapCore("map").init("MONITORING_DASHBOARD", null, null, function(){
			$(".monitorig_side_button .side_item .is-side-btn").click();
		});
	    
	    gitsApp.setMap(map);
	 });
	
	$(function(){
	    linkDelayChartInit();
	    trafficInfoChartInit();
	    emergencyChartInit();
		
	    timer = setInterval( function () {
		    linkDelayChartInit();
		    trafficInfoChartInit();
		    emergencyChartInit();
	    }, 300000);	
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
    
    function emergencyChartInit(){
	    //긴급차량 발생 유형
	  	new GITSChart(GITSChartType.BAR).init("emergency_chart")
		.setDataSetLabel('','')
		.setDataSet({
	        label:'구급',
	        data:[4],
	        backgroundColor: '#FF2828',
	        borderRadius:3,
	    },{
	        label:'재해',
	        data:[1],
	        backgroundColor:'#58EDD2',
	        borderRadius:3,
	    })
		.setAxis('y')
		.setBarGridY(false)
		.setBarGridX(false)
		.setDisplayX(false)
		.setDisplayY(false)
		.setLabelDisplay(false)
		.draw();
    }
    
    //경기도 서비스 링크 정체 순위
    function linkDelayChartInit(){
        new GITSChart(GITSChartType.LINE).init("linkDelayChart")
        .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
        .setDataSet({
            	label : '평균속도',
                data : ['1','2'],
                backgroundColor: '#58EDD2',
                borderColor : '#58EDD2',
                borderRadius:1,
        })
        .setTicksStep(50)
        .setLabelDisplay(false)
        .setBarGridY(true)
        .draw();
    }
    
    //경기도 서비스 링크 교통량 순위
    function trafficInfoChartInit(){
        new GITSChart(GITSChartType.BAR).init("trafficInfoChart")
        .setDataSetLabel('00시','1시','2시','3시','4시','5시','6시','7시','8시','9시','10시','11시','12시','13시','14시','15시','16시','17시','18시','19시','20시','21시','22시','23시')
        .setDataSet({
            	label : '교통량',
                data : ['1','2'],
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
</script>
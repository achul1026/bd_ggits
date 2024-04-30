<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
    <div class="monitorig_side_button">
    	<div>
    		<select id="sggCd" class="selectBox dashboard_select" onchange="focusSgg(this)">
                <option value="41110">경기도 전체</option>
				<c:forEach var="sggCdList" items="${sggCdList}">
                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
				</c:forEach>
    		</select>
    	</div>
	    <ul>
			<li class="side_item"><button type="button" onclick="drawLayer('FTC000',this)" class="is-side-btn">소통정보</button></li>
			<li class="side_item"><button type="button" onclick="drawLayer('FTC002',this)" class="is-side-btn">돌발현황</button></li>
			<li class="side_item"><button type="button" onclick="drawLayer('FTC003',this)" class="is-side-btn">긴급차량</button></li>
	    </ul> 
    </div>
    <!-- 대시보드 -->
    <div class="monitoring_dashboard_map_conatiner">
        <div class="monitoring_dashboard_map_wrap">
		<c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
			<div class="moitoring_dashboard_contentbox">
				<div id="smcrdDiv" class="mini_dash_box mini_height">
				</div>
				<div id="svcCongestionDiv" class="mini_dash_box mini_height">
				</div>
			</div>
		</div>
    </div>
    <div class="monitoring_dashboard_sortable_conatiner">
		<div id="acdntPredictionDiv" class="mini_dash_box">
		</div>
		<div id="populationPredictionDiv" class="mini_dash_box">
		</div>
		<div id="emrgAcheivePtgDiv" class="mini_dash_box">
		</div>
		<div id="busStationUsageDiv" class="mini_dash_box">
		</div>
		<div id="emrgByMnginstcdDiv" class="mini_dash_box">
		</div>
		<div id="warningByMnginstcdDiv" class="mini_dash_box">
		</div>
    </div>
</div> 

<script>

	var smcrdTop10Stts = true;
	var emergByMnginstcdStts = true;
	var warningByMnginstcdStts = true;
	var svcCongestionTop10Stts = true;
	var acdntPredictionTop10Stts = true;
	var busStationUsageStts = true;
	var populationPredictionTop10Stts = true;
	var emergAcheivePtgStts = true;
	
	
	$('main').css('margin-top', '-1rem');
	$('.main_container').addClass('monitoring_main_container')
	$('#map-container').addClass('monitoring_dashboard_mapbox');
	$('.control_container').addClass('monitoring_dashboard_control');
	$('.remarks_container').css('z-index', '9997');
	$('.my_layout_btn').removeClass('none');
	window.map = new GITSMapCore("map").init("MONITORING_DASHBOARD", null, null, function(){
		$(".monitorig_side_button .side_item .is-side-btn").click();
	});

//  	gitsApp.setMap(map);

	var loadingHtml = 
		 '<div class="loadingPart loading-dashboard">'
        +	'<span></span>'
        +	'<span></span>'
        +	'<span></span>'
		+'</div>';
		
	var errorHtml = 
		'<div class="mini_box_error_message">정보 조회중 오류가 발생했습니다.</div>';

    
	function drawLayer(fnctType,_this){
    	//버튼 active 기능
    	var layerBtn = $(_this);
    	var isActive = layerBtn.hasClass("active");
    	
    	switch(fnctType){
    	case "FTC000":
    	//교통량
    		if(isActive){
	       		//실시간 교통 현황 조회 끄기
    			map.monitoring.removeTrafficInfo();
    		} else {
	        	//실시간 교통 현황 조회 켜기
				map.monitoring.getTrafficInfoMinimize();
	       	 	map.monitoring.getTrafficInfo(true,60000);
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
	
	function focusSgg(_this){
			let $this = $(_this);
			const u = new GitsMapUtil();
			
			let sggInfo = u.getSGGInfoByCode($this.val(),GITS_ENV);
			map.control.moveMap(sggInfo.COORDINATES, 11);
	}

	$(function(){
		/* 스마트교차로 교차로별 top 10 */
		smcrdTop10Init(true);
		/* 지자체별 긴급차량 발생건수 */
		emergByMnginstcdInit(true);
		/* 돌발 수집원별 수 */
		warningByMnginstcdInit(true);
		/* 도로별 주요 정체구간 TOP 10 */
		svcCongestionTop10Init(true,'고속국도');
		/* 사고예측구간 지수 top 10 */
		acdntPredictionTop10Init(true);
		/* 정류장별 이용객수 */
		busStationUsageInit(true);
		/* 유동인구 밀집 예측 */
		populationPredictionTop10Init(true);
		/* 일일 긴급차량 달성률 분석정보 */
		emergAcheivePtgInit(true);
		
		$(document).ready(function(){
	    timer = setInterval( function () {
	    	var roadDiv = $("#roadDiv").val();
			/* 스마트교차로 교차로별 top 10 */
			smcrdTop10Init(smcrdTop10Stts);
			/* 지자체별 긴급차량 발생건수 */
			emergByMnginstcdInit(emergByMnginstcdStts);
			/* 돌발 수집원별 수 */
			warningByMnginstcdInit(warningByMnginstcdStts);
			/* 도로별 주요 정체구간 TOP 10 */
			svcCongestionTop10Init(svcCongestionTop10Stts,roadDiv);
			/* 사고예측구간 지수 top 10 */
			acdntPredictionTop10Init(acdntPredictionTop10Stts);
			/* 정류장별 이용객수 */
			busStationUsageInit(busStationUsageStts);
			/* 유동인구 밀집 예측 */
			populationPredictionTop10Init(populationPredictionTop10Stts);
			/* 일일 긴급차량 달성률 분석정보 */
			emergAcheivePtgInit(emergAcheivePtgStts);
	    }, 600000);
		})
		
	    
	});
	
	/* 스마트교차로 교차로별 top 10 */
	function smcrdTop10Init(smcrdTop10Stts){
		if(smcrdTop10Stts){
			smcrdTop10Stts = false;
			
			$("#smcrdDiv").empty().append(loadingHtml);
			
	        $.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM000/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	smcrdTop10Stts = true;
	            	$("#smcrdDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	smcrdTop10Stts = true;
	            	$("#smcrdDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
		
	}
	
	/* 지자체별 긴급차량 발생건수 */
	function emergByMnginstcdInit(emergByMnginstcdStts) {
		if(emergByMnginstcdStts){
			emergByMnginstcdStts = false;
			$("#emrgByMnginstcdDiv").empty().append(loadingHtml);
			
	        $.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM001/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	emergByMnginstcdStts = true;
	            	$("#emrgByMnginstcdDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	emergByMnginstcdStts = true;
	            	$("#emrgByMnginstcdDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
	/* 돌발 수집원별 수 */
	function warningByMnginstcdInit(warningByMnginstcdStts){
		if(warningByMnginstcdStts){
			warningByMnginstcdStts = false;
			$("#warningByMnginstcdDiv").empty().append(loadingHtml);
			
	        $.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM002/loadMonitoringModule.ajax",
	            cache : false,
				timeout : 60*1000*10,
	            dataType : "html",
	            success : function(html) {
	            	warningByMnginstcdStts = true;
	            	$("#warningByMnginstcdDiv").empty().html(html);
	            },
	            error : function(e){
	            	warningByMnginstcdStts = true;
	            	$("#warningByMnginstcdDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
	/* 도로별 주요 정체구간 TOP 10 */
	function svcCongestionTop10Init(svcCongestionTop10Stts , roadDiv){
		if(svcCongestionTop10Stts){
			svcCongestionTop10Stts = false;
			$("#svcCongestionDiv").empty().append(loadingHtml);
			
			var urlAddr = "${pageContext.request.contextPath}/monitoring/MDM003/loadMonitoringModule.ajax";
			
			if(roadDiv != null && roadDiv != '' && typeof(roadDiv) != 'undefined'){
				urlAddr = "${pageContext.request.contextPath}/monitoring/MDM003/loadMonitoringModule.ajax?roadDiv="+roadDiv;
			}
			
	        $.ajax ({
	            type : "get",
	            url : urlAddr,
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	svcCongestionTop10Stts = true;
	            	$("#svcCongestionDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	svcCongestionTop10Stts = true;
	            	$("#svcCongestionDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
	/* 사고예측구간 지수 top 10 */
	function acdntPredictionTop10Init(acdntPredictionTop10Stts){
		if(acdntPredictionTop10Stts){
			acdntPredictionTop10Stts = false;
			
			$("#acdntPredictionDiv").empty().append(loadingHtml);
			
	        $.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM004/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	acdntPredictionTop10Stts = true;
	            	$("#acdntPredictionDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	acdntPredictionTop10Stts = true;
	            	$("#acdntPredictionDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
	/* 정류장별 이용객수 */
	function busStationUsageInit(busStationUsageStts){
		if(busStationUsageStts){
			busStationUsageStts = false;
			
			$("#busStationUsageDiv").empty().append(loadingHtml);
	
			$.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM005/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	busStationUsageStts = true;
	            	$("#busStationUsageDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	busStationUsageStts = true;
	            	$("#busStationUsageDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
	/* 유동인구 밀집 예측 */
	function populationPredictionTop10Init(populationPredictionTop10Stts){
		if(populationPredictionTop10Stts){
			populationPredictionTop10Stts = false;
			
			$("#populationPredictionDiv").empty().append(loadingHtml);
	        $.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM006/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	populationPredictionTop10Stts = true;
	            	$("#populationPredictionDiv").empty().html(html);
	            } ,
	            error : function(e){
	            	populationPredictionTop10Stts = true;
	            	$("#populationPredictionDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
		
	}
	
	/* 일일 긴급차량 달성률 분석정보 */
	function emergAcheivePtgInit(emergAcheivePtgStts) {
		if(emergAcheivePtgStts) {
			emergAcheivePtgStts = false;

			$("#emrgAcheivePtgDiv").empty().append(loadingHtml);
	
			$.ajax ({
	            type : "get",
	            url : "${pageContext.request.contextPath}/monitoring/MDM007/loadMonitoringModule.ajax",
	            cache : false,
	            dataType : "html",
	            success : function(html) {
	            	emergAcheivePtgStts = true;
	            	$("#emrgAcheivePtgDiv").empty().html(html);
	            },
	            error : function(e){
	            	emergAcheivePtgStts = true;
	            	$("#emrgAcheivePtgDiv").empty().html(errorHtml);
	            }
	        })
		} else {
			return false;
		}
	}
	
</script>
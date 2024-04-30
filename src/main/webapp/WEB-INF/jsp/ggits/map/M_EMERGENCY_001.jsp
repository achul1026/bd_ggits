<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_body_wrap">
		<div class="unex_list mb10">
	<div class="unex_list_title">광역 긴급차량 이동 발생건수</div>
	<div class="unex_list_number"><c:out value='${fn:length(emergencyList)}'/>건</div>
</div>
<div class="unex_wrap gis_scroll" id="emergencyDiv">
		<c:forEach var="emergencyList" items="${emergencyList}">
		<div class="tab_box_content map_movement_status map_movement_status1">
			<div class="unex_history" data-mapx="<c:out value='${emergencyList.currentlat}'/>" data-mapy="<c:out value='${emergencyList.currentlng}'/>" data-serviceid="<c:out value='${emergencyList.serviceid}'/>" onclick="fnEmergencyEvent(this);">
				<div class="<c:out value='${emergencyList.emrgCurSttsCd eq "CUS001" ? "blue" : "red"}'/> unex_title" data-check-serviceid="<c:out value='${emergencyList.serviceid}'/>">
					<h3>[<c:out value='${emergencyList.emrgCurSttsCd eq "CUS001" ? "이동완료" : "이동중"}'/>] <c:out value='${emergencyList.evno}'/> <c:out value='${emergencyList.servicename}'/></h3>
				</div>
			</div>
			<div class="<c:out value='${emergencyList.emrgCurSttsCd eq "CUS001" ? "blue" : "red"}'/> unex_content">
				<ul>
					<li>[<c:out value='${emergencyList.emrgCurSttsCd eq "CUS001" ? "이동완료" : "이동중"}'/>] : <c:out value='${emergencyList.evno}'/></li>
					<li>출발시간 : <fmt:formatDate value="${emergencyList.startDate}" pattern="HH시 mm분"/> </li>
					<li>도착예측시간 : <fmt:formatDate value="${emergencyList.predictedArrivalDate}" pattern="HH시 mm분"/> </li>
					<li>예상운행시간 : <c:out value='${emergencyList.arrivaltimeFormat}'/></li>
					<li>실제도착시간 :
						<c:choose>
							<c:when test="${emergencyList.emrgCurSttsCd eq 'CUS001'}">
								<fmt:formatDate value="${emergencyList.arrivalDate}" pattern="HH시 mm분"/>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</li>
					<li>운행단축시간  : <c:out value='${emergencyList.timeDifference}'/></li>
					<li>소속기관  : <c:out value='${emergencyList.firename}'/></li>
				</ul>
			</div>
		</div>
	</c:forEach>
</div>
</div>

<script>

$(function() {

	dataTimer = setInterval( function () {
		if($("#emergencyDiv").length === 0){
			return;
		}
        $.ajax ({
            "url" : "${pageContext.request.contextPath}/map/monitoring/emergency/M_EMERGENCY_001/data.ajax",
            cache : false,
            success : function (result) {
				            	
            	var unexTitle = $(".unex_title");
            	var checkServiceId = "";
            	$(unexTitle).each(function(idx,item){
            		if($(item).hasClass("on")){
            			checkServiceId = $(item).data("check-serviceid"); 
            		}
            	})
            	$("#emergencyDiv").children().remove();
            	var html = "";
            	var resultList = result.data;
            	var totalSize = resultList.length;
            	$(resultList).each(function(idx,item){
	            	var curSttsClass = "red";
	            	var curSttsText = "이동중";
	            	var arrivalDate = "-";
	            	var startDate = new Date(item.startDate);
	            	var predictedArrivalDate = new Date(item.predictedArrivalDate);
	            	var predictedArrivalDate = new Date(item.predictedArrivalDate);
	            	if(item.emrgCurSttsCd == "CUS001"){
	            		curSttsClass = "blue";
	            		curSttsText = "이동완료";
	            		arrivalDate = dateFormatHH24MI(item.arrivalDate);
	            	}
	            	var addStyle = 'style="display: none;"';
	            	var activeClass = "";
	            	if(checkServiceId == item.serviceid){
	            		activeClass = "on";
	            		addStyle = 'style="display: block;"';
	            	}
            		html += '<div class="tab_box_content map_movement_status map_movement_status1">'+
				            			'<div class="unex_history" data-mapx="'+item.currentlat+'" data-mapy="'+item.currentlng+'" data-serviceid="'+item.serviceid+'" onclick="fnEmergencyEvent(this);">'+
				    				'<div class="'+curSttsClass+' unex_title '+activeClass+'" data-check-serviceid="'+item.serviceid+'">'+
				    					'<h3>['+curSttsText+'] '+item.evno+' '+item.servicename+' </h3>'+
				    				'</div>'+
				    			'</div>'+
				    			'<div class="'+curSttsClass+' unex_content" '+addStyle+'>'+
				    				'<ul>'+
				    					'<li>['+curSttsText+'] : '+item.evno+'</li>'+
										'<li>위치 : '+item.servicename+' </li>'+
				    					'<li>출발시간 : '+dateFormatHH24MI(item.startDate)+' </li>'+
				    					'<li>도착예측시간 : '+dateFormatHH24MI(item.predictedArrivalDate)+' </li>'+
				    					'<li>예상운행시간 : '+item.arrivaltimeFormat+
				    					'<li>실제도착시간 : '+arrivalDate+'</li>'+
				    					'<li>운행단축시간 : '+item.timeDifference+'</li>'+
				    					'<li>소속기관 : '+item.firename+'</li>'+
				    				'</ul>'+
				    			'</div>'+
				    		'</div>';
            	})
            	$(".unex_list_number").text(totalSize+"건");
            	$("#emergencyDiv").append(html);
            }
        });
	}, 60000); 

});

	function fnEmergencyEvent(_this){
		if ($(_this).parent().find('.unex_title').hasClass('on')) {
			$('.unex_title').removeClass('on').parent().next().slideUp();
		} else {
			$('.unex_title').removeClass('on').parent().next().slideUp();
			$(_this).parent().find('.unex_title').addClass('on').parent().next().slideDown(200);
			
	        var serviceid = $(_this).data("serviceid"); 
	        
	        getEmergencyMoveMap(serviceid);
		}
	}
// 	$('.map_movement_status .unex_history').click(function(){
// 		if ($(this).parent().find('.unex_title').hasClass('on')) {
// 			$('.unex_title').removeClass('on').parent().next().slideUp();
// 		} else {
// 			$('.unex_title').removeClass('on').parent().next().slideUp();
// 			$(this).parent().find('.unex_title').addClass('on').parent().next().slideDown(200);
			
// 	        var serviceid = $(this).data("serviceid"); 
	        
// 	        getEmergencyMoveMap(serviceid);
// 		}
// 	})
	
	
	function getEmergencyMoveMap(serviceid){
		
		const mapUtil = new GitsMapUtil();
		
		$.ajax({
			type : "get",
			data : {"serviceid" : serviceid},
			url : "${pageContext.request.contextPath}/map/monitoring/emergency/detail.ajax",
			success : function(result) {
				
				let monitoringEmergencyBoundBox = {};
				let routeFeatures = [];
				let routeGeoJson = JSON.parse(result.data.routeGeojson);
		        let routeFeaturesByServiceId = [];
		        
		        for(const geometry of routeGeoJson.geometries) {
		            let f = {
		                'type': 'Feature',
		                'properties' : {
		                    "type" : geometry.type
		                },
		                'geometry': geometry
		            };
		            routeFeatures.push(f);
		            routeFeaturesByServiceId.push(f);
		        }
				let arrivalPopup = `<li>${result.data.servicename} 종료지점</li>`;
				let arrivalObj = {
					'type': 'Feature',
					'properties' : {
						'type' : 'loc',
						'description' : arrivalPopup,
						'icon' : "end_icon"
					},
					'geometry': {
						'type': 'Point',
						'coordinates': [result.data.arrivallng, result.data.arrivallat]
					}
				}
				let startObj = {
					'type': 'Feature',
					'properties': {
						'type': 'loc',
						'icon': "emerg_start_icon"
					},
					'geometry': {
						'type': 'Point',
						'coordinates': [result.data.currentlng, result.data.currentlat]
					}
				};
				routeFeaturesByServiceId.push(arrivalObj);
				routeFeatures.push(arrivalObj);
				routeFeaturesByServiceId.push(startObj);
				routeFeatures.push(startObj);
		        monitoringEmergencyBoundBox[result.data.serviceid] = {};
		        const routeFeatureCollectionByServiceId = mapUtil.wrapFeatureCollection(routeFeaturesByServiceId);
		        monitoringEmergencyBoundBox[result.data.serviceid].bbox = mapUtil.getBBOX(routeFeatureCollectionByServiceId.featureCollection);
		        
		        const source = GITS_ENV.LAYER.EMERGENCY+"_TMP";

				if(__Map.getLayer(source)) __Map.removeLayer(source);
				if(__Map.getLayer(source+"_POINT")) __Map.removeLayer(source+"_POINT");
				if(__Map.getLayer(source+"_LOC")) __Map.removeLayer(source+"_LOC");
				if(__Map.getSource(source)) __Map.removeSource(source);

		        let routeRoadLayer = {
	                    'id': source ,
	                    'type': 'line',
	                    'source': source ,
	                    'layout': {
	                        'line-join': 'round',
	                        'line-cap': 'round'
	                    },
	                    'paint': {
	                        'line-color': "#3fbfde",
	                        'line-opacity': 1,
	                        'line-width': 5
	                    },
	                    filter : ["==", 'type', "LineString"]
	                }

	                let pointLayer = {
	                    id: source+"_POINT",
	                    type: 'circle',
	                    source: source ,
	                    paint: {
	                        'circle-color': "transparent",
	                        'circle-radius': 5,
	                        'circle-opacity' : 0.8,
	                        'circle-stroke-color' : "#3fbfde",
	                        'circle-stroke-width' : 2
	                    },
	                    filter : ["==", 'type', "Point"]
	                }

				let arrivalLayer = {
					'id': source+"_LOC",
					'type': 'symbol',
					'source': source,
					'maxzoom': 22,
					'minzoom': 8,
					'layout': {
						'icon-allow-overlap': true,
						'icon-image': ['get', 'icon'],
						"icon-size": [
							'interpolate',
							['linear'],
							['zoom'],
							10, 0.4,
							15, 0.4
						]
					},
					filter : ['has', 'icon']
				}

	                map.control.addExpertSourceAndLayer(source, {}, routeFeatureCollectionByServiceId.featureCollection, [routeRoadLayer,pointLayer,arrivalLayer]);
		        
				__Map.fitBounds(monitoringEmergencyBoundBox[result.data.serviceid].bbox, {padding: 100});
			}
		});	
		
	}
</script>
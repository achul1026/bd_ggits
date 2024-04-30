<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pd16">
	<div class="">
		<div class="dashboard_sub_title">
			- 총 운행중인 버스 대수 : <span class="bus_search_on" id="totalCnt1"><c:out value='${totalCnt}'/></span>
		</div>
	</div>
	<div class="">
	
		<div class="dashboard_sub_title">
	        - 시군구 : 
			<select class="selectBox radius result_change change-detect" id="districtGnm" name="districtGnm">
				<option value="">시군구 전체</option>
				<c:forEach var="sggCd" items="${sggCdList}" varStatus="status">
					<option value="<c:out value='${sggCd.cdNm}'/>"><c:out value='${sggCd.cdNm}'/></option>
				</c:forEach>
			</select>
   		</div>
		<div class="dashboard_sub_title">
	        - 버스 종류 : 
	        <select class="selectBox radius result_change change-detect" id="routeTp" name="routeTp">
	            <option value="">전체</option>
	            <option value="RTC000">시외버스</option>
	            <option value="RTC001">일반버스</option>
	            <option value="RTC002">마을버스</option>
	            <option value="RTC003">광역버스</option>
	            <option value="RTC004">공항버스</option>
	        </select>
   		</div>
		<div class="dashboard_sub_title">
			- 버스번호 :
			<input type="text" class="dashboard_input mr4" id="schRouteNm" placeholder="버스번호를 입력해주세요.">
			<input type="hidden" id="mapPage" name="page" value="1"/>
			<button type="button" class="is-darkgreen-btn mj0" id="searchBtn" data-type="bus">찾기</button>
		</div>
	</div>
	<div class="dashboard_btn_box">
		
	</div>
	<div class="dashboard_node_box">
		<div class="node_title">
			현재 <span id="totalCnt2"></span>대 노선 버스가 이동중 입니다.
		</div>
		<ul id="modalTbody">
			<c:forEach var="list" items="${list}">
				<li class="node_item node_map_bus">
					<button type="button" class="node_button_item" onclick="findBusMoveBtn('<c:out value='${list.routeId}'/>','<c:out value='${list.companyId}'/>','<c:out value='${list.plateNo}'/>');"><img src="/statics/images/list_bus.png">[<c:out value='${list.companyNm}'/>] [<c:out value='${list.routeNm}'/>]<c:out value='${list.plateNo}'/> [<c:out value="${list.routeTpNm}"/>]</button>
				</li>
			</c:forEach>
		</ul>
		<div id="modalPaging">
		   	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
		</div>
	</div>
	
</div>

<script>
	var dataTotalCnt1 = '<c:out value="${totalCnt}"/>';
	var dataTotalCnt2 = '<c:out value="${totalCnt}"/>';
	$("#totalCnt1").text(numberComma(dataTotalCnt1));
	$("#totalCnt2").text(numberComma(dataTotalCnt2));
	
$("#searchBtn").on('click', function(){
	$("#mapPage").val("1");
	fnSearchList();
});

function fnSearchList(){
	$("#modalTbody > li").remove();
	$("#modalPaging > .dashboard-pg-wrap").remove();
	$(".node_title").text("");
	
	
	var page = $("#mapPage").val();
	var schRouteNm = $("#schRouteNm").val();
	var districtGnm = $("#districtGnm").val();
	var routeTp = $("#routeTp").val();

	$.ajax({
		type : "get",
		data : {
			"districtGnm" : districtGnm,
			"routeTp" : routeTp,
			"routeNm" : schRouteNm,
			"page" : page
			},
		url : "${pageContext.request.contextPath}/map/monitoring/bus/M_BUS_001/data.ajax",
		success : function(result) {
			var html = '';
			var title = '';
			var totalCnt = result.data.totalCnt;
			$(".bus_search_on").text(numberComma(totalCnt)+"대");
			
			var routeHtml = "";
			if(totalCnt == 0){
				routeHtml = "현재 0대 노선 버스가 이동중 입니다."
			}else{
				if(schRouteNm != null && schRouteNm != ''){
					routeHtml = '<span id="routeTile">'+schRouteNm+'번</span> 노선 버스가 현재 '+numberComma(totalCnt)+'대 이동중 입니다.';
				}else{
					routeHtml = '현재 <span>'+numberComma(totalCnt)+'대</span> 노선 버스가 이동중 입니다.';
				}
			}
			$(".node_title").html(routeHtml)
			
			$(result.data.list).each(function(index, item){
				html += '<li class="node_item node_map_bus">'+
							'<button type="button" class="node_button_item" onclick="findBusMoveBtn('+item.routeId+',\''+item.companyId+'\',\''+item.plateNo+'\');">'+
								'<img src="/statics/images/list_bus.png">['+item.companyNm+'] ['+item.routeNm+']'+item.plateNo+' ['+item.routeTpNm+']'+
							'</button>'+
						'</li>';
	    	})
			$("#modalTbody").html(html)
			
			$("#schRouteNm").val(schRouteNm);		
			
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				$("#modalPaging").html(getGisPagingHtml(paging,page));
			} 
		}
	});
	
	
}

/*function findBusMoveBtn(x,y,routeId){
	/!*map.control.moveMap([x,y], 18);*!/
	map.control.highlightingTarget(null, parseFloat(x), parseFloat(y));
	window.map.monitoring.getBusRouteInfo(routeId);
}*/

function findBusMoveBtn(routeId,companyId,plateNo){
	/*map.control.moveMap([x,y], 18);*/
	map.monitoring.focusBus(routeId, companyId, plateNo);
	window.map.monitoring.getBusRouteInfo(routeId);
	window.map.monitoring.getBusStationInfo(false, "routeId="+routeId);
}
</script>
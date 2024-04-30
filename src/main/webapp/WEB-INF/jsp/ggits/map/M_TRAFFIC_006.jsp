<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="monitoringModalForm">
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">수집원별</h5>
	<select id="mtraffic006_collector_select" class="selectBox radius result_change change-detect" name="collectType">
		<option value="vds">VDS</option>
		<option value="dsrc">DSRC</option>
		<option value="smc">스마트교차로</option>
	</select>
	<select class="selectBox radius selectTime radius" id="schTime" name="schTime">
		<option value="">시간대 선택</option>
	</select>
</div>
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">시군구</h5>
	<select class="selectBox radius result_change change-detect" <%--onchange="getDongListBySggCd(this.value, '#sggDongSelect')"--%> name="mngInstCd">
		<option value="">시군구 전체</option>
		<c:forEach var="sggCd" items="${sggCdList}" varStatus="status">
			<option value="<c:out value='${sggCd.cdId}'/>"><c:out value='${sggCd.cdNm}'/></option>
		</c:forEach>
	</select>
	<%--<select class="selectBox radius result_change change-detect" id="sggDongSelect" name="dongNm">
		<option value="">전체</option>
	</select>--%>
</div>
<div class="tab_item_box flex-center">
	<h5 id="mtraffic006_road_label" class="tab_item_title">도로명</h5>
	<input type="text" class="dashboard_input radius" id="schRoadName" name="schRoadName" placeholder="도로명을 입력해주세요.">
	<button type="button" class="is-darkgreen-btn ml8" id="searchBtn">찾기</button>
	<input type="hidden" id="mapPage" name="page" value="1"/>
</div>
</form>
<div class="tab_box_body_wrap">
	<table class="monitor_table">
		<colgroup>
			<col width="20%">
			<col width="55%">
			<col width="25%">
		</colgroup>
		<thead>
			<tr>
				<th>순위</th>
				<th id="mtraffic006_road_thead">도로명</th>
				<th>교통량</th>
			</tr>
		</thead>
		<tbody id="modalTbody">
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach var="road" items="${list}" varStatus="status">
					    <tr>
					        <td><c:out value='${road.rnk}'/></td>
					        <td>
								[<c:out value='${road.adsiNm}'/><c:out value='${road.adstdgNm ? " ".concat(road.adstdgNm) : ""}'/>]<br/>
								<c:out value='${road.roadName}'/>
							</td>
					        <td>
								<fmt:formatNumber value="${road.trfVol}" pattern="#,###" />
								대
							</td>
					    </tr>
				    </c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div id="modalPaging">
	   	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
	</div>
</div>


<script>
	dateTiemInit()
	$("#searchBtn").on('click', function(){
		$("#mapPage").val("1");
		fnSearchList();
	});
	$(document).ready(function() {
		$('#schRoadName').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});
	$("select#mtraffic006_collector_select").on("change", function(){
		if($(this).val() === "dsrc") {
			$("#mtraffic006_road_label").text("구간명");
			$("#mtraffic006_road_thead").text("구간명");
		}else{
			$("#mtraffic006_road_label").text("도로명");
			$("#mtraffic006_road_thead").text("도로명");
		}
	});
/*$("#monitoringModalForm select").on("change", function(){
	$("#mapPage").val("1");
	fnSearchList();
})*/
let loadingAjax = null;
function fnSearchList(){
	if(loadingAjax) loadingAjax.abort();
	loadingAjax = $.ajax({
		type : "get",
		data : $("#monitoringModalForm").serialize(),
		url : "${pageContext.request.contextPath}/map/monitoring/traffic/M_TRAFFIC_006/data.ajax",
		beforeSend : function(){
			gitsApp.startLoading();
		},
		error : function(){
			gitsApp.endLoading();
		},
		success : function(result) {
			gitsApp.endLoading();
			$("#modalTbody > tr").remove();
			$("#modalPaging > .dashboard-pg-wrap").remove();
			var html = '';
			var title = '';
			var totalCnt = result.data.totalCnt;
			if(totalCnt > 0){
				$(result.data.list).each(function(index, item){
					html += '<tr>'+
						        '<td>'+item.rnk+'</td>'+
						        '<td>['+item.adsiNm+(item.adstdgNm ? ' '+item.adstdgNm : '')+']<br/>'+item.roadName+'</td>'+
						        '<td>'+numberComma(item.trfVol)+'대</td>'+
						    '</tr>';
		    	})
			}else{
				html += '<tr>'+
							'<td colspan="3">데이터가 없습니다.</td>'+
					    '</tr>';
			}
			$("#modalTbody").html(html)
	    	var paging = result.data.paging;
			if(paging != null && paging != '' && totalCnt > 0){
				$("#modalPaging").html(getGisPagingHtml(paging,$("#mapPage").val()));
			} 
		}
	});
}

</script>
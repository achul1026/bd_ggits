<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<div class="tab_box_body_wrap">
	<div class="">
	    <div class="dashboard_sub_title">DSRC 명으로 찾기</div>
	    <input type="text" class="dashboard_input" id="mapDsrcName" placeholder="도로명을 입력해주세요.">
	    <input type="hidden" id="mapPage" name="page" value="1"/>
	</div>
	<div class="mt16">
	    <div class="dashboard_sub_title">DSRC 번호로 찾기</div>
	    <input type="text" class="dashboard_input" id="mapDsrcId" placeholder="DSRC 번호를 입력해 주세요.">
	</div>
	<div class="dashboard_btn_box">
	    <button type="button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
	</div>
	<div class="dashboard_node_box">
	    <div class="node_title"><div id="totalCnt"><c:out value='${paging.totalCount}'/></div>개의 <span>"DSRC"</span>를 찾았습니다.</div>
		    <ul id="modalTbody">
		    	<c:forEach var="resultList" items="${resultList}">
					<li class="node_item">
						<button type="button" class="node_button_item" onclick="map.control.highlightingTarget(this,'<c:out value='${resultList.lon}'/>','<c:out value='${resultList.lat}'/>');" >
							<img src="/statics/images/facility_icon02.png">[<c:out value='${resultList.name}'/>]<c:out value='${resultList.id}'/>
						</button>
					</li>
				</c:forEach>
		    </ul>
	    <div id="modalPaging">
	    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
		</div>
	</div>
</div>
<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
$(".modal_input_srbtn").on('click', function(){
	$("#mapPage").val("1");
	fnSearchList();
});

function fnSearchList(){
	$("#modalTbody > li").remove();
	$("#modalPaging > .dashboard-pg-wrap").remove();
	$(".tableTitle").empty();
	
	var page = $("#mapPage").val();
	var id = $("#mapDsrcId").val();
	var mapDsrcName = $("#mapDsrcName").val();

	$.ajax({
		type : "get",
		data : {
			"id" : id ,
			"name" : mapDsrcName,
			"page" : page
			},
		url : "${pageContext.request.contextPath}/map/facility/information/F_FACILITY_002/data.ajax",
		success : function(result) {
			var html = '';
			var title = '';
			$(result.data.list).each(function(index, item){
				html += 	'<li class="node_item">';
				html += 		'<button type="button" class="node_button_item" onclick="map.control.highlightingTarget(this,'+item.lon+','+item.lat+');" >['+item.name+']'+item.id+'</button>';
				html += 	'</li>';
	    	})
			$("#modalTbody").append(html)				
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				title += numberComma(paging.totalCount)+'개의 <span>"DSRC"</span>를 찾았습니다.';
				$("#modalPaging").append(getGisPagingHtml(paging,page));
			} 
			$(".tableTitle").append(title);
		}
	});
}
</script>
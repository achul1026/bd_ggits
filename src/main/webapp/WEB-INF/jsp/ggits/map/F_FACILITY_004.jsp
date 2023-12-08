<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<div>
	<div class="mt16">
	    <div class="dashboard_sub_title">도로명으로 찾기</div>
	    <input type="text" class="dashboard_input" id="mapNodeName" placeholder="도로명을 입력해주세요.">
	    <input type="hidden" id="mapPage" name="page" value="1"/>
	</div>
	<div class="mt16">
	    <div class="dashboard_sub_title">교차로 번호로 찾기</div>
	    <input type="text" class="dashboard_input" id="mapIntLcno" placeholder="교차로 번호를 입력해 주세요.">
	</div>
	<div class="dashboard_btn_box">
	    <button type="button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
	</div>
	<div class="dashboard_node_box">
	    <div class="node_title">${paging.totalCount}개의 <span>"신호정보"</span>를 찾았습니다.</div>
	    <div id="modalTbody">
		    <ul>
		    	<c:forEach var="resultList" items="${resultList}">
					<li class="node_item">
						<button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,'${resultList.nodeId}',${resultList.lon},${resultList.lat});"data-marker-html='
						<ul>
							<li class="popup_item">교차로명 : <span>${resultList.name}</span></li>
							<li class="popup_item">경도 : <span>${resultList.lon}</span></li>
							<li class="popup_item">위도 : <span>${resultList.lat}</span></li>
						</ul>
						'>${resultList.id}</button>
					</li>
				</c:forEach>
		    </ul>
	    </div>
	    <div id="modalPaging">
	    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
		</div>
	</div>
</div>
<script>
$(".modal_input_srbtn").on('click', function(){
	$("#mapPage").val("1");
	fnSearchList();
});

function fnSearchList(){
	$("#modalTbody > ul").remove();
	$("#modalPaging > .dashboard-pg-wrap").remove();
	$(".tableTitle").empty();
	
	var page = $("#mapPage").val();
	var id = $("#mapIntLcno").val();
	var nodeName = $("#mapNodeName").val();

	$.ajax({
		type : "get",
		data : {
			"id" : id ,
			"nodeName" : nodeName,
			"page" : page
			},
		url : "${pageContext.request.contextPath}/map/facility/information/F_FACILITY_004/data.ajax",
		success : function(result) {
			var html = '';
			var title = '';
			$(result.data.list).each(function(index, item){
				var detailHtml = 	'<ul>'+
										'<li class=\'popup_item\'>교차로명 : <span>'+item.name+'</span></li>'+
										'<li class=\'popup_item\'>경도 : <span>'+item.lon+'</span></li>'+
										'<li class=\'popup_item\'>위도 : <span>'+item.lat+'</span></li>'+
									'</ul>';
				html += '<ul>';
				html += 	'<li class="node_item">';
				html += 		'<button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,\''+item.nodeId+'\','+item.lon+','+item.lat+');" data-marker-html="'+detailHtml+'">'+item.id+'</button>';
				html += 	'</li>';
				html +=	'</ul>';
	    	})
			$("#modalTbody").append(html)				
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				title += paging.totalCount+'개의 <span>"신호정보"</span>를 찾았습니다.';
				$("#modalPaging").append(getGisPagingHtml(paging,page));
			} 
			$(".tableTitle").append(title);
		}
	});
}
</script>
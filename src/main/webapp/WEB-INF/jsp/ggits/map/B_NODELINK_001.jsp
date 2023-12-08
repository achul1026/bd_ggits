<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div class="mt16">
	<div class="dashboard_sub_title">도로명으로 찾기</div>
	<input type="text" id="map-modal-nodeName" name="nodeName" class="dashboard_input" placeholder="도로명을 입력해주세요.">
	<input type="hidden" id="modalPage" name="page" value="1"/>
</div>
<div class="mt16">
	<div class="dashboard_sub_title">노드 번호로 찾기</div>
	<input type="text" id="map-modal-nodeId" name="nodeId" class="dashboard_input" placeholder="노드 번호를 입력해 주세요.">
</div>
<div class="dashboard_btn_box">
	<button type="button" id="map-node-search-button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
</div>
<div id="nodeSearchResult" class="dashboard_node_box">
	<div class="node_title">${paging.totalCount}개의 <span>"노드"</span>를 찾았습니다.</div>
	<div id="modalTbody">
		<c:forEach var="nodeList" items="${nodeList}">
			<ul>
				<li class="node_item"><button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,'${nodeList.nodeId}',${nodeList.x},${nodeList.y});">${nodeList.nodeName}[${nodeList.nodeId}]</button></li>
			</ul>
		</c:forEach>
	</div>
	<div id="modalPaging">
    	<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
	</div>
</div>
<script>

	$(".modal_input_srbtn").on('click', function(){
		fnSearchList();
	});
	
	function fnSearchList(){
		$("#modalTbody > ul").remove();
		$("#modalPaging > .dashboard-pg-wrap").remove();
		$(".node_title").empty();
		
		var page = $("#modalPage").val();
		var nodeId = $("#map-modal-nodeId").val();
		var nodeName = $("#map-modal-nodeName").val();

		$.ajax({
			type : "get",
			data : {
				"nodeId" : nodeId ,
				"nodeName" : nodeName,
				"page" : page
				},
			url : "${pageContext.request.contextPath}/map/basicinfo/node/search/list.ajax",
			success : function(result) {
				var html = '';
				var title = '';
				$(result.data.list).each(function(index, item){
					html += '<ul>';
					html += '<li class="node_item"><button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,\''+item.nodeId+'\','+item.x+','+item.y+');">'+item.nodeName+'['+item.nodeId+']</button></li>';
					html +=	'</ul>';
		    	})
				$("#modalTbody").append(html)				
		    	var paging = result.data.paging;
				if(paging != null && paging != ''){
					title += paging.totalCount+'개의 <span>"노드"</span>를 찾았습니다.';
					$("#modalPaging").append(getGisPagingHtml(paging,page));
				} 
				$(".node_title").append(title);
			}
		});
	}
</script>
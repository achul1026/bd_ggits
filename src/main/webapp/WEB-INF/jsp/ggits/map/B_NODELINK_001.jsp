<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="tab_box_body_wrap">
	<div class="">
		<div class="dashboard_sub_title">교차로명으로 찾기</div>
		<input type="text" id="map-modal-nodeName" name="nodeName" class="dashboard_input wd100 radius-none" placeholder="교차로명을 입력해주세요.">
		<input type="hidden" id="modalPage" name="page" value="1"/>
	</div>
	<div class="mt16">
		<div class="dashboard_sub_title">노드 번호로 찾기</div>
		<input type="text" id="map-modal-nodeId" name="nodeId" class="dashboard_input wd100 radius-none" placeholder="노드 번호를 입력해 주세요.">
	</div>
	<div class="dashboard_btn_box mt16">
		<button type="button" id="map-node-search-button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
	</div>
	<div id="nodeSearchResult" class="dashboard_node_box">
		<div class="node_title"><div id="totalCnt"><c:out value='${paging.totalCount}'/></div>개의 <span>"노드"</span>를 찾았습니다.</div>
			<ul id="modalTbody">
				<c:forEach var="nodeList" items="${nodeList}">
					<li class="node_item">
						<button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,'<c:out value='${nodeList.nodeId}'/>','<c:out value='${nodeList.x}'/>','<c:out value='${nodeList.y}'/>');">
							<img src="/statics/images/node_icon01.png"><c:out value='${nodeList.nodeName}'/>[<c:out value='${nodeList.nodeId}'/>]
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
		$("#modalPage").val("1");
		fnSearchList();
	});
	
	function fnSearchList(){
		
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
				$("#modalTbody > li").remove();
				$("#modalPaging > .dashboard-pg-wrap").remove();
				$(".node_title").empty();
				
				var html = '';
				var title = '';
				$(result.data.list).each(function(index, item){
					html += '<li class="node_item">';
					html += 	'<button type="button" class="node_button_item" onclick="map.control.highlightingByNodeId(this,\''+item.nodeId+'\','+item.x+','+item.y+');">';
					html +=			'<img src="/statics/images/node_icon01.png">'+item.nodeName+'['+item.nodeId+']';
					html +=		'</button>';
					html +=	'</li>';
		    	})
				$("#modalTbody").append(html)				
		    	var paging = result.data.paging;
				if(paging != null && paging != ''){
					title += numberComma(paging.totalCount)+'개의 <span>"노드"</span>를 찾았습니다.';
					$("#modalPaging").append(getGisPagingHtml(paging,page));
				} 
				$(".node_title").append(title);
			}
		});
	}
</script>
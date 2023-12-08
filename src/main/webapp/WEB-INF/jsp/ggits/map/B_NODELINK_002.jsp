<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="mt16">
	<div class="dashboard_sub_title">링크 번호로 찾기</div>
	<input type="text" id="map-modal-linkId" name="linkId" class="dashboard_input" placeholder="링크 번호를 입력해 주세요.">
	<input type="hidden" id="modalPage" name="page" value="1"/>
</div>
<div class="dashboard_btn_box">
	<button type="button" id="map-link-search-button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
</div>
<div id="nodeSearchResult" class="dashboard_node_box">
	<div class="node_title">${paging.totalCount}개의 <span>"노드"</span>를 찾았습니다.</div>
	<div id="modalTbody">
		<c:forEach var="linkList" items="${linkList}">
			<ul>
				<li class="node_item"><button type="button" class="node_button_item" onclick="map.control.highlightingByLinkId(this,'${linkList.linkId}',${linkList.x},${linkList.y});">${linkList.roadName}[${linkList.linkId}]</button></li>
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
	var linkId = $("#map-modal-linkId").val();

	$.ajax({
		type : "get",
		data : {
			"linkId" : linkId ,
			"page" : page
			},
		url : "${pageContext.request.contextPath}/map/basicinfo/link/search/list.ajax",
		success : function(result) {
			var html = '';
			var title = '';
			$(result.data.list).each(function(index, item){
				html += '<ul>';
				html += '<li class="node_item"><button type="button" class="node_button_item" onclick="map.control.highlightingByLinkId(this,\''+item.linkId+'\','+item.x+','+item.y+');">'+item.roadName+'['+item.linkId+']</button></li>';
				html +=	'</ul>';
	    	})
			$("#modalTbody").append(html)				
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				title += paging.totalCount+'개의 <span>"링크"</span>를 찾았습니다.';
				$("#modalPaging").append(getGisPagingHtml(paging,page));
			} 
			$(".node_title").append(title);
		}
	});
}
</script>
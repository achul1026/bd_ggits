<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="tab_box_body_wrap">
	<div class="">
		<div class="dashboard_sub_title">도로명으로 찾기</div>
		<input type="text" id="roadName" name="roadName" class="dashboard_input" placeholder="도로명을 입력해 주세요.">
		<input type="hidden" id="modalPage" name="page" value="1"/>
	</div>
	<div class="dashboard_btn_box">
		<button type="button" id="map-link-search-button" class="is-darkgreen-btn modal_input_srbtn">찾기</button>
	</div>
	<div id="nodeSearchResult" class="dashboard_node_box">
		<div class="node_title"><div id="totalCnt"><c:out value="${paging.totalCount}"/></div>개의 <span>"링크"</span>를 찾았습니다.</div>
			<ul id="modalTbody">
				<c:forEach var="linkList" items="${linkList}">
				<li class="node_item">
					<button type="button" class="node_button_item" onclick="map.control.highlightingByLinkId(this,'<c:out value='${linkList.linkId}'/>','<c:out value='${linkList.x}'/>','<c:out value='${linkList.y}'/>');">
						<img src="/statics/images/node_icon02.png"><c:out value='${linkList.roadName}'/>[<c:out value='${linkList.linkId}'/>]
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
	var roadName = $("#roadName").val();

	$.ajax({
		type : "get",
		data : {
			"roadName" : roadName ,
			"page" : page
			},
		url : "${pageContext.request.contextPath}/map/basicinfo/link/search/list.ajax",
		success : function(result) {
			$("#modalTbody > li").remove();
			$("#modalPaging > .dashboard-pg-wrap").remove();
			$(".node_title").empty();
			
			var html = '';
			var title = '';
			$(result.data.list).each(function(index, item){
				html += '<li class="node_item">';
				html +=		'<button type="button" class="node_button_item" onclick="map.control.highlightingByLinkId(this,\''+item.linkId+'\','+item.x+','+item.y+');">';
				html +=			'<img src="/statics/images/node_icon02.png">'+item.roadName+'['+item.linkId+']';
				html +=		'</button>';
				html +=	'</li>';
	    	})
			$("#modalTbody").append(html)				
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				title += numberComma(paging.totalCount)+'개의 <span>"링크"</span>를 찾았습니다.';
				$("#modalPaging").append(getGisPagingHtml(paging,page));
			} 
			$(".node_title").append(title);
		}
	});
}
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_box_content">
<!-- 	<div class="mt16"> -->
<!-- 	    <label class="dashboard_sub_title">지역명으로 찾기</label> -->
<!-- 	    <input type="text" class="dashboard_input" placeholder="지역명을 입력해주세요."> -->
<!-- 	</div> -->
	<div class="mt16 mb16">
	    <label class="dashboard_sub_title">도로명으로 찾기</label>
	    <input type="text" class="dashboard_input" id="searchInput" placeholder="도로명을 입력해주세요.">
	</div>
	<div class="dashboard_btn_box mj0">
		<input type="hidden" id="nodeId" />
		<input type="hidden" id="lon" />
		<input type="hidden" id="lat" />
	    <button type="button" class="is-dark-btn" id="searchBtn">찾기</button>
	</div>
</div>
<script>
$(function() {
	//데이터 가공해서 불러오기
	var nodeList = '${nodeList}';
	if(nodeList != null && nodeList != ''){
		nodeList = JSON.parse('${nodeList}'); 
	}
	
	$("#searchInput").autocomplete({
		source: nodeList,	
		focus : function(event, ui) { 	
			return false;
		},
		select: function(event, ui) {
			$("#nodeId").val(ui.item.nodeId)
			$("#lon").val(ui.item.lon)
			$("#lat").val(ui.item.lat)
			$("#searchBtn").removeClass("is-dark-btn");
			$("#searchBtn").addClass("is-darkgreen-btn");
        },
		minLength: 1,
		delay: 100,
	});
	
	$("#searchBtn").on("click",function(){
		const nodeId = $("#nodeId").val();
		if(nodeId == null && nodeId == '') return;
		const lon = $("#lon").val();
		const lat = $("#lat").val();
		map.control.highlightingByNodeId(this,nodeId,lon,lat);
		$("#nodeId").val("");
		$("#lon").val("");
		$("#lat").val("");
	})
	
	$("#searchInput").on("change",function(){
		var searchBtn = $("#searchBtn"); 
		if(searchBtn.hasClass("is-darkgreen-btn")){
			searchBtn.addClass("is-dark-btn");
			searchBtn.removeClass("is-darkgreen-btn");
		}else{
			$("#nodeId").val("");
			$("#lon").val("");
			$("#lat").val("");
		}
	})
});
</script>
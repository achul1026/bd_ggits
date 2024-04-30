<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab_box_body_wrap">
	<div class="tab_box_chart">
		<div id="m_warning_chart_wrap" class="tab_box_chart_content has-preloading">
			<canvas id="m_warning_chart" width="400" height="150"></canvas>
		</div>
	</div>
</div>
<script>
	$(function(){

		window.map.monitoring.getWarningInfoToday().then(function(list){

		});

	})
</script>
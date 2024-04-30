<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_body_wrap">
    <div class="tab_box_body_title">
    	- 장애발생 : <span id="totalCnt"></span> 건
    </div>
    
    <div class="">
    	<c:forEach var="mapMonitoringLinkDataList" items="${mapMonitoringLinkDataList}">
			<div class="tab_box_content">
				<div class="unex_history" onclick="fnEmergencyEvent(this);">
					<div class="red link_title">
						<div class="flex-between">
							<ul class="flex-center gap8">
								<li>[<c:out value='${mapMonitoringLinkDataList.jobNm}'/>]</li>
								<li class="link_error_text"><c:out value='${mapMonitoringLinkDataList.errorMsg}'/></li>
							</ul>
							<ul class="flex-center gap8">
								<li><c:out value='${mapMonitoringLinkDataList.renewTime}'/></li>
								<li class="link_arrow"><img src="${pageContext.request.contextPath}/statics/images/wh_arrow_down.png" alt="화살표"></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="red unex_content" style="width:400px;">
					<ul>
						<li>데이터 : [<c:out value='${mapMonitoringLinkDataList.jobNm}'/>]</li>
						<li class="word-break">에러내용 : <c:out value='${mapMonitoringLinkDataList.errorMsg}'/></li>
						<li>에러시간 : <c:out value='${mapMonitoringLinkDataList.renewTime}'/></li>
					</ul>
				</div>
			</div>				
    	</c:forEach>
	</div>
</div>
<script>
	var dataTotalCnt = '<c:out value="${fn:length(mapMonitoringLinkDataList) ne 0 ? mapMonitoringLinkDataList.get(0).errorTotalCnt : 0 }"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))

	$('.dash_check_box').on('change', function(){
		$(this).toggleClass('is-darkgreen-btn');
	})
	$('.dash_check_box_all').on('click', function(){
		if($('.dash_check_box').hasClass('is-darkgreen-btn')){
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', false);
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').removeClass('is-darkgreen-btn');
			$(this).removeClass('is-darkgreen-btn');
		} else {
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', true);
			$(this).siblings().addClass('is-darkgreen-btn');
			$(this).addClass('is-darkgreen-btn')
		}
	})
	
	function fnEmergencyEvent(_this){
		if ($(_this).parent().find('.link_title').hasClass('on')) {
			$('.link_title').removeClass('on').parent().next().slideUp();
			$('.link_arrow').css('transform', 'rotate(0deg)');
		} else {
			$('.link_title').removeClass('on').parent().next().slideUp();
			$(_this).parent().find('.link_title').addClass('on').parent().next().slideDown(200);
			$('.link_arrow').css('transform', 'rotate(0deg)');
			$(_this).parent().find('.link_arrow').css('transform', 'rotate(180deg)')
		}
	}
	
</script> 
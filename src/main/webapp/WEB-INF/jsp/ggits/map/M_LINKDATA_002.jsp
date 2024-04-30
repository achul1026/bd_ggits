<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_body_wrap">
    <div class="tab_box_body_title">
    	- 전체수집완료 : <span class="numberData" data-value="<c:out value='${fn:length(mapMonitoringLinkDataList) ne 0 ? mapMonitoringLinkDataList.get(0).dataTotalCnt : 0 }'/>"></span> 건
    </div>
    
    <div class="tab_box_body_table_wrap">
    	<c:forEach var="mapMonitoringLinkDataList" items="${mapMonitoringLinkDataList}">
			<table class="monitor_table link_data_table">
				<colgroup>
					<col width="">
					<col width="">
				</colgroup>
				<thead>
					<tr>
						<th colspan="3" class="link_table_title">
							<c:out value='${mapMonitoringLinkDataList.jobNm}'/>
						</th>
					</tr>
					<tr>
						<th>수집건수</th>
						<th>최근갱신</th>
					</tr>
				</thead>
				<tbody>
				    <tr>
				        <td class="numberData" data-value="<c:out value='${mapMonitoringLinkDataList.dataCnt}'/>"></td>
				        <td><c:out value='${mapMonitoringLinkDataList.renewTime}'/></td>
				    </tr>
				 
				</tbody>
			</table>
    	</c:forEach>
		
	</div>
</div>
<script>
	
	$(document).ready(function(){
		var numberData = $(".numberData");
		
		for(var i = 0; i < numberData.length; i++){
			var number = Number(numberData.eq(i).data('value'));
			numberData.eq(i).text(numberComma(number));
		}
	});

	// 선택 데이터별 수집 이력
	$('.dash_check_box').on('change', function(){
		$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', false);
		$(this).find('input').prop('checked', true);
		$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').removeClass('is-darkgreen-btn');
		$(this).addClass('is-darkgreen-btn');
	})
</script> 
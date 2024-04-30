<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_body_wrap">
    <div class="tab_box_body_title">
    	- 전체수집완료/장애발생 : 
    		<span class="numberData" data-value="<c:out value='${fn:length(mapMonitoringLinkDataList) ne 0 ? mapMonitoringLinkDataList.get(0).dataTotalCnt : 0 }'/>"></span>
    		건 / 
    		<span class="numberData" data-value="<c:out value='${fn:length(mapMonitoringLinkDataList) ne 0 ? mapMonitoringLinkDataList.get(0).errorTotalCnt : 0 }'/>"></span>
    		건
    </div>
    
    <div class="tab_box_body_table_wrap">
    	<c:forEach var="mapMonitoringLinkDataList" items="${mapMonitoringLinkDataList}">
			<table class="monitor_table link_data_table">
				<colgroup>
					<col width="">
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
						<th>오류건수</th>
						<th>최근갱신</th>
					</tr>
				</thead>
				<tbody>
				    <tr>
				        <td class="numberData" data-value="<c:out value='${mapMonitoringLinkDataList.dataCnt}'/>"></td>
				        <td class="numberData" data-value="<c:out value='${mapMonitoringLinkDataList.errorCnt}'/>"></td>
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

	
	
	
	$('.dash_check_box').on('click', function(){
		$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', false);
		$(this).find('input').prop('checked', true);
	})

</script>
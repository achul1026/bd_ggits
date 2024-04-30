<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통 DB화 통계</h3>
                    <div class="side_txt">
                        교통 DB화 통계 정보를 <br>조회합니다.
                    </div>
                    <div class="side_btn">
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do"  class="on" onclick="startLoading()">교통영향평가</a>
                    </div>
                </div>
            </aside>
            <section class="main_section">
            	<c:import url="/WEB-INF/jsp/ggits/statistics/searchTrfImpact.jsp" />           
                <div class="search_container mt8">
                	<div class="search_table_width">
		        		<div class="flex">
							<div class="row1 flex">
								<div class="flex">
									<div class="table_middle table_title table_line4">구분</div>
									<!-- box for start -->
									<c:forEach begin="0" end="19" step="1" varStatus="status">
										<div class="flex-column">
											<div class="table_column1200 table_title">용도<c:out value='${status.index+1}'/></div>
											<div class="flex">
												<div class="table_small table_title">용도명</div>	
												<div class="table_column1100 table_title usgNmTrgt" id="usgNm<c:out value='${status.index+1}'/>">(작성하세요)</div>	
											</div>
											<div class="flex">
												<div class="table_column600 table_title">활동인구(인)</div>
												<div class="table_column600 table_title">활동인구 비율(%)</div>
											</div>
											<div class="flex">
												<div class="flex">
													<div class="table_column200 table_title">상주/상근 (단위:인)</div>
													<div class="table_column200 table_title">방문/이용 (단위:인)</div>
													<div class="table_column200 table_title">합계 (단위:인)</div>
												</div>
												<div class="flex">
													<div class="table_column200 table_title">상주/상근 (단위:%)</div>
													<div class="table_column200 table_title">방문/이용 (단위:%)</div>
													<div class="table_column200 table_title">합계 (단위:%)</div>
												</div>
											</div>
											<div class="flex">
												<div class="flex">
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
												</div>
												<div class="flex">
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
													<div class="table_small table_title">유입</div>
													<div class="table_small table_title">유출</div>
												</div>
											</div>					
										</div>
									</c:forEach>
									<!-- box for end -->
								</div>
							</div>
		        		</div>
		        		<!-- time value for start -->
		        		<c:choose>
		        			<c:when test="${fn:length(impactReportList) >0}">
		        				<c:forEach var="impactInfo" items="${impactReportList}">
									<div class="row_value flex max-content">     		
		        					<div class="table_middle table_txt"><c:out value="${impactInfo.timeSctnNm}"/></div>
		        					<c:set var="dataLength" value="${fn:length(impactInfo.trfvlmStatisticsList)}"/>
		        					<!-- value start -->
		        						<ul class="flex">
		        						<c:forEach var="impactDetail" items="${impactInfo.trfvlmStatisticsList}">
			        						<li class="table_small table_txt"><c:out value='${impactDetail.resdngInfl}'/></li>
			        						<li class="table_small table_txt"><c:out value='${impactDetail.resdngOutfl}'/></li>
			        						<li class="table_small table_txt"><c:out value='${impactDetail.visitInfl}'/></li>
			        						<li class="table_small table_txt"><c:out value='${impactDetail.visitOutfl}'/></li>
			        						<li class="table_small table_txt"><c:out value='${impactDetail.totInfl}'/></li>
			        						<li class="table_small table_txt"><c:out value='${impactDetail.totOutfl}'/></li>
		        						</c:forEach>
		        						</ul>
		        					<!-- value end -->
		        					<c:if test="${40 - dataLength > 0}">
										<c:set var="endPoint" value="${40 - dataLength}"/>
										<c:forEach begin="0" end="${endPoint-1}" step="1" varStatus="status">
											<ul class="flex">
												<!-- value for start -->
												<li class="table_small table_txt">-</li>
												<li class="table_small table_txt">-</li>
												<li class="table_small table_txt">-</li>
												<li class="table_small table_txt">-</li>
												<li class="table_small table_txt">-</li>
												<li class="table_small table_txt">-</li>
												<!-- value for start -->
											</ul>
										</c:forEach>
									</c:if>
		        					</div>
		        				</c:forEach>
		        			</c:when>
		        			<c:otherwise></c:otherwise>
		        		</c:choose>
		        		
		        		<div class="row_value flex max-content">
							<div class="table_middle table_title">합계</div>
							<ul class="flex">
								<c:forEach var="unitInfo" items="${unitSumList}">
									<!-- value start -->
									<li class="table_small table_title"><c:out value='${unitInfo.resdngInfl}'/></li>
									<li class="table_small table_title"><c:out value='${unitInfo.resdngOutfl}'/></li>
									<li class="table_small table_title"><c:out value='${unitInfo.visitInfl}'/></li>
									<li class="table_small table_title"><c:out value='${unitInfo.visitOutfl}'/></li>
									<li class="table_small table_title"><c:out value='${unitInfo.totInfl}'/></li>
									<li class="table_small table_title"><c:out value='${unitInfo.totOutfl}'/></li>
									<!-- value end -->
								</c:forEach>
								<c:if test="${40 - dataLength > 0}">
										<c:set var="endPoint" value="${40 - dataLength}"/>
										<c:forEach begin="0" end="${endPoint-1}" step="1" varStatus="status">
											<ul class="flex">
												<!-- value for start -->
												<li class="table_small table_title">-</li>
												<li class="table_small table_title">-</li>
												<li class="table_small table_title">-</li>
												<li class="table_small table_title">-</li>
												<li class="table_small table_title">-</li>
												<li class="table_small table_title">-</li>
												<!-- value for start -->
											</ul>
										</c:forEach>
									</c:if>
							</ul>
						</div>
						<!-- time value for end -->
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">시간대별 유출입 통행</div>
	                        	<div style="height:380px">
	                        		<canvas id="inflow_outflow_chart"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>    	            	
            </section> 
        </div>



	<script>
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})

	$(document).ready(function() {
		$('#selectBox').change(function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo
	  	}); 

		var selectedType = $("#selectedType").val();
		$('#selectBox').val(selectedType).prop("selected",true);
		var cdNmArr = '<c:out value="${cdNmArr}"/>';
		cdNmArr = cdNmArr.replaceAll("[","").replaceAll("]","").replaceAll("\"","");
		if(!cdNmArr.includes(",")){
			cdNmArr = cdNmArr+",";
		}
		cdNmArr = cdNmArr.split(","); 			
		
		$(".usgNmTrgt").each(function(index, item){
			if(!isNull(cdNmArr[index]) && cdNmArr[index] != ''){
				$(this).text(cdNmArr[index]);
			}
		})
		
	});
	
	$('#upload_btn').on('click', function(){
		var type = $('#selectBox option:selected').val();
		var ipcssMngNo = $("#ipcssMngNo").val();
        new ModalBuilder().init('교통영향평가 수정').ajaxBody("${pageContext.request.contextPath}/common/modal/csvupdate/list.do?type="+type+"&ipcssMngNo="+ipcssMngNo).footer(1,'수정',function(button, modal){
       		// validation 체크
        	$.ajax({
       			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/"+type+"/update.ajax?ipcssMngNo="+ipcssMngNo,
       			type : "POST",
       			data : new FormData($("#frmAttachedFiles")[0]),
       			dataType: "json",
       			processData: false,
       			contentType: false,
       			beforeSend : function(){
	    			startLoading();
	    		},
       			success: function(data) {
       				if(data.code == 200){
						new ModalBuilder().init().successBody("교통영향평가를 수정하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
						}).open();
						modalAlertWrap();						
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
       			},
       			error: function(error){
       				new ModalBuilder().init().alertBoby("교통영향평가 수정에 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();	
       			},
	    		 complete : function(){
	    			 impactEndLoading();
	             }
       		});
        }).open();
       	$('.modal_footer').removeClass('none');
    });
	
	// 시간대별 유출입 통행
	var resdngInflRtDataArray = '<c:out value="${resdngInflRtDataArray}"/>';
	var resdngOutflRtDataArray = '<c:out value="${resdngOutflRtDataArray}"/>';
	var visitInflRtDataArray = '<c:out value="${visitInflRtDataArray}"/>';
	var visitOutflRtDataArray = '<c:out value="${visitOutflRtDataArray}"/>';
	var timeDataArray = '<c:out value="${timeDataArray}"/>';
    new GITSChart(GITSChartType.BAR).init("inflow_outflow_chart")
    .setData({
    labels: timeDataArray.split(','),
    datasets: [{
		    	label:'상주/상근 유입',
		        data: resdngInflRtDataArray.split(','),
		        backgroundColor:'#00bcb1'
    		},{
    			label:'방문/이용 유입',
    	        data: visitInflRtDataArray.split(','),
    	        backgroundColor:'#ad49fb'
    		},{
    			label:'상주/상근 유출',
    	        data: resdngOutflRtDataArray.split(','),
    	        backgroundColor:'#91de6c'
    		},{
    			label:'방문/이용 유출',
    	        data: visitOutflRtDataArray.split(','),
    	        backgroundColor:'#ff5454'
    		}]
    })
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
    </script>

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
				<!-- tab5 -->
                <div class="search_container mt8">
                	<div class="search_table_width">
                		<c:choose>
		        		<c:when test="${fn:length(impactReportList) > 0}">
		        		<c:forEach var="impactInfo" items="${impactReportList}" varStatus="status">
		        		<div class="flex">
			        		<!-- left -->
							<div class="flex-column">
								<c:if test="${status.index == 0}">
								<div class="flex">
									<div class="table_small table_title table_line6">번호</div>
									<div class="table_column300 table_title table_line6">유사시설 명칭</div>
									<div class="table_column400 table_title table_line6">유사시설 주소</div>
									<div class="table_column200 table_title table_line6">규모(층수/동수)</div>
									<div class="table_column200 table_title table_line6">연면적(㎡)</div>
									<div class="table_column200 table_title table_line6">조사구분(현장/문헌)</div>
									<div class="table_column200 table_title table_line6">현장조사 일자(요일)</div>
									<div class="table_column500 table_title table_line6">문헌조사(참고문헌)</div>
								</div>
								</c:if>
								<!-- 번호 ~ 문헌조사 2depth for start -->
								<div class="flex">
									<div class="table_small table_txt"><c:out value="${status.index+1}"/></div>
									<div class="table_column300 table_txt"><c:out value="${impactInfo.smlfactNm}"/></div>
									<div class="table_column400 table_txt"><c:out value="${impactInfo.smlfactAddr}"/></div>
									<div class="table_column200 table_txt"><c:out value="${impactInfo.scale}"/></div>
									<div class="table_column200 table_txt"><c:out value="${impactInfo.totfrar}"/></div>
									<div class="table_column200 table_txt"><c:out value="${impactInfo.exmnDivNm}"/></div>
									<div class="table_column200 table_txt"><c:out value="${impactInfo.exmnYmd}"/></div>
									<div class="table_column500 table_txt"><c:out value="${impactInfo.exmnDocNm}"/></div>
									<c:set var="ipcssResultList" value="${impactInfo.ipcssResultList}"/>
								</div>
								<!-- 번호 ~ 문헌조사 2depth for end -->
							</div>
							<!-- right -->
							<div>
								<div>
									<div class="flex">
										<c:if test="${status.index == 0}">
										<!-- 용도 for start -->
										<c:forEach begin="0" end="7" step="1" varStatus="status">
										<div class="flex-column">
											<div class="table_column2100 table_title">용도<c:out value="${status.index+1}"/></div>
											<div class="flex">
												<div class="table_small table_title table_line3">용도명칭</div>
												<div class="flex-column">
													<div class="table_column1500 table_title">상주</div>
													<div class="flex">
														<div class="flex">
															<div class="flex-column">
																<div class="table_column500 table_title">통근</div>
																<div class="flex">
																	<div class="table_small table_title">승용차</div>
																	<div class="table_small table_title">택시</div>
																	<div class="table_small table_title">버스</div>
																	<div class="table_small table_title">전철/지하철</div>
																	<div class="table_small table_title">도보 및 기타</div>
																</div>
															</div>
														</div>	
														<div class="flex">
															<div class="flex-column">
																<div class="table_column500 table_title">통학</div>
																<div class="flex">
																	<div class="table_small table_title">승용차</div>
																	<div class="table_small table_title">택시</div>
																	<div class="table_small table_title">버스</div>
																	<div class="table_small table_title">전철/지하철</div>
																	<div class="table_small table_title">도보 및 기타</div>
																</div>
															</div>
														</div>	
														<div class="flex">
															<div class="flex-column">
																<div class="table_column500 table_title">기타</div>
																<div class="flex">
																	<div class="table_small table_title">승용차</div>
																	<div class="table_small table_title">택시</div>
																	<div class="table_small table_title">버스</div>
																	<div class="table_small table_title">전철/지하철</div>
																	<div class="table_small table_title">도보 및 기타</div>
																</div>
															</div>
														</div>	
													</div>
												</div>
												<div class="flex-column">
													<div class="table_column500 table_title table_line7">방문</div>
													<div class="flex">
														<div class="table_small table_title">승용차</div>
														<div class="table_small table_title">택시</div>
														<div class="table_small table_title">버스</div>
														<div class="table_small table_title">전철/지하철</div>
														<div class="table_small table_title">도보 및 기타</div>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
										<!-- 용도 for end -->
										</c:if>
									</div>
									<div class="row_value flex max-content">
										<c:if test="${fn:length(ipcssResultList) > 0}">
										<c:set var="detailLength" value="${fn:length(ipcssResultList)}"/>
										<c:forEach var="impactDetail" items="${ipcssResultList}" varStatus="status">
										<ul class="flex">
											<c:if test="${status.index == 0 || status.index % 20 == 0}">
												<li class="table_small table_txt"><c:out value="${impactDetail.cdNm}"/></li>
											</c:if>
											<li class="table_small table_txt"><c:out value="${impactDetail.shareRt}"/></li>
											<!-- value for start -->
										</ul>
										</c:forEach>
										</c:if>
										<c:if test="${8 - (detailLength/20) > 0}">
											<c:set var="endPoint" value="${8 - (detailLength/20)}"/>
												<c:forEach begin="0" end="${endPoint-1}" step="1" varStatus="status">
													<ul class="flex">
														<!-- value for start -->
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
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
								</div>
							</div>
		        		</div>
		        		</c:forEach>
		        		</c:when>
						<c:otherwise>
						</c:otherwise>
		        		</c:choose>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통근</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통학</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart2"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">기타</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart3"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">이용</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart4"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            	   	            	
            </section> 
        </div>
        
	<script>
	//<![CDATA[
		var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	//]]>
	$("#totalCnt").text(numberComma(dataTotalCnt))	
	
	/* 검색결과 */
	// id name 바꿔서 사용하세요~
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
	});

   	// 용도별 교통수단 분담률 > 통근
	var meanShareRtArr1 = '<c:out value="${meanShareRtArr1}"/>';
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart1")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:meanShareRtArr1.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
   	
   	// 용도별 교통수단 분담률 > 통학
	var meanShareRtArr2 = '<c:out value="${meanShareRtArr2}"/>';
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart2")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:meanShareRtArr2.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
   	
	// 용도별 교통수단 분담률 > 기타
	var meanShareRtArr3 = '<c:out value="${meanShareRtArr3}"/>';
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart3")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:meanShareRtArr3.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
   	
	// 용도별 교통수단 분담률 > 방문
	var meanShareRtArr4 = '<c:out value="${meanShareRtArr4}"/>';
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart4")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:meanShareRtArr4.split(','),
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
	
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
    </script>

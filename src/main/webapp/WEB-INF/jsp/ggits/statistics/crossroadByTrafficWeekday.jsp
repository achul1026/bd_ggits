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
<!-- tab1 -->
        <div class="search_container tab1">
        	<div class="search_table_width">
        		<!-- 전체 -->
        		<c:forEach var="impactReportList" items="${impactReportList}">
	        		<div class="flex">
		        		<!-- left -->
						<div>
							<div class="flex">
								<div class="table_middle table_title">번호</div>
								<div class="table_middle table_title">교차로명칭</div>
								<div class="table_middle table_title">위도</div>
								<div class="table_middle table_title">경도</div>
							</div>
							<div class="flex table_left_height" style="height:64.7rem">
								<div class="table_middle table_txt table_line"><c:out value='${impactReportList.dataNo}'/></div>
								<div class="table_middle table_txt table_line"><c:out value='${impactReportList.name}'/></div>
								<div class="table_middle table_txt table_line"><c:out value='${impactReportList.lat}'/></div>
								<div class="table_middle table_txt table_line"><c:out value='${impactReportList.lon}'/></div>
							</div>
						</div>
						<!-- right -->
						<div>
							<div>
								<div class="row1 flex">
									<div class="flex">
										<div class="table_middle table_title">교차로방향</div>
										
										<!-- 교차로명 숫자 -->
										<div class="flex">
											<c:forEach var="headerNo" items="${impactReportList.headerList}">
												<div class="table_big table_title"><c:out value='${headerNo}'/></div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="row2 flex">
									<div class="table_middle table_title table_line">시간대</div>
									
									<!-- 타입 -->
									<c:forEach var="headerNo" items="${impactReportList.headerList}">
										<div class="flex">
											<div class="table_small table_title table_line">승용차</div>
											<div class="flex-column">
												<div class="table_column200 table_title">버스</div>
												<ul class="flex">
													<li class="table_small table_title">소형</li>
													<li class="table_small table_title">대형</li>
												</ul>
											</div>
											<div class="flex-column">
												<div class="table_column300 table_title">화물</div>
												<ul class="flex">
													<li class="table_small table_title">소형</li>
													<li class="table_small table_title">중형</li>
													<li class="table_small table_title">대형</li>
												</ul>
											</div>
											<div class="table_small table_title table_line">합계(대)</div>
										</div>
									</c:forEach>
								</div>
								<c:forEach var="timeTrfvlmResultList" items="${impactReportList.timeTrfvlmResultList}">
									<div class="row_value flex max-content">
										<div class="table_middle ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}""><c:out value='${timeTrfvlmResultList.timeSctnNm}'/></div>
										
										<ul class="flex" id="dataUl">
											<c:forEach var="timeTrfvlmDataList" items="${timeTrfvlmResultList.timeTrfvlmDataList}">
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="psgvhclTrfvlm" value="<c:out value='${timeTrfvlmDataList.psgvhclTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="busTrfvlm" value="<c:out value='${timeTrfvlmDataList.busTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="busLrgszTrfvlm" value="<c:out value='${timeTrfvlmDataList.busLrgszTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="frghtSmlszTrfvlm" value="<c:out value='${timeTrfvlmDataList.frghtSmlszTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="frghtMdmszTrfvlm" value="<c:out value='${timeTrfvlmDataList.frghtMdmszTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'input_statistics_item' : 'input_statistics_total_item'}" name="frghtLrgszTrfvlm" value="<c:out value='${timeTrfvlmDataList.frghtLrgszTrfvlm}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" readonly="readonly">
												</li>
												<li class="table_small ${fn:contains(timeTrfvlmResultList.timeSctnNm, '~') ? 'table_txt' : 'table_title'}">
													<input type="text" class="statistics_data_input input_statistics_total_item" name="totalCnt" value="<c:out value='${timeTrfvlmDataList.totalCnt}'/>" data-crsrd-no="<c:out value='${impactReportList.dataNo}'/>" data-crsrd-drct-cd="<c:out value='${timeTrfvlmDataList.crsrDdrctCd}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>" data-time="<c:out value='${timeTrfvlmResultList.timeSctnNm}'/>">
												</li>
											</c:forEach>			
										</ul>
									</div>
								</c:forEach>
							</div>
						</div>
	        		</div>
        		</c:forEach>
			</div>
			<div class="table_chart_wrap">
				<div class="table_chart_list">
					<c:forEach var="impactReportGraphList" items="${impactReportGraphList}"> 
						<div class="chart chart_height">
							<div class="tab_box_title left mb16">교차로 방향 (<c:out value='${impactReportGraphList.crsrdDrctCd}'/>)</div>
		                 	<div id="chart<c:out value='${impactReportGraphList.crsrdDrctCd}'/>"></div>
						</div>
					</c:forEach>
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
		$('#searchCrsrdNm').change(function() {
			var type = $('#selectBox option:selected').val();
			var dataNo = $('#searchCrsrdNm option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo+"&dataNo="+dataNo
	  	}); 
		
		var selectedType = $("#selectedType").val();
		$('#selectBox').val(selectedType).prop("selected",true);
	}); 
	
 	$(function(){
	 	var impactReportGraphList = '<c:out value="${impactReportGraphListJson}"/>';
 		if(impactReportGraphList != null && impactReportGraphList != ''){
 			impactReportGraphList = JSON.parse('${impactReportGraphListJson}'); 
 		}
 		$(impactReportGraphList).each(function(idx,item){
 			var graphIdx = Number(item.crsrdDrctCd);
 			var data = [{
	            type: "sunburst",
		            ids: [
	                    "root",
	                    "am", "pm",
	                    "am07", "am08", "pm17", "pm18",
	                    "am00Child1", "am00Child2", "am00Child3", "am00Child4", "am00Child5", "am00Child6", 
	                    "am06Child1", "am06Child2", "am06Child3", "am06Child4", "am06Child5", "am06Child6", 
	                    "pm12Child1", "pm12Child2", "pm12Child3", "pm12Child4", "pm12Child5", "pm12Child6", 
	                    "pm18Child1", "pm18Child2", "pm18Child3", "pm18Child4", "pm18Child5", "pm18Child6", 
					],
					labels:["", "오전", "오후", "07:00~08:00", "08:00~09:00", "17:00~18:00", "18:00~19:00",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물", 
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물"
					         ],
					parents:["", "root", "root", "am", "am", "pm", "pm",
	                            "am07", "am07", "am07", "am07", "am07", "am07", 
	                            "am08", "am08", "am08", "am08", "am08", "am08", 
	                            "pm17", "pm17", "pm17", "pm17", "pm17", "pm17", 
	                            "pm18", "pm18", "pm18", "pm18", "pm18", "pm18", 
					          ],
					values: item.allDataList.split(','),
					leaf: {"opacity": 0.9},
					marker: {
						"line": {"width": 2},
						 colors: ["", "#1f77b4", "#ff7f0e"]
					},
	                branchvalues: 'total',
	                insidetextfont: {
	                	color:'#fff',
	                	family: 'Pretendard'
	                },
	                hoverlabel: {
	                	font:{color:'#fff'},
	                	bordercolor:'#fff'
	                }
				}];
		
				var layout = {
				  margin: {"l": 0, "r": 0, "b": 0, "t": 5}
				};

	            Plotly.newPlot('chart'+graphIdx, data, layout, {showSendToCloud: true, displayModeBar:false})
	            myPlot = document.getElementById("chart"+graphIdx);	
 		})
			
	})
	
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

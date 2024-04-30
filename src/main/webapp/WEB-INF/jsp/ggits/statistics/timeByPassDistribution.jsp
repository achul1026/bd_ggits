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
									<div class="table_middle table_title table_line3">주거</div>
									<div class="flex-column">
										<div class="table_column600 table_title">주거 용도 - 상주</div>
										<div class="flex">
											<div class="table_column200 table_title">통근</div>	
											<div class="table_column200 table_title">통학</div>	
											<div class="table_column200 table_title">기타</div>	
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
									<div class="flex-column">
										<div class="table_column200 table_title table_line2">방문</div>
										<div class="flex">
											<div class="table_small table_title">유입</div>
											<div class="table_small table_title">유출</div>
										</div>					
									</div>
									
									<!-- 비주거용도 for start -->
									<c:forEach begin="0" end="19" step="1" varStatus="status">
										<div class="flex-column">
											<div class="table_column500 table_title">비주거 용도<c:out value='${status.index+1}'/></div>
											<div class="flex">
												<div class="flex-column">
													<div class="table_title table_small">용도명칭</div>
													<div class="table_title table_small usgNmTrgt">(작성하세요)</div>
												</div>
												<div class="flex-column">
													<div class="table_column200 table_title">상근</div>
													<div class="flex">
														<div class="table_title table_small">유입</div>
														<div class="table_title table_small">유출</div>
													</div>
												</div>
												<div class="flex-column">
													<div class="table_column200 table_title">이용</div>
													<div class="flex">
														<div class="table_title table_small">유입</div>
														<div class="table_title table_small">유출</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
									<!-- 비주거용도 for end -->
								</div>
							</div>
		        		</div>
		        		<!-- row start -->
		        		<c:choose>
		        			<c:when test="${fn:length(impactReportList) > 0}">
		        				<c:forEach var="impactInfo" items="${impactReportList}">
			        				<div class="row_value flex max-content">
										<div class="table_middle table_txt"><c:out value='${impactInfo.timeSctnNm}'/></div>
										<ul class="flex">
											<c:set var="dataLength" value="${fn:length(impactInfo.trfvlmStatisticsList)}"/>
											<c:forEach var="impactDeatil" items="${impactInfo.trfvlmStatisticsList}" varStatus="status">
												<!-- column start -->
												<c:if test="${status.index >= 0 && status.index <= 3}">
													<li class="table_small table_txt"><c:out value='${impactDeatil.inflRt}'/></li>
													<li class="table_small table_txt"><c:out value='${impactDeatil.outflRt}'/></li>												
												</c:if>
												<c:if test="${status.index >= 4}">
													<c:if test="${status.index % 2 == 0}">
														<li class="table_small table_txt"><c:out value='${impactInfo.timeSctnNm}'/></li>
													</c:if>
													<li class="table_small table_txt"><c:out value='${impactDeatil.inflRt}'/></li>
													<li class="table_small table_txt"><c:out value='${impactDeatil.outflRt}'/></li>
												</c:if>
												<!-- value end -->											
											</c:forEach>
											<c:if test="${25 - dataLength > 0}">
												<c:set var="endPoint" value="${25 - dataLength}"/>
												<c:forEach begin="0" end="${endPoint-1}" step="1" varStatus="status">
													<ul class="flex">
<!-- 														value for start -->
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
														<li class="table_small table_txt">-</li>
<!-- 														value for start -->
													</ul>
												</c:forEach>
											</c:if>
										</ul>
									</div>
		        				</c:forEach>
		        			</c:when>
		        		</c:choose>
       					
						<!-- time value for end -->
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">시간대별 통행</div>
	                        	<div style="height:380px">
	                        		<canvas id="passage_chart"></canvas>
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
		$('.search_head').removeClass('none');
	})

	$(document).ready(function() {
		var searchFilter = $(`
        <div class="group2">
        	<div class="group_text2">요일 구분</div>
				<div class="chngDywkDiv">
					<input type="hidden" id="dywkDiv" value="<c:out value='${dywkDiv}'/>">
					<select class="selectBox" name="dywkDivSelectBox" id="dywkDivSelectBox">
						<option value="평일">평일</option>
						<option value="주말">주말</option>
					</select>
				</div>      
    	</div>		
	`)
	$('.contents_wrap').append(searchFilter);
		
		$('#selectBox').change(function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo
	  	}); 

		var selectedType = $("#selectedType").val();
		$('#selectBox').val(selectedType).prop("selected",true);
		
		$('.chngDywkDiv').on('change', '#dywkDivSelectBox', function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
			var dywkDiv = $('#dywkDivSelectBox option:selected').val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo+"&dywkDiv="+dywkDiv;
		});
		
		var dywkDiv = $("#dywkDiv").val();
		$('#dywkDivSelectBox').val(dywkDiv).prop("selected",true);
		
		var cdNmArr = '<c:out value="${cdNmArr}"/>';
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
	
	// 시간대별 통행
	var timeDataArray = '<c:out value="${timeDataArray}"/>';
	var inflRt1 = '<c:out value="${inflRt1}"/>';
	var outflRt1 = '<c:out value="${outflRt1}"/>';
	var inflRt2 = '<c:out value="${inflRt2}"/>';
	var outflRt2 = '<c:out value="${outflRt2}"/>';
	var inflRt3 = '<c:out value="${inflRt3}"/>';
	var outflRt3 = '<c:out value="${outflRt3}"/>';
	var inflRt4 = '<c:out value="${inflRt4}"/>';
	var outflRt4 = '<c:out value="${outflRt4}"/>';
	var inflRt5 = '<c:out value="${inflRt5}"/>';
	var outflRt5 = '<c:out value="${outflRt5}"/>';
	var inflRt6 = '<c:out value="${inflRt6}"/>';
	var outflRt6 = '<c:out value="${outflRt6}"/>';

    new GITSChart(GITSChartType.BAR).init("passage_chart")
    .setData({
 	labels: timeDataArray.split(','),
    datasets: [{
		    	label:'통근유입',
		        data: inflRt1.split(','),
		        backgroundColor:'#00bcb1'
    		},{
    			label:'통근유출',
    	        data: outflRt1.split(','),
    	        backgroundColor:'#ad49fb'
    		},{
    			label:'통학유입',
    	        data: inflRt2.split(','),
    	        backgroundColor:'#91de6c'			
    		},{
    			label:'통학유출',
    	        data: outflRt2.split(','),
    	        backgroundColor:'#ff5454'	
    		},{
    			label:'기타유입',
   		        data: inflRt3.split(','),
   		        backgroundColor:'#65abdd'
    		},{
    			label:'기타유출',
    	        data: outflRt3.split(','),
    	        backgroundColor:'#c38686'
    		},{
    			label:'방문유입',
    	        data: inflRt4.split(','),
    	        backgroundColor:'#e983ce'
    		},{
    			label:'방문유출',
    	        data: outflRt4.split(','),
    	        backgroundColor:'#c9cb5a'
    		},{
    			label:'상근유입',
    	        data: inflRt5.split(','),
    	        backgroundColor:'red'
    		},{
    			label:'상근유출',
    	        data: outflRt5.split(','),
    	        backgroundColor:'yellow'
    		},{
    			label:'이용유입',
    	        data: inflRt6.split(','),
    	        backgroundColor:'blue'
    		},{
    			label:'이용유출',
    	        data: outflRt6.split(','),
    	        backgroundColor:'green'
    		}]
    })
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
    </script>

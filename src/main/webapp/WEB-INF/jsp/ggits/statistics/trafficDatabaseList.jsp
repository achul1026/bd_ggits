<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통DB화 통계</h3>
                    <div class="side_txt">
                        교통DB화 통계정보를 <br>조회합니다.
                    </div>
                    <div class="side_btn">
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do" onclick="startLoading()">교통영향평가</a>
                    </div>
                </div>
            </aside>
            <section class="main_section">
                <form id="searchForm" method="get">
                <input type="hidden" id="page" name="page"  value="1"/>
                <h2 class="blind">통계 리포트</h2>
                <div class="contents_wrap">
                    <div class="group2 pt0">
                        <div class="group_text2">주소 검색</div>
                        <div class="btn_search_wrap">
                        	<ul>
                        		<li>
                        			시군 : 
                        			 <select class="selectBox" name="sigunCdId" id="sigunCdId">
	                                    <option value="searchAllLocation">전체</option>
										<c:forEach var="sggCdList" items="${sggCdList}">
		                					<option value="<c:out value='${sggCdList.cdId}'/>" <c:if test ="${sigunCdId eq sggCdList.cdId}">selected="selected"</c:if>><c:out value='${sggCdList.cdNm}'/></option>
										</c:forEach>
	                                </select>
                        		</li>
                        		<li>
                        			검색 : 
									<input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="도로명을 입력해주세요." value="<c:out value='${searchContent}'/>">
                        		</li>
                        	</ul>

                        </div>
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="btn_search_wrap">
	                               	<ul>
	                               		<li>
	                               			<input type="text" class="date_picker input_same input_picker" name="strDt" id="strDt"  placeholder="날짜를 선택해주세요." autocomplete="off">
	                               		</li>
	                               		<li>
	                               			 ~
	                               		</li>
	                               		<li>
	                               			  <input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
	                               		</li>
	                               	</ul>  
	                            </div>
	                        </div>   
                    	</div>
                    	<div class="btn_search_wrap btn_search_wrap_center">
                    		<ul>
                    			<li>
                    				<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
                    			</li>
                    			<li>
                    				<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
                    			</li>
                    		</ul>
                    	</div>
                </div>
                <div class="search_container">
				    <div class="search_head">
				        <div class="search_number">
				            <span id="totalCnt">"<c:out value='${totalCnt}'/>"</span>개의 검색결과를 찾았습니다.
				        </div>
				    </div>
                    <table class="mt16">
                        <colgroup>
                           
                        </colgroup>
						<thead>
	                        <tr>
				                <th scope="col">도로명</th>
				                <th scope="col">시군</th>
				                <th scope="col">누적 교통량(대)</th>
				                <th scope="col">평균 교통량(대)</th>
				                <th scope="col">평균속도(km/h)</th>                    
				            </tr>
						</thead>
						<tbody>
	                       <c:choose>
				          		<c:when test="${fn:length(linkStdList) > 0}">
				          			<c:forEach var="linkStd" items="${linkStdList}">
				          				<tr>
				          					<td><c:out value='${linkStd.roadName}'/></td>
				          					<td><c:out value='${linkStd.adsiNm}'/></td>
					          				<td>
					          					<fmt:formatNumber value="${linkStd.sumVol}" pattern="#,###.##"/>
					          				</td>
					          				<td>
					          					<fmt:formatNumber value="${linkStd.avgVol}" pattern="#,###.##"/>
					          				</td>
					          				<td><c:out value='${linkStd.avgSpd}'/></td>
					          			</tr>
				          			</c:forEach>
				          		</c:when>
				          		<c:otherwise>
				          			<tr>
				          				<td colspan="5">검색된 내용이 없습니다.</td>
				          			</tr>
				          		</c:otherwise>
				          	</c:choose>
                        </tbody>
                    </table>
                    <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
                    
                  
                    <div class="table_chart_wrap date_table_chart">
                    	  <div class="btn_search_wrap">
	                       	<ul>
	                       		<li>
	                       			기간 설정 : <input type="text" class="date_picker input_same input_picker" name="chartStrDt" id="chartStrDt"  placeholder="날짜를 선택해주세요." value="<c:out value='${chartStrDt}'/>" autocomplete="off">
	                       		</li>
	                       		<li>
	                       			 ~
	                       		</li>
	                       		<li>
	                       			  <input type="text" class="end_date_picker input_same input_picker" name="chartEndDt" id="chartEndDt" placeholder="날짜를 선택해주세요." value="<c:out value='${chartEndDt}'/>" autocomplete="off">
	                       		</li>
								<li>
                    				<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnChartSearchList();">찾기</button>
                    			</li>
	                       	</ul>  
	                    </div>
	                    <div class="loadingPart none">
							<span></span>
							<span></span>
							<span></span>
	                    </div>
	                    <div class="table_chart_list none">
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">평균교통량</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">평균속도</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart2"></canvas>
	                        	</div>
	                        </div>
<!-- 	                        <div class="chart"> -->
<!-- 	                        	<div class="tab_box_title left mb16">혼잡레벨</div> -->
<!-- 	                        	<div style="height:380px"> -->
<%-- 	                        		<canvas id="traffic_chart3"></canvas> --%>
<!-- 	                        	</div> -->
<!-- 	                        </div> -->
	                     </div>
                     </div>
            	</div>
            	</form>
            </section> 
        </div>



	<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
		
	$(document).ready(function(){
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var strDt = '<c:out value="${strDt}"/>';
		var endDt = '<c:out value="${endDt}"/>';
	
		//searchOption dataInit
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
		}
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		
		// 통계 데이터 비동기 조회
		var chartForm = $("#searchForm").serializeObject();
		$.ajax({
   			url : "${pageContext.request.contextPath}/statistics/traffic/database/list.ajax",
   			type : "POST",
   			data : JSON.stringify(chartForm),
			contentType: "application/json; charset=UTF-8",
   			beforeSend : function(){
   				$(".loadingPart").removeClass("none");
    		},
   			success: function(result) {
   				if(result.code == 200){	
   					$(".table_chart_list").removeClass("none");
   					/* chart */
   				   	var sigunColorEx = ['#FF6666', '#FF8B66', '#FF9D66', '#FFB966', '#FFD466', '#FFF066', '#F3FF66', '#C5FF66', '#8EFF66', '#66FF75', '#66FF9A', '#66FFBF', '#66FFF6', '#66E3FF', '#66B6FF', '#669AFF', '#6688FF', '#6675FF', '#666CFF', '#7B66FF', '#9766FF', '#A966FF', '#BC66FF', '#D766FF', '#F366FF', '#FF66D4', '#FF66B9', '#FF668B', '#FF778F', '#C9C9C9', '#FFFFFF'];
   				   	var volSigunNmArr = result.data.volSigunNmArr;
   				   	var volDataArr = result.data.volDataArr;
   				   	if(!isNull(volSigunNmArr) && !isNull(volDataArr)){
	   				   	// 교통량
	   				    new GITSChart(GITSChartType.BAR).init("traffic_chart1")
	   				  	.setData({
	   				 		labels: volSigunNmArr.split(','),
	   				        datasets: [{
	   					        	label : '교통량',
	   					        	data : volDataArr.split(','),
	   					        	backgroundColor : sigunColorEx,
	   					        	borderRadius:2
	   				       }]
	   				   	})
	   				    .setTicksStep(100)
	   				    .setBarGridY(true)
	   				    .setAxisStackedX(false)
	   				    .setLabelDisplay(false)
	   				    .draw();
   				   	}
   				   		
   				  	// 평균속도
   				  	var spdSigunNmArr = result.data.spdSigunNmArr;
   					var spdDataArr = result.data.spdDataArr
   					if(!isNull(spdSigunNmArr) && !isNull(spdDataArr)){
	   				  	new GITSChart(GITSChartType.BAR).init("traffic_chart2")
	   				  	.setData({
	   				   			labels: spdSigunNmArr.split(','),
	   					        datasets: [{
	   					        	label:'평균속도',
	   					        	data:spdDataArr.split(','),
	   					         	backgroundColor:sigunColorEx,
	   					            borderRadius:2,
	   					            fill: false,
	   					        }]
	   				    	})
	   				   	.setTicksStep(10)
	   				    .setBarGridY(true)
	   				    .setAxisStackedX(false)
	   				    .setLabelDisplay(false)
	   				   	.draw();
   					}

   				   	// 혼잡레벨
//    				   	var congGradeSigunNmArr = result.data.congGradeSigunNmArr;
//    				   	var congGradeDataArr = result.data.congGradeDataArr;
// 	   				if(!isNull(congGradeSigunNmArr) && !isNull(congGradeDataArr)){
// 	   				    new GITSChart(GITSChartType.BAR).init("traffic_chart3")
// 	   				     .setData({
// 	   				   			labels: congGradeSigunNmArr.split(','),
// 	   					        datasets: [{
// 	   					        	label : '혼잡레벨',
// 	   					        	data:congGradeDataArr.split(','),
// 	   					        	backgroundColor:sigunColorEx,
// 	   					            borderRadius:2,
// 	   					            fill: false,
// 	   					        }]
// 	   				    	})
// 	   				    .setTicksStep(1)
// 	   				    .setBarGridY(true)
// 	   				    .setAxisStackedX(false)
// 	   				    .setLabelDisplay(false)
// 	   				    .draw(); 
// 	   				}
				}
   			},
     		 complete : function(){
    			 $(".loadingPart").addClass("none");
             }
   		});
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/database/list.do";
		document.getElementById('searchForm').submit();
 	} 
	
	function fnChartSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/database/list.do";
		document.getElementById('searchForm').submit();
 	} 
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
    </script>

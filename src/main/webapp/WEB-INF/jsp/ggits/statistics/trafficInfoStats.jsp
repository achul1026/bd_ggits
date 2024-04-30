<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통정보 통계분석</h3>
	        <div class="side_txt">
	            교통정보 통계분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" class="on" onclick="startLoading()">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?initAppchYn=Y" onclick="startLoading()">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" onclick="startLoading()">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" onclick="startLoading()">도로안전</a>
<%-- 	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do" onclick="startLoading()">교통시설물 통계</a> --%>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap btn_search_wrap_left">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do'" >교통현황 통계</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">지역별 대기질 통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">광역 긴급차량 이동 현황통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page"  value="1"/>
	        		<input type="hidden" id="totalCntChk" name="totalCntChk" value="${totalCnt}">
		            <div class="group2">
							<div class="group_text2">주소</div>
							<div class="btn_search_wrap">
								<ul>
									<li>
										시군 : 
										<select class="selectBox" name="sigunCdId" id="sigunCdId">
											<option value="searchAllLocation">전체</option>
											<c:forEach var="sggCdList" items="${sggCdList}">
												<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
											</c:forEach>
										</select>
									</li>
									<li>
										검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="도로명을 입력해주세요.">
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
                              			<input type="text" class="date_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                              		</li>
                              		<li>
                              			~
                              		</li>
                              		<li>
                              			<input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요."autocomplete="off">
                              		</li>
                              	</ul> 
                            </div>
                        </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="btn_search_wrap">
                            		<input type="hidden" name="dayOfTheWeekStr" id="dayOfTheWeekStr" value="<c:out value='${searchOption.dayOfTheWeekStr}'/>">
                                <ul>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="7">일</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="1">월</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="2">화</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="3">수</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="4">목</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="5">금</label>
                                	</li>
                                	<li>
                                		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="6">토</label>
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
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span><fmt:formatNumber value="${totalCnt}" pattern="#,###" />개</span>의 검색결과를 찾았습니다.
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <colgroup>
	                   
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="">도로명</th>
		                    <th scope="col">평균 교통량(대)</th>
		                    <th scope="col">평균속도(km/h)</th>
		                    <th scope="col">최고속도(km/h)</th>
		                    <th scope="col">최저속도(km/h)</th>
		                    <th scope="col">도로유형</th>
		                </tr>
	                </thead>
	                <tbody>
		                <c:choose>
		                	<c:when test="${fn:length(statsList) > 0 }">
		                		<c:forEach var="statsInfo" items="${statsList}">
		                			<tr>
		                				<td class=""><c:out value="${statsInfo.roadName}"/></td>
		                				<td>
		                					<fmt:formatNumber value="${statsInfo.avgVol}" pattern="#,###"/>
		                				</td>
		                				<td><c:out value="${statsInfo.avgSpd}"/></td>
		                				<td><c:out value="${statsInfo.maxSpd}"/></td>
		                				<td><c:out value="${statsInfo.minSpd}"/></td>
		                				<td><c:out value="${statsInfo.roadRank}"/></td>
		                			</tr>
		                		</c:forEach>
		                	</c:when>
		                	<c:otherwise>
		                		<tr>
		                			<td colspan="6">현황 정보가 존재하지 않습니다.</td>
		                		</tr>
		                	</c:otherwise>
		                </c:choose>
	                </tbody>
	            </table>
	            <div>
	                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            </div>
	    
	            <div class="table_chart_wrap">
	                <div class="table_chart_list">
	                    <div class="chart chart_table">
	                        <div class="chart_table_title">
	                            <span class="traffic_b">최고 교통량</span> 도로 순위
	                        </div>
	                        <div class="table_chart_box">
		                        <table>
		                            <colgroup>
		                                <col style="width:25%">
		                                <col style="width:50%">
		                                <col style="width:25%">
		                            </colgroup>
		                            <thead>
			                            <tr>
			                                <th scope="col">순위</th>
			                                <th scope="col">도로명</th>
			                                <th scope="col">교통량(대)</th>
			                            </tr>
		                            </thead>
									<tbody id="maxTrafficValPart">
			                           <tr>
			                           		<td colspan="3">데이터를 조회중입니다.</td>
			                           </tr>
									</tbody>	    
		                        </table>
	                        </div>
	                    </div>
	                    <div class="chart chart_table">
	                        <div class="chart_table_title">
	                            <span class="traffic_g">최저 교통량</span> 도로 순위
	                        </div>
	                        <div class="table_chart_box">
		                        <table>
		                            <colgroup>
		                                <col style="width:25%">
		                                <col style="width:50%">
		                                <col style="width:25%">
		                            </colgroup>
		                            <thead>
			                            <tr>
			                                <th scope="col">순위</th>
			                                <th scope="col">도로명</th>
			                                <th scope="col">교통량(대)</th>
			                            </tr>
		                            </thead>
									<tbody id="minTrafficValPart">
			                           <tr>
			                           		<td colspan="3">데이터를 조회중입니다.</td>
			                           </tr>
									</tbody>	    
		                        </table>
	                        </div>
	                    </div>
	                    <div class="chart chart_table">
	                        <div class="chart_table_title">
	                            <span class="traffic_b">소통 정체</span> 도로 순위
	                        </div>
	                        <div class="table_chart_box">
		                        <table>
		                            <colgroup>
		                                <col style="width:25%">
		                                <col style="width:50%">
		                                <col style="width:25%">
		                            </colgroup>
		                            <thead>
			                            <tr>
			                                <th scope="col">순위</th>
			                                <th scope="col">도로명</th>
			                                <th scope="col">평균속도(km/h)</th>
			                            </tr>
		                            </thead>
									<tbody id="smoothTrafficPart">
			                          	<tr>
			                          		<td colspan="3">데이터를 조회중입니다.</td>
			                          	</tr>
									</tbody>	    
		                        </table>
	                        </div>
	                    </div>
	                    <div class="chart chart_table">
	                        <div class="chart_table_title">
	                            <span class="traffic_g">소통 원활</span> 도로 순위
	                        </div>
	                        <div class="table_chart_box">
		                        <table>
		                            <colgroup>
		                                <col style="width:25%">
		                                <col style="width:50%">
		                                <col style="width:25%">
		                            </colgroup>
		                            <thead>
			                            <tr>
			                                <th scope="col">순위</th>
			                                <th scope="col">도로명</th>
			                                <th scope="col">평균속도(km/h)</th>
			                            </tr>
		                            </thead>
									<tbody id="jamTrafficPart">
			                            <tr>
			                            	<td colspan="3">데이터를 조회중입니다.</td>
			                            </tr>
									</tbody>	    
		                        </table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
        </div>
	</section>
</div>
<script>
	$(document).ready(function(){
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		var searchContent = '<c:out value="${searchOption.searchContent}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';

		//searchOption dataInit
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
		}
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
		}
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(!isNull(searchContent)){
			$("#searchContent").val(searchContent);
		}
		if(dayOfTheWeekStr != null && dayOfTheWeekStr != ''){
			var dayOfWeek = $(".dayOfWeek")
			if(dayOfTheWeekStr.indexOf(",")){
				var dayOfTheWeekStrArr = dayOfTheWeekStr.split(",");
				for(var i = 0; i < dayOfTheWeekStrArr.length; i++){
					for(var j = 0; j < dayOfWeek.length; j++){
						if(dayOfTheWeekStrArr[i] == dayOfWeek.eq(j).val()){
							dayOfWeek.eq(j).prop("checked",true);
							dayOfWeek.eq(j).parent('label').addClass("is-darkgreen-btn");
						}
					}
				}
			} else {
				for(var i = 0; i < dayOfWeek.length; i++){
					if(dayOfTheWeekStr == dayOfWeek.eq(i).val()){
						dayOfWeek.eq(i).prop("checked",true);
						dayOfWeek.eq(i).parent('label').addClass("is-darkgreen-btn");
					}
				}
			}
		}
		
		var listForm = $("#searchForm").serialize();
   		$.ajax({
   			type : "post",
   			url : "${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.ajax",
   			data : listForm,
   			success: function(result) {
   				$("#maxTrafficValPart tr").remove();
   				$("#minTrafficValPart tr").remove();
   				$("#smoothTrafficPart tr").remove();
   				$("#jamTrafficPart tr").remove();
   				
   				var maxTrafficValList = result.data.maxTrafficValList;
   		 	   	var minTrafficValList = result.data.minTrafficValList;
   		 	   	var smoothTrafficList = result.data.smoothTrafficList;
   		 	   	var jamTrafficList = result.data.jamTrafficList;
   		 	   	
   		 	   	if(!isNull(maxTrafficValList)){
   		 	   		var maxTrafficValHtml = '';
   		 	   		
	   		 	   	$(maxTrafficValList).each(function(index, item){
	   		 	   		maxTrafficValHtml += '<tr>';
	   		 			maxTrafficValHtml += '		<td>' + (index + 1) + '</td>';
	   		 			maxTrafficValHtml += '		<td>' + item.roadName + '</td>';
	   					maxTrafficValHtml += '		<td>' + item.avgVol + '</td>';
	   					maxTrafficValHtml += '</tr>';
	   		 	   	})
   		 	   		
   		 	   		$("#maxTrafficValPart").append(maxTrafficValHtml);
   		 	   	}else{
   		 	   		var maxTrafficValHtml = '';
   		 	   		maxTrafficValHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
   		 	   		$("#maxTrafficValPart").append(maxTrafficValHtml);
   		 	   	}
   		 	   	
	   		 	if(!isNull(minTrafficValList)){
	   		 		var minTrafficValHtml = '';
	   		 		
	   		 		$(minTrafficValList).each(function(index, item){
	       				minTrafficValHtml += '<tr>';
	       				minTrafficValHtml += '		<td>' + (index + 1) + '</td>';
	       				minTrafficValHtml += '		<td>' + item.roadName + '</td>';
	       				minTrafficValHtml += '		<td>' + item.avgVol + '</td>';
	       				minTrafficValHtml += '</tr>';
	   		 		})
	   		 		
	   		 		$("#minTrafficValPart").append(minTrafficValHtml);
   		 	   	}else{
   		 	   		var minTrafficValHtml = '';
   		 	   		minTrafficValHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
   		 	   		$("#minTrafficValPart").append(minTrafficValHtml);
   		 	   	}
	   		 	
	   		 	if(!isNull(smoothTrafficList)){
	   		 		var smoothTrafficHtml = '';
	   		 		
	   		 		$(smoothTrafficList).each(function(index, item){
	       				smoothTrafficHtml += '<tr>';
	       				smoothTrafficHtml += '		<td>' + (index + 1) + '</td>';
	       				smoothTrafficHtml += '		<td>' + item.roadName + '</td>';
	       				smoothTrafficHtml += '		<td>' + item.avgSpd + '</td>';
	       				smoothTrafficHtml += '</tr>';
		   		 	})
	   		 		
	   		 		$("#smoothTrafficPart").append(smoothTrafficHtml);
		 	   	}else{
		 	   		var smoothTrafficHtml = '';
		 	   		smoothTrafficHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
		 	   		$("#smoothTrafficPart").append(smoothTrafficHtml);
		 	   	}
	   		 
	   			if(!isNull(jamTrafficList)){
   		 			var jamTrafficHtml = '';
   		 		
	   		 		$(jamTrafficList).each(function(index, item){
	   		 			jamTrafficHtml += '<tr>';
	   		 			jamTrafficHtml += '		<td>' + (index + 1) + '</td>';
	   		 			jamTrafficHtml += '		<td>' + item.roadName + '</td>';
	   		 			jamTrafficHtml += '		<td>' + item.avgSpd + '</td>';
	   		 			jamTrafficHtml += '</tr>';
	   		 		})
   		 		
   		 			$("#jamTrafficPart").append(jamTrafficHtml);
		 	   	}else{
		 	   		var jamTrafficHtml = '';
		 	   		jamTrafficHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
		 	   		$("#jamTrafficPart").append(jamTrafficHtml);
		 	   	}
   			}
   		});
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
</script>
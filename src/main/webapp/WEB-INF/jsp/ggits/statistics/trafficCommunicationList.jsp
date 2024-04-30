<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통 정보 통계 분석</h3>
	        <div class="side_txt">
	            교통 정보 통계 분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" onclick="startLoading()">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?initAppchYn=Y" class="on" onclick="startLoading()">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" onclick="startLoading()">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" onclick="startLoading()">도로안전</a>
<%-- 	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do" onclick="startLoading()">교통시설물 통계</a> --%>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <form id="searchForm" method="get">
	    	<input type="hidden" name="page" id="page" value="1">
	    	<input type="hidden" name="totalCntChk" id="totalCntChk" value="${totalCnt}">
		    <div class="group_btn_wrap tab_fc">
		        <div class="table_btn_left">
		        	<input type="hidden" id="tabNum" name="tabNum" value="${tabNum}">
		            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4'}">is-darkgreen-btn</c:if>" data-index="1">소통정보</button>
		            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq '5' or tabNum eq '6' or tabNum eq '7'}">is-darkgreen-btn</c:if>" data-index="5">수집원별 소통정보</button>
		        </div>
		    </div>
		    <div class="contents_wrap tab_area">
		        <div class="tab1">
		        		<c:if test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4'}">
			        		<div class="group2">
				                <div class="group_text2">시계열</div>
				                <select class="selectBox selectTabMenu">
				                	<option <c:if test="${tabNum eq '2' or tabNum eq '1'}">selected</c:if> data-index="2">일별</option>
				                	<option <c:if test="${tabNum eq '3'}">selected</c:if> data-index="3">월별</option>
				                	<option <c:if test="${tabNum eq '4'}">selected</c:if> data-index="4">연도별</option>
				                </select>
				            </div>
		        		</c:if>
		        		<c:if test="${tabNum eq '5' or tabNum eq '6' or tabNum eq '7'}">
		        			<div class="group2">
				                <div class="group_text2">수집원</div>
				                <select class="selectBox selectTabMenu">
				                	<option <c:if test="${tabNum eq '5'}">selected</c:if> data-index="5">VDS</option>
				                	<option <c:if test="${tabNum eq '6'}">selected</c:if> data-index="6">DSRC</option>
				                	<option <c:if test="${tabNum eq '7'}">selected</c:if> data-index="7">스마트교차로</option>
				                </select>
				            </div>
		        		</c:if>
			            <div class="group2">
							<div class="flex-center gap16">
								<div class="group_text2">주소</div>
								<div class="flex-center gap16 pl64">
									<div class="group_text3">시군</div>
									<select class="selectBox" name="sigunCdId" id="sigunCdId">
										<option value="searchAllLocation">전체</option>
										<c:forEach var="sggCdList" items="${sggCdList}">
											<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
										</c:forEach>
									</select>
								</div>
								<div class="flex-center gap16">
									<div class="group_text3">검색</div>
									<input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="도로명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
								</div>
							</div>
							<div class="search_detail_btn">
								상세 검색 <i></i>
							</div>
						</div>
			            <div class="search_detail_wrap">
			                <div class="group2">
			                    <div class="group_text2">기간 설정</div>
			                    <div class="flex-center">
		                        	<c:choose>
		                        		<c:when test="${tabNum eq '2' or tabNum eq '1'}">
		                        			<input type="text" class="date_picker input_same mr8 input_picker mr8" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
				                            ~
				                            <input type="text" class="end_date_picker input_same mr8 input_picker ml8 mr8" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
		                        		</c:when>
		                        		<c:when test="${tabNum eq '3'}">
				                            <div class="flex-center mr16">
				                            	<div class="mr8">연도</div>
												<select class="selectBox mr8" name="strSearchYear" id="strSearchYear">
									                <option value="searchAllYear">전체</option>
									           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
									                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
									           		</c:forEach>
									            </select>
							           			<div class="mr8">월</div>					                            
												<select class="selectBox" name="strSearchMonth" id="strSearchMonth">
							           				<option value="searchAllMonth">전체</option>
							           				<c:forEach begin="0" end="11" step="1" varStatus="status">
										                <option value="<c:out value='${status.index+1}'/>"><c:out value='${status.index+1}'/>월</option>
									           		</c:forEach>
							           			</select>
				                            </div>
				                            ~
				                            <div class="flex-center ml16">
				                            	<div class="mr8">연도</div>
												<select class="selectBox mr8" name="endSearchYear" id="endSearchYear">
									                <option value="searchAllYear">전체</option>
									           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
									                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
									           		</c:forEach>
									            </select>
							           			<div class="mr8">월</div>					                            
 												<select class="selectBox" name="endSearchMonth" id="endSearchMonth">
							           				<option value="searchAllMonth">전체</option>
							           				<c:forEach begin="0" end="11" step="1" varStatus="status">
										                <option value="<c:out value='${status.index+1}'/>"><c:out value='${status.index+1}'/>월</option>
									           		</c:forEach>
							           			</select>
				                            </div>					                            
		                        		</c:when>
		                        		<c:when test="${tabNum eq '4'}">
						           			<select class="selectBox mr8" name="strSearchYear" id="strSearchYear">
								                <option value="searchAllYear">전체</option>
								           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
								                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
								           		</c:forEach>
								            </select>
						           			~
											<select class="selectBox mr8" name="endSearchYear" id="endSearchYear">
								                <option value="searchAllYear">전체</option>
								           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
								                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
								           		</c:forEach>
								            </select>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
<!-- 		                        			<select class="selectBox selectTime" name="startTime" id="startTime"></select> -->
				                            &nbsp; ~ &nbsp;
				                            <input type="text" class="end_date_picker input_same mr8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
<!-- 				                            <select class="selectBox selectTime" name="endTime" id="endTime"></select> -->
		                        		</c:otherwise>
		                        	</c:choose>
			                    </div>
			                </div>
			                <c:if test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4'}">
		                        <div class="group2">
		                            <div class="group_text2">요일 설정</div>
		                            <div class="flex-center gap24">
		                                <div>
		                                    <input type="hidden" name="dayOfTheWeekStr" id="dayOfTheWeekStr" value="<c:out value='${searchOption.dayOfTheWeekStr}'/>">
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="7">일</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="1">월</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="2">화</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="3">수</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="4">목</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="5">금</label>
		                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="6">토</label>
		                                </div>
		                            </div>
		                        </div>
			                </c:if>
			            </div>
		                <div class="group2_btn">
							<button type="button" class="is-darkgreen-btn" id="searchBtn"  onclick="fnSearchList();">찾기</button>
							<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span id="totalCnt"></span>의 검색결과를 찾았습니다.
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:20%">
		                    <c:if test="${tabNum eq '5' or tabNum eq '6'}">
			                    <col style="width:20%">
		                    </c:if>
		                    <col style="width:20%">
		                    <col style="width:20%">
		                    <col style="width:20%">
		                </colgroup>
		                <thead>
			                <tr>
					            <th scope="col" class="left">도로명</th>
			                	<c:choose>
			                		<c:when test="${tabNum eq '5'}">
					                    <th scope="col" class="left">VDS명</th>
			                		</c:when>
			                		<c:when test="${tabNum eq '6'}">
					                    <th scope="col" class="left">구간명</th>
			                		</c:when>
			                	</c:choose>
			                    <th scope="col">누적 교통량(대)</th>
			                    <th scope="col">평균 교통량(대)</th>
			                    <th scope="col">평균속도(km/h)</th>
			                </tr>
		                </thead>
		                <tbody>
		                	<c:choose>
		                		<c:when test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4' or tabNum eq '7'}">
				                	<c:choose>
				                		<c:when test="${fn:length(comunicationList) > 0}">
				                			<c:forEach var="comunicationInfo" items="${comunicationList}">
					                			<tr>
					                				<td class="left"><c:out value='${comunicationInfo.roadName}'/></td>
								                    <td>
								                    	<fmt:formatNumber value="${comunicationInfo.sumTrfvlm}" pattern="#,###"/>
								                    </td>
								                    <td>
								                    	<fmt:formatNumber value="${comunicationInfo.avgTrfvlm}" pattern="#,###.##"/>
								                    </td>
								                    <td><c:out value='${comunicationInfo.avgSpd ne null and comunicationInfo.avgSpd ne "" ? comunicationInfo.avgSpd : "0"}'/></td>
					                			</tr>
				                			</c:forEach>
				                		</c:when>
				                		<c:otherwise>
				                			<td colspan="4">소통정보가 존재하지 않습니다.</td>
				                		</c:otherwise>
				                	</c:choose>
		                		</c:when>
		                		<c:when test="${tabNum eq '5' or tabNum eq '6'}">
		                			<c:choose>
				                		<c:when test="${fn:length(comunicationList) > 0}">
				                			<c:forEach var="comunicationInfo" items="${comunicationList}">
					                			<tr>					                				
					                				<td class="left"><c:out value='${comunicationInfo.roadName}'/></td>
					                				<td>
					                					<c:choose>
					                						<c:when test="${tabNum eq '5' }">
								                				<c:out value='${comunicationInfo.vdsNm}'/>
					                						</c:when>
					                						<c:when test="${tabNum eq '6' }">
								                				<c:out value='${comunicationInfo.strRseNm}'/> -> <c:out value='${comunicationInfo.endRseNm}'/>
					                						</c:when>
					                					</c:choose>
					                				</td>
								                    <td>
								                    	<c:choose>
								                    		<c:when test="${tabNum eq '5' or tabNum eq '7'}">
										                    	<fmt:formatNumber value="${comunicationInfo.sumTrfvlm}" pattern="#,###.##"/>								                    			
								                    		</c:when>
								                    		<c:otherwise>
								                    			-
								                    		</c:otherwise>
								                    	</c:choose>
								                    </td>
								                    <td>
								                    	<c:choose>
								                    		<c:when test="${tabNum eq '5' or tabNum eq '7'}">
										                    	<fmt:formatNumber value="${comunicationInfo.avgTrfvlm}" pattern="#,###.##"/>								                    			
								                    		</c:when>
								                    		<c:otherwise>
								                    			-
								                    		</c:otherwise>
								                    	</c:choose>
								                    </td>
								                    <td><c:out value='${comunicationInfo.avgSpd}'/></td>
					                			</tr>
				                			</c:forEach>
				                		</c:when>
				                		<c:otherwise>
				                			<td colspan="5">소통정보가 존재하지 않습니다.</td>
				                		</c:otherwise>
				                	</c:choose>
		                		</c:when>
		                	</c:choose>
		                </tbody>
		            </table>
		            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            <div class="table_chart_wrap">
		                <div class="table_chart_list">
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_b">소통 원활</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <c:choose>
							                		<c:when test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4' or tabNum eq '7'}">
									                    <th scope="col" class="left">도로명</th>
							                		</c:when>
							                		<c:when test="${tabNum eq '5'}">
									                    <th scope="col" class="left">VDS명</th>
							                		</c:when>
							                		<c:when test="${tabNum eq '6'}">
									                    <th scope="col" class="left">구간명</th>
							                		</c:when>
							                	</c:choose>
				                                <th scope="col">평균속도(km/h)</th>
				                            </tr>
			                            </thead>
										<tbody id="smoothPart">
											<tr>
												<td colspan="3">데이터를 조회중입니다.</td>
											</tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_g">소통 정체</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <c:choose>
							                		<c:when test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4' or tabNum eq '7'}">
									                    <th scope="col" class="left">도로명</th>
							                		</c:when>
							                		<c:when test="${tabNum eq '5'}">
									                    <th scope="col" class="left">VDS명</th>
							                		</c:when>
							                		<c:when test="${tabNum eq '6'}">
									                    <th scope="col" class="left">구간명</th>
							                		</c:when>
							                	</c:choose>
				                                <th scope="col">평균속도(km/h)</th>
				                            </tr>
			                            </thead>
										<tbody id="jamPart">
											<tr>
												<td colspan="3">데이터를 조회중입니다.</td>
											</tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <c:if test="${tabNum eq '1' or tabNum eq '2' or tabNum eq '3' or tabNum eq '4'}">
		                       	<div class="chart">
		                       		<div class="tab_box_title left mb16">차종 <span>총 <c:out value='${totCntVhcl}'/>대</span></div>
		                       		<div style="height:380px">
			                       		<canvas id="tab1_1_chart"></canvas>
		                       		</div>
		                       	</div>
		                    </c:if>
		                </div>
		            </div>
		        </div>
	        </div>
	    </form>
	</section>
</div>
<script type="text/javascript">
	//검색결과 갯수
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt) + '개')

	$(document).ready(function(){
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
		var strSearchYear = '<c:out value="${searchOption.strSearchYear}"/>';
		var endSearchYear = '<c:out value="${searchOption.endSearchYear}"/>';
		var strSearchMonth = '<c:out value="${searchOption.strSearchMonth}"/>';
		var endSearchMonth = '<c:out value="${searchOption.endSearchMonth}"/>';
		var tabNum = '<c:out value="${tabNum}"/>';
		
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
		if(strSearchYear != null && strSearchYear != ''){
			$("#strSearchYear").val(strSearchYear).prop("selected",true);
		}
		if(endSearchYear != null && endSearchYear != ''){
			$("#endSearchYear").val(endSearchYear).prop("selected",true);
		}
		if(strSearchMonth != null && strSearchMonth != ''){
			$("#strSearchMonth").val(strSearchMonth).prop("selected",true);
		}
		if(endSearchMonth != null && endSearchMonth != ''){
			$("#endSearchMonth").val(endSearchMonth).prop("selected",true);
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
		if(tabNum == '5'){
			$("#searchContent").attr("placeholder", "도로명 또는 VDS명을 입력하세요.");
		}else if(tabNum == '6'){
			$("#searchContent").attr("placeholder", "도로명 또는 구간명을 입력하세요.");
		}
		
		var listForm = $("#searchForm").serialize();
   		$.ajax({
   			type : "post",
   			url : "${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.ajax",
   			data : listForm,
   			success: function(result) {
   				$("#smoothPart tr").remove();
   				$("#jamPart tr").remove();
   				var smoothList = result.data.smoothList;
   		 	   	var jamList = result.data.jamList;
   		 	   	
   		 	   	if(!isNull(smoothList)){
   		 	   		var smoothHtml = '';
   		 	   		
	   		 	   	$(smoothList).each(function(index, item){
		   		 	   	if(tabNum == '1' || tabNum == '2' || tabNum == '3' || tabNum == '4'){
	   		 	   			smoothHtml += '<tr>';
	   		 	   			smoothHtml += '		<td>'+ (index + 1) +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.roadName +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.avgSpd +'</td>';
	   		 	   			smoothHtml += '</tr>';
	   		 	   		}else if(tabNum == '5'){
		   		 	   		smoothHtml += '<tr>';
	   		 	   			smoothHtml += '		<td>'+ (index + 1) +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.vdsNm +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.avgSpd +'</td>';
	   		 	   			smoothHtml += '</tr>';
	   		 	   		}else if(tabNum == '6'){
		   		 	   		smoothHtml += '<tr>';
	   		 	   			smoothHtml += '		<td>'+ (index + 1) +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.strRseNm + ' -> ' + item.endRseNm +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.avgSpd +'</td>';
	   		 	   			smoothHtml += '</tr>';
	   		 	   		}else if(tabNum == '7'){
		   		 	   		smoothHtml += '<tr>';
	   		 	   			smoothHtml += '		<td>'+ (index + 1) +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.roadName +'</td>';
	   		 	   			smoothHtml += '		<td>'+ item.avgSpd +'</td>';
	   		 	   			smoothHtml += '</tr>';
	   		 	   		}
	   		 	   	})
   		 	   		
   		 	   		$("#smoothPart").append(smoothHtml);
   		 	   	}else{
   		 	   		var smoothHtml = '';
   		 	   		smoothHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
   		 	   		$("#smoothPart").append(smoothHtml);
   		 	   	}
   		 	   	
	   		 	if(!isNull(jamList)){
	   		 		var jamHtml = '';
	   		 		
	   		 		$(jamList).each(function(index, item){
						if(tabNum == '1' || tabNum == '2' || tabNum == '3' || tabNum == '4'){
							jamHtml += '<tr>';
							jamHtml += '	<td>'+ (index + 1) +'</td>';
							jamHtml += '	<td>'+ item.roadName +'</td>';
							jamHtml += '	<td>'+ item.avgSpd +'</td>';
							jamHtml += '</tr>';
			 	   		}else if(tabNum == '5'){
				 	   		jamHtml += '<tr>';
				 	   		jamHtml += '		<td>'+ (index + 1) +'</td>';
				 	   		jamHtml += '		<td>'+ item.vdsNm +'</td>';
				 	  		jamHtml += '		<td>'+ item.avgSpd +'</td>';
				 	 		jamHtml += '</tr>';
			 	   		}else if(tabNum == '6'){
				 	   		jamHtml += '<tr>';
							jamHtml += '	<td>'+ (index + 1) +'</td>';
							jamHtml += '		<td>'+ item.strRseNm + ' -> ' + item.endRseNm +'</td>';
							jamHtml += '	<td>'+ item.avgSpd +'</td>';
							jamHtml += '</tr>';
			 	   		}else if(tabNum == '7'){
				 	   		jamHtml += '<tr>';
							jamHtml += '	<td>'+ (index + 1) +'</td>';
							jamHtml += '	<td>'+ item.roadName +'</td>';
							jamHtml += '	<td>'+ item.avgSpd +'</td>';
							jamHtml += '</tr>';
			 	   		}	
	   		 		})
	   		 		
	   		 		$("#jamPart").append(jamHtml);
   		 	   	}else{
   		 	   		var jamHtml = '';
   		 	   		jamHtml += '<tr><td colspan="3">소통정보가 존재하지 않습니다.</td></tr>';
   		 	   		$("#jamPart").append(jamHtml);
   		 	   	}
   			}
   		});
			
		
		// tab1-1 chart
	   	if(tabNum != '5' || tabNum != '6' || tabNum != '7'){
	   		var chartForm = $("#searchForm").serialize();
	   		$.ajax({
	   			type : "post",
	   			url : "${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list/chart.ajax",
	   			data : chartForm,
	   			success: function(result) {
	   				var totCntVhcl = result.data.totCntVhcl;
	   		 	   	var vhclInfoLabelArray = result.data.vhclInfoLabelArray; 
	   		 	   	var vhclInfoDataArray = result.data.vhclInfoDataArray;
	   		 	   	
	   		 	   	$("#totCntVhcl").text(totCntVhcl);
	   		 	   	
	   			   	if(isNull(vhclInfoDataArray) || vhclInfoDataArray == 0){
	   			   		vhclInfoDataArray = '0,0'
	   			   	}
	   			   	if(isNull(vhclInfoLabelArray)){
	   			   		vhclInfoLabelArray = '0,0'
	   			   	}
	   				new GITSChart(GITSChartType.DOUGHNUT).init("tab1_1_chart")
	   				.setData({
	   			            labels: vhclInfoLabelArray.split(','),
	   			            datasets: [{
	   				          	 label:vhclInfoLabelArray.split(','),
	   				          	 data: vhclInfoDataArray.split(','),
	   				          	 backgroundColor:['#00C600', '#00E5D8',  '#E1E100', '#FF3131'],
	   				          	 borderColor:'transparent'
	   			            }]
	   			        })	
	   				.setLabelPadding(30)
	   				.draw();
	   			}
	   		});
	   	}
	})
	
	/* 세부 검색 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	$(".tabMenu").on('click', function(){
		var tabNum = $(this).data("index");
		window.location.href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?tabNum="+tabNum+"&initAppchYn=Y";
	})
	
	$(".selectTabMenu").on('change', function(){
		var tabNum = $(".selectTabMenu option:selected").data("index");
		window.location.href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?tabNum="+tabNum+"&initAppchYn=Y";
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do";
		document.getElementById('searchForm').submit();
	}	
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
	$(function(){
// 		연도만
		$('.year_picker').datepicker( {
		    yearRange: "c-20:c",
		    changeMonth: false,
		    changeYear: true,
		    showButtonPanel: true,
		    closeText:'선택하기',
		    currentText: 'This year',
		    onClose: function(dateText, inst) {
		      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		      $(this).val($.datepicker.formatDate('yy년', new Date(year, 1, 1)));
		    }
		  }).focus(function () {
		    $(".ui-datepicker-month").hide();
		    $(".ui-datepicker-calendar").hide();
		    $(".ui-datepicker-current").hide();
		    $(".ui-datepicker-prev").hide();
		    $(".ui-datepicker-next").hide();
		    $("#ui-datepicker-div").position({
		      my: "left top",
		      at: "left bottom",
		      of: $(this)
		    });
		    $('table.ui-datepicker-calendar').hide();
		    $('.ui-datepicker').css({"width":"210px", "top":"380px"});
		    $('.ui-datepicker-year').css({"width":"100%","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker .ui-datepicker-title').css({"line-height":"0","text-align":"left",});
		    $('.ui-widget-content').css({"border":"none"});
		    $('.ui-datepicker-close').css({"font-size":"0.875rem","font-weight":"700",})
		  }).attr("readonly", false);
	})
	
	$(".month_picker").datepicker({ 
// 			dateFormat: 'yy.mm',
// 			changeMonth: true,
// 		    changeYear: true,
// 		    showButtonPanel: true,
			closeText: '선택하기',
            nextText : '다음 달',
            prevText : '이전 달',
            currentText : "이번 달",
            changeMonth : true,
            changeYear : true,
            monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
//             dayNames : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
//             dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
//             dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
            weekHeader : "주",
            firstDay : 0,
            isRTL : false,
            showMonthAfterYear : true,
            yearSuffix : "년",
            showButtonPanel: true,
            dateFormat: 'yy-mm',          
            yearRange: "-10:+0",
		    onClose: function(dateText, inst) {  
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
	            $(this).datepicker('setDate', new Date(year, month, 1));
	            $(".ui-datepicker-calendar").css("display","none");
	        }
		});
		$(".month_picker").focus(function () {
			$(".ui-datepicker-calendar").css("display","none");
			$("#ui-datepicker-div").position({
				  my: "center top",
				  at: "center bottom",
				  of: $(this)
			});
		    $('.ui-datepicker-year').css({"width":"44%","margin-right":"0.5rem","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker-month').css({"width":"44%","margin-left":"0.5rem","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker .ui-datepicker-title').css({"line-height":"0","text-align":"left",});
		    $('.ui-widget-content').css({"border":"none"});
		    $('.ui-datepicker-close').css({"font-size":"0.875rem","font-weight":"700",})
		    $('.ui-datepicker-current').css({"font-size":"0.875rem","font-weight":"500",})
		    $('.ui-icon-circle-triangle-w').hide();
		    $('.ui-icon-circle-triangle-e').hide();
		});
</script>
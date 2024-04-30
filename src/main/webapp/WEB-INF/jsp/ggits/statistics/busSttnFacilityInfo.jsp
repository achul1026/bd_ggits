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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" onclick="startLoading()">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do?initAppchYn=Y" onclick="startLoading()">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do" onclick="startLoading()">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" class="on" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" onclick="startLoading()">도로안전</a>
<%-- 	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do" onclick="startLoading()">교통시설물 통계</a> --%>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap_left btn_search_wrap">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do'">버스 DTG</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_rout/list.do'">정류장별 버스노선, 버스유형</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_facility_info/list.do'">정류장별 시설물 및 기타정보</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_pasnr/list.do'">정류장별 승하차 승객</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_rout_pasnr/list.do'">노선구간별 승하차/재차 승객수</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
			<div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="page" name="page"  value="1"/>
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
										검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="정류장명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
                                <ul class="">
                                	<li>
                                		 <input type="text" class="date_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">	
                                	</li>
<!--                                 	<li> -->
<!--                                 		 <select class="selectBox selectTime" id="startTime" name="startTime"></select> -->
<!--                                 	</li> -->
                                	<li>
                                		 ~
                                	</li>
                                	<li>
                                		<input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	</li>
<!--                                 	<li> -->
<!--                                 		<select class="selectBox selectTime" id="endTime" name="endTime"></select> -->
<!--                                 	</li> -->
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
	                        <span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <thead>
		                <tr>
		                    <th scope="col" class="">정류장</th>
		                    <th scope="col" class="">시설물</th>
		                    <th scope="col" class="">BIT 수</th>
		                    <th scope="col">분석일시</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(statsList) > 0 }">
	                			<c:forEach var="statsInfo" items="${statsList}">
	                				<tr>
	                					<td class=""><c:out value='${statsInfo.bstpNm}'/></td>
	                					<td>
	                						<c:if test="${statsInfo.bitType eq null or statsInfo.bitType eq '' }">
	                							버스 도착 안내 단말기
	                						</c:if>
	                					</td>
	                					<td><c:out value='${statsInfo.bitCnt}'/></td>
	                					<td><fmt:formatDate value="${statsInfo.anlsDt}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="4">시설물 정보가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	        </div>
        </div>
        <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	</section>
</div>
<script>
	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	$(document).ready(function(){
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
// 		var strTime = '<c:out value="${searchOption.startTime}"/>';
// 		var endTime = '<c:out value="${searchOption.endTime}"/>';
		var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
		var sigunCdId = '<c:out value="${searchOption.sigunCdId}"/>';

		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
// 		if(strTime != null && strTime != ''){
// 			$("#startTime").val(strTime).prop("selected",true);
// 		}
// 		if(endTime != null && endTime != ''){
// 			$("#endTime").val(endTime).prop("selected",true);
// 		}
		if(sigunCdId != null && sigunCdId != ''){
			$("#sigunCdId").val(sigunCdId);
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
	})
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	}) 
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_sttn_facility_info/list.do";
		document.getElementById('searchForm').submit();
	}

	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
</script>
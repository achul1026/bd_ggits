<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">데이터 활용 통계</h3>
	        <div class="side_txt">
	            데이터 활용 통계 및 분석<br>자료입니다.
	        </div>
	        <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do">서비스 이력</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do" class="on">이력 집계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">데이터 활용 통계</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do'">교통정보 이력집계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/recode/public_traffic_info_log_colt/list.do'">대중교통 정보 이력 집계</button>
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/recode/bus_sttn_rout_info_colt/list.do'">정류장별 운행이력 정보 집계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab3">
	        	<form id="searchForm" method="get">
		            <div class="group2">
						<div class="flex-center gap16">
							<div class="group_text2">주소</div>
			            	<div class="flex-center gap16 pl64">
				                <div class="group_text3">시군</div>
					            <select class="selectBox">
		                            <option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
	                					<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
									</c:forEach>
		                        </select>
			            	</div>
			            	<div class="flex-center gap16">
				                <div class="group_text3">정류장명</div>
					            <select class="selectBox">
		                            <option selected="selected">전체</option>
		                            <option>test</option>
		                            <option>test</option>
		                            <option>test</option>
		                        </select>
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
		                        <div class="calendar">
									<input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
									<select class="selectBox selectTime" id="startTime" name="startTime"></select>
                                    ~
                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
									<select class="selectBox selectTime" id="endTime" name="endTime"></select>
		                        </div>
		                    </div>       
		                </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="flex-center gap24">
                                <div>
                                   <input type="hidden" name="dayOfTheWeekStr" id="dayOfTheWeekStr" value="${searchOption.dayOfTheWeekStr}">
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="1">일</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="2">월</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="3">화</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="4">수</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="5">목</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="6">금</label>
                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none dayOfWeek" value="7">토</label>
                                </div>
                            </div>
                        </div>			                
		            </div>
	                <div class="group2_btn">
						<!-- button id  name 바꿔서 사용하세요  -->
						<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
						<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span>"${totalCnt}개"</span>의 검색결과를 찾았습니다.
	                    </div>
	                </div>
	            </div>
	            <table class="mt16">
	                <colgroup>
	                    <col style="width:25%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:35%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">정류장명</th>
		                    <th scope="col">운행 노선 수</th>
		                    <th scope="col">승차</th>
		                    <th scope="col">하차</th>
		                    <th scope="col">환승</th>
		                    <th scope="col">승차일자</th>
		                </tr>
	                </thead>
	                <tbody>
		                <c:choose>
		                	<c:when test="${fn:length(logTotalList) > 0 }">
		                		<c:forEach var="logInfo" items="${logTotalList}">
		                			<tr>
		                				<td class="left">${logInfo.bstpNm}</td>
		                				<td>${logInfo.busRouteRungCnt}</td>
		                				<td>${logInfo.rideUserCnt}</td>
		                				<td>${logInfo.lndiUserCnt}</td>
		                				<td>${logInfo.trnsitUserCnt}</td>
		                				<td>
		                					<fmt:parseDate value="${logInfo.rideYmd}" var="rideYmdDate" pattern="yyyyMMdd"/>
		                					<fmt:formatDate pattern="yyyy-MM-dd" value="${rideYmdDate}"/>
		                				</td>
		                			</tr>
		                		</c:forEach>
		                	</c:when>
		                	<c:otherwise>
		                		<tr>
		                			<td colspan="6">운행이력이 존재하지 않습니다.</td>
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
	$(document).ready(function(){
		//<![CDATA[
			var strDt = '${searchOption.strDt}';
			var endDt = '${searchOption.endDt}';
			var strTime = '${searchOption.startTime}';
			var endTime = '${searchOption.endTime}';
			var searchContent = '${searchOption.searchContent}';
			var dayOfTheWeekStr = '${searchOption.dayOfTheWeekStr}';
		//]]>
	
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(strTime != null && strTime != ''){
			$("#startTime").val(strTime).prop("selected",true);
		}
		if(endTime != null && endTime != ''){
			$("#endTime").val(endTime).prop("selected",true);
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
	})
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/data/recode/bus_sttn_rout_info_colt/list.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
</script>
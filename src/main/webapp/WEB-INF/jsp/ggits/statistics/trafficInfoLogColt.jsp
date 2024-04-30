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
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do" onclick="startLoading()">서비스 이력</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do" onclick="startLoading()">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do" class="on" onclick="startLoading()">이력 집계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">데이터 활용 통계</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap btn_search_wrap_left">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do'">교통정보 이력집계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/recode/public_traffic_info_log_colt/list.do'">대중교통 정보 이력 집계</button>
	        		</li>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/recode/bus_sttn_rout_info_colt/list.do'">정류장별 운행이력 정보 집계</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab1">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" name="page" id="page" value="1">
		            <div class="group2">
							<div class="group_text2">주소</div>
			            	<div class="btn_search_wrap">
			            		<ul>
			            			<li>
			            				시군	: <select class="selectBox" name="sigunCdId" id="sigunCdId">
						                            <option value="searchAllLocation">전체</option>
													<c:forEach var="sggCdList" items="${sggCdList}">
					                					<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
													</c:forEach>
						                        </select>
			            			</li>
			            			<li>
			            				검색	: <input type="text" class="input_same" id="searchContent" name="searchContent" placeholder="도로명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
		                        		<input type="text" class="date_picker input_same input_picker" name="strDt" placeholder="날짜를 선택해주세요." value="<c:out value='${searchOption.strDt}'/>" autocomplete="off">
		                        	</li>
<!-- 		                        	<li> -->
<!-- 		                        		<select class="selectBox selectTime" id="startTime" name="startTime"></select> -->
<!-- 		                        	</li> -->
		                        	<li>
		                        		~
		                        	</li>
		                        	<li>
		                        		<input type="text" class="end_date_picker input_same input_picker" name="endDt" placeholder="날짜를 선택해주세요." value="<c:out value='${searchOption.endDt}'/>" autocomplete="off">
		                        	</li>
<!-- 		                        	<li> -->
<!-- 		                        		<select class="selectBox selectTime" id="endTime" name="endTime"></select> -->
<!-- 		                        	</li> -->
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
		                    <th scope="col" class="">도로명</th>
		                    <th scope="col">교통량(대)</th>
		                    <th scope="col">평균속도(km/h)</th>
<!-- 		                    <th scope="col">혼잡도(%)</th> -->
		                    <th scope="col">분석일시</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(logTotalList) > 0}">
	                			<c:forEach var="logInfo" items="${logTotalList}">
	                				<tr>
	                					<td class=""><c:out value='${logInfo.roadName}'/></td>
	                					<td><fmt:formatNumber value="${logInfo.trfvlm}" pattern="#,###" /></td>
	                					<td><c:out value='${logInfo.speed}'/></td>
<!-- 	                				TODO: 컬럼 추가 필요 -->
<!-- 	                					<td>-</td> -->
	                					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${logInfo.anlsDt}"/></td>
	                				</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="6">집계정보가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            <div>
	                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            </div>
			</div>
        </div>
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
			$("#sigunCdId").val(sigunCdId).prop("selected",true);
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
		$('.search_head').removeClass('none');
	});
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})
	
	
</script>
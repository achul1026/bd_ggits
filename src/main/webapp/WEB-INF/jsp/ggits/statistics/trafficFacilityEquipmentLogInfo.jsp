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
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do" onclick="startLoading()">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do" onclick="startLoading()">도로안전</a>
<%-- 	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do" class="on" onclick="startLoading()">교통시설물 통계</a> --%>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap_left btn_search_wrap">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do'">교통시설물 장애통계</button>
	        		</li>
	        		<li>
	        			 <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_equipment_log_info/list.do'">교통시설물 장비 로그 상세</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab2">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" name="page" id="page" value="1">
		            <div class="group2">
							<div class="group_text2">주소</div>		            	
			            	<div class="btn_search_wrap">
			            		<ul>
			            			<li>
			            				관리 기관 : <select class="selectBox" name="mngCdId" id="mngCdId">
					                            <option value="searchAllLocation">전체</option>
												<c:forEach var="item" items="${mngInstCdList}">
				                					<option value="<c:out value='${item.cdId}'/>"><c:out value='${item.cdNm}'/></option>
												</c:forEach>
					                        </select>
			            			</li>
			            			<li>
			            				검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="시설물 ID/시설물 명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
<!--                                 		<select class="selectBox selectTime" name="startTime" id="startTime"></select> -->
<!--                                 	</li> -->
                                	<li>
                                		~
                                	</li>
                                	<li>
                                		<input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
                                	</li>
<!--                                 	<li> -->
<!--                                 		<select class="selectBox selectTime" name="endTime" id="endTime"></select> -->
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
	            <div class="search_container">
					
	                <div class="search_head">
	                    <div class="search_number">
	                        <span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                    	<!-- TODO 수정 필요 -->
	                	<div class="btn_search_wrap">
	                		<ul>
	                			<li>
	                				<input type="hidden" name="searchType" id="searchType">
	                			</li>
	                			<li>
	                				<button type="button" class="is-dark-btn is-darkgreen-btn mj0 selectSearchTp" id="VDS" value="VDS">VDS</button>	                		
	                			</li>
	                			<li>
	                				 <button type="button" class="is-dark-btn mj0 selectSearchTp"  id="RSE" value="RSE">RSE</button>	   
	                			</li>
	                			<li>
	                				<button type="button" class="is-dark-btn mj0 selectSearchTp" id="SMC" value="SMC">SMART_CAMERA</button>	         
	                			</li>
	                			<li>
	                				<button type="button" class="is-darkgreen-btn mj0" onclick="fnDownloadExcel();">엑셀 다운로드</button> 
	                			</li>
	                		</ul>    		
	                	</div>
	                  
	                </div>
	            </div>
	            <table class="mt16">
	                <colgroup>
	                    <col style="width:12%">
	                    <col style="width:10%">
	                    <col style="width:8%">
	                    <col style="width:10%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th scope="col" class="left">ID</th>
		                    <th scope="col">시설물 명</th>
		                    <th scope="col">상태</th>
		                    <th scope="col">날짜</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:choose>
	                		<c:when test="${fn:length(trfFcltsSttsList) > 0 }">
	                			<c:forEach var="trfFcltsStts" items="${trfFcltsSttsList}">
		                			<tr>
		                				<td class="left"><c:out value='${trfFcltsStts.id}'/></td>
		                				<td><c:out value='${trfFcltsStts.name ne null and trfFcltsStts.name ne "" ? trfFcltsStts.name : "-"}'/></td>
		                				<td>
		                					<c:out value='${trfFcltsStts.stts eq "0" ? "고장" : "정상"}'/>
		                				</td>
		                				<td><fmt:formatDate value="${trfFcltsStts.clctDt}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		                			</tr>
	                			</c:forEach>
	                		</c:when>
	                		<c:otherwise>
	                			<tr>
	                				<td colspan="4">교통시설물 장비 로그가 존재하지 않습니다.</td>
	                			</tr>
	                		</c:otherwise>
	                	</c:choose>
	                </tbody>
	            </table>
	            </form>
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
		var mngCdId = '<c:out value="${searchOption.mngCdId}"/>';
		var seltRoadRank = '<c:out value="${searchOption.seltRoadRank}"/>';
		var seltRoadDrct = '<c:out value="${searchOption.seltRoadDrct}"/>';
		var seltFicltInfo = '<c:out value="${searchOption.seltFicltInfo}"/>';
		var searchType = '<c:out value="${searchOption.searchType}"/>';
	
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
		if(mngCdId != null && mngCdId != ''){
			$("#mngCdId").val(mngCdId).prop("selected",true);
		}
		if(seltRoadRank != null && seltRoadRank != ''){
			$("#seltRoadRank").val(seltRoadRank).prop("selected",true);
		}
		if(seltRoadDrct != null && seltRoadDrct != ''){
			$("#seltRoadDrct").val(seltRoadDrct).prop("selected",true);
		}
		if(seltFicltInfo != null && seltFicltInfo != ''){
			$("#seltFicltInfo").val(seltFicltInfo).prop("selected",true);
		}
		if(searchType != null && searchType != ''){
			$(".selectSearchTp").removeClass("is-darkgreen-btn");
			$("#"+searchType).addClass("is-darkgreen-btn");
			$("#searchType").val(searchType);
		}else{
			$(".selectSearchTp").removeClass("is-darkgreen-btn");
			$("#vds").addClass("is-darkgreen-btn");
			$("#searchType").val("VDS");
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
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_equipment_log_info/list.do";
		document.getElementById('searchForm').submit();
	}
	
	function fnDownloadExcel(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/common/excel/traffic_facility_equipment_log_info/download.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	});
	
	$(".seltFilter").on("change", function(){
		fnSearchList();
	});
	
	$(".selectSearchTp").on("click", function(){
		$("#searchType").val($(this).val());
		fnSearchList();
	});
</script>
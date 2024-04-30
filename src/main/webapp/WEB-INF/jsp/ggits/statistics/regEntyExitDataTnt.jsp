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
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do" class="on" onclick="startLoading()">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do" onclick="startLoading()">이력 집계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">데이터 활용 통계</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="btn_search_wrap_left btn_search_wrap">
	        	<ul>
	        		<li>
	        			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do'">데이터 집계</button>
	        		</li>
	        	</ul>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="tab1">
	        	<form id="searchForm">
		            <div class="group2">
			                <div class="group_text2">평가명</div>
			                <div class="btn_search_wrap">
			                	<ul>
			                		<li>
									검색 : <input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="평가명을 입력해주세요." value="<c:out value='${searchOption.searchContent}'/>">
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
		                        		<input type="text" class="date_picker input_same input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
		                        	</li>
		                        	<li>
		                        		~
		                        	</li>
		                        	<li>
		                        		<input type="text" class="end_date_picker input_same input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요."  autocomplete="off">
		                        	</li>
		                        </ul>
		                    </div>               
		                </div>
                        <div class="group2">
                            <div class="group_text2">요일 설정</div>
                            <div class="btn_search_wrap ">
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
	                			<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
	                		</li>
	                	</ul>
						
						
	              	</div>
	        	</form>
	            <div class="search_container">
	                <div class="search_head">
	                    <div class="search_number">
	                        <span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
	                    </div>
	                    <div class="table_right_btn">
	                        <button type="button" class="is-darkgreen-btn mj0" onclick="fnDownloadExcel();">엑셀 다운로드</button>
	                    </div>
	                </div>
	            </div>
	            <div class="statistics_table">
		            <table class="table_border">
		                <colgroup>
		                    <col style="width:400px">
		                    <col style="width:140px">
		                	<c:forEach var="usgCd" items="${usgCdList}">
			                    <col style="width:140px">
			                    <col style="width:140px">
							</c:forEach>
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" rowspan="2">평가명</th>
		                    	<th scope="col" rowspan="2">기간</th>
			                	<c:forEach var="usgCd" items="${usgCdList}">
			                		<th scope="col" colspan="2"><c:out value='${usgCd.cdNm}'/></th>
								</c:forEach>
			                </tr>
			                <tr>
			                	<c:forEach var="usgCd" items="${usgCdList}">
                					<th scope="col">상주/상근</th>
		                   			<th scope="col">방문/이동</th>
								</c:forEach>
			                </tr>
		                </thead>
		                <tbody>
		                <c:choose>
		                	<c:when test="${fn:length(ipcssAnalList) > 0}">
		                		<c:forEach var="ipcssInfo" items="${ipcssAnalList}" varStatus="status">
			                		<tr>
			                			<td class="left"><c:out value='${ipcssInfo.exmnDivNm}'/></td>            			
			                			<td>
			                				<fmt:parseDate value="${ipcssInfo.exmnYmd}" var="exmnDate" pattern="yyyyMMdd"/>
			                				<fmt:formatDate value="${exmnDate}" pattern="yyyy-MM-dd"/>
			                			</td>
			                			<c:forEach var="sumDate" items="${ipcssInfo.ipcssResultList}">
			                				<td><c:out value='${sumDate.resdng_bsunt}'/></td>
			                				<td><c:out value='${sumDate.visit_bsunt}'/></td>
			                			</c:forEach>
			                		</tr>
		                		</c:forEach>
		                	</c:when>
		                	<c:otherwise>
			                	<tr>
			                		<td colspan="72">교통영향평가 정보가 없습니다.</td>
			                	</tr>		                	
		                	</c:otherwise>
		                </c:choose>
		                </tbody>
		            </table>
	            </div>
			</div>
        </div>
	</section>
</div>
<script>
$(document).ready(function(){
	var strDt = '<c:out value="${searchOption.strDt}"/>';
	var endDt = '<c:out value="${searchOption.endDt}"/>';
	var dayOfTheWeekStr = '<c:out value="${searchOption.dayOfTheWeekStr}"/>';
	var searchContent = '<c:out value="${searchOption.searchContent}"/>';

	//searchOption dataInit
	if(strDt != null && strDt != ''){
		$("#strDt").val(strDt.substring(0,10));
	}
	if(endDt != null && endDt != ''){
		$("#endDt").val(endDt.substring(0,10));
	}
	if(searchContent != null && searchContent != ''){
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

	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do";
		document.getElementById('searchForm').submit();
	}
	
	function fnDownloadExcel(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/common/excel/reg_enty_exit_data_tnt/download.do";
		document.getElementById('searchForm').submit();
	}
	
	$(".dayOfWeek").on("click", function(){
		chkDayOfWeek();			
	})

	
</script>
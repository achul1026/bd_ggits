<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">데이터 활용 통계</h3>
            <div class="side_txt">
                데이터 활용 통계 및 분석 자료
            </div>
            <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do" class="on" onclick="startLoading()">서비스 이력</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do" onclick="startLoading()">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do" onclick="startLoading()">이력 집계</a>
            </div>
        </div>
    </aside>
    <section class="main_section tab_set">
    	<form id="searchForm" method="get">
    	<input type="hidden" id="page" name="page"  value="1"/>
        <h2 class="blind">수집/연계 시스템 이력</h2>
        <div class="table_btn_wrap tab_fc">
            <div class="btn_search_wrap">
            	<ul>
<!--             		<li> -->
<%--             			 <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do'" >Open API 활용 이력</button> --%>
<!--             		</li> -->
            		<li>
            			<button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/use/clit_link_sys_log/list.do'">수집/연계시스템 이력</button>
            		</li>
            		<li>
            			<button type="button" class="tab_btn_item is-dark-btn" onclick="startLoading(); location.href='${pageContext.request.contextPath}/statistics/data/use/sm_crsrd_data_log/list.do'">스마트교차로 데이터 이력</button>
            		</li>
            	</ul>
            </div>
          </div>
          <div class="contents_wrap tab_area">
         	<div class="">
            	<div class="group2">
	            	<div class="btn_search_wrap">
			        	<ul>
			            	<li>
			                	<select class="selectBox" name="searchType" id="searchType">
			                    	<option value="all" <c:if test="${searchInfo.searchType eq 'all'}">selected</c:if>>전체</option>
			                        <option value="etlClsf" <c:if test="${searchInfo.searchType eq 'etlClsf'}">selected</c:if>>수집/연계 시스템</option>
			                        <option value="apiRqstLog" <c:if test="${searchInfo.searchType eq 'apiRqstLog'}">selected</c:if>>수집자료</option>
								</select>
							</li>
			                <li>
			                	<input type="text" placeholder="검색어를 입력하세요." name="searchContent" id="searchContent" class="input_same search_box" value="<c:out value='${searchInfo.searchContent}'/>">
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
            </div>
        </div>
        <div class="tab_area">
            <div>
                <table>
                    <colgroup>
                        <col style="width:8%">
                        <col style="width:23%">
                        <col style="width:23%">
                        <col style="width:23%">
                        <col style="width:23%">
                    </colgroup>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">수집/연계 시스템</th>
                        <th scope="col">수집자료</th>
                        <th scope="col">결과</th>
                        <th scope="col">수집 일자</th>
                    </tr>
                    <c:choose>
		            		<c:when test="${fn:length(dataUseStatsList) > 0}">
		            			<c:forEach var="dataUseStatsInfo" items="${dataUseStatsList}" varStatus="status">
			            			<tr>
				            			<td><c:out value='${dataUseStatsInfo.rownum}'/></td>
				            			<td><c:out value='${dataUseStatsInfo.etlClsf}'/></td>
						                <td><c:out value='${dataUseStatsInfo.jobNm}'/></td>
						                <c:choose>
						                	<c:when test="${dataUseStatsInfo.prgrsStts eq 'END'}">
						                		<td>성공<span>(<fmt:formatNumber value="${dataUseStatsInfo.clctDataCnt}" pattern="#,###"/>건)</span></td>	
						                	</c:when> 
						                	<c:when test="${dataUseStatsInfo.prgrsStts eq 'RUNNING'}">
						                		<td>진행중</td>
						                	</c:when>
						                	<c:when test="${dataUseStatsInfo.prgrsStts eq 'ERROR'}">
						                			<td><button type="button" class="fail_info" data-jobid="<c:out value='${dataUseStatsInfo.jobId}'/>" data-dsetid="<c:out value='${dataUseStatsInfo.dsetId}'/>" data-etldt="<c:out value='${dataUseStatsInfo.etlDt}'/>">실패(사유보기)</button></td>
						                	</c:when>
						                	<c:otherwise>
						                		<td>-</td>
						                	</c:otherwise>
						                </c:choose>
						                <td>
						                	<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${dataUseStatsInfo.clctStartDt}"/>
						                </td>
			            			</tr>			            			
		            			</c:forEach>
		            		</c:when>
		            		<c:otherwise>
		            			<tr>
		            				<td colspan="5">시스템 이력이 존재하지 않습니다.</td>
		            			</tr>
		            		</c:otherwise>
		            	</c:choose>
                </table>
            </div>
        	</div>
			 <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
          </form>
    </section>
</div>

<script>
	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	$(document).ready(function(){
		var strDt = '<c:out value="${searchInfo.strDt}"/>';
		var endDt = '<c:out value="${searchInfo.endDt}"/>';
	
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
	})
	/* modal start */
	/* 실패사유 */
	$('.fail_info').on("click", function(){
		var jobId = $(this).data("jobid");
		var dsetId = $(this).data("dsetid");
		var etlDt = $(this).data("etldt");
	    new ModalBuilder().init('실패 정보').ajaxBody("${pageContext.request.contextPath}/common/modal/collectionsystem/list.do?jobId="+jobId+"&dsetId="+dsetId+"&etlDt="+etlDt).footer(1,'확인',function(button, modal){
	    	modal.close();
	    }).open();
	    $('.modal_footer').removeClass('none');
	});
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/data/use/clit_link_sys_log/list.do";
		document.getElementById('searchForm').submit();
	}
</script>
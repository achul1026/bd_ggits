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
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do" class="on">서비스 이력</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/impact/reg_enty_exit_data_tnt/list.do">교통영향평가 데이터</a>
			     <a href="${pageContext.request.contextPath}/statistics/data/recode/traffic_info_log_colt/list.do">이력 집계</a>
            </div>
        </div>
    </aside>
    <section class="main_section tab_set">
    	<form id="searchForm" method="get">
    	<input type="hidden" id="page" name="page"  value="1"/>
        <h2 class="blind">운영 관리</h2>
        <div class="table_btn_wrap clearfix tab_fc">
            <div class="btn_search_wrap float-left">
                <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do'" >Open API 활용 이력</button>
                <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/clit_link_sys_log/list.do'">수집/연계시스템 이력</button>
                <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/sm_crsrd_data_log/list.do'">스마트교차로 데이터 이력</button>
            </div>
            <div class="flex-end">
                <div class="flex-center">
                    <div>
                        <select class="selectBox" name="searchType" id="searchType">
                            <option value="all" <c:if test="${searchInfo.searchType eq 'all'}">selected</c:if>>전체</option>
                            <option value="etlClsf" <c:if test="${searchInfo.searchType eq 'etlClsf'}">selected</c:if>>수집/연계 시스템</option>
                            <option value="apiRqstLog" <c:if test="${searchInfo.searchType eq 'apiRqstLog'}">selected</c:if>>수집자료</option>
                        </select>
                    </div>
                </div>
                <div class="btn_search_wrap float-none">
                    <input type="text" placeholder="검색어를 입력하세요." name="searchContent" id="searchContent" class="input_same search_box" value="${searchInfo.searchContent}">
                    <input type="button" class="input_same search_box2" onclick="fnSearchList();" value="검색" >
                </div>
            </div>
        </div>
        <div class="contents_wrap tab_area mt8">
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
				            			<td>${dataUseStatsInfo.rownum}</td>
				            			<td>${dataUseStatsInfo.etlClsf}</td>
						                <td>${dataUseStatsInfo.jobNm}</td>
						                <c:if test="${dataUseStatsInfo.prgrsStts eq 'END'}">
							                <td>성공<span>(${dataUseStatsInfo.clctDataCnt}건)</span></td>							                	
						                </c:if>
						                <c:if test="${dataUseStatsInfo.prgrsStts eq 'RUNNING'}">
							                <td>진행중</td>							                	
						                </c:if>
						                <c:if test="${dataUseStatsInfo.prgrsStts eq 'ERROR'}">
							                <td><button type="button" id="fail_info" data-jobid="${dataUseStatsInfo.jobId}" data-dsetid="${dataUseStatsInfo.dsetId}" data-etldt="${dataUseStatsInfo.etlDt}">실패(사유보기)</button></td>				                	
						                </c:if>
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
	/* modal start */
	/* 실패사유 */
	$('#fail_info').on("click", function(){
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
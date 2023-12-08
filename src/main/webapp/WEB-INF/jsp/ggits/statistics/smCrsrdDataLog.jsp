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
			     <a href="${pageContext.request.contextPath}/statistics/data/use/open_api_use_log/list.do"class="on">서비스 이력</a>
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
                <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/clit_link_sys_log/list.do'">수집/연계시스템 이력</button>
                <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/data/use/sm_crsrd_data_log/list.do'">스마트교차로 데이터 이력</button>
            </div>
            <div class="flex-end">
                <div class="flex-center">
                    <div>
                        <select class="selectBox" name="searchType" id="searchType">
                            <option value="all" <c:if test="${searchInfo.searchType eq 'all'}">selected</c:if>>전체</option>
                            <option value="apiRqstLog" <c:if test="${searchInfo.searchType eq 'apiRqstLog'}">selected</c:if>>제목</option>
                        </select>
                    </div>
                </div>
                <div class="btn_search_wrap float-none">
                    <input type="text" placeholder="검색어를 입력하세요." name="searchContent" id="searchContent" class="input_same search_box" value="${searchInfo.searchContent}">
                    <input type="button" class="input_same search_box2" onclick="fnSearchList();" value="검색">
                </div>
            </div>
        </div>
        <div class="contents_wrap tab_area mt8">
            <div class="">
                <table>
                    <colgroup>
                        <col style="width:10%">
                        <col style="width:30%">
                        <col style="width:30%">
                        <col style="width:30%">
                    </colgroup>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">스마트 교차로</th>
                        <th scope="col">수집 데이터 보기</th>
                        <th scope="col">수집일자</th>
                    </tr>
                    <c:choose>
                    	<c:when test="${fn:length(dataUseStatsList) > 0}">
                    		<c:forEach var="dataUseStats" items="${dataUseStatsList}">
                    			<tr>
                    				<td>${dataUseStats.rownum}</td>
                    				<td>-</td>
                    				<td><button type="button" class="smart_data_modal" data-index="${dataUseStats.dsetId}">${dataUseStats.jobNm}</button></td>
                    				<td><fmt:formatDate value="${dataUseStats.clctStartDt}" pattern="yyyy년 MM월 dd일 hh시 mm분"/></td>
                    			</tr>
                    		</c:forEach>
                    	</c:when>
                    	<c:otherwise>
                    		<tr>
                    			<td colspan="4">데이터 이력이 존재하지 않습니다.</td>
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
	/* 수집데이터 */
	$('.smart_data_modal').on("click", function(){
		var dsetId = $(this).data("index");
	    new ModalBuilder().init('수집 데이터').ajaxBody("${pageContext.request.contextPath}/common/modal/collectiondata/list.do?dsetId="+dsetId).footer(1,'확인',function(button, modal){
	    	modal.close();
	    }).open();
	    $('.modal_footer').removeClass('none');
	});
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/data/use/sm_crsrd_data_log/list.do";
		document.getElementById('searchForm').submit();
	}
</script>
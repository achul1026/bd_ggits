<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">운영 관리</h3>
                <div class="side_txt">시스템 운영에 대한 로그 및<br>상세 기록을 확인할 수 있습니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">운영 관리</h2>
           	<form id="searchForm" method="get">
            	<div class="table_btn_wrap clearfix">
            		<input type="hidden" id="page" name="page"  value="1"/>
	                <div class="btn_search_wrap float-left">
	                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/system/oprtr/server/list.do'" class="group_btn_item is-dark-btn">서버</button>
	                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/system/oprtr/user/list.do'" class="group_btn_item is-dark-btn">사용자</button>
	                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/system/oprtr/facility/list.do'" class="group_btn_item is-dark-btn">시설물</button>
	                    <button type="button" onclick="location.href='${pageContext.request.contextPath}/system/oprtr/accident/list.do'" class="group_btn_item is-dark-btn">사고</button>
	                </div>
	                <div class="flex-end">
	                    <div class="calendar mr8">
		             		<input type="text" name="strDt" id="strDt" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요." value="${strDt}" autocomplete="off">
		                    ~
		                    <input type="text" name="endDt" id="endDt" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요." value="${endDt}" autocomplete="off">          
	                    </div>
	                    <select class="selectBox" name="searchType" id="searchType">
	                        <option value="all" <c:if test="${searchType eq 'all'}">selected</c:if>>전체</option>
	                        <option value="logId" <c:if test="${searchType eq 'logId'}">selected</c:if>>로그ID</option>
	                        <option value="occurDt" <c:if test="${searchType eq 'occurDt'}">selected</c:if>>발생기간설정</option>
	                        <option value="sesnId" <c:if test="${searchType eq 'sesnId'}">selected</c:if>>요청자ID</option>
	                        <option value="rqstrNm" <c:if test="${searchType eq 'rqstrNm'}">selected</c:if>>요청자 이름</option>
	                        <option value="lgnIp" <c:if test="${searchType eq 'lgnIp'}">selected</c:if>>요청자 IP</option>
	                    </select>
	                    <div class="btn_search_wrap float-none">
	                        <input type="text" name="searchContent" id="searchContent" placeholder="검색어를 입력하세요." class="input_same search_box" value="${searchContent}">
	                        <input type="button" id="searchBtn" value="검색" class="input_same search_box2" onclick="fnSearchList();">
	                    </div>
	                </div>
  		          </div>
           	</form>
            
            <table>
                <colgroup>
                    <col style="width:10%">
                    <col style="width:16%">
                    <col style="width:10%">
                    <col style="width:14%">
                    <col style="width:26%">
                    <col style="width:10%">
                    <col style="width:14%">
                </colgroup>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">로그ID/유형</th>
                    <th scope="col">발생일자</th>
                    <th scope="col">로그유형</th>
                    <th scope="col">요청자ID</th>
                    <th scope="col">요청자이름</th>
                    <th scope="col">요청자IP</th>
                </tr>
                <c:choose> 
                	<c:when test="${fn:length(oprtrLoglist) > 0}">
                  <c:forEach var="oprtrLog" items="${oprtrLoglist}">
                   <tr class="modal_operation pointer" data-logid="${oprtrLog.logId}" data-logtype="${logType}">
                       <td>${oprtrLog.rownum}</td>
                       <td>
                       	${oprtrLog.logId} 
                       	<c:if test="${oprtrLog.cdNm ne null && oprtrLog.cdNm ne ''}">/</c:if>
                       	${oprtrLog.cdNm}
                      	</td>
                       <td>${oprtrLog.occurDtStr}</td>
                       <td>${logCtg.grpCdNm}</td>
                       <c:choose>
                       	<c:when test="${logCtg.grpCdId ne 'USER_LOG_CD'}">
		                    <td>${oprtrLog.sesnId}</td>
                       	</c:when>
                       	<c:when test="${logCtg.grpCdId eq 'USER_LOG_CD'}">
		                    <td>${oprtrLog.prgrmSesnId}</td>
                       	</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
                       </c:choose>
	                <c:choose>
	                	<c:when test="${logCtg.grpCdId ne 'SRVR_LOG_CD'}">
			                <td>${oprtrLog.rqstrNm}</td>
	                	</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
	                </c:choose>
                       <td>${oprtrLog.lgnIp}</td>
                   </tr>
                  </c:forEach>
                	</c:when>
                	<c:otherwise>
                		<td colspan="7">해당 로그가 존재하지 않습니다.</td>
                	</c:otherwise>
                </c:choose>
              </table>
              <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
        </section>
    </div>
</main>
<script>
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* modal start */
	/* 로그정보 */
    $('.modal_operation').on("click", function(){
    	var logId = $(this).data("logid");
    	var logType = $(this).data("logtype");
        new ModalBuilder().init('로그 정보').ajaxBody("${pageContext.request.contextPath}/common/modal/operation/list.do?logId="+logId+"&logType="+logType).footer(1,'확인',function(button, modal){
        	modal.close();
        }).open();
        $('.modal_footer').removeClass('none');
    });
	
	//tab active
    pageUrl = window.location.href;
    $(document).ready(function(){
        let buttonItem = $(".group_btn_item");
        if (pageUrl.indexOf('server') > -1){
            $(buttonItem).eq(0).addClass("is-darkgreen-btn");
        } else if (pageUrl.indexOf('user') > -1 ) {
            $(buttonItem).eq(1).addClass("is-darkgreen-btn");
        }
        else if (pageUrl.indexOf('facility') > -1) {
            $(buttonItem).eq(2).addClass("is-darkgreen-btn");
        } 	
        else if (pageUrl.indexOf('accident') > -1) {
            $(buttonItem).eq(3).addClass("is-darkgreen-btn");
        } 	
        else if (pageUrl.indexOf('oprtr/list.do') > -1) {
            $(buttonItem).eq(0).addClass("is-darkgreen-btn");
        } 	
    })
</script>
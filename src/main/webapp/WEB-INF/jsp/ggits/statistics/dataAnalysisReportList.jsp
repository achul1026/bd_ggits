<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">분석 리포트</h3>
            <div class="side_txt">
                데이터 활용 <br> 통계 분석 리포트 입니다.
            </div>
            <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/analysis/traffic/report/list.do" onclick="startLoading()">교통정보 통계 리포트</a>
			     <a href="${pageContext.request.contextPath}/statistics/analysis/data/report/list.do" class="on" onclick="startLoading()">데이터 활용 통계 리포트</a>
            </div>
        </div>
    </aside>
    <section class="main_section">
        <h2 class="blind">분석 리포트</h2>
    	<form id="searchForm" method="get">
	        <div class="table_btn_wrap flex-between">
		        <div class="search_container">
			        <div class="search_head">
			            <div class="search_number">
			                <span id="totalCnt"></span>개의 검색결과를 찾았습니다.
			            </div>
			        </div>
		        </div>	        
	            <div class="btn_search_wrap">
	                <ul>
	                	<li>
                		  	 <input type="text" id="searchContent" name="searchContent" placeholder="제목을 입력하세요." class="input_same search_box" value="<c:out value='${searchOption.searchContent}'/>">
	                	</li>
	                	<li>
                		  	<input id="searchBtn" type="button" value="검색" class="input_same search_box2">
	                	</li>
	                	<c:if test="${authCd eq 'AUC000'}">
		                	<li>
	                		  	<button type="button" class="is-darkgreen-btn" id="uploadButton">등록하기</button>
		                	</li>
		                	<li>
	                		  	<a href="http://192.168.13.26" type="button" class="is-darkgreen-btn mj0" target="_blank;">보고서 만들기</a>
		                	</li>
	                	</c:if>
	                </ul>
	              
	            </div>
	        </div>
		</form>
        <div>
			<table>
				<thead>
					<tr>
					    <th scope="col">번호</th>
					    <th scope="col">제목</th>
					    <th scope="col">등록자</th>
					    <th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty gOpDataUseStatsRptList}">
						<c:forEach var="gOpDataUseStatsRpt" items="${gOpDataUseStatsRptList}">
							<tr onclick="viewRptDetail('<c:out value='${gOpDataUseStatsRpt.rptId}'/>','<c:out value='${gOpDataUseStatsRpt.rptTitle}'/>')">
								<td><c:out value='${gOpDataUseStatsRpt.rownum}'/></td>
								<td><c:out value='${gOpDataUseStatsRpt.rptTitle}'/></td>
								<td><c:out value='${gOpDataUseStatsRpt.oprtrNm}(${gOpDataUseStatsRpt.oprtrEmail})'/></td>
								<td><c:out value='${gOpDataUseStatsRpt.registYmd}'/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">
								조회된 데이터 활용 리포트 정보가 없습니다.
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
        </div>
    </section>
</div>

<script>
	$(document).ready(function() {
		$('#searchContent').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});

	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))

	function viewRptDetail(rptId,rtpTitle){
	    new ModalBuilder().init(rtpTitle).ajaxBody("${pageContext.request.contextPath}/common/modal/data/report/"+rptId+"/list.do").footer(1,'푸터없음',function(button, modal){}).open();
	}
	
	//분석 리포트 작성
	<c:if test="${authCd eq 'AUC000'}">
	$(function(){
		let isClick = true;
		$('#uploadButton').on('click', function(){
		    new ModalBuilder().init('데이터 활용 통계 리포트 작성').ajaxBody("${pageContext.request.contextPath}/common/modal/data/analysis/report/save.do").footer(1,'등록하기',function(button, modal){
	    		var rptTitle = $("#rptTitle").val();
	    		var dtlUrl = $("#dtlUrl").val();
	    		
	    		var obj = new Object();
	    		obj.rptTitle = rptTitle;
	    		obj.dtlUrl = dtlUrl;
	    		
	    		$.ajax({
	    			type : "post",
	    			dataType : "json",
	    			contentType : "application/json; charset=UTF-8",
	    			data : JSON.stringify(obj),
	    			url : "${pageContext.request.contextPath}/statistics/analysis/data/report/save.ajax",
	    			success : function(data) {
	    				if(data.code == 200){
	    					new ModalBuilder().init().successBody(data.message).footer(4,'확인',function(button, modal){
	    						modalAlertWrap();
	    					    modal.close();
		    					location.href = "${pageContext.request.contextPath}/statistics/analysis/data/report/list.do";
	    						}).open();
	    				} else {
	    					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){
		    					modalAlertWrap();
		    					}).open();
	    					isClick = true;
	    				}
	    			}
	    		});
		    }).open();
		   	$('.modal_footer').removeClass('none');
		});
	})
	</c:if>
	
	$("#searchBtn").on("click", function(){
		$("#searchForm").submit();
	})
	
	function fnSearchList() {
		document.getElementById("searchForm").action = "${pageContext.request.contextPath}/statistics/analysis/data/report/list.do";
		document.getElementById("searchForm").submit();
	}
</script>
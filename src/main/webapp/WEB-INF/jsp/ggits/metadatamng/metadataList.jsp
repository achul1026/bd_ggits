<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="body-bg">
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">메타데이터 관리</h3>
                    <div class="side_txt">메타데이터 등록 및 관리를<br>
                        할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">메타데이터 관리</h2>         
                <form id="searchFrm" name="searchFrm" action="${pageContext.request.contextPath}/metadata/manage/list.do" method="get">
                <input type="hidden" id="page" name="page" value="1"/>
	                <div class="table_btn_wrap">
	                    <div class="table_btn_left flex-between">
	                    	<div class="btn_search_wrap">
	                    		<ul>
<!-- 	                    			<li> -->
<!-- 	                    				 <select class="selectBox big_selcetbox fncOnchange" id="clschmId" name="clschmId"> -->
<!-- 				                            <option value="">분류 체계</option> -->
<%-- 				                            <c:if test="${not empty clschmIdList}"> --%>
<%-- 					                            <c:forEach var="clschmId" items="${clschmIdList}" varStatus="status"> --%>
<%-- 						                            <option value="<c:out value='${clschmId}'/>" ${searchOption.clschmId eq clschmId ? 'selected':''}><c:out value='${clschmNmList[status.index]}'/></option> --%>
<%-- 					                            </c:forEach> --%>
<%-- 				                            </c:if> --%>
<!-- 				                        </select> -->
<!-- 	                    			</li> -->
	                    			<li>
	                    				<select class="selectBox fncOnchange" id="dataType" name="dataType">
				                            <option value="">데이터 유형 선택</option>
				                            <c:if test="${not empty dataTypeList}">
					                            <c:forEach var="dataType" items="${dataTypeList}">
						                            <option value="<c:out value='${dataType}'/>" ${searchOption.dataType eq dataType ? 'selected':''}><c:out value='${dataType}'/></option>
					                            </c:forEach>
				                            </c:if>
				                        </select>
	                    			</li>
<!-- 	                    			<li> -->
<!-- 	                    				 <select class="selectBox fncOnchange" id="opngDataListNm" name="opngDataListNm"> -->
<!-- 				                            <option value="">수집 유형 선택</option> -->
<%--                 				            <c:if test="${not empty collTyCdList}"> --%>
<%-- 					                            <c:forEach var="collTyCdList" items="${collTyCdList}"> --%>
<%-- 						                            <option value="<c:out value='${collTyCdList.cdId}'/>" ${searchOption.opngDataListNm eq collTyCdList.cdId ? 'selected':''}><c:out value='${collTyCdList.cdNm}'/></option> --%>
<%-- 					                            </c:forEach> --%>
<%-- 					                        </c:if> --%>
<!-- 				                        </select> -->
<!-- 	                    			</li> -->
	                    		</ul>
	                    	</div>
		                    <div class="flex-center">
		                        <div class="btn_search_wrap">
		                        	<ul>
		                        		<li>
		                        			<select class="selectBox" id="searchType" name="searchType">
				                                <option value="">전체</option>
				                                <option value="title" ${searchOption.searchType eq 'title' ? 'selected':''}>제목</option>
				                                <option value="writer" ${searchOption.searchType eq 'writer' ? 'selected':''}>등록자</option>
				                                <option value="keyword" ${searchOption.searchType eq 'keyword' ? 'selected':''}>키워드</option>
				                            </select>
		                        		</li>
		                        		<li>
		                        			<input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" id="searchContent" name="searchContent" value="<c:out value='${searchOption.searchContent}'/>">
		                        		</li>
		                        		<li>
		                        			<button type="button" id="searchBtn" class="input_same search_box2">검색</button>
		                        		</li>
		                        		<c:if test="${authCd eq 'AUC000'}">
			                        		<li>
			                        			<a href="${pageContext.request.contextPath}/metadata/manage/save.do" class="is-darkgreen-btn mj0">등록하기</a>
			                        		</li>
		                        		</c:if>
		                        	</ul>
		                        </div>
		                    </div>
	                    </div>
	                </div>
                </form>                
                <c:choose>
                	<c:when test="${not empty metaDataList}">
				<div class="search_container metadatamng">
                    <div class="search_head">
                        <div class="search_number mj0">
                            <span id="totalCnt"><c:out value='${paging.totalCount}'/></span>개의 검색결과를 찾았습니다.
                        </div>
                    </div>
                    <c:forEach var="metaDataList" items="${metaDataList}">
	                   <div class="search_bigbox pointer" onclick="location.href='${pageContext.request.contextPath}/metadata/manage/${metaDataList.tblId}/detail.do'">
	                        <div class="search_wrap is-hover">
	                            <div class="search_txt"><c:out value='${metaDataList.tblKoreanNm}'/></div>
	                            <h5 class="search_title"><c:out value='${metaDataList.tblEngNm}'/></h5>
	                            <div class="search_ft_box">
	                                <div class="search_day_data_box">
	                                	<fmt:formatDate var="updtDt" pattern="yyyy년 MM월 dd일" value="${metaDataList.updtDt}"/>
	                                    <div class="fertilization">수정일 : <c:out value='${updtDt}'/></div>
	                                    <div class="registrant">등록자 : <c:out value='${metaDataList.tblOwnrNm}'/></div>
	                                	<c:if test="${metaDataList.tblType eq 'GPDB' or metaDataList.tblType eq 'NDAP'}">
		                                	<c:choose>
		                                    	<c:when test="${metaDataList.fileExstYn == 'Y'}">
				                                    <div class="tblType">유형 : DATABASE, FILE</div>	                                    	                                    	
		                                    	</c:when>
		                                    	<c:otherwise>
		                                    		<div class="tblType">유형 : DATABASE</div>
		                                    	</c:otherwise>
		                                    </c:choose>	                                		
	                                	</c:if>
	                                </div>
	                                <div class="search_file_list">
	                                	<c:forEach var="collData" items="${metaDataList.collDataList}">
		                                    <button class="mr8 is-dark-btn pointer-not"><c:out value='${collData}'/></button>
	                                	</c:forEach>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
              		<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
                	</c:when>
                	<c:otherwise>
		                <div class="search_null_container mt88">
		                    <div class="search_null">검색 결과가 존재 하지 않습니다. 검색조건 및 검색어를 다시 입력해 주세요. </div>
		                </div>
                	</c:otherwise>
                </c:choose>
            </section>
        </div>
    </main>
</body>


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
	
	$("#searchBtn").on('click',function(){
		$("#searchFrm").submit();
	});
	
	$(".fncOnchange").on('change',function(){
		$("#searchFrm").submit();
	});
	
	function fnSearchList(){
		document.getElementById('searchFrm').action= "${pageContext.request.contextPath}/metadata/manage/list.do";
		document.getElementById('searchFrm').submit();
	}		
</script>

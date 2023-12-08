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
                <form id="searchFrm" name="searchFrm" action="/metadatamng/list.do" method="get">
	                <div class="table_btn_wrap clearfix">
	                    <div class="table_btn_left float-left">
	                        <select class="selectBox" id="clschmId" name="clschmId">
	                            <option value="">분류 체계</option>
	                            <c:forEach var="clschmId" items="${clschmIdList}">
		                            <option value="${clschmId}" ${searchOption.clschmId eq clschmId ? 'selected':''}>${clschmId}</option>
	                            </c:forEach>
	                        </select>
	                        <select class="selectBox" id="dataType" name="dataType">
	                            <option value="">데이터 유형 선택</option>
	                            <c:forEach var="dataType" items="${dataTypeList}">
		                            <option value="${dataType}" ${searchOption.dataType eq dataType ? 'selected':''}>${dataType}</option>
	                            </c:forEach>
	                        </select>
	                        <select class="selectBox" id="opngDataListNm" name="opngDataListNm">
	                            <option value="">수집 유형 선택</option>
	                            <c:forEach var="collTyCdList" items="${collTyCdList}">
		                            <option value="${collTyCdList.cdId}" ${searchOption.opngDataListNm eq collTyCdList.cdId ? 'selected':''}>${collTyCdList.cdNm}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="flex-end">
	                        <div class="btn_search_wrap float-none">
	                            <select class="selectBox" id="searchType" name="searchType">
	                                <option value="">전체</option>
	                                <option value="title" ${searchOption.searchType eq 'title' ? 'selected':''}>제목</option>
	                                <option value="writer" ${searchOption.searchType eq 'writer' ? 'selected':''}>등록자</option>
	                                <option value="keyword" ${searchOption.searchType eq 'keyword' ? 'selected':''}>키워드</option>
	                            </select>
	                            <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" id="searchContent" name="searchContent" value="${searchOption.searchContent}">
	                            <button type="button" id="searchBtn" class="input_same search_box2">검색</button>
	                        </div>
	                    </div>
	                </div>
                </form>
                <c:choose>
                	<c:when test="${not empty metaDataList}">
				<div class="search_container mt16">
                    <div class="search_head">
                        <div class="search_number">
                            <span>"${paging.totalCount}개"</span>의 검색결과를 찾았습니다.
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/metadatamng/save.do" class="is-darkgreen-btn mj0">등록하기</a>
                        </div>
                    </div>
                    <c:forEach var="metaDataList" items="${metaDataList}">
	                   <div class="search_bigbox pointer" onclick="location.href='${pageContext.request.contextPath}/metadatamng/${metaDataList.tblId}/detail.do'">
	                        <div class="search_wrap is-hover">
	                            <h5 class="search_title">${metaDataList.tblEngNm}</h5>
	                            <div class="search_txt">${metaDataList.tblKoreanNm}</div>
	                            <div class="search_ft_box">
	                                <div class="search_day_data_box">
	                                <!-- 컬럼확인필요 -->
	                                	 <fmt:formatDate var="updtDt" pattern="yyyy년 MM월 dd일" value="${metaDataList.updtDt}"/>
	                                    <div class="fertilization">수정일 : ${updtDt}</div>
	                                    <div class="registrant">등록자 : ${metaDataList.tblOwnrNm}</div>
	                                </div>
	                                <!-- 컬럼확인필요 -->
	                                <div class="search_file_list">
	                                	<c:forEach var="collData" items="${metaDataList.collDataList}">
		                                    <span class="mr8 is-dark-btn pointer-not">${collData}</span>
	                                	</c:forEach>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
<!-- 	                    <div class="search_bigbox"> -->
<!-- 	                        <div class="search_wrap"> -->
<!-- 	                            <h5 class="search_title">BUS_STATION_MASTER</h5> -->
<!-- 	                            <div class="search_txt">경기도 BIS 데이터베이스 정류소 마스터 정보</div> -->
<!-- 	                            <div class="search_ft_box"> -->
<!-- 	                                <div class="search_day_data_box"> -->
<!-- 	                                    <div class="fertilization">수정일 : 2022년 10월 23일</div> -->
<!-- 	                                    <div class="registrant">등록자 : (홍길동)admin0423@gyeonggi.com</div> -->
<!-- 	                                </div> -->
<!-- 	                                <div class="search_download"> -->
<!-- 	                                    <button type="button" class="mr8 is-dark-btn">JSON</button> -->
<!-- 	                                    <button type="button" class="is-dark-btn">XML</button> -->
<!-- 	                                </div> -->
<!-- 	                            </div> -->
<!-- 	                        </div> -->
<!-- 	                    </div> -->
<!--                     <div class="search_bigbox"> -->
<!--                         <div class="search_wrap"> -->
<!--                             <h5 class="search_title">BIS_ROUTE_INFO</h5> -->
<!--                             <div class="search_txt">경기도 BIS 시스템 버스노선 마스터 정보</div> -->
<!--                             <div class="search_ft_box"> -->
<!--                                 <div class="search_day_data_box"> -->
<!--                                     <div class="fertilization">수정일 : 2022년 10월 23일</div> -->
<!--                                     <div class="registrant">등록자 : (홍길동)admin0423@gyeonggi.com</div> -->
<!--                                 </div> -->
<!--                                 <div class="search_download"> -->
<!--                                     <button type="button" class="mr8 is-dark-btn">JSON</button> -->
<!--                                     <button type="button" class="is-dark-btn">XML</button> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                </div>
              		<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
                	</c:when>
                	<c:otherwise>
		                <div class="search_null_container mt88">
		                    <div class="register_box">
		                       <a href="${pageContext.request.contextPath}/metadatamng/save.do" class="is-darkgreen-btn">등록하기</a>
		                    </div>
		                    <div class="search_null">검색 결과가 존재 하지 않습니다. 검색조건 및 검색어를 입력해 주세요.</div>
		                </div>
                	</c:otherwise>
                </c:choose>
            </section>
        </div>
    </main>
</body>


<script>
	$("#searchBtn").on('click',function(){
		$("#searchFrm").submit();
	});
</script>

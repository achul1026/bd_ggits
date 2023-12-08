<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">권한 관리</h3>
                    <div class="side_txt">권한을 미리 설정하여 사용자별<br>권한을 지정할 수 있습니다.<br><br>각 권한을 클릭하여 수정하실 수<br>있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">권한 관리</h2>
               	<form id="searchForm" method="get">
                <div class="table_btn_wrap clearfix">
                    <div class="flex-end">
                    		<input type="hidden" id="page" name="page"  value="1"/>
	                        <div class="btn_search_wrap float-none">
	                            <select class="selectBox float-left" id="searchType" name="searchType">
	                                <option value="all" ${searchType eq '' or searchType eq 'all'? 'selected':''}>전체</option>
	                                <option value="authNm" ${searchType eq 'authNm'? 'selected':''}>권한 이름</option>
	                                <option value="descr" ${searchType eq 'descr'? 'selected':''}>권한 설명</option>
	                            </select>
	                            <input type="text" id="searchContent" name="searchContent" placeholder="검색어를 입력하세요." class="input_same search_box" value="${searchContent}">
	                            <button type="button" id="searchBtn" class="input_same search_box2" onclick="fnSearchList();">검색</button>
	                            <a href="${pageContext.request.contextPath}/system/auth/save.do" class="is-darkgreen-btn mj0">등록하기</a>
	                        </div>
                    </div>
                </div>
               	</form>
                
                <table>
                    <colgroup>
                        <col style="width:10%">
                        <col style="width:23%">
                        <col style="width:23%">
                        <col style="width:10%">
                        <col style="width:16.5%">
                        <col style="width:16.5%">
                    </colgroup>
                    <tr>
                      <th scope="col">번호</th>
                      <th scope="col">권한 이름</th>
                      <th scope="col">권한 설명</th>
                      <th scope="col">권한 그룹 수</th>
                      <th scope="col">수정일</th>
                      <th scope="col">등록일</th>
                    </tr>
                  	<c:forEach var="authList" items="${authList}">
	                    <tr onclick="detailView('${authList.authId}')" class="pointer">
	                        <td>${authList.rownum}</td>
	                        <td>${authList.authNm}</td>
	                        <td>${authList.descr}</td>
	                        <td>${authList.grpCnt}</td>
	                        <fmt:formatDate var="updtDt" pattern="yyyy-MM-dd" value="${authList.updtDt}"/>
	                        <td>${updtDt}</td>
	                        <fmt:formatDate var="crtDt" pattern="yyyy-MM-dd" value="${authList.crtDt}"/>
	                        <td>${crtDt}</td>
	                    </tr>
                  	</c:forEach>
                  </table>
                  <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
            </section>
        </div>
    </main>

<script>
	$("#searchBtn").on('click',function(){
		fnSearchList();
	});
	
	function detailView(authId){
	    location.href = ${pageContext.request.contextPath}"/system/auth/"+authId+"/detail.do";
	}
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/system/auth/list.do";
		document.getElementById('searchForm').submit();
	}
</script>

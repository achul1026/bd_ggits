<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
		<div class="snb_wrap">
			<h3 class="side_title">사용자 그룹 관리</h3>
			<div class="side_txt">
				그룹 정보를 확인하거나<br>수정할 수 있습니다.
			</div>
			<a href="${pageContext.request.contextPath}/system/user/list.do" class="is-darkgreen-btn">사용자 관리하기</a>
		</div>
	</aside>
	<section class="main_section">
		<h2 class="blind">사용자 그룹 관리</h2>
		<form id="searchForm" method="get">
			<input type="hidden" id="page" name="page" value="1"/>
			<div class="table_btn_wrap clearfix">
				<div class="flex-end">
					<div class="mr8">
	             		<input type="text" name="strDt" id="strDt" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요." value="${strDt}">
	                    ~
	                    <input type="text" name="endDt" id="endDt" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요." value="${endDt}">
					</div>
					<div class="btn_search_wrap float-none">
						<div class="search_head">그룹명</div>
						<input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" name="searchContent" value="${searchContent}">  
						<input type="button" value="검색" class="input_same search_box2" onclick="fnSearchList();"> 
						<a href="${pageContext.request.contextPath}/system/user/group/save.do" class="is-darkgreen-btn mj0">등록하기</a>
					</div>
				</div>
			</div>
		</form>

		<table>
			<colgroup>
				<col style="width: 7%">
				<col style="width: 23%">
				<col style="width: 23%">
				<col style="width: 14%">
				<col style="width: 18%">
				<col style="width: 18%">
			</colgroup>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">그룹명</th>
				<th scope="col">권한</th>
				<th scope="col">그룹원 수</th>
				<th scope="col">수정일</th>
				<th scope="col">등록일</th>
			</tr>
			<c:forEach var="groupList" items="${groupList}">
				<tr onclick="location.href='${pageContext.request.contextPath}/system/user/group/${groupList.grpId}/detail.do';" class="pointer">
					<td>${groupList.rownum}</td>
					<td>${groupList.grpNm}</td>
					<td>${groupList.authNm}</td>
					<td>${groupList.userCnt}</td>
					<td><fmt:formatDate value="${groupList.updtDt}" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${groupList.crtDt}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	</section>
</div>
<script type="text/javascript">
			
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/system/user/group/list.do";
		document.getElementById('searchForm').submit();
	}				
</script>
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
				<div class="btn_search_wrap btn_search_wrap_left float-right">
					<ul>
						<li>
							<input type="text" name="strDt" id="strDt" class="date_picker input_same input_picker" placeholder="날짜를 선택해주세요." value="<c:out value='${strDt}'/>" autocomplete="off">
						</li>
						<li>
							~
						</li>
						<li>
							<input type="text" name="endDt" id="endDt" class="end_date_picker input_same input_picker" placeholder="날짜를 선택해주세요." value="<c:out value='${endDt}'/>" autocomplete="off">
						</li>
						<li>
							그룹명 : <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box" name="searchContent" value="<c:out value='${searchContent}'/>">  
						</li>
						<li>
							<input type="button" value="검색" class="input_same search_box2 pointer" onclick="fnSearchList();"> 
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/system/user/group/save.do" class="is-darkgreen-btn mj0">등록하기</a>
						</li>
					</ul>
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
				<tr onclick="location.href='${pageContext.request.contextPath}/system/user/group/<c:out value="${groupList.grpId}"/>/detail.do';" class="pointer">
					<td><c:out value="${groupList.rownum}"/></td>
					<td><c:out value="${groupList.grpNm}"/></td>
					<td><c:out value="${groupList.authNm}"/></td>
					<td><c:out value="${groupList.userCnt}"/></td>
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
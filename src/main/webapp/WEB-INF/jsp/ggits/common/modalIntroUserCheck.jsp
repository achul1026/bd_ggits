<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab_container">
	<div class="">
		<ul class="">
			<li class="modal_layout_item"><input type="checkbox" id="GG001" value="GG001" class="cntnSystemCd"><label for="GG001" >GIMS(돌발 정보 시스템)</label></li>
			<li class="modal_layout_item"><input type="checkbox" id="GG002" value="GG002" class="cntnSystemCd"><label for="GG002" >광역 긴급차량 신호 및 이동 현황</label></li>
			<li class="modal_layout_item"><input type="checkbox" id="GG003" value="GG003" class="cntnSystemCd"><label for="GG003" >빅데이터 분석 플랫폼</label></li>
			<li class="modal_layout_item"><input type="checkbox" id="GG004" value="GG004" class="cntnSystemCd"><label for="GG004" >시각화 리포트</label></li>
			<li class="modal_layout_item"><input type="checkbox" id="GG005" value="GG005" class="cntnSystemCd"><label for="GG005" >시각화 분석</label></li>
			<li class="modal_layout_item"><input type="checkbox" id="GG006" value="GG006" class="cntnSystemCd"><label for="GG006" >공간정보 제어 시스템</label></li>
		</ul>
	</div>
</div>


<script>
	<c:forEach var="menuItem" items="${menuList}" varStatus="status">
		var cntnSystemCd = '<c:out value="${menuItem.cntnSystemCd}"/>';
		var useYn = '<c:out value="${menuItem.useYn}"/>';
		if(useYn == 'Y'){
			$("#"+cntnSystemCd).prop("checked",true);
		}
	</c:forEach>
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">서버 정보 조회</h3>
            <div class="side_txt">서버 정보를 조회합니다.</div>
        </div>
    </aside>
    <section class="main_section tab_set">
        <h2 class="blind">서버 정보 조회</h2>
        <div class="table_btn_wrap clearfix tab_fc">
            <div class="btn_search_wrap float-left">
            	<c:forEach var="ctgryCd" items="${ctgryCdList}">
                	<button type="button" class="tab_btn_item is-dark-btn ${srvrMngType eq ctgryCd.cdId ? 'is-darkgreen-btn' : ''}" onclick="moveCtgry('${ctgryCd.cdId}')">${ctgryCd.cdNm}</button>
            	</c:forEach>
            </div>
        </div>
        <div class="facility_container tab_area">
            <div class="tab">
            	<c:forEach var="serverInfo" items="${serverInfoList}">
	                <div class="flex-between">
	                    <div class="facility_title">${serverInfo.srvrNm}</div>
	                    <label class="flex-center">
	                        <span>${serverInfo.srvrSttsCd}</span>
	                        <input role="switch" type="checkbox" class="facility_input ml16" ${serverInfo.procsCtrlCd eq 'Y' ? 'checked':''}/>
	                    </label>
	                </div>
            	</c:forEach>
            </div>
        </div>
    </section>
</div>

<script>
	$('.facility_input').on('change', function(){
		if($(this).is(':checked')){
			$(this).siblings('span').text('정상');
		} else {
			$(this).siblings('span').text('중지');
		}
	})
	
	function moveCtgry(cdId){
		location.href="${pageContext.request.contextPath}/facility/server/info/list.do?srvrMngType="+cdId;
	}
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="busViolatnInfoModalForm">
    <div class="tab_item_box flex-center">
        <h5 class="tab_item_title">시군구</h5>
        <select class="selectBox radius result_change change-detect" <%--onchange="getDongListBySggCd(this.value, '#sggDongSelect')"--%> name="districtGnm">
            <option value="">시군구 전체</option>
            <c:forEach var="sggCd" items="${sggCdList}" varStatus="status">
                <option value="<c:out value='${sggCd.cdNm}'/>"><c:out value='${sggCd.cdNm}'/></option>
            </c:forEach>
        </select>
    </div>
    <div class="tab_item_box flex-center">
        <h5 class="tab_item_title">버스종류</h5>
        <select class="selectBox radius result_change change-detect" id="routeTp" name="routeTp">
            <option value="">전체</option>
            <option value="RTC000">시외버스</option>
            <option value="RTC001">일반버스</option>
            <option value="RTC002">마을버스</option>
            <option value="RTC003">광역버스</option>
            <option value="RTC004">공항버스</option>
        </select>
    </div>
    <div class="tab_item_box flex-center">
        <h5 class="tab_item_title">버스번호</h5>
        <input type="text" class="dashboard_input radius mr4" name="routeNm" id="schRouteNm" placeholder="버스번호를 입력해주세요.">
        <button type="button" class="is-darkgreen-btn ml8" id="searchBtn">찾기</button>
        <input type="hidden" id="mapPage" name="page" value="1"/>
    </div>
</form>
<div class="tab_box_body_wrap" style="width:650px;">
    <div class="node_title" style="margin-bottom:5px;">
        버스이탈정보가 <span id="totalCnt2">${totalCnt}</span>건이 존재합니다.
    </div>
    <table class="monitor_table">
        <colgroup>
            <col style="width:20%">
            <col style="width:20%">
            <col style="width:20%">
            <col style="width:20%">
            <col style="width:20%">
        </colgroup>
        <thead>
        <tr>
            <th>노선번호</th>
            <th>차량번호</th>
            <th>운수회사명</th>
            <th>지역</th>
            <th>발생일시</th>
        </tr>
        </thead>
        <tbody id="modalTbody">
        <c:choose>
            <c:when test="${fn:length(list) > 0}">
                <c:forEach var="bus" items="${list}" varStatus="status">
                    <tr>
                        <td>[<c:out value='${bus.routeTp}'/>]<br/><c:out value='${bus.routeNm}'/></td>
                        <td><c:out value="${bus.plateNo}"/></td>
                        <td><c:out value="${bus.companyNm}"/></td>
                        <td><c:out value="${bus.districtGnm ? bus.districtGnm : bus.districtSnm}"/></td>
                        <td><c:out value="${bus.clctDt}"/></td>

                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5">데이터가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <div id="modalPaging">
        <%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
    </div>
</div>


<script>
	$(document).ready(function() {
		$('#schRouteNm').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});

    $("#searchBtn").on('click', function(){
        $("#mapPage").val("1");
        fnSearchList();
    });

    let loadingAjax = null;
    function fnSearchList(){
        if(loadingAjax) loadingAjax.abort();
        loadingAjax = $.ajax({
            type : "get",
            data : $("#busViolatnInfoModalForm").serialize(),
            url : "${pageContext.request.contextPath}/map/monitoring/bus/M_BUS_002/data.ajax",
            beforeSend : function(){
                gitsApp.startLoading();
            },
            error : function(){
                gitsApp.endLoading();
            },
            success : function(result) {
                gitsApp.endLoading();
                $("#modalTbody > tr").remove();
                $("#modalPaging > .dashboard-pg-wrap").remove();
                var html = '';
                var title = '';
                var totalCnt = result.data.totalCnt;
                if(totalCnt > 0){
                    $(result.data.list).each(function(index, item){
                        html += '<tr>'+
                            '<td>['+item.routeTp+']<br/>'+item.routeNm+'</td>'+
                            '<td>'+item.plateNo+'</td>'+
                            '<td>'+item.companyNm+'</td>'+
                            '<td>'+(item.districtGnm ? item.districtGnm : item.districtSnm)+'</td>'+
                            '<td>'+item.clctDt+'</td>'+
                            '</tr>';
                    })
                }else{
                    html += '<tr>'+
                        '<td colspan="5">데이터가 없습니다.</td>'+
                        '</tr>';
                }
                $("#totalCnt2").text(totalCnt ? numberComma(totalCnt) : 0);
                $("#modalTbody").html(html)
                var paging = result.data.paging;
                if(paging != null && paging != '' && totalCnt > 0){
                    $("#modalPaging").html(getGisPagingHtml(paging,$("#mapPage").val()));
                }
            }
        });
    }

</script>
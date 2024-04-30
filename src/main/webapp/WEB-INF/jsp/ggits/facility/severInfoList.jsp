<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">연계시설물 서버</h3>
            <div class="side_txt">각 지자체별 연계시설물<br>서버 정보를 조회합니다.</div>
        </div>
    </aside>
    <section class="main_section tab_set">
        <h2 class="blind">연계 시설물 서버</h2>
       
        <div class="facility_container tab_area">
			<div>
	        	<select class="selectBox" id="mngInstCd" onchange="fnChangeMngInstCd(this);">
	        		<c:forEach var="mngInstCdList" items="${mngInstCdList}">
	                	<option value="<c:out value='${mngInstCdList.cdId}'/>" ${searchInfo.mngInstCd eq mngInstCdList.cdId ? 'selected="selected"' : ''} ><c:out value='${mngInstCdList.cdNm}'/></option>
					</c:forEach>
	        	</select>
	        </div>
            <div class="tab" id="serverList">
            	<c:choose>
            		<c:when test="${fn:length(serverInfoList) ne 0}">
            			<c:forEach var="serverInfo" items="${serverInfoList}">
			                <div class="flex-between">
			                    <div class="facility_title"><c:out value='${serverInfo.asctnSrvrId}'/></div>
			                    <div class="flex-center gap8">
			                    	<span class="server_circle <c:out value='${serverInfo.cntnSttsCd eq "007001" ? "server_normalcy_color" : "server_error_color"}'/>"></span>
			                    	<span><c:out value='${serverInfo.cntnSttsCd eq "007001" ? "정상" : "비정상"}'/></span>
			                    </div>
			                </div>
		            	</c:forEach>
            		</c:when>
            		<c:otherwise>
            			<div class="flex-between">
		                    <div class="facility_title">서버 정보가 존재하지 않습니다.</div>
		                    <div class="flex-center gap8">
		                    	<span></span>
		                    </div>
		                </div>
            		</c:otherwise>
            	</c:choose>
            	
            </div>
        </div>
    </section>
</div>

<script>
$(function() {
	//30초마다 데이터 재호출
	timer = setInterval( function () {
	    $.ajax ({
	        "url" : "${pageContext.request.contextPath}/facility/server/info/list.ajax",
	        data : { "mngInstCd" : $("#mngInstCd").val()}, 
	        cache : false,
	        success : function (result) {
				$("#serverList").children().remove();
	        	var resultList = result.data;
	        	var html = "";
	        	if(resultList.length > 0){
		        	$(resultList).each(function(idx,item){
		        		var serverClassCheck = 'server_error_color';
		        		var serverTextCheck = '비정상';
		        		if(item.cntnSttsCd == '007001'){
		        			serverClassCheck = 'server_normalcy_color';
		        			serverTextCheck = '정상';
		        		}
		        		html += '<div class="flex-between">'+
				                    '<div class="facility_title">'+item.asctnSrvrId+'</div>'+
				                    '<div class="flex-center gap8">'+
				                    	'<span class="server_circle '+serverClassCheck+'"></span>'+
				                    	'<span>'+serverTextCheck+'</span>'+
				                    '</div>'+
				                '</div>';
		        	})
	        	}else{
	        		html = '<div class="flex-between">'+
                    			'<div class="facility_title">서버 정보가 존재하지 않습니다.</div>'+
                    			'<div class="flex-center gap8">'+
                    				'<span></span>'+
                    			'</div>'+
                			'</div>';
	        	}
	        	$("#serverList").append(html);
	        }
	    });
	}, 30000); 
	
});

	function fnChangeMngInstCd(_this){
		var _thisVal = $(_this).val();
		location.href="${pageContext.request.contextPath}/facility/server/info/list.do?mngInstCd="+_thisVal;
	}
</script>
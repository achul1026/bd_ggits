<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="urlSplit" value="${fn:split(requestScope['javax.servlet.forward.servlet_path'],'.')}" />
<c:set var="urlFirst" value="${urlSplit[0]}" />
<c:set var="parentsUrlSplit" value="${fn:split(urlFirst,'/')}" />
<c:set var="parentsUrl" value="${parentsUrlSplit[0]}" />
<c:set var="loginUser" value="${sessionScope['mOpOperatorInfo']}"/>
<c:set var="today" value="${sessionScope['today']}"/>
<header class="header ${fn:startsWith(urlFirst, '/monitoring') || fn:startsWith(urlFirst, '/bigdata') || fn:startsWith(urlFirst, '/basicinfo/dashboard') || fn:startsWith(urlFirst, '/facility/dashboard') ? 'ver1' : 'ver2'}">
    <div class="header_container">
        <div class="logo_box">
            <h1 class="logo">
                <a href="${pageContext.request.contextPath}/monitoring.do" class="logo_title">
                    <img src="${pageContext.request.contextPath}/statics/images/hd_logo.png" alt="경기도 교통 빅데이터 시스템">
                </a>
            </h1>
        </div>
        <div class="utility_box flex-center">
           <div class="hd_monitor_box flex-center">
	           <div class="monitor_link ${urlFirst ne '/monitoring/dashboard'?'monitor_link1':''}">
	               <a href="${pageContext.request.contextPath}/monitoring.do"><img src="${pageContext.request.contextPath}/statics/images/index.png" alt="메인페이지"></a>
	           </div>
	           <div class="monitor_link ${urlFirst eq '/monitoring/dashboard'?'monitor_link1':''}">
	               <a href="${pageContext.request.contextPath}/monitoring/dashboard.do"><img src="${pageContext.request.contextPath}/statics/images/monitor_dash.png" alt="모니터링대쉬보드"></a>
	           </div>
            </div>
            <c:if test="${parentsUrl == 'monitoring'}">
	            <div>
	                <label class="flex-center">
	                    <span>날씨정보</span>
	                    <input role="switch" type="checkbox" class="facility_input" id="weatherInfoBtn"/>
	                </label>
	            </div>
            </c:if>
            
            <div class="day_weather_box flex-center">
                <div class="day_data mr8">${today}</div>
            </div>
            <div class="email_box flex-center">
                <span class="email_name mr8">${fn:substring(loginUser.oprtrNm, 0, 1)}</span>
                <div class="email">${loginUser.oprtrEmail}</div>
            </div>
            <div class="setting">
                <button type="button" class="userinfo_btn"><img src="${pageContext.request.contextPath}/statics/images/setting.png" alt="설정"></button>
                <div class="userinfo_box none">
                	<div class="userinfo"><a href="${pageContext.request.contextPath}/system/user/${loginUser.oprtrId}/detail.do">마이페이지</a></div>
                	<div class="userinfo"><a href="javascript:void(0)" onclick="logout(this);">로그아웃</a></div>
                </div>
            </div>
        </div>
    </div>
    <nav class="nav_container">
    	<c:if test="${not empty gnbMenuDTO}">
        <ul class="gnb">
        	<c:forEach var="gnbMenuDTO" items="${gnbMenuDTO}">
				<c:set var="menuUrlSplit" value="${fn:split(gnbMenuDTO.urlAddr,'.')}" />
				<c:set var="menuUrlFirst" value="${menuUrlSplit[0]}" />
				<c:set var="mainMenuUrlSplit" value="${fn:split(menuUrlSplit[0],'/')}" />
				<c:set var="mainMenuUrl" value="${mainMenuUrlSplit[0]}" />
            <li class="depth1 ${parentsUrl == mainMenuUrl ? 'active' : ''}">
<%-- ${urlFirst} --%>
<%-- 				${parentsUrl}-${mainMenuUrl} --%>
             	<a href="${pageContext.request.contextPath}${gnbMenuDTO.urlAddr}">${gnbMenuDTO.menuNm}</a>
                <div class="depth2_wrap ${parentsUrl == mainMenuUrl ? 'active' : ''}">
                    <c:choose>
                    	<c:when test="${gnbMenuDTO.categCd ne null}">
                    		<c:choose>
                    			<c:when test="${gnbMenuDTO.categCd eq 'MCC000'}">
                    				<c:forEach var="menuCtgry" items="${menuCtgryList}" varStatus="status">
	                    				<div class="depth2_item_box">
					                        <h4 class="depth2_title">${menuCtgry.cdNm}</h4>
					                        <ul class="depth2_box">
					                        <c:forEach var="subMenuList" items="${gnbMenuDTO.mOpMenuList}">
					                    		<c:if test="${menuCtgry.cdId eq subMenuList.categCd}">
						                            <li class="depth2_item${subMenuList.sbmnuSortNo eq 1?' svg_active the_first':''}">
							                            <button type="button" onclick="gitsApp.drawMenu('${subMenuList.menuId}','${subMenuList.menuPttrnType}')" data-id="${subMenuList.menuId}" data-depth="${subMenuList.sbmnuSortNo}"><img src="${pageContext.request.contextPath}/statics/images${subMenuList.asctnInfo}" alt="${subMenuList.menuNm}">
							                            	<span>${subMenuList.menuNm}</span>
							                            </button>
						                            </li>
												</c:if>
					                        </c:forEach>
					                        </ul>
										</div>
                    				</c:forEach>
                    			</c:when>
                    			<c:otherwise>
		                  			<div class="depth2_item_box">
				                        <ul class="depth2_box">
				                           	<c:forEach var="subMenuList" items="${gnbMenuDTO.mOpMenuList}">
					                        	<c:choose>
					                        		<c:when test="${subMenuList.cdDivCd eq 'CDC000'}">
						                           		<c:set var="subUrlPttrn" value="${fn:replace(subMenuList.urlPttrn, '/**', '')}"/>
								                    	<c:set var="subUrlAddrSplit" value="${fn:split(subMenuList.urlAddr,'.')}" />
														<c:set var="subUrlFirst" value="${subUrlAddrSplit[0]}" />
							                            <li class="depth2_item ${urlFirst eq subUrlFirst or fn:indexOf(urlFirst,subUrlPttrn) != -1 ? 'svg_active':''}">
								                            <a href="${pageContext.request.contextPath}${subMenuList.urlAddr}"><img src="${pageContext.request.contextPath}/statics/images${subMenuList.asctnInfo}" alt="${subMenuList.menuNm}">
								                            	<span>${subMenuList.menuNm}</span>
								                            </a>
							                            </li>
							                        </c:when>
							                        <c:otherwise>
							                    		<li class="depth2_item ${fn:startsWith(urlFirst, '/facility/server')? 'gis_link':''} ${subMenuList.sbmnuSortNo eq 1 and !fn:startsWith(urlFirst, '/facility/server') ? 'svg_active':''}">
								                            <button type="button" onclick="gitsApp.drawMenu('${subMenuList.menuId}','${subMenuList.menuPttrnType}')" data-id="${subMenuList.menuId}" data-depth="${subMenuList.sbmnuSortNo}"><img src="${pageContext.request.contextPath}/statics/images${subMenuList.asctnInfo}" alt="${subMenuList.menuNm}">
								                            	<span>${subMenuList.menuNm}</span>
								                            </button>
							                            </li>
							                        </c:otherwise>
							                    </c:choose>
					                        </c:forEach>
				                        </ul>
									</div>
                    			</c:otherwise>
                    		</c:choose>
                    	</c:when>
                    	<c:otherwise>
		                    <div class="depth2_item_box">
		                        <ul class="depth2_box">
		                           	<c:forEach var="subMenuList" items="${gnbMenuDTO.mOpMenuList}">
				                    	<c:set var="subUrlAddrSplit" value="${fn:split(subMenuList.urlAddr,'.')}" />
										<c:set var="subUrlFirst" value="${subUrlAddrSplit[0]}" />
			                            <li class="depth2_item ${urlFirst eq subUrlFirst ? 'svg_active':''}">
				                            <a href="${pageContext.request.contextPath}${subMenuList.urlAddr}"><img src="${pageContext.request.contextPath}/statics/images${subMenuList.asctnInfo}" alt="${subMenuList.menuNm}">
				                            	<span>${subMenuList.menuNm}</span>
				                            </a>
			                            </li>
			                        </c:forEach>
		                        </ul>
							</div>
                    	</c:otherwise>
                    </c:choose>
					</div>
            </li>
        	</c:forEach>
        </ul>
        <div class="depth2_open_box">
            <button type="button" class="depth2_open_btn"></button>
        </div>
    </c:if>
	    <!-- 특정페이지 스크립트 작업  -->
	<c:if test="${fn:startsWith(urlFirst, '/basicinfo/nodelink')}">
    	<script>
			$(document).ready(function(){
				$(".svg_active button").attr("onclick","location.href='/basicinfo/dashboard.do'");
			});
     	</script>
    </c:if>
    <c:if test="${fn:startsWith(urlFirst, '/facility/server')}">
    	<script>
			$(document).ready(function(){
				$(".gis_link button").attr("onclick","location.href='/facility/dashboard.do'");
			});
     	</script>
    </c:if>
    </nav>
</header>
<script type="text/javascript">

$("#weatherInfoBtn").on("click",function(){
	gitsApp.callMenuAction('EVC003',$(this).is(":checked"))
})

function logout(_this){
	if(confirm("로그아웃 하시겠습니까?")){
			$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/logout.ajax",
			success : function(data) {
				if(data.code == '200'){
					location.href = "${pageContext.request.contextPath}/login.do";
				} else {
					alert(data.message);
				}
			}
		});	
	}
}

$(window).load(function(){
	var activeBtn = $(".svg_active");
	var firstUrl = '${urlFirst}'
	switch(firstUrl){
	case "/monitoring":
		$(".svg_active").eq(0).find('button').trigger('click');
		break;
	case "/bigdata/dashboard":
		$(".svg_active").eq(1).find('button').trigger('click');
		break;
	case "/basicinfo/dashboard":
		$(".svg_active").eq(2).find('button').trigger('click');
		break;
	case "/facility/dashboard":
		$(".svg_active").eq(3).find('button').trigger('click');
		break;
	default : 
		break;
	}
});
</script>
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
                <a href="${pageContext.request.contextPath}/monitoring/dashboard.do" class="logo_title">
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
            <c:if test="${parentsUrl == 'monitoring' && urlFirst != '/monitoring/dashboard'}">
	            <div>
	                <label class="flex-center">
	                    <span>날씨정보</span>
	                    <input role="switch" type="checkbox" class="facility_input" id="weatherInfoBtn"/>
	                </label>
	            </div>
            </c:if>
			<div>
				<label class="flex-center">
					<span>시군영역 표시</span>
					<input role="switch" type="checkbox" class="facility_input" id="sggLayerToggleBtn" checked/>
				</label>
			</div>
            <div class="day_weather_box flex-center">
                <div class="day_data"><c:out value="${today}"/></div>
            </div>
            <div class="email_box flex-center">
                <div class="email"><c:out value="${loginUser.oprtrEmail}"/></div>
            </div>
            <div class="setting">
                <button type="button" class="userinfo_btn"><img src="${pageContext.request.contextPath}/statics/images/setting.png" alt="설정"></button>
                <div class="userinfo_box none">
                	<div class="userinfo">
                		<a href="${pageContext.request.contextPath}/system/user/<c:out value="${loginUser.oprtrId}"/>/detail.do">
                			<img src="/statics/images/mypage_icon.png">마이페이지
                		</a>
                	</div>
                	<div class="userinfo">
                		<a href="javascript:void(0)" onclick="logout(this);">
                			<img src="/statics/images/logout_icon.png">로그아웃 
                		</a>
                	</div>
					<div class="userinfo">
						<a href="https://gits.gg.go.kr/web/main/index.do" target="_blank">
							경기도 교통정보센터 포털
						</a>
					</div>
                </div>
            </div>
        </div>
    </div>
    <nav class="nav_container">
    	<c:if test="${not empty gnbMenuDTO and urlFirst ne '/monitoring/dashboard'}">
        <ul class="gnb">
        	<c:forEach var="gnbMenuDTO" items="${gnbMenuDTO}">
				<c:set var="menuUrlSplit" value="${fn:split(gnbMenuDTO.urlAddr,'.')}" />
				<c:set var="menuUrlFirst" value="${menuUrlSplit[0]}" />
				<c:set var="mainMenuUrlSplit" value="${fn:split(menuUrlSplit[0],'/')}" />
				<c:set var="mainMenuUrl" value="${mainMenuUrlSplit[0]}" />
            <li class="depth1 ${parentsUrl == mainMenuUrl ? 'active' : ''}">
             	<a href="${pageContext.request.contextPath}${gnbMenuDTO.urlAddr}"><c:out value="${gnbMenuDTO.menuNm}"/></a>
                <div class="depth2_wrap <c:out value="${parentsUrl == mainMenuUrl ? 'active' : ''}"/>">
                <c:if test="${parentsUrl == mainMenuUrl}">
                    <c:choose>
                    	<c:when test="${gnbMenuDTO.categCd ne null}">
                    		<c:choose>
                    			<c:when test="${gnbMenuDTO.categCd eq 'MCC000'}">
                    				<c:forEach var="menuCtgry" items="${menuCtgryList}" varStatus="status">
	                    				<div class="depth2_item_box">
					                        <h4 class="depth2_title"><c:out value="${menuCtgry.cdNm}"/></h4>
					                        <ul class="depth2_box">
					                        <c:forEach var="subMenuList" items="${gnbMenuDTO.mOpMenuList}" varStatus="status">
					                    		<c:if test="${menuCtgry.cdId eq subMenuList.categCd}">
						                            <li class="depth2_item<c:out value="${status.index eq 0?' svg_active':''}"/>" data-pttern-type="<c:out value="${subMenuList.menuPttrnType}"/>">
						                            <c:choose>
						                            		<c:when test="${subMenuList.menuPttrnType eq 'EVC010' or subMenuList.menuPttrnType eq 'EVC001' or subMenuList.menuPttrnType eq 'EVC016'}">
						                            			<c:if test="${subMenuList.menuPttrnType eq 'EVC010'}">
							                            			<a href="http://192.168.11.25:28080/ndap/v2#/dashboard/main" target="_blank;">
							                            				<img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
							                            				<span><c:out value="${subMenuList.menuNm}"/></span>
							                            			</a>
						                            			</c:if>
						                            			<c:if test="${subMenuList.menuPttrnType eq 'EVC001' or subMenuList.menuPttrnType eq 'EVC016'}">
							                            			<a href="http://192.168.21.77:3000/login" target="_blank;">
							                            				<img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
							                            				<span><c:out value="${subMenuList.menuNm}"/></span>
							                            			</a>
						                            			</c:if>
						                            		</c:when>
						                            		<c:otherwise>
						                            			<button type="button" onclick="gitsApp.drawMenu('<c:out value="${subMenuList.menuId}"/>','<c:out value="${subMenuList.menuPttrnType}"/>')" data-id="<c:out value="${subMenuList.menuId}"/>" data-depth="<c:out value="${subMenuList.sbmnuSortNo}"/>"><img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
									                            	<span><c:out value="${subMenuList.menuNm}"/></span>
									                            </button>
						                            		</c:otherwise>
					                            		</c:choose>
							                            
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
														<li class="depth2_item <c:out value="${urlFirst eq subUrlFirst or fn:indexOf(urlFirst,subUrlPttrn) != -1 and subMenuList.menuPttrnType ne 'EVC010' ? 'svg_active':''}"/>" data-pttern-type="<c:out value="${subMenuList.menuPttrnType}"/>">
					                        			<c:choose>
					                        				<c:when test="${subMenuList.menuPttrnType eq 'EVC010'}">
					                        					<a href="http://192.168.11.25:28080/ndap/v2#/dashboard/main" target="_blank;">
						                            				<img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
						                            				<span><c:out value="${subMenuList.menuNm}"/></span>
						                            			</a>
					                        				</c:when>
					                        				<c:otherwise>
										                            <a href="${pageContext.request.contextPath}<c:out value="${subMenuList.urlAddr}"/>"><img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
										                            	<span><c:out value="${subMenuList.menuNm}"/></span>
										                            </a>
					                        				</c:otherwise>
					                        			</c:choose>
					                        			</li>
							                        </c:when>
							                        <c:otherwise>
							                        	<li class="depth2_item <c:out value="${fn:startsWith(urlFirst, '/facility/server')? 'gis_link':''}"/> <c:out value="${subMenuList.sbmnuSortNo eq 1 and !fn:startsWith(urlFirst, '/facility/server') and subMenuList.menuPttrnType ne 'EVC001' ? 'svg_active':''}"/>" data-pttern-type="<c:out value="${subMenuList.menuPttrnType}"/>">
							                        	<c:choose>
								                        	<c:when test="${subMenuList.menuPttrnType eq 'EVC001' or subMenuList.menuPttrnType eq 'EVC016'}">
						                            			<a href="http://192.168.21.77:3000/login" target="_blank;">
						                            				<img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
						                            				<span><c:out value="${subMenuList.menuNm}"/></span>
						                            			</a>
						                            		</c:when>
								                        	<c:otherwise>
									                            <button type="button" onclick="gitsApp.drawMenu('<c:out value="${subMenuList.menuId}"/>','<c:out value="${subMenuList.menuPttrnType}"/>')" data-id="<c:out value="${subMenuList.menuId}"/>" data-depth="<c:out value="${subMenuList.sbmnuSortNo}"/>"><img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
									                            	<span><c:out value="${subMenuList.menuNm}"/></span>
									                            </button>
								                        	</c:otherwise>
							                        	</c:choose>
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
                       						<c:set var="subUrlPttrn" value="${fn:replace(subMenuList.urlPttrn, '/**', '')}"/>
					                    	<c:set var="subUrlAddrSplit" value="${fn:split(subMenuList.urlAddr,'.')}" />
											<c:set var="subUrlFirst" value="${subUrlAddrSplit[0]}" />
				                            <li class="depth2_item ${urlFirst eq subUrlFirst or fn:indexOf(urlFirst,subUrlPttrn) != -1 ? 'svg_active':''}">
					                            <a href="${pageContext.request.contextPath}<c:out value="${subMenuList.urlAddr}"/>"><img src="${pageContext.request.contextPath}/statics/images<c:out value="${subMenuList.asctnInfo}"/>" alt="<c:out value="${subMenuList.menuNm}"/>">
					                            	<span><c:out value="${subMenuList.menuNm}"/></span>
					                            </a>
				                            </li>
			                        </c:forEach>
		                        </ul>
							</div>
                    	</c:otherwise>
                    </c:choose>
				</c:if>
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
$("#sggLayerToggleBtn").on("click",function(){
	if($(this).is(":checked")) {
		window.map.control.showLayer(GITS_ENV.LAYER.SGG);
		window.map.control.showLayer(GITS_ENV.LAYER.SGG_LINE);
	}else{
		window.map.control.hideLayer(GITS_ENV.LAYER.SGG);
		window.map.control.hideLayer(GITS_ENV.LAYER.SGG_LINE);
	}
});
$("#congestionLayerToggleBtn").on("click", function(){
	if($(this).is(":checked")) {
		window.map.control.showLayer(GITS_ENV.LAYER.LINK);
	}else{
		window.map.control.hideLayer(GITS_ENV.LAYER.LINK);
	}
})
function logout(_this){
	new ModalBuilder().init().alertBoby("로그아웃 하시겠습니까?").footer(5,'로그아웃',function(button, modal){
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/logout.ajax",
			success : function(data) {
				if(data.code == '200'){
					location.href = "${pageContext.request.contextPath}/login.do";
				} else {
					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();					
				}
			}
		});	
	},'취소하기',function(button, modal){
		modal.close();
	}).open();
}
		

$(window).load(function(){
	var activeBtn = $(".svg_active");
	var items = $('.depth2_item');
	var menuPttrnType = activeBtn.data('pttern-type');
	switch(menuPttrnType){
	case "EVC001":
		items.removeClass("svg_active");
		break;
	case "EVC010":
		items.removeClass("svg_active");
		break;
	case "EVC016":
		items.removeClass("svg_active");
		break;
	case "EVC021":
		<c:if test="${!fn:startsWith(urlFirst, '/basicinfo/nodelink')}">
			activeBtn.eq(0).find('button').trigger('click');
		</c:if>
		break;
	default : 
		activeBtn.eq(0).find('button').trigger('click');
		break;
	}
});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="urlSplit" value="${fn:split(requestScope['javax.servlet.forward.servlet_path'],'.')}" />
<div id="map-container">
    <div id="map" ></div>
    <div id="compare_map"></div>

    <div class="control_container">
        <ul class="control_box">
            <li>
                <ul>
                    <li class="flex-center gap8">
                        <button type="button" class="zoom" onclick="slideToggleByTarget('#mapThemeSelector')">테마설정</button>
                        <button type="button" class="zoom" onclick="map.control.zoomOut()"><img src="${pageContext.request.contextPath}/statics/images/control_minus.png" alt="마이너스" class="minus"></button>
                        <button type="button" id="currentMapZoom" class="zoom">5</button>
                        <button type="button" class="zoom plus" onclick="map.control.zoomIn()"><img src="${pageContext.request.contextPath}/statics/images/control_plus.png" alt="플러스"></button>
                        <button type="button" class="zoom" onclick="map.control.setDefaultPanTilt()">초기화</button>
                    </li>
                </ul>
            </li>
            <li class="control_on mt8">
                <ul id="mapThemeSelector" style="display:none;">
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('DARK')">다크</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('LIGHT')">라이트</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('SATELLITE')">지형</button> </li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('STREET')">STREET</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('NAVIGATION')">NAVI</button></li>
                </ul>
            </li>
        </ul>
        <c:if test="${fn:startsWith(requestScope['javax.servlet.forward.servlet_path'], '/monitoring.do')}">
            <div id="layerControlWrap">
                <a href="javascript:void(0)" id="layerControlButton" onclick="toggleClassByTarget('#layerControlList-wrapper', 'on')" title="레이어 설정">
                    <img src="${pageContext.request.contextPath}/statics/images/layers.png" alt="레이어 설정">
                </a>
                <div id="layerControlList-wrapper" class="on">
                    <ul id="layerControlList">
                        <li>
                            <label class="">
                                <span>소통정보</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onchange="gitsApp.mapToggleLayer(this)" data-layer="trf" checked="">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>노드</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onchange="gitsApp.mapToggleLayer(this)" data-layer="node" checked="">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>긴급차량</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="emrg">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>위험물차량</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="dngr" >
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>돌발현황</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="warn" checked>
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>실시간버스</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="bus">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>스마트교차로-교통량</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="smc">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>VDS-교통량</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="vds">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>DSRC</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="dsrc">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>상시교통량(국도)</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="ordtmNlrd">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>수시교통량(국도)</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="anytmNlrd">
                            </label>
                        </li>
                        <li>
                            <label class="">
                                <span>수시교통량(고속도로)</span>
                                <input role="switch" type="checkbox" class="facility_input is-lightgrey" onclick="this.checked ? this.disabled = true : void(0)" onchange="gitsApp.mapToggleLayer(this)" data-layer="anytmHghw">
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
        </c:if>
    </div>

    <%-- 좌측 메뉴 및 컨텐츠 모달 --%>
    <div id="asideSectionWrap"></div>
    <%-- 맵 데이터 로딩 및 Job 액션 --%>
    <div id="mapDataLoadingJobList"></div>

    <div id="bigdataDetailInfoContainer" class="bigdataDetailInfoContainer">
        <button id="bigdataDetailInfoToggleButton" class="bigdataDetailInfoToggleButton" type="button" onclick="toggleClassByTarget('#bigdataDetailInfoContainer', 'off')">
            <img src="${pageContext.request.contextPath}/statics/images/white_arrow.png" alt="숨기기">
        </button>
        <div id="bigdataDetailContentWrap" class="bigdataDetailContentWrap">

        </div>
    </div>

    <div id="bigdataDetailInfoContainerDual" class="bigdataDetailInfoContainer">
        <button id="bigdataDetailInfoToggleButtonDual" class="bigdataDetailInfoToggleButton" type="button" onclick="toggleClassByTarget('#bigdataDetailInfoContainerDual', 'off')">
            <img src="${pageContext.request.contextPath}/statics/images/white_arrow.png" alt="숨기기">
        </button>
        <div id="bigdataDetailContentWrapDual" class="bigdataDetailContentWrap">

        </div>
    </div>

   	
    <div class="chart_video_container">
        <div class="tab_box_header chart_video_header">
            <div class="tab_box_title">시간대별 위험등급 발생빈도</div>
            <div class="tab_box_close">
                <div class="chart_video_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all mt4">
                    <div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
                    <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
                </div>
                <div>
                    <img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="monitor_tab_close dashboard_close">
                </div>
            </div>
        </div>
        <div class="chart_video_body">
            <div class="chart_video_txt">
                <span>화양로</span>
                <div>18:00 ~ 19:00</div>
                <div>
                    <span>운행속도 :</span>
                    <span>145(km/h)</span>
                </div>
            </div>
            <div class="chart_video_box">
                <div class="chart_video"></div>
                <div>
                    <canvas id="map_chart_canvas"></canvas>
                </div>
            </div>
        </div>
        <div class="chart_play_footer">
            <div class="play_box">
                <div class="chart_play svg_active">
                	<button type="button"><img src="${pageContext.request.contextPath}/statics/images/play.svg" alt="재생"></button>
                </div>
                <div class="chart_stop">
                	<button type="button"><img src="${pageContext.request.contextPath}/statics/images/stop.svg" alt="멈춤"></button>
                </div>
            </div>
        </div>
    </div>
</div>
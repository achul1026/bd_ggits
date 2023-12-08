<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div id="map-container">
    <div id="map" ></div>
    <div id="compare_map"></div>

    <div class="control_container">
        <div class="control_open_box">
            <button type="button" class="control_open_btn"></button>
        </div>
        <ul class="control_box">
            <li>
                <ul>
                    <li class="flex-center gap8">
                        <button type="button" class="zoom" onclick="map.control.zoomOut()"><img src="/statics/images/control_minus.png" alt="마이너스" class="minus"></button>
                        <button type="button" id="currentMapZoom" class="zoom">5</button>
                        <button type="button" class="zoom plus" onclick="map.control.zoomIn()"><img src="/statics/images/control_plus.png" alt="플러스"></button>
                    </li>
                </ul>
            </li>
            <li class="control_on mt8 none">
                <ul>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('DARK')">다크</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('LIGHT')">라이트</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('SATELLITE')">지형</button> </li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('STREET')">STREET</button></li>
                    <li class="side_item"><button type="button" class="is-control-btn" onclick="map.control.setStyle('NAVIGATION')">NAVI</button></li>
                </ul>
            </li>
        </ul>
    </div>

    <%-- 좌측 메뉴 및 컨텐츠 모달 --%>
    <div id="asideSectionWrap"></div>
    <%-- 맵 데이터 로딩 및 Job 액션 --%>
    <div id="mapDataLoadingJobList"></div>

    <%-- 맵 범례 모달 --%>
<!--     <div id="dataRemarkModalWrap" class="remarks_container">
        <div id="dataRemarkModalHeader" class="remarks_title_box" onclick="gitsApp.toggleRemarkModal()">
            <h6 class="remarks_title rotate">범례</h6>
        </div>
        <div id="dataRemarkContent" class="remarks_wrap" style="display: none;">
            <div>
                <div>
                    <select id="remarkKeyList" class="selectBox radius">

                    </select>
                </div>
                <div id="remarkLegendsList" class="check_line_container">

                </div>
            </div>
            <div class="remarks_setting flex-between mt16">
                <div>단위 : (<span id="legendUnit"></span>)</div>
            </div>
        </div>
    </div> -->
        
   	
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
                <div>차트 들어갈곳 입니다</div>
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
	<div class="my_layout_btn none">
		나의 레이아웃${layoutNo}
	</div>
</div>
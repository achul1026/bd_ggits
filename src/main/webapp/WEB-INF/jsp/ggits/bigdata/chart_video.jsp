<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="chart_video_container">
    <div class="tab_box_header chart_video_header">
        <div class="tab_box_title">시간대별 위험등급 발생빈도</div>
        <div class="tab_box_close">
            <div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
                <div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
                <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
            </div>
            <div class="chart_box_zoom">
                <img src="../statics/images/chart_zoom.png"> 
            </div>
            <div>
                <img src="../statics/images/wh_close.png" class="monitor_tab_close dashboard_close"> 
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
            <div class="chart_play"><img src="/statics/images/play.png" alt="재생"></div>
            <div class="chart_stop"><img src="/statics/images/stop.png" alt="멈춤"></div>
            <div>1.4X</div>
        </div>
    </div>
</div>
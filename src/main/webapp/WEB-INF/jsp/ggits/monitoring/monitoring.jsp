<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="main">
    <div class="main_container">
        <c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
    </div>
    <div class="monitoring_remarks_container">
        <div class="remarks_title_box">
            <h6 class="remarks_title">범례</h6>
        </div>
       	<div class="remarks_wrap tab-none">
           	<div>
                <div class="check_line_container">
                    <button type="button" class="check_line_box remarks-red">정체</button>
                    <button type="button" class="check_line_box remarks-orange">서행</button>
                    <button type="button" class="check_line_box remarks-green">원활</button>
                </div>
           	</div>
            <div class="unit">단위 : 혼잡도</div>
       	</div>
   	</div>    
    <div class="outbreak_container clearfix">
        <div class="outbreak_push_box">
            <ul id="outbreak_push" class="outbreak_push_item_box">

            </ul>
        </div>
        <div>
            <div class="outbreak_button_box">
                <button type="button" class="outbreak_button" onclick="outbreakList()"><img src="statics/images/outbreak.png" alt="돌발"></button>
            </div>
            <div class="outbreak_box none">
                <div class="flex-center gap4">
                    <div class="outbreak_list_button">
                        <button type="button">돌발 상황 발생 <span id="warningAlarmCount">8</span>건</button>
                    </div>
                    <div class="outbreak_list_button">
                        <button type="button">수집 장애 발생 <span id="errorAlarmCount">12</span>건</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script>

    // const map = new GITSMapCore("map").init("성남시");
    window.map = new GITSMapCore("map").init("MONITORING");
    $(function(){
        gitsApp.setMap(map);
    })
    
    legendToggle();
</script>
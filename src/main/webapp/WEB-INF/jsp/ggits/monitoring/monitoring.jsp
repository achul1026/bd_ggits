<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="main">
    <div class="main_container">
        <c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
    </div>
    <div class="monitoring_remarks_container">
        <div class="remarks_title_box">
            <h6 class="remarks_title">소통등급 범례</h6>
        </div>
       	<div class="remarks_wrap">
           	<div>
                <ul class="check_line_container">
                    <li class="check_line_box remarks-red">정체</li>
                    <li class="check_line_box remarks-orange">서행</li>
                    <li class="check_line_box remarks-green">원활</li>
                </ul>
           	</div>
            <div class="unit">
                단위 : 소통등급 / 수집원 : GITS<br/>
                <a href='javascript:void(0)' style="text-decoration: underline;color:white;" onclick="openCongestionGradeInfo()">등급정보보기</a>
            </div>
       	</div>
   	</div>
    <div id="dangerRemarks" class="monitoring_remarks_container remarks-position-2">
        <div class="remarks_title_box">
            <h6 class="remarks_title">돌발현황</h6>
        </div>
        <div class="remarks_wrap">
            <div>
                <ul class="check_line_container">
                    <div class="check_line_container">
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="2">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon2.png" alt=""><span>사고</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="6">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon6.png" alt=""><span>공사</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="13">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon13.png" alt=""><span>통제</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="8">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon3.png" alt=""><span>재난</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="3">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon8.png" alt=""><span>기상</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="12">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon12.png" alt=""><span>행사</span>
                        </div>
                        <div class="check_line_box check_border is-double is-triple danger-remark-btn is-active" data-incicate="1">
                            <img src="${pageContext.request.contextPath}/statics/images/outbreak_icon1.png" alt=""><span>기타</span>
                        </div>
                        <div style='clear:both;'></div>
                    </div>
                </ul>
            </div>
            <div class="unit">
                단위 : 돌발유형  / <a href="javascript:void(0)"  style="text-decoration: underline;color:white;" onclick="openWarningFilter()">수집원 필터</a>
            </div>
        </div>
    </div>
    <div id="weatherWarningDiv" class="monitoring_remarks_container remarks-position-3 none">
       	<p>
	    날씨 데이터는 실시간 관측된 자료이며 측정소 현지 사정이나 데이터의 수신상태에 따라 미수신 될 수 있음.
		<span>출처 : 환경부/한국환경공단</span>
		</p>
    </div>
    <div class="outbreak_container clearfix">
        <div class="outbreak_push_box">
            <ul id="outbreak_push" class="outbreak_push_item_box">

            </ul>
        </div>
        <div>
            <div class="outbreak_button_box">
                <button type="button" class="outbreak_button" onclick="outbreakList()"><img src="${pageContext.request.contextPath}/statics/images/outbreak.png" alt="돌발"></button>
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
        gitsApp.setMap(window.map);
        $(".danger-remark-btn").on("click", function(){
           $(this).toggleClass("is-active");
           window.map.monitoring.getWarningInfo(false);
        });
    })

    legendToggle();
</script>
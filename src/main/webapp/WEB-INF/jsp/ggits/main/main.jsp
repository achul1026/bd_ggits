<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <main class="main">
        <div class="main_container">
            <div id="map"></div>
            <div class="control_container">
                <div class="control_open_box">
                    <button type="button" class="control_open_btn"></button>
                </div>
                <ul class="control_box">
                    <li>
                        <ul>
                            <li class="flex-center gap8">
                                <button type="button" class="zoom"><img src="../statics/images/control_minus.png" alt="마이너스" class="minus"></button>
                                <button type="button" class="zoom">5</button>
                                <button type="button" class="zoom plus"><img src="../statics/images/control_plus.png" alt="플러스"></button>
                            </li>
                        </ul>
                    </li>
                    <li class="control_on mt8">
                        <ul>
                            <li class="side_item"><button type="button" class="is-control-btn">POI 검색</button></li>
                            <li class="side_item"><button type="button" class="is-control-btn">지역 설정</button></li>
                            <li class="side_item"><button type="button" class="is-control-btn">레이어 설정</button></li>
                            <li class="side_item"><button type="button" class="is-control-btn">ID 검색</button></li>
                            <li class="side_item"><button type="button" class="is-control-btn">범례</button></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="asideSectionWrap"></div>
        </div>
    </main>
<script>

    // const map = new GITSMapCore("map").init("성남시");
    const map = new GITSMapCore("map").init();
    $(function(){
        gitsApp.setMap(map);
        gitsApp.drawMenu();
    })
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>시스템 관리</title>
    <link rel="stylesheet" href="../statics/css/style.css">
    <script src="../statics/js/jquery-1.12.4.min.js"></script>
    <script src="../statics/js/jquery-ui.js"></script>
    <script src="../statics/js/common.js"></script>
    <script src="../statics/js/main.js"></script>
</head>
<body class="body-bg">
    <div id="skip" class="center">
        <a href="../index.html">메인페이지 바로가기</a>
    </div>
    <header class="header">
        <div class="header_container">
            <div class="logo_box">
                <h1 class="logo">
                    <a href="../index.html" class="logo_title">
                        <img src="../statics/images/hd_logo.png" alt="경기도 교통 빅데이터 시스템">
                    </a>
                </h1>
            </div>
            <div class="utility_box flex-center">
                <div class="danger_box flex-center">
                    <button class="danger_item mr8">
                        돌발 상황 발생
                        <span class="danger_number">30</span>
                    </button>
                    <button class="danger_item">
                        데이터 수집 이상
                        <span class="danger_number">10</span>
                    </button>
                </div>
                <div class="day_weather_box flex-center">
                    <div class="day_data mr8">2023년 12월 31일</div>
                    <div class="weather">맑음 10˚</div>
                </div>
                <div class="email_box flex-center">
                    <span class="email_name mr8">홍</span>
                    <div class="email">admin123@welcome.com</div>
                </div>
                <div class="setting">
                    <button type="button"><img src="../statics/images/setting.png" alt="설정"></button>
                </div>
            </div>
        </div>
        <nav class="nav_container">
            <ul class="gnb">
                <li class="depth1">
                    <a href="../index.html">모니터링</a>
                    <div class="depth2_wrap monitoring">
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">경기도 통합 교통 현황</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon01.svg" alt="교통현황"><span>교통현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon02.svg" alt="실시간교통신호"><span>실시간<br>교통신호</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon03.svg" alt="돌발현황"><span>돌발현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon04.svg" alt="기상현황"><span>기상현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon05.svg" alt="광역 긴급차량이동현황"><span>광역긴급차량<br>이동현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon06.svg" alt="시내버스이동형황"><span>시내버스<br>이동현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon07.svg" alt="위험예측구간현황"><span>위험예측<br>구간현황</span></button></li>
                            </ul>
                        </div>
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">수집 시스템 운영 현황</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon08.svg" alt="연계대상데이터"><span>연계대상<br>데이터</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon09.svg" alt="데이터별수집이력"><span>데이터별<br>수집이력</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon10.svg" alt="수집장애시알림"><span>수집장애시<br>알림</span></button></li>
                            </ul>
                        </div> 
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">서비스 운영 현황</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon11.svg" alt="서비스운영현황"><span>서비스<br>운영현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon12.svg" alt="유스케이스접속현황"><span>유스케이스<br>접속현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon13.svg" alt="대상별접속현황"><span>대상별<br>접속현황</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon14.svg" alt="데이터갱신현황"><span>데이터<br>갱신현황</span></button></li>
                            </ul>
                        </div>
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">빅데이터 운영</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon15.svg" alt="WEB시스템"><span>WEB<br>시스템</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon16.svg" alt="WAS시스템"><span>WAS<br>시스템</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon17.svg" alt="MEXUSRepository"><span>NEXUS<br>Repository</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon18.svg" alt="DB시스템"><span>DB<br>시스템</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon19.svg" alt="분석가공/사고예측"><span>분석가공/<br>사고예측</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon20.svg" alt="분석가공/메타데이터"><span>분석가공/<br>메타데이터</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon21.svg" alt="저장시스템"><span>저장시스템</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon22.svg" alt="분석/시각화솔루션서버"><span>분석/시각화<br>솔루션서버</span></button></li>
                            </ul>
                        </div>
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">운반차량 모니터링</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon23.svg" alt="지자차별연계상태"><span>지자체별<br>연계상태</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/monitor_icon24.svg" alt="외부기관연계상태"><span>외부기관<br>연계상태</span></button></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../big_data/big_data.html">빅데이터 분석</a>
                    <div class="depth2_wrap big_data">
                        <div class="depth2_item_box">
                            <h4 class="depth2_title">교통 패턴 분석</h4>
                            <ul class="depth2_box">
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon01.svg" alt="경기도최고/최저교통량패턴분석"><span>교통패턴분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon02.svg" alt="정체구간분석"><span>교통활동<br>효과분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon03.svg" alt="정체구간개선효과"><span>교통위험<br>구간분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon04.svg" alt="교통예측분석"><span>교통예측분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon05.svg" alt="도로안전정보분석"><span>교통신호<br>연동분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon06.svg" alt="교통사고위험지역정보분석"><span>대중교통<br>이용현황 분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon07.svg" alt="교차로 교통량 예측 분석"><span>버스위험운영<br>구간분석</span></button></li>
                                <li class="depth2_item"><button type="button"><img src="statics/images/bigdata_icon08.svg" alt="사고예측구간분석"><span>대중교통<br>예측분석</span></button></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../system/system.html">시스템 관리</a>
                    <div class="depth2_wrap system">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="system.html"><img src="../statics/images/system_icon01.svg" alt="사용자관리"><span>사용자 관리</span></a></li>
                                <li class="depth2_item"><a href="menu.html"><img src="../statics/images/system_icon02.svg" alt="메뉴관리"><span>메뉴 관리</span></a></li>
                                <li class="depth2_item"><a href="auth.html"><img src="../statics/images/system_icon03.svg" alt="권한관리"><span>권한 관리</span></a></li>
                                <li class="depth2_item"><a href="operate.html"><img src="../statics/images/system_icon04.svg" alt="운영관리"><span>운영 관리</span></a></li>
                                <li class="depth2_item"><a href="code.html"><img src="../statics/images/system_icon05.svg" alt="코드관리"><span>코드 관리</span></a></li>
                                <li class="depth2_item"><a href="parameter.html"><img src="../statics/images/system_icon06.svg" alt="파라미터관리"><span>파라미터관리</span></a></li>
                                <li class="depth2_item"><a href="open_api.html"><img src="../statics/images/system_icon07.svg" alt="OpenAPI관리"><span>Open API 관리</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../basic/basic_dashboard.html">기초 정보 조회</a>
                    <div class="depth2_wrap basic">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="basic_dashboard.html"><img src="../statics/images/info_icon01.svg" alt="표준노드링크조회"><span>표준 노드/링크 조회</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../facility/facility_dashbord.html">연계 시설물 조회</a>
                    <div class="depth2_wrap facility">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="facility_dashbord.html"><img src="../statics/images/facility_icon01.svg" alt="사용자관리"><span>시설물 정보 조회</span></a></li>
                                <li class="depth2_item"><a href="facility_server.html"><img src="../statics/images/facility_icon02.svg" alt="메뉴관리"><span>서버 정보 조회</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../record/record.html">이력 관리</a>
                    <div class="depth2_wrap record">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="record.html" class="depth2_on_active"><img src="../statics/images/history_icon01.svg" alt="수집이력"><span>수집이력</span></a></li>
                                <li class="depth2_item"><a href="record_server_control.html"><img src="../statics/images/history_icon02.svg" alt="서버 제어 이력"><span>서버 제어 이력</span></a></li>
                                <li class="depth2_item"><a href="record_obstacle.html"><img src="../statics/images/history_icon03.svg" alt="서버 장애 이력"><span>서버 장애 이력</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../stats/traffic_dblist.html">통계 분석</a>
                    <div class="depth2_wrap stats">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="traffic_dblist.html"><img src="../statics/images/statistical_icon01.svg" alt="교통 DB화통계"><span>교통 DB화통계</span></a></li>
                                <li class="depth2_item"><a href="traffic_statistics.html"><img src="../statics/images/statistical_icon02.svg" alt="교통정보 통계분석"><span>교통정보 통계분석</span></a></li>
                                <li class="depth2_item"><a href="data_statistics.html"><img src="../statics/images/statistical_icon03.svg" alt="데이터 활용통계"><span>데이터 활용통계</span></a></li>
                                <li class="depth2_item"><a href="analysis_report.html"><img src="../statics/images/statistical_icon04.svg" alt="분석 리포트"><span>분석 리포트</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="depth1">
                    <a href="../meta_data/meta_data.html">메타데이터 관리</a>
                    <div class="depth2_wrap meta_data">
                        <div class="depth2_item_box">
                            <ul class="depth2_box">
                                <li class="depth2_item"><a href="meta_data.html"><img src="../statics/images/metadata_icon01.svg" alt="메타데이터관리"><span>메타데이터 관리</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="depth2_open_box">
                <button type="button" class="depth2_open_btn"></button>
            </div>
        </nav>
    </header>
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 그룹 정보</h3>
                    <div class="side_txt">그룹 정보를 확인하고<br> 수정할수 있습니다.</div>
                    <a href="#" class="is-darkgreen-btn">사용자 관리하기</a>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 관리</h2>

                <div class="contents_wrap">
                    <div class="group">
                        <div class="group_text">그룹명 (필수)</div>
                        <input type="text" placeholder="그룹명을 입력하세요." class="input_same group_box">
                    </div>
                    <div class="group">
                        <div class="group_text">그룹설명</div>
                        <input type="text" placeholder="그룹 설명을 입력하세요." class="input_same group_box">
                    </div>
                    <div class="group">
                        <div class="group_text">그룹 사용자 (필수)</div>
                        <input type="text" placeholder="그룹에 속할 사용자를 선택하세요." class="input_same group_box"> <input type="button" value="찾기" class="on_input_same btn_modal">
                    </div>

                    <div class="group_on">
                        <!-- 테이블 -->
                        <table>
                            <colgroup>
                                <col style="width:20%">
                                <col style="width:20%">
                                <col style="width:20%">
                                <col style="width:20%">
                                <col style="width:20%">

                            </colgroup>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">사용자 ID</th>
                                <th scope="col">이름</th>
                                <th scope="col">그룹</th>
                                <th scope="col">사용자 상태</th>
                            </tr>

                            <tr>
                                <td>5</td>
                                <td>교통정보센터장</td>
                                <td>7</td>
                                <td>정지</td>
                                <td>2023.12.31</td>
                            </tr>

                            <tr>
                                <td>4</td>
                                <td>교통정보센터장</td>
                                <td>7</td>
                                <td>정지</td>
                                <td>2023.12.31</td>
                            </tr>

                            <tr>
                                <td>3</td>
                                <td>교통정보센터장</td>
                                <td>7</td>
                                <td>정지</td>
                                <td>2023.12.31</td>
                            </tr>

                            <tr>
                                <td>2</td>
                                <td>교통정보센터장</td>
                                <td>7</td>
                                <td>정지</td>
                                <td>2023.12.31</td>
                            </tr>

                            <tr>
                                <td>1</td>
                                <td>교통정보센터장</td>
                                <td>7</td>
                                <td>정지</td>
                                <td>2023.12.31</td>
                            </tr>

                        </table>

                        <div class="page-wrap">
                            <ul class="pagination">
                                <li><a href="#" class="first">처음 페이지</a></li>
                                <li><a href="#" class="arrow left"><</a></li>
                                <li><a href="#" class="active num">1</a></li>
                                <li><a href="#" class="num">2</a></li>
                                <li><a href="#" class="num">3</a></li>
                                <li><a href="#" class="num">4</a></li>
                                <li><a href="#" class="num">5</a></li>
                                <li><a href="#" class="arrow right">></a></li>
                                <li><a href="#" class="last">마지막 페이지</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="group">
                        <div class="group_text">그룹 설정 (필수)</div>
                        <select class="selectBox" selected="selected">
                            <option>권한을 선택해 주세요.</option>
                            <option>test1</option>
                            <option>test2</option>
                            <option>test3</option>
                            <option>test4</option>
                            <option>test5</option>
                        </select>

                    </div>
                    <div class="group group_search">
                        <input type="button" value="등록" class="on_input_same search_box2">
                        <input type="button" value="취소" class="input_same">
                    </div>
                </div>
            </section>
        </div>
    </main>
    <!-- 모달 -->
    <div id="modal" class="modal_overlay">
        <div class="modal_wrap2">
            <div class="title">
                <h2>대상 찾기</h2>
                <div class="modal_close"></div>
            </div>
            <div class="modal_content target_find">
                <div class="modal_con1">
                    <select class="modal_select" selected="selected">
                        <option>전체</option>
                        <option>모잠비크</option>
                        <option>모잠비크</option>
                        <option>모잠비크</option>
                        <option>모잠비크</option>
                        <option>모잠비크</option>
                    </select>
                    <div class="modal_input_wrap">
                       <input type="text" placeholder="검색어 입력" class="modal_input_srbox">
                       <input type="button" value="검색" class="modal_input_srbtn">
                    </div>
                </div>
                <div class="table_text_on">
                    선택된 사용자 수 : 선택된 사용자가 없습니다.
                </div>

                <table class="modal_table">
                    <colgroup>
                        <col style="width:10%">
                        <col style="width:10%">
                        <col style="width:30%">
                        <col style="width:15%">
                        <col style="width:15%">
                        <col style="width:15%">
                    </colgroup>
                    <tr>
                      <th scope="col"><input type="checkbox"></td>
                      <th scope="col">번호</td>
                      <th scope="col">사용자 ID</td>
                      <th scope="col">이름</td>
                      <th scope="col">그룹</td>
                      <th scope="col">사용자 상태</td>
                    </tr>
                      <tr>
                        <td><input type="checkbox" ></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" ></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox"></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" ></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" ></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox"></td>
                        <td>성남시</td>
                        <td>교통정보센터장</td>
                        <td>7</td>
                        <td>정지</td>
                        <td>2023.12.31</td>
                      </tr>
                  </table>

                  <div class="modal_page_wrap">
                    <ul class="pagination">
                        <li><a href="#" class="first">처음 페이지</a></li>
                        <li><a href="#" class="arrow left"><</a></li>
                        <li><a href="#" class="active num">1</a></li>
                        <li><a href="#" class="num">2</a></li>
                        <li><a href="#" class="num">3</a></li>
                        <li><a href="#" class="num">4</a></li>
                        <li><a href="#" class="num">5</a></li>
                        <li><a href="#" class="arrow right">></a></li>
                        <li><a href="#" class="last">마지막 페이지</a></li>
                    </ul>
                  </div>

                  <div class="modal_btn">
                    <input type="button" value="확인">
                  </div>
            </div>
        </div>
    </div>
    <!-- 모달 -->

    <!-- 모달2 
    <div id="modal" class="modal_overlay">
        <div class="modal_wrap2">
            <div class="title">
                <h2 class="title_color">메인 메뉴 등록하기</h2>
                <div class="modal_close"></div>
            </div>

            <div class="modal_content">
                <div class="modal_con flex-center">
                    <div class="input_list">
                        <div class="input_title">메인 메뉴명 *</div>
                        <input type="text" placeholder="시스템 관리">
                    </div>
                    <div class="input_list">
                        <div class="input_title">노출여부</div>
                        <select class="modal_selectBox" selected="selected">
                            <option>예스</option>
                            <option>노</option>
                        </select>
                    </div>
                    <div class="input_list">
                        <div class="input_title">메인 메뉴 URL 패턴</div>
                        <input type="text" placeholder="시스템 관리">
                    </div>
                    <div class="input_list">
                        <div class="input_title">노출순위</div>
                        <input type="text" placeholder="시스템 관리">
                        <div class="input_sub_text">* 순위가 높을수록 메뉴가 앞에 표시됩니다.</div>
                    </div>
                </div>
              
                <div class="modal_btn">
                    <input type="button" value="등록">
                </div>
            </div>
        </div>
    </div> 
     -->
    <!-- 모달3 
    <div id="modal" class="modal_overlay">
        <div class="modal_wrap2">
            <div class="title">
                <h2 class="title_color">메인 메뉴 등록하기</h2>
                <div class="modal_close"></div>
            </div>

            <div class="modal_content">
                <div class="modal_con flex-center">
                    <div class="input_list">
                        <div class="input_title">메인 메뉴명 *</div>
                        <input type="text" placeholder="시스템 관리">
                    </div>
                    <div class="input_list">
                        <div class="input_title">노출여부</div>
                        <select class="modal_selectBox" selected="selected">
                            <option>예스</option>
                            <option>노</option>
                        </select>
                    </div>
                    <div class="input_list">
                        <div class="input_title">메인 메뉴 URL 패턴</div>
                        <input type="text" placeholder="시스템 관리">
                    </div>
                    <div class="input_list">
                        <div class="input_title">노출순위</div>
                        <input type="text" placeholder="시스템 관리">
                        <div class="input_sub_text">* 순위가 높을수록 메뉴가 앞에 표시됩니다.</div>
                    </div>
                </div>
            
                <div class="table_text_on">
                    선택된 사용자 수 : 선택된 사용자가 없습니다.
                </div>
                    <table class="modal_table">
                        <colgroup>
                            <col style="width:20%">
                            <col style="width:20%">
                            <col style="width:20%">
                            <col style="width:20%">
                            <col style="width:20%">
                        </colgroup>
                        <tr>
                            <th scope="col">노출 순위</th>
                            <th scope="col">서브 메뉴 명</th>
                            <th scope="col">URL</th>
                            <th scope="col">노출여부</th>
                            <th scope="col">삭제하기</th>
                        </tr>
                            <tr>
                            <td>1</td>
                            <td>박한수</td>
                            <td>www.naver.com</td>
                            <td>노출</td>
                            <td scope="col"><button type="button" class="table_delete">삭제하기</button></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>박한수</td>
                                <td>www.naver.com</td>
                                <td>노출</td>
                                <td scope="col"><button type="button" class="table_delete">삭제하기</button></td>
                            </tr>
                            <tr class="table_exple">
                                <td>3</td>
                                <td></td>
                                <td>예시)user/main</td>
                                <td>노출</td>
                                <td scope="col"><button type="button" class="table_delete">삭제하기</button></td>
                            </tr>
                            <tr class="table_plus">
                            <td colspan="5"><button type="button">추가하기</button></td>
                            </tr>
                        </table>
                
                <div class="modal_btn">
                    <input type="button" value="등록">
                </div>
            </div>
        </div>
    </div> 
    <!-- 모달 -->
</body>
</html>
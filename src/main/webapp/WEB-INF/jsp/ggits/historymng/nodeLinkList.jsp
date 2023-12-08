<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">노드/링크<br>자료실 이력</h3>
                <div class="side_txt">노드/링크 자료실 이력 관리<br>정보를 조회합니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">노드/링크 자료실 이력 관리</h2>
            <div class="contents_wrap">
                <div class="tab1">
                    <div class="group2">
                        <div class="group_text2">검색</div>
                        <input type="text" placeholder="검색어를 입력해 주세요." class="input_same group_box">
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <div class="search_detail_wrap">
	                    <form>
	                        <div class="group2">
	                            <div class="group_text2">시간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
										<input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="endTime"></select>
	                                </div>
	                                <div class="flex-center">
	                                    <div class="select_tltie">집계 시간</div>
	                                    <select class="selectBox">
	                                        <option selected="selected">10분</option>
	                                        <option>test1</option>
	                                        <option>test2</option>
	                                        <option>test3</option>
	                                        <option>test4</option>
	                                        <option>test5</option>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
		                     <div class="group2_btn">
		                    	<!-- button id  name 바꿔서 사용하세요  -->
		                        <button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
		                        <input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
	                    	</div>
		                </form>
		            </div>
                    <div class="search_container">
                        <div class="search_head">
                            <div class="search_number">
                                <span>"121534개"</span>의 검색결과를 찾았습니다.
                            </div>
                        </div>
                    </div>
					<table class="mt16">
					       <colgroup>
					           <col style="width:8%">
					           <col style="width:20%">
					           <col style="width:12%">
					           <col style="width:14%">
					           <col style="width:18%">
					           <col style="width:12%">
					           <col style="width:18%">
					       </colgroup>
					       <thead>
					        <tr>
					            <th>번호</th>
					            <th>제목</th>
								<th>
					            	<select class="table-filter">
										<option selected="selected">사용 유형</option>
										<option>전체</option>
										<option>다운로드</option>
										<option>업로드</option>
					          		</select>   									
								</th>
								<th>사용자 이름</th>
								<th>사용자 ID</th>
					            <th>
					            	<select class="table-filter">
										<option selected="selected">소속 기관</option>
										<option>전체</option>
										<option>수원시</option>
										<option>파주시</option>
										<option>성남시</option>
					          		</select>   	                            
					            </th>
					            <th>일시</th>
					        </tr>
					       </thead>
					       <tbody>
						        <tr>
						            <td>1</td>
						            <td>경기도 5월 표준 노드</td>
						            <td>다운로드</td>
						            <td>홍길동</td>
						            <td>hong123@gmail.com</td>
						            <td>수원시</td>
						            <td>2023.12.10 23:00</td>
						        </tr>
						        <tr>
						            <td>2</td>
						            <td>경기도 6월 표준 노드</td>
						            <td>업로드</td>
						            <td>홍길동</td>
						            <td>hong123@gmail.com</td>
						            <td>파주시</td>
						            <td>2023.12.10 23:00</td>
						        </tr>
						   </tbody>
					</table>
                </div>
            </div>
            <div class="page_wrap">
              <ul class="pagination">
                  <li><a href="#" class="first">처음 페이지</a></li>
                  <li><a href="#" class="arrow left"><</a></li>
                  <li><a href="#" class="active num">1</a></li>
                  <li><a href="#" class="num">2</a></li>
                  <li><a href="#" class="num">3</a></li>
                  <li><a href="#" class="num">4</a></li>
                  <li><a href="#" class="num">5</a></li>
                  <li><a href="#" class="num">6</a></li>
                  <li><a href="#" class="num">7</a></li>
                  <li><a href="#" class="num">8</a></li>
                  <li><a href="#" class="num">9</a></li>
                  <li><a href="#" class="num">10</a></li>
                  <li><a href="#" class="arrow right">></a></li>
                  <li><a href="#" class="last">마지막 페이지</a></li>
              </ul>
            </div>
        </section>
    </div>
</main>

<script>
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})
</script>

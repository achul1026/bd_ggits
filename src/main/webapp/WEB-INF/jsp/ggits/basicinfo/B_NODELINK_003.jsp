<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <div class="main_container">
     <aside class="snb_container">
         <div class="snb_wrap">
             <h3 class="side_title">표준 노드/링크 현황</h3>
             <div class="side_txt">경기도내 시/군/구/도로별<br>표준 노드 및 링크 정보를<br>조회 할 수 있습니다.</div>
             <a href="${pageContext.request.contextPath}/basicinfo/dashboard.do" class="is-darkgreen-btn" style="font-size:0.8rem">GIS로 노드/링크 조회하기</a>
         </div>
     </aside>
     <section class="main_section">
         <h2 class="blind">표준노드링크조회</h2>
         <div class="table_btn_wrap">
             <select class="selectBox" selected="selected">
                 <option>전체 시군구</option>
                 <option>test1</option>
                 <option>test2</option>
                 <option>test3</option>
                 <option>test4</option>
                 <option>test5</option>
             </select>
             <div class="btn_search_wrap">
                 <a href="${pageContext.request.contextPath}/basicinfo/nodelink/reference/list.do" class="is-darkgreen-btn">자료실 이동하기</a>
                 <button type="button" class="is-darkgreen-btn">엑셀 다운로드</button>
             </div>
         </div>
         
         <table class="center">
             <colgroup>
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
                 <col style="width:11%">
             </colgroup>
             <tr>
                 <th scope="col">구분</th>
                 <th scope="col">전체</th>
                 <th scope="col">고속도로</th>
                 <th scope="col">도시고속도로</th>
                 <th scope="col">일반국도</th>
                 <th scope="col">특별/광역시도</th>
                 <th scope="col">국가지원지방도</th>
                 <th scope="col">지방도</th>
                 <th scope="col">시군도</th>
             </tr>
             <tr>
                 <td class="table_dark_bg"><span class="table_span">광역시도</span><span class="table_span">시군구</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
                 <td class="table_dark_bg"><span class="table_span">노드</span><span class="table_span">링크</span></td>
             </tr>
             <tr>
                 <td>합계</td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
                 <td><span class="table_span">42684</span><span class="table_span">156663</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
             <tr>
                 <td><span class="table_span">경기도</span><span class="table_span">파주시<br>금촌동</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
                 <td><span class="table_span">304</span><span class="table_span">803</span></td>
             </tr>
           </table>
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

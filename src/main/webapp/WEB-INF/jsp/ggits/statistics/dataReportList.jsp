<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">분석리포트</h3>
            <div class="side_txt">
                교통정보 통게 및 데이터 활용<br>통계 분석 리포트 입니다.
            </div>
            <div class="side_btn">
			     <a href="${pageContext.request.contextPath}/statistics/analysis/report/list.do">교통정보 통계 리포트</a>
			     <a href="${pageContext.request.contextPath}/statistics/analysis/data/report/list.do" class="on">데이터 활용 통계 리포트</a>
            </div>
        </div>
    </aside>
    <section class="main_section">
    	<form>
        <h2 class="blind">분석리포트</h2>
        <div class="table_btn_wrap">
            <div class="flex-center">
                <div class="mr8">
	                <select class="selectBox">
	                	<option>전체</option>
	                	<option>test1</option>
	                	<option>test1</option>
	                </select>
                </div>
                <div class="btn_search_wrap float-none">
                    <input type="text" placeholder="검색어를 입력하세요." class="input_same search_box">
                    <input type="button" value="검색" class="input_same search_box2">
                    <button type="button" class="is-darkgreen-btn mj0">등록하기</button>
                </div>
            </div>
        </div>
        <div class="search_container">
	        <div class="search_head">
	            <div class="search_number">
	                <span>"121534개"</span>의 검색결과를 찾았습니다.
	            </div>
	            <div class="table_right_btn">
	                <button type="button" class="is-darkgreen-btn">보고서 만들기</button>
	            </div>
	        </div>
        </div>
        <div class="contents_wrap tab_area mt8">
			<table class="mt16">
				<colgroup>
					<col style="width:8%">
					<col style="width:30%">
					<col style="width:30%">
					<col style="width:12%">
					<col style="width:10%">
					<col style="width:10%">
				</colgroup>
				<thead>
					<tr>
					    <th scope="col">번호</th>
					    <th scope="col">제목</th>
					    <th scope="col">등록자</th>
					    <th scope="col">등록일</th>
					    <th scope="col">조회수</th>
					    <th scope="col">다운로드</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table_1depth">
						<td>1</td>
						<td>2023년 남양주시 교통정보 통계 보고서 3월</td>
						<td>김길동(glidong123@naver.com)</td>
						<td>2023.12.10</td>
						<td>1234</td>
						<td><button type="button">다운로드</button></td>
					</tr>
					<tr class="table_1depth">
						<td>2</td>
						<td>2023년 남양주시 교통정보 통계 보고서 4월</td>
						<td>김길동(glidong123@naver.com)</td>
						<td>2023.12.10</td>
						<td>1234</td>
						<td><button type="button">다운로드</button></td>
					</tr>					
				</tbody>
			</table>
        </div>
        </form>
    </section>
</div>

<script>
	/* modal start */
	/* 실패사유 */
	$('#fail_info').on("click", function(){
		var jobId = $(this).data("jobid");
		var dsetId = $(this).data("dsetid");
		var etlDt = $(this).data("etldt");
	    new ModalBuilder().init('실패 정보').ajaxBody("${pageContext.request.contextPath}/common/modal/collectionsystem/list.do?jobId="+jobId+"&dsetId="+dsetId+"&etlDt="+etlDt).footer(1,'확인',function(button, modal){}).open();
	    $('.modal_footer').removeClass('none');
	});
	
	
</script>
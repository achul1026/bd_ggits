<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">교통 DB화 통계</h3>
                <div class="side_txt">
                    교통 DB화 통계 정보를 <br>조회합니다.
                </div>
                <div class="side_btn">
                    <a href="${pageContext.request.contextPath}/statistics/traffic/database/list.do">교통총괄지표</a>
                    <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do" class="on">교통영향평가</a>
                </div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">교통영향평가</h2>
            <div class="table_btn_wrap clearfix">
            	<!-- 검색결과 갯수  -->
            	<div class="float-left mj0">
					<div class="search_head none">
						<div class="search_number">
							<span>"121534개"</span>의 검색결과를 찾았습니다.
						</div>
					</div>
				</div>
                <div class="flex-end">
                    <div class="flex-center mr8">
                        <div>
                            <span class="group_btn_title">시군</span>
                            <select class="selectBox">
                                <option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
	                					<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
									</c:forEach>
                            </select>
                        </div>
                        <div>
                            <span class="group_btn_title">기간</span>
                            <input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." value="${strDt}">
                            ~
                            <input type="text" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요.">
                        </div>
                    </div>
                    <div class="btn_search_wrap float-none">
                        <input type="button" value="찾기" class="input_same search_box2 pointer" id="searchBtn">
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/download.do?fileNm=traffic_import_report_sample.csv" class="is-darkgreen-btn">양식 다운로드</a>
                        <button type="button" class="is-darkgreen-btn" id="chkDeleteBtn">선택삭제</button>
                        <button type="button" class="is-darkgreen-btn upload_btn mj0">신규조사등록</button>
                    </div>
                </div>
            </div>
			<table class="db_table">
                <colgroup>
                    <col style="width:6%">
                    <col style="width:6%">
                    <col style="width:20%">
                    <col style="width:20%">
                    <col style="width:10%">
                    <col style="width:10%">
                    <col style="width:10%">
                </colgroup>
                <tr>
                    <th scope="col">선택</th>
                    <th scope="col">조사지역</th>
                    <th scope="col">조사명</th>
                    <th scope="col">조사기간</th>
                    <th scope="col">상태</th>
                    <th scope="col">등록수정일</th>
                    <th scope="col">관리자</th>
                </tr>
                <c:choose>
                	<c:when test="${fn:length(trafficImpactList) > 0}">
                		<c:forEach var="tfcIptInfo" items="${trafficImpactList}">
                			<tr data-tr_value="1" class="table_1depth">
                				<td><input type="checkbox" id="info" value="1" name="checkRow" class="checkRow"><label for="info"></label></td>
                				<td>${tfcIptInfo.smlfactNm}</td>
                				<td onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/database/assessment/crossroad_by_traffic/list.do?ipcssMngNo=${tfcIptInfo.ipcssMngNo}'">${tfcIptInfo.exmnDocNm}</td>
                				<td>-</td>
                				<td>-</td>
                				<td>-</td>
                				<td>-</td>
                			</tr>
                		</c:forEach>
                	</c:when>
                	<c:otherwise>
                		<tr>
                			<td colspan="7">교통영향평가가 존재하지 않습니다.</td>
                		</tr>
                	</c:otherwise>
                </c:choose>
            </table>
            <div>
          		<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
          	</div>
        </section>          
    </div>
</main>

<script>
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	$('.upload_btn').on('click', function(){
        new ModalBuilder().init('CSV 업로드').ajaxBody("${pageContext.request.contextPath}/common/modal/csvupload/list.do").footer(1,'확인',function(button, modal){
       		// validation 체크
        	$.ajax({
       			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/upload.do",
       			type : "POST",
       			data : new FormData($("#frmAttachedFiles")[0]),
       			dataType: "json",
       			processData: false,
       			contentType: false,
       			success: function(data) {
       				if(data.code == 200){
						new ModalBuilder().init().successBody("신규 교통영향평가를 등록하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
						}).open();
						modalAlertWrap();						
					} else {
						new ModalBuilder().init().alertBoby("교통영향평가 등록에 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
       			}
       		});
        }).open();
       	$('.modal_footer').removeClass('none');
    });
    
    $("#chkDeleteBtn").on('click', function(){
    	var checkRow = $(".checkRow");
    	var ipcssMngNoArr = new Array();
    	for(var i=0; i < checkRow.length; i++){
    		var isChkRow = checkRow.eq(i).is(":checked");
    		if(isChkRow){
    			var ipcssMngNo = checkRow.eq(i).val();
    			ipcssMngNoArr.push(ipcssMngNo);
    		}
    	}
    	
    	if(ipcssMngNoArr.length == 0){
    		new ModalBuilder().init().alertBoby("교통영향평가를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
    	}
    	
    	 $.ajax({
 			type : "post",
 			dataType : "json",
 			data : {"ipcssMngNoArr" : ipcssMngNoArr},
 			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/delete.ajax",
 			success : function(data) {
 				if(data.code == 200){
 					new ModalBuilder().init().successBody("선택된 교통영향평가를 삭제하였습니다.").footer(4,'확인',function(button, modal){
 						modal.close();
 						location.reload();
 					}).open();
 					modalAlertWrap();					
 				} else {
 					new ModalBuilder().init().alertBoby("교통영향평가 삭제에 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
 					modalAlertWrap();					
 				}
 			}
 		});
    })
</script>
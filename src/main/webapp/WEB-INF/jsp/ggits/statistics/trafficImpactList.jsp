<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">교통DB화 통계</h3>
                <div class="side_txt">
                    교통DB화 통계정보를 <br>조회합니다.
                </div>
                <div class="side_btn">
                    <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do" class="on" onclick="startLoading()">교통영향평가</a>
                </div>
            </div>
        </aside>
        <section class="main_section">
            <h2 class="blind">교통영향평가</h2>
            <div class="contents_wrap">
                <form id="searchForm" method="get">
                	<input type="hidden" id="page" name="page"  value="1"/>
                    <div class="group2 pt0">
                        <div class="group_text2">검색</div>
                        <div class="btn_search_wrap flex-center">
                        	<ul>
                        		<li>
                        			검색 : 
									<input type="text" class="input_same" name="searchContent" id="searchContent" placeholder="평가명을 입력해주세요.">
                        		</li>
                        	</ul>

                        </div>
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="btn_search_wrap">
	                               	<ul>
	                               		<li>
	                               			<input type="text" class="date_picker input_same input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." autocomplete="off">
	                               		</li>
	                               		<li>
	                               			 ~
	                               		</li>
	                               		<li>
	                               			<input type="text" class="end_date_picker input_same input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." autocomplete="off">
	                               		</li>
	                               	</ul>  
	                            </div>
	                        </div>
	                       
                    	</div>
                    	<div class="btn_search_wrap btn_search_wrap_center">
                    		<ul>
                    			<li>
                    				<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
                    			</li>
                    			<li>
                    				<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
                    			</li>
                    		</ul>
                    	</div>
	               </form>
                </div>
            
            
            <div class="table_btn_wrap flex-between">
				<div class="search_head">
					<div class="search_number">
						<span id="totalCnt"></span>개의 검색결과를 찾았습니다.
					</div>
				</div>
				<c:if test="${authCd eq 'AUC000'}">
	                <div class="flex-center">
	                    <div class="btn_search_wrap">
	                    	<ul>
	                    		<li>
	                    			<button type="button" class="is-darkgreen-btn" id="chkDeleteBtn">선택삭제</button>
	                    		</li>
	                    		<li>
	                    			<a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/sample/download.do?fileNm=traffic_import_report_sample.xlsx" class="is-darkgreen-btn">양식 다운로드</a>
	                    		</li>
	                    		<li>
	                    			 <button type="button" class="is-darkgreen-btn upload_btn">신규조사등록</button>
	                    		</li>
	                    	</ul>
	                    </div>
	                </div>
				</c:if>            
            </div>
			<table class="db_table">
                <tr>
                    <th scope="col">선택</th>
                    <th scope="col">조사용도</th>
                    <th scope="col">조사명</th>
                    <th scope="col">조사기간</th>
                    <th scope="col">등록일자</th>
                </tr>
                <c:choose>
                	<c:when test="${fn:length(trafficImpactList) > 0}">
                		<c:forEach var="tfcIptInfo" items="${trafficImpactList}" varStatus="status">
                			<tr data-tr_value="1" class="table_1depth">
                				<td class="check_box"><input type="checkbox" id="info<c:out value='${status.index}'/>" value="<c:out value='${tfcIptInfo.ipcssMngNo}'/>" name="checkRow" class="checkRow"><label for="info<c:out value='${status.index}'/>"></label></td>
                				<td><c:out value='${tfcIptInfo.bizUsg}'/></td>
                				<td onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/database/assessment/crossroad_by_traffic_weekday/list.do?ipcssMngNo=<c:out value="${tfcIptInfo.ipcssMngNo}"/>'"><c:out value='${tfcIptInfo.bizNm}'/></td>
                				<td><c:out value='${tfcIptInfo.exmnDd }'/></td>
                				<td><c:out value='${tfcIptInfo.etlDt}'/></td>
                			</tr>
                		</c:forEach>
                	</c:when>
                	<c:otherwise>
                		<tr>
                			<td colspan="5">교통영향평가가 존재하지 않습니다.</td>
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
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function(){
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		var searchContent = '<c:out value="${searchOption.searchContent}"/>';

		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(!isNull(searchContent)){
			$("#searchContent").val(searchContent);
		}
	})

	/* 검색결과 */
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do";
		document.getElementById('searchForm').submit();
	}
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$('.upload_btn').on('click', function(){
        new ModalBuilder().init('CSV 업로드').ajaxBody("${pageContext.request.contextPath}/common/modal/csvupload/list.do").footer(1,'확인',function(button, modal){
       		// validation 체크
        	$.ajax({
       			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/upload.ajax",
       			type : "POST",
       			timeout : 100000,
       			data : new FormData($("#frmAttachedFiles")[0]),
       			dataType: "json",
       			processData: false,
       			contentType: false,
       			beforeSend : function(){
	    			startLoading();
	    		},
       			success: function(data) {
       				if(data.code == 200){
						new ModalBuilder().init().successBody("신규 교통영향평가를 등록하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
						}).open();
						modalAlertWrap();						
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
       			},
       			error: function(error){
       				new ModalBuilder().init().alertBoby("교통영향평가 등록에 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();	
       			},
	    		 complete : function(){
	    			 impactEndLoading();
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
    		new ModalBuilder().init().alertBoby("삭제할 교통영향평가를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
			return false;
    	}
    	
   		new ModalBuilder().init().alertBoby("영향평가를 삭제하시겠습니까?").footer(5,'삭제하기',function(button, modal){
	   		 $.ajax({
	  			type : "post",
	  			dataType : "json",
	  			data : {"ipcssMngNoArr" : ipcssMngNoArr},
	  			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/delete.ajax",
	  			beforeSend : function(){
	    			startLoading();
	    		},
	  			success : function(data) {
	  				if(data.code == 200){
	  					new ModalBuilder().init().successBody(data.message).footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
							$(".modal_container").remove();
						}).open();
	  					modalAlertWrap();					
	  				} else {
	  					new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
	  					modalAlertWrap();					
	  				}
	  			},
	    		 complete : function(){
	    			 impactEndLoading();
	             }
	  		});
   		},'취소하기',function(button, modal){
			modalAlertClose();    
		}).open();	
    })
</script>
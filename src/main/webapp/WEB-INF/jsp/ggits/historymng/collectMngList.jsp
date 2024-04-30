<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">수집 이력 관리</h3>
                <div class="side_txt">수집 이력 관리<br>정보를 조회합니다.</div>
            </div>
        </aside>
        <section class="main_section tab_set">
            <h2 class="blind">수집 이력 관리</h2>
            <div class="table_btn_wrap clearfix tab_fc">
                <div class="btn_search_wrap float-left">
                	<input type="hidden" id="linkedType" value="<c:out value='${linkedType}'/>"/>
                	<ul>
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT000" class="is-dark-btn ${linkedType eq 'SMT000' ? 'is-darkgreen-btn':''}">지자체 연계</a>
                		</li>
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT001" class="is-dark-btn ${linkedType eq 'SMT001' ? 'is-darkgreen-btn':''}">외부기관 연계</a>
                		</li>
<!--                 		<li> -->
<%--                 			<a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT002" class="is-dark-btn ${linkedType eq 'SMT002' ? 'is-darkgreen-btn':''}">신호 연계</a> --%>
<!--                 		</li> -->
                		<li>
                			<a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT003" class="is-dark-btn ${linkedType eq 'SMT003' ? 'is-darkgreen-btn':''}">빅데이터 연계 시스템</a>
                		</li>
                	</ul>
                </div>
            </div>
            <div class="contents_wrap tab_area">
                <div class="tab1">
                    <div class="group2">
                        <div class="group_text2">검색</div>
						<input type="text" id="searchContent" placeholder="수집 기관 또는 수집원을 입력해 주세요" class="input_same group_box history-input-witdh" value="<c:out value='${searchOption.searchContent}'/>">							
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <form id="searchForm">
	                    <div class="search_detail_wrap">
	                    	<input type="hidden" name="page" id="page" value="1">
	                        <div class="group2">
	                            <div class="group_text2">시간 설정</div>
	                            <div class="btn_search_wrap">
	                  	            <ul>
	                  	            	<li>
	                  	            		<input type="text" class="date_picker input_same input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요.">
	                  	            	</li>
	                  	            	<li>
	                  	            		~
	                  	            	</li>
	                  	            	<li>
	                  	            		<input type="text" class="end_date_picker input_same input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요.">
	                  	            	</li>
	                  	            </ul>
			
	                            </div>
	                        </div>
			            </div>
	                    <div class="btn_search_wrap btn_search_wrap_center">
	                    	<ul>
	                    		<li>
	                    			<button type="button" class="is-darkgreen-btn" id="searchBtn">찾기</button>
	                    		</li>
	                    		<li>
	                    			<input type="button" id="resetSchOption" class="is-dark-btn selected_reset" value="검색값 초기화">
	                    		</li>
	                    	</ul>
                        </div>
	                </form>
                    <div class="search_container">
                        <div class="search_head">
                            <div class="search_number">
                                <span id="totalCnt"><c:out value='${paging.totalCount}'/></span>개의 검색결과를 찾았습니다.
                            </div>
                        </div>
                    </div>
                    <table class="mt16">
                        <thead>
	                        <tr>
	                            <th>번호</th>
	                            <th>수집일시</th>
	                            <th>수집기관</th>
	                            <th>수집데이터</th>
	                            <th>데이터 수집 건수</th>
	                            <th>처리시간</th>
	                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        	<c:when test="${not empty collectList}">
	                        	<c:forEach var="collectList" items="${collectList}">
			                        <tr>
			                            <td><c:out value='${collectList.rownum}'/></td>
			                            <td>
			                            	<fmt:formatDate value="${collectList.clctStartDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
			                            </td>
			                            <td><c:out value='${collectList.cdNm ne null ? collectList.cdNm : "-"}'/></td>
			                            <td><c:out value='${collectList.jobNm}'/></td>
			                            <td>
											<fmt:formatNumber value="${collectList.clctDataCnt}" type="number" />건
										</td>
			                            <td><c:out value='${collectList.procTime ne null ? collectList.procTime : "-"}'/></td>
			                        </tr>
	                        	</c:forEach>
                        	</c:when>
                        	<c:otherwise>
			                    <tr>
									<td colspan="7">조회된 결과가 없습니다.</td>
								</tr>
                        	</c:otherwise>
                        </c:choose>
                        </tbody>
                	</table>
                </div>
            </div>
            <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
        </section>
    </div>
</main>

<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function(){
		var strDt = '<c:out value="${searchOption.strDt}"/>';
		var endDt = '<c:out value="${searchOption.endDt}"/>';
		var colTyCd = '<c:out value="${searchOption.colTyCd}"/>';
		
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(colTyCd != null && colTyCd != ''){
			var collectionType = $(".collectionType")
			if(colTyCd.indexOf(",")){
				var colTyCdArr = colTyCd.split(",");
				for(var i = 0; i < colTyCdArr.length; i++){
					for(var j = 0; j < collectionType.length; j++){
						if(colTyCdArr[i] == collectionType.eq(j).val()){
							collectionType.eq(j).prop("checked",true);
							collectionType.eq(j).parent('label').addClass("is-darkgreen-btn");
						}
					}
				}
			} else {
				for(var i = 0; i < collectionType.length; i++){
					if(colTyCd == collectionType.eq(i).val()){
						collectionType.eq(i).prop("checked",true);
						collectionType.eq(i).parent('label').addClass("is-darkgreen-btn");
					}
				}
			}
		}
	})
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
		fnSearchList();
	})
	
	function fnSearchList(){
		var searchContent = $("#searchContent").val();
		var collectionType = $(".collectionType"); //아직 컬럼 미생성
		var linkedType = $("#linkedType").val();

		var colTyCd = "";
		if(collectionType.is(":checked")){
			for(var i = 0; i < collectionType.length; i++){
				if(collectionType.eq(i).is(":checked")){
					if(colTyCd == ''){
						colTyCd += collectionType.eq(i).val();
					} else {
						colTyCd += ","+collectionType.eq(i).val();
					}
				}				
			}
		}
		
		var formData = $("#searchForm").serialize();
		formData += '&searchContent='+searchContent;
		formData += '&colTyCd='+colTyCd;
		formData += '&linkedType='+linkedType;
		
		location.href="${pageContext.request.contextPath}/historymng/collectmng/list.do?"+formData;
// 		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/historymng/collectmng/list.do?"+formData;
// 		document.getElementById('searchForm').submit();
	}
	
	$("#resetSchOption").on('click',function(){
		$("#searchContent").val("");
		//수집상태 초기화
		$(".collectionType").prop('checked',false);
		$(".collectionType").parent('label').removeClass('is-darkgreen-btn');
	});
</script>
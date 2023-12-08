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
                    <a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT000" class="is-dark-btn ${linkedType eq 'SMT000' ? 'is-darkgreen-btn':''}">지자체 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT001" class="is-dark-btn ${linkedType eq 'SMT001' ? 'is-darkgreen-btn':''}">외부기관 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT002" class="is-dark-btn ${linkedType eq 'SMT002' ? 'is-darkgreen-btn':''}">신호 연계</a>
                    <a href="${pageContext.request.contextPath}/historymng/collectmng/list.do?linkedType=SMT003" class="is-dark-btn ${linkedType eq 'SMT003' ? 'is-darkgreen-btn':''}">빅데이터 저장 플랫폼</a>
                </div>
            </div>
            <div class="contents_wrap tab_area">
                <div class="tab1">
                    <div class="group2">
                        <div class="group_text2">검색</div>
                        <input type="text" id="searchContent" name="searchContent" placeholder="기관명을 검색해주세요." class="input_same group_box" value="${searchOption.searchContent}">
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <div class="search_detail_wrap">
	                    <form id="searchForm">
	                    	<input type="hidden" name="page" value="${paging ne null ? paging.pageNo : '1'}"/>
	                        <div class="group2">
	                            <div class="group_text2">시간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
										<input type="text" class="date_picker input_same mr8 input_picker" id="strDt" name="strDt" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="startTime" name="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" id="endDt" name="endDt" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="endTime" name="endTime"></select>
	                                </div>
<!-- 	                                <div class="flex-center"> -->
<!-- 	                                    <div class="select_tltie">집계 시간</div> -->
<!-- 	                                    <select class="selectBox"> -->
<!-- 	                                        <option selected="selected">10분</option> -->
<!-- 	                                        <option>test1</option> -->
<!-- 	                                        <option>test2</option> -->
<!-- 	                                        <option>test3</option> -->
<!-- 	                                        <option>test4</option> -->
<!-- 	                                        <option>test5</option> -->
<!-- 	                                    </select> -->
<!-- 	                                </div> -->
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">수집상태</div>
	                            <div>
				                    <c:forEach var="collTyCd" items="${collTyCdList}">
				                    	<c:if test="${collTyCd.cdId eq 'CTC000'}">
											<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="${collTyCd.cdNm}">${collTyCd.cdNm}</label>
				                    	</c:if>
				                    	<c:if test="${collTyCd.cdId eq 'CTC002'}">
											<label class="is-dark-btn single-toggle"><input type="checkbox" class="none collectionType" value="${collTyCd.cdNm}">${collTyCd.cdNm}</label>
				                    	</c:if>
									</c:forEach>
	                            </div> 
	                        </div>
		                     <div class="group2_btn">
		                    	<!-- button id  name 바꿔서 사용하세요 -->
		                        <button type="button" class="is-darkgreen-btn" id="searchBtn">찾기</button>
		                        <input type="button" id="resetSchOption" class="is-dark-btn selected_reset" value="검색값 초기화">
	                    	</div>
		                </form>
		            </div>
                    <div class="search_container">
                        <div class="search_head">
                            <div class="search_number">
                                <span>"${paging.totalCount}개"</span>의 검색결과를 찾았습니다.
                            </div>
                        </div>
                    </div>
                    <table class="mt16">
                        <colgroup>
                            <col style="width:8%">
                            <col style="width:30%">
                            <col style="width:20%">
                            <col style="width:20%">
                            <col style="width:20%">
                        </colgroup>
                        <thead>
	                        <tr>
	                            <th>번호</th>
	                            <th>수집일시</th>
	                            <th>수집기관</th>
	                            <th>수집유형</th>
	                            <th>
		                            <select class="table-filter">
			                            <option selected="selected">정상여부</option>
			                            <option>전체</option>
			                            <option>정상</option>
			                            <option>비정상</option>
			                        </select>                            	
	                            </th>
	                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        	<c:when test="${not empty collectList}">
	                        	<c:forEach var="collectList" items="${collectList}">
			                        <tr>
			                            <td>${collectList.rownum}</td>
			                            <td>
			                            	<fmt:formatDate value="${collectList.clctStartDt}" pattern="yyyy-MM-dd HH:mm"/>
			                            </td>
			                            <td>${collectList.cdNm}</td>
			                            <td>${collectList.dataClctType ne null and collectList.dataClctType ne ''? collectList.dataClctType:'-'}</td>
			                            <td>
			                            	<c:choose>
												<c:when test="${collectList.prgrsStts eq 'ERROR'}">
													비정상
												</c:when>
												<c:otherwise>
													정상
												</c:otherwise>		                            	
			                            	</c:choose>
			                            </td>
			                        </tr>
	                        	</c:forEach>
                        	</c:when>
                        	<c:otherwise>
			                    <tr>
									<td colspan="5">조회된 결과가 없습니다.</td>
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

	$(document).ready(function(){
		//<![CDATA[
			var strDt = '${searchOption.strDt}';
			var endDt = '${searchOption.endDt}';
			var strTime = '${searchOption.startTime}';
			var endTime = '${searchOption.endTime}';
			var colTyCd = '${searchOption.colTyCd}';
		//]]>
		
		//searchOption dataInit
		if(strDt != null && strDt != ''){
			$("#strDt").val(strDt.substring(0,10));
		}
		if(endDt != null && endDt != ''){
			$("#endDt").val(endDt.substring(0,10));
		}
		if(strTime != null && strTime != ''){
			$("#startTime").val(strTime).prop("selected",true);
		}
		if(endTime != null && endTime != ''){
			$("#endTime").val(endTime).prop("selected",true);
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

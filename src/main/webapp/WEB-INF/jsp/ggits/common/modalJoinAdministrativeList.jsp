<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<div>
        <div class="flex">
        	<input type="hidden" id="modalPage" name="page" value="1">
	        <input type="text" id="searchContent"  class="modal_input_srbox" placeholder="검색어를 입력해주세요.">
	        <input type="button" class="modal_input_srbtn ml8" value="찾기">
        </div>
        <div class="modal_input_txt ml8 mt8 modal_ex_txt">
        	<c:choose>
        		<c:when test="${type eq 'addng' }">
		        	예시) 수원시, 안양시 등 두 글자 이상 입력해 주세요.
        		</c:when>
        		<c:otherwise>
		        	예시) 국토부, 국토 등 두 글자 이상 입력해 주세요.
        		</c:otherwise>
        	</c:choose>
        </div>
	</div>
	<div class="modal_table_container none">
		<div>
			<div class="modal_input_txt mt24">총 <span id="totCnt">""</span> 개의 검색결과가 있습니다.</div>
		</div>
		<table class="modal_table">
			<colgroup>
				<col style="width:20%">
				<col style="width:80%">
			</colgroup>
			<thead>
				<tr>
					<th>체크</th>
					<th class="">행정기관명</th>
				</tr>
			</thead>
			<tbody id="modalTbody">
				<c:choose>
					<c:when test="${fn:length(codeList) > 0 }">
						<c:forEach var="codeList" items="${codeList}">
							<tr>
								<td class="">
									<label class="">
										<input type="radio" class="mj8" name="modalCdId" value="<c:out value='${codeList.cdId}'/>" data-cdnm="<c:out value='${codeList.cdNm}'/>">
										
									</label>
								</td>
								<td class="">
									<c:out value='${codeList.cdNm}'/>
								</td>	
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="1"></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	    <div id="modalPaging">
			<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
		</div>
	</div>
</div>
<script>
	$(".modal_input_srbtn").on('click', function(){
		fnSearchList();
	})
	
	var type = '<c:out value="${type}"/>';
	if(type == null || type == ''){
		type = 'inst'//행정기관
	}
	
	// div placeholdr 제어
	$('.is-close').on('click', function(){
		//DEFAULT 행정기관
		var inputLabe = "inst_label";
		var btnClass = "administrative_btn";
		if(type == "addng"){
			inputLabe = "addng_label";
			btnClass = "addng_btn";
		}
		$('.'+inputLabe).removeClass('title_up');
		$('.'+btnClass).siblings('.div_input_style').find('span').removeClass('none');
	})
	
	function fnSearchList(){
		$("#modalTbody > tr").remove();
		$("#modalPaging > .page_wrap").remove();
		
		var page = $("#modalPage").val();
		var searchContent = $("#searchContent").val();
		$.ajax({
			type : "get",
			data : {
				"searchContent" : searchContent, 
				"page" : page
			},
			url : "${pageContext.request.contextPath}/code/"+type+"/list.ajax",
			success : function(result) {
				var html = '';
				$(result.data.list).each(function(index, item){
					html += '<tr>'+
								'<td class="">'+
									'<label class="">'+
										'<input type="radio" class="mj0" name="modalCdId" value="'+item.cdId+'" data-cdnm="'+item.cdNm+'">'+
									'</label>'+
								'</td>'+
								'<td class="">'+
										item.cdNm+
								'</td>'+
							'</tr>';
		    	})
		    	$("#totCnt").text(result.data.totCnt);
				$("#modalTbody").append(html)				
		    	var paging = result.data.paging;
				if(paging != null && paging != ''){
					$("#modalPaging").append(getPagingHtml(paging,page));
				}
			}
		});
	}	
</script>

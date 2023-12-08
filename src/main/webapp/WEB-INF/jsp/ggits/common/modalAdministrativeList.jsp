<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<div>
        <div class="flex-center">
        	<input type="hidden" id="modalPage" name="page" value="1">
	        <input type="text" id="searchContent"  class="modal_input_srbox" placeholder="검색어를 입력해주세요.">
	        <input type="button" class="modal_input_srbtn ml8" value="찾기">
        </div>
        <div class="modal_input_txt ml8 mt8 modal_ex_txt">예시) 국토부, 국토 등 두 글자 이상 입력해 주세요.</div>
	</div>
	<div class="modal_table_container none">
		<div>
			<div class="modal_input_txt ml8">총 <span id="totCnt"></span> 개의 검색결과가 있습니다.</div>
		</div>
		<table class="modal_table mt8" style="width:26rem">
			<colgroup>
				<col style="width:100%">
			</colgroup>
			<thead>
				<tr>
					<th class="pl24">행정기관명</th>
				</tr>
			</thead>
			<tbody id="modalTbody">
				<c:choose>
					<c:when test="${fn:length(codeList) > 0 }">
						<c:forEach var="codeList" items="${codeList}">
							<tr>
								<td class="pl24">
									<label class="flex-center">
										<input type="radio" class="mr8" name="modalCdId" value="${codeList.cdId}" data-cdnm="${codeList.cdNm}">${codeList.cdNm}
									</label>
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
	
	// div placeholdr 제어
	$('.is-close').on('click', function(){
		$('.input_label').removeClass('title_up');
		$('.administrative_btn').siblings('.div_input_style').find('span').removeClass('none');
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
			url : "${pageContext.request.contextPath}/system/codegrp/code/list.ajax",
			success : function(result) {
				var html = '';
				$(result.data.list).each(function(index, item){
					html += '<tr>'+
								'<td class="pl24">'+
									'<label class="flex-center">'+
										'<input type="radio" class="mr8" name="modalCdId" value="'+item.cdId+'" data-cdnm="'+item.cdNm+'">'+item.cdNm+''+
									'</label>'+
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

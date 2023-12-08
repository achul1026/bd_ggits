<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<div>
        <div class="flex-between">
        	<input type="hidden" id="modalPage" name="page" value="1">
        	<input type="hidden" id="searchType" name="searchType" value="searchType">
	        <input type="text" class="modal_input_srbox" id="searchContent" placeholder="검색어를 입력해주세요.">
	        <input type="button" class="modal_input_srbtn ml8" value="찾기">
        </div>
	</div>
	<div class="modal_radio_container">
		<table class="modal_table mt8" style="width:30rem">
			<colgroup>
				<col style="width:100%">
			</colgroup>
			<thead>
				<tr>
					<th class="pl24">그룹명</th>
				</tr>
			</thead>
			<tbody  id="modalTbody">
				<c:choose>
					<c:when test="${fn:length(groupList) > 0}">
						<c:forEach var="groupList" items="${groupList}">
							<tr>
								<td class="pl24">
									<label class="flex-center">
										<input type="radio" class="mr8" name="modalGrpId" value="${groupList.grpId}" data-grpnm="${groupList.grpNm}">${groupList.grpNm}
									</label>
								</td>	
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td>그룹 정보가 없습니다.</td>
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

<script type="text/javascript">
	$(document).ready(function(){
		$('.modal_footer').removeClass('none');
		if($("input[name='modalGrpId']").length > 0){
			var grpId = $("#grpId").val();
			$("input[name='modalGrpId']").each(function(idx,item){
				if($(item).val() == grpId){
					$(item).attr("checked",true);
				} 
			})
		}
		
		$(".modal_input_srbtn").on('click', function(){
			fnSearchList();
		})
		
	});
		function fnSearchList(){
			
			$("#modalTbody > tr").remove();
			$("#modalPaging > .page_wrap").remove();
			
			var page = $("#modalPage").val();
			var searchType = $("#searchType").val();
			var searchContent = $("#searchContent").val();
			var grpId = $("#grpId").val();
			$.ajax({
				type : "get",
				data : {"searchType" : searchType , "searchContent" : searchContent, "page" : page},
				url : "${pageContext.request.contextPath}/system/user/group/list.ajax",
				success : function(result) {
					var html = '';
					$(result.data.list).each(function(index, item){
						var checkedVal = "";
						if(grpId == item.grpId){
							checkedVal = "checked";
						}
						
						html += '<tr>'+
									'<td class="pl24">'+
										'<label class="flex-center">'+
											'<input type="radio" class="mr8" name="modalGrpId" value="'+item.grpId+'" data-grpnm="'+item.grpNm+'" '+checkedVal+'>'+item.grpNm+''+
										'</label>'+
									'</td>'+
								'</tr>';
			    	})
					$("#modalTbody").append(html)				
			    	var paging = result.data.paging;
					if(paging != null && paging != ''){
						$("#modalPaging").append(getPagingHtml(paging,page));
					}
				}
			});
		}
	
</script>
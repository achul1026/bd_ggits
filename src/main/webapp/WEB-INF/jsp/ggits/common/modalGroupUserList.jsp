<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<div>
	<div class="flex-center">
		<input type="hidden" id="modalPage" name="page" value="1">
		<select class="modal_selectbox" id="searchType">
	        <option value="all">전체</otpion>
			<option value="schOprtrEmail">사용자 ID</otpion>
			<option value="schOprtrNm">이름</otpion>
			<option value="schGrpNm">그룹</otpion>
        </select>
        <div class="flex-center">
	        <input type="text" class="modal_input_srbox" id="searchContent" placeholder="검색어를 입력해주세요.">
	        <input type="button" class="modal_input_srbtn ml8" value="찾기">
        </div>
	</div>
	<div class="modal_table_container none">
		<div>
			<div class="modal_input_txt">총<span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과가 있습니다.</div>
		</div>
		<table class="modal_table mt8" style="width:42rem">
			<colgroup>
				<col style="width:7%">
				<col style="width:7%">
				<col style="width:34%">
				<col style="width:14%">
				<col style="width:18%">
				<col style="width:20%">
			</colgroup>
			<thead>
				<tr>
					<th class="center"><input type="checkbox"></th>
					<th class="center">번호</th>
					<th class="pl24">사용자 ID</th>
					<th class="pl24">이름</th>
					<th class="pl24">그룹</th>
					<th class="pl24">사용자 상태</th>
				</tr>
			</thead>
			<tbody id="modalTbody">
				<c:forEach var="userList" items="${userList}">
					<tr>
						<td class="center">
							<input type="checkbox" id="modalOprtrId_<c:out value='${userList.oprtrId}'/>" name="modalOprtrId" value="<c:out value='${userList.oprtrId}'/>" 
								<c:forEach var="modalUser" items="${modalUserList}">
									<c:if test="${userList.oprtrId eq modalUser}">checked</c:if>
								</c:forEach>
								/>
						</td>
						<td class="center"><label for="modalOprtrId_<c:out value='${userList.oprtrId}'/>"><c:out value='${userList.rownum}'/></label></td>
                       	<td class="pl24" id="oprtrEmail_<c:out value='${userList.oprtrId}'/>"><label for="modalOprtrId_<c:out value='${userList.oprtrId}'/>"><c:out value='${userList.oprtrEmail}'/></label></td>
                       	<td class="pl24" id="oprtrNm_<c:out value='${userList.oprtrId}'/>"><label for="modalOprtrId_<c:out value='${userList.oprtrId}'/>"><c:out value='${userList.oprtrNm}'/></label></td>
                       	<td class="pl24"><label for="modalOprtrId_${userList.oprtrId}"><c:out value="${userList.grpNm}"/></label></td>
                       	<td class="pl24">
                       		<label for="modalOprtrId_<c:out value='${userList.oprtrId}'/>">
	                       		<c:choose>
	                        		<c:when test="${userList.oprtrSttsCd eq 'OSC001'}">
	                        			미승인
	                        		</c:when>
	                        		<c:when test="${userList.oprtrSttsCd eq 'OSC002'}">
	                        			승인
	                        		</c:when>
	                        		<c:when test="${userList.oprtrSttsCd eq 'OSC003'}">
	                        			중지
	                        		</c:when>
	                        		<c:otherwise>
	                        			유저 상태 확인 필요
	                        		</c:otherwise>
	                        	</c:choose>
                       		</label>
                       	</td>
                   	</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="modalPaging">
			<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
		</div>
	</div>
</div>
<script type="text/javascript">


	var dataTotalCnt = '<c:out value="${totalCnt}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$('.modal_table_container').removeClass('none');
	$('.modal_footer').removeClass('none');

	$(".modal_input_srbtn").on('click', function(){
		fnSearchList();
	})
	
	function fnSearchList(){
		
		$("#modalTbody > tr").remove();
		$("#modalPaging > .page_wrap").remove();
		
		var page = $("#modalPage").val();
		var searchType = $("#searchType").val();
		var searchContent = $("#searchContent").val();
		
		$.ajax({
			type : "get",
			data : {"searchType" : searchType , "searchContent" : searchContent, "page" : page},
			url : "${pageContext.request.contextPath}/system/user/list.ajax",
			success : function(result) {
				var html = '';
				$(result.data.list).each(function(index, item){
					var checkedVal = "";
					var oprtrSttsCdNm = "";
					if(modalUserList.includes(item.oprtrId)){
						checkedVal = "checked";
					}
					
					html += '<tr>'+
									'<td class="center"><input type="checkbox" id="modalOprtrId_'+item.oprtrId+'" name="modalOprtrId" value="'+item.oprtrId+'" '+checkedVal+' /></td>'+
									'<td class="center">'+item.rownum+'</td>'+
									'<td class="pl24" id="oprtrEmail_'+item.oprtrId+'">'+item.oprtrEmail+'</td>'+
									'<td class="pl24" id="oprtrNm_'+item.oprtrId+'">'+item.oprtrNm+'</td>'+
									'<td class="pl24">'+item.grpNm+'</td>'+
									'<td class="pl24">';
									if(item.oprtrSttsCd == 'OSC001') oprtrSttsCdNm = "미승인";
									if(item.oprtrSttsCd == 'OSC002') oprtrSttsCdNm = "승인";
									if(item.oprtrSttsCd == 'OSC003') oprtrSttsCdNm = "중지";
					html +=				oprtrSttsCdNm
									'</td>';
					html +=	'</tr>';
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
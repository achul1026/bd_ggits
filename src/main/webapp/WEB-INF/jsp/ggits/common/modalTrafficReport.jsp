<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal-fillter-btn-wrap">
	  <c:out value='${gOpTrfInfoStatsRpt.dtlUrl}'/>
</div>
<div class="iframe-btn-wrap">
	<c:if test="${isUserChk and authCd eq 'AUC000'}">
		<button type="button"class="is-darkgreen-btn iframe-btn" onclick="deleteBtn('<c:out value="${gOpTrfInfoStatsRpt.rptId}"/>')">삭제</button>
	</c:if>
</div>

<script>
	function deleteBtn(rptId){
		new ModalBuilder().init().alertBoby("삭제하시면 다시 복구 할 수 없습니다.<br>삭제하시겠습니까?").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/statistics/analysis/traffic/report/"+rptId+"/delete.ajax",
					success : function(result) {
					var resultCode = result.code;
					if(resultCode == '200'){
						new ModalBuilder().init().successBody(result.message).footer(4,'확인',function(button, modal){
							modal.close();
	    					location.reload();
						}).open();
						modalAlertWrap();
					}else{
						new ModalBuilder().init().alertBoby(result.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
	  			}
			});
		},'취소하기',function(button, modal){
			modalAlertClose();    
		}).open();
	}
</script>
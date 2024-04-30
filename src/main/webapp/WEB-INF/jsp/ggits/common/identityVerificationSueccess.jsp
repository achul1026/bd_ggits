<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/kgmobilans.js"></script>
<script type="text/javascript">

	if(opener.identityProcessManager.identityResultCallback){
		
	 	var resultCode = '<c:out value="${result.resultCode}"/>';
	 	var resultMsg = '<c:out value="${result.resultMsg}"/>';
	 	var userName = "";
	 	var tel = "";
	 	if(resultCode == "0000"){
	 		userName = '<c:out value="${result.userName}"/>';
	 		tel = '<c:out value="${result.tel}"/>';
	 	}
		var arg = opener.parent.identityProcessManager.identityArg;
		var resultData = {	resultCode 	: resultCode, 
							resultMsg 	: resultMsg	,
							userName 	: userName 	,
							tel			: tel
						};
		opener.parent.identityProcessManager.identityResultCallback(arg,resultData);
		
		window.close();
	}
</script>
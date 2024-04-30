<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 그룹 등록</h3>
                    <div class="side_txt">그룹을 생성하여 그룹별<br>사용자를 등록할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 그룹 등록</h2>
                <form id="groupUserSaveForm">
	                <div class="contents_wrap mt24">
	                    <div class="group">
	                        <div class="group_text">그룹명<span class="required-alert">*</span></div>
	                        <input type="text" placeholder="그룹명을 입력해 주세요." class="input_same group_box data-validate" name="grpNm"
	                        	data-valid-name="그룹명" data-valid-maximum="50" data-valid-required/>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">그룹 설명</div>
	                        <input type="text" placeholder="그룹 설명을 입력해 주세요." data-valid-name="그룹 설명" data-valid-maximum="400" class="input_same group_box data-validate" name="grpDescr">
	                    </div>
	                    <div class="group">
	                        <div class="group_text">그룹 사용자</div>
	                        <div class="input_same group_box div_input_style">그룹에 속할 사용자를 선택해주세요.</div>
	                        <input type="hidden" id="oprtrIdArr" name="oprtrIdArr">
	                        <input type="button" value="찾기" class="is-darkgreen-btn ml8" id="searchUserBtn">
	                    </div>
                        <!-- 찾기에서 선택 후 생길 테이블 -->
                        <div class="group_table group_table_scroll mb32 none" style="width:30.063rem">
                            <table>
                                <colgroup>
                                    <col style="width:12%">
                                    <col style="width:54%">
                                    <col style="width:15%">
                                    <col style="width:19%">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">번호</th>
                                        <th scope="col">사용자 ID</th>
                                        <th scope="col">이름</th>
                                        <th scope="col">선택취소</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                </tbody>
                            </table>
                        </div>
	                    <div class="group">
		                        <div class="group_text">권한설정<span class="required-alert">*</span></div>
		                        <select class="selectBox data-validate mj0" name="authId" data-valid-name="권한" data-valid-required>
		                            <option value="">권한설정</option>
								<c:forEach var="authList" items="${authList}">
		                            <option <c:if test="${groupDetail.authId eq authList.authId}">selected="selected"</c:if> value="<c:out value='${authList.authId}'/>"><c:out value="${authList.authNm}"/></option>
		                        </c:forEach>
		                        </select>
		                </div>
		                <c:if test="${defaultChkVal}">
	                    <div class="group">
	                        <div class="group_text">기본 그룹 여부</div>
	                        <select class="selectBox" id="bscGrpYn" name="bscGrpYn">
	                            <option value="N">N</option>
	                            <option value="Y">Y</option>
	                        </select>
		                </div>
	                    <div class="group none" id="upprYnDiv">
	                        <div class="group_text">상위 운영자 그룹 여부</div>
	                        <select class="selectBox" name="upperOprtrAuthGrpYn">
	                            <option value="N">N</option>
	                            <option value="Y">Y</option>
	                        </select>
		                </div>
		                </c:if>
	                    <div class="group group_search">
	                        <input type="button" class="is-darkgreen-btn" id="groupUserSaveBtn" value="등록">
	                        <a href="${pageContext.request.contextPath}/system/user/group/list.do" class="is-dark-btn">취소</a>
	                    </div>
	                </div>
                </form>
            </section>
        </div>
    <script type="text/javascript">
		var modalUserList = new Array();    
		var modalUserInfo = new Object();
		
    	/* modal start */
		$("#searchUserBtn").on("click", function(){
        	modalUserList = new Array();
        	$("input[name='oprtrId']").each(function(index, item){
				var oprtrIdVal = $(item).attr("value");
				modalUserList.push(parseInt(oprtrIdVal));
	    	})
	    	
			
	        new ModalBuilder().init('대상찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/group/user/list.do").footer(1,'확인',function(button, modal){
		    	
		    	
	        	$("#tbody > tr").remove();
				var modalOprtrId = $("input[name='modalOprtrId']");
				var html = '';
				var rownum = $("input[name='modalOprtrId']:checked").length;
				
				modalOprtrId.each(function(index, item){
					var oprtrIdValChkVal = $(item).is(":checked");
					if(oprtrIdValChkVal){
						var modalOprtrId = $(item).attr("value");
						var modalOprtrEmail = $("#oprtrEmail_"+modalOprtrId).text(); 
						var modalOprtrNm = $("#oprtrNm_"+modalOprtrId).text(); 
						html += '<tr>'+
			                        '<td class="rownum">'+rownum+'</td>'+
			                        '<td>'+modalOprtrEmail+'</td>'+
			                        '<td>'+modalOprtrNm+'</td>'+
			                        '<td class="selectCancelBtn pointer">'+
			                        	'<input type="hidden" name="oprtrId" value="'+modalOprtrId+'">'+
			                        	'선택취소'+
			                        '</td>'+
			                    '</tr>';
			                    
						rownum--;
						
					}
			    })
			    $('.group_table').removeClass('none');
			    $("#tbody").append(html);
				
				modal.close();
		  	}).open(); 
		})
    	
	    $("#groupUserSaveBtn").on("click",function(){
	    	if($("#groupUserSaveForm").soValid()){
		    	var oprtrIdArr = new Array();
		    	$("input[name='oprtrId']").each(function(index, item){
					var oprtrIdVal = $(item).attr("value");
					oprtrIdArr.push(oprtrIdVal);
		    	})
		    	
		    	$("#oprtrIdArr").val(oprtrIdArr);
		    	
		    	var groupUserSaveForm = $("#groupUserSaveForm").serializeObject();
		    	
		    	$.ajax({
					type : "post",
					data : groupUserSaveForm,
					url : "${pageContext.request.contextPath}/system/user/group/save.ajax",
					success : function(data) {
						var resultCode = data.code;
						var resultMessage = data.message;
						
						if(resultCode == '200'){
							new ModalBuilder().init().successBody("그룹 정보가 등록되었습니다.").footer(4,'확인',function(button, modal){
								window.location.href="${pageContext.request.contextPath}/system/user/group/list.do";
								modal.close();
							}).open();
							modalAlertWrap();
						}else{
							new ModalBuilder().init().alertBoby("그룹정보 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				});
	    	}
	    })
	    
	    $("#tbody").on("click",".selectCancelBtn",function(){
	    	var removeTr = $(this).closest('tr');
	    	removeTr.remove();
	    	var allTr = $("#tbody > tr");
	    	var allTrLength = allTr.length;
	    	$(allTr).each(function(index, item){
	    		$(item).find(".rownum").text(allTrLength)
	    		allTrLength--;
	    	})
	    })
	    
	    $("#bscGrpYn").on("change",function(){
	    	
	    	if($(this).val() == 'Y'){
				$("#upprYnDiv").removeClass("none");	    		
	    	}else{
	    		$("#upprYnDiv").addClass("none");
	    	}
	    })
    </script>

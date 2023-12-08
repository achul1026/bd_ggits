<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">사용자 그룹 등록</h3>
                    <div class="side_txt">그룹을 생성하여 그룹별<br>사용자를 등록할 수 있습니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">사용자 그룹 등록</h2>
                <form id="groupUserUpdateForm">
                	<input type="hidden" id="grpId" name="grpId" value="${groupDetail.grpId}"/>
	                <div class="contents_wrap mt24">
	                    <div class="group">
	                        <div class="group_text">그룹명</div>
	                        <input type="text"  class="input_same group_box data-validate" name="grpNm" value="${groupDetail.grpNm}"
	                        	data-valid-name="그룹명" data-valid-maximum="50" data-valid-required/>
	                    </div>
	                    <div class="group">
	                        <div class="group_text">그룹 설명</div>
	                        <input type="text"  class="input_same group_box data-validate" data-valid-name="그룹 설명" data-valid-maximum="400" name="grpDescr" value="${groupDetail.grpDescr}">
	                    </div>
	                    <div class="group">
	                        <div class="group_text">그룹 사용자</div>
	                        <div class="input_same group_box div_input_style">그룹에 속할 사용자를 선택해주세요.</div>
	                        <input type="hidden" id="oprtrIdArr" name="oprtrIdArr">
	                        <input type="button" class="is-darkgreen-btn ml8" id="searchUserBtn" value="찾기">
	                        <!-- 찾기에서 선택 후 생길 테이블 -->
	                    </div>    
                        <div class="group_table group_table_scroll mb32 none" style="width:30.063rem">
                            <table>
                                <colgroup>
                                    <col style="width:12%">
                                    <col style="width:54%">
                                    <col style="width:15%">
                                    <c:if test="${groupDetail.bscGrpYn eq 'N'}">
                                    <col style="width:19%">
                                    </c:if>                                   
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">번호</th>
                                        <th scope="col">사용자 ID</th>
                                        <th scope="col">이름</th>
                                        <c:if test="${groupDetail.bscGrpYn eq 'N'}">
                                        <th scope="col">선택취소</th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                	<c:choose>
                                		<c:when test="${fn:length(groupUserList) > 0}">
											<c:forEach var="groupUserList" items="${groupUserList}">
			                                    <tr>
			                                        <td class="rownum">${groupUserList.rownum}</td>
			                                        <td><input type="hidden" name="oprtrId" value="${groupUserList.oprtrId}"/>${groupUserList.oprtrEmail}</td>
			                                        <td>${groupUserList.oprtrNm}</td>
			                                        <c:if test="${groupDetail.bscGrpYn eq 'N'}">
			                                        <td class="selectCancelBtn pointer" data-oprtr-name="${groupUserList.oprtrNm}">
			                                        	선택취소
			                                        </td>
			                                        </c:if>
			                                    </tr>
		                                	</c:forEach>                                		
                                		</c:when>
                                		<c:otherwise>
                                			<tr>
                                				<td colspan="4">그룹 사용자 정보가 없습니다.</td>
                                			</tr>
                                		</c:otherwise>
                                	</c:choose>
                                </tbody>
                            </table>
                    	</div>
	                    <div class="group">
	                        <div class="group_text">권한설정</div>
	                        <select class="selectBox" name="authId">
	                            <option value="">권한설정</option>
							<c:forEach var="authList" items="${authList}">
	                            <option <c:if test="${groupDetail.authId eq authList.authId}">selected="selected"</c:if> value="${authList.authId}">${authList.authNm}</option>
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
	                        <a href="${pageContext.request.contextPath}/system/user/group/list.do" class="is-dark-btn">이전 페이지</a>
	                        <input type="button" class="is-darkgreen-btn" id="groupUserUpdateBtn"  value="수정">
	                        <c:if test="${groupDetail.bscGrpYn eq 'N'}">
	                        <input type="button" class="is-dark-btn" id="groupUserDeleteBtn" value="삭제" >
	                        </c:if>
	                    </div>
	                </div>
                </form>
            </section>
        </div>
    <script type="text/javascript">
	    var modalUserList = new Array();    
		var modalUserInfo = new Object();
	
	    		
	    		
		$("#searchUserBtn").on("click", function(){
        	modalUserList = new Array();
        	$("input[name='oprtrId']").each(function(index, item){
				var oprtrIdVal = $(item).attr("value");
				modalUserList.push(parseInt(oprtrIdVal));
	    	})
	    	
	    	var data = {"modalUserList" : modalUserList};
	        new ModalBuilder().init('대상찾기').ajaxBody("${pageContext.request.contextPath}/common/modal/group/user/list.do",data).footer(1,'확인',function(button, modal){
		    	
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
			                        '<td><input type="hidden" name="oprtrId" value="'+modalOprtrId+'">'+modalOprtrEmail+'</td>'+
			                        '<td>'+modalOprtrNm+'</td>'+
			                        '<td class="selectCancelBtn pointer" data-oprtr-name="'+modalOprtrNm+'">'+
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
	   	
	   		
   		function findAllUserList(modalTxtClassNm,schType,schKeyword){
	    	$.ajax({
				type : "get",
				data : {"schType" : schType , "schKeyword" : schKeyword},
				url : "${pageContext.request.contextPath}/system/user/list.ajax",
				success : function(data) {
					const dataLength = data.length;
					$(".total_cnt").text("");
					$(".total_cnt").text("총 "+dataLength+"개의 검색결과가 있습니다.");
					modalTxtClassNm.find(".modal_input_txt").hide();
					modalTxtClassNm.find(".table_div").append(tableUserView(5, "대상", data));
					modalTxtClassNm.append(pageNation(1,5,dataLength));
				}
			});
	    }
	    
	    
	    $("#groupUserUpdateBtn").on("click",function(){
	    	if($("#groupUserUpdateForm").soValid()){
		    	var oprtrIdArr = new Array();
		    	$("input[name='oprtrId']").each(function(index, item){
					var oprtrIdVal = $(item).attr("value");
					oprtrIdArr.push(oprtrIdVal);
		    	})
		    	
		    	$("#oprtrIdArr").val(oprtrIdArr);
		    	
		    	var groupUserUpdateForm = $("#groupUserUpdateForm").serializeObject();
		    	
		    	$.ajax({
					type : "post",
					data : groupUserUpdateForm,
					url : "${pageContext.request.contextPath}/system/user/group/update.ajax",
					success : function(data) {
						var resultCode = data.code;
	    				var resultMessage = data.message;
	    				
	    				if(resultCode == '200'){
	    					new ModalBuilder().init().successBody("그룹정보가 수정되었습니다.").footer(4,'확인',function(button, modal){
	    						modal.close();
	    						window.location.reload();
	    					}).open();
	    					modalAlertWrap();	    					
	    				}else{
	    					new ModalBuilder().init().alertBoby("그룹정보 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
	    				}
					}
				});
	    	}
	    })
	    
	    $("#groupUserDeleteBtn").on("click",function(){
	    	var grpId = $("#grpId").val();
	    	var oprtrIdArr = new Array();
	    	$("input[name='oprtrId']").each(function(index, item){
				var oprtrIdVal = $(item).attr("value");
				oprtrIdArr.push(oprtrIdVal);
	    	})
	    	$("#oprtrIdArr").val(oprtrIdArr);
	    	var groupUserUpdateForm = $("#groupUserUpdateForm").serializeObject();
	    	
			new ModalBuilder().init().alertBoby("해당 그룹 삭제 시 그룹원은 일반사용자 그룹으로 이전됩니다.").footer(5,'삭제하기',function(button, modal){
		    	$.ajax({
					type : "get",
					data : groupUserUpdateForm,
					url : "${pageContext.request.contextPath}/system/user/group/"+grpId+"/delete.ajax",
					success : function(data) {
						var resultCode = data.code;
	    				var resultMessage = data.message;
	    				
	    				if(resultCode == '200'){
    						new ModalBuilder().init().successBody("그룹이 삭제 되었습니다.").footer(4,'확인',function(button, modal){
    							modal.close();
	    						window.location.href="${pageContext.request.contextPath}/system/user/group/list.do";
    						}).open();
    						modalAlertWrap();    						
	    				}else{
	    					new ModalBuilder().init().alertBoby("그룹 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();	    					
	    				}
					}
				});
			 },'취소하기',function(button, modal){}).open();
	    })
	    
	    $("#tbody").on("click",".selectCancelBtn",function(){
	    	var oprtrNm = $(this).data("oprtr-name");
		    var removeTr = $(this).closest('tr');
			new ModalBuilder().init().alertBoby("선택 취소 시 ["+oprtrNm+"]님은 일반 사용자 그룹으로 이전됩니다.").footer(5,'확인',function(button, modal){
		    	removeTr.remove();
		    	var allTr = $("#tbody > tr");
		    	var allTrLength = allTr.length;
		    	$(allTr).each(function(index, item){
		    		$(item).find(".rownum").text(allTrLength)
		    		allTrLength--;
		    	})
		    	modal.close();
			 },'취소',function(button, modal){}).open();	    	
	    })
	    
    </script>

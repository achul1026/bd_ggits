<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <main>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통 정보 센터<br>자료 관리</h3>
                    <div class="side_txt">교통 정보 센터 관련 자료를<br>등록 및 관리합니다.</div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">교통 정보 센터 자료 관리</h2>
                <form id="searchForm" method="get">
                	<input type="hidden" id="page" name="page" value="1"/>
	                <div class="table_btn_wrap clearfix">
	                    <div class="table-btn-between-wrap">
						    <div class="search_head">
						        <div class="search_number">
						            <span id="totalCnt"></span>개의 검색결과를 찾았습니다.
						        </div>
						    </div>	                    
	                        <div class="btn_search_wrap">
	                        	<ul>
	                        		<li>
	                        			<input type="text" placeholder="자료명을 입력해주세요." id="searchContent" name="searchContent" class="input_same search_box" value="<c:out value='${searchOption.searchContent}'/>">
	                        		</li>
	                        		<li>
	                        			<input type="button" value="검색" class="input_same search_box2 pointer" id="searchBtn">
	                        		</li>
	                        		<c:if test="${authCd eq 'AUC000'}">
		                        		<li>
		                        			<button type="button" class="is-darkgreen-btn" id="uploadButton">자료등록</button>
		                        		</li>
	                        		</c:if>
	                        	</ul>
	                        </div>
	                    </div>
	                </div>
                </form>
                <table>
                    <tr>
                        <th>자료명</th>
                        <th>등록일</th>
                        <th>기능</th>
                    </tr>
					<c:choose>
						<c:when test="${not empty fileMngTotInfoList}">
							<c:forEach var="fileMngTotInfo" items="${fileMngTotInfoList}">
								<tr>
									<td><c:out value='${fileMngTotInfo.fileNm}'/></td>
									<td><c:out value='${fileMngTotInfo.registYmd}'/></td>
									<td>
										<ul class="datamng_table_ul">
											<li>
												<a href="${pageContext.request.contextPath}/datamng/trfInfoCenter/file/download.do?fileMngId=<c:out value='${fileMngTotInfo.fileMngId}'/>">
													<img src="/statics/images/download.png" alt="다운로드" class="datamng_img">[다운로드]
												</a>
											</li>
											<c:if test="${authCd eq 'AUC000'}">
												<li>
													<button type="button" onclick="updateFileBtn('<c:out value='${fileMngTotInfo.fileMngId}'/>')">
														<img src="${pageContext.request.contextPath}/statics/images/modify.png" alt="수정" class="datamng_img">[수정]
													</button>
												</li>
												<li>
													<button type="button" onclick="deleteFileBtn('<c:out value='${fileMngTotInfo.fileMngId}'/>')">
														<img src="${pageContext.request.contextPath}/statics/images/delete.png" alt="삭제" class="datamng_img">[삭제]
													</button>
												</li>
											</c:if>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">
									조회된 철도 건설 자료 결과가 없습니다.
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
            </section>
        </div>
    </main>


<script>

	//검색결과 갯수
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	$(document).ready(function() {
		$('#searchContent').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	});
	
	<c:if test="${authCd eq 'AUC000'}">
	//자료 업로드
	$(function(){
		let isClick = true;
		$('#uploadButton').on('click', function(){
		    new ModalBuilder().init('자료 업로드').ajaxBody("${pageContext.request.contextPath}/common/modal/datamng/trfInfoCenter/save.do").footer(1,'업로드',function(button, modal){
		    	if(isClick) {
		    		// 중복 클릭 방지
		    		isClick = false;
		    		
		    		var fileNm = $("#fileNm").val();
		    		var fileDivCd = $("#fileDivCd").val();
		    		var uploadFile = $("#uploadFile")[0];
		    		
		    		if(fileNm == null || fileNm == '') {
    					new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    					isClick = true;
    					return false;
		    		}
		    		 
		    		var obj = new Object();
		    		obj.fileNm = fileNm;
		    		obj.fileDivCd = fileDivCd;
		    		
		    		const formData = new FormData();
		    		formData.acceptChartset = "UTF-8";
		    		
		    		if(uploadFile.files.length > 0) {
		    			formData.append("uploadFile", uploadFile.files[0]);
		    			
		    			let uploadFileNm = uploadFile.files[0].name;
		    			var ext = uploadFileNm.slice(uploadFileNm.lastIndexOf(".")+1).toLowerCase();
	                    if(ext != 'hwp' && ext != 'hwpx' && ext != 'xlsx' && ext != 'xls' && ext != 'pdf'){
	    					new ModalBuilder().init().alertBoby(ext+' 유형의 파일은 등록이 불가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
	    					modalAlertWrap();
	                        return false;
	                    }
		    		} else {
    					new ModalBuilder().init().alertBoby("파일을 추가 해주세요").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    					isClick = true;
    					return false;
		    		}
		    		
		    		formData.append("fileMngTotInfo", new Blob([JSON.stringify(obj)], 
		    				{type: "application/json"}));
		    		
		    		$.ajax({
		    			type : "post",
		    			data : formData,
		    			contentType : false,
		    			processData : false,
		    			enctype : "multipart/form-data;charset=UTF-8",
		    			dataType : "json",
		    			url : "${pageContext.request.contextPath}/datamng/trfInfoCenter/save.ajax",
		    			success : function(result) {
		    				if(result.code == 200) {
		    					new ModalBuilder().init().successBody(result.message).footer(4, '확인', function(button, modal) {
		    						modal.close();
		    						location.href = ${pageContext.request.contextPath}"/datamng/trfInfoCenter/list.do";
		    					}).open();
		    				} else {
		    					new ModalBuilder().init().alertBoby(result.message).footer(4,'확인',function(button, modal){modal.close();}).open();
		    					modalAlertWrap();
		    					isClick = true;
		    				}
		    			}
		    		});
		    	}
		    }).open();
		   	$('.modal_footer').removeClass('none');
		});
	})
	</c:if>
	
	$("#searchBtn").on("click", function(){
		$("#searchForm").submit();
	})
	function fnSearchList() {
		document.getElementById("searchForm").action = "${pageContext.request.contextPath}/datamng/trfInfoCenter/list.do";
		document.getElementById("searchForm").submit();
	}
	
	//자료 상세보기
	function updateFileBtn(fileMngId){
		let isClick = true;
		new ModalBuilder().init('자료 상세정보').ajaxBody("${pageContext.request.contextPath}/common/modal/datamng/road/update.do?fileMngId="+fileMngId).footer(1,'저장',function(button, modal){
			if(isClick){
				//중복클릭 방지
				isClick = false;
				
				var fileNm = $("#fileNm").val();
				var fileMngId = $("#fileMngId").val();
				var fileDivCd = $("#fileDivCd").val();
				var uploadFile = $("#uploadFile")[0];
				
	    		if(fileNm == null || fileNm == '') {
					new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					isClick = true;
					return false;
	    		}
		    	
		    	var obj = new Object();
				obj.fileNm = fileNm;
				obj.fileMngId = fileMngId;
				obj.fileDivCd = fileDivCd;
				
				const formData = new FormData();
				formData.acceptChartset="UTF-8";
				
	    		if(uploadFile.files.length > 0 || isFileNotChanged) {
	    			formData.append("uploadFile", uploadFile.files[0]);
	    			
 	    			let uploadFileNm = uploadFile.files[0].name;
 	    			var ext = uploadFileNm.slice(uploadFileNm.lastIndexOf(".")+1).toLowerCase();
                     if(ext != 'hwp' && ext != 'hwpx' && ext != 'xlsx' && ext != 'xls' && ext != 'pdf'){
     					new ModalBuilder().init().alertBoby(ext+' 유형의 파일은 등록이 불가능합니다.').footer(4,'확인',function(button, modal){modal.close();}).open();
     					modalAlertWrap();
                         return false;
                     }
	    		} else {
					new ModalBuilder().init().alertBoby("파일을 추가 해주세요").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					isClick = true;
					return false;
	    		}
	    		
				formData.append("fileMngTotInfo",new Blob([JSON.stringify(obj)], {type: "application/json"}));
				
		    	$.ajax({
					type : "post",
					data : formData,
					contentType : false,
					processData : false,
					enctype : "multipart/form-data;charset=UTF-8",
					dataType : "json",
					url : "${pageContext.request.contextPath}/datamng/trfInfoCenter/update.ajax",
					success : function(data) {
						if(data.code == 200){
							new ModalBuilder().init().successBody(data.message).footer(4,'확인',function(button, modal){
								modal.close();
								location.reload();
								}).open();
							modalAlertWrap();
						} else {
							new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
						}
					}
				});
			}
	    }).open();
	   	$('.modal_footer').removeClass('none');
	}
	
	//삭제
	function deleteFileBtn(fileMngId){
		new ModalBuilder().init().alertBoby("삭제하시면 다시 복구 할 수 없습니다.<br>삭제하시겠습니까?").footer(5,'삭제하기',function(button, modal){
			$.ajax({
				type : "get",
				dataType : "json",
				url : "${pageContext.request.contextPath}/datamng/trfInfoCenter/"+fileMngId+"/delete.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody(data.message).footer(4,'확인',function(button, modal){
							modal.close();
							$(".modal_container").remove();
							location.href = ${pageContext.request.contextPath}"/datamng/trfInfoCenter/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
		},'취소하기',function(button, modal){
			modalAlertClose();    
		}).open();	
	}

</script>

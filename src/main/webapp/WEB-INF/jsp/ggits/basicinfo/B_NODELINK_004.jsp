<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
    <aside class="snb_container">
        <div class="snb_wrap">
            <h3 class="side_title">표준 노드/링크<br> 자료실</h3>
            <div class="side_txt">자료를 업로드 하거나<br>자료를 확인 할 수 있습니다.</div>
            <a href="${pageContext.request.contextPath}/basicinfo/dashboard.do" class="is-darkgreen-btn" style="font-size:0.8rem">GIS로 노드/링크 조회하기</a>
        </div>
    </aside>
    <section class="main_section">
        <h2 class="blind">표준노드링크 자료실</h2>
        <div class="table_btn_wrap clearfix">
        	<form id="searchForm" name="searchForm" action="/basicinfo/nodelink/reference/list.do">
	            <div class="btn_search_wrap float-left">
               			<input type="text" class="year_picker input_same input_picker" name="searchContent" id="searchContent" placeholder="날짜를 선택해주세요." autocomplete="off" readonly>
<%-- 	                <input type="text" placeholder="검색어를 입력하세요." id="searchContent" name="searchContent" class="input_same search_box" value="${searchContent ne null and searchContent ne '' ? searchContent :''}"> --%>
	                <input type="button" id="searchBtn" value="검색" class="input_same search_box2">
	            </div>
        	</form>
            <div class="float-right mt4">
               	<button type="button" id="uploadBtn" class="input_same search_box2">변경사항 업데이트</button>
            </div>
        </div>
        <table>
            <colgroup>
                <col style="width:10%">
                <col style="width:15%">
                <col style="width:20%">
                <col style="width:30%">
                <col style="width:10%">
                <col style="width:15%">
            </colgroup>
            <tr>
                <th scope="col" class="left">
	                <select class="table-filter">
	                	<option value="">유형</option>
	                	<option value="NODE">노드</option>
	                	<option value="LINK">링크</option>
	                </select>
                </th>
                <th scope="col">업데이트 일자</th>
                <th scope="col">제목</th>
                <th scope="col">업데이트 내용</th>
                <th scope="col">등록자</th>
                <th scope="col">작성일</th>
            </tr>
            <c:choose>
            	<c:when test="${not empty referenceList}">
		            <c:forEach var="referenceList" items="${referenceList}">
			            <tr onclick="getDetail('${referenceList.stdInfoId}')">
			                <td class="left">아직컬럼없음</td>
			                <td>
					    		<fmt:parseDate value="${referenceList.etlDt}" var="etlDt" pattern="yyyyMMdd"/>
								<fmt:formatDate value="${etlDt}" pattern="yyyy-MM-dd"/>
			                </td>
			                <td>${referenceList.stdInfoNm}</td>
			                <td>${referenceList.rmrk}</td>
			                <td>${referenceList.saveInfo}</td>
			                <td>
				    		<fmt:parseDate value="${referenceList.aplcnYmd}" var="aplcnYmd" pattern="yyyyMMdd"/>
							<fmt:formatDate value="${aplcnYmd}" pattern="yyyy-MM-dd"/>
			                </td>
			            </tr>
		            </c:forEach>
            	</c:when>
            	<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
            	</c:otherwise>
            </c:choose>
          </table>
          <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp" %>
    </section>
</div>
<script>
	/* modal start */
	/* 업로드 버튼 모달 */
	$('#uploadBtn').on("click", function(){
	    new ModalBuilder().init('노드/링크 변경사항 등록').ajaxBody("${pageContext.request.contextPath}/common/modal/nodelink/reference/save.do").footer(1,'저장',function(button, modal){
			var stdInfoNm = $("#stdInfoNm").val();
			var rmrk = $("#rmrk").val();
			var refTyCd = $("input[name='refTyCd']:checked").val();
			var etlDt = $("#etlDt").val() != null ? $("#etlDt").val().replaceAll("-","") : '';
			
			if(refTyCd == null || refTyCd == ''){
				new ModalBuilder().init().alertBoby("자료 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(stdInfoNm == null || stdInfoNm == ''){
				new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(etlDt == null || etlDt == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			} else {
				var regExp = /^[0-9]+$/;
				if(!regExp.test(etlDt)){
					new ModalBuilder().init().alertBoby("날짜 형식을 확인해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
			}
			
			
			var obj = new Object();
			obj.stdInfoNm = stdInfoNm;
// 			obj.refTyCd = refTyCd;
			obj.rmrk = rmrk;
			obj.etlDt = etlDt;
			
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/save.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("자료 등록에 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href = ${pageContext.request.contextPath}"/basicinfo/nodelink/reference/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("자료 등록을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			});
	    	modal.close();
	    }).open();
	    $('.modal_footer').removeClass('none');
	});
	
	$("#searchBtn").on('click',function(){
		fnSearchList();
	});
	
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/basicinfo/nodelink/reference/list.do";
		document.getElementById('searchForm').submit();
	}
	
	function getDetail(stdInfoId){
		new ModalBuilder().init('노드/링크 변경사항 수정').ajaxBody("${pageContext.request.contextPath}/common/modal/nodelink/reference/"+stdInfoId+"/detail.do").footer(3,'삭제',function(button, modal){
    		$.ajax({
    			type : "get",
    			url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/"+stdInfoId+"/delete.ajax",
    				success : function(result) {
    				var resultCode = result.code;
    				if(resultCode == '200'){
						new ModalBuilder().init().successBody("노드/링크 자료 삭제를 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
	    					location.reload();
						}).open();
						modalAlertWrap();
    				}else{
    					new ModalBuilder().init().alertBoby("노드/링크 자료 삭제를 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
    					modalAlertWrap();
    				}
	  			}
   			});
	    	modal.close();
	    }, '수정', function(button, modal){
	    	var stdInfoId = $("#stdInfoId").val();
			var stdInfoNm = $("#stdInfoNm").val();
			var rmrk = $("#rmrk").val();
			var refTyCd = $("input[name='refTyCd']:checked").val();
			var etlDt = $("#etlDt").val() != null ? $("#etlDt").val().replaceAll("-","") : '';
			
			if(refTyCd == null || refTyCd == ''){
				new ModalBuilder().init().alertBoby("자료 유형을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(stdInfoNm == null || stdInfoNm == ''){
				new ModalBuilder().init().alertBoby("자료명을 입력해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			}
			
			if(etlDt == null || etlDt == ''){
				new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				return false
			} else {
				var regExp = /^[0-9]+$/;
				if(!regExp.test(etlDt)){
					new ModalBuilder().init().alertBoby("날짜 형식을 확인해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
					return false;
				}
			}
			
			
			var obj = new Object();
			obj.stdInfoId = stdInfoId;
			obj.stdInfoNm = stdInfoNm;
// 			obj.refTyCd = refTyCd;
			obj.rmrk = rmrk;
			obj.etlDt = etlDt;
			
			$.ajax({
				type : "post",
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				data : JSON.stringify(obj),
				url : "${pageContext.request.contextPath}/basicinfo/nodelink/reference/update.ajax",
				success : function(data) {
					if(data.code == 200){
						new ModalBuilder().init().successBody("노드/링크 자료 수정을 성공하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.href = ${pageContext.request.contextPath}"/basicinfo/nodelink/reference/list.do";
						}).open();
						modalAlertWrap();
					} else {
						new ModalBuilder().init().alertBoby("노드/링크 자료 수정을 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
					}
				}
			})
	    	modal.close();
        }).open();
		$('.modal_footer').removeClass('none');
	}
	
	
	$(function(){
// 		연도만
		$('.year_picker').datepicker({
		    yearRange: "c-20:c",
		    changeMonth: false,
		    changeYear: true,
		    showButtonPanel: true,
		    closeText:'선택하기',
		    currentText: 'This year',
// 		    dataFormat:'yy년',
		    onClose: function(dateText, inst) {
		      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		      $(this).val($.datepicker.formatDate('yy년', new Date(year, 1, 1)));
		    }
		  }).focus(function () {
			 $(".ui-datepicker-month").hide();
		    $(".ui-datepicker-calendar").hide();
		    $(".ui-datepicker-current").hide();
		    $(".ui-datepicker-prev").hide();
		    $(".ui-datepicker-next").hide();
		    $("#ui-datepicker-div").position({
		      my: "left top",
		      at: "left bottom",
		      of: $(this)
		    });
		    $('table.ui-datepicker-calendar').hide();
		    $('.ui-datepicker').css({"width":"210px", "top":"198px"});
		    $('.ui-datepicker-year').css({"width":"100%","color":"#000","font-size":"1rem","font-family":"Pretendard","border":"1px solid #3e3e3e","border-radius":"0.425rem",});
		    $('.ui-datepicker .ui-datepicker-title').css({"line-height":"0","text-align":"left",});
		    $('.ui-widget-content').css({"border":"none"});
		    $('.ui-datepicker-close').css({"font-size":"0.875rem","font-weight":"700",})
		  }).attr("readonly", false);
	})


</script>
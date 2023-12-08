<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="pt24 pb24" style="width:34rem;">
	<form id="nodeLinkFrm">
		<div class="flex-center gap16 wd100">
			<div class="modal_input_box wd100">
				<div class="modal_input_label mb8">노드 및 링크 유형을 선택한 후 업데이트 내용을 입력해주세요.</div>
				<div class="flex-center">
					<label class="is-dark-btn is-sub-btn ftsize12"><input type="radio" name="refTyCd" class="none" value="NODE" checked>노드</label>
					<label class="is-dark-btn ftsize12"><input type="radio" name="refTyCd" class="none" value="LINK">링크</label>
				</div>
			</div>
		</div>
		<div class="flex-center gap16 wd100 mt16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">제목</label>
				<input type="text" id="stdInfoNm" name="stdInfoNm" class="modal_input" placeholder="제목을 입력해주세요."/> 
			</div>
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">노드/링크 업데이트 날짜</label>
				<input type="text" id="etlDt" name="etlDt" class="date_picker mr8 modal_input input_picker wd100" placeholder="날짜를 선택해주세요."/>
			</div>
		</div>
		<div class="flex-center gap16 wd100 mt16">
			<div class="flex-column modal_input_box wd100">
				<label class="modal_input_label">노드/링크 내용</label>
				<textarea id="rmrk" name="rmrk" class="modal_input" placeholder="업데이트된 노드/링크 내용을 입력하세요."></textarea> 
			</div>
		</div>
	</form>
</div>
<script>
	$(function(){
		datePickerInit();
		
		$('.is-dark-btn').on('change', function(){
			$(this).parent('.flex-center').find('.is-dark-btn').removeClass('is-sub-btn')
			$(this).addClass('is-sub-btn')
		})
	})
</script>

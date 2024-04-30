<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<div class="" id="codeSaveFrm">
	<div class="flex-center gap16">
		<input type="hidden" name="grpCdId" id="grpCdId" value="<c:out value='${mOpCode.grpCdId}'/>">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">코드</label>
			<input type="text" name="cdId" id="cdId" class="modal_input data-validate" placeholder="코드를 입력해 주세요."
				data-valid-name="코드" data-valid-required maxlength="20"/> 
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">코드명</label>
			<input type="text" name="cdNm" id="cdNm" class="modal_input data-validate" placeholder="코드명을 입력해 주세요."
				data-valid-name="코드명" data-valid-required maxlength="50"/> 
		</div>
	</div>
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">코드 설명</label>
			<input type="text" name="cdDescr" id="cdDescr" class="modal_input" placeholder="코드 설명을 입력해 주세요." maxlength="400"> 
		</div>
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">순위</label>
			<input type="text" name="sortNo" id="sortNo" class="modal_input data-validate" onkeyup="chkSortNo(this)" placeholder="1~99까지의 숫자를 입력해주세요."
				data-valid-name="순위" data-valid-required/> 
		</div>
	</div>
	<div class="flex-center gap16 mt16">
		<div class="flex-column modal_input_box">
			<label class="modal_input_label">사용 여부</label>
			<select class="modal_input_selectbox data-validate" name="cdUseYn" id="cdUseYn" data-valid-name="사용여부" data-valid-required>
                <option value="Y">사용</option>
                <option value="N">미사용</option>
            </select>
		</div>
	</div>
</div>
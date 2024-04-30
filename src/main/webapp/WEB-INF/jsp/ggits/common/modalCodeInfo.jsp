<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<div class="pt16 pb16">
	<form id="codeUpdFrm">
		<div class="flex-center gap16 mt16">
			<div class="flex-column modal_input_box">
				<input type="hidden" name="grpCdId" id="grpCdId" value="<c:out value='${mOpCode.grpCdId}'/>">
				<input type="hidden" name="cdId" id="cdId" value="<c:out value='${mOpCode.cdId}'/>">
				<label class="modal_input_label">코드</label>
				${mOpCode.cdId}
			</div>
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">코드명</label>
				<input type="text" name="cdNm" id="cdNm" class="modal_input data-validate" placeholder="코드명을 입력해 주세요." value="<c:out value='${mOpCode.cdNm}'/>"
					data-valid-name="코드명" data-valid-required maxlength="50"/> 
			</div>
		</div>
		<div class="flex-center gap16 mt16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">코드 설명</label>
				<input type="text" name="descr" id="descr" class="modal_input" placeholder="코드 설명을 입력해 주세요." value="<c:out value='${mOpCode.descr}'/>" maxlength="400"> 
			</div>
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">순위</label>
				<input type="text" name="sortNo" id="sortNo" class="modal_input data-validate" onkeyup='chkSortNo(this)' placeholder="1~99까지의 숫자를 입력해주세요." value="<c:out value='${mOpCode.sortNo}'/>"
					data-valid-name="순위" data-valid-required/> 
			</div>
		</div>
		<div class="flex-center gap16 mt16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">사용 여부</label>
				<select class="modal_input_selectbox data-validate" name="useYn" id="useYn" data-valid-name="사용여부" data-valid-required>
					<option value="Y" ${mOpCode.useYn eq 'Y' ?'selected':''}>사용</option>
		           	<option value="N" ${mOpCode.useYn eq 'N' ?'selected':''}>미사용</option>
            </select>
			</div>
		</div>
	</form>
</div>
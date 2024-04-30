<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="" style="width:34rem;">
	<form id="menuSaveFrm">
		<div class="flex-center gap16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인 메뉴명</label>
				<input type="text" id="menuNm" name="menuNm" class="modal_input data-validate" placeholder="메뉴명을 입력해 주세요." 
					data-valid-name="메인 메뉴명" data-valid-required maxlength="50"/> 
			</div>
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인 메뉴 URL 패턴</label>
				<input type="text" id="urlPttrn" name="urlPttrn" class="modal_input data-validate" placeholder="URL 패턴을 입력해 주세요. 예시) user/**"
					data-valid-name="메뉴 URL 패턴" data-valid-required maxlength="100"/> 
			</div>
		</div>
		<div class="flex-center gap16 wd100 mt16">
			<div class="flex-column modal_input_box">
				<label class="modal_input_label">메인 메뉴 URL</label>
				<input type="text" id="urlAddr" name="urlAddr" class="modal_input data-validate" placeholder="전체 URL을 입력해 주세요. 예시) /user/main" 
					data-valid-name="메뉴 URL" data-valid-required maxlength="100"/> 
			</div>
			<div class="flex-center modal_input_box">
				<div class="flex-column wd100">
					<label class="modal_input_label">노출여부</label>
					<select class="modal_input_selectbox" id="useYn" name="useYn">
			            <option value="Y">노출</option>
			           	<option value="N">비노출</option>
		        	</select>
				</div>
			</div>
		</div>
	</form>
	<div class="flex-center gap16 mt16">
		<div class="flex-center modal_input_box">
			<div class="flex-column wd100">
				<label class="modal_input_label">GIS 카테고리</label>
		        <select id="categCd" name="categCd" class="modal_input_selectbox">
		        	<option value="">없음</option>
		        	<c:forEach var="ctgryCdList"  items="${ctgryCdList}">
		        		<option value="<c:out value='${ctgryCdList.cdId}'/>"><c:out value='${ctgryCdList.cdNm}'/></option>
		        	</c:forEach>
		        </select>
			</div>
		</div>
	</div>
</div>
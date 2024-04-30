<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20202" checked="checked">안전거리미확보</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20306" checked="checked">미끄러운도로</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20302" checked="checked">급커브(굽은도로)</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20304" checked="checked">급경사(내리막)</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20313" checked="checked">기상-결빙</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20310" checked="checked">기상-비</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20311" checked="checked">기상-눈</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20309" checked="checked">침수</label>
<label class="danger_zone_item is-dark-btn radius inpd is-darkgreen-btn mb8"><input type="checkbox" class="none" name="dangerType" value="SF20204" checked="checked">과속</label>
<input type="hidden" id="searchDangerType" name="searchDangerType"/>

<script>

	const dangerZoneBtnInput = $(".danger_zone_item input"); 
	
	$(".danger_zone_all_selector").on("click", function(){
		$("#searchForm").trigger("change");
	    if ($(".danger_zone_item").hasClass("is-darkgreen-btn")) {
	        $(this).removeClass("is-darkgreen-btn");
	        $(this).siblings("label").removeClass("is-darkgreen-btn");
	        $(this).siblings(".danger_zone_item").find(dangerZoneBtnInput).prop('checked', false);
	    } else {
	        $(this).addClass("is-darkgreen-btn");
	        $(this).siblings("label").addClass("is-darkgreen-btn");
	        $(this).siblings(".danger_zone_item").find(dangerZoneBtnInput).prop('checked', true);
	    }
	})
	
    $(".danger_zone_item").on("change", function(){
        $(this).toggleClass("is-darkgreen-btn");
        $(this).siblings(".danger_zone_all_selector").removeClass("is-darkgreen-btn");
    })

</script>

<!-- 
 <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">도로 위험<br>유형별</h5> 
	            <select class="selectBox radius" name="dangerType">
	                <option value="">전체</option>
	                <option value="SF20202">안전거리미확보</option>
					<option value="SF20306">미끄러운도로</option>
					<option value="SF20302">급커브(굽은도로)</option>
					<option value="SF20304">급경사(내리막)</option>
					<option value="SF20313">기상-결빙</option>
					<option value="SF20310">기상-비</option>
					<option value="SF20311">기상-눈</option>
					<option value="SF20309">침수</option>
					<option value="SF20204">과속</option>
	            </select>
	        </div> -->
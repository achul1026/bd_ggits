$.fn.soValid = function(){
	let $wrap = $(this);
	let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,32}$/;
	let valid = true;
	
	function checkEmail(email) {
		var regExp = /^[a-z0-9A-Z._-]+@[a-z0-9A-Z._-]+\.[a-zA-Z.-]*$/i;
		if (regExp.test(email)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	function checkTel(tel) {
		tel = tel.replace(/-/gi, "");
	    var regExp = /^(010{1})[0-9]{3,4}[0-9]{4}$/i;
				//   /^[0-9]{2,4}-?[0-9]{3,4}-?[0-9]{4}$/ 
	    if (regExp.test(tel)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	$wrap.find(".data-validate").each(function(){
		let $validElement = $(this);
		let tagName = $(this).get(0).tagName.toUpperCase();
		let value = "";
		let attributeName = "";
		let labelname = $(this).attr("data-valid-name");
		if(tagName == "SELECT"){
			value = $validElement.find("option:selected").val();
			
			if(typeof $validElement.attr("data-valid-required") !== "undefined" && (isNull(value) || value.replaceAll(" ", "") == "")){
				msg = "을(를) 선택해주세요.";
				new ModalBuilder().init().alertBoby(labelname+msg).footer(4,'확인',function(button, modal){
					modal.close();
					$validElement.focus();
				}).open();
				modalAlertWrap();				
			valid = false;
			return false;
			}
		}else{
			if($validElement.attr("type").toUpperCase() == "RADIO" || $validElement.attr("type").toUpperCase() == "CHECKBOX"){
				attributeName = $validElement.attr("name");
				value = $wrap.find(`input[type='${$validElement.attr("type")}'][name='${attributeName}']:checked`).val();
			}else{
				value = $validElement.val();
			}
		}
		if(typeof $validElement.attr("data-valid-required") !== "undefined" && (isNull(value) || value.replaceAll(" ", "") == "")) {
			let msg = "을(를) 입력해주세요.";
			if($validElement.attr("type").toUpperCase() == "RADIO" || $validElement.attr("type").toUpperCase() == "CHECKBOX"){
				msg = "을(를) 선택해주세요.";
			}
			new ModalBuilder().init().alertBoby(labelname+msg).footer(4,'확인',function(button, modal){
				$validElement.focus();
				modal.close();
			}).open();
			modalAlertWrap();			
			valid = false;
			return false;
		}
		if(typeof $validElement.attr("data-valid-email") !== "undefined" && !checkEmail(value)) {
			new ModalBuilder().init().alertBoby("이메일 형식이 아닙니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();			
			valid = false;
			return false;
		}
		
		if(typeof $validElement.attr("data-valid-phone") !== "undefined" && !checkTel(value)) {
			new ModalBuilder().init().alertBoby("연락처 정보가 올바르지 않습니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();				
			valid = false;
			return false;
		}
		if(typeof $validElement.attr("data-valid-maximum") !== "undefined" && value.length > parseInt($validElement.attr("data-valid-maximum"))) {
			let max = $validElement.attr("data-valid-maximum");
			new ModalBuilder().init().alertBoby(labelname+"은(는) "+max+" 자리를 넘어갈 수 없습니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();				
			valid = false;
			return false;
		}
		
		if(typeof $validElement.attr("data-valid-minimum") !== "undefined" && value.length < parseInt($validElement.attr("data-valid-minimum"))) {
			let min = $validElement.attr("data-valid-minimum");
			new ModalBuilder().init().alertBoby(labelname+"은(는) "+min+"자리보다 커야합니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();				
			valid = false;
			return false;
		}
		
		if(typeof $validElement.attr("data-valid-maximum-number") !== "undefined" && parseInt(value) > parseInt($validElement.attr("data-valid-maximum-number"))) {
			let max = $validElement.attr("data-valid-maximum-number");
			new ModalBuilder().init().alertBoby(labelname+"은(는) "+max+"보다 클 수없습니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();				
			valid = false;
			return false;
		}
		
		if(typeof $validElement.attr("data-valid-minimum-number") !== "undefined" && parseInt(value) < parseInt($validElement.attr("data-valid-minimum-number"))) {
			let min = $validElement.attr("data-valid-minimum-number");
			new ModalBuilder().init().alertBoby(labelname+"은(는) "+min+"보다 커야합니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();			
			valid = false;
			return false;
		}
		
		if(typeof $validElement.attr("data-valid-password") !== 'undefined' && !passwordRegExp.test(value)) {
			new ModalBuilder().init().alertBoby("비밀번호 값이 올바르지 않습니다.").footer(4,'확인',function(button, modal){
				modal.close();
				$validElement.focus();			
			}).open();
			modalAlertWrap();			
			valid = false;
			return false;
		}

		if(typeof $validElement.attr("data-valid-startDate") !== "undefined"){
			startDate = value;
		}

		if(typeof $validElement.attr("data-valid-endDate") !== "undefined"){
			endDate = value;
		}
	});

	return valid;
}
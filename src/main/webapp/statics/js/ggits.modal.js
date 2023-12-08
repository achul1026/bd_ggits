//modal custom

function isNull(value){
	if(typeof value == "undefined" || value == null || value == "null" || (value.constructor.name.toLowerCase() == "string" && value == "")) {
		return true;
	}else{
		return false;
	}
}
function randomString(num) {
	const char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	let result = '';
	for(let i = 0; i < num; i++){
		result += char.charAt(Math.floor(Math.random() * char.length));
	}
	return result;
}

 /*
	alert 
	ex) new ModalBuilder().init().alertbody('컨텐츠').footer(4,'버튼명',function(button, modal){modal.close();}).open();
	success
	ex) newModalBuilder().init().successBody('컨텐츠').footer(4,'버튼명',function(button, modal){modal.close();}).open();
	modal
	ex) new ModalBuilder().init('타이틀').ajaxBody(url, data).footer(2,'버튼명',function(button, modal){modal.close();}).open(); 
 */

let ModalBuilder = function(){
    let _self = this;
    let titleText = null;
    let opening = false;
    let $container, $inner, $wrap, $content, $header, $modal;
    let containerId, modalId;
    let promise = null;

    let $headerCloseBtn = $(`<button type="button" class="is-close"><img src="/statics/images/close.png" alt="닫기"></button>`);
    _self.$body = null;

    const UI = {
        WRAP : function(containerId, modalId, width) {
            $container = $(`<div id="${containerId}" class="modal_container"></div>`);
            $content = $(`<div id="${modalId}" class="modal_content"></div>`);

            if(isNull(width)){
                $content.css({"max-width":"60rem"});
            } else {
                $content.css({"max-width":width+"px"});
            }

            $modal = $(`<div class="modal"><span class="blind">모달백그라운드</span></div>`)
            $inner = $(`<div class="modal_wrap terms"></div>`);
            $inner.append($content);
            $container.append($modal);
            $container.append($inner);
            /*$("body").addClass("hidden");*/
            return $container;
        },
        HEADER : function(title) {
            if(title != undefined || title != null) {
                return $(`<div class="modal_header"><h3 class="modal_title">${title}</h3></div>`)
            }
        },
        BODY : function(content) {
            if(typeof content !== 'string') {
                let body = $(`<div class="modal_body"><div class="modal_txt"></div></div>`);
                body.find('.modal_txt').append(content);
                return body;
            }else{
                return $(`<div class="modal_body"><div class="modal_txt">${content}</div></div>`);
            }
        },
		ALERT_BODY : function(content) {
			let alertBody = $(`<div class="modal_body pt32 pb8"><div class="flex-column"><div class="alert_img"><img src="/statics/images/alert.png" alt="경고"></div><div class="modal_txt modal_alert"></div></div></div>`);
			if(content !== null){
				alertBody.find('.modal_txt').append(`${content}`)
			}
			return alertBody;
		},
		SUCCESS_BODY : function(content) {
			let alertBody = $(`<div class="modal_body pt32 pb8"><div class="flex-column"><div class="alert_img"><img src="/statics/images/success.png" alt="성공"></div><div class="modal_txt modal_alert"></div></div></div>`);
			if(content !== null){
				alertBody.find('.modal_txt').append(`${content}`)
			}
			return alertBody;
		},
		FOOTER : function(buttonText, callback){
			let $footerWrap = $(`<div class="modal_footer none"></div>`);
			let $footerButton = $(`<button class="modal_footer_btn">${buttonText}</button>`);
			$footerButton.on("click", function(){
				if(!isNull(callback)){
					 callback($(this), _self);
				}
			});
			$footerWrap.append($footerButton);
			return $footerWrap; 
		},		
		FOOTER_ALERT : function(buttonText, callback){
			let $footerWrap = $(`<div class="modal_alert_footer"></div>`);
			let $footerButton = $(`<button class="modal_footer_alert_btn">${buttonText}</button>`);
			$footerButton.on("click", function(){
				if(!isNull(callback)){
					 callback($(this), _self);
				}
			});
			$footerWrap.append($footerButton);
			return $footerWrap; 
		},
		FOOTER_TWO_BUTTON : function(buttonText, callback){
			let $footerWrap = $(`<div class="modal_footer flex-between none"></div>`);
			let $footerButton = $(`<button class="modal_footer_remove_btn">${buttonText}</button>`);
			let $footerCloseButton = $(`<button class="modal_footer_btn">닫기</button>`);
			$footerButton.on("click", function(){
				if(!isNull(callback)){
					 callback($(this), _self);
				}
			});
			
			$footerCloseButton.on("click", function(){
				_self.close();
			});
			
			$footerWrap.append($footerButton);
			$footerWrap.append($footerCloseButton);
			return $footerWrap; 
		},
		FOOTER_CUSTOM_TWO_BUTTON : function(buttonText, callback, buttonText2, callback2){
			let $footerWrap = $(`<div class="modal_footer none"></div>`);
			let $footerButton = $(`<button class="modal_footer_remove_btn">${buttonText}</button>`);
			let $footerCloseButton = $(`<button class="modal_footer_btn">${buttonText2 ? buttonText2 : "닫기"}</button>`);
			$footerButton.on("click", function(){
				if(!isNull(callback)){
					 callback($(this), _self);
				}
			});
			
			$footerCloseButton.on("click", function(){
				if(!isNull(callback2)){
					callback2($(this), _self);
				}else{
					_self.close();	
				}
			});
			
			$footerWrap.append($footerButton);
			$footerWrap.append($footerCloseButton);
			return $footerWrap; 
		},
		FOOTER_ALERT_TWO_BUTTON : function(buttonText, callback, buttonText2, callback2){
			let $footerWrap = $(`<div class="modal_footer"></div>`);
			let $footerButton = $(`<button class="modal_footer_remove_btn is-key-color-700">${buttonText}</button>`);
			let $footerCloseButton = $(`<button class="modal_footer_btn is-black-color modal_alret_close" onclick="modalAlertClose()">${buttonText2 ? buttonText2 : "닫기"}</button>`);
			$footerButton.on("click", function(){
				if(!isNull(callback)){
					 callback($(this), _self);
				}
			});
			
			$footerCloseButton.on("click", function(){
				if(!isNull(callback2)){
					callback2($(this), _self);
				}else{
					_self.close();	
				}
			});
			
			$footerWrap.append($footerButton);
			$footerWrap.append($footerCloseButton);
			return $footerWrap; 
		}
    }

    const checkInit = function(){
        if($wrap == null){
            console.error("Modal 오픈은 init() 으로 시작해주세요.");
            return false;
        }
        return true;
    }

    _self.init = function (title, width, isHeader = true){
        let id = randomString(6)+"-"+Date.now();
        opening = true;
        containerId = "modal-container-"+id;
        modalId = "modal-"+id;
        $wrap = UI.WRAP(containerId,modalId,width);

        titleText = title;
        if(isHeader == null || isHeader){
            $header = UI.HEADER(title);
            $content.append($header);
        }
        if(!isNull($header)){
            $headerCloseBtn.on("click", function(){
                _self.close();
            })
            $header.append($headerCloseBtn)
        }
        return _self;
    }

    _self.body = function(content){
        if(!checkInit()) return false;
        _self.$body = UI.BODY(content);
        $content.append(_self.$body);
        return _self;
    }

	_self.alertBoby = function(content){
		if(!checkInit()) return false;
		_self.$alertBody = UI.ALERT_BODY(content);
		$content.append(_self.$alertBody);
		$content.find('.modal_header').remove();
		return _self;
	}
	
	_self.successBody = function(content){
		if(!checkInit()) return false;
		_self.$successBody = UI.SUCCESS_BODY(content);
		$content.append(_self.$successBody);
		$content.find('.modal_header').remove();
		return _self;
	}
	
	// html 로드
	_self.ajaxBody = function(url,data, method = "get", contentType) {
		let opt = {};
		promise = new Promise(function(resolve, reject){
			window.modalUrl = url;
			if(method == 'post'){
				opt = {
					type : method,
					url : url,
					async : false,
					contentType : "application/json",
					dataType : "html",
					success : function(html){
						_self.$body = UI.BODY(html);
						$content.append(_self.$body);
						resolve();
					}
				}
			}else{
				
				opt = {
					type : method,
					url : url,
					async : false,
					dataType : "html",
					success : function(html){
						_self.$body = UI.BODY(html);
						$content.append(_self.$body);
						resolve();
					}
				}
			}
			if(!isNull(contentType)) opt.contentType = contentType
			if(!isNull(data)) opt.data = data;
			$.ajax(opt);
		});
		return _self;
	}

	_self.footer = function(type, buttonText, callback, customCancelButtonText, cancelCallback) {
		
		if(!checkInit()) return false;
		let footerButtonType = "FOOTER";
		if(parseInt(type) == 2) footerButtonType = "FOOTER_TWO_BUTTON";
		if(parseInt(type) == 3) footerButtonType = "FOOTER_CUSTOM_TWO_BUTTON";
		if(parseInt(type) == 4) footerButtonType = "FOOTER_ALERT";
		if(parseInt(type) == 5) footerButtonType = "FOOTER_ALERT_TWO_BUTTON";
		if(footerButtonType == "FOOTER_TWO_BUTTON"){
			$footer = UI[footerButtonType](buttonText, callback, cancelCallback);
		} else if(footerButtonType == "FOOTER_CUSTOM_TWO_BUTTON", "FOOTER_ALERT_TWO_BUTTON"){
			$footer = UI[footerButtonType](buttonText , callback , customCancelButtonText, cancelCallback);
		} else{
			$footer = UI[footerButtonType](buttonText, callback);	
		}
		
		$content.append($footer);
		
		return _self;
	}

    _self.open = function(callback){
        if(!checkInit()) return false;
        if(promise !== null) {
            promise.then(function(){
                promise = null;
                generate(callback);
                return;
            });
        }
        generate(callback);
        return _self;
    }

    _self.close = function(){
		window.modalUrl = null;
		$content.fadeOut(10, function(){
            $("#"+containerId).remove();
        });
        $("body").removeClass("hidden");
	}

   function generate(callback){
        $("body").append($container).ready(function(){
            $container.show();
            opening = false;
        })
   }

   return _self;
   
}


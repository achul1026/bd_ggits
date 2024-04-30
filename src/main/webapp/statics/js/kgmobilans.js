

/*
 * 외부가맹점에서 KG모빌리언스 Mcash를 연동하기 위해 include 할 javascript 파일
 */
var PG_URL = "";
var MCASH_MAIN_URL = "https://auth.mobilians.co.kr/goCashMain.mcash";
var M_PG_URL = "";


var PAY_WIN;

(function($, win){

    win.identityManager = new function(){
	
        this.identityProcess = function(data){
	
			var arg = {cashGb:"", siteUrl:"", tradeId:"", ciSvcid:"", ciMode:"", okUrl:"", cryptYn:"", keygb:""};
            arg = $.extend(arg, data);

			identityProcessManager.identityExecute(arg);
        };
		
		
        this.identityCallback = function(arg,data){
            arg.callbackFunc(data);
        };

    };


    win.identityProcessManager = new function(){

        this.identityArg; 

        this.identityExecute = function(arg){
	

            if(arg.cashGb == ""){
				new ModalBuilder().init().alertBoby("CI 고정값이 존재하지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return false;
            }

            identityProcessManager.identityArg = arg; 
			
			//form 생성
            identityProcessManager.identityFormCreate();

            //본인 인증 호출
            identityProcessManager.identityCall();

        };


        // 다우페이 결제모듈 처리요청
        this.identityCall = function(){


            var identityForm  = document.identityForm;
			
			
			// 대표캐쉬구분 있는경우 우선적용 	
			if( identityForm.CASH_GB.value != "" ){		
				identityForm.CASH_GB.value = "";			
			} 
			
			if( identityForm.CASH_GB.value == "" ){	
				identityForm.CASH_GB.value = "CI";
			}
			
			var mobile_flag = false;	
			
		    if(window.navigator.userAgent.indexOf("Mobile") >= 0 ||
		       window.navigator.userAgent.indexOf("Phone") >= 0 ||
		       window.navigator.userAgent.indexOf("Opera") >= 0 ||
		       window.navigator.userAgent.indexOf("Safari") >= 0) mobile_flag = true;
		    
		    var UserAgent = navigator.userAgent;
		    if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null 
		   		|| UserAgent.match(/LG|SAMSUNG|Samsung/) != null) mobile_flag = true;
		    else mobile_flag = false;
		        
		    //실서버모드 이면 실제 URL 로 변환
			if(identityForm.PAY_MODE.value == '10' ){
				MCASH_MAIN_URL = "https://auth.mobilians.co.kr/goCashMain.mcash";
			}else{
				MCASH_MAIN_URL = "https://authtest.mobilians.co.kr/goCashMain.mcash";
			}
				
			identityForm.action = MCASH_MAIN_URL;
			if(identityForm.CALL_TYPE.value == "SELF"){
				identityForm.target="_self"; 
			}else if(identityForm.CALL_TYPE.value == "I"){  // 2013.01.24 추가
				identityForm.target = identityForm.IFRAME_NAME.value; 			
		    }else{
		    	if(mobile_flag) {		    		
		    		PAY_WIN = window.open("","PAY_WIN","fullscreen=yes,toolbar=yes,menubar=yes,scrollbars=no,resizable=no");
		    	}else{
		   			PAY_WIN = window.open("","PAY_WIN","width=400,height=631,toolbar=no,menubar=no,scrollbars=no,resizable=yes");
		    		//PAY_WIN.opener = self;
		    	}
				PAY_WIN.focus();
				identityForm.target="PAY_WIN";
		    }
		    
		    var orgCharset = document.charset;
		    
		    try{
		    	// 가맹점 charset 복구용 
		        identityForm.acceptCharset='euc-kr';
		    	// 모빌리언스 charset euc-kr 이므로 강제변경
		    	document.charset = identityForm.acceptCharset;
		    	identityForm.submit();
		        // 가맹점 charset 원복
		        document.charset = orgCharset;
		    }catch(e){
		    	identityForm.submit();
		    }
        };


        this.identityFormCreate = function(){

            var formHtml = [];
            formHtml.push('<form id="identityForm" name="identityForm">\n');
            formHtml.push('<input type="hidden" name="CASH_GB" 			value="'+identityProcessManager.identityArg.cashGb+'" />\n');    //구분(CI 고정값)
            formHtml.push('<input type="hidden" name="PAY_MODE" 		value="10" />\n');      					  		//연동시 테스트 /실거레 구분 (00:테스트 , 10:실거래)
            formHtml.push('<input type="hidden" name="Siteurl" 			value="'+identityProcessManager.identityArg.siteUrl+'" />\n');   //가맹점 도메인
            formHtml.push('<input type="hidden" name="Tradeid" 			value="'+identityProcessManager.identityArg.tradeId+'" />\n');   //가맹점 거래번호
            formHtml.push('<input type="hidden" name="CI_SVCID" 		value="'+identityProcessManager.identityArg.ciSvcid+'" />\n');   //모빌리언스에서 부여한 서비스
            formHtml.push('<input type="hidden" name="CI_Mode" 			value="'+identityProcessManager.identityArg.ciMode+'" />\n');    //요청 구분 (41:LMG문구 설정, 51:SMS 문구 설정)
            formHtml.push('<input type="hidden" name="Okurl" 			value="'+identityProcessManager.identityArg.okUrl+'" />\n');     //인증 완료 후 사용자에게 보여질 가맹점 완료페이지
            formHtml.push('<input type="hidden" name="Cryptyn" 			value="'+identityProcessManager.identityArg.cryptYn+'" />\n');   //암호화 사용 여부
            formHtml.push('<input type="hidden" name="Keygb" 			value="'+identityProcessManager.identityArg.keygb+'" />\n');      //암호화 KEY 지정값(1 OR 2)
            formHtml.push('<input type="hidden" name="CALL_TYPE" 		value="P" />\n');      					  			//인증창 호출 방식 (P:팝업, SELF:페이지 전환, I:아이프레임)

            formHtml.push('</form>\n');
            $("#identityForm").remove();            // 다우페이 form 제거
            $(formHtml.join('')).appendTo("body"); // 다우페이 form 추가
        };

		this.identityResultCallback = function(resultArg,resultData){
            identityManager.identityCallback(resultArg,resultData);
        };

    };
})(jQuery, window);
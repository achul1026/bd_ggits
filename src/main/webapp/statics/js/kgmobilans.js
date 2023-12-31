/*
 * 외부가맹점에서 KG모빌리언스 Mcash를 연동하기 위해 include 할 javascript 파일
 */
var PG_URL = "";
var MCASH_MAIN_URL = "https://auth.mobilians.co.kr/goCashMain.mcash";
var M_PG_URL = "";


var PAY_WIN;

function IDENTITY_VERIFICATION(mcashForm){

	// 대표캐쉬구분 있는경우 우선적용 	
	if( mcashForm.CASH_GB.value != "" ){		
		try {
			if( eval("mcashForm." + mcashForm.CASH_GB.value + "_SVCID.value") == "" )
				mcashForm.CASH_GB.value = "";			
		} catch (exception){
			mcashForm.CASH_GB.value = "";
		}			
	} 
	
	if( mcashForm.CASH_GB.value == "" ){	
		mcashForm.CASH_GB.value = "CI";
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
	if(mcashForm.PAY_MODE.value == '10' ){
		MCASH_MAIN_URL = "https://auth.mobilians.co.kr/goCashMain.mcash";
	}else{
		MCASH_MAIN_URL = "https://authtest.mobilians.co.kr/goCashMain.mcash";
	}
		
	mcashForm.action = MCASH_MAIN_URL;
	if(mcashForm.CALL_TYPE.value == "SELF"){
		mcashForm.target="_self"; 
	}else if(mcashForm.CALL_TYPE.value == "I"){  // 2013.01.24 추가
		mcashForm.target = mcashForm.IFRAME_NAME.value; 			
    }else{
    	if(mobile_flag) {		    		
    		PAY_WIN = window.open("","PAY_WIN","fullscreen=yes,toolbar=yes,menubar=yes,scrollbars=no,resizable=no");
    	}else{
   			PAY_WIN = window.open("","PAY_WIN","width=400,height=631,toolbar=no,menubar=no,scrollbars=no,resizable=yes");
    		PAY_WIN.opener = self;
    	}
		PAY_WIN.focus();
		mcashForm.target="PAY_WIN";
    }
	/*
    mcashForm.method="post";
    
    // 가맹점 charset 복구용 
    var orgCharset = document.charset;	    
    
    mcashForm.acceptCharset='euc-kr';
    // 모빌리언스 charset euc-kr 이므로 강제변경  	    
    document.charset = mcashForm.acceptCharset; 
    	    
    mcashForm.submit();
    
    // 가맹점 charset 원복
    document.charset = orgCharset;
    */
    try{
    	mcashForm.method="post";
    } catch(e){
    }
    
    var orgCharset = document.charset;
    
    try{
    	// 가맹점 charset 복구용 
        mcashForm.acceptCharset='euc-kr';
    	// 모빌리언스 charset euc-kr 이므로 강제변경
    	document.charset = mcashForm.acceptCharset;
    	mcashForm.submit();
        // 가맹점 charset 원복
        document.charset = orgCharset;
    }catch(e){
    	mcashForm.submit();
    }
}
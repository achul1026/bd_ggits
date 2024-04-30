package com.neighbor21.ggits.web.controller.kgmobilians;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

import mup.mcash.module.common.McashCipher.McashCipher;


@Controller
@RequestMapping("/kg")
public class KGMobilansController {
	
	@Value("#{commonProperties['kg.mobilans.cash_gb']}")
	public String CASH_GB;
	
	@Value("#{commonProperties['kg.mobilans.ci_svcid']}")
	public String CI_SVCID;
	
	@Value("#{commonProperties['kg.mobilans.pay_mode']}")
	public String PAY_MODE;
	
	@Value("#{commonProperties['kg.mobilans.ci_mode']}")
	public String CI_MODE;
	
	@Value("#{commonProperties['kg.mobilans.site_url']}")
	public String SITE_URL;
	
	@Value("#{commonProperties['kg.mobilans.trade_id']}")
	public String TRADE_ID;
	
	@Value("#{commonProperties['kg.mobilans.ok_url']}")
	public String OK_URL;
	
	@Value("#{commonProperties['kg.mobilans.cryptyn']}")
	public String CRYPT_YN;
	
	@Value("#{commonProperties['kg.mobilans.keygb']}")
	public String KEYGB;
	
	@Value("#{commonProperties['kg.mobilans.key']}")
	public String KEY;
	
    @GetMapping("/base/info.ajax")
    public @ResponseBody CommonResponse<?> baseInfo(HttpServletRequest request){
    	
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	String protocol = "https://";
    	String serverName = "ggdata.gg.go.kr";
//    	String host = request.getRemoteHost();리
    	String okUrl = protocol+serverName;
    	String port = String.valueOf(request.getServerPort());
//    	if(!GgitsCommonUtils.isNull(port)) {
//    		okUrl = okUrl+":"+ port;
//    	}
    	okUrl = okUrl + OK_URL;
    	
    	resultMap.put("CASH_GB", CASH_GB);
    	resultMap.put("CI_SVCID", CI_SVCID);
    	resultMap.put("TRADE_ID", GgitsCommonUtils.getUuid());
    	resultMap.put("PAY_MODE", PAY_MODE);
    	resultMap.put("SITE_URL", SITE_URL);
    	
    	resultMap.put("OK_URL", okUrl);
//    	resultMap.put("OK_URL", OK_URL);
    	resultMap.put("CI_MODE", CI_MODE);
    	resultMap.put("CRYPT_YN", CRYPT_YN);
    	resultMap.put("KEYGB", KEYGB);
    	
 		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "sueccess" ,resultMap);
    }
    
    @PostMapping("/identity/verification/success.do")
    public String identityVerificationSueccess(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	
//    	request.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("EUC-KR");

    	String Svcid		= request.getParameter("Svcid");		//서비스아이디
    	String Mobilid 		= request.getParameter("Mobilid");		//모빌리언스 거래번호
    	String Signdate		= request.getParameter("Signdate");		//결제일자
    	String Tradeid		= request.getParameter("Tradeid");		//가맹점 거래번호
    	
    	String Name			= request.getParameter("Name");			//이름
    	String No			= request.getParameter("No");			//휴대폰번호
    	String Commid		= request.getParameter("Commid");		//이동통신사
    	String Resultcd		= request.getParameter("Resultcd");		//결과코드
    	String Resultmsg	= request.getParameter("Resultmsg");	//결과메시지
    	
    	String Cryptyn		= request.getParameter("Cryptyn");		//암호화 사용 여부 (default : Y)
    	String Keygb		= request.getParameter("Keygb");		//암호화 Key 구분 (1 or 2 : 가맹점 관리자 등록 후 사용)
    	
    	String Socialno		= request.getParameter("Socialno");		//생년월일
    	String Sex			= request.getParameter("Sex");			//성별 (남성:M, 여성:F)
    	String Foreigner	= request.getParameter("Foreigner");	//외국인여부 (외국인 : Y)
    	
    	String Ci			= request.getParameter("Ci");			//CI
    	String Di			= request.getParameter("Di");			//DI
    	
    	String CI_Mode		= request.getParameter("CI_Mode");		//CI_Mode 41:LMS문구설정, 51:SMS문구설정, 61:SMS발송
    	String DI_Code		= request.getParameter("DI_Code");		//웹사이트코드
    	String Mac			= request.getParameter("Mac");			//검증키
    	
    	String MSTR			= request.getParameter("MSTR");			//가맹점 확장 변수
    	
    	/*********************************************************************************
    	' Okurl 암호화 사용 시 사용자정보 암호문 전달
    	' 주) 고유KEY 설정 시 비밀키 : "가맹점 고유 KEY + CI_SVCID 앞 8자리" (16byte)    	// Keygb 1 or 2
    	'	  "userKey" 항목에 key 세팅
    	*********************************************************************************/
    	String secretSvcId = Svcid.substring(0, 8); 
    	String secretKey = KEY + secretSvcId;
    	Name			= McashCipher.decodeString( Name, secretKey );
    	No				= McashCipher.decodeString( No, secretKey );
    	Commid			= McashCipher.decodeString( Commid, secretKey );
    	Socialno		= McashCipher.decodeString( Socialno, secretKey );
    	Sex				= McashCipher.decodeString( Sex, secretKey );
    	Foreigner		= McashCipher.decodeString( Foreigner, secretKey );
    	Ci				= McashCipher.decodeString( Ci, secretKey );
    	Di				= McashCipher.decodeString( Di, secretKey );
    	
    	/*********************************************************************************
    	* Mac값 위변조 여부 확인 SHA256(Signdate+Di+Ci+Mobilid+Svcid.substring(0, 8)+Svcid.substring(0, 8))
    	**********************************************************************************/
    	String key = Signdate+Di+Ci+Mobilid+secretSvcId+secretSvcId;
    	MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
    	sha256.update(key.getBytes());
    	byte[] byteData = sha256.digest();
    	
    	String sha = Hex.encodeHexString(byteData);
    	String resultMsg = "정상 데이터";
    	
    	if( !Mac.equals(sha)){
    		resultMsg = "데이터가 위·변조되었습니다.";
    		Resultcd = "9999";
    	}
    	
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("resultCode", Resultcd);
    	resultMap.put("resultMsg", resultMsg);
    	resultMap.put("userName", Name);
    	resultMap.put("tel", No);
    	
    	model.addAttribute("result", resultMap);
    	
    	return "modal/common/identityVerificationSueccess";
    }
    
}

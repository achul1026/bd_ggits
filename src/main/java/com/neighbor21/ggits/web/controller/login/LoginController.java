package com.neighbor21.ggits.web.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.web.service.login.LoginService;
import com.neighbor21.ggits.web.service.systemmng.UserMngService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserMngService userMngService;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
    /**
      * @Method Name : viewLogin
      * @작성일 : 2023. 8. 22.
      * @작성자 : 82103
      * @Method 설명 : 로그인 화면
      * @return
     * @throws Exception 
      */
    @GetMapping("/login.do")
    public String viewLogin() {
        return "login/login";
    }
    
    /**
      * @Method Name : login
      * @작성일 : 2023. 8. 25.
      * @작성자 : NK.KIM
      * @Method 설명 : 로그인 프로세스 
      * @param mOpOperator
      * @param model
      * @param session
      * @return Map<String,Object>
      */
    @PostMapping("/login.ajax")
    public @ResponseBody CommonResponse<?> login(MOpOperator mOpOperator, Model model, HttpSession session, HttpServletRequest req){
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
    	dtoValidator
			        .addRule("oprtrPswd", new ValidateChecker().setPassword().setRequired())
			        .addRule("oprtrEmail", new ValidateChecker().setEmail().setRequired());
    	
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		MOpOperator mOpOperatorInfo = loginService.findOneMOpOperatorByEmail(mOpOperator);
    		
    		if(mOpOperatorInfo != null) {
    			session.setAttribute("mOpOperatorInfo", mOpOperatorInfo);
    			//세션 유지 시간 최대 30분
    			session.setMaxInactiveInterval(1800);
    			
    			//사용자 로그인 로그 저장
    			loginService.saveLOpPgmLogn(mOpOperatorInfo, req, "ULC001");
    			
    			// 오늘 날짜 저장
    			session.setAttribute("today", BDDateFormatUtil.isNowStr("yyyy년 MM월 dd일"));
    			
    		}
    	}catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
 		return CommonResponse.ResponseCodeAndMessage(0000 , "하위 메뉴 등록중 오류가 발생했습니다.");
    }
    
    /**
     * @Method Name : viewJoinUs
     * @작성일 : 2023. 9. 8.
     * @작성자 : 82103
     * @Method 설명 : 로그아웃 프로세스
     * @return
     */
    @GetMapping("/logout.ajax")
    public @ResponseBody CommonResponse<?> logout(Model model, HttpSession session, HttpServletRequest req) {
    	try {
    		MOpOperator mOpOperatorInfo = (MOpOperator) session.getAttribute("mOpOperatorInfo");
    		
    		if(mOpOperatorInfo != null) {
        		//로그아웃 로그 저장
        		loginService.saveLOpPgmLogn(mOpOperatorInfo, req , "ULC002");
        		//세션 초기화
        		session.invalidate();
    		}
    	}catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "");
    }
                
    
    
    /**
      * @Method Name : viewJoinUs
      * @작성일 : 2023. 8. 22.
      * @작성자 : 82103
      * @Method 설명 : 관리자 등록 화면
      * @return
      */
    @GetMapping("/joinUs.do")
    public String viewJoinUs(){
    	
    	return "login/joinUs";
    }
    
    /**
     * @Method Name : findPw
     * @작성일 : 2023. 8. 22.
     * @작성자 : KY.LEE
     * @Method 설명 : 비밀번호 찾기 화면
     * @return
     */
    @GetMapping("/findPw.do")
    public String findPw(){
    	
    	return "login/findPw";
    }
    
    /**
     * @Method Name : resetPw
     * @작성일 : 2023. 8. 22.
     * @작성자 : KY.LEE
     * @Method 설명 : 비밀번호 찾기 화면
     * @return
     */
    @GetMapping("/resetPw.do")
    public String resetPw(
    		@RequestParam(value="oprtrId") Long oprtrId ,
    		@RequestParam(value="oprtrNm") String oprtrNm ,
    		@RequestParam(value="oprtrTel") String oprtrTel ,
    		Model model) {
    	MOpOperator mOpOperator = new MOpOperator();
    	mOpOperator.setOprtrId(oprtrId);
    	mOpOperator.setOprtrNm(oprtrNm);
    	mOpOperator.setOprtrTel(oprtrTel);
    	
 		MOpOperator dbMOpOperator = loginService.findOneMOpOperatorByNmAndTelAndId(mOpOperator);
 		
    	model.addAttribute("oprtrEmail", dbMOpOperator.getOprtrEmail());
    	model.addAttribute("oprtrNm", oprtrNm);
    	model.addAttribute("oprtrId", oprtrId);
    	model.addAttribute("oprtrTel", oprtrTel);
    	return "login/resetPw";
    }
    
    /**
     * @Method Name : resetPw
     * @작성일 : 2023. 8. 22.
     * @작성자 : KY.LEE
     * @Method 설명 : 비밀번호 찾기 이메일 목록 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/operator/email/list.do")
    public String findPwEmailList(
    		@RequestParam(value="oprtrNm") String oprtrNm ,
    		@RequestParam(value="oprtrTel") String oprtrTel ,
    		Model model) throws Exception {
    	MOpOperator mOpOperator = new MOpOperator();
    	mOpOperator.setOprtrNm(oprtrNm);
    	mOpOperator.setOprtrTel(oprtrTel);

    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
    	dtoValidator
			        .addRule("oprtrNm", new ValidateChecker().setPhone().setRequired())
			        .addRule("oprtrTel", new ValidateChecker().setRequired());
    	
    	List<MOpOperator> mOpOperatorList = loginService.findAllMOpOperatorByNmAndTel(mOpOperator);
    	String maskingEmail = "";
    	for(MOpOperator dbMOpOperator : mOpOperatorList) {
    		maskingEmail = dbMOpOperator.getOprtrEmail().replaceAll("(?<=.{3}).(?=.*@)", "*");
    		dbMOpOperator.setOprtrEmail(maskingEmail);
    	}
    	model.addAttribute("mOpOperatorList", mOpOperatorList);
    	model.addAttribute("oprtrNm", oprtrNm);
    	model.addAttribute("oprtrTel", oprtrTel);
    	return "login/findPwEmailList";
    }

    /**
     * @Method Name : operatorDeatilPswd
     * @작성일 : 2023. 8. 22.
     * @작성자 : KY.LEE
     * @Method 설명 : 비밀번호 인증 후 사용자 정보 조회로직
     * @return
     */
    @GetMapping(value ="/operator/deatil/pswd.ajax", produces = "application/json")
    public @ResponseBody CommonResponse<?> operatorDeatilPswd(@RequestParam Map<String,Object> paramMap){
    	Map<String,Object> result = new HashMap<String,Object>();
    	MOpOperator mOpOperator = new MOpOperator();
		String oprtrTel = paramMap.get("oprtrTel") 	!= null ? String.valueOf(paramMap.get("oprtrTel")) 	: "";
		String oprtrNm 	= paramMap.get("oprtrNm") 	!= null ? String.valueOf(paramMap.get("oprtrNm")) 	: "";
		
		mOpOperator.setOprtrNm(oprtrNm);
		mOpOperator.setOprtrTel(oprtrTel);
    	try {
    		List<MOpOperator> dbMOpOperator = loginService.findAllMOpOperatorByNmAndTel(mOpOperator);
    		if(!dbMOpOperator.isEmpty()) {
    			result.put("oprtrNm", oprtrNm);
    			result.put("oprtrTel", oprtrTel);
    		} else {
    			return CommonResponse.ResponseCodeAndMessage(HttpStatus.NOT_FOUND , "가입된 유저가 없습니다.");
    		}
    	} catch (Exception e) {
     		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "유저 정보 조회 중 오류가 발생했습니다.");
    	}
 		return CommonResponse.ResponseSuccess(HttpStatus.OK , "본인인증이 완료 되었습니다. 등록하신 이메일 목록 화면으로 이동 됩니다.", "/operator/email/list.do" , result);
    }
    
    /**
     * @Method Name : operatorUpdateDeatilPswd
     * @작성일 : 2023. 8. 22.
     * @작성자 : KY.LEE
     * @Method 설명 : 비밀번호 인증 후 사용자 정보 조회로직
     * @return
     * @throws Exception 
     */
    @PostMapping(value ="/operator/update/pswd.ajax", produces = "application/json")
    public @ResponseBody CommonResponse<?>  operatorUpdateDeatilPswd(@RequestBody MOpOperator mOpOperator) throws Exception{
 		MOpOperator dbMOpOperator = loginService.findOneMOpOperatorByNmAndTelAndId(mOpOperator);
		try {
			//패스워드 일치여부 확인
			if(mOpOperator.getOprtrPswd().equals(mOpOperator.getOprtrPswdChk())) {
				loginService.updateMOpOperatorPw(dbMOpOperator,mOpOperator.getOprtrPswd());
			} else {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
	  		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "비밀번호 변경에 실패하였습니다. 다시 시도 해주세요.");
		}
    	return CommonResponse.ResponseCodeAndMessageAndSuccessUrl(HttpStatus.OK , "비밀번호가 재설정 되었습니다. 로그인 페이지로 이동됩니다.", "/login.do");
    }
    
    /**
      * @Method Name : operatorSave
      * @작성일 : 2023. 8. 22.
      * @작성자 : 82103
      * @Method 설명 : 관리자 등록
      * @param paramMap
      * @return
      * @throws Exception 
      */
    @PostMapping("/operator/save.ajax")
    public @ResponseBody CommonResponse<?> operatorSave(MOpOperator mOpOperator) throws Exception{
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
    	dtoValidator.addRule("oprtrEmail", new ValidateChecker().setEmail().setRequired().setMaxLength(40, "관리자 이메일은 40자를 넘을 수 없습니다."))
    				.addRule("oprtrNm", new ValidateChecker().setRequired().setMaxLength(50, "사용자 명은 50자를 넘을 수 없습니다."))
    				.addRule("oprtrPswd", new ValidateChecker().setPassword().setRequired().setMaxLength(256, "사용자 비밀번호는 256자를 넘을 수 없습니다."))
    				.addRule("oprtrTel", new ValidateChecker().setRequired());
    	
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		// 유효성 검사
    		userMngService.saveUserInfo(mOpOperator);	
    	}catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "등록 신청이 완료되었습니다.\n관리자의 등록 승인이 완료되면 입력하신\n이메일 주소로 알림 메시지가 전달됩니다.");
    }
    
    /**
     * @Method Name : viewJoinUs
     * @작성일 : 2023. 8. 22.
     * @작성자 : 82103
     * @Method 설명 : 관리자 등록 화면
     * @return
     */
   @GetMapping("/admin/joinUs.do")
   public String viewAdminJoinUs(){
   	
   	return "login/adminJoinUs";
   }
   
    /**
     * @Method Name : operatorSave
     * @작성일 : 2023. 8. 22.
     * @작성자 : 82103
     * @Method 설명 : 관리자 등록
     * @param paramMap
     * @return
     * @throws Exception 
     */
    @PostMapping("/admin/operator/save.ajax")
    public @ResponseBody CommonResponse<?> adminOperatorSave(MOpOperator mOpOperator) throws Exception{
    	try {
    		// 유효성 검사
    		userMngService.saveAdminInfo(mOpOperator);	
    	}catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "등록 신청이 완료되었습니다.\n관리자의 등록 승인이 완료되면 입력하신\n이메일 주소로 알림 메시지가 전달됩니다.");
    }
    
    /**
     * @Method Name : codelistAjax
     * @작성일 : 2023. 9. 07.
     * @작성자 : KC.KIM
     * @Method 설명 : 행정기관 목록 비동기 호출
     * @param : mOpCode
     * @return
     */
    @GetMapping("/code/list.ajax")
    public @ResponseBody CommonResponse<?> codelistAjax(MOpCode mOpCode){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	mOpCode.setSearchType("all");
    	mOpCode.setGrpCdId("MNG_INST_CD");
    	
    	List<MOpCode> codeList = mOpCodeMapper.findAllCodeList(mOpCode);
    	int codeListTotalCnt = mOpCodeMapper.countCodeListByGrpCdIdAndSearchOption(mOpCode);
    	
    	Paging paging = new Paging();
    	paging.setPageNo(mOpCode.getPage());
    	paging.setTotalCount(codeListTotalCnt);
    	resultMap.put("list", codeList);
    	resultMap.put("paging", paging);
    	resultMap.put("totCnt", codeListTotalCnt);
    	
    	return CommonResponse.successToData(resultMap, "");
    }
}

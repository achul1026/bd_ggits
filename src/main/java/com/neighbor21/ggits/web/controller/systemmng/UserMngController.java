package com.neighbor21.ggits.web.controller.systemmng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MOpAuthority;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.PasswordUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.systemmng.UserMngService;

@Controller
@RequestMapping("/system")
public class UserMngController {
	
	@Autowired
	UserMngService userMngService;
	
	@Autowired
	MOpOperatorMapper mOpOperatorMapper;
	
	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;
	
    /**
      * @Method Name : userList
      * @작성일 : 2023. 8. 22.
      * @작성자 : NK.KIM
      * @Method 설명 : 시스템관리 > 사용자 관리
      * @return
      */
	@GetMapping("/user/list.do")
    public String userList(Model model,MOpOperator mOpOperator){		
		model.addAttribute("strDt", mOpOperator.getStrDt());
		model.addAttribute("endDt", mOpOperator.getEndDt());
		
		mOpOperator.setStrDt(GgitsCommonUtils.removeHyphen(mOpOperator.getStrDt()));
		mOpOperator.setEndDt(GgitsCommonUtils.removeHyphen(mOpOperator.getEndDt()));

		List<MOpOperator> userList = mOpOperatorMapper.findAllUserList(mOpOperator);
		int totalCnt = mOpOperatorMapper.countAll(mOpOperator);
		
		Paging paging = new Paging();
		paging.setPageNo(mOpOperator.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchContent", mOpOperator.getSearchContent());
    	model.addAttribute("searchType", mOpOperator.getSearchType());
    	model.addAttribute("oprtrSttsCd", mOpOperator.getOprtrSttsCd());
    	
        return "view/systemmng/userList";
    }
	
	/**
	 * @Method Name : userList
	 * @작성일 : 2023. 8. 22.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 사용자 목록 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/user/list.ajax")
    public @ResponseBody CommonResponse<?> userListAjax(MOpOperator mOpOperator){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
    	List<MOpOperator> oprtrList = mOpOperatorMapper.findAllUserList(mOpOperator);
    	int totalCnt = mOpOperatorMapper.countAll(mOpOperator);
		
		Paging paging = new Paging();
    	paging.setTotalCount(totalCnt);
    	paging.setPageNo(mOpOperator.getPage());
    	resultMap.put("list", oprtrList);
    	resultMap.put("paging", paging);
    	
		return CommonResponse.successToData(resultMap,"");
    }
	
	/**
	 * @Method Name : userSave
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 관리 > 사용자 등록
	 * @return
	 */
	@GetMapping("/user/save.do")
	public String userSave(){
		
		return "view/systemmng/userSave";
	}
	
	/**
     * @Method Name : operatorSave
     * @작성일 : 2023. 8. 22.
     * @작성자 : 82103
     * @Method 설명 : 관리자 등록
     * @param paramMap
     * @return
     */
   @PostMapping("/user/save.ajax")
   public @ResponseBody CommonResponse<?> operatorSave(MOpOperator mOpOperator){
	   ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
	   dtoValidator.addRule("oprtrEmail", new ValidateChecker().setEmail().setRequired().setMaxLength(40, "관리자 이메일은 40자를 넘을 수 없습니다."))
		   		   .addRule("oprtrNm", new ValidateChecker().setRequired().setMaxLength(50, "관리자 명은 50자를 넘을 수 없습니다."))
		   		   .addRule("oprtrPswd", new ValidateChecker().setRequired().setPassword().setMaxLength(256, "관리자 비밀번호는 256자를 넘을 수 없습니다."))
	   			   .addRule("addngCd", new ValidateChecker().setRequired())
	   			   .addRule("grpNm", new ValidateChecker().setRequired());
	   
	   ValidateResult dtoValidatorResult = dtoValidator.isValid();
	   
	   if(!dtoValidatorResult.isSuccess()) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
	   }
	
   	try {
   		userMngService.saveUserInfo(mOpOperator);
   	}catch (CommonException e) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
   	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "사용자 등록이 되었습니다.");
   }
	/**
	 * @Method Name : userDetail
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 관리 > 사용자 상세
	 * @return
	 */
	@GetMapping("/user/{oprtrId}/detail.do")
	public String userDetail(@PathVariable long oprtrId, Model model){
		
		MOpOperator userDetail = userMngService.findOneUserDetailByOprtrId(oprtrId);
		userDetail.setOprtrTel(GgitsCommonUtils.phoneNumAddHyphen(userDetail.getOprtrTel()));
		model.addAttribute("userDetail", userDetail);
		
		return "view/systemmng/userDetail";
	}
	
	/**
	 * @Method Name : userUpdate
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 정보 수정 
	 * @return
	 */
	@PostMapping("/user/update.ajax")
    public @ResponseBody CommonResponse<?> userUpdate(@RequestBody MOpOperator mOpOperator){
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
		
		dtoValidator.addRule("oprtrEmail", new ValidateChecker().setEmail().setRequired().setMaxLength(40, "관리자 이메일은 40자를 넘을 수 없습니다."))
					.addRule("oprtrNm", new ValidateChecker().setRequired().setMaxLength(50, "관리자 명은 50자를 넘을 수 없습니다."))
			   		.addRule("addngCd", new ValidateChecker().setRequired())
			   		.addRule("grpNm", new ValidateChecker().setRequired());
		
	   ValidateResult dtoValidatorResult = dtoValidator.isValid();
	   
	   if(!dtoValidatorResult.isSuccess()) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
	   }
		
		try {
			userMngService.updateUserInfo(mOpOperator);
		}catch (CommonException e) {
	 	    return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "유저 정보가 수정되었습니다.");
    }
	
	/**
	 * @Method Name : userDelete
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 정보 삭제 
	 * @return
	 */
	@GetMapping("/user/{oprtrId}/delete.ajax")
	public @ResponseBody CommonResponse<?> userDelete(@PathVariable(value = "oprtrId", required = true) long oprtrId){
		
		MOpOperator mOpOperator = new MOpOperator();
		mOpOperator.setOprtrId(oprtrId);
		
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
    	dtoValidator
    		.addRule("oprtrId", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}

		
		try {
			mOpOperator.setOprtrId(oprtrId);
			userMngService.deleteUserInfo(mOpOperator);
		}catch (CommonException e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "유저 정보가 삭제되었습니다.");
	}
	
	/**
	 * @Method Name : userPswdCheck
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 사용자 비밀번호 확인 
	 * @return
	 */
	@PostMapping("/user/pswd/update.ajax")
	public @ResponseBody CommonResponse<?> userPswdUpdate(
															@RequestParam(value = "oprtrId", required = true) long oprtrId,
															@RequestParam(value = "oprtrPswd", required = true) String oprtrPswd
														){
		MOpOperator mOpOperator = new MOpOperator();
		mOpOperator.setOprtrPswd(oprtrPswd);
		
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpOperator);
		dtoValidator.addRule("oprtrPswd", new ValidateChecker().setPassword().setRequired());
		ValidateResult dtoValidatorResult = dtoValidator.isValid();
	   if(!dtoValidatorResult.isSuccess()) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
	   }
		
	   try {
		   mOpOperator.setOprtrId(oprtrId);
		   mOpOperator.setOprtrPswd(oprtrPswd);
		   userMngService.updateUserPswd(mOpOperator);
	   }catch (CommonException e) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "비밀번호가 변경 되었습니다.");
	}
	
	/**
	 * @Method Name : userDelete
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 정보 삭제 
	 * @return
	 */
	@PostMapping("/user/pswd/check.ajax")
	public @ResponseBody CommonResponse<?> userPswdCheck(
			@RequestParam(value = "oprtrPswd", required = true) String oprtrPswd,
			@RequestParam(value = "oprtrEmail", required = true) String oprtrEmail
			){
		
		MOpOperator mOpOperator = new MOpOperator();
		mOpOperator.setOprtrEmail(oprtrEmail);
		
		MOpOperator mOpOperatorInfo = mOpOperatorMapper.findOneMOpOperatorByEmail(mOpOperator);
		
		if(mOpOperatorInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"유저 정보가 존재하지 않습니다.");
		}
		
		if(!PasswordUtils.checkPassword(oprtrPswd, mOpOperatorInfo.getOprtrPswd())) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "기존 비밀번호가 일치하지 않습니다.");
		}
		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "기존 비밀번호 확인 되셨습니다. 새 비밀번호를 입력해주세요.");
	}
	
	/**
     * @Method Name : groupList
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 사용자 관리 > 사용자 그룹관리
     * @return
     */
	@GetMapping("/user/group/list.do")
	public String groupList(MOpGrpInfo mOpGrpInfo,Model model){
		model.addAttribute("searchContent", mOpGrpInfo.getSearchContent());
		model.addAttribute("strDt", mOpGrpInfo.getStrDt());
		model.addAttribute("endDt", mOpGrpInfo.getEndDt());

		if(!GgitsCommonUtils.isNull(mOpGrpInfo.getStrDt())) {
			mOpGrpInfo.setStrDt(mOpGrpInfo.getStrDt() + " 00:00:00");
		}
		if(!GgitsCommonUtils.isNull(mOpGrpInfo.getEndDt())) {
			mOpGrpInfo.setEndDt(mOpGrpInfo.getEndDt() + " 23:59:59");
		}
		List<MOpGrpInfo> groupList = mOpGrpInfoMapper.findAllGroupList(mOpGrpInfo);
		int grpListTotalCnt = mOpGrpInfoMapper.countAll(mOpGrpInfo);
		model.addAttribute("groupList",groupList);
		
		Paging paging = new Paging();
		paging.setPageNo(mOpGrpInfo.getPage());
    	paging.setTotalCount(grpListTotalCnt);
    	
    	model.addAttribute("paging", paging);
		
		return "view/systemmng/groupList";
    
	}
	
	/**
	 * @Method Name : groupList
	 * @작성일 : 2023. 8. 22.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 목록 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/user/group/list.ajax")
    public @ResponseBody CommonResponse<?> groupListAjax(MOpGrpInfo mOpGrpInfo){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		List<MOpGrpInfo> groupList = mOpGrpInfoMapper.findAllGroupList(mOpGrpInfo);
		int grpListTotalCnt = mOpGrpInfoMapper.countAll(mOpGrpInfo);
		
		Paging paging = new Paging();
    	paging.setTotalCount(grpListTotalCnt);
    	paging.setPageNo(mOpGrpInfo.getPage());
    	resultMap.put("list", groupList);
    	resultMap.put("paging", paging);
    	
		return CommonResponse.successToData(resultMap,"");
    }
	
	/**
	 * @Method Name : groupSave
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 그룹 관리 > 그룹 등록
	 * @return
	 */
	@GetMapping("/user/group/save.do")
	public String groupSave(Model model){
		
		//전체 권한 조회
		MOpAuthority mOpAuthority = new MOpAuthority();
		List<MOpAuthority> authList = mOpAuthorityMapper.findAllAuthList(mOpAuthority);
		model.addAttribute("authList", authList);
		
		//기본 권한 그룹 존재여부 체크
		boolean defaultChkVal = false;
		Map<String,Object> defaultGrpInfoCheck = mOpGrpInfoMapper.findOneDefaultGrpCheck();
		if(defaultGrpInfoCheck != null) {
			String upperGrpChk 		= defaultGrpInfoCheck.get("upperGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("upperGrpChk") 	: "FALSE";
			String generalGrpChk 	= defaultGrpInfoCheck.get("generalGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("generalGrpChk") 	: "FALSE";
			if("TRUE".equals(upperGrpChk) || "TRUE".equals(generalGrpChk)) {
				defaultChkVal = true;
			}
		}
		model.addAttribute("defaultChkVal", defaultChkVal);
				
		return "view/systemmng/groupSave";
	}
	
	/**
	 * @Method Name : userDetail
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시스템관리 > 사용자 관리 > 사용자 등록
	 * @return
	 */
	@GetMapping("/user/group/{grpId}/detail.do")
	public String groupDetail(@PathVariable String grpId, Model model,MOpOperator mOpOperator){
		
		//그룹 상세 조회
		MOpGrpInfo groupDetail = userMngService.findOneGroupDetailByGrpId(grpId);
		
		if(groupDetail != null) {
			//그룹 유저 목록 조회
			mOpOperator.setPage(0);
			List<MOpOperator> groupUserList = mOpOperatorMapper.findAllUserList(mOpOperator);
			model.addAttribute("groupUserList", groupUserList);
			
			//전체 권한 조회
			MOpAuthority mOpAuthority = new MOpAuthority();
			List<MOpAuthority> authList = mOpAuthorityMapper.findAllAuthList(mOpAuthority);
			model.addAttribute("authList", authList);
			
		}
		
		//기본 권한 그룹 존재여부 체크
		boolean defaultChkVal = false;
		Map<String,Object> defaultGrpInfoCheck = mOpGrpInfoMapper.findOneDefaultGrpCheck();
		if(defaultGrpInfoCheck != null) {
			String upperGrpChk 		= defaultGrpInfoCheck.get("upperGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("upperGrpChk") 	: "FALSE";
			String generalGrpChk 	= defaultGrpInfoCheck.get("generalGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("generalGrpChk") 	: "FALSE";
			if("TRUE".equals(upperGrpChk) || "TRUE".equals(generalGrpChk)) {
				defaultChkVal = true;
			}
		}
		model.addAttribute("defaultChkVal", defaultChkVal);
		
		model.addAttribute("groupDetail", groupDetail);
		
		return "view/systemmng/groupDetail";
	}
	
	/**
	  * @Method Name : groupUpdate
	  * @작성일 : 2023. 8. 31.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 정보 수정
	  * @param grpId
	  * @param grpNm
	  * @param grpDescr
	  * @param authId
	  * @param oprtrIdArr
	  * @return
	  */
	@PostMapping("/user/group/update.ajax")
	public @ResponseBody CommonResponse<?> groupUpdate(	
														@RequestParam(value = "grpId", required = true) 	String grpId,
														@RequestParam(value = "grpNm") 						String grpNm,
														@RequestParam(value = "grpDescr") 					String grpDescr,
														@RequestParam(value = "authId", required = true)	long   authId,
														@RequestParam(value = "oprtrIdArr")					String strOprtrIdArr
													  ){
		
		try {
			MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
			mOpGrpInfo.setGrpId(grpId);
			mOpGrpInfo.setGrpNm(grpNm);
			mOpGrpInfo.setGrpDescr(grpDescr);
			mOpGrpInfo.setAuthId(authId);
			
			ValidateBuilder dtoValidator = new ValidateBuilder(mOpGrpInfo);

			dtoValidator.addRule("grpNm", new ValidateChecker().setRequired().setMaxLength(50, "그룹명은 50자를 넘을 수 없습니다."))
				   		.addRule("authId", new ValidateChecker().setRequired())
				   		.addRule("grpId", new ValidateChecker().setRequired());
			
			ValidateResult dtoValidatorResult = dtoValidator.isValid();
			   
		    if(!dtoValidatorResult.isSuccess()) {
			   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
		    }
			  
    		userMngService.updateGrpInfo(mOpGrpInfo,strOprtrIdArr);
    	}catch (CommonException e) {
     	    return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "그룹 정보가 수정되었습니다.");
	}
	
	/**
	 * @Method Name : groupSave
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 정보 등록
	 * @param grpNm
	 * @param grpDescr
	 * @param authId
	 * @param oprtrIdArr
	 * @return
	 */
	@PostMapping("/user/group/save.ajax")
	public @ResponseBody CommonResponse<?> groupSave(	
			@RequestParam(value = "grpNm") 									String grpNm,
			@RequestParam(value = "grpDescr") 								String grpDescr,
			@RequestParam(value = "bscGrpYn", required = false) 			String bscGrpYn,
			@RequestParam(value = "upperOprtrAuthGrpYn", required = false) 	String upperOprtrAuthGrpYn,
			@RequestParam(value = "authId", required = true)				long   authId,
			@RequestParam(value = "oprtrIdArr")								String strOprtrIdArr
			){
		try {
			MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
			mOpGrpInfo.setGrpNm(grpNm);
			mOpGrpInfo.setGrpDescr(grpDescr);
			mOpGrpInfo.setAuthId(authId);
			mOpGrpInfo.setBscGrpYn(bscGrpYn);
			mOpGrpInfo.setUpperOprtrAuthGrpYn(upperOprtrAuthGrpYn);
			
			ValidateBuilder dtoValidator = new ValidateBuilder(mOpGrpInfo);
			
			dtoValidator.addRule("grpNm", new ValidateChecker().setRequired().setMaxLength(50, "그룹명은 50자를 넘을 수 없습니다."))
				   		.addRule("authId", new ValidateChecker().setRequired());
			
		   ValidateResult dtoValidatorResult = dtoValidator.isValid();
		   
		   if(!dtoValidatorResult.isSuccess()) {
			   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
		   }
			
			userMngService.saveGrpInfo(mOpGrpInfo,strOprtrIdArr);
		}catch (CommonException e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "그룹 정보가 등록되었습니다.");
	}
	
	/**
	 * @Method Name : groupDelete
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 정보 삭제
	 * @param grpId
	 * @return
	 */
	@GetMapping("/user/group/{grpId}/delete.ajax")
	public @ResponseBody CommonResponse<?> groupDelete(	
			@PathVariable(value = "grpId", required = true) 	String grpId,
			@RequestParam(value = "oprtrIdArr")					String strOprtrIdArr
			){
		
		MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
		mOpGrpInfo.setGrpId(grpId);
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpGrpInfo);
    	dtoValidator
    		.addRule("grpId", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
		try {
			userMngService.deleteGrpInfo(mOpGrpInfo,strOprtrIdArr);
		}catch (CommonException e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , e.getMessage());
		}
		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "그룹 정보가 삭제되었습니다.");
	}
}

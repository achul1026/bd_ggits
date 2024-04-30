package com.neighbor21.ggits.web.controller.systemmng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpCodeGrp;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpCodeGrpMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.systemmng.CodeMngService;

@Controller
@RequestMapping("/system")
public class CodeMngController {
	
	@Autowired
	CodeMngService codeMngService;
	
	@Autowired
	MOpCodeGrpMapper mOpCodeGrpMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
    /**
     * @Method Name : codeGrpList
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 코드 관리
     * @param : page
     * @param : searchType
     * @param : searchContent
     * @param : useYn
     * @return
     */
    @GetMapping("/codegrp/list.do")
    public String codeGrpList(Model model, MOpCodeGrp mOpCodeGrp){
    	
    	int totalCnt = mOpCodeGrpMapper.countCodeGrpList(mOpCodeGrp);
    	List<MOpCodeGrp> codeGrpList = codeMngService.findAllCodeGrpList(mOpCodeGrp);
    	
    	Paging paging = new Paging();
    	paging.setPageNo(mOpCodeGrp.getPage());
    	paging.setTotalCount(totalCnt);
    	 
    	model.addAttribute("paging", paging);
    	model.addAttribute("grpCdList", codeGrpList);
    	model.addAttribute("searchType", mOpCodeGrp.getSearchType());
    	model.addAttribute("searchContent", mOpCodeGrp.getSearchContent());
    	model.addAttribute("useYn", mOpCodeGrp.getUseYn());
    	
    	return "view/systemmng/codeMng";
    }
    
    /**
     * @Method Name : codeGrpSave
     * @작성일 : 2023. 8. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 코드 등록 화면
     * @param 
     * @return
     */
    @GetMapping("/codegrp/save.do")
    public String codeGrpSave() {
    	return "view/systemmng/codeGroupRegister";
    }
    
    /**
     * @Method Name : codeGrpSave
     * @작성일 : 2023. 8. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 그룹 코드 등록
     * @param : grpCdId
     * @param : grpCdNm
     * @param : descr
     * @param : useyn
     * @return
     */
    @PostMapping("/codegrp/save.ajax")
    public @ResponseBody CommonResponse<?> codeGrpSave(@RequestBody MOpCodeGrp mOpCodeGrp
    		, HttpSession session) {
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpCodeGrp);
    	dtoValidator
    		.addRule("grpCdId", new ValidateChecker().setRequired().setMaxLength(20, "그룹 코드 아이디는 20자를 넘을 수 없습니다."))
    		.addRule("grpCdNm", new ValidateChecker().setRequired().setMaxLength(50, "그룹 코드명은 50자를 넘을 수 없습니다."))
    		.addRule("useYn", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}

		MOpOperator mOpOperator = (MOpOperator) session.getAttribute("mOpOperatorInfo");
		int mOpCodeGrpCnt = 0;
		// 중복 확인
		mOpCodeGrpCnt = mOpCodeGrpMapper.countCodeGrpByCdId(mOpCodeGrp.getGrpCdId());
		
		if(mOpCodeGrpCnt == 0) {
			mOpCodeGrp.setCrtusrId(String.valueOf(mOpOperator.getOprtrId()));
			mOpCodeGrp.setUptusrId(String.valueOf(mOpOperator.getOprtrId()));
			mOpCodeGrpMapper.save(mOpCodeGrp);
		}else {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "중복된 그룹코드가 존재 합니다.");
		}
		
 		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "그룹 코드가 등록되었습니다.");
    }
    
    /**
     * @Method Name : codeGrpDetail
     * @작성일 : 2023. 8. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 상세
     * @param : grpCdId
     * @param : page
     * @return
     */
    @GetMapping("/codegrp/{grpCdId}/detail.do")
    public String codeGrpDetail(@PathVariable String grpCdId
    		, MOpCode mOpCode
    		, Model model) {
    	
    	MOpCodeGrp mopcodeGrp = new MOpCodeGrp();
		List<MOpCode> codeList = null;
		
		// validation check
    	if(!GgitsCommonUtils.isNull(grpCdId)) {
    		// 그룹코드 정보 조회
    		mopcodeGrp = mOpCodeGrpMapper.findOneCodeGrp(grpCdId);
    		
    		// 관리 코드 리스트 조회
    		mOpCode.setGrpCdId(grpCdId);
    		
        	int totalCnt = mOpCodeMapper.countCodeListByGrpCdIdAndSearchOption(mOpCode);
        	codeList = codeMngService.findAllCodeList(mOpCode);
        	
        	Paging paging = new Paging();
        	paging.setPageNo(mOpCode.getPage());
        	paging.setTotalCount(totalCnt);
        	
        	model.addAttribute("paging", paging);
    	}
    	model.addAttribute("grpCodeInfo", mopcodeGrp);
    	model.addAttribute("codeList", codeList);
    	model.addAttribute("searchType", mOpCode.getSearchType());
    	model.addAttribute("searchContent", mOpCode.getSearchContent());
    	model.addAttribute("useYn", mOpCode.getUseYn());
    	
    	return "view/systemmng/codeGroupInfo";
    }
    
    /**
     * @Method Name : codeGrpUpdate
     * @작성일 : 2023. 8. 28.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 수정
     * @param : grpCdId
     * @param : grpCdNm
     * @param : descr
     * @param : useYn
     * @return
     */
    @PostMapping("/codegrp/update.ajax")
    public @ResponseBody CommonResponse<?> codeGrpUpdate(MOpCodeGrp mOpCodeGrp, HttpSession session){
		// validation check
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpCodeGrp);
		dtoValidator
					.addRule("grpCdId", new ValidateChecker().setRequired().setMaxLength(20, "그룹 코드 아이디는 20자를 넘을 수 없습니다."))
					.addRule("grpCdNm", new ValidateChecker().setRequired().setMaxLength(50, "그룹 코드명은 50자를 넘을 수 없습니다."))
					.addRule("useYn", new ValidateChecker().setRequired());
		
		ValidateResult dtoValidatorResult = dtoValidator.isValid();
		
		if(!dtoValidatorResult.isSuccess()) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
		}
    	
		MOpCodeGrp dbCodeGrp = mOpCodeGrpMapper.findOneCodeGrp(mOpCodeGrp.getGrpCdId());
		
		if(dbCodeGrp == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"그룹 코드 정보가 존재하지 않습니다.");
		}
		
		MOpOperator mOpOperator =  (MOpOperator) session.getAttribute("mOpOperatorInfo");
		
		dbCodeGrp.setGrpCdId(mOpCodeGrp.getGrpCdId());
		dbCodeGrp.setGrpCdNm(mOpCodeGrp.getGrpCdNm());
		dbCodeGrp.setDescr(mOpCodeGrp.getDescr());
		dbCodeGrp.setUseYn(mOpCodeGrp.getUseYn());
		dbCodeGrp.setUptusrId(String.valueOf(mOpOperator.getOprtrId()));
		mOpCodeGrpMapper.updateCodeGrp(dbCodeGrp);
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "그룹 코드가 수정되었습니다.");
    }
    
    /**
     * @Method Name : codeGrpDelete
     * @작성일 : 2023. 8. 28.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 삭제
     * @param : grpCdId
     * @return
     */
    @GetMapping("/codegrp/{grpCdId}/delete.do")
    public @ResponseBody CommonResponse<?>  codeGrpDelete(@PathVariable String grpCdId){
    	
    	MOpCode mOpCode = new MOpCode();
    	mOpCode.setGrpCdId(grpCdId);
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpCode);
    	dtoValidator
    		.addRule("grpCdId", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
		List<MOpCode> dbOpCodeList = mOpCodeMapper.findAllCodeList(mOpCode);
		
		if(dbOpCodeList.size() > 0) {
			for(MOpCode dbOpCode : dbOpCodeList) {
				mOpCodeMapper.deleteCodeByCdId(dbOpCode.getCdId());
			}
		}
		
		MOpCodeGrp dbOpCodeGrp = mOpCodeGrpMapper.findOneCodeGrp(grpCdId);
		if(!dbOpCodeGrp.equals(null)) {
			mOpCodeGrpMapper.deleteCodeGrpByGrpCdId(dbOpCodeGrp.getGrpCdId());
		}
    
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "그룹 코드를 삭제하였습니다.");
    }
    
    /**
     * @Method Name : codeSave
     * @작성일 : 2023. 8. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 관리 코드 등록
     * @param : grpCdId
     * @param : cdId
     * @param : cdNm
     * @param : descr
     * @param : useYn
     * @param : sortNo
     * @return
     */
    @PostMapping("/codegrp/code/save.ajax")
    public @ResponseBody CommonResponse<?> codeSave(@RequestBody MOpCode mOpCode
    		, HttpSession session) {
    	
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpCode);
  	   	dtoValidator.addRule("grpCdId", new ValidateChecker().setRequired().setMaxLength(20, "그룹 코드 아이디는 20자를 넘을 수 없습니다."))
  	   				.addRule("cdId", new ValidateChecker().setRequired().setMaxLength(20, "코드 아이디는 20자를 넘을 수 없습니다."))
  	   				.addRule("cdNm", new ValidateChecker().setRequired().setMaxLength(50, "코드명은 50자를 넘을 수 없습니다."))
  	   				.addRule("sortNo", new ValidateChecker().setRequired());
  	   
  	   ValidateResult dtoValidatorResult = dtoValidator.isValid();
  	   
  	   if(!dtoValidatorResult.isSuccess()) {
  		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, dtoValidatorResult.getMessage());
  	   }
    	
		MOpOperator mOpOperator =  (MOpOperator) session.getAttribute("mOpOperatorInfo");
		int mOpCodeCnt = 0;
		
		mOpCode.setCrtusrId(String.valueOf(mOpOperator.getOprtrId()));
		mOpCode.setUptusrId(String.valueOf(mOpOperator.getOprtrId()));
		mOpCodeCnt = mOpCodeMapper.countCodeBygrpCdIdAndCdId(mOpCode);
		
		if(mOpCodeCnt == 0) {
			mOpCodeMapper.saveCode(mOpCode);
		}else {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "중복된 관리 코드가 존재 합니다.");
		}
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "관리 코드를 등록하였습니다.");
    }
    
    /**
     * @Method Name : codeUpdate
     * @작성일 : 2023. 8. 30.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 관리 코드 수정
     * @param : grpCdId
     * @param : cdId
     * @param : cdNm
     * @param : descr
     * @param : useYn
     * @param : sortNo
     * @return
     */
    @PostMapping("/codegrp/code/update.ajax")
    public @ResponseBody CommonResponse<?> codeUpdate(MOpCode mOpCode, HttpSession session){
    	
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpCode);
    	dtoValidator
    		.addRule("grpCdId", new ValidateChecker().setRequired().setMaxLength(20, "그룹 코드 아이디는 20자를 넘을 수 없습니다."))
    		.addRule("cdId", new ValidateChecker().setRequired().setMaxLength(20, "코드 아이디는 20자를 넘을 수 없습니다."))
    		.addRule("cdNm", new ValidateChecker().setRequired().setMaxLength(50, "코드명은 50자를 넘을 수 없습니다."))
    		.addRule("sortNo", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	MOpCode dbOpCode = mOpCodeMapper.findOneMOpCodeByCdId(mOpCode);
    	
    	if(dbOpCode == null) {
    		throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"관리 코드 정보가 존재하지 않습니다.");
    	}
    	
		MOpOperator mOpOperator =  (MOpOperator) session.getAttribute("mOpOperatorInfo");
		
		mOpCode.setUptusrId(String.valueOf(mOpOperator.getOprtrId()));
		int codeDuplChk = mOpCodeMapper.countMOpCodeByCdId(mOpCode.getCdId());
		
		if(codeDuplChk > 0) {
			mOpCodeMapper.updateCode(mOpCode);
		}else {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "관리 코드를 찾을 수 없습니다.");
		}
    		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "관리 코드를 수정하였습니다.");
    }
    
    /**
     * @Method Name : codeDelete
     * @작성일 : 2023. 8. 30.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 관리 코드 삭제
     * @param : cdId
     * @return
     */
    @GetMapping("/codegrp/code/{cdId}/delete.ajax")
    public @ResponseBody CommonResponse<?> codeDelete(@PathVariable("cdId") String cdId){
    	ValidateBuilder dtoValidator = new ValidateBuilder(new MOpCode(cdId));
    	dtoValidator.addRule("cdId", new ValidateChecker().setRequired());
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}

		int countCodeChk = mOpCodeMapper.countMOpCodeByCdId(cdId);
		
		if(countCodeChk > 0) {
			mOpCodeMapper.deleteCodeByCdId(cdId);
		}else {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.NOT_FOUND ,"삭제 할 관리 코드 정보를 찾지 못했습니다.");
		}
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "관리 코드를 삭제하였습니다.");
    }
    
    /**
     * @Method Name : codeDetail
     * @작성일 : 2023. 9. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 > 관리 코드 상세
     * @param : cdId
     * @return
     */
    @GetMapping("/codegrp/code/{cdId}/detail.do")
    public String codeDetail(@PathVariable String cdId
    		, Model model) {
    	
    	if(!GgitsCommonUtils.isNull(cdId)) {
    		MOpCode mOpCode = new MOpCode();
    		MOpCode dbMOpCode = mOpCodeMapper.findOneMOpCodeByCdId(mOpCode);
    		model.addAttribute("codeDetail", dbMOpCode);
    	}
    	
    	return "view/systemmng/codeGroupInfo"; 
    }
    
    /**
     * @Method Name : codelistAjax
     * @작성일 : 2023. 9. 07.
     * @작성자 : KC.KIM
     * @Method 설명 : 행정기관 목록 비동기 호출
     * @param : mOpCode
     * @return
     */
    @GetMapping("/codegrp/code/list.ajax")
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

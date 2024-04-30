package com.neighbor21.ggits.web.controller.systemmng;

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
import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;
import com.neighbor21.ggits.common.entity.MetaColInfo;
import com.neighbor21.ggits.common.entity.OpenApiPvsnLog;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpOpenApiInfoMapper;
import com.neighbor21.ggits.common.mapper.OpenApiPvsnLogMapper;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.web.service.systemmng.OpenApiMngService;

@Controller
@RequestMapping("/system")
public class OpenApiMngController {
	
	@Autowired
	OpenApiMngService openApiMngService;
	
	@Autowired
	OpenApiPvsnLogMapper openApiPvsnLogMapper;
	
	@Autowired
	MOpOpenApiInfoMapper mOpOpenApiInfoMapper;

    /**
     * @Method Name : viewOpenApiMng
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 오픈 API 관리
     * @return
     */
    @GetMapping("/openapi/list.do")
    public String viewOpenApiMng(Model model,MOpOpenApiInfo mOpOpenApiInfo){

		int totalCount = mOpOpenApiInfoMapper.countAllBySearchOption(mOpOpenApiInfo);

		Paging paging = new Paging();
    	paging.setPageNo(mOpOpenApiInfo.getPage());
    	paging.setTotalCount(totalCount);

    	model.addAttribute("paging", paging);
    	model.addAttribute("page", mOpOpenApiInfo.getPage());
    	model.addAttribute("searchOption", mOpOpenApiInfo);
    	model.addAttribute("openApiList", openApiMngService.getOpenApiInfoList(mOpOpenApiInfo));
    	return "view/systemmng/openApiMng";
    }
    
    /**
     * @Method Name : viewOpenApiMngRegist
     * @작성일 : 2023. 10. 27.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 오픈 API 관리
     * @return
     */
    @GetMapping("/openapi/save.do")
    public String viewOpenApiMngSave(Model model){
    	return "view/systemmng/openApiSave";
    }
    
    @PostMapping("/openapi/save.ajax")
    public @ResponseBody CommonResponse<?> saveMOpOpenApiInfo(@RequestBody MOpOpenApiInfo mOpOpenApiInfo){
        // validation check
        ValidateBuilder dtoValidator = new ValidateBuilder(mOpOpenApiInfo);
        ValidateResult dtoValidatorResult = dtoValidator
                    .addRule("apiNm", new ValidateChecker().setRequired())
                    .addRule("apiCallUrl", new ValidateChecker().setRequired())
                    .addRule("contents", new ValidateChecker().setRequired())
                    .addRule("descr", new ValidateChecker().setRequired()).isValid();
        
        if(!dtoValidatorResult.isSuccess()) {
            return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
        }
        
        try {
            openApiMngService.saveMOpOpenApiInfo(mOpOpenApiInfo);
        } catch (CommonException e) {
            return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "Open API 등록 중 오류가 발생했습니다.");
        }
        return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "Open API 등록 성공");
    }

    @PostMapping("/openapi/update.ajax")
    public @ResponseBody CommonResponse<?> updateOpenApiPvsnLog(@RequestBody MOpOpenApiInfo mOpOpenApiInfo){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpOpenApiInfo);
    	ValidateResult dtoValidatorResult = dtoValidator
					.addRule("apiNm", new ValidateChecker().setRequired())
					.addRule("apiCallUrl", new ValidateChecker().setRequired())
					.addRule("contents", new ValidateChecker().setRequired())
					.addRule("descr", new ValidateChecker().setRequired()).isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		openApiMngService.updateMOpOpenApiInfo(mOpOpenApiInfo);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "Open API 수정 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "Open API 수정 성공");
    }
    
    /**
     * @Method Name : viewOpenApiDetail
     * @작성일 : 2023. 10. 19.
     * @작성자 : KY.LEE
     * @Method 설명 : 시스템관리 > 오픈 API 관리 > 오픈API 상세
     * @return
     */
    @GetMapping("/openapi/{dsetId}/detail.do")
    public String viewOpenApiDetail(Model model,@PathVariable String dsetId){
        MOpOpenApiInfo mOpOpenApiInfo = openApiMngService.getOpenApiInfoDetail(dsetId);
    	boolean isUserChk = false;
    	if(mOpOpenApiInfo.getOprtrId() == LoginSessionUtils.getOprtrId()) {
    		isUserChk = true;
    	}
    	model.addAttribute("isUserChk", isUserChk);
    	model.addAttribute("openApiDetailInfo", mOpOpenApiInfo);
    	return "view/systemmng/openApiDetail";
    }
    
    /**
	 * @Method Name : deleteMetaColInfo
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 데이터 유형 삭제
	 * @return
	 */
    @PostMapping("/openapi/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMetaColInfo(@RequestBody MOpOpenApiInfo mOpOpenApiInfo){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpOpenApiInfo);
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("dsetId", new ValidateChecker().setRequired()).isValid();
    
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	try {
    		openApiMngService.deleteMOpOpenApiInfo(mOpOpenApiInfo);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "OPEN API삭제 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "OPEN API를 삭제 되었습니다.");
    }

}

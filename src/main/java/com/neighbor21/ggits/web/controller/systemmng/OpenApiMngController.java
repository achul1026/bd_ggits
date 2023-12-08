package com.neighbor21.ggits.web.controller.systemmng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;
import com.neighbor21.ggits.web.service.systemmng.OpenApiMngService;

@Controller
@RequestMapping("/system")
public class OpenApiMngController {
	
	@Autowired
	OpenApiMngService openApiMngService;

    /**
     * @Method Name : viewOpenApiMng
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 오픈 API 관리
     * @return
     */
    @GetMapping("/openapi/list.do")
    public String viewOpenApiMng(Model model,MOpOpenApiInfo mOpOpenApiInfo){
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
    /**
     * @Method Name : viewOpenApiDetail
     * @작성일 : 2023. 10. 19.
     * @작성자 : KY.LEE
     * @Method 설명 : 시스템관리 > 오픈 API 관리 > 오픈API 상세
     * @return
     */
    @GetMapping("/openapi/{apiId}/detail.do")
    public String viewOpenApiDetail(Model model,@PathVariable String apiId){
    	model.addAttribute("openApiDetailInfo", openApiMngService.getOpenApiInfoDetail(apiId));
    	return "view/systemmng/openApiDetail";
    }
}

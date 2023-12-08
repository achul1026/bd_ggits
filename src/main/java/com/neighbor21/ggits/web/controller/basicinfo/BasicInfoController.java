package com.neighbor21.ggits.web.controller.basicinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.neighbor21.ggits.common.entity.MGmStdLinkNodeMngInfo;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MGmStdLinkNodeMngInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;

@Controller
@RequestMapping("/basicinfo")
public class BasicInfoController {
	
	@Autowired
	MGmStdLinkNodeMngInfoMapper mGmStdLinkNodeMngInfoMapper;
	
	/**
     * @Method Name : viewDashboard
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 기초 정보 조회 대시보드 화면
     * @return
     */
   @GetMapping("/dashboard.do")
   public String viewDashboard(){
   	
       return "view/basicinfo/dashboard";
   }
   
   /**
    * 노드/링크 현황보기 ViewPage
    * @param type the type
    * @return the bigdata sub page
    */
   @GetMapping("/nodelink/current/list.do")
   public String getNodeLinkCurrentSituiation(Model model) {
		return "view/basicinfo/B_NODELINK_003";
   }

   /**
    * 노드/링크 자료실 ViewPage
    * @param type the type
    * @return the bigdata sub page
    */
   @GetMapping("/nodelink/reference/list.do")
   public String getNodeLinkReference(Model model,MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo) {
	   
	   	Paging paging = new Paging();
	   	
		paging.setPageNo(1);
		paging.setTotalCount(mGmStdLinkNodeMngInfoMapper.countByStdInfoNm(mGmStdLinkNodeMngInfo));
		paging.setTotalCount(1);
		model.addAttribute("searchContent", mGmStdLinkNodeMngInfo.getSearchContent());
		model.addAttribute("referenceList", mGmStdLinkNodeMngInfoMapper.findByNodeLinkMngInfoByStdInfoNm(mGmStdLinkNodeMngInfo));
		model.addAttribute("paging", paging);
   	return "view/basicinfo/B_NODELINK_004";
   }
   
   
   /**
    * @Method Name : saveRefInfo
    * @작성일 : 2023. 9. 20.
    * @작성자 : KY.LEE
    * @Method 설명 : 링크/노드 자료실 등록
    * @return
    */
   @PostMapping("/nodelink/reference/save.ajax")
   public @ResponseBody CommonResponse<?> saveRefInfo(@RequestBody MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo){
   	// validation check
   	ValidateBuilder dtoValidator = new ValidateBuilder(mGmStdLinkNodeMngInfo);
   	ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("stdInfoNm", new ValidateChecker().setRequired())
			        .addRule("etlDt", new ValidateChecker().setRequired()).isValid();
   	
   	if(!dtoValidatorResult.isSuccess()) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
   	}
		
   	try {
   		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
   		Date now = new Date();
   		String aplcnYmd = format.format(now);
   		mGmStdLinkNodeMngInfo.setStdInfoId(GgitsCommonUtils.getUuid());
   		mGmStdLinkNodeMngInfo.setSaveInfo(LoginSessionUtils.getOprtrNm());
   		mGmStdLinkNodeMngInfo.setAplcnYmd(aplcnYmd);
   		mGmStdLinkNodeMngInfoMapper.saveMGmStdLinkNodeMngInfo(mGmStdLinkNodeMngInfo);
   	} catch (Exception e) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "노드/링크 자료등록에 실패 하였습니다.");
   	}
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "노드/링크 자료를 등록했습니다.");
   }
   
   /**
    * @Method Name : deleteRefInfo
    * @작성일 : 2023. 8. 29.
    * @작성자 : KY.LEE
    * @Method 설명 : 링크/노드 자료실 삭제
    * @return
    */
   @GetMapping("/nodelink/reference/{stdInfoId}/delete.ajax")
   public @ResponseBody CommonResponse<?> deleteRefInfo(@PathVariable("stdInfoId") String stdInfoId){
	  try {
		  mGmStdLinkNodeMngInfoMapper.deleteByStdInfoId(stdInfoId);
	  	} catch (Exception e) {
	   	  return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "노드/링크 삭제에 실패 하였습니다.");
	   	}
	   return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "노드/링크 자료를 삭제 했습니다.");
   }
   
   
   /**
    * @Method Name : updateRefInfo
    * @작성일 : 2023. 9. 20.
    * @작성자 : KY.LEE
    * @Method 설명 : 링크/노드 자료실 수정
    * @return
    */
   @PostMapping("/nodelink/reference/update.ajax")
   public @ResponseBody CommonResponse<?> updateRefInfo(@RequestBody MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo){
	   	// validation check
	   	ValidateBuilder dtoValidator = new ValidateBuilder(mGmStdLinkNodeMngInfo);
	   	ValidateResult dtoValidatorResult = dtoValidator
	   					.addRule("stdInfoId", new ValidateChecker().setRequired())
				        .addRule("stdInfoNm", new ValidateChecker().setRequired())
				        .addRule("etlDt", new ValidateChecker().setRequired()).isValid();
	   	
	   	if(!dtoValidatorResult.isSuccess()) {
	   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
	   	}  
	   try {
		   	  mGmStdLinkNodeMngInfo.setSaveInfo(LoginSessionUtils.getOprtrNm());
			  mGmStdLinkNodeMngInfoMapper.updateMGmStdLinkNodeMngInfo(mGmStdLinkNodeMngInfo);
		  	} catch (Exception e) {
		   	  return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "노드/링크 수정에 실패 하였습니다.");
		   	}
	   return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "노드/링크 자료를 수정 했습니다.");
   }
   
}

package com.neighbor21.ggits.web.controller.basicinfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.CGmStdLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.mapper.MGmStdLinkNodeMngInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;

@Controller
@RequestMapping("/basicinfo")
public class BasicInfoController {
	
	@Autowired
	MGmStdLinkNodeMngInfoMapper mGmStdLinkNodeMngInfoMapper;

	@Autowired
	CGmStdLinkMapper cGmStdLinkMapper;
	
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
    * @return the bigdata sub page
    */
   @GetMapping("/nodelink/current/list.do")
   public String getNodeLinkCurrentSituiation(Model model) {
		return "view/basicinfo/B_NODELINK_003";
   }

   /**
    * 노드/링크 자료실 ViewPage
    * @return the bigdata sub page
    */
   @GetMapping("/nodelink/reference/list.do")
   public String getNodeLinkReference(Model model,MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo) {
	   
	   	Paging paging = new Paging();
	   	List<Map<String,Object>> yearsList = mGmStdLinkNodeMngInfoMapper.findAllDataYears();
	   	
		paging.setPageNo(1);
		paging.setTotalCount(mGmStdLinkNodeMngInfoMapper.countByStdInfoNm(mGmStdLinkNodeMngInfo));
		paging.setTotalCount(1);
		model.addAttribute("searchContent", mGmStdLinkNodeMngInfo.getSearchContent());
		model.addAttribute("referenceList", mGmStdLinkNodeMngInfoMapper.findByNodeLinkMngInfoByStdInfoNm(mGmStdLinkNodeMngInfo));
		model.addAttribute("paging", paging);
		model.addAttribute("yearsList", yearsList);
		model.addAttribute("searchType", mGmStdLinkNodeMngInfo.getSearchType());
		model.addAttribute("aplcnYmd", mGmStdLinkNodeMngInfo.getAplcnYmd());
		
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
			        .addRule("stdAplcnType", new ValidateChecker().setRequired())
			        .addRule("aplcnYmd", new ValidateChecker().setRequired()).isValid();
   	
   	if(!dtoValidatorResult.isSuccess()) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
   	}
		
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	Date now = new Date();
	String aplcnYmd = format.format(now);
	mGmStdLinkNodeMngInfo.setStdInfoId(GgitsCommonUtils.getUuid());
	mGmStdLinkNodeMngInfo.setSaveInfo(LoginSessionUtils.getOprtrNm());
	mGmStdLinkNodeMngInfo.setAplcnYmd(aplcnYmd);
	mGmStdLinkNodeMngInfoMapper.saveMGmStdLinkNodeMngInfo(mGmStdLinkNodeMngInfo);
	
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
	   
	   mGmStdLinkNodeMngInfoMapper.deleteByStdInfoId(stdInfoId);
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
				        .addRule("stdAplcnType", new ValidateChecker().setRequired())
				        .addRule("stdInfoNm", new ValidateChecker().setRequired())
				        .addRule("aplcnYmd", new ValidateChecker().setRequired()).isValid();
	   	
	   	if(!dtoValidatorResult.isSuccess()) {
	   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
	   	}  
	   	
   	   mGmStdLinkNodeMngInfo.setSaveInfo(LoginSessionUtils.getOprtrNm());
	   mGmStdLinkNodeMngInfoMapper.updateMGmStdLinkNodeMngInfo(mGmStdLinkNodeMngInfo);
	   
	   return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "노드/링크 자료를 수정 했습니다.");
   }

   @GetMapping("/nodelink/getLinkInfo.ajax")
   public @ResponseBody
   ResponseEntity<?> getDangerVehicleInfo(
		   @RequestParam("linkId") String linkId
   ){
	   CGmStdLink linkData = cGmStdLinkMapper.findOneWithNodeByLinkInfo(linkId);
	   return new ResponseEntity<>(linkData, HttpStatus.OK);
   }
   
}

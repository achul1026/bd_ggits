package com.neighbor21.ggits.web.controller.statistics;

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
import com.neighbor21.ggits.common.entity.GOpDataUseStatsRpt;
import com.neighbor21.ggits.common.entity.GOpTrfInfoStatsRpt;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.GOpDataUseStatsRptMapper;
import com.neighbor21.ggits.common.mapper.GOpTrfInfoStatsRptMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;

@Controller
@RequestMapping("/statistics")
public class AnalysisReportController {
	
	@Autowired
	GOpTrfInfoStatsRptMapper gOpTrfInfoStatsRptMapper;
	
	@Autowired
	GOpDataUseStatsRptMapper gOpDataUseStatsRptMapper;
	
	/**
     * @Method Name : viewTrafficInfoStatsReport
     * @작성일 : 2023. 9. 7.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석  > 분석 리포트
     * @return
     */
	@GetMapping("/analysis/traffic/report/list.do")
    public String viewAnalyReport(Model model , GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt){
		Long totalCount = gOpTrfInfoStatsRptMapper.countBySearchOption(gOpTrfInfoStatsRpt);
    	Paging paging = new Paging();
    	paging.setPageNo(gOpTrfInfoStatsRpt.getPage());
    	paging.setTotalCount(totalCount.intValue());
    	
    	model.addAttribute("searchOption", gOpTrfInfoStatsRpt);
    	model.addAttribute("paging", paging);
    	model.addAttribute("gOpTrfInfoStatsRptList", gOpTrfInfoStatsRptMapper.findGOpTrfInfoStatsRptListBySearchOption(gOpTrfInfoStatsRpt));
        return "view/statistics/trafficInfoReportList";
    }
	
	/**
	 * @Method Name : viewTrafficInfoStatsReport
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석  > 분석 리포트 > 데이터 활용 통계 리포트
	 * @return
	 */
	@GetMapping("/analysis/data/report/list.do")
	public String viewDataReport(Model model ,GOpDataUseStatsRpt gOpDataUseStatsRpt){
		Long totalCount = gOpDataUseStatsRptMapper.countBySearchOption(gOpDataUseStatsRpt);
    	Paging paging = new Paging();
    	paging.setPageNo(gOpDataUseStatsRpt.getPage());
    	paging.setTotalCount(totalCount.intValue());
    	
    	model.addAttribute("searchOption", gOpDataUseStatsRpt);
    	model.addAttribute("paging", paging);
    	model.addAttribute("gOpDataUseStatsRptList", gOpDataUseStatsRptMapper.findGOpDataUseStatsRptListBySearchOption(gOpDataUseStatsRpt));
		return "view/statistics/dataAnalysisReportList";
	}
	
    /**
     * @Method Name : saveGOpTrfInfoStatsRpt
     * @작성일 : 2023. 11. 24
     * @작성자 : KY.LEE
     * @Method 설명 : 교통정보 통계 리포트 등록
     * @param GOpTrfInfoStatsRpt
     * @return
     */
   @PostMapping("/analysis/traffic/report/save.ajax")
   public @ResponseBody CommonResponse<?> saveGOpTrfInfoStatsRpt(@RequestBody GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt){
		ValidateBuilder dtoValidator = new ValidateBuilder(gOpTrfInfoStatsRpt);
		ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("rptTitle", new ValidateChecker().setRequired())
			        .addRule("dtlUrl", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "필수 입력 항목을 입력해주세요.");
    	}
    	
	 String nowStr = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
	 
	 gOpTrfInfoStatsRpt.setRptId(GgitsCommonUtils.getUuid());
	 gOpTrfInfoStatsRpt.setOprtrId(LoginSessionUtils.getOprtrId());
	 gOpTrfInfoStatsRpt.setRegistYmd(nowStr);
	 
	 gOpTrfInfoStatsRptMapper.saveGOpTrfInfoStatsRpt(gOpTrfInfoStatsRpt);
	 
   	 return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "분석 리포트가 등록 되었습니다.");
   }
   
   
   /**
    * @Method Name : saveGOpDataUseStatsRpt
    * @작성일 : 2023. 11. 26
    * @작성자 : KY.LEE
    * @Method 설명 : 데이터 활용 통계 리포트
    * @param GOpDataUseStatsRpt
    * @return
    */
  @PostMapping("/analysis/data/report/save.ajax")
  public @ResponseBody CommonResponse<?> saveGOpDataUseStatsRpt(@RequestBody GOpDataUseStatsRpt gOpDataUseStatsRpt){
		ValidateBuilder dtoValidator = new ValidateBuilder(gOpDataUseStatsRpt);
		ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("rptTitle", new ValidateChecker().setRequired())
			        .addRule("dtlUrl", new ValidateChecker().setRequired()).isValid();
		
   	if(!dtoValidatorResult.isSuccess()) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "필수 입력 항목을 입력해주세요.");
   	}
   	
	 String nowStr = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
	 
	 gOpDataUseStatsRpt.setRptId(GgitsCommonUtils.getUuid());
	 gOpDataUseStatsRpt.setOprtrId(LoginSessionUtils.getOprtrId());
	 gOpDataUseStatsRpt.setRegistYmd(nowStr);
	 
	 gOpDataUseStatsRptMapper.saveGOpDataUseStatsRpt(gOpDataUseStatsRpt);
	 
  	 return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "분석 리포트가 등록 되었습니다.");
  }
   
   /**
    * @Method Name : deleteGOpTrfInfoStatsRpt
    * @작성일 : 2023. 11. 24.
    * @작성자 : KY.LEE
    * @Method 설명 : 교통정보 리포트 정보 삭제
    * @return
    */
   @GetMapping("/analysis/traffic/report/{rptId}/delete.ajax")
   public @ResponseBody CommonResponse<?> deleteGOpTrfInfoStatsRpt(@PathVariable("rptId") String rptId){
	   //삭제하려는 사람이 작성자인지 체크
	   GOpTrfInfoStatsRpt dbGOpTrfInfoStatsRpt = gOpTrfInfoStatsRptMapper.findOneByRptId(rptId);
	   if(LoginSessionUtils.getOprtrId() != dbGOpTrfInfoStatsRpt.getOprtrId()) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "리포트 삭제는 작성자만 가능합니다.");
	   }
	  gOpTrfInfoStatsRptMapper.deleteGOpTrfInfoStatsRpt(rptId);
	  
	   return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "리포트 자료를 삭제 했습니다.");
   }

   /**
    * @Method Name : deleteGOpTrfInfoStatsRpt
    * @작성일 : 2023. 11. 26.
    * @작성자 : KY.LEE
    * @Method 설명 : 데이터활용 리포트 정보 삭제
    * @return
    */
   @GetMapping("/analysis/data/report/{rptId}/delete.ajax")
   public @ResponseBody CommonResponse<?> deleteGOpDataUseStatsRpt(@PathVariable("rptId") String rptId){
	   //삭제하려는 사람이 작성자인지 체크
	   GOpDataUseStatsRpt dbGOpDataUseStatsRpt = gOpDataUseStatsRptMapper.findOneByRptId(rptId);
	   if(LoginSessionUtils.getOprtrId() != dbGOpDataUseStatsRpt.getOprtrId()) {
		   return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "리포트 삭제는 작성자만 가능합니다.");
	   }
	   
	   gOpDataUseStatsRptMapper.deleteGOpDataUseStatsRpt(rptId);
	   
	   return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "리포트 자료를 삭제 했습니다.");
   }
   
}

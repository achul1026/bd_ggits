package com.neighbor21.ggits.web.controller.statistics;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.ExcelFileComponent;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssActPopltnBsuntMapper;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.web.service.statistics.TrafficDatabaseStatisticsService;

@Controller
@RequestMapping("/statistics")
public class TrafficDatabaseStatisticsController {
	
	@Autowired
	TrafficDatabaseStatisticsService trafficDatabaseStatisticsService;
	
	@Autowired
	TrfIpcssActPopltnBsuntMapper trfIpcssActPopltnBsuntMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	ExcelFileComponent excelFileComponent;
	
	/**
     * @Method Name : viewTrafficDatabase
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표
     * @return
     */
	
	@GetMapping("/traffic/database/list.do")
    public String viewTrafficDatabase(Model model){
    	
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
        return "view/statistics/trafficDatabaseList";
    }
	
	/**
     * @Method Name : viewTrafficDatabase
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통영향평가
     * @return
     */
	@GetMapping("/traffic/database/impact/list.do")
    public String viewTrafficImpactList(Model model, TrfIpcssActPopltnBsunt trfIpcssActPopltnBsunt){
		int totalCnt = 0;
		
		totalCnt = trfIpcssActPopltnBsuntMapper.countTrafficImpactList(trfIpcssActPopltnBsunt);
		
		List<TrfIpcssActPopltnBsunt> trafficImpactList = trfIpcssActPopltnBsuntMapper.findAllTrafficImpactList(trfIpcssActPopltnBsunt);

		Paging paging = new Paging();
    	paging.setPageNo(trfIpcssActPopltnBsunt.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
    	model.addAttribute("trafficImpactList", trafficImpactList);
		model.addAttribute("strDt", trfIpcssActPopltnBsunt.getStrDt());
		model.addAttribute("endDt", trfIpcssActPopltnBsunt.getEndDt());
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/trafficImpactList";
    }
	
	/**
     * @Method Name : downloadTrafficImpactReportFormat
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 >양식 다운로드
     * @return
	 * @throws Exception 
     */
	@GetMapping("/traffic/database/impact/download.do")
	public void downloadTrafficImpactReportFormat(HttpServletResponse res, @RequestParam("fileNm") String fileNm) throws Exception {
		excelFileComponent.downloadTrafficImpactReportSampleFile(res, fileNm);
	}
	
	/**
     * @Method Name : deleteTrafficImpactReport
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 선택 삭제
     * @return
	 */
	@PostMapping("/traffic/database/impact/delete.ajax")
	public @ResponseBody CommonResponse<?> deleteTrafficImpactReport(@RequestParam(value="ipcssMngNoArr[]") String[] ipcssMngNoArr){
		
		if(ipcssMngNoArr.length <= 0) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "삭제중 오류가 발생하였습니다.");
		}
		try {
//			trafficDatabaseStatisticsService.delectImpactReport(ipcssMngNoArr);
		} catch (Exception e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "삭제중 오류가 발생하였습니다.");
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "선택된 교통영향평가를 삭제하였습니다.");
	}
	
	/**
     * @return 
	 * @Method Name : createImpactReport
     * @작성일 : 2023. 10. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 신규조사등록
     * @return
     */
	@PostMapping("/traffic/database/impact/upload.do")
	public @ResponseBody CommonResponse<?> createImpactReport(@RequestParam("excelFile") MultipartFile file) {
		try {
			if(file == null || file.isEmpty()) {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 파일을 찾을 수 없습니다.");
			}
			excelFileComponent.uploadTrafficImpactReport(file, "9");
		} catch (Exception e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 등록에 실패하였습니다.");
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "교통영향평가를 등록하였습니다.");
	}
	
	/**
     * @Method Name : viewTrafficInfoStatsReport
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 교통영향평가 상세
     * @return
     */
	@GetMapping("/traffic/database/assessment/{type}/list.do")
    public String imppactAssessmentDetail(Model model, @PathVariable String type, @RequestParam String ipcssMngNo){
		
		switch (type) {
		case "crossroad_by_traffic":
			// 교차로별 교통량
			break;
		case "section_by_traffic":
			// 구간별 교통량
			break;
		case "mean_by_traffic":
			// 이용수단별
			break;
		case "similar_facilities_by_floating_population":
			// 유사시설별 유동인구
			break;
		case "use_by_traffic_mean_share_rate":
			// 용도별 교통수단 분담률
			break;
		case "use_by_nbopl_cnt":
			// 용도별 재차인원
			break;
		case "parking_occurrence":
			// 주차발생
			break;
		case "time_by_in_and_out_pass":
			// 시간대별 유출입 통행
			break;
		case "time_by_pass_distribution":
			// 시간대별 통행 분포
			break;	
		}
		
		model.addAttribute("type", type);
		model.addAttribute("ipcssMngNo", ipcssMngNo);
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
//       return "view/statistics/trafficImpactAssessmentDetail";
    }
}

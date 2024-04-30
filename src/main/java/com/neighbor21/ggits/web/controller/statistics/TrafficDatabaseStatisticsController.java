package com.neighbor21.ggits.web.controller.statistics;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbor21.ggits.common.component.ExcelDownloadComponent;
import com.neighbor21.ggits.common.component.ExcelUploadComponent;
import com.neighbor21.ggits.common.dto.TimeInoutflExmnDTO;
import com.neighbor21.ggits.common.dto.TimePassDistrbDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsGraphDTO;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.GgitsLinkStd1d;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssCrsrdTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssEtcTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssExmnBizInfo;
import com.neighbor21.ggits.common.entity.TrfIpcssMeanShareRt;
import com.neighbor21.ggits.common.entity.TrfIpcssNbopl;
import com.neighbor21.ggits.common.entity.TrfIpcssParkngOccurBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssTimeInoutflExmn;
import com.neighbor21.ggits.common.mapper.GgitsLinkStd1dMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssActPopltnBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssCrsrdTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssEtcTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssExmnBizInfoMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssMeanShareRtMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssNboplMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssParkngOccurBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssStrsctTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimeInoutflExmnMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimePassDistrbMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
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
	GgitsLinkStd1dMapper ggitsLinkStd1dMapper;
	
	@Autowired
	TrfIpcssExmnBizInfoMapper trfIpcssExmnBizInfoMapper;
	
	@Autowired
	TrfIpcssTimePassDistrbMapper trfIpcssTimePassDistrbMapper;
	
	@Autowired
	TrfIpcssTimeInoutflExmnMapper trfIpcssTimeInoutflExmnMapper;
	
	@Autowired
	TrfIpcssParkngOccurBsuntMapper trfIpcssParkngOccurBsuntMapper;
	
	@Autowired
	TrfIpcssCrsrdTrfvlmMapper trfIpcssCrsrdTrfvlmMapper;
	
	@Autowired
	TrfIpcssStrsctTrfvlmMapper trfIpcssStrsctTrfvlmMapper;
	
	@Autowired
	TrfIpcssNboplMapper trfIpcssNboplMapper;
	
	@Autowired
	TrfIpcssMeanShareRtMapper trfIpcssMeanShareRtMapper;
	
	@Autowired
	TrfIpcssEtcTrfvlmMapper trfIpcssEtcTrfvlmMapper;
	
	@Autowired
	ExcelUploadComponent excelUploadComponent;
	
	@Autowired
	ExcelDownloadComponent excelDownloadComponent;
	
	/**
     * @Method Name : viewTrafficDatabase
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표
     * @return
     */
	
	@GetMapping("/traffic/database/list.do")
    public String viewTrafficDatabase(Model model, GgitsLinkStd1d ggitsLinkStd1d){
		Integer totalCnt = 0;
		ggitsLinkStd1d.setChartYn("N");
		totalCnt = ggitsLinkStd1dMapper.countLinkStd(ggitsLinkStd1d);
		List<GgitsLinkStd1d> linkStdList = ggitsLinkStd1dMapper.findAllLinkStdList(ggitsLinkStd1d);
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(ggitsLinkStd1d.getStrDt()) && GgitsCommonUtils.isNull(ggitsLinkStd1d.getEndDt())) {
			if(GgitsCommonUtils.isNull(ggitsLinkStd1d.getChartStrDt()) || GgitsCommonUtils.isNull(ggitsLinkStd1d.getChartEndDt())) {
				ggitsLinkStd1d.setChartStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -5));
				ggitsLinkStd1d.setChartEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
			}		
		}
		
		Paging paging = new Paging();
    	paging.setPageNo(ggitsLinkStd1d.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
    	model.addAttribute("totalCnt", totalCnt);
    	model.addAttribute("linkStdList", linkStdList);
    	model.addAttribute("searchContent", ggitsLinkStd1d.getSearchContent());
    	model.addAttribute("sigunCdId", ggitsLinkStd1d.getSigunCdId());
    	model.addAttribute("strDt", ggitsLinkStd1d.getStrDt());
    	model.addAttribute("endDt", ggitsLinkStd1d.getEndDt());
    	model.addAttribute("chartStrDt", ggitsLinkStd1d.getChartStrDt());
    	model.addAttribute("chartEndDt", ggitsLinkStd1d.getChartEndDt());
        	
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
        return "view/statistics/trafficDatabaseList";
    }
	
	@PostMapping("/traffic/database/list.ajax")
    public @ResponseBody CommonResponse<?> viewTrafficChartDatabase(@RequestBody GgitsLinkStd1d ggitsLinkStd1d){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	// 차트 기간만 검색 조건에 추가 
    	ggitsLinkStd1d.setStrDt(null);
    	ggitsLinkStd1d.setEndDt(null);
    	
    	ggitsLinkStd1d.setChartYn("Y");
    	
		// 시별 교통량
		Map<String, Object> trfVolRankMap = ggitsLinkStd1dMapper.trfVolRankMap(ggitsLinkStd1d);
		resultMap.put("volSigunNmArr", trfVolRankMap.get("sigunNmArr"));
		resultMap.put("volDataArr", trfVolRankMap.get("volDataArr"));
		
		// 시별 평균속도
		Map<String, Object> trfSpdRankMap = ggitsLinkStd1dMapper.trfSpdRankMap(ggitsLinkStd1d);
		resultMap.put("spdSigunNmArr", trfSpdRankMap.get("sigunNmArr"));
		resultMap.put("spdDataArr", trfSpdRankMap.get("spdDataArr"));
		
		// 시별 혼잡레벨
//		Map<String, Object> trfCongGradeRankMap = ggitsLinkStd1dMapper.trfCongGradeRankMap(ggitsLinkStd1d);
//		resultMap.put("congGradeSigunNmArr", trfCongGradeRankMap.get("congGradeSigunNmArr"));
//		resultMap.put("congGradeDataArr", trfCongGradeRankMap.get("congGradeDataArr"));
		
    	return CommonResponse.successToData(resultMap, "");
    }
	
	/**
     * @Method Name : viewTrafficDatabase
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통영향평가
     * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
     */
	@GetMapping("/traffic/database/impact/list.do")
    public String viewTrafficImpactList(Model model, TrfIpcssExmnBizInfo trfIpcssExmnBizInfo){
		int totalCnt = 0;
		totalCnt = trfIpcssExmnBizInfoMapper.countTrafficImpact(trfIpcssExmnBizInfo);
		List<TrfIpcssExmnBizInfo> trafficImpactList = trfIpcssExmnBizInfoMapper.findAllTrafficImpactList(trfIpcssExmnBizInfo);
		
		Paging paging = new Paging();
    	paging.setPageNo(trfIpcssExmnBizInfo.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
    	model.addAttribute("trafficImpactList", trafficImpactList);
		model.addAttribute("searchOption", trfIpcssExmnBizInfo);
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/trafficImpactList";
    }
	
	/**
     * @Method Name : downloadTrafficImpactReportFormat
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 양식 다운로드
     * @return
	 * @throws Exception 
     */
	@GetMapping("/traffic/database/impact/sample/download.do")
	public void downloadTrafficImpactReportFormat(HttpServletResponse res, @RequestParam("fileNm") String fileNm) throws Exception {
		excelDownloadComponent.downloadTrafficImpactReportSampleFile(res, fileNm);
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
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "삭제할 영향평가를 선택해주세요.");
		}
		try {
			trafficDatabaseStatisticsService.delectImpactReport(ipcssMngNoArr);
		} catch (SQLException e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "삭제중 오류가 발생하였습니다.");
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "선택된 교통영향평가를 삭제하였습니다.");
	}
	
	/**
	 * @Method Name : createImpactReport
     * @작성일 : 2023. 10. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 신규조사등록
     * @param : file
     * @return
	 * @throws ParseException 
	 * @throws IOException 
     */
	@PostMapping("/traffic/database/impact/upload.ajax")
	public @ResponseBody CommonResponse<?> createImpactReport(@RequestParam("excelFile") MultipartFile file) throws ParseException, IOException {
		try {
			if(file == null || file.isEmpty()) {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 파일을 찾을 수 없습니다.");
			}
			excelUploadComponent.uploadTrafficImpactReport(file);
		} catch (Exception e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 등록에 실패하였습니다. <br>"+ e.getMessage());
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "교통영향평가를 등록하였습니다.");
	}
	
	/**
     * @Method Name : viewTrafficInfoStatsReport
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 교통영향평가 상세
     * @return
	 * @throws ParseException 
	 * @throws JsonProcessingException 
     */
	@GetMapping("/traffic/database/assessment/{type}/list.do")
    public String imppactAssessmentDetail(Model model, @PathVariable String type, @RequestParam(required = true) String ipcssMngNo,TrfvlmStatisticsDTO trfvlmStatisticsDTO) throws ParseException, JsonProcessingException{
		List<TrfIpcssCrsrdTrfvlm> crsrdNmList = new ArrayList<TrfIpcssCrsrdTrfvlm>();
		String TrfvlmStatisticsGraphJson = 	"";
		switch (type) {
		case "crossroad_by_traffic_weekday":
			// 주교차로별 교통량(default / TRF_IPCSS_CRSRD_TRFVLM)
			trfvlmStatisticsDTO.setDywkDiv("평일");
			List<TrfvlmStatisticsDTO> crsrdTrfvlmListForDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
			List<TrfvlmStatisticsGraphDTO> crsrdTrfvlmGraphListForDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsGraphList(trfvlmStatisticsDTO);
			crsrdNmList =  trfIpcssCrsrdTrfvlmMapper.findAllCrsrdNmAndCrsrdNo(trfvlmStatisticsDTO);
			model.addAttribute("impactReportList", crsrdTrfvlmListForDwell);
			model.addAttribute("impactReportGraphList", crsrdTrfvlmGraphListForDwell);
			if(crsrdTrfvlmGraphListForDwell.size() > 0) {
				TrfvlmStatisticsGraphJson = new ObjectMapper().writeValueAsString(crsrdTrfvlmGraphListForDwell);
	    	}
			model.addAttribute("impactReportGraphListJson", TrfvlmStatisticsGraphJson);
			
			model.addAttribute("crsrdNmList", crsrdNmList);
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDataNo())) model.addAttribute("dataNo", trfvlmStatisticsDTO.getDataNo());
			// 통계 데이터 조회
			break;
		case "crossroad_by_traffic_weekend":
			// 교차로별 교통량(default / TRF_IPCSS_CRSRD_TRFVLM)
			trfvlmStatisticsDTO.setDywkDiv("주말");
			List<TrfvlmStatisticsDTO> crsrdTrfvlmListForNonDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
			List<TrfvlmStatisticsGraphDTO> crsrdTrfvlmGraphListForNonDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsGraphList(trfvlmStatisticsDTO);
			crsrdNmList =  trfIpcssCrsrdTrfvlmMapper.findAllCrsrdNmAndCrsrdNo(trfvlmStatisticsDTO);
			model.addAttribute("impactReportGraphList", crsrdTrfvlmGraphListForNonDwell);
			model.addAttribute("impactReportList", crsrdTrfvlmListForNonDwell);
			model.addAttribute("crsrdNmList", crsrdNmList);
			
			if(crsrdTrfvlmGraphListForNonDwell.size() > 0) {
				TrfvlmStatisticsGraphJson = new ObjectMapper().writeValueAsString(crsrdTrfvlmGraphListForNonDwell);
	    	}
			model.addAttribute("impactReportGraphListJson", TrfvlmStatisticsGraphJson);
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDataNo())) model.addAttribute("dataNo", trfvlmStatisticsDTO.getDataNo());
			// 통계 데이터 조회
			break;
		case "section_by_traffic_weekday":
			// 평일 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
			trfvlmStatisticsDTO.setDywkDiv("평일");
			List<TrfvlmStatisticsDTO> strsctTrfvlmListForDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);
			List<TrfvlmStatisticsGraphDTO> strsctTrfvlmGraphListForDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsGraphList(trfvlmStatisticsDTO);
			model.addAttribute("impactReportList", strsctTrfvlmListForDwell);
			model.addAttribute("impactReportGraphList", strsctTrfvlmGraphListForDwell);
			
			if(strsctTrfvlmGraphListForDwell.size() > 0) {
				TrfvlmStatisticsGraphJson = new ObjectMapper().writeValueAsString(strsctTrfvlmGraphListForDwell);
	    	}
			model.addAttribute("impactReportGraphListJson", TrfvlmStatisticsGraphJson);
			// 통계 데이터 조회
			break;
		case "section_by_traffic_weekend":
			// 주말 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
			trfvlmStatisticsDTO.setDywkDiv("주말");
			List<TrfvlmStatisticsDTO> strsctTrfvlmListForNonDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);
			List<TrfvlmStatisticsGraphDTO> strsctTrfvlmGraphListForNonDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsGraphList(trfvlmStatisticsDTO);
			model.addAttribute("impactReportList", strsctTrfvlmListForNonDwell);
			model.addAttribute("impactReportGraphList", strsctTrfvlmGraphListForNonDwell);
			
			if(strsctTrfvlmGraphListForNonDwell.size() > 0) {
				TrfvlmStatisticsGraphJson = new ObjectMapper().writeValueAsString(strsctTrfvlmGraphListForNonDwell);
	    	}
			model.addAttribute("impactReportGraphListJson", TrfvlmStatisticsGraphJson);
			break;
		case "mean_by_traffic":
			// 이용수단별(TRF_IPCSS_ETC_TRFVLM)
			String[] timeArray = new String[] {"07:00~07:15", "07:15~07:30", "07:30~07:45", "07:45~08:00", "08:00",
					"08:00~08:15", "08:15~08:30", "08:30~08:45", "08:45~09:00", "09:00",
					"17:00~17:15", "17:15~17:30", "17:30~17:45", "17:45~18:00", "18:00",
					"18:00~18:15", "18:15~18:30", "18:30~18:45", "18:45~19:00", "19:00"};
			List<String> timeSctrnList = Arrays.asList(timeArray);
			if(GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDywkDiv())) {
				trfvlmStatisticsDTO.setDywkDiv("평일");
			}
			
			List<TrfIpcssEtcTrfvlm> headerList = trfIpcssEtcTrfvlmMapper.findAllPointNmList(trfvlmStatisticsDTO);
			
			String exmnYmd = "";
			exmnYmd = headerList.get(0).getExmnYmd();
			
			List<TrfIpcssEtcTrfvlm> etcInfoList = new ArrayList<TrfIpcssEtcTrfvlm>();
			for(String timeSctrn : timeSctrnList) {
				TrfIpcssEtcTrfvlm etcDTO = new TrfIpcssEtcTrfvlm();
				etcDTO.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
				etcDTO.setTimeSctnNm(timeSctrn);
				etcDTO.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
				etcDTO.setIpcssResultList(trfIpcssEtcTrfvlmMapper.findOneEtcTrfvlmInfo(etcDTO));
				etcInfoList.add(etcDTO);
			}
			model.addAttribute("headerList", headerList);
			model.addAttribute("impactReportList", etcInfoList);
			model.addAttribute("exmnYmd", exmnYmd);
			
			// 통계 데이터 조회
			for(TrfIpcssEtcTrfvlm headerInfo : headerList) {
				headerInfo.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
				headerInfo.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
				Map<String, Object> etcChartData = trfIpcssEtcTrfvlmMapper.findallChartData(headerInfo);	
				model.addAttribute("pointNo"+headerInfo.getPointNo(), etcChartData.get("etcchartdataarray"));
			}
			
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDywkDiv())) model.addAttribute("dywkDiv", trfvlmStatisticsDTO.getDywkDiv());
			break;
		case "similar_facilities_by_floating_population":
			// 유사시설별 유동인구(TRF_IPCSS_ACT_POPLTN_BSUNT)
			List<TrfIpcssActPopltnBsunt> actPopltnBsuntList = trfIpcssActPopltnBsuntMapper.findAllActPopltnBsuntList(trfvlmStatisticsDTO);
			
			for(TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
				actPopltnBsunt.setIpcssResultList(trfIpcssActPopltnBsuntMapper.findOneActPopltnBsuntInfo(actPopltnBsunt));
			}
			model.addAttribute("impactReportList", actPopltnBsuntList);
			
			// 통계 데이터 조회
			List<TrfIpcssActPopltnBsunt> actPopltnUsgNoList = new ArrayList<TrfIpcssActPopltnBsunt>();
			actPopltnUsgNoList = trfIpcssActPopltnBsuntMapper.findAllusgNoList(trfvlmStatisticsDTO);
			
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getUsgCd())) {
				trfvlmStatisticsDTO.setUsgCd(actPopltnUsgNoList.get(0).getUsgCd());
			}
			TrfIpcssActPopltnBsunt actPopltnChartData = trfIpcssActPopltnBsuntMapper.findAllChartData(trfvlmStatisticsDTO);	

			model.addAttribute("usgNoList", actPopltnUsgNoList);
			model.addAttribute("actPopltnChartData", actPopltnChartData);
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getUsgCd())) model.addAttribute("usgCd", trfvlmStatisticsDTO.getUsgCd());
			break;
		case "use_by_traffic_mean_share_rate_dwell":
			// 주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
			trfvlmStatisticsDTO.setDwellYn("Y");
			List<TrfIpcssMeanShareRt> meanShareRtForDwellList = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
			
			for(TrfIpcssMeanShareRt meanShareRtForDwell : meanShareRtForDwellList) {
				meanShareRtForDwell.setIpcssResultList(trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForDwell(meanShareRtForDwell));
			}
			
			model.addAttribute("impactReportList", meanShareRtForDwellList);
			
			// 통계 데이터 조회
			TrfIpcssMeanShareRt meanShareForDwell = new TrfIpcssMeanShareRt();
			List<String> meanShareTrfUseCdListForDwell = Arrays.asList(meanShareForDwell.getTrfUseCdListForDwell());
			for(String trfUseCd : meanShareTrfUseCdListForDwell) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("trfUseCd", trfUseCd);
				paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
				Map<String, Object> meanShareMap = trfIpcssMeanShareRtMapper.findAllChartData(paramMap);
				model.addAttribute("meanShareRtArr"+trfUseCd, meanShareMap.get("meansharertarr"));  	
			}
			break;
		case "use_by_traffic_mean_share_rate_non_dwell":
			// 비주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
			trfvlmStatisticsDTO.setDwellYn("N");
			List<TrfIpcssMeanShareRt> meanShareRtForNonDwellList = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
			
			for(TrfIpcssMeanShareRt meanShareRtForNonDwell : meanShareRtForNonDwellList) {
				meanShareRtForNonDwell.setIpcssResultList(trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForNonDwell(meanShareRtForNonDwell));
			}
			
			model.addAttribute("impactReportList", meanShareRtForNonDwellList);
			
			// 통계 데이터 조회
			TrfIpcssMeanShareRt meanShareForNonDwell = new TrfIpcssMeanShareRt();
			List<String> meanShareTrfUseCdListForNonDwell = Arrays.asList(meanShareForNonDwell.getTrfUseCdListForNonDwell());
			for(String trfUseCd : meanShareTrfUseCdListForNonDwell) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("trfUseCd", trfUseCd);
				paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
				Map<String, Object> meanShareMap = trfIpcssMeanShareRtMapper.findAllChartData(paramMap);
				model.addAttribute("meanShareRtArr"+trfUseCd, meanShareMap.get("meansharertarr"));  	
			}
			break;
		case "use_by_nbopl_cnt_dwell":
			// 주거용도 재차인차인원(TRF_IPCSS_NBOPL)
			trfvlmStatisticsDTO.setDwellYn("Y");
			List<TrfIpcssNbopl> nboplForDwellList = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
			
			for(TrfIpcssNbopl nboplForNonDwell : nboplForDwellList) {
				nboplForNonDwell.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForDwell(nboplForNonDwell));
			}
			model.addAttribute("impactReportList", nboplForDwellList);
			
			// 통계 데이터 조회
			TrfIpcssNbopl nboplForDwell = new TrfIpcssNbopl();
			List<String> nboplTrfUseCdListForDwell = Arrays.asList(nboplForDwell.getTrfUseCdListForDwell());
			for(String trfUseCd : nboplTrfUseCdListForDwell) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("trfUseCd", trfUseCd);
				paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
				Map<String, Object> nboplMap = trfIpcssNboplMapper.findAllChartData(paramMap);
				model.addAttribute("nboplCntArr"+trfUseCd, nboplMap.get("nboplcntarr"));  	
			}
			break;
		case "use_by_nbopl_cnt_non_dwell":
			// 비주거용도 재차인차인원(TRF_IPCSS_NBOPL)
			trfvlmStatisticsDTO.setDwellYn("N");
			List<TrfIpcssNbopl> nboplForNonDwellList = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
			
			for(TrfIpcssNbopl nboplForNonDwell : nboplForNonDwellList) {
				nboplForNonDwell.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForNonDwell(nboplForNonDwell));
			}
			model.addAttribute("impactReportList", nboplForNonDwellList);
			
			// 통계 데이터 조회
			TrfIpcssNbopl nboplForNonDwell = new TrfIpcssNbopl();
			List<String> nboplTrfUseCdListForNonDwell = Arrays.asList(nboplForNonDwell.getTrfUseCdListForNonDwell());
			for(String trfUseCd : nboplTrfUseCdListForNonDwell) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("trfUseCd", trfUseCd);
				paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
				Map<String, Object> nboplMap = trfIpcssNboplMapper.findAllChartData(paramMap);
				model.addAttribute("nboplCntArr"+trfUseCd, nboplMap.get("nboplcntarr"));  	
			}
			break;
		case "parking_occurrence":
			// 주차발생(TRF_IPCSS_PARKNG_OCCUR_BSUNT)
			List<TrfIpcssParkngOccurBsunt> parkngOccurBsuntList = trfIpcssParkngOccurBsuntMapper.findAllParkngOccurBsuntList(trfvlmStatisticsDTO);
			for(TrfIpcssParkngOccurBsunt parkngOccurBsunt : parkngOccurBsuntList) {
				parkngOccurBsunt.setIpcssResultList(trfIpcssParkngOccurBsuntMapper.findOneParkngOccurBsuntInfo(parkngOccurBsunt));
			}
			List<TrfIpcssParkngOccurBsunt> parkngUsgNoList = new ArrayList<TrfIpcssParkngOccurBsunt>();
			parkngUsgNoList = trfIpcssParkngOccurBsuntMapper.findAllUsgNoList(trfvlmStatisticsDTO);

			// 통계 데이터 조회
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getUsgCd())) {
				trfvlmStatisticsDTO.setUsgCd(parkngUsgNoList.get(0).getUsgCd());
			}
			TrfIpcssParkngOccurBsunt parkingChartData = trfIpcssParkngOccurBsuntMapper.findAllChartData(trfvlmStatisticsDTO);
			
			model.addAttribute("impactReportList", parkngOccurBsuntList);
			model.addAttribute("usgNoList", parkngUsgNoList);
			model.addAttribute("parkingChartData", parkingChartData);
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getUsgCd())) model.addAttribute("usgCd", trfvlmStatisticsDTO.getUsgCd());
			break;
		case "time_by_in_and_out_pass":
			// 시간대별 유출입 통행(TRF_IPCSS_TIME_INOUTFL_EXMN)
			Map<String, Object> inAndOutUsgCdNmMap = trfIpcssTimeInoutflExmnMapper.findOneUsgCdNmList(trfvlmStatisticsDTO);
			List<TimeInoutflExmnDTO> timeInoutflExmnList = trfIpcssTimeInoutflExmnMapper.findAllTimeInoutflExmnList(trfvlmStatisticsDTO);
			
			// 합계 데이터 조회
			List<TrfIpcssTimeInoutflExmn> unitSumList = new ArrayList<TrfIpcssTimeInoutflExmn>();
			String usgNmStr = (String) inAndOutUsgCdNmMap.get("cdnmarr");
			if(!GgitsCommonUtils.isNull(usgNmStr)) {
				String[] usgNmArr = usgNmStr.split(",");
				List<String> usgNmList = Arrays.asList(usgNmArr);
				for(String usgNm : usgNmList) {
					TrfIpcssTimeInoutflExmn inoutCntSum = new TrfIpcssTimeInoutflExmn();
					TrfIpcssTimeInoutflExmn inoutRateSum = new TrfIpcssTimeInoutflExmn();
					
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
					paramMap.put("usgNm", usgNm);
					
					inoutCntSum = trfIpcssTimeInoutflExmnMapper.findAllInoutCntSumList(paramMap);
					unitSumList.add(inoutCntSum);
					
					inoutRateSum = trfIpcssTimeInoutflExmnMapper.findAllInoutRateSumList(paramMap);
					unitSumList.add(inoutRateSum);
				}
			}
			
			model.addAttribute("cdNmArr", inAndOutUsgCdNmMap.get("cdnmarr"));
			model.addAttribute("impactReportList", timeInoutflExmnList);
			model.addAttribute("unitSumList", unitSumList);
			
			// 통계 데이터 조회
			Map<String, Object> timeInOutChartMap = trfIpcssTimeInoutflExmnMapper.findAllChartData(trfvlmStatisticsDTO);
			model.addAttribute("resdngInflRtDataArray", timeInOutChartMap.get("resdnginflrtdataarray"));
			model.addAttribute("resdngOutflRtDataArray", timeInOutChartMap.get("resdngoutflrtdataarray"));
			model.addAttribute("visitInflRtDataArray", timeInOutChartMap.get("visitinflrtdataarray"));
			model.addAttribute("visitOutflRtDataArray", timeInOutChartMap.get("visitoutflrtdataarray"));
//			model.addAttribute("totInflRtDataArray", timeInOutChartMap.get("totinflrtdataarray"));
//			model.addAttribute("totOutflRtDataArray", timeInOutChartMap.get("totoutflrtdataarray"));
			model.addAttribute("timeDataArray", timeInOutChartMap.get("timedataarray"));
			break;
		case "time_by_pass_distribution":
			// 시간대별 통행 분포(TRF_IPCSS_TIME_PASS_DISTRB)
			if(GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDywkDiv())) {
				trfvlmStatisticsDTO.setDywkDiv("평일");
			}
			Map<String, Object>  usgCdNmMap = trfIpcssTimePassDistrbMapper.findOneUsgCdNmList(trfvlmStatisticsDTO);
			List<TimePassDistrbDTO> timePassDistrbList = trfIpcssTimePassDistrbMapper.findAllTimePassDistrbList(trfvlmStatisticsDTO);

			model.addAttribute("cdNmArr", usgCdNmMap.get("cdnmarr"));
			model.addAttribute("impactReportList", timePassDistrbList);
			
			// 통계 데이터 조회(시간대별 통행)
			List<String> usgNoList = trfIpcssTimePassDistrbMapper.findOneUsgCdNmListForChart(trfvlmStatisticsDTO);
			List<MOpCode> trfUseCdList = mOpCodeMapper.findAllCodeListByGrpCdId("TRF_USE_CD");
			for(String usgNoStr : usgNoList) {
				for(MOpCode trfUseCd : trfUseCdList) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("ipcssMngNo", trfvlmStatisticsDTO.getIpcssMngNo());
					paramMap.put("dywkDiv", trfvlmStatisticsDTO.getDywkDiv());
					paramMap.put("usgCd", usgNoStr);
					paramMap.put("trfUseCd", trfUseCd.getCdId());
					
					Map<String, Object> chartDataMap = new HashMap<String, Object>();
					int usgNo = Integer.parseInt(usgNoStr);
					int trfUseCdNo = Integer.parseInt(trfUseCd.getCdId());
					if((usgNo <= 1 || usgNo >=101) && (trfUseCdNo >=1 && trfUseCdNo <=4)) {
						chartDataMap = trfIpcssTimePassDistrbMapper.findAllChartDataForDwell(paramMap);						
						model.addAttribute("inflRt" + trfUseCd.getCdId(), chartDataMap.get("inflRtDataArray"));
						model.addAttribute("outflRt" + trfUseCd.getCdId(), chartDataMap.get("outflRtDataArray"));
					}else if((usgNo > 1 && usgNo < 101) && (trfUseCdNo >=5 && trfUseCdNo <=6)){
						chartDataMap = trfIpcssTimePassDistrbMapper.findAllChartDataForNonDwell(paramMap);												
						model.addAttribute("inflRt" + trfUseCd.getCdId(), chartDataMap.get("inflRtDataArray"));
						model.addAttribute("outflRt" + trfUseCd.getCdId(), chartDataMap.get("outflRtDataArray"));
					}
					model.addAttribute("timeDataArray", chartDataMap.get("timeDataArray"));	
				}
			}
			if(!GgitsCommonUtils.isNull(trfvlmStatisticsDTO.getDywkDiv())) model.addAttribute("dywkDiv", trfvlmStatisticsDTO.getDywkDiv());
			break;	
		}
		model.addAttribute("type", type);
		model.addAttribute("ipcssMngNo", ipcssMngNo);
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
    }
	
	/**
	 * @Method Name : downloadImpactReport
     * @작성일 : 2023. 11. 21.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 영향평가 다운로드
     * @param : file
     * @return
	 * @throws ParseException 
     */
	@GetMapping("/traffic/database/impact/download.do")
	public void downloadImpactReport(@RequestParam String ipcssMngNo, HttpServletRequest req , HttpServletResponse resp) throws ParseException {
		excelDownloadComponent.downloadImactReportExcel(ipcssMngNo, req, resp);
	}
	
	/**
	 * @Method Name : downloadTrafficImpactReport
     * @작성일 : 2023. 11. 30.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 영향평가 수정용 엑셀 다운로드
     * @param : file
     * @return
     */
	@GetMapping("/traffic/database/impact/{type}/download.do")
	public void downloadTrafficImpactReport(HttpServletRequest req , HttpServletResponse resp, @PathVariable String type, @RequestParam("ipcssMngNo") String ipcssMngNo) throws Exception {
		excelDownloadComponent.downloadImpactReportPartExcel(req, resp, type, ipcssMngNo);
	}
	
	/**
	 * @Method Name : updateImpactReport
     * @작성일 : 2023. 11. 30.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 영향평가 수정
     * @param : file
     * @return
     */
	@PostMapping("/traffic/database/impact/{type}/update.ajax")
	public @ResponseBody CommonResponse<?> updateImpactReport(@PathVariable String type, @RequestParam String ipcssMngNo, @RequestParam("excelFile") MultipartFile file) {
		try {
			if(file == null || file.isEmpty()) {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 파일을 찾을 수 없습니다.");
			}
			excelUploadComponent.updateTrafficImpactReport(file, ipcssMngNo, type);
		} catch (Exception e) {
			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "교통영향평가 등록에 실패하였습니다. <br>"+ e.getMessage());
		}
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "교통영향평가 수정에 성공하였습니다.");
	}
}

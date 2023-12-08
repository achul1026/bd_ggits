package com.neighbor21.ggits.web.controller.statistics;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.LTcOpenApiRqstLog;
import com.neighbor21.ggits.common.entity.MrtBusRungLogAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnRungAnal;
import com.neighbor21.ggits.common.entity.MrtStdLinkSectnInfo;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.LTcOpenApiRqstLogMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRungLogAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnRungAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Controller
@RequestMapping("/statistics")
public class DataUseStatisticsController {
	
	@Autowired
	LTcOpenApiRqstLogMapper lTcOpenApiRqstLogMapper;
	
	@Autowired
	LTcDataLogMapper lTcDataLogMapper;
	
	@Autowired
	MOpOperatorMapper mOpOperatorMapper;
	
	@Autowired
	MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
	
	@Autowired
	MrtBusSttnRungAnalMapper mrtBusSttnRungAnalMapper;
	
	@Autowired
	MrtBusRungLogAnalMapper mrtBusRungLogAnalMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	/**
     * @Method Name : viewDataUseStats
     * @작성일 : 2023. 9. 7.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 데이터 활용 통계 > 서비스 이력
     * @return
     */
	@GetMapping("/data/use/{type}/list.do")
    public String viewDataUseStats(Model model, CommonEntity commonEntity, @PathVariable String type){

		int totalCnt = 0;
		switch (type) {
		case "open_api_use_log":
			//Open API 활용 이력
			totalCnt = lTcOpenApiRqstLogMapper.countDataUseStatsList(commonEntity);
			
			List<LTcOpenApiRqstLog> ltcOpenApiRqstLogList = lTcOpenApiRqstLogMapper.findAllDataUseStatsList(commonEntity);

			model.addAttribute("dataUseStatsList", ltcOpenApiRqstLogList);
			break;
		case "clit_link_sys_log":
			// 수집/연계시스템 이력
			totalCnt = lTcDataLogMapper.countDataUseStatsList(commonEntity);
			
			List<LTcDataLog> lTcDataLogList = lTcDataLogMapper.findAllDataUseStatsList(commonEntity);
			
			model.addAttribute("dataUseStatsList", lTcDataLogList);
			break;
		case "sm_crsrd_data_log":
			// 스마트 교차로 데이터 이력 (l_tc_data_log)
			totalCnt = lTcDataLogMapper.countSmcrdDataLogList(commonEntity);
			
			List<LTcDataLog> ltcdatataList = lTcDataLogMapper.findAllSmcrdDataLogList(commonEntity);
			
			model.addAttribute("dataUseStatsList", ltcdatataList);
			break;
		}
		
    	Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
    	
    	model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchInfo", commonEntity);
        return "view/statistics/"+BDStringUtil.convertCamelCase(type);
    }

	/**
	 * @Method Name : viewDataTrafficImpact
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 데이터 활용 통계 > 교통영향평가 데이터
	 * @return
	 */
	@GetMapping("/data/impact/{type}/list.do")
	public String viewDataTrafficImpacts(Model model, @PathVariable String type){
		
		switch (type) {
		case "reg_enty_exit_data_tnt":
			// 지역별 진출입 데이터 집계
			break;
		case "catg_enty_exit_data_tnt":
			// 유형별 진출입 데이터 집계
			break;
		}
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
	/**
	 * @Method Name : viewDataTrafficRecode
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 데이터 활용 통계 > 이력 집계
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/data/recode/{type}/list.do")
	public String viewDataRecode(@PathVariable String type, Model model, CommonEntity commonEntity) throws ParseException{
		
		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_info_log_colt":
			// 교통정보 이력 집계(mrt_std_link_sectn_info)
			totalCnt = mrtStdLinkSectnInfoMapper.countTrafficInfoLogList(commonEntity);
			
			List<MrtStdLinkSectnInfo> trafficInfoLogList = mrtStdLinkSectnInfoMapper.findAllTrafficInfoLogList(commonEntity);
			
			model.addAttribute("logTotalList", trafficInfoLogList);
			
			break;
		case "public_traffic_info_log_colt":
			// 대중교통 정보 이력 집계(mrt_bus_rung_log_anal)
			totalCnt = mrtBusRungLogAnalMapper.countBusInfoLogList(commonEntity);
			List<MrtBusRungLogAnal> busInfoLogList = mrtBusRungLogAnalMapper.findAllBusInfoLogList(commonEntity);
			
			model.addAttribute("logTotalList", busInfoLogList);
			break;
		case "bus_sttn_rout_info_colt":
			// 정류장별 운행이력 정보 집계(mrt_bus_sttn_rung_anal)
			totalCnt = mrtBusSttnRungAnalMapper.countBusSttnRungAnal(commonEntity);
			List<MrtBusSttnRungAnal> busSttnRungAnalList = mrtBusSttnRungAnalMapper.findAllBusSttnRungAnal(commonEntity);
			
			model.addAttribute("logTotalList", busSttnRungAnalList);
			break;
		}
		
		Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity);
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
}

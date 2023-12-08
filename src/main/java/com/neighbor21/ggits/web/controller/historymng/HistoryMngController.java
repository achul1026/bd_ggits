package com.neighbor21.ggits.web.controller.historymng;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.LTcOpenApiRqstLog;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.LinkedTableInfo;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.LTcOpenApiRqstLogMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.web.service.historymng.HistoryMngService;

@Controller
@RequestMapping("/historymng")
public class HistoryMngController {
	
	@Autowired
	HistoryMngService historyMngService;
	
	@Autowired
	LTcDataLogMapper lTcDataLogMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	LTcOpenApiRqstLogMapper lTcOpenApiRqstLogMapper;
	
    /**
      * @Method Name : viewCollectList
      * @작성일 : 2023. 8. 26.
      * @작성자 : NK.KIM
      * @Method 설명 : 이력관리 > 수집이력
      * @return
     * @throws ParseException 
      */
    @GetMapping("/collectmng/list.do")
    public String viewCollectList(Model model,LTcDataLog lTcDataLog) throws ParseException{
    	ServerMngType linkedType = lTcDataLog.getLinkedType()!=null&&!"".equals(lTcDataLog.getLinkedType())?ServerMngType.getServerMngTypeFromCode(lTcDataLog.getLinkedType()):ServerMngType.LOC_GOVMNT_LINKAGE;

    	List<String> linkedList = LinkedTableInfo.getLinkedTableInfoList(linkedType);
    	if(!linkedList.isEmpty()) {
    		lTcDataLog.setLinkedList(linkedList);
    	}
    	
    	if(GgitsCommonUtils.isNull(lTcDataLog.getTabNum())) {
			lTcDataLog.setTabNum("1");
		}
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(lTcDataLog.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(lTcDataLog.getStartTime())) {
				int startTime = Integer.parseInt(lTcDataLog.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(lTcDataLog.getEndTime())) {
				int endTime = Integer.parseInt(lTcDataLog.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setEndDt(endToday);
		}
    	
    	lTcDataLog.setEtlClsf("KAFKA"); //TODO::KAFKA(카프카) 가 수집이력인지 확인필요
    	List<LTcDataLog> collectList = historyMngService.findAllLTcDataLogList(lTcDataLog);
		int totalCnt = lTcDataLogMapper.countAll(lTcDataLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcDataLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("linkedType", linkedType.getCode());
    	model.addAttribute("searchOption",lTcDataLog);
    	model.addAttribute("paging", paging);
    	model.addAttribute("collectList",collectList);
		model.addAttribute("collTyCdList", mOpCodeMapper.findAllCodeListByGrpCdId("COLL_TY_CD"));
    	
        return "view/historymng/collectMngList";
    }
    
    /**
     * @Method Name : viewServerControlList
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 이력관리 > 데이터 수집 상태 이력
     * @return
     * @throws ParseException 
     */
    @GetMapping("/collect/status/list.do")
    public String viewCollectStatusList(Model model,LTcDataLog lTcDataLog) throws ParseException{
    	ServerMngType linkedType = lTcDataLog.getLinkedType()!=null&&!"".equals(lTcDataLog.getLinkedType())?ServerMngType.getServerMngTypeFromCode(lTcDataLog.getLinkedType()):ServerMngType.LOC_GOVMNT_LINKAGE;

    	List<String> linkedList = LinkedTableInfo.getLinkedTableInfoList(linkedType);
    	if(!linkedList.isEmpty()) {
    		lTcDataLog.setLinkedList(linkedList);
    	}
    	
    	if(GgitsCommonUtils.isNull(lTcDataLog.getTabNum())) {
			lTcDataLog.setTabNum("1");
		}
    	
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(lTcDataLog.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(lTcDataLog.getStartTime())) {
				int startTime = Integer.parseInt(lTcDataLog.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(lTcDataLog.getEndTime())) {
				int endTime = Integer.parseInt(lTcDataLog.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setEndDt(endToday);
		}
		
//    	lTcDataLog.setEtlClsf(""); //TODO::서버 제어 이력명 예제 필요
		
    	List<LTcDataLog> collectStatusList = historyMngService.findAllLTcDataLogList(lTcDataLog);
    	int totalCnt = lTcDataLogMapper.countAll(lTcDataLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcDataLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("linkedType", linkedType.getCode());
    	model.addAttribute("paging", paging);
    	model.addAttribute("collectStatusList",collectStatusList);

    	return "view/historymng/collectStatusList";
    }
    
    /**
     * @Method Name : viewServerErrorList
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 이력관리 > 서버 장애 이력
     * @return
     * @throws ParseException 
     */
    @GetMapping("/collect/error/list.do")
    public String viewCollectErrorList(Model model,LTcDataLog lTcDataLog) throws ParseException{
    	ServerMngType linkedType = lTcDataLog.getLinkedType()!=null&&!"".equals(lTcDataLog.getLinkedType())?ServerMngType.getServerMngTypeFromCode(lTcDataLog.getLinkedType()):ServerMngType.LOC_GOVMNT_LINKAGE;

    	List<String> linkedList = LinkedTableInfo.getLinkedTableInfoList(linkedType);
    	if(!linkedList.isEmpty()) {
    		lTcDataLog.setLinkedList(linkedList);
    	}
    	
    	if(GgitsCommonUtils.isNull(lTcDataLog.getTabNum())) {
			lTcDataLog.setTabNum("1");
		}
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(lTcDataLog.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(lTcDataLog.getStartTime())) {
				int startTime = Integer.parseInt(lTcDataLog.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(lTcDataLog.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(lTcDataLog.getEndTime())) {
				int endTime = Integer.parseInt(lTcDataLog.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcDataLog.setEndDt(endToday);
		}
    	
    	//lTcDataLog.setEtlClsf(""); //TODO::서버 장애 이력 이력명 예제 필요
		
    	List<LTcDataLog> collectErrorList = historyMngService.findAllLTcDataLogList(lTcDataLog);
    	int totalCnt = lTcDataLogMapper.countAll(lTcDataLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcDataLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("linkedType", linkedType.getCode());
    	model.addAttribute("paging", paging);
    	model.addAttribute("collectErrorList",collectErrorList);
    	
    	return "view/historymng/collectErrorList";
    }

    /**
     * @Method Name : viewOpenApiHistList
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 이력관리 > OPEN API 사용 이력
     * @return
     * @throws ParseException 
     */
    @GetMapping("/open/api/list.do")
    public String viewOpenApiHistList(Model model,LTcOpenApiRqstLog lTcOpenApiRqstLog) throws ParseException{
		if(GgitsCommonUtils.isNull(lTcOpenApiRqstLog.getTabNum())) {
			lTcOpenApiRqstLog.setTabNum("1");
		}
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(lTcOpenApiRqstLog.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(lTcOpenApiRqstLog.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(lTcOpenApiRqstLog.getStartTime())) {
				int startTime = Integer.parseInt(lTcOpenApiRqstLog.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcOpenApiRqstLog.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(lTcOpenApiRqstLog.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(lTcOpenApiRqstLog.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(lTcOpenApiRqstLog.getEndTime())) {
				int endTime = Integer.parseInt(lTcOpenApiRqstLog.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			lTcOpenApiRqstLog.setEndDt(endToday);
		}
    	
    	List<LTcOpenApiRqstLog> openApiList = historyMngService.findAllLTcOpenApiRqstLogList(lTcOpenApiRqstLog);
    	int totalCnt = lTcOpenApiRqstLogMapper.countAllBySearchOption(lTcOpenApiRqstLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcOpenApiRqstLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchOption", lTcOpenApiRqstLog);
    	model.addAttribute("openApiList",openApiList);
    	
    	return "view/historymng/openApiHistList";
    }
    
    
    /**
     * @Method Name : viewOpenApiHistList
     * @작성일 : 2023. 9. 11.
     * @작성자 : NK.KIM
     * @Method 설명 : 이력관리 > 노드/링크 자료실 이력
     * @return
     */
    @GetMapping("/node/list.do")
    public String nodeLinkList(Model model){
    	
    	return "view/historymng/nodeLinkList";
    }
}

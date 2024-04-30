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
import com.neighbor21.ggits.common.entity.OpenApiPvsnLog;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.LinkedTableInfo;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.OpenApiPvsnLogMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
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
	OpenApiPvsnLogMapper openApiPvsnLogMapper;
	
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
    	// 처음 접근 시
		if(GgitsCommonUtils.isNull(lTcDataLog.getStrDt()) && GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			lTcDataLog.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			lTcDataLog.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
    	
		lTcDataLog.setPrgrsStts("END"); //수집완료 상태
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
    	
    	// 처음 접근 시
		if(GgitsCommonUtils.isNull(lTcDataLog.getStrDt()) && GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			lTcDataLog.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			lTcDataLog.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
		
		if(!GgitsCommonUtils.isNull(lTcDataLog.getSearchContent())){
			lTcDataLog.setDsetId(lTcDataLog.getSearchContent());
		}
		
    	List<LTcDataLog> collectStatusList = historyMngService.findAllLTcDataLogList(lTcDataLog);
    	for(LTcDataLog item : collectStatusList) {
    		item.setColName(LinkedTableInfo.getCodeName(item.getTrgtTbl()));
    	}
    	int totalCnt = lTcDataLogMapper.countAll(lTcDataLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcDataLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
		model.addAttribute("searchOption",lTcDataLog);
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
		
    	// 처음 접근 시
		if(GgitsCommonUtils.isNull(lTcDataLog.getStrDt()) && GgitsCommonUtils.isNull(lTcDataLog.getEndDt())) {
			lTcDataLog.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			lTcDataLog.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
		
		if(!GgitsCommonUtils.isNull(lTcDataLog.getSearchContent())){
			lTcDataLog.setDsetId(lTcDataLog.getSearchContent());
		}
    	
		lTcDataLog.setPrgrsStts("ERROR");
		
    	List<LTcDataLog> collectErrorList = historyMngService.findAllLTcDataLogList(lTcDataLog);
    	for(LTcDataLog item : collectErrorList) {
    		item.setColName(LinkedTableInfo.getCodeName(item.getTrgtTbl()));
    	}
    	
    	int totalCnt = lTcDataLogMapper.countAll(lTcDataLog);
		
		Paging paging = new Paging();
		paging.setPageNo(lTcDataLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("linkedType", linkedType.getCode());
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchOption",lTcDataLog);
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
    public String viewOpenApiHistList(Model model,OpenApiPvsnLog openApiPvsnLog) throws ParseException{
		if(GgitsCommonUtils.isNull(openApiPvsnLog.getTabNum())) {
			openApiPvsnLog.setTabNum("1");
		}
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(openApiPvsnLog.getStrDt()) && GgitsCommonUtils.isNull(openApiPvsnLog.getEndDt())) {
			openApiPvsnLog.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			openApiPvsnLog.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
    	
    	List<OpenApiPvsnLog> openApiList = openApiPvsnLogMapper.findAllOpenApiPvsnLogList(openApiPvsnLog);
    	int totalCnt = openApiPvsnLogMapper.countAllOpenApiPvsnLogBySearchOption(openApiPvsnLog);
		
		Paging paging = new Paging();
		paging.setPageNo(openApiPvsnLog.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchOption", openApiPvsnLog);
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

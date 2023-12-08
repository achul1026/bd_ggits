package com.neighbor21.ggits.web.controller.systemmng;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neighbor21.ggits.common.entity.LOpPgmLogn;
import com.neighbor21.ggits.common.entity.LTcAcdntLogInfo;
import com.neighbor21.ggits.common.entity.LTcFcltsLogInfo;
import com.neighbor21.ggits.common.entity.LTcSrvrLogInfo;
import com.neighbor21.ggits.common.entity.MOpCodeGrp;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.LTcAcdntLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcFcltsLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcSrvrLogInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeGrpMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.web.service.systemmng.OperateMngService;

@Controller
@RequestMapping("/system")
public class OperateMngController {
	
	@Autowired
	OperateMngService operateMngService;
	
	@Autowired
	LOpPgmLognMapper lOpPgmLognMapper;
	
	@Autowired
	MOpCodeGrpMapper mOpCodeGrpMapper;
	
	@Autowired
	LTcFcltsLogInfoMapper lTcFcltsLogInfoMapper;
	
	@Autowired
	LTcSrvrLogInfoMapper lTcSrvrLogInfoMapper;
	
	@Autowired
	LTcAcdntLogInfoMapper lTcAcdntLogInfoMapper;
	
	/**
     * @Method Name : oprtrMngUserList
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 운영 관리
     * @param : logType
     * @param : page
     * @param : searchType
     * @param : searchContent
     * @param : strDt
     * @param : endDt
     * @return 
     */
    @GetMapping(value={"/oprtr/{logType}/list.do", "/oprtr/list.do"})
    public String oprtrMngUserList(@PathVariable(required = false) String logType
    		, @RequestParam(required = false, defaultValue = "1") int page
    		, @RequestParam(required = false) String searchType
    		, @RequestParam(required = false) String searchContent
    		, @RequestParam(required = false) String strDt
    		, @RequestParam(required = false) String endDt
    		, Model model){
    	
    	MOpCodeGrp codeGrp = new MOpCodeGrp();
    	
    	String strDatetime = null;
    	String endDatetime = null;
    	strDatetime = GgitsCommonUtils.dateToDatetimeStr(strDt, "startDate");
    	endDatetime = GgitsCommonUtils.dateToDatetimeStr(endDt, "endDate");
    	
    	int totalCnt = 1;
    	String grpCodeId = "";
    	if(GgitsCommonUtils.isNull(logType)) {
    		logType = "user";
    	}
    	switch (logType) {
		case "user":
			// 사용자 로그
			LOpPgmLogn lOpPgmLogn = new LOpPgmLogn();
			lOpPgmLogn.setSearchType(searchType);
			lOpPgmLogn.setSearchContent(searchContent);
			
			lOpPgmLogn.setStrDt(strDatetime);
			lOpPgmLogn.setEndDt(endDatetime);

			lOpPgmLogn.setPage(page);
			
			List<LOpPgmLogn> userLogList = new ArrayList<LOpPgmLogn>();
			totalCnt = lOpPgmLognMapper.countUserLogBySearchInfo(lOpPgmLogn);
			
			userLogList = lOpPgmLognMapper.findAllLOpList(lOpPgmLogn);
			if(userLogList.size() > 0) {
				grpCodeId = mOpCodeGrpMapper.findOneCodeGrpId(userLogList.get(0).getLogType());				
			}
			codeGrp = mOpCodeGrpMapper.findOneCodeGrpInfo(grpCodeId);
			
			model.addAttribute("oprtrLoglist", userLogList);
			model.addAttribute("logCtg", codeGrp);
			break;
		case "server":
			// 서버 로그
			LTcSrvrLogInfo lTcSrvrLogInfo = new LTcSrvrLogInfo();
			lTcSrvrLogInfo.setSearchType(searchType);
			lTcSrvrLogInfo.setSearchContent(searchContent);
			
			lTcSrvrLogInfo.setStrDt(strDatetime);
			lTcSrvrLogInfo.setEndDt(endDatetime);
			
			lTcSrvrLogInfo.setPage(page);
			
			List<LTcSrvrLogInfo> srvrLogList = new ArrayList<LTcSrvrLogInfo>();
			totalCnt = lTcSrvrLogInfoMapper.countSrvrLogBySearchInfo(lTcSrvrLogInfo);
			
			srvrLogList = lTcSrvrLogInfoMapper.findAllSrvrLogList(lTcSrvrLogInfo);
			if(srvrLogList.size() > 0) {
				grpCodeId = mOpCodeGrpMapper.findOneCodeGrpId(srvrLogList.get(0).getLogType());				
				codeGrp = mOpCodeGrpMapper.findOneCodeGrpInfo(grpCodeId);
			}
			
			model.addAttribute("oprtrLoglist", srvrLogList);
			model.addAttribute("logCtg", codeGrp);
			break;
		case "facility":
			// 시설물 로그
			LTcFcltsLogInfo lTcFcltsLogInfo = new LTcFcltsLogInfo();
			lTcFcltsLogInfo.setSearchType(searchType);
			lTcFcltsLogInfo.setSearchContent(searchContent);
			
			lTcFcltsLogInfo.setStrDt(strDatetime);
			lTcFcltsLogInfo.setEndDt(endDatetime);
			
			lTcFcltsLogInfo.setPage(page);
			
			List<LTcFcltsLogInfo> fcltsLogList = new ArrayList<LTcFcltsLogInfo>();
			totalCnt = lTcFcltsLogInfoMapper.countFcltsLogBySearchInfo(lTcFcltsLogInfo);
			
			fcltsLogList = lTcFcltsLogInfoMapper.findAllFcltsLogList(lTcFcltsLogInfo);
			if(fcltsLogList.size() > 0) {
				grpCodeId = mOpCodeGrpMapper.findOneCodeGrpId(fcltsLogList.get(0).getLogType());				
				codeGrp = mOpCodeGrpMapper.findOneCodeGrpInfo(grpCodeId);
			}
			
			model.addAttribute("oprtrLoglist", fcltsLogList);
			model.addAttribute("logCtg", codeGrp);
			break;
		case "accident":
			// 사고 로그
			LTcAcdntLogInfo lTcAcdntLogInfo = new LTcAcdntLogInfo();
			lTcAcdntLogInfo.setSearchType(searchType);
			lTcAcdntLogInfo.setSearchContent(searchContent);
			
			lTcAcdntLogInfo.setStrDt(strDatetime);
			lTcAcdntLogInfo.setEndDt(endDatetime);
			
			lTcAcdntLogInfo.setPage(page);
			
			List<LTcAcdntLogInfo> acdntLogList = new ArrayList<LTcAcdntLogInfo>();
			totalCnt = lTcAcdntLogInfoMapper.countAcdntLogBySearchInfo(lTcAcdntLogInfo);
			
			acdntLogList = lTcAcdntLogInfoMapper.findAllAcdntLogList(lTcAcdntLogInfo);
			if(acdntLogList.size() > 0) {
				grpCodeId = mOpCodeGrpMapper.findOneCodeGrpId(acdntLogList.get(0).getLogType());				
				codeGrp = mOpCodeGrpMapper.findOneCodeGrpInfo(grpCodeId);
			}
			
			model.addAttribute("oprtrLoglist", acdntLogList);
			model.addAttribute("logCtg", codeGrp);
			break;
		default:
			// 사용자 로그
			LOpPgmLogn lOpPgmLognDft = new LOpPgmLogn();
			lOpPgmLognDft.setSearchType(searchType);
			lOpPgmLognDft.setSearchContent(searchContent);
			
			lOpPgmLognDft.setStrDt(strDatetime);
			lOpPgmLognDft.setEndDt(endDatetime);
			
			lOpPgmLognDft.setPage(page);
			
			totalCnt = lOpPgmLognMapper.countUserLogBySearchInfo(lOpPgmLognDft);
			
			userLogList = lOpPgmLognMapper.findAllLOpList(lOpPgmLognDft);
			if(userLogList.size() > 0) {
				grpCodeId = mOpCodeGrpMapper.findOneCodeGrpId(userLogList.get(0).getLogType());				
				codeGrp = mOpCodeGrpMapper.findOneCodeGrpInfo(grpCodeId);
			}
			
			model.addAttribute("oprtrLoglist", userLogList);
			model.addAttribute("logCtg", codeGrp);
			break;
		}
    	
    	Paging paging = new Paging();
    	paging.setPageNo(page);
    	paging.setTotalCount(totalCnt);
    	
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchType", searchType);
    	model.addAttribute("searchContent", searchContent);
    	model.addAttribute("logType", logType);
    	model.addAttribute("strDt", strDt);
    	model.addAttribute("endDt", endDt);
    	
    	return "view/systemmng/operateMng";
    }

}

package com.neighbor21.ggits.web.controller.common;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.enums.CntnSystemCd;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.component.ExcelDownloadComponent;
import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.common.CommonService;
import com.neighbor21.ggits.web.service.systemmng.MenuMngService;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	MenuMngService menuMngService;
	
	@Autowired
	GOpTrfInfoStatsRptMapper gOpTrfInfoStatsRptMapper;
	
	@Autowired
	GOpDataUseStatsRptMapper gOpDataUseStatsRptMapper;

	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;
	
	@Autowired
	MOpOperatorMapper mOpOperatorMapper;
	
	@Autowired
	MOpMenuMapper mOpMenuMapper;
	
	@Autowired
	LOpPgmLognMapper lOpPgmLognMapper;
	
	@Autowired
	LTcSrvrLogInfoMapper lTcSrvrLogInfoMapper;
	
	@Autowired
	LTcAcdntLogInfoMapper lTcAcdntLogInfoMapper;
	
	@Autowired
	LTcFcltsLogInfoMapper lTcFcltsLogInfoMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	LTcDataLogMapper lTcDataLogMapper;
	
	@Autowired
	MGmStdLinkNodeMngInfoMapper mGmStdLinkNodeMngInfoMapper;
	
	@Autowired
	DsetInfoMapper dsetInfoMapper;
	
	@Autowired
	ExcelDownloadComponent excelDownloadComponent;
	
	@Autowired
	FileMngTotInfoMapper fileMngTotInfoMapper;

	@Autowired
	OpenApiPvsnLogMapper openApiPvsnLogMapper;
	
	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	CGmStdLinkAdstdgMppgMapper cGmStdLinkAdstdgMppgMapper;
	
	@Autowired
	MOpUserCntnSystemMenuMapper mOpUserCntnSystemMenuMapper;
	
	
	  /**
	   * @Method Name : saveSubMenu
	   * @작성일 : 2023. 8. 28
	   * @작성자 : KY.LEE
	   * @Method 설명 : 서브 메뉴 등록
	   * @param paramMap
	   * @return
	   */
	 @PostMapping("/saveMenuLog.ajax")
	 public @ResponseBody CommonResponse<?> saveMenuLog(@RequestParam Map<String,Object> paramMap){
			ValidateBuilder dtoValidator = new ValidateBuilder(paramMap);
			ValidateResult dtoValidatorResult = dtoValidator
						.addRule("menuId", new ValidateChecker().setRequired()).isValid();
	   	
	   	if(!dtoValidatorResult.isSuccess()) { 
	   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
	   	}
	   	String menuId = String.valueOf(paramMap.get("menuId"));
	   	
   		menuMngService.saveLOpUseMenu(menuId);
	   	
	 	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "메뉴 로그 삽입 성공");
	 }
	
	/**
     * @Method Name : modalAdministrativeList
     * @작성일 : 2023. 8. 31.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 행정 기관 모달 팝업 
     * @return
     */
	@GetMapping("/modal/administrative/list.do")
	public String modalAdministrativeList() {
		return "modal/common/modalAdministrativeList";
	}
	
	/**
     * @Method Name : modalAdministrativeList
     * @작성일 : 2023. 8. 31.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 회원가입 행정기관 모달 팝업
     * @return
     */
	@GetMapping("/modal/join/administrative/{type}/list.do")
	public String modalJoinAdministrativeList(@PathVariable String type, Model model) {
		model.addAttribute("type",type);
		return "modal/common/modalJoinAdministrativeList";
	}
	
	/**
	 * @Method Name : modalGroupList
	 * @작성일 : 2023. 9. 1.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 목록 팝업 
	 * @return
	 */
	@GetMapping("/modal/group/list.do")
	public String modalGroupList(Model model,MOpGrpInfo mOpGrpInfo) {
		
		List<MOpGrpInfo> groupList = mOpGrpInfoMapper.findAllGroupList(mOpGrpInfo);
		int grpListTotalCnt = mOpGrpInfoMapper.countAll(mOpGrpInfo);
		model.addAttribute("groupList",groupList);
		
		Paging paging = new Paging();
    	paging.setTotalCount(grpListTotalCnt);
    	paging.setPageNo(mOpGrpInfo.getPage());
    	
    	model.addAttribute("paging", paging);
		return "modal/common/modalGroupList";
	}
	
	/**
	 * @Method Name : modalGroupUserList
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 상세 > 그룹 사용자 모달 팝업 
	 * @return
	 */
	@GetMapping("/modal/group/user/list.do")
	public String modalGroupUserList(	Model model,
										HttpSession session,
										MOpOperator mOpOperator,
										@RequestParam(value = "modalUserList[]", required = false) long[] modalUserList
									) {
		
		
		//2023-11-11 일반 관리자 기관별 목록 조회 추가
		MOpOperator mOpOperatorInfo = (MOpOperator) session.getAttribute("mOpOperatorInfo");
		mOpOperator.setMngInstCd(mOpOperatorInfo.getMngInstCd());
		mOpOperator.setOprtrGrd(mOpOperatorInfo.getOprtrGrd());
				
		List<MOpOperator> userList = mOpOperatorMapper.findAllUserList(mOpOperator);
		int totalCnt = mOpOperatorMapper.countAll(mOpOperator);
		
		Paging paging = new Paging();
    	paging.setTotalCount(totalCnt);
    	paging.setPageNo(mOpOperator.getPage());
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("modalUserList", modalUserList);
    	model.addAttribute("paging", paging);
    	model.addAttribute("searchContent", mOpOperator.getSearchContent());
    	model.addAttribute("searchType", mOpOperator.getSearchType());
    	model.addAttribute("totalCnt", totalCnt);
    	
		return "modal/common/modalGroupUserList";
	}

	/**
	 * @Method Name : modalPasswordChange
	 * @작성일 : 2023. 9. 4.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 사용자 정보 > 사용자 비밀번호 변경
	 * @return
	 */
	@GetMapping("/modal/password/list.do")
	public String modalPasswordChange() {
		return "modal/common/modalPasswordChange";
	}
	
	/**
	 * @Method Name : modalmenuRegistration
	 * @작성일 : 2023. 9. 4.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 메뉴 관리 > 메뉴 등록 
	 * @return
	 */
	@GetMapping("/modal/memuregistration/list.do")
	public String modalMenuRegistration(Model model) {
		model.addAttribute("ctgryCdList", mOpCodeMapper.findAllCodeListByGrpCdId("MENU_CTGRY_CD"));
		return "modal/common/modalMenuRegistration";
	}
	
	
	/**
	 * @Method Name : modalOperation
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 운영 관리 > 로그 
	 * @return
	 */
	@GetMapping("/modal/operation/list.do")
	public String modalOperation(Model model, String logId, String logType) {
		switch (logType) {
		case "user":
 			LOpPgmLogn userLog = lOpPgmLognMapper.findOneLOpPgmLognByLogId(logId);
			model.addAttribute("logInfo", userLog);
			break;
		case "server":
			LTcSrvrLogInfo srvrLog = lTcSrvrLogInfoMapper.findOneLTcSrvrLogInfoByLogId(logId);
			model.addAttribute("logInfo", srvrLog);
			break;
		case "facility":
			LTcFcltsLogInfo fcltsLog = lTcFcltsLogInfoMapper.findOneLTcFcltsLogInfoByLogId(logId);
			model.addAttribute("logInfo", fcltsLog);
			break;
		case "accident":
			LTcAcdntLogInfo acdntlog = lTcAcdntLogInfoMapper.findOneLTcFcltsLogInfoByLogId(logId);
			model.addAttribute("logInfo", acdntlog);
			break;
		}
		model.addAttribute("logType", logType);
		return "modal/common/modalOperation";
	}
	
	
	/**
	 * @Method Name : modalOperation
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 메뉴 관리 > 메뉴 정보 
	 * @return
	 */
	@GetMapping("/modal/menuinfo/{menuId}/list.do")
	public String modalMenuInfo(@PathVariable("menuId") String menuId,Model model) {
	   model.addAttribute("mOpMenu", menuMngService.getMenuDetailInfo(menuId));
	   model.addAttribute("subMenuList", mOpMenuMapper.findAllSubMenuBySortNoIsNotNullAndUpprMenuId(menuId));
	   model.addAttribute("ctgryCdList", mOpCodeMapper.findAllCodeListByGrpCdId("MENU_CTGRY_CD"));
		return "modal/common/modalMenuInfo";
	}
	
	
	/**
	 * @Method Name : modalCodeAdd
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 코드관리 > 그룹코드 정보 > 추가하기 
	 * @return
	 */
	@GetMapping("/modal/codeadd/list.do")
	public String modalCodeAdd(MOpCode mOpCode, Model model) {
		model.addAttribute("mOpCode", mOpCode);
		return "modal/common/modalCodeAdd";
	}
	
	/**
	 * @Method Name : modalCodeInfo
	 * @작성일 : 2023. 9. 6.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 코드관리 > 그룹코드 정보 
	 * @return
	 */
	@GetMapping("/modal/codeinfo/list.do")
	public String modalCodeInfo(Model model, String cdId, String grpCdId) {
		MOpCode mOpCode = new MOpCode();
		mOpCode.setCdId(cdId);
		mOpCode.setGrpCdId(grpCdId);
		MOpCode dbMOpCode = mOpCodeMapper.findOneMOpCodeByCdId(mOpCode);
		model.addAttribute("mOpCode", dbMOpCode);
		return "modal/common/modalCodeInfo";
	}
	
	/**
	 * @Method Name : modalCsvUpload
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 신규조사등록 
	 * @return
	 */
	@GetMapping("/modal/csvupload/list.do")
	public String modalCsvUpload(Model model) {
		return "modal/common/modalCsvUpload";
	}
	
	/**
	 * @Method Name : modalmenuRegistration
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 수정 
	 * @return
	 */
	@GetMapping("/modal/csvupdate/list.do")
	public String modalCsvUpdate(Model model, String type, String ipcssMngNo) {
		model.addAttribute("type", type);
		model.addAttribute("ipcssMngNo", ipcssMngNo);
		return "modal/common/modalCsvUpdate";
	}
	
	/**
	 * @Method Name : modalCollectionSystem
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 서비스 이력 > 수집/연계시스템 이력 > 실패결과 
	 * @return
	 */
	@GetMapping("/modal/collectionsystem/list.do")
	public String modalCollectionSystem(Model model, String jobId, String dsetId, String etlDt) {
		
		LTcDataLog lTcDataLog = new LTcDataLog();
		lTcDataLog.setJobId(jobId);
		lTcDataLog.setDsetId(dsetId);
		lTcDataLog.setEtlDt(etlDt);
		
		lTcDataLog = lTcDataLogMapper.findOneLtcDataLogFailInfo(lTcDataLog); 
		
		model.addAttribute("lTcDataLog", lTcDataLog);
		return "modal/common/modalCollectionSystem";
	}
	
	/**
	 * @Method Name : modalCollectionData
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 서비스 이력 > 스마트교차로 데이터 이력 > 수집 데이터 
	 * @return
	 */
	@GetMapping("/modal/collectiondata/list.do")
	public String modalCollectionData(Model model, String dsetId) {
		
		DsetInfo dsetInfo = new DsetInfo();
		dsetInfo.setDsetId(dsetId);
		
		dsetInfo = dsetInfoMapper.findOneDsetInfo(dsetInfo);
		
		model.addAttribute("dsetInfo", dsetInfo);
		return "modal/common/modalCollectionData";
	}
	
	/**
	 * @Method Name : modalTrafficReport
	 * @작성일 : 2023. 11. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 통계 분석 > 분석 리포트 > 데이터 활용 통계 리포트
	 * @return
	 */
	@GetMapping("/modal/traffic/report/{rptId}/list.do")
	public String modalTrafficReport(Model model,@PathVariable String rptId) {
		GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt = gOpTrfInfoStatsRptMapper.findOneByRptId(rptId);
		boolean isUserChk = false;
		if(gOpTrfInfoStatsRpt.getOprtrId() == LoginSessionUtils.getOprtrId()) {
			isUserChk = true;
		}
		model.addAttribute("isUserChk", isUserChk);
		model.addAttribute("gOpTrfInfoStatsRpt", gOpTrfInfoStatsRpt);
		return "modal/common/modalTrafficReport";
	}

	/**
	 * @Method Name : modaldataUseReport
	 * @작성일 : 2023. 11. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 통계 분석 > 분석 리포트 > 교통정보 통계 리포트
	 * @return
	 */
	@GetMapping("/modal/data/report/{rptId}/list.do")
	public String modaldataUseReport(Model model,@PathVariable String rptId) {
		GOpDataUseStatsRpt gOpDataUseStatsRpt = gOpDataUseStatsRptMapper.findOneByRptId(rptId);
		boolean isUserChk = false;
		if(gOpDataUseStatsRpt.getOprtrId() == LoginSessionUtils.getOprtrId()) {
			isUserChk = true;
		}
		model.addAttribute("isUserChk", isUserChk);
		model.addAttribute("gOpDataUseStatsRpt", gOpDataUseStatsRpt);
		return "modal/common/modalDataUseReport";
	}
	
	/**
	 * @Method Name : modalDataReport
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 서비스 이력 > 스마트교차로 데이터 이력 > 수집 데이터 
	 * @return
	 */
	@GetMapping("/modal/data/report/list.do")
	public String modalDataReport(Model model) {
		return "modal/common/modalDataReport";
	}
	
	/**
	 * @Method Name : modalNodeLinkReference
	 * @작성일 : 2023. 9. 19.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 기초 정보 조회 > 표준 노드/링크 조회 > 표준 노드/링크 자료실 > 업로드 버튼 
	 * @return
	 */
	@GetMapping("/modal/nodelink/reference/save.do")
	public String modalNodeLinkReference(Model model) {
		return "modal/common/modalNodeLinkReferenceSave";
	}

	/**
	 * @Method Name : modalNodeLinkReferenceDetail
	 * @작성일 : 2023. 9. 19.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 기초 정보 조회 > 표준 노드/링크 조회 > 표준 노드/링크 자료실 > table > tr
	 * @return
	 */
	@GetMapping("/modal/nodelink/reference/{stdInfoId}/detail.do")
	public String modalNodeLinkReferenceDetail(Model model ,@PathVariable("stdInfoId") String stdInfoId) {
		model.addAttribute("refDetailInfo", mGmStdLinkNodeMngInfoMapper.findOneByStdInfoId(stdInfoId));
		return "modal/common/modalNodeLinkReferenceDetail";
	}
	
	/**
	 * @Method Name : downloadExcelFile
	 * @작성일 : 2023. 10. 23.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 엑셀 파일 다운로드
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@GetMapping("/excel/{type}/download.do")
	public void downloadExcelFile(@PathVariable("type")String type, HttpServletResponse resp, CommonEntity commonEntity) throws IOException, ParseException {
		excelDownloadComponent.downLoadExcelFile(resp, type, commonEntity);
	}
	
	/**
	   * @Method Name : saveSubMenu
	   * @작성일 : 2023. 8. 28
	   * @작성자 : KY.LEE
	   * @Method 설명 : 서브 메뉴 등록
	   * @param paramMap
	   * @return
	   */
	@GetMapping("/update/sessionTime.ajax")
	public @ResponseBody CommonResponse<?> updateSessionTime(HttpServletRequest request, HttpSession session){
		session.setMaxInactiveInterval(1800);
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "");
	 }
	
	/**
	 * @Method Name : modalDatamngUpload
	 * @작성일 : 2023. 11. 16
	 * @작성자 : TY.LEE
	 * @Method 설명 : 자료 관리 > 자료 등록
	 * @return
	 */
	@GetMapping("/modal/datamng/{fileDivCd}/save.do")
		public String modalRoadUpload(Model model, @PathVariable("fileDivCd") String fileDivCd){
		model.addAttribute("fileDivCd", fileDivCd);
		return "modal/common/modalFileMngSave";
	}
	
	/**
	 * @Method Name : modalDatamngUpdata
	 * @작성일 : 2023. 11. 17
	 * @작성자 : TY.LEE
	 * @Method 설명 : 자료 관리 > 자료 수정
	 * @return
	 */
	@GetMapping("/modal/datamng/{fileDivCd}/update.do")
	public String modalRoadUpdata(Model model, @PathVariable("fileDivCd") String fileDivCd,@RequestParam Map<String,Object> paramMap){
		String fileMngId = String.valueOf(paramMap.get("fileMngId"));
		model.addAttribute("fileDivCd", fileDivCd);
		model.addAttribute("fileMngTotInfo", fileMngTotInfoMapper.findOneByFileMngId(fileMngId));
		return "modal/common/modalFileMngUpdate";
	}
	
	/**
	 * @Method Name : modalDashboardLayout
	 * @작성일 : 2023. 11. 20
	 * @작성자 : TY.LEE
	 * @Method 설명 : 모니터링 대쉬보드 > 나의 레이아웃
	 * @return
	 */
	@GetMapping("/modal/monitroing/layout/list.do")
	public String modalDashBoardMyLayout(Model model) {
		return "modal/common/modalDashBoardMyLayout";
	}
	
	/**
	 * @Method Name : modalAnalysisReport
	 * @작성일 : 2023. 11. 20
	 * @작성자 : KY.LEE
	 * @Method 설명 : 통계 분석 > 분석 리포트 등록하기
	 * @return
	 */
	@GetMapping("/modal/traffic/analysis/report/save.do")
	public String modalAnalysisReportSave(Model model) {
		return "modal/common/modalTrafficInfoAnalysisReportSave";
	}
	
	/**
	 * @Method Name : modalDataReportSave
	 * @작성일 : 2023. 11. 20
	 * @작성자 : KY.LEE
	 * @Method 설명 : 통계 분석 > 분석 리포트 등록하기
	 * @return
	 */
	@GetMapping("/modal/data/analysis/report/save.do")
	public String modalDataReportSave(Model model) {
		return "modal/common/modalDataAnalysisReportSave";
	}
	
	/**
	 * @Method Name : modalDataReportSave
	 * @작성일 : 2023. 12. 15
	 * @작성자 : TY.LEE
	 * @Method 설명 : 이력관리 > openApi 사용이력 
	 * @return
	 */
	@GetMapping("/modal/historymng/openapi/result.do")
	public String modalOpenApiResult(Model model,OpenApiPvsnLog openApiPvsnLog) {
		if(GgitsCommonUtils.isNull(openApiPvsnLog.getDsetId())) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		model.addAttribute("openApiLogDetail", openApiPvsnLogMapper.findOneForOpenApiLogHistory(openApiPvsnLog));

		return "modal/common/modalOpenApiResult";
	}
	
	/**
	 * @Method Name : modal
	 * @작성일 : 2023. 12. 21
	 * @작성자 : JH.PARK
	 * @Method 설명 : 사용자 정보 > 메뉴 권한
	 * @return
	 */
	@GetMapping("/modal/user/detail/intro.do")
	public String modalIntroUserCheck(Model model,@RequestParam(name="oprtrId", required = true)Long oprtrId) {
		int isMenuChk = mOpUserCntnSystemMenuMapper.countByMOpCntnSystemMenuByOprtrId(oprtrId);

		//메뉴권한 초기값세팅 (아무것도없을때)
		if(isMenuChk == 0) {
			for(CntnSystemCd r : CntnSystemCd.values()) {
				MOpUserCntnSystemMenu mOpUserCntnSystemMenu = new MOpUserCntnSystemMenu();
				mOpUserCntnSystemMenu.setOprtrId(oprtrId);
				mOpUserCntnSystemMenu.setUseYn(r.getDefaultYn());
				mOpUserCntnSystemMenu.setCntnSystemCd(r.getCode());
				mOpUserCntnSystemMenuMapper.saveMOpUserCntnSystemMenu(mOpUserCntnSystemMenu);
			}
		}
		
		MOpUserCntnSystemMenu mOpUserCntnSystemMenu = new MOpUserCntnSystemMenu();
		mOpUserCntnSystemMenu.setUseYn(null);
		mOpUserCntnSystemMenu.setOprtrId(oprtrId);
		
		List<MOpUserCntnSystemMenu> menuList = mOpUserCntnSystemMenuMapper.findAllMOpCntnSystemMenuByOprtrIdAndUseYn(mOpUserCntnSystemMenu);

		model.addAttribute("menuList", menuList);
		return "modal/common/modalIntroUserCheck";
	}


	/**
	 * 행정동 가져오기(도로그룹)
	 * @param sggCd
	 * @return
	 */
	@GetMapping("/getDongList.ajax")
	public @ResponseBody ResponseEntity<?> getDongList(
			@RequestParam(name = "sggCd", required = false) String sggCd
	){
		List<CGmStdLinkAdstdgMppg> list = cGmStdLinkAdstdgMppgMapper.findAllGroupDongNmBySggCd(sggCd);
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
}

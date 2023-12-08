package com.neighbor21.ggits.web.controller.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.neighbor21.ggits.common.component.ExcelFileComponent;
import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.DsetInfo;
import com.neighbor21.ggits.common.entity.LOpPgmLogn;
import com.neighbor21.ggits.common.entity.LTcAcdntLogInfo;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.LTcFcltsLogInfo;
import com.neighbor21.ggits.common.entity.LTcSrvrLogInfo;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.DsetInfoMapper;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.LTcAcdntLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.LTcFcltsLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcSrvrLogInfoMapper;
import com.neighbor21.ggits.common.mapper.MGmStdLinkNodeMngInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
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
	ExcelFileComponent excelFileComponent;
	
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
	   	try {
	   		menuMngService.saveLOpUseMenu(menuId);
	   	} catch (Exception e) {
	   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "메뉴 로그 삽입 오류");
	   	}
	   	
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
	@GetMapping("/modal/join/administrative/list.do")
	public String modalJoinAdministrativeList() {
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
										MOpOperator mOpOperator,
										@RequestParam(value = "modalUserList[]", required = false) long[] modalUserList
									) {
		
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
	   model.addAttribute("subMenuList", mOpMenuMapper.findAllBySortNoIsNotNullAndUpprMenuId(menuId));
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
	 * @Method Name : modalmenuRegistration
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
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 서비스 이력 > 스마트교차로 데이터 이력 > 수집 데이터 
	 * @return
	 */
	@GetMapping("/modal/traffic/report/list.do")
	public String modalTrafficReport(Model model) {
		return "modal/common/modalTrafficReport";
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
	 */
	@GetMapping("/excel/{type}/download.do")
	public void downloadExcelFile(@PathVariable("type")String type, HttpServletResponse resp, CommonEntity commonEntity) throws IOException {
		excelFileComponent.downLoadExcelFile(resp, type, commonEntity);
	}
}

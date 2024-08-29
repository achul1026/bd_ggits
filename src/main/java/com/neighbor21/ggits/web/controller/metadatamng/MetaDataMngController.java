package com.neighbor21.ggits.web.controller.metadatamng;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MetaColInfo;
import com.neighbor21.ggits.common.entity.MetaFileInfo;
import com.neighbor21.ggits.common.entity.MetaTabInfo;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.CollectionTypeCode;
import com.neighbor21.ggits.common.mapper.ClschmInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MetaColInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaFileInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaInfsysInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaTabInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.metadatamng.MetaDataMngService;

@Controller
@RequestMapping("/metadatamng")
public class MetaDataMngController {
	
	@Autowired
	MetaTabInfoMapper metaTabInfoMapper;
	
	@Autowired
	MetaColInfoMapper metaColInfoMapper;
	
	@Autowired
	MetaInfsysInfoMapper metaInfsysInfoMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	MetaFileInfoMapper metaFileInfoMapper;
	
	@Autowired
	MetaDataMngService metaDataMngService;
	
	@Autowired
	ClschmInfoMapper clschmInfoMapper;
	
	/**
     * @Method Name : viewTrafficDatabase
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 통계 리포트 > 교통 db화 통계
     * @return
     */
	
	@GetMapping("/list.do")
    public String viewTrafficDatabase(Model model, MetaTabInfo metaTabInfo){
		
		int totalCount = metaTabInfoMapper.countAllMetaTabInfoList(metaTabInfo);
		
		Paging paging = new Paging();
		paging.setPageNo(metaTabInfo.getPage());
		paging.setTotalCount(totalCount);
		
		//수집 유형은 데이터베이스조회
//		model.addAttribute("collTyCdList", mOpCodeMapper.findAllCodeListByGrpCdId("COLL_TY_CD"));
		//나머지는 사용자 글 조회
		
		//검색 조건 조회 로직
		Map<String,Object> searchTabInfo = metaTabInfoMapper.findOneSearchOptionForArrayList();
		List<Object> dataKeywordList = new ArrayList<Object>();
		List<Object> dataTypeList = new ArrayList<Object>();
		List<Object> clschmNmList = new ArrayList<Object>();
		List<Object> clschmIdList = new ArrayList<Object>();
		if(searchTabInfo != null) {
			dataKeywordList = searchTabInfo.get("dataKeywordList")!= null ? GgitsCommonUtils.pgArrayToJavaArray(searchTabInfo.get("dataKeywordList")):null;
			dataTypeList 	= searchTabInfo.get("dataTypeList")!= null ? GgitsCommonUtils.pgArrayToJavaArray(searchTabInfo.get("dataTypeList")):null;
			clschmNmList 	= searchTabInfo.get("clschmNmList")!= null ? GgitsCommonUtils.pgArrayToJavaArray(searchTabInfo.get("clschmNmList")):null;
			clschmIdList 	= searchTabInfo.get("clschmIdList")!= null ? GgitsCommonUtils.pgArrayToJavaArray(searchTabInfo.get("clschmIdList")):null;
		}
		List<MetaTabInfo> metaTabInfoList = metaDataMngService.getMetaDataList(metaTabInfo);
//		for(MetaTabInfo dbMetaTabInfo : metaTabInfoList) {
//			String[] collDataArr = dbMetaTabInfo.getOpngDataListNm().split(",");
//			List<String> resultList = new ArrayList<String>();
//			for(int i = 0; i < collDataArr.length; i++) {
//				resultList.add(CollectionTypeCode.getNameForCode(collDataArr[i]));
//			}
//			dbMetaTabInfo.setCollDataList(resultList);
//		}
		
		for(MetaTabInfo metaInfo : metaTabInfoList) {
			List<MetaFileInfo> fileList = metaFileInfoMapper.findAllByTblId(metaInfo.getTblId());
			if(fileList != null && !fileList.isEmpty()) {
				metaInfo.setFileExstYn("Y");
			}else {
				metaInfo.setFileExstYn("N");
			}
		}

		model.addAttribute("clschmIdList", clschmIdList);
		model.addAttribute("clschmNmList", clschmNmList);
		model.addAttribute("dataKeywordList", dataKeywordList);
		model.addAttribute("dataTypeList", dataTypeList);
		model.addAttribute("paging", paging);
		model.addAttribute("metaDataList", metaTabInfoList);
		model.addAttribute("searchOption", metaTabInfo);
		
        return "view/metadatamng/metadataList";
    }
	 
	/**
	 * @Method Name : metadataDetail
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 메타데이터 상세
	 * @return
	 */
	@GetMapping("/{tblId}/detail.do")
	public String metadataDetail(@PathVariable("tblId") String tblId , Model model){
    	boolean isUserChk = false;
		//나머지는 사용자 글 조회
    	MetaTabInfo metaTabInfo = metaTabInfoMapper.findOneByTblId(tblId);
    	if(metaTabInfo != null) {
    		isUserChk = LoginSessionUtils.loginUserChk(metaTabInfo.getTblOwnrNm());
    	}
    	
    	model.addAttribute("clschmIdList", clschmInfoMapper.findAllClschNm());
//		model.addAttribute("collTyCdList", mOpCodeMapper.findAllCodeListByGrpCdId("COLL_TY_CD"));
		model.addAttribute("metaInfSysInfoList", metaInfsysInfoMapper.findAllMetaInfsysInfo(null));
		model.addAttribute("metaColInfoList", metaColInfoMapper.findAllByTblId(tblId));
		model.addAttribute("metadataInfo", metaTabInfo);
		model.addAttribute("metaFileInfoList", metaFileInfoMapper.findAllByTblId(tblId));
		model.addAttribute("isUserChk", isUserChk);
		return "view/metadatamng/metadataDetail";
	}

	/**
	 * @Method Name : metadataSave
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 메타데이터 등록
	 * @return
	 */
	@GetMapping("/save.do")
	public String metadataSave(Model model){
		model.addAttribute("clschmIdList", clschmInfoMapper.findAllClschNm());
		model.addAttribute("metaInfSysInfoList", metaInfsysInfoMapper.findAllMetaInfsysInfo(null));
		model.addAttribute("collTyCdList", mOpCodeMapper.findAllCodeListByGrpCdId("COLL_TY_CD"));
		return "view/metadatamng/metadataSave";
	}

	/**
	 * @Method Name : metadataSave
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 메타데이터 등록
	 * @return
	 */
	@GetMapping("/file/download.do")
	public void fileDownload(HttpServletResponse response,@RequestParam Map<String,Object> paramMap) throws Exception{
		String fileId = String.valueOf(paramMap.get("fileId"));
		if(GgitsCommonUtils.isNull(fileId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		
		MetaFileInfo dbMetaFileInfo = metaFileInfoMapper.findOneByFileId(fileId);
		
		File dFile = new File(dbMetaFileInfo.getSaveLc());
		
		String encodedFileName = URLEncoder.encode(dbMetaFileInfo.getOrgFileNm(), "UTF-8");
		if(dFile.exists()) {
			response.setHeader("Content-Disposition", "attachment; fileName=\""+encodedFileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", encodedFileName);
			response.setHeader("Content-Length", "" + dbMetaFileInfo.getSaveSize());
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			FileInputStream fis = null;
			OutputStream out = null;
			try {
				fis = new FileInputStream(dFile);
				out = response.getOutputStream();
				
				int readCount = 0;
				byte[] buffer = new byte[4096];
				while((readCount = fis.read(buffer)) != -1){
					out.write(buffer,0,readCount);
				}
				
			} finally {
				fis.close();
				out.close();
			}
		} else {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
	}
	
	
	/**
	 * @Method Name : deleteMetaColInfo
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 데이터 유형 삭제
	 * @return
	 */
    @PostMapping("/dataType/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMetaColInfo(@RequestBody MetaColInfo metaColInfo){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(metaColInfo);
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("tblId", new ValidateChecker().setRequired())
    	.addRule("dsetId", new ValidateChecker().setRequired())
    	.addRule("colSqno", new ValidateChecker().setRequired()).isValid();
    
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	try {
    		metaColInfoMapper.deleteMetaColInfo(metaColInfo);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "데이터 유형 삭제에 실패 했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "데이터 유형이 삭제 되었습니다.");
    }
 
    /**
     * @Method Name : deleteMetaFileInfo
     * @작성일 : 2023. 10. 31.
     * @작성자 : KY.LEE
     * @Method 설명 : 메타데이터 파일 삭제
     * @return
     */
    @PostMapping("/uploadfile/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMetaFileInfo(@RequestParam String fileId){
    	if(GgitsCommonUtils.isNull(fileId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	try {
    		metaDataMngService.deleteMetaFileInfo(fileId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "파일이 삭제 되었습니다.");
    }
    
    /**
     * @Method Name : deleteMetaData
     * @작성일 : 2023. 9. 15.
     * @작성자 : KY.LEE
     * @Method 설명 : 메타데이터 삭제
     * @return
     */
    @PostMapping("/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMetaData(@RequestBody MetaTabInfo metaTabInfo){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(metaTabInfo);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("tblId", new ValidateChecker().setRequired())
    	.addRule("dsetId", new ValidateChecker().setRequired()).isValid();
    
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		metaDataMngService.deleteMetaData(metaTabInfo);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "메타데이터 삭제에 실패 하였습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "메타데이터가 삭제 되었습니다.");
    }
    
	
    /**
     * @Method Name : saveMetaTabInfo
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 메타데이터 저장
     * @return
     */
    @PostMapping("/save.ajax")
    public @ResponseBody CommonResponse<?> saveMetaTabInfo(@RequestPart MetaTabInfo metaTabInfo,
    		@RequestPart(required = false) MultipartFile[] uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(metaTabInfo);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("tblKoreanNm", new ValidateChecker().setRequired().setKorean("서비스 이름(KOR)은 한글만 입력이 가능 합니다."))
					.addRule("tblEngNm", new ValidateChecker().setRequired().setEnglish("서비스 이름(ENG)는 영어만 입력이 가능 합니다."))
					.addRule("rltinstId", new ValidateChecker().setRequired()).isValid();
//					.addRule("orgDataNm", new ValidateChecker().setRequired())
//					.addRule("dataType", new ValidateChecker().setRequired())
//					.addRule("dataKeyword", new ValidateChecker().setRequired());
//					.addRule("collDataType", new ValidateChecker().setRequired());
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		metaDataMngService.saveMetaData(metaTabInfo ,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "메타데이터 등록 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "메타데이터 등록 성공");
    }
    
    /**
     * @Method Name : saveAuthInfo
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 메타데이터 수정
     * @return
     */
    @PostMapping("/update.ajax")
    public @ResponseBody CommonResponse<?> updateMetaTabInfo(@RequestPart MetaTabInfo metaTabInfo,
    		@RequestPart(required = false) MultipartFile[] uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(metaTabInfo);
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("tblId", new ValidateChecker().setRequired())
    	.addRule("dsetId", new ValidateChecker().setRequired())
    	.addRule("tblKoreanNm", new ValidateChecker().setRequired().setKorean("서비스 이름(KOR)은 한글만 입력이 가능 합니다."))
    	.addRule("tblEngNm", new ValidateChecker().setRequired().setEnglish("서비스 이름(ENG)는 영어만 입력이 가능 합니다.")).isValid();
//    	.addRule("rltinstId", new ValidateChecker().setRequired())
//    	.addRule("orgDataNm", new ValidateChecker().setRequired())
//    	.addRule("dataKeyword", new ValidateChecker().setRequired())
//    	.addRule("collDataType", new ValidateChecker().setRequired());
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
   		metaDataMngService.updateMetaData(metaTabInfo,uploadFiles);

   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "메타데이터 수정에 성공 했습니다.");
    }

    /**
     * @Method Name : saveMetaColInfo
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 수정페이지 데이터 유형 추가
     * @return
     */
    @PostMapping("/saveMetaColInfo.ajax")
    public @ResponseBody CommonResponse<?> saveMetaColInfo(@RequestBody MetaColInfo metaColInfo){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(metaColInfo);
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("tblId", new ValidateChecker().setRequired())
    	.addRule("dsetId", new ValidateChecker().setRequired())
    	.addRule("rltinstId", new ValidateChecker().setRequired())
    	.addRule("dataType", new ValidateChecker().setRequired()).isValid();
    	
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	int colSqno = metaColInfoMapper.findColsqnoNextVal()+1;
		metaColInfo.setDataLen(metaColInfo.getDataType().length());
		metaColInfo.setColSqno(colSqno);
		
		metaColInfoMapper.saveMetaColInfo(metaColInfo);
		
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "메타데이터 수정에 성공 했습니다.",null,colSqno);
    }
}

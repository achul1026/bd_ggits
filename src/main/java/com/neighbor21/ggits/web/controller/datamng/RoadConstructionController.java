package com.neighbor21.ggits.web.controller.datamng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.FileMngTotInfo;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.FiledivCd;
import com.neighbor21.ggits.common.mapper.FileMngTotInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.datamng.DataMngService;

@Controller
@RequestMapping("/datamng/roadConstruction")
public class RoadConstructionController {
	
	@Autowired
	DataMngService dataMngService;
	
	@Autowired
	FileMngTotInfoMapper fileMngTotInfoMapper;

	/**
     * @Method Name : viewDataMngList
     * @작성일 : 2023. 11. 09.
     * @작성자 : KY.LEE
     * @Method 설명 : 자료관리 메뉴 -> 도로관리
     * @return
     */
	@GetMapping("/list.do")
    public String viewDataMngList(Model model ,FileMngTotInfo fileMngTotInfo){
		fileMngTotInfo.setFileDivCd(FiledivCd.ROAD.getCode());
		
		Long totalCount = fileMngTotInfoMapper.countFileMngTotListByFileDivCd(fileMngTotInfo);
		
		Paging paging = new Paging();
    	paging.setPageNo(fileMngTotInfo.getPage());
    	paging.setTotalCount(totalCount.intValue());
		
		model.addAttribute("fileMngTotInfoList", dataMngService.getFileMngTotInfoList(fileMngTotInfo));
		model.addAttribute("searchOption", fileMngTotInfo);
    	model.addAttribute("paging", paging);
		return "view/datamng/roadConstructionList";
	}

    /**
     * @Method Name : saveFileMngRoadCnstrcInfo
     * @작성일 : 2023. 11. 20.
     * @작성자 : KY.LEE
     * @Method 설명 : 도로관리 자료 등록
     * @return
     */
    @PostMapping("/save.ajax")
    public @ResponseBody CommonResponse<?> saveFileMngRoadCnstrcInfo(@RequestPart FileMngTotInfo fileMngTotInfo,
    		@RequestPart(required = true) MultipartFile uploadFile){
    	
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(fileMngTotInfo);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("fileNm", new ValidateChecker().setRequired())
    	.addRule("fileDivCd", new ValidateChecker().setRequired()).isValid();
    
    	if(!dtoValidatorResult.isSuccess() || uploadFile.isEmpty()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST ,"필수(값)항목이 누락 되었습니다.");
    	}
    	
    	try {
    		dataMngService.saveFileMng(fileMngTotInfo, uploadFile);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "자료 등록 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "자료 등록에 성공했습니다.");
    }
    
    
	/**
	 * @Method Name : fileDownload
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 자료관리 파일다운로드
	 * @return
	 */
	@GetMapping("/file/download.do")
	public void fileDownload(HttpServletResponse response,@RequestParam Map<String,Object> paramMap) throws IOException{
		String fileMngId = String.valueOf(paramMap.get("fileMngId"));
		
		if(GgitsCommonUtils.isNull(fileMngId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		
	    FileMngTotInfo fileMngTotInfo = fileMngTotInfoMapper.findOneByFileMngId(fileMngId);

	    File dFile = new File(fileMngTotInfo.getSavePath());

	    String encodedFileName = URLEncoder.encode(fileMngTotInfo.getFileOrgNm(), "UTF-8");
	    
	    response.setHeader("Content-Disposition",
	            "attachment; fileName=\"" + encodedFileName + "\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Content-Type", encodedFileName);
	    response.setHeader("Pragma", "no-cache;");
	    response.setHeader("Expires", "-1;");
	    response.setHeader("Content-Length", "" + fileMngTotInfo.getAtchFile().length);
	    
	    if (dFile.exists()) {
	      FileInputStream fis = null;
	      OutputStream out = null;
	      
	      try {
	        fis = new FileInputStream(dFile);
	        out = response.getOutputStream();
	        
	        int readCount = 0;
	        byte[] buffer = new byte[4096];
	        while ((readCount = fis.read(buffer)) != -1) {
	          out.write(buffer, 0, readCount);
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
	   * @Method Name : deleteFileMngTotInfo
	   * @작성일 : 2023. 11. 20.
	   * @작성자 : KY.LEE
	   * @Method 설명 : 서브 메뉴 삭제
	   * @return
	   */
	  @GetMapping("/{fileMngId}/delete.ajax")
	  public @ResponseBody CommonResponse<?> deleteFileMngTotInfo(@PathVariable("fileMngId") String fileMngId){
		 if(GgitsCommonUtils.isNull(fileMngId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "파일을 조회하는데 실패했습니다.");
		 }
		  
	  dataMngService.deleteFileMngTotInfo(fileMngId);
	  return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "자료가 삭제 되었습니다.");

	  }
	  
	    /**
	     * @Method Name : updateFileMngRoadCnstrcInfo
	     * @작성일 : 2023. 11. 20.
	     * @작성자 : KY.LEE
	     * @Method 설명 : 도로관리 자료 수정
	     * @return
	     */
	    @PostMapping("/update.ajax")
	    public @ResponseBody CommonResponse<?> updateFileMngRoadCnstrcInfo(@RequestPart FileMngTotInfo fileMngTotInfo,
	    		@RequestPart(required = false) MultipartFile uploadFile){
	    	
	    	// validation check
	    	ValidateBuilder dtoValidator = new ValidateBuilder(fileMngTotInfo);
	    	
	    	ValidateResult dtoValidatorResult = dtoValidator
	    	.addRule("fileNm", new ValidateChecker().setRequired())
	    	.addRule("fileDivCd", new ValidateChecker().setRequired()).isValid();
	    
	    	if(!dtoValidatorResult.isSuccess()) {
	    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
	    	}
	    	
    		dataMngService.updateFileMng(fileMngTotInfo, uploadFile);
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "자료 수정에 성공했습니다.");
	    }
}

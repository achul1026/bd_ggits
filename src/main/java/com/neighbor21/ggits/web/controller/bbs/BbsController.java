package com.neighbor21.ggits.web.controller.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import com.neighbor21.ggits.common.entity.MOpInqryAns;
import com.neighbor21.ggits.common.entity.MOpInqryBbs;
import com.neighbor21.ggits.common.entity.MOpNtcBbs;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpInqryAnsMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.bbs.BbsService;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Autowired
	BbsService bbsService;
	
	@Autowired 
	MOpInqryAnsMapper mOpInqryAnsMapper;
	
    /**
     * @Method Name : viewNoticeList
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 공지사항 목록
     * @return
     */
    @GetMapping("/notice/list.do")
    public String viewNoticeList(Model model , MOpNtcBbs  mOpNtcBbs){
    	
		Long totalCount = bbsService.getNoticeTotalCnt(mOpNtcBbs);
		
		Paging paging = new Paging();
		paging.setPageNo(mOpNtcBbs.getPage());
		paging.setTotalCount(totalCount.intValue());
		
		model.addAttribute("mOpNtcBbsList", bbsService.getNoticeList(mOpNtcBbs));
		model.addAttribute("paging", paging);
		model.addAttribute("searchOption", mOpNtcBbs);
		
    	return "view/bbs/noticeList";
    }

    /**
     * @Method Name : viewNoticeDetail
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 공지사항 > 상세보기
     * @return
     */
    @GetMapping("/notice/{ntcId}/detail.do")
    public String viewNoticeDetail(Model model, @PathVariable(name="ntcId") String ntcId){
    	//조회수 증가 
    	bbsService.updateNtcSrchCnt(ntcId);
    	
    	model.addAttribute("mOpNtcBbsDetail", bbsService.getNoticeDetail(ntcId));
    	return "view/bbs/noticeDetail";
    }

    /**
	 * @Method Name : noticeFileDownload
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 첨부파일 다운로드
	 * @return
	 */
	@GetMapping("/notice/file/download.do")
	public void noticeFileDownload(HttpServletResponse response,@RequestParam Map<String,Object> paramMap) throws Exception{
		String ntcId = String.valueOf(paramMap.get("ntcId"));
		if(GgitsCommonUtils.isNull(ntcId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}

		MOpNtcBbs dbMOpNtcBbs = bbsService.getNoticeDetail(ntcId);

		if(dbMOpNtcBbs == null) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}

		File dFile = new File(dbMOpNtcBbs.getAtchFilePath());

		String encodedFileName = new String(dbMOpNtcBbs.getAtchFileOrgNm().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		if(dFile.exists()) {
			response.setHeader("Content-Disposition", "attachment; fileName=\""+encodedFileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", dbMOpNtcBbs.getAtchFileOrgNm());
			response.setHeader("Content-Length", "" + dbMOpNtcBbs.getAtchFileSize());
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
				try {
					if(fis != null) {
						fis.close();
					}
					if(out != null) {
						out.close();
					}
				} catch(CommonException e) {
					throw new CommonException(ErrorCode.FILE_DOWNLOAD_FAIL);
				}
			}
		} else {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
	}
	
    /**
     * @Method Name : deleteNtcFile
     * @작성일 : 2024. 01. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 공지사항 파일 제거
     * @return
     */
    @PostMapping("/notice/file/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteNtcFile(@RequestParam String ntcId){
    	if(GgitsCommonUtils.isNull(ntcId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	try {
    		bbsService.deleteNtcFile(ntcId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "파일이 삭제 되었습니다.");
    }
    
    /**
     * @Method Name : viewNoticeSave
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 공지사항 > 공지사항 등록
     * @return
     */
    @GetMapping("/notice/save.do")
    public String viewNoticeSave(Model model){
		if(!"SUPER".equals(LoginSessionUtils.getMOpOperatorInfo().getOprtrGrd())) {
			throw new CommonException(ErrorCode.NO_PERMISSION);
		}
		
    	return "view/bbs/noticeSave";
    }
    
    /**
     * @Method Name : updateNotice
     * @작성일 : 2024. 01. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 공지사항 수정
     * @return
     */
    @PostMapping("/notice/update.ajax")
    public @ResponseBody CommonResponse<?> updateNotice(@RequestPart MOpNtcBbs mOpNtcBbs,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpNtcBbs);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    				.addRule("ntcId", new ValidateChecker().setRequired())
			        .addRule("ntcTitle", new ValidateChecker().setRequired())
					.addRule("ntcCnts", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.updateNotice(mOpNtcBbs,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "공지사항 수정 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "공지사항 수정 성공");
    }
    
    /**
     * @Method Name : saveNotice
     * @작성일 : 2024. 01. 25.
     * @작성자 : KY.LEE
     * @Method 설명 : 공지사항 등록
     * @return
     */
    @PostMapping("/notice/save.ajax")
    public @ResponseBody CommonResponse<?> saveNotice(@RequestPart MOpNtcBbs mOpNtcBbs,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpNtcBbs);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("ntcTitle", new ValidateChecker().setRequired())
					.addRule("ntcCnts", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.saveNotice(mOpNtcBbs,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "공지사항 등록 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "공지사항 등록 성공");
    }
    
    /**
     * @Method Name : deleteMOpNtcBbs
     * @작성일 : 2024. 1. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 공지사항 삭제
     * @return
     */
    @PostMapping("/notice/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMOpNtcBbs(@RequestParam String ntcId){
    	if(GgitsCommonUtils.isNull(ntcId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "공지사항 삭제에 실패했습니다.");
    	}
    	
    	try {
    		bbsService.deleteMOpNtcBbs(ntcId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "공지사항 삭제에 실패했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "공지사항을 삭제했습니다.");
    }
    
    /**
     * @Method Name : viewNoticeList
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 문의
     * @return
     */
    @GetMapping("/inquiry/list.do")
    public String viewInquiryList(Model model, MOpInqryBbs mOpInqryBbs){

		
		MOpOperator mOpOperator =  LoginSessionUtils.getMOpOperatorInfo();
		
		if(mOpOperator != null) {
			if(!"SUPER".equals(mOpOperator.getOprtrGrd())) {
				mOpInqryBbs.setOprtrId(LoginSessionUtils.getOprtrId());
			}
		}
		Long totalCount = bbsService.getInquiryTotalCnt(mOpInqryBbs);
		Paging paging = new Paging();
		paging.setPageNo(mOpInqryBbs.getPage());
		paging.setTotalCount(totalCount.intValue());
		
		model.addAttribute("mOpInqryBbsList", bbsService.getInquiryList(mOpInqryBbs));
		model.addAttribute("paging", paging);
		model.addAttribute("searchOption", mOpInqryBbs);
    	return "view/bbs/inquiryList";
    }
    
    /**
     * @Method Name : viewInquiryeSave
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 문의하기 > 문의 등록
     * @return
     */
    @GetMapping("/inquiry/save.do")
    public String viewInquiryeSave(Model model){
    	return "view/bbs/inquirySave";
    }
    
    /**
     * @Method Name : saveInquiry
     * @작성일 : 2024. 01. 25.
     * @작성자 : KY.LEE
     * @Method 설명 : 문의하기 등록
     * @return
     */
    @PostMapping("/inquiry/save.ajax")
    public @ResponseBody CommonResponse<?> saveInquiry(@RequestPart MOpInqryBbs mOpInqryBbs,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpInqryBbs);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("inqryTitle", new ValidateChecker().setRequired())
					.addRule("inqryCnts", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.saveInquiry(mOpInqryBbs,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "문의 등록 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "문의 등록 성공");
    }
    
    /**
     * @Method Name : viewInquiryDetail
     * @작성일 : 2024. 01. 24.
     * @작성자 : KY.LEE
     * @Method 설명 : 정보/문의 > 문의 > 상세보기
     * @return
     */
    @GetMapping("/inquiry/{inqryId}/detail.do")
    public String viewInquiryDetail(Model model, @PathVariable(name="inqryId") String inqryId){
    	//조회수 증가 
    	bbsService.updateInqrySrchCnt(inqryId);
    	model.addAttribute("mOpInqryBbsDetail", bbsService.getInquiryDetail(inqryId));
    	return "view/bbs/inquiryDetail";
    }
    
    /**
     * @Method Name : updateInquiry
     * @작성일 : 2024. 01. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 문의 수정
     * @return
     */
    @PostMapping("/inquiry/update.ajax")
    public @ResponseBody CommonResponse<?> updateInquiry(@RequestPart MOpInqryBbs mOpInqryBbs,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpInqryBbs);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    				.addRule("inqryId", new ValidateChecker().setRequired())
			        .addRule("inqryTitle", new ValidateChecker().setRequired())
					.addRule("inqryCnts", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.updateInquiry(mOpInqryBbs,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "문의 수정 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "문의 수정 성공");
    }
    
    /**
	 * @Method Name : inquiryfileDownload
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기(작성자) 첨부파일 다운로드
	 * @return
	 */
	@GetMapping("/inquiry/file/download.do")
	public void inquiryfileDownload(HttpServletResponse response,@RequestParam Map<String,Object> paramMap) throws Exception{
		String inqryId = String.valueOf(paramMap.get("inqryId"));
		if(GgitsCommonUtils.isNull(inqryId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		
		MOpInqryBbs dbMOpInqryBbs = bbsService.getInquiryDetail(inqryId);
		
		if(dbMOpInqryBbs == null) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
		
		File dFile = new File(dbMOpInqryBbs.getAtchFilePath());
		
//		String encodedFileName = URLEncoder.encode(dbMOpInqryBbs.getAtchFileOrgNm(), "UTF-8");
		String encodedFileName = new String(dbMOpInqryBbs.getAtchFileOrgNm().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		if(dFile.exists()) {
			response.setHeader("Content-Disposition", "attachment; fileName=\""+encodedFileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", dbMOpInqryBbs.getAtchFileOrgNm());
			response.setHeader("Content-Length", "" + dbMOpInqryBbs.getAtchFileSize());
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
				try {
					if(fis != null) {
						fis.close();
					}
					if(out != null) {
						out.close();
					}
				} catch(CommonException e) {
					throw new CommonException(ErrorCode.FILE_DOWNLOAD_FAIL);
				}
			}
		} else {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
	}
    
	
    /**
     * @Method Name : deleteInqryFile
     * @작성일 : 2024. 01. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 문의하기 파일 제거(문의자)
     * @return
     */
    @PostMapping("/inquiry/file/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteInqryFile(@RequestParam String inqryId){
    	if(GgitsCommonUtils.isNull(inqryId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	try {
    		bbsService.deleteInqryFile(inqryId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "파일이 삭제 되었습니다.");
    }
    
    /**
     * @Method Name : deleteMOpInqryBbs
     * @작성일 : 2024. 1. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 문의 삭제 (작성자)
     * @return
     */
    @PostMapping("/inquiry/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteMOpInqryBbs(@RequestParam String inqryId){
    	if(GgitsCommonUtils.isNull(inqryId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "문의 삭제에 실패했습니다.");
    	}
    	
    	try {
    		bbsService.deleteMOpInqryBbs(inqryId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "문의 삭제에 실패했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "문의를 삭제했습니다.");
    }
    
    /**
     * @Method Name : saveAnswer
     * @작성일 : 2024. 01. 25.
     * @작성자 : KY.LEE
     * @Method 설명 : 답변 등록
     * @return
     */
    @PostMapping("/inquiry/answer/save.ajax")
    public @ResponseBody CommonResponse<?> saveAnswer(@RequestPart MOpInqryAns mOpInqryAns,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpInqryAns);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("inqryId", new ValidateChecker().setRequired())
					.addRule("ansCnts", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.saveAnswer(mOpInqryAns,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "답변 등록 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "답변 등록 성공");
    }
    
    /**
     * @Method Name : deleteMOpInqryBbs
     * @작성일 : 2024. 1. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 답변 삭제 (작성자)
     * @return
     */
    @PostMapping("/inquiry/answer/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteAnswer(@RequestParam String inqryAnsId){
    	if(GgitsCommonUtils.isNull(inqryAnsId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "답변 삭제에 실패했습니다.");
    	}
    	
    	try {
    		bbsService.deleteMOpInqryAns(inqryAnsId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "답변 삭제에 실패했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "답변을 삭제했습니다.");
    }
    
    /**
     * @Method Name : deleteAnswerFile
     * @작성일 : 2024. 01. 30.
     * @작성자 : KY.LEE
     * @Method 설명 : 답변 파일 제거(문의자)
     * @return
     */
    @PostMapping("/inquiry/answer/file/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteAnswerFile(@RequestParam String inqryAnsId){
    	if(GgitsCommonUtils.isNull(inqryAnsId)) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	try {
    		bbsService.deleteAnswerFile(inqryAnsId);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "파일 삭제에 실패 했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "파일이 삭제 되었습니다.");
    }
    
    /**
	 * @Method Name : inquiryfileDownload
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기(작성자) 첨부파일 다운로드
	 * @return
	 */
	@GetMapping("/inquiry/answer/file/download.do")
	public void answerFileDownload(HttpServletResponse response,@RequestParam Map<String,Object> paramMap) throws Exception{
		String inqryAnsId = String.valueOf(paramMap.get("inqryAnsId"));
		if(GgitsCommonUtils.isNull(inqryAnsId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		
		MOpInqryAns dbMOpInqryAns = mOpInqryAnsMapper.findOneByInqryAnsId(inqryAnsId);
		
		if(dbMOpInqryAns == null) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
		
		File dFile = new File(dbMOpInqryAns.getAtchFilePath());
		
//		String encodedFileName = URLEncoder.encode(dbMOpNtcBbs.getAtchFileOrgNm(), "UTF-8");
		
		if(dFile.exists()) {
			response.setHeader("Content-Disposition", "attachment; fileName=\""+dbMOpInqryAns.getAtchFileOrgNm()+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", dbMOpInqryAns.getAtchFileOrgNm());
			response.setHeader("Content-Length", "" + dbMOpInqryAns.getAtchFileSize());
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
				try {
					if(fis != null) {
						fis.close();
					}
					if(out != null) {
						out.close();
					}
				} catch(CommonException e) {
					throw new CommonException(ErrorCode.FILE_DOWNLOAD_FAIL);
				}
			}
		} else {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		}
	}
	
    /**
     * @Method Name : updateAnswer
     * @작성일 : 2024. 01. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 답변 수정
     * @return
     */
    @PostMapping("/inquiry/answer/update.ajax")
    public @ResponseBody CommonResponse<?> updateAnswer(@RequestPart MOpInqryAns mOpInqryAns,
    		@RequestPart(required = false) MultipartFile uploadFiles){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpInqryAns);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    				.addRule("ansCnts", new ValidateChecker().setRequired())
			        .addRule("inqryId", new ValidateChecker().setRequired())
					.addRule("inqryAnsId", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
    	
    	try {
    		bbsService.updateAnswer(mOpInqryAns,uploadFiles);
    	} catch (CommonException e) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "공지사항 수정 중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "공지사항 수정 성공");
    }
    
}

package com.neighbor21.ggits.web.service.bbs;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.FileUploadComponent;
import com.neighbor21.ggits.common.dto.UploadFileInfo;
import com.neighbor21.ggits.common.entity.MOpInqryAns;
import com.neighbor21.ggits.common.entity.MOpInqryBbs;
import com.neighbor21.ggits.common.entity.MOpNtcBbs;
import com.neighbor21.ggits.common.mapper.MOpInqryAnsMapper;
import com.neighbor21.ggits.common.mapper.MOpInqryBbsMapper;
import com.neighbor21.ggits.common.mapper.MOpNtcBbsMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;

@Service
public class BbsService{
	
	@Autowired
	FileUploadComponent fileUploadComponent;
	
	@Autowired
	MOpNtcBbsMapper mOpNtcBbsMapper;
	
	@Autowired
	MOpInqryBbsMapper mOpInqryBbsMapper;
	
	@Autowired
	MOpInqryAnsMapper mOpInqryAnsMapper;
	
	
	/**
	 * @Method Name : saveNotice
	 * @작성일 : 2024. 01. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 저장
	 */	
	public void saveNotice(MOpNtcBbs mOpNtcBbs, MultipartFile uploadFiles) {
		String ntcId = GgitsCommonUtils.getUuid();
		Long oprtrId = LoginSessionUtils.getOprtrId();
		
		if(uploadFiles != null) {
			String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
			
			UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);

			mOpNtcBbs.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
			mOpNtcBbs.setAtchFileNm(uploadFileInfo.getFileNm());
			mOpNtcBbs.setAtchFilePath(uploadFileInfo.getFilePath());
			mOpNtcBbs.setAtchFileSize(uploadFileInfo.getFileSize());
			mOpNtcBbs.setAtchFileYn("Y");
		} else {
			mOpNtcBbs.setAtchFileOrgNm(null);
			mOpNtcBbs.setAtchFileNm(null);
			mOpNtcBbs.setAtchFilePath(null);
			mOpNtcBbs.setAtchFileSize(null);
			mOpNtcBbs.setAtchFileYn("N");
		}
		
		mOpNtcBbs.setOprtrId(oprtrId);
		mOpNtcBbs.setNtcSrchCnt(0L);
		mOpNtcBbs.setNtcId(ntcId);
		
		mOpNtcBbsMapper.saveMOpNtcBbs(mOpNtcBbs);
	}
	
	/**
	 * @Method Name : updateNotice
	 * @작성일 : 2024. 01. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 수정
	 */	
	public void updateNotice(MOpNtcBbs mOpNtcBbs, MultipartFile uploadFiles) {
		//기존 DB조회
		MOpNtcBbs dbMOpNtcBbs = mOpNtcBbsMapper.findOneByNtcId(mOpNtcBbs.getNtcId());
		
		//기존DB 파일 존재할때
		if("Y".equals(dbMOpNtcBbs.getAtchFileYn())) {
			//신규파일이있으면 기존 파일제거
			if(uploadFiles != null) {
				//파일제거로직
				fileUploadComponent.deleteUploadFile(dbMOpNtcBbs.getAtchFilePath());
				
				String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
				
				UploadFileInfo newUploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
	
				dbMOpNtcBbs.setAtchFileOrgNm(newUploadFileInfo.getOriginalFileNm());
				dbMOpNtcBbs.setAtchFileNm(newUploadFileInfo.getFileNm());
				dbMOpNtcBbs.setAtchFilePath(newUploadFileInfo.getFilePath());
				dbMOpNtcBbs.setAtchFileSize(newUploadFileInfo.getFileSize());
				dbMOpNtcBbs.setAtchFileYn("Y");
			}
		} else {
			//신규파일 업로드
			if(uploadFiles != null) {
				String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
				
				UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
	
				dbMOpNtcBbs.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
				dbMOpNtcBbs.setAtchFileNm(uploadFileInfo.getFileNm());
				dbMOpNtcBbs.setAtchFilePath(uploadFileInfo.getFilePath());
				dbMOpNtcBbs.setAtchFileSize(uploadFileInfo.getFileSize());
				dbMOpNtcBbs.setAtchFileYn("Y");
			}
		}
		dbMOpNtcBbs.setNtcTitle(mOpNtcBbs.getNtcTitle());
		dbMOpNtcBbs.setNtcCnts(mOpNtcBbs.getNtcCnts());
		
		mOpNtcBbsMapper.updateMOpNtcBbs(dbMOpNtcBbs);
	}
	
	
	
	
	/**
	 * @Method Name : getNoticeTotalCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 전체 카운트
	 */	
	public Long getNoticeTotalCnt(MOpNtcBbs mOpNtcBbs) {
		String startToday = "";
		String endToday = "";

		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getSearchType())) {
			if("all".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setNtcTitle(mOpNtcBbs.getSearchContent());
				mOpNtcBbs.setNtcCnts(mOpNtcBbs.getSearchContent());
			} else if("title".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setNtcTitle(mOpNtcBbs.getSearchContent());
			} else if("writer".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setOprtrNm(mOpNtcBbs.getSearchContent());
			}
		}
		
		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mOpNtcBbs.getStrDt(), "startDate");
			mOpNtcBbs.setStrDt(startToday);
		}
		
		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mOpNtcBbs.getEndDt(), "endDate");			
			mOpNtcBbs.setEndDt(endToday);
		}
		
		return mOpNtcBbsMapper.countBySearchOption(mOpNtcBbs);
	}

	/**
	 * @Method Name : findMOpNtcBbsBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 목록 조회 쿼리 
	 */	
	public List<MOpNtcBbs> getNoticeList(MOpNtcBbs mOpNtcBbs) {
		String startToday = "";
		String endToday = "";

		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getSearchType())) {
			if("all".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setNtcTitle(mOpNtcBbs.getSearchContent());
				mOpNtcBbs.setNtcCnts(mOpNtcBbs.getSearchContent());
			} else if("title".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setNtcTitle(mOpNtcBbs.getSearchContent());
			} else if("writer".equals(mOpNtcBbs.getSearchType())) {
				mOpNtcBbs.setOprtrNm(mOpNtcBbs.getSearchContent());
			}
		}
		
		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mOpNtcBbs.getStrDt(), "startDate");
			mOpNtcBbs.setStrDt(startToday);
		}
		
		if(!GgitsCommonUtils.isNull(mOpNtcBbs.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mOpNtcBbs.getEndDt(), "endDate");			
			mOpNtcBbs.setEndDt(endToday);
		}
		
		return mOpNtcBbsMapper.findMOpNtcBbsBySearchOption(mOpNtcBbs);
	}
	
	

	/**
	 * @Method Name : getNoticeDetail
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 상세 조회
	 */	
	public MOpNtcBbs getNoticeDetail(String ntcId) {
		return mOpNtcBbsMapper.findOneByNtcId(ntcId);
	}
	
	/**
	 * @Method Name : updateNtcSrchCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 조회수 증가
	 */	
	public void updateNtcSrchCnt(String ntcId) {
//		Long ntcSrchCnt= mOpNtcBbsMapper.findntcSrchCntByNtcId(ntcId);
		
		MOpNtcBbs mOpNtcBbs = new MOpNtcBbs();
		mOpNtcBbs.setNtcId(ntcId);
//		mOpNtcBbs.setNtcSrchCnt(ntcSrchCnt);
		
		mOpNtcBbsMapper.updateNtcSrchCnt(mOpNtcBbs);
	}
	
	/**
	 * @Method Name : deleteNtcFile
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 파일 제거
	 */	
	public void deleteNtcFile(String ntcId) {
		MOpNtcBbs dbMOpNtcBbs = mOpNtcBbsMapper.findOneByNtcId(ntcId);
		
		if(dbMOpNtcBbs != null) {
			try{
				fileUploadComponent.deleteUploadFile(dbMOpNtcBbs.getAtchFilePath());
			}catch(Exception ignored) {}
		
			dbMOpNtcBbs.setAtchFileYn("N");
			dbMOpNtcBbs.setAtchFileOrgNm(null);
			dbMOpNtcBbs.setAtchFileNm(null);
			dbMOpNtcBbs.setAtchFilePath(null);
			dbMOpNtcBbs.setAtchFileSize(null);

			mOpNtcBbsMapper.updateMOpNtcBbs(dbMOpNtcBbs);
		}
	}
	
	/**
	 * @Method Name : deleteMOpNtcBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 삭제
	 */	
	public void deleteMOpNtcBbs(String ntcId) {
		MOpNtcBbs dbMOpNtcBbs = mOpNtcBbsMapper.findOneByNtcId(ntcId);
		
		if(dbMOpNtcBbs != null) {
			if(!GgitsCommonUtils.isNull(dbMOpNtcBbs.getAtchFilePath())) {
				try{
					fileUploadComponent.deleteUploadFile(dbMOpNtcBbs.getAtchFilePath());
				}catch(Exception ignored) {}
			}
		}
		mOpNtcBbsMapper.deleteMOpNtcBbs(ntcId);
	}
	
	/**
	 * @Method Name : saveInquiry
	 * @작성일 : 2024. 01. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 등록
	 */	
	public void saveInquiry(MOpInqryBbs mOpInqryBbs, MultipartFile uploadFiles) {
		String inqryId = GgitsCommonUtils.getUuid();
		Long oprtrId = LoginSessionUtils.getOprtrId();
		
		if(uploadFiles != null) {
			String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
			
			UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);

			mOpInqryBbs.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
			mOpInqryBbs.setAtchFileNm(uploadFileInfo.getFileNm());
			mOpInqryBbs.setAtchFilePath(uploadFileInfo.getFilePath());
			mOpInqryBbs.setAtchFileSize(uploadFileInfo.getFileSize());
			mOpInqryBbs.setAtchFileYn("Y");
		} else {
			mOpInqryBbs.setAtchFileOrgNm(null);
			mOpInqryBbs.setAtchFileNm(null);
			mOpInqryBbs.setAtchFilePath(null);
			mOpInqryBbs.setAtchFileSize(null);
			mOpInqryBbs.setAtchFileYn("N");
		}
		
		mOpInqryBbs.setOprtrId(oprtrId);
		mOpInqryBbs.setInqrySrchCnt(0L);
		mOpInqryBbs.setInqryId(inqryId);
		
		mOpInqryBbsMapper.saveMOpInqryBbs(mOpInqryBbs);
	}
	
	
	/**
	 * @Method Name : getInquiryTotalCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기 전체 카운트
	 */	
	public Long getInquiryTotalCnt(MOpInqryBbs mOpInqryBbs) {
		String startToday = "";
		String endToday = "";

		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getSearchType())) {
			if("title".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setInqryTitle(mOpInqryBbs.getSearchContent());
			} else if("content".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setInqryCnts(mOpInqryBbs.getSearchContent());
			} else if("writer".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setOprtrNm(mOpInqryBbs.getSearchContent());
			}
		}
		
		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mOpInqryBbs.getStrDt(), "startDate");
			mOpInqryBbs.setStrDt(startToday);
		}
		
		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mOpInqryBbs.getEndDt(), "endDate");			
			mOpInqryBbs.setEndDt(endToday);
		}
		return mOpInqryBbsMapper.countBySearchOption(mOpInqryBbs);
	}

	/**
	 * @Method Name : findMOpNtcBbsBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 목록 조회 쿼리 
	 */	
	public List<MOpInqryBbs> getInquiryList(MOpInqryBbs mOpInqryBbs) {
		String startToday = "";
		String endToday = "";

		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getSearchType())) {
			if("title".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setInqryTitle(mOpInqryBbs.getSearchContent());
			} else if("content".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setInqryCnts(mOpInqryBbs.getSearchContent());
			} else if("writer".equals(mOpInqryBbs.getSearchType())) {
				mOpInqryBbs.setOprtrNm(mOpInqryBbs.getSearchContent());
			}
		}
		
		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mOpInqryBbs.getStrDt(), "startDate");
			mOpInqryBbs.setStrDt(startToday);
		}
		
		if(!GgitsCommonUtils.isNull(mOpInqryBbs.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mOpInqryBbs.getEndDt(), "endDate");			
			mOpInqryBbs.setEndDt(endToday);
		}
		
		return mOpInqryBbsMapper.findMOpInqryBbsBySearchOption(mOpInqryBbs);
	}
	
	/**
	 * @Method Name : updateInqrySrchCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 조회수 증가
	 */	
	public void updateInqrySrchCnt(String inqryId) {
		MOpInqryBbs mOpInqryBbs = new MOpInqryBbs();
		mOpInqryBbs.setInqryId(inqryId);
		mOpInqryBbsMapper.updateInqrySrchCnt(mOpInqryBbs);
	}

	/**
	 * @Method Name : getNoticeDetail
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 상세 조회
	 */	
	public MOpInqryBbs getInquiryDetail(String inqryId) {
		MOpInqryBbs dbMOpInqryBbs =  mOpInqryBbsMapper.findOneByInqryId(inqryId);
		
		if("Y".equals(dbMOpInqryBbs.getInqryAnsYn())) {
			List<MOpInqryAns> mOpInqryAnsList = mOpInqryAnsMapper.findAllByInqryId(inqryId);
			
			dbMOpInqryBbs.setmOpInqryAnsList(mOpInqryAnsList);
		}
		return dbMOpInqryBbs;
	}

	
	/**
	 * @Method Name : updateInquiry
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의사항 수정 (작성자)
	 */	
	public void updateInquiry(MOpInqryBbs mOpInqryBbs, MultipartFile uploadFiles) {
		
		//기존 DB조회
		MOpInqryBbs dbMOpInqryBbs = mOpInqryBbsMapper.findOneByInqryId(mOpInqryBbs.getInqryId());
		
		//기존DB 파일 존재할때
		if("Y".equals(dbMOpInqryBbs.getAtchFileYn())) {
			//신규파일이있으면 기존 파일제거
			if(uploadFiles != null) {
				//기존 파일제거로직
				fileUploadComponent.deleteUploadFile(dbMOpInqryBbs.getAtchFilePath());
				
				String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
				
				UploadFileInfo newUploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
	
				dbMOpInqryBbs.setAtchFileOrgNm(newUploadFileInfo.getOriginalFileNm());
				dbMOpInqryBbs.setAtchFileNm(newUploadFileInfo.getFileNm());
				dbMOpInqryBbs.setAtchFilePath(newUploadFileInfo.getFilePath());
				dbMOpInqryBbs.setAtchFileSize(newUploadFileInfo.getFileSize());
				dbMOpInqryBbs.setAtchFileYn("Y");
			}
		} else {
			//신규파일 업로드
			if(uploadFiles != null) {
				String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
				
				UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
	
				dbMOpInqryBbs.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
				dbMOpInqryBbs.setAtchFileNm(uploadFileInfo.getFileNm());
				dbMOpInqryBbs.setAtchFilePath(uploadFileInfo.getFilePath());
				dbMOpInqryBbs.setAtchFileSize(uploadFileInfo.getFileSize());
				dbMOpInqryBbs.setAtchFileYn("Y");
			}
		}
		dbMOpInqryBbs.setInqryTitle(mOpInqryBbs.getInqryTitle());
		dbMOpInqryBbs.setInqryCnts(mOpInqryBbs.getInqryCnts());
		
		mOpInqryBbsMapper.updateMOpInqryBbs(dbMOpInqryBbs);
	}

	
	/**
	 * @Method Name : deleteInqryFile
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 파일 제거 (작성자)
	 */	
	public void deleteInqryFile(String inqryId) {
		//기존 DB조회
		MOpInqryBbs dbMOpInqryBbs = mOpInqryBbsMapper.findOneByInqryId(inqryId);
		
		if(dbMOpInqryBbs != null) {
			//파일 제거 로직
			try {
				fileUploadComponent.deleteUploadFile(dbMOpInqryBbs.getAtchFilePath());
			}catch(Exception ignored) {}
		
			dbMOpInqryBbs.setAtchFileYn("N");
			dbMOpInqryBbs.setAtchFileOrgNm(null);
			dbMOpInqryBbs.setAtchFileNm(null);
			dbMOpInqryBbs.setAtchFilePath(null);
			dbMOpInqryBbs.setAtchFileSize(null);

			mOpInqryBbsMapper.updateMOpInqryBbs(dbMOpInqryBbs);
		}
	}
	
	/**
	 * @Method Name : deleteMOpNtcBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 삭제
	 */	
	public void deleteMOpInqryBbs(String inqryId) {
		//기존 DB조회
		MOpInqryBbs dbMOpInqryBbs = mOpInqryBbsMapper.findOneByInqryId(inqryId);
		
		if(dbMOpInqryBbs != null) {
			if(!GgitsCommonUtils.isNull(dbMOpInqryBbs.getAtchFilePath())) {
				fileUploadComponent.deleteUploadFile(dbMOpInqryBbs.getAtchFilePath());
			}
		}
		
		List<MOpInqryAns> mOpInqryAnsList = mOpInqryAnsMapper.findAllByInqryId(inqryId);
		
		//답변목록 파일 전부 제거
		if(!mOpInqryAnsList.isEmpty()) {
			for(MOpInqryAns dbMOpInqryAns : mOpInqryAnsList) {
				if(!GgitsCommonUtils.isNull(dbMOpInqryAns.getAtchFilePath())) {
					fileUploadComponent.deleteUploadFile(dbMOpInqryAns.getAtchFilePath());
				}
			}
		}

		//답변 제거
		mOpInqryAnsMapper.deleteMOpInqryAnsByInqryId(inqryId);
		
		//본문 제거
		mOpInqryBbsMapper.deleteMOpInqryBbs(dbMOpInqryBbs);

	}

	/**
	 * @Method Name : saveAnswer
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 답변 등록 (운영자)
	 */	
	public void saveAnswer(MOpInqryAns mOpInqryAns, MultipartFile uploadFiles) {
		String inqryAnsId = GgitsCommonUtils.getUuid();
		Long oprtrId = LoginSessionUtils.getOprtrId();
		
		//기존 DB조회
		MOpInqryBbs dbMOpInqryBbs = mOpInqryBbsMapper.findOneByInqryId(mOpInqryAns.getInqryId());
		if(dbMOpInqryBbs != null) {
			Timestamp now = new Timestamp(System.currentTimeMillis());

			dbMOpInqryBbs.setInqryAnsYn("Y");
			dbMOpInqryBbs.setInqryAnsDt(now);
			
			mOpInqryBbsMapper.updateInqryAnsYnAndInqryAnsDt(dbMOpInqryBbs);
			
			if(uploadFiles != null) {
				String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
				
				UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
				
				mOpInqryAns.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
				mOpInqryAns.setAtchFileNm(uploadFileInfo.getFileNm());
				mOpInqryAns.setAtchFilePath(uploadFileInfo.getFilePath());
				mOpInqryAns.setAtchFileSize(uploadFileInfo.getFileSize());
				mOpInqryAns.setAtchFileYn("Y");
			} else {
				mOpInqryAns.setAtchFileOrgNm(null);
				mOpInqryAns.setAtchFileNm(null);
				mOpInqryAns.setAtchFilePath(null);
				mOpInqryAns.setAtchFileSize(null);
				mOpInqryAns.setAtchFileYn("N");
			}
			mOpInqryAns.setOprtrId(oprtrId);
			mOpInqryAns.setInqryAnsId(inqryAnsId);
			
			mOpInqryAnsMapper.saveMOpInqryAns(mOpInqryAns);
		}
		
	}

	

	/**
	 * @Method Name : deleteMOpInqryAns
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 삭제
	 */	
	public void deleteMOpInqryAns(String inqryAnsId) {
		//기존 DB조회
		MOpInqryAns dbMOpInqryAns = mOpInqryAnsMapper.findOneByInqryAnsId(inqryAnsId);
		
		if(dbMOpInqryAns != null) {
			if(!GgitsCommonUtils.isNull(dbMOpInqryAns.getAtchFilePath())) {
				fileUploadComponent.deleteUploadFile(dbMOpInqryAns.getAtchFilePath());
			}
		}
		
		//답변 제거
		mOpInqryAnsMapper.deleteMOpInqryAnsByInqryAnsId(inqryAnsId);

		List<MOpInqryAns> mOpInqryAnsList = mOpInqryAnsMapper.findAllByInqryId(dbMOpInqryAns.getInqryId());
		
		if(mOpInqryAnsList.isEmpty()) {
			//답변여부 N처리
			MOpInqryBbs dbMOpInqryBbs = mOpInqryBbsMapper.findOneByInqryId(dbMOpInqryAns.getInqryId());
			dbMOpInqryBbs.setInqryAnsYn("N");
			dbMOpInqryBbs.setInqryUpdtDt(null);
			mOpInqryBbsMapper.updateInqryAnsYnAndInqryAnsDt(dbMOpInqryBbs);
		}
	}

	/**
	 * @Method Name : deleteMOpInqryAns
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 파일 제거
	 */	
	public void deleteAnswerFile(String inqryAnsId) {
		//기존 DB조회
		MOpInqryAns dbMOpInqryAns = mOpInqryAnsMapper.findOneByInqryAnsId(inqryAnsId);
		
		if(dbMOpInqryAns != null) {
			//파일 제거 로직
			fileUploadComponent.deleteUploadFile(dbMOpInqryAns.getAtchFilePath());
		
			dbMOpInqryAns.setAtchFileYn("N");
			dbMOpInqryAns.setAtchFileOrgNm(null);
			dbMOpInqryAns.setAtchFileNm(null);
			dbMOpInqryAns.setAtchFilePath(null);
			dbMOpInqryAns.setAtchFileSize(null);

			mOpInqryAnsMapper.updateMOpInqryAns(dbMOpInqryAns);
		}
	}

	
	/**
	 * @Method Name : updateAnswer
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의사항 수정 (작성자)
	 */	
	public void updateAnswer(MOpInqryAns mOpInqryAns, MultipartFile uploadFiles) {
		//기존 DB조회
		MOpInqryAns dbMOpInqryAns = mOpInqryAnsMapper.findOneByInqryAnsId(mOpInqryAns.getInqryAnsId());
			//기존DB 파일 존재할때
			if("Y".equals(dbMOpInqryAns.getAtchFileYn())) {
				//신규파일이있으면 기존 파일제거
				if(uploadFiles != null) {
					//파일제거로직
					fileUploadComponent.deleteUploadFile(dbMOpInqryAns.getAtchFilePath());
					
					String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
					
					UploadFileInfo newUploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
		
					dbMOpInqryAns.setAtchFileOrgNm(newUploadFileInfo.getOriginalFileNm());
					dbMOpInqryAns.setAtchFileNm(newUploadFileInfo.getFileNm());
					dbMOpInqryAns.setAtchFilePath(newUploadFileInfo.getFilePath());
					dbMOpInqryAns.setAtchFileSize(newUploadFileInfo.getFileSize());
					dbMOpInqryAns.setAtchFileYn("Y");
				}
			} else {
				//신규파일 업로드
				if(uploadFiles != null) {
					String[] extArr = {"zip","hwp","pdf","docx","doc","xlsx","xls","ppt","pptx","hwpx","xml"};
					
					UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFiles, extArr);
		
					dbMOpInqryAns.setAtchFileOrgNm(uploadFileInfo.getOriginalFileNm());
					dbMOpInqryAns.setAtchFileNm(uploadFileInfo.getFileNm());
					dbMOpInqryAns.setAtchFilePath(uploadFileInfo.getFilePath());
					dbMOpInqryAns.setAtchFileSize(uploadFileInfo.getFileSize());
					dbMOpInqryAns.setAtchFileYn("Y");
				}
			}
		dbMOpInqryAns.setAnsCnts(mOpInqryAns.getAnsCnts());
		
		mOpInqryAnsMapper.updateMOpInqryAns(dbMOpInqryAns);
	}
}

package com.neighbor21.ggits.web.service.datamng;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.FileUploadComponent;
import com.neighbor21.ggits.common.dto.UploadFileInfo;
import com.neighbor21.ggits.common.entity.FileMngTotInfo;
import com.neighbor21.ggits.common.entity.MOpAuthority;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.enums.FiledivCd;
import com.neighbor21.ggits.common.mapper.FileMngTotInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class DataMngService{
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;
	
	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	FileMngTotInfoMapper fileMngTotInfoMapper;
	
	@Autowired
	FileUploadComponent fileUploadComponent;
	
	public void saveFileMng(FileMngTotInfo fileMngTotInfo, MultipartFile uploadFile) {
		String fileMngId = GgitsCommonUtils.getUuid();
		String nowStr = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
		String fileDivCd = FiledivCd.getCodeForName(fileMngTotInfo.getFileDivCd());
		
		MOpGrpInfo schMOpGrpInfo = new MOpGrpInfo();
		schMOpGrpInfo.setGrpId(LoginSessionUtils.getGrpId());
		
		MOpGrpInfo mOpGrpInfo =  mOpGrpInfoMapper.findOneGroupDetailByGrpId(schMOpGrpInfo);
		
		MOpAuthority mOpAuthority = mOpAuthorityMapper.findOneByAuthId(mOpGrpInfo.getAuthId());
		if("AUC000".equals(mOpAuthority.getAuthCd()) || "SUPER".equals(LoginSessionUtils.getMOpOperatorInfo().getOprtrGrd())) {
			String[] extArr = {"xlsx","xls","hwp","pdf"};
			UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFile,extArr);
			
			fileMngTotInfo.setFileMngId(fileMngId);
			fileMngTotInfo.setFileOrgNm(uploadFileInfo.getOriginalFileNm());
			fileMngTotInfo.setSavePath(uploadFileInfo.getFilePath());
			fileMngTotInfo.setRegistYmd(nowStr);
			fileMngTotInfo.setFileDivCd(fileDivCd);
			fileMngTotInfo.setAtchFile(uploadFileInfo.getAtchFile());
			
			fileMngTotInfo.setSaveSize(uploadFileInfo.getFileSize());
			
			fileMngTotInfoMapper.saveFileMngTotInfo(fileMngTotInfo);
		} else {
			throw new CommonException(ErrorCode.NO_PERMISSION);
		}
		
	}
	
	public List<FileMngTotInfo> getFileMngTotInfoList(FileMngTotInfo fileMngTotInfo){
		return fileMngTotInfoMapper.findAllFileMngTotListByFileDivCd(fileMngTotInfo);
	}
	
	public void deleteFileMngTotInfo(String fileMngId) {
		//권한 체크 로직
		MOpOperator mOpOperator = LoginSessionUtils.getMOpOperatorInfo();
		
		MOpGrpInfo schMOpGrpInfo = new MOpGrpInfo();
		schMOpGrpInfo.setGrpId(mOpOperator.getGrpId());
		
		MOpGrpInfo mOpGrpInfo =  mOpGrpInfoMapper.findOneGroupDetailByGrpId(schMOpGrpInfo);
		
		MOpAuthority mOpAuthority = mOpAuthorityMapper.findOneByAuthId(mOpGrpInfo.getAuthId());
		if("AUC000".equals(mOpAuthority.getAuthCd()) || "SUPER".equals(mOpOperator.getOprtrGrd())) {
			FileMngTotInfo fileMngTotInfo = fileMngTotInfoMapper.findOneByFileMngId(fileMngId);
			
			//파일제거로직
			fileUploadComponent.deleteUploadFile(fileMngTotInfo.getSavePath());
			
			fileMngTotInfoMapper.deleteFileMngTotInfo(fileMngId);
		} else {
			throw new CommonException(ErrorCode.NO_PERMISSION);
		}
	}
	
	public void updateFileMng(FileMngTotInfo fileMngTotInfo, MultipartFile uploadFile) {
		//권한 체크 로직
		MOpOperator mOpOperator = LoginSessionUtils.getMOpOperatorInfo();
		
		MOpGrpInfo schMOpGrpInfo = new MOpGrpInfo();
		schMOpGrpInfo.setGrpId(mOpOperator.getGrpId());
		
		MOpGrpInfo mOpGrpInfo =  mOpGrpInfoMapper.findOneGroupDetailByGrpId(schMOpGrpInfo);
		
		MOpAuthority mOpAuthority = mOpAuthorityMapper.findOneByAuthId(mOpGrpInfo.getAuthId());
		if("AUC000".equals(mOpAuthority.getAuthCd()) || "SUPER".equals(mOpOperator.getOprtrGrd())) {
			FileMngTotInfo dbFileMngTotInfo = fileMngTotInfoMapper.findOneByFileMngId(fileMngTotInfo.getFileMngId());
			
			//새 업로드 파일이 있을때
			if(uploadFile != null && !uploadFile.isEmpty()) {
				//신규파일 업로드
				String[] extArr = {"xlsx","xls","hwp","pdf"};
				
				UploadFileInfo uploadFileInfo = fileUploadComponent.uploadFileToUploadFileInfoChkExtension(uploadFile,extArr);
				
				//기존 파일 제거로직
				fileUploadComponent.deleteUploadFile(dbFileMngTotInfo.getSavePath());
	
				dbFileMngTotInfo.setFileOrgNm(uploadFileInfo.getOriginalFileNm());
				dbFileMngTotInfo.setSavePath(uploadFileInfo.getFilePath());
				dbFileMngTotInfo.setAtchFile(uploadFileInfo.getAtchFile());
	
				dbFileMngTotInfo.setSaveSize(uploadFileInfo.getFileSize());
			}
			dbFileMngTotInfo.setFileNm(fileMngTotInfo.getFileNm());
			
			fileMngTotInfoMapper.updateFileMngTotInfo(dbFileMngTotInfo);
		} else {
			throw new CommonException(ErrorCode.NO_PERMISSION);
		}
	}
}

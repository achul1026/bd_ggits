package com.neighbor21.ggits.web.service.metadatamng;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.component.FileUploadComponent;
import com.neighbor21.ggits.common.dto.UploadFileInfo;
import com.neighbor21.ggits.common.entity.ClschmInfo;
import com.neighbor21.ggits.common.entity.DsetInfo;
import com.neighbor21.ggits.common.entity.MetaColInfo;
import com.neighbor21.ggits.common.entity.MetaFileInfo;
import com.neighbor21.ggits.common.entity.MetaTabInfo;
import com.neighbor21.ggits.common.mapper.ClschmInfoMapper;
import com.neighbor21.ggits.common.mapper.DsetInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MetaColInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaDbInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaFileInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaInfsysInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaTabInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class MetaDataMngService{
	
	@Autowired
	FileUploadComponent fileUploadComponent;

	@Autowired
	MetaTabInfoMapper metaTabInfoMapper;
	
	@Autowired
	MetaInfsysInfoMapper metaInfsysInfoMapper;
	
	@Autowired
	MetaFileInfoMapper metaFileInfoMapper; 
	
	@Autowired
	MetaDbInfoMapper metaDbInfoMapper;
	
	@Autowired
	MetaColInfoMapper metaColInfoMapper; 
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	ClschmInfoMapper clschmInfoMapper;
	
	@Autowired
	DsetInfoMapper dsetInfoMapper;
	
	public List<MetaTabInfo> getMetaDataList(MetaTabInfo metaTabInfo){
		//검색 조건이 있을때
		if(metaTabInfo.getSearchType() != null && !"".equals(metaTabInfo.getSearchType())) {
			switch(metaTabInfo.getSearchType()) {
			case "title":
				metaTabInfo.setTblKoreanNm(metaTabInfo.getSearchContent());
				metaTabInfo.setTblEngNm(metaTabInfo.getSearchContent());
				break;
			case "writer":
				metaTabInfo.setTblOwnrNm(metaTabInfo.getSearchContent());
				break;
			case "keyword":
				metaTabInfo.setDataKeyword(metaTabInfo.getSearchContent());
				break;
			default:
				metaTabInfo.setTblKoreanNm(metaTabInfo.getSearchContent());
				metaTabInfo.setTblEngNm(metaTabInfo.getSearchContent());
				metaTabInfo.setTblOwnrNm(metaTabInfo.getSearchContent());
				metaTabInfo.setDataKeyword(metaTabInfo.getSearchContent());
				break;
			}
		} else {
			//검색어만 있을때
			if(metaTabInfo.getSearchContent() != null && !"".equals(metaTabInfo.getSearchContent())) {
				metaTabInfo.setTblKoreanNm(metaTabInfo.getSearchContent());
				metaTabInfo.setTblEngNm(metaTabInfo.getSearchContent());
				metaTabInfo.setTblOwnrNm(metaTabInfo.getSearchContent());
				metaTabInfo.setDataKeyword(metaTabInfo.getSearchContent());
			}
		}
		
		return metaTabInfoMapper.findAllMetadataList(metaTabInfo);
	}
	
	
	/**
	 * @Method Name : saveMetaData
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 테이블 저장
	 */	
	public String saveMetaData(MetaTabInfo metaTabInfo , MultipartFile[] uploadFiles) {
		String rltinstId = metaTabInfo.getRltinstId();
		String tblId = GgitsCommonUtils.getUuid();
		String dsetId = GgitsCommonUtils.getUuid();
		String clschmId = null;
		
		DsetInfo dsetInfo = new DsetInfo();
		dsetInfo.setDsetId(dsetId);
		dsetInfo.setSrvcNm("T_META_TAB_INFO");
		dsetInfo.setOrgDataNm("meta_tab_info");
		dsetInfo.setRltinstId(rltinstId);
		
		if(!GgitsCommonUtils.isNull(metaTabInfo.getClschmId())) {
			String clschmNm = metaTabInfo.getClschmId();
			
		    String dbClschmId = clschmInfoMapper.findClschmIdByClschmNm(clschmNm);
			if(dbClschmId != null && !"".equals(dbClschmId)) {
				clschmId = dbClschmId;
			} else {
				clschmId = GgitsCommonUtils.getUuid();
				ClschmInfo clschmInfo = new ClschmInfo();
				clschmInfo.setClschmNm(clschmNm);
				clschmInfo.setClschmId(clschmId);
				
				//신규 
				clschmInfoMapper.saveClschmInfo(clschmInfo);
			}
		}
		
		//데이터셋에 set
		dsetInfo.setClschmId(clschmId);
		
//		MetaDbInfo dbMetaDbInfo = metaDbInfoMapper.findMetaDbInfoByrltinstId(rltinstId);

		if(uploadFiles != null && uploadFiles.length > 0) {
			String[] extArr = {"json","csv","hwp","pdf","txt","xml","zip"};
			
			List<UploadFileInfo> uploadFileInfoList = fileUploadComponent.uploadFilesToUploadFileInfoListChkExtension(uploadFiles, extArr);
			
			for(UploadFileInfo uploadFileInfo : uploadFileInfoList) {
				//파일관련업로드
				MetaFileInfo metaFileInfo = new MetaFileInfo();
				metaFileInfo.setFileId(GgitsCommonUtils.getUuid());
				metaFileInfo.setDsetId(dsetId);
				//아직컬럼x
//				metaFileInfo.setTblId(tblId);
				metaFileInfo.setSaveStorgType(tblId);
				metaFileInfo.setOrgFileNm(uploadFileInfo.getOriginalFileNm());
				metaFileInfo.setFileNm(uploadFileInfo.getFileNm());
				metaFileInfo.setSaveLc(uploadFileInfo.getFilePath());
				metaFileInfo.setSaveSize(uploadFileInfo.getFileSize());
				metaFileInfoMapper.saveMetaFileInfo(metaFileInfo);
			}
		}
		metaTabInfo.setTblId(tblId);
		metaTabInfo.setDsetId(dsetId);
		metaTabInfo.setTblOwnrNm(LoginSessionUtils.getOprtrNm());
		//추구 컬럼 변경 수집유형 컬럼
//		metaTabInfo.setOpngDataListNm(metaTabInfo.getCollDataType());
		
		//null 비참조 numberic
		metaTabInfo.setPrsrvPeriod(0);
		metaTabInfo.setTblSize(0);
		metaTabInfo.setOccurCycl(0);
		metaTabInfo.setDataSaveCycl(0);
		metaTabInfo.setClschmId(clschmId);
		
		metaTabInfoMapper.saveMetaTabInfo(metaTabInfo);
		dsetInfoMapper.saveDsetInfo(dsetInfo);
		if(metaTabInfo.getDataType().contains(",")) {
			String[] dataTypeArr = metaTabInfo.getDataType().split(",");
			for(int i = 0; i < dataTypeArr.length; i++) {
				MetaColInfo metaColInfo = new MetaColInfo();
				metaColInfo.setTblId(tblId);
				metaColInfo.setDsetId(dsetId);
				metaColInfo.setRltinstId(rltinstId);
				metaColInfo.setDataType(dataTypeArr[i]);
				metaColInfo.setDataLen(dataTypeArr[i].length());
				metaColInfo.setColSqno(metaColInfoMapper.findColsqnoNextVal()+1);
				metaColInfoMapper.saveMetaColInfo(metaColInfo);
			}
		} else {
			MetaColInfo metaColInfo = new MetaColInfo();
			metaColInfo.setTblId(tblId);
			metaColInfo.setDsetId(dsetId);
			metaColInfo.setRltinstId(rltinstId);
			metaColInfo.setDataType(metaTabInfo.getDataType());
			metaColInfo.setDataLen(metaTabInfo.getDataType().length());
			metaColInfo.setColSqno(metaColInfoMapper.findColsqnoNextVal()+1);
			metaColInfoMapper.saveMetaColInfo(metaColInfo);
		}
		
		return tblId; 
	}
	/**
	 * @Method Name : updateMetaData
	 * @작성일 : 2023. 9. 12.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 테이블 수정
	 */	
	public void updateMetaData(MetaTabInfo metaTabInfo , MultipartFile[] uploadFiles) {
//		String rltinstId = metaTabInfo.getRltinstId();

//		MetaDbInfo dbMetaDbInfo = metaDbInfoMapper.findMetaDbInfoByrltinstId(rltinstId);
		String clschmId = metaTabInfo.getClschmId();
		String clschmNm = metaTabInfo.getClschmNm();
		
		//분류체계 관련로직
		DsetInfo dsetInfo = new DsetInfo();
		dsetInfo.setDsetId(metaTabInfo.getDsetId());
		DsetInfo dbDsetInfo = new DsetInfo();
		dbDsetInfo = dsetInfoMapper.findOneDsetInfo(dsetInfo);
		
		String dbClschmId = clschmInfoMapper.findClschmIdByClschmNm(clschmNm);

		if(!GgitsCommonUtils.isNull(dbClschmId)) {
			//분류체계 기존과 같거나 DB에 존재할때
			clschmId = dbClschmId;
		} else {
			clschmId = GgitsCommonUtils.getUuid();
			ClschmInfo clschmInfo = new ClschmInfo();
			clschmInfo.setClschmNm(clschmNm);
			clschmInfo.setClschmId(clschmId);
			
			//신규 
			clschmInfoMapper.saveClschmInfo(clschmInfo);
		}
		dbDsetInfo.setClschmId(clschmId);

		dbDsetInfo.setRltinstId(metaTabInfo.getRltinstId());

		
		if(uploadFiles != null && uploadFiles.length > 0) {
			String[] extArr = {"json","csv","hwp","pdf","txt","xml","zip"};
			
			List<UploadFileInfo> uploadFileInfoList = fileUploadComponent.uploadFilesToUploadFileInfoListChkExtension(uploadFiles,extArr);
			
			for(UploadFileInfo uploadFileInfo : uploadFileInfoList) {
				//파일관련업로드
				MetaFileInfo metaFileInfo = new MetaFileInfo();
				metaFileInfo.setFileId(GgitsCommonUtils.getUuid());
				metaFileInfo.setDsetId(metaTabInfo.getDsetId());
				//아직 컬럼x
//				metaFileInfo.setTblId(metaTabInfo.getTblId());
				metaFileInfo.setSaveStorgType(metaTabInfo.getTblId());
				metaFileInfo.setOrgFileNm(uploadFileInfo.getOriginalFileNm());
				metaFileInfo.setFileNm(uploadFileInfo.getFileNm());
				metaFileInfo.setSaveLc(uploadFileInfo.getFilePath());
				metaFileInfo.setSaveSize(uploadFileInfo.getFileSize());
				metaFileInfoMapper.saveMetaFileInfo(metaFileInfo);
			}
		}

		metaTabInfo.setTblOwnrNm(LoginSessionUtils.getOprtrNm());
		//추구 컬럼 변경 수집유형 컬럼
		metaTabInfo.setOpngDataListNm(metaTabInfo.getCollDataType());
		metaTabInfo.setClschmId(clschmId);
		metaTabInfoMapper.updateMetaTabInfo(metaTabInfo);
		dsetInfoMapper.updateDsetInfo(dbDsetInfo);
		
		//유관기관 제외 별도관리
//		MetaColInfo metaColInfo = new MetaColInfo();
//		metaColInfo.setTblId(metaTabInfo.getTblId());
//		metaColInfo.setDsetId(dbMetaDbInfo.getDbId());
//		metaColInfo.setDsetId(dbMetaDbInfo.getRltinstId());
//		metaColInfoMapper.updateMetaColInfo(dbMetaColInfo);
	}
	
	/**
	 * @Method Name : getDataKeywordList
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 전체 데이터 키워드 조회
	 */	
	public List<String> getDataKeywordList(List<MetaTabInfo> metaTabInfoList) {
		List<String> resultList = new ArrayList<String>();	
		for(MetaTabInfo metaTabInfo : metaTabInfoList) {
			String[] dataKeywordArr = metaTabInfo.getDataKeyword().split(",");
			for(int i = 0; i < dataKeywordArr.length; i++) {
				resultList.add(dataKeywordArr[i]);
			}
		}
		return resultList.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * @Method Name : getDataTypeList
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 전체 데이터 타입 조회
	 */	
	public List<String> getDataTypeList(List<MetaTabInfo> metaTabInfoList) {
		List<String> resultList = new ArrayList<String>();	
		for(MetaTabInfo metaTabInfo : metaTabInfoList) {
			resultList.add(metaTabInfo.getDataType());
		}
		return resultList.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * @Method Name : getClschmIdList
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 전체 분류 체계명 조회
	 */	
	public List<String> getClschmIdList(List<MetaTabInfo> metaTabInfoList) {
		//String 으로 바뀔때 까지 Long으로 사용
		List<String> resultList = new ArrayList<String>();	
		for(MetaTabInfo metaTabInfo : metaTabInfoList) {
			resultList.add(metaTabInfo.getClschmId());
		}
		return resultList.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * @Method Name : getCollectionTypeList
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 전체 수집 유형 조회
	 */	
	public List<String> getCollectionTypeList(List<MetaTabInfo> metaTabInfoList) {
		List<String> resultList = new ArrayList<String>();	
		for(MetaTabInfo metaTabInfo : metaTabInfoList) {
			//임시 컬럼
			String[] collectionTypeArr = metaTabInfo.getOpngDataListNm().split(",");
			for(int i = 0; i < collectionTypeArr.length; i++) {
				resultList.add(collectionTypeArr[i]);
			}
		}
		return resultList.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * @Method Name : deleteMetaData
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 삭제
	 */	
	public void deleteMetaData(MetaTabInfo metaTabInfo) {
		MetaColInfo metaColInfo = new MetaColInfo();
		metaColInfo.setTblId(metaTabInfo.getTblId());
		metaColInfo.setDsetId(metaTabInfo.getDsetId());
		metaColInfoMapper.deleteMetaColInfoByTblIdAndDsetId(metaColInfo);
		
		metaTabInfoMapper.deleteMetaTabInfo(metaTabInfo);
	}
	
	public void deleteMetaFileInfo(String fileId) {
		MetaFileInfo dbMetaFileInfo = metaFileInfoMapper.findOneByFileId(fileId);
		
		//파일제거로직
		fileUploadComponent.deleteUploadFile(dbMetaFileInfo.getSaveLc());
		
		//DB제거
		metaFileInfoMapper.deleteMetaFileInfoByFileId(fileId);
	}
}

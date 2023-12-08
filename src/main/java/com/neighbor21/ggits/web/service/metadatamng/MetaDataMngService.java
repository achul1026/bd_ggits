package com.neighbor21.ggits.web.service.metadatamng;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.MetaColInfo;
import com.neighbor21.ggits.common.entity.MetaDbInfo;
import com.neighbor21.ggits.common.entity.MetaTabInfo;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MetaColInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaDbInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaInfsysInfoMapper;
import com.neighbor21.ggits.common.mapper.MetaTabInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class MetaDataMngService{

	@Autowired
	MetaTabInfoMapper metaTabInfoMapper;
	
	@Autowired
	MetaInfsysInfoMapper metaInfsysInfoMapper;
	
	@Autowired
	MetaDbInfoMapper metaDbInfoMapper;
	
	@Autowired
	MetaColInfoMapper metaColInfoMapper; 
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
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
	public String saveMetaData(MetaTabInfo metaTabInfo) {
		String rltinstId = metaTabInfo.getRltinstId();
		String tblId = GgitsCommonUtils.getUuid();
		
		MetaDbInfo dbMetaDbInfo = metaDbInfoMapper.findMetaDbInfoByrltinstId(rltinstId);
		
		metaTabInfo.setTblId(tblId);
		metaTabInfo.setDsetId(dbMetaDbInfo.getDbId());
		metaTabInfo.setTblOwnrNm(LoginSessionUtils.getOprtrNm());
		//추구 컬럼 변경 수집유형 컬럼
		metaTabInfo.setOpngDataListNm(metaTabInfo.getCollDataType());
		
		//null 비참조 numberic
		metaTabInfo.setPrsrvPeriod(0);
		metaTabInfo.setTblSize(0);
		metaTabInfo.setOccurCycl(0);
		metaTabInfo.setDataSaveCycl(0);
		
		try {
			metaTabInfoMapper.saveMetaTabInfo(metaTabInfo);
			
			if(metaTabInfo.getDataType().contains(",")) {
				String[] dataTypeArr = metaTabInfo.getDataType().split(",");
				for(int i = 0; i < dataTypeArr.length; i++) {
					MetaColInfo metaColInfo = new MetaColInfo();
					metaColInfo.setTblId(tblId);
					metaColInfo.setDsetId(dbMetaDbInfo.getDbId());
					metaColInfo.setRltinstId(rltinstId);
					metaColInfo.setDataType(dataTypeArr[i]);
					metaColInfo.setDataLen(dataTypeArr[i].length());
					metaColInfo.setColSqno(metaColInfoMapper.findColsqnoNextVal()+1);
					metaColInfoMapper.saveMetaColInfo(metaColInfo);
				}
			} else {
				MetaColInfo metaColInfo = new MetaColInfo();
				metaColInfo.setTblId(tblId);
				metaColInfo.setDsetId(dbMetaDbInfo.getDbId());
				metaColInfo.setRltinstId(rltinstId);
				metaColInfo.setDataType(metaTabInfo.getDataType());
				metaColInfo.setDataLen(metaTabInfo.getDataType().length());
				metaColInfo.setColSqno(metaColInfoMapper.findColsqnoNextVal()+1);
				metaColInfoMapper.saveMetaColInfo(metaColInfo);
			}
		}catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_SAVE_FAIL,"메타데이터 등록 중 오류가 발생했습니다.");
		}
		return tblId; 
	}
	/**
	 * @Method Name : updateMetaData
	 * @작성일 : 2023. 9. 12.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 테이블 수정
	 */	
	public void updateMetaData(MetaTabInfo metaTabInfo) {
		String rltinstId = metaTabInfo.getRltinstId();

		MetaDbInfo dbMetaDbInfo = metaDbInfoMapper.findMetaDbInfoByrltinstId(rltinstId);

		metaTabInfo.setDsetId(dbMetaDbInfo.getDbId());
		metaTabInfo.setTblOwnrNm(LoginSessionUtils.getOprtrNm());
		//추구 컬럼 변경 수집유형 컬럼
		metaTabInfo.setOpngDataListNm(metaTabInfo.getCollDataType());
		metaTabInfoMapper.updateMetaTabInfo(metaTabInfo);
		
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
	
}

package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaColInfo;

@Mapper
public interface MetaColInfoMapper {
	
	/**
	 * @Method Name : findColsqnoNextVal
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 컬럼 테이블 고유값 ID조회
	 */	
	public int findColsqnoNextVal();
	
	/**
	 * @Method Name : saveMetaColInfo
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 컬럼정보 저장
	 */	
	public void saveMetaColInfo(MetaColInfo metaColInfo);

	/**
	 * @Method Name : updateMetaColInfo
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 컬럼 정보 수정
	 */	
	public void updateMetaColInfo(MetaColInfo metaColInfo);

	/**
	 * @Method Name : findOneMetaColInfoByTblIdAndDbId
	 * @작성일 : 2023. 9. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 컬럼 정보 조회
	 */	
	public MetaColInfo findOneMetaColInfoByTblIdAndDbId(MetaColInfo metaColInfo);

	/**
	 * @Method Name : findAllByTblId
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 컬럼 정보 다중조회
	 */	
	public List<MetaColInfo> findAllByTblId(String tblId);
	
	/**
	 * @Method Name : deleteMetaColInfo
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 데이터 유형 단일 삭제
	 */	
	public void deleteMetaColInfo(MetaColInfo metaColInfo);
	
	/**
	 * @Method Name : deleteMetaColInfoByTblIdAndDsetId
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 데이터 유형 전체 삭제
	 */	
	public void deleteMetaColInfoByTblIdAndDsetId(MetaColInfo metaColInfo);
	
}

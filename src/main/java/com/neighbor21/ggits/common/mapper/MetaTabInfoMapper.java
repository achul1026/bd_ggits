package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaTabInfo;

@Mapper
public interface MetaTabInfoMapper {
	
	/**
	 * @Method Name : findAllMetadataList
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 검색 조회
	 */	
	public List<MetaTabInfo> findAllMetadataList(MetaTabInfo metaTabInfo);

	/**
	 * @Method Name : countAllMetaTabInfoList
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 TotalCount 조회
	 */	
	public int countAllMetaTabInfoList(MetaTabInfo metaTabInfo);
	
	/**
	 * @Method Name : saveMetaTabInfo
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 테이블 저장
	 */	
	public void saveMetaTabInfo(MetaTabInfo metaTabInfo);

	/**
	 * @Method Name : updateMetaTabInfo
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 테이블 수정
	 */	
	public void updateMetaTabInfo(MetaTabInfo metaTabInfo);
	
	/**
	 * @Method Name : findOneByTblId
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 단건 Table 조회
	 */	
	public MetaTabInfo findOneByTblId(String tblId);
	
	/**
	 * @Method Name : findAllSearchOption
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리스트 검색 조건 조회
	 */	
	public List<MetaTabInfo> findAllSearchOption();
	
	/**
	  * @Method Name : findAllSearchOptionForArrayList
	  * @작성일 : 2023. 9. 12.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 메타 데이터 관리 검색 옵션 데이터 
	  * @return Map<String,Object>
	  */
	public Map<String,Object> findOneSearchOptionForArrayList();
	/**
	 * @Method Name : deleteMetaTabInfo
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 삭제
	 */
	public void deleteMetaTabInfo(MetaTabInfo metaTabInfo);
	
	
}

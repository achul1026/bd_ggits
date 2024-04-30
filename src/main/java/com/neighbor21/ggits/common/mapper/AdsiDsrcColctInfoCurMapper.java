package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiDsrcColctInfoCur;

@Mapper
public interface AdsiDsrcColctInfoCurMapper {
	/**
	 * @Method Name : findAdsiDsrcColctInfoList
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(DSRC) 리스트 조회
	 */	
	List<AdsiDsrcColctInfoCur> findAdsiDsrcColctInfoList(AdsiDsrcColctInfoCur adsiDsrcColctInfoCur);

	/**
	 * @Method Name : countDsrcColctInfo
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(DSRC) 리스트 조회
	 */	
	int countDsrcColctInfo(AdsiDsrcColctInfoCur adsiDsrcColctInfoCur);

}

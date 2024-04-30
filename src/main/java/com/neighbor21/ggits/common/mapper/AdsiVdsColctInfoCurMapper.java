package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiVdsColctInfoCur;

@Mapper
public interface AdsiVdsColctInfoCurMapper {
	/**
	 * @Method Name : findAdsiVdsColctInfoList
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(VDS) 리스트 조회
	 */	
	List<AdsiVdsColctInfoCur> findAdsiVdsColctInfoList(AdsiVdsColctInfoCur adsiVdsColctInfoCur);

	/**
	 * @Method Name : findAdsiVdsColctInfoList
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(VDS) 리스트 개수
	 */
	int countVdsColctInfo(AdsiVdsColctInfoCur adsiVdsColctInfoCur);

}

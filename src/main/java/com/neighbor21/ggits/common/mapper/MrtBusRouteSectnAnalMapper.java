package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtBusRouteSectnAnal;

@Mapper
public interface MrtBusRouteSectnAnalMapper {
	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 중복구간 도출 및 적정성 분석 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();

	/**
	 * @Method Name : countAllPubTrfDuplSetAdequacyList
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 중복구간 도출 및 적정성 분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countAllPubTrfDuplSetAdequacyList(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : findAllPubTrfDuplSetAdequacyList
     * @작성일 : 2023. 10. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 중복구간 도출 및 적정성 분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<MrtBusRouteSectnAnal> findAllPubTrfDuplSetAdequacyList(MapBigdataSearchDTO mapBigdataSearchDTO);


	List<MrtBusRouteSectnAnal> findAllDuplicateSectionInfoGeometry(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<MrtBusRouteSectnAnal> findAllDuplicateSectionInfo(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<MrtBusRouteSectnAnal> findTop10ByRouteId(MapBigdataSearchDTO mapBigdataSearchDTO);

	Integer countDuplicateSectionInfo(MapBigdataSearchDTO mapBigdataSearchDTO);



	List<MrtBusRouteSectnAnal> findAllBySectionId(@Param("sectionIds") List<Map<String,String>> sectionIds);


}

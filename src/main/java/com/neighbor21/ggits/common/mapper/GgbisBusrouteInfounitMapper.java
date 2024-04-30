package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.GgbisBusrouteInfounit;

@Mapper
public interface GgbisBusrouteInfounitMapper {


    /**
     * 출발지 도착지로 중복구간 맵 정보 조회
     * @param stStationId
     * @param edStationId
     * @param searchPeriod 
     * @param searchYear 
     * @return
     */
    List<GgbisBusrouteInfounit> findAllByStStationIdAndEdStationId(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : countAllPubTrfDuplSetAdequacyList
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 중복구간 도출 및 적정성 분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	Integer countAllPubTrfDuplSetAdequacyList(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : findAllPubTrfDuplSetAdequacyList
     * @작성일 : 2023. 10. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 중복구간 도출 및 적정성 분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<Map<String, Object>> findAllPubTrfDuplSetAdequacyList(MapBigdataSearchDTO mapBigdataSearchDTO);
}

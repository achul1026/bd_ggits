package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtRoadAccntAnal;

@Mapper
public interface MrtRoadAccntAnalMapper {
	/**
     * @Method Name : findAllAcdntCatgLogColt
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 유형별 이력 집계 리스트 조회
     * @return
     */
	List<MrtRoadAccntAnal> findAllAcdntCatgLogColt(CommonEntity commonEntity);

	/**
     * @Method Name : findAllRiskZnPrdtList
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 위험예측구간 현황통계 리스트 조회
     * @return
     */
	List<MrtRoadAccntAnal> findAllRiskZnPrdtList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllTrafficAcdntLogMapByType
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 도로안전 > 교통사고 유형별 이력집계 통계 정보 조회
     * @return
     */
	Map<String, Object> findAllTrafficAcdntLogMapByType(CommonEntity commonEntity);

	/**
     * @Method Name : findAllTrafficAcdntLogMapByType
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 교통 지표 총괄 통계 > 위험예측구간 현황통계 > 사고유형별 통계
     * @return
     */
	Map<String, Object> findAllRiskPrdnStatsMap(CommonEntity commonEntity);
	
	/**
     * @Method Name : countRiskZnPrdt
     * @작성일 : 2023. 12. 20.
     * @작성자 : KC.KIM
     * @Method 설명 : 위험예측구간 현황통계 리스트 개수 조회
     * @return
     */
	int countRiskZnPrdt(CommonEntity commonEntity);

	/**
     * @Method Name : findAllAcdntCatgLogColt
     * @작성일 : 2023. 12. 20.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 유형별 이력 집계 리스트 개수 조회
     * @return
     */
	int countAcdntCatgLog(CommonEntity commonEntity);
}

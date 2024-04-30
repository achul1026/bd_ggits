package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtAccntLog;
import com.neighbor21.ggits.openapi.request.TrafficAccidentStatisticsRequest;
import com.neighbor21.ggits.openapi.response.TrafficAccidentStatisticsResponse;

@Mapper
public interface MrtAccntLogMapper {
	/**
     * @Method Name : findAllAcdntGenLogInfo
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 리스트 조회
     * @return
     */
	List<MrtAccntLog> findAllAcdntGenLogInfo(CommonEntity commonEntity);

	/**
     * @Method Name : findAllTrafficAcdntGenLogMap
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 도로 안전 > 교통사고 발생이력 통계
     * @return
     */
	Map<String, Object> findAllTrafficAcdntGenLogMap(CommonEntity commonEntity);
	
	/**
	 * @Method Name : findTrafficAccidentStatistics
	 * @작성일 : 2023. 12. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 OPEN API -> 위치 기반 사고 분포 정보
	 * @param TrafficAccidentStatisticsRequest
	 * @return List<TrafficAccidentStatisticsResponse>
	 */
	List<TrafficAccidentStatisticsResponse> findTrafficAccidentStatistics(TrafficAccidentStatisticsRequest trafficAccidentStatisticsRequest);

	/**
     * @Method Name : countAcdntGenLog
     * @작성일 : 2023. 12. 20.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 리스트 개수 조회
     * @return
     */
	int countAcdntGenLog(CommonEntity commonEntity);

}

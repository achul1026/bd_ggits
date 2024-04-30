package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MrtEvcPassAnal;
import com.neighbor21.ggits.openapi.request.EmergencyPathAnalysisRequest;
import com.neighbor21.ggits.openapi.response.EmergencyPathAnalysisResponse;

@Mapper
public interface MrtEvcPassAnalMapper {
	

	public List<EmergencyPathAnalysisResponse> findEmergencyPathAnalysis(EmergencyPathAnalysisRequest emergencyPathAnalysisRequest);

   /**
    * @Method Name : findEmergAcheivePtg
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 전일 광역 긴급 차량 달성률
    **/
	List<MrtEvcPassAnal> findEmergAcheivePtg();

	/**
	 * @Method Name : findEmergAcheivePtg
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 실시간 광역긴급차량 정보
	 **/
	List<MrtEvcPassAnal> findEmergAcheivePtgV2();
	
	/**
	 * @Method Name : findEmergAcheivePtg
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 평균속도
	 **/
	int findAvgDifferentTimeForToday();
}

package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.openapi.request.TrafficSignalInfoRequest;
import com.neighbor21.ggits.openapi.response.TrafficSignalInfoResponse;

@Mapper
public interface MrtSmcSpotLosMapper {
	
	/**
	 * @Method Name : findTrafficSignalInfo
	 * @작성일 : 2023. 12. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 OPEN API -> 교통 통계 정보
	 * @param findTrafficSignalInfo
	 * @return List<TrafficSignalInfoResponse>
	 */
	public List<TrafficSignalInfoResponse> findTrafficSignalInfo(TrafficSignalInfoRequest trafficSignalInfoRequest);

	
	
}

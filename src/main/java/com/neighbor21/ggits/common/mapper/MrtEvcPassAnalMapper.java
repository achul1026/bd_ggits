package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.openapi.request.EmergencyPathAnalysisRequest;
import com.neighbor21.ggits.openapi.response.EmergencyPathAnalysisResponse;

@Mapper
public interface MrtEvcPassAnalMapper {
	public List<EmergencyPathAnalysisResponse> findEmergencyPathAnalysis(EmergencyPathAnalysisRequest emergencyPathAnalysisRequest);
}

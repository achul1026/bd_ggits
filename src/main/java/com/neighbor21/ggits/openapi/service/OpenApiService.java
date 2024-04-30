package com.neighbor21.ggits.openapi.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neighbor21.ggits.common.entity.OpenApiPvsnLog;
import com.neighbor21.ggits.common.enums.OpenApiInfo;
import com.neighbor21.ggits.common.enums.ResultStatus;
import com.neighbor21.ggits.common.mapper.MrtAccntLogMapper;
import com.neighbor21.ggits.common.mapper.MrtEvcPassAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcAbnLosMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcSpotLosMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfAcdntDngrPrdctnMapper;
import com.neighbor21.ggits.common.mapper.OpenApiPvsnLogMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.openapi.request.CongestionSestionInfoRequest;
import com.neighbor21.ggits.openapi.request.EmergencyPathAnalysisRequest;
import com.neighbor21.ggits.openapi.request.TrafficAccidentPredictionInfoRequest;
import com.neighbor21.ggits.openapi.request.TrafficAccidentStatisticsRequest;
import com.neighbor21.ggits.openapi.request.TrafficAnalysisRequest;
import com.neighbor21.ggits.openapi.request.TrafficSignalInfoRequest;
import com.neighbor21.ggits.openapi.request.TrafficSpeedByTimezoneRequest;
import com.neighbor21.ggits.openapi.request.TrafficVolumeByTimezoneRequest;
import com.neighbor21.ggits.openapi.response.CongestionSestionInfoResponse;
import com.neighbor21.ggits.openapi.response.EmergencyPathAnalysisResponse;
import com.neighbor21.ggits.openapi.response.TrafficAccidentPredictionInfoResponse;
import com.neighbor21.ggits.openapi.response.TrafficAccidentStatisticsResponse;
import com.neighbor21.ggits.openapi.response.TrafficAnalysisResponse;
import com.neighbor21.ggits.openapi.response.TrafficSignalInfoResponse;
import com.neighbor21.ggits.openapi.response.TrafficSpeedByTimezoneResponse;
import com.neighbor21.ggits.openapi.response.TrafficVolumeByTimezoneResponse;

@Service
public class OpenApiService {
	
	@Autowired
	MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
	
	@Autowired
	MrtSmcAbnLosMapper mrtSmcAbnLosMapper;
	
	@Autowired
	MrtTrfAcdntDngrPrdctnMapper mrtTrfAcdntDngrPrdctnMapper;
	
	@Autowired
	MrtAccntLogMapper mrtAccntLogMapper;
	
	@Autowired
	MrtSmcSpotLosMapper mrtSmcSpotLosMapper;
	
	@Autowired
	MrtEvcPassAnalMapper mrtEvcPassAnalMapper;
	
	@Autowired
	OpenApiPvsnLogMapper openApiPvsnLogMapper;
	
	public List<TrafficVolumeByTimezoneResponse> getTrafficVolumeByTimezone(TrafficVolumeByTimezoneRequest trafficVolumeByTimezoneRequest){
		return mrtStdLinkSectnInfoMapper.findTrafficVolumeByTimezone(trafficVolumeByTimezoneRequest);
	}

	public List<TrafficSpeedByTimezoneResponse> getTrafficSpeedByTimezone(TrafficSpeedByTimezoneRequest trafficSpeedByTimezoneRequest){
		return mrtStdLinkSectnInfoMapper.findTrafficSpeedByTimezone(trafficSpeedByTimezoneRequest);
	}
	
	public List<CongestionSestionInfoResponse> getCongestionSestionInfo(CongestionSestionInfoRequest congestionSestionInfoRequest){
		return mrtSmcAbnLosMapper.findCongestionSestionInfo(congestionSestionInfoRequest);
	}
	
	public List<TrafficAccidentPredictionInfoResponse> getTrafficAccidentPredictionInfo(TrafficAccidentPredictionInfoRequest trafficAccidentPredictionInfoRequest){
		return mrtTrfAcdntDngrPrdctnMapper.findTrafficAccidentPredictionInfo(trafficAccidentPredictionInfoRequest);
	}
	
	public List<TrafficAccidentStatisticsResponse> getTrafficAccidentStatistics(TrafficAccidentStatisticsRequest trafficAccidentStatisticsRequest){
		return mrtAccntLogMapper.findTrafficAccidentStatistics(trafficAccidentStatisticsRequest);
	}
	
	public List<TrafficSignalInfoResponse> getTrafficSignalInfo(TrafficSignalInfoRequest trafficSignalInfoRequest){
		return mrtSmcSpotLosMapper.findTrafficSignalInfo(trafficSignalInfoRequest);
	}
	
	public List<EmergencyPathAnalysisResponse> getEmergencyPathAnalysis(EmergencyPathAnalysisRequest emergencyPathAnalysisRequest){
		return mrtEvcPassAnalMapper.findEmergencyPathAnalysis(emergencyPathAnalysisRequest);
	}
	
	public List<TrafficAnalysisResponse> getTrafficAnalysis(TrafficAnalysisRequest trafficAnalysisRequest){
		return mrtStdLinkSectnInfoMapper.findTrafficAnalysis(trafficAnalysisRequest);
	}
	
	public void insertOpenApiLog(OpenApiInfo openApiInfo,String requestStr, int responseCnt, ResultStatus resultStatus,String mngInstCd) {
		if(openApiInfo != null) {
			OpenApiPvsnLog openApiPvsnLog = new OpenApiPvsnLog();
			openApiPvsnLog.setDsetId(GgitsCommonUtils.getUuid());
			JsonObject jb = new JsonObject();
			openApiPvsnLog.setApiNm(openApiInfo.getApiTitle());
			openApiPvsnLog.setOprtrIp(LoginSessionUtils.getUserIpAddr());
			
			Gson gson = new Gson();
			jb.addProperty("param", !GgitsCommonUtils.isNull(requestStr) ? requestStr : "");
			jb.addProperty("status", !GgitsCommonUtils.isNull(resultStatus.getCode()) ? resultStatus.getCode() : "RSC001");
			jb.addProperty("cnt", !GgitsCommonUtils.isNull(responseCnt) ? responseCnt : 0);
			String contents = gson.toJson(jb);
			
			openApiPvsnLog.setContents(contents);
			openApiPvsnLog.setApiCallUrl(openApiInfo.getApiUrl());
			if(!GgitsCommonUtils.isNull(mngInstCd)) openApiPvsnLog.setMngInstCd(mngInstCd);
			
			openApiPvsnLogMapper.saveOpenApiPvsnLog(openApiPvsnLog);
		}
	}
}

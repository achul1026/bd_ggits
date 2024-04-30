package com.neighbor21.ggits.common.enums;

public enum OpenApiInfo {
	TRAFFIC_VOLUME_BY_TIME_ZONE("1","/openapi/trafficVolumeByTimezone.do","시간대별 교통량 정보",1),
	TRAFFIC_SPEED_BY_TIME_ZONE("2","/openapi/trafficSpeedByTimezone.do","시간대별 평균속도 정보",2),
	CONGESTION_SESTION_INFO("3","/openapi/congestionSestionInfo.do","정체구간 발생정보",3),
	TRAFFIC_ACCIDENT_PREDICTION_INFO("4","/openapi/trafficAccidentPredictionInfo.do","위험 도로 정보",4),
	TRAFFIC_ACCIDENT_STATISTICS("5","/openapi/trafficAccidentStatistics.do","위치 기반 사고 분포 정보",5),
	TRAFFIC_SIGNAL_INFO("6","/openapi/trafficSignalInfo.do","교통 통계 정보",6),
	EMERGENCY_PATH_ANALYSIS("7","/openapi/emergencyPathAnalysis.do","긴급차량 이동 경로 분석",7),
	TRAFFIC_ANALYSIS("8","/openapi/trafficAnalysis.do","교통정보 분석 데이터",8);
	
	private String apiId;
	
	private String apiUrl;
	
	private String apiTitle;

	private int sortNo;
	
	private OpenApiInfo(String apiId, String apiUrl, String apiTitle, int sortNo) {
		this.apiId = apiId;
		this.apiUrl = apiUrl;
		this.apiTitle = apiTitle;
		this.sortNo = sortNo;
	}

	public String getApiId() {
		return apiId;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getApiTitle() {
		return apiTitle;
	}

	public int getSortNo() {
		return sortNo;
	}

	public static OpenApiInfo getApiInfoForApiId(String apiId) {
		for(OpenApiInfo r : OpenApiInfo.values()) {
			if(r.apiId.equals(apiId)) {
				return r;
			}
		}
		return null;
	}
	
	public static OpenApiInfo getApiInfoForApiUrl(String apiUrl) {
		for(OpenApiInfo r : OpenApiInfo.values()) {
			if(r.apiUrl.equals(apiUrl)) {
				return r;
			}
		}
		return null;
	}
	
}

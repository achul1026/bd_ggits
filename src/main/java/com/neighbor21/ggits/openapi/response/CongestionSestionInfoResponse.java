package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CongestionSestionInfoResponse {
	
	@JsonProperty("trfvlm_cngrt")
	String trfvlmCngrt;			//교통량 혼잡도
	
	@JsonProperty("link_id")
	String linkId;				//링크 ID
	
	@JsonProperty("avg_vhcl_speed")
	double avgVhclSpeed;		//평균 속도

	public String getTrfvlmCngrt() {
		return trfvlmCngrt;
	}

	public void setTrfvlmCngrt(String trfvlmCngrt) {
		this.trfvlmCngrt = trfvlmCngrt;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public double getAvgVhclSpeed() {
		return avgVhclSpeed;
	}

	public void setAvgVhclSpeed(double avgVhclSpeed) {
		this.avgVhclSpeed = avgVhclSpeed;
	}
	
}

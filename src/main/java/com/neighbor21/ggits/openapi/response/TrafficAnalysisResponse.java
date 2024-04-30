package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrafficAnalysisResponse {
	
	@JsonProperty("link_id")
	String linkId;	//링크아이디

	@JsonProperty("trfvlm")
	Long trfvlm;	//교통량 총합
	
	@JsonProperty("speed")
	double speed;	// 평균 속도

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Long getTrfvlm() {
		return trfvlm;
	}

	public void setTrfvlm(Long trfvlm) {
		this.trfvlm = trfvlm;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

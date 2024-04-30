package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrafficAccidentPredictionInfoResponse {
	
	@JsonProperty("speed")
	double speed;			//속도
	
	@JsonProperty("link_id")
	String linkId;			//링크 아이디
	
	@JsonProperty("safe_idex")
	double safeIdex;		//안전 지수
	
	@JsonProperty("safe_grd")
	String safeGrd;			//안전등급

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public double getSafeIdex() {
		return safeIdex;
	}

	public void setSafeIdex(double safeIdex) {
		this.safeIdex = safeIdex;
	}

	public String getSafeGrd() {
		return safeGrd;
	}

	public void setSafeGrd(String safeGrd) {
		this.safeGrd = safeGrd;
	}
}

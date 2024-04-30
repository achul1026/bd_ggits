package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrafficVolumeByTimezoneResponse {
	
	@JsonProperty("link_id")
	String linkId;
	
	@JsonProperty("trfvlm")
	Long trfvlm;
	
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

}

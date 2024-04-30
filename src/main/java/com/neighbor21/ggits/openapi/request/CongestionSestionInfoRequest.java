package com.neighbor21.ggits.openapi.request;

public class CongestionSestionInfoRequest {
	
	private String linkId;
	
	private String date;
	
	public CongestionSestionInfoRequest() {
		super();
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[linkId :" + linkId + ", date :" + date + "]";
	}
}

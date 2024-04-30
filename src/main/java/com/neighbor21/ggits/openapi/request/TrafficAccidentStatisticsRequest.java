package com.neighbor21.ggits.openapi.request;

public class TrafficAccidentStatisticsRequest {
	
	private String acdntDstrctIdentifier;
	
	private String date;

	public String getAcdntDstrctIdentifier() {
		return acdntDstrctIdentifier;
	}

	public void setAcdntDstrctIdentifier(String acdntDstrctIdentifier) {
		this.acdntDstrctIdentifier = acdntDstrctIdentifier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[acdntDstrctIdentifier : " + acdntDstrctIdentifier + ", date : " + date
				+ "]";
	}
	
}

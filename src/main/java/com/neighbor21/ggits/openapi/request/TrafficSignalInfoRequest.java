package com.neighbor21.ggits.openapi.request;

public class TrafficSignalInfoRequest {
	
	private String crsrdId;
	
	private String date;
	
	private String mngInstCd;

	public String getCrsrdId() {
		return crsrdId;
	}

	public void setCrsrdId(String crsrdId) {
		this.crsrdId = crsrdId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	@Override
	public String toString() {
		return "[crsrdId :" + crsrdId + ", date :" + date + ", mngInstCd :" + mngInstCd + "]";
	}
	
}

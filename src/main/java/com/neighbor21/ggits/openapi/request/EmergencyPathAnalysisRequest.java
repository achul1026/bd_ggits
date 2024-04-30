package com.neighbor21.ggits.openapi.request;

public class EmergencyPathAnalysisRequest {
	
	private String mngInstCd;
	
	private String date;
	
	private String srvcId;

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSrvcId() {
		return srvcId;
	}

	public void setSrvcId(String srvcId) {
		this.srvcId = srvcId;
	}

	@Override
	public String toString() {
		return "[mngInstCd :" + mngInstCd + ", date :" + date + ", srvcId :" + srvcId + "]";
	}
}

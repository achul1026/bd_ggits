package com.neighbor21.ggits.common.dto;

public class MapMonitoringLinkDataDTO {
	
	private String jobNm;
	private String renewTime;
	private String errorMsg;
	private long dataCnt = 0;
	private long dataTotalCnt = 0;
	private long errorCnt = 0;
	private long errorTotalCnt = 0;
	
	public String getJobNm() {
		return jobNm;
	}
	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}
	public long getDataCnt() {
		return dataCnt;
	}
	public void setDataCnt(long dataCnt) {
		this.dataCnt = dataCnt;
	}
	public long getDataTotalCnt() {
		return dataTotalCnt;
	}
	public void setDataTotalCnt(long dataTotalCnt) {
		this.dataTotalCnt = dataTotalCnt;
	}
	public long getErrorCnt() {
		return errorCnt;
	}
	public void setErrorCnt(long errorCnt) {
		this.errorCnt = errorCnt;
	}
	public long getErrorTotalCnt() {
		return errorTotalCnt;
	}
	public void setErrorTotalCnt(long errorTotalCnt) {
		this.errorTotalCnt = errorTotalCnt;
	}
	public String getRenewTime() {
		return renewTime;
	}
	public void setRenewTime(String renewTime) {
		this.renewTime = renewTime;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
}

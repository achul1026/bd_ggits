package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsDsrcRsestatus {
	private String rseid; // rse 아이디
	private Timestamp recordtimestamp; // 수집일시
	private String commstatus; // 통신상태
	private String doorstatus; // 도어상태
	private String fanstatus; // 팬상태
	private String tempstatus; // 온도
	private String antstatus; // 안테나상태

	public String getRseid() {
		return rseid;
	}

	public void setRseid(String rseid) {
		this.rseid = rseid;
	}

	public Timestamp getRecordtimestamp() {
		return recordtimestamp;
	}

	public void setRecordtimestamp(Timestamp recordtimestamp) {
		this.recordtimestamp = recordtimestamp;
	}

	public String getCommstatus() {
		return commstatus;
	}

	public void setCommstatus(String commstatus) {
		this.commstatus = commstatus;
	}

	public String getDoorstatus() {
		return doorstatus;
	}

	public void setDoorstatus(String doorstatus) {
		this.doorstatus = doorstatus;
	}

	public String getFanstatus() {
		return fanstatus;
	}

	public void setFanstatus(String fanstatus) {
		this.fanstatus = fanstatus;
	}

	public String getTempstatus() {
		return tempstatus;
	}

	public void setTempstatus(String tempstatus) {
		this.tempstatus = tempstatus;
	}

	public String getAntstatus() {
		return antstatus;
	}

	public void setAntstatus(String antstatus) {
		this.antstatus = antstatus;
	}

}

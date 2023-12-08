package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//표준링크 교통정보 분석
public class MrtStdLinkSectnInfo extends CommonEntity {
	private Timestamp anlsDt;	// 분석 일시
	private String link_id;		// 링크 아이디
	private long speed;			// 속도
	private long trfvlm;		// 교통량
	private String etlDt;		// etl일시
	
	private String roadName;	// 도로명
	private String safeGrd;		// 안전등급
	
	public Timestamp getAnlsDt() {
		return anlsDt;
	}
	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public long getSpeed() {
		return speed;
	}
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	public long getTrfvlm() {
		return trfvlm;
	}
	public void setTrfvlm(long trfvlm) {
		this.trfvlm = trfvlm;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getSafeGrd() {
		return safeGrd;
	}
	public void setSafeGrd(String safeGrd) {
		this.safeGrd = safeGrd;
	}

	
}

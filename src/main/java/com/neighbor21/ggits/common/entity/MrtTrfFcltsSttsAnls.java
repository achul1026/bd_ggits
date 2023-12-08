package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtTrfFcltsSttsAnls extends CommonEntity{
	private Timestamp anlsDt;		// 분석 일시
	private String fcltsType;		// 시설물 유형
	private String fcltsId;			// 시설물 아이디
	private String linkId;			// 링크 아이디
	private String roadGrd;			// 도로 등급
	private String roadDrct;		// 도로 방향
	private long trblOccurCnt;		// 장애 발생 수
	private String etlDt;			// etl 일시
	
	private String roadName;		// 도로명

	public Timestamp getAnlsDt() {
		return anlsDt;
	}

	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}

	public String getFcltsType() {
		return fcltsType;
	}

	public void setFcltsType(String fcltsType) {
		this.fcltsType = fcltsType;
	}

	public String getFcltsId() {
		return fcltsId;
	}

	public void setFcltsId(String fcltsId) {
		this.fcltsId = fcltsId;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getRoadGrd() {
		return roadGrd;
	}

	public void setRoadGrd(String roadGrd) {
		this.roadGrd = roadGrd;
	}

	public String getRoadDrct() {
		return roadDrct;
	}

	public void setRoadDrct(String roadDrct) {
		this.roadDrct = roadDrct;
	}

	public long getTrblOccurCnt() {
		return trblOccurCnt;
	}

	public void setTrblOccurCnt(long trblOccurCnt) {
		this.trblOccurCnt = trblOccurCnt;
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
	
	
}

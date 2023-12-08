package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtRptTotInfo extends CommonEntity{
	private Timestamp anlsDt;		// 분석 일시
	private String linkId;			// 링크 아이디
	private String roadRoutNm;		// 도로 노선명
	private String Drct;			// 도로 방향
	private String roadSctn;		// 도로 구간
	private long trfvlm;			// 교통량
	private double avgSpeed;		// 평균 속도
	private String cngrt;			// 혼잡도
	private long unxpOccurCnt;		// 돌발 발생 수
	
	
	// 기상현황/별 교통량 / 돌발 대기오염현황 통계
	private String roadName;		// 도로명
	
	public Timestamp getAnlsDt() {
		return anlsDt;
	}
	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getRoadRoutNm() {
		return roadRoutNm;
	}
	public void setRoadRoutNm(String roadRoutNm) {
		this.roadRoutNm = roadRoutNm;
	}
	public String getDrct() {
		return Drct;
	}
	public void setDrct(String drct) {
		Drct = drct;
	}
	public String getRoadSctn() {
		return roadSctn;
	}
	public void setRoadSctn(String roadSctn) {
		this.roadSctn = roadSctn;
	}
	public long getTrfvlm() {
		return trfvlm;
	}
	public void setTrfvlm(long trfvlm) {
		this.trfvlm = trfvlm;
	}
	public double getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	public String getCngrt() {
		return cngrt;
	}
	public void setCngrt(String cngrt) {
		this.cngrt = cngrt;
	}
	public long getUnxpOccurCnt() {
		return unxpOccurCnt;
	}
	public void setUnxpOccurCnt(long unxpOccurCnt) {
		this.unxpOccurCnt = unxpOccurCnt;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	
}

package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSigCtrlLog {
	private String mngInstCd; // 관리 기관 코드
	private Timestamp anlsDt; // 분석 일시
	private String srvcId; // 서비스아이디
	private String crsrdId; // 교차로 아이디
	private long ctrlCnt; // 제어 수
	private long avgCtrlTime; // 평균 제어 시간
	private String etlDt; // ETL 일시
	
    //맵용
	//TODO:: 테이블 데이터 확인 후 컬럼 결과값 재수정
	private String linkId;
    private double avgVhclSpeedAvg; // 시간대 평균속도
    private long vhclTrfvlmTotal; // 누적통행량
    private String timeGroupTxt; // 시간대별 평균속도 통행량 yyyy-MM-dd HH:mi|평균속도|교통량 ex)2023-09-22 15:38|19.00|0$$2023-09-22 16:18|22.00|0
    
	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public Timestamp getAnlsDt() {
		return anlsDt;
	}

	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}

	public String getSrvcId() {
		return srvcId;
	}

	public void setSrvcId(String srvcId) {
		this.srvcId = srvcId;
	}

	public String getCrsrdId() {
		return crsrdId;
	}

	public void setCrsrdId(String crsrdId) {
		this.crsrdId = crsrdId;
	}

	public long getCtrlCnt() {
		return ctrlCnt;
	}

	public void setCtrlCnt(long ctrlCnt) {
		this.ctrlCnt = ctrlCnt;
	}

	public long getAvgCtrlTime() {
		return avgCtrlTime;
	}

	public void setAvgCtrlTime(long avgCtrlTime) {
		this.avgCtrlTime = avgCtrlTime;
	}

	public String getEtlDt() {
		return etlDt;
	}

	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public double getAvgVhclSpeedAvg() {
		return avgVhclSpeedAvg;
	}

	public void setAvgVhclSpeedAvg(double avgVhclSpeedAvg) {
		this.avgVhclSpeedAvg = avgVhclSpeedAvg;
	}

	public long getVhclTrfvlmTotal() {
		return vhclTrfvlmTotal;
	}

	public void setVhclTrfvlmTotal(long vhclTrfvlmTotal) {
		this.vhclTrfvlmTotal = vhclTrfvlmTotal;
	}

	public String getTimeGroupTxt() {
		return timeGroupTxt;
	}

	public void setTimeGroupTxt(String timeGroupTxt) {
		this.timeGroupTxt = timeGroupTxt;
	}
	
	
}

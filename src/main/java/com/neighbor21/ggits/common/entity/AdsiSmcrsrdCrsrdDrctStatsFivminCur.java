package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 스마트교차로 교차로 방향 통계 5분 현황
public class AdsiSmcrsrdCrsrdDrctStatsFivminCur extends CommonEntity{
	private String mngInstCd;			// 관리 기관 코드
	private Timestamp clctDt;			// 수집 일시
	private String crsrdId;				// 교차로 아이디
	private String acsRoadId;			// 접근 도로 아이디
	private String drctCd;				// 방향 코드
	private String vhclDiv;				// 차량 구분
	private Long laneNo;				// 차량 번호
	private Long vhclTrfvlm;			// 차량 교통량
	private Double avgVhclSpeed;		// 평균 차량 속도
	private String etlDt;				// etl 일시
	public String getMngInstCd() {
		return mngInstCd;
	}
	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}
	public Timestamp getClctDt() {
		return clctDt;
	}
	public void setClctDt(Timestamp clctDt) {
		this.clctDt = clctDt;
	}
	public String getCrsrdId() {
		return crsrdId;
	}
	public void setCrsrdId(String crsrdId) {
		this.crsrdId = crsrdId;
	}
	public String getAcsRoadId() {
		return acsRoadId;
	}
	public void setAcsRoadId(String acsRoadId) {
		this.acsRoadId = acsRoadId;
	}
	public String getDrctCd() {
		return drctCd;
	}
	public void setDrctCd(String drctCd) {
		this.drctCd = drctCd;
	}
	public String getVhclDiv() {
		return vhclDiv;
	}
	public void setVhclDiv(String vhclDiv) {
		this.vhclDiv = vhclDiv;
	}
	public Long getLaneNo() {
		return laneNo;
	}
	public void setLaneNo(Long laneNo) {
		this.laneNo = laneNo;
	}
	public Long getVhclTrfvlm() {
		return vhclTrfvlm;
	}
	public void setVhclTrfvlm(Long vhclTrfvlm) {
		this.vhclTrfvlm = vhclTrfvlm;
	}
	public Double getAvgVhclSpeed() {
		return avgVhclSpeed;
	}
	public void setAvgVhclSpeed(Double avgVhclSpeed) {
		this.avgVhclSpeed = avgVhclSpeed;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	
	
}

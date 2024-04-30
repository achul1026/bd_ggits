package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// vds 수집 현황 정보
public class AdsiVdsColctInfoCur extends CommonEntity{
	private String mngInstCd;		// 관리 기관 코드
	private Timestamp clctDt;		// 수집 일시
	private String vdsId;			// vds 아이디
	private Long laneNo;			// 차로 번호
	private Double avgSpeed;		// 평균 속도
	private Long trfvlm;			// 교통량
	private Double occpRt;			// 점유 비율
	private String etlDt;			// etl 일시
	
	private Double sumTrfvlm;		// 누적 교통량 
	private Double avgTrfvlm;		// 평균 교통량
	private Double avgSpd;			// 평균 속도
	private String vdsNm;			// VDS명
	private String roadName;		// 도로명
	
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
	public String getVdsId() {
		return vdsId;
	}
	public void setVdsId(String vdsId) {
		this.vdsId = vdsId;
	}
	public Long getLaneNo() {
		return laneNo;
	}
	public void setLaneNo(Long laneNo) {
		this.laneNo = laneNo;
	}
	public Double getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	public Long getTrfvlm() {
		return trfvlm;
	}
	public void setTrfvlm(Long trfvlm) {
		this.trfvlm = trfvlm;
	}
	public Double getOccpRt() {
		return occpRt;
	}
	public void setOccpRt(Double occpRt) {
		this.occpRt = occpRt;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public Double getSumTrfvlm() {
		return sumTrfvlm;
	}
	public void setSumTrfvlm(Double sumTrfvlm) {
		this.sumTrfvlm = sumTrfvlm;
	}
	public Double getAvgTrfvlm() {
		return avgTrfvlm;
	}
	public void setAvgTrfvlm(Double avgTrfvlm) {
		this.avgTrfvlm = avgTrfvlm;
	}
	public Double getAvgSpd() {
		return avgSpd;
	}
	public void setAvgSpd(Double avgSpd) {
		this.avgSpd = avgSpd;
	}
	public String getVdsNm() {
		return vdsNm;
	}
	public void setVdsNm(String vdsNm) {
		this.vdsNm = vdsNm;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	
	
}

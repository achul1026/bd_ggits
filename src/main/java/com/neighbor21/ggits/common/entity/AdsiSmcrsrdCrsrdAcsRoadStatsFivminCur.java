package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 교차로 접근 도로 통계 5분 현황
public class AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur extends CommonEntity{
	private String mngInstCd;			// 관리 기관 코드
	private Timestamp clctDt;			// 수집 일시
	private String crsrdId;				// 교차로 아이디
	private String acsRoadId;			// 접근 도로 아이디
	private Long vhclTrfvlm;			// 차량 교통량
	private Long pdstCnt;				// 보행자 수
	private Double avgVhclSpeed;		// 평균 차량 속도
	private Double avgPdstSpeed;		// 평균 보행자 속도
	private Long ctrlDelayTime;			// 제어 지연 시간
	private String etlDt;				// etl 일시
	
	private String roadName;			// 도로명
	private String cameraNm;			// 카메라 명
	private Double sumTrfvlm;			// 누적 교통량
	private Double avgTrfvlm;			// 평균 교통량
	private Double avgSpd;				// 평균 속도
	
	//#AdsiSmcrsrdCrsrdInfo
	private String crsrdNm;				//교차로 이름
	
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
	public Long getVhclTrfvlm() {
		return vhclTrfvlm;
	}
	public void setVhclTrfvlm(Long vhclTrfvlm) {
		this.vhclTrfvlm = vhclTrfvlm;
	}
	public Long getPdstCnt() {
		return pdstCnt;
	}
	public void setPdstCnt(Long pdstCnt) {
		this.pdstCnt = pdstCnt;
	}
	public Double getAvgVhclSpeed() {
		return avgVhclSpeed;
	}
	public void setAvgVhclSpeed(Double avgVhclSpeed) {
		this.avgVhclSpeed = avgVhclSpeed;
	}
	public Double getAvgPdstSpeed() {
		return avgPdstSpeed;
	}
	public void setAvgPdstSpeed(Double avgPdstSpeed) {
		this.avgPdstSpeed = avgPdstSpeed;
	}
	public Long getCtrlDelayTime() {
		return ctrlDelayTime;
	}
	public void setCtrlDelayTime(Long ctrlDelayTime) {
		this.ctrlDelayTime = ctrlDelayTime;
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
	public String getCameraNm() {
		return cameraNm;
	}
	public void setCameraNm(String cameraNm) {
		this.cameraNm = cameraNm;
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
	public String getCrsrdNm() {
		return crsrdNm;
	}
	public void setCrsrdNm(String crsrdNm) {
		this.crsrdNm = crsrdNm;
	}
	
}

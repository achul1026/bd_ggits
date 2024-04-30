package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// dsrc 수집 현황 정보
public class AdsiDsrcColctInfoCur extends CommonEntity{
	private String mngInstCd;		// 관리 기관 코드
	private Timestamp clctDt;		// 수집 일시
	private String dsrcSctnId;		// dsrc 구간 아이디
	private String obuId;			// obu 아이디
	private Double speed;			// 속도
	private String  etlDt;			// etl 일시
	
	private String strRseNm;		// 시작 구간명
	private String endRseNm;		// 종료 구간명
	private String roadName;		// 도로명
	private Double avgSpd;			// 평균 속도

	
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
	public String getDsrcSctnId() {
		return dsrcSctnId;
	}
	public void setDsrcSctnId(String dsrcSctnId) {
		this.dsrcSctnId = dsrcSctnId;
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getStrRseNm() {
		return strRseNm;
	}
	public void setStrRseNm(String strRseNm) {
		this.strRseNm = strRseNm;
	}
	public String getEndRseNm() {
		return endRseNm;
	}
	public void setEndRseNm(String endRseNm) {
		this.endRseNm = endRseNm;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public Double getAvgSpd() {
		return avgSpd;
	}
	public void setAvgSpd(Double avgSpd) {
		this.avgSpd = avgSpd;
	}
	
	
}

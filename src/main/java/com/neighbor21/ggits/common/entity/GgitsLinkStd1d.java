package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// link 표준링크 1일 통계 소통정보
public class GgitsLinkStd1d extends CommonEntity{
	private Timestamp procDate;		// 1분 소통정보가공시각
	private String linkId;			// 링크 아이디
	private long spd;				// 1분 동안의 링크의 평균속도
	private long vol;				// 교통량
	private long dens;				// 밀도
	private long trvlTime;			// 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private long linkDelaytime;		// 링크 지체시간
	private long qLen;				// 대기 길이
	private long occ;				// 점유율
	private String congGrade;		// 링크별 속도에 따른 혼잡도구분(소통원활/지체서행/정체)
	private String dowCd;			// 요일구분 (일주일+특별휴일)
	private String weathInflCd;		// 수집일의 기상상태 구분
	private String tfinfoCreTpCd;	// 소통정보생성유형코드
	
	private String roadName;		// 도로 명
	private String dateStr;			// 지표 기간
	private Double sumVol;			// 누적 교통량
	private Double avgVol;			// 평균 교통량
	private Double avgSpd;			// 평균 속도
	private String adsiNm;			// 시군명
	
	private String chartYn;
	
	public Timestamp getProcDate() {
		return procDate;
	}
	public void setProcDate(Timestamp procDate) {
		this.procDate = procDate;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public long getSpd() {
		return spd;
	}
	public void setSpd(long spd) {
		this.spd = spd;
	}
	public long getVol() {
		return vol;
	}
	public void setVol(long vol) {
		this.vol = vol;
	}
	public long getDens() {
		return dens;
	}
	public void setDens(long dens) {
		this.dens = dens;
	}
	public long getTrvlTime() {
		return trvlTime;
	}
	public void setTrvlTime(long trvlTime) {
		this.trvlTime = trvlTime;
	}
	public long getLinkDelaytime() {
		return linkDelaytime;
	}
	public void setLinkDelaytime(long linkDelaytime) {
		this.linkDelaytime = linkDelaytime;
	}
	public long getqLen() {
		return qLen;
	}
	public void setqLen(long qLen) {
		this.qLen = qLen;
	}
	public long getOcc() {
		return occ;
	}
	public void setOcc(long occ) {
		this.occ = occ;
	}
	public String getCongGrade() {
		return congGrade;
	}
	public void setCongGrade(String congGrade) {
		this.congGrade = congGrade;
	}
	public String getDowCd() {
		return dowCd;
	}
	public void setDowCd(String dowCd) {
		this.dowCd = dowCd;
	}
	public String getWeathInflCd() {
		return weathInflCd;
	}
	public void setWeathInflCd(String weathInflCd) {
		this.weathInflCd = weathInflCd;
	}
	public String getTfinfoCreTpCd() {
		return tfinfoCreTpCd;
	}
	public void setTfinfoCreTpCd(String tfinfoCreTpCd) {
		this.tfinfoCreTpCd = tfinfoCreTpCd;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getChartYn() {
		return chartYn;
	}
	public void setChartYn(String chartYn) {
		this.chartYn = chartYn;
	}
	public Double getSumVol() {
		return sumVol;
	}
	public void setSumVol(Double sumVol) {
		this.sumVol = sumVol;
	}
	public Double getAvgVol() {
		return avgVol;
	}
	public void setAvgVol(Double avgVol) {
		this.avgVol = avgVol;
	}
	public Double getAvgSpd() {
		return avgSpd;
	}
	public void setAvgSpd(Double avgSpd) {
		this.avgSpd = avgSpd;
	}
	public String getAdsiNm() {
		return adsiNm;
	}
	public void setAdsiNm(String adsiNm) {
		this.adsiNm = adsiNm;
	}
	
}

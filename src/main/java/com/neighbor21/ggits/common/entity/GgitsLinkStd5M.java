package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsLinkStd5M {
	private Timestamp procDate; // 1분 소통정보가공시각
	private String linkId; // 링크 아이디
	private long spd; // 1분 동안의 링크의 평균속도
	private long vol; // 교통량
	private long dens; // 밀도
	private long trvlTime; // 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private long linkDelaytime; // 링크 지체시간
	private long qLen; // 대기 길이
	private long occ; // 점유율
	private String congGrade; // 링크별 속도에 따른 혼잡도구분(소통원활/지체서행/정체)
	private String dowCd; // 요일구분 (일주일+특별휴일)
	private String weathInflCd; // 수집일의 기상상태 구분
	private String tfinfoCreTpCd; // 소통정보생성유형코드
	private String recurrentYn; // 반복정체 여부
	private String orgCode; // 생성기관코드
	private Timestamp collDate; // 수집일시(소통정보 갱신 시각

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

	public long getQLen() {
		return qLen;
	}

	public void setQLen(long qLen) {
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

	public String getRecurrentYn() {
		return recurrentYn;
	}

	public void setRecurrentYn(String recurrentYn) {
		this.recurrentYn = recurrentYn;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Timestamp getCollDate() {
		return collDate;
	}

	public void setCollDate(Timestamp collDate) {
		this.collDate = collDate;
	}

}

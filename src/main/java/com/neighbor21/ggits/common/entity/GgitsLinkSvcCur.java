package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsLinkSvcCur {
	private String linkId; // 링크 아이디
	private Timestamp procDate; // 소통정보 가공시각
	private long spd; // 1분 동안의 링크의 평균속도
	private long vol; // 교통량
	private long dens; // 밀도
	private long trvlTime; // 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private long linkDelaytime; // 링크 지체시간
	private long qLen; // 대기 길이
	private long occ; // 점유율
	private String congGrade; // 링크별 속도에 따른 혼잡도구분
	private String weathInflCd; // 기상영향코드
	private String tfinfoCreTpCd; // 소통정보생성유형코드

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Timestamp getProcDate() {
		return procDate;
	}

	public void setProcDate(Timestamp procDate) {
		this.procDate = procDate;
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

}

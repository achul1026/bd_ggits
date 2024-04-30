package com.neighbor21.ggits.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

// 매 1분마다 가공된 소통정보를 누적한다.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtGgitsLinkStd1m {
	private String linkId;				// 링크를 고유키로 식별하기 위해 생성 도로구분(1자리) + 링크(1자리) + 방향(1자리) + 행정구역(2자리) + 일련번호(4자리) 예) ALW100001
	private Double spd;					// 1분 동안의 링크의 평균속도
	private Long vol;					// 교통량
	private Long dens;					// 밀도
	private Double trvlTime;				// 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private Long linkDelaytime;		// 링크 지체시간
	private Long qLen;					// 대기 길이
	private Double occ;					// 점유율
	private String congGrade;			// 링크별 속도에 따른 혼잡도구분(소통원활/지체서행/정체)
	private String dowCd;				// 요일구분 (일주일+특별휴일)
	private String weathInflCd;		// 수집일의 기상상태 구분
	private String tfinfoCreTpCd;	// 소통정보생성유형코드(01:운영자, 02:VDS, 03:프로브, 04:외부연계정보, 05:VDS+외부연계, 06:프로브+외부연계, 07: VDS+프로브, 08:VDS+프로브+외부연계, 09:VDS+프로브패턴, 10:VDS+프로브패턴+외부연계, 11:VDS패턴+프로브, 12:VDS패턴+프로브+외부연계, 13:외부연계+VDS패턴+프로브패턴, 14:전시점링크소통정보, 15:링크패턴자료, 16:링크패턴+외부연계, 17:BUS)
	private String recurrentYn;		// 반복정체 여부
	private String orgCode;			// 생성기관코드
	private Timestamp collDate;		// 수집일시(소통정보 갱신 시각
	
	private String roadName;			// 도로명
	private Double avgSpd;				// 평균 속도
	private Double minSpd;				// 최저 속도
	private Double maxSpd;				// 최고 속도
	private Long roadLength;			// 도로 길이
	
	private String roadRank;			// 도로 등급

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Double getSpd() {
		return spd;
	}

	public void setSpd(Double spd) {
		this.spd = spd;
	}

	public Long getVol() {
		return vol;
	}

	public void setVol(Long vol) {
		this.vol = vol;
	}

	public Long getDens() {
		return dens;
	}

	public void setDens(Long dens) {
		this.dens = dens;
	}

	public Double getTrvlTime() {
		return trvlTime;
	}

	public void setTrvlTime(Double trvlTime) {
		this.trvlTime = trvlTime;
	}

	public Long getLinkDelaytime() {
		return linkDelaytime;
	}

	public void setLinkDelaytime(Long linkDelaytime) {
		this.linkDelaytime = linkDelaytime;
	}

	public Long getqLen() {
		return qLen;
	}

	public void setqLen(Long qLen) {
		this.qLen = qLen;
	}

	public Double getOcc() {
		return occ;
	}

	public void setOcc(Double occ) {
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

	public Double getMinSpd() {
		return minSpd;
	}

	public void setMinSpd(Double minSpd) {
		this.minSpd = minSpd;
	}

	public Double getMaxSpd() {
		return maxSpd;
	}

	public void setMaxSpd(Double maxSpd) {
		this.maxSpd = maxSpd;
	}

	public Long getRoadLength() {
		return roadLength;
	}

	public void setRoadLength(Long roadLength) {
		this.roadLength = roadLength;
	}

	public String getRoadRank() {
		return roadRank;
	}

	public void setRoadRank(String roadRank) {
		this.roadRank = roadRank;
	}
}

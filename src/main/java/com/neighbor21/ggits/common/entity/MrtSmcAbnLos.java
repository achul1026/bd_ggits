package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcAbnLos extends CommonEntity {
    private String mngInstCd;        //관리 기관 코드
    private Timestamp anlsDt;        //분석 일시
    private String linkId;        //링크 아이디
    private String dywkCd;        //요일 코드
    private Long avgVhclSpeed;        //평균 차량 속도
    private String vhclTrfvlm;        //교통량 혼잡도
    private String etlDt;        //etl 일시
    private String geojson;
    private String acsRoadNm;    // 도로명
    private String roadRank;    // 도로 등급
    private String roadName;
    private String trfvlmCngrt;
    private String trfvlmCngrtGrd;

    public MrtSmcAbnLos(CommonEntity commonEntity) {
        super(commonEntity);
    }

    public MrtSmcAbnLos() {
        super();
    }

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


    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }


    public String getDywkCd() {
        return dywkCd;
    }

    public void setDywkCd(String dywkCd) {
        this.dywkCd = dywkCd;
    }

    public Long getAvgVhclSpeed() {
		return avgVhclSpeed;
	}

	public void setAvgVhclSpeed(Long avgVhclSpeed) {
		this.avgVhclSpeed = avgVhclSpeed;
	}

	public String getVhclTrfvlm() {
        return vhclTrfvlm;
    }

    public void setVhclTrfvlm(String vhclTrfvlm) {
        this.vhclTrfvlm = vhclTrfvlm;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public String getAcsRoadNm() {
        return acsRoadNm;
    }

    public void setAcsRoadNm(String acsRoadNm) {
        this.acsRoadNm = acsRoadNm;
    }

    public String getRoadRank() {
        return roadRank;
    }

    public void setRoadRank(String roadRank) {
        this.roadRank = roadRank;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getTrfvlmCngrt() {
        return trfvlmCngrt;
    }

    public void setTrfvlmCngrt(String trfvlmCngrt) {
        this.trfvlmCngrt = trfvlmCngrt;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getTrfvlmCngrtGrd() {
        return trfvlmCngrtGrd;
    }

    public void setTrfvlmCngrtGrd(String trfvlmCngrtGrd) {
        this.trfvlmCngrtGrd = trfvlmCngrtGrd;
    }
}

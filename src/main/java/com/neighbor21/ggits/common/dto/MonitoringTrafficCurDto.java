package com.neighbor21.ggits.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonitoringTrafficCurDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double avgSpeed;
    private Long trfVol;
    private String time;
    private String sggCd;
    private String mngInstCd;
    private String adsiNm;
    private String adstdgNm;
    private String roadName;
    private String linkId;
    private Long rnk;
    private String vhclDiv;
    private String drctCd;
    private String crsrdNm;
    private Double lonCrdn;
    private Double latCrdn;
    private Double angle;
    private String st;
    private String ed;
    private String crsrdId;
    private String acsRoadId;
    private String acsRoadNm;
    private Long strghtTrfvlm;
    private Long trnghtTrfvlm;
    private Long trnlftTrfvlm;
    private Long trnutrnTrfvlm;
    private Long trfvlmTotal;

    private String stRseNm;
    private String edRseNm;

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Long getTrfVol() {
        return trfVol;
    }

    public void setTrfVol(Long trfVol) {
        this.trfVol = trfVol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSggCd() {
        return sggCd;
    }

    public void setSggCd(String sggCd) {
        this.sggCd = sggCd;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Long getRnk() {
        return rnk;
    }

    public void setRnk(Long rnk) {
        this.rnk = rnk;
    }

    public String getAdsiNm() {
        return adsiNm;
    }

    public void setAdsiNm(String adsiNm) {
        this.adsiNm = adsiNm;
    }

    public String getAdstdgNm() {
        return adstdgNm;
    }

    public void setAdstdgNm(String adstdgNm) {
        this.adstdgNm = adstdgNm;
    }

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public String getVhclDiv() {
        return vhclDiv;
    }

    public void setVhclDiv(String vhclDiv) {
        this.vhclDiv = vhclDiv;
    }

    public String getDrctCd() {
        return drctCd;
    }

    public void setDrctCd(String drctCd) {
        this.drctCd = drctCd;
    }

    public String getCrsrdNm() {
        return crsrdNm;
    }

    public void setCrsrdNm(String crsrdNm) {
        this.crsrdNm = crsrdNm;
    }

    public Double getLonCrdn() {
        return lonCrdn;
    }

    public void setLonCrdn(Double lonCrdn) {
        this.lonCrdn = lonCrdn;
    }

    public Double getLatCrdn() {
        return latCrdn;
    }

    public void setLatCrdn(Double latCrdn) {
        this.latCrdn = latCrdn;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
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

    public String getAcsRoadNm() {
        return acsRoadNm;
    }

    public void setAcsRoadNm(String acsRoadNm) {
        this.acsRoadNm = acsRoadNm;
    }

    public Long getStrghtTrfvlm() {
        return strghtTrfvlm;
    }

    public void setStrghtTrfvlm(Long strghtTrfvlm) {
        this.strghtTrfvlm = strghtTrfvlm;
    }

    public Long getTrnghtTrfvlm() {
        return trnghtTrfvlm;
    }

    public void setTrnghtTrfvlm(Long trnghtTrfvlm) {
        this.trnghtTrfvlm = trnghtTrfvlm;
    }

    public Long getTrnlftTrfvlm() {
        return trnlftTrfvlm;
    }

    public void setTrnlftTrfvlm(Long trnlftTrfvlm) {
        this.trnlftTrfvlm = trnlftTrfvlm;
    }

    public Long getTrfvlmTotal() {
        return trfvlmTotal;
    }

    public void setTrfvlmTotal(Long trfvlmTotal) {
        this.trfvlmTotal = trfvlmTotal;
    }

    public Long getTrnutrnTrfvlm() {
        return trnutrnTrfvlm;
    }

    public void setTrnutrnTrfvlm(Long trnutrnTrfvlm) {
        this.trnutrnTrfvlm = trnutrnTrfvlm;
    }
}

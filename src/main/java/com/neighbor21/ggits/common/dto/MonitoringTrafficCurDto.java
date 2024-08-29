package com.neighbor21.ggits.common.dto;

public class MonitoringTrafficCurDto {
    private Double avgSpeed;
    private Long trfVol;
    private String time;
    private String sggCd;
    private String adsiNm;
    private String adstdgNm;
    private String roadName;
    private String linkId;
    private Long rnk;

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
}

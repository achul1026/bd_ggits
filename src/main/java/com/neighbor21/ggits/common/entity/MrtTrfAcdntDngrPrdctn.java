package com.neighbor21.ggits.common.entity;

public class MrtTrfAcdntDngrPrdctn {
    private String linkId;        //링크 아이디
    private long speed;        //속도
    private long safeIdex;        //안전 지수
    private String safeGrd;        //안전 등급

    private Long noneLinkCntBySgg;
    private Long speedOverCntBySgg;
    private Long speedUnderCntBySgg;
    private Long safeCntBySgg;
    private Long warnCntBySgg;
    private Long dangerCntBySgg;
    private Long seriousCntBySgg;
    private String sggCd;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }


    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }


    public long getSafeIdex() {
        return safeIdex;
    }

    public void setSafeIdex(long safeIdex) {
        this.safeIdex = safeIdex;
    }


    public String getSafeGrd() {
        return safeGrd;
    }

    public void setSafeGrd(String safeGrd) {
        this.safeGrd = safeGrd;
    }

    public Long getNoneLinkCntBySgg() {
        return noneLinkCntBySgg;
    }

    public void setNoneLinkCntBySgg(Long noneLinkCntBySgg) {
        this.noneLinkCntBySgg = noneLinkCntBySgg;
    }

    public Long getSpeedOverCntBySgg() {
        return speedOverCntBySgg;
    }

    public void setSpeedOverCntBySgg(Long speedOverCntBySgg) {
        this.speedOverCntBySgg = speedOverCntBySgg;
    }

    public Long getSpeedUnderCntBySgg() {
        return speedUnderCntBySgg;
    }

    public void setSpeedUnderCntBySgg(Long speedUnderCntBySgg) {
        this.speedUnderCntBySgg = speedUnderCntBySgg;
    }

    public Long getSafeCntBySgg() {
        return safeCntBySgg;
    }

    public void setSafeCntBySgg(Long safeCntBySgg) {
        this.safeCntBySgg = safeCntBySgg;
    }

    public Long getWarnCntBySgg() {
        return warnCntBySgg;
    }

    public void setWarnCntBySgg(Long warnCntBySgg) {
        this.warnCntBySgg = warnCntBySgg;
    }

    public Long getDangerCntBySgg() {
        return dangerCntBySgg;
    }

    public void setDangerCntBySgg(Long dangerCntBySgg) {
        this.dangerCntBySgg = dangerCntBySgg;
    }

    public Long getSeriousCntBySgg() {
        return seriousCntBySgg;
    }

    public void setSeriousCntBySgg(Long seriousCntBySgg) {
        this.seriousCntBySgg = seriousCntBySgg;
    }

    public String getSggCd() {
        return sggCd;
    }

    public void setSggCd(String sggCd) {
        this.sggCd = sggCd;
    }
}

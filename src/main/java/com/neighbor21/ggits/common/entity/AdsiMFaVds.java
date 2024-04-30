package com.neighbor21.ggits.common.entity;

import java.util.List;

//vds 시설물 정보
public class AdsiMFaVds extends AdsiVdsSttsInfo {

    private String mngInstCd; //관리 기관 코드
    private String vdsId; //vds 아이디
    private String vdsNm; //vds 명
    private String vdsType; //vds 유형
    private Long laneCnt; //차로 수
    private Double lon; //경도
    private Double lat; //위도
    private String descr; //설명


    private String vdsSctnId; //vds 구간 아이디
    private String vdsSctnNm; //vds 구간 명
    private Long vdsSctnLen; //vds 구간 길이
    private String roadGrd; //도로 등급
    private Long minLimitSpeed; //최소 제한 속도
    private Long maxLimitSpeed; //최대 제한 속도

    private String colctInfo;

    private List<AdsiVdsSctnStlinkMppg> sectionLinkList;

    @Override
    public String getMngInstCd() {
        return mngInstCd;
    }

    @Override
    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    @Override
    public String getVdsId() {
        return vdsId;
    }

    @Override
    public void setVdsId(String vdsId) {
        this.vdsId = vdsId;
    }

    public String getVdsNm() {
        return vdsNm;
    }

    public void setVdsNm(String vdsNm) {
        this.vdsNm = vdsNm;
    }

    public String getVdsType() {
        return vdsType;
    }

    public void setVdsType(String vdsType) {
        this.vdsType = vdsType;
    }

    public Long getLaneCnt() {
        return laneCnt;
    }

    public void setLaneCnt(Long laneCnt) {
        this.laneCnt = laneCnt;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getVdsSctnId() {
        return vdsSctnId;
    }

    public void setVdsSctnId(String vdsSctnId) {
        this.vdsSctnId = vdsSctnId;
    }

    public String getVdsSctnNm() {
        return vdsSctnNm;
    }

    public void setVdsSctnNm(String vdsSctnNm) {
        this.vdsSctnNm = vdsSctnNm;
    }

    public Long getVdsSctnLen() {
        return vdsSctnLen;
    }

    public void setVdsSctnLen(Long vdsSctnLen) {
        this.vdsSctnLen = vdsSctnLen;
    }

    public String getRoadGrd() {
        return roadGrd;
    }

    public void setRoadGrd(String roadGrd) {
        this.roadGrd = roadGrd;
    }

    public Long getMinLimitSpeed() {
        return minLimitSpeed;
    }

    public void setMinLimitSpeed(Long minLimitSpeed) {
        this.minLimitSpeed = minLimitSpeed;
    }

    public Long getMaxLimitSpeed() {
        return maxLimitSpeed;
    }

    public void setMaxLimitSpeed(Long maxLimitSpeed) {
        this.maxLimitSpeed = maxLimitSpeed;
    }

    public String getColctInfo() {
        return colctInfo;
    }

    public void setColctInfo(String colctInfo) {
        this.colctInfo = colctInfo;
    }

    public List<AdsiVdsSctnStlinkMppg> getSectionLinkList() {
        return sectionLinkList;
    }

    public void setSectionLinkList(List<AdsiVdsSctnStlinkMppg> sectionLinkList) {
        this.sectionLinkList = sectionLinkList;
    }
}

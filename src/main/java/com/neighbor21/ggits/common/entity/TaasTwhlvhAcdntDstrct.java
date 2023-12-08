package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//이륜차 사고 구역
public class TaasTwhlvhAcdntDstrct {

  private Timestamp clctDt; //수집 일시
  private String acdntDstrctIdentifier; //사고 구역 식별자
  private String acdntDstrctId; //사고 구역 아이디
  private String adstdgCd; //법정동 코드
  private String pointCd; //지점 코드
  private String adsiNm; //행정시 명
  private String pointNm; //지점 명
  private long acdntCnt; //사고 수
  private long casltCnt; //사상자 수
  private long dcsdCnt; //사망자 수
  private long swpsnCnt; //중상자 수
  private long sinjpsnCnt; //경상자 수
  private long injDclrCnt; //부상 신고 수
  private double lonCrdn; //경도 좌표
  private double latCrdn; //위도 좌표
  private String acdntDstrctPyn; //사고 구역 폴리곤


  public Timestamp getClctDt() {
    return clctDt;
  }

  public void setClctDt(Timestamp clctDt) {
    this.clctDt = clctDt;
  }


  public String getAcdntDstrctIdentifier() {
    return acdntDstrctIdentifier;
  }

  public void setAcdntDstrctIdentifier(String acdntDstrctIdentifier) {
    this.acdntDstrctIdentifier = acdntDstrctIdentifier;
  }


  public String getAcdntDstrctId() {
    return acdntDstrctId;
  }

  public void setAcdntDstrctId(String acdntDstrctId) {
    this.acdntDstrctId = acdntDstrctId;
  }


  public String getAdstdgCd() {
    return adstdgCd;
  }

  public void setAdstdgCd(String adstdgCd) {
    this.adstdgCd = adstdgCd;
  }


  public String getPointCd() {
    return pointCd;
  }

  public void setPointCd(String pointCd) {
    this.pointCd = pointCd;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getPointNm() {
    return pointNm;
  }

  public void setPointNm(String pointNm) {
    this.pointNm = pointNm;
  }


  public long getAcdntCnt() {
    return acdntCnt;
  }

  public void setAcdntCnt(long acdntCnt) {
    this.acdntCnt = acdntCnt;
  }


  public long getCasltCnt() {
    return casltCnt;
  }

  public void setCasltCnt(long casltCnt) {
    this.casltCnt = casltCnt;
  }


  public long getDcsdCnt() {
    return dcsdCnt;
  }

  public void setDcsdCnt(long dcsdCnt) {
    this.dcsdCnt = dcsdCnt;
  }


  public long getSwpsnCnt() {
    return swpsnCnt;
  }

  public void setSwpsnCnt(long swpsnCnt) {
    this.swpsnCnt = swpsnCnt;
  }


  public long getSinjpsnCnt() {
    return sinjpsnCnt;
  }

  public void setSinjpsnCnt(long sinjpsnCnt) {
    this.sinjpsnCnt = sinjpsnCnt;
  }


  public long getInjDclrCnt() {
    return injDclrCnt;
  }

  public void setInjDclrCnt(long injDclrCnt) {
    this.injDclrCnt = injDclrCnt;
  }


  public double getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(double lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public double getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(double latCrdn) {
    this.latCrdn = latCrdn;
  }


  public String getAcdntDstrctPyn() {
    return acdntDstrctPyn;
  }

  public void setAcdntDstrctPyn(String acdntDstrctPyn) {
    this.acdntDstrctPyn = acdntDstrctPyn;
  }

}

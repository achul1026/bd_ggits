package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//자전거 사고 구역
public class TaasAcdntDstrctMaster {

  private Timestamp clctDt; //수집 일시
  private String acdntDstrctIdentifier; //사고 구역 식별자
  private String acdntDstrctId; //사고 구역 아이디
  private String adstdgCd; //법정동 코드
  private String adstdgNm; //법정동 이름
  private String pointCd; //지점 코드
  private String adsiNm; //행정시 명
  private String pointNm; //지점 명
  private Long acdntCnt; //사고 수
  private Long casltCnt; //사상자 수
  private Long dcsdCnt; //사망자 수
  private Long swpsnCnt; //중상자 수
  private Long sinjpsnCnt; //경상자 수
  private Long injDclrCnt; //부상 신고 수
  private Double lonCrdn; //경도 좌표
  private Double latCrdn; //위도 좌표
  private String acdntDstrctPyn; //사고 구역 폴리곤
  private String type; // 타입

  private String sggCd;

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

  public String getAdstdgNm() {
    return adstdgNm;
  }

  public void setAdstdgNm(String adstdgNm) {
    this.adstdgNm = adstdgNm;
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

  public Long getAcdntCnt() {
    return acdntCnt;
  }

  public void setAcdntCnt(Long acdntCnt) {
    this.acdntCnt = acdntCnt;
  }

  public Long getCasltCnt() {
    return casltCnt;
  }

  public void setCasltCnt(Long casltCnt) {
    this.casltCnt = casltCnt;
  }

  public Long getDcsdCnt() {
    return dcsdCnt;
  }

  public void setDcsdCnt(Long dcsdCnt) {
    this.dcsdCnt = dcsdCnt;
  }

  public Long getSwpsnCnt() {
    return swpsnCnt;
  }

  public void setSwpsnCnt(Long swpsnCnt) {
    this.swpsnCnt = swpsnCnt;
  }

  public Long getSinjpsnCnt() {
    return sinjpsnCnt;
  }

  public void setSinjpsnCnt(Long sinjpsnCnt) {
    this.sinjpsnCnt = sinjpsnCnt;
  }

  public Long getInjDclrCnt() {
    return injDclrCnt;
  }

  public void setInjDclrCnt(Long injDclrCnt) {
    this.injDclrCnt = injDclrCnt;
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

  public String getAcdntDstrctPyn() {
    return acdntDstrctPyn;
  }

  public void setAcdntDstrctPyn(String acdntDstrctPyn) {
    this.acdntDstrctPyn = acdntDstrctPyn;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSggCd() {
    return sggCd;
  }

  public void setSggCd(String sggCd) {
    this.sggCd = sggCd;
  }

  public static TaasAcdntDstrctMaster merge(TaasAcdntDstrctMaster current, TaasAcdntDstrctMaster next) {
    current.setSwpsnCnt(current.getSwpsnCnt()+next.getSwpsnCnt());
    current.setSinjpsnCnt(current.getSinjpsnCnt()+next.getSinjpsnCnt());
    current.setInjDclrCnt(current.getInjDclrCnt()+next.getInjDclrCnt());
    current.setCasltCnt(current.getCasltCnt()+next.getCasltCnt());
    current.setDcsdCnt(current.getDcsdCnt()+next.getDcsdCnt());
    current.setAcdntCnt(current.getAcdntCnt()+next.getAcdntCnt());
    return current;
  }
}

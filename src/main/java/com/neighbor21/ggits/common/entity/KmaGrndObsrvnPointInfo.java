package com.neighbor21.ggits.common.entity;
public class KmaGrndObsrvnPointInfo {
    private String    pointNo;        //지점 번호
    private String    pointNm;        //지점 명
    private String    adstdgCd;        //법정동 코드
    private long    lonCrdn;        //경도 좌표
    private long    latCrdn;        //위도 좌표
    private String    etlDt;        //etl 일시


  public String getPointNo() {
    return pointNo;
  }

  public void setPointNo(String pointNo) {
    this.pointNo = pointNo;
  }


  public String getPointNm() {
    return pointNm;
  }

  public void setPointNm(String pointNm) {
    this.pointNm = pointNm;
  }


  public String getAdstdgCd() {
    return adstdgCd;
  }

  public void setAdstdgCd(String adstdgCd) {
    this.adstdgCd = adstdgCd;
  }


  public long getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(long lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(long latCrdn) {
    this.latCrdn = latCrdn;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

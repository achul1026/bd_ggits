package com.neighbor21.ggits.common.entity;

//도로 위험 상태 예보
public class UticRoadDngrSttsFrcst {

  private String safeDataId; //안전 데이터 아이디
  private String dngrSttsNm; //위험 상태 명
  private String dngrSttsGrd; //위험 상태 등급
  private String dataType; //데이터 유형
  private String pcttType; //강수량 유형
  private String timePctt; //시간 강수량
  private long tmprt; //온도
  private String dngrSttsImg; //위험 상태 이미지
  private String linkId; //링크 아이디
  private double lonCrdn; //경도 좌표
  private double latCrdn; //위도 좌표
  private String lotnoAddr; //지번 주소
  private String roadNmAddr; //도로명 주소
  private String totCrdnData; //전체 좌표 데이터


  public String getSafeDataId() {
    return safeDataId;
  }

  public void setSafeDataId(String safeDataId) {
    this.safeDataId = safeDataId;
  }


  public String getDngrSttsNm() {
    return dngrSttsNm;
  }

  public void setDngrSttsNm(String dngrSttsNm) {
    this.dngrSttsNm = dngrSttsNm;
  }


  public String getDngrSttsGrd() {
    return dngrSttsGrd;
  }

  public void setDngrSttsGrd(String dngrSttsGrd) {
    this.dngrSttsGrd = dngrSttsGrd;
  }


  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }


  public String getPcttType() {
    return pcttType;
  }

  public void setPcttType(String pcttType) {
    this.pcttType = pcttType;
  }


  public String getTimePctt() {
    return timePctt;
  }

  public void setTimePctt(String timePctt) {
    this.timePctt = timePctt;
  }


  public long getTmprt() {
    return tmprt;
  }

  public void setTmprt(long tmprt) {
    this.tmprt = tmprt;
  }


  public String getDngrSttsImg() {
    return dngrSttsImg;
  }

  public void setDngrSttsImg(String dngrSttsImg) {
    this.dngrSttsImg = dngrSttsImg;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
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


  public String getLotnoAddr() {
    return lotnoAddr;
  }

  public void setLotnoAddr(String lotnoAddr) {
    this.lotnoAddr = lotnoAddr;
  }


  public String getRoadNmAddr() {
    return roadNmAddr;
  }

  public void setRoadNmAddr(String roadNmAddr) {
    this.roadNmAddr = roadNmAddr;
  }


  public String getTotCrdnData() {
    return totCrdnData;
  }

  public void setTotCrdnData(String totCrdnData) {
    this.totCrdnData = totCrdnData;
  }

}

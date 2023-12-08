package com.neighbor21.ggits.common.entity;

//버스 터미널 정보
public class GgdtdrBusTrmnlInfo {

  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String trmnlNm; //터미널 명
  private String usgDstrctDivNm; //용도 구역 구분 명
  private String rprsvNm; //대표자 명
  private String lcnsYmdInfo; //면허 일자 정보
  private long routeCnt; //노선 수
  private long ddUserCnt; //일 사용자 수
  private long totar; //연면적
  private String dataCrtrYmd; //데이터 기준 일자
  private String locplcZip; //소재지 우편번호
  private String locplcLotnoAddr; //소재지 지번 주소
  private String locplcRoadNmAddr; //소재지 도로명 주소
  private double latCrdn; //위도 좌표
  private double lonCrdn; //경도 좌표


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public String getTrmnlNm() {
    return trmnlNm;
  }

  public void setTrmnlNm(String trmnlNm) {
    this.trmnlNm = trmnlNm;
  }


  public String getUsgDstrctDivNm() {
    return usgDstrctDivNm;
  }

  public void setUsgDstrctDivNm(String usgDstrctDivNm) {
    this.usgDstrctDivNm = usgDstrctDivNm;
  }


  public String getRprsvNm() {
    return rprsvNm;
  }

  public void setRprsvNm(String rprsvNm) {
    this.rprsvNm = rprsvNm;
  }


  public String getLcnsYmdInfo() {
    return lcnsYmdInfo;
  }

  public void setLcnsYmdInfo(String lcnsYmdInfo) {
    this.lcnsYmdInfo = lcnsYmdInfo;
  }


  public long getRouteCnt() {
    return routeCnt;
  }

  public void setRouteCnt(long routeCnt) {
    this.routeCnt = routeCnt;
  }


  public long getDdUserCnt() {
    return ddUserCnt;
  }

  public void setDdUserCnt(long ddUserCnt) {
    this.ddUserCnt = ddUserCnt;
  }


  public long getTotar() {
    return totar;
  }

  public void setTotar(long totar) {
    this.totar = totar;
  }


  public String getDataCrtrYmd() {
    return dataCrtrYmd;
  }

  public void setDataCrtrYmd(String dataCrtrYmd) {
    this.dataCrtrYmd = dataCrtrYmd;
  }


  public String getLocplcZip() {
    return locplcZip;
  }

  public void setLocplcZip(String locplcZip) {
    this.locplcZip = locplcZip;
  }


  public String getLocplcLotnoAddr() {
    return locplcLotnoAddr;
  }

  public void setLocplcLotnoAddr(String locplcLotnoAddr) {
    this.locplcLotnoAddr = locplcLotnoAddr;
  }


  public String getLocplcRoadNmAddr() {
    return locplcRoadNmAddr;
  }

  public void setLocplcRoadNmAddr(String locplcRoadNmAddr) {
    this.locplcRoadNmAddr = locplcRoadNmAddr;
  }

  public double getLatCrdn() {
	  return latCrdn;
	}
  public void setLatCrdn(double latCrdn) {
	this.latCrdn = latCrdn;
  }

  public double getLonCrdn() {
	return lonCrdn;
  }

  public void setLonCrdn(double lonCrdn) {
	this.lonCrdn = lonCrdn;
  }




}

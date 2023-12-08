package com.neighbor21.ggits.common.entity;

//어린이보호구역 정보
public class GgdtdrSchznInfo {

  private String schznFcltsTypeNm; //어린이보호구역 시설물 유형 명
  private String schznFcltsNm; //어린이보호구역  시설물 명
  private String locplcRoadNmAddr; //소재지 도로명 주소
  private String locplcLotnoAddr; //소재지 지번 주소
  private double latCrdn; //위도 좌표
  private double lonCrdn; //경도 좌표
  private String mngInstNm; //관리 기관 명
  private String cmptncPolcsttnNm; //관할 경찰서 명
  private String cctvInstlYn; //cctv 설치 여부
  private long cctvInstlCnt; //cctv 설치 수
  private long schznRoadWidth; //어린이보호구역 도로 너비
  private String dataCrtrYmd; //데이터 기준 일자
  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String locplcZip; //소재지 우편번호


  public String getSchznFcltsTypeNm() {
    return schznFcltsTypeNm;
  }

  public void setSchznFcltsTypeNm(String schznFcltsTypeNm) {
    this.schznFcltsTypeNm = schznFcltsTypeNm;
  }


  public String getSchznFcltsNm() {
    return schznFcltsNm;
  }

  public void setSchznFcltsNm(String schznFcltsNm) {
    this.schznFcltsNm = schznFcltsNm;
  }


  public String getLocplcRoadNmAddr() {
    return locplcRoadNmAddr;
  }

  public void setLocplcRoadNmAddr(String locplcRoadNmAddr) {
    this.locplcRoadNmAddr = locplcRoadNmAddr;
  }


  public String getLocplcLotnoAddr() {
    return locplcLotnoAddr;
  }

  public void setLocplcLotnoAddr(String locplcLotnoAddr) {
    this.locplcLotnoAddr = locplcLotnoAddr;
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


  public String getMngInstNm() {
    return mngInstNm;
  }

  public void setMngInstNm(String mngInstNm) {
    this.mngInstNm = mngInstNm;
  }


  public String getCmptncPolcsttnNm() {
    return cmptncPolcsttnNm;
  }

  public void setCmptncPolcsttnNm(String cmptncPolcsttnNm) {
    this.cmptncPolcsttnNm = cmptncPolcsttnNm;
  }


  public String getCctvInstlYn() {
    return cctvInstlYn;
  }

  public void setCctvInstlYn(String cctvInstlYn) {
    this.cctvInstlYn = cctvInstlYn;
  }


  public long getCctvInstlCnt() {
    return cctvInstlCnt;
  }

  public void setCctvInstlCnt(long cctvInstlCnt) {
    this.cctvInstlCnt = cctvInstlCnt;
  }


  public long getSchznRoadWidth() {
    return schznRoadWidth;
  }

  public void setSchznRoadWidth(long schznRoadWidth) {
    this.schznRoadWidth = schznRoadWidth;
  }


  public String getDataCrtrYmd() {
    return dataCrtrYmd;
  }

  public void setDataCrtrYmd(String dataCrtrYmd) {
    this.dataCrtrYmd = dataCrtrYmd;
  }


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


  public String getLocplcZip() {
    return locplcZip;
  }

  public void setLocplcZip(String locplcZip) {
    this.locplcZip = locplcZip;
  }

}

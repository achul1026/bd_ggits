package com.neighbor21.ggits.common.entity;

//주차장 정보
public class GgdtdrPkplcInfo {

  private String adsiCd; //행정시 코드
  private String adsiNm; //행정시 명
  private String pkplcMngNo; //주차장 관리 번호
  private String pkplcNm; //주차장 명
  private String pkplcDiv; //주차장 구분
  private String pkplcType; //주차장 유형
  private String locplcRoadNmAddr; //소재지 도로명 주소
  private String locplcLotnoAddr; //소재지 지번 주소
  private long pklotCnt; //주차구획 수
  private String lndlvDiv; //급지 구분
  private String pkrstOprtDiv; //주차부제 운영 구분
  private String oprtDywk; //운영 요일
  private String wkdayOprtStartTime; //평일 운영 시작 시간
  private String wkdayOprtEndTime; //평일 운영 종료 시간
  private String satOprtStartTime; //토요일 운영 시작 시간
  private String satOprtEndTime; //토요일 운영 종료 시간
  private String hldyOprtStartTime; //휴일 운영 시작 시간
  private String hldyOprtEndTime; //휴일 운영 종료 시간
  private String fareInfo; //요금 정보
  private long parkngBscTime; //주차 기본 시간
  private long parkngBscFare; //주차 기본 요금
  private long addUnitTime; //추가 단위 시간
  private long addUnitFare; //추가 단위 요금
  private long ddPktckFareAplcnTime; //일 주차권 요금 적용 시간
  private long ddPktckFare; //일 주차권 요금
  private long mmCmmtktFare; //월 정기권 요금
  private String pmntMthd; //결제 방법
  private String ptmatInfo; //특이사항 정보
  private String mngInstNm; //관리 기관 명
  private String tel; //전화번호
  private double latCrdn; //위도 좌표
  private double lonCrdn; //경도 좌표
  private String dataCrtrYmd; //데이터 기준 일자
  private String locplcZip; //소재지 우편번호


  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getPkplcMngNo() {
    return pkplcMngNo;
  }

  public void setPkplcMngNo(String pkplcMngNo) {
    this.pkplcMngNo = pkplcMngNo;
  }


  public String getPkplcNm() {
    return pkplcNm;
  }

  public void setPkplcNm(String pkplcNm) {
    this.pkplcNm = pkplcNm;
  }


  public String getPkplcDiv() {
    return pkplcDiv;
  }

  public void setPkplcDiv(String pkplcDiv) {
    this.pkplcDiv = pkplcDiv;
  }


  public String getPkplcType() {
    return pkplcType;
  }

  public void setPkplcType(String pkplcType) {
    this.pkplcType = pkplcType;
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


  public long getPklotCnt() {
    return pklotCnt;
  }

  public void setPklotCnt(long pklotCnt) {
    this.pklotCnt = pklotCnt;
  }


  public String getLndlvDiv() {
    return lndlvDiv;
  }

  public void setLndlvDiv(String lndlvDiv) {
    this.lndlvDiv = lndlvDiv;
  }


  public String getPkrstOprtDiv() {
    return pkrstOprtDiv;
  }

  public void setPkrstOprtDiv(String pkrstOprtDiv) {
    this.pkrstOprtDiv = pkrstOprtDiv;
  }


  public String getOprtDywk() {
    return oprtDywk;
  }

  public void setOprtDywk(String oprtDywk) {
    this.oprtDywk = oprtDywk;
  }


  public String getWkdayOprtStartTime() {
    return wkdayOprtStartTime;
  }

  public void setWkdayOprtStartTime(String wkdayOprtStartTime) {
    this.wkdayOprtStartTime = wkdayOprtStartTime;
  }


  public String getWkdayOprtEndTime() {
    return wkdayOprtEndTime;
  }

  public void setWkdayOprtEndTime(String wkdayOprtEndTime) {
    this.wkdayOprtEndTime = wkdayOprtEndTime;
  }


  public String getSatOprtStartTime() {
    return satOprtStartTime;
  }

  public void setSatOprtStartTime(String satOprtStartTime) {
    this.satOprtStartTime = satOprtStartTime;
  }


  public String getSatOprtEndTime() {
    return satOprtEndTime;
  }

  public void setSatOprtEndTime(String satOprtEndTime) {
    this.satOprtEndTime = satOprtEndTime;
  }


  public String getHldyOprtStartTime() {
    return hldyOprtStartTime;
  }

  public void setHldyOprtStartTime(String hldyOprtStartTime) {
    this.hldyOprtStartTime = hldyOprtStartTime;
  }


  public String getHldyOprtEndTime() {
    return hldyOprtEndTime;
  }

  public void setHldyOprtEndTime(String hldyOprtEndTime) {
    this.hldyOprtEndTime = hldyOprtEndTime;
  }


  public String getFareInfo() {
    return fareInfo;
  }

  public void setFareInfo(String fareInfo) {
    this.fareInfo = fareInfo;
  }


  public long getParkngBscTime() {
    return parkngBscTime;
  }

  public void setParkngBscTime(long parkngBscTime) {
    this.parkngBscTime = parkngBscTime;
  }


  public long getParkngBscFare() {
    return parkngBscFare;
  }

  public void setParkngBscFare(long parkngBscFare) {
    this.parkngBscFare = parkngBscFare;
  }


  public long getAddUnitTime() {
    return addUnitTime;
  }

  public void setAddUnitTime(long addUnitTime) {
    this.addUnitTime = addUnitTime;
  }


  public long getAddUnitFare() {
    return addUnitFare;
  }

  public void setAddUnitFare(long addUnitFare) {
    this.addUnitFare = addUnitFare;
  }


  public long getDdPktckFareAplcnTime() {
    return ddPktckFareAplcnTime;
  }

  public void setDdPktckFareAplcnTime(long ddPktckFareAplcnTime) {
    this.ddPktckFareAplcnTime = ddPktckFareAplcnTime;
  }


  public long getDdPktckFare() {
    return ddPktckFare;
  }

  public void setDdPktckFare(long ddPktckFare) {
    this.ddPktckFare = ddPktckFare;
  }


  public long getMmCmmtktFare() {
    return mmCmmtktFare;
  }

  public void setMmCmmtktFare(long mmCmmtktFare) {
    this.mmCmmtktFare = mmCmmtktFare;
  }


  public String getPmntMthd() {
    return pmntMthd;
  }

  public void setPmntMthd(String pmntMthd) {
    this.pmntMthd = pmntMthd;
  }


  public String getPtmatInfo() {
    return ptmatInfo;
  }

  public void setPtmatInfo(String ptmatInfo) {
    this.ptmatInfo = ptmatInfo;
  }


  public String getMngInstNm() {
    return mngInstNm;
  }

  public void setMngInstNm(String mngInstNm) {
    this.mngInstNm = mngInstNm;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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

}

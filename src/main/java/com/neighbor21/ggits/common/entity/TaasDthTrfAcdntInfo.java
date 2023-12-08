package com.neighbor21.ggits.common.entity;

//사망 교통 사고 정보
public class TaasDthTrfAcdntInfo {

  private String acdntYy; //사고 년
  private String occurYyMmDdTime; //발생 년 월 일 시간
  private String occurAddoCd; //발생 행정도 코드
  private String occurAdsiCd; //발생 행정시 코드
  private String wekdyntDivCd; //주야간 구분 코드
  private String dywkCd; //요일 코드
  private Long dcsdCnt; //사망자 수
  private Long injpsnCnt; //부상자 수
  private Long swpsnCnt; //중상자 수
  private Long sinjpsnCnt; //경상자 수
  private Long injDclrCnt; //부상 신고 수
  private String acdntTypeLclsfCd; //사고 유형 대분류 코드
  private String acdntTypeMclsfCd; //사고 유형 중분류 코드
  private String acdntTypeCd; //사고 유형 코드
  private String wrngdorLawVltnCd; //가해자 법 위반 코드
  private String roadTypeLclsfCd; //도로 유형 대분류 코드
  private String roadTypeCd; //도로 유형 코드
  private String wrngdoIsrtyVhcclsLclsfCd; //가해 당사자 차종 대분류 코드
  private String dmgeIsrtyVhcclsLclsfCd; //피해 당사자 차종 대분류 코드
  private Long xcord; //x좌표
  private Long ycord; //y좌표
  private Double lonCrdn; //경도 좌표
  private Double latCrdn; //위도 좌표

  public String getAcdntYy() {
    return acdntYy;
  }

  public void setAcdntYy(String acdntYy) {
    this.acdntYy = acdntYy;
  }

  public String getOccurYyMmDdTime() {
    return occurYyMmDdTime;
  }

  public void setOccurYyMmDdTime(String occurYyMmDdTime) {
    this.occurYyMmDdTime = occurYyMmDdTime;
  }

  public String getOccurAddoCd() {
    return occurAddoCd;
  }

  public void setOccurAddoCd(String occurAddoCd) {
    this.occurAddoCd = occurAddoCd;
  }

  public String getOccurAdsiCd() {
    return occurAdsiCd;
  }

  public void setOccurAdsiCd(String occurAdsiCd) {
    this.occurAdsiCd = occurAdsiCd;
  }

  public String getWekdyntDivCd() {
    return wekdyntDivCd;
  }

  public void setWekdyntDivCd(String wekdyntDivCd) {
    this.wekdyntDivCd = wekdyntDivCd;
  }

  public String getDywkCd() {
    return dywkCd;
  }

  public void setDywkCd(String dywkCd) {
    this.dywkCd = dywkCd;
  }

  public Long getDcsdCnt() {
    return dcsdCnt;
  }

  public void setDcsdCnt(Long dcsdCnt) {
    this.dcsdCnt = dcsdCnt;
  }

  public Long getInjpsnCnt() {
    return injpsnCnt;
  }

  public void setInjpsnCnt(Long injpsnCnt) {
    this.injpsnCnt = injpsnCnt;
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

  public String getAcdntTypeLclsfCd() {
    return acdntTypeLclsfCd;
  }

  public void setAcdntTypeLclsfCd(String acdntTypeLclsfCd) {
    this.acdntTypeLclsfCd = acdntTypeLclsfCd;
  }

  public String getAcdntTypeMclsfCd() {
    return acdntTypeMclsfCd;
  }

  public void setAcdntTypeMclsfCd(String acdntTypeMclsfCd) {
    this.acdntTypeMclsfCd = acdntTypeMclsfCd;
  }

  public String getAcdntTypeCd() {
    return acdntTypeCd;
  }

  public void setAcdntTypeCd(String acdntTypeCd) {
    this.acdntTypeCd = acdntTypeCd;
  }

  public String getWrngdorLawVltnCd() {
    return wrngdorLawVltnCd;
  }

  public void setWrngdorLawVltnCd(String wrngdorLawVltnCd) {
    this.wrngdorLawVltnCd = wrngdorLawVltnCd;
  }

  public String getRoadTypeLclsfCd() {
    return roadTypeLclsfCd;
  }

  public void setRoadTypeLclsfCd(String roadTypeLclsfCd) {
    this.roadTypeLclsfCd = roadTypeLclsfCd;
  }

  public String getRoadTypeCd() {
    return roadTypeCd;
  }

  public void setRoadTypeCd(String roadTypeCd) {
    this.roadTypeCd = roadTypeCd;
  }

  public String getWrngdoIsrtyVhcclsLclsfCd() {
    return wrngdoIsrtyVhcclsLclsfCd;
  }

  public void setWrngdoIsrtyVhcclsLclsfCd(String wrngdoIsrtyVhcclsLclsfCd) {
    this.wrngdoIsrtyVhcclsLclsfCd = wrngdoIsrtyVhcclsLclsfCd;
  }

  public String getDmgeIsrtyVhcclsLclsfCd() {
    return dmgeIsrtyVhcclsLclsfCd;
  }

  public void setDmgeIsrtyVhcclsLclsfCd(String dmgeIsrtyVhcclsLclsfCd) {
    this.dmgeIsrtyVhcclsLclsfCd = dmgeIsrtyVhcclsLclsfCd;
  }

  public Long getXcord() {
    return xcord;
  }

  public void setXcord(Long xcord) {
    this.xcord = xcord;
  }

  public Long getYcord() {
    return ycord;
  }

  public void setYcord(Long ycord) {
    this.ycord = ycord;
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
}

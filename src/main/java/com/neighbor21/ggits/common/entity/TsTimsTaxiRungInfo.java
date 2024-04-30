package com.neighbor21.ggits.common.entity;
public class TsTimsTaxiRungInfo {
    private String    pmntYmd;        //결제 일자
    private String    vin;        //차대번호
    private String    vhclType;        //차량 유형
    private String    vhclRegistNo;        //차량 등록 번호
    private String    rideDt;        //승차 일시
    private long    rideLonCrdn;        //승차 경도 좌표
    private long    rideLatCrdn;        //승차 위도 좌표
    private String    lndiDt;        //하차 일시
    private long    lndiLonCrdn;        //하차 경도 좌표
    private long    lndiLatCrdn;        //하차 위도 좌표
    private long    rideDstne;        //승차 거리
    private String    etlDt;        //etl 일시
    private String    inputFileNm;        //입력 파일 명


  public String getPmntYmd() {
    return pmntYmd;
  }

  public void setPmntYmd(String pmntYmd) {
    this.pmntYmd = pmntYmd;
  }


  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }


  public String getVhclType() {
    return vhclType;
  }

  public void setVhclType(String vhclType) {
    this.vhclType = vhclType;
  }


  public String getVhclRegistNo() {
    return vhclRegistNo;
  }

  public void setVhclRegistNo(String vhclRegistNo) {
    this.vhclRegistNo = vhclRegistNo;
  }


  public String getRideDt() {
    return rideDt;
  }

  public void setRideDt(String rideDt) {
    this.rideDt = rideDt;
  }


  public long getRideLonCrdn() {
    return rideLonCrdn;
  }

  public void setRideLonCrdn(long rideLonCrdn) {
    this.rideLonCrdn = rideLonCrdn;
  }


  public long getRideLatCrdn() {
    return rideLatCrdn;
  }

  public void setRideLatCrdn(long rideLatCrdn) {
    this.rideLatCrdn = rideLatCrdn;
  }


  public String getLndiDt() {
    return lndiDt;
  }

  public void setLndiDt(String lndiDt) {
    this.lndiDt = lndiDt;
  }


  public long getLndiLonCrdn() {
    return lndiLonCrdn;
  }

  public void setLndiLonCrdn(long lndiLonCrdn) {
    this.lndiLonCrdn = lndiLonCrdn;
  }


  public long getLndiLatCrdn() {
    return lndiLatCrdn;
  }

  public void setLndiLatCrdn(long lndiLatCrdn) {
    this.lndiLatCrdn = lndiLatCrdn;
  }


  public long getRideDstne() {
    return rideDstne;
  }

  public void setRideDstne(long rideDstne) {
    this.rideDstne = rideDstne;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }


  public String getInputFileNm() {
    return inputFileNm;
  }

  public void setInputFileNm(String inputFileNm) {
    this.inputFileNm = inputFileNm;
  }

}

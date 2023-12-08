package com.neighbor21.ggits.common.entity;
public class GgbisBusGarage {
    private String    garageId;        //차고지ID
    private String    garageNm;        //차고지명
    private String    address;        //차고지위치(주소)
    private String    telNo;        //전화번호
    private long    gpsX;        //GPS X좌표
    private long    gpsY;        //GPS Y좌표
    private String    companyId;        //버스회사ID


  public String getGarageId() {
    return garageId;
  }

  public void setGarageId(String garageId) {
    this.garageId = garageId;
  }


  public String getGarageNm() {
    return garageNm;
  }

  public void setGarageNm(String garageNm) {
    this.garageNm = garageNm;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getTelNo() {
    return telNo;
  }

  public void setTelNo(String telNo) {
    this.telNo = telNo;
  }


  public long getGpsX() {
    return gpsX;
  }

  public void setGpsX(long gpsX) {
    this.gpsX = gpsX;
  }


  public long getGpsY() {
    return gpsY;
  }

  public void setGpsY(long gpsY) {
    this.gpsY = gpsY;
  }


  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

}

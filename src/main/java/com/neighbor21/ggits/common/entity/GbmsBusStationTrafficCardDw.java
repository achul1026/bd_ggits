package com.neighbor21.ggits.common.entity;
public class GbmsBusStationTrafficCardDw {
    private String    rideDate;        //승차일시
    private String    stOrgCd;        //승차 지역코드
    private String    edOrgCd;        //하차 지역코드
    private String    stStationId;        //승차 정류소id
    private String    edStationId;        //하차 정류소id
    private String    stOrgNm;        //승차 지역명
    private String    edOrgNm;        //하차 지역명
    private String    stMobileNo;        //승차 정류소번호
    private String    edMobileNo;        //하차 정류소번호
    private long    stNum;        //승차통행량
    private long    edNum;        //하차통행량
    private long    edTnsNum;        //환승통행량
    private String    stNm;        //승차 정류소명
    private String    edNm;        //하차 정류소명
    private String    stEbStationId;        //승차 eb정류소 id
    private String    edEbStationId;        //하차 eb정류소 id


  public String getRideDate() {
    return rideDate;
  }

  public void setRideDate(String rideDate) {
    this.rideDate = rideDate;
  }


  public String getStOrgCd() {
    return stOrgCd;
  }

  public void setStOrgCd(String stOrgCd) {
    this.stOrgCd = stOrgCd;
  }


  public String getEdOrgCd() {
    return edOrgCd;
  }

  public void setEdOrgCd(String edOrgCd) {
    this.edOrgCd = edOrgCd;
  }


  public String getStStationId() {
    return stStationId;
  }

  public void setStStationId(String stStationId) {
    this.stStationId = stStationId;
  }


  public String getEdStationId() {
    return edStationId;
  }

  public void setEdStationId(String edStationId) {
    this.edStationId = edStationId;
  }


  public String getStOrgNm() {
    return stOrgNm;
  }

  public void setStOrgNm(String stOrgNm) {
    this.stOrgNm = stOrgNm;
  }


  public String getEdOrgNm() {
    return edOrgNm;
  }

  public void setEdOrgNm(String edOrgNm) {
    this.edOrgNm = edOrgNm;
  }


  public String getStMobileNo() {
    return stMobileNo;
  }

  public void setStMobileNo(String stMobileNo) {
    this.stMobileNo = stMobileNo;
  }


  public String getEdMobileNo() {
    return edMobileNo;
  }

  public void setEdMobileNo(String edMobileNo) {
    this.edMobileNo = edMobileNo;
  }


  public long getStNum() {
    return stNum;
  }

  public void setStNum(long stNum) {
    this.stNum = stNum;
  }


  public long getEdNum() {
    return edNum;
  }

  public void setEdNum(long edNum) {
    this.edNum = edNum;
  }


  public long getEdTnsNum() {
    return edTnsNum;
  }

  public void setEdTnsNum(long edTnsNum) {
    this.edTnsNum = edTnsNum;
  }


  public String getStNm() {
    return stNm;
  }

  public void setStNm(String stNm) {
    this.stNm = stNm;
  }


  public String getEdNm() {
    return edNm;
  }

  public void setEdNm(String edNm) {
    this.edNm = edNm;
  }


  public String getStEbStationId() {
    return stEbStationId;
  }

  public void setStEbStationId(String stEbStationId) {
    this.stEbStationId = stEbStationId;
  }


  public String getEdEbStationId() {
    return edEbStationId;
  }

  public void setEdEbStationId(String edEbStationId) {
    this.edEbStationId = edEbStationId;
  }

}

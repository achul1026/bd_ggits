package com.neighbor21.ggits.common.entity;
public class GgitsRelatOrg {
    private String    relatOrgId;        //생성방법 : 일련번호
    private String    relatOrgNm;        //관계기관명
    private String    relatOrgTpCd;        //관계기관구분코드
    private String    relatOrgBizno;        //관계기관사업자등록번호. 시설물 관리기관의 사업자 등록번호
    private String    relatOrgZipcode;        //관계기관우편번호. 시설물 관리기관의 우편번호
    private String    relatOrgAddr;        //관계기관주소
    private String    relatOrgResponer;        //관계기관담당자
    private String    relatOrgTelno;        //관계기관전화번호
    private String    relatOrgFaxno;        //관계기관팩스번호
    private String    regionCd;        //region_code 테이블의 region_cd_1(지역코드1)
    private String    centerId;        //center_id


  public String getRelatOrgId() {
    return relatOrgId;
  }

  public void setRelatOrgId(String relatOrgId) {
    this.relatOrgId = relatOrgId;
  }


  public String getRelatOrgNm() {
    return relatOrgNm;
  }

  public void setRelatOrgNm(String relatOrgNm) {
    this.relatOrgNm = relatOrgNm;
  }


  public String getRelatOrgTpCd() {
    return relatOrgTpCd;
  }

  public void setRelatOrgTpCd(String relatOrgTpCd) {
    this.relatOrgTpCd = relatOrgTpCd;
  }


  public String getRelatOrgBizno() {
    return relatOrgBizno;
  }

  public void setRelatOrgBizno(String relatOrgBizno) {
    this.relatOrgBizno = relatOrgBizno;
  }


  public String getRelatOrgZipcode() {
    return relatOrgZipcode;
  }

  public void setRelatOrgZipcode(String relatOrgZipcode) {
    this.relatOrgZipcode = relatOrgZipcode;
  }


  public String getRelatOrgAddr() {
    return relatOrgAddr;
  }

  public void setRelatOrgAddr(String relatOrgAddr) {
    this.relatOrgAddr = relatOrgAddr;
  }


  public String getRelatOrgResponer() {
    return relatOrgResponer;
  }

  public void setRelatOrgResponer(String relatOrgResponer) {
    this.relatOrgResponer = relatOrgResponer;
  }


  public String getRelatOrgTelno() {
    return relatOrgTelno;
  }

  public void setRelatOrgTelno(String relatOrgTelno) {
    this.relatOrgTelno = relatOrgTelno;
  }


  public String getRelatOrgFaxno() {
    return relatOrgFaxno;
  }

  public void setRelatOrgFaxno(String relatOrgFaxno) {
    this.relatOrgFaxno = relatOrgFaxno;
  }


  public String getRegionCd() {
    return regionCd;
  }

  public void setRegionCd(String regionCd) {
    this.regionCd = regionCd;
  }


  public String getCenterId() {
    return centerId;
  }

  public void setCenterId(String centerId) {
    this.centerId = centerId;
  }

}

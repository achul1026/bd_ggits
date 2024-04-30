package com.neighbor21.ggits.common.entity;
public class MOpOpenApiInfo extends CommonEntity {
  private String dsetId;
  private String apiNm;
  private String apiCallUrl;
  private String dataRegistYmd;
  private String dataUpdtYmd;
  private String descr;
  private long maxPvsnCnt;
  private String contents;
  private Long oprtrId; // 운영자 아이디
  private String mngInstCd; // 운영자 소속 코드
  
  // 검색
  private String oprtrNm; // 등록자
  private String mngInstNm; // 관리기관
  
  public String getDsetId() {
    return dsetId;
  }
  public void setDsetId(String dsetId) {
    this.dsetId = dsetId;
  }
  public String getApiNm() {
    return apiNm;
  }
  public void setApiNm(String apiNm) {
    this.apiNm = apiNm;
  }
  public String getApiCallUrl() {
    return apiCallUrl;
  }
  public void setApiCallUrl(String apiCallUrl) {
    this.apiCallUrl = apiCallUrl;
  }
  public String getDataRegistYmd() {
    return dataRegistYmd;
  }
  public void setDataRegistYmd(String dataRegistYmd) {
    this.dataRegistYmd = dataRegistYmd;
  }
  public String getDataUpdtYmd() {
    return dataUpdtYmd;
  }
  public void setDataUpdtYmd(String dataUpdtYmd) {
    this.dataUpdtYmd = dataUpdtYmd;
  }
  public String getDescr() {
    return descr;
  }
  public void setDescr(String descr) {
    this.descr = descr;
  }
  public long getMaxPvsnCnt() {
    return maxPvsnCnt;
  }
  public void setMaxPvsnCnt(long maxPvsnCnt) {
    this.maxPvsnCnt = maxPvsnCnt;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public Long getOprtrId() {
    return oprtrId;
  }
  public void setOprtrId(Long oprtrId) {
    this.oprtrId = oprtrId;
  }
  public String getMngInstCd() {
    return mngInstCd;
  }
  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }
  
  
  public String getOprtrNm() {
    return oprtrNm;
  }
  public void setOprtrNm(String oprtrNm) {
    this.oprtrNm = oprtrNm;
  }
  public String getMngInstNm() {
    return mngInstNm;
  }
  public void setMngInstNm(String mngInstNm) {
    this.mngInstNm = mngInstNm;
  }
  
}

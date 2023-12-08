package com.neighbor21.ggits.common.entity;
public class KosisAdsiBitInstlInfo {
    private String    crtrYy;        //기준 년
    private String    adsiCd;        //행정시 코드
    private String    adsiNm;        //행정시 명
    private String    mapLvl;        //지도 레벨
    private long    bstpCnt;        //버스정류장 수
    private long    bitCnt;        //bit 수
    private long    instlRt;        //설치 비율
    private String    etlDt;        //etl 일시


  public String getCrtrYy() {
    return crtrYy;
  }

  public void setCrtrYy(String crtrYy) {
    this.crtrYy = crtrYy;
  }


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


  public String getMapLvl() {
    return mapLvl;
  }

  public void setMapLvl(String mapLvl) {
    this.mapLvl = mapLvl;
  }


  public long getBstpCnt() {
    return bstpCnt;
  }

  public void setBstpCnt(long bstpCnt) {
    this.bstpCnt = bstpCnt;
  }


  public long getBitCnt() {
    return bitCnt;
  }

  public void setBitCnt(long bitCnt) {
    this.bitCnt = bitCnt;
  }


  public long getInstlRt() {
    return instlRt;
  }

  public void setInstlRt(long instlRt) {
    this.instlRt = instlRt;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

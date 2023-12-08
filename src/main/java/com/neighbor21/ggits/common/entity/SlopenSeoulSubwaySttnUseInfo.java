package com.neighbor21.ggits.common.entity;

//서울 지하철 역사 사용 정보
public class SlopenSeoulSubwaySttnUseInfo {

  private String useYmd; //사용 일자
  private String subwayRouteNm; //지하철 노선 명
  private String subwaySttnNm; //지하철 역 명
  private long totRideCnt; //전체 승차 수
  private long totLndiCnt; //전체 하차 수
  private String registYmd; //등록 일자


  public String getUseYmd() {
    return useYmd;
  }

  public void setUseYmd(String useYmd) {
    this.useYmd = useYmd;
  }


  public String getSubwayRouteNm() {
    return subwayRouteNm;
  }

  public void setSubwayRouteNm(String subwayRouteNm) {
    this.subwayRouteNm = subwayRouteNm;
  }


  public String getSubwaySttnNm() {
    return subwaySttnNm;
  }

  public void setSubwaySttnNm(String subwaySttnNm) {
    this.subwaySttnNm = subwaySttnNm;
  }


  public long getTotRideCnt() {
    return totRideCnt;
  }

  public void setTotRideCnt(long totRideCnt) {
    this.totRideCnt = totRideCnt;
  }


  public long getTotLndiCnt() {
    return totLndiCnt;
  }

  public void setTotLndiCnt(long totLndiCnt) {
    this.totLndiCnt = totLndiCnt;
  }


  public String getRegistYmd() {
    return registYmd;
  }

  public void setRegistYmd(String registYmd) {
    this.registYmd = registYmd;
  }

}

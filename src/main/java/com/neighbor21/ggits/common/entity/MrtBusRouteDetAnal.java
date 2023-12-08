package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRouteDetAnal {
    private Timestamp    anlsDt;        //분석 일시
    private String    busRouteId;        //버스 노선 아이디
    private String    routeNm;        //노선 명
    private String    rungType;        //운행 유형
    private long    routeLen;        //노선 길이
    private long    rungIntv;        //운행 간격
    private String    addngCd;        //행정동 코드
    private long    totBstpCnt;        //전체 버스정류장 수
    private long    totUserCnt;        //전체 사용자 수
    private long    curvt;        //굴곡도
    private String    etlDt;        //etl 일시


  public Timestamp getAnlsDt() {
    return anlsDt;
  }

  public void setAnlsDt(Timestamp anlsDt) {
    this.anlsDt = anlsDt;
  }


  public String getBusRouteId() {
    return busRouteId;
  }

  public void setBusRouteId(String busRouteId) {
    this.busRouteId = busRouteId;
  }


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }


  public String getRungType() {
    return rungType;
  }

  public void setRungType(String rungType) {
    this.rungType = rungType;
  }


  public long getRouteLen() {
    return routeLen;
  }

  public void setRouteLen(long routeLen) {
    this.routeLen = routeLen;
  }


  public long getRungIntv() {
    return rungIntv;
  }

  public void setRungIntv(long rungIntv) {
    this.rungIntv = rungIntv;
  }


  public String getAddngCd() {
    return addngCd;
  }

  public void setAddngCd(String addngCd) {
    this.addngCd = addngCd;
  }


  public long getTotBstpCnt() {
    return totBstpCnt;
  }

  public void setTotBstpCnt(long totBstpCnt) {
    this.totBstpCnt = totBstpCnt;
  }


  public long getTotUserCnt() {
    return totUserCnt;
  }

  public void setTotUserCnt(long totUserCnt) {
    this.totUserCnt = totUserCnt;
  }


  public long getCurvt() {
    return curvt;
  }

  public void setCurvt(long curvt) {
    this.curvt = curvt;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

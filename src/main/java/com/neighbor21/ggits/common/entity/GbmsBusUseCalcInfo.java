package com.neighbor21.ggits.common.entity;
public class GbmsBusUseCalcInfo {
    private String    clctYmd;        //수집 일자
    private String    coId;        //회사 아이디
    private String    coNm;        //회사 명
    private String    busRouteId;        //버스 노선 아이디
    private String    busRouteNo;        //버스 노선 번호
    private String    cardApprType;        //카드 결재 유형
    private long    busUserCnt;        //버스 사용자 수
    private long    busUseTotAmt;        //버스 사용 전체 금액


  public String getClctYmd() {
    return clctYmd;
  }

  public void setClctYmd(String clctYmd) {
    this.clctYmd = clctYmd;
  }


  public String getCoId() {
    return coId;
  }

  public void setCoId(String coId) {
    this.coId = coId;
  }


  public String getCoNm() {
    return coNm;
  }

  public void setCoNm(String coNm) {
    this.coNm = coNm;
  }


  public String getBusRouteId() {
    return busRouteId;
  }

  public void setBusRouteId(String busRouteId) {
    this.busRouteId = busRouteId;
  }


  public String getBusRouteNo() {
    return busRouteNo;
  }

  public void setBusRouteNo(String busRouteNo) {
    this.busRouteNo = busRouteNo;
  }


  public String getCardApprType() {
    return cardApprType;
  }

  public void setCardApprType(String cardApprType) {
    this.cardApprType = cardApprType;
  }


  public long getBusUserCnt() {
    return busUserCnt;
  }

  public void setBusUserCnt(long busUserCnt) {
    this.busUserCnt = busUserCnt;
  }


  public long getBusUseTotAmt() {
    return busUseTotAmt;
  }

  public void setBusUseTotAmt(long busUseTotAmt) {
    this.busUseTotAmt = busUseTotAmt;
  }

}

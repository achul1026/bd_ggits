package com.neighbor21.ggits.common.entity;

//여객 관광 목적별 od 교통량
public class KtdbPasngrTourPurpsOdTrfvlm {

  private String statsYy; //통계 년
  private long stptZoneNo; //기점 zone 번호
  private String stptCd; //기점 코드
  private long stptNode; //기점 노드
  private long edpntZoneNo; //종점 zone 번호
  private String edpntCd; //종점 코드
  private long edpntNode; //종점 노드
  private long hmbsAttendTrfvlm; //가정기반 출근 교통량
  private long hmbsGtschTrfvlm; //가정기반 등교 교통량
  private long hmbsInstutTrfvlm; //가정기반 학원 교통량
  private long hmbsShpngTrfvlm; //가정기반 쇼핑 교통량
  private long hmbsEtcTrfvlm; //가정기반 기타 교통량
  private long nhmbsJobTrfvlm; //비가정기반 업무 교통량
  private long nhmbsShpngTrfvlm; //비가정기반 쇼핑 교통량
  private long nhmbsEtcTrfvlm; //비가정기반 기타 교통량


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public long getStptZoneNo() {
    return stptZoneNo;
  }

  public void setStptZoneNo(long stptZoneNo) {
    this.stptZoneNo = stptZoneNo;
  }


  public String getStptCd() {
    return stptCd;
  }

  public void setStptCd(String stptCd) {
    this.stptCd = stptCd;
  }


  public long getStptNode() {
    return stptNode;
  }

  public void setStptNode(long stptNode) {
    this.stptNode = stptNode;
  }


  public long getEdpntZoneNo() {
    return edpntZoneNo;
  }

  public void setEdpntZoneNo(long edpntZoneNo) {
    this.edpntZoneNo = edpntZoneNo;
  }


  public String getEdpntCd() {
    return edpntCd;
  }

  public void setEdpntCd(String edpntCd) {
    this.edpntCd = edpntCd;
  }


  public long getEdpntNode() {
    return edpntNode;
  }

  public void setEdpntNode(long edpntNode) {
    this.edpntNode = edpntNode;
  }


  public long getHmbsAttendTrfvlm() {
    return hmbsAttendTrfvlm;
  }

  public void setHmbsAttendTrfvlm(long hmbsAttendTrfvlm) {
    this.hmbsAttendTrfvlm = hmbsAttendTrfvlm;
  }


  public long getHmbsGtschTrfvlm() {
    return hmbsGtschTrfvlm;
  }

  public void setHmbsGtschTrfvlm(long hmbsGtschTrfvlm) {
    this.hmbsGtschTrfvlm = hmbsGtschTrfvlm;
  }


  public long getHmbsInstutTrfvlm() {
    return hmbsInstutTrfvlm;
  }

  public void setHmbsInstutTrfvlm(long hmbsInstutTrfvlm) {
    this.hmbsInstutTrfvlm = hmbsInstutTrfvlm;
  }


  public long getHmbsShpngTrfvlm() {
    return hmbsShpngTrfvlm;
  }

  public void setHmbsShpngTrfvlm(long hmbsShpngTrfvlm) {
    this.hmbsShpngTrfvlm = hmbsShpngTrfvlm;
  }


  public long getHmbsEtcTrfvlm() {
    return hmbsEtcTrfvlm;
  }

  public void setHmbsEtcTrfvlm(long hmbsEtcTrfvlm) {
    this.hmbsEtcTrfvlm = hmbsEtcTrfvlm;
  }


  public long getNhmbsJobTrfvlm() {
    return nhmbsJobTrfvlm;
  }

  public void setNhmbsJobTrfvlm(long nhmbsJobTrfvlm) {
    this.nhmbsJobTrfvlm = nhmbsJobTrfvlm;
  }


  public long getNhmbsShpngTrfvlm() {
    return nhmbsShpngTrfvlm;
  }

  public void setNhmbsShpngTrfvlm(long nhmbsShpngTrfvlm) {
    this.nhmbsShpngTrfvlm = nhmbsShpngTrfvlm;
  }


  public long getNhmbsEtcTrfvlm() {
    return nhmbsEtcTrfvlm;
  }

  public void setNhmbsEtcTrfvlm(long nhmbsEtcTrfvlm) {
    this.nhmbsEtcTrfvlm = nhmbsEtcTrfvlm;
  }

}

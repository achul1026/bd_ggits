package com.neighbor21.ggits.common.entity;

//국도 시간대별 상시 교통량
public class TmsNlrdTimeOrdtmTrfvlm {

  private String roadDivNm; //도로 구분 명
  private String roadRouteNm; //도로 노선 명
  private String exmnPointId; //조사 지점 아이디
  private String exmnStartDstrctNm; //조사 시작 구역 명
  private String exmnEndDstrctNm; //조사 종료 구역 명
  private String exmnYy; //조사 년
  private String exmnMm; //조사 월
  private String exmnDd; //조사 일
  private String exmnDywk; //조사 요일
  private String roadDrctCd; //도로 방향 코드
  private String timeCd; //시간 코드
  private long trfvlm; //교통량


  public String getRoadDivNm() {
    return roadDivNm;
  }

  public void setRoadDivNm(String roadDivNm) {
    this.roadDivNm = roadDivNm;
  }


  public String getRoadRouteNm() {
    return roadRouteNm;
  }

  public void setRoadRouteNm(String roadRouteNm) {
    this.roadRouteNm = roadRouteNm;
  }


  public String getExmnPointId() {
    return exmnPointId;
  }

  public void setExmnPointId(String exmnPointId) {
    this.exmnPointId = exmnPointId;
  }


  public String getExmnStartDstrctNm() {
    return exmnStartDstrctNm;
  }

  public void setExmnStartDstrctNm(String exmnStartDstrctNm) {
    this.exmnStartDstrctNm = exmnStartDstrctNm;
  }


  public String getExmnEndDstrctNm() {
    return exmnEndDstrctNm;
  }

  public void setExmnEndDstrctNm(String exmnEndDstrctNm) {
    this.exmnEndDstrctNm = exmnEndDstrctNm;
  }


  public String getExmnYy() {
    return exmnYy;
  }

  public void setExmnYy(String exmnYy) {
    this.exmnYy = exmnYy;
  }


  public String getExmnMm() {
    return exmnMm;
  }

  public void setExmnMm(String exmnMm) {
    this.exmnMm = exmnMm;
  }


  public String getExmnDd() {
    return exmnDd;
  }

  public void setExmnDd(String exmnDd) {
    this.exmnDd = exmnDd;
  }


  public String getExmnDywk() {
    return exmnDywk;
  }

  public void setExmnDywk(String exmnDywk) {
    this.exmnDywk = exmnDywk;
  }


  public String getRoadDrctCd() {
    return roadDrctCd;
  }

  public void setRoadDrctCd(String roadDrctCd) {
    this.roadDrctCd = roadDrctCd;
  }


  public String getTimeCd() {
    return timeCd;
  }

  public void setTimeCd(String timeCd) {
    this.timeCd = timeCd;
  }


  public long getTrfvlm() {
    return trfvlm;
  }

  public void setTrfvlm(long trfvlm) {
    this.trfvlm = trfvlm;
  }

}

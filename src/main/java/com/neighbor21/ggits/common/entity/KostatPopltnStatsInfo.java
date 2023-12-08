package com.neighbor21.ggits.common.entity;

//인구 통계 정보
public class KostatPopltnStatsInfo {

  private String addngCd; //행정동 코드
  private String addngNm; //행정동 명
  private Long popltnCnt; //인구 수
  private long popltnAvgAge; //인구 평균 연령


  public String getAddngCd() {
    return addngCd;
  }

  public void setAddngCd(String addngCd) {
    this.addngCd = addngCd;
  }


  public String getAddngNm() {
    return addngNm;
  }

  public void setAddngNm(String addngNm) {
    this.addngNm = addngNm;
  }


  public long getPopltnCnt() {
    return popltnCnt;
  }

  public void setPopltnCnt(long popltnCnt) {
    this.popltnCnt = popltnCnt;
  }


  public long getPopltnAvgAge() {
    return popltnAvgAge;
  }

  public void setPopltnAvgAge(long popltnAvgAge) {
    this.popltnAvgAge = popltnAvgAge;
  }

}

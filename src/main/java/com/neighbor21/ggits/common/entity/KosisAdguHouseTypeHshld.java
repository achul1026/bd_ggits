package com.neighbor21.ggits.common.entity;

//행정구 주택 유형별 세대
public class KosisAdguHouseTypeHshld {

  private String statsYy; //통계 년
  private String adguNm; //행정구 명
  private long houseCnt; //주택 수
  private long slhTotCnt; //단독주택 전체 수
  private long slhGnrlCnt; //단독주택 일반 수
  private long slhMltfmlCnt; //단독주택 다가구 수
  private long slhBizcbuseCnt; //단독주택 영업겸용 수
  private long aptCnt; //아파트 수
  private long rwhsCnt; //연립주택 수
  private long mlpxhouCnt; //다세대주택 수
  private long noresidBuldHouseCnt; //비거주 건물 주택 수


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public String getAdguNm() {
    return adguNm;
  }

  public void setAdguNm(String adguNm) {
    this.adguNm = adguNm;
  }


  public long getHouseCnt() {
    return houseCnt;
  }

  public void setHouseCnt(long houseCnt) {
    this.houseCnt = houseCnt;
  }


  public long getSlhTotCnt() {
    return slhTotCnt;
  }

  public void setSlhTotCnt(long slhTotCnt) {
    this.slhTotCnt = slhTotCnt;
  }


  public long getSlhGnrlCnt() {
    return slhGnrlCnt;
  }

  public void setSlhGnrlCnt(long slhGnrlCnt) {
    this.slhGnrlCnt = slhGnrlCnt;
  }


  public long getSlhMltfmlCnt() {
    return slhMltfmlCnt;
  }

  public void setSlhMltfmlCnt(long slhMltfmlCnt) {
    this.slhMltfmlCnt = slhMltfmlCnt;
  }


  public long getSlhBizcbuseCnt() {
    return slhBizcbuseCnt;
  }

  public void setSlhBizcbuseCnt(long slhBizcbuseCnt) {
    this.slhBizcbuseCnt = slhBizcbuseCnt;
  }


  public long getAptCnt() {
    return aptCnt;
  }

  public void setAptCnt(long aptCnt) {
    this.aptCnt = aptCnt;
  }


  public long getRwhsCnt() {
    return rwhsCnt;
  }

  public void setRwhsCnt(long rwhsCnt) {
    this.rwhsCnt = rwhsCnt;
  }


  public long getMlpxhouCnt() {
    return mlpxhouCnt;
  }

  public void setMlpxhouCnt(long mlpxhouCnt) {
    this.mlpxhouCnt = mlpxhouCnt;
  }


  public long getNoresidBuldHouseCnt() {
    return noresidBuldHouseCnt;
  }

  public void setNoresidBuldHouseCnt(long noresidBuldHouseCnt) {
    this.noresidBuldHouseCnt = noresidBuldHouseCnt;
  }

}

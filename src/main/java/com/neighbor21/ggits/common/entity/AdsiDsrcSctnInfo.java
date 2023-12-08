package com.neighbor21.ggits.common.entity;

//dsrc 구간 정보
public class AdsiDsrcSctnInfo {

  private String mngInstCd; //관리 기관 코드
  private String dsrcSctnId; //dsrc 구간 아이디
  private String dsrcSctnNm; //dsrc 구간 명
  private long dsrcSctnLen; //dsrc 구간 길이
  private String roadGrd; //도로 등급
  private long minLimitSpeed; //최소 제한 속도
  private long maxLimitSpeed; //최대 제한 속도
  private String startRseId; //시작 rse 아이디
  private String endRseId; //종료 rse 아이디


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getDsrcSctnId() {
    return dsrcSctnId;
  }

  public void setDsrcSctnId(String dsrcSctnId) {
    this.dsrcSctnId = dsrcSctnId;
  }


  public String getDsrcSctnNm() {
    return dsrcSctnNm;
  }

  public void setDsrcSctnNm(String dsrcSctnNm) {
    this.dsrcSctnNm = dsrcSctnNm;
  }


  public long getDsrcSctnLen() {
    return dsrcSctnLen;
  }

  public void setDsrcSctnLen(long dsrcSctnLen) {
    this.dsrcSctnLen = dsrcSctnLen;
  }


  public String getRoadGrd() {
    return roadGrd;
  }

  public void setRoadGrd(String roadGrd) {
    this.roadGrd = roadGrd;
  }


  public long getMinLimitSpeed() {
    return minLimitSpeed;
  }

  public void setMinLimitSpeed(long minLimitSpeed) {
    this.minLimitSpeed = minLimitSpeed;
  }


  public long getMaxLimitSpeed() {
    return maxLimitSpeed;
  }

  public void setMaxLimitSpeed(long maxLimitSpeed) {
    this.maxLimitSpeed = maxLimitSpeed;
  }


  public String getStartRseId() {
    return startRseId;
  }

  public void setStartRseId(String startRseId) {
    this.startRseId = startRseId;
  }


  public String getEndRseId() {
    return endRseId;
  }

  public void setEndRseId(String endRseId) {
    this.endRseId = endRseId;
  }

}

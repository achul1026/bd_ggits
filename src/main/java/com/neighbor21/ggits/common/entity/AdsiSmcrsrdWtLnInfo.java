package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 대기행렬 정보
public class AdsiSmcrsrdWtLnInfo {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String cameraId; //카메라 아이디
  private String drctCd; //방향 코드
  private long laneNo; //차로 번호
  private long wtLnLen; //대기행렬 길이
  private long wtngVhclCnt; //대기 차량 수
  private long occpRt; //점유 비율


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public Timestamp getClctDt() {
    return clctDt;
  }

  public void setClctDt(Timestamp clctDt) {
    this.clctDt = clctDt;
  }


  public String getCameraId() {
    return cameraId;
  }

  public void setCameraId(String cameraId) {
    this.cameraId = cameraId;
  }


  public String getDrctCd() {
    return drctCd;
  }

  public void setDrctCd(String drctCd) {
    this.drctCd = drctCd;
  }


  public long getLaneNo() {
    return laneNo;
  }

  public void setLaneNo(long laneNo) {
    this.laneNo = laneNo;
  }


  public long getWtLnLen() {
    return wtLnLen;
  }

  public void setWtLnLen(long wtLnLen) {
    this.wtLnLen = wtLnLen;
  }


  public long getWtngVhclCnt() {
    return wtngVhclCnt;
  }

  public void setWtngVhclCnt(long wtngVhclCnt) {
    this.wtngVhclCnt = wtngVhclCnt;
  }


  public long getOccpRt() {
    return occpRt;
  }

  public void setOccpRt(long occpRt) {
    this.occpRt = occpRt;
  }

}

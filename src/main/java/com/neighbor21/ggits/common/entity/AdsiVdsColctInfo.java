package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//vds 수집 정보
public class AdsiVdsColctInfo extends CommonEntity{

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String vdsId; //vds 아이디
  private long laneNo; //차로 번호
  private long avgSpeed; //평균 속도
  private long trfvlm; //교통량
  private long occpRt; //점유 비율
  
  private String vdsNm;

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


  public String getVdsId() {
    return vdsId;
  }

  public void setVdsId(String vdsId) {
    this.vdsId = vdsId;
  }


  public long getLaneNo() {
    return laneNo;
  }

  public void setLaneNo(long laneNo) {
    this.laneNo = laneNo;
  }


  public long getAvgSpeed() {
    return avgSpeed;
  }

  public void setAvgSpeed(long avgSpeed) {
    this.avgSpeed = avgSpeed;
  }


  public long getTrfvlm() {
    return trfvlm;
  }

  public void setTrfvlm(long trfvlm) {
    this.trfvlm = trfvlm;
  }


  public long getOccpRt() {
    return occpRt;
  }

  public void setOccpRt(long occpRt) {
    this.occpRt = occpRt;
  }

public String getVdsNm() {
	return vdsNm;
}

public void setVdsNm(String vdsNm) {
	this.vdsNm = vdsNm;
}

}

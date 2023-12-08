package com.neighbor21.ggits.common.entity;
public class GbmsBusRoutePasnrLog {
    private String    rideDt;        //승차일
    private String    rideTime;        //승차시간
    private String    rideBusId;        //승차역 id
    private String    transType;        //환승횟수
    private String    busType;        //수단코드
    private String    busId;        //버스노선id
    private long    pass1;        //승객1
    private long    pass2;        //승객2
    private long    paas3;        //승객3
    private String    quitBusId;        //하차역id
    private String    quitDt;        //하치일시


  public String getRideDt() {
    return rideDt;
  }

  public void setRideDt(String rideDt) {
    this.rideDt = rideDt;
  }


  public String getRideTime() {
    return rideTime;
  }

  public void setRideTime(String rideTime) {
    this.rideTime = rideTime;
  }


  public String getRideBusId() {
    return rideBusId;
  }

  public void setRideBusId(String rideBusId) {
    this.rideBusId = rideBusId;
  }


  public String getTransType() {
    return transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }


  public String getBusType() {
    return busType;
  }

  public void setBusType(String busType) {
    this.busType = busType;
  }


  public String getBusId() {
    return busId;
  }

  public void setBusId(String busId) {
    this.busId = busId;
  }


  public long getPass1() {
    return pass1;
  }

  public void setPass1(long pass1) {
    this.pass1 = pass1;
  }


  public long getPass2() {
    return pass2;
  }

  public void setPass2(long pass2) {
    this.pass2 = pass2;
  }


  public long getPaas3() {
    return paas3;
  }

  public void setPaas3(long paas3) {
    this.paas3 = paas3;
  }


  public String getQuitBusId() {
    return quitBusId;
  }

  public void setQuitBusId(String quitBusId) {
    this.quitBusId = quitBusId;
  }


  public String getQuitDt() {
    return quitDt;
  }

  public void setQuitDt(String quitDt) {
    this.quitDt = quitDt;
  }

}

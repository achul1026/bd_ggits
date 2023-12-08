package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusTotMoveInfo {
    private Timestamp    anlsDt;        //분석 일시
    private String    busRouteId;       //버스 노선 아이디
    private String    rideSttnId;       //승차 역 아이디
    private String    lndiSttnId;       //하차 역 아이디
    private String    rideTime;        	//승차 시간
    private String    lndiTime;        	//하차 시간
    private long    busUseCnt;        	//버스 사용 수
    private long    mvmnDstne;        	//이동 거리
    
    private String rideSttnNm;			// 출발정류장명
    private String lndiSttnNm;			// 도착정류장명

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


  public String getRideSttnId() {
    return rideSttnId;
  }

  public void setRideSttnId(String rideSttnId) {
    this.rideSttnId = rideSttnId;
  }


  public String getLndiSttnId() {
    return lndiSttnId;
  }

  public void setLndiSttnId(String lndiSttnId) {
    this.lndiSttnId = lndiSttnId;
  }


  public String getRideTime() {
    return rideTime;
  }

  public void setRideTime(String rideTime) {
    this.rideTime = rideTime;
  }


  public String getLndiTime() {
    return lndiTime;
  }

  public void setLndiTime(String lndiTime) {
    this.lndiTime = lndiTime;
  }


  public long getBusUseCnt() {
    return busUseCnt;
  }

  public void setBusUseCnt(long busUseCnt) {
    this.busUseCnt = busUseCnt;
  }


  public long getMvmnDstne() {
    return mvmnDstne;
  }

  public void setMvmnDstne(long mvmnDstne) {
    this.mvmnDstne = mvmnDstne;
  }

  public String getRideSttnNm() {
	return rideSttnNm;
  }

  public void setRideSttnNm(String rideSttnNm) {
	this.rideSttnNm = rideSttnNm;
  }

  public String getLndiSttnNm() {
	return lndiSttnNm;
  }

  public void setLndiSttnNm(String lndiSttnNm) {
	this.lndiSttnNm = lndiSttnNm;
  }

}

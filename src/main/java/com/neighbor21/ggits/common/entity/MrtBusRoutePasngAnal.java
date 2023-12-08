package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRoutePasngAnal {
    private Timestamp    anlsDt;        //분석 일시
    private String    busRouteId;       //버스 노선 아이디
    private String    meanCd;        	//수단 코드
    private String    rideBusId;        //승차역 id
    private String    rideDt;        	//승차 일시
    private String    quitBusId;        //하차역id
    private String    quitDt;        	//하차일시
    private long    pass1;        		//승객1
    private long    pass2;        		//승객2
    private long    pass3;        		//승객3
    private String    etlDt;        	//etl 일시

    private String routeNm;				//구간 명
    
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


  public String getMeanCd() {
    return meanCd;
  }

  public void setMeanCd(String meanCd) {
    this.meanCd = meanCd;
  }


  public String getRideBusId() {
    return rideBusId;
  }

  public void setRideBusId(String rideBusId) {
    this.rideBusId = rideBusId;
  }


  public String getRideDt() {
    return rideDt;
  }

  public void setRideDt(String rideDt) {
    this.rideDt = rideDt;
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


  public long getPass3() {
    return pass3;
  }

  public void setPass3(long pass3) {
    this.pass3 = pass3;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

  public String getRouteNm() {
	return routeNm;
  }

  public void setRouteNm(String routeNm) {
	this.routeNm = routeNm;
  }

}

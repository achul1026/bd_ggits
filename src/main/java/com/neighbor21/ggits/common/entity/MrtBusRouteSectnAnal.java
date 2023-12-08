package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRouteSectnAnal {
    private Timestamp    anlsDt;        //분석 일시
    private String    startBstpId;        //시작 버스정류장 아이디
    private String    endBstpId;        //종료 버스정류장 아이디
    private String    busRouteId;        //버스 노선 아이디
    private String    sctnId;        //구간 아이디
    private String    vhclId;        //차량 아이디
    private long    bstpSctnLen;        //버스정류장 구간 길이
    private long    passTime;        //통행 시간
    private long    stpvehTime;        //정차 시간
    private String    etlDt;        //etl 일시
    
    private String routeNm;			// 노선명
    private String routeInterval;	// 배차 간격
    

  public Timestamp getAnlsDt() {
    return anlsDt;
  }

  public void setAnlsDt(Timestamp anlsDt) {
    this.anlsDt = anlsDt;
  }


  public String getStartBstpId() {
    return startBstpId;
  }

  public void setStartBstpId(String startBstpId) {
    this.startBstpId = startBstpId;
  }


  public String getEndBstpId() {
    return endBstpId;
  }

  public void setEndBstpId(String endBstpId) {
    this.endBstpId = endBstpId;
  }


  public String getBusRouteId() {
    return busRouteId;
  }

  public void setBusRouteId(String busRouteId) {
    this.busRouteId = busRouteId;
  }


  public String getSctnId() {
    return sctnId;
  }

  public void setSctnId(String sctnId) {
    this.sctnId = sctnId;
  }


  public String getVhclId() {
    return vhclId;
  }

  public void setVhclId(String vhclId) {
    this.vhclId = vhclId;
  }


  public long getBstpSctnLen() {
    return bstpSctnLen;
  }

  public void setBstpSctnLen(long bstpSctnLen) {
    this.bstpSctnLen = bstpSctnLen;
  }


  public long getPassTime() {
    return passTime;
  }

  public void setPassTime(long passTime) {
    this.passTime = passTime;
  }


  public long getStpvehTime() {
    return stpvehTime;
  }

  public void setStpvehTime(long stpvehTime) {
    this.stpvehTime = stpvehTime;
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

public String getRouteInterval() {
	return routeInterval;
}

public void setRouteInterval(String routeInterval) {
	this.routeInterval = routeInterval;
}

}

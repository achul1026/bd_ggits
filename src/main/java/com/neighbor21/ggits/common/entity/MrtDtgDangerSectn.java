package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtDtgDangerSectn {
    private Timestamp    anlsDt;        //분석 일시
    private String    vhclNo;        //차량 번호
    private String    linkId;        //링크 아이디
    private String    busRouteNo;        //버스 노선 번호
    private String    coId;        //회사 아이디
    private long    spdngRungCnt;        //과속 운행 수
    private long    sdacelRungCnt;        //급가속 운행 수
    private long    rpddclRungCnt;        //급감속 운행 수
    private long    sdstopRungCnt;        //급정지 운행 수
    private long    sdstrtRungCnt;        //급출발 운행 수
    private long    lngtrmaclRungCnt;        //장기과속 운행 수

    private long 	riskJugCnt;			  // 위험 판정 수
    private String stStaNm;				// 출발 정류장명
    private String edStaNm;				// 도착 정류장명
    private String routeInterval;		// 운행 간격
    private String routeLength;			// 운행 거리
    
    private String routeNm;				// 노선 명
    
  public Timestamp getAnlsDt() {
    return anlsDt;
  }

  public void setAnlsDt(Timestamp anlsDt) {
    this.anlsDt = anlsDt;
  }


  public String getVhclNo() {
    return vhclNo;
  }

  public void setVhclNo(String vhclNo) {
    this.vhclNo = vhclNo;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getBusRouteNo() {
    return busRouteNo;
  }

  public void setBusRouteNo(String busRouteNo) {
    this.busRouteNo = busRouteNo;
  }


  public String getCoId() {
    return coId;
  }

  public void setCoId(String coId) {
    this.coId = coId;
  }


  public long getSpdngRungCnt() {
    return spdngRungCnt;
  }

  public void setSpdngRungCnt(long spdngRungCnt) {
    this.spdngRungCnt = spdngRungCnt;
  }


  public long getSdacelRungCnt() {
    return sdacelRungCnt;
  }

  public void setSdacelRungCnt(long sdacelRungCnt) {
    this.sdacelRungCnt = sdacelRungCnt;
  }


  public long getRpddclRungCnt() {
    return rpddclRungCnt;
  }

  public void setRpddclRungCnt(long rpddclRungCnt) {
    this.rpddclRungCnt = rpddclRungCnt;
  }


  public long getSdstopRungCnt() {
    return sdstopRungCnt;
  }

  public void setSdstopRungCnt(long sdstopRungCnt) {
    this.sdstopRungCnt = sdstopRungCnt;
  }


  public long getSdstrtRungCnt() {
    return sdstrtRungCnt;
  }

  public void setSdstrtRungCnt(long sdstrtRungCnt) {
    this.sdstrtRungCnt = sdstrtRungCnt;
  }


  public long getLngtrmaclRungCnt() {
    return lngtrmaclRungCnt;
  }

  public void setLngtrmaclRungCnt(long lngtrmaclRungCnt) {
    this.lngtrmaclRungCnt = lngtrmaclRungCnt;
  }

  public long getRiskJugCnt() {
	return riskJugCnt;
  }

  public void setRiskJugCnt(long riskJugCnt) {
	this.riskJugCnt = riskJugCnt;
  }

public String getStStaNm() {
	return stStaNm;
}

public void setStStaNm(String stStaNm) {
	this.stStaNm = stStaNm;
}

public String getEdStaNm() {
	return edStaNm;
}

public void setEdStaNm(String edStaNm) {
	this.edStaNm = edStaNm;
}

public String getRouteInterval() {
	return routeInterval;
}

public void setRouteInterval(String routeInterval) {
	this.routeInterval = routeInterval;
}

public String getRouteLength() {
	return routeLength;
}

public void setRouteLength(String routeLength) {
	this.routeLength = routeLength;
}

public String getRouteNm() {
	return routeNm;
}

public void setRouteNm(String routeNm) {
	this.routeNm = routeNm;
}

}

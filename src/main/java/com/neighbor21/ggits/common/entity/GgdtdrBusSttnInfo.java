package com.neighbor21.ggits.common.entity;

//버스 정류소 정보
public class GgdtdrBusSttnInfo {

  private String adsiCd; //행정시 코드
  private String bstpNm; //버스정류장 명
  private String bstpId; //버스정류장 아이디
  private String adsiNm; //행정시 명
  private String bstpEngNm; //버스정류장 영문 명
  private String bstpNo; //버스정류장 번호
  private String cntrLaneYn; //중앙 차로 여부
  private String cmptncInst; //관할 기관
  private String instlAddr; //설치 주소
  private double lonCrdn; //경도 좌표
  private double latCrdn; //위도 좌표
  
  private String routeNm;	// 노선명
  private String stStaNm;	// 출발지
  private String edStaNm;	// 도착지
  private String routeInterval;	// 배차간격
  private String routeTp;	// 노선 유형

  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public String getBstpNm() {
    return bstpNm;
  }

  public void setBstpNm(String bstpNm) {
    this.bstpNm = bstpNm;
  }


  public String getBstpId() {
    return bstpId;
  }

  public void setBstpId(String bstpId) {
    this.bstpId = bstpId;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getBstpEngNm() {
    return bstpEngNm;
  }

  public void setBstpEngNm(String bstpEngNm) {
    this.bstpEngNm = bstpEngNm;
  }


  public String getBstpNo() {
    return bstpNo;
  }

  public void setBstpNo(String bstpNo) {
    this.bstpNo = bstpNo;
  }


  public String getCntrLaneYn() {
    return cntrLaneYn;
  }

  public void setCntrLaneYn(String cntrLaneYn) {
    this.cntrLaneYn = cntrLaneYn;
  }


  public String getCmptncInst() {
    return cmptncInst;
  }

  public void setCmptncInst(String cmptncInst) {
    this.cmptncInst = cmptncInst;
  }


  public String getInstlAddr() {
    return instlAddr;
  }

  public void setInstlAddr(String instlAddr) {
    this.instlAddr = instlAddr;
  }

  public double getLonCrdn() {
		return lonCrdn;
  }
	
  public void setLonCrdn(double lonCrdn) {
		this.lonCrdn = lonCrdn;
  }
	
  public double getLatCrdn() {
		return latCrdn;
  }
	
  public void setLatCrdn(double latCrdn) {
		this.latCrdn = latCrdn;
  }

public String getRouteNm() {
	return routeNm;
}

public void setRouteNm(String routeNm) {
	this.routeNm = routeNm;
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

public String getRouteTp() {
	return routeTp;
}

public void setRouteTp(String routeTp) {
	this.routeTp = routeTp;
}
  
}

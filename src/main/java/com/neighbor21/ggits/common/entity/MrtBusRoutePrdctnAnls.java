package com.neighbor21.ggits.common.entity;
public class MrtBusRoutePrdctnAnls {
    private String    baseym;        //기준년월
    private String    btcId;        //id
    private String    btcDt;        //일자
    private java.sql.Time    elTm;        //수행시간
    private String    routeId;        //노선id
    private String    routeNm;        //노선명
    private String    etlDt;        //etl 일시
    
	private String candRouteId;		//  후보경로 아이디
	private long score;				// 평가 점수
	private long length;			// 총거리
	private long lengthRatio;		// 굴곡도
	

  public String getBaseym() {
    return baseym;
  }

  public void setBaseym(String baseym) {
    this.baseym = baseym;
  }


  public String getBtcId() {
    return btcId;
  }

  public void setBtcId(String btcId) {
    this.btcId = btcId;
  }


  public String getBtcDt() {
    return btcDt;
  }

  public void setBtcDt(String btcDt) {
    this.btcDt = btcDt;
  }


  public java.sql.Time getElTm() {
    return elTm;
  }

  public void setElTm(java.sql.Time elTm) {
    this.elTm = elTm;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

public String getCandRouteId() {
	return candRouteId;
}

public void setCandRouteId(String candRouteId) {
	this.candRouteId = candRouteId;
}

public long getScore() {
	return score;
}

public void setScore(long score) {
	this.score = score;
}

public long getLength() {
	return length;
}

public void setLength(long length) {
	this.length = length;
}

public long getLengthRatio() {
	return lengthRatio;
}

public void setLengthRatio(long lengthRatio) {
	this.lengthRatio = lengthRatio;
}

}

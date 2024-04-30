package com.neighbor21.ggits.common.entity;
public class MrtInlayArrTmFrcst {
    private String    baseymd;        //기준일자
    private String    timezn;        //시간대
    private String    stPtId;        //출발지점ID
    private String    basePtId;        //기준지점ID
    private String    routeId;        //버스노선ID
    private double    tfcLength;        //통행거리
    private double    tmSin;        //시간대_sin
    private double    tmCos;        //시간대_cos
    private String    rhYn;        //출퇴근시간대여부
    private long    lanes;        //차로수
    private long    maxSpd;        //최고속도제한
    private double    avgTfcTm;        //구간별평균통행시간
    private String    centerYn;        //중앙차로여부
    private double    ta;        //기온
    private double    rainAmt;        //강수량
    private double    ws;        //풍속
    private long    hm;        //습도
    private String    rainYn;        //강수여부
    private double    tfcTm;        //통행시간
    private String    etlDt;        //ETL 일시


  public String getBaseymd() {
    return baseymd;
  }

  public void setBaseymd(String baseymd) {
    this.baseymd = baseymd;
  }


  public String getTimezn() {
    return timezn;
  }

  public void setTimezn(String timezn) {
    this.timezn = timezn;
  }


  public String getStPtId() {
    return stPtId;
  }

  public void setStPtId(String stPtId) {
    this.stPtId = stPtId;
  }


  public String getBasePtId() {
    return basePtId;
  }

  public void setBasePtId(String basePtId) {
    this.basePtId = basePtId;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public double getTfcLength() {
    return tfcLength;
  }

  public void setTfcLength(double tfcLength) {
    this.tfcLength = tfcLength;
  }


  public double getTmSin() {
    return tmSin;
  }

  public void setTmSin(double tmSin) {
    this.tmSin = tmSin;
  }


  public double getTmCos() {
    return tmCos;
  }

  public void setTmCos(double tmCos) {
    this.tmCos = tmCos;
  }


  public String getRhYn() {
    return rhYn;
  }

  public void setRhYn(String rhYn) {
    this.rhYn = rhYn;
  }


  public long getLanes() {
    return lanes;
  }

  public void setLanes(long lanes) {
    this.lanes = lanes;
  }


  public long getMaxSpd() {
    return maxSpd;
  }

  public void setMaxSpd(long maxSpd) {
    this.maxSpd = maxSpd;
  }


  public double getAvgTfcTm() {
    return avgTfcTm;
  }

  public void setAvgTfcTm(double avgTfcTm) {
    this.avgTfcTm = avgTfcTm;
  }


  public String getCenterYn() {
    return centerYn;
  }

  public void setCenterYn(String centerYn) {
    this.centerYn = centerYn;
  }


  public double getTa() {
    return ta;
  }

  public void setTa(double ta) {
    this.ta = ta;
  }


  public double getRainAmt() {
    return rainAmt;
  }

  public void setRainAmt(double rainAmt) {
    this.rainAmt = rainAmt;
  }


  public double getWs() {
    return ws;
  }

  public void setWs(double ws) {
    this.ws = ws;
  }


  public long getHm() {
    return hm;
  }

  public void setHm(long hm) {
    this.hm = hm;
  }


  public String getRainYn() {
    return rainYn;
  }

  public void setRainYn(String rainYn) {
    this.rainYn = rainYn;
  }


  public double getTfcTm() {
    return tfcTm;
  }

  public void setTfcTm(double tfcTm) {
    this.tfcTm = tfcTm;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

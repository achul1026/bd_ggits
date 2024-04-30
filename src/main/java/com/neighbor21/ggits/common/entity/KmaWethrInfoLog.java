package com.neighbor21.ggits.common.entity;
public class KmaWethrInfoLog {
    private String    crtrDt;        //기준 일시
    private String    pointNo;        //지점 번호
    private double    tmprt;        //기온
    private double    avgWddr;        //평균 풍향
    private double    avgWdsd;        //평균 풍속
    private String    ddPctt;        //일 강수량
    private String    timePctt;        //시간 강수량
    private double    hmdt;        //습도
    private double    prsfel;        //현지기압
    private double    prssl;        //해면기압
    private String    etlDt;        //etl 일시


  public String getCrtrDt() {
    return crtrDt;
  }

  public void setCrtrDt(String crtrDt) {
    this.crtrDt = crtrDt;
  }


  public String getPointNo() {
    return pointNo;
  }

  public void setPointNo(String pointNo) {
    this.pointNo = pointNo;
  }


  public double getTmprt() {
    return tmprt;
  }

  public void setTmprt(double tmprt) {
    this.tmprt = tmprt;
  }


  public double getAvgWddr() {
    return avgWddr;
  }

  public void setAvgWddr(double avgWddr) {
    this.avgWddr = avgWddr;
  }


  public double getAvgWdsd() {
    return avgWdsd;
  }

  public void setAvgWdsd(double avgWdsd) {
    this.avgWdsd = avgWdsd;
  }


  public String getDdPctt() {
    return ddPctt;
  }

  public void setDdPctt(String ddPctt) {
    this.ddPctt = ddPctt;
  }


  public String getTimePctt() {
    return timePctt;
  }

  public void setTimePctt(String timePctt) {
    this.timePctt = timePctt;
  }


  public double getHmdt() {
    return hmdt;
  }

  public void setHmdt(double hmdt) {
    this.hmdt = hmdt;
  }


  public double getPrsfel() {
    return prsfel;
  }

  public void setPrsfel(double prsfel) {
    this.prsfel = prsfel;
  }


  public double getPrssl() {
    return prssl;
  }

  public void setPrssl(double prssl) {
    this.prssl = prssl;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

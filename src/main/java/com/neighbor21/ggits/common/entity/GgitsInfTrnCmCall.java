package com.neighbor21.ggits.common.entity;
public class GgitsInfTrnCmCall {
    private String    ifSeq;        //연계순번
    private String    regSeq;        //접수번호
    private String    ifRegDttm;        //연계등록일시
    private String    ifCudFlag;        //연계작업구분
    private String    ifHndlDttm;        //연계처리일시
    private String    ifHndlFlag;        //연계처리상태
    private String    ifHndlErr;        //연계오류내역
    private String    regDtime;        //접수일시
    private String    callContent;        //신고내역
    private long    gisX;        //신고위치(gis x좌표)
    private long    gisY;        //신고위치(gis y좌표)
    private String    regEndDtime;        //접수종료일시
    private String    treatClsCd;        //처리구분코드(안내,오접속,정상출동 등)
    private String    dsrSeq;        //긴급구조번호


  public String getIfSeq() {
    return ifSeq;
  }

  public void setIfSeq(String ifSeq) {
    this.ifSeq = ifSeq;
  }


  public String getRegSeq() {
    return regSeq;
  }

  public void setRegSeq(String regSeq) {
    this.regSeq = regSeq;
  }


  public String getIfRegDttm() {
    return ifRegDttm;
  }

  public void setIfRegDttm(String ifRegDttm) {
    this.ifRegDttm = ifRegDttm;
  }


  public String getIfCudFlag() {
    return ifCudFlag;
  }

  public void setIfCudFlag(String ifCudFlag) {
    this.ifCudFlag = ifCudFlag;
  }


  public String getIfHndlDttm() {
    return ifHndlDttm;
  }

  public void setIfHndlDttm(String ifHndlDttm) {
    this.ifHndlDttm = ifHndlDttm;
  }


  public String getIfHndlFlag() {
    return ifHndlFlag;
  }

  public void setIfHndlFlag(String ifHndlFlag) {
    this.ifHndlFlag = ifHndlFlag;
  }


  public String getIfHndlErr() {
    return ifHndlErr;
  }

  public void setIfHndlErr(String ifHndlErr) {
    this.ifHndlErr = ifHndlErr;
  }


  public String getRegDtime() {
    return regDtime;
  }

  public void setRegDtime(String regDtime) {
    this.regDtime = regDtime;
  }


  public String getCallContent() {
    return callContent;
  }

  public void setCallContent(String callContent) {
    this.callContent = callContent;
  }


  public long getGisX() {
    return gisX;
  }

  public void setGisX(long gisX) {
    this.gisX = gisX;
  }


  public long getGisY() {
    return gisY;
  }

  public void setGisY(long gisY) {
    this.gisY = gisY;
  }


  public String getRegEndDtime() {
    return regEndDtime;
  }

  public void setRegEndDtime(String regEndDtime) {
    this.regEndDtime = regEndDtime;
  }


  public String getTreatClsCd() {
    return treatClsCd;
  }

  public void setTreatClsCd(String treatClsCd) {
    this.treatClsCd = treatClsCd;
  }


  public String getDsrSeq() {
    return dsrSeq;
  }

  public void setDsrSeq(String dsrSeq) {
    this.dsrSeq = dsrSeq;
  }

}

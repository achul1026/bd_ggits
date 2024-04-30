package com.neighbor21.ggits.common.entity;
public class GgitsInfTrnCmDsr {
    private String    dsrSeq;        //긴급구조번호
    private String    ifSeq;        //연계순번
    private String    ifRegDttm;        //연계등록일시
    private String    ifCudFlag;        //연계작업구분
    private String    ifHndlDttm;        //연계처리일시
    private String    ifHndlFlag;        //연계처리상태
    private String    ifHndlErr;        //연계오류내역
    private String    dsrKndCd;        //긴급구조종별코드(화재,구조,구급,기타)
    private String    dsrClsCd;        //긴급구조분류코드(교통사고, 질병, 가스사고 등)
    private String    dsrSizeCd;        //긴급구조규모코드(1차,2차,3차 출동)
    private String    procCd;        //긴급구조진행상황코드(현장출동,병원출발,완료 등)
    private String    statEndDtime;        //상황종료일시
    private long    gisX;        //긴급구조위치(gis x 좌표)
    private long    gisY;        //긴급구조위치(gis y 좌표)
    private String    dFstRegSeq;        //최초접수번호
    private String    dsrEtcAddr;        //긴급구조상세주소
    private String    pointAccrCls;        //지점정확도구분


  public String getDsrSeq() {
    return dsrSeq;
  }

  public void setDsrSeq(String dsrSeq) {
    this.dsrSeq = dsrSeq;
  }


  public String getIfSeq() {
    return ifSeq;
  }

  public void setIfSeq(String ifSeq) {
    this.ifSeq = ifSeq;
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


  public String getDsrKndCd() {
    return dsrKndCd;
  }

  public void setDsrKndCd(String dsrKndCd) {
    this.dsrKndCd = dsrKndCd;
  }


  public String getDsrClsCd() {
    return dsrClsCd;
  }

  public void setDsrClsCd(String dsrClsCd) {
    this.dsrClsCd = dsrClsCd;
  }


  public String getDsrSizeCd() {
    return dsrSizeCd;
  }

  public void setDsrSizeCd(String dsrSizeCd) {
    this.dsrSizeCd = dsrSizeCd;
  }


  public String getProcCd() {
    return procCd;
  }

  public void setProcCd(String procCd) {
    this.procCd = procCd;
  }


  public String getStatEndDtime() {
    return statEndDtime;
  }

  public void setStatEndDtime(String statEndDtime) {
    this.statEndDtime = statEndDtime;
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


  public String getDFstRegSeq() {
    return dFstRegSeq;
  }

  public void setDFstRegSeq(String dFstRegSeq) {
    this.dFstRegSeq = dFstRegSeq;
  }


  public String getDsrEtcAddr() {
    return dsrEtcAddr;
  }

  public void setDsrEtcAddr(String dsrEtcAddr) {
    this.dsrEtcAddr = dsrEtcAddr;
  }


  public String getPointAccrCls() {
    return pointAccrCls;
  }

  public void setPointAccrCls(String pointAccrCls) {
    this.pointAccrCls = pointAccrCls;
  }

}

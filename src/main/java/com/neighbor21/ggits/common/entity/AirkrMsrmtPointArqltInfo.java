package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//측정 지점 대기질 정보
public class AirkrMsrmtPointArqltInfo {

  private Timestamp msrmtDt; //측정 일시
  private String msrmtPointNm; //측정 지점 명
  private String msrmtPointCd; //측정 지점 코드
  private String msrmtDstrctCd; //측정 구역 코드
  private long so2Cnct; //이산화황 농도
  private long cmoCnct; //일산화탄소 농도
  private long ozCnct; //오존 농도
  private long no2Cnct; //이산화질소 농도
  private long fptcCnct; //미세먼지 농도
  private long fptcTwfrhrPrdctnMvmnCnct; //미세먼지 24시간 예측 이동 농도
  private long ulfptcCnct; //초미세먼지 농도
  private long ulfptcTwfrhrPrdctnMvmnCnct; //초미세먼지 24시간 예측 이동 농도
  private long totArqltNcl; //전체 대기질 수치
  private long totArqltIdex; //전체 대기질 지수
  private long so2Idex; //이산화황 지수
  private long cmoIdex; //일산화탄소 지수
  private long ozIdex; //오존 지수
  private long no2Idex; //이산화질소 지수
  private long fptcTwfrhrGrd; //미세먼지 24시간 등급
  private long ulfptcTwfrhrGrd; //초미세먼지 24시간 등급
  private long fptcOnhrGrd; //미세먼지 1시간 등급
  private long ulfptcOnhrGrd; //초미세먼지 1시간 등급
  private String so2Flag; //이산화황 플래그
  private String cmoFlag; //일산화탄소 플래그
  private String ozFlag; //오존 플래그
  private String no2Flag; //이산화질소 플래그
  private String fptcFlag; //미세먼지 플래그
  private String ulfptcFlag; //초미세먼지 플래그


  public Timestamp getMsrmtDt() {
    return msrmtDt;
  }

  public void setMsrmtDt(Timestamp msrmtDt) {
    this.msrmtDt = msrmtDt;
  }


  public String getMsrmtPointNm() {
    return msrmtPointNm;
  }

  public void setMsrmtPointNm(String msrmtPointNm) {
    this.msrmtPointNm = msrmtPointNm;
  }


  public String getMsrmtPointCd() {
    return msrmtPointCd;
  }

  public void setMsrmtPointCd(String msrmtPointCd) {
    this.msrmtPointCd = msrmtPointCd;
  }


  public String getMsrmtDstrctCd() {
    return msrmtDstrctCd;
  }

  public void setMsrmtDstrctCd(String msrmtDstrctCd) {
    this.msrmtDstrctCd = msrmtDstrctCd;
  }


  public long getSo2Cnct() {
    return so2Cnct;
  }

  public void setSo2Cnct(long so2Cnct) {
    this.so2Cnct = so2Cnct;
  }


  public long getCmoCnct() {
    return cmoCnct;
  }

  public void setCmoCnct(long cmoCnct) {
    this.cmoCnct = cmoCnct;
  }


  public long getOzCnct() {
    return ozCnct;
  }

  public void setOzCnct(long ozCnct) {
    this.ozCnct = ozCnct;
  }


  public long getNo2Cnct() {
    return no2Cnct;
  }

  public void setNo2Cnct(long no2Cnct) {
    this.no2Cnct = no2Cnct;
  }


  public long getFptcCnct() {
    return fptcCnct;
  }

  public void setFptcCnct(long fptcCnct) {
    this.fptcCnct = fptcCnct;
  }


  public long getFptcTwfrhrPrdctnMvmnCnct() {
    return fptcTwfrhrPrdctnMvmnCnct;
  }

  public void setFptcTwfrhrPrdctnMvmnCnct(long fptcTwfrhrPrdctnMvmnCnct) {
    this.fptcTwfrhrPrdctnMvmnCnct = fptcTwfrhrPrdctnMvmnCnct;
  }


  public long getUlfptcCnct() {
    return ulfptcCnct;
  }

  public void setUlfptcCnct(long ulfptcCnct) {
    this.ulfptcCnct = ulfptcCnct;
  }


  public long getUlfptcTwfrhrPrdctnMvmnCnct() {
    return ulfptcTwfrhrPrdctnMvmnCnct;
  }

  public void setUlfptcTwfrhrPrdctnMvmnCnct(long ulfptcTwfrhrPrdctnMvmnCnct) {
    this.ulfptcTwfrhrPrdctnMvmnCnct = ulfptcTwfrhrPrdctnMvmnCnct;
  }


  public long getTotArqltNcl() {
    return totArqltNcl;
  }

  public void setTotArqltNcl(long totArqltNcl) {
    this.totArqltNcl = totArqltNcl;
  }


  public long getTotArqltIdex() {
    return totArqltIdex;
  }

  public void setTotArqltIdex(long totArqltIdex) {
    this.totArqltIdex = totArqltIdex;
  }


  public long getSo2Idex() {
    return so2Idex;
  }

  public void setSo2Idex(long so2Idex) {
    this.so2Idex = so2Idex;
  }


  public long getCmoIdex() {
    return cmoIdex;
  }

  public void setCmoIdex(long cmoIdex) {
    this.cmoIdex = cmoIdex;
  }


  public long getOzIdex() {
    return ozIdex;
  }

  public void setOzIdex(long ozIdex) {
    this.ozIdex = ozIdex;
  }


  public long getNo2Idex() {
    return no2Idex;
  }

  public void setNo2Idex(long no2Idex) {
    this.no2Idex = no2Idex;
  }


  public long getFptcTwfrhrGrd() {
    return fptcTwfrhrGrd;
  }

  public void setFptcTwfrhrGrd(long fptcTwfrhrGrd) {
    this.fptcTwfrhrGrd = fptcTwfrhrGrd;
  }


  public long getUlfptcTwfrhrGrd() {
    return ulfptcTwfrhrGrd;
  }

  public void setUlfptcTwfrhrGrd(long ulfptcTwfrhrGrd) {
    this.ulfptcTwfrhrGrd = ulfptcTwfrhrGrd;
  }


  public long getFptcOnhrGrd() {
    return fptcOnhrGrd;
  }

  public void setFptcOnhrGrd(long fptcOnhrGrd) {
    this.fptcOnhrGrd = fptcOnhrGrd;
  }


  public long getUlfptcOnhrGrd() {
    return ulfptcOnhrGrd;
  }

  public void setUlfptcOnhrGrd(long ulfptcOnhrGrd) {
    this.ulfptcOnhrGrd = ulfptcOnhrGrd;
  }


  public String getSo2Flag() {
    return so2Flag;
  }

  public void setSo2Flag(String so2Flag) {
    this.so2Flag = so2Flag;
  }


  public String getCmoFlag() {
    return cmoFlag;
  }

  public void setCmoFlag(String cmoFlag) {
    this.cmoFlag = cmoFlag;
  }


  public String getOzFlag() {
    return ozFlag;
  }

  public void setOzFlag(String ozFlag) {
    this.ozFlag = ozFlag;
  }


  public String getNo2Flag() {
    return no2Flag;
  }

  public void setNo2Flag(String no2Flag) {
    this.no2Flag = no2Flag;
  }


  public String getFptcFlag() {
    return fptcFlag;
  }

  public void setFptcFlag(String fptcFlag) {
    this.fptcFlag = fptcFlag;
  }


  public String getUlfptcFlag() {
    return ulfptcFlag;
  }

  public void setUlfptcFlag(String ulfptcFlag) {
    this.ulfptcFlag = ulfptcFlag;
  }

}

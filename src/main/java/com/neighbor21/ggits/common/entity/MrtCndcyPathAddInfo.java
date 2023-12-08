package com.neighbor21.ggits.common.entity;
public class MrtCndcyPathAddInfo {
    private long    excnRsltId;        //실행 결과 아이디
    private long    cndcyPathId;        //후보 경로 아이디
    private long    totDstne;        //전체 거리
    private long    curvt;        //굴곡도
    private long    evalScore;        //평가 점수


  public long getExcnRsltId() {
    return excnRsltId;
  }

  public void setExcnRsltId(long excnRsltId) {
    this.excnRsltId = excnRsltId;
  }


  public long getCndcyPathId() {
    return cndcyPathId;
  }

  public void setCndcyPathId(long cndcyPathId) {
    this.cndcyPathId = cndcyPathId;
  }


  public long getTotDstne() {
    return totDstne;
  }

  public void setTotDstne(long totDstne) {
    this.totDstne = totDstne;
  }


  public long getCurvt() {
    return curvt;
  }

  public void setCurvt(long curvt) {
    this.curvt = curvt;
  }


  public long getEvalScore() {
    return evalScore;
  }

  public void setEvalScore(long evalScore) {
    this.evalScore = evalScore;
  }

}

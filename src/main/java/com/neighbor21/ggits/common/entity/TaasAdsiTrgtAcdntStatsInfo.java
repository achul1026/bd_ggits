package com.neighbor21.ggits.common.entity;

//행정시 대상 사고 통계 정보
public class TaasAdsiTrgtAcdntStatsInfo {

  private String crtrYy; //기준 년
  private String acdntClsfNm; //사고 분류 명
  private String adsiAdguNm; //행정시 행정구 명
  private long acdntCnt; //사고 수
  private long acdntCntOccurRt; //사고 수 발생 비율
  private long dcsdCnt; //사망자 수
  private long dcsdCntOccurRt; //사망자 수 발생 비율
  private long ftltRt; //치사성 비율
  private long injpsnCnt; //부상자 수
  private long injpsnCntOccurRt; //부상자 수 발생 비율
  private long totAcdntCnt; //전체 사고 수
  private long totDcsdCnt; //전체 사망자 수
  private long totInjpsnCnt; //전체 부상자 수
  private long popltn100KAcdntCnt; //인구 10만 사고 수
  private long vhcl10KAcdntCnt; //차량 1만 사고 수
  private long spdngAcdntCnt; //과속 사고 수
  private long ctLnVltnAcdntCnt; //중앙선 위반 사고 수
  private long siglVltnAcdntCnt; //신호 위반 사고 수
  private long safeDstneVltnAcdntCnt; //안전 거리 위반 사고 수
  private long safeDrvDutyVltnAcdntCnt; //안전 운전 의무 위반 사고 수
  private long crsrdPassMthdVltnAcdntCnt; //교차로 통행 방법 위반 사고 수
  private long pdstPrtcDutyVltnAcdntCnt; //보행자 보호 의무 위반 사고 수
  private long etcAcdntCnt; //기타 사고 수
  private long vhtoprAcdntCnt; //차대사람 사고 수
  private long vhtovhAcdntCnt; //차대차 사고 수
  private long vhaLnAcdntCnt; //차량단독 사고 수
  private long rlcrsAcdntCnt; //철길건널목 사고 수


  public String getCrtrYy() {
    return crtrYy;
  }

  public void setCrtrYy(String crtrYy) {
    this.crtrYy = crtrYy;
  }


  public String getAcdntClsfNm() {
    return acdntClsfNm;
  }

  public void setAcdntClsfNm(String acdntClsfNm) {
    this.acdntClsfNm = acdntClsfNm;
  }


  public String getAdsiAdguNm() {
    return adsiAdguNm;
  }

  public void setAdsiAdguNm(String adsiAdguNm) {
    this.adsiAdguNm = adsiAdguNm;
  }


  public long getAcdntCnt() {
    return acdntCnt;
  }

  public void setAcdntCnt(long acdntCnt) {
    this.acdntCnt = acdntCnt;
  }


  public long getAcdntCntOccurRt() {
    return acdntCntOccurRt;
  }

  public void setAcdntCntOccurRt(long acdntCntOccurRt) {
    this.acdntCntOccurRt = acdntCntOccurRt;
  }


  public long getDcsdCnt() {
    return dcsdCnt;
  }

  public void setDcsdCnt(long dcsdCnt) {
    this.dcsdCnt = dcsdCnt;
  }


  public long getDcsdCntOccurRt() {
    return dcsdCntOccurRt;
  }

  public void setDcsdCntOccurRt(long dcsdCntOccurRt) {
    this.dcsdCntOccurRt = dcsdCntOccurRt;
  }


  public long getFtltRt() {
    return ftltRt;
  }

  public void setFtltRt(long ftltRt) {
    this.ftltRt = ftltRt;
  }


  public long getInjpsnCnt() {
    return injpsnCnt;
  }

  public void setInjpsnCnt(long injpsnCnt) {
    this.injpsnCnt = injpsnCnt;
  }


  public long getInjpsnCntOccurRt() {
    return injpsnCntOccurRt;
  }

  public void setInjpsnCntOccurRt(long injpsnCntOccurRt) {
    this.injpsnCntOccurRt = injpsnCntOccurRt;
  }


  public long getTotAcdntCnt() {
    return totAcdntCnt;
  }

  public void setTotAcdntCnt(long totAcdntCnt) {
    this.totAcdntCnt = totAcdntCnt;
  }


  public long getTotDcsdCnt() {
    return totDcsdCnt;
  }

  public void setTotDcsdCnt(long totDcsdCnt) {
    this.totDcsdCnt = totDcsdCnt;
  }


  public long getTotInjpsnCnt() {
    return totInjpsnCnt;
  }

  public void setTotInjpsnCnt(long totInjpsnCnt) {
    this.totInjpsnCnt = totInjpsnCnt;
  }


  public long getPopltn100KAcdntCnt() {
    return popltn100KAcdntCnt;
  }

  public void setPopltn100KAcdntCnt(long popltn100KAcdntCnt) {
    this.popltn100KAcdntCnt = popltn100KAcdntCnt;
  }


  public long getVhcl10KAcdntCnt() {
    return vhcl10KAcdntCnt;
  }

  public void setVhcl10KAcdntCnt(long vhcl10KAcdntCnt) {
    this.vhcl10KAcdntCnt = vhcl10KAcdntCnt;
  }


  public long getSpdngAcdntCnt() {
    return spdngAcdntCnt;
  }

  public void setSpdngAcdntCnt(long spdngAcdntCnt) {
    this.spdngAcdntCnt = spdngAcdntCnt;
  }


  public long getCtLnVltnAcdntCnt() {
    return ctLnVltnAcdntCnt;
  }

  public void setCtLnVltnAcdntCnt(long ctLnVltnAcdntCnt) {
    this.ctLnVltnAcdntCnt = ctLnVltnAcdntCnt;
  }


  public long getSiglVltnAcdntCnt() {
    return siglVltnAcdntCnt;
  }

  public void setSiglVltnAcdntCnt(long siglVltnAcdntCnt) {
    this.siglVltnAcdntCnt = siglVltnAcdntCnt;
  }


  public long getSafeDstneVltnAcdntCnt() {
    return safeDstneVltnAcdntCnt;
  }

  public void setSafeDstneVltnAcdntCnt(long safeDstneVltnAcdntCnt) {
    this.safeDstneVltnAcdntCnt = safeDstneVltnAcdntCnt;
  }


  public long getSafeDrvDutyVltnAcdntCnt() {
    return safeDrvDutyVltnAcdntCnt;
  }

  public void setSafeDrvDutyVltnAcdntCnt(long safeDrvDutyVltnAcdntCnt) {
    this.safeDrvDutyVltnAcdntCnt = safeDrvDutyVltnAcdntCnt;
  }


  public long getCrsrdPassMthdVltnAcdntCnt() {
    return crsrdPassMthdVltnAcdntCnt;
  }

  public void setCrsrdPassMthdVltnAcdntCnt(long crsrdPassMthdVltnAcdntCnt) {
    this.crsrdPassMthdVltnAcdntCnt = crsrdPassMthdVltnAcdntCnt;
  }


  public long getPdstPrtcDutyVltnAcdntCnt() {
    return pdstPrtcDutyVltnAcdntCnt;
  }

  public void setPdstPrtcDutyVltnAcdntCnt(long pdstPrtcDutyVltnAcdntCnt) {
    this.pdstPrtcDutyVltnAcdntCnt = pdstPrtcDutyVltnAcdntCnt;
  }


  public long getEtcAcdntCnt() {
    return etcAcdntCnt;
  }

  public void setEtcAcdntCnt(long etcAcdntCnt) {
    this.etcAcdntCnt = etcAcdntCnt;
  }


  public long getVhtoprAcdntCnt() {
    return vhtoprAcdntCnt;
  }

  public void setVhtoprAcdntCnt(long vhtoprAcdntCnt) {
    this.vhtoprAcdntCnt = vhtoprAcdntCnt;
  }


  public long getVhtovhAcdntCnt() {
    return vhtovhAcdntCnt;
  }

  public void setVhtovhAcdntCnt(long vhtovhAcdntCnt) {
    this.vhtovhAcdntCnt = vhtovhAcdntCnt;
  }


  public long getVhaLnAcdntCnt() {
    return vhaLnAcdntCnt;
  }

  public void setVhaLnAcdntCnt(long vhaLnAcdntCnt) {
    this.vhaLnAcdntCnt = vhaLnAcdntCnt;
  }


  public long getRlcrsAcdntCnt() {
    return rlcrsAcdntCnt;
  }

  public void setRlcrsAcdntCnt(long rlcrsAcdntCnt) {
    this.rlcrsAcdntCnt = rlcrsAcdntCnt;
  }

}

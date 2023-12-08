package com.neighbor21.ggits.common.entity;

//공공 자전거 운영 정보
public class GgdtdrPublicBcyclOprtInfo {

  private String aggYy; //집계 년
  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String bizStartYy; //사업 시작 년
  private String oprtMthdType; //운영 방법 유형
  private long bcyclCstdPlaceCnt; //자전거 보관 장소 수
  private long bcyclHoldCnt; //자전거 보유 수
  private long instlBizAmt; //설치 사업 금액
  private long yyCntnMngAmt; //년 지속 관리 금액
  private String oprtMthdCnts; //운영 방법 내용
  private long befYyRentUseCnt; //전 년 대여 사용 수
  private String rmrk; //비고


  public String getAggYy() {
    return aggYy;
  }

  public void setAggYy(String aggYy) {
    this.aggYy = aggYy;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public String getBizStartYy() {
    return bizStartYy;
  }

  public void setBizStartYy(String bizStartYy) {
    this.bizStartYy = bizStartYy;
  }


  public String getOprtMthdType() {
    return oprtMthdType;
  }

  public void setOprtMthdType(String oprtMthdType) {
    this.oprtMthdType = oprtMthdType;
  }


  public long getBcyclCstdPlaceCnt() {
    return bcyclCstdPlaceCnt;
  }

  public void setBcyclCstdPlaceCnt(long bcyclCstdPlaceCnt) {
    this.bcyclCstdPlaceCnt = bcyclCstdPlaceCnt;
  }


  public long getBcyclHoldCnt() {
    return bcyclHoldCnt;
  }

  public void setBcyclHoldCnt(long bcyclHoldCnt) {
    this.bcyclHoldCnt = bcyclHoldCnt;
  }


  public long getInstlBizAmt() {
    return instlBizAmt;
  }

  public void setInstlBizAmt(long instlBizAmt) {
    this.instlBizAmt = instlBizAmt;
  }


  public long getYyCntnMngAmt() {
    return yyCntnMngAmt;
  }

  public void setYyCntnMngAmt(long yyCntnMngAmt) {
    this.yyCntnMngAmt = yyCntnMngAmt;
  }


  public String getOprtMthdCnts() {
    return oprtMthdCnts;
  }

  public void setOprtMthdCnts(String oprtMthdCnts) {
    this.oprtMthdCnts = oprtMthdCnts;
  }


  public long getBefYyRentUseCnt() {
    return befYyRentUseCnt;
  }

  public void setBefYyRentUseCnt(long befYyRentUseCnt) {
    this.befYyRentUseCnt = befYyRentUseCnt;
  }


  public String getRmrk() {
    return rmrk;
  }

  public void setRmrk(String rmrk) {
    this.rmrk = rmrk;
  }

}

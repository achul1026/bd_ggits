package com.neighbor21.ggits.common.entity;

//대중교통 환승 시설물 정보
public class GgdtdrPbtrnspTrsfrFcltsInfo {

  private String trsfrFcltsNm; //환승 시설물 명
  private String trsfrFcltsType; //환승 시설물 유형
  private String addoNm; //행정도 명
  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String locplcRoadNmAddr; //소재지 도로명 주소
  private String locplcLotnoAddr; //소재지 지번 주소
  private double latCrdn; //위도 좌표
  private double lonCrdn; //경도 좌표
  private long trsfrFcltsArea; //환승 시설물 면적
  private String trsfrPbtrnspFcltsNm; //환승 대중교통 시설물 명
  private String bstpId; //버스정류장 아이디
  private String bstpNm; //버스정류장 명
  private String subwayRouteNo; //지하철 노선 번호
  private String subwayRouteNm; //지하철 노선 명
  private String subwaySttnNm; //지하철 역 명
  private String rlroadRouteNo; //철도 노선 번호
  private String rlroadRouteNm; //철도 노선 명
  private String rlroadSttnNm; //철도 역 명
  private String pasngrVhclTrmnlNm; //여객 차량 터미널 명
  private String arprtPasngrTrmnlNm; //공항 여객 터미널 명
  private String harborPasngrTrsfrFcltsNm; //항만 여객 환승 시설물 명
  private long gnrlPklotCnt; //일반 주차구획 수
  private long lrgszPklotCnt; //대형 주차구획 수
  private String etcTrsfrTrfFcltsNm; //기타 환승 교통 시설물 명
  private String brllSgnlbrdInstlYn; //점자 표지판 설치 여부
  private String tfrwkerMbcnfcsInstlYn; //교통약자 이동편의시설 설치 여부
  private String cvntlInstlYn; //편의시설 설치 여부
  private String aggYmd; //집계 일자
  private String aggCnts; //집계 내용
  private String devStartYy; //개발 시작 년
  private String devEndYy; //개발 종료 년
  private String devMainObjType; //개발 주요 객체 유형
  private String oprtYn; //운영 여부
  private String dataCrtrYmd; //데이터 기준 일자


  public String getTrsfrFcltsNm() {
    return trsfrFcltsNm;
  }

  public void setTrsfrFcltsNm(String trsfrFcltsNm) {
    this.trsfrFcltsNm = trsfrFcltsNm;
  }


  public String getTrsfrFcltsType() {
    return trsfrFcltsType;
  }

  public void setTrsfrFcltsType(String trsfrFcltsType) {
    this.trsfrFcltsType = trsfrFcltsType;
  }


  public String getAddoNm() {
    return addoNm;
  }

  public void setAddoNm(String addoNm) {
    this.addoNm = addoNm;
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


  public String getLocplcRoadNmAddr() {
    return locplcRoadNmAddr;
  }

  public void setLocplcRoadNmAddr(String locplcRoadNmAddr) {
    this.locplcRoadNmAddr = locplcRoadNmAddr;
  }


  public String getLocplcLotnoAddr() {
    return locplcLotnoAddr;
  }

  public void setLocplcLotnoAddr(String locplcLotnoAddr) {
    this.locplcLotnoAddr = locplcLotnoAddr;
  }


  public double getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(double latCrdn) {
    this.latCrdn = latCrdn;
  }


  public double getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(double lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getTrsfrFcltsArea() {
    return trsfrFcltsArea;
  }

  public void setTrsfrFcltsArea(long trsfrFcltsArea) {
    this.trsfrFcltsArea = trsfrFcltsArea;
  }


  public String getTrsfrPbtrnspFcltsNm() {
    return trsfrPbtrnspFcltsNm;
  }

  public void setTrsfrPbtrnspFcltsNm(String trsfrPbtrnspFcltsNm) {
    this.trsfrPbtrnspFcltsNm = trsfrPbtrnspFcltsNm;
  }


  public String getBstpId() {
    return bstpId;
  }

  public void setBstpId(String bstpId) {
    this.bstpId = bstpId;
  }


  public String getBstpNm() {
    return bstpNm;
  }

  public void setBstpNm(String bstpNm) {
    this.bstpNm = bstpNm;
  }


  public String getSubwayRouteNo() {
    return subwayRouteNo;
  }

  public void setSubwayRouteNo(String subwayRouteNo) {
    this.subwayRouteNo = subwayRouteNo;
  }


  public String getSubwayRouteNm() {
    return subwayRouteNm;
  }

  public void setSubwayRouteNm(String subwayRouteNm) {
    this.subwayRouteNm = subwayRouteNm;
  }


  public String getSubwaySttnNm() {
    return subwaySttnNm;
  }

  public void setSubwaySttnNm(String subwaySttnNm) {
    this.subwaySttnNm = subwaySttnNm;
  }


  public String getRlroadRouteNo() {
    return rlroadRouteNo;
  }

  public void setRlroadRouteNo(String rlroadRouteNo) {
    this.rlroadRouteNo = rlroadRouteNo;
  }


  public String getRlroadRouteNm() {
    return rlroadRouteNm;
  }

  public void setRlroadRouteNm(String rlroadRouteNm) {
    this.rlroadRouteNm = rlroadRouteNm;
  }


  public String getRlroadSttnNm() {
    return rlroadSttnNm;
  }

  public void setRlroadSttnNm(String rlroadSttnNm) {
    this.rlroadSttnNm = rlroadSttnNm;
  }


  public String getPasngrVhclTrmnlNm() {
    return pasngrVhclTrmnlNm;
  }

  public void setPasngrVhclTrmnlNm(String pasngrVhclTrmnlNm) {
    this.pasngrVhclTrmnlNm = pasngrVhclTrmnlNm;
  }


  public String getArprtPasngrTrmnlNm() {
    return arprtPasngrTrmnlNm;
  }

  public void setArprtPasngrTrmnlNm(String arprtPasngrTrmnlNm) {
    this.arprtPasngrTrmnlNm = arprtPasngrTrmnlNm;
  }


  public String getHarborPasngrTrsfrFcltsNm() {
    return harborPasngrTrsfrFcltsNm;
  }

  public void setHarborPasngrTrsfrFcltsNm(String harborPasngrTrsfrFcltsNm) {
    this.harborPasngrTrsfrFcltsNm = harborPasngrTrsfrFcltsNm;
  }


  public long getGnrlPklotCnt() {
    return gnrlPklotCnt;
  }

  public void setGnrlPklotCnt(long gnrlPklotCnt) {
    this.gnrlPklotCnt = gnrlPklotCnt;
  }


  public long getLrgszPklotCnt() {
    return lrgszPklotCnt;
  }

  public void setLrgszPklotCnt(long lrgszPklotCnt) {
    this.lrgszPklotCnt = lrgszPklotCnt;
  }


  public String getEtcTrsfrTrfFcltsNm() {
    return etcTrsfrTrfFcltsNm;
  }

  public void setEtcTrsfrTrfFcltsNm(String etcTrsfrTrfFcltsNm) {
    this.etcTrsfrTrfFcltsNm = etcTrsfrTrfFcltsNm;
  }


  public String getBrllSgnlbrdInstlYn() {
    return brllSgnlbrdInstlYn;
  }

  public void setBrllSgnlbrdInstlYn(String brllSgnlbrdInstlYn) {
    this.brllSgnlbrdInstlYn = brllSgnlbrdInstlYn;
  }


  public String getTfrwkerMbcnfcsInstlYn() {
    return tfrwkerMbcnfcsInstlYn;
  }

  public void setTfrwkerMbcnfcsInstlYn(String tfrwkerMbcnfcsInstlYn) {
    this.tfrwkerMbcnfcsInstlYn = tfrwkerMbcnfcsInstlYn;
  }


  public String getCvntlInstlYn() {
    return cvntlInstlYn;
  }

  public void setCvntlInstlYn(String cvntlInstlYn) {
    this.cvntlInstlYn = cvntlInstlYn;
  }


  public String getAggYmd() {
    return aggYmd;
  }

  public void setAggYmd(String aggYmd) {
    this.aggYmd = aggYmd;
  }


  public String getAggCnts() {
    return aggCnts;
  }

  public void setAggCnts(String aggCnts) {
    this.aggCnts = aggCnts;
  }


  public String getDevStartYy() {
    return devStartYy;
  }

  public void setDevStartYy(String devStartYy) {
    this.devStartYy = devStartYy;
  }


  public String getDevEndYy() {
    return devEndYy;
  }

  public void setDevEndYy(String devEndYy) {
    this.devEndYy = devEndYy;
  }


  public String getDevMainObjType() {
    return devMainObjType;
  }

  public void setDevMainObjType(String devMainObjType) {
    this.devMainObjType = devMainObjType;
  }


  public String getOprtYn() {
    return oprtYn;
  }

  public void setOprtYn(String oprtYn) {
    this.oprtYn = oprtYn;
  }


  public String getDataCrtrYmd() {
    return dataCrtrYmd;
  }

  public void setDataCrtrYmd(String dataCrtrYmd) {
    this.dataCrtrYmd = dataCrtrYmd;
  }

}

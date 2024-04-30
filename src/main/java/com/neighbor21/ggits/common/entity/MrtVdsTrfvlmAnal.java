package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//VDS 교통패턴
public class MrtVdsTrfvlmAnal {
  private String mngInstCd;
  private Timestamp clct_dt;
  private String stdLinkId;
  private Long trfvlm;
  private Double avgSpeed;

  private Long vhclTrfvlm;        //차량 교통량
  private Long avgVhclSpeed;        //평균 차량 속도

  private double avgVhclSpeedAvg; // 시간대 평균속도
  private long vhclTrfvlmTotal; // 누적통행량
  private double vhclTrfvlmAvg; // 평균통행량
  private String timeGroupTxt; // 시간대별 평균속도 통행량 yyyy-MM-dd HH:mi|평균속도|교통량 ex)2023-09-22 15:38|19.00|0$$2023-09-22 16:18|22.00|0

  //링크정보
  private String linkId;
  private Long gid;
  private String fNode;
  private String tNode;
  private Long lanes;
  private String roadRank;
  private String roadType;
  private String roadNo;
  private String roadName;
  private String roadUse;
  private String multiLink;
  private String connect;
  private Long maxSpd;
  private String restVeh;
  private Long restW;
  private Long restH;
  private Long length;
  private String remark;
  private String geom;
  private String geojson;
  private String time;

  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }

  public Timestamp getClct_dt() {
    return clct_dt;
  }

  public void setClct_dt(Timestamp clct_dt) {
    this.clct_dt = clct_dt;
  }

  public String getStdLinkId() {
    return stdLinkId;
  }

  public void setStdLinkId(String stdLinkId) {
    this.stdLinkId = stdLinkId;
  }

  public Long getTrfvlm() {
    return trfvlm;
  }

  public void setTrfvlm(Long trfvlm) {
    this.trfvlm = trfvlm;
  }

  public Double getAvgSpeed() {
    return avgSpeed;
  }

  public void setAvgSpeed(Double avgSpeed) {
    this.avgSpeed = avgSpeed;
  }

  public Long getVhclTrfvlm() {
    return vhclTrfvlm;
  }

  public void setVhclTrfvlm(Long vhclTrfvlm) {
    this.vhclTrfvlm = vhclTrfvlm;
  }

  public Long getAvgVhclSpeed() {
    return avgVhclSpeed;
  }

  public void setAvgVhclSpeed(Long avgVhclSpeed) {
    this.avgVhclSpeed = avgVhclSpeed;
  }

  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }

  public Long getGid() {
    return gid;
  }

  public void setGid(Long gid) {
    this.gid = gid;
  }

  public String getfNode() {
    return fNode;
  }

  public void setfNode(String fNode) {
    this.fNode = fNode;
  }

  public String gettNode() {
    return tNode;
  }

  public void settNode(String tNode) {
    this.tNode = tNode;
  }

  public Long getLanes() {
    return lanes;
  }

  public void setLanes(Long lanes) {
    this.lanes = lanes;
  }

  public String getRoadRank() {
    return roadRank;
  }

  public void setRoadRank(String roadRank) {
    this.roadRank = roadRank;
  }

  public String getRoadType() {
    return roadType;
  }

  public void setRoadType(String roadType) {
    this.roadType = roadType;
  }

  public String getRoadNo() {
    return roadNo;
  }

  public void setRoadNo(String roadNo) {
    this.roadNo = roadNo;
  }

  public String getRoadName() {
    return roadName;
  }

  public void setRoadName(String roadName) {
    this.roadName = roadName;
  }

  public String getRoadUse() {
    return roadUse;
  }

  public void setRoadUse(String roadUse) {
    this.roadUse = roadUse;
  }

  public String getMultiLink() {
    return multiLink;
  }

  public void setMultiLink(String multiLink) {
    this.multiLink = multiLink;
  }

  public String getConnect() {
    return connect;
  }

  public void setConnect(String connect) {
    this.connect = connect;
  }

  public Long getMaxSpd() {
    return maxSpd;
  }

  public void setMaxSpd(Long maxSpd) {
    this.maxSpd = maxSpd;
  }

  public String getRestVeh() {
    return restVeh;
  }

  public void setRestVeh(String restVeh) {
    this.restVeh = restVeh;
  }

  public Long getRestW() {
    return restW;
  }

  public void setRestW(Long restW) {
    this.restW = restW;
  }

  public Long getRestH() {
    return restH;
  }

  public void setRestH(Long restH) {
    this.restH = restH;
  }

  public Long getLength() {
    return length;
  }

  public void setLength(Long length) {
    this.length = length;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getGeom() {
    return geom;
  }

  public void setGeom(String geom) {
    this.geom = geom;
  }

  public String getGeojson() {
    return geojson;
  }

  public void setGeojson(String geojson) {
    this.geojson = geojson;
  }

  public double getAvgVhclSpeedAvg() {
    return avgVhclSpeedAvg;
  }

  public void setAvgVhclSpeedAvg(double avgVhclSpeedAvg) {
    this.avgVhclSpeedAvg = avgVhclSpeedAvg;
  }

  public long getVhclTrfvlmTotal() {
    return vhclTrfvlmTotal;
  }

  public void setVhclTrfvlmTotal(long vhclTrfvlmTotal) {
    this.vhclTrfvlmTotal = vhclTrfvlmTotal;
  }

  public double getVhclTrfvlmAvg() {
    return vhclTrfvlmAvg;
  }

  public void setVhclTrfvlmAvg(double vhclTrfvlmAvg) {
    this.vhclTrfvlmAvg = vhclTrfvlmAvg;
  }

  public String getTimeGroupTxt() {
    return timeGroupTxt;
  }

  public void setTimeGroupTxt(String timeGroupTxt) {
    this.timeGroupTxt = timeGroupTxt;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}

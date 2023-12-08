package com.neighbor21.ggits.common.entity;

public class GyCommInfoLink extends CommonEntity {
    private long    gid;        
    private String    linkId;        
    private String    fNode;        
    private String    tNode;        
    private long    lanes;        
    private String    roadRank;        
    private String    roadType;        
    private String    roadNo;        
    private String    roadName;        
    private String    roadUse;        
    private String    multiLink;        
    private String    connect;        
    private long    maxSpd;        
    private String    restVeh;        
    private long    restW;        
    private long    restH;        
    private long    length;        
    private String    remark;        
    private String    geom;
    private double  x;	//미보유 컬럼 geometry lag값
    private double  y;	//미보유 컬럼 geometry lng 값


  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getFNode() {
    return fNode;
  }

  public void setFNode(String fNode) {
    this.fNode = fNode;
  }


  public String getTNode() {
    return tNode;
  }

  public void setTNode(String tNode) {
    this.tNode = tNode;
  }


  public long getLanes() {
    return lanes;
  }

  public void setLanes(long lanes) {
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


  public long getMaxSpd() {
    return maxSpd;
  }

  public void setMaxSpd(long maxSpd) {
    this.maxSpd = maxSpd;
  }


  public String getRestVeh() {
    return restVeh;
  }

  public void setRestVeh(String restVeh) {
    this.restVeh = restVeh;
  }


  public long getRestW() {
    return restW;
  }

  public void setRestW(long restW) {
    this.restW = restW;
  }


  public long getRestH() {
    return restH;
  }

  public void setRestH(long restH) {
    this.restH = restH;
  }


  public long getLength() {
    return length;
  }

  public void setLength(long length) {
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

  public double getX() {
	return x;
  }

  public void setX(double x) {
	this.x = x;
  }

  public double getY() {
	return y;
  }

  public void setY(double y) {
	this.y = y;
  }

}

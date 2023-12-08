package com.neighbor21.ggits.common.entity;
public class GyCommInfoNode extends CommonEntity {
    private long    gid;        
    private String    nodeId;        
    private String    nodeType;        
    private String    nodeName;        
    private String    turnP;        
    private String    remark;        
    private double    X;        
    private double    Y;        
    private String    geom;


  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }


  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }


  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }


  public String getTurnP() {
    return turnP;
  }

  public void setTurnP(String turnP) {
    this.turnP = turnP;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }



  public double getX() {
	return X;
  }

  public void setX(double x) {
	X = x;
  }

  public double getY() {
	return Y;
  }

  public void setY(double y) {
	Y = y;
  }

  public String getGeom() {
	  return geom;
  }

  public void setGeom(String geom) {
	this.geom = geom;
  }

}

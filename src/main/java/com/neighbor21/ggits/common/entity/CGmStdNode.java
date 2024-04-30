package com.neighbor21.ggits.common.entity;
public class CGmStdNode extends CommonEntity {
    private String    nodeId;        
    private String    nodeType;        
    private String    nodeName;        
    private String    turnP;        
    private String    remark;        
    private String    geometry;        

    private double  x;	//미보유 컬럼 geometry lag값
    private double  y;	//미보유 컬럼 geometry lng 값

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

  public String getGeometry() {
    return geometry;
  }

  public void setGeometry(String geometry) {
    this.geometry = geometry;
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

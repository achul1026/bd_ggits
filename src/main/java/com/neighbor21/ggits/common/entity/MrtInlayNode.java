package com.neighbor21.ggits.common.entity;
public class MrtInlayNode {
    private String    baseym;        //기준년월
    private String    routeId;        //노선ID
    private String    updown;        //노선방향
    private String    nodeId;        //노드ID
    private String    nodeType;        //노드유형
    private String    nodeName;        //노드명칭
    private String    turnP;        //회전제한유무
    private String    remark;        //비고
    private String    pointWgs84;        //WGS84_좌표
    private String    etlDt;        //ETL 일시


  public String getBaseym() {
    return baseym;
  }

  public void setBaseym(String baseym) {
    this.baseym = baseym;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getUpdown() {
    return updown;
  }

  public void setUpdown(String updown) {
    this.updown = updown;
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


  public String getPointWgs84() {
    return pointWgs84;
  }

  public void setPointWgs84(String pointWgs84) {
    this.pointWgs84 = pointWgs84;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}

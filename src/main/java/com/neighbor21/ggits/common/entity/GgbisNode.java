package com.neighbor21.ggits.common.entity;
public class GgbisNode {
    private String    nodeId;        //노드 ID
    private String    nodeNm;        //노드명칭
    private String    nodeEngNm;        //노드영문명칭
    private String    nodeTp;        //노드유형
    private long    approaches;        //접근로 수
    private String    turnYn;        //회전제한유무
    private String    gid;        //GID
    private String    areaCd;        //지역코드
    private long    gpsX;        //GPS X좌표
    private long    gpsY;        //GPS Y좌표
    private long    mapX;        //MAP X좌표
    private long    mapY;        //MAP Y좌표
    private long    localNo;        //Local 번호
    private long    preRadius;        //앞 반경
    private long    nextRadius;        //뒤 반경


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }


  public String getNodeNm() {
    return nodeNm;
  }

  public void setNodeNm(String nodeNm) {
    this.nodeNm = nodeNm;
  }


  public String getNodeEngNm() {
    return nodeEngNm;
  }

  public void setNodeEngNm(String nodeEngNm) {
    this.nodeEngNm = nodeEngNm;
  }


  public String getNodeTp() {
    return nodeTp;
  }

  public void setNodeTp(String nodeTp) {
    this.nodeTp = nodeTp;
  }


  public long getApproaches() {
    return approaches;
  }

  public void setApproaches(long approaches) {
    this.approaches = approaches;
  }


  public String getTurnYn() {
    return turnYn;
  }

  public void setTurnYn(String turnYn) {
    this.turnYn = turnYn;
  }


  public String getGid() {
    return gid;
  }

  public void setGid(String gid) {
    this.gid = gid;
  }


  public String getAreaCd() {
    return areaCd;
  }

  public void setAreaCd(String areaCd) {
    this.areaCd = areaCd;
  }


  public long getGpsX() {
    return gpsX;
  }

  public void setGpsX(long gpsX) {
    this.gpsX = gpsX;
  }


  public long getGpsY() {
    return gpsY;
  }

  public void setGpsY(long gpsY) {
    this.gpsY = gpsY;
  }


  public long getMapX() {
    return mapX;
  }

  public void setMapX(long mapX) {
    this.mapX = mapX;
  }


  public long getMapY() {
    return mapY;
  }

  public void setMapY(long mapY) {
    this.mapY = mapY;
  }


  public long getLocalNo() {
    return localNo;
  }

  public void setLocalNo(long localNo) {
    this.localNo = localNo;
  }


  public long getPreRadius() {
    return preRadius;
  }

  public void setPreRadius(long preRadius) {
    this.preRadius = preRadius;
  }


  public long getNextRadius() {
    return nextRadius;
  }

  public void setNextRadius(long nextRadius) {
    this.nextRadius = nextRadius;
  }

}

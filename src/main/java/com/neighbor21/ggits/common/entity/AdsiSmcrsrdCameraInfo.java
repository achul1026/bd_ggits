package com.neighbor21.ggits.common.entity;

//스마트교차로 카메라 정보
public class AdsiSmcrsrdCameraInfo {

  private String mngInstCd; //관리 기관 코드
  private String cameraId; //카메라 아이디
  private long cameraUid; //카메라 uid
  private String cameraNm; //카메라 명
  private double lonCrdn; //경도 좌표
  private double latCrdn; //위도 좌표
  private long angl; //각도
  private String crsrdId; //교차로 아이디
  private String acsRoadId; //접근 도로 아이디
  private String srvrId; //서버 아이디

  private String crsrdNm; //교차로 명
  private long maxTrfvlm; //최대 교통량
  private long maxPdstCnt; //최대 보행자 수

  private String nodeType; // 노드 타입
  private String nodeName; // 노드 이름
  private String nodeId; // 노드 아이디



  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getCameraId() {
    return cameraId;
  }

  public void setCameraId(String cameraId) {
    this.cameraId = cameraId;
  }


  public long getCameraUid() {
    return cameraUid;
  }

  public void setCameraUid(long cameraUid) {
    this.cameraUid = cameraUid;
  }


  public String getCameraNm() {
    return cameraNm;
  }

  public void setCameraNm(String cameraNm) {
    this.cameraNm = cameraNm;
  }
  public double getLonCrdn() {
	return lonCrdn;
  }

  public void setLonCrdn(double lonCrdn) {
	this.lonCrdn = lonCrdn;
  }

  public double getLatCrdn() {
	return latCrdn;
  }

  public void setLatCrdn(double latCrdn) {
	this.latCrdn = latCrdn;
  }

public long getAngl() {
    return angl;
  }

  public void setAngl(long angl) {
    this.angl = angl;
  }


  public String getCrsrdId() {
    return crsrdId;
  }

  public void setCrsrdId(String crsrdId) {
    this.crsrdId = crsrdId;
  }


  public String getAcsRoadId() {
    return acsRoadId;
  }

  public void setAcsRoadId(String acsRoadId) {
    this.acsRoadId = acsRoadId;
  }


  public String getSrvrId() {
    return srvrId;
  }

  public void setSrvrId(String srvrId) {
    this.srvrId = srvrId;
  }

  public String getCrsrdNm() {
    return crsrdNm;
  }

  public void setCrsrdNm(String crsrdNm) {
    this.crsrdNm = crsrdNm;
  }

  public long getMaxTrfvlm() {
    return maxTrfvlm;
  }

  public void setMaxTrfvlm(long maxTrfvlm) {
    this.maxTrfvlm = maxTrfvlm;
  }

  public long getMaxPdstCnt() {
    return maxPdstCnt;
  }

  public void setMaxPdstCnt(long maxPdstCnt) {
    this.maxPdstCnt = maxPdstCnt;
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

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }
}

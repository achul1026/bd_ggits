package com.neighbor21.ggits.common.entity;
public class GgbisLink {
    private String    linkId;        //도로 ID
    private String    fnodeId;        //시작노드 ID
    private String    tnodeId;        //종료노드 ID
    private String    roadNm;        //도로명칭
    private long    roadWidth;        //도로폭
    private long    roadLength;        //도로길이
    private long    laneCnt;        //차로수
    private String    roadClass;        //도로등급
    private long    designSpeed;        //설계속도
    private long    maxSpeed;        //최대속도(제한최대속도)
    private long    minSpeed;        //최소속도(제한최소속도)
    private String    shoulderYn;        //갓길 유무
    private String    dividerYn;        //중앙분리대 유무
    private String    buslaneYn;        //버스전용차로 유무
    private String    restrictTp;        //통행제한 차량 유형
    private long    restrictWeight;        //통과제한 하중
    private long    restrictHeight;        //통과제한 높이
    private long    roadNo;        //도로번호
    private String    roadLevel;        //도로레벨
    private String    virtuallinkYn;        //가상링크 여부
    private long    startDistance;        //기점거리
    private long    endDistance;        //종점거리
    private String    gid;        //GID
    private String    rampYn;        //도로램프 유무
    private String    paveYn;        //도로포장 유무
    private String    tollYn;        //통행료징수 유무
    private String    facilityTp;        //도로부속시설 유형
    private String    facilityNm;        //도로부속시설 명칭
    private long    overCnt;        //중용도로수
    private long    variablelaneCnt;        //가변차선수
    private String    refroadYn;        //누락도로 여부
    private String    newroadYn;        //신규도로 여부
    private String    adminNm;        //도로관리기관
    private String    uplinkId;        //상위도로 ID


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getFnodeId() {
    return fnodeId;
  }

  public void setFnodeId(String fnodeId) {
    this.fnodeId = fnodeId;
  }


  public String getTnodeId() {
    return tnodeId;
  }

  public void setTnodeId(String tnodeId) {
    this.tnodeId = tnodeId;
  }


  public String getRoadNm() {
    return roadNm;
  }

  public void setRoadNm(String roadNm) {
    this.roadNm = roadNm;
  }


  public long getRoadWidth() {
    return roadWidth;
  }

  public void setRoadWidth(long roadWidth) {
    this.roadWidth = roadWidth;
  }


  public long getRoadLength() {
    return roadLength;
  }

  public void setRoadLength(long roadLength) {
    this.roadLength = roadLength;
  }


  public long getLaneCnt() {
    return laneCnt;
  }

  public void setLaneCnt(long laneCnt) {
    this.laneCnt = laneCnt;
  }


  public String getRoadClass() {
    return roadClass;
  }

  public void setRoadClass(String roadClass) {
    this.roadClass = roadClass;
  }


  public long getDesignSpeed() {
    return designSpeed;
  }

  public void setDesignSpeed(long designSpeed) {
    this.designSpeed = designSpeed;
  }


  public long getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(long maxSpeed) {
    this.maxSpeed = maxSpeed;
  }


  public long getMinSpeed() {
    return minSpeed;
  }

  public void setMinSpeed(long minSpeed) {
    this.minSpeed = minSpeed;
  }


  public String getShoulderYn() {
    return shoulderYn;
  }

  public void setShoulderYn(String shoulderYn) {
    this.shoulderYn = shoulderYn;
  }


  public String getDividerYn() {
    return dividerYn;
  }

  public void setDividerYn(String dividerYn) {
    this.dividerYn = dividerYn;
  }


  public String getBuslaneYn() {
    return buslaneYn;
  }

  public void setBuslaneYn(String buslaneYn) {
    this.buslaneYn = buslaneYn;
  }


  public String getRestrictTp() {
    return restrictTp;
  }

  public void setRestrictTp(String restrictTp) {
    this.restrictTp = restrictTp;
  }


  public long getRestrictWeight() {
    return restrictWeight;
  }

  public void setRestrictWeight(long restrictWeight) {
    this.restrictWeight = restrictWeight;
  }


  public long getRestrictHeight() {
    return restrictHeight;
  }

  public void setRestrictHeight(long restrictHeight) {
    this.restrictHeight = restrictHeight;
  }


  public long getRoadNo() {
    return roadNo;
  }

  public void setRoadNo(long roadNo) {
    this.roadNo = roadNo;
  }


  public String getRoadLevel() {
    return roadLevel;
  }

  public void setRoadLevel(String roadLevel) {
    this.roadLevel = roadLevel;
  }


  public String getVirtuallinkYn() {
    return virtuallinkYn;
  }

  public void setVirtuallinkYn(String virtuallinkYn) {
    this.virtuallinkYn = virtuallinkYn;
  }


  public long getStartDistance() {
    return startDistance;
  }

  public void setStartDistance(long startDistance) {
    this.startDistance = startDistance;
  }


  public long getEndDistance() {
    return endDistance;
  }

  public void setEndDistance(long endDistance) {
    this.endDistance = endDistance;
  }


  public String getGid() {
    return gid;
  }

  public void setGid(String gid) {
    this.gid = gid;
  }


  public String getRampYn() {
    return rampYn;
  }

  public void setRampYn(String rampYn) {
    this.rampYn = rampYn;
  }


  public String getPaveYn() {
    return paveYn;
  }

  public void setPaveYn(String paveYn) {
    this.paveYn = paveYn;
  }


  public String getTollYn() {
    return tollYn;
  }

  public void setTollYn(String tollYn) {
    this.tollYn = tollYn;
  }


  public String getFacilityTp() {
    return facilityTp;
  }

  public void setFacilityTp(String facilityTp) {
    this.facilityTp = facilityTp;
  }


  public String getFacilityNm() {
    return facilityNm;
  }

  public void setFacilityNm(String facilityNm) {
    this.facilityNm = facilityNm;
  }


  public long getOverCnt() {
    return overCnt;
  }

  public void setOverCnt(long overCnt) {
    this.overCnt = overCnt;
  }


  public long getVariablelaneCnt() {
    return variablelaneCnt;
  }

  public void setVariablelaneCnt(long variablelaneCnt) {
    this.variablelaneCnt = variablelaneCnt;
  }


  public String getRefroadYn() {
    return refroadYn;
  }

  public void setRefroadYn(String refroadYn) {
    this.refroadYn = refroadYn;
  }


  public String getNewroadYn() {
    return newroadYn;
  }

  public void setNewroadYn(String newroadYn) {
    this.newroadYn = newroadYn;
  }


  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }


  public String getUplinkId() {
    return uplinkId;
  }

  public void setUplinkId(String uplinkId) {
    this.uplinkId = uplinkId;
  }

}

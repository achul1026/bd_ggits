package com.neighbor21.ggits.common.entity;
public class GgbisBusEventinfo {
    private String    collectId;        //수집ID
    private long    serverNo;        //수집서버번호
    private String    vehId;        //차량ID
    private String    collectDate;        //정보생성일시(버스에서 생성한 시간)
    private String    locationId;        //교차로/정류소 ID
    private String    locationTp;        //교차로/정류소 구분(S:정류소 I:교차로)
    private String    routeId;        //버스노선ID
    private String    eventCd;        //이벤트코드
    private long    serviceTime;        //정차시간
    private String    systemDate;        //정보기록일시
    private long    gpsX;        //Local X좌표
    private long    gpsY;        //Local Y좌표
    private String    errorCd;        //오류코드
    private String    ebcollectDate;        //EB센터 수집시간
    private String    bmscollectDate;        //BMS센터 수집시간
    private String    eventTp;        //이벤트 구분(1:실시간정보 2:EB history정보 3:내부 history정보)
    private long    pacNo;        //패킷 번호
    private String    lvehCd;        //막차코드(0:막차아님 1:막차)
    private String    lvehStationid;        //막차의 최종 도착정류소ID
    private String    doorOpcd;        //도어개폐코드(0:개폐없음 1:개폐있음)
    private long    remainseatCnt;        //빈자리수
    private long    passengerCnt;        //탑승중인 승객 인원
    private long    totalseatCnt;        //전체 좌석수(현재 혼잡도로 사용중)
    private long    partNo;        //파트구분


  public String getCollectId() {
    return collectId;
  }

  public void setCollectId(String collectId) {
    this.collectId = collectId;
  }


  public long getServerNo() {
    return serverNo;
  }

  public void setServerNo(long serverNo) {
    this.serverNo = serverNo;
  }


  public String getVehId() {
    return vehId;
  }

  public void setVehId(String vehId) {
    this.vehId = vehId;
  }


  public String getCollectDate() {
    return collectDate;
  }

  public void setCollectDate(String collectDate) {
    this.collectDate = collectDate;
  }


  public String getLocationId() {
    return locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }


  public String getLocationTp() {
    return locationTp;
  }

  public void setLocationTp(String locationTp) {
    this.locationTp = locationTp;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getEventCd() {
    return eventCd;
  }

  public void setEventCd(String eventCd) {
    this.eventCd = eventCd;
  }


  public long getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(long serviceTime) {
    this.serviceTime = serviceTime;
  }


  public String getSystemDate() {
    return systemDate;
  }

  public void setSystemDate(String systemDate) {
    this.systemDate = systemDate;
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


  public String getErrorCd() {
    return errorCd;
  }

  public void setErrorCd(String errorCd) {
    this.errorCd = errorCd;
  }


  public String getEbcollectDate() {
    return ebcollectDate;
  }

  public void setEbcollectDate(String ebcollectDate) {
    this.ebcollectDate = ebcollectDate;
  }


  public String getBmscollectDate() {
    return bmscollectDate;
  }

  public void setBmscollectDate(String bmscollectDate) {
    this.bmscollectDate = bmscollectDate;
  }


  public String getEventTp() {
    return eventTp;
  }

  public void setEventTp(String eventTp) {
    this.eventTp = eventTp;
  }


  public long getPacNo() {
    return pacNo;
  }

  public void setPacNo(long pacNo) {
    this.pacNo = pacNo;
  }


  public String getLvehCd() {
    return lvehCd;
  }

  public void setLvehCd(String lvehCd) {
    this.lvehCd = lvehCd;
  }


  public String getLvehStationid() {
    return lvehStationid;
  }

  public void setLvehStationid(String lvehStationid) {
    this.lvehStationid = lvehStationid;
  }


  public String getDoorOpcd() {
    return doorOpcd;
  }

  public void setDoorOpcd(String doorOpcd) {
    this.doorOpcd = doorOpcd;
  }


  public long getRemainseatCnt() {
    return remainseatCnt;
  }

  public void setRemainseatCnt(long remainseatCnt) {
    this.remainseatCnt = remainseatCnt;
  }


  public long getPassengerCnt() {
    return passengerCnt;
  }

  public void setPassengerCnt(long passengerCnt) {
    this.passengerCnt = passengerCnt;
  }


  public long getTotalseatCnt() {
    return totalseatCnt;
  }

  public void setTotalseatCnt(long totalseatCnt) {
    this.totalseatCnt = totalseatCnt;
  }


  public long getPartNo() {
    return partNo;
  }

  public void setPartNo(long partNo) {
    this.partNo = partNo;
  }

}

package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsLEvcEvent {
    private Timestamp    createDate;        //서비스 생성 일시
    private String    serviceId;        //서비스 ID
    private long    evcEvent;        //EVC이벤트
    private String    evCurLat;        //EV현재좌표_LAT
    private String    evCurLng;        //EV현재좌표_LNG
    private long    evCurSpeed;        //EV현재속도
    private long    evRemainDistance;        //목적지남은거리


  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }


  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }


  public long getEvcEvent() {
    return evcEvent;
  }

  public void setEvcEvent(long evcEvent) {
    this.evcEvent = evcEvent;
  }


  public String getEvCurLat() {
    return evCurLat;
  }

  public void setEvCurLat(String evCurLat) {
    this.evCurLat = evCurLat;
  }


  public String getEvCurLng() {
    return evCurLng;
  }

  public void setEvCurLng(String evCurLng) {
    this.evCurLng = evCurLng;
  }


  public long getEvCurSpeed() {
    return evCurSpeed;
  }

  public void setEvCurSpeed(long evCurSpeed) {
    this.evCurSpeed = evCurSpeed;
  }


  public long getEvRemainDistance() {
    return evRemainDistance;
  }

  public void setEvRemainDistance(long evRemainDistance) {
    this.evRemainDistance = evRemainDistance;
  }

}

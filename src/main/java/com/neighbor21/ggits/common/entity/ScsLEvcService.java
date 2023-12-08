package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsLEvcService {
    private Timestamp    createDate;        //서비스생성일시
    private String    serviceId;        //서비스 ID
    private String    serviceName;        //서비스명
    private String    originationLat;        //출발지_LAT
    private String    originationLng;        //출발지_LNG
    private String    destinationLat;        //도착지_LAT
    private String    destinationLng;        //도착지_LNG
    private String    status;        //서비스 상태
    private long    serviceTravel;        //서비스 시간
    private long    serviceDistance;        //서비스 거리
    private long    deviceNo;        //디바이스 번호
    private String    routePac;        //노선정보
    private String    lcList;        //교차로정보
    private String    ocrNo;        //재난코드번호
    private String    ocrType;        //재난종별명
    private long    arrivalTime;        //도착예정시간
    private long    arrivalDistance;        //도착예정거리


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


  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }


  public String getOriginationLat() {
    return originationLat;
  }

  public void setOriginationLat(String originationLat) {
    this.originationLat = originationLat;
  }


  public String getOriginationLng() {
    return originationLng;
  }

  public void setOriginationLng(String originationLng) {
    this.originationLng = originationLng;
  }


  public String getDestinationLat() {
    return destinationLat;
  }

  public void setDestinationLat(String destinationLat) {
    this.destinationLat = destinationLat;
  }


  public String getDestinationLng() {
    return destinationLng;
  }

  public void setDestinationLng(String destinationLng) {
    this.destinationLng = destinationLng;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public long getServiceTravel() {
    return serviceTravel;
  }

  public void setServiceTravel(long serviceTravel) {
    this.serviceTravel = serviceTravel;
  }


  public long getServiceDistance() {
    return serviceDistance;
  }

  public void setServiceDistance(long serviceDistance) {
    this.serviceDistance = serviceDistance;
  }


  public long getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(long deviceNo) {
    this.deviceNo = deviceNo;
  }


  public String getRoutePac() {
    return routePac;
  }

  public void setRoutePac(String routePac) {
    this.routePac = routePac;
  }


  public String getLcList() {
    return lcList;
  }

  public void setLcList(String lcList) {
    this.lcList = lcList;
  }


  public String getOcrNo() {
    return ocrNo;
  }

  public void setOcrNo(String ocrNo) {
    this.ocrNo = ocrNo;
  }


  public String getOcrType() {
    return ocrType;
  }

  public void setOcrType(String ocrType) {
    this.ocrType = ocrType;
  }


  public long getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(long arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  public long getArrivalDistance() {
    return arrivalDistance;
  }

  public void setArrivalDistance(long arrivalDistance) {
    this.arrivalDistance = arrivalDistance;
  }

}

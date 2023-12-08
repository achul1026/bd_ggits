package com.neighbor21.ggits.api.module.monitoring.dto;

import com.neighbor21.ggits.api.module.common.dto.GitsFeatureInfo;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  MBusInfo
 * @since : 2023-09-07
 */
public class MBusInfo extends GitsFeatureInfo {

    String vehicleNumber;
    String lat;
    String lng;
    String startLng;
    String startLat;
    String endLng;
    String endLat;
    String startLocationNm;
    String endLocationNm;
    String startTime;
    String endTime;
    String precisionTime;
    String realEndtime;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getStartLocationNm() {
        return startLocationNm;
    }

    public void setStartLocationNm(String startLocationNm) {
        this.startLocationNm = startLocationNm;
    }

    public String getEndLocationNm() {
        return endLocationNm;
    }

    public void setEndLocationNm(String endLocationNm) {
        this.endLocationNm = endLocationNm;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPrecisionTime() {
        return precisionTime;
    }

    public void setPrecisionTime(String precisionTime) {
        this.precisionTime = precisionTime;
    }

    public String getRealEndtime() {
        return realEndtime;
    }

    public void setRealEndtime(String realEndtime) {
        this.realEndtime = realEndtime;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getEndLng() {
        return endLng;
    }

    public void setEndLng(String endLng) {
        this.endLng = endLng;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}

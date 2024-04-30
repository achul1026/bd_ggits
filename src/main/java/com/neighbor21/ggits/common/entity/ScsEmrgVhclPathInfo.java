package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsEmrgVhclPathInfo {
	private String serviceid;
	private String evno;
	private Double currentlat;
	private Double currentlng;
	private String servicename;
	private Double arrivallat;
	private Double arrivallng;
	private String route;
	private Long vehiclelength;
	private String ocrno;
	private String ocrtype;
	private Long arrivaltime;

	private String arrivaltimeFormat;
	private String emrgCurSttsCd; //CUS001 : 이동완료 / CUS002 : 이동중
	private Timestamp    startDate;    
	private Timestamp    predictedArrivalDate; // 예측 도착 시간    
	private Timestamp    arrivalDate;
	private String routeGeojson;
	private String timeDifference;

	private Double startlat;
	private Double startlng;
	
	private String firename;
	
	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public String getEvno() {
		return evno;
	}

	public void setEvno(String evno) {
		this.evno = evno;
	}

	public Double getCurrentlat() {
		return currentlat;
	}

	public void setCurrentlat(Double currentlat) {
		this.currentlat = currentlat;
	}

	public Double getCurrentlng() {
		return currentlng;
	}

	public void setCurrentlng(Double currentlng) {
		this.currentlng = currentlng;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public Double getArrivallat() {
		return arrivallat;
	}

	public void setArrivallat(Double arrivallat) {
		this.arrivallat = arrivallat;
	}

	public Double getArrivallng() {
		return arrivallng;
	}

	public void setArrivallng(Double arrivallng) {
		this.arrivallng = arrivallng;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Long getVehiclelength() {
		return vehiclelength;
	}

	public void setVehiclelength(Long vehiclelength) {
		this.vehiclelength = vehiclelength;
	}

	public String getOcrno() {
		return ocrno;
	}

	public void setOcrno(String ocrno) {
		this.ocrno = ocrno;
	}

	public String getOcrtype() {
		return ocrtype;
	}

	public void setOcrtype(String ocrtype) {
		this.ocrtype = ocrtype;
	}

	public Long getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(Long arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getArrivaltimeFormat() {
		return arrivaltimeFormat;
	}

	public void setArrivaltimeFormat(String arrivaltimeFormat) {
		this.arrivaltimeFormat = arrivaltimeFormat;
	}

	public String getEmrgCurSttsCd() {
		return emrgCurSttsCd;
	}

	public void setEmrgCurSttsCd(String emrgCurSttsCd) {
		this.emrgCurSttsCd = emrgCurSttsCd;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getPredictedArrivalDate() {
		return predictedArrivalDate;
	}

	public void setPredictedArrivalDate(Timestamp predictedArrivalDate) {
		this.predictedArrivalDate = predictedArrivalDate;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getRouteGeojson() {
		return routeGeojson;
	}

	public void setRouteGeojson(String routeGeojson) {
		this.routeGeojson = routeGeojson;
	}

	public String getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(String timeDifference) {
		this.timeDifference = timeDifference;
	}

	public Double getStartlat() {
		return startlat;
	}

	public void setStartlat(Double startlat) {
		this.startlat = startlat;
	}

	public Double getStartlng() {
		return startlng;
	}

	public void setStartlng(Double startlng) {
		this.startlng = startlng;
	}

	public String getFirename() {
		return firename;
	}

	public void setFirename(String firename) {
		this.firename = firename;
	}
}

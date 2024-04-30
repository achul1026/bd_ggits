package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsEmrgVhclPathLog {
	private Timestamp collectdt; // 수집일시
	private String serviceid; // 서비스 id
	private String evno; // 차량 번호
	private double currentlat; // 현재위치lat
	private double currentlng; // 현재위치lng
	private String servicename; // 서비스명
	private double arrivallat; // 도착위치lat
	private double arrivallng; // 도착위치lng
	private String route; // 경로
	private long vehiclelength; // 차량길이(군집차량 길이 포함)
	private String ocrno; // 재난번호
	private String ocrtype; // 재난종별명
	private long arrivaltime; // 예상도착시각(단위: 초)

	public Timestamp getCollectdt() {
		return collectdt;
	}

	public void setCollectdt(Timestamp collectdt) {
		this.collectdt = collectdt;
	}

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

	public double getCurrentlat() {
		return currentlat;
	}

	public void setCurrentlat(double currentlat) {
		this.currentlat = currentlat;
	}

	public double getCurrentlng() {
		return currentlng;
	}

	public void setCurrentlng(double currentlng) {
		this.currentlng = currentlng;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public double getArrivallat() {
		return arrivallat;
	}

	public void setArrivallat(double arrivallat) {
		this.arrivallat = arrivallat;
	}

	public double getArrivallng() {
		return arrivallng;
	}

	public void setArrivallng(double arrivallng) {
		this.arrivallng = arrivallng;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public long getVehiclelength() {
		return vehiclelength;
	}

	public void setVehiclelength(long vehiclelength) {
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

	public long getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(long arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

}

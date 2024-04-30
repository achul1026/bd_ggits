package com.neighbor21.ggits.openapi.request;

public class TrafficSpeedByTimezoneRequest {
	
	private String date;

	private String time;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "[date :" + date + ", time :" + time + "]";
	}
}

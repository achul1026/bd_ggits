package com.neighbor21.ggits.common.dto;

import java.util.List;

public class MapTotalServiceInfoDTO {
	private String today;
	private String renuwalTime;
	private int totalLoginCnt;
	private int totalAdminCnt;
	private String monthArr;
	private List<MapLoginStatisticsDTO> chartDataList;
	
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getRenuwalTime() {
		return renuwalTime;
	}
	public void setRenuwalTime(String renuwalTime) {
		this.renuwalTime = renuwalTime;
	}
	public int getTotalLoginCnt() {
		return totalLoginCnt;
	}
	public void setTotalLoginCnt(int totalLoginCnt) {
		this.totalLoginCnt = totalLoginCnt;
	}
	public int getTotalAdminCnt() {
		return totalAdminCnt;
	}
	public void setTotalAdminCnt(int totalAdminCnt) {
		this.totalAdminCnt = totalAdminCnt;
	}
	public String getMonthArr() {
		return monthArr;
	}
	public void setMonthArr(String monthArr) {
		this.monthArr = monthArr;
	}
	public List<MapLoginStatisticsDTO> getChartDataList() {
		return chartDataList;
	}
	public void setChartDataList(List<MapLoginStatisticsDTO> chartDataList) {
		this.chartDataList = chartDataList;
	}
}

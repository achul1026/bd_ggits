package com.neighbor21.ggits.common.dto;

import java.util.List;

public class MapMonitoringMenuDTO {
	
	String mngInstCd;	//관리 기관 코드
	String currentTime; //현재시간
	String beforeTime;  //이전시간
	int averageCnt = 0; // 평균 속도, 누적 교통량  
	int compareCnt = 0; // 전일 동시간 대비 데이터
	String graphTime;   // 그래프 시간 
	String graphCnt;	// 그래프 데이터
	List<TrafficInfo> trafficList; // 교통 상황 목록
	
	public static class TrafficInfo {
		
		String vdsSctnNm;		//구간 명칭
		int totalCnt = 0; // 테이블 총 개수
		
		public String getVdsSctnNm() {
			return vdsSctnNm;
		}
		public void setVdsSctnNm(String vdsSctnNm) {
			this.vdsSctnNm = vdsSctnNm;
		}
		public int getTotalCnt() {
			return totalCnt;
		}
		public void setTotalCnt(int totalCnt) {
			this.totalCnt = totalCnt;
		}
		
    }
	
	public String getMngInstCd() {
		return mngInstCd;
	} 
	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public String getBeforeTime() {
		return beforeTime;
	}
	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}
	public int getAverageCnt() {
		return averageCnt;
	}
	public void setAverageCnt(int averageCnt) {
		this.averageCnt = averageCnt;
	}
	public int getCompareCnt() {
		return compareCnt;
	}
	public void setCompareCnt(int compareCnt) {
		this.compareCnt = compareCnt;
	}
	public String getGraphTime() {
		return graphTime;
	}
	public void setGraphTime(String graphTime) {
		this.graphTime = graphTime;
	}
	public String getGraphCnt() {
		return graphCnt;
	}
	public void setGraphCnt(String graphCnt) {
		this.graphCnt = graphCnt;
	}
	public List<TrafficInfo> getTrafficList() {
		return trafficList;
	}
	public void setTrafficList(List<TrafficInfo> trafficList) {
		this.trafficList = trafficList;
	}

}

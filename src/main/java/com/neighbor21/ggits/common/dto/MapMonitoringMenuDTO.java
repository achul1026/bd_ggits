package com.neighbor21.ggits.common.dto;

import java.util.List;

public class MapMonitoringMenuDTO {

    String collectType; // 수집원
    String mngInstCd;    //관리 기관 코드
    String dongNm; // 동이름
    String currentTime; //현재시간
    String beforeTime;  //이전시간
    int averageCnt = 0; // 평균 속도, 누적 교통량
    int compareCnt = 0; // 전일 동시간 대비 데이터
    String graphTime;   // 그래프 시간
    String graphCnt;    // 그래프 데이터
    List<TrafficInfo> trafficList; // 교통 상황 목록

    //검색
    String schRoadName;    // 검색 도로명
    String schTime;    // 검색 시간

    //paging
    int totalCnt = 0;
    int page = 0;

    public static class TrafficInfo {

        private long rownum; // 번호
        String roadName;    //도로명
        String anlsDt;    //시간대
        int totalCnt = 0; // 테이블 총 개수

        public long getRownum() {
            return rownum;
        }

        public void setRownum(long rownum) {
            this.rownum = rownum;
        }

        public String getRoadName() {
            return roadName;
        }

        public void setRoadName(String roadName) {
            this.roadName = roadName;
        }

        public String getAnlsDt() {
            return anlsDt;
        }

        public void setAnlsDt(String anlsDt) {
            this.anlsDt = anlsDt;
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

    public String getSchRoadName() {
        return schRoadName;
    }

    public void setSchRoadName(String schRoadName) {
        this.schRoadName = schRoadName;
    }

    public String getSchTime() {
        return schTime;
    }

    public void setSchTime(String schTime) {
        this.schTime = schTime;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDongNm() {
        return dongNm;
    }

    public void setDongNm(String dongNm) {
        this.dongNm = dongNm;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }
}

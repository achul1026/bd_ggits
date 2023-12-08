package com.neighbor21.ggits.common.dto;

public class MapBigdataSearchDTO {

    String searchYear;
    String searchPeriod;
    String startDate;
    String endDate;
    String searchTime;
    String startTime;
    String endTime;
    String searchLocation;
    String searchResultType;
    String searchCrossroadsType;
    String searchCrossroadsLinkId;
    String searchCrossroadsNodeId;
    String searchRoadRank;
    String menuCode;

    int page = 1;            	//pageNum
    String routeNm;            	//노선 명
    String[] dayOfTheWeek;    	// 기간 검색용
    String startNodeNm;        	// 출발지
    String endNodeNm;        	// 도착지
    String peekAndOffPeek;    	// 출퇴근 시간 검색용
    String searchContent;		// text 검색용

    String accidentType;

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchPeriod() {
        return searchPeriod;
    }

    public void setSearchPeriod(String searchPeriod) {
        this.searchPeriod = searchPeriod;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
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

    public String getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(String searchLocation) {
        this.searchLocation = searchLocation;
    }

    public String getSearchResultType() {
        return searchResultType;
    }

    public void setSearchResultType(String searchResultType) {
        this.searchResultType = searchResultType;
    }

    public String getSearchCrossroadsType() {
        return searchCrossroadsType;
    }

    public void setSearchCrossroadsType(String searchCrossroadsType) {
        this.searchCrossroadsType = searchCrossroadsType;
    }

    public String getSearchCrossroadsNodeId() {
        return searchCrossroadsNodeId;
    }

    public void setSearchCrossroadsNodeId(String searchCrossroadsNodeId) {
        this.searchCrossroadsNodeId = searchCrossroadsNodeId;
    }

    public String getSearchCrossroadsLinkId() {
        return searchCrossroadsLinkId;
    }

    public void setSearchCrossroadsLinkId(String searchCrossroadsLinkId) {
        this.searchCrossroadsLinkId = searchCrossroadsLinkId;
    }

    public String getSearchRoadRank() {
        return searchRoadRank;
    }

    public void setSearchRoadRank(String searchRoadRank) {
        this.searchRoadRank = searchRoadRank;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String[] getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String[] dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getStartNodeNm() {
        return startNodeNm;
    }

    public void setStartNodeNm(String startNodeNm) {
        this.startNodeNm = startNodeNm;
    }

    public String getEndNodeNm() {
        return endNodeNm;
    }

    public void setEndNodeNm(String endNodeNm) {
        this.endNodeNm = endNodeNm;
    }

    public String getPeekAndOffPeek() {
        return peekAndOffPeek;
    }

    public void setPeekAndOffPeek(String peekAndOffPeek) {
        this.peekAndOffPeek = peekAndOffPeek;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
    
}

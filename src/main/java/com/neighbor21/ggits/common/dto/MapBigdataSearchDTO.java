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
    String searchLocationNm;
    String menuCode;
    String dataOption;	//쿼리 param 경용 추가
    
    Long limit;
    int page = 1;            	//pageNum
    String routeNm;            	//노선 명
    String[] dayOfTheWeek;    	// 기간 검색용
    String startStationId;		// 출발지 아이디
    String startStationNm;		// 출발지 명
    String endStationId;		// 도착지 아이디
    String endStationNm;		// 도착지 명
    String peekAndOffPeek;    	// 출퇴근 시간 검색용
    String searchContent;		// text 검색용
    String sigunCdId;			// 시군 코드 아이디
    String routeTp;				// 노선 유형
    
    
    String stStationId;			// 출발지 아이디
    String edStationId;			// 도착지 아이디
    
    String accidentType;
    String dangerType; // 도로안전 유형
    String routeId;
    String pageType;
    String[] timeArray = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23",};

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

	public String getStartStationId() {
		return startStationId;
	}

	public void setStartStationId(String startStationId) {
		this.startStationId = startStationId;
	}
	public String getEndStationId() {
		return endStationId;
	}

	public void setEndStationId(String endStationId) {
		this.endStationId = endStationId;
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

	public String getSigunCdId() {
		return sigunCdId;
	}

	public void setSigunCdId(String sigunCdId) {
		this.sigunCdId = sigunCdId;
	}

	public String getRouteTp() {
		return routeTp;
	}

	public void setRouteTp(String routeTp) {
		this.routeTp = routeTp;
	}

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String[] getTimeArray() {
        return timeArray;
    }

    public void setTimeArray(String[] timeArray) {
        this.timeArray = timeArray;
    }

	public String getStStationId() {
		return stStationId;
	}

	public void setStStationId(String stStationId) {
		this.stStationId = stStationId;
	}

	public String getEdStationId() {
		return edStationId;
	}

	public void setEdStationId(String edStationId) {
		this.edStationId = edStationId;
	}

	public String getStartStationNm() {
		return startStationNm;
	}

	public void setStartStationNm(String startStationNm) {
		this.startStationNm = startStationNm;
	}

	public String getEndStationNm() {
		return endStationNm;
	}

	public void setEndStationNm(String endStationNm) {
		this.endStationNm = endStationNm;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public String getDataOption() {
		return dataOption;
	}

	public void setDataOption(String dataOption) {
		this.dataOption = dataOption;
	}

	public String getSearchLocationNm() {
		return searchLocationNm;
	}

	public void setSearchLocationNm(String searchLocationNm) {
		this.searchLocationNm = searchLocationNm;
	}

    public String getDangerType() {
        return dangerType;
    }

    public void setDangerType(String dangerType) {
        this.dangerType = dangerType;
    }

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
    
}

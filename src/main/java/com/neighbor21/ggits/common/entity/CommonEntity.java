package com.neighbor21.ggits.common.entity;

import java.util.List;

//공통
public class CommonEntity {
	private String tabNum;
	private String searchType;
	private String searchContent;
	private String strDt;
	private String endDt;
	private String startTime;
	private String endTime;
	private int page = 1;
	private String useYn;
	private long rownum; // 번호
	private String dayOfTheWeekStr; // 요일 검색용 String
	private List<String> dayOfTheWeek; // 요일 검색용
	
	

	public CommonEntity(CommonEntity commonEntity) {
		super();
		this.tabNum = commonEntity.getTabNum();
		this.searchType = commonEntity.getSearchType();
		this.searchContent = commonEntity.getSearchContent();
		this.strDt = commonEntity.getStrDt();
		this.endDt = commonEntity.getEndDt();
		this.startTime = commonEntity.getStartTime();
		this.endTime = commonEntity.getEndTime();
		this.page = commonEntity.getPage();
		this.useYn = commonEntity.getUseYn();
		this.rownum = commonEntity.getRownum();
		this.dayOfTheWeekStr = commonEntity.getDayOfTheWeekStr();
		this.dayOfTheWeek = commonEntity.getDayOfTheWeek();
	}
	
	public CommonEntity() {
		super();
	}

	public List<String> getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(List<String> dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public long getRownum() {
		return rownum;
	}

	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

	public String getTabNum() {
		return tabNum;
	}

	public void setTabNum(String tabNum) {
		this.tabNum = tabNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String getStrDt() {
		return strDt;
	}

	public void setStrDt(String strDt) {
		this.strDt = strDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public String getDayOfTheWeekStr() {
		return dayOfTheWeekStr;
	}

	public void setDayOfTheWeekStr(String dayOfTheWeekStr) {
		this.dayOfTheWeekStr = dayOfTheWeekStr;
	}
	
	
}

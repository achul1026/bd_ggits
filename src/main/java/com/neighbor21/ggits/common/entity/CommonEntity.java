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
	private String sigunCdId;		// 시군 코드
	private String mngCdId;			// 관리 기관 코드
	private String seltRoadRank;	// 도로유형
	private String seltRoadDrct;	// 방향
	private String seltFicltInfo;	// 시설물
	private String selInciCate;		// 돌발유형
	private String routeTp;			// 버스 유형
	private String routeId;			// 버스ID
	private String selAcdntTp;		// 사고 유형
	private String chartStrDt;
	private String chartEndDt;
	private String strSearchYear;
	private String endSearchYear;
	private String strSearchMonth;
	private String endSearchMonth;
	private Long totalCntChk;
	
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
		this.sigunCdId = commonEntity.getSigunCdId();
		this.mngCdId = commonEntity.getMngCdId();
		this.seltRoadRank = commonEntity.getSeltRoadRank();
		this.seltRoadDrct = commonEntity.getSeltRoadDrct();
		this.seltFicltInfo = commonEntity.getSeltFicltInfo();
		this.routeTp = commonEntity.getRouteTp();
		this.routeId = commonEntity.getRouteId();
		this.selAcdntTp = commonEntity.getSelAcdntTp();
		this.chartStrDt = commonEntity.getChartStrDt();
		this.chartEndDt = commonEntity.getChartEndDt();
		this.strSearchYear = commonEntity.getStrSearchYear();
		this.endSearchYear = commonEntity.getEndSearchYear();
		this.strSearchMonth = commonEntity.getStrSearchMonth();
		this.endSearchMonth = commonEntity.getEndSearchMonth();
		this.selInciCate = commonEntity.getSelInciCate();
		this.totalCntChk = commonEntity.getTotalCntChk();
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

	public String getSigunCdId() {
		return sigunCdId;
	}

	public void setSigunCdId(String sigunCdId) {
		this.sigunCdId = sigunCdId;
	}
	public String getMngCdId() {
		return mngCdId;
	}

	public void setMngCdId(String mngCdId) {
		this.mngCdId = mngCdId;
	}

	public String getSeltRoadRank() {
		return seltRoadRank;
	}

	public void setSeltRoadRank(String seltRoadRank) {
		this.seltRoadRank = seltRoadRank;
	}

	public String getSeltRoadDrct() {
		return seltRoadDrct;
	}

	public void setSeltRoadDrct(String seltRoadDrct) {
		this.seltRoadDrct = seltRoadDrct;
	}

	public String getSeltFicltInfo() {
		return seltFicltInfo;
	}

	public void setSeltFicltInfo(String seltFicltInfo) {
		this.seltFicltInfo = seltFicltInfo;
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

	public String getSelAcdntTp() {
		return selAcdntTp;
	}

	public void setSelAcdntTp(String selAcdntTp) {
		this.selAcdntTp = selAcdntTp;
	}

	public String getChartStrDt() {
		return chartStrDt;
	}

	public void setChartStrDt(String chartStrDt) {
		this.chartStrDt = chartStrDt;
	}

	public String getChartEndDt() {
		return chartEndDt;
	}

	public void setChartEndDt(String chartEndDt) {
		this.chartEndDt = chartEndDt;
	}

	public String getStrSearchYear() {
		return strSearchYear;
	}

	public void setStrSearchYear(String strSearchYear) {
		this.strSearchYear = strSearchYear;
	}

	public String getEndSearchYear() {
		return endSearchYear;
	}

	public void setEndSearchYear(String endSearchYear) {
		this.endSearchYear = endSearchYear;
	}

	public String getStrSearchMonth() {
		return strSearchMonth;
	}

	public void setStrSearchMonth(String strSearchMonth) {
		this.strSearchMonth = strSearchMonth;
	}

	public String getEndSearchMonth() {
		return endSearchMonth;
	}

	public void setEndSearchMonth(String endSearchMonth) {
		this.endSearchMonth = endSearchMonth;
	}

	public String getSelInciCate() {
		return selInciCate;
	}

	public void setSelInciCate(String selInciCate) {
		this.selInciCate = selInciCate;
	}
	
	public Long getTotalCntChk() {
		return totalCntChk;
	}

	public void setTotalCntChk(Long totalCntChk) {
		this.totalCntChk = totalCntChk;
	}
}

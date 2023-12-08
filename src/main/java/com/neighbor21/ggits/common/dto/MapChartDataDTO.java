package com.neighbor21.ggits.common.dto;

import java.util.List;
import java.util.Map;

/**
 * 모니터링 대시보드 -> 차트 정보 DTO
 * Date : 2023-10-23
 * @author KY.LEE
 *
 */
public class MapChartDataDTO {
	String title;		//제목
	String subTitle;	//서브 제목
	String fnctType;	//함수 타입
	
	int totalCnt;		//전체 건수
	int compareCnt;		//전일 동시간 대비 퍼센트
	
	String compareStts;	//전일 대비 증가,감소 ex)CSC000 증가 CSC001감소
	//비교숫자 ex) 11건/31건
	int startCnt;
	int endCnt;
	
	String chartLabel; //ArrayToString
	String chartData; //ArrayToString
	
	String startDt;			//테이블형 데이터 시작 시간조건
	String endDt;			//테이블형 데이터 끝 시간조건
	
	List<Map<String,Object>> tableData; //리스트 데이터 MAP
	String tableOption; //테이블 데이터 파라미터 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getFnctType() {
		return fnctType;
	}

	public void setFnctType(String fnctType) {
		this.fnctType = fnctType;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getCompareCnt() {
		return compareCnt;
	}

	public void setCompareCnt(int compareCnt) {
		this.compareCnt = compareCnt;
	}
	
	public String getCompareStts() {
		return compareStts;
	}

	public void setCompareStts(String compareStts) {
		this.compareStts = compareStts;
	}

	public int getStartCnt() {
		return startCnt;
	}

	public void setStartCnt(int startCnt) {
		this.startCnt = startCnt;
	}

	public int getEndCnt() {
		return endCnt;
	}

	public void setEndCnt(int endCnt) {
		this.endCnt = endCnt;
	}
	
	public String getChartLabel() {
		return chartLabel;
	}

	public void setChartLabel(String chartLabel) {
		this.chartLabel = chartLabel;
	}

	public String getChartData() {
		return chartData;
	}

	public void setChartData(String chartData) {
		this.chartData = chartData;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public List<Map<String, Object>> getTableData() {
		return tableData;
	}

	public void setTableData(List<Map<String, Object>> tableData) {
		this.tableData = tableData;
	}

	public String getTableOption() {
		return tableOption;
	}

	public void setTableOption(String tableOption) {
		this.tableOption = tableOption;
	}
	
}

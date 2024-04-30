package com.neighbor21.ggits.common.dto;

public class TrfvlmStatisticsGraphDTO{
	
	String crsrdDrctCd;				//교차로 번호
	String strsctDrctNm;			//가로구간명
	String allDataList;				//시간별 데이터 개수
    
	public String getCrsrdDrctCd() {
		return crsrdDrctCd;
	}
	public void setCrsrdDrctCd(String crsrdDrctCd) {
		this.crsrdDrctCd = crsrdDrctCd;
	}
	public String getAllDataList() {
		return allDataList;
	}
	public void setAllDataList(String allDataList) {
		this.allDataList = allDataList;
	}
	public String getStrsctDrctNm() {
		return strsctDrctNm;
	}
	public void setStrsctDrctNm(String strsctDrctNm) {
		this.strsctDrctNm = strsctDrctNm;
	}
    
}

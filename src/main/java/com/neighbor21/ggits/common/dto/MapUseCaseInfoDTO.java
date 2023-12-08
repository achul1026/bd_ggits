package com.neighbor21.ggits.common.dto;

public class MapUseCaseInfoDTO {
	private String menuNm;		//메뉴이름
	private String menuId;  	//메뉴ID
	private String chartData;	//차트 데이터
//	private int renuwalCnt;  //갱신횟수
//	private Date renuwalDt;		//최근갱신일시
//	private Long renuwalMn = 60L; //갱신주기(분)
	
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getChartData() {
		return chartData;
	}
	public void setChartData(String chartData) {
		this.chartData = chartData;
	}
//	public int getRenuwalCnt() {
//		return renuwalCnt;
//	}
//	public void setRenuwalCnt(int renuwalCnt) {
//		this.renuwalCnt = renuwalCnt;
//	}
//	public Date getRenuwalDt() {
//		return renuwalDt;
//	}
//	public void setRenuwalDt(Date renuwalDt) {
//		this.renuwalDt = renuwalDt;
//	}
//	public Long getRenuwalMn() {
//		return renuwalMn;
//	}
//	public void setRenuwalMn(Long renuwalMn) {
//		this.renuwalMn = renuwalMn;
//	}
}

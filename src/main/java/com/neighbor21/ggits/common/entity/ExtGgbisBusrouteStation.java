package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 버스노선과 정류소구성(빅데이터 스미카)
public class ExtGgbisBusrouteStation {
	private String    routeId;        		// 노선 ID
    private String    stationId;        	// 정류소 ID
    private long    staOrder;        		// 정류소 순서
    private String    useYn;        		// 사용여부
    private long    nearStaCnt;        		// 인접정류소수
    private String    mainStaYn;        	// 주요정류소구분
    private long    coordX;        			// X좌표
    private long    coordY;        			// Y좌표
    private long    busDirection;        	// 버스방향
    private long    busDistance;        	// 버스거리
    private long    gisLength;        		// 정류소간거리
    private long    sumLength;        		// 누적거리
    private long    linkOrder;        		// 도로순서
    private String    gowayTp;        		// 진행방향구분
    private long    serviceCnt;        		// 제공정류소수
    private String    directionDesc;     	// 방향설명   
    private String    stationNm;        	// 정류소명칭
    private String    routeNm;        		// 노선명칭
    private String    stLinkId;        		// 표준링크 ID(검토)
    private String    linkId;        		// 도로 ID(검토)
    private String    drvendYn;        		// 운행유무
    private Timestamp    drvendTime;       	// 운행시간 
    private String    drvendVehId;       	// 운행차량 ID 
    private String    drvendPlateNo;        // 운행차량 번호
    private String    drvendCompanyNm;      // 운행차량 운수사 이름  
    private String    delayYn;				// 지연 유무
    
    private String startStationId;			// 출발 정류장 명	
    private String endStationId;			// 도착 정류장 명
    
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public long getStaOrder() {
		return staOrder;
	}
	public void setStaOrder(long staOrder) {
		this.staOrder = staOrder;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public long getNearStaCnt() {
		return nearStaCnt;
	}
	public void setNearStaCnt(long nearStaCnt) {
		this.nearStaCnt = nearStaCnt;
	}
	public String getMainStaYn() {
		return mainStaYn;
	}
	public void setMainStaYn(String mainStaYn) {
		this.mainStaYn = mainStaYn;
	}
	public long getCoordX() {
		return coordX;
	}
	public void setCoordX(long coordX) {
		this.coordX = coordX;
	}
	public long getCoordY() {
		return coordY;
	}
	public void setCoordY(long coordY) {
		this.coordY = coordY;
	}
	public long getBusDirection() {
		return busDirection;
	}
	public void setBusDirection(long busDirection) {
		this.busDirection = busDirection;
	}
	public long getBusDistance() {
		return busDistance;
	}
	public void setBusDistance(long busDistance) {
		this.busDistance = busDistance;
	}
	public long getGisLength() {
		return gisLength;
	}
	public void setGisLength(long gisLength) {
		this.gisLength = gisLength;
	}
	public long getSumLength() {
		return sumLength;
	}
	public void setSumLength(long sumLength) {
		this.sumLength = sumLength;
	}
	public long getLinkOrder() {
		return linkOrder;
	}
	public void setLinkOrder(long linkOrder) {
		this.linkOrder = linkOrder;
	}
	public String getGowayTp() {
		return gowayTp;
	}
	public void setGowayTp(String gowayTp) {
		this.gowayTp = gowayTp;
	}
	public long getServiceCnt() {
		return serviceCnt;
	}
	public void setServiceCnt(long serviceCnt) {
		this.serviceCnt = serviceCnt;
	}
	public String getDirectionDesc() {
		return directionDesc;
	}
	public void setDirectionDesc(String directionDesc) {
		this.directionDesc = directionDesc;
	}
	public String getStationNm() {
		return stationNm;
	}
	public void setStationNm(String stationNm) {
		this.stationNm = stationNm;
	}
	public String getRouteNm() {
		return routeNm;
	}
	public void setRouteNm(String routeNm) {
		this.routeNm = routeNm;
	}
	public String getStLinkId() {
		return stLinkId;
	}
	public void setStLinkId(String stLinkId) {
		this.stLinkId = stLinkId;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getDrvendYn() {
		return drvendYn;
	}
	public void setDrvendYn(String drvendYn) {
		this.drvendYn = drvendYn;
	}
	public Timestamp getDrvendTime() {
		return drvendTime;
	}
	public void setDrvendTime(Timestamp drvendTime) {
		this.drvendTime = drvendTime;
	}
	public String getDrvendVehId() {
		return drvendVehId;
	}
	public void setDrvendVehId(String drvendVehId) {
		this.drvendVehId = drvendVehId;
	}
	public String getDrvendPlateNo() {
		return drvendPlateNo;
	}
	public void setDrvendPlateNo(String drvendPlateNo) {
		this.drvendPlateNo = drvendPlateNo;
	}
	public String getDrvendCompanyNm() {
		return drvendCompanyNm;
	}
	public void setDrvendCompanyNm(String drvendCompanyNm) {
		this.drvendCompanyNm = drvendCompanyNm;
	}
	public String getDelayYn() {
		return delayYn;
	}
	public void setDelayYn(String delayYn) {
		this.delayYn = delayYn;
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
	
}

package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 버스 노선 운행정보 이력 집계
public class MrtBusRungLogAnal extends CommonEntity {
    private String clctYmd;        // 수집 일자
    private String busRouteId;    // 버스 노선 아이디
    private String coId;        // 회사 아이디
    private Long trsfrCnt;        // 환승 수
    private Long psgrCnt;        // 승객 수
    private String rideBstpId;    // 승차 버스정류장 아이디
    private String rideDt;        // 승차 일시
    private String lndiBstpId;    // 하차 버스정류장 아이디
    private String lndiDt;        // 하차 일시
    private Long busUserCnt;    // 버스 사용자 수
    private Long busRungDstne;    // 버스 운행 거리
    private String etlDt;        // etl 일시

    private String routeNm;        // 구간 명
    private String stStaNm;        // 출발지
    private String edStaNm;        // 도착지

    private String    routeId;
    private String    stationId;
    private Long    staOrder;
    private String    useYn;
    private Long    nearStaCnt;
    private String    mainStaYn;
    private Long    coordX;
    private Long    coordY;
    private Long    busDirection;
    private Long    busDistance;
    private Long    gisLength;
    private Long    sumLength;
    private Long    linkOrder;
    private String    gowayTp;
    private Long    serviceCnt;
    private String    directionDesc;
    private String    stationNm;
    private String    stLinkId;
    private String    linkId;
    private String    drvendYn;
    private Timestamp drvendTime;
    private String    drvendVehId;
    private String    drvendPlateNo;
    private String    drvendCompanyNm;
    private String    delayYn;

    private String geojson;
    private String stStationNm;
    private String edStationNm;
    private String stStationId;
    private String edStationId;
    private String roadName;
    private String routeTp;

    public String getClctYmd() {
        return clctYmd;
    }

    public void setClctYmd(String clctYmd) {
        this.clctYmd = clctYmd;
    }

    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public Long getTrsfrCnt() {
        return trsfrCnt;
    }

    public void setTrsfrCnt(Long trsfrCnt) {
        this.trsfrCnt = trsfrCnt;
    }

    public Long getPsgrCnt() {
        return psgrCnt;
    }

    public void setPsgrCnt(Long psgrCnt) {
        this.psgrCnt = psgrCnt;
    }

    public String getRideBstpId() {
        return rideBstpId;
    }

    public void setRideBstpId(String rideBstpId) {
        this.rideBstpId = rideBstpId;
    }

    public String getRideDt() {
        return rideDt;
    }

    public void setRideDt(String rideDt) {
        this.rideDt = rideDt;
    }

    public String getLndiBstpId() {
        return lndiBstpId;
    }

    public void setLndiBstpId(String lndiBstpId) {
        this.lndiBstpId = lndiBstpId;
    }

    public String getLndiDt() {
        return lndiDt;
    }

    public void setLndiDt(String lndiDt) {
        this.lndiDt = lndiDt;
    }

    public Long getBusUserCnt() {
        return busUserCnt;
    }

    public void setBusUserCnt(Long busUserCnt) {
        this.busUserCnt = busUserCnt;
    }

    public Long getBusRungDstne() {
        return busRungDstne;
    }

    public void setBusRungDstne(Long busRungDstne) {
        this.busRungDstne = busRungDstne;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String getStStaNm() {
        return stStaNm;
    }

    public void setStStaNm(String stStaNm) {
        this.stStaNm = stStaNm;
    }

    public String getEdStaNm() {
        return edStaNm;
    }

    public void setEdStaNm(String edStaNm) {
        this.edStaNm = edStaNm;
    }

    @Override
    public String getRouteId() {
        return routeId;
    }

    @Override
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Long getStaOrder() {
        return staOrder;
    }

    public void setStaOrder(Long staOrder) {
        this.staOrder = staOrder;
    }

    @Override
    public String getUseYn() {
        return useYn;
    }

    @Override
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Long getNearStaCnt() {
        return nearStaCnt;
    }

    public void setNearStaCnt(Long nearStaCnt) {
        this.nearStaCnt = nearStaCnt;
    }

    public String getMainStaYn() {
        return mainStaYn;
    }

    public void setMainStaYn(String mainStaYn) {
        this.mainStaYn = mainStaYn;
    }

    public Long getCoordX() {
        return coordX;
    }

    public void setCoordX(Long coordX) {
        this.coordX = coordX;
    }

    public Long getCoordY() {
        return coordY;
    }

    public void setCoordY(Long coordY) {
        this.coordY = coordY;
    }

    public Long getBusDirection() {
        return busDirection;
    }

    public void setBusDirection(Long busDirection) {
        this.busDirection = busDirection;
    }

    public Long getBusDistance() {
        return busDistance;
    }

    public void setBusDistance(Long busDistance) {
        this.busDistance = busDistance;
    }

    public Long getGisLength() {
        return gisLength;
    }

    public void setGisLength(Long gisLength) {
        this.gisLength = gisLength;
    }

    public Long getSumLength() {
        return sumLength;
    }

    public void setSumLength(Long sumLength) {
        this.sumLength = sumLength;
    }

    public Long getLinkOrder() {
        return linkOrder;
    }

    public void setLinkOrder(Long linkOrder) {
        this.linkOrder = linkOrder;
    }

    public String getGowayTp() {
        return gowayTp;
    }

    public void setGowayTp(String gowayTp) {
        this.gowayTp = gowayTp;
    }

    public Long getServiceCnt() {
        return serviceCnt;
    }

    public void setServiceCnt(Long serviceCnt) {
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

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getStStationNm() {
        return stStationNm;
    }

    public void setStStationNm(String stStationNm) {
        this.stStationNm = stStationNm;
    }

    public String getEdStationNm() {
        return edStationNm;
    }

    public void setEdStationNm(String edStationNm) {
        this.edStationNm = edStationNm;
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

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }
}

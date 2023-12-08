package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgbisBusrouteLink {
    private String routeId;
    private String linkId;
    private String fnodeId;
    private String tnodeId;
    private Double fnodeX;
    private Double fnodeY;
    private Double tnodeX;
    private Double tnodeY;
    private Double roadLength;
    private Double roadWidth;
    private Double routeLength;
    private Double totalLength;
    private Long linkOrder;
    private String stLinkId;
    private Long stLinkOrder;

    private String geojson;
    private String roadName;

    private String    routeIds;
    private String    routeNms;
    private String    routeTps;
    private String    routeLengths;
    private String    routeIntervals;
    private String    stStaNms;
    private String    edStaNms;
    private String    stStaIds;
    private String    edStaIds;
    private String    sidoCds;
    private String    manageTps;
    private String   permVols;
    private String  beginDates;
    private String    closeDates;
    private String    routeExs;
    private String    companyIds;
    private String    companyNms;
    private String    adminNms;
    private String    turnSeqs;
    private String    routeAllocs;
    private String    ebRouteIds;
    private String    routeAllocCompnayIds;
    private String    useYns;
    private String    remarks;
    private String    areaCds;
    private String    turnStaIds;
    private String    turnUseYns;
    private String    turninfoFlags;
    private String    lastvehFlags;
    private String    turnprocessFlags;
    private String    firstvehFlags;


    public Long getLinkOrder() {
        return linkOrder;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }


    public String getFnodeId() {
        return fnodeId;
    }

    public void setFnodeId(String fnodeId) {
        this.fnodeId = fnodeId;
    }


    public String getTnodeId() {
        return tnodeId;
    }

    public void setTnodeId(String tnodeId) {
        this.tnodeId = tnodeId;
    }

    public Double getFnodeX() {
        return fnodeX;
    }

    public void setFnodeX(Double fnodeX) {
        this.fnodeX = fnodeX;
    }

    public Double getFnodeY() {
        return fnodeY;
    }

    public void setFnodeY(Double fnodeY) {
        this.fnodeY = fnodeY;
    }

    public Double getTnodeX() {
        return tnodeX;
    }

    public void setTnodeX(Double tnodeX) {
        this.tnodeX = tnodeX;
    }

    public Double getTnodeY() {
        return tnodeY;
    }

    public void setTnodeY(Double tnodeY) {
        this.tnodeY = tnodeY;
    }

    public Double getRoadLength() {
        return roadLength;
    }

    public void setRoadLength(Double roadLength) {
        this.roadLength = roadLength;
    }

    public Double getRoadWidth() {
        return roadWidth;
    }

    public void setRoadWidth(Double roadWidth) {
        this.roadWidth = roadWidth;
    }

    public Double getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Double totalLength) {
        this.totalLength = totalLength;
    }


    public String getStLinkId() {
        return stLinkId;
    }

    public void setStLinkId(String stLinkId) {
        this.stLinkId = stLinkId;
    }


    public Long getStLinkOrder() {
        return stLinkOrder;
    }

    public void setStLinkOrder(Long stLinkOrder) {
        this.stLinkOrder = stLinkOrder;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(Double routeLength) {
        this.routeLength = routeLength;
    }

    public void setLinkOrder(Long linkOrder) {
        this.linkOrder = linkOrder;
    }

    public String getRouteIds() {
        return routeIds;
    }

    public void setRouteIds(String routeIds) {
        this.routeIds = routeIds;
    }

    public String getRouteNms() {
        return routeNms;
    }

    public void setRouteNms(String routeNms) {
        this.routeNms = routeNms;
    }

    public String getRouteTps() {
        return routeTps;
    }

    public void setRouteTps(String routeTps) {
        this.routeTps = routeTps;
    }

    public String getRouteLengths() {
        return routeLengths;
    }

    public void setRouteLengths(String routeLengths) {
        this.routeLengths = routeLengths;
    }

    public String getRouteIntervals() {
        return routeIntervals;
    }

    public void setRouteIntervals(String routeIntervals) {
        this.routeIntervals = routeIntervals;
    }

    public String getStStaNms() {
        return stStaNms;
    }

    public void setStStaNms(String stStaNms) {
        this.stStaNms = stStaNms;
    }

    public String getEdStaNms() {
        return edStaNms;
    }

    public void setEdStaNms(String edStaNms) {
        this.edStaNms = edStaNms;
    }

    public String getStStaIds() {
        return stStaIds;
    }

    public void setStStaIds(String stStaIds) {
        this.stStaIds = stStaIds;
    }

    public String getEdStaIds() {
        return edStaIds;
    }

    public void setEdStaIds(String edStaIds) {
        this.edStaIds = edStaIds;
    }

    public String getSidoCds() {
        return sidoCds;
    }

    public void setSidoCds(String sidoCds) {
        this.sidoCds = sidoCds;
    }

    public String getManageTps() {
        return manageTps;
    }

    public void setManageTps(String manageTps) {
        this.manageTps = manageTps;
    }

    public String getPermVols() {
        return permVols;
    }

    public void setPermVols(String permVols) {
        this.permVols = permVols;
    }

    public String getBeginDates() {
        return beginDates;
    }

    public void setBeginDates(String beginDates) {
        this.beginDates = beginDates;
    }

    public String getCloseDates() {
        return closeDates;
    }

    public void setCloseDates(String closeDates) {
        this.closeDates = closeDates;
    }

    public String getRouteExs() {
        return routeExs;
    }

    public void setRouteExs(String routeExs) {
        this.routeExs = routeExs;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getCompanyNms() {
        return companyNms;
    }

    public void setCompanyNms(String companyNms) {
        this.companyNms = companyNms;
    }

    public String getAdminNms() {
        return adminNms;
    }

    public void setAdminNms(String adminNms) {
        this.adminNms = adminNms;
    }

    public String getTurnSeqs() {
        return turnSeqs;
    }

    public void setTurnSeqs(String turnSeqs) {
        this.turnSeqs = turnSeqs;
    }

    public String getRouteAllocs() {
        return routeAllocs;
    }

    public void setRouteAllocs(String routeAllocs) {
        this.routeAllocs = routeAllocs;
    }

    public String getEbRouteIds() {
        return ebRouteIds;
    }

    public void setEbRouteIds(String ebRouteIds) {
        this.ebRouteIds = ebRouteIds;
    }

    public String getRouteAllocCompnayIds() {
        return routeAllocCompnayIds;
    }

    public void setRouteAllocCompnayIds(String routeAllocCompnayIds) {
        this.routeAllocCompnayIds = routeAllocCompnayIds;
    }

    public String getUseYns() {
        return useYns;
    }

    public void setUseYns(String useYns) {
        this.useYns = useYns;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAreaCds() {
        return areaCds;
    }

    public void setAreaCds(String areaCds) {
        this.areaCds = areaCds;
    }

    public String getTurnStaIds() {
        return turnStaIds;
    }

    public void setTurnStaIds(String turnStaIds) {
        this.turnStaIds = turnStaIds;
    }

    public String getTurnUseYns() {
        return turnUseYns;
    }

    public void setTurnUseYns(String turnUseYns) {
        this.turnUseYns = turnUseYns;
    }

    public String getTurninfoFlags() {
        return turninfoFlags;
    }

    public void setTurninfoFlags(String turninfoFlags) {
        this.turninfoFlags = turninfoFlags;
    }

    public String getLastvehFlags() {
        return lastvehFlags;
    }

    public void setLastvehFlags(String lastvehFlags) {
        this.lastvehFlags = lastvehFlags;
    }

    public String getTurnprocessFlags() {
        return turnprocessFlags;
    }

    public void setTurnprocessFlags(String turnprocessFlags) {
        this.turnprocessFlags = turnprocessFlags;
    }

    public String getFirstvehFlags() {
        return firstvehFlags;
    }

    public void setFirstvehFlags(String firstvehFlags) {
        this.firstvehFlags = firstvehFlags;
    }
}

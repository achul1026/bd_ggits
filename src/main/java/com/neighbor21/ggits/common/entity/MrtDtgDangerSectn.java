package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtDtgDangerSectn {
    private Timestamp anlsDt;        //분석 일시
    private String vhclNo;        //차량 번호
    private String linkId;        //링크 아이디
    private String busRouteNo;        //버스 노선 번호
    private String coId;        //회사 아이디
    private Long spdngRungCnt;        //과속 운행 수
    private Long sdacelRungCnt;        //급가속 운행 수
    private Long rpddclRungCnt;        //급감속 운행 수
    private Long sdstopRungCnt;        //급정지 운행 수
    private Long sdstrtRungCnt;        //급출발 운행 수
    private Long lngtrmaclRungCnt;        //장기과속 운행 수

    private Long riskJugCnt;              // 위험 판정 수
    private String stStaNm;                // 출발 정류장명
    private String edStaNm;                // 도착 정류장명
    private String routeInterval;        // 운행 간격
    private String routeLength;            // 운행 거리

    private String routeNm;                // 노선 명
    private String routeTp;				   // 노선 유형

    private String routeId;
    private String fnodeId;
    private String tnodeId;
    private Double fnodeX;
    private Double fnodeY;
    private Double tnodeX;
    private Double tnodeY;
    private Double roadLength;
    private Double roadWidth;
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

    private String    hh;
    private Long riskJugCnt00;
    private Long riskJugCnt01;
    private Long riskJugCnt02;
    private Long riskJugCnt03;
    private Long riskJugCnt04;
    private Long riskJugCnt05;
    private Long riskJugCnt06;
    private Long riskJugCnt07;
    private Long riskJugCnt08;
    private Long riskJugCnt09;
    private Long riskJugCnt10;
    private Long riskJugCnt11;
    private Long riskJugCnt12;
    private Long riskJugCnt13;
    private Long riskJugCnt14;
    private Long riskJugCnt15;
    private Long riskJugCnt16;
    private Long riskJugCnt17;
    private Long riskJugCnt18;
    private Long riskJugCnt19;
    private Long riskJugCnt20;
    private Long riskJugCnt21;
    private Long riskJugCnt22;
    private Long riskJugCnt23;

    public Timestamp getAnlsDt() {
        return anlsDt;
    }

    public void setAnlsDt(Timestamp anlsDt) {
        this.anlsDt = anlsDt;
    }

    public String getVhclNo() {
        return vhclNo;
    }

    public void setVhclNo(String vhclNo) {
        this.vhclNo = vhclNo;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getBusRouteNo() {
        return busRouteNo;
    }

    public void setBusRouteNo(String busRouteNo) {
        this.busRouteNo = busRouteNo;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public Long getSpdngRungCnt() {
        return spdngRungCnt;
    }

    public void setSpdngRungCnt(Long spdngRungCnt) {
        this.spdngRungCnt = spdngRungCnt;
    }

    public Long getSdacelRungCnt() {
        return sdacelRungCnt;
    }

    public void setSdacelRungCnt(Long sdacelRungCnt) {
        this.sdacelRungCnt = sdacelRungCnt;
    }

    public Long getRpddclRungCnt() {
        return rpddclRungCnt;
    }

    public void setRpddclRungCnt(Long rpddclRungCnt) {
        this.rpddclRungCnt = rpddclRungCnt;
    }

    public Long getSdstopRungCnt() {
        return sdstopRungCnt;
    }

    public void setSdstopRungCnt(Long sdstopRungCnt) {
        this.sdstopRungCnt = sdstopRungCnt;
    }

    public Long getSdstrtRungCnt() {
        return sdstrtRungCnt;
    }

    public void setSdstrtRungCnt(Long sdstrtRungCnt) {
        this.sdstrtRungCnt = sdstrtRungCnt;
    }

    public Long getLngtrmaclRungCnt() {
        return lngtrmaclRungCnt;
    }

    public void setLngtrmaclRungCnt(Long lngtrmaclRungCnt) {
        this.lngtrmaclRungCnt = lngtrmaclRungCnt;
    }

    public Long getRiskJugCnt() {
        return riskJugCnt;
    }

    public void setRiskJugCnt(Long riskJugCnt) {
        this.riskJugCnt = riskJugCnt;
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

    public String getRouteInterval() {
        return routeInterval;
    }

    public void setRouteInterval(String routeInterval) {
        this.routeInterval = routeInterval;
    }

    public String getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(String routeLength) {
        this.routeLength = routeLength;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
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

    public Long getLinkOrder() {
        return linkOrder;
    }

    public void setLinkOrder(Long linkOrder) {
        this.linkOrder = linkOrder;
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

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public Long getRiskJugCnt00() {
        return riskJugCnt00;
    }

    public void setRiskJugCnt00(Long riskJugCnt00) {
        this.riskJugCnt00 = riskJugCnt00;
    }

    public Long getRiskJugCnt01() {
        return riskJugCnt01;
    }

    public void setRiskJugCnt01(Long riskJugCnt01) {
        this.riskJugCnt01 = riskJugCnt01;
    }

    public Long getRiskJugCnt02() {
        return riskJugCnt02;
    }

    public void setRiskJugCnt02(Long riskJugCnt02) {
        this.riskJugCnt02 = riskJugCnt02;
    }

    public Long getRiskJugCnt03() {
        return riskJugCnt03;
    }

    public void setRiskJugCnt03(Long riskJugCnt03) {
        this.riskJugCnt03 = riskJugCnt03;
    }

    public Long getRiskJugCnt04() {
        return riskJugCnt04;
    }

    public void setRiskJugCnt04(Long riskJugCnt04) {
        this.riskJugCnt04 = riskJugCnt04;
    }

    public Long getRiskJugCnt05() {
        return riskJugCnt05;
    }

    public void setRiskJugCnt05(Long riskJugCnt05) {
        this.riskJugCnt05 = riskJugCnt05;
    }

    public Long getRiskJugCnt06() {
        return riskJugCnt06;
    }

    public void setRiskJugCnt06(Long riskJugCnt06) {
        this.riskJugCnt06 = riskJugCnt06;
    }

    public Long getRiskJugCnt07() {
        return riskJugCnt07;
    }

    public void setRiskJugCnt07(Long riskJugCnt07) {
        this.riskJugCnt07 = riskJugCnt07;
    }

    public Long getRiskJugCnt08() {
        return riskJugCnt08;
    }

    public void setRiskJugCnt08(Long riskJugCnt08) {
        this.riskJugCnt08 = riskJugCnt08;
    }

    public Long getRiskJugCnt09() {
        return riskJugCnt09;
    }

    public void setRiskJugCnt09(Long riskJugCnt09) {
        this.riskJugCnt09 = riskJugCnt09;
    }

    public Long getRiskJugCnt10() {
        return riskJugCnt10;
    }

    public void setRiskJugCnt10(Long riskJugCnt10) {
        this.riskJugCnt10 = riskJugCnt10;
    }

    public Long getRiskJugCnt11() {
        return riskJugCnt11;
    }

    public void setRiskJugCnt11(Long riskJugCnt11) {
        this.riskJugCnt11 = riskJugCnt11;
    }

    public Long getRiskJugCnt12() {
        return riskJugCnt12;
    }

    public void setRiskJugCnt12(Long riskJugCnt12) {
        this.riskJugCnt12 = riskJugCnt12;
    }

    public Long getRiskJugCnt13() {
        return riskJugCnt13;
    }

    public void setRiskJugCnt13(Long riskJugCnt13) {
        this.riskJugCnt13 = riskJugCnt13;
    }

    public Long getRiskJugCnt14() {
        return riskJugCnt14;
    }

    public void setRiskJugCnt14(Long riskJugCnt14) {
        this.riskJugCnt14 = riskJugCnt14;
    }

    public Long getRiskJugCnt15() {
        return riskJugCnt15;
    }

    public void setRiskJugCnt15(Long riskJugCnt15) {
        this.riskJugCnt15 = riskJugCnt15;
    }

    public Long getRiskJugCnt16() {
        return riskJugCnt16;
    }

    public void setRiskJugCnt16(Long riskJugCnt16) {
        this.riskJugCnt16 = riskJugCnt16;
    }

    public Long getRiskJugCnt17() {
        return riskJugCnt17;
    }

    public void setRiskJugCnt17(Long riskJugCnt17) {
        this.riskJugCnt17 = riskJugCnt17;
    }

    public Long getRiskJugCnt18() {
        return riskJugCnt18;
    }

    public void setRiskJugCnt18(Long riskJugCnt18) {
        this.riskJugCnt18 = riskJugCnt18;
    }

    public Long getRiskJugCnt19() {
        return riskJugCnt19;
    }

    public void setRiskJugCnt19(Long riskJugCnt19) {
        this.riskJugCnt19 = riskJugCnt19;
    }

    public Long getRiskJugCnt20() {
        return riskJugCnt20;
    }

    public void setRiskJugCnt20(Long riskJugCnt20) {
        this.riskJugCnt20 = riskJugCnt20;
    }

    public Long getRiskJugCnt21() {
        return riskJugCnt21;
    }

    public void setRiskJugCnt21(Long riskJugCnt21) {
        this.riskJugCnt21 = riskJugCnt21;
    }

    public Long getRiskJugCnt22() {
        return riskJugCnt22;
    }

    public void setRiskJugCnt22(Long riskJugCnt22) {
        this.riskJugCnt22 = riskJugCnt22;
    }

    public Long getRiskJugCnt23() {
        return riskJugCnt23;
    }

    public void setRiskJugCnt23(Long riskJugCnt23) {
        this.riskJugCnt23 = riskJugCnt23;
    }

	public String getRouteTp() {
		return routeTp;
	}

	public void setRouteTp(String routeTp) {
		this.routeTp = routeTp;
	}
    
}

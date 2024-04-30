package com.neighbor21.ggits.common.entity;

public class GgbisBusrouteInfounit {
    private String routeId;
    private String unitSectionId;
    private long unitOrder;
    private String sectionId;
    private String linkId;

    private String geojson;
    private String routeIds;
    private Long duplLinkCnt;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getUnitSectionId() {
        return unitSectionId;
    }

    public void setUnitSectionId(String unitSectionId) {
        this.unitSectionId = unitSectionId;
    }


    public long getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(long unitOrder) {
        this.unitOrder = unitOrder;
    }


    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }


    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getRouteIds() {
        return routeIds;
    }

    public void setRouteIds(String routeIds) {
        this.routeIds = routeIds;
    }

    public Long getDuplLinkCnt() {
        return duplLinkCnt;
    }

    public void setDuplLinkCnt(Long duplLinkCnt) {
        this.duplLinkCnt = duplLinkCnt;
    }
}

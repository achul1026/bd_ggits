package com.neighbor21.ggits.common.entity;

public class ScsEmrgVhclCurInfo extends ScsEmrgVhclPathInfo {
    private String serviceid;
    private String evno;
    private Double currentlat;
    private Double currentlng;
    private Double speed;

    private String routeGeojson;
   

    @Override
    public String getServiceid() {
        return serviceid;
    }

    @Override
    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    @Override
    public String getEvno() {
        return evno;
    }

    @Override
    public void setEvno(String evno) {
        this.evno = evno;
    }

    @Override
    public Double getCurrentlat() {
        return currentlat;
    }

    @Override
    public void setCurrentlat(Double currentlat) {
        this.currentlat = currentlat;
    }

    @Override
    public Double getCurrentlng() {
        return currentlng;
    }

    @Override
    public void setCurrentlng(Double currentlng) {
        this.currentlng = currentlng;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public String getRouteGeojson() {
        return routeGeojson;
    }

    @Override
    public void setRouteGeojson(String routeGeojson) {
        this.routeGeojson = routeGeojson;
    }

}

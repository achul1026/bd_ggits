package com.neighbor21.ggits.api.module.common;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GgbisBusRoute;
import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import com.neighbor21.ggits.common.mapper.GgbisBusRouteMapper;
import com.neighbor21.ggits.common.mapper.GgbisBusrouteLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 버스 노선정보 조회 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  CMBusComponent
 * @since : 2023-10-23
 */
@Component
public class CMBusComponent extends BaseMapDataComponent {

    @Autowired
    GgbisBusrouteLinkMapper ggbisBusrouteLinkMapper;

    @Autowired
    GgbisBusRouteMapper ggbisBusRouteMapper;

    /**
     * 노선별 버스 경로 조회(링크id)
     * @return
     */
    public List<GgbisBusrouteLink> getBusRouteLinkInfoByRouteId(String routeId){
        return ggbisBusrouteLinkMapper.findAllByRouteId(routeId);
    }

    public List<GgbisBusrouteLink> getBusRouteLinkInfoUpDownByRouteId(String routeId){
        return ggbisBusrouteLinkMapper.findAllUpDownByRouteId(routeId);
    }

    /**
     * 노선별 버스 경로 조회(링크id) - 정방향만
     * @return
     */
    public List<GgbisBusrouteLink> getBusRouteLinkInfoByRouteIdForward(String routeId){
        GgbisBusRoute busRoute = ggbisBusRouteMapper.findOneByRouteId(routeId);
        return ggbisBusrouteLinkMapper.findAllByRouteIdAndForward(routeId, busRoute.getTurnSeq());
    }

    /**
     * 노선별 버스 경로 조회(링크id) - 역방향만
     * @return
     */
    public List<GgbisBusrouteLink> getBusRouteLinkInfoByRouteIdReverse(String routeId){
        GgbisBusRoute busRoute = ggbisBusRouteMapper.findOneByRouteId(routeId);
        return ggbisBusrouteLinkMapper.findAllByRouteIdAndReverse(routeId, busRoute.getTurnSeq());
    }

    public List<GgbisBusrouteLink> getBusRouteLinkInfoByStationIdAndRouteId(String startStationId,String endStationId, String routeId){
        return ggbisBusrouteLinkMapper.findRouteLinkByStStationIdAndEdStationIdAndRouteId(startStationId, endStationId, routeId);
    }



    /**
     * 전체 버스 경로 조회(링크id)
     * @return
     */
    public List<GgbisBusrouteLink> getAllBusRouteLinkInfo(){
        return ggbisBusrouteLinkMapper.findAll();
    }
}

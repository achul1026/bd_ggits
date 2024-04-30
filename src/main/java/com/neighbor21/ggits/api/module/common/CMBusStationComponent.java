package com.neighbor21.ggits.api.module.common;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GgbisBusRoute;
import com.neighbor21.ggits.common.entity.GgbisBusStation;
import com.neighbor21.ggits.common.mapper.GgbisBusRouteMapper;
import com.neighbor21.ggits.common.mapper.GgbisBusStationMapper;
import com.neighbor21.ggits.common.util.BDStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 버스 정류장 조회 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  CMBusStationComponent
 * @since : 2023-09-07
 */
@Component
public class CMBusStationComponent extends BaseMapDataComponent {

    @Autowired
    GgbisBusStationMapper ggbisBusStationMapper;

    @Autowired
    GgbisBusRouteMapper ggbisBusRouteMapper;

    /**
     * 버스 정류장 정보 조회
     */
    public List<GgbisBusStation> getBusStationList(String routeId) {
        List<GgbisBusStation> list = new ArrayList<>();
        if(BDStringUtil.isNull(routeId)){
            list = ggbisBusStationMapper.findAll();
        }else{
            list = ggbisBusStationMapper.findAllByRouteId(routeId);
        }

        return list;
    }

    public List<GgbisBusStation> getBusStationListByRouteIds(String[] routeIds) {
        List<GgbisBusStation> list = ggbisBusStationMapper.findAllByRouteIds(routeIds);
        return list;
    }

    /**
     * 버스정류장 노선리스트 조회
     * @param stationId 정류장 id
     * @return
     */
    public List<GgbisBusRoute> getBusStationRouteList(String stationId) {
        return ggbisBusRouteMapper.findAllRouteInfoByStationId(stationId);
    }
}

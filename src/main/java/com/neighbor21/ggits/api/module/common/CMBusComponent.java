package com.neighbor21.ggits.api.module.common;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
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

    /**
     * 노선별 버스 경로 조회(링크id)
     * @return
     */
    public List<GgbisBusrouteLink> getBusRouteLinkInfoByRouteId(String routeId){
        return ggbisBusrouteLinkMapper.findAllByRouteId(routeId);
    }

    /**
     * 전체 버스 경로 조회(링크id)
     * @return
     */
    public List<GgbisBusrouteLink> getAllBusRouteLinkInfo(){
        return ggbisBusrouteLinkMapper.findAll();
    }
}

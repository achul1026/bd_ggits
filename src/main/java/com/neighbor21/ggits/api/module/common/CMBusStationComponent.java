package com.neighbor21.ggits.api.module.common;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GgbisBusStation;
import com.neighbor21.ggits.common.mapper.GgbisBusStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 버스 정류장 정보 조회
     */
    public List<GgbisBusStation> getBusStationList() {
        List<GgbisBusStation> list = ggbisBusStationMapper.findAll();
        return list;
    }

}

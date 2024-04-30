package com.neighbor21.ggits.api.module.bigdata;

import java.util.List;

import com.neighbor21.ggits.common.entity.MrtBusRouteSectnAnal;
import com.neighbor21.ggits.common.mapper.MrtBusRouteSectnAnalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.GgbisBusrouteInfounit;
import com.neighbor21.ggits.common.entity.MrtBusRouteDetAnal;
import com.neighbor21.ggits.common.mapper.GgbisBusrouteInfounitMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRouteDetAnalMapper;

/**
 * 대중교통 노선별 분석
 *
 * @author : Charles Kim
 * @fileName :  BDPublicTransferRouteAnalysisComponent
 * @since : 2023-09-07
 */
@Component
public class BDPublicTransferRouteAnalysisComponent extends BaseMapDataComponent {

    @Autowired
    MrtBusRouteDetAnalMapper mrtBusRouteDetAnalMapper;

    @Autowired
    GgbisBusrouteInfounitMapper ggbisBusrouteInfounitMapper;

    @Autowired
    MrtBusRouteSectnAnalMapper mrtBusRouteSectnAnalMapper;

    /**
     * 대중교통 노션별 분석 > 노선구간별 승하차/재차 승객수 조회
     * @return
     */
    public List<?> getRouteRideLndiPassengerInfo(){
        return null;
    }

    /**
     * 대중교통 노션별 분석 > 노선구간별 수용성 및 굴곡도 분석
     * @return
     */
    public MrtBusRouteDetAnal getRouteCurveInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtBusRouteDetAnalMapper.findOneByRouteId(mapBigdataSearchDTO);
    }

    /**
     * 대중교통 노션별 분석 > 노선구간별 중복구간 도출 및 적정성 분석(맵호출)
     * @return
     */
    public  List<MrtBusRouteSectnAnal> getDuplicateRouteGeometryInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtBusRouteSectnAnalMapper.findAllDuplicateSectionInfoGeometry(mapBigdataSearchDTO);

    }
}

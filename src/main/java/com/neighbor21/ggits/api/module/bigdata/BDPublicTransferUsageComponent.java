package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtBusArvlTimePrdctnRslt;
import com.neighbor21.ggits.common.entity.MrtBusRungLogAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnPasngAnal;
import com.neighbor21.ggits.common.mapper.MrtBusArvlTimePrdctnRsltMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRungLogAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnPasngAnalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 대중교통 이용현황 분석 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPublicTransferUsedComponent
 * @since : 2023-09-07
 */
@Component
public class BDPublicTransferUsageComponent extends BaseMapDataComponent {

    @Autowired
    MrtBusRungLogAnalMapper mrtBusRungLogAnalMapper;

    @Autowired
    MrtBusSttnPasngAnalMapper mrtBusSttnPasngAnalMapper;

    @Autowired
    MrtBusSttnAnalMapper mrtBusSttnAnalMapper;

    @Autowired
    MrtBusArvlTimePrdctnRsltMapper mrtBusArvlTimePrdctnRsltMapper;

    /**
     * 기종점 대중교통 이용량 데이터 조회
     */
    public List<MrtBusRungLogAnal> getPublicTransferStartEndUsage(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtBusRungLogAnalMapper.findAllByRouteId(mapBigdataSearchDTO);
    }

    /**
     * 권역별 대중교통 이용현황
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtBusSttnPasngAnal> getPublicTransferUsageBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtBusSttnPasngAnalMapper.findAllGroupBySGG(mapBigdataSearchDTO);
    }

    /**
     * 정류장별 버스 이용률
     * @param stationId
     * @param searchYear
     * @param searchPeriod
     * @param searchTime
     * @return
     */
    public List<MrtBusSttnAnal>getPublicTransferUsageByStation(String stationId, String searchYear, String searchPeriod, String searchTime){
        return mrtBusSttnAnalMapper.findAllByStationId(stationId, searchYear, searchPeriod, searchTime);
    }

    /**
     * 버스 도착정보 예측 조회
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtBusArvlTimePrdctnRslt> getPublicTransferBIT(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtBusArvlTimePrdctnRsltMapper.findAllByRouteIdAndStationId(mapBigdataSearchDTO);
    }
}

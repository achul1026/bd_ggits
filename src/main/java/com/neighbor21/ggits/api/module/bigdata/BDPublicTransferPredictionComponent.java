package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 대중교통 예측분석
 *
 * @author : Charles Kim
 * @fileName :  BDPublicTransferPredictionComponent
 * @since : 2023-09-07
 */
@Component
public class BDPublicTransferPredictionComponent extends BaseMapDataComponent {

    @Autowired
    MrtDynmcPopltnCell500RsltMapper mrtDynmcPopltnCell500RsltMapper;

    @Autowired
    MrtCndcyPathLinkInfoMapper mrtCndcyPathLinkInfoMapper;

    @Autowired
    MrtCndcyPathRouteBstpInfoMapper mrtCndcyPathRouteBstpInfoMapper;

    /**
     * 기간별 유동인구 예측 조회
     * @return
     */
    public List<MrtDynmcPopltnCell500Rslt> getPopulationInfoBySearchDto(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDynmcPopltnCell500Rslt> searchedList = new ArrayList<>();
        searchedList = mrtDynmcPopltnCell500RsltMapper.findAllBySearchDto(mapBigdataSearchDTO);
        if(searchedList.isEmpty()){
            searchedList = mrtDynmcPopltnCell500RsltMapper.findAllBySearchDtoMax(mapBigdataSearchDTO);
        }
        return searchedList;
    }

    /**
     * 기간별 유동인구 예측 조회(차트 플레이어용)
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtDynmcPopltnCell500Rslt> getPopulationInfoBySearchDtoForChart(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDynmcPopltnCell500Rslt> searchedList = new ArrayList<>();
        searchedList = mrtDynmcPopltnCell500RsltMapper.findAllBySearchDtoForChart(mapBigdataSearchDTO);
        if(searchedList.isEmpty()) {
            searchedList = mrtDynmcPopltnCell500RsltMapper.findAllBySearchDtoForChartMax(mapBigdataSearchDTO);
        }
        return searchedList;
    }

    /**
     * 최적화 후보경로의 링크정보 조회
     * @param candRouteId
     */
    public List<MrtCndcyPathLinkInfo> getPublicTransferCndcyPathLinkInfo(String btcId, String baseym, String candRouteId) {
        return mrtCndcyPathLinkInfoMapper.findAllByCandRouteId(btcId, baseym, candRouteId);
    }

    /**
     * 최적화 후보경로의 정류장 조회
     * @param candRouteId
     * @return
     */
    public List<MrtCndcyPathRouteBstpInfo> getPublicTransferCndcyStationInfo(String btcId, String baseym,String candRouteId) {
        return mrtCndcyPathRouteBstpInfoMapper.findAllByCandRouteId(btcId, baseym, candRouteId);
    }

}

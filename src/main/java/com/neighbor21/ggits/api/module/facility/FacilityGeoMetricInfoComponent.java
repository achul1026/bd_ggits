package com.neighbor21.ggits.api.module.facility;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  VDSComponent
 * @since : 2023-10-24
 */
@Component
public class FacilityGeoMetricInfoComponent extends BaseMapDataComponent {

    @Autowired
    AdsiMFaVdsMapper adsiMFaVdsMapper;

    @Autowired
    AdsiVdsColctInfoMapper adsiVdsColctInfoMapper;

    @Autowired
    AdsiVdsSctnStlinkMppgMapper adsiVdsSctnStlinkMppgMapper;

    @Autowired
    AdsiMFaDsrcMapper adsiMFaDsrcMapper;

    @Autowired
    AdsiDsrcColctInfoMapper adsiDsrcColctInfoMapper;
    
    @Autowired
    AdsiDsrcSctnStlinkMppgMapper adsiDsrcSctnStlinkMppgMapper;

    @Autowired
    AdsiSmcrsrdCrsrdInfoMapper adsiSmcrsrdCrsrdInfoMapper;

    @Autowired
    AdsiSmcrsrdCrsrdAcsRoadInfoMapper adsiSmcrsrdCrsrdAcsRoadInfoMapper;


    /**
     * VDS 정보 조회
     * @return
     */
    public List<AdsiMFaVds> getVDSList(){
        List<AdsiMFaVds> list = adsiMFaVdsMapper.findAllVdsInfoForMap();
        for(AdsiMFaVds item : list) {
            List<AdsiVdsColctInfo> collectInfo = adsiVdsColctInfoMapper.findRecentListByVdsID(item.getVdsId());
            item.setColctInfo(collectInfo);
        }
        return list;
    }

    /**
     * VDS 구간정보 조회
     * @return
     */
    public List<AdsiVdsSctnStlinkMppg> getVDSSectionList(){
        return null;
    }

    /**
     * DSRC 정보 조회
     * @return
     */
    public List<AdsiMFaDsrc> getDSRCList(){
        List<AdsiMFaDsrc> list = adsiMFaDsrcMapper.findAllDSRCInfoForMap();
        for(AdsiMFaDsrc item : list) {
            List<AdsiDsrcColctInfo> collectInfo = adsiDsrcColctInfoMapper.findRecentListByRseId(item.getRseId());
            item.setColctInfo(collectInfo);
        }
        return list;
    }

    /**
     * DSRC 구간정보 전체 조회
     * @return
     */
    public List<AdsiDsrcSctnStlinkMppg> getDSRCSectionList(){
        return adsiDsrcSctnStlinkMppgMapper.findAllDSRCSectionLinkInfo();
    }

    /**
     * 신호 정보 조회
     * @return
     */
    public List<AdsiMFaVds> getSignalList(){
        return null;
    }

    /**
     * 스마트교차로 정보 조회
     * @return
     */
    public List<AdsiSmcrsrdCrsrdInfo> getSmartCrossRoadList(){
        return adsiSmcrsrdCrsrdInfoMapper.findAllOneHourStats();
    }

    /**
     * 스마트교차로 인접 도로(링크) 정보 조회
     * @return
     */
    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> getSmartCrossRoadLinkList(){
        return adsiSmcrsrdCrsrdAcsRoadInfoMapper.findAllOneHourStats();
    }
}

package com.neighbor21.ggits.api.module.facility;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.component.StaticDataLoadComonent;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Autowired
    ScsTConIntlcMapper scsTConIntlcMapper;

    @Autowired
    ScsTConIntflowMapper scsTConIntflowMapper;

    @Autowired
    StaticDataLoadComonent staticDataLoadComonent;

    /**
     * VDS 정보 조회
     * @return
     */
    public List<AdsiMFaVds> getVDSList(){
        List<AdsiMFaVds> list = adsiMFaVdsMapper.findAllVdsInfoForMap();
        /*for(AdsiMFaVds item : list) {
            List<AdsiVdsColctInfo> collectInfo = adsiVdsColctInfoMapper.findRecentListByVdsID(item.getVdsId());
            item.setColctInfo(collectInfo);
        }*/
        return list;
    }

    public List<AdsiVdsColctInfo> getVDSCollectInfo(String vdsId, String mngInstCd){
        return adsiVdsColctInfoMapper.findRecentListByVdsID(vdsId, mngInstCd);
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
        /*for(AdsiMFaDsrc item : list) {
            List<AdsiDsrcColctInfo> collectInfo = adsiDsrcColctInfoMapper.findRecentListByRseId(item.getRseId());
            item.setColctInfo(collectInfo);
        }*/
        return list;
    }

    public List<AdsiDsrcColctInfo> getDSRCCollectInfo(String rseId){
        return  adsiDsrcColctInfoMapper.findRecentListByRseId(rseId);
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
    public List<ScsTConIntlc> getSignalList(){
        /*List<ScsTConIntlc> list = scsTConIntlcMapper.findAll();*/
        return scsTConIntlcMapper.findAll();
    }

    /**
     * 스마트교차로 정보 조회
     * @return
     */
    public List<AdsiSmcrsrdCrsrdInfo> getSmartCrossRoadList(String mngInstCd){
        return adsiSmcrsrdCrsrdInfoMapper.findAllOneHourStats(mngInstCd);
    }

    /**
     * 스마트교차로 인접 도로(링크) 정보 조회
     * @return
     */
    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> getSmartCrossRoadLinkList(){
        List<AdsiSmcrsrdCrsrdAcsRoadInfo> mergedList = new ArrayList<>();
        List<AdsiSmcrsrdCrsrdAcsRoadInfo> list = adsiSmcrsrdCrsrdAcsRoadInfoMapper.findAllOneHourStats();
        /*광명 정적 스마트교차로 표준링크정보 추가*/
//        mergedList.addAll(staticDataLoadComonent.getGmSmcrdAcsrdList());
        mergedList.addAll(list);
        return mergedList;
    }

    /**
     * 신호 이동류 연계 데이터 조회
     * @return
     */
    public List<ScsTConIntflow> getSignalInfo(Long intLcno) {
        List<ScsTConIntflow> list = scsTConIntflowMapper.findByIntLcno(intLcno);
        return list;
    }
}

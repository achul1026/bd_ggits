package com.neighbor21.ggits.api.module.bigdata;

import com.google.gson.Gson;
import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.component.StaticDataLoadComonent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadInfo;
import com.neighbor21.ggits.common.entity.CGmStdLink;
import com.neighbor21.ggits.common.entity.MrtSmcrsrdTrfvlmAnal;
import com.neighbor21.ggits.common.entity.MrtTrfAcdntDngrPrdctn;
import com.neighbor21.ggits.common.mapper.CGmStdLinkMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcrsrdTrfvlmAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfAcdntDngrPrdctnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 교통예측분석 컴포넌트
 * @author : Charles Kim
 * @fileName :  BDPredictionComponent
 * @since : 2023-10-10
 */
@Component
public class BDPredictionComponent extends BaseMapDataComponent {

    @Autowired
    MrtTrfAcdntDngrPrdctnMapper mrtTrfAcdntDngrPrdctnMapper;

    @Autowired
    MrtSmcrsrdTrfvlmAnalMapper mrtSmcrsrdTrfvlmAnalMapper;

    @Autowired
    CGmStdLinkMapper cGmStdLinkMapper;

    @Autowired
    StaticDataLoadComonent staticDataLoadComonent;

    /**
     * 스마트 교차로 예측 교통량
     * @return
     */
    public List<MrtSmcrsrdTrfvlmAnal> getCrossRoadTrafficPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findBySearchOption(mapBigdataSearchDTO);
    }

    /**
     * 스마트 교차로 예측 교통량(날짜별 그룹)
     * @return
     */
    public List<MrtSmcrsrdTrfvlmAnal> getCrossRoadTrafficPredictionByYmd(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findBySearchOptionGroupYmd(mapBigdataSearchDTO);
    }


    public List<MrtSmcrsrdTrfvlmAnal> getTrfvlmGroupTime(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findAllBySearchOptionGroupTime(mapBigdataSearchDTO);
    }

    public List<MrtSmcrsrdTrfvlmAnal> getTrfvlmTop10(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findAllBySearchOptionTop10(mapBigdataSearchDTO);
    }

    /**
     * 스마트교차로 인접도로 예측 교통량 정보
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtSmcrsrdTrfvlmAnal> getCrossRoadAngleInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        String searchLocation = mapBigdataSearchDTO.getSearchLocation();
        if(searchLocation != null && "41210".equals(searchLocation)) {
            // 광명 데이터 정적 데이터로 인해 분기처리
            mapBigdataSearchDTO.setSearchLocation(null);
        }
        List<MrtSmcrsrdTrfvlmAnal> mergeList = new ArrayList<>();
        List<MrtSmcrsrdTrfvlmAnal> dbList = mrtSmcrsrdTrfvlmAnalMapper.findAllRoadAndAngleInfoBySearchOption(mapBigdataSearchDTO);
        List<MrtSmcrsrdTrfvlmAnal> gmWithoutDbList = dbList.stream().filter(x -> !"42383800".equals(x.getMngInstCd())).collect(Collectors.toList());
        List<MrtSmcrsrdTrfvlmAnal> gwangmyung = dbList.stream().filter(x -> "42383800".equals(x.getMngInstCd()) && x.getSt() == null).collect(Collectors.toList());
        List<AdsiSmcrsrdCrsrdAcsRoadInfo> gmRoadList = staticDataLoadComonent.getGmSmcrdAcsrdList();
        gwangmyung.stream().forEach(new Consumer<MrtSmcrsrdTrfvlmAnal>() {
            @Override
            public void accept(MrtSmcrsrdTrfvlmAnal mrtSmcrsrdTrfvlmAnal) {
                Optional<AdsiSmcrsrdCrsrdAcsRoadInfo> oGmRoad = gmRoadList.stream().filter(x -> x.getAcsRoadId().equals(mrtSmcrsrdTrfvlmAnal.getAcsRoadId()) && x.getCrsrdId().equals(mrtSmcrsrdTrfvlmAnal.getCrsrdId())).findFirst();
                if(oGmRoad.isPresent()){
                    CGmStdLink stdLink = cGmStdLinkMapper.findOneByLinkIdForCrsrdPrediction(oGmRoad.get().getLinkId());
                    mrtSmcrsrdTrfvlmAnal.setAngle(stdLink.getAngle());
                    mrtSmcrsrdTrfvlmAnal.setSt(stdLink.getSt());
                    mrtSmcrsrdTrfvlmAnal.setEd(stdLink.getEd());
                }
            }
        });
        if(searchLocation != null && "41210".equals(searchLocation)) {
            // 광명 데이터 정적 데이터로 인해 분기처리
            mergeList.addAll(gwangmyung);
        }else{
            mergeList.addAll(gmWithoutDbList);
            mergeList.addAll(gwangmyung);
        }
        return mergeList;
    }

    /**
     * 스마트 교차로 예측 교통량(차트 플레이어용)
     * @return
     */
    public List<MrtSmcrsrdTrfvlmAnal> getCrossRoadTrafficPredictionForChart(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findBySearchOptionGroupYmdForChart(mapBigdataSearchDTO);
    }

    /**
     * 사고 예측구간 데이터 조회
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtTrfAcdntDngrPrdctn> getTrafficAccidentPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtTrfAcdntDngrPrdctnMapper.findAllBySearchOption(mapBigdataSearchDTO);
    }

    /**
     * 시군구별 사고 예측구간 데이터 조회
     * @param mapBigdataSearchDTO
     * @return
     */
    public List<MrtTrfAcdntDngrPrdctn> getTrafficAccidentPredictionGroupSgg(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtTrfAcdntDngrPrdctnMapper.findAllBySearchOptionGroupSGG(mapBigdataSearchDTO);
    }

}

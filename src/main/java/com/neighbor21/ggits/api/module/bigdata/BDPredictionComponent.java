package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcrsrdTrfvlmAnal;
import com.neighbor21.ggits.common.entity.MrtTrfAcdntDngrPrdctn;
import com.neighbor21.ggits.common.mapper.MrtSmcrsrdTrfvlmAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfAcdntDngrPrdctnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 스마트 교차로 예측 교통량
     * @return
     */
    public List<MrtSmcrsrdTrfvlmAnal> getCrossRoadTrafficPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtSmcrsrdTrfvlmAnalMapper.findBySearchOption(mapBigdataSearchDTO);
    }

    /**
     * 사고 예측구간 데이터 조회
     */
    public List<MrtTrfAcdntDngrPrdctn> getTrafficAccidentPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        return mrtTrfAcdntDngrPrdctnMapper.findAll();
    }

}

package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtDtgDangerSectn;
import com.neighbor21.ggits.common.entity.MrtTrfAcdntDngrPrdctn;
import com.neighbor21.ggits.common.mapper.MrtDtgDangerSectnMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfAcdntDngrPrdctnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 대중교통 위험운영 구간 분석 컴포넌트
 * @author : Charles Kim
 * @fileName :  BDPublicTransferDangerComponent
 * @since : 2023-10-10
 */
@Component
public class BDPublicTransferDangerComponent extends BaseMapDataComponent {

    @Autowired
    MrtDtgDangerSectnMapper mrtDtgDangerSectnMapper;


    /**
     * 버스안전 운행분석
     */
    public List<MrtDtgDangerSectn> getBusDtgDangerSectionInfo(MapBigdataSearchDTO  mapBigdataSearchDTO){
        return mrtDtgDangerSectnMapper.findAllBySearchOption(mapBigdataSearchDTO);
    }

}

package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcrsrdTrfvlmAnal;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface MrtSmcrsrdTrfvlmAnalMapper {

    /**
     * 교차로 교통량 예측
     * @param mapBigdataSearchDTO
     * @return
     */
    List<MrtSmcrsrdTrfvlmAnal> findBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);
}

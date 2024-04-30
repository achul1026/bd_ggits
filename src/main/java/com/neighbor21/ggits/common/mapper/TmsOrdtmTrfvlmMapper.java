package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.ExtTmsNlrdTimeOrdtmTrfvlm;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/*상시 교통량 매퍼*/
@Mapper
public interface TmsOrdtmTrfvlmMapper {
    List<ExtTmsNlrdTimeOrdtmTrfvlm> findAllNlrdTimeByYmd(MapBigdataSearchDTO mapBigdataSearchDTO);
}

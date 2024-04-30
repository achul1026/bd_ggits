package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtDsrcTrfvlmAnal;
import com.neighbor21.ggits.common.entity.MrtVdsTrfvlmAnal;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface MrtDsrcTrfvlmAnalMapper {

    List<MrtDsrcTrfvlmAnal> findAllGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<MrtDsrcTrfvlmAnal> findAllByTotalChart(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDsrcTrfvlmAnal> findAllByTotalChartGroupDay(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDsrcTrfvlmAnal> findAllBySGGChart(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDsrcTrfvlmAnal> findAllBySGGChartGroupDay(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDsrcTrfvlmAnal> findAllByTop10Chart(MapBigdataSearchDTO mapBigdataSearchDTO);
}

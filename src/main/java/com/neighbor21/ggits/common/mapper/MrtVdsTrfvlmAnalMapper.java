package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcTrfPat;
import com.neighbor21.ggits.common.entity.MrtVdsTrfvlmAnal;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MrtVdsTrfvlmAnalMapper {


    List<MrtVdsTrfvlmAnal> findAllGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);

    /*디테일정보용*/
    List<MrtVdsTrfvlmAnal> findAllByTotalChart(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtVdsTrfvlmAnal> findAllByTotalChartGroupDay(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtVdsTrfvlmAnal> findAllBySGGChart(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtVdsTrfvlmAnal> findAllBySGGChartGroupDay(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtVdsTrfvlmAnal> findAllByTop10Chart(MapBigdataSearchDTO mapBigdataSearchDTO);
}

package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.TaasDthTrfAcdntInfo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasDthTrfAcdntInfoMapper {

    public List<TaasDthTrfAcdntInfo> findAll();

    public List<TaasDthTrfAcdntInfo> findAllByAcdntGroupBySGG(MapBigdataSearchDTO mapBigdataSearchDTO);
}

package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.TaasDthTrfAcdntInfo;

@Mapper
public interface TaasDthTrfAcdntInfoMapper {

    public List<TaasDthTrfAcdntInfo> findAll();

    public List<TaasDthTrfAcdntInfo> findAllByAcdntGroupBySGG(MapBigdataSearchDTO mapBigdataSearchDTO);
}

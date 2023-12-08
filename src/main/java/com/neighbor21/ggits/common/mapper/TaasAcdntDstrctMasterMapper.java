package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.TaasAcdntDstrctMaster;
import com.neighbor21.ggits.common.entity.TaasBcyclAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasAcdntDstrctMasterMapper {

    public List<TaasAcdntDstrctMaster> findAll(MapBigdataSearchDTO mapBigdataSearchDTO);

}

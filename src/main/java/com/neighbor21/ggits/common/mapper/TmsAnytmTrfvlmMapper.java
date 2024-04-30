package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.ExtTmsHghwVhcclsAnytmTrfvlm;
import com.neighbor21.ggits.common.entity.ExtTmsNlrdVhcclsAnytmTrfvlm;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/*수시 교통량 매퍼*/
@Mapper
public interface TmsAnytmTrfvlmMapper {

    List<ExtTmsHghwVhcclsAnytmTrfvlm> findAllHghwTimeByYmd(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<ExtTmsNlrdVhcclsAnytmTrfvlm> findAllNlrdTimeByYmd(MapBigdataSearchDTO mapBigdataSearchDTO);
}

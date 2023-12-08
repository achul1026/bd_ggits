package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.AdsiDsrcSctnStlinkMppg;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface AdsiDsrcSctnStlinkMppgMapper {

    /**
     * DSRC 구간 링크 정보 조회
     * @return
     */
    public List<AdsiDsrcSctnStlinkMppg> findAllDSRCSectionLinkInfo();

}

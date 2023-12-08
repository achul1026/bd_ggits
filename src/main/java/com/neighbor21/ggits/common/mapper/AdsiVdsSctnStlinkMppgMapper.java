package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.AdsiVdsSctnStlinkMppg;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface AdsiVdsSctnStlinkMppgMapper {

    /**
     * VDS 구간 링크정보 조회
     * @param vdsSctnId
     * @return
     */
    public List<AdsiVdsSctnStlinkMppg> findAllByVdsSctnId(@Param("vdsSctnId") String vdsSctnId);

}

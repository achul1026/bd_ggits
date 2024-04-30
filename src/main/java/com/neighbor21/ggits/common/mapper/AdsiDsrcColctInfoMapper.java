package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.AdsiDsrcColctInfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
@CacheNamespace(flushInterval = 60000)
public interface AdsiDsrcColctInfoMapper {

    List<AdsiDsrcColctInfo> findRecentListByRseId(@Param("rseId") String rseId);
}

package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.MrtCndcyPathRouteBstpInfo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface MrtCndcyPathRouteBstpInfoMapper {

    /**
     * 후보경로로 최적화 정류장 조회
     * @param candRouteId
     * @return
     */
    List<MrtCndcyPathRouteBstpInfo> findAllByCandRouteId(@Param("btcId") String btcId, @Param("baseym") String baseym, @Param("candRouteId") String candRouteId);
}

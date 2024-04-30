package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.MrtCndcyPathLinkInfo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface MrtCndcyPathLinkInfoMapper {


    /**
     * 후보경로 조회
     * @param candRouteId
     * @return
     */
    List<MrtCndcyPathLinkInfo> findAllByCandRouteId(@Param("btcId") String btcId, @Param("baseym") String baseym, @Param("candRouteId") String candRouteId);


}

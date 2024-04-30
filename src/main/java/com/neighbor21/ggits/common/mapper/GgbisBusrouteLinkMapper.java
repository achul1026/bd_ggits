package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface GgbisBusrouteLinkMapper {

    List<GgbisBusrouteLink> findAllByRouteId(@Param("routeId") String routeId);
    List<GgbisBusrouteLink> findAllUpDownByRouteId(@Param("routeId") String routeId);

    List<GgbisBusrouteLink> findAll();

    /**
     * 출발지 도착지 설정후에 대한 링크 병합 정보
     * @return
     */
    List<GgbisBusrouteLink> findRouteLinkByStStationIdAndEdStationIdAndRouteId(@Param("startStationId") String startStationId, @Param("endStationId") String endStationId, @Param("routeId") String routeId);

    List<GgbisBusrouteLink> findAllByRouteIdAndReverse(@Param("routeId")String routeId, @Param("turnSeq") Long turnSeq);

    List<GgbisBusrouteLink> findAllByRouteIdAndForward(@Param("routeId") String routeId, @Param("turnSeq") Long turnSeq);
}

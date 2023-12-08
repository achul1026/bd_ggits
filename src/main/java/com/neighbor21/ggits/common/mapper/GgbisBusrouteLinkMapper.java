package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface GgbisBusrouteLinkMapper {

    List<GgbisBusrouteLink> findAllByRouteId(@Param("routeId") String routeId);

    List<GgbisBusrouteLink> findAll();
}

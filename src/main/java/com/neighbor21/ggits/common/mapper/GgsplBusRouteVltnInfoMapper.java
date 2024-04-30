package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.GgsplBusPeriodicinfo;
import com.neighbor21.ggits.common.entity.GgsplBusRouteVltnInfo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface GgsplBusRouteVltnInfoMapper {

    int countAllBySearchOption(GgsplBusPeriodicinfo ggsplBusRouteVltnInfo);

    List<GgsplBusRouteVltnInfo> findAllBySearchOption(GgsplBusPeriodicinfo ggsplBusRouteVltnInfo);
}

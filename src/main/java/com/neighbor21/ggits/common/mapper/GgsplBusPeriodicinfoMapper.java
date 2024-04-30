package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.GgsplBusPeriodicinfo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface GgsplBusPeriodicinfoMapper {

    List<GgsplBusPeriodicinfo> findAllRealTimeBusMoveInfo(GgsplBusPeriodicinfo ggsplBusPeriodicinfo);

    int countAllRealTimeBusMoveInfo(GgsplBusPeriodicinfo ggsplBusPeriodicinfo);
}

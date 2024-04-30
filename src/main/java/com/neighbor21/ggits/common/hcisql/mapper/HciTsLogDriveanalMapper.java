package com.neighbor21.ggits.common.hcisql.mapper;


import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.GgbisVehicle;
import com.neighbor21.ggits.common.entity.TsLogDriveanal;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HciTsLogDriveanalMapper {

    List<TsLogDriveanal> findAllBySearchOptionForGIS(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<TsLogDriveanal> findAllBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<GgbisVehicle> findAllBusVehicleByPlateNo(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<Map<String,String>> findAllGgbisCompany();

}

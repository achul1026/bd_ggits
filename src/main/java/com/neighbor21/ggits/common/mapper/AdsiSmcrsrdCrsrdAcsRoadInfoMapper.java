package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadInfo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AdsiSmcrsrdCrsrdAcsRoadInfoMapper {

    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> findAllOneHourStats();

    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> findAllByCrsrdId(String crsrdId)throws SQLException;
}

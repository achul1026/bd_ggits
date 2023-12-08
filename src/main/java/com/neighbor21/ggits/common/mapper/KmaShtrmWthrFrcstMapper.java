package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.KmaShtrmWthrFrcst;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface KmaShtrmWthrFrcstMapper {

    public List<KmaShtrmWthrFrcst> findAllWeatherList();

    public List<KmaShtrmWthrFrcst> findAllWeatherListByFrcstDivCdAndPrdctnYmdAndprdctnTime(@Param("frcstDivCd") String frcstDivCd, @Param("prdctnYmd") String prdctnYmd, @Param("prdctnTime") String prdctnTime);

    public List<KmaShtrmWthrFrcst> findRecentAllWeatherList();
}

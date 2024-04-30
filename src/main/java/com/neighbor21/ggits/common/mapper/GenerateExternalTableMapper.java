package com.neighbor21.ggits.common.mapper;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface GenerateExternalTableMapper {

    public String generateExternalTable(@Param("tableNm") String tableNm);

}

package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;
@Mapper
public interface MOpOpenApiInfoMapper {
	
	public List<MOpOpenApiInfo> findAllMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo);
	
	public MOpOpenApiInfo findOneByApiId(String apiId);
}

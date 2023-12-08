package com.neighbor21.ggits.common.mapper;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaDbInfo;

@Mapper
public interface MetaDbInfoMapper {

	public MetaDbInfo findMetaDbInfoByrltinstId(String rltinstId);
}

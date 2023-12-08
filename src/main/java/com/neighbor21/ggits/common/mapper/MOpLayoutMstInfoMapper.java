package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;

@Mapper
public interface MOpLayoutMstInfoMapper {
	
	public List<MOpLayoutMstInfo> findAllByOprtrIdAndMenuIdList(MOpLayoutMstInfo mOpLayoutMstInfo);

	public MOpLayoutMstInfo findOneByLayoutId(String layoutId);
	
	public void updateMOpLayoutMstInfo(MOpLayoutMstInfo mOpLayoutMstInfo);
}

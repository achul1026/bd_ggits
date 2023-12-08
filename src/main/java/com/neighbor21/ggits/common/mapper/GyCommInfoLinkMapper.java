package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GyCommInfoLink;

@Mapper
public interface GyCommInfoLinkMapper {
	
	public List<GyCommInfoLink> findAllGyCommInfoLink();

	public List<GyCommInfoLink> findLinkListByLinkId(GyCommInfoLink gyCommInfoLink);

	public int countAll();
	
	public int countByLinkId(GyCommInfoLink gyCommInfoLink);
}

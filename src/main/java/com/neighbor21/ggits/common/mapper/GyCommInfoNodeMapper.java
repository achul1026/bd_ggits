package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GyCommInfoNode;

@Mapper
public interface GyCommInfoNodeMapper {
	
	public List<GyCommInfoNode> findAllNodeList();

	public List<GyCommInfoNode> findNodeListByNodeIdAndNodeName(GyCommInfoNode gyCommInfoNode);
	
	public int countByNodeNmAndNodeNo(GyCommInfoNode gyCommInfoNode);

	public int countAll();
	
}

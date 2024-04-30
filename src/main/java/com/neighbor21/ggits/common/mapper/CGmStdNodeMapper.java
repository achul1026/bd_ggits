package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CGmStdNode;

@Mapper
public interface CGmStdNodeMapper {
	
	public List<CGmStdNode> findAllNodeList();

	public List<CGmStdNode> findNodeListByNodeIdAndNodeName(CGmStdNode cGmStdNode);
	
	public int countByNodeNmAndNodeNo(CGmStdNode CGmStdNode);

	public int countAll();
}

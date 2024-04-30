package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CGmStdLink;
@Mapper
public interface CGmStdLinkMapper {
	
	public List<CGmStdLink> findAllGyCommInfoLink();

	public List<CGmStdLink> findLinkListByLinkId(CGmStdLink cGmStdLink);

	public int countAll();
	
	public int countByLinkId(CGmStdLink cGmStdLink);

	public CGmStdLink findOneByLinkIdForCrsrdPrediction(@Param("linkId") String linkId);

	public CGmStdLink findOneWithNodeByLinkInfo(@Param("linkId") String linkId);

	
}

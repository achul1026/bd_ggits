package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MGmStdLinkNodeMngInfo;

@Mapper
public interface MGmStdLinkNodeMngInfoMapper {

	public void saveMGmStdLinkNodeMngInfo(MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo);

	public int countByStdInfoNm(MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo);
	
	public List<MGmStdLinkNodeMngInfo> findByNodeLinkMngInfoByStdInfoNm(MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo);

	public MGmStdLinkNodeMngInfo findOneByStdInfoId(String stdInfoId);
	
	public void deleteByStdInfoId(String stdInfoId);
	
	public void updateMGmStdLinkNodeMngInfo(MGmStdLinkNodeMngInfo mGmStdLinkNodeMngInfo);
}

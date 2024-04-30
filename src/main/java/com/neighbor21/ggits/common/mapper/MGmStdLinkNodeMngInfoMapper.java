package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

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
	
	/**
	  * @Method Name : findAllDataYears
	  * @작성일 : 2023. 11. 6.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 노드/링크 자료실 연도별 목록 조회
	  * @return
	  */
	public List<Map<String,Object>> findAllDataYears();
}

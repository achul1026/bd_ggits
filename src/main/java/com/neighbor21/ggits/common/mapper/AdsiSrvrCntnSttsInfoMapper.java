package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiSrvrCntnSttsInfo;

@Mapper
public interface AdsiSrvrCntnSttsInfoMapper {
	
	
	/**
	  * @Method Name : findAllServerInfoList
	  * @작성일 : 2023. 12. 5.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계시설물 서버 조회
	  * @param adsiSrvrCntnSttsInfo
	  * @return
	  */
	List<AdsiSrvrCntnSttsInfo> findAllServerInfoList(AdsiSrvrCntnSttsInfo adsiSrvrCntnSttsInfo);
}

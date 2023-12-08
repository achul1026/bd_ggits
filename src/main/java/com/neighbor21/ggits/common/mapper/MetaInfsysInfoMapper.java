package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaInfsysInfo;

@Mapper
public interface MetaInfsysInfoMapper {
	
	/**
	 * @Method Name : findAllMetaInfsysInfo
	 * @작성일 : 2023. 9. 8.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 유관기관 조회
	 */	
	public List<MetaInfsysInfo> findAllMetaInfsysInfo(MetaInfsysInfo metaInfsysInfo);
}

package com.neighbor21.ggits.common.hivesql.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaTabInfo;

@Mapper
public interface TemporaryMetaDataMapper {

	/**
	 * @Method Name : findAllCsvInfo
	 * @작성일 : 2023. 01. 03.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 엔답 메타데이터 정보 조회
	 */
	public List<Map<String,String>> findAllCsvInfo(MetaTabInfo metaTabInfo);
}

package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtAccntLog;

@Mapper
public interface MrtAccntLogMapper {

	/**
     * @Method Name : countAcdntGenLogInfo
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 개수 조회
     * @return
     */
	int countAcdntGenLogInfo(CommonEntity commonEntity);

	/**
     * @Method Name : findAllAcdntGenLogInfo
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 리스트 조회
     * @return
     */
	List<MrtAccntLog> findAllAcdntGenLogInfo(CommonEntity commonEntity);

}

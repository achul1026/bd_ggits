package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusRoutePasngAnal;

@Mapper
public interface MrtBusRoutePasngAnalMapper {

	/**
	 * @Method Name : countBusRoutePasngList
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 노선구간별 승하차/재차 승객수 리스트 개수 조회
	 * @param commonEntity
	 */
	public int countBusRoutePasngList(CommonEntity commonEntity);

	/**
	 * @Method Name : findAllBusRoutePasngMapper
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 노선구간별 승하차/재차 승객수 리스트 조회
	 * @param commonEntity
	 */
	public List<MrtBusRoutePasngAnal> findAllBusRoutePasngMapper(CommonEntity commonEntity);
}

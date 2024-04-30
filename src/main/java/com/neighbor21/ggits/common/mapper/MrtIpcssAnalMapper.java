package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtIpcssAnal;

@Mapper
public interface MrtIpcssAnalMapper {
	/**
     * @Method Name : findAllIpcssAnalList
     * @작성일 : 2023. 11. 08.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 교통영향평가 데이터 조회
     * @param commonEntity
     * @return
     */
	List<MrtIpcssAnal> findAllIpcssAnalList(CommonEntity commonEntity);
	
	/**
     * @Method Name : findOneIpcssAnalInfo
     * @작성일 : 2023. 11. 08.
     * @작성자 : KC.KIM
     * @Method 설명 : 
     * @param 통계 분석 > 데이터 활용 통계 > 교통영향평가 데이터 상세 조회
     * @return
     */
	List<Map<String, Object>> findOneIpcssAnalInfo(MrtIpcssAnal ipcssInfo);

	/**
     * @Method Name : countIpcssAnal
     * @작성일 : 2023. 12. 20.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 교통영향평가 데이터 개수 조회
     * @param commonEntity
     * @return
     */
	int countIpcssAnal(CommonEntity commonEntity);
}

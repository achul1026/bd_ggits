package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtRoadAccntAnal;

@Mapper
public interface MrtRoadAccntAnalMapper {
	/**
     * @Method Name : countAcdntCatgLogColt
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 유형별 이력 개수 조회
     * @return
     */
	int countAcdntCatgLogColt(CommonEntity commonEntity);
	
	/**
     * @Method Name : findAllAcdntCatgLogColt
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 유형별 이력 집계 리스트 조회
     * @return
     */
	List<MrtRoadAccntAnal> findAllAcdntCatgLogColt(CommonEntity commonEntity);

	/**
     * @Method Name : findAllRiskZnPrdtList
     * @작성일 : 2023. 10. 04.
     * @작성자 : KC.KIM
     * @Method 설명 : 위험예측구간 현황통계 리스트 조회
     * @return
     */
	List<MrtRoadAccntAnal> findAllRiskZnPrdtList(CommonEntity commonEntity);

}

package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.TaasAdsiAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaasAdsiAcdntDstrctMapper {

    public List<TaasAdsiAcdntDstrct> findAll();
    
    /**
     * @Method Name : findAllAcdntGenLogInfo
     * @작성일 : 2024. 04. 09.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 리스트 조회
     * @return
     */
	public List<TaasAdsiAcdntDstrct> findAllAcdntGenLogInfo(CommonEntity commonEntity);

	/**
     * @Method Name : countAcdntGenLog
     * @작성일 : 2024. 04. 09.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통사고 발생이력 리스트 개수 조회
     * @return
     */
	public int countAcdntGenLog(CommonEntity commonEntity);

	/**
     * @Method Name : findAllTrafficAcdntGenLogMap
     * @작성일 : 2024. 04. 09.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 도로 안전 > 교통사고 발생이력 통계
     * @return
     */
	public Map<String, Object> findAllAcdntGenLogMap(CommonEntity commonEntity);
}

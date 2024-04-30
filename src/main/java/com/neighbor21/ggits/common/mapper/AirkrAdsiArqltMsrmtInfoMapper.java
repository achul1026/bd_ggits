package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AirkrAdsiArqltMsrmtInfo;
import com.neighbor21.ggits.common.entity.CommonEntity;

@Mapper
public interface AirkrAdsiArqltMsrmtInfoMapper {

    /**
     * @Method Name : countArqltMsrmtInfo
     * @작성일 : 2023. 11. 02.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 지역별 대기질 통계 리스트 개수 조회
     * @param : commonEntity
     * @return
     */
	int countArqltMsrmtInfo(CommonEntity commonEntity);

    /**
     * @Method Name : findAllArqltMsrmtInfoList
     * @작성일 : 2023. 11. 02..
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 지역별 대기질 통계 리스트 개수 조회
     * @param : commonEntity
     * @return
     */
	List<AirkrAdsiArqltMsrmtInfo> findAllArqltMsrmtInfoList(CommonEntity commonEntity);

}

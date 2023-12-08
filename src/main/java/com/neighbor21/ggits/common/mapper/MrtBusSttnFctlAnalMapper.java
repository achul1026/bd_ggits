package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusSttnFctlAnal;

@Mapper
public interface MrtBusSttnFctlAnalMapper {

    /**
     * @Method Name : countBusSttnFcltList
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 시설물 및 기타 정보 리스트 개수 조회
     * @param : commonEntity
     * @return
     */
	int countBusSttnFcltList(CommonEntity commonEntity);

    /**
     * @Method Name : findAllBusSttnFcltsList
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 시설물 및 기타 정보 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtBusSttnFctlAnal> findAllBusSttnFcltsList(CommonEntity commonEntity);

}

package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtTrfFcltsSttsAnls;

@Mapper
public interface MrtTrfFcltsSttsAnlsMapper {

    /**
     * @Method Name : countTrfFcltsSttsAnls
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 교통시설물 통계 > 교통시설물 장애 통계 개수 조회
     * @param : commonEntity
     * @return
     */
	int countTrfFcltsSttsAnls(CommonEntity commonEntity);

    /**
     * @Method Name : findAllTrfFcltsSttsAnls
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 교통시설물 통계 > 교통시설물 장애 통계 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtTrfFcltsSttsAnls> findAllTrfFcltsSttsAnls(CommonEntity commonEntity);

    /**
     * @Method Name : countTrfFcltsEqpLog
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 교통시설물 통계 > 교통시설물 장비 로그 상세 개수 조회
     * @param : commonEntity
     * @return
     */
	int countTrfFcltsEqpLog(CommonEntity commonEntity);

    /**
     * @Method Name : findAllTrfFcltsEqpLogList
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 교통시설물 통계 > 교통시설물 장비 로그 상세 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtTrfFcltsSttsAnls> findAllTrfFcltsEqpLogList(CommonEntity commonEntity);
	
}

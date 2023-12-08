package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.LTcOpenApiRqstLog;

@Mapper
public interface LTcOpenApiRqstLogMapper {
	/**
     * @Method Name : countdataUseStatsList
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : Open API 활용 이력 개수 조회
     * @return
     */
	int countDataUseStatsList(CommonEntity commonEntity);
	
	/**
     * @Method Name : findAllDataUseStatsList
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : Open API 활용 이력 리스트 조회
     * @return
     */
	List<LTcOpenApiRqstLog> findAllDataUseStatsList(CommonEntity commonEntity);
	
	/**
     * @Method Name : findAllLTcOpenApiRqstLogList
     * @작성일 : 2023. 10. 04.
     * @작성자 : KY.LEE
     * @Method 설명 : 수집이력 Open Api 사용 이력 목록 조회
     * @return
     */
	List<LTcOpenApiRqstLog> findAllLTcOpenApiRqstLogList(LTcOpenApiRqstLog lTcOpenApiRqstLog);
	
	/**
     * @Method Name : countAllBySearchOption
     * @작성일 : 2023. 10. 04.
     * @작성자 : KY.LEE
     * @Method 설명 : 수집이력 Open Api 사용 이력 목록 조회 카운트 쿼리
     * @return
     */
	int countAllBySearchOption(LTcOpenApiRqstLog lTcOpenApiRqstLog);
}

package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.OpenApiPvsnLog;
@Mapper
public interface OpenApiPvsnLogMapper {
	

	/**
	 * @Method Name : saveOpenApiPvsnLog
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 : OPEN API 저장
	 */	
	public void saveOpenApiPvsnLog(OpenApiPvsnLog openApiPvsnLog);
	
	
	/**
	 * @Method Name : saveOpenApiPvsnLog
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 : OPEN API 수정
	 */	
	public void updateOpenApiPvsnLog(OpenApiPvsnLog openApiPvsnLog);
	
	
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
	List<OpenApiPvsnLog> findAllDataUseStatsList(CommonEntity commonEntity);
	
	/**
  * @Method Name : findAllLTcOpenApiRqstLogList
  * @작성일 : 2023. 10. 04.
  * @작성자 : KY.LEE
  * @Method 설명 : 수집이력 Open Api 사용 이력 목록 조회
  * @return
  */
	List<OpenApiPvsnLog> findAllOpenApiPvsnLogList(OpenApiPvsnLog openApiPvsnLog);

	/**
	 * @Method Name : countAllBySearchOption
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 : OPEN API 목록 카운트
	 */	
	public int countAllOpenApiPvsnLogBySearchOption(OpenApiPvsnLog openApiPvsnLog);	
	
	/**
   * @Method Name : findOneByOpenApiLogId
   * @작성일 : 2023. 12. 18.
   * @작성자 : KY.LEE
   * @Method 설명 : 수집이력 Open Api 사용 이력 단건 조회
   * @return LTcOpenApiRqstLog
   */
	OpenApiPvsnLog findOneForOpenApiLogHistory(OpenApiPvsnLog openApiPvsnLog);
	
}	

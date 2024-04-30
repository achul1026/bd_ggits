package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusRungLogAnal;

@Mapper
public interface MrtBusRungLogAnalMapper {

	/**
     * @Method Name : countBusInfoLogList
     * @작성일 : 2023. 10. 19.
     * @작성자 : KC.KIM
     * @Method 설명 : 
     * @param commonEntity
     * @return
     */
	int countBusInfoLogList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllBusInfoLogList
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 정체 발생량 개수 조회
     * @return
     */
	List<MrtBusRungLogAnal> findAllBusInfoLogList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 기종점 대중교통 이용량 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();


	/**
	 * 맵용 링크정보 조회
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtBusRungLogAnal> findAllByRouteId(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 권역별,버스타입별 대중교통 이용현황 조회
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtBusRungLogAnal> findAllGroupBySGGAndRouteTp(MapBigdataSearchDTO mapBigdataSearchDTO);

	Integer countByRouteNmPaging(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<MrtBusRungLogAnal> findListByRouteNmPaging(MapBigdataSearchDTO mapBigdataSearchDTO);
}

package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcrsrdTrfvlmAnal;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MrtSmcrsrdTrfvlmAnalMapper {

    /**
     * 교차로 교통량 예측
     * @param mapBigdataSearchDTO
     * @return
     */
    List<MrtSmcrsrdTrfvlmAnal> findBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 교차로 인접도로 교통량 예측
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSmcrsrdTrfvlmAnal> findAllRoadAndAngleInfoBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<MrtSmcrsrdTrfvlmAnal> findAllBySearchOptionGroupTime(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<MrtSmcrsrdTrfvlmAnal> findAllBySearchOptionTop10(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 교차로 교통량 예측(날짜별 그룹)
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSmcrsrdTrfvlmAnal> findBySearchOptionGroupYmd(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 교차로 교통량 예측(시군구 플레이어용 )
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSmcrsrdTrfvlmAnal> findBySearchOptionGroupYmdForChart(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	Map<String, Object> findAllsmcrsrdTrfvlmRank(String searchTime);
	
	/**
	  * @Method Name : findAllDataYears
	  * @작성일 : 2023. 11. 8.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 데이터 존재하는 연도 조회
	  * @return
	  */
	List<Map<String,Object>> findAllDataYears();
}

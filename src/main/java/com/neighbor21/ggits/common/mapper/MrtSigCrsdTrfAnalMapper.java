package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSigCrsdTrfAnal;

@Mapper
public interface MrtSigCrsdTrfAnalMapper {


	/**
	  * @Method Name : findAllGroupByLinkId
	  * @작성일 : 2023. 10. 16.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 교통활동 효과분석 > 정체구간 개선효과 (리스트에서 선택)
	  * @param mapBigdataSearchDTO
	  * @return
	  */
	List<MrtSigCrsdTrfAnal> findAllGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 교통활동 효과분석 병합 데이터
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSigCrsdTrfAnal> findAllMergeDataGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);


	/**
	 * 모니터링 최근 도로 소통정보 조회
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSigCrsdTrfAnal> findAllRecent();
	
	
	/**
    * @Method Name : findAllDataYears
    * @작성일 : 2023. 10. 5.
    * @작성자 : NK.KIM
    * @Method 설명 : 데이터 연도별 목록 조회
    * @return
    */
	List<Map<String,Object>> findAllDataYears();

	/**
	 * @Method Name : findTop5DelayTrfInfo
	 * @작성일 : 2023. 10. 27.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 교차로 별 혼잡도 조회
	 * @return
	 */
	List<Map<String,Object>> findTop5DelayTrfInfo(MrtSigCrsdTrfAnal mrtSigCrsdTrfAnal);
}

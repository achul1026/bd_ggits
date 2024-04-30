package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtSmcSpotAbn;

@Mapper
public interface MrtSmcSpotAbnMapper {
	/**
     * @Method Name : countSmcSpotAbnList
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 정체 발생량 개수 조회
     * @return
     */
	int countSmcSpotAbnList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllSmcSpotAbnList
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 정체 발생량 리스트 조회
     * @return
     */
	List<MrtSmcSpotAbn> findAllSmcSpotAbnList(CommonEntity commonEntity);

	/**
     * @Method Name : findOneSpotAbnAvgSpd
     * @작성일 : 2023. 10. 05.
     * @작성자 : KC.KIM
     * @Method 설명 : 정체 발생량 전체 평균 속도 통계
     * @return
     */
	Integer findOneSpotAbnAvgSpd(CommonEntity commonEntity);
	
	/**
	 * @Method Name : findTop5CrossRoadsInfo
	 * @작성일 : 2023. 10. 27.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 교차로 교통량
	 * @return
	 */
	List<Map<String,Object>> findTop5CrossRoadsInfo(MrtSmcSpotAbn mrtSmcSpotAbn);
	
	/**
	 * @Method Name : findTop5SumVhclTrfVlm
	 * @작성일 : 2023. 10. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 > 교통패턴분석 -> 더많은 데이터보기
	 * @return
	 */
	List<Map<String,Object>> findTop5SumVhclTrfVlm(MrtSmcSpotAbn mrtSmcSpotAbn);

	/**
	 * @Method Name : findTop5ByMrtSmcSpotAbnInfo
	 * @작성일 : 2023. 10. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 > 교통패턴분석 -> 더많은 데이터보기 -> 정체구간 순위 (시,군별)
	 * @return
	 */
	List<Map<String,Object>> findTop5ByMrtSmcSpotAbnInfo();

	/**
	 * @Method Name : findTop5ByMrtSmcSpotAbnInfo
	 * @작성일 : 2023. 10. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 > 교통패턴분석 -> 더많은 데이터보기 -> 교통량 순위 (도로별)
	 * @return
	 */
	List<Map<String,Object>> findTop5SumVhclTrfVlmLink();

	/**
	 * @Method Name : findTop5ByMrtSmcSpotAbnInfo
	 * @작성일 : 2023. 10. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 > 교통패턴분석 -> 더많은 데이터보기 -> 교통량 순위 (교차로별)
	 * @return
	 */
	List<Map<String,Object>> findTop5SumVhclTrfVlmCross();
	
	/**
	 * @Method Name : findTop5ByMrtSmcSpotAbnInfo
	 * @작성일 : 2023. 10. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 > 교통패턴분석 -> 더많은 데이터보기 -> 교통량 순위 (교차로별)
	 * @return
	 */
	List<Map<String,Object>> findTop5SumVhclTrfVlmTown();
	
	/**
	 * @Method Name : findAllSmcSpotAbnListForStats
	 * @작성일 : 2024. 04. 04.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(스마트 교차로) 리스트 조회
	 */	
	List<MrtSmcSpotAbn> findAllSmcSpotAbnListForStats(MrtSmcSpotAbn mrtSmcSpotAbn);

	/**
	 * @Method Name : countSmcSpotAbnForStats
	 * @작성일 : 2024. 04. 04.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(스마트 교차로) 리스트 개수 조회
	 */	
	int countSmcSpotAbnForStats(MrtSmcSpotAbn mrtSmcSpotAbn);
	
}

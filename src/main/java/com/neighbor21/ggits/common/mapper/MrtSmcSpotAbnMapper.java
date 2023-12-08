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
	int findOneSpotAbnAvgSpd(CommonEntity commonEntity);
	
	/**
	 * @Method Name : findOneSumVhclTrfvlmByAnlsDt
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 금일 전체 교통량 
	 * @return
	 */
	int findOneSumVhclTrfvlmByAnlsDt(MrtSmcSpotAbn mrtSmcSpotAbn);
	
	/**
	 * @Method Name : findOneVclSpeedAvgByAnlsDt
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 금일 평균 속력
	 * @return
	 */
	int findOneVclSpeedAvgByAnlsDt(MrtSmcSpotAbn mrtSmcSpotAbn);
	
	/**
	 * @Method Name : findTop5ByAnlsDtOrderByVhclTrfVlm
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 시간대 별 누적 교통량 순위 TOP5 보기 
	 * @return
	 */
	List<Map<String, Object>> findTop5ByAnlsDtOrderByVhclTrfVlm(MrtSmcSpotAbn mrtSmcSpotAbn);

	/**
	 * @Method Name : findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 시간대 별 평균 속도 순위 TOP5 보기 
	 * @return
	 */
	List<Map<String, Object>> findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed(MrtSmcSpotAbn mrtSmcSpotAbn);

	/**
	 * @Method Name : findTodaysStatistics
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 시간대 별 누적 교통량 순위 차트데이터
	 * @return
	 */
	List<Map<String,Object>> findVhclFrfvlTodaysStatistics(MrtSmcSpotAbn mrtSmcSpotAbn);

	/**
	 * @Method Name : findSpeedAvgTodaysStatistics
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 시간대 별 평균 속도 차트데이터
	 * @return
	 */
	List<Map<String,Object>> findSpeedAvgTodaysStatistics(MrtSmcSpotAbn mrtSmcSpotAbn);
	
	/**
	 * @Method Name : findTop5CrossRoadsInfo
	 * @작성일 : 2023. 10. 27.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 > 교차로 교통량
	 * @return
	 */
	List<Map<String,Object>> findTop5CrossRoadsInfo(MrtSmcSpotAbn mrtSmcSpotAbn);
	
}

package com.neighbor21.ggits.common.mapper;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GimsMngInciDetail;

@Mapper
public interface GimsMngInciDetailMapper {
	
	/**
	  * @Method Name : findAllWarningList
	  * @작성일 : 2023. 9. 19.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 돌발 이력 목록 조회
	  * @param paramMap
	  * @return
	  */
	public List<GimsMngInciDetail> findAllWarningList(Map<String,Object> paramMap);
	
	/**
	 * @Method Name : findAllDailyWarningList
	 * @작성일 : 2023. 9. 19.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 일일 돌발 이력 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<GimsMngInciDetail> findAllDailyWarningList(Map<String,Object> paramMap);

	public List<GimsMngInciDetail> findAllDailyWarningAlarmList();

	/**
	 * @Method Name : findTop3GimsMngInciDetailByBeginDateAndEndDate
	 * @작성일 : 2023. 10. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 일일 돌발 발생 목록 상위3개
	 * @param GimsMngInciDetail
	 * @return
	 */
	public List<Map<String,Object>> findTop3GimsMngInciDetailByBeginDateAndEndDate(GimsMngInciDetail gimsMngInciDetail);

	/**
     * @Method Name : countGimsMngInciDetailList
     * @작성일 : 2023. 9. 19.
     * @작성자 : KC.KIM
     * @Method 설명 : 돌발현황 통계 리스트 개수 조회
     * @param paramMap
     * @return
     */
	public int countGimsMngInciDetailList(Map<String, Object> paramMap);

	/**
     * @param paramMap 
	 * @Method Name : findOneGimsStatsRateInfo
     * @작성일 : 2023. 9. 19.
     * @작성자 : KC.KIM
     * @Method 설명 : 돌발상황 통계 > 돌발상황 비율
     * @param paramMap
     * @return
     */
	public Map<String, Object> findOneGimsStatsRateInfo(Map<String, Object> paramMap);

	/**
     * @param paramMap 
	 * @Method Name : findOneGimsStatsRateInfo
     * @작성일 : 2023. 9. 19.
     * @작성자 : KC.KIM
     * @Method 설명 : 돌발상황 통계 > 시간대별 돌발 상황 발생 건수
     * @param paramMap
     * @return
     */
	public Map<String, Object> findOneGimsStatsCntInfo(Map<String, Object> paramMap);
	
	/**
	 * @param paramMap 
	 * @Method Name : findOneGimsCountInfo
	 * @작성일 : 2023. 10. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 돌발상황 차트
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findOneGimsCountInfo(Map<String, Object> paramMap);

	public List<String> findOneInciCateList();

	/**
	 * 맵에 돌발상황 마커를 그리기 위한 쿼리
	 * @return
	 */
	public List<GimsMngInciDetail> findAllWarningListForMapMarker();
	
	/**
     * @param GimsMngInciDetail 
	 * @Method Name : countByBeginDateAndEndDate
     * @작성일 : 2023. 10. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 일일 돌발 상황 건수 조회
	 * @return int
	 */
	public int countByBeginDateAndEndDate(GimsMngInciDetail gimsMngInciDetail);
	
	/**
     * @param GimsMngInciDetail 
	 * @Method Name : countByBeginDateAndEndDateAndInUpdateCate
     * @작성일 : 2023. 10. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 일일 돌발 상황 건수 조회 조건(상태값 포함)
	 * @return int
	 */
	public int countByBeginDateAndEndDateAndInUpdateCate(GimsMngInciDetail gimsMngInciDetail);
	
	/**
     * @param GimsMngInciDetail 
	 * @Method Name : countByBeginDateAndEndDateAndNotInUpdateCate
     * @작성일 : 2023. 10. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 일일 돌발 상황 건수 조회 조건(상태값 미포함)
	 * @return int
	 */
	public int countByBeginDateAndEndDateAndNotInUpdateCate(GimsMngInciDetail gimsMngInciDetail);
}

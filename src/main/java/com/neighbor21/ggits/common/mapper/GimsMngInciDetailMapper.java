package com.neighbor21.ggits.common.mapper;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import org.postgresql.util.PSQLException;

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


	public List<GimsMngInciDetail> findAllDailyWarningAlarmList() throws PSQLException;

	/**
	 * @Method Name : findTop3GimsMngInciDetailByBeginDateAndEndDate
	 * @작성일 : 2023. 10. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 일일 돌발 발생 목록
	 * @param GimsMngInciDetail
	 * @return
	 */
	public List<Map<String,Object>> findWarningTabInfo(GimsMngInciDetail gimsMngInciDetail);

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
	
	/**
	 * @param paramMap 
	 * @Method Name : findByMonitoringChartData
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 돌발상황 차트
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findByMonitoringChartData(GimsMngInciDetail gimsMngInciDetail);

	public List<String> findOneInciCateList();

	/**
	 * 맵에 돌발상황 마커를 그리기 위한 쿼리
	 * @return
	 */
	public List<GimsMngInciDetail> findAllWarningListForMapMarker() throws PSQLException;

	public List<GimsMngInciDetail> findAllUTICWarningListForMapMarker() throws PSQLException;
	
	/**
	  * @Method Name : findAllWarningListForList
	  * @작성일 : 2023. 11. 28.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 돌발 현황 목록
	  * @return
	  */
	public List<GimsMngInciDetail> findAllWarningListForList();
	
	/**
     * @param countByMonitoringWaringInfo 
	 * @Method Name : countByMonitoringWaringInfo
     * @작성일 : 2023. 10. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 돌발 건수 조회 진행중/완료됨
	 * @return int
	 */
	public Map<String,Object> countByMonitoringWaringInfo(GimsMngInciDetail gimsMngInciDetail);

	
	/**
	  * @Method Name : findOneDataCntForMonitoring
	  * @작성일 : 2023. 11. 14.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 일일 긴급차량 데이터 개수 조회 (연계대상 수집 이력)
	  * @return
	  */
	public MapMonitoringLinkDataDTO findOneDataCntForMonitoring();

   /**
    * @Method Name : findWarningByMnginstcd
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 돌발 수집원별 수
    */
	List<GimsMngInciDetail> findWarningByMnginstcd();

	List<GimsMngInciDetail> findAllWarningInfoToday();
}

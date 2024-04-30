package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.LTcDataLog;

@Mapper
public interface LTcDataLogMapper {
	
	/**
	  * @Method Name : findAllLTcDataLogList
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 수집 이력 목록 조회
	  * @param lTcDataLog
	  * @return
	  */
	public List<LTcDataLog> findAllLTcDataLogList(LTcDataLog lTcDataLog);

	/**
	 * @Method Name : findTop5ByClctStartDt
	 * @작성일 : 2023. 10. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 데이터 수집 장애 알림
	 * @param lTcDataLog
	 * @return
	 */
	public List<Map<String,Object>> findTop5ByClctStartDtAndEtlClsfAndLinkedList(LTcDataLog lTcDataLog);

	/**
	 * 장애수집 알람용
	 * @param todayStDt
	 * @param todayEndDt
	 * @return
	 */
	public List<LTcDataLog> findAllLTcDataLogTodayListForAlarm(@Param("todayStDt") String todayStDt, @Param("todayEndDt") String todayEndDt);
	/**
	  * @Method Name : countAll
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 수집 이력 목록 개수 조회
	  * @param lTcDataLog
	  * @return
	  */
	public int countAll(LTcDataLog lTcDataLog);

	/**
     * @Method Name : countdataUseStatsList
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 수집/연계시스템 이력 개수 조회
     * @return
     */
	public int countDataUseStatsList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllDataUseStatsList
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 수집/연계시스템 이력 리스트 조회 
     * @return
     */
	public List<LTcDataLog> findAllDataUseStatsList(CommonEntity commonEntity);

	/**
     * @Method Name : findOneLtcDataLogFailInfo
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 수집/연계시스템 이력 실패 사유 조회
     * @return
     */
	public LTcDataLog findOneLtcDataLogFailInfo(LTcDataLog lTcDataLog);

	/**
     * @Method Name : countSmcrdDataLogList
     * @작성일 : 2023. 10. 16.
     * @작성자 : KC.KIM
     * @Method 설명 : 스마트교차로 데이터 이력 개수 조회
     * @return
     */
	public int countSmcrdDataLogList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllSmcrdDataLogList
     * @작성일 : 2023. 10. 16.
     * @작성자 : KC.KIM
     * @Method 설명 : 스마트교차로 데이터 이력 리스트 조회
     * @return
     */
	public List<LTcDataLog> findAllSmcrdDataLogList(CommonEntity commonEntity);
	
	/**
	  * @Method Name : findAllForMonitoringLinkData
	  * @작성일 : 2023. 11. 13.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 대상데이터 목록 조회
	  * @return
	  */
	public List<MapMonitoringLinkDataDTO> findAllForMonitoringLinkData();

	/**
	  * @Method Name : findAllErrorDataForMonitoringLinkData
	  * @작성일 : 2023. 11. 14.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 대상데이터 데러 목록 조회
	  * @return
	  */
	public List<MapMonitoringLinkDataDTO> findAllErrorDataForMonitoringLinkData();
}

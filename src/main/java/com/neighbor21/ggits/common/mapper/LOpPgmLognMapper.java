package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LOpPgmLogn;

@Mapper
public interface LOpPgmLognMapper {
	
    /**
     * @Method Name : findAllLOpList
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사용자 로그 리스트 조회
     * @param : lOpPgmLogn
     * @return
     */
	public List<LOpPgmLogn> findAllLOpList(LOpPgmLogn lOpPgmLogn);

    /**
     * @Method Name : countUserLogBySearchInfo
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사용자 로그 개수 조회
     * @param : lOpPgmLogn
     * @return
     */
	public int countUserLogBySearchInfo(LOpPgmLogn lOpPgmLogn);
	
	/**
     * @Method Name : saveLOpPgmLogn
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사용자 로그 저장
     * @param : lOpPgmLogn
     * @return
     */
	public void saveLOpPgmLogn(LOpPgmLogn lOpPgmLogn);
	  
	/**
     * @Method Name : countAll
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사용자 로그 전체 개수 조회
     * @param : 
     * @return
     */
	public int countAll();

    /**
     * @Method Name : findOneLOpPgmLognByLogId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사용자 로그 상세 조회
     * @param : logId
     * @return
     */
	public LOpPgmLogn findOneLOpPgmLognByLogId(String logId);
	
	/**
	  * @Method Name : deleteByOprtrId
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 삭제
	  * @param oprtrId
	  */
	public void deleteByOprtrId(long oprtrId);
	
	/**
	 * @Method Name : countByLoginCnt
	 * @작성일 : 2023. 10. 10.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 시군 이용 대상별 접속 현황 통계
	 * @param lOpPgmLogn
	 */
	public List<Map<String,Object>> countByLoginCnt(LOpPgmLogn lOpPgmLogn);
	
	/**
	 * @Method Name : countAllByLogType
	 * @작성일 : 2023. 10. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 전체 접속 현황
	 * @param lOpPgmLogn
	 */
	public int countAllByLogType(LOpPgmLogn lOpPgmLogn);
	
	/**
	 * @Method Name : countByLoginCntDays
	 * @작성일 : 2023. 10. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 전체 서비스 운영현황 차트
	 * @param lOpPgmLogn
	 */
	public List<Map<String,Object>> countByLoginCntDays(LOpPgmLogn lOpPgmLogn);
	
}

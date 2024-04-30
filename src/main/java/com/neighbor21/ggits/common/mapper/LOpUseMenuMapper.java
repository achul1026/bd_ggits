package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LOpUseMenu;

@Mapper
public interface LOpUseMenuMapper {
	
	/**
	  * @Method Name : deleteByOprtrId
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 삭제
	  * @param oprtrId
	  */
	public void deleteByOprtrId(long oprtrId);
	
	/**
	 * @Method Name : saveLOpUseMenu
	 * @작성일 : 2023. 10. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 로그 삽입
	 * @param LOpUseMenu
	 */
	public void saveLOpUseMenu(LOpUseMenu lOpUseMenu);
	
	/**
	 * @Method Name : countByMenuIdAndOccurDt
	 * @작성일 : 2023. 10. 05.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 시간으로 건수 조회
	 * @param LOpUseMenu
	 */
	public int countByMenuIdAndOccurDt(LOpUseMenu lOpUseMenu);
	
	/**
	 * @Method Name : findUseCaseChartInfo
	 * @작성일 : 2023. 10. 05.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 서비스 운영 현황 - > 빅데이터 소메뉴 -> 유스케이스 항목 접속 현황 차트 데이터 조회
	 * @param LOpUseMenu
	 */
	public List<Map<String,Object>> findUseCaseChartInfo(LOpUseMenu lOpUseMenu);

	/**
	 * @Method Name : findUseCaseChartInfo
	 * @작성일 : 2023. 10. 05.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 서비스 운영 현황 - > 빅데이터 소메뉴 -> 그룹 라벨 조회
	 * @param LOpUseMenu
	 */
	public List<Map<String,Object>> findUseCaseChartInfoLabelList(LOpUseMenu lOpUseMenu);
	
}	

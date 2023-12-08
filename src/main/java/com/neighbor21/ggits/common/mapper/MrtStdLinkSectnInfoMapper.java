package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtStdLinkSectnInfo;

@Mapper
public interface MrtStdLinkSectnInfoMapper {
    /**
     * @Method Name : countTrafficInfoLogList
     * @작성일 : 2023. 9. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 이력 집계 > 교통정보 이력집계 수 조회
     * @param : commonEntity
     * @return
     */
	int countTrafficInfoLogList(CommonEntity commonEntity);

    /**
     * @Method Name : findAllTrafficInfoLogList
     * @작성일 : 2023. 9. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 이력 집계 > 교통정보 이력집계 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtStdLinkSectnInfo> findAllTrafficInfoLogList(CommonEntity commonEntity);

	/**
	 * @Method Name : findTop5ByAnlsDt
	 * @작성일 : 2023. 10. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 링크별 교통량 TOP5 조회
	 * @param : MrtStdLinkSectnInfo
	 * @return
	 */
	List<Map<String,Object>> findTop5ByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);

	/**
	 * @Method Name : findTop5DelayInfoByAnlsDt
	 * @작성일 : 2023. 10. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 주요 정체구간 TOP5 조회
	 * @param : MrtStdLinkSectnInfo
	 * @return
	 */
	List<Map<String,Object>> findTop5DelayInfoByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);
	
}

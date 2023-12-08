package com.neighbor21.ggits.common.mapper;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.DsetInfo;

@Mapper
public interface DsetInfoMapper {
	
	/**
	 * @Method Name : findOneDsetInfo
	 * @작성일 : 2023. 10. 16.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 통계 분석 > 데이터 활용 통계 > 서비스 이력 > 스마트교차로 데이터 이력 > 수집 데이터 
	 * @return
	 */
	DsetInfo findOneDsetInfo(DsetInfo dsetInfo);

}

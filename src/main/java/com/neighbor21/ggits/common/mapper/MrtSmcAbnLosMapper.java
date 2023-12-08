package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtSmcAbnLos;

@Mapper
public interface MrtSmcAbnLosMapper {
	/**
     * @Method Name : countSmcAbnLosList
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 혼잡도 개수 조회
     * @return
     */
	int countSmcAbnLosList(CommonEntity commonEntity);

	/**
     * @Method Name : countSmcAbnLosList
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 혼잡도 리스트 조회
     * @return
     */
	List<MrtSmcAbnLos> findAllSmcAbnLosList(CommonEntity commonEntity);
	
	
	/**
	 * @Method Name : findByTrfvimCngrtAndAvgVhclSpeed
	 * @작성일 : 2023. 9. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 교통 통계 혼잡도 조회
	 * @return
	 */
	List<MrtSmcAbnLos> findByTrfvimCngrtAndAvgVhclSpeed(MrtSmcAbnLos mrtSmcAbnLos);
}

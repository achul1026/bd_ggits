package com.neighbor21.ggits.common.mapper;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface TrfIpcssNboplMapper {
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

}

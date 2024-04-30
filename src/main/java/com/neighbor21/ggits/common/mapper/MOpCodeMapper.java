package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpCode;

@Mapper
public interface MOpCodeMapper {
	
    /**
     * @Method Name : findAllCodeList
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 리스트 조회
     * @param : mOpCode
     * @return
     */
	public List<MOpCode> findAllCodeList(MOpCode mOpCode);
	
    /**
     * @Method Name : findAllCodeListByGrpCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 조회
     * @param : grpCdId
     * @return
     */
	public List<MOpCode> findAllCodeListByGrpCdId(String grpCdId);
	
	/**
	 * @Method Name : findAllCodeListByGrpCdIdForMonitoring
	 * @작성일 : 2023. 12. 27.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 리스트 조회
	 */
	public List<MOpCode> findAllCodeListByGrpCdIdForMonitoring(Long authId);

    /**
     * @Method Name : countCodeBygrpCdIdAndCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 중복 조회
     * @param : mOpCode
     * @return
     */
	public int countCodeBygrpCdIdAndCdId(MOpCode mOpCode);
	
    /**
     * @Method Name : saveCode
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 등록
     * @param : mOpCode
     * @return
     */
	public void saveCode(MOpCode mOpCode);

    /**
     * @Method Name : deleteCodeByCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 삭제
     * @param : cdId
     * @return
     */
	public void deleteCodeByCdId(String cdId);

    /**
     * @Method Name : updateCode
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 수정
     * @param : mOpCode
     * @return
     */
	public void updateCode(MOpCode mOpCode);

    /**
     * @Method Name : countMOpCodeByCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 중복 조회
     * @param : cdId
     * @return
     */
	public int countMOpCodeByCdId(String cdId);
	
    /**
     * @Method Name : countCodeByGrpCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 리스트 개수 조회
     * @param : mOpCode
     * @return
     */
	public int countCodeByGrpCdId(MOpCode mOpCode);

    /**
     * @Method Name : findOneMOpCodeByCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 상세 조회
     * @param : cdId
     * @return
     */
	public MOpCode findOneMOpCodeByCdId(MOpCode mOpCode);

    /**
     * @Method Name : countCodeListByGrpCdIdAndSearchOption
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 관리 코드 개수 비동기 호출
     * @param : mOpCode
     * @return
     */
	public int countCodeListByGrpCdIdAndSearchOption(MOpCode mOpCode);

	/**
	 * @Method Name : findOneMOpCodeLikeCdIdAndGrpCdId
	 * @작성일 : 2023. 11. 07.
	 * @작성자 : KY.LEE
	 * @Method 설명 : CDID LIKE검색
	 * @param : mOpCode
	 * @return
	 */
	public MOpCode findOneMOpCodeLikeCdIdAndGrpCdId(MOpCode mOpCode);
	
	/**
	 * @Method Name : findOndeMOpCodeCdNmAndGrpCdId
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 그룹 코드 아이디와 코드이름으로 코드 아이디 조회
	 * @param : mOpCode
	 * @return
	 */
	public MOpCode findOndeMOpCodeCdNmAndGrpCdId(MOpCode mOpCode);
}

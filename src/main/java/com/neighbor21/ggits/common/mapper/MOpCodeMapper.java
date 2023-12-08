package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

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
}

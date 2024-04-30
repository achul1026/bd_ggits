package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.EqpLogDTO;

@Mapper
public interface AdsiVdsSttsInfoMapper {

	/**
     * @Method Name : countVdsInfoList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : VDS 장비 로그 조회
     * @return
     */
	public int countVdsInfoList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllVdsInfoList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : VDS 장비 로그 조회
     * @return
     */
	public List<EqpLogDTO> findAllVdsInfoList(CommonEntity commonEntity);

}

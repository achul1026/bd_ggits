package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.EqpLogDTO;

@Mapper
public interface AdsiSmcrsrdCameraSttsInfoMapper {

	/**
     * @Method Name : countSmcInfoList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 스마트카메라 장비 로그 조회
     * @return
     */
	public int countSmcInfoList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllSmcInfoList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 스마트카메라 장비 로그 조회
     * @return
     */
	public List<EqpLogDTO> findAllSmcInfoList(CommonEntity commonEntity);

}

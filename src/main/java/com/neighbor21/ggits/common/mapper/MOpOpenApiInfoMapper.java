package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;

@Mapper
public interface MOpOpenApiInfoMapper {
  
  /**
   * @Method Name : findAllMOpOpenApiInfo
   * @작성일 : 2023.11.28
   * @작성자 : IK.MOON
   * @Method 설명 : OPEN API 목록 조회
   */ 
  public List<MOpOpenApiInfo> findAllMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo);
  
  /**
   * @Method Name : countAllBySearchOption
   * @작성일 : 2023.11.28
   * @작성자 : IK.MOON
   * @Method 설명 : OPEN API 목록 카운트
   */ 
  public int countAllBySearchOption(MOpOpenApiInfo mOpOpenApiInfo);

  /**
   * @Method Name : findOneByDsetId
   * @작성일 : 2023.11.28
   * @작성자 : IK.MOON
   * @Method 설명 : OPEN API 단건 조회
   */ 
  public MOpOpenApiInfo findOneByDsetId(String dSetId);
  
  /**
   * @Method Name : saveMOpOpenApiInfo
   * @작성일 : 2023.11.28
   * @작성자 : IK.MOON
   * @Method 설명 : OPEN API 저장
   */ 
  public void saveMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo);
  
  
  /**
   * @Method Name : updateMOpOpenApiInfo
   * @작성일 : 2023.11.28
   * @작성자 : IK.MOON
   * @Method 설명 : OPEN API 수정
   */ 
  public void updateMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo);
  
  /**
   * @Method Name : deleteMOpOpenApiInfo
   * @작성일 : 2023.12.18
   * @작성자 : KY.LEE
   * @Method 설명 : OPEN API 삭제
   */ 
  public void deleteMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo);
}

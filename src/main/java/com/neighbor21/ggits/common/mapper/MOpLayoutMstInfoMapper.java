package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;

@Mapper
public interface MOpLayoutMstInfoMapper {
	
	/**
	 * @Method Name : findAllByOprtrIdAndMenuIdList
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 유저 레이아웃 정보 목록 조회
	 * @param MOpLayoutMstInfo
	 */
	public List<MOpLayoutMstInfo> findAllByOprtrIdAndMenuIdList(MOpLayoutMstInfo mOpLayoutMstInfo);

	/**
	 * @Method Name : findOneByLayoutId
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 유저 레이아웃 정보 조회
	 * @param String layoutId
	 */	
	public MOpLayoutMstInfo findOneByLayoutId(String layoutId);
	
	/**
	 * @Method Name : updateMOpLayoutMstInfo
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 레이아웃 정보 수정
	 * @param MOpLayoutMstInfo
	 */	
	public void updateMOpLayoutMstInfo(MOpLayoutMstInfo mOpLayoutMstInfo);
	
	/**
	 * @Method Name : saveMOpLayoutMstInfo
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 레이아웃 정보 저장
	 * @param MOpLayoutMstInfo
	 */	
	public void saveMOpLayoutMstInfo(MOpLayoutMstInfo mOpLayoutMstInfo);
	
	/**
	 * @Method Name : countByOprtrId
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 카운트 조회 쿼리
	 * @param Long oprtrId
	 */	
	public int countByOprtrId(Long oprtrId);
	
	/**
	 * @Method Name : deleteByOprtrId
	 * @작성일 : 2023. 11. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 레이아웃 정보 삭제
	 * @param Long oprtrId
	 */	
	public void deleteByOprtrId(long oprtrId);
}

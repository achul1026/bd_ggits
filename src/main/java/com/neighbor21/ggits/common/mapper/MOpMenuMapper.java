package com.neighbor21.ggits.common.mapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;

@Mapper
public interface MOpMenuMapper {

	/**
	 * @Method Name : findAllMenuIdAndUpperMenuIdIsNullAndSortNoIsNotNullByMenuLvlAndUseYn
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 상위 메뉴 조건 조회
	 */
	public List<MOpMenu> findAllMenuIdAndUpperMenuIdIsNullAndSortNoIsNotNullByMenuLvlAndUseYn(Map<String,Object> paramMap);

	/**
	 * @Method Name : countByUpperMenuIdIsNullAndUseYn
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 상위 메뉴 카운트
	 */
	public Long countByUpperMenuIdIsNullAndUseYn(Map<String,Object> paramMap);

	/**
	 * @Method Name : countByUpperMenuId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 하위 메뉴 카운트
	 */
	public Long countByUpperMenuId(String upperMenuId);

	/**
	 * @Method Name : countBySortNoMaxCount
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : sortNo 조회
	 */
	public Long countBySortNoMaxCount();

	/**
	 * @Method Name : findAllBySortNoIsNotNullAndUpprMenuId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 하위 메뉴 리스트 조회
	 */
	public List<MOpMenu> findAllBySortNoIsNotNullAndUpprMenuId(String upperMenuId);
	
	/**
	 * @Method Name : findAllBySortNoIsNotNullAndUpprMenuId
	 * @작성일 : 2023. 12. 26.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 하위 메뉴 리스트 조회(메뉴관리)
	 */
	public List<MOpMenu> findAllSubMenuBySortNoIsNotNullAndUpprMenuId(String upperMenuId);

	/**
	 * @Method Name : findAllByUpperMenuIdAndAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한별 하위 목록 조회
	 */
	public List<MOpMenu> findAllByUpperMenuIdAndAuthId(MOpMenu mOpMenu);
	
	/**
	 * @Method Name : save
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 저장
	 */
	public void save(MOpMenu mOpMenu);

	/**
	 * @Method Name : countByMenuIdNextVal
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 ID 맥스 카운트 조회
	 */
	public int countByMenuIdNextVal();
	
	/**
	 * @Method Name : findOneByMenuId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 단건 조회
	 */
	public MOpMenu findOneByMenuId(String menuId);
	
	/**
	 * @Method Name : deleteMOpMenuByMenuId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 삭제
	 */
	public void deleteMOpMenuByMenuId(String menuId) throws SQLException;

	/**
	 * @Method Name : update
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 수정
	 */
	public void update(MOpMenu mOpMenu);
	
	/**
	 * @Method Name : findAllByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한별 상위 목록 조회
	 */
	public List<MOpMenu> findAllByAuthId(MOpOperator mOpOperator);
	
	/**
	 * @Method Name : existsMenuByAuthId
	 * @작성일 : 2023. 9. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : URL 권한 체크
	 */
	public boolean existsMenuByAuthId(MOpMenu mOpMenu);
	
	
	/**
	 * @Method Name : findFirstByMenuAddr
	 * @작성일 : 2023. 10. 4.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 주소 상위 1개 조회
	 */
	public MOpMenu findFirstByMenuAddr(MOpMenu mOpMenu);
	
	/**
	 * @Method Name : findAllByUrlPttrn
	 * @작성일 : 2023. 10. 4.
	 * @작성자 : KY.LEE
	 * @Method 설명 : URL 패턴으로 메뉴 조회
	 */
	public List<MOpMenu> findAllByUrlPttrn(String urlPttrn);
	
	/**
	 * @Method Name : findMenuIdByMenuPttrnType
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴패턴 타입으로 메뉴 ID조회
	 */
	public List<String> findMenuIdByMenuPttrnType(MOpMenu mOpMenu);

	/**
	 * @Method Name : findOneMenuIdByMenuPttrnType
	 * @작성일 : 2023. 11. 02.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴패턴 타입으로 메뉴 ID조회 단건
	 */
	public String findOneMenuIdByMenuPttrnType(MOpMenu mOpMenu);
}

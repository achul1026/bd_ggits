package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MetaFileInfo;

@Mapper
public interface MetaFileInfoMapper {
	
	/**
	 * @Method Name : saveMetaFileInfo
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메타데이터 파일 업로드
	 * @return
	 */
	public void saveMetaFileInfo(MetaFileInfo metaFileInfo);
	
	/**
	 * @Method Name : findOneByFileId
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 단건 파일 찾기
	 * @return
	 */
	public MetaFileInfo findOneByFileId(String fileId);

	/**
	 * @Method Name : findAllByTblId
	 * @작성일 : 2023. 11. 1.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 다건 파일 찾기
	 * @return
	 */
	public List<MetaFileInfo> findAllByTblId(String tblId);
	
	/**
	 * @Method Name : deleteMetaFileInfoByFileId
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 파일 삭제
	 * @return
	 */
	public void deleteMetaFileInfoByFileId(String fileId);
}

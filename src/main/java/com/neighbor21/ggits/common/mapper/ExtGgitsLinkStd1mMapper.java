package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;

@Mapper
public interface ExtGgitsLinkStd1mMapper {
	/**
	 * 실시간 모니터링용 최근 소통정보 조회
	 * @return
	 */
	List<ExtGgitsLinkStd1m> findAllByRecent();
	List<ExtGgitsLinkStd1m> findAllByRecent2();


	/**
	 * 초기 소통정보 조회 (ROADRANK = 101, 102, 103)
	 * @return
	 */
	List<ExtGgitsLinkStd1m> findAllByRecentLowerRoadRank();
	List<ExtGgitsLinkStd1m> findAllByRecentLowerRoadRank2();
	
}

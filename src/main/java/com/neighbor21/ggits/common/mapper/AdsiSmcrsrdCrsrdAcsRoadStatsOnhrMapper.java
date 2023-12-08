package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsOnhr;

@Mapper
public interface AdsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper {

	int countcomunicationList(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> findAllComunicationList(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> findAllComunicationListWithoutPaging(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

}

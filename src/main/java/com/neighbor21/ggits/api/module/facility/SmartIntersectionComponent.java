package com.neighbor21.ggits.api.module.facility;

import com.neighbor21.ggits.api.module.facility.dto.SmartIntersectionDTO;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadInfo;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdInfo;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdAcsRoadInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 스마트교차로 정보 관리 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  SmartIntersectionComponent
 * @since : 2023-09-05
 */
@Component
public class SmartIntersectionComponent {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdsiSmcrsrdCrsrdInfoMapper adsiSmcrsrdCrsrdInfoMapper;

    @Autowired
    AdsiSmcrsrdCrsrdAcsRoadInfoMapper adsiSmcrsrdCrsrdAcsRoadInfoMapper;

    /**
     * 스마트 교차로 기초정보 조회
     * @return
     */
    public List<SmartIntersectionDTO> getSmartIntersectionInfo(){
        List<SmartIntersectionDTO> data = new ArrayList<>();
        List<AdsiSmcrsrdCrsrdInfo> adsiSmcrsrdCrsrdInfoList = adsiSmcrsrdCrsrdInfoMapper.findAll();
        try {
            for (AdsiSmcrsrdCrsrdInfo adsiSmcrsrdCrsrdInfo : adsiSmcrsrdCrsrdInfoList) {
                SmartIntersectionDTO smartIntersectionDTO = new SmartIntersectionDTO(adsiSmcrsrdCrsrdInfo);
                List<AdsiSmcrsrdCrsrdAcsRoadInfo> roadList = adsiSmcrsrdCrsrdAcsRoadInfoMapper.findAllByCrsrdId(smartIntersectionDTO.getCrsrdId());
                smartIntersectionDTO.setRoadInfoList(roadList);
                data.add(smartIntersectionDTO);
            }
        }catch (SQLException e) {
        	logger.info("스마트 교차로 기초정보 조회 오류");
        }
        return data;
    }

}

package com.neighbor21.ggits.api.module.facility.dto;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadInfo;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  SmartIntersectionDTO
 * @since : 2023-09-05
 */
public class SmartIntersectionDTO extends AdsiSmcrsrdCrsrdInfo {

    List<AdsiSmcrsrdCrsrdAcsRoadInfo> roadInfoList;

    public SmartIntersectionDTO(AdsiSmcrsrdCrsrdInfo entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public List<AdsiSmcrsrdCrsrdAcsRoadInfo> getRoadInfoList() {
        return roadInfoList;
    }

    public void setRoadInfoList(List<AdsiSmcrsrdCrsrdAcsRoadInfo> roadInfoList) {
        this.roadInfoList = roadInfoList;
    }
}

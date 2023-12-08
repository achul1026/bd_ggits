package com.neighbor21.ggits.api.module.common;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCameraInfo;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCameraInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 스마트교차로 카메라 정보 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  CMCrossRoadCameraComponent
 * @since : 2023-09-07
 */
@Component
public class CMCrossRoadCameraComponent extends BaseMapDataComponent {

    @Autowired
    AdsiSmcrsrdCameraInfoMapper adsiSmcrsrdCameraInfoMapper;

    /**
     * 스마트 교차로 카메라 정보 조회
     * @return
     */
    public List<AdsiSmcrsrdCameraInfo> getCrossRoadCameraList(){
        return adsiSmcrsrdCameraInfoMapper.findAll();
    }

}

package com.neighbor21.ggits.api.module.monitoring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GgsplBusPeriodicinfo;
import com.neighbor21.ggits.common.mapper.GgbisBusEventinfoMapper;
import com.neighbor21.ggits.common.mapper.GgsplBusPeriodicinfoMapper;

/**
 * 시내버스 이동현황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MBusComponent
 * @since : 2023-09-07
 */
@Component
public class MBusComponent extends BaseMapDataComponent {

    @Autowired
    GgbisBusEventinfoMapper ggbisBusEventinfoMapper;

    @Autowired
    GgsplBusPeriodicinfoMapper ggsplBusPeriodicinfoMapper;


    /**
     * 실시간 버스이동현황 조회
     * @return
     */
    public List<GgsplBusPeriodicinfo> getRealtimeBusMoveInfo(GgsplBusPeriodicinfo ggsplBusPeriodicinfo){
        return ggsplBusPeriodicinfoMapper.findAllRealTimeBusMoveInfo(ggsplBusPeriodicinfo);
    }
    
    /**
     * 실시간 버스이동현황 목록 개수 조회
     * @return
     */
    public int getRealtimeBusMoveInfoTotalCnt(GgsplBusPeriodicinfo ggsplBusPeriodicinfo){
    	return ggsplBusPeriodicinfoMapper.countAllRealTimeBusMoveInfo(ggsplBusPeriodicinfo);
    }
}

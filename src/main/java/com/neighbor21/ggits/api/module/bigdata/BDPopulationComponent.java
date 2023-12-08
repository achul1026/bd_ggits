package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.KtTimeZn;
import com.neighbor21.ggits.common.entity.KtWeekdays;
import com.neighbor21.ggits.common.mapper.KtTimeZnMapper;
import com.neighbor21.ggits.common.mapper.KtWeekDaysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 유동인구 밀집예측 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPopulationComponent
 * @since : 2023-09-07
 */
@Component
public class BDPopulationComponent extends BaseMapDataComponent {
    
    @Autowired
    KtWeekDaysMapper ktWeekDaysMapper;
    
    @Autowired
    KtTimeZnMapper ktTimeZnMapper;


    /**
     * 요일별 최근값 조회
     * @return
     */
    public List<KtWeekdays> getPopulationInfoByWeekDaysRecent(){
        return ktWeekDaysMapper.findAllByRecent();
    }

    /**
     * 시간별 최근값 조회
     * @return
     */
    public List<KtTimeZn> getPopulationInfoByTimeZnRecent(){
        return ktTimeZnMapper.findAllByRecent();
    }
}

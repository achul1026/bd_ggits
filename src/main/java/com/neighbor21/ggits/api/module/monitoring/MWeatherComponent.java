package com.neighbor21.ggits.api.module.monitoring;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.KmaShtrmWthrFrcst;
import com.neighbor21.ggits.common.mapper.KmaShtrmWthrFrcstMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.BDStringUtil;

/**
 * 기상현황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MWeatherComponent
 * @since : 2023-09-07
 */
@Component
public class MWeatherComponent extends BaseMapDataComponent {

    @Autowired
    KmaShtrmWthrFrcstMapper kmaShtrmWthrFrcstMapper;

    /**
     * 기상예보 데이터 조회
     * @param date
     * @param time
     * @return
     */
    public List<KmaShtrmWthrFrcst> getWeatherList(String date, String time) {

        Date now = new Date();
        if(BDStringUtil.isNull(date) || BDStringUtil.isNull(time)) {
            date = BDDateFormatUtil.format(now, "yyyyMMdd");
            time = BDDateFormatUtil.format(now, "HH")+"00";
        }
        return kmaShtrmWthrFrcstMapper.findAllWeatherListByFrcstDivCdAndPrdctnYmdAndprdctnTime("PTY", date, time);
    }
}

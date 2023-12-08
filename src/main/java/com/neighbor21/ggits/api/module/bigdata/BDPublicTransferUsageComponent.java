package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import org.springframework.stereotype.Component;

/**
 * 대중교통 이용현황 분석 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPublicTransferUsedComponent
 * @since : 2023-09-07
 */
@Component
public class BDPublicTransferUsageComponent extends BaseMapDataComponent {


    /**
     * 기종점 대중교통 이용량 데이터 조회
     */
    public void getPublicTransferStartEndUsage(){

    }

    /**
     * 권역별 대중교통 이용현황
     */
    public void getPublicTransferStartEndUsageBySGG(){

    }

    /**
     * 권역별 환승현황 분석
     */
    public void getPublicTransferTranslateAnalysis(){

    }

    /**
     * 정류장별 대중교통 및 노선 시설물 조회
     */
    public void getPublicTransferFacility(){

    }

    /**
     * 정류장별 버스 이용률
     */
    public void getPublicTransferUsagePercentByStation(){

    }

    /**
     * 정류장별 버스 노선 및 BIT
     */
    public void getPublicTransferRouteAndBITByStation(){

    }
}

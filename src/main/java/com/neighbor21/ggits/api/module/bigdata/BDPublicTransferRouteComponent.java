package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import org.springframework.stereotype.Component;

/**
 * 대중교통 노선별 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPublicTransferRouteComponent
 * @since : 2023-09-07
 */
@Component
public class BDPublicTransferRouteComponent extends BaseMapDataComponent {


    /**
     * 노선구간별 승하차/재차 승객 수 조회
     */
    public void getPublicTransferBoardingDropOff(){

    }

    /**
     * 노선구간별 수용성 및 굴곡도 분석
     */
    public void getPublicTransferCurvatureAnalysis(){

    }

    /**
     * 노선구간별 중복구간 도출 및 적정성 분석
     */
    public void getPublicTransferAdequacyAnalysis(){

    }
}

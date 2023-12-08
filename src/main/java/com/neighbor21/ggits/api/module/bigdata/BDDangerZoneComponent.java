package com.neighbor21.ggits.api.module.bigdata;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import com.neighbor21.ggits.common.util.BDStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 교통위험 구간분석
 *
 * @author : Charles Kim
 * @fileName :  BDDangerZoneComponent
 * @since : 2023-09-07
 */
@Component
public class BDDangerZoneComponent extends BaseMapDataComponent {

    @Autowired
    TaasLinkareaAcdntDstrctMapper taasLinkareaAcdntDstrctMapper;

    @Autowired
    TaasAdsiTrgtAcdntStatsInfoMapper taasAdsiTrgtAcdntStatsInfoMapper;

    @Autowired
    TaasAdsiAcdntDstrctMapper taasAdsiAcdntDstrctMapper; // 행정시 사고 구역

    @Autowired
    TaasBcyclAcdntDstrctMapper taasBcyclAcdntDstrctMapper; // 자전거 사고구역

    @Autowired
    TaasDrnkgAcdntDstrctMapper taasDrnkgAcdntDstrctMapper; // 음주 사고구역

    @Autowired
    TaasFrostAcdntDstrctMapper taasFrostAcdntDstrctMapper; // 결빙 사고구역

    @Autowired
    TaasJaywkPdstAcdntDstrctMapper taasJaywkPdstAcdntDstrctMapper; // 무단횡단 보행자 사고구역

    @Autowired
    TaasLawVltnPdstAcdntDstrctMapper taasLawVltnPdstAcdntDstrctMapper; // 법위반 보행자 사고구역

    @Autowired
    TaasPdsnAcdntDstrctMapper taasPdsnAcdntDstrctMapper; // 보행자 사고구역

    @Autowired
    TaasPdstChidAcdntDstrctMapper taasPdstChidAcdntDstrctMapper; // 어린이 보행자 사고구역

    @Autowired
    TaasPdstOlmanAcdntDstrctMapper taasPdstOlmanAcdntDstrctMapper; // 노인 보행자 사고구역

    @Autowired
    TaasSchznChidAcdntDstrctMapper taasSchznChidAcdntDstrctMapper; // 어린이보호구역 어린이 사고구역

    @Autowired
    TaasTruckAcdntDstrctMapper taasTruckAcdntDstrctMapper; // 화물자 차고구역

    @Autowired
    TaasTwhlvhAcdntDstrctMapper taasTwhlvhAcdntDstrctMapper; // 이륜차 사고구역

    @Autowired
    TaasDthTrfAcdntInfoMapper taasDthTrfAcdntInfoMapper; // 사망 교통 사고 정보

    @Autowired
    TaasAcdntDstrctMasterMapper taasAcdntDstrctMasterMapper; // 사망 교통 사고 정보
    /**
     * 행정시 사고구역 전체 가져오기
     * @return
     */
    public List<TaasAdsiAcdntDstrct> getSggAccidentInfo(){

        return taasAdsiAcdntDstrctMapper.findAll();
    }

    public List<TaasAdsiAcdntDstrct> getRoadAccidentInfo(String sggCode){
        /*taasDthTrfAcdntInfoMapper.f*/
        return taasAdsiAcdntDstrctMapper.findAll();
    }

    /**
     *
     * @return
     */
    public List<TaasDthTrfAcdntInfo> getRoadAccidentInfoGroupBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        return taasDthTrfAcdntInfoMapper.findAllByAcdntGroupBySGG(mapBigdataSearchDTO);
    }

    /**
     * 사고정보 전체 조회
     * @return
     */
    public List<TaasAcdntDstrctMaster> getAccidentInfoAll(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<TaasAcdntDstrctMaster> list = taasAcdntDstrctMasterMapper.findAll(mapBigdataSearchDTO);
        if(!BDStringUtil.isNull(mapBigdataSearchDTO.getAccidentType())) {
            list = list.stream().filter(x -> x.getType().equals(mapBigdataSearchDTO.getAccidentType())).collect(Collectors.toList());
        }
        return list;
    }

}

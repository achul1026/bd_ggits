package com.neighbor21.ggits.api.module.bigdata;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.neighbor21.ggits.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.mapper.TaasAcdntDstrctMasterMapper;
import com.neighbor21.ggits.common.mapper.TaasAdsiAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasAdsiTrgtAcdntStatsInfoMapper;
import com.neighbor21.ggits.common.mapper.TaasBcyclAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasDrnkgAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasDthTrfAcdntInfoMapper;
import com.neighbor21.ggits.common.mapper.TaasFrostAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasJaywkPdstAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasLawVltnPdstAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasLinkareaAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasPdsnAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasPdstChidAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasPdstOlmanAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasSchznChidAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasTruckAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TaasTwhlvhAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.UticRoadDngrSttsFrcstMapper;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

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

    @Autowired
    UticRoadDngrSttsFrcstMapper uticRoadDngrSttsFrcstMapper;
    /**
     * 행정시 사고구역 전체 가져오기
     * @return
     */
    public List<TaasAdsiAcdntDstrct> getSggAccidentInfo(){

        return taasAdsiAcdntDstrctMapper.findAll();
    }


    /**
     * 도로안전정보
     * @return
     */
    public List<UticRoadDngrSttsFrcst> getRoadDangerInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
    	if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getDangerType())) {
    		if(mapBigdataSearchDTO.getDangerType().contains(",")) {
    			mapBigdataSearchDTO.setDangerTypeList(mapBigdataSearchDTO.getDangerType().split(","));
    		}
    	}
        return uticRoadDngrSttsFrcstMapper.findAllBySearchOptionForMap(mapBigdataSearchDTO);
    }

    /**
     * 시군별 도로안전정보
     * @return
     */
    public List<UticRoadDngrSttsFrcst> getRoadDangerInfoBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){

        return uticRoadDngrSttsFrcstMapper.findAllBySearchOptionForMapGroupSGG(mapBigdataSearchDTO);
    }

    /**
     * 시군별 교통사고 위험지역 정보
     * @return
     */
    public List<TaasDthTrfAcdntInfo> getRoadAccidentInfoGroupBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        return taasDthTrfAcdntInfoMapper.findAllByAcdntGroupBySGG(mapBigdataSearchDTO);
    }

    /**
     * 교통사고 위험지역 정보
     * @return
     */
    private List<TaasAcdntDstrctMaster> getAccidentInfoAll(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<TaasAcdntDstrctMaster> list = taasAcdntDstrctMasterMapper.findAll(mapBigdataSearchDTO);
        if(!BDStringUtil.isNull(mapBigdataSearchDTO.getAccidentType())) {
            list = list.stream().filter(x -> x.getType().equals(mapBigdataSearchDTO.getAccidentType())).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 교통사고 위험지역 시군구포함 데이터
     * @param mapBigdataSearchDTO
     * @return
     */
    public TaasMaster getAccidentInfoAllAndSGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        TaasMaster taasMaster = new TaasMaster();
        List<TaasAcdntDstrctMaster> list = getAccidentInfoAll(mapBigdataSearchDTO);
        /*Map<String, TaasAcdntDstrctMaster> map =
                list.stream()
                        .collect(Collectors.toMap(TaasAcdntDstrctMaster::getSggCd, Function.identity(), TaasAcdntDstrctMaster::merge));*/
        Map<String, TaasAcdntDstrctMaster> map = new HashMap<>();
        taasMaster.setSggGroup(map);
        taasMaster.setPositions(list);
        return taasMaster;
    }

}

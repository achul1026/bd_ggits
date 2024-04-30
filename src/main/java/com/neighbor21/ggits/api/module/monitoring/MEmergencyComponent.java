package com.neighbor21.ggits.api.module.monitoring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclCurInfo;
import com.neighbor21.ggits.common.entity.TsDggdVhclRungInfoCur;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclCurInfoMapper;
import com.neighbor21.ggits.common.mapper.TsDggdVhclRungInfoCurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 긴급차량 이동현황
 *
 * @author : Charles Kim
 * @fileName :  MEmergencyComponent
 * @since : 2023-09-07
 */
@Component
public class MEmergencyComponent extends BaseMapDataComponent {


    @Autowired
    ScsEmrgVhclCurInfoMapper scsEmrgVhclCurInfoMapper;

    @Autowired
    TsDggdVhclRungInfoCurMapper tsDggdVhclRungInfoCurMapper;

    List<TsDggdVhclRungInfoCur>  savedTsDggdVhclData = new ArrayList<>();

    List<TsDggdVhclRungInfoCur> savedWarningDggdVhclData = new ArrayList<>();

    /**
     * 긴급차량 현재 이동상황 조회
     * @return
     */
    public List<ScsEmrgVhclCurInfo> getEmergencyVehicleMoveInfoList(){
        return scsEmrgVhclCurInfoMapper.findAll();
    }

    /**
     * 위험물 차량 실시간 정보 조회
     * @return
     */
    public List<TsDggdVhclRungInfoCur> getDggdVechicleMoveInfoListAll(){
        return savedTsDggdVhclData;
    }

    public void setSavedTsDggdVhclData(List<TsDggdVhclRungInfoCur> savedTsDggdVhclData){
        this.savedTsDggdVhclData = savedTsDggdVhclData;
    }


    /**
     * 위험물 차량 정보 조회(긴급차량 반경 1km)
     * @return
     */
    public List<TsDggdVhclRungInfoCur> getDggdVechicleMoveInfoList(){
        return tsDggdVhclRungInfoCurMapper.findAllByRungPlanYnIsY();
    }

    /**
     * 위험물 차량 정보 조회(돌발위치 반경 1km)
     * @return
     */
    public List<TsDggdVhclRungInfoCur> getDggdVechicleMoveInfoListByWarning(){
        return savedWarningDggdVhclData;
    }

    public void setSavedWarningDggdVhclData(List<TsDggdVhclRungInfoCur> warningDggdVhclData) {
        this.savedWarningDggdVhclData = warningDggdVhclData;
    }
}

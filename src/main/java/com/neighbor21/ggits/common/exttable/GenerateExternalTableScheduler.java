package com.neighbor21.ggits.common.exttable;

import com.neighbor21.ggits.api.module.monitoring.MEmergencyComponent;
import com.neighbor21.ggits.api.module.monitoring.MTrafficComponent;
import com.neighbor21.ggits.api.module.monitoring.MWarningComponent;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import org.apache.log4j.LogManager;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * External Table 생성 스케쥴러 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  GenerateExternalTableScheduler
 * @since : 2023-11-16
 */
@Component
public class GenerateExternalTableScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MOpCodeMapper mOpCodeMapper;

    @Autowired
    LOpExtnlAsctnTblCrtLogMapper lOpExtnlAsctnTblCrtLogMapper;

    @Autowired
    GenerateExternalTableComponent generateExternalTableComponent;

    @Autowired
    ExtGgitsLinkStd1mMapper extGgitsLinkStd1mMapper;

    @Autowired
    MTrafficComponent mTrafficComponent;

    @Autowired
    MWarningComponent mWarningComponent;

    @Autowired
    MEmergencyComponent mEmergencyComponent;

    @Autowired
    GimsMngInciDetailMapper gimsMngInciDetailMapper;

    @Autowired
    TsDggdVhclRungInfoCurMapper tsDggdVhclRungInfoCurMapper;


    static String doScheduler;

    boolean isDo = false;
    boolean runningWarningInfo = false;
    boolean runningDangerCarInfo = false;

    @PostConstruct
    public void init(){
        doScheduler = System.getProperty("ext.scheduler");
        logger.info("External Table 생성 스케쥴러 On/Off : {}", doScheduler);
        /*generate();*/
        String hh = BDDateFormatUtil.format(new Date(), "mm");
        int hhInt = Integer.parseInt(hh);
        int hhDuration = hhInt % 10;
        /*if(0%hhInt)*/
        getTrafficInfo();
//        getDangerVehicleInfo();
        /*geWarningInfo();
        geWarningAlarmInfo();*/
    }

    @Scheduled(cron = "0 */5 * * * *")
    @Async
    public void geWarningInfo(){
        if(runningWarningInfo) {
            return;
        }
        logger.info("돌발정보 마커용 조회 및 저장");
        List<GimsMngInciDetail> waringMarkerList = new ArrayList<>();
        List<TsDggdVhclRungInfoCur> warningDangerCarList = new ArrayList<>();
        List<GimsMngInciDetail> waringAlarmList = new ArrayList<>();
        try {
            runningWarningInfo = true;
            waringMarkerList = gimsMngInciDetailMapper.findAllWarningListForMapMarker();
            waringMarkerList.addAll(gimsMngInciDetailMapper.findAllUTICWarningListForMapMarker());
            warningDangerCarList = tsDggdVhclRungInfoCurMapper.findAllByRungPlanYnIsYByWarning();
            waringAlarmList = gimsMngInciDetailMapper.findAllDailyWarningAlarmList();
            mEmergencyComponent.setSavedWarningDggdVhclData(warningDangerCarList);
            mWarningComponent.setWaringMarkerList(waringMarkerList);
            mWarningComponent.setWaringAlarmList(waringAlarmList);
        }catch (Exception e) {
            if (e.getMessage().contains("does not exist")) {
                logger.info("bigdata.ext_gims_mng_inci_detail 테이블 생성중 저장된 데이터로 출력");
            } else {
                logger.error("돌발정보 테이블 조회 실패");
            }
        }finally {
            runningWarningInfo = false;
        }
        logger.info("돌발정보 마커용 조회 및 저장 완료");
    }

    @Scheduled(cron = "0 */1 * * * *")
    @Async
    public void getDangerVehicleInfo(){
        if(runningDangerCarInfo) {
            return;
        }
        logger.info("위험물차량 데이터 조회 저장 시작");
        try{
            runningDangerCarInfo = true;
            List<TsDggdVhclRungInfoCur> dbList = tsDggdVhclRungInfoCurMapper.findAll();
            mEmergencyComponent.setSavedTsDggdVhclData(dbList);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("위험물차량 데이터 조회 실패");
        }finally {
            runningDangerCarInfo = false;
        }
        logger.info("위험물차량 데이터 조회 저장 완료");
    }

    @Scheduled(cron = "0 */2 * * * *")
    @Async
    public void getTrafficInfo(){
        logger.info("최신 교통량 소통정보 조회 및 저장");
        try {
            List<ExtGgitsLinkStd1m> recentTrafficInfo = extGgitsLinkStd1mMapper.findAllByRecent();
            if (!recentTrafficInfo.isEmpty()) {
                mTrafficComponent.setSavedTrafficInfo(recentTrafficInfo);
            }
        }catch(DataAccessResourceFailureException e) {
            logger.info("최신 교통량 소통정보 조회 및 저장실패 -> 기존데이터로 표현");
        }catch(Exception e) {
            logger.info("최신 교통량 소통정보 조회 및 저장실패 -> 기존데이터로 표현");
        }
        logger.info("최신 교통량 소통정보 조회 및 저장 완료");
    }

    
    @Scheduled(cron = "0 */5 * * * *")
    @Async
    public void generate(){
        if(doScheduler != null && doScheduler.equals("true") && !isDo) {
            logger.info("External Table 생성 스케쥴러 시작");
            isDo = true;
            List<MOpCode> targetTableList = mOpCodeMapper.findAllCodeListByGrpCdId("EXT_TBL_GEN_DU");

            for (MOpCode table : targetTableList) {
                try {
                    if(!"Y".equals(table.getUseYn())){
                        continue;
                    }
                    String tableNm = table.getCdNm();
                    long duration = Long.parseLong(table.getDescr());
                    logger.info("tableNm : {}, duration : {} minute", tableNm, duration);
                    LOpExtnlAsctnTblCrtLog prsExtTable = lOpExtnlAsctnTblCrtLogMapper.findOneByExtnlAsctnTblNm(tableNm);
                    Date date = new Date();
                    Timestamp occurDt = new Timestamp(date.getTime());
                    if(prsExtTable != null) {
                        if(prsExtTable.getAddOccurDt().before(occurDt)) {
                            logger.info("External Table 생성 시작 {}", tableNm);
                            generateExternalTableComponent.generate(tableNm);
                            logger.info("External Table 생성 종료 {}", tableNm);
                        }else{
                            continue;
                        }
                    }
                    Date enddate = new Date();
                    Timestamp addOccurDt = new Timestamp(enddate.getTime());
                    addOccurDt.setTime(date.getTime() + TimeUnit.MINUTES.toMillis(duration));

                    LOpExtnlAsctnTblCrtLog extTblLog = new LOpExtnlAsctnTblCrtLog();
                    extTblLog.setExtnlAsctnTblNm(tableNm);
                    extTblLog.setOccurDt(occurDt);
                    extTblLog.setAddOccurDt(addOccurDt);
                    extTblLog.setOccurCycl(duration);
                    if(prsExtTable == null) {
                        lOpExtnlAsctnTblCrtLogMapper.insert(extTblLog);
                    }else{
                        lOpExtnlAsctnTblCrtLogMapper.update(extTblLog);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            isDo = false;
        }
    }


}

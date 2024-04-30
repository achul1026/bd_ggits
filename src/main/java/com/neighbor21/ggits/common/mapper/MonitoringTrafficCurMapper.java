package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;

/**
 * 모니터링 교통현황 매퍼
 *
 * @author : Charles Kim
 * @fileName :  MonitoringTrafficCurMapper
 * @since : 2023-12-23
 */
@Mapper
public interface MonitoringTrafficCurMapper {


    /*시간대별 교통량 */
    List<MonitoringTrafficCurDto> findAllTrafficVolumeByVDSForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllTrafficVolumeBySmartForChart(@Param("timeType") String timeType);

    /*스마트교차로 방향별 - 시군구 그룹*/
    List<MonitoringTrafficCurDto> findAllTrafficVolumeBySmartDrctGroupByMngInstCdForChart(@Param("timeType") String timeType);

    /*스마트교차로 방향별 - 차종별 그룹*/
    List<MonitoringTrafficCurDto> findAllTrafficVolumeBySmartDrctGroupByVhclDivForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllTrafficVolumeByDSRCForChart(@Param("timeType") String timeType);




    /*시간대별 평균속도 */
    List<MonitoringTrafficCurDto> findAllAvgSpeedByVDSForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllAvgSpeedBySmartForChart(@Param("timeType") String timeType);

    /*스마트교차로 방향별 - 시군구 그룹*/
    List<MonitoringTrafficCurDto> findAllAvgSpeedBySmartDrctGroupByMngInstCdForChart(@Param("timeType") String timeType);

    /*스마트교차로 방향별 - 차종별 그룹*/
    List<MonitoringTrafficCurDto> findAllAvgSpeedBySmartDrctGroupByVhclDivForChart(@Param("timeType") String timeType);

    List<MonitoringTrafficCurDto> findAllAvgSpeedByDSRCForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllAvgSpeedVDSBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedVDSBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);


    List<MonitoringTrafficCurDto> findAllAvgSpeedDSRCBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedDSRCBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);


    List<MonitoringTrafficCurDto> findAllAvgSpeedSmartBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedSmartBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> findAllTrafficVolumeVDSBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countTrafficVolumeVDSBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> findAllTrafficVolumeDSRCBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countTrafficVolumeDSRCBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> findAllTrafficVolumeSmartBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countTrafficVolumeSmartBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> getVolumeSmartForGIS();
    List<MonitoringTrafficCurDto> getVolumeSmartDcrtForGIS();


    List<MonitoringTrafficCurDto> getVolumeVDSForGIS();

    List<MonitoringTrafficCurDto> findOneCumulativeTrafficVolumeByVhclDiv();
}

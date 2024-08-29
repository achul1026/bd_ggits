package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

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



    /*시간대별 평균속도 */
    List<MonitoringTrafficCurDto> findAllAvgSpeedByVDSForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllAvgSpeedBySmartForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllAvgSpeedByDSRCForChart(@Param("timeType") String timeType);


    List<MonitoringTrafficCurDto> findAllAvgSpeedVDSBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedVDSBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);


    List<MonitoringTrafficCurDto> findAllAvgSpeedDSRCBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedDSRCBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);


    List<MonitoringTrafficCurDto> findAllAvgSpeedSmartBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countAvgSpeedSmartBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> findAllTrafficVolumeVDSBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countTrafficVolumeVDSBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    List<MonitoringTrafficCurDto> findAllTrafficVolumeSmartBySearchOptionPaging(MapMonitoringMenuDTO mapMonitoringMenuDTO);

    Integer countTrafficVolumeSmartBySearchOption(MapMonitoringMenuDTO mapMonitoringMenuDTO);
}

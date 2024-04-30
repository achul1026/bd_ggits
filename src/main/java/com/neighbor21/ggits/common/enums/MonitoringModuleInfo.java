package com.neighbor21.ggits.common.enums;

/**
 * 모니터링 모듈 정보 (MDM <-모니터링 ,MDV <-VIP)
 * @author KY.LEE
 *
 */
public enum MonitoringModuleInfo {
	DASHBOARD_SMCRD_TOP10("MDM000","dashboard_smcrd_top10"),
	DASHBOARD_EMERG_BY_MNGINSTCD("MDM001","dashboard_emerg_by_mnginstcd"),
	DASHBOARD_WARNING_BY_MNGINSTCD("MDM002","dashboard_warning_by_mnginstcd"),
	DASHBOARD_SVC_CONGESTION_TOP10("MDM003","dashboard_svc_congestion_top10"),
	DASHBOARD_ACDNT_PREDICTION_TOP10("MDM004","dashboard_acdnt_prediction_top10"),
	DASHBOARD_BUS_STATION_USAGE("MDM005","dashboard_bus_station_usage"),
	DASHBOARD_POPULATION_PREDICTION_TOP10("MDM006","dashboard_population_prediction_top10"),
	DASHBOARD_EMERG_ACHEIVE_PTG("MDM007","dashboard_emerg_acheive_ptg");
	
	private String code;
	private String name;
	
	private MonitoringModuleInfo(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static String getCodeName(String code) {
		for(MonitoringModuleInfo monitoringModuleInfo : MonitoringModuleInfo.values()) {
			if(monitoringModuleInfo.code.equals(code)) {
				return monitoringModuleInfo.name;
			}
		}
		return null;
	}

	public static MonitoringModuleInfo getMonitoringModuleInfo(String code) {
		for(MonitoringModuleInfo monitoringModuleInfo : MonitoringModuleInfo.values()) {
			if(monitoringModuleInfo.code.equals(code)) {
				return monitoringModuleInfo;
			}
		}
		return null;
	}
}

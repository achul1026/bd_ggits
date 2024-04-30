package com.neighbor21.ggits.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum LinkedTableInfo {
	
	//지자체 연계
	ADSI("adsi","지차제 연계", ServerMngType.LOC_GOVMNT_LINKAGE),
	GGITS("ggits","지차제 연계", ServerMngType.LOC_GOVMNT_LINKAGE),
	GGDTDR("ggdtdr","지차제 연계", ServerMngType.LOC_GOVMNT_LINKAGE),
	SLOPEN("slopen","지차제 연계", ServerMngType.LOC_GOVMNT_LINKAGE),
	
	//외부기간연계
	AIRKR("airkr","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	GBMS("gbms","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	GGBIS("ggbis","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	GGSPL("ggspl","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	GIMS("gims","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	KMA("kma","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	KOSTAT("kostat","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	TAAS("taas","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	TS("ts","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	UTIC("utic","외부 기간 연계", ServerMngType.EXTERNAL_LINKAGE),
	
	//빅데이터 플랫폼
	KT("kt","빅데이터 저장 플랫폼", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
	MRT("mrt","빅데이터 저장 플랫폼", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
	MOIS("mois","빅데이터 저장 플랫폼", ServerMngType.BIG_DATA_STORAGE_PLATFORM);
	
	
	//2024.01.12 최신화 아래 하드코딩
//	ADSI_SMCRSRD_VHCL_DATA("adsi_smcrsrd_vhcl_data","스마트교차로 차량 데이터", ServerMngType.LOC_GOVMNT_LINKAGE),
//	ADSI_VDS_COLCT_INFO("adsi_vds_colct_info","VDS 수집정보",ServerMngType.LOC_GOVMNT_LINKAGE),
//	AIRKR_ARQLT_IDEX_DNGR_MSRMT_POINT("airkr_arqlt_idex_dngr_msrmt_point","대기질 지수 위험 측정 지정정보", ServerMngType.EXTERNAL_LINKAGE),
//	AIRKR_ARQLT_MSRMT_FRCST_NTC("airkr_arqlt_msrmt_frcst_ntc","대기실 예보 공지", ServerMngType.EXTERNAL_LINKAGE),
//	AIRKR_MSRMT_POINT_ARQLT_INFO("airkr_msrmt_point_arqlt_info","측정 지점 대기질 정보", ServerMngType.EXTERNAL_LINKAGE),
//	AIRKR_ULFPTC_WEEK_FRCST("airkr_ulfptc_week_frcst","초미세먼지 주간 예보", ServerMngType.EXTERNAL_LINKAGE),
//	EX_FTNMIN_SCTN_TRFVLM("ex_ftnmin_sctn_trfvlm","15분 구간 교통량", ServerMngType.EXTERNAL_LINKAGE),
//	GBMS_BUS_ROUTE_PASNR_LOG("gbms_bus_route_pasnr_log","버스 노선 승하차 이력", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	GBMS_BUS_STATION_TRAFFIC_CARD_DW("gbms_bus_station_traffic_card_dw","정류소 od 정보", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	GBMS_BUS_USE_CALC_INFO("gbms_bus_use_calc_info","승하차 수익 정보", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	GGDTDR_BCYCL_PRVUSE_ROAD_INFO("ggdtdr_bcycl_prvuse_road_info","자전거 전용 도로 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_BUS_PRVUSE_LANE_INFO("ggdtdr_bus_prvuse_lane_info","버스 전용 차로 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_BUS_STTN_INFO("ggdtdr_bus_sttn_info","버스 정류소 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_GH_BLDLND_DEV_BIZ_INFO("ggdtdr_gh_bldlnd_dev_biz_info","경기주택공사 택지 개발 사업 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_GH_INDUST_HSMP_MAKE("ggdtdr_gh_indust_hsmp_make","경기주택공사 산업 단지 조성", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_LNDSKUL_INFO("ggdtdr_lndskul_info","학교용지 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_PBTRNSP_TRSFR_FCLTS_INFO("ggdtdr_pbtrnsp_trsfr_fclts_info","대중교통 환승 시설물 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_PKPLC_INFO("ggdtdr_pkplc_info","주차장 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_PUBLIC_BCYCL_OPRT_INFO("ggdtdr_public_bcycl_oprt_info","공공 자전거 운영 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_ROAD_ROUTE_LEN_INFO("ggdtdr_road_route_len_info","도로 노선 길이 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_SCHL_CLASS_CNT_INFO("ggdtdr_schl_class_cnt_info","학교 학급 수 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_SCHL_SKLSTF_INFO("ggdtdr_schl_sklstf_info","학교 교직원 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_SCHL_STDNT_CNT_INFO("ggdtdr_schl_stdnt_cnt_info","학교 학생 수 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_SCHZN_INFO("ggdtdr_schzn_info","어린이보호구역 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_TAXI_LCNS_ISSN_INFO("ggdtdr_taxi_lcns_issn_info","택시 면허 발급 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_TRIT_LNDCGR_STATS_INFO("ggdtdr_trit_lndcgr_stats_info","국토 지목 통계 정보", ServerMngType.EXTERNAL_LINKAGE),
//	GGDTDR_VHCL_REGIST_CNT_INFO("ggdtdr_vhcl_regist_cnt_info","자동차 등록 대수 정보", ServerMngType.EXTERNAL_LINKAGE),
//	KASI_TEMP1("kasi_temp1","", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	KASI_TEMP2("kasi_temp2","", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	KMA_SHTRM_WTHR_FRCST("kma_shtrm_wthr_frcst","단기 기상 예보", ServerMngType.EXTERNAL_LINKAGE),
//	KMA_ULTR_SHTRM_WTHR_ACTL("kma_ultr_shtrm_wthr_actl","초단기 기상 실황", ServerMngType.EXTERNAL_LINKAGE),
//	KT_TIMEZN("kt_timezn","KT 시간단위 유동인구 정보", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	KT_WEEKDAYS("kt_weekdays","KT 요일단위 유동인구 정보", ServerMngType.BIG_DATA_STORAGE_PLATFORM),
//	KOSTAT_POPLTN_STATS_INFO("kostat_popltn_stats_info","인구 통계 정보", ServerMngType.EXTERNAL_LINKAGE),
//	MOIS_ADDNG_RRNO_POPLTN_HSHHLD_INFO("mois_addng_rrno_popltn_hshhld_info","행정동 주민등록번호 인구 세대 정보", ServerMngType.EXTERNAL_LINKAGE),
//	SLOPEN_SEOUL_SUBWAY_STTN_USE_INFO("slopen_seoul_subway_sttn_use_info","서울 지하철 역사 사용 정보", ServerMngType.EXTERNAL_LINKAGE),
//	SCS_EMRG_VHCL_CRSRD_INFO("scs_emrg_vhcl_crsrd_info","긴급차량 교차로 정보",ServerMngType.SIGNAL_LINKAGE),
//	SCS_L_EVC_EVENT("scs_l_evc_event","이벤트이력_긴급",ServerMngType.SIGNAL_LINKAGE),
//	SCS_L_EVC_SERVICE("scs_l_evc_service","서비스이력_긴급",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_CON_INTDPLAN("scs_t_con_intdplan","교차로일계획_연계",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_CON_INTFLOW("scs_t_con_intflow","교차로이동류_연계",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_CON_INTFPLAN("scs_t_con_intfplan","교차로예약계획_연계",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_CON_INTLC("scs_t_con_intlc","교차로구성_연계",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_INTSIGMAP("scs_t_intsigmap","교차로시그널맵_연계",ServerMngType.SIGNAL_LINKAGE),
//	SCS_T_CON_INTWEEK("scs_t_con_intweek","교차로주간계획_연계",ServerMngType.SIGNAL_LINKAGE),
//	TAAS_ADSI_ACDNT_DSTRCT("taas_adsi_acdnt_dstrct","행정시 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_BCYCL_ACDNT_DSTRCT("taas_bcycl_acdnt_dstrct","자전거 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_DRNKG_ACDNT_DSTRCT("taas_drnkg_acdnt_dstrct","음주 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_FROST_ACDNT_DSTRCT("taas_frost_acdnt_dstrct","결빙 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_HGHW_SCTN_ROAD_DNGR_ANLS("taas_hghw_sctn_road_dngr_anls","고속도로 구간 도로 위험 분석", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_HLDY_PERIOD_ACDNT_DSTRCT("taas_hldy_period_acdnt_dstrct","휴일 기간 보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_JAYWK_PDST_ACDNT_DSTRCT("taas_jaywk_pdst_acdnt_dstrct","무단횡단 보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_LAW_VLTN_PDST_ACDNT_DSTRCT("taas_law_vltn_pdst_acdnt_dstrct","법 위반 보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_LINKAREA_ACDNT_DSTRCT("taas_linkarea_acdnt_dstrct","링크기반 사고 위험 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_PDSN_ACDNT_DSTRCT("taas_pdsn_acdnt_dstrct","보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_PDST_CHID_ACDNT_DSTRCT("taas_pdst_chid_acdnt_dstrct","어린이 보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_PDST_OLMAN_ACDNT_DSTRCT("taas_pdst_olman_acdnt_dstrct","노인 보행자 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_SCHZN_CHID_ACDNT_DSTRCT("taas_schzn_chid_acdnt_dstrct","어린이보호구역 어린이 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_TRUCK_ACDNT_DSTRCT("taas_truck_acdnt_dstrct","화물차 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TAAS_TWHLVH_ACDNT_DSTRCT("taas_twhlvh_acdnt_dstrct","이륜차 사고 구역", ServerMngType.EXTERNAL_LINKAGE),
//	TS_DTG_RUNG_DATA("ts_dtg_rung_data","DTG 운행 기록 데이터", ServerMngType.EXTERNAL_LINKAGE),
//	UTIC_ROAD_DNGR_STTS_FRCST("utic_road_dngr_stts_frcst","도로 위험 상태 예보", ServerMngType.BIG_DATA_STORAGE_PLATFORM);
	
	private String code;
	private String name;
	private ServerMngType serverMngType;
	
	private LinkedTableInfo(String code, String name,ServerMngType serverMngType) {
		this.code = code;
		this.name = name;
		this.serverMngType = serverMngType;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public ServerMngType getServerMngType() {
		return serverMngType;
	}
	
	public static List<String> getLinkedTableInfoList(ServerMngType serverMngType) {
		List<String> resultList = new ArrayList<String>();
		for(LinkedTableInfo r : LinkedTableInfo.values()) {
			if(r.serverMngType.equals(serverMngType)) {
				resultList.add(r.code);
			}
		}
		return resultList;
	}
	
	public static String getCodeName(String code) {
		for(LinkedTableInfo info : LinkedTableInfo.values()) {
			if(info.getCode().equals(code)) {
				return info.name;
			}
		}
		return null;
	}
}

package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 매 1분마다 가공된 소통정보를 누적한다.
public class ExtGgitsLinkStd1m {
	private Timestamp proc_date;		// 1분 소통정보가공시각
	private String link_id;				// 링크를 고유키로 식별하기 위해 생성 도로구분(1자리) + 링크(1자리) + 방향(1자리) + 행정구역(2자리) + 일련번호(4자리) 예) ALW100001
	private long spd;					// 1분 동안의 링크의 평균속도
	private long vol;					// 교통량
	private long dens;					// 밀도
	private long trvl_time;				// 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private long link_delaytime;		// 링크 지체시간
	private long q_len;					// 대기 길이
	private long occ;					// 점유율
	private String cong_grade;			// 링크별 속도에 따른 혼잡도구분(소통원활/지체서행/정체)
	private String dow_cd;				// 요일구분 (일주일+특별휴일)
	private String weath_infl_cd;		// 수집일의 기상상태 구분
	private String tfinfo_cre_tp_cd;	// 소통정보생성유형코드(01:운영자, 02:VDS, 03:프로브, 04:외부연계정보, 05:VDS+외부연계, 06:프로브+외부연계, 07: VDS+프로브, 08:VDS+프로브+외부연계, 09:VDS+프로브패턴, 10:VDS+프로브패턴+외부연계, 11:VDS패턴+프로브, 12:VDS패턴+프로브+외부연계, 13:외부연계+VDS패턴+프로브패턴, 14:전시점링크소통정보, 15:링크패턴자료, 16:링크패턴+외부연계, 17:BUS)
	private String recurrent_yn;		// 반복정체 여부
	private String org_code;			// 생성기관코드
	private Timestamp coll_date;		// 수집일시(소통정보 갱신 시각
	public Timestamp getProc_date() {
		return proc_date;
	}
	public void setProc_date(Timestamp proc_date) {
		this.proc_date = proc_date;
	}
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public long getSpd() {
		return spd;
	}
	public void setSpd(long spd) {
		this.spd = spd;
	}
	public long getVol() {
		return vol;
	}
	public void setVol(long vol) {
		this.vol = vol;
	}
	public long getDens() {
		return dens;
	}
	public void setDens(long dens) {
		this.dens = dens;
	}
	public long getTrvl_time() {
		return trvl_time;
	}
	public void setTrvl_time(long trvl_time) {
		this.trvl_time = trvl_time;
	}
	public long getLink_delaytime() {
		return link_delaytime;
	}
	public void setLink_delaytime(long link_delaytime) {
		this.link_delaytime = link_delaytime;
	}
	public long getQ_len() {
		return q_len;
	}
	public void setQ_len(long q_len) {
		this.q_len = q_len;
	}
	public long getOcc() {
		return occ;
	}
	public void setOcc(long occ) {
		this.occ = occ;
	}
	public String getCong_grade() {
		return cong_grade;
	}
	public void setCong_grade(String cong_grade) {
		this.cong_grade = cong_grade;
	}
	public String getDow_cd() {
		return dow_cd;
	}
	public void setDow_cd(String dow_cd) {
		this.dow_cd = dow_cd;
	}
	public String getWeath_infl_cd() {
		return weath_infl_cd;
	}
	public void setWeath_infl_cd(String weath_infl_cd) {
		this.weath_infl_cd = weath_infl_cd;
	}
	public String getTfinfo_cre_tp_cd() {
		return tfinfo_cre_tp_cd;
	}
	public void setTfinfo_cre_tp_cd(String tfinfo_cre_tp_cd) {
		this.tfinfo_cre_tp_cd = tfinfo_cre_tp_cd;
	}
	public String getRecurrent_yn() {
		return recurrent_yn;
	}
	public void setRecurrent_yn(String recurrent_yn) {
		this.recurrent_yn = recurrent_yn;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public Timestamp getColl_date() {
		return coll_date;
	}
	public void setColl_date(Timestamp coll_date) {
		this.coll_date = coll_date;
	}

	
}

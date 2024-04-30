package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsEmrgVhclCrsrdVhclLog {
	private Timestamp collectdt; // 수집일시
	private String serviceid; // 서비스 id
	private long intlcno; // 교차로 번호
	private String evno; // 차량 번호

	public Timestamp getCollectdt() {
		return collectdt;
	}

	public void setCollectdt(Timestamp collectdt) {
		this.collectdt = collectdt;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public long getIntlcno() {
		return intlcno;
	}

	public void setIntlcno(long intlcno) {
		this.intlcno = intlcno;
	}

	public String getEvno() {
		return evno;
	}

	public void setEvno(String evno) {
		this.evno = evno;
	}

}

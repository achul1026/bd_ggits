package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class EqpLogDTO {
	private String mngInstCd;
	private Timestamp clctDt;
	private String Id;
	private String Stts;
	private String name;
	private String type;
	public String getMngInstCd() {
		return mngInstCd;
	}
	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}
	public Timestamp getClctDt() {
		return clctDt;
	}
	public void setClctDt(Timestamp clctDt) {
		this.clctDt = clctDt;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getStts() {
		return Stts;
	}
	public void setStts(String stts) {
		Stts = stts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}

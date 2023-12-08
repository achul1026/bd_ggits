package com.neighbor21.ggits.common.enums;

public enum OprtrGrd {
	
	SPUER_ADMIN("SUPER","최상위 관리자"),
	GENERAL_ADMIN("GENERAL","일반 관리자");
	
	private String grade; 
	private String name;
	
	private OprtrGrd(String grade, String name) {
		this.grade = grade;
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}
}

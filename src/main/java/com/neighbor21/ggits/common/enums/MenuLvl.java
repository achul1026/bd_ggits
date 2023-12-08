package com.neighbor21.ggits.common.enums;

public enum MenuLvl {
	MAIN_MENU("메인메뉴",0L),
	SUB_MENU("서브메뉴",1L);
	
	private String name; 
	private Long level;
	
	private MenuLvl(String name, Long level) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	public Long getLevel() {
		return level;
	}
}

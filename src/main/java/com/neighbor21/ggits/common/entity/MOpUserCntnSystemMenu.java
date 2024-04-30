package com.neighbor21.ggits.common.entity;

/**
 * 설명 : 사용자별 접속 시스템 메뉴
 * @date : 2023-12-27
 * @author KY.LEE
 *
 */
public class MOpUserCntnSystemMenu {
	
    Long oprtrId;
    String cntnSystemCd;
    String useYn;

    //파라미터 전용
    String[] cntnSystemCdArr;
    
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getCntnSystemCd() {
		return cntnSystemCd;
	}
	public void setCntnSystemCd(String cntnSystemCd) {
		this.cntnSystemCd = cntnSystemCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String[] getCntnSystemCdArr() {
		return cntnSystemCdArr;
	}
	public void setCntnSystemCdArr(String[] cntnSystemCdArr) {
		this.cntnSystemCdArr = cntnSystemCdArr;
	}
}

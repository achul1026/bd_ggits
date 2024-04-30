package com.neighbor21.ggits.common.entity;

//마스터 운영 메뉴
public class MOpMenu {

    private String    menuId;        //메뉴 아이디
    private String    menuNm;        //메뉴 명
    private long    menuLvl;        //메뉴 레벨
    private String    upperMenuId;        //상위 메뉴 아이디
    private String    useYn;        //사용 여부
    private String    asctnInfo;        //연계 정보
    private String    descr;        //설명
    private long    sortNo;        //정렬 번호
    private String    urlPttrn;        //URL 패턴
    private String    urlAddr;        //URL 주소
    private long    sbmnuSortNo;        //서브메뉴 정렬 번호
    private String categCd;				//메뉴 카테고리 코드
    private String menuPttrnType; 		//메뉴패턴타입
    private String cdDivCd;				//코드 구분 코드
    //#MOpAuthority
    private long authId; //권한Id
    private String bttnCd; //버튼코드

    //컬럼 x
    private String[] menuPttrnTypeArr; //메뉴패턴타입 Array
    
    public MOpMenu(String menuId) {
		super();
		this.menuId = menuId;
	}

    public MOpMenu() {
		super();
	}

    public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }


  public String getMenuNm() {
    return menuNm;
  }

  public void setMenuNm(String menuNm) {
    this.menuNm = menuNm;
  }


  public long getMenuLvl() {
    return menuLvl;
  }

  public void setMenuLvl(long menuLvl) {
    this.menuLvl = menuLvl;
  }


  public String getUpperMenuId() {
    return upperMenuId;
  }

  public void setUpperMenuId(String upperMenuId) {
    this.upperMenuId = upperMenuId;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }


  public String getAsctnInfo() {
    return asctnInfo;
  }

  public void setAsctnInfo(String asctnInfo) {
    this.asctnInfo = asctnInfo;
  }


  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }


  public long getSortNo() {
    return sortNo;
  }

  public void setSortNo(long sortNo) {
    this.sortNo = sortNo;
  }


  public String getUrlPttrn() {
    return urlPttrn;
  }

  public void setUrlPttrn(String urlPttrn) {
    this.urlPttrn = urlPttrn;
  }


  public String getUrlAddr() {
    return urlAddr;
  }

  public void setUrlAddr(String urlAddr) {
    this.urlAddr = urlAddr;
  }


  public long getSbmnuSortNo() {
    return sbmnuSortNo;
  }

  public void setSbmnuSortNo(long sbmnuSortNo) {
    this.sbmnuSortNo = sbmnuSortNo;
  }

  public long getAuthId() {
	return authId;
  }

  public void setAuthId(long authId) {
	this.authId = authId;
  }
  
  public String getCategCd() {
	return categCd;
  }

  public void setCategCd(String categCd) {
	this.categCd = categCd;
  }

  public String getBttnCd() {
	return bttnCd;
  }

  public void setBttnCd(String bttnCd) {
	this.bttnCd = bttnCd;
  }
  
  

  public String getMenuPttrnType() {
	return menuPttrnType;
  }

  public void setMenuPttrnType(String menuPttrnType) {
	this.menuPttrnType = menuPttrnType;
  }

  public String getCdDivCd() {
	return cdDivCd;
  }

  public void setCdDivCd(String cdDivCd) {
	this.cdDivCd = cdDivCd;
  }
  
  

  public String[] getMenuPttrnTypeArr() {
    String[] menuPttrnTypeArrCopy = null;
    if(this.menuPttrnTypeArr != null) {
      menuPttrnTypeArrCopy = new String[menuPttrnTypeArr.length];
      for (int i = 0; i < menuPttrnTypeArr.length; i++) {
        menuPttrnTypeArrCopy[i] = this.menuPttrnTypeArr[i];
      }
    } 
    return menuPttrnTypeArrCopy;
  }

  public void setMenuPttrnTypeArr(String[] menuPttrnTypeArr) {
    if(menuPttrnTypeArr != null) {
      this.menuPttrnTypeArr = new String[menuPttrnTypeArr.length];
      for (int i = 0; i <menuPttrnTypeArr.length; i++) {
        this.menuPttrnTypeArr[i] = menuPttrnTypeArr[i];
      }
    }
  }

  @Override
  public String toString() {
	return "MOpMenu [menuId=" + menuId + ", menuNm=" + menuNm + ", menuLvl=" + menuLvl + ", upperMenuId=" + upperMenuId
			+ ", useYn=" + useYn + ", asctnInfo=" + asctnInfo + ", descr=" + descr + ", sortNo=" + sortNo
			+ ", urlPttrn=" + urlPttrn + ", urlAddr=" + urlAddr + ", sbmnuSortNo=" + sbmnuSortNo + ", categCd="
			+ categCd + ", menuPttrnType=" + menuPttrnType + ", cdDivCd=" + cdDivCd + ", authId=" + authId + ", bttnCd="
			+ bttnCd + ", getMenuId()=" + getMenuId() + ", getMenuNm()=" + getMenuNm() + ", getMenuLvl()="
			+ getMenuLvl() + ", getUpperMenuId()=" + getUpperMenuId() + ", getUseYn()=" + getUseYn()
			+ ", getAsctnInfo()=" + getAsctnInfo() + ", getDescr()=" + getDescr() + ", getSortNo()=" + getSortNo()
			+ ", getUrlPttrn()=" + getUrlPttrn() + ", getUrlAddr()=" + getUrlAddr() + ", getSbmnuSortNo()="
			+ getSbmnuSortNo() + ", getAuthId()=" + getAuthId() + ", getCategCd()=" + getCategCd() + ", getBttnCd()="
			+ getBttnCd() + ", getMenuPttrnType()=" + getMenuPttrnType() + ", getCdDivCd()=" + getCdDivCd()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
  }
}

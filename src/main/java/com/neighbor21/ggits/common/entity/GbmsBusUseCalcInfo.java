package com.neighbor21.ggits.common.entity;

public class GbmsBusUseCalcInfo {
    private String clctYmd;        //수집 일자
    private String coId;        //회사 아이디
    private String coNm;        //회사 명
    private String busRouteId;        //버스 노선 아이디
    private String busRouteNo;        //버스 노선 번호
    private String cardApprType;        //카드 결재 유형
    private Long busUserCnt;        //버스 사용자 수
    private Long busUseTotAmt;        //버스 사용 전체 금액
    private Long card1; // 선불카드
    private Long card2; // 후불카드
    private Long card3; // 조합카드
    private Long etc; // 기타

    private Long user1; // 선불카드
    private Long user2; // 후불카드
    private Long user3; // 조합카드
    private Long userEtc; // 기타

    private String routeTy;
    private String routeId;
    private String districtGnm;
    private String districtSnm;

    public String getClctYmd() {
        return clctYmd;
    }

    public void setClctYmd(String clctYmd) {
        this.clctYmd = clctYmd;
    }


    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }


    public String getCoNm() {
        return coNm;
    }

    public void setCoNm(String coNm) {
        this.coNm = coNm;
    }


    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }


    public String getBusRouteNo() {
        return busRouteNo;
    }

    public void setBusRouteNo(String busRouteNo) {
        this.busRouteNo = busRouteNo;
    }


    public String getCardApprType() {
        return cardApprType;
    }

    public void setCardApprType(String cardApprType) {
        this.cardApprType = cardApprType;
    }


    public Long getBusUserCnt() {
        return busUserCnt;
    }

    public void setBusUserCnt(Long busUserCnt) {
        this.busUserCnt = busUserCnt;
    }


    public Long getBusUseTotAmt() {
        return busUseTotAmt;
    }

    public void setBusUseTotAmt(Long busUseTotAmt) {
        this.busUseTotAmt = busUseTotAmt;
    }

    public String getRouteTy() {
        return routeTy;
    }

    public void setRouteTy(String routeTy) {
        this.routeTy = routeTy;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Long getCard1() {
        return card1;
    }

    public void setCard1(Long card1) {
        this.card1 = card1;
    }

    public Long getCard2() {
        return card2;
    }

    public void setCard2(Long card2) {
        this.card2 = card2;
    }

    public Long getCard3() {
        return card3;
    }

    public void setCard3(Long card3) {
        this.card3 = card3;
    }

    public Long getEtc() {
        return etc;
    }

    public void setEtc(Long etc) {
        this.etc = etc;
    }

    public Long getUser1() {
        return user1;
    }

    public void setUser1(Long user1) {
        this.user1 = user1;
    }

    public Long getUser2() {
        return user2;
    }

    public void setUser2(Long user2) {
        this.user2 = user2;
    }

    public Long getUser3() {
        return user3;
    }

    public void setUser3(Long user3) {
        this.user3 = user3;
    }

    public Long getUserEtc() {
        return userEtc;
    }

    public void setUserEtc(Long userEtc) {
        this.userEtc = userEtc;
    }

    public String getDistrictGnm() {
        return districtGnm;
    }

    public void setDistrictGnm(String districtGnm) {
        this.districtGnm = districtGnm;
    }

    public String getDistrictSnm() {
        return districtSnm;
    }

    public void setDistrictSnm(String districtSnm) {
        this.districtSnm = districtSnm;
    }
}

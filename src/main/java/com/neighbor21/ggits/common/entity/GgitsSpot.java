package com.neighbor21.ggits.common.entity;
public class GgitsSpot {
    private String    spotId;        //지점 id
    private String    linkId;        //링크 아이디
    private String    spotNm;        //지점명. 지점이 설치되어 있는 주소
    private String    ustreamSpotId;        //상류지점 id
    private String    dstreamSpotId;        //하류지점id
    private long    spotLanecnt;        //지점 차로수. 지점이 가지고 있는 총 차로수
    private long    detectLanecnt;        //검지기 차로수. 지점에 설치되어 있는 vds로 검지할 수 있는 차로수
    private String    vdsKind;        //검지기 종류
    private String    linkTrafficvolRepresYn;        //링크 교통량 대표여부
    private long    desId;        //des_id
    private long    apidId;        //apid_id
    private long    mcmaId;        //mcma_id
    private long    seqInLink;        //seq_in_link
    private long    spotDist;        //spot_dist


  public String getSpotId() {
    return spotId;
  }

  public void setSpotId(String spotId) {
    this.spotId = spotId;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getSpotNm() {
    return spotNm;
  }

  public void setSpotNm(String spotNm) {
    this.spotNm = spotNm;
  }


  public String getUstreamSpotId() {
    return ustreamSpotId;
  }

  public void setUstreamSpotId(String ustreamSpotId) {
    this.ustreamSpotId = ustreamSpotId;
  }


  public String getDstreamSpotId() {
    return dstreamSpotId;
  }

  public void setDstreamSpotId(String dstreamSpotId) {
    this.dstreamSpotId = dstreamSpotId;
  }


  public long getSpotLanecnt() {
    return spotLanecnt;
  }

  public void setSpotLanecnt(long spotLanecnt) {
    this.spotLanecnt = spotLanecnt;
  }


  public long getDetectLanecnt() {
    return detectLanecnt;
  }

  public void setDetectLanecnt(long detectLanecnt) {
    this.detectLanecnt = detectLanecnt;
  }


  public String getVdsKind() {
    return vdsKind;
  }

  public void setVdsKind(String vdsKind) {
    this.vdsKind = vdsKind;
  }


  public String getLinkTrafficvolRepresYn() {
    return linkTrafficvolRepresYn;
  }

  public void setLinkTrafficvolRepresYn(String linkTrafficvolRepresYn) {
    this.linkTrafficvolRepresYn = linkTrafficvolRepresYn;
  }


  public long getDesId() {
    return desId;
  }

  public void setDesId(long desId) {
    this.desId = desId;
  }


  public long getApidId() {
    return apidId;
  }

  public void setApidId(long apidId) {
    this.apidId = apidId;
  }


  public long getMcmaId() {
    return mcmaId;
  }

  public void setMcmaId(long mcmaId) {
    this.mcmaId = mcmaId;
  }


  public long getSeqInLink() {
    return seqInLink;
  }

  public void setSeqInLink(long seqInLink) {
    this.seqInLink = seqInLink;
  }


  public long getSpotDist() {
    return spotDist;
  }

  public void setSpotDist(long spotDist) {
    this.spotDist = spotDist;
  }

}

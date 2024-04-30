package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

/**
 *
 * @author : Charles Kim
 * @fileName :  LOpExtnlAsctnTblCrtLog
 * @since : 2023-11-16
 */
public class LOpExtnlAsctnTblCrtLog {
    String extnlAsctnTblNm;
    Timestamp occurDt;
    Timestamp addOccurDt;
    Long occurCycl;

    public String getExtnlAsctnTblNm() {
        return extnlAsctnTblNm;
    }

    public void setExtnlAsctnTblNm(String extnlAsctnTblNm) {
        this.extnlAsctnTblNm = extnlAsctnTblNm;
    }

    public Timestamp getOccurDt() {
        return occurDt;
    }

    public void setOccurDt(Timestamp occurDt) {
        this.occurDt = occurDt;
    }

    public Timestamp getAddOccurDt() {
        return addOccurDt;
    }

    public void setAddOccurDt(Timestamp addOccurDt) {
        this.addOccurDt = addOccurDt;
    }

    public Long getOccurCycl() {
        return occurCycl;
    }

    public void setOccurCycl(Long occurCycl) {
        this.occurCycl = occurCycl;
    }
}

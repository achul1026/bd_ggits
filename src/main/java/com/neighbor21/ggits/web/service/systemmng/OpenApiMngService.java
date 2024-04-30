package com.neighbor21.ggits.web.service.systemmng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.OpenApiPvsnLog;
import com.neighbor21.ggits.common.mapper.MOpOpenApiInfoMapper;
import com.neighbor21.ggits.common.mapper.OpenApiPvsnLogMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class OpenApiMngService {


  @Autowired
  OpenApiPvsnLogMapper openApiPvsnLogMapper;

  @Autowired
  MOpOpenApiInfoMapper mOpOpenApiInfoMapper;

  public List<MOpOpenApiInfo> getOpenApiInfoList(MOpOpenApiInfo mOpOpenApiInfo) {
    if (!GgitsCommonUtils.isNull(mOpOpenApiInfo.getSearchType())
        && !"all".equals(mOpOpenApiInfo.getSearchType())) {
      if (!GgitsCommonUtils.isNull(mOpOpenApiInfo.getSearchContent())) {
        String searchContent = String.valueOf(mOpOpenApiInfo.getSearchContent());
        switch (mOpOpenApiInfo.getSearchType()) {
          case "apiNm":
            mOpOpenApiInfo.setApiNm(searchContent);
            break;
          case "oprtrNm":
            mOpOpenApiInfo.setOprtrNm(searchContent);
            break;
          case "mngInstNm":
            mOpOpenApiInfo.setMngInstNm(searchContent);
            break;
        }
      }
    }
    return mOpOpenApiInfoMapper.findAllMOpOpenApiInfo(mOpOpenApiInfo);
  };

  public MOpOpenApiInfo getOpenApiInfoDetail(String dsetId) {
    return mOpOpenApiInfoMapper.findOneByDsetId(dsetId);
  };

  public String saveMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo) {
    String dsetId = GgitsCommonUtils.getUuid();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Date now = new Date();
    String nowStr = format.format(now);

    MOpOperator loginInfo = LoginSessionUtils.getMOpOperatorInfo();

    mOpOpenApiInfo.setMngInstCd(loginInfo.getMngInstCd());
    mOpOpenApiInfo.setOprtrId(loginInfo.getOprtrId());
    mOpOpenApiInfo.setDsetId(dsetId);
    mOpOpenApiInfo.setMaxPvsnCnt(0);
    mOpOpenApiInfo.setDataRegistYmd(nowStr);
    mOpOpenApiInfo.setDataUpdtYmd(nowStr);
    mOpOpenApiInfoMapper.saveMOpOpenApiInfo(mOpOpenApiInfo);
    return dsetId;
  };

  public void updateMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo) {

    MOpOpenApiInfo dbMOpOpenApiInfo =
        mOpOpenApiInfoMapper.findOneByDsetId(mOpOpenApiInfo.getDsetId());

    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Date now = new Date();
    String nowStr = format.format(now);

    if (dbMOpOpenApiInfo.getOprtrId().equals(LoginSessionUtils.getOprtrId())) {
      dbMOpOpenApiInfo.setApiNm(mOpOpenApiInfo.getApiNm());
      dbMOpOpenApiInfo.setContents(mOpOpenApiInfo.getContents());
      dbMOpOpenApiInfo.setDescr(mOpOpenApiInfo.getDescr());
      dbMOpOpenApiInfo.setApiCallUrl(mOpOpenApiInfo.getApiCallUrl());
      dbMOpOpenApiInfo.setDataUpdtYmd(nowStr);
      mOpOpenApiInfoMapper.updateMOpOpenApiInfo(dbMOpOpenApiInfo);
    } else {
      throw new CommonException(ErrorCode.USER_MISMATCH, "OPEN API는 작성자만 수정이 가능합니다.");
    }
  };

  public void deleteMOpOpenApiInfo(MOpOpenApiInfo mOpOpenApiInfo){
	  mOpOpenApiInfoMapper.deleteMOpOpenApiInfo(mOpOpenApiInfo);
  }
}

package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.TaasLawVltnPdstAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasLawVltnPdstAcdntDstrctMapper {

    public List<TaasLawVltnPdstAcdntDstrct> findAll();

}

package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.TaasAdsiAcdntDstrct;
import com.neighbor21.ggits.common.entity.TaasBcyclAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasBcyclAcdntDstrctMapper {

    public List<TaasBcyclAcdntDstrct> findAll();

}
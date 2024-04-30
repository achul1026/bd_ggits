package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.TaasFrostAcdntDstrct;

@Mapper
public interface TaasFrostAcdntDstrctMapper {

    public List<TaasFrostAcdntDstrct> findAll();
}

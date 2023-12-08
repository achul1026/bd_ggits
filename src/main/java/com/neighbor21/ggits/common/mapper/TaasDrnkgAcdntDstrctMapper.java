package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.TaasDrnkgAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasDrnkgAcdntDstrctMapper {

    public List<TaasDrnkgAcdntDstrct> findAll();

}

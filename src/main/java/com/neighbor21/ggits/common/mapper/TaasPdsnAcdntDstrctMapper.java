package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.TaasPdsnAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasPdsnAcdntDstrctMapper {

    public List<TaasPdsnAcdntDstrct> findAll();

}

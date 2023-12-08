package com.neighbor21.ggits.common.mapper;
import com.neighbor21.ggits.common.entity.TaasDrnkgAcdntDstrct;
import com.neighbor21.ggits.common.entity.TaasFrostAcdntDstrct;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TaasFrostAcdntDstrctMapper {

    public List<TaasFrostAcdntDstrct> findAll();
}

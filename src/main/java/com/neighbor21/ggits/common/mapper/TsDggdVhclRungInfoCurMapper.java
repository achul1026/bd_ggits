package com.neighbor21.ggits.common.mapper;


import com.neighbor21.ggits.common.entity.TsDggdVhclRungInfoCur;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface TsDggdVhclRungInfoCurMapper {

    List<TsDggdVhclRungInfoCur> findAll();

    List<TsDggdVhclRungInfoCur> findAllByRungPlanYnIsY();

    List<TsDggdVhclRungInfoCur> findAllByRungPlanYnIsYByWarning();
}

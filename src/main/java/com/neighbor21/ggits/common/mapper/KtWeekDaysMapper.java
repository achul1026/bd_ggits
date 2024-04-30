package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.KtWeekdays;

@Mapper
public interface KtWeekDaysMapper {

	public List<KtWeekdays> findAllByRecent();
}

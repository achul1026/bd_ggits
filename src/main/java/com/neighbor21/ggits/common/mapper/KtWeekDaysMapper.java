package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.KtTimeZn;
import com.neighbor21.ggits.common.entity.KtWeekdays;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface KtWeekDaysMapper {

	public List<KtWeekdays> findAllByRecent();
}

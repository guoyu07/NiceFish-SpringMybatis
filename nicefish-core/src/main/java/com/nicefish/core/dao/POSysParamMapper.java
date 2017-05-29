package com.nicefish.core.dao;


import com.nicefish.common.utils.BaseMapper;
import com.nicefish.core.po.POSysParam;

import java.util.List;

public interface POSysParamMapper extends BaseMapper<POSysParam, String> {

	List<POSysParam> findAll();
	
	POSysParam findSysParam(String paramKey);
	
}

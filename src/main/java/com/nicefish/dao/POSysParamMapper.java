package com.nicefish.dao;

import java.util.List;

import com.nicefish.po.POSysParam;
import com.nicefish.utils.BaseMapper;

public interface POSysParamMapper extends BaseMapper<POSysParam, String>{

	List<POSysParam> findAll();
	
	POSysParam findSysParam(String paramKey);
	
}

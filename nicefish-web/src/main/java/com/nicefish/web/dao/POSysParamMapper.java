package com.nicefish.web.dao;

import java.util.List;

import com.nicefish.web.po.POSysParam;
import com.nicefish.web.utils.mybatis.BaseMapper;

public interface POSysParamMapper extends BaseMapper<POSysParam, String> {

	List<POSysParam> findAll();
	
	POSysParam findSysParam(String paramKey);
	
}

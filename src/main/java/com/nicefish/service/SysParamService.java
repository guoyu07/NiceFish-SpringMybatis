package com.nicefish.service;

import java.util.List;

import com.nicefish.po.POSysParam;

public interface SysParamService {
	public int insert(POSysParam sysParam);

	public List<POSysParam> findAllParams();

	public POSysParam findByParamKey(String id);

	public int delete(String paramKey);

	public int update(POSysParam sysParam);

}

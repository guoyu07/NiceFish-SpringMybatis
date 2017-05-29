package com.nicefish.core.service;


import com.nicefish.core.po.POSysParam;

import java.util.List;

public interface SysParamService {
	public int insert(POSysParam sysParam);

	public List<POSysParam> findAllParams();

	public POSysParam findByParamKey(String id);

	public int delete(String paramKey);

	public int update(POSysParam sysParam);

}

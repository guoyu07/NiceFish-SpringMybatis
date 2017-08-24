package com.nicefish.web.service.impl;

import com.nicefish.web.dao.POSysParamMapper;
import com.nicefish.web.po.POSysParam;
import com.nicefish.web.service.SysParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysParamService")
public class SysParamServiceImpl implements SysParamService {

	@Resource
	private POSysParamMapper sysParamMapper;

	public int insert(POSysParam sysParam){
		return sysParamMapper.insertSelective(sysParam);
	}

	public POSysParam findByParamKey(String id){
		return sysParamMapper.selectByPrimaryKey(id);
	}

	public int delete(String paramKey){
		return sysParamMapper.deleteByPrimaryKey(paramKey);
	}

	public int update(POSysParam sysParam){
		return sysParamMapper.updateByPrimaryKeySelective(sysParam);
	}

	public List<POSysParam> findAllParams() {
		return sysParamMapper.findAll();
	}
}

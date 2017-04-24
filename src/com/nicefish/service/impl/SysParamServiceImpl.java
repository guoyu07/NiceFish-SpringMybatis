package com.nicefish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POSysParamMapper;
import com.nicefish.po.POSysParam;
import com.nicefish.service.SysParamService;

@Service("sysParamService")
public class SysParamServiceImpl implements SysParamService {

	@Autowired
	private POSysParamMapper sysParamMapper;

	@Override
	public int insert(POSysParam sysParam){
		return sysParamMapper.insertSelective(sysParam);
	}
	
	@Override
	public POSysParam findByParamKey(String id){
		return sysParamMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(String paramKey){
		return sysParamMapper.deleteByPrimaryKey(paramKey);
	}

	@Override
	public int update(POSysParam sysParam){
		return sysParamMapper.updateByPrimaryKeySelective(sysParam);
	}

	@Override
	public List<POSysParam> findAllParams() {
		return sysParamMapper.findAll();
	}
}

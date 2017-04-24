package com.nicefish.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POPostMapper;
import com.nicefish.po.POPost;
import com.nicefish.po.POSysParam;
import com.nicefish.service.PostService;
import com.nicefish.service.SysParamService;
import com.nicefish.utils.WebUtil;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private POPostMapper postMapper;

	@Override
	public int insert(POPost model) {
		return postMapper.insertSelective(model);
	}
	
	@Override
	public POPost findById(String id) {
		return postMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<POPost> findAll() {
		return postMapper.findAll();
	}
	
	@Override
	public int delete(String id) {
		return postMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<POPost> findByTitle(String key) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(key!=null&&!key.equals(null)){
			map.put("key", key);
		}
		return postMapper.findByTitle(map);
	}

	@Override
	public String getTotalPages() {
		int totalCount=postMapper.selectCount();
		POSysParam poSysParam=sysParamService.findByParamKey("POST_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		return WebUtil.calcPages(totalCount+"", pageSize);
	}

	@Override
	public List<POPost> selectPostsByPage(String currentPage) {
		POSysParam poSysParam=sysParamService.findByParamKey("POST_PAGE_NUM");
		String pageSize=poSysParam.getParamValue();
		
		String[] pageParams=WebUtil.parseStartLimit(currentPage,pageSize);
		String start=pageParams[0];
		String limit=pageParams[1];
		
		return postMapper.selectByPage(start,limit);
	}
}

package com.nicefish.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POPostMapper;
import com.nicefish.po.POPost;
import com.nicefish.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {

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
	public int selectCount() {
		return postMapper.selectCount();
	}

	@Override
	public List<POPost> selectByPage(int beginRow, int pageSize) {
		return postMapper.selectByPage(beginRow, pageSize);
	}
}

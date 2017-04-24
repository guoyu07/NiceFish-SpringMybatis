package com.nicefish.service;

import java.util.List;

import com.nicefish.po.POPost;

public interface PostService {
	
	public int insert(POPost model);
	
	public POPost findById(String id);
	
	public List<POPost> findAll();
	
	public int delete(String id);
	
	public List<POPost> findByTitle(String key);
	
	public int selectCount();
	
	public List<POPost> selectByPage(int beginRow,int pageSize);
}

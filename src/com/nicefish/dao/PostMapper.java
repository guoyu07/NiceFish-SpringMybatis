package com.nicefish.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nicefish.model.Post;
import com.nicefish.util.base.BaseMapper;

public interface PostMapper extends BaseMapper<Post, String>{
    
	List<Post> findAll();
	
	List<Post> findByTitle(Map<String, Object> map);

	List<Post> selectByPage(@Param("beginRow")int beginRow,@Param("pageSize")int pageSize);

	int selectCount();
}
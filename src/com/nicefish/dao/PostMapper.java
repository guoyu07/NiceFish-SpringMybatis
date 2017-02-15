package com.nicefish.dao;

import java.util.List;

import com.nicefish.model.Post;
import com.nicefish.util.base.BaseMapper;

public interface PostMapper extends BaseMapper<Post, String>{
    
	List<Post> findAll();
}
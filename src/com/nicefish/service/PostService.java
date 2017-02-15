package com.nicefish.service;

import java.util.List;

import com.nicefish.model.Post;

public interface PostService {
	
	public int insert(Post model);
	
	public Post findById(String id);
	
	public List<Post> findAll();
	
	public int delete(String id);
	
	public List<Post> findByTitle(String key);
}

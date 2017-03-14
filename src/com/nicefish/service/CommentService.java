package com.nicefish.service;

import java.util.List;

import com.nicefish.model.Comment;

public interface CommentService {
	
	public int insert(Comment model);
	
	public Comment findById(String id);
	
	public int delete(String id);
	
	public List<Comment> findByPostId(String postId);
}

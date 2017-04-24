package com.nicefish.service;

import java.util.List;

import com.nicefish.po.POComment;

public interface CommentService {
	
	public int insert(POComment model);
	
	public POComment findById(String id);
	
	public int delete(String id);
	
	public List<POComment> findByPostId(String postId);
}

package com.nicefish.dao;

import java.util.List;

import com.nicefish.model.Comment;
import com.nicefish.util.base.BaseMapper;

public interface CommentMapper extends BaseMapper<Comment, String>{
    
	List<Comment> findByPostId(String postId);
}
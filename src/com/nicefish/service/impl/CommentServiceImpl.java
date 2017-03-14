package com.nicefish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.CommentMapper;
import com.nicefish.model.Comment;
import com.nicefish.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int insert(Comment model) {
		return commentMapper.insertSelective(model);
	}

	@Override
	public Comment findById(String id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(String id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Comment> findByPostId(String postId) {
		return commentMapper.findByPostId(postId);
	}
}

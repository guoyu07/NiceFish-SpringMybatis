package com.nicefish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.CommentMapper;
import com.nicefish.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private CommentMapper commentMapper;

	public CommentMapper getCommentMapper() {
		return commentMapper;
	}
	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
}

package com.nicefish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POCommentMapper;
import com.nicefish.po.POComment;
import com.nicefish.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private POCommentMapper commentMapper;

	@Override
	public int insert(POComment model) {
		return commentMapper.insertSelective(model);
	}

	@Override
	public POComment findById(String id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(String id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<POComment> findByPostId(String postId) {
		return commentMapper.findByPostId(postId);
	}
}

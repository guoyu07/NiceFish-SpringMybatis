package com.nicefish.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.POCommentMapper;
import com.nicefish.po.POComment;
import com.nicefish.po.POUser;
import com.nicefish.service.CommentService;
import com.nicefish.service.UserService;
import com.nicefish.vo.VONewComment;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private POCommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Override
	public int newComment(VONewComment voNewComment)
			throws IllegalAccessException, InvocationTargetException {
		POComment poComment = new POComment();
		BeanUtils.copyProperties(poComment, voNewComment);

		POUser poUser = userService.findById(voNewComment.getUserId());
		BeanUtils.copyProperties(poComment, poUser);

		poComment.setCommentId(UUID.randomUUID().toString());
		return commentMapper.insertSelective(poComment);
	}

	@Override
	public POComment getCommentById(String id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delCommentById(String id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<POComment> getCommentListByPostId(String postId) {
		return commentMapper.findByPostId(postId);
	}
}

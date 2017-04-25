package com.nicefish.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.nicefish.po.POComment;
import com.nicefish.vo.VONewComment;

public interface CommentService {

	public POComment getCommentById(String commentId);

	public List<POComment> getCommentListByPostId(String postId);

	public int newComment(VONewComment voNewComment)
			throws IllegalAccessException, InvocationTargetException;

	public int delCommentById(String commentId);

}

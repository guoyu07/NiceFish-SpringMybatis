package com.nicefish.web.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.nicefish.web.po.POComment;
import com.nicefish.web.vo.VONewComment;

public interface CommentService {

	public POComment getCommentById(String commentId);

	public List<POComment> getCommentListByPostId(String postId);

	public List<POComment> getCommentByPostIdAndPage(String postId,
			String pageIndex);

	public Map<String, Object> getPagerParam(String postId);

	public int newComment(VONewComment voNewComment)
			throws IllegalAccessException, InvocationTargetException;

	public int delCommentById(String commentId);

}

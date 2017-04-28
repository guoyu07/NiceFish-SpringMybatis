package com.nicefish.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.service.CommentService;
import com.nicefish.vo.VONewComment;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	@ResponseBody
	public Object PostFindByPid(@PathVariable("postId") String postId)
			throws Exception {
		return commentService.getCommentListByPostId(postId);
	}

	@RequestMapping(value = "/newComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> newComment(@RequestBody VONewComment voNewComment,
			HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		commentService.newComment(voNewComment);
		return this.ajaxSuccessResponse();
	}

	@RequestMapping(value = "/delComment/{commentId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delComment(@PathVariable("commentId") String commentId) {
		commentService.delCommentById(commentId);
		return this.ajaxSuccessResponse();
	}
}

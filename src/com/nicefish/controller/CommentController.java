package com.nicefish.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicefish.po.POComment;
import com.nicefish.service.CommentService;
import com.nicefish.vo.VONewComment;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{postid}", method = RequestMethod.GET)
	@ResponseBody
	public String PostFindByPid(@PathVariable("postid") String postid)
			throws Exception {
		List<POComment> list = commentService.getCommentListByPostId(postid);
		return this.writeJSON("comments", list);
	}

	@RequestMapping(value = "/newComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> newComment(VONewComment voNewComment,
			HttpServletRequest request, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		commentService.newComment(voNewComment);
		return this.ajaxSuccessResponse();
	}
}

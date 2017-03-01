package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.CommentService;

@Controller
public class CommentController {
	private CommentService commentService;

	public CommentService getCommentService() {
		return commentService;
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}

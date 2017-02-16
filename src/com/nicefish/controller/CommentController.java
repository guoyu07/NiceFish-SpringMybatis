package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;

}

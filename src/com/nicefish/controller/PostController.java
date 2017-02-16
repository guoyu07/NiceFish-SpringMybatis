package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;

}

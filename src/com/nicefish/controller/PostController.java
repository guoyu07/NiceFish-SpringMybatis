package com.nicefish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nicefish.service.PostService;

@Controller
public class PostController {
	private PostService postService;

	public PostService getPostService() {
		return postService;
	}
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
}

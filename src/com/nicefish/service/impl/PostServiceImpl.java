package com.nicefish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.dao.PostMapper;
import com.nicefish.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {
	private PostMapper postMapper;

	public PostMapper getPostMapper() {
		return postMapper;
	}
	@Autowired
	public void setPostMapper(PostMapper postMapper) {
		this.postMapper = postMapper;
	}
}

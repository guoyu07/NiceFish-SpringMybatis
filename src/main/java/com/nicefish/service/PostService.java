package com.nicefish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicefish.entity.Post;
import com.nicefish.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> listByUserId(Long userId) {
		return repo.findByUserId(userId);
	}
	
	public void save(Post post) {
		repo.save(post);
	}
}

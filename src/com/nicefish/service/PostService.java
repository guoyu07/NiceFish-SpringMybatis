package com.nicefish.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.nicefish.po.POPost;
import com.nicefish.vo.VONewPost;

public interface PostService {

	public POPost getPostById(String postId);

	public List<POPost> getAllPosts();

	public List<POPost> getPostListByPage(String currentPage);

	public List<POPost> getPostByTitle(String postTitle);

	public String newPost(VONewPost voNewPost) throws IllegalAccessException,
			InvocationTargetException;

	public int deleteById(String postId);

	public String getTotalPages();

}

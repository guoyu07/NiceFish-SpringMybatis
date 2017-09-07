package com.nicefish.web.service;

import com.nicefish.web.po.POPost;
import com.nicefish.web.vo.VONewPost;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface PostService {

	POPost getPostById(String postId);

	List<POPost> getAllPosts();

	List<POPost> getPostListByPage(String currentPage);

	List<POPost> getPostByTitle(String postTitle);

	String newPost(VONewPost voNewPost) throws IllegalAccessException,InvocationTargetException;

	int deleteById(String postId);

	String getTotalPages();

	String getTotalItemsNum();

	Map<String,Object> getPagerParam();

	Map<String,Object> getPagerParamByUserId(String userId);

    List<POPost> getPostByUserId(String userId, String currentPage);

	int selectCountByUserId(String userId);

	void readTimesPlusOne(String postId);

	void commentTimesPlusOne(String postId);
}

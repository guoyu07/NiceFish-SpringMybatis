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

	String newPost(VONewPost voNewPost) throws IllegalAccessException,
			InvocationTargetException;

	int deleteById(String postId);

	String getTotalPages();
	
	String getTotalItemsNum();
	
	Map<String,Object> getPagerParam();

	/**
	 * <p>
	 *     根据用户ID查询文章
	 * </p>
	 * @param userId String 用户ID
	 * @param currentPage String 当前页
	 * @return List 文章集合
	 */
    List<POPost> getPostByUserId(String userId, String currentPage);

	Long countByUserId(String userId);

	void readTimesPlusOne(String postId);

	void commentTimesPlusOne(String postId);
}

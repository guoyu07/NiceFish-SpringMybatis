package com.nicefish.core.service;


import com.nicefish.core.po.POPost;
import com.nicefish.core.vo.VONewPost;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface PostService {

	public POPost getPostById(String postId);

	public List<POPost> getAllPosts();

	public List<POPost> getPostListByPage(String currentPage);

	public List<POPost> getPostByTitle(String postTitle);

	public String newPost(VONewPost voNewPost) throws IllegalAccessException,
			InvocationTargetException;

	public int deleteById(String postId);

	public String getTotalPages();
	
	public String getTotalItemsNum();
	
	public Map<String,Object> getPagerParam();

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
}

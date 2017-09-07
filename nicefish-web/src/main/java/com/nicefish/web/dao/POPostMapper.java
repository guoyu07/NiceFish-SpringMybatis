package com.nicefish.web.dao;

import com.nicefish.web.po.POPost;
import com.nicefish.web.utils.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface POPostMapper extends BaseMapper<POPost, String> {
    
	List<POPost> findAll();
	
	List<POPost> findByTitle(Map<String, Object> map);

	List<POPost> selectByPage(@Param("beginRow")int beginRow,@Param("pageSize")int pageSize);

	int selectCount();

    List<POPost> getPostByUserId(@Param("userId") String userId, @Param("start") int start, @Param("limit") int limit);

	int selectCountByUserId(@Param("userId") String userId);

	void readTimesPlusOne(@Param("postId") String postId);

	void commentTimesPlusOne(@Param("postId") String postId);
}
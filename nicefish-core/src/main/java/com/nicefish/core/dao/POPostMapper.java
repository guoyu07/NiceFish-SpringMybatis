package com.nicefish.core.dao;

import com.nicefish.common.utils.BaseMapper;
import com.nicefish.core.po.POPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface POPostMapper extends BaseMapper<POPost, String> {
    
	List<POPost> findAll();
	
	List<POPost> findByTitle(Map<String, Object> map);

	List<POPost> selectByPage(@Param("beginRow") int beginRow, @Param("pageSize") int pageSize);

	int selectCount();

    List<POPost> getPostByUserId(@Param("userId") String userId, @Param("start") int start, @Param("limit") int limit);

	Long countByUserId(@Param("userId") String userId);
}
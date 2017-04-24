package com.nicefish.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nicefish.po.POPost;
import com.nicefish.utils.BaseMapper;

public interface POPostMapper extends BaseMapper<POPost, String>{
    
	List<POPost> findAll();
	
	List<POPost> findByTitle(Map<String, Object> map);

	List<POPost> selectByPage(@Param("beginRow")String beginRow,@Param("pageSize")String pageSize);

	int selectCount();
}